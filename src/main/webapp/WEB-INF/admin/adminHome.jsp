<%--
  Created by IntelliJ IDEA.
  User: Алексей
  Date: 3/10/2016
  Time: 7:00 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
    <title>Страница админа</title>
</head>
<body>
<%@ include file="/WEB-INF/jspf/headerAdmin.jspf"%>
<h2>Панель администратора:</h2>


<input type="button" value="Добавить новый факультет" class="submit-btn" onclick="document.location.href='controller?command=addNewFacultyForward'">

</body>
</html>