import React from 'react';
import '../setupEnzyme';
import { shallow, ShallowWrapper } from 'enzyme';
import Row from './row';
import Group from './group';

describe('Row', () => {

  let wrapper: ShallowWrapper;

  beforeEach(() => {
    wrapper = shallow(<Row id='id' />);
  });

  it('has correct id', () => {
    expect(wrapper.find(Group).prop('id')).toEqual('id');
  });

  it('is not vertical', () => {
    expect(wrapper.find(Group).prop('vertical')).toBeFalsy();
  });
});