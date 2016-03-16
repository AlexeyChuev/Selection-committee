<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Unblock Enrollee</title>
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
    <h2>Unblocking enrollee</h2>
    <br>
    <br>
    <p align="center"> List of blocked enrollees</p>

    <table id="blockedEnrolleesTable" class="display">
        <thead>
        <tr>
            <td>Enrollee full name</td>
            <td>ID</td>
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
                        <c:out value="Unblock"></c:out>
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
