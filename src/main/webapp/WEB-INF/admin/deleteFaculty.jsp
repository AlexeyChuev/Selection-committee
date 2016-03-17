
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Delete faculty</title>
</head>
<body>
<%@ include file="/WEB-INF/jspf/headerAdmin.jspf" %>
<div align="center">
<h2>Select the faculty which you want to delete:</h2>
<br>
<h3><b>Warning!</b> Deleting of the faculty will remove all applications submitted to it.</h3>
<br>

<form id="deleteFaculty" method="POST" action="controller">
    <input type="hidden" name="command" value="deleteFaculty"/>
    <p><select size="1" name="facultiesSelect" required>
        <option disabled>Choose faculty</option>
        <c:forEach var="faculty" items="${faculties}">
            <option value="${faculty.id}">${faculty.name}</option>
        </c:forEach>
    </select></p>
    <p><input type="submit" value="Delete"></p>
    <br><br>
    <input type="button" value="Back to administrator page" class="submit-btn"
           onclick="document.location.href='controller?command=adminHomePage'">
</form>
</div>
</body>
</html>
