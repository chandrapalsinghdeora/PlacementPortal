<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Indian Institute of Management Ahmedabad (IIMA) | Home - IIMA</title>
    <link rel="icon" href="images/favicon.ico" type="image/x-icon"/>

<script>

function AutoRefresh( t ) {
    setTimeout("location.reload(true);", t);
 }

function getUpdateKm(applicationid, vFlag)
{  
	if(vFlag.value==""){
		
	}else{
	var choosed = vFlag.value;
	if(choosed == 0)
   {
		var retVal = confirm("Do you want to unverify it ?");
		if( retVal == true ){
        	$("#kmId").val(applicationid);
        	$("#kmValue").val(vFlag.value);
        	$("#kmupdate").submit();
        	return true;
        }
		else{
			JavaScript:AutoRefresh(1000);
			return false;
			
		}
   }
	else if(choosed == 1)
	{
		var retVal = confirm("Do you want to verify it ?");
		if( retVal == true ){
        	$("#kmId").val(applicationid);
        	$("#kmValue").val(vFlag.value);
        	$("#kmupdate").submit();
        	return true;
        }
		else{
			JavaScript:AutoRefresh(1000);
			return false;
		}
	}
	}
/* 	$("#kmId").val(applicationid);
	$("#kmValue").val(vFlag.value);
	$("#kmupdate").submit();
 */
}
</script>

