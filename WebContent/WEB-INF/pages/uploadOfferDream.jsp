<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Indian Institute of Management Ahmedabad (IIMA) | Home - IIMA</title>
    <link rel="icon" href="images/favicon.ico" type="image/x-icon"/>
<!-- Bootstrap -->
<link href="css/bootstrap.css" rel="stylesheet">

<!-- Custom -->
<link href="css/custom.css" rel="stylesheet">
<link rel="stylesheet" href="css/font-awesome.css">

<!-- CSS STYLE-->
<link rel="stylesheet" type="text/css" href="css/style.css" media="screen" />
<link rel="stylesheet" type="text/css" href="css/dataTables.bootstrap.min.css" media="screen" />
<link rel="stylesheet" type="text/css" href="css/buttons.dataTables.min.css" media="screen" />
<link rel="stylesheet" type="text/css" href="css/jquery.dataTables.min.css" media="screen" />
<link rel="stylesheet" type="text/css" href="assets/css/bootstrap-datetimepicker.min.css" media="screen" />
<link rel="stylesheet" type="text/css" href="assets/css/bootstrap.min.css" media="screen" />

 <style>
.panel_allocation{ border:none !important; border-bottom: 1px solid #ddd !important;}
.panel_allocation li{ display:inline-table !important;}
.panel_allocation_txt p{ line-height:35px; margin-bottom:0px;}
</style>

</head>
<body>
<jsp:include page="commonJsp/Header.jsp" />

<div class="breadcumb-wrapper">
  <div class="clearfix">
    <div class="pull-left">
      <ul class="list-inline link-list">
         <li><a href="#">Home</a></li>
         <li>Side Tracker</li>
      </ul>
    </div>
  </div>
</div>
<!-- <div class="space"></div> -->
<div class="container-fluid">
<div class="content">
		                 

	
	<div class="container">
	<div class="row">
	<div class="col-sm-5 ">

  <center class="table_head">
                
  <h3>Offer Table</h3></br>
                                
<table id="table1" class="display table table-bordered" cellspacing="0" width="100%">
        <thead>
            <tr>
                <th></th>
				<th>Student</th>
                <th>Firm</th>
                <th>Role</th>
              
             
                
            </tr>
        </thead>
  
        <tbody>
            <tr>
                <td><input type="checkbox" name="" value=""></td>
				<th>Student1</th>
                <td>Firm 1</td>
                <td>Role 1</td>
                
               
                
            </tr>
            <tr>
                 <td><input type="checkbox" name="" value=""></td>
				 <th>Student 2</th>
                <td>Firm 1</td>
                <td>Role 1</td>
               
              
            </tr>
            <tr>
                  <td><input type="checkbox"  name="" value=""></td>
				  <th>Student 3</th>
                <td>Firm 1</td>
                <td>Role 1</td>
              
              
            </tr>
            <tr>
                  <td><input type="checkbox"  name="" value=""></td>
				  <th>Student 4</th>
                <td>Firm 1</td>
                <td>Role 1</td>
              
               
            </tr>
          
           
			</table>
			
<!-- <div class="form-group col-md-12 text-center" style="margin-top:10px;">  
               <input type="submit" value="Delete Offer" class="btn btn-primary" style="margin-right:20px;">
			    <input type="submit" value="Add Offer" class="btn btn-primary">
                    </div> -->
                   
 <div class="form-group col-md-12 text-center" style="margin-top:10px;">  
			    <input type="button" value="Upload Offer Excel" class="btn btn-primary" data-toggle="modal" data-target="#c-upload-file" onclick="csd();">
                    </div>
</center>

</div>
	<div class="col-sm-2">
     <center>
                        
     </center>
	</div>
	<div class="col-sm-5">
	<center class="table_head">
                       <h3 >Dream Table</h3></br>
<table id="table2" class="display table table-bordered" cellspacing="0" width="100%">
        <thead>
            <tr>
                <th></th>
				<th>Student</th>
                <th>Firm</th>
                <th>Role</th>
               
                
            </tr>
        </thead>
  
        <tbody>
            <tr>
                <td><input type="checkbox"  name="" value=""></td>
				<th>Student 1</th>
                <td>Firm 1</td>
                <td>Role 1</td>
               
               
                
            </tr>
            <tr>
                  <td><input type="checkbox"  name="" value=""></td>
				  <th>Student 2</th>
                <td>Firm 1</td>
                <td>Role 1</td>
                
            </tr>
            <tr>
                  <td><input type="checkbox"  name="" value=""></td>
				  <th>Student3</th>
                <td>Firm 1</td>
                <td>Role 1</td>
               
            </tr>
            <tr>
                  <td><input type="checkbox"  name="" value=""></td>
				  <th>qwe hu</th>
                <td>Firm 1</td>
                <td>asd</td>
                
            </tr>
          
           
			</table>
 
<!-- <div class="form-group col-md-12 text-center" style="margin-top:10px;">  
               <input type="submit" value="Delete Dream" class="btn btn-primary" style="margin-right:20px;">
			    <input type="submit" value="Add Dream" class="btn btn-primary">
                    </div> -->
 <div class="form-group col-md-12 text-center" style="margin-top:10px;">  
             
			    <input type="button" value="Upload Dream Excel" class="btn btn-primary" data-toggle="modal" data-target="#c-upload-file" onclick="csd2();">
                    </div>
 
 </center>
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
					
					<div class="col-md-12"><form id="upladFile"  action="offerexcel" method="POST" enctype="multipart/form-data">
					   <input type="hidden" name="differ" id="differ" value="0">
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
                   
					
					<a href="images/Capture8.PNG" target="_blank"> 
					<img src="images/IIMStudentUploadThumbnail.PNG"/> 
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

<script type="text/javascript" src="js/jquery.dataTables.min.js"></script> 
<script type="text/javascript" src="js/dataTables.buttons.min.js"></script> 
<script type="text/javascript" src="js/buttons.flash.min.js"></script> 
<script type="text/javascript" src="js/jszip.min.js"></script> 
<script type="text/javascript" src="js/pdfmake.min.js"></script> 
<script type="text/javascript" src="js/vfs_fonts.js"></script> 
<script type="text/javascript" src="js/buttons.html5.min.js"></script> 
<script type="text/javascript" src="js/buttons.print.min.js"></script> 
<!-- <script type="text/javascript" src="js/bootstrap.js"></script> --> 

<!--For datepicker --> 
<!--For Tiem picker only --> 
<script src="assets/js/bootstrap-datepicker.min.js"></script>
<script src="assets/js/bootstrap-timepicker.min.js"></script>
<script src="assets/js/moment.min.js"></script>
<script src="assets/js/bootstrap-datetimepicker.min.js"></script>

<!-- ace scripts -->

<script>


       
$(document).ready(function() {
    $('#table1').DataTable({
	"bFilter": true,
		 	 "bInfo": false,
		 	 "scrollY":        "200px",
		     "scrollCollapse": true,
		     "paging":         false,
				
		        dom: 'Bfrtip',
		        language: {
        searchPlaceholder: "Search"
    }

	});

	$('#table2').DataTable({
		"bFilter": true,
		 	 "bInfo": false,
		 	 "scrollY":        "200px",
		     "scrollCollapse": true,
		     "paging":         false,
				
		        dom: 'Bfrtip',
				        language: {
        searchPlaceholder: "Search"
    }	
		       
	
} );

} );

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

function csd()
{
	document.getElementById('differ').value=1;
	$('#c-upload-file').modal('show');
}

function csd2()
{
	document.getElementById('differ').value=2;
	$('#c-upload-file').modal('show');
}
</script>


</body>
</html>