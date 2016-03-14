<%--
  Created by IntelliJ IDEA.
  User: Алексей
  Date: 3/12/2016
  Time: 3:30 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Факультет удален</title>
</head>
<body>
<%@ include file="/WEB-INF/jspf/headerAdmin.jspf" %>
<h2>${facultyName} удален</h2>
<input type="button" value="Вернуться на панель администратора" class="submit-btn"
       onclick="document.location.href='controller?command=adminHomePage'">
</body>
</html>
