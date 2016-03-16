<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Block enrollee</title>
    <style>
        table {
            margin: auto;
            border-collapse: collapse; /* Remove the double lines between cells */
        }

        td, th {
            text-align: center;
            padding: 3px; /* The fields around the content of the table */
            border: 1px solid black; /* Frame paramtrs */
        }
    </style>
</head>
<body>
<%@ include file="/WEB-INF/jspf/headerAdmin.jspf" %>
<div align="center">
    <h2>Blocking enrollee</h2>
    <br>
    <br>
    <p align="center"> List of unblocked enrollees</p>
    <table id="unblockEnrolleesTable" class="display">
        <thead>
        <tr>
            <td>Enrollee full name</td>
            <td>ID</td>
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
                        <c:out value="Block"></c:out>
                    </a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <br>
    <input type="button" value="Back to administrator home page" class="submit-btn"
           onclick="document.location.href='controller?command=adminHomePage'">
</div>
</body>
</html>
