import React from 'react';
import '../setupEnzyme';
import { shallow, ShallowWrapper } from 'enzyme';
import StatusBar from './statusBar';
import Text from './text';
import { Message } from './message';

describe('StatusBar', () => {

  let wrapper: ShallowWrapper;

  beforeEach(() => {
    wrapper = shallow(<StatusBar id='id' message={Message.CannotSolve} />);
  });

  it('has correct id', () => {
    expect(wrapper.find(Text).prop('id')).toEqual('id');
  });

  it('has correct text', () => {
    expect(wrapper.find(Text).prop('text')).toEqual(Message.CannotSolve);
    wrapper.setProps({ 'message' : Message.NoInternet });
    expect(wrapper.find(Text).prop('text')).toEqual(Message.NoInternet);
  });

  it('is not large', () => {
    expect(wrapper.find(Text).prop('large')).toBeFalsy();
  });
});