'use strict';

var React = require('react/addons');
const NavigationBar = require('./NavigationBar');
var ReactTransitionGroup = React.addons.TransitionGroup;

// CSS
require('normalize.css');
require('../styles/main.scss');


var AssignmentApp = React.createClass({
  render: function() {
    return (
      <div className="main">
        <ReactTransitionGroup transitionName="fade">
          <NavigationBar />
        </ReactTransitionGroup>
      </div>
    );
  }
});

module.exports = AssignmentApp;
