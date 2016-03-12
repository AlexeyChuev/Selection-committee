<%--
  Created by IntelliJ IDEA.
  User: Алексей
  Date: 3/12/2016
  Time: 4:05 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Добавление новых значений для факультета</title>
</head>
<body>
<%@ include file="/WEB-INF/jspf/headerAdmin.jspf"%>
<h2>Информация о факультете:</h2>
<br>
<br>
Название:${faculty.name}
<br>
Количество бюджетных мест:${faculty.budgetVolume}
<br>
Всего мест:${faculty.totalVolume}
<br>
<br>
<h2>Введите новые значения для факультета:</h2>
<br>

<form id="faculty_edit_form" method="POST" action="controller">
    <input type="hidden" name="command" value="updateFaculty"/>
    <input type="hidden" name="facultyid" value="${faculty.id}"/>

    <div class="field">
        <label>
            Новое название:<input type="text" name="newName" value="" required maxlength="50"/>
        </label>
    </div>
    <br>
    <div class="field">
        <label>
            Количество бюджетных мест:
            <input type="number" name="budgetVolume" value="" required min="0"/>
        </label>
    </div>
    <br>
    <div class="field">
        <label>
            Общее количество мест:
            <input type="number" name="totalVolume" value="" required min="0"/>
        </label>
    </div>

    <br>
    <br>
    <div class="field">
        <input type="submit"
               value="Редактировать"/>
    </div>
    <br>
    <div class="field">
        <input type="reset"
               value="Очистить заполненные поля"/>
    </div>


</form>


</body>
</html>
