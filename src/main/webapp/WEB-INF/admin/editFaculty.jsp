<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Edit faculty</title>
</head>
<body>
<div align="center">
    <%@ include file="/WEB-INF/jspf/headerAdmin.jspf" %>
    <h3>Select the faculty which you want to edit:</h3>
    <br>

    <form id="deleteFaculty" method="POST" action="controller">
        <input type="hidden" name="command" value="addNewInformationAboutFaculty"/>
        <p><select size="1" name="facultiesSelect" required>
            <option disabled>Choose faculty</option>
            <c:forEach var="faculty" items="${faculties}">
                <option value="${faculty.id}">${faculty.name}</option>
            </c:forEach>
        </select></p>
        <p><input type="submit" value="Edit"></p>
    </form>
</div>
</body>
</html>
