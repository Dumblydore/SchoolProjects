var createStore = require('CreateStore');

module.exports = createStore('AssignmentStore', {
    state: {
        assignments: [],
        labs: []
    },
    bind: {
        getAssignments: function(assignments) {
            this.assignments = assignments;
        },

        getLabs: function(labs) {
            this.labs = labs;
        }
    }
});
