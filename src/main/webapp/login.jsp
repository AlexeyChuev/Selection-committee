<%--
  Created by IntelliJ IDEA.
  User: Алексей
  Date: 3/10/2016
  Time: 8:41 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>

<form id ="login" action="controller" method="post">
    <input type="hidden" name="command" value="login" />

    <label>
        Username:<br>
        <input required type="text" name="username" class="input-field">
    </label>
    <br>
    <label>
        Password:<br>
        <input required type="password" name="password" class="input-field">
    </label>
    <br>
    <br>
    <input type=submit value="Login" class="submit-btn">
    <input type="button" value="Register" class="submit-btn" onclick="document.location.href='registration.jsp'">
</form>
</body>
</html>
