<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add new faculty</title>
</head>
<body>
<div align="center">
    <%@ include file="/WEB-INF/jspf/headerAdmin.jspf" %>
    <h3>Adding new faculty</h3>
    <div class="welcomeform">
        <form id="addNewFaculty" method="POST" action="controller">
            <input type="hidden" name="command" value="addNewFaculty"/>
            <br>
            <div class="field">
                <label> Faculty name:
                    <input type="text" name="name" value="" required maxlength="50"/>
                </label>
            </div>
            <br>
            <div class="field">
                <label> Number of budget seats:
                    <input type="number" name="budget" value="" required min="0"/>
                </label>
            </div>
            <br>
            <div class="field">
                <label> Total numer of seats:
                    <input type="number" name="total" value="" required min="0"/>
                </label>
            </div>
            <br>
            <div class="field">
                <input type="submit"
                       value="Add"/>
            </div>
            <div class="field">
                <input type="reset"
                       value="Clear all fields"/>
            </div>
            <br><br>
            <input type="button" value="Back to administrator page" class="submit-btn"
                   onclick="document.location.href='controller?command=adminHomePage'">
        </form>
    </div>
</div>
</body>
</html>
