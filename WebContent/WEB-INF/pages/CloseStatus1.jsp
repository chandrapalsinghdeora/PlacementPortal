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
<title>Indian Institute of Management Ahmedabad (IIMA) | Home - IIMA</title>
    <link rel="icon" href="images/favicon.ico" type="image/x-icon"/>
<!-- Bootstrap -->
<link href="css/bootstrap.css" rel="stylesheet">

<!-- Custom -->
<link href="css/custom.css" rel="stylesheet">
<link rel="stylesheet" href="css/font-awesome.css">

<!-- CSS STYLE-->
<link rel="stylesheet" type="text/css" href="css/style.css"
	media="screen" />
<link rel="stylesheet" type="text/css"
	href="css/dataTables.bootstrap.min.css" media="screen" />
<link rel="stylesheet" type="text/css"
	href="css/buttons.dataTables.min.css" media="screen" />
<link rel="stylesheet" type="text/css"
	href="css/jquery.dataTables.min.css" media="screen" />

</head>
<body>
	<jsp:include page="commonJsp/Header.jsp" />
	<div class="clearfix"></div>
	<div class="breadcumb-wrapper">
		<div class="clearfix">
			<div class="pull-left">
				<ul class="list-inline link-list">
					<li><a href="addfineValues">Home</a></li>
					<li><a href="appManage">Manage Application</a></li>
					<li>Close Status</li>
				</ul>
			</div>

		</div>
	</div>

	<form method="post" id="downloadCV">
		<input type="hidden" name="downloadCvId" id="downloadCvId" />
		<input type="hidden" name="coverLetter" id="coverLetter" value="0">
	</form>
	<form method="post" id="pdfBind">
		<input type="hidden" name="bindId" id="bindId" />
	</form>
	<form method="post" id="updateFlag">
		<input type="hidden" name="updateApplyId" id="updateApplyId" />
	</form>

	<div class="space"></div>
	<div class="clearfix"></div>
	<div class="closed-status">
		<aside class="col-md-2">
			<nav class="nav-sidebar">
				<ul class="nav tabs">
					<li class="active"><a href="#tab1" data-toggle="tab">Verify
							Application</a></li>
					<li class=""><a href="#tab2" data-toggle="tab">Approved
							Application List</a></li>
					<li class=""><a href="#tab3" data-toggle="tab">Removed
							Candidates</a></li>
					<li class=""><a href="#tab4" data-toggle="tab">Download
							Verified List</a></li>

					<!-- <li class=""><a href="#tab6" data-toggle="tab">Generate Shortlist Link
						</a></li> -->
					<li class=""><a href="#tab5" data-toggle="tab">Enter
							Shortlist </a></li>
					<li class=""><a href="#tab6" data-toggle="tab">View
							Released Shortlist </a></li>

					<li class=""><a href="#tab9" data-toggle="tab">Shortlisted 
							By HR</a></li>
				</ul>
			</nav>

		</aside>

		<div class="col-md-10">

			<div class="tab-content">
				<div class="tab-pane active text-style" id="tab1">
					<div class="col-md-12">
						<h3>${closeStatus.firmName} - ${closeStatus.roleName} -
							${closeStatus.year}</h3>
					</div>

					<!-- panel preview -->
					<div class="container-fluid btn-container">
						<a href="#" class="pull-right" data-toggle="modal"
							data-target="#rm-status-schedule-msg" onclick="getInfoValues();">
							<i class="fa fa-info-circle" aria-hidden="true"></i>
						</a><label class="pull-right"> [ ${closeStatus.process}] :
							[${closeStatus.experienceReq} months] : </label>
					</div>

					<div class="clearfix"></div>

					<table id="verify-application"
						class="table table-bordered">
						<thead>
							<tr>
								<th><input type="checkbox" id="verfiylistbtn"></th>
								<th>Roll Number</th>
								<th>Email ID</th>
								<th>Name</th>
								<th>Internship</th>
								<th>Experience</th>
								<th>CV</th>
								<th>Roll Over</th>
								<th>Status</th>
								<th>verify</th>
								<th>Flag</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${CloseStatusDetails}" var="fm">
								<tr>
									<td><input type="checkbox" name="selector[]"
										class="close1_Checkbox checkbox" id="Checkbox${fm.applyId}"
										value="${fm.applyId}"></td>
									<td>${fm.rollnumber}</td>
									<td><a href="#" data-toggle="modal"
										data-target="#closed-status-email"
										onclick="getValues('${fm.rollnumber}');">${fm.emailId}</a></td>
									<td>${fm.name}</td>
									<td><a href="#" data-toggle="modal"
										data-target="#Internship-popup"
										onclick="getInternshipValues('${fm.rollnumber}');">${fm.internship}</a>

									</td>
									<td><a href="#" data-toggle="modal"
										data-target="#experience-popup"
										onclick="getExperienceValues('${fm.rollnumber}');">${fm.experience}</a>

									</td>
									<td><a href="#" data-toggle="modal"
										data-target="#cv-title-popup"
										onclick="getCVValues('${fm.rollnumber}','${fm.emailId}');">${fm.cv}</a></td>
									<td>${fm.rollover}</td>
									<td>${fm.status}</td>
									<td>${fm.verify}</td>
									<td>${fm.flag}</td>
								</tr>

							</c:forEach>
						</tbody>

					</table>

					<div class="space"></div>
					<div class="col-md-12 text-right">
					<input type="button" value="PDF Bind"
							class="btn btn-primary" id="verifypdfbind" /> 
							
							<input type="button" value="Download CV or Cover Letter(zip)"
							class="btn btn-primary" data-toggle="modal"
								data-target="#cover-letterTab1" /> 
						
								<div class="modal fade" id="cover-letterTab1" role="dialog">
								<div class="modal-dialog">
									<div class="modal-content">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal">&times;</button>
											<h4 class="modal-title text-left">Cover Letter Download</h4>
										</div>
										<div class="modal-body">

											<div class="row">
												<div class="col-md-12 text-left">
													<input type="checkbox" name="cvs1" id="cvs1">
													Do you want to download Cover letters along with PDFs? 
												</div>
											</div>

										</div>
										<div class="modal-footer text-center">
											<button type="button" class="btn btn-primary"
												data-dismiss="modal" onclick="downloadCVsTab1();">OK</button>
											<button type="button" class="btn btn-primary"
												data-dismiss="modal">NO</button>
										</div>
									</div>

								</div>
							</div>
						<input type="button" id="verifyBtn" value="Verify"
							class="btn btn-primary " />
					</div>
					<div class="clearfix"></div>
				</div>


				<div class="tab-pane text-style" id="tab2">

					<div class="col-md-12">
						<h3>${closeStatus.firmName}- ${closeStatus.roleName} -
							${closeStatus.year}</h3>
					</div>

					<!-- panel preview -->
					<div class="container-fluid btn-container">
						<b>Note:</b>Verify status is yes than this list is show <a
							href="#" class="pull-right" data-toggle="modal"
							data-target="#rm-status-schedule-msg" onclick="getInfoValues();">
							<i class="fa fa-info-circle" aria-hidden="true"></i>
						</a><label class="pull-right"> [ ${closeStatus.process}] :
							[${closeStatus.experienceReq} months] : </label>
					</div>

					<div class="clearfix"></div>

					<table id="approve-application"
						class="table table-bordered">
						<thead>
							<tr>
								<th>Roll Number</th>
								<th>Email ID</th>
								<th>Name</th>
								<th>InternShip</th>
								<th>Experience</th>
								<th>CV</th>
								<th>Status</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${approveCloseStatusList}" var="fm">
								<tr>
									<td>${fm.rollnumber}    
										<input type="hidden" name="approveId" value="${fm.applyId}" class="approveCheckbox">
									</td>
									<td><a href="#" data-toggle="modal"
										data-target="#closed-status-email"
										onclick="getValues('${fm.rollnumber}');">${fm.emailId}</a></td>
									<td>${fm.name}</td>
									<td><a href="#" data-toggle="modal"
										data-target="#Internship-popup"
										onclick="getInternshipValues('${fm.rollnumber}');">${fm.internship}</a>

									</td>
									<td><a href="#" data-toggle="modal"
										data-target="#experience-popup"
										onclick="getExperienceValues('${fm.rollnumber}');">${fm.experience}</a>

									</td>
									<td><a href="#" data-toggle="modal"
										data-target="#cv-title-popup"
										onclick="getCVValues('${fm.rollnumber}','${fm.emailId}');">${fm.cv}</a></td>
									<td>${fm.status}</td>
								</tr>

							</c:forEach>
						</tbody>
					</table>
					<div class="space"></div>
					<div class="col-md-12 text-right">
						<input type="button" value="PDF Bind"
							class="btn btn-primary" id="approvepdfbind" /> 
												<input type="button" value="Download CV or Cover Letter(zip)" class="btn btn-primary"  data-toggle="modal" data-target="#cover-letterTab2"  />
						 <!-- id="downloadBtnEnterHotlist" -->
							<div class="modal fade" id="cover-letterTab2" role="dialog">
								<div class="modal-dialog">

									<div class="modal-content">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal">&times;</button>
											<h4 class="modal-title text-left">Cover Letter Download</h4>
										</div>
										<div class="modal-body">
											<div class="row">
												<div class="col-md-12 text-left">
													<input type="checkbox" name="cvs2" id="cvs2">
													Do you want to download Cover letters along with PDFs? 
												</div>
											</div>

										</div>
										<div class="modal-footer text-center">
											<button type="button" class="btn btn-primary"
												data-dismiss="modal" onclick="downloadCVsTab2();">OK</button>
											<button type="button" class="btn btn-primary"
												data-dismiss="modal">NO</button>
										</div>
									</div>

								</div>
							</div>
						
					</div>
					<div class="clearfix"></div>
				</div>


				<div class="tab-pane text-style" id="tab3">
					<div class="col-md-12">
						<h3>${closeStatus.firmName} - ${closeStatus.roleName} -
							${closeStatus.year}</h3>
					</div>

					<!-- panel preview -->
					<div class="container-fluid btn-container">
						<b>Note:</b>Verify status is no than this list is show <a href="#"
							class="pull-right" data-toggle="modal"
							data-target="#rm-status-schedule-msg" onclick="getInfoValues();">
							<i class="fa fa-info-circle" aria-hidden="true"></i>
						</a><label class="pull-right"> [ ${closeStatus.process}] :
							[${closeStatus.experienceReq} months] : </label>
					</div>

					<div class="clearfix"></div>
					<table id="remove-application"
						class="table table-bordered display nowrap">
						<thead>
							<tr>
								<th>Roll Number</th>
								<th>Email ID</th>
								<th>Name</th>
								<th>InternShip</th>
								<th>Experience</th>
								<th>CV</th>
								<th>Status</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${unApproveCloseStatusList}" var="fm">
								<tr>
									<td>${fm.rollnumber}
										<input type="hidden" name="removeId" value="${fm.applyId}" class="removeCheckbox">
									</td>
									<td><a href="#" data-toggle="modal"
										data-target="#closed-status-email"
										onclick="getValues('${fm.rollnumber}');">${fm.emailId}</a></td>
									<td>${fm.name}</td>
									<td><a href="#" data-toggle="modal"
										data-target="#Internship-popup"
										onclick="getInternshipValues('${fm.rollnumber}');">${fm.internship}</a>

									</td>
									<td><a href="#" data-toggle="modal"
										data-target="#experience-popup"
										onclick="getExperienceValues('${fm.rollnumber}');">${fm.experience}</a>

									</td>
									<td><a href="#" data-toggle="modal"
										data-target="#cv-title-popup"
										onclick="getCVValues('${fm.rollnumber}','${fm.emailId}');">${fm.cv}</a></td>
									<td>${fm.status}</td>
								</tr>

							</c:forEach>
						</tbody>
					</table>
					
					<div class="space"></div>
					<div class="col-md-12 text-right">
						<input type="button" value="PDF Bind"
							class="btn btn-primary" id="removepdfbind" /> 
							<input type="button" value="Download CV or Cover Letter(zip)" class="btn btn-primary"  data-toggle="modal" data-target="#cover-letterTab3"  />
						 <!-- id="downloadBtnEnterHotlist" -->
							<div class="modal fade" id="cover-letterTab3" role="dialog">
								<div class="modal-dialog">

									<div class="modal-content">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal">&times;</button>
											<h4 class="modal-title text-left">Cover Letter Download</h4>
										</div>
										<div class="modal-body">
											<div class="row">
												<div class="col-md-12 text-left">
													<input type="checkbox" name="cvs3" id="cvs3">
													Do you want to download Cover letters along with PDFs? 
												</div>
											</div>

										</div>
										<div class="modal-footer text-center">
											<button type="button" class="btn btn-primary"
												data-dismiss="modal" onclick="downloadCVsTab3();">OK</button>
											<button type="button" class="btn btn-primary"
												data-dismiss="modal">NO</button>
										</div>
									</div>

								</div>
							</div>
					</div>
					<div class="clearfix"></div>

				</div>

				<div class="tab-pane text-style" id="tab4">

					<div class="col-md-12">
						<h3>${closeStatus.firmName}- ${closeStatus.roleName} -
							${closeStatus.year}</h3>
					</div>

					<!-- panel preview -->
					<div class="container-fluid btn-container">
						<b>Note:</b>Verify status is yes than this list is show <a href="#"
							class="pull-right" data-toggle="modal"
							data-target="#rm-status-schedule-msg" onclick="getInfoValues();">
							<i class="fa fa-info-circle" aria-hidden="true"></i>
						</a><label class="pull-right"> [ ${closeStatus.process}] :
							[${closeStatus.experienceReq} months] : </label>

					</div>

					<div class="clearfix"></div>

					<table id="verified-application"
						class="table table-bordered display nowrap">
						<thead>
							<tr>
								<th><input type="checkbox" id="considerlistbtn"></th>
								<th>Roll Number</th>
								<th>Email ID</th>
								<th>Name</th>
								<th>CV</th>
								<th>Prefrences</th>
								<th>Status</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${approveCloseStatusList}" var="fm">
								<tr>
									<td><input type="checkbox" name="selector[]"
										class="considerCheck" id="Checkbox${fm.applyId}"
										value="${fm.applyId}"></td>
									<td>${fm.rollnumber}</td>
									<td><a href="#" data-toggle="modal"
										data-target="#closed-status-email"
										onclick="getValues('${fm.rollnumber}');">${fm.emailId}</a></td>
									<td>${fm.name}</td>
									<td><a href="#" data-toggle="modal"
										data-target="#cv-title-popup"
										onclick="getCVValues('${fm.rollnumber}','${fm.emailId}');">${fm.cv}</a></td>
									<td>${fm.prefrences}</td>
									<td>${fm.status}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<div class="space"></div>
					<div class="col-md-12 text-right">
					<input type="button" value="PDF Bind"
							class="btn btn-primary" id="downloadpdfbind" /> 
								
						<input type="button" value="Download CV or Cover Letter(zip)" class="btn btn-primary"  data-toggle="modal" data-target="#cover-letterTab4"  />
						 <!-- id="downloadBtnEnterHotlist" -->
							<div class="modal fade" id="cover-letterTab4" role="dialog">
								<div class="modal-dialog">

									<div class="modal-content">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal">&times;</button>
											<h4 class="modal-title text-left">Cover Letter Download</h4>
										</div>
										<div class="modal-body">
											<div class="row">
												<div class="col-md-12 text-left">
													<input type="checkbox" name="cvs4" id="cvs4">
													Do you want to download Cover letters along with PDFs? 
												</div>
											</div>

										</div>
										<div class="modal-footer text-center">
											<button type="button" class="btn btn-primary"
												data-dismiss="modal" onclick="downloadCVsTab4();">OK</button>
											<button type="button" class="btn btn-primary"
												data-dismiss="modal">NO</button>
										</div>
									</div>

								</div>
							</div>	
					
					 <input type="button"
							value="Genrate Consideration List Link" class="btn btn-primary"
							data-toggle="modal" id="shortListBtn" />

						<div class="modal fade" id="shortlist-link-popup" role="dialog">
							<div class="modal-dialog">
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal">&times;</button>
										<h4 class="modal-title text-left">List Send to Firm</h4>
									</div>
									<form id="insertShortList1" name="CloseStatus">
										<input type="hidden" name="updateApplyId" id="updateApplyIds"
											value="0" /> <input type="hidden" name="roleId" id="roleId"
											value=" ${closeStatus.roleId}" />
										<div class="modal-body">
										
											<div class="row">
												<label class="col-md-6 text-right">HR Name
													: </label>
												<div class="col-md-6">
													<input type="text" class="form-control" id="hrName"
														name="hrName">
												</div>
											</div>
											<div class="row">
												<label class="col-md-6 text-right">Firm HR Email Id
													: </label>
												<div class="col-md-6">
													<input type="text" class="form-control" id="emailId"
														name="emailId">
												</div>
											</div>
											<!-- 
											<div class="row">
												<div class="col-md-12 text-left">
													<input type="checkbox" id="rankFlag" name="rankFlag" />
													Send Preferences
												</div>
											</div>
											 -->
											<div class="row">
												<div class="col-md-12 text-left">
													<textarea rows="3" class="form-control" id="mailDes"
														name="mailDes"></textarea>
												</div>
											</div>
										</div>
										<div class="modal-footer text-center">

											<button type="button" class="btn btn-primary" id="insertList1">Send</button>
											<button type="button" class="btn btn-primary"
												data-dismiss="modal">Close</button>
										</div>
									</form>
								</div>
							</div>
						</div>
						
						<div class="modal fade" id="list-link1-popup" role="dialog">
							<div class="modal-dialog">
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal">&times;</button>
										<h4 class="modal-title text-left">Are you sure you want to send the mail ?</h4>
									</div>
									
										<div class="modal-body">
										
											<div class="row">
												<div class="col-md-12  text-center"><strong>HR Name : </strong>
													<span id="hrName1" >	</span>
												</div>
												
											</div>
											<div class="row">
												<div class="col-md-12 text-center"><strong> Firm HR Email Id	:</strong> 
												<span id="emailId1" >	</span>
												
												</div>
											</div>
											
										</div>
										<div class="modal-footer text-center">

											<button type="submit" class="btn btn-primary"  id="insertList2" onclick="getvalueindb();">Ok</button>
											<button type="button" class="btn btn-primary"
												data-dismiss="modal">Cancel</button>
										</div>
									
								</div>
							</div>
						</div>
						
					</div>
					<div class="space"></div>

				</div>

				<div class="tab-pane text-style" id="tab5">

					<div class="col-md-12">
						<h3>${closeStatus.firmName}- ${closeStatus.roleName} -
							${closeStatus.year}</h3>
					</div>

					<div class="container-fluid btn-container">
						<a href="#" class="pull-right" data-toggle="modal"
							data-target="#rm-status-schedule-msg" onclick="getInfoValues();">
							<i class="fa fa-info-circle" aria-hidden="true"></i>
						</a> <label class="pull-right"> [ ${closeStatus.process}] :
							[${closeStatus.experienceReq} months] : </label>
					</div>

					<div class="clearfix"></div>
					<form name="shortlistForm" id="shortlistformId">
						<table id="shortlist_application"
							class="table table-bordered display nowrap">
							<thead>
								<tr>
									<th>Roll Number</th>
									<th>Email ID</th>
									<th>Name</th>
									<th>Prefrence</th>
									<th>Status</th>
									<th>RM Status</th>
									<th>Shortlist <input type="checkbox" id="shortlistbtn"></th>
								</tr>
							</thead>
							<tbody>

								<c:forEach items="${shortlist1}" var="shrt">
									<input type="hidden" value="${shrt.shortListId}"
										name="shortListId" />
									<tr>
										<td>${shrt.rollNumber}</td>
										<td><a href="#" data-toggle="modal"
											data-target="#closed-status-email"
											onclick="getUserDetailsByRolNo('${shrt.rollNumber}');">
												${shrt.emailID}</a></td>
										<td>${shrt.name}</td>
										<td>${shrt.preference}</td>
										<td>${shrt.status}</td>
										<td>${shrt.rmStatus}</td>
										<td align="center"><input type="hidden" value=""
											name="shortListIdsSelected" id="shortListIdsSelected" /> 
											<input type="hidden" name="notShortlisted" value=""	id="notShortListedIds">
											
											<c:choose>
												<c:when test="${shrt.status=='Withdrawal'}">
														<input type="checkbox" disabled="disabled"
														value="${shrt.applyId}##${shrt.shortListId}">
												</c:when>
												<c:otherwise>
												<input type="checkbox" name="shortlisted" value="${shrt.applyId}" class="checkbox shortlisted">
												 </c:otherwise>
											 </c:choose>
											 <%-- <c:if test="${shrt.shortList eq false}">
												<input type="checkbox" name="shortlisted" value="${shrt.applyId}" class="checkbox">
											</c:if>
											 <c:if test="${shrt.shortList eq true}">
												<input type="checkbox" name="shortlisted" checked="checked"	value="${shrt.applyId}" class="checkbox" checked>
											</c:if> --%>
										</td>
									</tr>

								</c:forEach>
							</tbody>
						</table>

						<div class="space"></div>
						<div class="col-md-12 text-right">
						<input type="button" id="saveVerifyId"
								value="Remove Shortlist" class="btn btn-primary" />
							<div class="modal fade" id="save-verify-details" role="dialog">
								<div class="modal-dialog">
									<div class="modal-content">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal">&times;</button>
											<h4 class="modal-title text-left">Confirmation</h4>
										</div>
										<div class="modal-body">
											<div class="row">
												<div class="col-md-12 text-center">													
												 <label>Are you sure to remove shortlisted students?</label> 
												</div>
											</div>
											

										</div>
										<div class="modal-footer text-center">
											<button type="button" class="btn btn-primary"
												onclick="shortlistRemove();">Yes</button>
											<button type="button" class="btn btn-primary"
												data-dismiss="modal">No</button>
										</div>
									</div>
								</div>
							</div>
						<input type="button" value="PDF Bind"
							class="btn btn-primary" id="shortlistpdfbind" /> 
								
						<input type="button" value="Download CV or Cover Letter(zip)" class="btn btn-primary"  data-toggle="modal" data-target="#cover-letterTab5"  />
						 <!-- id="downloadBtnEnterHotlist" -->
							<div class="modal fade" id="cover-letterTab5" role="dialog">
								<div class="modal-dialog">

									<div class="modal-content">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal">&times;</button>
											<h4 class="modal-title text-left">Cover Letter Download</h4>
										</div>
										<div class="modal-body">
											<div class="row">
												<div class="col-md-12 text-left">
													<input type="checkbox" name="cvs5" id="cvs5">
													Do you want to download Cover letters along with PDFs? 
												</div>
											</div>

										</div>
										<div class="modal-footer text-center">
											<button type="button" class="btn btn-primary"
												data-dismiss="modal" onclick="downloadCVsTab5();">OK</button>
											<button type="button" class="btn btn-primary"
												data-dismiss="modal">NO</button>
										</div>
									</div>

								</div>
							</div>
						

							<input type="button" id="saveReleaseId1"
								value="Save Shortlist Details"
							<%-- 	onclick="getGreetings('${cmpRoleId}');" --%> data-toggle="modal"
								data-target="#save-shortlist-details" class="btn btn-primary" />

							<div class="modal fade" id="save-shortlist-details" role="dialog">
								<div class="modal-dialog">
									<div class="modal-content">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal">&times;</button>
											<h4 class="modal-title text-left">Confirmation</h4>
										</div>
										<div class="modal-body">
											<div class="row">
												<div class="col-md-12 text-center">													
												 <label>Are you sure want to shortlist?</label> 
												</div>
											</div>
											

										</div>
										<div class="modal-footer text-center">
											<button type="button" class="btn btn-primary"
												onclick="saveShortlisted();">Yes</button>
											<button type="button" class="btn btn-primary"
												data-dismiss="modal">No</button>
										</div>
									</div>
								</div>
							</div>
						</div>
					</form>
				</div>

				<div class="tab-pane text-style" id="tab6">

					<div class="col-md-12">
						<h3>${closeStatus.firmName}- ${closeStatus.roleName} -
							${closeStatus.year}</h3>
					</div>

					<div class="container-fluid btn-container">
						<a href="#" class="pull-right" data-toggle="modal"
							data-target="#rm-status-schedule-msg" onclick="getInfoValues();">
							<i class="fa fa-info-circle" aria-hidden="true"></i>
						</a> <label class="pull-right"> [ ${closeStatus.process}] :
							[${closeStatus.experienceReq} months] : </label>
				
					</div>
					<div class="clearfix"></div>
					<form name="shortlistedReleaseForm" id="shortlistedReleaseFormId">
						<input type="hidden" name="cmpRoleId" value="${closeStatus.roleId}">
						<table id="shortlistRelease_application"
							class="table table-bordered display nowrap">
							<thead>
								<tr>
									<th>Roll Number</th>
									<th>Email ID</th>
									<th>Name</th>
									<th>Preference</th>
									<th>Status</th>

								</tr>
							</thead>
							<tbody>
								<c:forEach items="${viewReleasedShortlist}" var="shrt">
								
									<tr>
										<td>${shrt.rollNumber}<input type="hidden"
											value="${shrt.shortListId}##${shrt.applyId}" name="idlist" />
											<input type="hidden" value="${shrt.applyId}" class="viewReleaseCheckbox"></td>
										<td><a href="#" data-toggle="modal"
											data-target="#closed-status-email"
											onclick="getUserDetailsByRolNo('${shrt.rollNumber}');">
												${shrt.emailID}</a></td>
										<td>${shrt.name}</td>
										<td>${shrt.preference}</td>
										<td>${shrt.status}</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>

						<div class="space"></div>
						<div class="col-md-12 text-right">
						<input type="button" value="PDF Bind" class="btn btn-primary" id="downloadPdfBindViewRelease" /> 
						  			
						<input type="button" value="Download CV or Cover Letter(zip)" class="btn btn-primary"  data-toggle="modal" data-target="#cover-letterTab6"  />
						 <!-- id="downloadBtnEnterHotlist" -->
							<div class="modal fade" id="cover-letterTab6" role="dialog">
								<div class="modal-dialog">

									<div class="modal-content">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal">&times;</button>
											<h4 class="modal-title text-left">Cover Letter Download</h4>
										</div>
										<div class="modal-body">
											<div class="row">
												<div class="col-md-12 text-left">
													<input type="checkbox" name="cvs6" id="cvs6">
													Do you want to download Cover letters along with PDFs? 
												</div>
											</div>

										</div>
										<div class="modal-footer text-center">
											<button type="button" class="btn btn-primary"
												data-dismiss="modal" onclick="downloadCVsTab6();">OK</button>
											<button type="button" class="btn btn-primary"
												data-dismiss="modal">NO</button>
										</div>
									</div>

								</div>
							</div>
						
							
							<input type="button" value="Shortlist Release"
								class="btn btn-primary"  onclick="checkFlagStatus('${cmpRoleId}');" /><!--  onclick="saveShortlistReleaseByRM();" -->
							
										<div class="modal fade" id="errorMessageModal" role="dialog"  >
								<div class="modal-dialog">

									<div class="modal-content">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal">&times;</button>
											<h4 class="modal-title text-left">Shortlist Unrelease</h4>
										</div>
										<div class="modal-body">
											<div class="row">
												<div class="col-md-12 text-left">
													
													Please Re-verify Shortlist of Students
												</div>
											</div>

										</div>
										<div class="modal-footer text-center">
											
											<button type="button" class="btn btn-primary"
												data-dismiss="modal">OK</button>
										</div>
									</div>

								</div>
							</div>
							
							
							<div class="modal fade" id="save-shortlist-release-rm" role="dialog">
								<div class="modal-dialog">

									<div class="modal-content">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal">&times;</button>
											<h4 class="modal-title text-left">Congratulation</h4>
										</div>
										<div class="modal-body">
											<div class="row">
												<div class="col-md-12 text-center">
													<label>Student Congratulation</label>
												</div>
											</div>
											<div class="row">
												<div class="col-md-12 text-left">
													<textarea rows="3" class="form-control" id="greetingsData"
														name="greetings"></textarea>
												</div>
											</div>

										</div>
										<div class="modal-footer text-center">
											<button type="button" class="btn btn-primary"
												onclick="saveShortlistReleaseByRM();">OK</button>
											<button type="button" class="btn btn-primary"
												data-dismiss="modal">Close</button>
										</div>
									</div>
								</div>
							</div>
							 
							<!--  <input	type="button" value="Generate Hotlist Link" data-toggle="modal"
								data-target="#generate-shortlist-link" class="btn btn-primary" /> -->

							<input type="hidden" name="roleId" id="roleId"
								value=" ${closeStatus.roleId}" />

							<div class="modal fade" id="generate-shortlist-link"
								role="dialog">
								<div class="modal-dialog">

									<div class="modal-content">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal">&times;</button>
											<h4 class="modal-title text-left">List Send to Firm</h4>
										</div>
										<div class="modal-body">
											<div class="row">
												<label class="col-md-6 text-right">Firm HR Email Id
													: </label>
												<div class="col-md-6">
													<input type="text" class="form-control" name="emailId">
												</div>
											</div>
											<div class="row">
												<div class="col-md-12 text-left">
													<input type="checkbox" name="rankFlag" /> Send Preference
												</div>
											</div>
											<div class="row">
												<div class="col-md-12 text-left">
													<textarea rows="3" class="form-control" name="mailDes"></textarea>
												</div>
											</div>

										</div>
										<div class="modal-footer text-center">
											<button type="button" class="btn btn-primary"
												onclick="saveSendMail();">Send</button>
											<button type="button" class="btn btn-primary"
												data-dismiss="modal">Close</button>
										</div>
									</div>
								</div>
							</div>
						</div>
					</form>
				</div>

				<!-- Shortlisted by HR div starts -->
				
				<div class="tab-pane text-style" id="tab9">

					<div class="col-md-12">
						<h3>${closeStatus.firmName}- ${closeStatus.roleName} -
							${closeStatus.year}</h3>
					</div>

					<div class="container-fluid btn-container">
						<a href="#" class="pull-right" data-toggle="modal"
							data-target="#rm-status-schedule-msg" onclick="getInfoValues();">
							<i class="fa fa-info-circle" aria-hidden="true"></i>
						</a> <label class="pull-right"> [ ${closeStatus.process}] :
							[${closeStatus.experienceReq} months] : </label>
					</div>

					<div class="clearfix"></div>
					<form name="releaseShortlistForm" id="releaseShortlistformId" >
					<input type="hidden" name="cmpRoleId" value="${closeStatus.roleId}">
						<table id="shortlistHR_application"
							class="table table-bordered display nowrap">
							<thead>
								<tr>
									<th>Roll Number</th>
									<th>Email ID</th>
									<th>Name</th>
									<th>Preference</th>
									<th>Status</th>
									<!-- <th>RM Status</th> -->
									<!-- <th>Shortlist</th> -->
								</tr>
							</thead>
							<tbody>
								<!-- <script>
								var array=[];
								</script> -->
								<c:forEach items="${shortlistedByHr}" var="shrt">
								
									<input type="hidden" value="${shrt.shortListId}"
										name="shortListId" />
									<!-- <script>
									var shortId=${shrt.shortListId};
									array.push(shortId);									
									</script> -->									
									
									<tr>
									<td>${shrt.rollNumber}<input type="hidden" value="${shrt.applyId}" name="applyIdListIdslist" class="shortlistedByHR"></td>
									<td><a href="#" data-toggle="modal"
											data-target="#closed-status-email"
											onclick="getUserDetailsByRolNo('${shrt.rollNumber}');">
												${shrt.emailID}</a></td>
										<td>${shrt.name}</td>
										<td>${shrt.preference}</td>
										<td>Shortlisted By HR<%-- ${shrt.status} --%></td>
										<%-- <td>${shrt.rmStatus}</td> --%>
										<%-- <td align="center">
										 <input type="hidden" value="" name="shortListIdsSelected" id="shortListIdsSelected" />
										 <input	type="hidden" name="notShortlisted" value="" id="notShortListedIds">									
										
										 <c:if test="${shrt.shortList eq false}">
												<input type="checkbox" name="shortlisted" value="${shrt.applyId}" class="checkbox">
										 </c:if> 
										 <c:if test="${shrt.shortList eq true}">
										<input type="checkbox" name="shortlisted" checked="checked"	value="${shrt.applyId}" class="checkbox" checked>
										</c:if> 
										
										</td>--%>
									</tr>

								</c:forEach>
							</tbody>
						</table>

						<div class="space"></div>
						<div class="col-md-12 text-right">
						 <input type="button" value="PDF Bind" class="btn btn-primary" id="downloadPdfBindShortlistHR" /> 
              							
						<input type="button" value="Download CV or Cover Letter(zip)" class="btn btn-primary"  data-toggle="modal" data-target="#cover-letterTab9"  />
						 <!-- id="downloadBtnEnterHotlist" -->
							<div class="modal fade" id="cover-letterTab9" role="dialog">
								<div class="modal-dialog">

									<div class="modal-content">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal">&times;</button>
											<h4 class="modal-title text-left">Cover Letter Download</h4>
										</div>
										<div class="modal-body">
											<div class="row">
												<div class="col-md-12 text-left">
													<input type="checkbox" name="cvs9" id="cvs9">
													Do you want to download Cover letters along with PDFs? 
												</div>
											</div>

										</div>
										<div class="modal-footer text-center">
											<button type="button" class="btn btn-primary"
												data-dismiss="modal" onclick="downloadCVsTab9();">OK</button>
											<button type="button" class="btn btn-primary"
												data-dismiss="modal">NO</button>
										</div>
									</div>

								</div>
							</div>
						

							<input type="button" id="saveReleaseId"
								value="Shortlist Release"
								onclick="getFinalGreetings('${cmpRoleId}');" data-toggle="modal"
								data-target="#final-shortlist-release" class="btn btn-primary" />

							<div class="modal fade" id="final-shortlist-release" role="dialog">
								<div class="modal-dialog">

									<div class="modal-content">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal">&times;</button>
											<h4 class="modal-title text-left">Congratulation</h4>
										</div>
										<div class="modal-body">
											<div class="row">
												<div class="col-md-12 text-center">
													<label>Student Congratulation</label>
												</div>
											</div>
											<div class="row">
												<div class="col-md-12 text-left">
													<textarea rows="3" class="form-control" id="finalGreetingsData"
														name="greetings"></textarea>
												</div>
											</div>

										</div>
										<div class="modal-footer text-center">
											<button type="button" class="btn btn-primary"
												onclick="saveReleaseHRShortlistedByRM();">OK</button>
											<button type="button" class="btn btn-primary"
												data-dismiss="modal">Close</button>
										</div>
									</div>
								</div>
							</div>
						</div>
					</form>
				</div>
				
				<!-- Shortlisted by HR div ends -->
				
				
				
			</div>
		</div>
	</div>
	<div class="space"></div>
	<div class="clearfix"></div>
	
	<!-- all dialog box panel preview -->
	<div class="modal fade" id="rm-status-schedule-msg" role="dialog">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Info</h4>
				</div>
				<div class="modal-body">
					<div class="row">
						<h4 class="col-md-12 title">Company</h4>
					</div>
					<hr>

					<div class="row">
						<div class="col-md-6">Cluster :</div>
						<div class="col-md-6" id="cluster"></div>
					</div>
					<div class="row">
						<div class="col-md-6">Cohort :</div>
						<div class="col-md-6" id="cohort"></div>
					</div>
					<div class="row">
						<div class="col-md-6">Compensation(CTC) :</div>
						<div class="col-md-6" id="compensation"></div>
					</div>
					<div class="row">
						<div class="col-md-6">Expected No of Hires:</div>
						<div class="col-md-6" id="hires"></div>
					</div>
					<hr>

					<div class="row">
						<h4 class="col-md-12 title">Application</h4>
					</div>
					<hr>

					<div class="row">
						<div class="col-md-6">No. of Application</div>
						<div class="col-md-6" id="noOfApp"></div>
					</div>
					<div class="row">
						<div class="col-md-6">Verify Application</div>
						<div class="col-md-6" id="rollOver"></div>
					</div>
					<div class="row">
						<div class="col-md-6">UnVerify Application</div>
						<div class="col-md-6" id="verified"></div>
					</div>
					<div class="row">
						<div class="col-md-6">No. of RollOver</div>
						<div class="col-md-6" id="unVerified"></div>
					</div>

				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="closed-status-email" role="dialog">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title" id="closeEmailId"></h4>
				</div>
				
				<div class="modal-body">
					<div class="row">
						<label class="col-md-6 text-right">Full Name : </label>
						<div class="col-md-6" id="fullName"></div>
					</div>
					<div class="row">
						<label class="col-md-6 text-right">CV Name : </label>
						<div class="col-md-6" id="cvName"></div>
					</div>
					<div class="row">
						<label class="col-md-6 text-right">Mentor Name : </label>
						<div class="col-md-6" id="mentorName"></div>
					</div>
					<div class="row">
						<label class="col-md-6 text-right">Gender : </label>
						<div class="col-md-6" id="gender"></div>
					</div>
					<div class="row">
						<label class="col-md-6 text-right">10% : </label>
						<div class="col-md-6" id="10thPer"></div>
					</div>
					<div class="row">
						<label class="col-md-6 text-right">12% :</label>
						<div class="col-md-6" id="12thPer"></div>
					</div>
					<div class="row">
						<label class="col-md-6 text-right">Graduation : </label>
						<div class="col-md-6" id="grad"></div>
					</div>
					<div class="row">
						<label class="col-md-6 text-right">Post Graduate : </label>
						<div class="col-md-6" id="postGrad"></div>
					</div>

					<!-- <div class="row">
						<div class="col-md-12 text-center">
							<a href="#" class="btn btn-primary">View Profile</a>
						</div>
					</div> -->
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
				</div>
			</div>

		</div>
	</div>

	<div class="modal fade" id="Internship-popup" role="dialog">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Internship</h4>
				</div>
				<div class="modal-body">
					<div class="row">
						<h4 class="col-md-12 title">Summer</h4>
					</div>
					<hr>
					<div class="row">
						<label class="col-md-6">Company Name </label>
						<div class="col-md-6">
							<strong>Duration(month) </strong>
						</div>
					</div>
					<div class="row" id="summerInternData"></div>

					<hr>

					<div class="row">
						<label class="col-md-6">Total </label>
						<div class="col-md-6" id="summnerinterntotal"></div>
					</div>

					<div class="row">
						<h4 class="col-md-12 title">Other</h4>
					</div>
					<hr>
					<div class="row">
						<label class="col-md-6">Company Name </label>
						<div class="col-md-6">
							<strong>Duration(month) </strong>
						</div>
					</div>
					<div class="row" id="otherInternData"></div>
					<hr>
					<div class="row">
						<label class="col-md-6">Total </label>
						<div class="col-md-6" id="otherInternTotal"></div>
					</div>

					<div class="modal-footer">
						<button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
					</div>
				</div>

			</div>
		</div>
	</div>

	<div class="modal fade" id="experience-popup" role="dialog">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Experience</h4>
				</div>
				<div class="modal-body">
					<div class="row">
						<h4 class="col-md-12 title">Work</h4>
					</div>
					<hr>
					<div class="row">
						<label class="col-md-6">Company Name </label>
						<div class="col-md-6">
							<strong>Duration(month) </strong>
						</div>
					</div>
					<div class="row" id="experienceData"></div>

					<hr>

					<div class="row">
						<label class="col-md-6">Total </label>
						<div class="col-md-6" id="experiencetotal"></div>
					</div>
					<div class="modal-footer text-center">
						<button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div class="modal fade" id="cv-title-popup" role="dialog">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title" id="showemailId"></h4>
				</div>
				<div class="modal-body" id="CVData"></div>
				<div class="modal-footer text-center">
					<button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
				</div>
			</div>

		</div>
	</div>

	<jsp:include page="commonJsp/Footer.jsp" />

	<!-- get jQuery from the google apis
		<script type="text/javascript" src="js/jquery-1.12.4.js"></script>
		<script type="text/javascript" src="js/jquery.min.js"></script>
		<script type="text/javascript" src="js/jquery.dataTables.min.js"></script> -->
	<!-- <script type="text/javascript" src="js/jquery.min.js"></script> -->
	<script type="text/javascript" src="js/jquery.dataTables.min.js"></script>
	<script type="text/javascript" src="js/dataTables.buttons.min.js"></script>
	<script type="text/javascript" src="js/buttons.flash.min.js"></script>
	<script type="text/javascript" src="js/jszip.min.js"></script>
	<script type="text/javascript" src="js/pdfmake.min.js"></script>
	<script type="text/javascript" src="js/vfs_fonts.js"></script>
	<script type="text/javascript" src="js/buttons.html5.min.js"></script>
	<script type="text/javascript" src="js/buttons.print.min.js"></script>
	<!-- <script type="text/javascript" src="js/bootstrap.js"></script> -->
	
	<script>
			
			$(document).ready(function() {
				$('#verify-application').DataTable({
					dom : 'Bfrtip',
					lengthMenu: [
					              [ -1, 10, 25, 50, 100 ],
					              [ 'Show all' , '10 rows', '25 rows', '50 rows', '100 rows' ]
					          ],
					buttons : [ 'pageLength', 'excel', 'pdf', 'print' ]
				});
				
				$('#approve-application').DataTable({
					dom : 'Bfrtip',
					lengthMenu: [
					              [ -1, 10, 25, 50, 100 ],
					              [ 'Show all' , '10 rows', '25 rows', '50 rows', '100 rows' ]
					          ],
					buttons : [ 'pageLength', 'excel', 'pdf', 'print' ]
				});
	
				$('#remove-application').DataTable({
					dom : 'Bfrtip',
					lengthMenu: [
					              [ -1, 10, 25, 50, 100 ],
					              [ 'Show all' , '10 rows', '25 rows', '50 rows', '100 rows' ]
					          ],
					buttons : [ 'pageLength', 'excel', 'pdf', 'print' ]
				});
	
				$('#verified-application').DataTable({
					dom : 'Bfrtip',
					lengthMenu: [
					              [ -1, 10, 25, 50, 100 ],
					              [ 'Show all' , '10 rows', '25 rows', '50 rows', '100 rows' ]
					          ],
					buttons : [ 'pageLength', 'excel', 'pdf', 'print' ]
				});
				
				$('#shortlistRelease_application').DataTable({
					dom : 'Bfrtip',
					lengthMenu: [
					              [ -1, 10, 25, 50, 100 ],
					              [ 'Show all' , '10 rows', '25 rows', '50 rows', '100 rows' ]
					          ],
					buttons : [ 'pageLength', 'excel', 'pdf', 'print' ]
				});
				$('#shortlist_application').DataTable({
					dom : 'Bfrtip',
					lengthMenu: [
					              [ -1, 10, 25, 50, 100 ],
					              [ 'Show all' , '10 rows', '25 rows', '50 rows', '100 rows' ]
					          ],
					buttons : [ 'pageLength', 'excel', 'pdf', 'print' ]
				});
				$('#shortlistHR_application').DataTable({
					dom : 'Bfrtip',
					lengthMenu: [
					              [ -1, 10, 25, 50, 100 ],
					              [ 'Show all' , '10 rows', '25 rows', '50 rows', '100 rows' ]
					          ],
					buttons : [ 'pageLength', 'excel', 'pdf', 'print' ]
				});
	
			});
			
			
			$(function() {
				$('#saveVerifyId').click(function() {
					valArrDownload = [];
					 if ($("input[name=shortlisted]").is(":checked")) {
						 $("#save-verify-details").modal('show'); 
					 }else{
						alert("Please select at least one check box.");
						return false;
					}
				});
			});
			
			$(function () {
			    $("#considerlistbtn").click(function () {
			        $('.considerCheck').prop('checked', this.checked);
			    });

			    $(".considerCheck").click(function () {
			        if ($(".considerCheck").length == $(".case:checked").length) {
			            $("#considerlistbtn").prop("checked", "checked");
			        } else {
			            $("#considerlistbtn").removeProp("checked");
			        }
			    });
			  
			});
			
			$(function () {
			    $("#verfiylistbtn").click(function () {
			        $('.close1_Checkbox').prop('checked', this.checked);
			    });

			    $(".close1_Checkbox").click(function () {
			        if ($(".close1_Checkbox").length == $(".case:checked").length) {
			            $("#verfiylistbtn").prop("checked", "checked");
			        } else {
			            $("#verfiylistbtn").removeProp("checked");
			        }
			    });
			  
			});
			$(function () {
			    $("#shortlistbtn").click(function () {
			        $('.shortlisted').prop('checked', this.checked);
			    });

			    $(".shortlisted").click(function () {
			        if ($(".shortlisted").length == $(".case:checked").length) {
			            $("#shortlistbtn").prop("checked", "checked");
			        } else {
			            $("#shortlistbtn").removeProp("checked");
			        }
			    });
			  
			});
			function downloadCVsTab1(){
		    	  valArr = [];
		    	  var coverLetter = 0;
		      	
		      	if ($('#cvs1').is(":checked")){
		      		coverLetter = 1;
		      	}
		   	    if ($(".close1_Checkbox").length > 0){
		   			$('.close1_Checkbox:checked').each(function(i) {
		   			 var c=$(this).val();
		   				valArr[i] = c+"##0000";
		   				
		   			});
		   			if(valArr.length>0){
		   				downloadCV1(valArr, coverLetter);
		   				
		   			}else{
		   				alert("Please select at least one check box");
		   				return false;
		   			}
		   	   	}else{
		   			alert("No data available.");
		   			return false;
		   		}
		   }
			function downloadCVsTab2(){
		    	  valArr = [];
		    	  var coverLetter = 0;
		      	
		      	if ($('#cvs2').is(":checked")){
		      		coverLetter = 1;
		      	}
		   	    if ($(".approveCheckbox").length > 0){
		   			$('.approveCheckbox').each(function(i) {
		   			 var c=$(this).val();
		   				valArr[i] = c+"##0000";
		   			});
		   			
		   			if(valArr.length>0){
		   				downloadCV1(valArr, coverLetter);
		   			}else{
		   				alert("Please select at least one check box");
		   				return false;
		   			}
		   	   	}else{
		   			alert("No data available.");
		   			return false;
		   		}
		   }
			function downloadCVsTab3(){
		    	  valArr = [];
		    	  var coverLetter = 0;
		      	
		      	if ($('#cvs3').is(":checked")){
		      		coverLetter = 1;
		      	}
		   	    if ($(".removeCheckbox").length > 0){
		   			$('.removeCheckbox').each(function(i) {
		   				var c=$(this).val();
		   				valArr[i] = c+"##0000";
		   			});
		   			if(valArr.length>0){
		   				downloadCV1(valArr, coverLetter);
		   			}else{
		   				alert("Please select at least one check box");
		   				return false;
		   			}
		   	   	}else{
		   			alert("No data available.");
		   			return false;
		   		}
		   }
			function downloadCVsTab4(){
		    	  valArr = [];
		    	  var coverLetter = 0;
		      	
		      	if ($('#cvs4').is(":checked")){
		      		coverLetter = 1;
		      	}
		   	    if ($(".considerCheck").length > 0){
		   			$('.considerCheck:checked').each(function(i) {
		   				var c=$(this).val();
		   				valArr[i] = c+"##0000";
		   			});
		   			if(valArr.length>0){
		   				downloadCV1(valArr, coverLetter);
		   			}else{
		   				alert("Please select at least one check box");
		   				return false;
		   			}
		   	   	}else{
		   			alert("No data available.");
		   			return false;
		   		}
		   }
			function downloadCVsTab5(){
		    	  valArr = [];
		    	  var coverLetter = 0;
		      	
		      	if ($('#cvs5').is(":checked")){
		      		coverLetter = 1;
		      	}
		   	    if ($(".shortlisted").length > 0){
		   			$('.shortlisted:checked').each(function(i) {
		   				var c=$(this).val();
		   				valArr[i] = c+"##0000";
		   			});
		   			if(valArr.length>0){
		   				downloadCV1(valArr, coverLetter);
		   			}else{
		   				alert("Please select at least one check box");
		   				return false;
		   			}
		   	   	}else{
		   			alert("No data available.");
		   			return false;
		   		}
		   }
			function downloadCVsTab6(){
		    	  valArr = [];
		    	  var coverLetter = 0;
		      	
		      	if ($('#cvs6').is(":checked")){
		      		coverLetter = 1;
		      	}
		   	    if ($(".viewReleaseCheckbox").length > 0){
		   			$('.viewReleaseCheckbox').each(function(i) {
		   				var c=$(this).val();
		   				valArr[i] = c+"##0000";
		   			});
		   			if(valArr.length>0){
		   				downloadCV1(valArr, coverLetter);
		   			}else{
		   				alert("Please select at least one check box");
		   				return false;
		   			}
		   	   	}else{
		   			alert("No data available.");
		   			return false;
		   		}
		   }
			function downloadCVsTab9(){
		    	  valArr = [];
		    	  var coverLetter = 0;
		      	
		      	if ($('#cvs9').is(":checked")){
		      		coverLetter = 1;
		      	}
		   	    if ($(".shortlistedByHR").length > 0){
		   			$('.shortlistedByHR').each(function(i) {
		   				var c=$(this).val();
		   				valArr[i] = c+"##0000";
		   			});
		   			if(valArr.length>0){
		   				downloadCV1(valArr, coverLetter);
		   			}else{
		   				alert("Please select at least one check box");
		   				return false;
		   			}
		   	   	}else{
		   			alert("No data available.");
		   			return false;
		   		}
		   }
			function getValues(rollNumber) {
				var xhttp = new XMLHttpRequest();
				xhttp.onreadystatechange = function() {
					if (this.readyState == 4 && this.status == 200) {
						this.responseText;
						var i;
						var obj = JSON.parse(this.responseText);
						//obj.inboxSubject = eval("(" + obj.inboxSubject + ")");
						document.getElementById("closeEmailId").innerHTML = obj[0].emailId;
						document.getElementById("fullName").innerHTML = obj[0].fullname;
						document.getElementById("cvName").innerHTML = obj[0].cvName;
						document.getElementById("mentorName").innerHTML = obj[0].mentor;
						document.getElementById("gender").innerHTML = obj[0].gender;
						document.getElementById("10thPer").innerHTML = obj[0].percentage;
						document.getElementById("12thPer").innerHTML = obj[0].percenatageTwelve;
						document.getElementById("grad").innerHTML = obj[0].percentageGraduate;
						document.getElementById("postGrad").innerHTML = obj[0].postgraduatePercentage;
					}
				};
				var action = "getUserValues1?rollNumber=" + rollNumber;
				xhttp.open("POST", action, true);
				xhttp.send();
			}
	
			function getInternshipValues(rollNumber) {
				var xhttp = new XMLHttpRequest();
				xhttp.onreadystatechange = function() {
					if (this.readyState == 4 && this.status == 200) {
						this.responseText;
						var i;
						var obj = JSON.parse(this.responseText);
	
						/*  alert(JSON.stringify(obj));
						alert("length : "+obj.length);
						alert(obj[0].summerIntern.length); */
						var j;
						var internshipData = '';
						if (obj.length == 1) {
							for (i = 0; i < obj[0].summerIntern.length - 1; i++) {
								internshipData += "<div class='col-md-12'><div class='col-md-6'>"
										+ obj[0].summerIntern[i].companyname
										+ "</div><div class='col-md-6'>"
										+ obj[0].summerIntern[i].duration
										+ "</div></div>";
							}
							$("#summerInternData").html(internshipData);
							$("#summnerinterntotal")
									.html('<strong>'
										+ obj[0].summerIntern[obj[0].summerIntern.length - 1].duration
										+ '</strong>');
	
							internshipData = "";
							for (i = 0; i < obj[0].otherIntern.length - 1; i++) {
								internshipData += "<div class='col-md-12'><div class='col-md-6'>"
										+ obj[0].otherIntern[i].otherCompanyname
										+ "</div><div class='col-md-6'>"
										+ obj[0].otherIntern[i].otherDuration
										+ "</div></div>";
							}
	
							$("#otherInternData").html(internshipData);
							//alert("otherIntern   ::::: duration : "+obj[0].otherIntern[obj[0].otherIntern.length-1].otherDuration);
							$("#otherInternTotal").html('<strong>'
								+ obj[0].otherIntern[obj[0].otherIntern.length - 1].otherDuration + '</strong>');
						}
					}
				};
				var action = "getInternshipList1?rollNumber=" + rollNumber;
				xhttp.open("POST", action, true);
				xhttp.send();
			}
	
			$(function() {
				$('#insertList1').click(function() {
						var id1=document.getElementById("hrName").value;
						var id2=document.getElementById("emailId").value;
						$("#hrName1").html(id1);
					    $("#emailId1").html(id2);
						$("#list-link1-popup").modal('show'); 
				});
				
			});
			
			function getExperienceValues(rollNumber) {
				var xhttp = new XMLHttpRequest();
				xhttp.onreadystatechange = function() {
					if (this.readyState == 4 && this.status == 200) {
						this.responseText;
						var i;
						var obj = JSON.parse(this.responseText);
	
						// alert(JSON.stringify(obj));
						//  alert("length : "+obj.length);
	
						var j = obj.length - 1;
						var experienceData = '';
	
						for (i = 0; i < obj.length - 1; i++) {
							experienceData += "<div class='col-md-12'><div class='col-md-6'>"
									+ obj[i].companyname+ "</div><div class='col-md-6'>"
									+ obj[i].duration + "</div></div>";
						}
						$("#experienceData").html(experienceData);
						$("#experiencetotal").html(	'<strong>' + obj[j].duration + '</strong>');
						//  alert("total :: "+obj[0].summerIntern[obj[0].summerIntern.length-1].duration);  
	
					}
				};
				var action = "getExperienceList1?rollNumber=" + rollNumber;
				xhttp.open("POST", action, true);
				xhttp.send();
			}
	
			function getCVValues(rollNumber,emailId) {
				var xhttp = new XMLHttpRequest();
				xhttp.onreadystatechange = function() {
					if (this.readyState == 4 && this.status == 200) {
						this.responseText;
						var i;
						var obj = JSON.parse(this.responseText);
	
						// alert(JSON.stringify(obj));
						//  alert("length : "+obj.length);
	
						var j;
						var CVData = '';
						for (i = 0; i < obj.length; i++) {
							CVData += "<div class='row'><label class='col-md-6 text-right'>CV</label><div class='col-md-6'>"
									+ obj[i].title + "</div></div>";
							CVData += "<div class='row'><label class='col-md-6 text-right'>Cover Letter</label><div class='col-md-6'>"
									+ obj[i].fileName + "</div></div><br/>";
						}
						$("#showemailId").html(emailId);
						$("#CVData").html(CVData);
					}
				};
				var action = "getCVList1?rollNumber=" + rollNumber;
				xhttp.open("POST", action, true);
				xhttp.send();
			}
			
			function getvalueindb()	{
				document.getElementById("insertShortList1").action = "shortList1";
				document.getElementById("insertShortList1").method = "post";
				document.getElementById("insertShortList1").submit();
			}
	
			function getInfoValues() {
				var xhttp = new XMLHttpRequest();
				xhttp.onreadystatechange = function() {
					if (this.readyState == 4 && this.status == 200) {
						this.responseText;
						var i;
						var obj = JSON.parse(this.responseText);
						document.getElementById("cluster").innerHTML = obj[0].clusterName;
						document.getElementById("cohort").innerHTML = obj[0].cohortName;
						document.getElementById("compensation").innerHTML = obj[0].compensation;
						document.getElementById("hires").innerHTML = obj[0].hires;
	
						document.getElementById("noOfApp").innerHTML = obj[0].noOfApp;
						document.getElementById("rollOver").innerHTML = obj[0].rollOver;
						document.getElementById("verified").innerHTML = obj[0].verified;
						document.getElementById("unVerified").innerHTML = obj[0].unVerified;
					}
				};
				var action = "getInfoValues1";
				xhttp.open("POST", action, true);
				xhttp.send();
			}
	
			var valArr = [];
			$(function() {
				$('#verifyBtn').click(function() {
					valArr = [];
					$('.close1_Checkbox:checked').each(function(i) {
						valArr[i] = $(this).val();
					});
					//alert(valArr);
					if(valArr.length>0){
						updateFlag(valArr);
					}else{
						alert("Please select atleast one records");
						return false;
					}
				});
			});
	
			function updateFlag(valArr) {
				//alert("value :: " + valArr.toString());
				$("#updateApplyId").val(valArr.toString());
				document.getElementById("updateFlag").action = "updateFlag1";
				document.getElementById("updateFlag").method = "post";
				document.getElementById("updateFlag").submit();
			}
			
			
			$(function() {
				$('#downloadpdfbind').click(function() {
					valArrDownload = [];
					 if ($(".considerCheck").is(":checked")) {
						 var i = 0;
						 $('.considerCheck:checked').each(function(i) {
							valArrDownload[i] = $(this).val();
							i++;
						});
						pdfBind(valArrDownload);
					 }else{
						alert("Please select at least one check box.");
						return false;
					}
				});
			});
			
			 
			$(function() {
				$('#verifypdfbind').click(function() {
					valArrDownload = [];
					 if ($(".close1_Checkbox").is(":checked")) {
						 var i = 0;
						 $('.close1_Checkbox:checked').each(function(i) {
							valArrDownload[i] = $(this).val();
							i++;
						});
						pdfBind(valArrDownload);
					 }else{
						alert("Please select at least one check box.");
						return false;
					}
				});
			});
			

			$(function() {
				$('#approvepdfbind').click(function() {
					valArrDownload = [];
					
					if ($(".approveCheckbox").length > 0){
						 var i = 0;
						$(".approveCheckbox").each(function() {
							valArrDownload[i] = $(this).val();
							i++;
						});
					}
					if (valArrDownload!=null && valArrDownload.length>0) {
						pdfBind(valArrDownload);
					}else{
						alert("No data available.");
						return false;
					}
					 
				});
			});
			
	
			$(function() {
				$('#removepdfbind').click(function() {
					valArrDownload = [];
					if ($(".removeCheckbox").length > 0){
						 var i = 0;
						$(".removeCheckbox").each(function() {
							valArrDownload[i] = $(this).val();
							i++;
						});
					}
					//alert("valArrDownload : "+valArrDownload+" length :: "+valArrDownload.length+" i::"+i);
					if (valArrDownload!=null && valArrDownload.length>0) {
						pdfBind(valArrDownload);
					}else{
						alert("No data available.");
						return false;
					}
					
				});
			});
			$(function() {
				$('#downloadPdfBindViewRelease').click(function() {
					valArrDownload = [];
					if ($(".viewReleaseCheckbox").length > 0){
						 var i = 0;
						$(".viewReleaseCheckbox").each(function() {
							valArrDownload[i] = $(this).val();
							i++;
						});
					}
					if (valArrDownload!=null && valArrDownload.length>0) {
						pdfBind(valArrDownload);
					}else{
						alert("No data available.");
						return false;
					}
					 
				});
			});
			$(function() {
				$('#shortlistpdfbind').click(function() {
					valArrDownload = [];
					 if ($("input[name=shortlisted]").is(":checked")) {
						 var i = 0;
						 $('input[name=shortlisted]:checked').each(function(i) {
							 valArrDownload[i] = $(this).val();
								i++;
						});
						pdfBind(valArrDownload);
					 }else{
						alert("Please select at least one check box.");
						return false;
					}
				});
			}); 
			
			$(function() {
				$('#downloadPdfBindShortlistHR').click(function() {
					valArrDownload = [];
					
					if ($(".shortlistedByHR").length > 0){
						 var i = 0;
						$(".shortlistedByHR").each(function() {
							valArrDownload[i] = $(this).val();
							i++;
						});
					}
					if (valArrDownload!=null && valArrDownload.length>0) {
						pdfBind(valArrDownload);
					}else{
						alert("No data available.");
						return false;
					}
					 
				});
			});
			
			function pdfBind(valArrDownload) {
				//alert("value :: " + valArrDownload.toString());
				$("#bindId").val();
				$("#bindId").val(valArrDownload.toString());
				document.getElementById("pdfBind").action = "pdfmerge1";
				document.getElementById("pdfBind").method = "post";
				document.getElementById("pdfBind").submit();
			}
			
			
			 function downloadCV1(valArrDownload,coverLetter) {
					$("#downloadCvId").val('');
					$("#downloadCvId").val(valArrDownload.toString());
					$("#coverLetter").val(coverLetter);
					document.getElementById("downloadCV").action = "downloadRMCv";
					document.getElementById("downloadCV").method = "post";
					document.getElementById("downloadCV").submit();
				}
			var valArrShortlist = [];
			$(function() {
				$('#shortListBtn').click(function() {
					valArrShortlist = [];
					$("#updateApplyId").val();
					/*  
					 if ($(".considerCheck").is(":checked")) {
						$('.considerCheck:checked').each(function(i) {
							valArrShortlist[i] = $(this).val();
						});
						$("#updateApplyIds").val(valArrShortlist);
						 
						 $("#shortlist-link-popup").modal('show'); 
					 }else{
						alert("Please select at least one check box.");
					}  */
					var i = 0;
					$(".considerCheck").each(function() {
						valArrShortlist[i] = $(this).val();
						i++;
					});
					if (valArrShortlist!=null && valArrShortlist.length>0) {
						$("#updateApplyIds").val(valArrShortlist.toString());
						$("#shortlist-link-popup").modal('show'); 
					}else{
						alert("No data available.");
						return false;
					}
				});
				
			});
			
			
			 function saveShortlisted(){
				  var x = document.getElementsByName("shortlisted");
				  var notShorted = document.getElementsByName("notShortlisted");
				    var i,j;
				    var txt=[];
				    var notShortelisted=[];
				    for (i = 0; i < x.length; i++) {	      
				        if (x[i].checked) {	        	
				            if(txt==''){	            	
				            	 txt[txt.length]=x[i].value;	            	 
				            }else{	            	
				            	 txt[txt.length]=x[i].value;
				            }	            
				        }
				        if (x[i].checked==false) {	        	
				            if(notShortelisted==''){	            	
				            	notShortelisted[notShortelisted.length]=x[i].value;	            	 
				            }else{	            	
				            	notShortelisted[notShortelisted.length]=x[i].value;
				            }	            
				        }	
				   }
				
				  var shortlisted= document.getElementById("shortListIdsSelected");
				  shortlisted.value=txt; 
				  var notshortlisted= document.getElementById("notShortListedIds");
				  notshortlisted.value=notShortelisted; 
				  document.getElementById("shortlistformId").action = "saveShortlisted1";
				  document.getElementById("shortlistformId").method = "post";
				  document.getElementById("shortlistformId").submit();  
				 
			   }
			
			   function saveReleaseHRShortlistedByRM(){				  
				  document.getElementById("releaseShortlistformId").action = "releaseHRShortlisted1";
				  document.getElementById("releaseShortlistformId").method = "post";
				  document.getElementById("releaseShortlistformId").submit();  
				 
			   }
			
			 function getGreetings(cmpRoleId) {
				   
				  // alert("inside method :: ")
				   var xhttp = new XMLHttpRequest();
					xhttp.onreadystatechange = function() {
						if (this.readyState == 4 && this.status == 200) {
							var i;
							var obj = JSON.parse(this.responseText);
							var greetingsData = '';

							for (i = 0; i < obj.length; i++) {
								greetingsData += "Congratulations! you have been Shortlisted for GD in "+
								 obj[i].CompanyName+ " for "+obj[i].roleName+" in "+ obj[i].ProcessName+" " + obj[i].year;
										 
							}
							$("#greetingsData").html(greetingsData);
							$("#save-shortlist-release-rm").modal('show');
						}
					};
					var action = "getGreetings?cmpRoleId=" + cmpRoleId;
					xhttp.open("POST", action, true);
					xhttp.send();
				}
			 
			 function getFinalGreetings(cmpRoleId) {				   
				 
				   var xhttp = new XMLHttpRequest();
					xhttp.onreadystatechange = function() {
						if (this.readyState == 4 && this.status == 200) {							
							var i;
							var obj = JSON.parse(this.responseText);
							var greetingsData = '';
							for (i = 0; i < obj.length; i++) {								
								greetingsData += "Congratulations! You have been Shortlisted for GD by "+
									obj[i].CompanyName+ " for "+obj[i].roleName+" in "+ obj[i].ProcessName+" " + obj[i].year;
							}
							
							$("#finalGreetingsData").html(greetingsData);							
						}
					};
					var action = "getGreetings?cmpRoleId=" + cmpRoleId;
					xhttp.open("POST", action, true);
					xhttp.send();
				}
			 
			 function getGreetingsHotList(cmpRoleId) {
				   
				   var xhttp = new XMLHttpRequest();
					xhttp.onreadystatechange = function() {
						if (this.readyState == 4 && this.status == 200) {
							var i;
							var obj = JSON.parse(this.responseText);
							var greetingsData = '';
							for (i = 0; i < obj.length; i++) {
								greetingsData += "Congratulation you have been Hotlisted by "+
									 obj[i].CompanyName+ " for "+obj[i].roleName+" in "+ obj[i].ProcessName+" " + obj[i].year;
										 
							}
							$("#greetingsDataHot").html(greetingsData);
						}
					};
					var action = "getGreetings1?cmpRoleId=" + cmpRoleId;
					xhttp.open("POST", action, true);
					xhttp.send();
				}
			 
			 function getUserDetailsByRolNo(rollNo){
				   var xhttp = new XMLHttpRequest();
					xhttp.onreadystatechange = function() {
						if (this.readyState == 4 && this.status == 200) {
							this.responseText;
							var i;
							var obj = JSON.parse(this.responseText);
							document.getElementById("closeEmailId").innerHTML = obj[0].emailId;
							document.getElementById("fullName").innerHTML = obj[0].fullname;
							document.getElementById("cvName").innerHTML = obj[0].cvName;
							document.getElementById("mentorName").innerHTML = obj[0].mentor;
							document.getElementById("gender").innerHTML = obj[0].gender;
							document.getElementById("10thPer").innerHTML = obj[0].percentage;
							document.getElementById("12thPer").innerHTML = obj[0].percenatageTwelve;
							document.getElementById("grad").innerHTML = obj[0].percentageGraduate;
							document.getElementById("postGrad").innerHTML = obj[0].postgraduatePercentage;
						}
					};
					var action = "getUserDetailsByRolNo1?rollNumber=" + rollNo;
					xhttp.open("POST", action, true);
					xhttp.send();
			}
			 function saveShortlistReleaseByRM(){
				    document.getElementById("shortlistedReleaseFormId").action = "saveReleaseShortlisted1";
					document.getElementById("shortlistedReleaseFormId").method = "post";
					document.getElementById("shortlistedReleaseFormId").submit(); 
					   
				}
				 
				function saveSendMail(){
					 document.getElementById("shortlistedReleaseFormId").action = "saveSendMail1";
					 document.getElementById("shortlistedReleaseFormId").method = "post";
					 document.getElementById("shortlistedReleaseFormId").submit(); 
				}
				
				function saveHotlist(){					

					  var x = document.getElementsByName("hotlisted");	 
					  alert(x);
					    var i,j;
					    var txt=[];
					    var notShortelisted=[];
					    for (i = 0; i < x.length; i++) {	      
					        if (x[i].checked) {	        	
					            if(txt==''){	            	
					            	 txt[txt.length]=x[i].value;	            	 
					            }else{	            	
					            	 txt[txt.length]=x[i].value;
					            }
					        }
					        if (x[i].checked==false) {	        	
					            if(notShortelisted==''){	            	
					            	notShortelisted[notShortelisted.length]=x[i].value;	            	 
					            }else{	            	
					            	notShortelisted[notShortelisted.length]=x[i].value;
					            }	            
					        }	
					   }
					   
					  var shortlisted= document.getElementById("hotListIdsSelected");
					 
					  shortlisted.value=txt; 	
					  var notshortlisted= document.getElementById("notShortListedIds");
					  notshortlisted.value=notShortelisted; 
					  document.getElementById("hotlistformId").action = "saveHotlisted1";
					  document.getElementById("hotlistformId").method = "post";
					  document.getElementById("hotlistformId").submit(); 
					  
				   }

				function saveProcessDone(){
			    	document.getElementById("hotListReleaseFormId").action = "processDoneHotlisted1";
					document.getElementById("hotListReleaseFormId").method = "post";
					document.getElementById("hotListReleaseFormId").submit();  
			   }
				
				function shortlistRemove(){
					 var x = document.getElementsByName("shortlisted");
					 var notShorted = document.getElementsByName("notShortlisted");
					 if ($("input[name=shortlisted]").is(":checked")) {
					    var i,j;
					    var txt=[];
					    var notShortelisted=[];
					    for (i = 0; i < x.length; i++) {	      
					        if (x[i].checked) {	        	
					            if(txt==''){	            	
					            	 txt[txt.length]=x[i].value;	            	 
					            }else{	            	
					            	 txt[txt.length]=x[i].value;
					            }	            
					        }
					        if (x[i].checked==false) {	        	
					            if(notShortelisted==''){	            	
					            	notShortelisted[notShortelisted.length]=x[i].value;	            	 
					            }else{	            	
					            	notShortelisted[notShortelisted.length]=x[i].value;
					            }	            
					        }	
					   	}
					    
					    if (txt!=null && txt.length>0) {
					    	var shortlisted= document.getElementById("shortListIdsSelected");
							  shortlisted.value=txt; 
							  var notshortlisted= document.getElementById("notShortListedIds");
							  notshortlisted.value=notShortelisted; 
							  document.getElementById("shortlistformId").action = "shortlistRemove1";
							  document.getElementById("shortlistformId").method = "post";
							  document.getElementById("shortlistformId").submit();
						}else{
							alert("No data available.");
							return false;
						}
				    }else{
						alert("Please select at least one check box.");
						return false;
					}
				 }
				
				 <c:set var="flag" value="${releaseFlag}"/>  
						function checkFlagStatus(roleId){
			               var id="${flag}";
			               if(id==0){
			            	   $("#errorMessageModal").modal('show'); 
			               }
			               else{
			   				getGreetings(roleId);
			               }
			            	   
						} 
		</script>

</body>
</html>