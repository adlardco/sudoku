import React from 'react';
import preval from 'preval.macro';

export default class BuildTime extends React.Component<{ id: string; show?: boolean;}, {}> {

    render(): React.ReactElement<React.ReactNode>  {
        const time = preval`module.exports = new Date().toUTCString();`
        return (
            <span 
              id={this.props.id} 
              key={this.props.id} 
              style={{ 
                 textAlign: 'center',
                 fontSize: '1.5vw',
                 fontStyle: 'italic',
                 width: '100%',
                 margin: 0, 
                 padding: 0,
                 cursor: 'inherit',
                 display: this.props.show? 'auto' : 'none'
              }}
            >
                {'Build time: ' + time}
            </span>
        );
    }
}