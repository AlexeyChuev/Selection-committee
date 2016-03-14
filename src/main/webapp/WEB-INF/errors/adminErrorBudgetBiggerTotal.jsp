<%--
  Created by IntelliJ IDEA.
  User: Алексей
  Date: 3/12/2016
  Time: 1:42 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Бюджета больше, чем общего числа мест</title>
</head>
<body>
<%@ include file="/WEB-INF/jspf/headerAdmin.jspf" %>
<h2>Бюджетных мест не может быть больше, чем общее количество мест</h2>
<input type="button" value="Вернуться на панель администратора" class="submit-btn"
       onclick="document.location.href='controller?command=adminHomePage'">
</body>
</html>
