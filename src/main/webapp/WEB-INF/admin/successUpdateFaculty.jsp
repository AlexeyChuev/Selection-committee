<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Faculty edited</title>
</head>
<body>
<%@ include file="/WEB-INF/jspf/headerAdmin.jspf" %>
<div align="center">
    <h2>${facultyName} was edited</h2>
    <input type="button" value="Back to administrator page" class="submit-btn"
           onclick="document.location.href='controller?command=adminHomePage'">
</div>
</body>
</html>
