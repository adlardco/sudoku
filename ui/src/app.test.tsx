import React from 'react';
import ReactDOM from 'react-dom';
import App from './app';
import './setupEnzyme';
import { mount, ReactWrapper } from 'enzyme';
import Title from './components/title';
import BuildTime from './components/buildTime';
import StatusBar from './components/statusBar';
import { Message } from './components/message';
import GridModel from './model/gridModel';

describe('App', () => {

  let wrapper: ReactWrapper;

  beforeEach(() => {
    wrapper = mount(<App/>);    
  });

  it('renders without crashing', () => {
    const div = document.createElement('div');
    ReactDOM.render(<App />, div);
    ReactDOM.unmountComponentAtNode(div);
  });

  it('has title', () => {
    expect(wrapper.find(Title).props()).toEqual({ 'id': 'title'});
  });

  it('has build time', () => {
    expect(wrapper.find(BuildTime).props()).toEqual({ 
      'id': 'buildTime',
      'show': false
    });
  });

  it('has status bar', () => {
    expect(wrapper.find(StatusBar).props()).toEqual({ 
      'id': 'statusBar',
      'message': Message.SelectCell,
      'error': false
    });
  });

  it('has selector', () => {
    const selector = wrapper.find('Grid#selector');
    expect(selector.prop('id')).toEqual('selector');
    expect(selector.prop('cellSize')).toEqual(5.3);
    expect(selector.prop('disabled')).toBeTruthy();
    expect(selector.prop('numRows')).toEqual(2);
    expect(selector.prop('grid')).toEqual(new GridModel(Array.from(Array(10).keys())));
  });

  it('has solve button', () => {
    expect(wrapper.find('Button#solveButton').prop('id')).toEqual('solveButton');
    expect(wrapper.find('Button#solveButton').prop('text')).toEqual('Solve');
    expect(wrapper.find('Button#solveButton').prop('disabled')).toBeTruthy();
  });

  it('has clear button', () => {
    expect(wrapper.find('Button#clearButton').prop('id')).toEqual('clearButton');
    expect(wrapper.find('Button#clearButton').prop('text')).toEqual('Clear');
    expect(wrapper.find('Button#clearButton').prop('disabled')).toBeFalsy();
  });

  it('has grid', () => {
    const gridId = 'Grid#grid';
    expect(wrapper.find(gridId).prop('id')).toEqual('grid');
    expect(wrapper.find(gridId).prop('cellSize')).not.toBeDefined();
    expect(wrapper.find(gridId).prop('disabled')).toBeFalsy();
    expect(wrapper.find(gridId).prop('numRows')).not.toBeDefined();
    expect(wrapper.find(gridId).prop('grid')).toEqual(new GridModel(Array<number>(81).fill(0)));
    expect(wrapper.find(gridId).prop('selectedIndex')).not.toBeDefined();
  });

  it('selects grid cells', () => {
    const gridId = 'Grid#grid';
    expect(wrapper.find(gridId).prop('disabled')).toBeFalsy();
    const id = 'Row#grid button#cell-4';
    wrapper.find(gridId).prop('cellSelected')
    wrapper.find(id).simulate('click', { preventDefault: () => {} });
    expect(wrapper.find(gridId).prop('disabled')).toBeFalsy();
    expect(wrapper.find(gridId).prop('selectedIndex')).toEqual(4);
  });

  it('clears grid cells', () => {
    const gridId = 'Grid#grid';
    const cellId = gridId + ' button#cell-4';
    wrapper.find(cellId).simulate('click', { preventDefault: () => {} });
    wrapper.find('button#clearButton').simulate('click', { preventDefault: () => {} });
    expect(wrapper.find(gridId).prop('disabled')).toBeFalsy();
    expect(wrapper.find(gridId).prop('selectedIndex')).not.toBeDefined();
  });

  it('sets grid cell values', () => {
    const gridId = 'Grid#grid';
    const selectorId = 'Grid#selector';
    expect(wrapper.find(StatusBar).prop('message')).toEqual(Message.SelectCell);
    // select cell
    const cellId = gridId + ' button#cell-4';
    wrapper.find(cellId).simulate('click', { preventDefault: () => {} });
    expect(wrapper.find(gridId).prop('disabled')).toBeFalsy();
    expect(wrapper.find(gridId).prop('selectedIndex')).toEqual(4);
    expect(wrapper.find(StatusBar).prop('message')).toEqual(Message.SelectNumber);
    expect(wrapper.find(selectorId).prop('disabled')).toBeFalsy();
    //we cannot solve because no cell values set
    expect(wrapper.find('Button#solveButton').prop('disabled')).toBeTruthy();
    expect(wrapper.find('Button#clearButton').prop('disabled')).toBeFalsy();

    // select 3
    wrapper.find(selectorId + ' button#cell-3').simulate('click', { preventDefault: () => {} });
    expect(wrapper.find(gridId).prop('disabled')).toBeFalsy();
    expect(wrapper.find(selectorId).prop('disabled')).toBeTruthy();
    expect(wrapper.find(gridId).prop('selectedIndex')).not.toBeDefined();
    expect(wrapper.find(cellId).text()).toEqual('3');
    expect(wrapper.find(StatusBar).prop('message')).toEqual(Message.SolveOrClear);

    // as we have now set a cell value we can solve
    expect(wrapper.find('Button#solveButton').prop('disabled')).toBeFalsy();
    expect(wrapper.find('Button#clearButton').prop('disabled')).toBeFalsy();

    // select the cell again and clear value
    wrapper.find(cellId).simulate('click', { preventDefault: () => {} });
    wrapper.find(selectorId + ' button#cell-0').simulate('click', { preventDefault: () => {} });
    expect(wrapper.find(StatusBar).prop('message')).toEqual(Message.SelectCell);
    wrapper.find(cellId).simulate('click', { preventDefault: () => {} });
    expect(wrapper.find(StatusBar).prop('message')).toEqual(Message.SelectNumber);

    // as we have now cleared the cell values we cannot solve
    expect(wrapper.find('Button#solveButton').prop('disabled')).toBeTruthy();
    expect(wrapper.find('Button#clearButton').prop('disabled')).toBeFalsy();
  });
});
