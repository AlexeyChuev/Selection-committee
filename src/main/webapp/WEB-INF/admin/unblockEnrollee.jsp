<%--
  Created by IntelliJ IDEA.
  User: Алексей
  Date: 3/12/2016
  Time: 8:01 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Unblock Enrollee</title>
    <style>
        table {
            margin: auto;
        }

        td {
            text-align: center;
        }
    </style>
</head>
<body>

<%@ include file="/WEB-INF/jspf/headerAdmin.jspf" %>
<h2>Разблокировка абитуриента</h2>
<br>
<br>
<p align="center"> Список заблокированных абитуриентов</p>

<table id="blockedEnrolleesTable" class="display">
    <thead>
    <tr>
        <td>ФИО абитуриента</td>
        <td>ID абитуриента</td>
        <td></td>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="blockedEnrollees" items="${blockedEnrollees}">
        <tr>
            <td>${blockedEnrollees.fullName}</td>
            <td>${blockedEnrollees.id}</td>
            <td>
                <a href="<c:url value="controller?command=unblockEnrollee"> <c:param name="enrolleeId" value="${blockedEnrollees.id}"/></c:url>">
                    <c:out value="Разблокировать"></c:out>
                </a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<br>
<input type="button" value="Вернуться на панель администратора" class="submit-btn"
       onclick="document.location.href='controller?command=adminHomePage'">
</body>
</html>
