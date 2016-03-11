<%--
  Created by IntelliJ IDEA.
  User: Алексей
  Date: 3/11/2016
  Time: 7:29 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Заявка на факультет</title>
</head>
<body>
<%@ include file="/WEB-INF/jspf/header.jspf" %>

<form id="submission_apply_form" method="POST" action="controller">

    <input type="hidden" name="command" value="applySubmission"/>

    <input type="hidden" name="facultyid" value="${faculty.id}"/>

    <h2> Подача заявки на <c:out value="${faculty.name}"></c:out>: </h2>
    <br>
    <div class="field">
        <label>
            Оценка по ЗНО. Предмет - <c:out value="${facultySubject1.name}"></c:out> (по 200-бальной шкале):
            <input type="number" name="exam1" value="" required max="200" min="100"/>
            <input type="hidden" name="exam1id" value="${facultySubject1.id}"/>
        </label>
    </div>
    <br>
    <div class="field">
        <label>
            Оценка по ЗНО. Предмет - <c:out value="${facultySubject2.name}"></c:out> (по 200-бальной шкале):
            <input type="number" name="exam2" value="" required max="200" min="100"/>
            <input type="hidden" name="exam2id" value="${facultySubject2.id}"/>

        </label>
    </div>
    <br>
    <div class="field">
        <label>
            Оценка по ЗНО. Предмет - <c:out value="${facultySubject3.name}"></c:out> (по 200-бальной шкале):
            <input type="number" name="exam3" value="" required max="200" min="100"/>
            <input type="hidden" name="exam3id" value="${facultySubject3.id}"/>
        </label>
    </div>
    <br>
    <br>
    <h3> Оценки аттестата (по 12-бальной шкале): </h3>
    <br>
    <div class="field">
        <label>
            Математика:
            <input type="number" name="maths" value="" required max="12" min="1"/>
        </label>
    </div>
    <br>
    <div class="field">
        <label>
            История:
            <input type="number" name="history" value="" required max="12" min="1"/>
        </label>
    </div>
    <br>
    <div class="field">
        <label>
            География:
            <input type="number" name="geography" value="" required max="12" min="1"/>
        </label>
    </div>
    <br>
    <div class="field">
        <label>
            Химия:
            <input type="number" name="chemistry" value="" required max="12" min="1"/>
        </label>
    </div>
    <br>
    <div class="field">
        <label>
            Физика:
            <input type="number" name="physics" value="" required max="12" min="1"/>
        </label>
    </div>
    <br>
    <div class="field">
        <label>
            Английский:
            <input type="number" name="english" value="" required max="12" min="1"/>
        </label>
    </div>
    <br>
    <div class="field">
        <label>
            Украинский:
            <input type="number" name="ukrainian" value="" required max="12" min="1"/>
        </label>
    </div>
    <br>
    <div class="field">
        <label>
            Биология:
            <input type="number" name="biology" value="" required max="12" min="1"/>
        </label>
    </div>
    <br>
    <div class="field">
        <label>
            Информатика:
            <input type="number" name="computer_science" value="" required max="12" min="1"/>
        </label>
    </div>
    <br>
    <div class="field">
        <label>
            Экономика:
            <input type="number" name="economics" value="" required max="12" min="1"/>
        </label>
    </div>
    <!--
    <br>
    <form action="controller?command=applySubmission">
        <button type="submit">Подать заявку</button>
    </form>


    <!--
    <input type=submit value="Подать заявку" class="submit-btn">
    !-->

    <br>
    <div class="field">
        <input type="submit"
               value="Подать заявку"/>
    </div>


</form>
</body>
</html>
