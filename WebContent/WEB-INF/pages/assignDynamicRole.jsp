<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ page isELIgnored="false" %>

<!DOCTYPE html>
<html lang="en">
    

<head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Indian Institute of Management Ahmedabad (IIMA) | Home - IIMA</title>
        <!-- Bootstrap -->
      
        <!-- CSS STYLE-->
        <link rel="stylesheet" type="text/css" href="css/style.css" media="screen" />
        <!-- SLIDER REVOLUTION 4.x CSS SETTINGS -->
        <link rel="stylesheet" type="text/css" href="rs-plugin/css/settings.css" media="screen" />
<style>
.content{ min-height:500px; width:100%; background-color:#fff !important; padding-top:50px;}
.button_width{width:200px;}
</style>
</head>
<body>
<jsp:include page="commonJsp/Header.jsp" />
   
<div class="clearfix"></div>
<div class="breadcumb-wrapper">
            <div class="clearfix">
                <div class="pull-left">
                    <ul class="list-inline link-list">
                        <li><a href="getScheduleAdminPage">Home</a></li>
                        
                        <li>Assign Dynamic Role</li>
                    </ul>
                </div>
             
            </div>
</div> 
        
<div class="clearfix"></div>
<center>  <h4><b>Assign Dynamic Role</b></h4></center>	

<form id="saveAssignFormId" action="saveAssignedRoles" method="post" >
            <input type="hidden" name="mentorId" id="mentorId" value="0">
			<input type="hidden" name="rmId" id="rmId" value="0">
			<input type="hidden" name="schedulingAdminId" id="schedulingAdminId" value="0">
			<input type="hidden" name="negoId" id="negoId" value="0">
			<input type="hidden" name="superRMOnTheDayId" id="superRMOnTheDayId" value="0">
			<input type="hidden" name="superRMThroughoutTheYearId" id="superRMThroughoutTheYearId" value="0">
			<input type="hidden" name="satcomId" id="satcomId" value="0">
			<input type="hidden" name="printingTeamId" id="printingTeamId" value="0">
			<input type="hidden" name="startoAdminId" id="startoAdminId" value="0">
			<input type="hidden" name="coordinator" id="coordinatorId" value="0">
			<input type="hidden" name="scheduler" id="schedulerId" value="0">
			<input type="hidden" name="studentId" value="0" id="studentId"/>
</form>	



<div class="container-fluid">
<div>
				
					<table class="table table-bordered table table-striped table-bordered">
					<thead>
					<tr>
					<th>Name</th>
					<th class="text-center">Coordinator</th>
					<th class="text-center">Scheduler</th>
					<th class="text-center"></th>
					</tr>
					</thead>
					<c:forEach items="${assignRole}" var="role">
					
					<tr>
					<td>${role.studentName}</td>
					<td class="text-center">
					<c:choose>
						   <c:when test="${role.coordinatorId==55 and role.coordinatorActive}">
							<input type="checkbox" value="55" checked="checked" id="checkCordinatorId${role.studentId}">
							</c:when>							
							<c:otherwise>
								<input type="checkbox" value="0" id="checkCordinatorId${role.studentId}">
							</c:otherwise> 
					</c:choose>
					</td>
					<td class="text-center">
					<c:choose>
						   <c:when test="${role.schedulerId == 56 and role.schedulerActive}">
							<input type="checkbox" value="56" checked="checked" id="checkSchedulerId${role.studentId}">
							</c:when>							
							<c:otherwise>
								<input type="checkbox" value="0" id="checkSchedulerId${role.studentId}">
							</c:otherwise> 
					</c:choose>
					</td>
					
					<td align="center"> 
						<c:choose>
						<c:when test="${role.coordinatorId==55 || role.schedulerId==56}">
							<input type="button"  value="Update Role" class="btn btn-primary" onclick="saveAssignRoles('${role.studentId}','true');" />
						</c:when>
						
						<c:otherwise>
								<input type="button"  value="Assign Role" class="btn btn-primary" onclick="saveAssignRoles('${role.studentId}','');" />
						</c:otherwise> 
						</c:choose>
						</td>
										
					</tr>
					</c:forEach>
					
					</table>
				
</div>
</div>

			
<jsp:include page="commonJsp/Footer.jsp" />


<script>
function saveAssignRoles(studentId,flag){
	  // alert("studentId -"+studentId);
	   $("#studentId").val(studentId);
	   	   
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
	   
	   
	   if($("#studentId").val()!=0 && ( $("#coordinatorId").val()!=0 || $("#schedulerId").val()!=0)){
			 /*    document.getElementById("saveAssignFormId").action = "saveAssignedRoles"; 
			   document.getElementById("saveAssignFormId").method = "post";
			   document.getElementById("saveAssignFormId").submit();   */
			  
			   var queryParam="saveAssignedRoles?studentId="+$("#studentId").val()+"&coordinatorId="+$("#coordinatorId").val()+"&schedulerId="+$("#schedulerId").val()
			   +"&rmId="+$("#rmId").val()+"&mentorId="+$("#mentorId").val()+"&startoAdminId="+$("#startoAdminId").val()+"&printingTeamId="+$("#printingTeamId").val()
			   +"&satcomId="+$("#satcomId").val()+"&schedulingAdminId="+$("#schedulingAdminId").val()+"&negoId="+$("#negoId").val()+"&superRMOnTheDayId="+$("#superRMOnTheDayId").val()
			   +"&superRMThroughoutTheYearId="+$("#superRMThroughoutTheYearId").val();
			   
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