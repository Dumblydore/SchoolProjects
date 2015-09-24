var alt = require('../alt');
var AssignmentActions = require('../actions/AssignmentActions');

class AssignmentStore {
    constructor() {
        this.assignments = [];
        this.labs = [];

        this.bindListeners({
            handleGetAssignments: AssignmentActions.GET_ASSIGNMENTS,
            handleGetLabs: AssignmentActions.GET_LABS
        });
    }

    handleGetAssignments(assignments) {
        this.assignments = assignments;
    }

    handleGetLabs(labs) {
        this.labs = labs;
    }
}

module.exports = alt.createStore(AssignmentStore, 'AssignmentStore');
