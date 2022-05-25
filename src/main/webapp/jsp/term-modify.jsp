<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Term Modify </title>
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
<a href="/terms">Назад</a>

<span style="margin-left:152px; font-family: cursive;"><big>Для редактирования семестра заполните следующие данные и нажмите кнопку "Применить"</big> </span>
<br>
<br>
<br>
<form style="margin-left: 285px; font-family: cursive;" action="/term-modify" method="post">
    <input type="hidden" name="idTermModify" value="${term.id}">
    <tr style="margin: 10px;"><small>Длительность (в неделях)</small></tr>
    <input style="font-family: cursive; font-size: 11px;" name="duration" type="text" value="${term.duration}">

    <p style="margin: 20px; margin-left: 285px; font-family: cursive;"><small>Дисциплины в семестре</small></p>

    <p><select style="font-family: cursive; width: 220px;margin-left: 459px; margin-top: -35px;" size="8" multiple
               name="idsDisciplines">
        <c:forEach items="${disciplines}" var="discipline">
            <c:choose>
                <c:when test="${disciplinesByTerm.contains(discipline)}">
                    <option selected value="${discipline.id}">${discipline.discipline}</option>
                </c:when>
                <c:otherwise>
                    <option value="${discipline.id}">${discipline.discipline}</option>
                </c:otherwise>
            </c:choose>
        </c:forEach>
    </select></p>


    <input
            style="font-family: cursive; border-color: dimgray; width: 90px;background-color: darkgray; margin-left: 459px; margin-top: 10px;"
            type="submit" value="Применить"></form>
</body>
</html>

