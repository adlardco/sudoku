import React from 'react';
import { shallow, ShallowWrapper } from 'enzyme';
import '../setupEnzyme';
import Button from './button';

describe('Button', () => {

  let wrapper: ShallowWrapper;

  beforeEach(() => {
    wrapper = shallow(<Button id='id'/>)
  });

  it('has correct id', () => {
    expect(wrapper.find('#id').prop('id')).toEqual('id');
  });

  it('has correct text', () => {
    expect(wrapper.find('#id').text()).toEqual('click');

    wrapper.setProps({text: 'press'});
    expect(wrapper.find('#id').text()).toEqual('press');
  });

  it('has default style', () => {
    expect(wrapper.find('#id').prop('style')).toEqual({
      'alignItems': 'center',
      'backgroundColor': '#007',
      'borderRadius': '2vw',
      'boxShadow': '0.4vw 0.4vw #777',
      'color': '#fff',
      'cursor': 'pointer',
      'display': 'inline-flex',
      'fontSize': '2vw',
      'height': '100%',
      'justifyContent': 'center',
      'margin': 0,
      'outline': 0,
      'padding': 0,
      'textDecoration': 'none',
      'width': '45%',
    });  
  });

  it('changes style if clicked', () => {
    expect(wrapper.find('#id').prop('style')?.boxShadow).toEqual('0.4vw 0.4vw #777');
    
    wrapper.find('#id').simulate('mouseDown', { preventDefault: () => {} });
    expect(wrapper.find('#id').prop('style')?.boxShadow).toEqual('none');

    wrapper.find('#id').simulate('mouseUp', { preventDefault: () => {} });
    expect(wrapper.find('#id').prop('style')?.boxShadow).toEqual('0.4vw 0.4vw #777');

    wrapper.find('#id').simulate('mouseDown', { preventDefault: () => {} });
    expect(wrapper.find('#id').prop('style')?.boxShadow).toEqual('none');

    wrapper.find('#id').simulate('mouseLeave', { preventDefault: () => {} });
    expect(wrapper.find('#id').prop('style')?.boxShadow).toEqual('0.4vw 0.4vw #777');
  });

  it('can be disabled', () => {
    wrapper.setProps({ disabled: true});
    wrapper.find('#id').simulate('mouseDown', { preventDefault: () => {} });
    expect(wrapper.find('#id').prop('style')?.color).toEqual('#777');
  });

  it('performs action when clicked', () => {
    let clicked = false;
    const action = () => { clicked = true };
    wrapper.setProps({ action: action });
    wrapper.find('#id').simulate('click', { preventDefault: () => {} });
    expect(clicked).toBeTruthy();
  });

  it('does not perform action when clicked if disabled', () => {
    let clicked = false;
    const action = () => { clicked = true };
    wrapper.setProps({
      disabled: true,
      action: action
    });
    wrapper.find('#id').simulate('click', { preventDefault: () => {} });
    expect(clicked).toBeFalsy();
  });
});