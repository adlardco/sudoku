import React from 'react';
import '../setupEnzyme';
import { shallow, ShallowWrapper } from 'enzyme';
import Title from './title';
import Text from './text';

describe('Title', () => {

  let wrapper: ShallowWrapper;

  beforeEach(() => {
    wrapper = shallow(<Title id='id' />);
  });

  it('has correct id', () => {
    expect(wrapper.find(Text).prop('id')).toEqual('id');
  });

  it('has correct text', () => {
    expect(wrapper.find(Text).prop('text')).toEqual('Sudoku Solver');
  });

  it('is large', () => {
    expect(wrapper.find(Text).prop('large')).toBeTruthy();
  });
});