'use strict';

var React = require('react/addons');


require('styles/Assignment1.scss');

var Assignment1 = React.createClass({

    getInitialState: function () {
    },

    componentDidMount() {
    },

    componentWillUnmount() {
    },

    onChange(state) {
        this.setState(state);
    },

  render: function () {
    return (
        <div className="Assignment1">
          <p>Content for Assignment1</p>
        </div>
      );
  }
});

module.exports = Assignment1;
