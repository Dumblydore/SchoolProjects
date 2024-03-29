'use strict';

// Uncomment the following lines to use the react test utilities
// import React from 'react/addons';
// const TestUtils = React.addons.TestUtils;

import createComponent from 'helpers/createComponent';
import NavigationBar from 'components/NavigationBar.js';

describe('NavigationBar', () => {
    let NavigationBarComponent;

    beforeEach(() => {
        NavigationBarComponent = createComponent(NavigationBar);
    });

    it('should have its component name as default className', () => {
        expect(NavigationBarComponent._store.props.className).toBe('NavigationBar');
    });
});
