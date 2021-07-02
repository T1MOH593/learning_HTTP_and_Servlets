<%--
  Created by IntelliJ IDEA.
  User: Владислав
  Date: 28.05.2021
  Time: 13:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
    <form action="${pageContext.request.contextPath}/login" method="post">
        <label for="email">Email
            <input type="text" value="${param.email}" name="email" id="email">
        </label><br>
        <label for="password">Password
            <input type="password" name="password" id="password">
        </label><br>
        <button type="submit">Login</button>
        <a href="${pageContext.request.contextPath}/registration">
            <button type="button">Register</button>
        </a>
        <c:if test="${param.error != null}">
            <div style="color: red">
                <span>Email or/and password is/are incorrect</span>
            </div>
        </c:if>
    </form>
</body>
</html>
