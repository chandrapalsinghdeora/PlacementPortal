<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!-- <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>IIAM</title> -->

<meta charset="utf-8">
<title>Indian Institute of Management Ahmedabad (IIMA) | Home - IIMA</title>
    <link rel="icon" href="images/favicon.ico" type="image/x-icon"/>
<link rel="stylesheet" href="css/bootstrap.css">

<!-- jQuery library -->
<script src="js/jquery.min.js"></script>

<!-- Latest compiled JavaScript -->
<script src="js/bootstrap.js"></script>

<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="stylesheet" type="text/css" href="css/font-awesome.css">



</head>
<body>
<%-- <jsp:include page="commonJsp/Header.jsp" /> --%>
<jsp:include page="commonJsp/StudentHeader.jsp" />
<div>
	<p> hi, ${sessionBean.name} <br/> 
	<h2>Admin DashBoard</h2>
	<a href="getAdminDashboardPage">Admin</a><br/>
    <a href="getCreateForumPage">Create Forum</a>   
	
	<div><div> <a href="logout">click</a></div>
	<div></div>
	<jsp:include page="commonJsp/Footer.jsp" />
</div>	
</body>
</html>