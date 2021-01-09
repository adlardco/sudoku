import React from 'react';

export default class Group extends React.Component<{
  id: string;
  vertical?: boolean;
  width?: string;
  height?: string;
  spaceAround?: boolean;
  wrap?: boolean;
}, {}> {
    
  render(): React.ReactElement<React.ReactNode> {
    const wrap = this.props.wrap ? 'wrap' : 'nowrap';
    return (
      <div 
          id={this.props.id} 
          key={this.props.id} 
          style={{
            display: 'flex',
            flexFlow: `${this.props.vertical ? 'column' : 'row'} ${wrap}`,
            justifyContent: this.props.spaceAround  ? 'space-around' : 'space-between',
            alignItems: 'center',
            width: this.props.width || 'auto',
            height: this.props.height || '100%',
            margin: 0,
            padding: 0
        }}>{this.props.children}</div> 
    );
  }
}