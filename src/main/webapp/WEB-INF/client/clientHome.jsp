<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
    <title>Client home page</title>
</head>
<body>
<%@ include file="/WEB-INF/jspf/header.jspf" %>
<div align="center">
    <h2>Your profile</h2>

    <b>Full name:</b><br>${name}
    <br><br>
    <b>City:</b><br>${city}
    <br><br>
    <b>Region:</b><br>${region}
    <br><br>
    <b>School name:</b><br>${school}
    <br><br>
    <b>Email:</b><br>${email}
    <br>
</div>
</body>
</html>
