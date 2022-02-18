<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Message</title>
</head>
<body>
    <form action="/MK-JD2-88-22-1.0-SNAPSHOT/message" method="POST">
        <p style="color: blue"><b>Enter a recipient and a message</b></p>
        <table>
            <tbody>
                <tr>
                    <td><b>Recipient:</b></td>
                    <td>
                        <input type="text" name="recipient">
                    </td>
                </tr>
                <tr>
                    <td><b>Message:</b></td>
                    <td>
                        <input type="text" name="message">
                    </td>
                </tr>
            </tbody>
        </table>
        <p><button type="submit">Send</button></p>
    </form>
    <p><input type="button" onclick="location.href='/MK-JD2-88-22-1.0-SNAPSHOT/mainMenu';" value="Back to main menu" /></p>
</body>
</html>