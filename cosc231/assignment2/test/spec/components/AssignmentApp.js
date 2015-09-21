'use strict';

describe('AssignmentApp', () => {
  let React = require('react/addons');
  let AssignmentApp, component;

  beforeEach(() => {
    let container = document.createElement('div');
    container.id = 'content';
    document.body.appendChild(container);

    AssignmentApp = require('components/AssignmentApp.js');
    component = React.createElement(AssignmentApp);
  });

  it('should create a new instance of AssignmentApp', () => {
    expect(component).toBeDefined();
  });
});
