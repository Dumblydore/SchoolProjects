var AssignmentApp = require('./AssignmentApp');
import Assignment1 from './Assignment1.js';
import Home from './Home.js';

var Router = require('react-router');
import {DefaultRoute, Route} from 'react-router';
var React = require('react');

var content = document.getElementById('content');

var Routes = (
    <Route handler={AssignmentApp}>
        <DefaultRoute handler={Home}/>
        <Route name="home" path="/home" handler={Home}/>
        <Route name="assignment1" path="/assignment1" handler={Assignment1}/>
    </Route>
);

Router.run(Routes, function (Handler) {
    React.render(<Handler/>, content);
});
