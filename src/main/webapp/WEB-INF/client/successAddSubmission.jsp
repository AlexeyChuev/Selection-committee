<%--
  Created by IntelliJ IDEA.
  User: Алексей
  Date: 3/11/2016
  Time: 11:40 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Заявка подана успещно!</title>
</head>
<body>
<%@ include file="/WEB-INF/jspf/header.jspf" %>
<h2>Ваша заявка принята!</h2>
<input type="button" value="Вернуться на страницу профиля" class="submit-btn"
       onclick="document.location.href='controller?command=clientHomePage'">
</body>
</html>
