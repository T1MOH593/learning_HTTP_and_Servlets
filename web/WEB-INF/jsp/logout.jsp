<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${param.user != null}">
    <div>
        <form action="${pageContext.request.contextPath}/logout" method="post">Logout</form>
    </div>
</c:if>