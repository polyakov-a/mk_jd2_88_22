<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
    <head>
        <title>Sending a message</title>
    </head>
         <body>
            <p style="color: blue"><b>Your message has been sent</b></p>
            <p><input type="button" onclick="location.href='${pageContext.request.contextPath}/message';" value="Back" /></p>
        </body>
 </html>