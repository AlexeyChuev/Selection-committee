<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Алексей
  Date: 3/13/2016
  Time: 1:54 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Ведомость абитуриентов</title>
</head>
<body>
<%@ include file="/WEB-INF/jspf/headerAdmin.jspf" %>

<h2> Финальная ведомость: </h2>
<br>

<c:forEach var="entry" items="${map}">
    <h3> ${entry.key.name} </h3>
    <table id="registerTable" class="display">
        <thead>
        <tr>
            <td>ФИО абитуриента</td>
            <td>
                <c:forEach var="listEntry" items="${entry.value}" begin="0"
                           end="0">Exam: ${listEntry.exam1.name}</c:forEach>
            </td>
            <td>
                <c:forEach var="listEntry" items="${entry.value}" begin="0"
                           end="0">Exam: ${listEntry.exam2.name}</c:forEach>
            </td>
            <td>
                <c:forEach var="listEntry" items="${entry.value}" begin="0"
                           end="0">Exam: ${listEntry.exam3.name}</c:forEach>
            </td>
            <td>Средний балл аттестата:</td>
            <td>Общая сумма баллов:</td>
            <td>Статус поступления:</td>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="listEntry" items="${entry.value}">
            <tr>
                <td>${listEntry.enrollee.fullName}</td>
                <td>${listEntry.exam1Grade}</td>
                <td>${listEntry.exam2Grade}</td>
                <td>${listEntry.exam3Grade}</td>
                <td>${listEntry.summaryCertificateGrade}</td>
                <td>${listEntry.total}</td>
                <td>${listEntry.admissionState}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <br>
</c:forEach>

<br>
<br>
<input type="button" value="Уведомить абитуриентов о результатах поступления" class="submit-btn"
       onclick="document.location.href='controller?command=sendEmail'">
</body>
</html>
