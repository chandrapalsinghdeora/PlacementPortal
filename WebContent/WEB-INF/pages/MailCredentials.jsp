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

</head>
<body>
	<jsp:include page="commonJsp/Header.jsp" />
	<div class="clearfix"></div>
	<div class="breadcumb-wrapper">
		<div class="clearfix">
			<div class="pull-left">
				<ul class="list-inline link-list">
					<li><a href="forumHomePage">Home</a></li>
					<li>Email Credentials</li>
					<!--  <li>Question</li> -->
				</ul>
			</div>

		</div>
	</div>
	<div class="container">
		<div class="row">
			<!-- panel preview -->
			<div class="space"></div>
			<div class="container-fluid btn-container">

			</div>
			<div class="clearfix"></div>
			<div class="table-responsive">
			<table id="main-fourum" class="table table-striped table-bordered">
				<thead>
					<tr>
						   <th>S.No</th>
							<th>Email Id</th>
							<%-- <th>Password</th>  --%>
						   <th>Action Edit</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${Designationdata1}" var="designation" varStatus="loop">
						<tr>
							<td>${loop.index+1}.</td>
							<td>${designation.email}</td>
							<%--  <td>${designation.password}</td> --%>
							<td><input type="button" class="btn btn-primary"
								data-name="${designation.email}" data-description="********************"
								 data-id="${designation.editid}"
								data-toggle="modal"  data-target="#C-role-edit"
								id="edit" value="Edit"> 
								 <!-- Modal --></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			</div>
			
			<!-- / panel preview -->
			<div class="modal fade" id="C-role-edit" role="dialog">
				<div class="modal-dialog">

					<!-- Modal content-->
					<div class="modal-content">
						<form id="editMail">
						<input type="hidden" id="editid1" name="editid">
							<div class="modal-body">
								<div class="row">
									<label class="col-md-4">Email Id</label>
													<span class="col-md-8" id="emailId" >	</span>
								</div>
								<p></p>
								<div class="row">
									<label class="col-md-4">Password</label>
									<div class="col-md-8">
										<input class="form-control" id="pwd" 
											type="text" name="password" >
									</div>
								</div>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-primary" id="gfghh">Submit</button>
								<button type="button" class="btn btn-primary"
									data-dismiss="modal">Cancel</button>
							</div>
						</form>
					</div>
				</div>
			</div>
			
			<div class="modal fade" id="C-role-confirm" role="dialog">
				<div class="modal-dialog">

					<!-- Modal content-->
					<div class="modal-content">
							<div class="modal-body">
								<div class="row">
									<label class="col-md-8">Are you sure to update password of this email id '<span id="showmailid"></span>' ?</label>
								</div>
								<p></p>
								
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-primary" onclick="csd()">Ok</button>
								<button type="button" class="btn btn-primary"
									data-dismiss="modal">Cancel</button>
							</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="commonJsp/Footer.jsp" />
</body>
<script type="text/javascript">

$(document).on("click", "#edit", function () {
    var Id = $(this).data('name');
    $("#emailId").html(Id);
    $("#editid1").val($(this).data('id'));
    $("#pwd").val($(this).data('description'));
    $("#C-role-edit").show();
});

$(function(){
	$('#gfghh').click(function(){
		 $("#showmailid").html($("#emailId").html());
		 $("#C-role-confirm").modal('show');
	})
})
function csd()
{
	document.getElementById("editMail").action="updateMailingpwd";
	document.getElementById("editMail").method="post";
	document.getElementById("editMail").submit();
}
</script>
</html>