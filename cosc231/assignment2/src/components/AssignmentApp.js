'use strict';

var React = require('react/addons');
const NavigationBar = require('./NavigationBar');
const ColorTable = require('./ColorTable');

// CSS
require('normalize.css');
require('../styles/main.scss');


var AssignmentApp = React.createClass({
    render: function () {
        return (
            <div className="main">
                <div className="hobbies-list">
                    <h2>Top 3 Movies</h2>
                    <ol>
                        <li>Pulp Fiction</li>
                        <ul>
                            <li>Quentin Tarantino</li>
                            <li>October 14, 1994</li>
                        </ul>
                        <li>Apocalypse Now</li>
                        <ul>
                            <li>Francis Coppola</li>
                            <li>August 15, 1979</li>
                        </ul>
                        <li>Hercules</li>
                        <ul>
                            <li>John Musker &amp; Ron Clements</li>
                            <li>June 27, 1997</li>
                        </ul>
                    </ol>
                </div>
                <div className="headers">
                    <h3>I didn't_</h3>
                    <h4>know what_</h4>
                    <h5>to do_</h5>
                    <h6>with these headers</h6>
                </div>
                <NavigationBar></NavigationBar>
                <ColorTable></ColorTable>
            </div>
        );
    }
});

module.exports = AssignmentApp;
