'use strict';

var React = require('react/addons');
const NavigationBar = require('./NavigationBar');
const TitleBar = require('./TitleBar');
const {Link, RouteHandler} = require('react-router');

// CSS
require('normalize.css');
require('../styles/main.scss');


var AssignmentApp = React.createClass({
  render: function () {
    return (
      <div className="main">
        <NavigationBar></NavigationBar>
        <RouteHandler/>
      </div>
    );
  }
});

module.exports = AssignmentApp;
