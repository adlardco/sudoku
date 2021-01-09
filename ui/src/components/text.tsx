import React from 'react';

export default class Text extends React.Component<{
  id: string;
  text?: string;
  large?: boolean;
  error?: boolean;
}, {}> {
  
  render(): React.ReactElement<React.ReactNode> {
    return (
      <span 
          id={this.props.id}
          key={this.props.id}
          style={{
          textAlign: 'center',
          fontSize: this.props.large ? '4vw' : '1.5vw',
          color: this.props.large ? '#007' : 'auto',
          backgroundColor: this.props.large ? 'transparent' : (this.props.error ? '#a00' : '#7f7'),
          borderRadius: '1vw',
          width: '100%',
          margin: 0,
          padding: this.props.large ? 0 : '1.5vw 0.4vw',
          cursor: 'inherit'
      }}>
        {this.props.text || 'placeholder' }
      </span>
    );
  }
}