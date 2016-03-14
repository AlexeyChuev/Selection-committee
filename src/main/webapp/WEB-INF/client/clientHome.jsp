<%--
  Created by IntelliJ IDEA.
  User: Алексей
  Date: 3/10/2016
  Time: 7:02 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
    <title>Страница клиента</title>
</head>
<body>
<%@ include file="/WEB-INF/jspf/header.jspf" %>
<h2>Ваш профиль</h2>

<b>ФИО:</b><br>${name}
<br><br>
<b>Город:</b><br>${city}
<br><br>
<b>Регион:</b><br>${region}
<br><br>
<b>Учебное заведение:</b><br>${school}
<br><br>
<b>Email:</b><br>${email}
<br>
</body>
</html>
