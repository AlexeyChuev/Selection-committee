<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>All faculties</title>

    <style type="text/css">
        TABLE {
            border-collapse: collapse; /* Remove the double lines between cells */
        }
        TD, TH {
            padding: 3px; /* The fields around the content of the table */
            border: 1px solid black; /* Frame paramtrs */
            text-align: center;
        }
    </style>
</head>
<body>
<%@ include file="/WEB-INF/jspf/header.jspf" %>
<div align="center">
    <h2> List of faculties: </h2>
    <h3> Click on faculty name to apply for admission </h3>

    <table id="facultiesTable" class="display">
        <thead>
        <tr>
            <td>Faculty name</td>
            <td>Number of budget seats</td>
            <td>Total seats</td>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="faculty" items="${faculties}">
            <tr>
                <td>
                    <a href="<c:url value="controller?command=defineFaculty"> <c:param name="facultyid" value="${faculty.id}"/></c:url>">
                        <c:out value="${faculty.name}"></c:out>
                    </a>
                </td>
                <td>${faculty.budgetVolume}</td>
                <td>${faculty.totalVolume}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <br>
    Sort faculties by:
    <br>
    <div class="menu">
        <a href="controller?command=sortFacultiesAZ">Name (A-Z) </a>
        <br>
        <a href="controller?command=sortFacultiesZA">Name (Z-A) </a>
        <br>
        <a href="controller?command=sortFacultiesByBudget">Number of budget seats </a>
        <br>
        <a href="controller?command=sortFacultiesByTotal">Total number </a>
    </div>
</div>
</body>
</html>
