var AssignmentApp = require('./AssignmentApp');
import Assignment1 from './Assignment1.js';
import Home from './Home.js';
import About from './About.js';
import Lab2 from './Lab2.js';
import Lab4 from './Lab4.js';
import Lab5 from './Lab5.js';

var Router = require('react-router');
import {Route, DefaultRoute} from 'react-router';
var React = require('react');

var content = document.getElementById('content');

var Routes = (
    <Route name="COSC231" path="/" handler={AssignmentApp}>
        <DefaultRoute handler={Home}/>
        <Route name="Assignment 1" path="/assignment1" handler={Assignment1}/>
        <Route name="About" path="/assignment2" handler={About}/>
        <Route name="Lab 2" path="/lab2" handler={Lab2}/>
        <Route name="Lab 4" path="/lab4" handler={Lab4}/>
        <Route name="Lab 5" path="/lab5" handler={Lab5}/>
    </Route>
);

Router.run(Routes, function (Handler) {
    React.render(<Handler/>, content);
});
