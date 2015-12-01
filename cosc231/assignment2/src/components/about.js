'use strict';

var React = require('react/addons');


require('styles/About.scss');

var About = React.createClass({


    onChange(state) {
        this.setState(state);
    },

    render: function () {
        return (
            <div className="About">
                <h1>Part 1</h1>
                <br/>
                <h1>Maurice Edwards</h1>

                <p>
                    <img className="img"
                         src="https://avatars3.githubusercontent.com/u/5824872?v=3&s=460"></img>
                    I am a 4th year Computer Science student at <span className="eastern">Eastern</span> Michigan
                    University.
                    I have been interning at TD Ameritrade for about a year. At this internship I've been developing
                    backend services
                    written in Java, and web apps using AngularJs. I also got to develop iOS &amp; Android apps as
                    intership projects.</p>

                <p>
                    I've been writing programs since I was about 13, starting with ActionScript,
                    and eventually moving to Java as my language of choice. I thought <span className="eastern">Eastern </span>
                    would be a good fit for me because of how close it is to home.
                    My hobbies include: </p>
                <ol>
                    <li><a href="http://github.com/DumblyDore" target="_blank">Writing programs</a></li>
                    <li>Playing Guitar</li>
                    <li><a href="https://en.wikipedia.org/wiki/Paintball" target="_blank">Paintball</a></li>
                </ol>
                <h1>Part 2</h1>
                <tbody>

                </tbody>
            </div>
        );
    }
});

module.exports = About;
