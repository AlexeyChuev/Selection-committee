<%--
  Created by IntelliJ IDEA.
  User: Алексей
  Date: 3/11/2016
  Time: 10:55 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Заявка уже есть</title>
</head>
<body>
<%@ include file="/WEB-INF/jspf/header.jspf" %>
<h2>Вы уже подали заявку на этот факультет</h2>
<input type="button" value="Вернуться на страницу профиля" class="submit-btn"
       onclick="document.location.href='controller?command=clientHomePage'">
</body>
</html>
