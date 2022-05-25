<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
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
    </span></h1>
<br>
<br>
<pre>                            <a href="/students">Студенты</a>                                                    <a
        href="/disciplines">Дисциплины</a>                                                    <a
        href="/terms">Семестры</a></pre>