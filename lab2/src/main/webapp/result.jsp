<%@ page import="java.util.List" %>
<%@ page import="ro.uaic.info.entities.Record" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% List<Record> records = (List<Record>) request.getAttribute("records"); %>
<html>
<head>
    <title>Result page</title>
</head>
<body>
<h1>Hello, World!</h1>
<table>
    <% for (Record record : records) { %>
    <tr>
        <td><%= record.getCategory().toString() %></td>
        <td><%= record.getWord() %></td>
        <td><%= record.getDefinition() %></td>
    </tr>
    <% } %>
</table>
</body>
</html>
