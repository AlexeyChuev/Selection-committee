<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
    <title>Admin page</title>
</head>
<body>
<%@ include file="/WEB-INF/jspf/headerAdmin.jspf" %>
<div align="center">
    <h2>Administrator home page:</h2>
    Select the action you want to perform:
    <br>

    <input type="button" value="Add new faculty" class="submit-btn"
           onclick="document.location.href='controller?command=addNewFacultyForward'">
    <br>
    <br>
    <input type="button" value="Delete faculty" class="submit-btn"
           onclick="document.location.href='controller?command=deleteFacultyForward'">
    <br>
    <br>
    <input type="button" value="Edit faculty" class="submit-btn"
           onclick="document.location.href='controller?command=editFacultyForward'">
    <br>
    <br>
    <input type="button" value="Block enrollee" class="submit-btn"
           onclick="document.location.href='controller?command=blockEnrolleeForward'">
    <br>
    <br>
    <input type="button" value="Unblock enrollee" class="submit-btn"
           onclick="document.location.href='controller?command=unblockEnrolleeForward'">
    <br>
    <br>
    <input type="button" value="Generate final register" class="submit-btn"
           onclick="document.location.href='controller?command=createRegister'">
</div>
</body>
</html>
