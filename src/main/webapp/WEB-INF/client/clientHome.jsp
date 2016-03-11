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
<%@ include file="/WEB-INF/jspf/header.jspf"%>
<h3>Ваш профиль</h3>

ФИО:${name}
<br>
Город:${city}
<br>
Регион:${region}
<br>
Учебное заведение:${school}
<br>
Email:${email}
<br>
<%--
<br>
sg1
<br>

<table>
    <c:forEach var="elem" items="${sessionScope.values()}" varStatus="status">
        <tr>
            <td><c:out value="${ elem }" /></td>

        </tr>
    </c:forEach>
</table>
<br>
sg2
<br>
<table>
    <c:forEach var="elem" items="${param.values()}" varStatus="status">
        <tr>
            <td><c:out value="${ elem }" /></td>

        </tr>
    </c:forEach>
</table>
--%>
</body>
</html>
