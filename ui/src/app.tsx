import React from 'react';
import ReactGA from 'react-ga';
import Row from './components/row';
import Column from './components/column';
import Button from './components/button';
import Title from './components/title';
import StatusBar from './components/statusBar';
import Grid from './components/grid';
import BuildTime from './components/buildTime';
import GridModel from './model/gridModel';
import RestControllerImpl from './controller/restControllerImpl';
import GridController from './controller/gridController';
import InfoController from './controller/infoController';
import GoogleController from './controller/googleController';
import InfoModel from './model/infoModel';
import { Message } from './components/message';
import Timer from './timer';
import FeatureFlag from './featureFlag';

export default class App extends React.Component<{}, {
  updating: boolean;
  grid: GridModel;
  selectedIndex?: number;
  message: Message;
  error: boolean;
  internetAccess: boolean;
  connected: boolean;
}> {

  private static readonly GridSize = 9;
  private static readonly NumCells = App.GridSize * App.GridSize;
  private static readonly LocalHost = 'localhost';
  private static readonly LocalPort = 8080;
  private static readonly ServerContext = 'api';
  private static readonly GoogleUrl = new URL('https://www.google.com');
  private static readonly ServerTimeout = 15000;
  private static readonly GoogleTimeout = 5000;
  private static readonly GoggleTimerInterval = 5000;
  private static readonly InfoCheckTimerInterval = 20000;
  private static readonly GACode = 'UA-184114069-1';
  private readonly googleController: GoogleController;
  private readonly infoController: InfoController;
  private readonly gridController: GridController;
  private readonly googleTimer: Timer;
  private readonly infoCheckTimer: Timer;

  constructor(props: {}) {
    super(props);
    this.state = { 
      updating: false, 
      grid: this.newGrid(), 
      selectedIndex: undefined,
      message: Message.SelectCell,
      error: false,
      internetAccess: false,
      connected: false
    };
    this.googleController = new GoogleController(new RestControllerImpl(App.GoogleUrl, App.GoogleTimeout));
    const url = this.url();
    const restController = new RestControllerImpl(url, App.ServerTimeout);
    this.infoController = new InfoController(restController);
    this.gridController = new GridController(restController);
    this.googleTimer = new Timer(App.GoggleTimerInterval, this.googleCheck);
    this.infoCheckTimer = new Timer(App.InfoCheckTimerInterval, this.infoCheck);
  }

  componentDidMount() {
    this.initGA();
    this.googleTimer.start();
    this.infoCheckTimer.start();
  }

  initGA() {
    try {
      ReactGA.initialize(App.GACode, {debug: false});
      ReactGA.pageview('/');
    } catch(err) {
      console.log("Failed to initialize GA");
    }
}

  componentWillUnmount() {
    this.googleTimer.stop();
    this.infoCheckTimer.stop();
  }
  
  render(): React.ReactElement<React.ReactNode> {
    const selectorGrid = new GridModel(Array.from(Array(App.GridSize + 1).keys()));
    return (
      <div style={{
        cursor: this.state.updating ? 'wait' : 'auto', 
        userSelect: 'none',
        margin: '6vw auto auto auto', 
        width: '66vw', 
        height: '33vw'
      }}>
        <Row id='mainRow' spaceAround>
          <Column id='controlColumn' spaceAround width='auto'>
            <Title id='title'/>
            <BuildTime id='buildTime' show={this.showBuildTime()}/>
            <StatusBar id='statusBar' message={this.state.message} error={this.state.error}/>
            <Grid id='selector' 
                  grid={selectorGrid} 
                  numRows={2}
                  cellSize={5.3}
                  cellSelected={this.valueSelected} 
                  disabled={this.selectorDisabled()}/>
            <Row id='buttons' width='100%' height='5vw'>
              <Button id='solveButton' text='Solve' action={this.solve} disabled={this.solveDisabled()}/>
              <Button id='clearButton' text='Clear' action={this.clear} disabled={this.clearDisabled()} />
            </Row>
          </Column>
          <Column id='gridColumn'>
            <Grid id='grid'
                  grid={this.state.grid} 
                  selectedIndex={this.state.selectedIndex} 
                  cellSelected={this.cellSelected} 
                  disabled={this.state.updating} 
            />
          </Column>
        </Row>
      </div>
    );
  }

  private googleCheck = () => { this.googleController.ping(this.googleResponse); }
  private infoCheck = () => { this.infoController.getInfo(this.infoOk, this.infoError); }
  private newGrid(): GridModel { return new GridModel(Array<number>(App.NumCells).fill(0)); }
  private googleResponse = (ok: boolean) => { this.setState({ internetAccess: ok }) }
  private infoOk = (info: InfoModel) => { this.setState({ connected: info.status === 'OK' }); }
  private infoError = (e: Error) => { this.setState({ connected: false }); }
  private selectorDisabled(): boolean { return this.state.updating || this.state.selectedIndex === undefined; }
  private solveDisabled(): boolean { return this.state.updating || this.gridIsInvalid(this.state.grid); }
  private clearDisabled(): boolean { return this.state.updating; }
  private gridHasValue(grid: GridModel): boolean { return grid.cellValues.some(value => value > 0); }
  private gridIsSolved(grid: GridModel): boolean { return !grid.cellValues.some(value => value < 1); }

  private url(): URL {
    const hostAndPort = window.location.hostname === App.LocalHost ? `${App.LocalHost}:${App.LocalPort}` : window.location.host;
    const text = `${window.location.protocol}//${hostAndPort}/${App.ServerContext}/`;
    return new URL(text);
  }
  
  private showBuildTime(): boolean { 
    const flag = new FeatureFlag('showBuildTime');
    return flag.isEnabled();
  }

  private clear = () => {
    this.setState({ 
      grid: this.newGrid(),
      selectedIndex: undefined,
      message: Message.SelectCell, 
      error: false  
    });
  }

  private solve = () => {
    this.setState({ 
      updating: true, 
      message: Message.Solving, 
      selectedIndex: undefined,
      error: false  
    });
    this.gridController.solve(this.state.grid, this.gridSolved, this.error);
  }

  private gridSolved = (grid: GridModel) => {
    const invalid = this.gridIsInvalid(grid);
    const solved = !invalid && this.gridIsSolved(grid);
    this.setState({
      updating: false, 
      grid: solved ? grid : this.state.grid, 
      selectedIndex: undefined,
      message: invalid ? Message.ErrorSolving : (solved ? Message.Solved : Message.CannotSolve),
      error: !solved
    });
  }

  private error = (e: Error) => {
    console.log(e.message);
    this.setState({
      updating: false, 
      selectedIndex: undefined,
      message: this.state.internetAccess ? 
          (this.state.connected ? Message.ErrorCallingServer : Message.CannotConnectToServer) :
          Message.NoInternet,
      error: true
    });
  }

  private cellSelected = (index: number) => {
    this.setState({ 
      selectedIndex: index,
      error: false,
      message: this.gridHasValue(this.state.grid) ? Message.SolveOrClear : Message.SelectNumber
    });
  }

  private valueSelected = (value: number) => {
    if(this.state.selectedIndex === undefined) {
        return;
    }
    const grid = this.state.grid;
    grid.cellValues[this.state.selectedIndex] = value;
    this.setState({ 
      grid: grid,
      error: false,
      selectedIndex: undefined,
      message: this.gridHasValue(grid) ? Message.SolveOrClear : Message.SelectCell
    });
  }

  private gridIsInvalid(grid: GridModel): boolean {
    if(!this.gridHasValue(grid)) {
      return true;
    }
    const numCells = this.state.grid.cellValues.length;
    const newNumCells = grid.cellValues.length;
    return newNumCells <= 1 || (numCells > 0 && newNumCells !== numCells);
  }
}