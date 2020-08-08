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
					<li><a href="#">Company</a></li>
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
			
				 <c:if test="${sessionBean.roleID!=6}">
				<input type="button" class="btn btn-primary pull-right"
					data-toggle="modal" data-target="#C-role-edit" value="Add Company">
					</c:if>
			</div>
			<div class="clearfix"></div>
			<div class="table-responsive">
			<table id="main-fourum" class="table table-striped table-bordered">
				<thead>
					<tr>
						<th>Sr. No.</th>
						<th>Company Name</th>
						<th>Description</th>
						<th>Cluster</th>
						<th>Cohort</th>
						<th>Action Edit</th>
						<th>Action Delete</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${companyList}" var="fm" varStatus="loop">
						<tr>
							<td>${loop.index+1}.</td>
							<td>${fm.companyName}</td>
							<td>${fm.companyDescription}</td>
							<td>${fm.cluster}</td>
							<td>${fm.cohort}</td>
							<td><input type="button" class="btn btn-primary"
								data-name="${fm.companyName}" data-description="${fm.companyDescription}"
								data-toggle="modal" data-id="${fm.companyId}" data-target="#C-role-edit"
								id="edit" value="Edit"> 
								 <!-- Modal --></td>
							<td>
							 
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
            <p>Are you sure want to delete company?</p>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal">No</button>
          <button type="button" onclick="deleteCompany(${fm.companyId});" class="btn btn-default" >Yes</button>        
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
			 <form method="post" id="deleteCompanyForm" >
          		<input type="hidden" name="deleteCompanyId" id="deleteCompanyId"/>
          	</form>
			<!-- / panel preview -->
			<div class="modal fade" id="C-role-edit" role="dialog">
				<div class="modal-dialog">

					<!-- Modal content-->
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal">&times;</button>
							<h4 class="modal-title">Add Company</h4>
						</div>
						<form id="submitCompanyMaster" action="getCompanyMaster" method="POST"
							name="Cluster">
							<input type="hidden"  name="companyId" id="companyId" value="0"/>
							<div class="modal-body">
								<div class="row">
									<label class="col-md-6">Company Name</label>
									<div class="col-md-6">
										<input class="form-control"  id="companyName" placeholder="Company Name"
											type="text" name="companyName" required>
									</div>
								</div>
								<p></p>
								<div class="row">
									<label class="col-md-6">Description</label>
									<div class="col-md-6">
										<input class="form-control" id="companyDescription" placeholder="Company Description"
											type="text" name="companyDescription" required>
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
    var companyId = $(this).data('id');
    $("#companyId").val( companyId );
    $("#companyName").val($(this).data('name'));
    $("#companyDescription").val($(this).data('description'));
  //  alert("cluster :: "+ $("#clusterId").val());
    $("#C-role-edit").show();
});

function deleteCompany(id){
	//deleteCluster
	$("#deleteCompanyId").val(id);
	document.getElementById("deleteCompanyForm").action = "deleteCompany";
	document.getElementById("deleteCompanyForm").method = "post";
	document.getElementById("deleteCompanyForm").submit();
}
</script>
</html>