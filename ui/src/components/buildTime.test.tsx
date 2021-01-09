import React from 'react';
import { shallow, ShallowWrapper } from 'enzyme';
import '../setupEnzyme';
import BuildTime from './buildTime';

describe('BuildTime', () => {

  let wrapper: ShallowWrapper;

  beforeEach(() => {
    wrapper = shallow(<BuildTime id='id'/>);
  });

  it('has correct id', () => {
      expect(wrapper.find('#id').prop('id')).toEqual('id');
  });

  it('has correct text', () => {
      expect(wrapper.find('#id').text()).toMatch(/Build time: .*GMT/);  
  });

  it('has default style', () => {
      expect(wrapper.find('#id').prop('style')).toEqual({
        'cursor': 'inherit',
        'display': 'none',
        'fontSize': '1.5vw',
        'fontStyle': 'italic',
        'margin': 0,
        'padding': 0,
        'textAlign': 'center',
        'width': '100%'
      });
  });

  it('can be shown', () => {
    wrapper.setProps({ show: true });
    expect(wrapper.find('#id').prop('style')?.display).toEqual('auto');
  });
});
