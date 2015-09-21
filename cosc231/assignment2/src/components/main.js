'use strict';

var AssignmentApp = require('./AssignmentApp');
var React = require('react');
var Router = require('react-router');
var Route = Router.Route;

var content = document.getElementById('content');

var Routes = (
  <Route handler={AssignmentApp}>
    <Route name="/" handler={AssignmentApp}/>
  </Route>
);

Router.run(Routes, function (Handler) {
  React.render(<Handler/>, content);
});
