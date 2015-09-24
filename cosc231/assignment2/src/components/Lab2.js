'use strict';

var React = require('react/addons');


require('styles/Lab2.scss');

var Lab2 = React.createClass({

    render: function () {
        return (
            <div className='Lab2'>
                <table>
                    <th>
                        <td className='header' style={{color: '#F44336'}}>Red</td>
                        <td className='header' style={{color: '#4CAF50'}}>Green</td>
                        <td className='header' style={{color: '#2979FF'}}>Blue</td>
                    </th>
                    <tr>
                        <td className='header' style={{color: '#F44336'}}>Red</td>
                        <td style={{color: '#F44336'}}>red</td>
                        <td style={{color: '#795548'}}>brown</td>
                        <td style={{color: '#673AB7'}}>purple</td>
                    </tr>
                    <tr>
                        <td className='header' style={{color: '#4CAF50'}}>Green</td>
                        <td style={{color: '#795548'}}>brown</td>
                        <td style={{color: '#4CAF50'}}>green</td>
                        <td style={{color: '#18FFFF'}}>aqua</td>
                    </tr>
                    <tr>
                        <td className='header' style={{color: '#2979FF'}}>Blue</td>
                        <td style={{color: '#673AB7'}}>purple</td>
                        <td style={{color: '#18FFFF'}}>aqua</td>
                        <td style={{color: '#2979FF'}}>blue</td>
                    </tr>
                    <tr>
                        <td>Result</td>
                        <td colSpan='3'>It's a rainbow spectacular!</td>
                    </tr>
                </table>
                <form>
                    <label>Select the size you want: </label>
                    <input type='radio' name='size'/>Small
                    <input type='radio' name='size' checked/>Medium
                    <input type='radio' name='size'/>Big
                    <br/>
                    Enter your name: <input type='text' />
                    <br/>
                    Select the features you want:
                    <input type='checkbox' />Long sleeves
                    <input type='checkbox' />Turtleneck
                    <input type='checkbox' />Long torso
                </form>
            </div>
        );
    }
});

module.exports = Lab2;
