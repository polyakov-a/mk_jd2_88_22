<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>User messenger page</title>
</head>
<body>
    <c:if test="${user == null}">
        <p style="color: blue"><b>Welcome, Stranger!</b></p>
        <p><b>Here you can:</b></p>
        <p><input type="button" onclick="location.href='${pageContext.request.contextPath}/signUp';" value="Sign Up" /></p>
        <p><input type="button" onclick="location.href='${pageContext.request.contextPath}/signIn';" value="Sign In" /></p>
    </c:if>

    <c:if test="${user != null}">
        <p style="color: blue"><b>Welcome</b>, ${user.lastName} ${user.firstName}
        <c:if test="${user.middleName != null}">
            ${sessionScope.user.middleName}
        </c:if>!</p>
        <p><b>Here you can:</b></p>
        <p><input type="button" onclick="location.href='${pageContext.request.contextPath}/chats';" value="View your messages" /></p>
        <p><input type="button" onclick="location.href='${pageContext.request.contextPath}/message';" value="Send a message" /></p>
        <p><input type="button" onclick="location.href='${pageContext.request.contextPath}/userInfo';" value="About you" /></p>
        <p><input type="button" onclick="location.href='${pageContext.request.contextPath}/logout';" value="Logout" /></p>
    </c:if>
</body>
</html>