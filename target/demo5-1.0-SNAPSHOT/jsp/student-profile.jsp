<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Student Progress</title>
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
<a href="/students">Назад</a>

<span style="margin-left:152px; font-family: cursive;"><big>Отображена успеваемость для следующего студента: </big>
    </span>
<br>
<br>
<table class="six">
    <thead>
    <tr style="background-color: darkgray;">
        <th>Фамилия</th>
        <th>Имя</th>
        <th>Группа</th>
        <th>Дата поступления</th>
    </tr>
    <tr>
        <td>${student.surname}</td>
        <td>${student.name}</td>
        <td>${student.group}</td>
        <td><fmt:formatDate pattern="dd/MM/yyyy" value="${student.date}"/></td>
    </tr>
    </thead>
</table>
<br>
<br>
<br>
<table class="seven" style="collapse: 1px">
    <thead>
    <tr style="background-color: darkgray;">
        <th>Дисциплина</th>
        <c:if test="${haveMarks eq 1}">
            <th>Оценка</th>
        </c:if>
    </tr>

    <c:if test="${haveMarks eq 1}">
        <c:forEach items="${disciplinesAndMarks}" var="DaM">
            <tr>
                <td>${DaM.get(0).discipline}</td>
                <c:choose>
                    <c:when test="${DaM.get(1) eq 0}">
                        <td></td>
                    </c:when>
                    <c:otherwise>
                        <td>${DaM.get(1)}</td>
                    </c:otherwise>
                </c:choose>
            </tr>
        </c:forEach>
    </c:if>
    <c:if test="${haveMarks eq 0}">
        <c:forEach items="${disciplinesAndMarks}" var="DaM">
            <tr>
                <td>${DaM.get(0).discipline}</td>
            </tr>
        </c:forEach>
    </c:if>

    </thead>
</table>


<p style="margin-left: 730px; margin-top: -600px; font-family: cursive;"><strong>Выбрать семестр</strong></p>

<form action="/student-profile" method="get">
    <input type="hidden" name="profileHidden" value="${student.id}">
    <select style="margin-left: 900px; font-family: cursive;" name="idSelectedTerm">
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
    <div style="margin-left: 1000px;font-family: cursive;">
        <input style="border-color: dimgray;background-color: darkgray; width: 90px; font-family: cursive;"
               type="submit" value="Выбрать">
    </div>
</form>
<c:if test="${haveMarks eq 1}">
    <p style="font-family: cursive; margin-left: 730px;"><b>Средняя оценка за семестр: <fmt:formatNumber pattern="0.00"
                                                                                                         value="${average}"></fmt:formatNumber>
        балла</b></p>
</c:if>


</body>

</html>
