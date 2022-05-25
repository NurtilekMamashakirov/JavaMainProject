<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="../resources/css/style.css">
</head>
<body bgcolor=#d3d3d3>
<table class="one">
    <thead>
    <tr>
        <td style="text-align: center"><big>Система управления студентами и их
            успеваемостью</big></td>
    </tr>
    </thead>
</table>
<h1 align="center">Вход в систему</h1>
<form action="/login" method="post">
    <div style="left: 30%">
        <div>
            <b>Логин</b>
            <input type="text" name="login">
        </div>
        <br>
        <div>
            <b>Пароль</b>
            <input type="password" name="password">
        </div>
        <br>
        <div>
            <select name="role" id="">
                <option value="3">Студент</option>
                <option value="2">Учитель</option>
                <option value="1">Администратор</option>
            </select>
        </div>
        <br>
        <input type="submit" value="Войти" style="background-color: gray">
    </div>
</form>
<br>
<c:if test="${message == '1'}">
    <h4>Неверный логин, пороль или роль!</h4>
</c:if>
</body>
</html>
