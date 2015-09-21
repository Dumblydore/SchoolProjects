'use strict';

var React = require('react/addons');


require('styles/ColorTable.scss');

var ColorTable = React.createClass({
    render: function () {
        return (
            <div className="ColorTable">
                <table>
                    <th>
                        <td>red</td>
                        <td>green</td>
                        <td>blue</td>
                    </th>
                    <tr>
                        <td>red</td>
                        <td></td>
                        <td></td>
                        <td></td>
                    </tr>
                    <tr>
                        <td>green</td>
                        <td></td>
                        <td></td>
                        <td></td>
                    </tr>
                    <tr>
                        <td>blue</td>
                        <td></td>
                        <td></td>
                        <td></td>
                    </tr>
                </table>
            </div>
        );
    }
});

module.exports = ColorTable;
