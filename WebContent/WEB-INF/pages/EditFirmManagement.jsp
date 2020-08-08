<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isELIgnored="false"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Indian Institute of Management Ahmedabad (IIMA) | Home - IIMA</title>
    <link rel="icon" href="images/favicon.ico" type="image/x-icon"/>
<link rel="stylesheet" href="css/jquery-ui.css">
<link rel="stylesheet"
	href="assets/css/bootstrap-datetimepicker.min.css" />
<style>
#firmManagement {
	width: 100%;
	float: left;
}

.role .rolemapping {
	display: none;
}
</style>
</head>
<body>

	<jsp:include page="commonJsp/Header.jsp" />
	<div class="space"></div>
	<div class="container">
		<div class="col-sm-12">
			<!-- <input type="button" class="btn btn-primary pull-right" onclick="clearCompanyForm();"
				data-toggle="modal" data-target="#C-role-edit" value="Add Company"> -->
		</div>
	</div>
	<div class="space"></div>
	<%-- <form id="firmManagement" action="updateFirmManagement" method="post"
		name="FirmManagementForm" enctype="multipart/form-data">--%>
 	<form id="firmManagement"  method="post"
		name="FirmManagementForm" enctype="multipart/form-data">

		<div class="container">
			<div class="main_box section_one panel panel-primary">
				<div class="panel-heading">Firm Management</div>
				<div class="panel-body">
					<input type="hidden" value="${firmMng.applicationId}" name="applicationId"/>
					
					<div class="row">
						<div class="  border">
							<div class="col-sm-3 ui-widget">
								<p>Name of the Firm *</p>
							</div>

							<div class="col-sm-3 inpur_box">
								<div id="error" align="center"></div>

								<input id="compId" class="form-control" name="companyName"
									placeholder="Name of the Firm " required
									value="${firmMng.companyName}" />


								<%-- <select class="form-control" name="companyId" required>
								<option>select</option>
								<c:forEach items="${companyList}" var="cluster">
									<option value="${cluster.companyId}">${cluster.companyName}</option>
								</c:forEach>
							</select> --%>

							</div>

							<!-- <div class="col-sm-3 inpur_box">
							<input type="text" class="form-control" name="firmName"
								placeholder="Firm Name" required>
						</div> -->


							<div class="col-sm-3 inpur_box">
								<div id="error" align="center"></div>
								<!-- <div class="ui-widget">
                             <input id="procId" class="form-control" name="processName" 
                             placeholder="Process "  />
							</div> -->


								<select class="form-control" name="processId" required>
									<option value="">Select</option>
									<c:forEach items="${processList}" var="process">
										<option value="${process.processId}"
											${process.processId  == firmMng.processId ? 'selected' : ' '}>${process.processName}</option>
									</c:forEach>
								</select>
							</div>

							<div class="col-sm-3 inpur_box">
								<div id="error" align="center"></div>
								<!-- <div class="ui-widget">
                             <input id="yearId" class="form-control" name="year" placeholder="year " />
							</div> -->
							</div>

							<div class="col-sm-3">
								<select class="form-control" name="year" id="selectYear">
									<option value="2018">2018</option>
									<option value="2017">2017</option>
									<option value="2016">2016</option>
								</select>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="  border">

							<div class="col-sm-3 ui-widget">
								<p>Cluster *</p>
							</div>

							<div class="col-sm-3 inpur_box">


								<!-- 	<input id="clustId" class="form-control" name="clusterName"
								placeholder="Cluster" onchange="getCohortNameByCName(this);"
									required /> 
						
						
							<div class="col-sm-3">
								<p>Cluster *</p>
							</div>-->
								<select class="form-control" id="clusterId" name="clusterName"
									onchange="getCohortValues(this);">
									<!-- <option value="">Select</option> -->
									<c:forEach items="${clusterList}" var="cluster"
										varStatus="loop">
										<option value="${cluster.clusterId}"
											${cluster.clusterId  == firmMng.clusterId ? 'selected' : ' '}>${cluster.clusterName}</option>
									</c:forEach>
								</select>


							</div>
						</div>
					</div>
					<div class="row">
						<div class="  border">
							<div class="col-sm-3 ui-widget">
								<p>Cohort *</p>
							</div>

							<div class="col-sm-3 inpur_box">

								
								<select class="form-control" id="cohortId" name="cohortName">
									<option value="${firmMng.cohortId}" selected="selected">${firmMng.cohortName}</option>
								</select>
								<%-- <div class="col-sm-3">
								<p>Cohort</p>
							</div>
							<div class="col-sm-12 inpur_box">
								<select class="form-control" name="cohortId" id="cohortId" required>
									<option value="">Select</option>
									<c:forEach items="${cohortList}" var="cohort">
									<option value="${cohort.cohortId}">${cohort.cohortName}</option>
								</c:forEach>
								</select>
							</div> --%>

							</div>
						</div>
					</div>

					<div id="internshipsbox1">
						<c:if test="${empty firmMng.url}">
							<div class="row">
								<input type="hidden" value="0" name="urlId"/>
								<div class="  border">
									<div class="col-sm-3">
										<p>Add URL</p>
									</div>
									<div class="col-sm-3 inpur_box">
										<input type="text" class="form-control" name="url"
											placeholder="URL">
									</div>
									<div class="col-sm-3 text-center">
										<input type="text" class="form-control" name="urlDescription"
											placeholder="Url Description">
									</div>
								</div>
							</div>
						</c:if>
					
						<c:if test="${not empty firmMng.url}">
							<c:forEach items="${firmMng.url}" var="url" varStatus="status">
								<div class="row">
								
									<div class="border" id="urlDiv${firmMng.urlId[status.index]}">
										<input type="hidden" value="${firmMng.urlId[status.index]}" name="urlId"/>
										<div class="col-sm-3">
											<p>Add URL</p>
										</div>
										<div class="col-sm-3 inpur_box">
											<input type="text" class="form-control" name="url"
												placeholder="URL" value="${url}">
										</div>
										<div class="col-sm-3 text-center">
											<input type="text" class="form-control" name="urlDescription"
												placeholder="Url Description"
												value="${firmMng.urlDescription[status.index]}">
										</div>
										<a href="#" onclick="deleteUrl(${firmMng.urlId[status.index]},${firmMng.applicationId})" style="float:right;margin-right: 20px;">Delete</a>
									</div>
								</div>
							</c:forEach>
						</c:if>
					</div>


					<div id="internships"></div>
					<div class="col-sm-offset-9 col-sm-3 text-center">
						<!-- <button class="btn btn-primary" id="add">
							<i class="fa fa-plus" aria-hidden="true"></i>
						</button> -->
						<a href="javascript:void(0)" class="btn btn-primary" id="add"><i class="fa fa-plus" aria-hidden="true"></i></a> 
						<a href="javascript:void(0)" class="btn btn-primary" id="remove"><i class="fa fa-minus" aria-hidden="true"></i></a> 
					<!-- <a href="javascript:void(0)" class="btn btn-primary"
					id="remove-panel">Remove Role</a> -->
						<!-- <button class="btn btn-primary" id="remove">
							<i class="fa fa-minus" aria-hidden="true"></i>
						</button> -->
					</div>
					<div class="row">
						<div class="  border">
							<div class="col-sm-3">
								<p>Preference</p>
							</div>
							<c:choose>
								<c:when test="${firmMng.preferenceServe==true}">
								<div class="col-sm-3">
									<input type="checkbox" name="preferenceServe"
									placeholder="Preference" id="preferenceServe" checked> 
									</div>
									</c:when>
								<c:otherwise>
								<div class="col-sm-3">
								<input type="checkbox" name="preferenceServe"
									placeholder="Preference" id="preferenceServe"> 
									</div>
									</c:otherwise>
								</c:choose>
							<!-- <div class="col-sm-3">
								<input type="checkbox" name="preferenceServe"
									placeholder="Preference" id="preferenceServe">
							</div> -->
                                
                           <div class="col-sm-3">
								<p>HR Hotlist</p>
							</div>
							<div class="col-sm-3">
							<c:if test="${firmMng.hrHotlist==true}">
								<input type="checkbox" name="hrHotlist" checked>
							</c:if>
							<c:if test="${firmMng.hrHotlist==false}">
								<input type="checkbox" name="hrHotlist">
							</c:if>
							</div>
						</div>
					</div>
					<!-- <div class="row">
						<div class="  border">
							<div class="col-sm-3">
								<p>Multiple CV Relates</p>
							</div>
							<div class="col-sm-3">
								<input type="checkbox" name="multCVRelates"
									placeholder="Multiple CV Relates" id="multiCvRelates">
							</div>

						</div>
					</div> -->

				 <div class="row">
						<div class="  border">
							<div class="col-sm-3">
								<p>Number of Roles *</p>
							</div>
							<div class="col-sm-3 inpur_box">
								<input type="text" class="form-control" name="limitCVNo"
									placeholder="Number of Roles" value="${firmMng.noOfRole}" required>
							</div>
						</div>
					</div>

					<div class="row">
						<div class="border">
							<div class="col-sm-3">
								<p>No.Of Selection Round *</p>
							</div>
							<!-- <div class="col-sm-3">
								<select class="form-control" name="noOfRound">
									<option value="1">1</option>
									<option value="2">2</option>

								</select>
							</div> -->

							<div class="col-sm-3 inpur_box">
								<div id="error" align="center"></div>
								<div class="ui-widget">
									<input id="noOfRoundId" class="form-control" name="noOfRound"
										placeholder="Selection Round " required="required"
										value="${firmMng.noOfSelectedRounds}" />
								</div>
							</div>
						</div>
					</div>

					<div class="row">
						<div class="  border">
							<div class="col-sm-3">
								<p>Opening Date & Time *</p>
							</div>
							<div class="col-sm-3 inpur_box">
								<div class="input-group">
									<input id="date-timepicker1"
										class="form-control input-group date" type="text"
										name="openingDate" required="required"
										value="${firmMng.openingDate}"> <span
										class="input-group-addon"> <i
										class="fa fa-clock-o bigger-110"></i>
									</span>
								</div>
							</div>
							<!-- <div class="col-sm-3 inpur_box">
							<div class="input-group">
								<input class="form-control date-picker"
									data-date-format="dd-MM-yyyy" type="text" name="openingDate" > <span
									class="input-group-addon"> <i
									class="fa fa-calendar bigger-110"></i>
								</span>
							</div>

						</div> -->

						</div>
					</div>
					<div class="row">
						<div class="  border">
							<div class="col-sm-3">
								<p>Closing Date & Time *</p>
							</div>
							<div class="col-sm-3 inpur_box">
								<!-- <input type="text" class="form-control date-picker" 
							name="closeingDate" required> -->

								<div class="input-group">
									<input id="closeingDate" class="form-control input-group date"
										type="text" name="closeingDate" required="required"
										value="${firmMng.closeingDate}"> <span
										class="input-group-addon"> <i
										class="fa fa-clock-o bigger-110"></i>
									</span>
								</div>


								<!-- <div class="input-group">
								<input class="form-control date-picker"
									data-date-format="dd-MM-yyyy" type="text" name="closeingDate" > <span
									class="input-group-addon"> <i
									class="fa fa-calendar bigger-110"></i>
								</span>
							</div> -->
							</div>
						</div>
					</div>
					<div class="row">
						<div class="  border">
							<div class="col-sm-3">
								<p>Additional Text</p>
							</div>
							<div class="col-sm-3 inpur_box">
								<textarea rows="4" cols="50" name="additionalTextArea"
									placeholder="Extra Details(if any) that need to be posted">${firmMng.additionalTextArea} </textarea>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="  border">
							<div class="col-sm-3">
								<p>Interview Experience URL</p>
							</div>
							<div class="col-sm-3 inpur_box">
								<input type="text" class="form-control"
									name="interviewExperience"
									placeholder="Interview Experience URL"
									value="${firmMng.interviewExperience}">
							</div>
						</div>
					</div>
					<div class="row">
						<div class="  border">
							<div class="col-sm-3">
								<p>InternShip Experience URL</p>
							</div>
							<div class="col-sm-3 inpur_box">
								<input type="text" class="form-control"
									name="internshipExperience"
									placeholder="Internship Experience URL"
									value="${firmMng.internshipExperience}">
							</div>
						</div>
					</div>
					<div class="row">
						<div class="  border">
							<div class="col-sm-3 tsize">Job Description</div>
							<div class="col-sm-3 tsize">
								<input id="id-input-file-2" type="file" name="jobDescription">
							</div>
							<a href="#">${firmMng.jobDecPath}</a>
						</div>
					</div>
					<div id="additionalbox">
						<c:if test="${empty firmMng.additionalFilePathList}">
							<div class="row">
							<input type="hidden" value="0" name="additionalFileId"/>
								<div class="border">
									<div class="col-sm-3 tsize">Additional Files</div>
									<div class="col-sm-3 tsize">
										<input id="id-input-file-2" type="file" name="additionalFile">
									</div>
								</div>
							</div>
						</c:if>
						<c:if test="${not empty firmMng.additionalFilePathList}">
							<c:forEach items="${firmMng.additionalFilePathList}"
								var="fileName" varStatus="status">
								<div class="row">
									<input type="hidden" value="${firmMng.additionalFileId[status.index]}" name="additionalFileId"/>
									<div class="border" id="addFileDiv${firmMng.additionalFileId[status.index]}">
										<div class="col-sm-3 tsize">Additional Files</div>
										<div class="col-sm-3 tsize">
											<input id="id-input-file-2" type="file" name="additionalFile">
										</div>
										<a href="#">${fileName}</a>
										<a href="#" onclick="deleteAdditionalFile(${firmMng.additionalFileId[status.index]},${firmMng.applicationId})" style="float:right;margin-right: 20px;">Delete</a>
									</div>
								</div>
							</c:forEach>
						</c:if>
					</div>

					<div id="additional"></div>
					<div class="col-md-offset-6 col-md-12 col-sm-12">
						<!-- <button class="btn btn-primary" id="add-additional">
							<i class="fa fa-plus" aria-hidden="true"></i>
						</button>
						<button class="btn btn-primary" id="remove-additional">
							<i class="fa fa-minus" aria-hidden="true"></i>
						</button> -->
						<a href="javascript:void(0)" class="btn btn-primary" id="add-additional"><i class="fa fa-plus" aria-hidden="true"></i></a> 
						<a href="javascript:void(0)" class="btn btn-primary" id="remove-additional"><i class="fa fa-minus" aria-hidden="true"></i></a> 
						<!-- <input type="button" value="+" class="btn btn-primary"
							id="add-additional" /> <input type="button" value="-"
							class="btn btn-primary" id="remove-additional" /> -->
					</div>
				</div>
			</div>
			
			<c:if test="${empty firmMng.roleId}">
				<div class="main_box section_one panel panel-primary">
					<div class="panel-heading">Role</div>
					<div id="internshipbox2">
						<div class="panel-body">
							<div class="row">
								<div class="  border">
									<div class="col-sm-3 ui-widget">
										<p>Role *</p>
									</div>
									<div class="col-sm-3 inpur_box">
										<!-- <input  id="roleId"  class="form-control" name="roleCompany" required/>  -->
									<input type="hidden" value="0" name="roleId"/>
										<select class="form-control" name="roleCompanyId" required>
											<option value="">select</option>
											<c:forEach items="${roleList}" var="role">
												<option value="${role.roleCompanyId}">${role.roleCompany}</option>
											</c:forEach>
										</select>
									</div>

									<!-- <div class="col-sm-3 ui-widget">
								<label for="tags">Role *</label>
							</div>

							<div class="col-sm-3 inpur_box">
								<div id="error" align="center"></div>

								<input id="roleId" class="form-control" name="roleCompany"
									required />
								
							</div> -->
								</div>
							</div>
							<div class="row">
								<div class="  border">
									<div class="col-sm-3">
										<p>Cover Letter</p>
									</div>
									<div class="col-sm-3 inpur_box">
										<input type="checkbox" name="coverLetter"
											placeholder="Cover Letter" checked="checked">
									</div>
								</div>
							</div>

							<div class="row">
								<div class="  border">
									<div class="col-sm-3">
										<p>Minimum Work Exp Requirement</p>
									</div>
									<div class="col-sm-3 inpur_box">
										<input type="text" class="form-control" name="workExp"
											placeholder="Work Exp Requirement">
									</div>
									Only Months
								</div>
							</div>

							<div class="row">
								<div class="  border">
									<div class="col-sm-3">
										<p>Maximum Work Exp Requirement</p>
									</div>
									<div class="col-sm-3 inpur_box">
										<input type="text" class="form-control" name="workExpMax"
											placeholder="Work Exp Requirement">
									</div>
									Only Months
								</div>
							</div>


							<div class="row">
								<div class="  border">
									<div class="col-sm-3">
										<p>Compensation(CTC)</p>
									</div>
									<div class="col-sm-3 inpur_box">
										<input type="text" class="form-control" name="compensation"
											placeholder="Compensation">
									</div>
								</div>
							</div>
							<div class="row">
								<div class="border">
									<div class="col-sm-3">
										<p>Minimum Expected No. Offers</p>
									</div>
									<div class="col-sm-3 inpur_box">
										<input type="number" class="form-control" name="noHire"
											placeholder="Minimum Expected No. Of Offers" >
									</div>
								</div>
							</div>
							<div class="row">
								<div class="border">
									<div class="col-sm-3">
										<p>Maximum Expected No. Offers</p>
									</div>
									<div class="col-sm-3 inpur_box">
										<input type="number" class="form-control" name="maxNoHire"
											placeholder="Maximum Expected No. Of Offers" >
									</div>
								</div>
							</div>
							<div class="row">
								<div class="border">
									<div class="col-sm-3">
										<p>Average No. Of Offers</p>
									</div>
									<div class="col-sm-3 inpur_box">
										<input type="number" class="form-control" name="avgNoHire"
											placeholder="Average Expected No. Of Offers" >
									</div>
								</div>
							</div>
							<!-- <div class="row">
								<div class="  border">
									<div class="col-sm-3">
										<p>Expected No. Of Hires</p>
									</div>
									<div class="col-sm-3 inpur_box">
										<input type="text" class="form-control" name="noHire"
											placeholder="Expected No. Of Hire">
									</div>
								</div>
							</div> -->
						</div>
					</div>
					</div>
				</c:if>
				<c:if test="${not empty firmMng.roleId}">
					<c:forEach items="${firmMng.roleId}" var="roleval" varStatus="status">
					<c:if test="${status.index+1==1}">
					<div class="main_box section_one panel panel-primary role" id="roleCount${firmMng.roleId[status.index]}">
					 </c:if>
					
					<c:if test="${status.index+1>1}">
					<div class="main_box section_one panel panel-primary" id="roleCount${firmMng.roleId[status.index]}">
					 </c:if>
						<div class="panel-heading" id="roleHeader${firmMng.roleId[status.index]}">Role ${status.index+1}  <a class="btn btn-sm btn-danger" href="#" onclick="deleteRole(${firmMng.roleId[status.index]},${firmMng.applicationId})" style="float:right;color: white;">Delete</a></div>
						<div id="internshipbox${status.count}" >
							<div class="panel-body" id="roleDiv${firmMng.roleId[status.index]}">
								<div class="row">
									<div class="  border">
										<div class="col-sm-3 ui-widget">
											<p>Role *</p>
										</div>
										<div class="col-sm-3 inpur_box">
										<input type="hidden" value="${firmMng.roleId[status.index]}" name="roleId"/>
											<!-- <input  id="roleId"  class="form-control" name="roleCompany" required/>  -->
											<select class="form-control" name="roleCompanyId" required>
												<c:forEach items="${roleList}" var="role" varStatus="loop">
												<%-- 	<option value="${role.roleCompanyId}">${role.roleCompany}</option> --%>
													<option value="${role.roleCompanyId}"
														${role.roleCompanyId  == firmMng.desiationId[status.index] ? 'selected' : ' '}>${role.roleCompany}
													</option>
												</c:forEach>
											</select>
										</div>
									<c:if test="${status.index+1>1}">
										<div class="col-sm-3 ui-widget">
											<p>Role CV Mapping 	</p>
										</div>
										 <div class="col-sm-3">
						            		<select class="form-control" name="mapRoleId" id="mapRoleId">
						                      	<option value="0">Select Role</option>
						                      	<c:forEach items="${firmMng.mapRoleId}" var="maplist" varStatus="loop">
							                      	<c:if test="${fn:length(firmMng.roleId) gt loop.count}">
							                       	<c:if test="${status.index>=loop.count}">
							                      
							                      	<c:choose><c:when test="${firmMng.mapRoleId[status.index]==firmMng.roleId[loop.index]}">
							                      		<option value="${loop.count}" selected="selected">Role ${loop.count} </option>
							                      	</c:when><c:otherwise>
							                      		<option value="${loop.count}">Role ${loop.count}  </option>
							                      	</c:otherwise> </c:choose>
							                      	</c:if>
							                      	</c:if>
						                      	</c:forEach>
						                    </select>
						          		</div>
						          	</c:if>
						          	<c:if test="${status.index+1==1}">
								         <div class="col-sm-3 ui-widget rolemapping">
											<p>Role CV Mapping</p>
										</div>
										<div class="col-sm-3 rolemapping">
											<select class="form-control" name="mapRoleId" id="mapRoleId">
												<option value="0">Select Role</option>
												<option value="1">Role 1</option>
											</select>
										</div>
									</c:if>
								
									</div>
								</div>
								<div class="row">
									<div class="  border">
										<div class="col-sm-3">
											<p>Cover Letter</p>
										</div>
										<div class="col-sm-3 inpur_box">
										<input type="hidden" name="coverLetterSelected" value="" id="coverLetterSelected">
										<c:choose>
											<c:when test="${firmMng.coverLetter[status.index]==true}">
												<input type="checkbox" name="coverLetter"
													placeholder="Cover Letter" checked="checked">
											</c:when>
											<c:otherwise>
												<input type="checkbox" name="coverLetter"
													placeholder="Cover Letter">
											</c:otherwise>
											</c:choose>
										</div>
									</div>
								</div>

								<div class="row">
									<div class="  border">
										<div class="col-sm-3">
											<p>Minimum Work Exp Requirement</p>
										</div>
										<div class="col-sm-3 inpur_box">
											<input type="text" class="form-control" name="workExp"
												placeholder="Work Exp Requirement" value="${firmMng.workExp[status.index] }">
										</div>
										Only Months
									</div>
								</div>

								<div class="row">
									<div class="border">
										<div class="col-sm-3">
											<p>Maximum Work Exp Requirement</p>
										</div>
										<div class="col-sm-3 inpur_box">
											<input type="text" class="form-control" name="workExpMax"
												placeholder="Work Exp Requirement" value="${firmMng.workExpMax[status.index] }">
										</div>
										Only Months
									</div>
								</div>

								<div class="row">
									<div class="border">
										<div class="col-sm-3">
											<p>Compensation(CTC)</p>
										</div>
										<div class="col-sm-3 inpur_box">
											<input type="text" class="form-control" name="compensation"
												placeholder="Compensation" value="${firmMng.compensation[status.index] }">
										</div>
									</div>
								</div>

								<div class="row">
									<div class="border">
										<div class="col-sm-3">
											<p>Minimum Expected No. Offers</p>
										</div>
										<div class="col-sm-3 inpur_box">
											<input type="number" class="form-control" name="noHire"
												placeholder="Minimum Expected No. Of Offers" value="${firmMng.noHire[status.index]}" >
										</div>
									</div>
								</div>
								<div class="row">
									<div class="border">
										<div class="col-sm-3">
											<p>Maximum Expected No. Offers</p>
										</div>
										<div class="col-sm-3 inpur_box">
											<input type="number" class="form-control" name="maxNoHire"
												placeholder="Maximum Expected No. Of Offers" value="${firmMng.maxNoHire[status.index]}" >
										</div>
									</div>
								</div>
								<div class="row">
									<div class="border">
										<div class="col-sm-3">
											<p>Average No. Of Offers</p>
										</div>
										<div class="col-sm-3 inpur_box">
											<input type="number" class="form-control" name="avgNoHire"
												placeholder="Average Expected No. Of Offers" value="${firmMng.avgNoHire[status.index]}" >
										</div>
									</div>
								</div>
								
								
							</div>
						</div>
					</div>
				</c:forEach>
				
			</c:if>
			
			<div id="internships1"></div>
			<div class="col-sm-offset-9 col-sm-3 text-center">
				<!-- <button class="btn btn-primary" id="add-panel">Add Role</button>
				<button class="btn btn-primary" id="remove-panel">Remove
					Role</button> -->
				<a href="javascript:void(0)" class="btn btn-primary" id="add-panel">Add
					Role</a> <a href="javascript:void(0)" class="btn btn-primary"
					id="remove-panel">Remove Role</a>
			</div>
			<div class="space"></div>
			<div class="col-md-12 text-center">
				<!--  <input type="submit" value="Update" class="btn btn-primary" />  -->
				 <button type="button"  class="btn btn-primary" onclick="updateFirmForm();"> Update</button>
			</div>
			<div class="space"></div>

		</div>
	</form>
	<!-- / panel preview -->
	<div class="modal fade" id="C-role-edit" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Add Company</h4>
				</div>
				<form id="submitCompanyMaster" action="getCompanyMaster"
					method="POST" name="Cluster">
					<input type="hidden" name="companyId" id="companyId" value="0" />
					<div class="modal-body">
						<div class="row">
							<label class="col-md-6">Company Name *</label>
							<div class="col-md-6">
								<input class="form-control" id="companyName"
									placeholder="Company Name" type="text" name="companyName"
									required="required">
							</div>

						</div>
						<p></p>
						<div class="row">
							<label class="col-md-6">Description *</label>
							<div class="col-md-6">
								<input class="form-control" id="companyDescription"
									placeholder="Company Description" type="text"
									name="companyDescription" required="required">
							</div>


						</div>
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

	<script src="js/jquery-ui.js"></script>
	<script src="assets/js/wizard.min.js"></script>
	<script src="assets/js/jquery.validate.min.js"></script>
	<script src="assets/js/jquery-additional-methods.min.js"></script>
	<script src="assets/js/bootbox.js"></script>
	<script src="assets/js/jquery.maskedinput.min.js"></script>
	<script src="assets/js/select2.min.js"></script>

	<!-- ace scripts -->
	<script src="assets/js/bootstrap-datepicker.min.js"></script>
	<script src="assets/js/bootstrap-timepicker.min.js"></script>
	<script src="assets/js/moment.min.js"></script>

	<script src="assets/js/bootstrap-datetimepicker.min.js"></script>

	<script src="assets/js/ace-elements.min.js"></script>
	<script src="assets/js/ace.min.js"></script>

	<!-- inline scripts related to this page -->
	<!-- <script type="text/javascript">
			jQuery(function($) {
			
				$('[data-rel=tooltip]').tooltip();
			
				$('.select2').css('width','200px').select2({allowClear:true})
				.on('change', function(){
					$(this).closest('form').validate().element($(this));
				}); 
			
			
				var $validation = false;
				$('#fuelux-wizard-container')
				.ace_wizard({
					//step: 2 //optional argument. wizard will jump to step "2" at first
					//buttons: '.wizard-actions:eq(0)'
				})
				.on('actionclicked.fu.wizard' , function(e, info){
					if(info.step == 1 && $validation) {
						if(!$('#validation-form').valid()) e.preventDefault();
					}
				})
				//.on('changed.fu.wizard', function() {
				//})
				.on('finished.fu.wizard', function(e) {
					bootbox.dialog({
						message: "Thank you! Your information was successfully saved!", 
						buttons: {
							"success" : {
								"label" : "OK",
								"className" : "btn-sm btn-primary"
							}
						}
					});
				}).on('stepclick.fu.wizard', function(e){
					//e.preventDefault();//this will prevent clicking and selecting steps
				});
			
			
				//jump to a step
				/**
				var wizard = $('#fuelux-wizard-container').data('fu.wizard')
				wizard.currentStep = 3;
				wizard.setState();
				*/
			
				//determine selected step
				//wizard.selectedItem().step
			
			
			
				//hide or show the other form which requires validation
				//this is for demo only, you usullay want just one form in your application
				$('#skip-validation').removeAttr('checked').on('click', function(){
					$validation = this.checked;
					if(this.checked) {
						$('#sample-form').hide();
						$('#validation-form').removeClass('hide');
					}
					else {
						$('#validation-form').addClass('hide');
						$('#sample-form').show();
					}
				})
			})
		</script> -->

	<script type="text/javascript">
		function comanycheck() {

			if (document.getElementById("companyName").value == "") {
				document.getElementById("companynameError").innerHTML = "You must enter a Company Name";
				return false;
			}

			if (document.getElementById("companyDescription").value == "") {
				document.getElementById("companyDescriptionError").innerHTML = "You must enter a Company Description";
				return false;
			}
		}
	</script>
	<script>
		$(document).ready(function() {
			var x = 1;
			$("#add-additional").click(function() {
				var wrapper = $('#additional');
				/* var data = $('#additionalbox').html(); */
				var data="";
				data='<div class="row"><input type="hidden" value="0" name="additionalFileId"/><div class="border"><div class="col-sm-3 tsize">Additional Files</div><div class="col-sm-3 tsize"><input id="id-input-file-2" type="file" name="additionalFile"></div></div></div>'
				if (x < 11) {
					var domElement = $('' + data + '');
					$(wrapper).append(domElement);
					x++;
				}
			});

			$("#remove-additional").click(function() {
				var main = document.getElementById("additional");
				if(x>1)
				x--;
				if (main.children.length > 0) {
					main.lastChild.remove();
				}
			});

		});

		$(document).ready(function() {
			//$('.chkpref').hide();
			var x = 1;
			$("#add").click(function() {
				var wrapper = $('#internships');
				/* var data1 = $('#internshipsbox1').html(); */
				var data2="";
				data2+='<div class="row"><input type="hidden" value="0" name="urlId"/><div class="  border"><div class="col-sm-3"><p>Add URL</p></div><div class="col-sm-3 inpur_box"><input type="text" class="form-control" name="url"placeholder="URL">'
				data2+='</div><div class="col-sm-3 text-center"><input type="text" class="form-control" name="urlDescription" placeholder="Url Description"></div></div></div>'
				if (x < 11) {
					var domElement = $('' + data2 + '');
					$(wrapper).append(domElement);
					x++;
				}

			});

			$("#remove").click(function() {
				var main = document.getElementById("internships");
				if(x>1)
				x--;
				if (main.children.length > 0) {
					main.lastChild.remove();
				}
			});
		});

		$('.date-picker').datepicker({
			autoclose : true,
			todayHighlight : true
		})
		//show datepicker when clicking on the icon
		.next().on(ace.click_event, function() {
			$(this).prev().focus();
		});

		$('#date-timepicker1').datetimepicker({
			format : 'DD-MM-YYYY HH:mm:ss',//use this option to display seconds
			//format: 'MM/DD/YYYY h:mm:ss A',//use this option to display seconds
			icons : {
				time : 'fa fa-clock-o',
				date : 'fa fa-calendar',
				up : 'fa fa-chevron-up',
				down : 'fa fa-chevron-down',
				previous : 'fa fa-chevron-left',
				next : 'fa fa-chevron-right',
				today : 'fa fa-arrows ',
				clear : 'fa fa-trash',
				close : 'fa fa-times'
			},
			defaultDate: moment(),
		}).next().on(ace.click_event, function() {
			$(this).prev().focus();
		});

		$('#closeingDate').datetimepicker({
			format : 'DD-MM-YYYY HH:mm:ss',//use this option to display seconds
			//format: 'MM/DD/YYYY h:mm:ss A',//use this option to display seconds
			icons : {
				time : 'fa fa-clock-o',
				date : 'fa fa-calendar',
				up : 'fa fa-chevron-up',
				down : 'fa fa-chevron-down',
				previous : 'fa fa-chevron-left',
				next : 'fa fa-chevron-right',
				today : 'fa fa-arrows ',
				clear : 'fa fa-trash',
				close : 'fa fa-times'
			},
			defaultDate: moment(),
		}).next().on(ace.click_event, function() {
			$(this).prev().focus();
		});
	</script>
	<script>
		$(document)	.ready(
			function() {
				var maplist = ${firmMng.mapRoleId};
				var mapCount  = maplist.length;
				var x = mapCount+1;
				var loop = 1;
				//alert("mapCount : "+mapCount);
				//alert("X : "+x);
				$("#add-panel")	.click(
					function() {
						loop = 1;
						var wrapper = $('#internships1');
					
						var data1="";
					  	data1+='<div id="internshipbox1"><div class="panel-body"><div class="row"><div class="  border"><div class="col-sm-3 ui-widget">'
						  data1+='<p>Role *</p></div><div class="col-sm-3 inpur_box"><input type="hidden" value="0" name="roleId"/><select class="form-control" name="roleCompanyId" required><option value="">select</option>'	
						  data1+='<c:forEach items="${roleList}" var="role"><option value="${role.roleCompanyId}">${role.roleCompany}</option></c:forEach></select></div>'
						  data1+='<div class="col-sm-3 ui-widget rolemapping"><p>Role CV Mapping</p></div> <div class="col-sm-3 rolemapping">'
						  data1+='<select class="form-control" name="mapRoleId"  id="mapRoleId">'
						  data1+='<option value="0">Select Role</option>'
						  while(loop<x){
				  			data1+='<option value="'+loop+'">'+'Role '+loop+'</option>' ;
				  			loop++;
				  		  }
						  data1+='</select></div></div>'
						  data1+='</div>'
						  
						  data1+='<div class="row"><div class="  border"><div class="col-sm-3"><p>Cover Letter</p></div><div class="col-sm-3 inpur_box"><input type="checkbox" name="coverLetter"placeholder="Cover Letter" checked="checked">'
						  data1+='</div></div></div><div class="row"><div class="  border"><div class="col-sm-3"><p>Minimum Work Exp Requirement</p></div>' 
						  data1+='<div class="col-sm-3 inpur_box"><input type="text" class="form-control" name="workExp"placeholder="Work Exp Requirement"></div>Only Months</div></div>'
						  data1+='<div class="row"><div class="  border"><div class="col-sm-3"><p>Maximum Work Exp Requirement</p></div><div class="col-sm-3 inpur_box">'
						  data1+='<input type="text" class="form-control" name="workExpMax"placeholder="Work Exp Requirement">'
						  data1+='</div>Only Months</div></div><div class="row"><div class="  border"><div class="col-sm-3"><p>Compensation(CTC)</p>'
						  data1+='</div><div class="col-sm-3 inpur_box"><input type="text" class="form-control" name="compensation" placeholder="Compensation">'
						  data1+='</div></div></div>'
						  
						  data1+=' <div class="row"><div class="border"><div class="col-sm-3"><p>Minimum Expected No. Offers</p>'
						  data1+='	</div><div class="col-sm-3 inpur_box"><input type="number" class="form-control" name="noHire"placeholder="Minimum Expected No. Of Offers" >'
						  data1+='</div></div></div><div class="row"><div class="border"><div class="col-sm-3"><p>Maximum Expected No. Offers</p></div><div class="col-sm-3 inpur_box">'
						  data1+='	<input type="number" class="form-control" name="maxNoHire" placeholder="Maximum Expected No. Of Offers" >'
						  data1+='</div></div></div><div class="row"><div class="border"><div class="col-sm-3"><p>Average No. Of Offers</p>'
						  data1+='	</div><div class="col-sm-3 inpur_box"><input type="number" class="form-control" name="avgNoHire" placeholder="Average Expected No. Of Offers" ></div></div></div> </div></div>'
							
						if (x < (11)) {
							var domElement = $(' <div class="main_box section_one panel panel-primary"><div class="panel-heading">Role'
									+ x	+ '</div>'	+ data1	+ '</div></div>');
							$(wrapper).append(
									domElement);
							x++;
						}
	
					});
				 $("#remove-panel").click(function() {
					 var main = document.getElementById("internships1");
					 if(x>1)
					 x--;	
					 if (main.children.length > 0) {           
						 main.lastChild.remove(); 
					}
							
			    });
				
			});
	</script>


	<script>
		$(function() {
			var jsonData = ${compArray};
			var arr = [];
			for ( var x in jsonData) {
				//arr.push(jsonData[x].companyId);
				arr.push(jsonData[x].companyName);
			}
			$("#compId").autocomplete({
				source : arr
			});

		});
	</script>

	<script>
		$(function() {
			var processData = ${processArray};
			//alert(processData);
			var procarr = [];
			for ( var x in processData) {
				procarr.push(processData[x].processName);
			}
			$("#procId").autocomplete({
				source : procarr
			});

		});
	</script>
	<script>
		$(function() {
			var clusterData = ${clusterArray};
			//alert(processData);
			var clusterarr = [];
			for ( var x in clusterData) {
				clusterarr.push(clusterData[x].clusterName);
			}
			$("#clustId").autocomplete({
				source : clusterarr
			});

		});
	</script>

	<script>
		$(function() {
			var cohortData = ${cohortArray};
			//alert(processData);
			var cohortarr = [];
			for ( var x in cohortData) {
				cohortarr.push(cohortData[x].cohortId);
			}
			$("#cohortId").autocomplete({
				source : cohortarr
			});

		});
	</script>

	<script>
		$(function() {
			var roleData = ${roleArray};
			//alert(processData);
			var rolearr = [];
			for ( var x in roleData) {

				rolearr.push(roleData[x].roleCompany);
			}
			$("#roleId").autocomplete({
				source : rolearr
			});

		});
	</script>
	<script>
		$(function() {
			//var yearData = ${yearArray};
			//alert(processData);
			var yeararr = [ "2016", "2017", "2018" ];
			/* for ( var x in yearData) {
				
				yeararr.push(yearData[x].year);
			} */
			$("#yearId").autocomplete({
				source : yeararr
			});

		});
	</script>
	<script>
		$(function() {
			//var yearData = ${yearArray};
			//alert(processData);
			var roundarr = [ "1", "2" ];
			/* for ( var x in yearData) {
				
				yeararr.push(yearData[x].year);
			} */
			$("#noOfRoundId").autocomplete({
				source : roundarr
			});

		});
	</script>
	<script>
		/* function getCohortNameByCid(cid) {
			var clusterId = cid.value;
			//alert("clusetid-"+document.getElementById("clusterId").value+" "+clusterId);

			var xhttp = new XMLHttpRequest();
			xhttp.onreadystatechange = function() {
				if (this.readyState == 4 && this.status == 200) {
					var i;
					var obj = JSON.parse(this.responseText);
					var cohortList = '';
					for (i = 0; i < obj.length; i++) {
						cohortList += "<option value="+obj[i].cohortId+" > "
								+ obj[i].cohortName;
					}
					//alert("cohort name "+cohortList); 
					document.getElementById("cohortId").innerHTML = cohortList;

				}
			};
			var action = "getCohortByClusterId?cid=" + clusterId;
			xhttp.open("GET", action, true);
			xhttp.send();
		}
		 */
		function getCohortNameByCName(cname) {
			document.getElementById("cohortId").innerHTML = "";
			//alert("inside method::");
			var clusterName = cname.value;
			//alert(" clusetid ::"+clusterName);
			var xhttp = new XMLHttpRequest();
			xhttp.onreadystatechange = function() {
				if (this.readyState == 4 && this.status == 200) {
					var options = "";
					var obj = JSON.parse(this.responseText);
					var cohortList = '';
					for ( var x in obj) {
						var valueIs = obj[x].cohortName;
						options += '<option value=' + valueIs.replace(" ", "_")
								+ '>' + valueIs + '</option>';
					}
					//alert("options :: "+ options);
					document.getElementById("cohortId").innerHTML = options;
				}

			};
			var action = "getCohortByClusterName?cname=" + clusterName;
			xhttp.open("GET", action, true);
			xhttp.send();
		}

		function clearCompanyForm() {
			var companyName = document.getElementById("companyName");
			companyName.value = ''
			var companyDescription = document
					.getElementById("companyDescription");
			companyDescription.value = ''
		}
			
		function updateFirmForm(){
			var x = document.getElementsByName("coverLetter");	
		    var i,j;
		    var txt=[];
		    for (i = 0; i < x.length; i++) {
	           if (x[i].checked) {	        	
	        	   txt[txt.length]="true";	            	 
		        }else{
		        	txt[txt.length]="false";	            	 
		        }
		   }
		  	$("#coverLetterSelected").val(txt);
		 	//alert($("#coverLetterSelected").val());
		  	document.getElementById("firmManagement").action = "updateFirmManagement";
		  	document.getElementById("firmManagement").method = "post";
		  	document.getElementById("firmManagement").submit();  			  	  
		}
			
	</script>


