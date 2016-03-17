<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit faculty</title>
</head>
<body>
<%@ include file="/WEB-INF/jspf/headerAdmin.jspf" %>
<div align="center">
    <h2>Information about faculty:</h2>
    <br>
    <br>
    Faculty name:${faculty.name}
    <br>
    Number of budget seats:${faculty.budgetVolume}
    <br>
    Total number of seats:${faculty.totalVolume}
    <br>
    <br>
    <h2>Input new information about faculty:</h2>
    <br>
    <form id="faculty_edit_form" method="POST" action="controller">
        <input type="hidden" name="command" value="updateFaculty"/>
        <input type="hidden" name="facultyid" value="${faculty.id}"/>

        <div class="field">
            <label>
                New name:<input type="text" name="newName" value="" required maxlength="50"/>
            </label>
        </div>
        <br>
        <div class="field">
            <label>
                Number of budget seats:
                <input type="number" name="budgetVolume" value="" required min="0"/>
            </label>
        </div>
        <br>
        <div class="field">
            <label>
                Total number of seats:
                <input type="number" name="totalVolume" value="" required min="0"/>
            </label>
        </div>
        <br>
        <br>
        <div class="field">
            <input type="submit"
                   value="Edit"/>
        </div>
        <br>
        <div class="field">
            <input type="reset"
                   value="Clear all fields"/>
        </div>
        <br><br>
        <input type="button" value="Back to administrator page" class="submit-btn"
               onclick="document.location.href='controller?command=adminHomePage'">
    </form>
</div>
</body>
</html>
