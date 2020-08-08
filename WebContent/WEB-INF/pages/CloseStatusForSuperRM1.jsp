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
    <link rel="icon" href="images/favicon.ico" type="image/x-icon"/>
	<!-- Bootstrap -->
	<!-- <link href="css/bootstrap.css" rel="stylesheet">
	
	Custom
	<link href="css/custom.css" rel="stylesheet">
	<link rel="stylesheet" href="css/font-awesome.css">
	
	CSS STYLE
	<link rel="stylesheet" type="text/css" href="css/style.css"
		media="screen" />
	<link rel="stylesheet" type="text/css"
		href="css/dataTables.bootstrap.min.css" media="screen" /> -->
	<link rel="stylesheet" type="text/css"
		href="css/buttons.dataTables.min.css" media="screen" />
	<link rel="stylesheet" type="text/css"
		href="css/jquery.dataTables.min.css" media="screen" />
	
	</head>
	<body>
		<jsp:include page="commonJsp/Header.jsp" />
	
		<form method="post" id="downloadCV">
			<input type="hidden" name="downloadCvId" id="downloadCvId" />
		</form>
		<form method="post" id="pdfBind">
		   <input type="hidden" name="bindId" id="bindId" />
	    </form>
		<form method="post" id="updateFlag">
			<input type="hidden" name="updateApplyId" id="updateApplyId" />
			<input type="hidden" name="cmpRoleId" value="${cmpRoleId}"/>
		</form>
		
		<div class="space"></div>
	
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
						<li class=""><a href="#tab5" data-toggle="tab">Enter Shortlist
						</a></li>
						<li class=""><a href="#tab6" data-toggle="tab">View Released Shortlist
						</a></li>
						<li class=""><a href="#tab7" data-toggle="tab">Enter Hotlist
						</a></li>
						<li class=""><a href="#tab8" data-toggle="tab">View Released Hotlist
						</a></li>		
					</ul>
				</nav>
	
			</aside>
	
			<div class="col-md-10">
		
				<div class="tab-content">
					
					<div class="tab-pane active text-style" id="tab1">  <!-- active -->
						<div class="col-md-12"><h3> ${closeStatus.firmName} - ${closeStatus.roleName} - ${closeStatus.year}  </h3></div>
					
					
						<div class="container-fluid btn-container">
							<a href="#" class="pull-right" data-toggle="modal"
								data-target="#rm-status-schedule-msg" onclick="getInfoValues();">
								<i class="fa fa-info-circle" aria-hidden="true"></i>
							</a><label class="pull-right"> [ ${closeStatus.process}] : [${closeStatus.experienceReq} months] :
							</label>
						</div>
					
						<div class="clearfix"></div>
					<div class="table-responsive">
						<table id="verify-application"
							class="table table-bordered display nowrap">
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
										<td>
										
										<input  type="checkbox" name="selector[]" class="close1_Checkbox checkbox" id="Checkbox${fm.applyId}" value="${fm.applyId}">
										</td>
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
						</div>
						<div class="space"></div>
						<div class="col-md-12 text-right">
						<input type="button" value="PDF Bind"
							class="btn btn-primary" id="verifypdfbind" /> 
						<input type="button" value="Download CV (zip)"
							class="btn btn-primary" id="verfiyDownloadBtn" /> 
						<input type="button" id="verifyBtn" value="Verify" 
							class="btn btn-primary" />
						</div>
					 </div>
					 
					 
			<div class="tab-pane text-style" id="tab2">
					
						<div class="col-md-12">
							<h3>${closeStatus.firmName} - ${closeStatus.roleName} -
								${closeStatus.year}</h3>
						</div>
					
						<!-- panel preview -->
						<div class="container-fluid btn-container">
							<b>Note:</b>Verify status is yes than this list is show <a
								href="#" class="pull-right" data-toggle="modal"
								data-target="#rm-status-schedule-msg"
								onclick="getInfoValues();"> <i class="fa fa-info-circle"
								aria-hidden="true"></i>
							</a><label class="pull-right"> [ ${closeStatus.process}] :
								[${closeStatus.experienceReq} months] : </label>
						</div>
					
						<div class="clearfix"></div>
					<div class="table-responsive">
						<table id="main-fourum2"
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
								<c:forEach items="${approveCloseStatusList}" var="fm">
									<tr>
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
										<td>${fm.status}</td>
									</tr>
					
								</c:forEach>
							</tbody>
						</table>
					</div></div>
								
								
					<div class="tab-pane text-style" id="tab3">
						<div class="col-md-12"><h3> ${closeStatus.firmName} - ${closeStatus.roleName} - ${closeStatus.year}  </h3></div>
					
						<!-- panel preview -->
						<div class="container-fluid btn-container">
							<b>Note:</b>Verify status is no than this list is show <a 
								href="#" class="pull-right" data-toggle="modal"
								data-target="#rm-status-schedule-msg"
								onclick="getInfoValues();"> <i class="fa fa-info-circle"
								aria-hidden="true"></i>
							</a><label class="pull-right"> [ ${closeStatus.process}] :
								[${closeStatus.experienceReq} months] : </label>
						</div>
					
						<div class="clearfix"></div>
						<div class="table-responsive">
						<table id="main-fourum3" class="table table-bordered display nowrap">
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
										<td>${fm.status}</td>
									</tr>
					
								</c:forEach>
							</tbody>
					  	</table>
					  	</div>
						<div class="space"></div>
						
					</div>
				
					<div class="tab-pane text-style" id="tab4">
						
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
				<div class="table-responsive">
						<table id="main-fourum4"
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
										<td><input type="checkbox" name="selector[]" class="considerCheck" id="Checkbox${fm.applyId}" value="${fm.applyId}"></td>
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
						</div>
						<div class="space"></div>
						<div class="col-md-12 text-right">
							<input type="button" value="Download CV (zip)"
								class="btn btn-primary" id="downloadBtn"/>
							<input type="button"
								value="Genrate Consideration List" class="btn btn-primary"
								id="shortListBtn" />
							
						</div>
						<div class="space"></div>
				
					</div>
						 
					 
				
			<div class="tab-pane text-style" id="tab5"> <!-- active -->
						<div style="float: left; padding-right: 12px">
				   						  <input type="file"
									       id="excelfile5" 
									       class="btn btn-primary"/>
						</div>
			
						<div style="float: left">
										<input type="button" 
										  class ="btn btn-primary" 
										  id="viewfile" 
										  value="Upload Excel File" 
										  onclick="Populatecheckbox('main-fourum5','excelfile5')"/>
						</div>
						<div class="col-md-12">
							<h3>${closeStatus.firmName} - ${closeStatus.roleName} -
								${closeStatus.year}</h3>
						</div>
					
						<div class="container-fluid btn-container"> <a href="#" class="pull-right" data-toggle="modal" 
						data-target="#rm-status-schedule-msg"  onclick="getInfoValues();"> 
						<i class="fa fa-info-circle" aria-hidden="true"></i> </a>
     					 <label class="pull-right"> [ ${closeStatus.process}] : [${closeStatus.experienceReq} months]  : </label>
      
                        </div>
    
    <div class="clearfix"></div>
    <form name="shortlistForm" id="shortlistformId">
    <div class="table-responsive">
    <table id="main-fourum5" class="table table-bordered display nowrap">
      <thead>
        <tr>
          <th>Roll Number</th>
          <th>Email ID</th>
          <th>Name</th>
          <th>Preference</th>
          <th>Status</th>
          <th>RM Status</th>          
          <th>Shortlist <input type="checkbox" id="shortlistbtn"></th>
        </tr>
      </thead>
      <tbody>
      
      <c:forEach items="${shortlist}" var="shrt">
      <input type="hidden" value="${shrt.shortListId}" name="shortListId"/>
        <tr>
          <td>${shrt.rollNumber}</td>
          <td><a href="#" data-toggle="modal" data-target="#closed-status-email" onclick="getUserDetailsByRolNo('${shrt.rollNumber}');"> ${shrt.emailID}</a>
           </td>
          <td> ${shrt.name} </td>
          <td> ${shrt.preference} </td>
          <td>${shrt.status}</td>
          <td>${shrt.rmStatus}</td>
          <td align="center"> 
             <input type="hidden" value="" name="shortListIdsSelected" id="shortListIdsSelected"/>         
             <input type="hidden" name="notShortlisted" value="" id="notShortListedIds"> 
            <input type="checkbox" name="shortlisted" value="${shrt.applyId}" class="checkbox shortlisted">              
           
           <%--  <c:if test="${shrt.shortList eq false}">
             <input type="checkbox" name="shortlisted" value="${shrt.applyId}" class="checkbox">              
             </c:if>
             <c:if test="${shrt.shortList eq true}">
             <input type="checkbox" name="shortlisted" checked="checked" value="${shrt.applyId}" class="checkbox" checked > 
           
             </c:if> --%>
          
          </td>
        </tr>
        
      </c:forEach>  
      </tbody>
    </table>
    </div>
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

      <input type="button" id="saveReleaseId" value="Save Shortlist Details" onclick="saveShortlistedBySuperRM();" data-toggle="modal" data-target="#save-shortlist-release"  class="btn btn-primary" />

      
     <!--  <div class="modal fade" id="save-shortlist-release" role="dialog">
         <div class="modal-dialog">
    
     
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title text-left">Congratulation</h4>
        </div>
        <div class="modal-body">
                
        <div class="row"><div class="col-md-12 text-center"> <label>Student Congratulation</label> </div></div>
         <div class="row"><div class="col-md-12 text-left"><textarea rows="3" class="form-control"  id="greetingsData" name="greetings"></textarea></div></div>
        
        </div>
         <div class="modal-footer text-center">
          <button type="button" class="btn btn-primary" onclick="saveShortlistedBySuperRM();">OK</button>
          <button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
        </div>
      </div>
      
    </div>
  </div> -->
      
    </div>
    
   </form>
					</div>
				
					<div class="tab-pane text-style" id="tab6">
					
						<div class="col-md-12">
							<h3>${closeStatus.firmName} - ${closeStatus.roleName} -
								${closeStatus.year}</h3>
						</div>
					
						<div class="container-fluid btn-container"> <a href="#" class="pull-right" 
						data-toggle="modal" data-target="#rm-status-schedule-msg" onclick="getInfoValues();">
						 <i class="fa fa-info-circle" aria-hidden="true"></i> </a>
    <label class="pull-right"> [ ${closeStatus.process}] : [${closeStatus.experienceReq} months]  : </label>
    <!-- <div class="modal fade" id="rm-status-schedule-msg4" role="dialog">
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
    </div> -->
  </div>
  <div class="clearfix"></div>
  <form name="shortlistedReleaseForm" id="shortlistedReleaseFormId"> 
  <div class="table-responsive">
  <table id="main-fourum6" class="table table-bordered display nowrap">
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
       
        <td>${shrt.rollNumber}  <input type="hidden" value="${shrt.shortListId}##${shrt.applyId}" name="idlist"/></td>
        <td><a href="#" data-toggle="modal" data-target="#closed-status-email" onclick="getUserDetailsByRolNo('${shrt.rollNumber}');"> ${shrt.emailID}</a>
         </td>
         <td> ${shrt.name} </td>
         <td> ${shrt.preference} </td>
         <td>${shrt.status} </td>
         
      </tr>
     </c:forEach> 
    </tbody>
  </table>
  </div>
  <div class="space"></div>
  <div class="col-md-12 text-right">
   <!--  <input type="button" value="Shortlist Release" class="btn btn-primary" onclick="saveShortlistFinalBySuperRM1();"/>
    <input type="button" value="Generate Hotlist Link" data-toggle="modal" data-target="#generate-shortlist-link" class="btn btn-primary" />
    -->
    <input type="hidden"  name="roleId" id="roleId" value=" ${closeStatus.roleId}"/>
     
      
      <div class="modal fade" id="generate-shortlist-link" role="dialog">
         <div class="modal-dialog">
    
     
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title text-left">List Send to Firm</h4>
        </div>
        <div class="modal-body">
                <div class="row"><label class="col-md-6 text-right">Firm HR Email Id : </label><div class="col-md-6">
                <input type="text" class="form-control" name="emailId"> </div></div>
        <div class="row"><div class="col-md-12 text-left"><input type="checkbox" name="rankFlag"/> Send Preference</div></div>
         <div class="row"><div class="col-md-12 text-left"><textarea rows="3" class="form-control" name="mailDes"></textarea></div></div>
        
        </div>
         <div class="modal-footer text-center">
          <button type="button" class="btn btn-primary" onclick="saveSendMail();">Send</button>
          <button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
        </div>
      </div>
      
    </div>
  </div>
  </div>
  
  </form>
					</div>
				
					<div class="tab-pane text-style" id="tab7">
					<div style="float: left; padding-right: 12px">
				   						  <input type="file"
									       id="excelfile7" 
									       class="btn btn-primary"/>
						</div>
			
						<div style="float: left">
										<input type="button" 
										  class ="btn btn-primary" 
										  id="viewfile" 
										  value="Upload Excel File" 
										  onclick="Populatecheckbox('main-fourum7','excelfile7')"/>
						</div>
					<div class="col-md-12">
							<h3>${closeStatus.firmName} - ${closeStatus.roleName} -
								${closeStatus.year}</h3>
						</div>
						
						 <div class="container-fluid btn-container"> <a href="#" class="pull-right" data-toggle="modal" 
						 data-target="#rm-status-schedule-msg"  onclick="getInfoValues();"> 
						 <i class="fa fa-info-circle" aria-hidden="true"></i> </a>
      <label class="pull-right"> [ ${closeStatus.process}] : [${closeStatus.experienceReq} months]  : </label>
     <!--  <div class="modal fade" id="rm-status-schedule-msg4" role="dialog">
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
      </div> -->
    </div>
    
    <div class="clearfix"></div>
    <form name="shortlistForm" id="hotlistformId">
    <div class="table-responsive">
    <table id="main-fourum7" class="table table-bordered display nowrap">
      <thead>
        <tr>
          <th>Roll Number</th>
          <th>Email ID</th>
          <th>Name</th>
          <th>Preference</th>
          <th>Status</th>
          <th>RM Status</th>
          <th>Hot list <input type="checkbox" id="hotlistbtn"></th>
        </tr>
      </thead>
      <tbody>
      
      <c:forEach items="${hotlist}" var="shrt">
      <input type="hidden" value="${shrt.hotListId}" name="hotListId"/>
        <tr>
          <td>${shrt.rollNumber}</td>
          <td><a href="#" data-toggle="modal" data-target="#closed-status-email" onclick="getUserDetailsByRolNo('${shrt.rollNumber}');"> ${shrt.emailID}</a>
           </td>
          <td> ${shrt.name} </td>
          <td> ${shrt.preference} </td>
          <td>${shrt.status}</td>
          <td>${shrt.rmStatus}</td>
          <td align="center"> 
             <input type="hidden" value="" name="hotListIdsSelected" id="hotListIdsSelected"/> 
             <input type="hidden" name="notShortlisted" value="" id="notShortListedIds">        
           <input type="checkbox" name="hotlisted" value="${shrt.hotListId}" class="checkbox hotlisted"> 
          
           <%--  <c:if test="${shrt.shortList eq false}">
             <input type="checkbox" name="hotlisted" value="${shrt.hotListId}" class="checkbox"> 
             </c:if>
             <c:if test="${shrt.shortList eq true}">
             <input type="checkbox" name="hotlisted"  value="${shrt.hotListId}" class="checkbox" checked > 
             </c:if> --%>
          
          </td>
        </tr>
        
      </c:forEach>  
      </tbody>
    </table>
    </div>
    <div class="space"></div>
    <div class="col-md-12 text-right">
      <input type="button" id="saveReleaseId" value="Save HotList Release" onclick="saveHotlistBySuperRm1();" class="btn btn-primary"  />
     <!--
      <div class="modal fade" id="save-hotlist-release" role="dialog">
         <div class="modal-dialog">
    
     
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title text-left">Congratulation</h4>
        </div>
        <div class="modal-body">
                
        <div class="row"><div class="col-md-12 text-center"> <label>Student Congratulation</label> </div></div>
         <div class="row"><div class="col-md-12 text-left"><textarea rows="3" class="form-control" name="greetingsHotlist" id="greetingsDataHot"></textarea></div></div>
        
        </div>
         <div class="modal-footer text-center">
          <button type="button" class="btn btn-primary" onclick="saveHotlistBySuperRm1();">OK</button>
          <button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
        </div>
      </div>
      
    </div>
  </div> -->
     
    </div>
    
   </form>
					</div>
	<!-- View Released Hotlist div starts -->
				<div class="tab-pane text-style" id="tab8">

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
					<form name="shortlistedReleaseForm" id="hotListReleaseFormId">
						<table id="main-fourum8"
							class="table table-bordered display nowrap">
							<thead>
								<tr>
									<th>Roll Number</th>
									<th>Email ID</th>
									<th>Name</th>
									<th>Prefrence</th>
									<th>Status</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${hotlistRelease}" var="shrt">
									<tr>
										<td>${shrt.rollNumber}<input type="hidden"
											value="${shrt.applyId}" name="idhotlist" class="hotListView" /></td>
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

							<!--  <input type="button" value="Process Done" class="btn btn-primary" onclick="saveShortlistReleaseHR();"/> -->
						<!-- 	<input type="button" value="Process Done" data-toggle="modal"
								data-target="#ProcessDone" class="btn btn-primary" /> -->
						<input type="button" value="PDF Bind" class="btn btn-primary" id="downloadPdfBindHotlistView" /> 
						<input type="button" value="Download CV (zip)" class="btn btn-primary" id="downloadBtnHotlistView" />
						
							<div class="modal fade" id="ProcessDone" role="dialog">

								<div class="modal-dialog">
									<div class="modal-content">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal">&times;</button>
											<h4 class="modal-title text-left">Congratulations</h4>
										</div>
										<div class="modal-body">
											Congratulations, You have been shortlisted .
											<div class="modal-footer">
												<button type="button" class="btn btn-primary"
													data-dismiss="modal" onclick="saveProcessDone();">OK</button>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</form>
				</div> 
				<!-- View Released Hotlist div ends -->				
				</div>
			</div>
		</div>
	
	    <!-- all dialog box panel preview -->

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
		
		<!-- <script type="text/javascript" src="js/jquery.min.js"></script> -->
		<script type="text/javascript" src="js/jquery.dataTables.min.js"></script>
		<script type="text/javascript" src="js/dataTables.buttons.min.js"></script>
		<script type="text/javascript" src="js/buttons.flash.min.js"></script>
		<script type="text/javascript" src="js/jszip.min.js"></script>
		<script type="text/javascript" src="js/pdfmake.min.js"></script>
		<script type="text/javascript" src="js/vfs_fonts.js"></script>
		<script type="text/javascript" src="js/buttons.html5.min.js"></script>
		<script type="text/javascript" src="js/buttons.print.min.js"></script>
	
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
				/* $(function() {
				    $('.checkbox').click(function() {
				        if ($('.checkbox:checked').length > 0) {
				            $('#verifyBtn').removeAttr('disabled');
				        } else {
				            $('#verifyBtn').attr('disabled', 'disabled');
				        }
				    });
				});  */    
				
			});
			$(document).ready(function() {
	
				$('#main-fourum2').DataTable({
					dom : 'Bfrtip',
					lengthMenu: [
					              [ -1, 10, 25, 50, 100 ],
					              [ 'Show all' , '10 rows', '25 rows', '50 rows', '100 rows' ]
					          ],
					buttons : [ 'pageLength', 'excel', 'pdf', 'print' ]
				});
	
				$('#main-fourum3').DataTable({
					dom : 'Bfrtip',
					lengthMenu: [
					              [ -1, 10, 25, 50, 100 ],
					              [ 'Show all' , '10 rows', '25 rows', '50 rows', '100 rows' ]
					          ],
					buttons : [ 'pageLength', 'excel', 'pdf', 'print' ]
				});
				$('#main-fourum4').DataTable({
					dom : 'Bfrtip',
					lengthMenu: [
					              [ -1, 10, 25, 50, 100 ],
					              [ 'Show all' , '10 rows', '25 rows', '50 rows', '100 rows' ]
					          ],
					buttons : [ 'pageLength', 'excel', 'pdf', 'print' ]
				});
				$('#main-fourum5').DataTable({
					dom : 'Bfrtip',
					lengthMenu: [
					              [ -1, 10, 25, 50, 100 ],
					              [ 'Show all' , '10 rows', '25 rows', '50 rows', '100 rows' ]
					          ],
					buttons : [ 'pageLength', 'excel', 'pdf', 'print' ]
				});
				$('#main-fourum6').DataTable({
					dom : 'Bfrtip',
					lengthMenu: [
					              [ -1, 10, 25, 50, 100 ],
					              [ 'Show all' , '10 rows', '25 rows', '50 rows', '100 rows' ]
					          ],
					buttons : [ 'pageLength', 'excel', 'pdf', 'print' ]
				});
				$('#main-fourum7').DataTable({
					dom : 'Bfrtip',
					lengthMenu: [
					              [ -1, 10, 25, 50, 100 ],
					              [ 'Show all' , '10 rows', '25 rows', '50 rows', '100 rows' ]
					          ],
					buttons : [ 'pageLength', 'excel', 'pdf', 'print' ]
				});
				$('#main-fourum8').DataTable({
					dom : 'Bfrtip',
					lengthMenu: [
					              [ -1, 10, 25, 50, 100 ],
					              [ 'Show all' , '10 rows', '25 rows', '50 rows', '100 rows' ]
					          ],
					buttons : [ 'pageLength', 'excel', 'pdf', 'print' ]
				});
					 
				
	
			});
			$(function() {
				$('#downloadPdfBindHotlistView').click(function() {
					valArrDownload = [];
					
					if ($(".hotListView").length > 0){
						 var i = 0;
						$(".hotListView").each(function() {
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
				$('#downloadBtnHotlistView').click(function() {
					valArrDownload = [];
					if ($(".hotListView").length > 0){
						 var i = 0;
						$(".hotListView").each(function() {
							valArrDownload[i] = $(this).val();
							i++;
						});
					}
					if (valArrDownload!=null && valArrDownload.length>0) {
						downloadCV(valArrDownload);
					}else{
						alert("No data available.");
						return false;
					}
				});
			});

	
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
				var action = "getUserValues?rollNumber=" + rollNumber;
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
							$("#otherInternTotal")
									.html('<strong>'
										+ obj[0].otherIntern[obj[0].otherIntern.length - 1].otherDuration
										+ '</strong>');
						}
					}
				};
				var action = "getInternshipList?rollNumber=" + rollNumber;
				xhttp.open("POST", action, true);
				xhttp.send();
			}
	
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
				var action = "getExperienceList?rollNumber=" + rollNumber;
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
				var action = "getCVList?rollNumber=" + rollNumber;
				xhttp.open("POST", action, true);
				xhttp.send();
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
				var action = "getInfoValues";
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
				$('#verfiyDownloadBtn').click(function() {
					valArrDownload = [];
					 if ($(".close1_Checkbox").is(":checked")) {
						 var i = 0;
						 $('.close1_Checkbox:checked').each(function(i) {
							valArrDownload[i] = $(this).val();
							i++;
						});
						downloadCV(valArrDownload);
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
	
			function updateFlag(valArr) {
				//alert("value :: " + valArr.toString());
				$("#updateApplyId").val(valArr.toString());
				document.getElementById("updateFlag").action = "updateFlagBySuperRM1";
				document.getElementById("updateFlag").method = "post";
				document.getElementById("updateFlag").submit();
			}
			
			
			var valArrDownload = [];
			$(function() {
				$('#downloadBtn').click(function() {
					/* valArrDownload = [];
					$('.close1_Checkbox:checked').each(function(i) {
						valArrDownload[i] = $(this).val();
					});
					//alert(valArrDownload);
					if(valArrDownload>0){
					downloadCV(valArrDownload);
					}
					else{
						alert("Please select at least one check box.");
					} */
					
					valArrDownload = [];
					 if ($(".considerCheck").is(":checked")) {
						 $('.considerCheck:checked').each(function(i) {
							valArrDownload[i] = $(this).val();
						});
						downloadCV(valArrDownload);
						
					 }else{
						alert("Please select at least one check box.");
					}
					
				});
				
			});
	
			function downloadCV(valArrDownload) {
				//alert("value :: " + valArrDownload.toString());
				$("#downloadCvId").val();
				$("#downloadCvId").val(valArrDownload.toString());
				document.getElementById("downloadCV").action = "downloadCv";
				document.getElementById("downloadCV").method = "post";
				document.getElementById("downloadCV").submit();
			}
			
			function pdfBind(valArrDownload) {
				//alert("value :: " + valArrDownload.toString());
				$("#bindId").val();
				$("#bindId").val(valArrDownload.toString());
				document.getElementById("pdfBind").action = "pdfmerge";
				document.getElementById("pdfBind").method = "post";
				document.getElementById("pdfBind").submit();
			}
			
			var valArrShortlist = [];
			$(function() {
				$('#shortListBtn').click(function() {
					valArrShortlist = [];
					$("#updateApplyId").val();
					 
					 if ($(".considerCheck").is(":checked")) {
						$('.considerCheck:checked').each(function(i) {
							valArrShortlist[i] = $(this).val();
						});
						$("#updateApplyId").val(valArrShortlist);
						document.getElementById("updateFlag").action = "shortListBySuperRM1";
						document.getElementById("updateFlag").method = "post";
						document.getElementById("updateFlag").submit();
						
					 }else{
						alert("Please select at least one check box.");
					} 
				
				});
			});
			
			
			 function saveShortlistedBySuperRM(){
				 // alert("rm");	
				  
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
				  document.getElementById("shortlistformId").action = "saveShortlistedBySuperRM1"; //  saveShortlisted
				  document.getElementById("shortlistformId").method = "post";
				  document.getElementById("shortlistformId").submit();  
			 }
			 function getGreetings(cmpRoleId) {
				 
				   var xhttp = new XMLHttpRequest();
					xhttp.onreadystatechange = function() {
						if (this.readyState == 4 && this.status == 200) {
							
							var i;
							var obj = JSON.parse(this.responseText);
							var greetingsData = '';

							for (i = 0; i < obj.length; i++) {
								
								greetingsData += "Congratulation you have been Shortlisted by "+
										 obj[i].CompanyName+ " for "+obj[i].roleName+" in "+ obj[i].ProcessName+" " + obj[i].year;
										
										 
							}
							
							$("#greetingsData").html(greetingsData);							
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
								
								greetingsData += "Congratulation you have been Hot-listed by "+
										 obj[i].CompanyName+ " for "+obj[i].roleName+" in "+ obj[i].ProcessName+" " + obj[i].year;
										
										 
							}
							
							$("#greetingsDataHot").html(greetingsData);
							
						}
					};
					var action = "getGreetings?cmpRoleId=" + cmpRoleId;
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
					var action = "getUserDetailsByRolNo?rollNumber=" + rollNo;
					xhttp.open("POST", action, true);
					xhttp.send();
			}
			 function saveShortlistFinalBySuperRM1(){
				    document.getElementById("shortlistedReleaseFormId").action = "saveReleaseShortlistedBySuperRm1";
					document.getElementById("shortlistedReleaseFormId").method = "post";
					document.getElementById("shortlistedReleaseFormId").submit(); 
					   
				}
				 
				function saveSendMail(){
					 document.getElementById("shortlistedReleaseFormId").action = "saveSendMailBySuperRM1";
					 document.getElementById("shortlistedReleaseFormId").method = "post";
					 document.getElementById("shortlistedReleaseFormId").submit(); 
				}
				
				function saveHotlistBySuperRm1(){

					  var x = document.getElementsByName("hotlisted");	 
					 
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
					  document.getElementById("hotlistformId").action = "saveHotlistedBySuperRM1";
					  document.getElementById("hotlistformId").method = "post";
					  document.getElementById("hotlistformId").submit(); 
					  
					 
				   }

				function saveProcessDone(){
				    document.getElementById("hotListReleaseFormId").action = "processDoneHotlistedBySuperRM1";
					document.getElementById("hotListReleaseFormId").method = "post";
					document.getElementById("hotListReleaseFormId").submit();  
					   
				   }
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
							  document.getElementById("shortlistformId").action = "removeShortlistedBySuperRM1";
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
				$(function () {
					$("#shortlistbtn").click(function () {
						$('.shortlisted').prop('checked', this.checked);
					});

					$("#hotlistbtn").click(function () {
						$('.hotlisted').prop('checked', this.checked);
					});
			  
				});
		</script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/xlsx/0.7.7/xlsx.core.min.js"></script>  
<script src="https://cdnjs.cloudflare.com/ajax/libs/xls/0.7.4-a/xls.core.min.js"></script> 
<script>
function Populatecheckbox(tableid, fileid) {  
    var regex = /^([a-zA-Z0-9\s_\\.\-:\(\)])+(.xlsx|.xls)$/;  
    /*Checks whether the file is a valid excel file*/ 
    
    if (regex.test($("#"+fileid).val().toLowerCase())) {  
        var xlsxflag = false; /*Flag for checking whether excel is .xls format or .xlsx format*/  
        if ($("#"+fileid).val().toLowerCase().indexOf(".xlsx") > 0) {  
            xlsxflag = true;  
        }  
        /*Checks whether the browser supports HTML5*/  
        if (typeof (FileReader) != "undefined") {  
            var reader = new FileReader();  
            reader.onload = function (e) {  
                var data = e.target.result;
                /*Converts the excel data in to object*/  
                if (xlsxflag) {  
                	try {
                		var workbook = XLSX.read(data, { type: 'binary' });
                	}
                	catch(err) {
                		alert("The file format is improper or corrupted, please upload a valid XLSX file");
                		return false;
                	}
                }  
                else {  
                	try {
                		var workbook = XLS.read(data, { type: 'binary' });
                	}
                	catch(err) {
                		alert("The file format is improper or corrupted, please upload a valid XLSX file");
                		return false;
                	}
                }  
                
                /*Gets all the sheetnames of excel in to a variable*/  
                var sheet_name_list = workbook.SheetNames;  
 
                var cnt = 0; /*This is used for restricting the script to consider only first sheet of excel*/  
                sheet_name_list.forEach(function (y) { /*Iterate through all sheets*/  
                    /*Convert the cell value to Json*/  
                    if (xlsxflag) {  
                        var exceljson = XLSX.utils.sheet_to_json(workbook.Sheets[y]);  
                    }  
                    else {  
                        var exceljson = XLS.utils.sheet_to_row_object_array(workbook.Sheets[y]);  
                    }  
                    
                    if (exceljson.length > 0 && cnt == 0) {  
                    	var regstr = "(";
                    	$.each( exceljson, function(index){
	                    	var rollno = exceljson[index]["Roll Number"];
	                    	if (typeof rollno == "undefined")
	                    		{
	                    			alert("Please ensure your Excel sheet has a 'Roll Number' Column");
	                    			return false;
	                    		}
	                    	regstr = regstr + rollno + "|";
	                    	    	
                    	});
                    	regstr= regstr.substring(0,regstr.length-1);
                    	regstr = regstr + ")+";
                    	var table = $("#"+tableid).DataTable();
                    	//var lastcell= table.cell(table.columns(0).search(rollno).search().row().index(), 1);
                    	var lastcell= table.columns(0).search(regstr, true, false).draw();
                    	
                    	
                        
                    }  
                });  
            }  
            if (xlsxflag) {/*If excel file is .xlsx extension than creates a Array Buffer from excel*/  
                reader.readAsArrayBuffer($("#"+fileid)[0].files[0]);  
            }  
            else {  
                reader.readAsBinaryString($("#"+fileid)[0].files[0]);  
            }  
        }  
        else {  
            alert("Sorry! Your browser does not support HTML5!");  
        }  
    }  
    else {  
        alert("Please upload a valid Excel file!");  
    }  
}

</script>		
	</body>
</html>