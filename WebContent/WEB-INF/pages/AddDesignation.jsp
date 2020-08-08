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
					<li><a href="#">Role</a></li>
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

				<input type="button" class="btn btn-primary pull-right" onclick="clearDesignationForm();"
					data-toggle="modal" data-target="#C-role-edit" value="Add Role">

			</div>
			<div class="clearfix"></div>
			<div class="table-responsive">
			<table id="main-fourum" class="table table-striped table-bordered">
				<thead>
					<tr>
						   <th>S.No</th>
		                   <th>Role Name</th>
		                   <th>Description</th>
						   <th>Action Edit</th>
						   <th>Action Delete</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${Designationdata}" var="designation" varStatus="loop">
						<tr>
							<td>${loop.index+1}.</td>
							<td>${designation.designationname}</td>
							<td>${designation.designationdescription}</td>
							<td><input type="button" class="btn btn-primary"
								data-name="${designation.designationname}" data-description="${designation.designationdescription}"
								data-toggle="modal" data-id="${designation.designationid}" data-target="#C-role-edit"
								id="edit" value="Edit"> 
								 <!-- Modal --></td>
							<td> <a href="#" onclick="deleteDesignation(${designation.designationid})">Delete</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			</div>
			 <form method="post" id="deleteDesignation" >
          		<input type="hidden" name="deleteDesignationId" id="deleteDesignationId"/>
          	</form>
			<!-- / panel preview -->
			<div class="modal fade" id="C-role-edit" role="dialog">
				<div class="modal-dialog">

					<!-- Modal content-->
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal">&times;</button>
							<h4 class="modal-title">Add Role</h4>
						</div>
						<form id="submitKmIIMStudent" action="saveDesignation" method="POST"
							name="Designation">
							
							<input type="hidden"  name="designationid" id="designationid" value="0"/>
							
							<div class="modal-body">
								
								<div class="row">
									<label class="col-md-4">Designation Name</label>
									<div class="col-md-8">
										<input class="form-control"  id="designationname" placeholder="Designation Name"
											type="text" name="designationname" required>
									</div>
								</div>
								<p></p>
								<div class="row">
									<label class="col-md-4">Designation Description</label>
									<div class="col-md-8">
										<input class="form-control" id="designationdescription" placeholder="Designation Description"
											type="text" name="designationdescription" required>
									</div>
								</div>
						
							</div>
							<div class="modal-footer">
								<button type="submit" class="btn btn-default">Submit</button>
								<button type="button" class="btn btn-default"
									data-dismiss="modal">Cancel</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="commonJsp/Footer.jsp" />
</body>
<script type="text/javascript">

$(document).on("click", "#edit", function () {
  
    var Id = $(this).data('id');
    $("#designationid").val($(this).data('id'));
    $("#designationname").val($(this).data('name'));
    $("#designationdescription").val($(this).data('description'));
    $("#C-role-edit").show();
});

function deleteDesignation(id){
	//deleteCluster
	$("#deleteDesignationId").val(id);
	document.getElementById("deleteDesignation").action = "deleteDesignation";
	document.getElementById("deleteDesignation").method = "post";
	document.getElementById("deleteDesignation").submit();
}
function clearDesignationForm(){
	var desigName=document.getElementById("designationname");
	var desigDesc=document.getElementById("designationdescription");
	desigName.value=''
	desigDesc.value=''
}
</script>





</html>