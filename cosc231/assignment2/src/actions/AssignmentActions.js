const alt = require('../alt');
const assignments = [
    {
        text: '1st Page',
        url: '/assignment1'
    },
    {
        text: 'About',
        url: '/assignment2'
    }
];

const labs = [
    {
        text: 'Lab 1',
        url: '/lab1'
    }
];

class AssignmentActions {

    getAssignments() {
        this.dispatch(assignments);
    }

    getLabs() {
        this.dispatch(labs);
    }

}

module.exports = alt.createActions(AssignmentActions);
