<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Terms List</title>
    <link rel="stylesheet" href="../resources/css/style.css">
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

<!--
    <p style="margin-left: 730px; margin-top: -600px; font-family: cursive;"><strong>Выбрать семестр</strong></p> -->

<p style="margin-left: 285px; font-family: cursive;"><strong>Выбрать семестр</strong></p>

<form action="/terms" method="get">
    <select style="margin-left: 450px; font-family: cursive;" name="idSelectedTerm">
        <c:forEach items="${terms}" var="term">
            <c:choose>
                <c:when test="${term.id eq selectedTerm.id}">
                    <option selected value="${term.id}">${term.name}</option>
                </c:when>
                <c:otherwise>
                    <option value="${term.id}">${term.name}</option>
                </c:otherwise>
            </c:choose>
        </c:forEach>
    </select>
    <div style="margin-left: 550px;font-family: cursive; ">

        <input style="font-family: cursive;border-color: dimgray;background-color: darkgray; width: 90px;" type="submit"
               value="Выбрать">

    </div>
</form>

<p style="font-family: cursive; margin-left: 285px;"><b>Длительность семестра: ${selectedTerm.duration}</b></p>
<br>


<span style="margin-left:285px; font-family: cursive;"><b>Список дисциплин </b>
    </span>
<br>
<br>
<table class="seven">
    <thead>
    <tr style="background-color: darkgray;">
        <th>Наименование дисциплины</th>
    </tr>

    <c:forEach items="${disciplines}" var="discipline">
        <tr>
            <td>${discipline.discipline}</td>
        </tr>
    </c:forEach>

    </thead>
</table>
<p style="margin-top: -615px;">
<form action="/term-create" method="get"><input
        style="font-family: cursive; border-color: dimgray; background-color: darkgray; width: 320px;margin-left: 750px;"
        type="submit" value="Создать семестр..."></form>
<br>
<form action="/term-modify" method="get">
    <input type="hidden" name="idTermToModify" value="${selectedTerm.id}">
    <input
            style="font-family: cursive; border-color: dimgray; width: 320px;background-color: darkgray; margin-left: 750px;"
            type="submit" value="Модифицировать текущий семестр..."></form>
<br>
<form action="/term-delete" method="post">
    <input type="hidden" name="idTermToDelete" value="${selectedTerm.id}">
    <input
            style="font-family: cursive; border-color: dimgray; background-color: darkgray; width: 320px;margin-left: 750px;"
            type="submit" value="Удалить текущий семестр"></form>
</p>
</body>

</html>
