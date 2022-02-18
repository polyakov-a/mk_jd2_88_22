<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>User Info</title>
</head>
<body>
    <p style="color: blue"><b>User information:</b></p>
    <p><b>Login: </b>${user.login}</p>
    <p><b>About you: </b>${user.lastName} ${user.firstName}
    <c:if test="${user.middleName != null}">
        ${user.middleName}
    </c:if></p></p>
    <p><b>Birthday: </b>${user.birthday}</p>
    <p><input type="button" onclick="location.href='/MK-JD2-88-22-1.0-SNAPSHOT/mainMenu';" value="Back to main menu" /></p>
</body>
</html>