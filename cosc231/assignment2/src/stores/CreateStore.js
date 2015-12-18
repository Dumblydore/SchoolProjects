var alt = require('../alt');

let getAction = action => {
    return require('../../actions/' + action);
};

export default function (name, content) {
    let bindListeners = {};
    content.bind.forEach(action => {
        let a = getAction(action);
    bindListeners.push(action.map(func => {
            action[func] = a[func];
}));

});

return alt.createStore(function () {
    this.displayName = name;
    this.state = content.state;
    this.bindListeners = bindListeners;
    this.publicMethods = content.filter(x => typeof x === 'function');
});
}
