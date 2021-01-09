import React from 'react';
import '../setupEnzyme';
import { shallow, ShallowWrapper } from 'enzyme';
import Text from './text';

describe('Text', () => {

  let wrapper: ShallowWrapper;

  beforeEach(() => {
    wrapper = shallow(<Text id='id' />);
  });

  it('has correct id', () => {
    expect(wrapper.find('#id').prop('id')).toEqual('id');
  });

  it('has correct text', () => {
    expect(wrapper.find('#id').text()).toEqual('placeholder');

    wrapper.setProps({ text: 'text' });
    expect(wrapper.find('#id').text()).toEqual('text');
  });

  it('has default style', () => {
    expect(wrapper.find('#id').prop('style')).toEqual({
      'backgroundColor': '#7f7',
      'borderRadius': '1vw',
      'color': 'auto',
      'cursor': 'inherit',
      'fontSize': '1.5vw',
      'margin': 0,
      'padding': '1.5vw 0.4vw',
      'textAlign': 'center',
      'width': '100%'
    });
  });

  it('has large style', () => {
    wrapper.setProps({ large: true });
    expect(wrapper.find('#id').prop('style')?.backgroundColor).toEqual('transparent');
    expect(wrapper.find('#id').prop('style')?.color).toEqual('#007');
    expect(wrapper.find('#id').prop('style')?.fontSize).toEqual('4vw');
    expect(wrapper.find('#id').prop('style')?.padding).toEqual(0);
  });

  it('has error style', () => {
    wrapper.setProps({ error: true });
    expect(wrapper.find('#id').prop('style')?.backgroundColor).toEqual('#a00');
  });
});