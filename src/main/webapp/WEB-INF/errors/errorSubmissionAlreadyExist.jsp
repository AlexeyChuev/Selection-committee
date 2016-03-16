<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Submission already exists</title>
</head>
<body>
<%@ include file="/WEB-INF/jspf/header.jspf" %>
<div align="center">
    <h2>You have already applied for this Faculty</h2>
    <input type="button" value="Back to profile page" class="submit-btn"
           onclick="document.location.href='controller?command=clientHomePage'">
</div>
</body>
</html>
