<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Inbox</title>
</head>
<body>
    <form action="addInboxData" name="inboxName" method="post">
        <input type="hidden" value="" name=""/>
        <table>            
            <tr><td>Inbox Subject</td><td><input type="text" name="inboxSubject"></td></tr>
            <tr><td>Inbox Text</td><td><input type="text" name="inboxText"></td></tr>
            <tr><td>Label Id</td><td><input type="text" name="labelId"></td></tr>
            <tr><td>Receiver Id</td><td><input type="text" name="receiverId"></td></tr>
            <tr><td>Sender Id</td><td><input type="text" name="senderId"></td></tr>
            <tr><td>Status Id</td><td><input type="text" name="statusId"></td></tr>
            <tr><td colspan="2" align="center"><input type="submit" value="submit"></td></tr>
        </table>
    </form>
</body>
</html>