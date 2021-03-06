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
<!-- Bootstrap -->
<link href="css/bootstrap.css" rel="stylesheet">

<!-- Custom
<link href="css/custom.css" rel="stylesheet"> -->
<link rel="stylesheet" href="css/font-awesome.css">

<!-- CSS STYLE-->
<link rel="stylesheet" type="text/css" href="css/style.css" media="screen" />
<!-- <link rel="stylesheet" type="text/css" href="css/style.css" media="screen" />
<link rel="stylesheet" type="text/css" href="assets/css/ace.min.css" media="screen" />
<link rel="stylesheet" type="text/css" href="css/dataTables.bootstrap.min.css" media="screen" /> -->

<link href="https://cdn.datatables.net/fixedcolumns/3.2.3/css/fixedColumns.dataTables.min.css" rel="stylesheet">




<style>
 	.DTFC_LeftBodyLiner table.table.table-bordered.stripe.row-border.order-column.dataTable.no-footer.DTFC_Cloned {
   
   margin-top: 0px !important;
    /*  margin-bottom: 0px !important; */
   
}
	 #example th, #example td { white-space: nowrap; }
    #example div.dataTables_wrapper {
        width: 800px;
        margin: 0 auto;
    }
    
    table.table.table-bordered.stripe.row-border.order-column.dataTable.no-footer.DTFC_Cloned {
     margin-bottom: 0 !important;
    border-top: none; 
}
   
   table.table.table-bordered.stripe.row-border.order-column.dataTable.no-footer.DTFC_Cloned tr td:first-child,
table.table  tr td:first-child { text-align:center;}
   .dataTables_wrapper .dataTables_scroll div.dataTables_scrollBody {
    -moz-overflow-scrolling: touch;
    
}


