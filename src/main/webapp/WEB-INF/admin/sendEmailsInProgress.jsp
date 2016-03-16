<%--
  Created by IntelliJ IDEA.
  User: Алексей
  Date: 3/16/2016
  Time: 1:31 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sending emails in progress</title>
</head>
<body>
<%@ include file="/WEB-INF/jspf/headerAdmin.jspf" %>
<h2> Sending emails for enrollees in progress </h2>
<input type="button" value="Вернуться на панель администратора" class="submit-btn"
       onclick="document.location.href='controller?command=adminHomePage'">
</body>
</html>
