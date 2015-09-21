'use strict';

// Uncomment the following lines to use the react test utilities
// import React from 'react/addons';
// const TestUtils = React.addons.TestUtils;

import createComponent from 'helpers/createComponent';
import Assignment1 from 'components/Assignment2.js';

describe('Assignment1', () => {
    let Assignment1Component;

    beforeEach(() => {
        Assignment1Component = createComponent(Assignment1);
    });

    it('should have its component name as default className', () => {
        expect(Assignment1Component._store.props.className).toBe('Assignment1');
    });
});
