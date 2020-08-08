<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Indian Institute of Management Ahmedabad (IIMA) | Home - IIMA</title>
    <link rel="icon" href="images/favicon.ico" type="image/x-icon"/>
<script>
function login(){
	//var currentLocation = window.location;
	    document.getElementById('forumId').value='forum';	  
	    document.forumLogin.action = "login";
	    document.forumLogin.submit();        
	
}

function reply(){
	alert("under construction");
}

</script>

</head>
<body>

	<form name="forumLogin" id="forumLogin" commandName="UserBean">
		<input type="hidden" value="forum" name="forumPage" id="forumId" />
		<h3>Forum Main page</h3>
		<p>
			<input type="button" id="loginBtnId" name="loginForum" value="Reply"
				onclick="login();" />
	<!-- </form>

	<form name="" id="replyFormId"> -->
		<table>
			<tr>
				<td>
					<p>Answer the question?</p>
				</td>
			</tr>
			<tr>
				<td>Image</td>
				<td><textarea name="reply" rows="5" cols="20"></textarea></td>
				<td><input type="button" id="replyBtnId" name="aa" value="Post"
				onclick="reply();" /></td>
			</tr>
		</table>
	</form>
</body>
</html>