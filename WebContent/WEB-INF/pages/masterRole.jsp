<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Indian Institute of Management Ahmedabad (IIMA) | Home - IIMA</title>
    <link rel="icon" href="images/favicon.ico" type="image/x-icon"/>

<!-- Bootstrap -->
<link href="css/bootstrap.css" rel="stylesheet">

<!-- Custom -->
<link href="css/custom.css" rel="stylesheet">


<link
	href='http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800'
	rel='stylesheet' type='text/css'>
<link rel="stylesheet" href="css/font-awesome.css">

<!-- CSS STYLE-->
<link rel="stylesheet" type="text/css" href="css/style.css"
	media="screen" />

<!-- SLIDER REVOLUTION 4.x CSS SETTINGS -->
<link rel="stylesheet" type="text/css" href="rs-plugin/css/settings.css"
	media="screen" />


<!-- <script>
	function OnButton1() {
		document.forum.action = "saveForum"
		document.forum.submit(); // Submit the page
	}

	function OnButton2() {
		document.forum.action = "editForum"
		document.forum.submit(); // Submit the page
	}
</script> -->


</head>
<body>

	<jsp:include page="commonJsp/Header.jsp" />
	<div class="clearfix"></div>
	<div class="breadcumb-wrapper">
		<div class="clearfix">
			<div class="pull-left">
				<ul class="list-inline link-list">
					<li><a href="forumHomePage">Home</a></li>
					<li><a href="getmasterrole">Role</a></li>
					<!--  <li>Question</li> -->
				</ul>
			</div>

		</div>
	</div>
	<div class="container">


		<div class="row" style="height:400px">
			<div class="col-sm-offset-3 col-sm-5">

				<div class="panel panel-default add-forum">
						<c:if test="${empty role}">
						<div class="panel-heading">Add Role</div>
					    <div class="panel-body form-horizontal">
							<form method="post" name="Role" action="saverole">
						</c:if>
						<c:if test="${ not empty role}">
						<div class="panel-heading">Edit Forum</div>
					    <div class="panel-body form-horizontal">
							<form method="post" name="Role" action="editRoleValues">
								<input type="hidden" value='${roleId}' name="pkroleid">
						</c:if>
						<div class="form-group" ${roleName}>
							<label class="col-sm-4 control-label">Role Name ${roleId}</label>
							<div class="col-sm-8">
								<input type="text" class="form-control" name="rolename"
									placeholder="Role Name" value="${roleName}" required>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 control-label">Role Description</label>
							<div class="col-sm-8">
							    <input type="text" class="form-control" name="roledescription"
									placeholder="Role Description" value="${roleDescription}" required>
							</div>
						</div>


						<div class="form-group">
							<label class="col-sm-4 control-label">ModuleID</label>
							<div class="col-sm-8">
								<select class="form-control" name="moduleid" required>
									<option value="">Select One</option>
									<c:choose>
									<c:when test="${moduleID==1}">
										<option value="1" selected="selected">1</option>
										<option value="2">2</option>
									</c:when>
									<c:when test="${moduleID==2}">
										<option value="1" >1</option>
										<option value="2" selected="selected">2</option>
									</c:when>
									<c:otherwise>
										<option value="1">1</option>
										<option value="2">2</option>
									</c:otherwise>
									</c:choose>
									
									
								</select>
							</div>
						</div>
	
						<div class="form-group">
							<div class="col-sm-12 text-right">
								<c:if test="${empty role}">
									<input type="submit" value="Submit" id="save"
										class="btn btn-primary" />
								</c:if>
								<c:if test="${ not empty role}">
									<input type="hidden" value="${roleId}" name="forumId"/>
									<input type="submit" value="Update" id="update"
										class="btn btn-primary" />
								</c:if>
							</div>
						</div>
					
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="commonJsp/Footer.jsp" />
</body>

</html>