'use strict';

var React = require('react/addons');
const NavigationBar = require('./NavigationBar');
const {Link, RouteHandler} = require('react-router');

// CSS
require('normalize.css');
require('../styles/main.scss');


var AssignmentApp = React.createClass({
    render: function () {
        return (
            <div className="main">
                <NavigationBar></NavigationBar>

                <div className="content">
                    <RouteHandler/>
                </div>
            </div>
        );
    }
});

module.exports = AssignmentApp;
