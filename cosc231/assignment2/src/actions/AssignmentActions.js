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
        text: 'Lab 2',
        url: '/lab2'
    },
    {
        text: 'Lab 4',
        url: '/permute'
    },
    {
        text: 'Lab 5',
        url: '/lab5'
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
