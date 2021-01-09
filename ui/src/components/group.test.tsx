import React from 'react';
import { shallow, ShallowWrapper } from 'enzyme';
import '../setupEnzyme';
import Group from './group';

describe('Group', () => {

  let wrapper: ShallowWrapper;

  beforeEach(() => {
    wrapper = shallow(<Group id='id'/>);
  });

  it('has correct id', () => {
    expect(wrapper.find('#id').prop('id')).toEqual('id');
  });

  it('has default style', () => {
    expect(wrapper.find('#id').prop('style')).toEqual({
      'alignItems': 'center',
      'display': 'flex',
      'flexFlow': 'row nowrap',
      'height': '100%',
      'justifyContent': 'space-between',
      'margin': 0,
      'padding': 0,
      'width': 'auto'
    });
  });

  it('has vertical style', () => {
    wrapper.setProps({ vertical: true });
    expect(wrapper.find('#id').prop('style')?.flexFlow).toEqual('column nowrap');
  });

  it('has space around style', () => {
    wrapper.setProps({ spaceAround: true });
    expect(wrapper.find('#id').prop('style')?.justifyContent).toEqual('space-around');
  });

  it('has width style', () => {
    wrapper.setProps({ width: '1vw' });
    expect(wrapper.find('#id').prop('style')?.width).toEqual('1vw');
  });

  it('has height style', () => {
    wrapper.setProps({ height: '1vw' });
    expect(wrapper.find('#id').prop('style')?.height).toEqual('1vw');
  });
});
