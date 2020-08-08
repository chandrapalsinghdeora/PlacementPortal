<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="org.hashids.Hashids"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Indian Institute of Management Ahmedabad (IIMA) | Home -
	IIMA</title>
<link rel="icon" href="images/favicon.ico" type="image/x-icon" />
<script type="text/javascript">
function getManageID(appid){
	//alert(appid);
	document.getElementById("viewFourm"+appid).action = "getAllCloseStatusDetails";	
	document.getElementById("viewFourm"+appid).method = "get";
	document.getElementById("viewFourm"+appid).submit();
}

function getManageGDID(appid){
	//alert(appid);
	document.getElementById("viewFourm"+appid).action = "getAllCloseStatusDetails1";	
	document.getElementById("viewFourm"+appid).method = "get";
	document.getElementById("viewFourm"+appid).submit();
}

function getScheduleID(appid){
	//alert(appid);
	document.getElementById("viewFourm"+appid).action = "getRMSchedulePage";	
	document.getElementById("viewFourm"+appid).method = "get";
	document.getElementById("viewFourm"+appid).submit();
}

function getQuery(compid)
{
	//alert(compid)
	document.getElementById("viewFourm"+compid).action = "viewComQuestions";	
	document.getElementById("viewFourm"+compid).method = "get";
    document.getElementById("viewFourm"+compid).submit();
	}
function getShort(appid)
{
	document.getElementById("viewFourm"+appid).action = "getshortlistReceivePage";	
	document.getElementById("viewFourm"+appid).method = "get";
	document.getElementById("viewFourm"+appid).submit();
	}
function getHot(appid)
{
	//alert(appid);
	document.getElementById("viewFourm"+appid).action = "gethotlistReceivePage";	
	document.getElementById("viewFourm"+appid).method = "get";
	document.getElementById("viewFourm"+appid).submit();
	}
	function getShortRele(appid)
	{
		document.getElementById("viewFourm"+appid).action = "getshortlistReleaseCompanyHRPage";	
		document.getElementById("viewFourm"+appid).method = "get";
		document.getElementById("viewFourm"+appid).submit();
	}
	
	function getHotRele(appid)
	{
		document.getElementById("viewFourm"+appid).action = "getHotlistReleaseCompanyHRPage";	
		document.getElementById("viewFourm"+appid).method = "get";
		document.getElementById("viewFourm"+appid).submit();
	}
function getDropDownData(){
	 var xhttp = new XMLHttpRequest();
	  xhttp.onreadystatechange = function() {
	    if (this.readyState == 4 && this.status == 200) {
	    	var jsonData=JSON.parse(this.responseText);
	    	var options="<option>---Select RM Name-----</option>";
	    	for(var i=0;i<jsonData.length;i++){
	    		options+="<option value="+jsonData[i].designationId+">"+jsonData[i].designationName+"</option>"
	    	}
	    	document.getElementById("designation").innerHTML=options;
	    }
	  };
	  xhttp.open("POST", "getDesignationList", true);
	  xhttp.send();
}
function getRMDropDownData(appShareid){
	
		//alert("appid :: " +appShareid)
		document.getElementById("appShareid").value=appShareid;
	 var xhttp = new XMLHttpRequest();
	  xhttp.onreadystatechange = function() {
	    if (this.readyState == 4 && this.status == 200) {
	    	var jsonData=JSON.parse(this.responseText);
	    	var options="<option>Share With</option>";
	    	for(var i=0;i<jsonData.length;i++){
	    		options+="<option value="+jsonData[i].userId+">"+jsonData[i].userName+"</option>"
	    	}
	    	document.getElementById("rmlist").innerHTML=options;
	    }
	  };
	  xhttp.open("POST", "getRMList", true);
	  xhttp.send();
}

