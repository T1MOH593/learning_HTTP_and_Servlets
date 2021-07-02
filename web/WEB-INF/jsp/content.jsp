<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <%@ include file="logout.jsp"%>
    <div>
        <span>Content. Русский</span>
        <p>Size :${requestScope.flights.size()}</p>
        <p>Id : ${requestScope.flights[0].id}</p>
        <p>Id : ${requestScope.flights[0].description}</p>
        <p>Id 2: ${requestScope.flights.get(0)}</p>
        <p>${sessionScope.flightsMap[2]}</p>
        <p>JSESSION id: ${cookie["JSESSIONID"].value}, unique identifier</p>
        <p>Header: ${header["cookie"]}</p>
        <p>Param: ${param.it}</p>
        <p>Param 2: ${param.bf}</p>
    </div>
    <%@ include file="footer.jsp"%>
</body>
</html>
