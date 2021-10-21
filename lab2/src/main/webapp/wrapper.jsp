<%--
  Created by IntelliJ IDEA.
  User: catalin121
  Date: 21.10.2021
  Time: 08:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Lab 2</title>
</head>
<body>
    <header>Hello, im a header</header>
    <%=request.getAttribute("content")%>
    <footer>Hello, im a footer</footer>
</body>
</html>