</head>
<body>

	<jsp:include page="commonJsp/Header.jsp" />
	<div class="clearfix"></div>
	<div class="breadcumb-wrapper">
		<div class="clearfix">
			<div class="pull-left">
				<ul class="list-inline link-list">
				<c:if test="${sessionBean.roleID==6}">
					<li><a href="forumHomePage">Home</a></li>
				</c:if>
				<%-- <c:if test="${sessionBean.roleID==9}">
					<li>Home</li>
				</c:if> --%>
					<li><a href="#">Firm Verification</a></li>
				</ul>
			</div>

		</div>
	</div>
	<div class="clearfix"></div>

	<div class="container">
		<div class="row">
			<div class="space"></div>
			<div class="clearfix"></div>
				<div class="table-responsive">
			<table id="main-fourum" class="table table-striped table-bordered">
				<thead>
					<tr>

						<th>Sr. No.</th>
						<th>Firm</th>
						<!-- <th>Role</th> -->
						<th>RM Name</th>
						<th>Share RM</th>
						<th>Cluster</th>
						<th>Cohort</th>
						<th>JD Link</th>
						<th>Verification</th>
						<th>Status</th>
						<th>Edit</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${km}" var="km" varStatus="loop">
						<tr>

							<td>${loop.index+1}.</td>

							<td>${km.companyName}</td>

							<%-- <td>${km.designationName}</td> --%>

							<td>${km.rmName}</td>

							<td>${km.share}</td>
							<td>${km.clusterName}</td>

							<td>${km.cohortName}</td>
							<td>
								<c:choose>
									<c:when test="${km.jobDescPath eq null}">
										NA
									</c:when>
									<c:otherwise>
										<form method="post" action="downloadJDFile" >
								       		<input type="hidden" value="${km.jobDescPath}" name="jdlink" id="jdlink"/>
								       		<button type="submit" title="Download Job Description File" class="btn btn-primary" >JD File</button>
								       	</form>
										
									</c:otherwise>
								</c:choose>
									
							</td>
							<td><c:choose>
									<c:when test="${km.applicationStatus == 'Pending'}">
										<select onchange="getUpdateKm( ${km.applicationId}, this )">
											<option value="">Select One</option>
											<c:choose>
												<c:when test="${km.verification == 'true'}">
													<option value="1" selected="selected">Yes</option>
													<option value="0">No</option>
												</c:when>
												<c:when test="${km.verification == 'false'}">
													<option value="1">yes</option>
													<option value="0" selected="selected">No</option>
												</c:when>
												<c:otherwise>
													<option value="1">Yes</option>
													<option value="0">No</option>
												</c:otherwise>
											</c:choose>
										</select>
									</c:when>
									<c:when test="${km.applicationStatus == 'Close'}">
										<select disabled>
											<option value="">Select One</option>
											<c:choose>
												<c:when test="${km.verification == 'true'}">
													<option value="1" selected="selected">Yes</option>
													<option value="0">No</option>
												</c:when>
												<c:when test="${km.verification == 'false'}">
													<option value="1">yes</option>
													<option value="0" selected="selected">No</option>
												</c:when>
												<c:otherwise>
													<option value="1">Yes</option>
													<option value="0">No</option>
												</c:otherwise>
											</c:choose>
										</select>
									</c:when>
									<c:when test="${km.applicationStatus == 'Open'}">

										<c:choose>
											<c:when test="${km.verification == 'true'}">
												<select disabled>
													<option value="">Select One</option>
													<option value="1" selected="selected">Yes</option>
													<option value="0">No</option>
												</select>
											</c:when>
											<c:when test="${km.verification == 'false'}">
												<select onchange="getUpdateKm(${km.applicationId}, this)">
													<option value="">Select One</option>
													<option value="1">yes</option>
													<option value="0" selected="selected">No</option>
												</select>
											</c:when>
											<c:otherwise>
												<select>
													<option value="">Select One</option>
													<option value="1">Yes</option>
													<option value="0">No</option>
												</select>
											</c:otherwise>
										</c:choose>
										</select>
									</c:when>
								</c:choose></td>

							<td>${km.applicationStatus}</td>
							<td><input type="button" class="btn btn-primary"
								data-toggle="modal" data-id="${km.applicationId}"
								data-cluster="${km.clusterName}" data-cohort="${km.cohortName}"
								data-target="#C-role-edit" id="edit" value="Edit"></td>

						</tr>

					</c:forEach>
				</tbody>
			</table>
			</div>
			<form id="kmupdate" method="post" action="updateKm">
				<input type="hidden" value="" name="kmId" id="kmId" /> <input
					type="hidden" value="" name="kmValue" id="kmValue" />
			</form>

			<!-- / panel preview -->

		</div>
	</div>
	<div class="space"></div>
	<div class="modal fade" id="C-role-edit" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Edit Role</h4>
				</div>
				<form id="editFirmVerfication" action="editFirmVerfication"
					method="POST" name="kmVerificationForm">

					<input type="hidden" name="applicationId" id="applicationId"
						value="0" />

					<div class="modal-body">

						<div class="row">
							<label class="col-md-4">Cluster</label>
							<div class="col-md-8">
								<!-- <input class="form-control"  id="designationname" placeholder="Designation Name"
											type="text" name="designationname" required> -->
								<select class="form-control" id="clusterId" name="clusterName"
									onchange="getCohortNameByCName(this);">
									<c:forEach items="${cluster}" var="cluster" varStatus="loop">
										<option value="${cluster.clusterName}">${cluster.clusterName}</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<p></p>
						<div class="row">
							<label class="col-md-4">Cohort</label>
							<div class="col-md-8">
								<select class="form-control" id="cohortId" name="cohortId">

								</select>
							</div>
						</div>
						<input type="hidden" name="compnayRolePkId" id="companyRolePkId" />
					</div>
					<div class="modal-footer">
						<button type="submit" class="btn btn-default">Submit</button>
						<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	<jsp:include page="commonJsp/Footer.jsp" />


	<script>
	$(document).on("click", "#edit", function () {
		//alert("hiii");
	    var applicationId = $(this).data('id');
	    $("#applicationId").val( applicationId );
	    $("#clusterId").val( $(this).data('cluster') );
	   	var  cohort = $(this).data('cohort') ;
	   	var xhttp = new XMLHttpRequest();
		xhttp.onreadystatechange = function() {
			if (this.readyState == 4 && this.status == 200) {
			var options="";
				var obj = JSON.parse(this.responseText);
				for ( var x in obj) {
				//	alert("name "+obj[x].cohortName+" id :: "+obj[x].cohortId);
					if(cohort==obj[x].cohortName)
						options+='<option selected="selected" value='+obj[x].cohortId+'>'+obj[x].cohortName+'</option>';
					else
						options+='<option value='+obj[x].cohortId+'>'+obj[x].cohortName+'</option>';
				}
				document.getElementById("cohortId").innerHTML=options;
			}
			
		};
		var action = "getCohortByClusterName?cname=" + $(this).data('cluster');
		xhttp.open("GET", action, true);
		xhttp.send(); 
	  //  alert("values::"+$(this).data('cohort')+ "  ::  "+ $(this).data('cluster') );
	    $("#C-role-edit").show();
	});

	
	function getCohortNameByCName(cname) {
		document.getElementById("cohortId").innerHTML="";
		var clusterName=cname.value;
		var xhttp = new XMLHttpRequest();
		xhttp.onreadystatechange = function() {
			if (this.readyState == 4 && this.status == 200) {
			var options="";
				var obj = JSON.parse(this.responseText);
				for ( var x in obj) {
					//alert("name "+obj[x].cohortName+" id :: "+obj[x].cohortId);
					var name=obj[x].cohortName;
					var Id=obj[x].cohortId;
					options+='<option value='+Id+'>'+name+'</option>';
				}
				document.getElementById("cohortId").innerHTML=options;
			}
			
		};
		var action = "getCohortByClusterName?cname=" + clusterName;
		xhttp.open("GET", action, true);
		xhttp.send(); 
	}
</script>


</body>
</html>