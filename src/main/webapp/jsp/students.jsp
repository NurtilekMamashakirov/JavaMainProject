<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>

<head>
    <script src="../resources/js/function.js"></script>
</head>

<body>
<h1 align="center"><span style="border: 1px solid black">Система управления студентами и их успеваемостью</span><span
        style="font-size: 20px">
    <c:choose>
        <c:when test="${isLogin eq 1}">
            <a href="/logout">${login}, Logout</a>
        </c:when>
        <c:otherwise>
            <a href="/login">Login</a>
        </c:otherwise>
    </c:choose>
</span>
</h1>
<a href="/">На главную</a>
<div style="left: 1000px" align="center" onclick="">
    <input type="submit" style="background: space gray; font-size: 20px"
           value="Просмотреть успеваемость выбранных студентов" onclick="profileStudents()">
    <c:if test="${role == 1}">
        <form action="/student-create"><input type="submit" style="font-size: 20px; background: space gray"
                                              value="Создать студента    ">
        </form>
        <br>

        <input type="submit" style="background: space gray; font-size: 20px" value="Модифицировать выбранного студента"
               onclick="modifyStudents()">

        <input type="submit" style="background: space gray; font-size: 20px" onclick="deleteStudents()"
               value="Удалить выбранных студентов">
    </c:if>
</div>
<br>
<br>
<table align="center" border="1" style="border-collapse: collapse" width="900">
    <caption style="font-size: 23px"><strong>Список студентов</strong></caption>
    <tr style="height: 120px">
        <th></th>
        <th>Фамилия</th>
        <th>Имя</th>
        <th>Группа</th>
        <th>Дата поступления</th>
    </tr>
    <c:forEach items="${students}" var="st">
        <tr style="height: 80px">
            <td><input name="idStudent" type="checkbox" value="${st.id}"></td>
            <td>${st.surname}</td>
            <td>${st.name}</td>
            <td>${st.group}</td>
            <td><fmt:formatDate pattern="dd/MM/yyyy" value="${st.date}"/></td>
        </tr>
    </c:forEach>
</table>
</body>

<form action="/student-delete" method="post" id="deleteForm">
    <input type="hidden" id="deleteHidden" name="deleteHidden">
</form>

<form action="/student-modify" method="get" id="modifyForm">
    <input type="hidden" id="modifyHidden" name="modifyHidden">
</form>

<form action="/student-profile" method="get" id="profileForm">
    <input type="hidden" id="profileHidden" name="profileHidden">
</form>


</html>