<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Title</title>
  <link rel="stylesheet" href="//code.jquery.com/ui/1.13.1/themes/base/jquery-ui.css">
  <script src="https://code.jquery.com/jquery-3.6.0.js"></script>
  <script src="https://code.jquery.com/ui/1.13.1/jquery-ui.js"></script>
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
    </span></h1>
<a href="/">На главную</a> <a href="/disciplines">Назад</a>
<h2 align="center">Для создания дисциплины заполните все поля и нажмите кнопку "Создать"</h2>
<br>
<form action="/discipline-create" method="post">
<pre align="center">
             Название <input type="text" name="disciplineName"> <br>

        <input type="submit" value="Создать">
</pre>
</form>
<c:if test="${error == '1'}">
  <h4>Поля не должны быть пустыми!!!</h4>
</c:if>
</body>
</html>
