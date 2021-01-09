import React from 'react';
import '../setupEnzyme';
import { shallow, ShallowWrapper } from 'enzyme';
import Column from './column';
import Group from './group';

describe('Column', () => {

  let wrapper: ShallowWrapper;

  beforeEach(() => {
    wrapper = shallow(<Column id='id' />);
  });

  it('has correct id', () => {
    expect(wrapper.find(Group).prop('id')).toEqual('id');
  });

  it('is vertical', () => {
    expect(wrapper.find(Group).prop('vertical')).toBeTruthy();
  });
});