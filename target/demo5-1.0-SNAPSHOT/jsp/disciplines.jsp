<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Terms List</title>
    <link rel="stylesheet" href="../resources/css/style.css">
    <script src="../resources/js/function.js"></script>
</head>

<body>
<table class="one">
    <thead>
    <tr>
        <td style="text-align: center;"><big>Система управления студентами и их
            успеваемостью</big></td>
    </tr>
    </thead>
</table>
<p style="margin-left: 1250px; margin-top: -20px;">
    <c:choose>
        <c:when test="${isLogin eq 1}">
            <a href="/logout">${login}, Logout</a>
        </c:when>
        <c:otherwise>
            <a href="/login">Login</a>
        </c:otherwise>
    </c:choose>
</p>
<a href="/">На главную</a>
<br>

<span style="margin-left:285px; font-family: cursive;"><b>Список дисциплин </b>
    </span>
<br>
<br>
<table class="seven">
    <thead>
    <tr style="background-color: darkgray;">
        <th></th>
        <th>Наименование дисциплины</th>
    </tr>

    <c:forEach items="${disciplines}" var="discipline">
        <tr>
            <td><input name="idDiscipline" type="checkbox" value="${discipline.id}"></td>
            <td>${discipline.discipline}</td>
        </tr>
    </c:forEach>

    </thead>
</table>
<p style="margin-top: -615px;">
<form action="/discipline-create" method="get"><input
        style="font-family: cursive; border-color: dimgray; background-color: darkgray; width: 320px;margin-left: 750px;"
        type="submit" value="Создать дисциплину"></form>
<br>
<input
        style="font-family: cursive; border-color: dimgray; width: 320px;background-color: darkgray; margin-left: 750px;"
        type="submit" value="Модифицировать выбранную дисциплину" onclick="modifyDiscipline()">
<br>
<br>
<br>
<input
        style="font-family: cursive; border-color: dimgray; background-color: darkgray; width: 320px;margin-left: 750px;"
        type="submit" value="Удалить выбранные дисциплины" onclick="deleteDiscipline()">
</p>

</body>
<form action="/discipline-delete" method="post" id="deleteForm">
    <input type="hidden" name="idsToDelete" id="deleteHidden" value="">
</form>

<form action="/discipline-modify" method="get" id="modifyForm">
    <input type="hidden" name="idToModify" id="modifyHidden" value="">
</form>



</html>
