'use strict';

const React = require('react/addons');
const {Link, RouteHandler} = require('react-router');
const ColorTable = require('./ColorTable');

require('styles/NavigationBar.scss');

let uiOptions = [
    {
        text: 'Assignment 2',
        url: '/assignment2'
    }
];

var NavigationBar = React.createClass({
    render: function () {
        return (
            <div className="NavigationBar">
                <div className="header">
                    <div className="head-shot-container">
                        <img className="head-shot"
                             src="https://avatars3.githubusercontent.com/u/5824872?v=3&s=460"></img>
                    </div>
                    <h1>Maurice Edwards</h1>
                    <a href='http://www.emich.edu/compsci/' rel="external" target="_blank">
                        <p>Computer Science Department</p>
                    </a>
                </div>
                <div className='links'>
                    {
                        uiOptions.map(option => {
                            return (
                                <Link to={option.url}>
                                    <div className="listItem">
                                        <p>{option.text}</p>
                                    </div>
                                </Link>
                            );
                        })
                    }
                </div>
                <ColorTable></ColorTable>
            </div>
        );
    }
});

module.exports = NavigationBar;
