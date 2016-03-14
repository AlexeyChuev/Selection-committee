<%--
  Created by IntelliJ IDEA.
  User: Алексей
  Date: 3/9/2016
  Time: 6:52 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Форма регистрации посетителей</title>
</head>
<body>
<br>
<br>

<div class="welcomeform">
    <form id="registration_form" method="POST" action="controller">
        <input type="hidden" name="command" value="client_registration" />

        <div class="field">
            <label>  Email:
            <input type="email" name="email" value="" required/>
        </label>
        </div>
        <br>
        <div class="field">
            <label>
                Пароль:
                <input type="password" name="password" value="" required/>
            </label>
        </div>
        <br>
        <div class="field">
            <label>
                ФИО:
                <input type="text" name="full_name" value="" required/>
            </label>
        </div>
        <br>
        <div class="field">
            <label>
                Город:
                <input type="text" name="city" value="" required/>
            </label>
        </div>
        <br>
        <div class="field">
            <label>
                Регион:
                <input type="text" name="region" value="" required/>
            </label>
        </div>
        <br>
        <div class="field">
            <label>
                Название учебного заведения:
                <input type="text" name="school" value="" required/>
            </label>
        </div>

        <br>
        <div class="field">
            <input type="submit"
                   value="Отправить" />
        </div>

        <div class="field">
            <input type="reset"
                   value="Очистить заполненные поля"/>
        </div>

        <br>
        <div class="field">
         <a href="index.jsp">Вернуться на страницу входа</a>
        </div>

    </form>
</div>

</body>
</html>
