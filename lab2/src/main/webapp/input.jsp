<%@ page import="ro.uaic.info.entities.RecordCategories" %><%--
  Created by IntelliJ IDEA.
  User: catalin121
  Date: 21.10.2021
  Time: 07:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Result</title>
</head>
<body>
<body>
<form method="POST">
    <label for="category">Category:</label>
    <select name="category" id="category">
        <% for (RecordCategories category : RecordCategories.values()) { %>
            <option value="<%=category.toString()%>"><%=category.toString()%></option>
        <% } %>
    </select>
    <br/>

    Word: <input type="text" name="key">
    <br/>

    Definition: <input type="text" name="value"/>
    <input type="submit" value="Submit"/>
</form>
</body>
</html>
