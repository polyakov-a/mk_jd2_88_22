<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Message</title>
</head>
<body>
    <p><b>Received messages:</b></p>
    <table border="1px">
        <tr>
            <th width="25%"><b>Sender</b></th>
            <th width="25%"><b>Date and time</b></th>
            <th width="50%"><b>Message</b></th>
        </tr>
        <c:forEach var="message" items="${messages}">
                <tr>
                    <td width="25%"> ${message.sender}</td>
                    <td width="25%"> ${message.date}</td>
                    <td width="50%"> ${message.message}</td>
                </tr>
        </c:forEach>
    </table>
    <p><input type="button" onclick="location.href='${pageContext.request.contextPath}/mainMenu';" value="Back to main menu" /></p>
</body>
</html>