import React from 'react';
import Text from './text';

export default class Title extends React.Component<{ id: string; }, {}> {
  
  render(): React.ReactElement<React.ReactNode> {
    return (
      <Text large id={this.props.id} text='Sudoku Solver'/>
    );
  }
}