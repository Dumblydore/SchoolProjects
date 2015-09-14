<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JH6_Medwards11</title>
        <style>
            body{
                width: 600px;
                margin: 0 auto;
            }

            table {
                width: 100%;
                border-collapse: collapse;
                border: 1px solid black;
            }  

            table td, table th{
                font-size: 1em;
                border: 1px solid black;
                padding: 3px 7px 2px 7px;
            }

            table th{
                color: white;
                background: gray;

            }

            table tr:hover { 
                background: lightblue;
            }

            table tr:hover .edit{ 
                border-right-style: hidden;
                border-top-style: hidden;
                border-bottom-style: hidden;
                background: white;
                visibility: visible;
            }
            
            table tr.alt td{ //alternating color
                             background-color: lightgray;
            }

            .edit {
                visibility: hidden;
            }
            #checkboxH {
                visibility: hidden;
            }

            .checkbox {
                border-left-style: hidden;
                border-top-style: hidden;
                border-bottom-style: hidden;
                background: white;
            }
            
            .bottom {
                border-left-style: hidden;
                border-bottom-style: hidden;
            }
            
            .clear {
                border-left-style: hidden;
                border-bottom-style: hidden;
                border-right-style: hidden;
                margin-right: auto;
            }
            
            .row:hover {
                background: white;
            }
        </style>
    </head>

    <body>
        <table>
            <tr>
                <th id="checkboxH"></th>
                <th>Name</th>
                <th>Eye Color</th>
                <th>Height</th>
                <th>Weight</th>
            </tr>
                <form method="GET">
            <c:forEach var="person" items="${people}">
                <tr>
                    <td class="checkbox" name="personSelect" value="${person.name}">
                        <input type="radio" name="personSelect">
                    </td>
                    <td>${person.name}</td>
                    <td>${person.eyeColor}</td>
                    <td>${person.height}</td>
                    <td>${person.weight}</td>
                </tr>
            </c:forEach>


            <tr>
                <td class="checkbox">
                </td>
                    <td><input type="text" name="personName"></td>
                    <td><input type="text" name="personEye"></td>
                    <td><input type="text" name="personHeight"></td>
                    <td><input type="text" name="personWeight"></td>
                    <td class="edit"><input type="Submit" name="personButton" value="Add"></td>
                    <td class="edit"><input type="Submit" name="personButton" value="Edit"></td>
            </tr>
            <tr class="row">
                <td id="checkboxH"></td>
                <td class="bottom"><input type="Submit" name="personButton" value="Remove"></td>
                <td class="bottom"></td>
                <td class="bottom"></td>
                <td class="clear"><input type="Submit" name="personButton" value="Clear"></td>
            </tr>
                </form>
            
        </table>

    </body>
</html>
