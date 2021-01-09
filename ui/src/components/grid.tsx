import React from 'react';
import Row from './row';
import GridModel from '../model/gridModel';

export default class Grid extends React.Component<{
  id: string;
  grid: GridModel;
  cellSelected: (index: number) => void;
  disabled?: boolean;
  numRows?: number;
  cellSize?: number;
  selectedIndex?: number;
}, {}> {

  private static readonly VisibleBorderStyle = 'solid';
  private static readonly HiddenBorderStyle = 'hidden';

  render(): React.ReactElement<React.ReactNode> {
    const numCells = this.props.grid.cellValues.length;
    if(numCells < 1) {
      return (<div></div>);
    }
    const indexes = Array.from(Array(numCells).keys());
    const numRows = this.numRows();
    const numCols = Math.floor(numCells / numRows);
    const cellSize = this.props.cellSize === undefined ? 4 : this.props.cellSize;
    const width = numCols * cellSize;
    const height = numRows * cellSize;
    return (
      <Row 
        id={this.props.id} 
        wrap 
        width={width + 'vw'} 
        height={height + 'vw'}>
        {indexes.map(index => 
          <button 
            id={'cell-' + index}
            key={index}
            onClick={() => this.props.cellSelected(index)}
            style={{
              color: this.props.disabled ? '#777' : '#000',
              backgroundColor: this.backgroundColor(index),
              borderStyle: this.border(index),
              width: cellSize + 'vw',
              height: cellSize + 'vw', 
              display: 'inline-flex',
              alignItems: 'center',
              justifyContent: 'center',
              textDecoration: 'none',
              margin: 0,
              padding: 0,
              borderRadius: 0,
              outline: 0,
              fontSize: (0.75 * cellSize) + 'vw',
              cursor: this.props.disabled ? 'inherit' : 'pointer'
          }}>
            {this.cellValue(index)}
          </button>
        )}
      </Row>
    );
  }

  private numRows() { return this.props.numRows || Math.floor(Math.sqrt(this.props.grid.cellValues.length)); }
  private toBorderStyle(visible: boolean): string { return visible ? Grid.VisibleBorderStyle : Grid.HiddenBorderStyle; }
  private cellIndexIsLast(index: number): boolean { return index === this.numRows() - 1; }

  private backgroundColor(index: number): string {
    return this.props.selectedIndex !== undefined && this.props.selectedIndex === index ? 
        '#ff7' : (index % 2 === 0 ? '#7ff' : '#0ff');
  }

  private cellValue(index: number): string {
    const value = this.props.grid.cellValues[index];
    return value > 0 ? '' + value : '';
  }

  private border(index: number): string {
    if(this.props.numRows !== undefined) {
      return Grid.HiddenBorderStyle;
    }
    const rowIndex = Math.floor(index / this.numRows());
    const colIndex = index % this.numRows();
    return `${this.toBorderStyle(this.subCellIndexIsZero(rowIndex))} ${this.toBorderStyle(this.cellIndexIsLast(colIndex))} ${this.toBorderStyle(this.cellIndexIsLast(rowIndex))} ${this.toBorderStyle(this.subCellIndexIsZero(colIndex))}`;
  }

  private subCellIndexIsZero(index: number): boolean {
    const subGridSize = Math.sqrt(this.numRows());
    return index % subGridSize === 0;
  }
}