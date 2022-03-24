<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
        <title>Registration</title>
</head>
<body>

<c:if test="${wrongUser != null}">
    <p style="color: red"><b>${wrongUser}</b></p>
</c:if>

<c:if test="${wrongData != null}">
    <p style="color: red"><b>${wrongData}</b></p>
</c:if>

<p style="color: blue"><b>Register, please</b></p>

<form action="${pageContext.request.contextPath}/signUp" method="POST">
    <table>
        <tbody>
            <tr>
                <td><b>Login:</b></td><td><input type="text" name="login"></td>
            </tr>
            <tr>
                <td><b>Password:</b></td><td><input type="password" name="password"></td>
            </tr>
            <tr>
                <td><b>First name:</b></td><td><input type="text" name="firstName"></td>
            </tr>
            <tr>
               <td><b>Last name:</b></td><td><input type="text" name="lastName"></td>
            </tr>
            <tr>
               <td><b>Middle name:</b></td><td><input type="text" name="middleName"></td>
            </tr>
            <tr>
                <td><b>Birthday:</b></td><td><input type="date" name="birthday"></td>
            </tr>
        </tbody>
    </table>
    <p><button type="submit">Sign Up</button></p>
    <p><input type="button" onclick="location.href='${pageContext.request.contextPath}/mainMenu';" value="Back to main menu" /></p>
</form>
</body>
</html>