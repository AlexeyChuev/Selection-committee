<%--
  Created by IntelliJ IDEA.
  User: Алексей
  Date: 3/12/2016
  Time: 1:11 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Добавить новый факультет</title>
</head>
<body>
<%@ include file="/WEB-INF/jspf/headerAdmin.jspf" %>
<h3>Добавление нового факультета</h3>
<div class="welcomeform">
    <form id="addNewFaculty" method="POST" action="controller">
        <input type="hidden" name="command" value="addNewFaculty"/>
        <br>
        <div class="field">
            <label> Название факультата:
                <input type="text" name="name" value="" required maxlength="50"/>
            </label>
        </div>
        <br>
        <div class="field">
            <label> Количество бюджетных мест:
                <input type="number" name="budget" value="" required min="0"/>
            </label>
        </div>
        <br>
        <div class="field">
            <label> Всего мест:
                <input type="number" name="total" value="" required min="0"/>
            </label>
        </div>
        <br>
        <div class="field">
            <input type="submit"
                   value="Добавить"/>
        </div>
        <div class="field">
            <input type="reset"
                   value="Очистить заполненные поля"/>
        </div>
    </form>
</div>
</body>
</html>