</body>
<script>
	var year = ${firmMng.year}
	$('#selectYear').val(year);

	var cohort = ${firmMng.cohortId}
	$('#cohortId').val(cohort);

	var preference = ${firmMng.preferenceServe}
	if (preference == true) {
		$('#preferenceServe').prop('checked', true);
	} else {
		$('#preferenceServe').prop('checked', false);
	}

	var multipleCv = ${firmMng.multCVRelates}
	if (multipleCv == true) {
		$('#multiCvRelates').prop('checked', true);
	} else {
		$('#multiCvRelates').prop('checked', false);
	}
</script>
<script>
function deleteRole(roleId,applicationId){
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			var response=(this.responseText);
			if(response=="success"){
				$("#roleCount"+roleId).hide();
				document.getElementById("roleHeader"+roleId).style.display='none';
				document.getElementById("roleDiv"+roleId).style.display='none';
				/* <div class="panel-heading" id="roleHeader${firmMng.roleId[status.index]}">Role  <a href="#" onclick="deleteRole(${firmMng.roleId[status.index]},${firmMng.applicationId})" style="float:right;color: white;">Delete</a></a></div>
				<div id="internshipbox1" id="roleDiv${firmMng.roleId[status.index]}"> */
			}
		}
	};
	xhttp.open("POST", "deleteRoleValue?roleId="+roleId+"&appId="+applicationId, true);
	xhttp.send();
}

function getCohortValues(val){
	document.getElementById("cohortId").innerHTML="";
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			var data="";
			var jsonData=JSON.parse(this.responseText);
			for(var i=0;i<jsonData.length;i++){
				data+="<option value="+jsonData[i].key+">"+jsonData[i].value+"<oprion>";
			}
			document.getElementById("cohortId").innerHTML=data;
		}
	};
	xhttp.open("POST", "getCohortValues?clusterId="+val.value, true);
	xhttp.send();
}

function deleteUrl(urlId,applicationId){
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			if(this.responseText=="success"){
			//	document.getElementById("urlDiv"+urlId).style.display='none';
				document.getElementById("urlDiv"+urlId).remove();
			}
		}
	};
	xhttp.open("POST", "deleteUrl?urlId="+urlId, true);
	xhttp.send();
}

function deleteAdditionalFile(fileId,applicationId){
	//alert("inside function::"+fileId);
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			if(this.responseText=="success"){
				document.getElementById("addFileDiv"+fileId).style.display='none';
			}
		}
	};
	xhttp.open("POST", "deleteAddFile?addFileId="+fileId, true);
	xhttp.send();
}
</script>
</html>