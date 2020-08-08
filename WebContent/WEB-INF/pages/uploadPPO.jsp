
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page isELIgnored="false"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Indian Institute of Management Ahmedabad (IIMA) | Home -
	IIMA</title>
<!-- Bootstrap -->

<!-- CSS STYLE-->
<link rel="stylesheet" type="text/css" href="css/style.css"
	media="screen" />
<!-- SLIDER REVOLUTION 4.x CSS SETTINGS -->
<link rel="stylesheet" type="text/css" href="rs-plugin/css/settings.css"
	media="screen" />
<style>
.content {
	min-height: 500px;
	width: 100%;
	background-color: #fff !important;
	padding-top: 50px;
}

.button_width {
	width: 200px;
}

ul.status li.active .btn-primary {
	color: #fff;
	background-color: #63de63;
	border-color: #63de63;
}

li {
	list-style: none;
}
</style>
</head>
<body>
	<jsp:include page="commonJsp/Header.jsp" />

	<div class="clearfix"></div>
	<div class="breadcumb-wrapper">
		<div class="clearfix">
			<div class="pull-left">
				<ul class="list-inline link-list">
					<li><a href="#">Home</a></li>

					<li>Upload PPO</li>
				</ul>
			</div>

		</div>
	</div>
	<%--         
<div class="clearfix"></div>


<div class="container-fluid">
<div class="content">
  <center>  <h2><b>Nego</b></h2></center> 
</div>
</div> --%>

	<div class="space"></div>

	<div class="container">

		
			
			<div class="row">
					<div class="space"></div>
					
					<div class="col-md-12"><form id="upladFile" name="KmIIMStudent" action="uploadPPO1" method="POST" enctype="multipart/form-data">
						<div class="col-md-6"><input type="file" name="file" id="excelFile" onchange="checkFileTypeForUpload();" /></div>
						<div class="col-md-3"><input class="btn btn-primary" type="button" onclick="submitform();"  value="Upload" name="uplaodFile" /></div>
					</form>
					<form id="excelform" method="get">
					<input type="hidden" name="excel12" id="excel" value="2">
					</form>
					</div>
					
					<div class="space"></div>
					<!-- <a href="downloadDemoFile">Download demo file</a> -->
					<span style="color:red"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;*** Note: Click the below thumbnail to view the format of the file to be uploaded.  
                  <br/>
                   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                   
					
					<a href="images/Capture9.PNG" target="_blank"> 
					<img   src="images/IIMStudentUploadThumbnail.PNG"/> 
					</a>
 
                   </span>
                   <div class="space"></div>
					</div>
                   <div class="col-md-9" style="color: red;" id="csd"></div>
					
		</div>
		
		<jsp:include page="commonJsp/Footer.jsp" />

	
	<script type="text/javascript" src="js/buttons.flash.min.js"></script>
	<script type="text/javascript" src="js/jszip.min.js"></script>
	<script type="text/javascript" src="js/pdfmake.min.js"></script>
	<script type="text/javascript" src="js/buttons.html5.min.js"></script>
	<script type="text/javascript" src="js/buttons.print.min.js"></script>

		<c:set var="csd" value="${csd }"/>

			
<script type="text/javascript">


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

jQuery(document).ready(function() {
	var x="${csd}";
	document.getElementById('csd').innerHTML=x; 
    setTimeout(function() {
     document.getElementById('csd').innerHTML='';  
    }, 2000);
});
   

</script>
			

			

				

		
	

</body>

</html>