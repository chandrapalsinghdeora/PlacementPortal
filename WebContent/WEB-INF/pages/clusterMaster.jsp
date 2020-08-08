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
					<li><a href="#">Cluster</a></li>
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

				<input type="button" class="btn btn-primary pull-right" onclick="clearClusterForm();"
					data-toggle="modal" data-target="#C-role-edit" value="Add Cluster">

			</div>
			<div class="clearfix"></div>
			<div class="table-responsive">
			<table id="main-fourum" class="table table-striped table-bordered">
				<thead>
					<tr>
						<th>Sr. No.</th>
						<th>Cluster Name</th>
						<th>Description</th>
						<th>Action Edit</th>
						<th>Action Delete</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${clusterList}" var="fm" varStatus="loop">
						<tr>
							<td>${loop.index+1}.</td>
							<td>${fm.clusterName}</td>
							<td>${fm.description}</td>
							<td><input type="button" class="btn btn-primary"
								data-name="${fm.clusterName}" data-description="${fm.description}"
								data-toggle="modal" data-id="${fm.clusterId}" data-target="#C-role-edit"
								id="edit" value="Edit"> 
								 <!-- Modal --></td>
							<td>
							 <%-- <a href="#" onclick="deleteCluster(${fm.clusterId});">Delete</a> --%>
							 
							 <a href="#"  data-toggle="modal" data-target="#confirm-box1${loop.count}">Delete</a>
						      <div class="modal fade" id="confirm-box1${loop.count}" role="dialog">
						      <div class="modal-dialog">    
						      <!-- Modal content-->
						      <div class="modal-content">
						        <div class="modal-header">
						          <button type="button" class="close" data-dismiss="modal">&times;</button>
						          <h4 class="modal-title">Confirmation Box</h4>
						        </div>
						        <div class="modal-body">
						            <p>Are you sure want to delete cluster?</p>
						        </div>
						        <div class="modal-footer">
						          <button type="button" class="btn btn-default" data-dismiss="modal">No</button>
						          <button type="button" onclick="deleteCluster(${fm.clusterId})"  class="btn btn-default" >Yes</button>        
						        </div>
						      </div>      
						      </div>
						     </div>
							 
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			</div>
			 <form method="post" id="deleteClusterForm" >
          		<input type="hidden" name="deleteClusterId" id="deleteClusterId"/>
          	</form>
			<!-- / panel preview -->
			<div class="modal fade" id="C-role-edit" role="dialog">
				<div class="modal-dialog">

					<!-- Modal content-->
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal">&times;</button>
							<h4 class="modal-title">Add Cluster</h4>
						</div>
						<form id="submitCluster" action="getCluster" method="POST"
							name="Cluster">
							<input type="hidden"  name="clusterId" id="clusterId" value="0"/>
							<div class="modal-body">
								<div class="row">
									<label class="col-md-6">Cluster Name</label>
									<div class="col-md-6">
										<input class="form-control"  id="clusterName" placeholder="Cluster Name"
											type="text" name="clusterName" required>
									</div>
								</div>
								<p></p>
								<div class="row">
									<label class="col-md-6">Description</label>
									<div class="col-md-6">
										<input class="form-control" id="description" placeholder="Description"
											type="text" name="description" required>
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
	//alert("hiii");
    var clusterId = $(this).data('id');
    $("#clusterId").val( clusterId );
    $("#clusterName").val($(this).data('name'));
    $("#description").val($(this).data('description'));
  //  alert("cluster :: "+ $("#clusterId").val());
    $("#C-role-edit").show();
});

function deleteCluster(id){
	//deleteCluster
	$("#deleteClusterId").val(id);
	document.getElementById("deleteClusterForm").action = "deleteCluster";
	document.getElementById("deleteClusterForm").method = "post";
	document.getElementById("deleteClusterForm").submit();
}

function clearClusterForm(){

    var clustername=document.getElementById("clusterName");
	var clusterdesc=document.getElementById("description");
	clustername.value=''
	clusterdesc.value=''
}


</script>





</html>