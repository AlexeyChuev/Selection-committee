<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Алексей
  Date: 3/12/2016
  Time: 6:04 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Заблокировать абитуриента</title>
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
<h2>Блокировка абитуриента</h2>
<br>
<br>
<p align="center"> Список не заблокированных абитуриентов</p>
<table id="unblockEnrolleesTable" class="display">
    <thead>
    <tr>
        <td>ФИО абитуриента</td>
        <td>ID абитуриента</td>
        <td></td>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="unblockEnrollee" items="${unblockEnrollees}">
        <tr>
            <td>${unblockEnrollee.fullName}</td>
            <td>${unblockEnrollee.id}</td>
            <td>
                <a href="<c:url value="controller?command=blockEnrollee"> <c:param name="enrolleeId" value="${unblockEnrollee.id}"/></c:url>">
                    <c:out value="Заблокировать"></c:out>
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
