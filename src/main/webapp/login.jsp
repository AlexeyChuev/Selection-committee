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

<form name="login" id ="login" action="controller" method="POST">
    <input type="hidden" name="command" value="login" />
    <h1>Система "Приемная комиссия"</h1>
    <label>
        Введите email:<br>
        <input required type="email" name="email" class="input-field">
    </label>
    <br>
    <label>
        Введите пароль:<br>
        <input required type="password" name="password" class="input-field">
    </label>
    <br>
    <br>
    <input type=submit value="Войти" class="submit-btn">
    <input type="button" value="Зарегистрироваться" class="submit-btn" onclick="document.location.href='registration.jsp'">
</form>
</body>
</html>
