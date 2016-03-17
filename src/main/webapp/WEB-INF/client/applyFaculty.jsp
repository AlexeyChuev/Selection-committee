<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Faculty submission</title>
</head>
<body>
<%@ include file="/WEB-INF/jspf/header.jspf" %>

<div align="center">
    <form id="submission_apply_form" method="POST" action="controller">

        <input type="hidden" name="command" value="applySubmission"/>

        <input type="hidden" name="facultyid" value="${faculty.id}"/>

        <h2> Apply for <c:out value="${faculty.name}"></c:out>: </h2>
        <br>
        <div class="field">
            <label>
                Grade on external testing. Subject - <c:out value="${facultySubject1.name}"></c:out> (200-point scale):
                <input type="number" name="exam1" value="" required max="200" min="100"/>
                <input type="hidden" name="exam1id" value="${facultySubject1.id}"/>
            </label>
        </div>
        <br>
        <div class="field">
            <label>
                Grade on external testing. Subject - <c:out value="${facultySubject2.name}"></c:out> (200-point scale):
                <input type="number" name="exam2" value="" required max="200" min="100"/>
                <input type="hidden" name="exam2id" value="${facultySubject2.id}"/>

            </label>
        </div>
        <br>
        <div class="field">
            <label>
                Grade on external testing. Subject - <c:out value="${facultySubject3.name}"></c:out> (200-point scale):
                <input type="number" name="exam3" value="" required max="200" min="100"/>
                <input type="hidden" name="exam3id" value="${facultySubject3.id}"/>
            </label>
        </div>
        <br>
        <br>
        <h3> School certificate grades (12-point scale): </h3>
        <br>
        <div class="field">
            <label>
                Mathematics:
                <input type="number" name="maths" value="" required max="12" min="1"/>
            </label>
        </div>
        <br>
        <div class="field">
            <label>
                History:
                <input type="number" name="history" value="" required max="12" min="1"/>
            </label>
        </div>
        <br>
        <div class="field">
            <label>
                Geography:
                <input type="number" name="geography" value="" required max="12" min="1"/>
            </label>
        </div>
        <br>
        <div class="field">
            <label>
                Chemistry:
                <input type="number" name="chemistry" value="" required max="12" min="1"/>
            </label>
        </div>
        <br>
        <div class="field">
            <label>
                Physics:
                <input type="number" name="physics" value="" required max="12" min="1"/>
            </label>
        </div>
        <br>
        <div class="field">
            <label>
                English:
                <input type="number" name="english" value="" required max="12" min="1"/>
            </label>
        </div>
        <br>
        <div class="field">
            <label>
                Ukrainian:
                <input type="number" name="ukrainian" value="" required max="12" min="1"/>
            </label>
        </div>
        <br>
        <div class="field">
            <label>
                Biology:
                <input type="number" name="biology" value="" required max="12" min="1"/>
            </label>
        </div>
        <br>
        <div class="field">
            <label>
                Computer science:
                <input type="number" name="computer_science" value="" required max="12" min="1"/>
            </label>
        </div>
        <br>
        <div class="field">
            <label>
                Economics:
                <input type="number" name="economics" value="" required max="12" min="1"/>
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

        <br><br>
        <input type="button" value="Back to profile page" class="submit-btn"
               onclick="document.location.href='controller?command=clientHomePage'">
    </form>
</div>
</body>
</html>
