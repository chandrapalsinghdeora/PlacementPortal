<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Indian Institute of Management Ahmedabad (IIMA) | Home - IIMA</title>
    <link rel="icon" href="images/favicon.ico" type="image/x-icon"/>
<link rel="icon" href="images/favicon.ico" type="image/x-icon"/>
<!-- Bootstrap -->
<link href="css/bootstrap.css" rel="stylesheet">

<!-- Custom -->
<link href="css/custom.css" rel="stylesheet">
<link rel="stylesheet" href="css/font-awesome.css">
<link rel="stylesheet" type="text/css" href="css/pignose.calendar.css" />
<!-- CSS STYLE-->
<link rel="stylesheet" type="text/css" href="css/style.css" media="screen" />
<link rel="stylesheet" type="text/css" href="css/dataTables.bootstrap.min.css" media="screen" />
<style>
.mail-box .table-inbox .fa.fa-star.activstar { color: #f78a09; }

.inbox-table-head{ display:none;}



</style>
</head>
<body>
<!-- Header started -->
<jsp:include page="commonJsp/Header.jsp" />

<div class="clearfix"></div>
<div class="breadcumb-wrapper">
            <div class="clearfix">
                <div class="pull-left">
                    <ul class="list-inline link-list">
                        <li><a href="#">Home</a></li>
                        
                        <li>Application Matrix</li>
                    </ul>
                </div>
             
            </div>
        </div> 


<div class="container-fluid">
<div class="content">
		                 
<center><h2><b>Application Matrix</b></h2></center></br>
	
	<div class="container">
	
	<h4 class="h4_margin"><b>Offer Count : ${offercount} </b></h4>
	<h4 class="h4_margin"><b>Latest Offered By : ${companyName} </b></h4>

  <center class="table_head">
                
   <div class="col-md-9" style="color: red;" id="csd" ></div>
  
                                
<table id="table1" class="display table table-bordered" cellspacing="0" width="100%">
        <thead>
            <tr>
               
                <th>Company Name</th>
                 <th>Role Name</th>
                <th>Cluster</th>
                <th>Cohort</th>
                <th>Withdraw</th>
                
            </tr>
        </thead>
  
        <tbody>
	<c:forEach items="${applylist}" var="fm" varStatus="loop">      
            <tr>
              
                <td>${fm.companyName}</td>
                <td>${fm.companyRoleName}</td>
                <td>${fm.cluserName}</td>
                <td>${fm.cohortName}</td>
                <td>
               	<a href="#" type="button" data-toggle="modal"
										data-target="#withdraw${loop.index+1}">Withdraw</a>
				<div class="modal fade" id="withdraw${loop.index+1}"
						role="dialog">
						<div class="modal-dialog modal-sm company-model-withdraw">

							<!-- Modal content-->
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal">&times;</button>
									<h4 class="modal-title">Withdraw Permission</h4>
								</div>
								<form id="withdrawForm" action="withdrawFirm" method="post"
									name="Company" >
									<input name="applicationId" type="hidden"
										value="${fm.applicationId}">
									<div class="modal-body">
										Are You Sure You Want to Withdraw "${fm.applyID}"
										<div class="modal-footer">
											<input type="hidden" value="${fm.applyID}" name="applyID" />
											<button type="submit" class="btn btn-primary">Yes</button>
											<button type="button" class="btn btn-primary"
												data-dismiss="modal">No</button>
										</div>
									</div>
								</form>
							</div>
						</div>
					</div>     
										</td>
                
            </tr>
           </c:forEach>
			</table>

</center>


<c:set var="csd" value="${flagMessage}"/>	

	
</div>
			
</div>
</div>


<script src="js/jquery-1.12.4.js"></script>
<script src="js/jquery.dataTables.min.js"></script>	

<script type="text/javascript" src="js/buttons.flash.min.js"></script>
	<script type="text/javascript" src="js/jszip.min.js"></script>
	<script type="text/javascript" src="js/pdfmake.min.js"></script>
	<script type="text/javascript" src="js/buttons.html5.min.js"></script>
	<script type="text/javascript" src="js/buttons.print.min.js"></script>

		



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

	

} );


$("#withdraw").change(function(event){
    if (this.checked){
        
		if (confirm('Your offer has been withdraw')) {
                document.getElementById("withdraw").disabled = true;
        } else {
            $('#withdraw').attr('checked', false);
        }
		
    } 
});

$("#withdraw1").change(function(event){
    if (this.checked){
        
		if (confirm('Your offer has been withdraw')) {
                document.getElementById("withdraw1").disabled = true;
        } else {
            $('#withdraw1').attr('checked', false);
        }
		
    } 
});

$("#withdraw2").change(function(event){
   if (this.checked){
        
		if (confirm('Your offer has been withdraw')) {
                document.getElementById("withdraw2").disabled = true;
        } else {
            $('#withdraw2').attr('checked', false);
        }
		
    } 
});

$("#withdraw3").change(function(event){
    if (this.checked){
        
		if (confirm('Your offer has been withdraw')) {
                document.getElementById("withdraw3").disabled = true;
        } else {
            $('#withdraw3').attr('checked', false);
        }
		
    } 
});
jQuery(document).ready(function() {
	var x="${csd}";
	document.getElementById('csd').innerHTML=x; 
    setTimeout(function() {
     document.getElementById('csd').innerHTML='';  
    }, 2000);
});
   
</script>



<jsp:include page="commonJsp/Footer.jsp" />


</body>

</html>