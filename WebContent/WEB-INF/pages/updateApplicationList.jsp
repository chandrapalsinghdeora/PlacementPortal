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
<div class="container-fluid">
<div class="content">
		                 

	
	<div class="container">
	<div class="col-sm-3 "></div>
	<div class="col-sm-6 ">

  <center class="table_head">
                
  <h3>Update Application Lists</h3></br>
                                
<table id="table1" class="display table table-bordered" cellspacing="0" width="100%">
        <thead>
            <tr>
                <th></th>
				<th>Cluster</th>
                <th>Firm</th>
                <th>Role</th>
            </tr>
        </thead>
        <tbody>
        <c:forEach items="${getAllList}" var="shrt">
            <tr>
                <td><input type="checkbox" name="" value="${shrt.roleId}" class="checkbox updateList"></td>
				<th>${shrt.clusterName}</th>
                <td>${shrt.firmName}</td>
                <td>${shrt.roleName}</td>
          
            </tr>
      </c:forEach>           
			</table>
			
			

 <div class="form-group col-md-12 text-center" style="margin-top:10px;">  
             
			    <input type="submit" value="Upload All Lists" class="btn btn-primary" onclick="updateList();">
                    </div>
</center>

</div>
<form id="sendroleid">
<input type="hidden" name="allroleid" id="allroleid">
</form>
	
<div class="col-sm-3 "></div>
	
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
<script src="js/jquery-1.12.4.js"></script>
<script src="js/jquery.dataTables.min.js"></script>	
<script>
       
$(document).ready(function() {
    $('#table1').DataTable({
	"bFilter": true,
		 	 "bInfo": false,
		 	 "scrollY":"200px",
		     "scrollCollapse": true,
		     "paging":false,
				
		        dom: 'Bfrtip',
		        language: {
        searchPlaceholder: "Search"
    }

	});

	$('#table2').DataTable({
		"bFilter": true,
		 	 "bInfo":false,
		 	 "scrollY":"200px",
		     "scrollCollapse":true,
		     "paging":false,
				
		        dom: 'Bfrtip',
				        language: {
        searchPlaceholder: "Search"
    }	
		       
	
} );

} );

function updateList()
{
	 valArrDownload = [];
	    if ($(".updateList").length > 0){
			if ($(".updateList").is(":checked")) {
				$('.updateList:checked').each(function(i) {
					valArrDownload[i] = $(this).val();
				});
				$("#allroleid").val(valArrDownload.toString());
				updateList1();
			}
	    }
	    else
	    	{
	    	alert("Please select at least one check box.");
			return false;
	    	}
}

function updateList1()
{
	document.getElementById('sendroleid').action="updatelistsend";
	document.getElementById('sendroleid').method="post";
	document.getElementById('sendroleid').submit();
	
}
</script>

</body>


</html>