<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<html><head>
    <meta http-equiv="content-type" content="text/html; charset=ISO-8859-1">

    <title> gettysburg home page </title>
</head>
<body bgcolor="#FFFF99">
<center>
    <table bgcolor="#FFCC66" bordercolor="#000000" border="4">
        <tr><td>
            <h2>Gettysburg</h2>
        </td></tr></table>
    <h2><a href="http://wccnet.edu">Wcc Home Page</a></h2>
    <p>Current Time = <% out.println(new Date().toString());%></p>

    <h2><img src="sunset.jpg" width="388" height="294"></h2>
    <h2><img src="cemetary.jpg" width="270" height="180"></h2>
    <h2>&nbsp;</h2>

</center>

</body></html>
`