<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Login</title>
</head>
<body>

<c:if test="${wrongData != null}">
    <p style="color: red"><b>${wrongData}</b></p>
</c:if>

<c:if test="${wrongUser != null}">
    <p style="color: red"><b>${wrongUser}</b></p>
</c:if>

<c:if test="${wrongPassword != null}">
    <p style="color: red"><b>${wrongPassword}</b></p>
</c:if>

<p><b>Enter your login and password</b></p>
<form action="${pageContext.request.contextPath}/signIn" method="POST">
    <table>
        <tbody>
            <tr>
                <td><b>Login:</b></td><td><input type="text" name="login"></td>
            </tr>
            <tr>
                <td><b>Password:</b></td><td><input type="password" name="password"></td>
            </tr>
        </tbody>
    </table>
    <p><input type="submit" value="Sign In" /></p>
    <p><input type="button" onclick="location.href='${pageContext.request.contextPath}/signUp';" value="Back to registration" /></p>
</form>
</body>
</html>