
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration form</title>
</head>
<body>

<br>
<br>

<div class="registrationform" align="center">
    <form id="registration_form" method="POST" action="controller">
        <input type="hidden" name="command" value="client_registration"/>
        <h1>Registration</h1>
        <br><br>
        <h2>Input information about yourself</h2>
        <br><br>
        <div class="field">
            <label> Email:
                <input type="email" name="email" value="" required/>
            </label>
        </div>
        <br>
        <div class="field">
            <label>
                Password:
                <input type="password" name="password" value="" required/>
            </label>
        </div>
        <br>
        <div class="field">
            <label>
                Full name:
                <input type="text" name="full_name" value="" required/>
            </label>
        </div>
        <br>
        <div class="field">
            <label>
                City:
                <input type="text" name="city" value="" required/>
            </label>
        </div>
        <br>
        <div class="field">
            <label>
                Region:
                <input type="text" name="region" value="" required/>
            </label>
        </div>
        <br>
        <div class="field">
            <label>
                School name:
                <input type="text" name="school" value="" required/>
            </label>
        </div>

        <br>
        <div class="field">
            <input type="submit"
                   value="Submit"/>
        </div>
        <br>
        <div class="field">
            <input type="reset"
                   value="Clear all fields"/>
        </div>

        <br>
        <div class="field">
            <a href="index.jsp">Back to login page</a>
        </div>

    </form>
</div>

</body>
</html>
