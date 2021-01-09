import React from 'react';
import Group from './group';

export default class Column extends React.Component<{
  id: string;
  width?: string;
  height?: string;
  spaceAround?: boolean;
  wrap?: boolean;
}, {}> {
    
  render(): React.ReactElement<React.ReactNode> {
    return (
      <Group vertical
          id={this.props.id} 
          width={this.props.width}
          height={this.props.height}
          spaceAround={this.props.spaceAround}
          wrap={this.props.wrap}
      >
        {this.props.children}
      </Group>
    );
  }
}