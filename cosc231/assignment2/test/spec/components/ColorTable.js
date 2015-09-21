'use strict';

// Uncomment the following lines to use the react test utilities
// import React from 'react/addons';
// const TestUtils = React.addons.TestUtils;

import createComponent from 'helpers/createComponent';
import ColorTable from 'components/ColorTable.js';

describe('ColorTable', () => {
    let ColorTableComponent;

    beforeEach(() => {
        ColorTableComponent = createComponent(ColorTable);
    });

    it('should have its component name as default className', () => {
        expect(ColorTableComponent._store.props.className).toBe('ColorTable');
    });
});
