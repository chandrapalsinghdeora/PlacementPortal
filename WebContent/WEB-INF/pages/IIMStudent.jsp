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
					<li><a href="#">IIM Student</a></li>
					<!--  <li>Question</li> -->
				</ul>
			</div>

		</div>
	</div>
	<div class="container">
		<div class="row">
			<!-- panel preview -->
			<div class="space"></div>
			<div id="errmsg" style="display:none;" class="alert alert-danger"><strong>Error: Upload Excel file contains duplicate username or email <br> <b>total ${errorcount} conflicts count</b></strong></div>
			<div class="container-fluid btn-container">
				<p style="color:red" id="errmsg"></p>
				<input type="button" class="btn btn-primary pull-right"
					data-toggle="modal" data-target="#c-upload-file"
					value="Upload Excel">
					 <input type="button" class="btn btn-primary pull-right" id="deleteStudents"value="Delete Student">
				<input type="button" class="btn btn-primary pull-right" onclick="clearAddForm();"
					data-toggle="modal" data-target="#C-role-edit" value="Add IIM Student">
                
			</div>
			<div class="clearfix"></div>
			<div class="table-responsive">
			
			<table id="main-fourum" class="table table-striped table-bordered">
				<thead>
					<tr>
						   <th>S.No</th>
		                   <th>Email Id</th>
		                   <th>Prefix</th>
		                   <th>WildCard</th>
		                   <th>GroupId</th>
		                   <th>Username</th>
						   <th>Action</th>
						   <th>Delete  &nbsp;&nbsp;<input type="checkbox" id="deletebtn"></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${IIMStudent}" var="iim" varStatus="loop">
						<tr>
							<td>${loop.index+1}.</td>
							<td>${iim.emailid}</td>
							<td>${iim.prefix}</td>
							<td><c:if test="${iim.wildCard}">WildCard</c:if> </td>
							<td>${iim.groupid}</td>
							<td>${iim.username}</td>
							<td><input type="button" class="btn btn-primary"
								data-name="${iim.prefix}" data-description="${iim.emailid}"
								data-toggle="modal" data-id="${iim.studentId}##${iim.wildCard}" data-user="${iim.username}" data-groupid="${iim.groupid}" data-target="#C-role-edit"
								id="edit" value="Edit"> 
								 <!-- Modal --></td>
							<td align="center">
							<input type="checkbox"
							name="deleted" value="${iim.studentId}" 
													class="checkbox deleteList">
							</td>
	
           <div class="modal fade" id="delete-students" role="dialog">
								<div class="modal-dialog">

									<div class="modal-content">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal">&times;</button>
											<h4 class="modal-title text-center">Confirmation Box</h4>
										</div>
										<div class="modal-body">

											<div class="row">
												<div class="col-md-12 text-center">
													<label>Are you sure want to delete student?</label>
												</div>
											</div>											
										</div>
										<div class="modal-footer text-center">
										<button type="button" class="btn btn-primary"
												data-dismiss="modal">No</button>
											<button type="button" class="btn btn-primary"
												onclick="deleteStudent();">Yes</button>
											
										</div>
									</div>
							
							
							
							
							<!-- </td> -->
						</tr>
					</c:forEach>
				</tbody>
			</table>
			</div>
			 <form method="post" id="deleteStudentForm" >
			
          		<input type="hidden" name="deleteStudentId" id="deleteStudentId"/>
          	</form>
          	
          	
          	
          	
			<!-- / panel preview -->
			<div class="modal fade" id="C-role-edit" role="dialog">
				<div class="modal-dialog">

					<!-- Modal content-->
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal">&times;</button>
							<h4 class="modal-title">Add KmIIMStudent</h4>
						</div>
						<form id="submitKmIIMStudent" action="saveIIMStudent" method="POST"
							name="KmIIMStudent">
							<input type="hidden"  name="studentId" id="studentId" value="0"/>
							<div class="modal-body">
								
								<div class="row">
									<label class="col-md-4">Prefix Name*</label>
									<div class="col-md-8">
										<input class="form-control"  id="prefix" placeholder="Prefix"
											type="text" name="prefix" required>
									</div>
								</div>
								<p></p>
								<div class="row">
									<label class="col-md-4">Email Id*</label>
									<div class="col-md-8">
										<input class="form-control" id="emailid" placeholder="Email Id"
											type="email" name="emailid" required onchange="checkEmailExits();">
									</div>
								</div>
								<div class="row">
									<label class="col-md-4">GroupId*</label>
									<div class="col-md-8">
										<select class="form-control" id="groupid" placeholder="GroupId"
											 name="groupid" required>
										     <option value="0">Select One</option>	
										     <option value="1">pgp1</option>
										     <option value="2">pgp2</option> 
									    </select>
									</div>
								</div>
								<div class="row">
									<label class="col-md-4">Username*</label>
									<div class="col-md-8">
										<input class="form-control" id="username" placeholder="Username"
											type="text" name="username" required onkeyup="checkUserExits();">
									</div>
								</div>
								<div id="message"></div>
								<div class="row" id="showWild" style="display: none;margin-top: 10px;">
									<label class="col-md-4">Wild Card*</label>
									<div class="col-md-8">
										 <input type="checkbox" name="wildCard" id="wildCard" >
									</div>
								</div>
							</div>
							<div class="modal-footer">
								<button type="submit" id='btnsub' class="btn btn-default">Submit</button>
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
					
					<div class="col-md-12"><form id="upladFile" name="KmIIMStudent" action="uploadExcelStudent" method="POST" enctype="multipart/form-data">
						<div class="col-md-6"><input type="file" name="file" id="excelFile" onchange="checkFileTypeForUpload();" /></div>
						<div class="col-md-3"><input class="btn btn-primary" type="button" onclick="submitform();"  value="Upload" name="uplaodFile" /></div>
					</form>getCalander
					<form id="excelform" method="get">
					<input type="hidden" name="excel12" id="excel" value="2">
					</form>
					</div>
					
					<div class="space"></div>
					<!-- <a href="downloadDemoFile">Download demo file</a> -->
					<span style="color:red"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;*** Note: Click the below thumbnail to view the format of the file to be uploaded.  
                  <br/>
                   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                   
					
					<a href="images/IIMStudentUpload.PNG" target="_blank"> 
					<img   src="images/IIMStudentUploadThumbnail.PNG"/> 
					</a>
                   </span>
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

