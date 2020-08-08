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
					<li><a href="#">Potential Student</a></li>
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
				<input type="button" class="btn btn-primary pull-right"
					data-toggle="modal" data-target="#c-upload-file"
					value="Upload Excel"> <input type="button"
					class="btn btn-primary pull-right" data-toggle="modal" onclick="clearPotentialForm();"
					data-target="#C-role-edit" value="Add Potential Student">

			</div>
			<div class="clearfix"></div>
			<div class="table-responsive">
			<table id="main-fourum" class="table table-striped table-bordered">
				<thead>
					<tr>
						<th>Sr. No.</th>
						<th>Name</th>
						<th>Email ID</th>
						<th>Action Edit</th>
						<th>Action Delete</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${potentialStudentDetailsDetails}" var="fm"
						varStatus="loop">
						<tr>
							<td>${loop.index+1}.</td>
							<td>${fm.name}</td>
							<td>${fm.emailId}</td>
							<td><input type="button" class="btn btn-primary"
								data-name="${fm.name}" data-description="${fm.emailId}"
								data-toggle="modal" data-id="${fm.potentialStudentId}"
								data-target="#C-role-edit" id="edit" value="Edit"> <!-- Modal --></td>
							<td> <a href="#"  data-toggle="modal" data-target="#confirm-box1${loop.count}">Delete</a>
								 <div class="modal fade" id="confirm-box1${loop.count}" role="dialog">
      <div class="modal-dialog">    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Confirmation Box</h4>
        </div>
        <div class="modal-body">
            <p>Are you sure want to delete student?</p>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal">No</button>
          <button type="button" onclick="deletepotentialStudent(${fm.potentialStudentId});" class="btn btn-default" >Yes</button>        
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
			<form method="post" id="deletepotentialStudentIdForm">
				<input type="hidden" name="deletepotentialStudentId"
					id="deletepotentialStudentId" />
			</form>
			<!-- / panel preview -->
			<div class="modal fade" id="C-role-edit" role="dialog">
				<div class="modal-dialog">

					<!-- Modal content-->
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal">&times;</button>
							<h4 class="modal-title">Add Potential Student</h4>
						</div>
						<form id="saveForm" action="submitPotentialStudent" method="POST"
							name="PotentialStudent">
							<input type="hidden" name="potentialStudentId"
								id="potentialStudentId" value="0" />
							<div class="modal-body">
								<div class="row">
									<label class="col-md-6">Name</label>
									<div class="col-md-6">
										<input class="form-control" id="name"
											placeholder="Potential Student Name" type="text" name="name"
											required>
									</div>
								</div>
								<p></p>
								<div class="row">
									<label class="col-md-6">Email-Id</label>
									<div class="col-md-6">
										<input class="form-control" id="emailId"
											placeholder="me@example.com" type="text"
											name="emailId"  pattern="[a-zA-Z0-9.-_]{1,}@[a-zA-Z.-]{2,}[.]{1}[a-zA-Z]{2,}" required title="Your format should be me@example.com">
									</div>
								</div>
							</div>
							<div class="modal-footer">
								<button type="submit" class="btn btn-default" >Submit</button>
								<button type="button" class="btn btn-default"
									data-dismiss="modal">Cancel</button>
							</div>
						</form>
					</div>
				</div>
			</div>
			<div class="modal fade" id="c-upload-file" role="dialog">
				<div class="modal-dialog">
				<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal">&times;</button>
							<h4 class="modal-title">Upload Excel</h4>
						</div>
					<div class="row">
					<div class="space"></div>
					
					<div class="col-md-12"><form id="upladFile" name="PotentialStudent" action="uploadIIMAStudentExcel" method="POST" enctype="multipart/form-data">
						<div class="col-md-6"><input type="file" name="fileName" id="excelFile" onchange="checkFileTypeForUpload();"/></div>
						<div class="col-md-3"><input class="btn btn-primary" type="button" onclick="submitform();" value="Upload" name="uplaodFile" /></div>
					</form></div>
					<div class="space"></div>
					<span style="color:red"> &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;*** Note: Click the below thumbnail to view the format of the file to be uploaded.  </span>
					<br> 
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<a href="images/potentialUpload.PNG" target="_blank"> 
					<img   src="images/potentialUploadTumbnail.PNG"/> 
					</a>
					<div class="space"></div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default"
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
	//alert("hiii");
    var potentialStudentId = $(this).data('id');
    $("#potentialStudentId").val( potentialStudentId);
    $("#name").val($(this).data('name'));
    $("#emailId").val($(this).data('description'));
  //  alert("cluster :: "+ $("#clusterId").val());
    $("#C-role-edit").show();
});

function deletepotentialStudent(id){
	//deleteCluster
	$("#deletepotentialStudentId").val(id);
	document.getElementById("deletepotentialStudentIdForm").action = "deletePotentialStudent";
	document.getElementById("deletepotentialStudentIdForm").method = "post";
	document.getElementById("deletepotentialStudentIdForm").submit();
}


function clearPotentialForm(){

    var potentialname=document.getElementById("name");
	var email=document.getElementById("emailId");
	potentialname.value=''
	email.value=''
}

function checkFileTypeForUpload(){
	
	 var fileName=document.getElementById("excelFile").value;
	    var ext = $('#excelFile').val().split('.').pop().toLowerCase();
	    if($.inArray(ext, ['xlsx','xls']) == -1) {
	            alert("Only .xlsx .xls File are allowed to upload.")
	            document.getElementById("excelFile").value="";
	    }else{
	            
	    }
}
function submitform(){
	var id=document.getElementById("excelFile").value;
	if(id==''){
		alert("Please select file to upload.")
	}
	else{
		document.getElementById("upladFile").submit();
	}
	
	
}
</script>





</html>