function editCompany(value){
	document.getElementById("editApplication"+value).action = "openCompanyEditForm";
	document.getElementById("editApplication"+value).submit();  
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
					<li><a href="addfineValues">Home</a></li>
					<li>Manage Application</li>
				</ul>
			</div>

		</div>
	</div>
	<div class="clearfix"></div>
	
	<div class="clearfix"></div>
	<div class="container">
		<div class="row">

			<!-- panel preview -->
			<div class="space"></div>
			<div class="container-fluid btn-container">
				<a href="getFirmMangement " class="btn btn-primary pull-right">Open
					Application</a>
			</div>
			<div class="clearfix"></div>
			<div class="table-responsive">
				<table id="main-fourum" class="table table-striped table-bordered">
					<thead>
						<tr>

							<th>Sr. No.</th>
							<th>Firm</th>
							<th>Role</th>
							<th>Application Status</th>
							<th>Manage</th>
							<th>Share</th>

							<th>Edit</th>
							<th>Query</th>
							<!-- <th>Panel Allocation Status</th> -->
						</tr>
					</thead>
					<tbody>

						<c:forEach items="${manageApp}" var="ma">

							<tr>
								<td>${ma.snum}</td>
								<td>${ma.firm}</td>
								<td>${ma.role}</td>
								<td>${ma.appStatus}</td>
								<c:set var="roleVal" value="${ma.appid}" />
								<%
									Integer appid = pageContext.getAttribute("roleVal") == null
											? 0	: (Integer) pageContext.getAttribute("roleVal");
										Hashids hashids = new Hashids("comp role id",10);
									//	String hash = "abdcr" + hashids.encode(appid);
										String hash = hashids.encode(appid);
								%>
								<td><c:choose>
										<c:when
											test="${ma.appStatus=='Pending'|| ma.appStatus=='Process Done'}">
							       	 		${ma.manage}
								        </c:when>

										<c:when test="${ma.appStatus=='Open'}">
											<form id="viewFourm${ma.appid}" method="get">
												<input type="hidden" value="<%=hash%>" name="cmpRoleId"
													id="viewFourm${ma.appid}" /> <a href="#"
													onclick="getScheduleID(${ma.appid})">${ma.manage}</a>
											</form>
										</c:when>
										<c:when test="${ma.appStatus=='Close'}">
											<c:choose>
												<c:when test="${ma.noOfSelectionRounds=='2' }">

													<form id="viewFourm${ma.appid}" method="get">
														<input type="hidden" value="<%=hash%>" name="cmpRoleId"
															id="viewFourm${ma.appid}" /> <a href="#"
															onclick="getManageGDID(${ma.appid})">${ma.manage}</a>
													</form>

												</c:when>
												<c:otherwise>
													<form id="viewFourm${ma.appid}" method="get">
														<input type="hidden" value="<%=hash%>" name="cmpRoleId"
															id="viewFourm${ma.appid}" /> <a href="#"
															onclick="getManageID(${ma.appid})">${ma.manage}</a>
													</form>
												</c:otherwise>

											</c:choose>
										</c:when>
										<c:when
											test="${ma.appStatus=='Short List Received-1' || ma.appStatus=='Sent to HR Shortlist-1'}">
											<form id="viewFourm${ma.appid}" method="get">
												<input type="hidden" value="<%=hash%>" name="cmpRoleId"
													id="viewFourm${ma.appid}" /> <a href="#"
													onclick="getManageGDID(${ma.appid})">${ma.manage}</a>
											</form>
										</c:when>

										<c:otherwise>
											<form id="viewFourm${ma.appid}" method="get">
												<input type="hidden" value="<%=hash%>" name="cmpRoleId"
													id="viewFourm${ma.appid}" /> <a href="#"
													onclick="getManageID(${ma.appid})">${ma.manage}</a>
											</form>
										</c:otherwise>
									</c:choose></td>

								<td><input type="button" class="btn btn-primary"
									data-toggle="modal" data-target="#rm-ma-share" value="Share"
									onclick="getRMDropDownData(${ma.appShareid});">
								<td>
									<%-- <input type="button" class="btn btn-primary"
									data-name="${ma.role}" data-description="${ma.firm}"
									data-toggle="modal" data-id="${ma.appid}" data-target="#C-role-edit"
									id="edit" value="Edit" onclick="getDropDownData();"> --%>
									<form action="openCompanyEditForm" method="post"
										id="editApplication${ma.appShareid}">
										<input type="hidden" value="${ma.appShareid}" id="appId"
											name="appId" /> <input type="button" name="Edit" value="Edit"
											class="btn btn-primary"
											onclick="editCompany(${ma.appShareid})" />
										<%-- 	<a href="openCompanyEditForm?appId=${ma.appShareid}" class="btn btn-primary">Edit</a>	 --%>
									</form>
								</td>
								<td><c:choose>
										<c:when
											test="${ma.appStatus=='Pending' || ma.appStatus=='Process Done'}">
     										${ma.query}
										</c:when>
										<c:otherwise>
											<a href="viewComQuestions?cmpId=${ma.compid}" target="_blank">${ma.query}</a>
										</c:otherwise>
									</c:choose></td>
								<%-- <td><c:choose>
										<c:when test="${ma.panelStatus==1}">
											<input type="checkbox" disabled="disabled" checked="checked">
										</c:when>
										<c:otherwise>
											<input type="checkbox" id="panelMail${ma.appid}"
												onclick="getPanelMail(${ma.appid})">
										</c:otherwise>
									</c:choose></td> --%>

							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<!-- / panel preview -->
			<div class="modal fade" id="C-role-edit" role="dialog">
				<div class="modal-dialog">

					<!-- Modal content-->
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal">&times;</button>
							<h4 class="modal-title">Edit Role</h4>
						</div>
						<form id="submitKmIIMStudent" action="editDesignationRole"
							method="POST" name="Designation">

							<input type="hidden" name="designationid" id="designationid"
								value="0" />

							<div class="modal-body">

								<div class="row">
									<label class="col-md-4">Designation Name</label>
									<div class="col-md-8">
										<select class="form-control" id="designation"
											name="designationname">
										</select>
									</div>
								</div>
								<p></p>
								<div class="row">
									<label class="col-md-4">Designation Description</label>
									<div class="col-md-8">
										<input class="form-control" id="designationdescription"
											placeholder="Designation Description" type="text"
											name="designationdescription" required>
									</div>
								</div>
								<input type="hidden" name="compnayRolePkId" id="companyRolePkId" />
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


			<div class="modal fade" id="rm-ma-share" role="dialog">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal">&times;</button>
							<h4 class="modal-title">Share Application</h4>
						</div>
						<form id="saveShare" action="shareRoleWith" method="POST"
							name="RmName">
							<!-- <input type="hidden" name="appid" value="0"/> -->
							<div class="modal-body">
								<input type="hidden" name="userid" id="userid"
									value="rmlist.useid" />
								<div class="row">
									<label class="col-md-6">Share With</label>
									<div class="col-md-6">
										<select class="form-control" id="rmlist" name="rmlist">
										</select>
									</div>
								</div>
							</div>
							<input type="hidden" name="appShareid" id="appShareid" />
							<div class="modal-footer">
								<button type="submit" class="btn btn-default">Add</button>
								<button type="button" class="btn btn-default"
									data-dismiss="modal">Cancel</button>
							</div>
						</form>
					</div>

				</div>
			</div>
		</div>
	</div>

	<div class="space"></div>


	<jsp:include page="commonJsp/Footer.jsp" />

</body>
<script type="text/javascript">

$(document).on("click", "#edit", function () {
  
    var Id = $(this).data('id');
    $("#designationid").val($(this).data('id'));
    $("#designationname").val($(this).data('name'));
   // $("#designationdescription").val($(this).data('description')); 
    $("#companyRolePkId").val(Id); 
    $("#C-role-edit").show();
});


function getPanelMail(appId){
	var r = confirm("Have you sent to HR");
	if (r == true) {
	   	if ($("#panelMail"+appId).is(":checked")) {
	   	  	var dataString = 'panelStatus=1&appid='+appId;
		   	$.ajax({
		   	    type:'POST',
		   	    data:dataString,
		   	    url:'updatePanelMailStatus',
		   	 	success:function(data) {
		   	      if(data=='success'){
		   	   		$("#panelMail"+appId).attr('disabled',true);
		   	      }
		   	    }
		   	});	
	   	}
	}else{
		$("#panelMail"+appId).attr('checked', false); 
	}
};


</script>
</html>