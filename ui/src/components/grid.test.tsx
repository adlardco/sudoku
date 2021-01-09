import React from 'react';
import { mount, ReactWrapper } from 'enzyme';
import '../setupEnzyme';
import Grid from './grid';
import GridModel from '../model/gridModel';

describe('Grid', () => {

  let wrapper: ReactWrapper;
  let gridModel: GridModel;

  beforeEach(() => {
    gridModel = new GridModel([
      0, 2, 3, 4,
      4, 3, 2, 1,
      2, 1, 4, 3,
      3, 4, 1, 2
    ]);
    wrapper = mount(<Grid id='id' grid={gridModel} cellSelected={() => {}}/>);
  });

  it('has correct id', () => {
    expect(wrapper.find('Group#id').prop('id')).toEqual('id');
  });

  it('has correct cell content', () => {
    expect(wrapper.find('#id').children().filter('button').length).toEqual(16);
    const indexes = Array.from(Array(gridModel.cellValues.length).keys());
    indexes.forEach(index => {
      const cellId = `#cell-${index}`;
      const cellValue = gridModel.cellValues[index];
      const expectedText = cellValue === 0 ? '' : '' + cellValue;
      expect(wrapper.find(cellId).prop('id')).toEqual(`cell-${index}`);
      expect(wrapper.find(cellId).text()).toEqual(expectedText);
    });
  });

  it('calls back if cell clicked', () => {
    const indexes = Array.from(Array(gridModel.cellValues.length).keys());
    let clickedCellIndex: number | undefined = undefined;
    const callback = (index: number) => {clickedCellIndex = index}
    wrapper.setProps({ cellSelected: callback });
    indexes.forEach(index => {
      const cellId = `#cell-${index}`;
      wrapper.find(cellId).simulate('click', { preventDefault: () => {} });
      expect(clickedCellIndex).toEqual(index);
      clickedCellIndex = undefined;
    });
  });

  it('has default style', () => {
    const indexes = Array.from(Array(gridModel.cellValues.length).keys());
    const numRows = Math.floor(Math.sqrt(indexes.length));
    wrapper.setProps({ selectedIndex: 4 });
    indexes.forEach(index => {
      const cellId = `#cell-${index}`;
      const expectedBackgroundColor = index === 4 ? '#ff7' : (index % 2 === 0 ? '#7ff' : '#0ff');
      const rowIndex = Math.floor(index / numRows);
      const colIndex = index % numRows;
      const subGridSize = Math.floor(Math.sqrt(numRows));
      const topStyle = rowIndex % subGridSize === 0 ? 'solid' : 'hidden';
      const rightStyle = colIndex === numRows - 1 ? 'solid' : 'hidden';
      const bottomStyle = rowIndex === numRows - 1 ? 'solid' : 'hidden';
      const leftStyle = colIndex % subGridSize === 0 ? 'solid' : 'hidden';
      const expectedBorder = `${topStyle} ${rightStyle} ${bottomStyle} ${leftStyle}`;
      expect(wrapper.find(cellId).prop('style')).toEqual({
        'alignItems': 'center',
        'backgroundColor': expectedBackgroundColor,
        'borderRadius': 0,
        'borderStyle': expectedBorder,
        'color': '#000',
        'cursor': 'pointer',
        'display': 'inline-flex',
        'fontSize': '3vw',
        'height': '4vw',
        'justifyContent': 'center',
        'margin': 0,
        'outline': 0,
        'padding': 0,
        'textDecoration': 'none',
        'width': '4vw'
      });
    });
  });

  it('has disabled style if disabled', () => {
    wrapper.setProps({ disabled: true });
    const indexes = Array.from(Array(gridModel.cellValues.length).keys());
    indexes.forEach(index => {
      const cellId = `#cell-${index}`;
      expect(wrapper.find(cellId).prop('style')?.color).toEqual('#777');
    });
  });
});
