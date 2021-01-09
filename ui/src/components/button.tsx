import React, { MouseEvent } from 'react';

interface Props {
  id: string;
  text?: string;
  disabled?: boolean;
  action?: () => void;
}

export default class Button extends React.Component<Props, { pressed: boolean }> {

  private static readonly TextColor = '#fff';
  private static readonly DisabledTextColor = '#777';
  private static readonly BackgroundColor = '#007';
  
  constructor(props: Props) {
    super(props);
    this.state = { pressed: false };
  }
  
  render(): React.ReactElement<React.ReactNode> {
    return (
      <button 
        id={this.props.id}
        key={this.props.id}
        onClick={this.click} 
        onMouseDown={this.press} 
        onMouseUp={this.depress} 
        onMouseLeave={this.depress}
        style={{
          color: this.textColor(),
          backgroundColor: Button.BackgroundColor,
          boxShadow: this.state.pressed ? 'none' : '0.4vw 0.4vw #777',
          borderRadius: '2vw',
          width: '45%',
          height: '100%',
          display: 'inline-flex',
          alignItems: 'center',
          justifyContent: 'center',
          textDecoration: 'none',
          margin: 0,
          padding: 0,
          outline: 0,
          fontSize: '2vw',
          cursor: this.props.disabled ? 'inherit' : 'pointer'
        }}>
          {this.props.text || 'click'}
        </button>
    );
  }

  private textColor(): string {
    return this.props.disabled ? Button.DisabledTextColor : Button.TextColor;
  }

  private click = (event: MouseEvent) => {
    event.preventDefault();
    if(!this.props.disabled && this.props.action !== undefined) {
      this.props.action();
    }
  }

  private press = (event: MouseEvent) => {
    event.preventDefault();
    if(!this.props.disabled) {
      this.setState({ pressed: true });
    }
  }

  private depress = (event: MouseEvent) => {
    event.preventDefault();
    if(!this.props.disabled) {
      this.setState({ pressed: false });
    }
  }
}