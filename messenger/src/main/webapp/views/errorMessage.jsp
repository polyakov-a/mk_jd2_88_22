<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
    <head>
        <title>No Recipient Exists</title>
    </head>
         <body>
            <p style="color: blue"><b>This recipient does not exist, try once again</b></p>
            <p><input type="button" onclick="location.href='${pageContext.request.contextPath}/message';" value="Back" /></p>
        </body>
 </html>