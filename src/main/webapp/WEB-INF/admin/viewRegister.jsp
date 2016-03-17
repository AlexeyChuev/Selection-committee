<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Final register</title>

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

    <h2> Final register: </h2>
    <br>

    <c:forEach var="entry" items="${map}">
        <h3> ${entry.key.name} </h3>
        <table id="registerTable" class="display">
            <thead>
            <tr>
                <td>Enrollee full name</td>
                <td>
                    <c:forEach var="listEntry" items="${entry.value}" begin="0"
                               end="0">${listEntry.exam1.name}</c:forEach>
                </td>
                <td>
                    <c:forEach var="listEntry" items="${entry.value}" begin="0"
                               end="0">${listEntry.exam2.name}</c:forEach>
                </td>
                <td>
                    <c:forEach var="listEntry" items="${entry.value}" begin="0"
                               end="0">${listEntry.exam3.name}</c:forEach>
                </td>
                <td>Average score of the certificate</td>
                <td>Total amount of points</td>
                <td>Admission status</td>
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
    <input type="button" value="Notify applicants of the results of admission (send e-mails)" class="submit-btn"
           onclick="document.location.href='controller?command=sendEmails'">

    <input type="button" value="Back to administrator page" class="submit-btn"
           onclick="document.location.href='controller?command=adminHomePage'">
</div>
</body>
</html>
