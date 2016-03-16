<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<div align="center">
    <form name="login" id="login" action="controller" method="POST">
        <input type="hidden" name="command" value="login"/>
        <h1>System "Admission Office"</h1>
        <label>
            Enter email:
            <input required type="email" name="email" class="input-field">
        </label>
        <br> <br>
        <label>
            Enter password:
            <input required type="password" name="password" class="input-field">
        </label>
        <br>
        <br>
        <input type=submit value="Enter" class="submit-btn">
        <input type="button" value="Registration" class="submit-btn"
               onclick="document.location.href='registration.jsp'">
    </form>
</div>
</body>
</html>
