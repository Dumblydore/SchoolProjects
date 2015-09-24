'use strict';

const React = require('react/addons');
const {Link, RouteHandler} = require('react-router');
const AssignmentStore = require('../stores/AssignmentStore');
const AssignmentActions = require('../actions/AssignmentActions');
require('styles/NavigationBar.scss');

var NavigationBar = React.createClass({
    getInitialState: function () {
        return AssignmentStore.getState();
    },

    componentDidMount() {
        AssignmentStore.listen(this.onChange);
        AssignmentActions.getAssignments();
        AssignmentActions.getLabs();
    },

    componentWillUnmount() {
        AssignmentStore.unlisten(this.onChange);
    },

    onChange(state) {
        this.setState(state);
    },
    contextTypes: {
        router: React.PropTypes.func.isRequired
    },
    render: function () {
        let cx = React.addons.classSet;
        let currentRoute = this.context.router.getCurrentPathname();
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
                        this.state.assignments.map(option => {
                            let classes = cx('listItem', option.url === currentRoute ? 'selected' : '');
                            return (
                                <Link to={option.url}>
                                    <div className={classes}>
                                        {option.text}
                                    </div>
                                </Link>
                            );
                        })
                    }
                    <div className="label">
                        <label>Labs</label>
                    </div>

                    {
                        this.state.labs.map(option => {
                            let classes = cx('listItem', option.url === currentRoute ? 'selected' : '');
                            return (
                                <Link to={option.url}>
                                    <div className={classes}>
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