.DTFC_LeftBodyWrapper {
    background-color: #fff;
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
				<li><a href="forumHomePage">Home</a></li>
				<li><a href="#">Assign Role</a></li>
			</ul>
		</div>

	</div>
</div>
<div class="clearfix"></div>

<div class="container">
	<div class="row">


		<!-- panel preview -->
		<div class="col-md-12 btn-container"></div>
		<div class="clearfix"></div>
		<form id="saveAssignFormId" action="saveAssignedRoles" method="post" >
			<input type="hidden" name="mentorId" id="mentorId" value="0">
			<input type="hidden" name="rmId" id="rmId" value="0">
			<input type="hidden" name="schedulingAdminId" id="schedulingAdminId" value="0">
			<input type="hidden" name="negoId" id="negoId" value="0">
			<input type="hidden" name="offerProcessorId" id="offerProcessorId" value="0">
			<input type="hidden" name="sideTrackerId" id="sideTrackerId" value="0">
			<input type="hidden" name="superRMOnTheDayId" id="superRMOnTheDayId" value="0">
			<input type="hidden" name="superRMThroughoutTheYearId" id="superRMThroughoutTheYearId" value="0">
			<input type="hidden" name="satcomId" id="satcomId" value="0">
			<input type="hidden" name="printingTeamId" id="printingTeamId" value="0">
			<input type="hidden" name="startoAdminId" id="startoAdminId" value="0">
			<input type="hidden" name="coordinator" id="coordinatorId" value="0">
			<input type="hidden" name="scheduler" id="schedulerId" value="0">
			<input type="hidden" name="studentId" value="0" id="studentId"/>
		</form>	
		
		<div>
		
		<table id="example"  class="table table-bordered stripe row-border order-column" cellspacing="0" width="100%">
			<thead>
				<tr>
					<th>S. No.</th>
					<th>Student Name</th>
					<th>RM</th>
					<th>Mentor</th>	
					<th>Strato </th>
					<th>Printing Team</th>
					<th>SATCOM</th>
					<th>Scheduling</th>						
					<th>Nego</th>	
					<th>Side Tracker</th>
					<th>Offer Processor</th>	
					<th>Super RM On day</th>	
					<th>Super RM throughout year</th>
					<th>Coordinator</th>	
					<th>Scheduler</th>		
					<th></th>				
				</tr>
			</thead>
			<tbody>
			<c:forEach var="role" items="${roles}" varStatus="i">
				<tr>
						<td>${i.count}</td>
						<td>${role.studentName}</td>
						<td align="center"> 
						<c:choose>
						   <c:when test="${role.rmId == 4 and role.rmActive}">
							<input type="checkbox" value="4" checked="checked" id="checkrmId${role.studentId}">
							</c:when>							
							<c:otherwise>
								<input type="checkbox" value="0" id="checkrmId${role.studentId}">
							</c:otherwise> 
						</c:choose>
						</td>
						<td align="center"> 
							<c:choose>
							<c:when test="${role.mentorId == 3 and role.mentorActive}">
								<input type="checkbox" value="3" checked="checked" id="checkmentorId${role.studentId}">
							</c:when>						
							<c:otherwise>
								<input type="checkbox" value="0" id="checkmentorId${role.studentId}">
							</c:otherwise> 
						    </c:choose>
						</td>
						<td align="center"> 
							<c:choose>
							<c:when test="${role.startoAdminId == 9 and role.startoAdminActive}">
								<input type="checkbox" value="9" checked="checked" id="checkstartoAdminId${role.studentId}">
							</c:when>						
							<c:otherwise>
								<input type="checkbox" value="0" id="checkstartoAdminId${role.studentId}">
							</c:otherwise> 
						    </c:choose>
						</td>
						<td align="center"> 
							<c:choose>
							<c:when test="${role.printingTeamId == 19 and role.printingTeamActive}">
								<input type="checkbox" value="19" checked="checked" id="checkprintingTeamId${role.studentId}">
							</c:when>						
							<c:otherwise>
								<input type="checkbox" value="0" id="checkprintingTeamId${role.studentId}">
							</c:otherwise> 
						    </c:choose>
						</td>
						<td align="center"> 
							<c:choose>
							<c:when test="${role.satcomId == 20 and role.satcomActive}">
								<input type="checkbox" value="20" checked="checked" id="checksatcomId${role.studentId}">
							</c:when>						
							<c:otherwise>
								<input type="checkbox" value="0" id="checksatcomId${role.studentId}">
							</c:otherwise> 
						    </c:choose>
						</td>
						
						<td align="center"> 
						   <c:choose>
							<c:when test="${role.schedulingAdminId == 21 and role.schedulingAdminActive}">
								<input type="checkbox" value="21" checked="checked" id="checkschedulingAdminId${role.studentId}">
							</c:when>						
							<c:otherwise>
								<input type="checkbox" value="0" id="checkschedulingAdminId${role.studentId}">
							</c:otherwise> 
						    </c:choose>
						</td>
						<td align="center"> 
						<c:choose>
							<c:when test="${role.negoId == 48 and role.negoActive}">
								<input type="checkbox" value="48" checked="checked" id="checknegoId${role.studentId}">
							</c:when>						
							<c:otherwise>
								<input type="checkbox" value="0" id="checknegoId${role.studentId}">
							</c:otherwise> 
						    </c:choose>
						</td>
						<td align="center"> 
						<c:choose>
							<c:when test="${role.sideTrackerId == 17 and role.sideTrackerActive}">
								<input type="checkbox" value="17" checked="checked" id="checksideTrackerId${role.studentId}">
							</c:when>						
							<c:otherwise>
								<input type="checkbox" value="0" id="checksideTrackerId${role.studentId}">
							</c:otherwise> 
						    </c:choose>
						</td>
						<td align="center"> 
						<c:choose>
							<c:when test="${role.offerProcessorId == 8 and role.offerProcessorActive}">
								<input type="checkbox" value="8" checked="checked" id="checkofferProcessorId${role.studentId}">
							</c:when>						
							<c:otherwise>
								<input type="checkbox" value="0" id="checkofferProcessorId${role.studentId}">
							</c:otherwise> 
						    </c:choose>
						</td>
						<td align="center"> 
						<c:choose>
							<c:when test="${role.superRMOnTheDayId == 49 and role.superRMOnTheDayActive}">
								<input type="checkbox" value="49" checked="checked" id="checksuperRMOnTheDayId${role.studentId}">
							</c:when>						
							<c:otherwise>
								<input type="checkbox" value="0" id="checksuperRMOnTheDayId${role.studentId}">
							</c:otherwise> 
						    </c:choose>
						</td>
						<td align="center"> 
						<c:choose>
							<c:when test="${role.superRMThroughoutTheYearId == 50 and role.superRMThroughoutTheYearActive}">
								<input type="checkbox" value="50" checked="checked" id="checksuperRMThroughoutTheYearId${role.studentId}">
							</c:when>						
							<c:otherwise>
								<input type="checkbox" value="0" id="checksuperRMThroughoutTheYearId${role.studentId}">
							</c:otherwise> 
						    </c:choose>
						</td>
						
						<td class="text-center">
					<c:choose>
						   <c:when test="${role.coordinatorId==55 and role.coordinatorActive}">
							<input type="checkbox" value="55" checked="checked" id="checkCordinatorId${role.studentId}" disabled>
							</c:when>							
							<c:otherwise>
								<input type="checkbox" value="0" id="checkCordinatorId${role.studentId}" disabled>
							</c:otherwise> 
					</c:choose>
					</td>
					<td class="text-center">
					<c:choose>
						   <c:when test="${role.schedulerId == 56 and role.schedulerActive}">
							<input type="checkbox" value="56" checked="checked" id="checkSchedulerId${role.studentId}" disabled>
							</c:when>							
							<c:otherwise>
								<input type="checkbox" value="0" id="checkSchedulerId${role.studentId}" disabled>
							</c:otherwise> 
					</c:choose>
					</td>
						
						<td align="center"> 
						<c:choose>
						<c:when test="${role.mentorId==3 || role.rmId==4 || role.startoAdminId==9 || role.printingTeamId==19 || role.satcomId==20 || role.schedulingAdminId==21 || role.negoId==48 || role.superRMOnTheDayId==49 || role.superRMThroughoutTheYearId==50}">
							<input type="button"  value="Update Role" class="btn btn-primary" onclick="saveAssignRoles('${role.studentId}','true');" />
						</c:when>
						
						<%-- <c:when test="${role.superRMThroughoutTheYearActive eq false and role.rmActive eq false}">
						   <input type="button"  value="Assign Role" class="btn btn-primary" onclick="saveAssignRoles('${role.studentId}','');" />
						</c:when> --%>
						
						
							<c:otherwise>
								<input type="button"  value="Assign Role" class="btn btn-primary" onclick="saveAssignRoles('${role.studentId}','');" />
							</c:otherwise> 
						</c:choose>
						</td>
						
				</tr>
			</c:forEach>
			<!-- <tr><td align="center">
			 <div class="col-md-12 text-center">
		      <input type="submit"  value="Assign Role" class="btn btn-primary" />
		      </div>
		  
		  </td>	</tr> -->		
			</tbody>
				
		</table>
		 
		</div>
	</div>


	<!-- / panel preview -->




</div>


<div class="space"></div>


<jsp:include page="commonJsp/Footer.jsp" />

<!-- //code.jquery.com/jquery-1.12.4.js
https://cdn.datatables.net/1.10.16/js/jquery.dataTables.min.js
https://cdn.datatables.net/fixedcolumns/3.2.3/js/dataTables.fixedColumns.min.js -->

<script src="https://cdn.datatables.net/fixedcolumns/3.2.3/js/dataTables.fixedColumns.min.js"></script>	

<script>

		$(document).ready(function() {
        var table = $('#example').DataTable( {
        scrollY:        "383px",
        scrollX:        true,
        scrollCollapse: true,
        paging:         false,
        fixedColumns:   {
            leftColumns: 2,
           
        }
    } );
        
       
        
        
        
} ); 
		
		
</script>


<script>
   function saveAssignRoles(studentId,flag){
	 //  alert("studentId -"+studentId);
	   $("#studentId").val(studentId);
	   
	   if ($("#checkrmId"+studentId).is(':checked')){
		   $("#rmId").val(4);
	   }else if(flag=='true'){
		   $("#rmId").val(1); 
	   }
	   if( $("#checkmentorId"+studentId).prop('checked')){
		   $("#mentorId").val(3);
	   }else if(flag=='true'){
		   $("#mentorId").val(1); 
	   }
	   if( $("#checkstartoAdminId"+studentId).prop('checked')){
		   $("#startoAdminId").val(9);
	   }else if(flag=='true'){
		   $("#startoAdminId").val(1); 
	   }
	   if( $("#checkprintingTeamId"+studentId).prop('checked')){
		   $("#printingTeamId").val(19);
	   }else if(flag=='true'){
		   $("#printingTeamId").val(1); 
	   }
	   if( $("#checksatcomId"+studentId).is(':checked')){
		   $("#satcomId").val(20);
	   }else if(flag=='true'){
		   $("#satcomId").val(1); 
	   }
	   if( $("#checkschedulingAdminId"+studentId).prop('checked')){
		   $("#schedulingAdminId").val(21);
	   }else if(flag=='true'){
		   $("#schedulingAdminId").val(1); 
	   }
	   if( $("#checknegoId"+studentId).prop('checked')){
		   $("#negoId").val(48);
	   }else if(flag=='true'){
		   $("#negoId").val(1); 
	   }
	   if( $("#checksideTrackerId"+studentId).prop('checked')){
		   $("#sideTrackerId").val(17);
	   }else if(flag=='true'){
		   $("#sideTrackerId").val(1); 
	   }
	   if( $("#checkofferProcessorId"+studentId).prop('checked')){
		   $("#offerProcessorId").val(8);
	   }else if(flag=='true'){
		   $("#offerProcessorId").val(1); 
	   }
	   if( $("#checksuperRMOnTheDayId"+studentId).prop('checked')){
		   $("#superRMOnTheDayId").val(49);
	   }else if(flag=='true'){
		   $("#superRMOnTheDayId").val(1); 
	   }
	   if( $("#checksuperRMThroughoutTheYearId"+studentId).prop('checked')){
		   $("#superRMThroughoutTheYearId").val(50);
	   }else if(flag=='true'){
		   $("#superRMThroughoutTheYearId").val(1); 
	   }
	   
	   
	   if( $("#checkCordinatorId"+studentId).prop('checked')){
		   $("#coordinatorId").val(55);
	   }else if(flag=='true'){
		   $("#coordinatorId").val(1); 
	   }
	   if( $("#checkSchedulerId"+studentId).prop('checked')){
		   $("#schedulerId").val(56);
	   }else if(flag=='true'){
		   $("#schedulerId").val(1); 
	   }
	   
	   
	   if($("#studentId").val()!=0 && ( $("#rmId").val()!=0 ||  $("#mentorId").val()!=0 || $("#startoAdminId").val()!=0 || $("#printingTeamId").val()!=0 || $("#satcomId").val()!=0 || $("#schedulingAdminId").val()!=0 || $("#negoId").val()!=0  || $("#sideTrackerId").val()!=0|| $("#superRMOnTheDayId").val()!=0 || $("#superRMThroughoutTheYearId").val()!=0)){
	 /*    document.getElementById("saveAssignFormId").action = "saveAssignedRoles"; 
	   document.getElementById("saveAssignFormId").method = "post";
	   document.getElementById("saveAssignFormId").submit();   */
	  
	   var queryParam="saveAssignedRoles?studentId="+$("#studentId").val()+"&rmId="+$("#rmId").val()+"&mentorId="+$("#mentorId").val()+"&startoAdminId="+$("#startoAdminId").val()+"&printingTeamId="+$("#printingTeamId").val()
			   +"&satcomId="+$("#satcomId").val()+"&schedulingAdminId="+$("#schedulingAdminId").val()+"&negoId="+$("#negoId").val()+"&sideTrackerId="+$("#sideTrackerId").val()+"&offerProcessorId="+$("#offerProcessorId").val()
			   +"&superRMOnTheDayId="+$("#superRMOnTheDayId").val()+"&superRMThroughoutTheYearId="+$("#superRMThroughoutTheYearId").val()+"&coordinatorId="+$("#coordinatorId").val()+"&schedulerId="+$("#schedulerId").val();
	   
		   var xhttp = new XMLHttpRequest();
			xhttp.onreadystatechange = function() {
				
				if (this.readyState == 4 && this.status == 200) {
					//alert(this.responseText);
					
				}
			};
		/* 	var action = "saveAssignedRoles?queryParam=" + queryParam; */
			xhttp.open("POST", queryParam, true);
			xhttp.send();
	   
	   
	   
	   
	   
	   }
	   alert("Role Successfully Updated!!");	    
   }
</script>
</body>
</html>