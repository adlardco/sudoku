import React from 'react';
import Text from './text';
import { Message } from './message';

export default class StatusBar extends React.Component<{ 
  id: string; 
  message: Message
  error?: boolean
}, {}> {
  
  render(): React.ReactElement<React.ReactNode> {
    return (
      <Text id={this.props.id} text={this.props.message} error={this.props.error} />
    );
  }
}