<%--
  Created by IntelliJ IDEA.
  User: Алексей
  Date: 3/12/2016
  Time: 3:56 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Редкатирование факультета</title>
</head>
<body>

<%@ include file="/WEB-INF/jspf/headerAdmin.jspf"%>
<h3>Выберите факультет, который хотите отредкатировать:</h3>
<br>


<form id="deleteFaculty" method="POST" action="controller">
    <input type="hidden" name="command" value="addNewInformationAboutFaculty" />
    <p><select size="1" name="facultiesSelect" required>
        <option disabled>Выберите факультет</option>
        <c:forEach var="faculty" items="${faculties}">
            <option value="${faculty.id}">${faculty.name}</option>
        </c:forEach>
    </select></p>
    <p><input type="submit" value="Редактировать"></p>
</form>

</body>
</html>
