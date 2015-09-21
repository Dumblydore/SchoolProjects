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
                        <td style={{'background-color': '#f44336'}}></td>
                        <td style={{'background-color': '#ffeb3b'}}></td>
                        <td style={{'background-color': '#673AB7'}}></td>
                    </tr>
                    <tr>
                        <td>green</td>
                        <td style={{'background-color': '#ffeb3b'}}></td>
                        <td style={{'background-color': '#4caf50'}}></td>
                        <td style={{'background-color': '#009688'}}></td>
                    </tr>
                    <tr>
                        <td>blue</td>
                        <td style={{'background-color': '#673AB7'}}></td>
                        <td style={{'background-color': '#009688'}}></td>
                        <td style={{'background-color': '#2196f3'}}></td>
                    </tr>
                </table>
            </div>
        );
    }
});

module.exports = ColorTable;