/* $(document).ready(function() { */
	var excelvalue=parseInt("${excelvalue}");
	//alert("errmsg" +excelvalue);
	if(excelvalue=="1"){
		$('#errmsg').show(); 
		 setTimeout(function(){
			$('#errmsg').hide();
			p();
		}, 5000);  
		
	}

	function p(){
		document.getElementById("excelform").action="getiimstudent"
		document.getElementById("excelform").method="get";
		document.getElementById("excelform").submit();
	}
	
</script>

<script type="text/javascript">

$(document).on("click", "#edit", function () {
    var Id = $(this).data('id');
    var arr = Id.split("##");
 //	alert("id : "+arr[0]+" wildcard : "+arr[1]);
    $("#studentId").val(arr[0] );
    $("#showWild").hide();
    $("#btnsub").prop('disabled', false);
    if(arr[1]=='true'){
    	$("#showWild").show();
    	$("#wildCard").prop( "checked", true ) ;
    	
    }
    $("#prefix").val($(this).data('name'));
    $("#emailid").val($(this).data('description'));
    $("#username").val($(this).data('user'));
    $("#groupid").val($(this).data('groupid'));
    $("#username").prop('disabled', false);
    $("#groupid").prop('disabled', false);
    $("#message").html('');
    $("#C-role-edit").show();
});

