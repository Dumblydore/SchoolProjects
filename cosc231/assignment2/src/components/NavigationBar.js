'use strict';

const React = require('react/addons');
const {Link, RouteHandler} = require('react-router');

require('styles/NavigationBar.scss');

let assignments = [
  {
    text: '1st Page',
    url: '/assignment1'
  },
  {
    text: 'About',
    url: '/about'
  }
];

var NavigationBar = React.createClass({
  render: function () {
    return (
      <div className="NavigationBar">
        <div className="header">
          <div className="head-shot-container">
            <Link to="/">
              <img className="head-shot"
                   src="https://avatars3.githubusercontent.com/u/5824872?v=3&s=460"></img>
            </Link>
          </div>
          <h1>Maurice Edwards</h1>
          <a href='http://www.emich.edu/compsci/' rel="external" target="_blank">
            <p>Computer Science Department</p>
          </a>
        </div>
        <div className='links'>
          <div className="label">
            <label>Assignments</label>
          </div>
          {
            assignments.map(option => {
              return (
                <Link to={option.url}>
                  <div className="listItem">
                    {option.text}
                  </div>
                </Link>
              );
            })
          }
        </div>
      </div>
    );
  }
});

module.exports = NavigationBar;
