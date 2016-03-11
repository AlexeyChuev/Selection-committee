<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Алексей
  Date: 3/11/2016
  Time: 3:48 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Факультеты</title>
</head>
<body>
<%@ include file="/WEB-INF/jspf/header.jspf"%>

<h2> Список факультетов: </h2>

<table id="facultiesTable" class="display">
<thead>
<tr>
    <td>Название факультета</td>
    <td>Количество бюджетных мест</td>
    <td>Всего мест</td>
</tr>
</thead>
<tbody>
<c:forEach var="faculty" items="${faculties}">
    <tr>
        <td>
            <a href="/index.jsp"> ${faculty.name} </a>
        </td>
        <td>${faculty.budgetVolume}</td>
        <td>${faculty.totalVolume}</td>
    </tr>
</c:forEach>
</tbody>
</table>

<br>
Отсортировать факультеты:
<br>
<div class="menu">
    <a href="controller?command=sortFacultiesAZ">По имени(а-я)</a>
    <a href="controller?command=sortFacultiesZA">По имени(я-а)</a>
    <a href="controller?command=sortFacultiesByBudget">По количеству бюджетных мест</a>
    <a href="controller?command=sortFacultiesByTotal">По общему количеству мес</a>
</div>

</body>
</html>