function deleteStudent(id){
	//deleteCluster
	$("#deleteStudentId").val(id);
	document.getElementById("deleteStudentForm").action = "deleteStudent";
	document.getElementById("deleteStudentForm").method = "post";
	document.getElementById("deleteStudentForm").submit();
}
function deleteStudent(){
	   
	   valArrDownload = [];
	    if ($(".deleteList").length > 0){
			if ($(".deleteList").is(":checked")) {
				$('.deleteList:checked').each(function(i) {
					valArrDownload[i] = $(this).val();
				});
				//pdfBind(valArrDownload);
		 	}else{
				alert("Please select at least one check box.");
				return false;
			} 
	    }
	    if (valArrDownload!=null && valArrDownload.length>0) {
 	 	$("#deleteStudentId").val(''); 
 	 	
	    	$("#deleteStudentId").val(valArrDownload.toString());
	    	document.getElementById("deleteStudentForm").action = "deleteStudent";
	   	  	document.getElementById("deleteStudentForm").method = "post";
	   	  	document.getElementById("deleteStudentForm").submit(); 
	    }else{
			alert("No data available.");
			return false;
		}
}
function clearAddForm(){
   $("#btnsub").prop('disabled', true);
   $("#username").prop('disabled', false);
   $("#groupid").prop('disabled', false);
   $("#message").html(''); 
   $("#prefix").val('');
   $("#emailid").val('');
   $("#username").val('');
   $("#groupid").val('');
   $("#studentId").val('0'); 

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

function checkEmailExits(){

	var testEmail = document.getElementById("emailid").value;
	var ErrorMessage= '';

 	<c:forEach items="${IIMStudent}" var="iim" varStatus="loop">
 	 
   		if(testEmail.toUpperCase() == "${iim.emailid}".toUpperCase())
 		ErrorMessage= '<div class="col-md-9" style="color: red;">Email already Exists. Please take another Email</div>';
    </c:forEach>
		   
    if(ErrorMessage == ''){
		$("#btnsub").prop('disabled', false);
		$("#groupid").prop('disabled', false);
		$("#username").prop('disabled', false);
	}else{
		$("#btnsub").prop('disabled', true);
		$("#groupid").prop('disabled', true);
		$("#username").prop('disabled', true);
	}
    $("#message").html(ErrorMessage);
 	 
}

function checkUserExits(){

	var testUsername = document.getElementById("username").value;
 	if(testUsername==''){
 		alert("Please enter username.")
 	}
    
    var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			this.responseText;
			var i;
			var obj = JSON.parse(this.responseText);
		//	alert(JSON.stringify(obj));
		//  alert("length : "+obj.length);

			var ErrorMessage= '';
			for (i = 0; i < obj.length; i++) {
				//alert(obj[i].userName)
				 if(testUsername.toUpperCase() == (obj[i].userName).toUpperCase()){
					 ErrorMessage= '<div class="col-md-9" style="color: red;">Username already Exists. Please take another username</div>';
	           	}
			}
			
			if(ErrorMessage == ''){
				$("#btnsub").prop('disabled', false);
			}else{
				$("#btnsub").prop('disabled', true);
			}
			$("#message").html(ErrorMessage);
		}
	};
	var action = "getUsernameList";
	xhttp.open("POST", action, true);
	xhttp.send();
}
$(function () {
    $("#deletebtn").click(function () {
        $('.deleteList').prop('checked', this.checked);
    });

    $(".deleteList").click(function () {
        if ($(".deleteList").length == $(".case:checked").length) {
            $("#deletebtn").prop("checked", "checked");
        } else {
            $("#deletebtn").removeProp("checked");
        }
    });
  
});

$(function() {
	$('#deleteStudents').click(function() {
    valArrDownload = [];
    if ($(".deleteList").length > 0){
		if ($(".deleteList").is(":checked")) {
			$('.deleteList:checked').each(function(i) {
				valArrDownload[i] = $(this).val();
			});
			
	 	}else{
			alert("Please select at least one check box.");
			return false;
		} 
    }
    if (valArrDownload!=null && valArrDownload.length>0) {
    	$("#delete-students").modal('show'); 
    }else{
		alert("No data available.");
		return false;
	}
	});
});


</script>


</html>