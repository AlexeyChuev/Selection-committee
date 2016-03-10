<%--
  Created by IntelliJ IDEA.
  User: Алексей
  Date: 3/9/2016
  Time: 6:54 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Страница успешного входа в систему</title>
</head>
<body>
<br>
<h1>Вход посетителя в систему прошел успешно</h1>
<jsp:useBean id="user" class="net.chuiev.selcommittee.view.UserServlet" scope="application"/>
<%--  Пользователь: <%= user.getUser()%><br>
Email: <%= user.getEmail()%><br>
Адрес: <%= user.getAddress()%><br>
Телефон: <%= user.getPhone()%><br>   --%>
</body>
</html>
