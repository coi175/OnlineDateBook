<%--
  Created by IntelliJ IDEA.
  User: coi
  Date: 2/16/21
  Time: 1:59 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form action="login" method="post">
        <input type="text" name="username">
        <input type="password" name="password">
        <input type="submit"> ${error}
    </form>
</body>
</html>
