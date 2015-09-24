var AssignmentApp = require('./AssignmentApp');
import Assignment1 from './Assignment1.js';
import Home from './Home.js';
import About from './About.js';
import Lab1 from './Lab1.js';

var Router = require('react-router');
import {Route, DefaultRoute} from 'react-router';
var React = require('react');

var content = document.getElementById('content');

var Routes = (
    <Route name="COSC231" path="/" handler={AssignmentApp}>
        <DefaultRoute handler={Home}/>
        <Route name="Assignment1" path="/assignment1" handler={Assignment1}/>
        <Route name="About" path="/assignment2" handler={About}/>
        <Route name="Lab" path="/lab1" handler={Lab1}/>
    </Route>
);

Router.run(Routes, function (Handler) {
    React.render(<Handler/>, content);
});
