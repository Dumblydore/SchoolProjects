'use strict';

const React = require('react/addons');
const AssignmentStore = require('../stores/AssignmentStore');
const AssignmentActions = require('../actions/AssignmentActions');

require('styles/TitleBar.scss');

var TitleBar = React.createClass({
    getInitialState: function () {
        return AssignmentStore.getState();
    },

    componentDidMount() {
        AssignmentStore.listen(this.onChange);
        AssignmentActions.getAssignments();
    },

    componentWillUnmount() {
        TwitchStore.unlisten(this.onChange);
    },

    onChange(state) {
        this.setState(state);
    },
    contextTypes: {
        router: React.PropTypes.func.isRequired
    },
    render: function () {
        let currentRoute = this.context.router.getCurrentPathname();
        var title;
        this.state.assignments.map(assignment => {
            if(assignment.url === currentRoute){
                title = assignment.text;
                return;
            }
        });
        return (
            <div className="TitleBar">
                <h1>{title}</h1>
            </div>
        );
    }
});

module.exports = TitleBar;
