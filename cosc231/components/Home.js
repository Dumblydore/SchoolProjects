'use strict';

var React = require('react/addons');


require('styles/Home.scss');

var Home = React.createClass({

  render: function () {
    return (
        <div className="Home">
          <h1>Content for Home</h1>
        </div>
      );
  }
});

module.exports = Home;
