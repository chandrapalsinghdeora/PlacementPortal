<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
     <%@ page import="java.util.UUID" %>
     <%@ page import="org.hashids.Hashids" %>
    <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="utf-8">
<title>Indian Institute of Management Ahmedabad (IIMA) | Home - IIMA</title>
    <link rel="icon" href="images/favicon.ico" type="image/x-icon"/>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="assets/css/bootstrap.min.css" id="bootstrap-css">
    <!-- <link href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css"> -->
        <link rel="stylesheet" href="assets/css/jquery-ui.custom.min.css" />
		<link rel="stylesheet" href="assets/css/chosen.min.css" />
		 <link rel="stylesheet" href="assets/css/bootstrap-datepicker3.min.css" /> 
		<link rel="stylesheet" href="assets/css/bootstrap-timepicker.min.css" />
		<link rel="stylesheet" href="assets/css/daterangepicker.min.css" />
		<link rel="stylesheet" href="assets/css/bootstrap-datetimepicker.min.css" />
		<link rel="stylesheet" href="assets/css/bootstrap-colorpicker.min.css" />

		<!-- ace styles -->
		<link rel="stylesheet" href="assets/css/ace.min.css" class="ace-main-stylesheet" id="main-ace-style" />

		<!--[if lte IE 9]>
			<link rel="stylesheet" href="assets/css/ace-part2.min.css" class="ace-main-stylesheet" />
		<![endif]-->
		<link rel="stylesheet" href="assets/css/ace-skins.min.css" />
		<link rel="stylesheet" href="assets/css/ace-rtl.min.css" />


		<!-- ace settings handler -->
		<script src="assets/js/ace-extra.min.js"></script>
   		
     <!-- <script src="http://code.jquery.com/jquery-1.10.2.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>  -->
    
    <script>
    function checkBlankValue(value){
    	var id='#'+value+"Approve";
    	var idApprove='#'+value+"Lock";
   		if ($(id).is(":checked")){
   			
   		}else{
   			alert("Please approve first then you will be able to lock the information");
   			$(idApprove).prop('checked', false);
   		}
    }
    
  
    function setValues(count,lockId,hiddenVarId){
    	if ($("#summerInternship"+count+"Lock").is(":checked")){
    		var idVal = $("#summerIdHidden"+count).val();
       		$("#summerIdHidden"+count).val("1");
    	}else{
        	var idVal = $("#summerIdHidden"+count).val();
       		$("#summerIdHidden"+count).val("0");
    	}
    }
    
    function  setApproveValueSummer(count){
    	if ($("#summerInternship"+count+"Approve").is(":checked")){
    		var idVal = $("#summerApproveId"+count).val();
       		$("#summerApproveId"+count).val("2");
    	}else{
        	var idVal = $("#summerIdHidden"+count).val();
       		$("#summerApproveId"+count).val("0");
    	}
    }
    
    function getSelectedValues(flag){
    	document.getElementById("studentIdValue").value=flag.value;
		document.forms["submitStudentId"].submit();
    }
    
    function openPg(){
		var checkBoxValue=document.getElementById("pgDiv").style.display;
		if(checkBoxValue=='none'){
		document.getElementById("pgDiv").style.display='block';
		$('#postGrdCheckBox').prop('checked',true);
		document.getElementById("postgraduateDegree").required = true;
		  document.getElementById("postgraduateDepartment").required = true;
		  document.getElementById("postgraduateInstitute").required = true;
		  document.getElementById("postgraduateAbbreviation").required = true;
		}else{
			document.getElementById("pgDiv").style.display='none';	
			$('#postGrdCheckBox').prop('checked',false);
			document.getElementById("postgraduateDegree").required = false;
			  document.getElementById("postgraduateDepartment").required = false;
			  document.getElementById("postgraduateInstitute").required = false;
			  document.getElementById("postgraduateAbbreviation").required = false;
		}
	}
    
    function addDuration(){
  	  var totalValue=0;
  	  var ids=document.getElementsByName("durationWorkExperience");
  	  for(var i=0;i<ids.length;i++){
  		  var value=document.getElementById(ids[i].id).value;
  		  if(value!=""){
  			  totalValue+=parseInt(value);  
  		  }else{
  		  }
  		
  	  }
  	  $("#totalWorkExp").val(totalValue);
    }
  
    	</script>
	
    </head>
    
    
    <body onload="setCheckedValues();" oncontextmenu="return false;">

<jsp:include page="commonJsp/Header.jsp" />
	<div class="clearfix"></div>
	<div class="breadcumb-wrapper">
		<div class="clearfix">
			<div class="pull-left">
				<ul class="list-inline link-list">
					<li><a href="MentorProfile">Home</a></li>
					<!--  <li>Question</li> -->
				</ul>
			</div>
		</div>
	</div>
<div class="space"></div>
 <div id="message" style="color: red;text-align:center;"></div>
<div class="container">
<div class="col-sm-2" > <label  class="control-label text-left col-xs-12  no-padding-right" style="  padding-top: 15px;" >Select Your Mentee* </label></div>
 <div class="col-sm-4" id="selectBox"> 
  <select class="form-control" required onchange="getSelectedValues(this);">
   	<option value="">select</option>
   	<c:forEach items="${studentList}" var="f">
   	<option value="${f.studentId}" ${f.studentId  == studentId ? 'selected' : ' '}>${f.studentName}</option>
   	</c:forEach>
  </select>
  
  <form method="post" action="MentorProfile" id="submitStudentId">
  <input type="hidden"  id="studentIdValue" name="studentId"/>
  </form>
 
 
 </div> </div>
 <div class="space"></div>
 <form id="userProfile" name="userProfile" method="post" action="MentorProfile" enctype="multipart/form-data">
<div class="container min-h-400">

      <div class="stepwizard">
    <div class="stepwizard-row setup-panel">
          <div class="stepwizard-step">
        <a href="#step-1" type="button" class="btn btn-primary btn-circle">1</a>
        <p>Step 1</p>
      </div>
          <div class="stepwizard-step">
        <a href="#step-2" type="button" class="btn btn-default btn-circle" >2</a>
        <p>Step 2</p>
      </div>
      <!--  <div class="stepwizard-step">
        <a href="#step-3" type="button" class="btn btn-default btn-circle" >3</a>
        <p>Step 3</p>
      </div> -->
       <div class="stepwizard-step">
        <a href="#step-4" type="button" class="btn btn-default btn-circle" >3</a>
        <p>Step 3</p>
      </div>
       <!-- <div class="stepwizard-step">
        <a href="#step-5" type="button" class="btn btn-default btn-circle" >5</a>
        <p>Step 5</p>
      </div> -->
       <div class="stepwizard-step">
        <a href="#step-6" type="button" class="btn btn-default btn-circle" >4</a>
        <p>Step 4</p>
      </div>
       <div class="stepwizard-step">
        <a href="#step-7" type="button" class="btn btn-default btn-circle" >5</a>
        <p>Step 5</p>
      </div>
       <div class="stepwizard-step">
        <a href="#step-8" type="button" class="btn btn-default btn-circle">6</a>
        <p>Step 6</p>
      </div>
       <div class="stepwizard-step">
        <a href="#step-9" type="button" class="btn btn-default btn-circle" >7</a>
        <p>Step 7</p>
      </div>
      
          <div class="stepwizard-step">
        <a href="#step-10" type="button" class="btn btn-default btn-circle" >8</a>
        <p>Step 8</p>
      </div>
        </div>
  </div>
 
     
    <div class="row setup-content" id="step-1">
     <input type="hidden" value="${submitFlag}" id="hiddenConfirmFlag"/>
    <div class="main_box section_one panel panel-primary">
     <div class="panel-heading">
           User Profile
        </div>
    <div class="panel-body">
       <div class="form-group">
		<label class="control-label text-left col-xs-12 col-sm-5 no-padding-right" for="text">Roll Number*</label>
            <div class="col-xs-12 col-sm-3">
			<div class="clearfix">
			
			<input  maxlength="100" value="${userBean.rollNumber}" name="rollNumber" id="rollNumber" type="text" required class="col-xs-12 col-sm-6 form-control"  placeholder="Roll Number" onblur="checkNumber('rollNumber','Please enter only numaric values')" />
          	
          	
          				</div>
                
			</div>
            <div class="col-sm-4 text-center">	
          <input type="checkbox" name="rollNumberApprove" value="2" id="rollNumberApprove"> Approve <input id="rollNumberLock" type="checkbox" name="rollNumberLock" value="1" onclick="checkBlankValue('rollNumber')"> Lock
        
        
        </div>
        
		</div>
       <div class="form-group">
     <label class="control-label text-left col-xs-12 col-sm-5 no-padding-right" for="text">Full Name*</label>
         <div class="col-xs-12 col-sm-3">
			<div class="clearfix">
			<input type="text" name="fullName" id="fullName" value="${userBean.fullName}" required class="col-xs-12 col-sm-6 form-control" placeholder="Full Name" />
				</div></div>
                
       
         <div class="col-sm-4 text-center">
          <c:if test="${sessionBean.roleID==3}">
          <input type="checkbox" id="fullNameApprove" name="fullNameApprove" value="2"> Approve <input id="fullNameLock" type="checkbox" name="fullNameLock" value="1" onclick="checkBlankValue('fullName')"> Lock
        </c:if>
         
        </div>
      </div>
   
     <div class="form-group">
     <label class="control-label text-left col-xs-12 col-sm-5 no-padding-right" for="text">CV Name*</label>
         <div class="col-xs-12 col-sm-3">
			<div class="clearfix">
			<input type="text"  name="cvName" id="cvName" value="${userBean.cvName}" required class="col-xs-12 col-sm-6 form-control" placeholder="CV Name"  required/>
				</div></div>
                
       
         <div class="col-sm-4 text-center">
          <c:if test="${sessionBean.roleID==3}">
          <input type="checkbox" id="cvNameApprove" name="cvNameApprove" value="2"> Approve <input id="cvNameLock" type="checkbox" name="cvNameLock" value="1" onclick="checkBlankValue('cvName')"> Lock
        </c:if>
       
        </div>
      </div>
      
       <div class="form-group">
     <label class="control-label text-left col-xs-12 col-sm-5 no-padding-right" for="text">Mentor*</label>
         <div class="col-xs-12 col-sm-3">
			<div class="clearfix">
			<input type="text" name="mentor" readonly="readonly" id="mentor" value="${mentorname}" id="mentor" required class="col-xs-12 col-sm-6 form-control" placeholder="Mentor Name" />  
				<%--  <select name="mentor" required>
				<option value="">Select Mentor</option>
				 <c:forEach items="${mentoreList}" var="mentor">
				 	<option value="${mentor.labelId}" ${mentor.labelId  == userBean.mentor ? 'selected' : ' '}>${mentor.labelName}</option>
				 </c:forEach>
				</select> --%>
				
           <%-- <c:choose>
	<c:when test="${userBean.genderApprove==1}">
	<c:if test="${empty userBean.mentor}"><select class="input-medium" id="genderField" name="mentor" required></c:if>
		<c:if test="${not empty userBean.mentor}"><select class="input-medium" id="genderField" name="mentor" disabled="disabled" required></c:if>
			<option value="" selected>Select Mentor</option>
			 <c:forEach items="${mentoreList}" var="mentor">
			 	<option value="${mentor.labelId}" ${mentor.labelId  == userBean.mentor ? 'selected' : ' '}>${mentor.labelName}</option>
			 </c:forEach>
			 <input type="hidden" name="mentor" value="${userBean.mentor}"/> 
			</select>	
	</c:when><c:otherwise>
	<c:if test="${empty userBean.mentor}"><select class="input-medium" id="genderField" name="mentor" required></c:if>
		<c:if test="${not empty userBean.mentor}"><select class="input-medium" id="genderField" name="mentor" required></c:if>
			<option value="" selected>Select Mentor</option>
			 <c:forEach items="${mentoreList}" var="mentor">
			 	<option value="${mentor.labelId}" ${mentor.labelId  == userBean.mentor ? 'selected' : ' '}>${mentor.labelName}</option>
			 </c:forEach>
			</select>	
	</c:otherwise></c:choose> --%>
       
       </div></div> 
         <div class="col-sm-4 text-center">
           <c:if test="${sessionBean.roleID==3}">
          <input id="mentorApprove" type="checkbox" name="mentorApprove" value="2"> Approve <input id="mentorLock" type="checkbox" name="mentorLock" value="1" onclick="checkBlankValue('mentor')"> Lock
        </c:if>
         
        </div>
      </div>
        <div class="form-group">
		<label class="control-label col-xs-12 col-sm-5 no-padding-right" for="platform">Gender*</label>
<div class="col-xs-12 col-sm-3">
	<div class="clearfix">
	<%-- <c:if test="${empty userBean.gender}"><select class="input-medium" id="genderField" name="gender"></c:if>
		<c:if test="${not empty userBean.gender}"><select class="input-medium" id="genderField" name="gender" disabled></c:if>
			<option value="" selected>----Select Gender----</option>
			<c:if test="${userBean.gender ==0}">
			<option value="0" selected>Male</option>
			<option value="1" >Female</option>
			</c:if>
			<c:if test="${userBean.gender ==1 }">
			<option value="0" >Male</option>
			<option value="1" selected>Female</option>
			</c:if>
			<c:if test="${empty userBean.gender || userBean.gender==null ||userBean.gender==''}">
			<option value="0">Male</option>
			<option value="1">Female</option>
			</c:if>
			</select>	 --%>
		<c:choose>
	<c:when test="${userBean.genderApprove==1}">
	<c:if test="${empty userBean.gender}"><select class="input-medium" id="genderField" name="gender" required></c:if>
		<c:if test="${not empty userBean.gender}"><select class="input-medium" id="genderField" name="gender" disabled="disabled" required></c:if>
			<option value="" selected>----Select Gender----</option>
			<c:if test="${userBean.gender ==0}">
			<option value="0" selected>Male</option>
			<option value="1" >Female</option>
			<option value="2">Other</option>
			</c:if>
			<c:if test="${userBean.gender ==1 }">
			<option value="0" >Male</option>
			<option value="1" selected>Female</option>
			<option value="2">Other</option>
			</c:if>
			<c:if test="${userBean.gender ==2 }">
			<option value="0" >Male</option>
			<option value="1" >Female</option>
			<option value="2" selected>Other</option>
			</c:if>
			<c:if test="${empty userBean.gender || userBean.gender==null ||userBean.gender==''}">
			<option value="0">Male</option>
			<option value="1">Female</option>
			<option value="2">Other</option>
			</c:if>
			 <input type="hidden" name="gender" value="${userBean.gender}"/> 
			</select>	
	</c:when><c:otherwise>
	<c:if test="${empty userBean.gender}"><select class="input-medium" id="genderField" name="gender" required></c:if>
		<c:if test="${not empty userBean.gender}"><select class="input-medium" id="genderField" name="gender" required></c:if>
			<option value="" selected>----Select Gender----</option>
			<c:if test="${userBean.gender ==0}">
			<option value="0" selected>Male</option>
			<option value="1" >Female</option>
			<option value="2">Other</option>
			</c:if>
			<c:if test="${userBean.gender ==1 }">
			<option value="0" >Male</option>
			<option value="1" selected>Female</option>
			<option value="2">Other</option>
			</c:if>
			<c:if test="${userBean.gender ==2 }">
			<option value="0" >Male</option>
			<option value="1" >Female</option>
			<option value="2" selected>Other</option>
			</c:if>
			<c:if test="${empty userBean.gender || userBean.gender==null ||userBean.gender==''}">
			<option value="0">Male</option>
			<option value="1">Female</option>
			<option value="2">Other</option>
			</c:if>
			</select>	
	</c:otherwise></c:choose>
			</div>
			<%-- <input type="hidden" name="gender" value="${userBean.gender}"/> --%>
		</div>
         <div class="col-sm-4 text-center">
         
           <c:if test="${sessionBean.roleID==3}">
          <input id="genderApprove" type="checkbox" name="genderApprove" value="2"> Approve <input id="genderLock" type="checkbox" name="genderLock" value="1" onclick="checkBlankValue('gender')"> Lock
        </c:if>
        
        </div>
	</div>
        
        
    <div class="form-group">
     <label class="control-label text-left col-xs-12 col-sm-5 no-padding-right" for="text">Date of Birth</label>
        
    <div class="col-xs-12 col-sm-3 inpur_box">
         <div class="input-group">
          <input name="dateOfBirth" id="dateOfBirth" type="text" value="${userBean.dateOfBirth}"  placeholder="Date Of Birth"  class="form-control date-picker" id="id-date-picker-1" data-date-format="dd-mm-yyyy" />
       <%-- 
        <c:if test="${userBean.dateOfBirthApprove==1}">
        	
        	<c:if test="${sessionBean.mentRoleID==3 and sessionBean.roleID==1}">
        	<input name="dateOfBirth" id="dateOfBirth" type="text" value="${userBean.dateOfBirth}"  placeholder="Date Of Birth"  class="form-control date-picker" id="id-date-picker-1" data-date-format="dd-mm-yyyy" />
        	<input type="hidden" name="dateOfBirth" value="${userBean.dateOfBirth}" />
        	</c:if>
        </c:if>
        <c:if test="${userBean.dateOfBirthApprove==2}">
	        <c:if test="${sessionBean.roleID==3}">
	        <input name="dateOfBirth" id="dateOfBirth" type="text" value="${userBean.dateOfBirth}"  placeholder="Date Of Birth"  class="form-control date-picker" id="id-date-picker-1" data-date-format="dd-mm-yyyy" />
	        </c:if>
        </c:if>
         <c:if test="${userBean.dateOfBirthApprove==0}">
         <input name="dateOfBirth" id="dateOfBirth" type="text" value="${userBean.dateOfBirth}"  placeholder="Date Of Birth"  class="form-control date-picker" id="id-date-picker-1" data-date-format="dd-mm-yyyy" />
         </c:if> --%>
		
		<span class="input-group-addon">
		<i class="fa fa-calendar bigger-110"></i>
		</span>
		</div>
		
		
		
		</div>
            
                
       
         <div class="col-sm-4 text-center">
          <c:if test="${sessionBean.roleID==3}">
          <input id="dateOfBirthApprove" type="checkbox" name="dateOfBirthApprove" value="2"> Approve <input id="dateOfBirthLock" type="checkbox" name="dateOfBirthLock" value="1" onclick="checkBlankValue('dateOfBirth')"> Lock
      		</c:if>
      		 
        </div>
      </div>
    
    
    
    
     <div class="form-group">
     <label class="control-label text-left col-xs-12 col-sm-5 no-padding-right" for="text">Dorm & Room*</label>
         <div class="col-xs-12 col-sm-3">
			<div class="clearfix">
			<input type="text" value="${userBean.domainRoom}" name="domainRoom" id="dormin" required class="col-xs-12 col-sm-6 form-control" placeholder="Dorm & Room" />
				</div></div>
                
       
        <!--  <div class="col-sm-4 text-center">
          <input id="domianRoomApprove" type="checkbox" name="domianRoomApprove" value="2"> Approve <input id="domainRoomLock" type="checkbox" name="domainRoomLock" value="1" onclick="checkBlankValue('domainRoom')"> Lock
        </div> -->
         <div class="col-sm-4 text-center">
      <c:if test="${sessionBean.roleID==3}">
         <input id="domianRoomApprove" type="checkbox" name="domianRoomApproved" value="2"> Approve <input id="domianRoomLock" type="checkbox" name="domainRoomLocked" value="1" onclick="checkBlankValue('domianRoom')"> Lock  
         </c:if>
             
                    </div>                                
															</div>
      
        
        <div class="form-group">
		<label class="control-label col-xs-12 col-sm-5 no-padding-right" for="phone">Phone Extn.:</label>

																<div class="col-xs-12 col-sm-3">
																	<div class="input-group">
																		<span class="input-group-addon">
																			<i class="ace-icon fa fa-phone"></i>
																		</span>

																		<input value="${userBean.phoneEtn}" id="phoneEtn" name="phoneEtn" type="text"   class="form-control" />
																	</div>
																</div> 
                                                                
                                                                 <div class="col-sm-4 text-center">
                <c:if test="${sessionBean.roleID==3}">                                       
         <input id="phoneExtApprove" type="checkbox" name="phoneExtApprove" value="2"/> Approve <input id="phoneExtLock" type="checkbox" name="phoneExtLock" value="1" onclick="checkBlankValue('phoneExt')"/> Lock  
         </c:if>
          
          </div>
            
           
          
         
															</div>
                                                            
           <div class="form-group">
		<label class="control-label col-xs-12 col-sm-5 no-padding-right" for="Mobile">Mobile:</label>

																<div class="col-xs-12 col-sm-3">
																	<div class="input-group">
																		<span class="input-group-addon">
																			<i class="ace-icon fa fa-phone"></i>
																		</span>

																		<input value="${userBean.mobile}" type="text" id="mobile" name="mobile" required class="form-control" />
																	</div>
																</div> 
                                                                
                                                                 <div class="col-sm-4 text-center">
         <c:if test="${sessionBean.roleID==3}">   
        <input type="checkbox" name="mobileApprove" value="2" id="mobileApprove"> Approve <input id="mobileLock" type="checkbox" name="mobileLock" value="1" onclick="checkBlankValue('mobile')"> Lock  
        </c:if>
       
         </div>
                                                                
															</div>

        <div class="form-group">
												<label class="control-label col-xs-12 col-sm-5 no-padding-right" for="email">Email Address*:</label>

																<div class="col-xs-12 col-sm-3">
																	<div class="clearfix">
																		<input id="email"  type="text" value="${userBean.emailAddress}" name="emailAddress" id="email"  class="col-xs-12" required onblur="validEmail('email','Email format should be me@example.com')" readonly="readonly"/>
																	</div>
																</div>
                                                                <div class="col-sm-4 text-center">
                                                                 <c:if test="${sessionBean.roleID==3}">  
         <input id="emailAddressApprove" type="checkbox" name="emailAddressApprove" value="2"> Approve <input id="emailAddressLock" type="checkbox" name="emailAddressLock" value="1" onclick="checkBlankValue('emailAddress')"> Lock  
         </c:if>
         
          </div>
															</div>
                                                            
		<div class="form-group">
												<label class="control-label col-xs-12 col-sm-5 no-padding-right" for="email">Alternative Email ID:</label>

																<div class="col-xs-12 col-sm-3">
																	<div class="clearfix">
																		<input id="alternativeEmailid" placeholder="Alternative Email ID"  value="${userBean.alternativeEmailid}" type="text" name="alternativeEmailid" id="email1"  class="col-xs-12"  onblur="validEmail('alternativeEmailid','Email format should be me@example.com')"/>
																	</div>
																</div>
                                                                <div class="col-sm-4 text-center">
                                                                 <c:if test="${sessionBean.roleID==3}">  
         <input id="alternativeEmailApprove" type="checkbox" name="alternativeEmailApprove" value="2"> Approve <input id="alternativeEmailLock" type="checkbox" name="alternativeEmailLock" value="1" onclick="checkBlankValue('alternativeEmail')"> Lock  
         </c:if>
           
				 </div>											</div>
                         <input type="hidden" name="userProfilePkId"  value="${userBean.userProfilePkId}"/>      
                          <input type="hidden" name="userId"  value="${userBean.userId}"/>                            
             
             
                                                             
                                                            
        
        
	    </div>
  </div>
          <button class="btn btn-primary nextBtn btn-lg pull-right" type="button" >Next</button>
        </div>
   <div class="row setup-content" id="step-2">
          
              <div class="main_box section_one panel panel-primary">
     <div class="panel-heading">
         10th standard
        </div>
    	<div class="panel-body">
        <div class="row">
    
      
      
        <div class="form-group">
     
        <label class="control-label text-left col-xs-12 col-sm-5 no-padding-right" for="School">City*</label>
        <div class="col-xs-12 col-sm-3">
			<div class="clearfix">
           <input type="text" name="city" value="${userBean.city}" id="city" required class="col-xs-12 col-sm-6 form-control"  placeholder="City"/>
        </div> </div>
        <div class="col-sm-4 text-center">
         <c:if test="${sessionBean.roleID==3}">  
         <input type="checkbox" id="city10thApprove" name="city10thApprove" value="2"> Approve <input type="checkbox" name="city10thLock" id="city10thLock" onclick="checkBlankValue('city10th')" value="1"> Lock
        </c:if>
        
        </div>
      </div>
        
      
    <div class="form-group">
     
        <label class="control-label text-left col-xs-12 col-sm-5 no-padding-right" for="School">State*</label>
        <div class="col-xs-12 col-sm-3">
			<div class="clearfix">
           <input type="text" name="state" value="${userBean.state}" id="state" required class="col-xs-12 col-sm-6 form-control"  placeholder="Board"/>
        </div> </div>
        <div class="col-sm-4 text-center">
        <c:if test="${sessionBean.roleID==3}">  
         <input type="checkbox" id="state10thApprove" name="state10thApprove" value="2"> Approve <input type="checkbox" name="state10thLock" id="state10thLock" onclick="checkBlankValue('state10th')" value="1"> Lock
        </c:if>
         
        </div>
      </div>
        
     <div class="form-group">
     
        <label class="control-label text-left col-xs-12 col-sm-5 no-padding-right" for="School">Board*</label>
        <div class="col-xs-12 col-sm-3">
			<div class="clearfix">
           <input name="board" type="text" value="${userBean.board}" id="board" required class="col-xs-12 col-sm-6 form-control"  placeholder="Board"/>
        </div> </div>
        <div class="col-sm-4 text-center">
         <c:if test="${sessionBean.roleID==3}"> 
         <input type="checkbox" name="board10thApprove" id="board10thApprove" value="2"> Approve <input type="checkbox" name="board10thLock" id="board10thLock" onclick="checkBlankValue('board10th')" value="1"> Lock
       	</c:if>
       	
        </div>
      </div>
    
      <div class="form-group">
     
        <label class="control-label text-left col-xs-12 col-sm-5 no-padding-right" for="School">School*</label>
        <div class="col-xs-12 col-sm-3">
			<div class="clearfix">
           <input name="school" type="text" value="${userBean.school}" id="school" required class="col-xs-12 col-sm-6 form-control"  placeholder="School"/>
        </div> </div>
        <div class="col-sm-4 text-center">
        <c:if test="${sessionBean.roleID==3}"> 
         <input type="checkbox" name="school10thApprove" id="school10thApprove" value="2"> Approve <input type="checkbox" name="school10thLock" id="school10thLock" onclick="checkBlankValue('school10th')" value="1"> Lock
        </c:if>
         
        </div>
      </div>
    
    
    
      <div class="form-group">
     <label class="control-label text-left col-xs-12 col-sm-5 no-padding-right" for="Percentage">Percentage*</label>
         <div class="col-xs-12 col-sm-3">
			<div class="clearfix">
			 <input name="percentage" type="text"  value="${userBean.percentage}" id="percentage" required class="col-xs-12 col-sm-6 form-control"  placeholder="Percentage" onblur="checkNumber('percentage','Please enter only numaric values')"/>
				</div></div>
                
       
         <div class="col-sm-4 text-center">
          <c:if test="${sessionBean.roleID==3}"> 
          <input type="checkbox" name="percentage10thApprove" id="percentage10thApprove" value="2"> Approve <input type="checkbox" name="percentage10thLock" id="percentage10thLock" onclick="checkBlankValue('percentage10th')" value="1"> Lock
        </c:if>
        
        </div>
      </div>
    
    
        
    	</div>
    </div>
             <div class="space"></div>  
              
            </div>
             <div class="main_box section_one panel panel-primary">
     <div class="panel-heading">
        12th standard
        </div>
    
    
    	<div class="panel-body">
        <div class="row">
    
      
      
        <div class="form-group">
     
        <label class="control-label text-left col-xs-12 col-sm-5 no-padding-right" for="School">City*</label>
        <div class="col-xs-12 col-sm-3">
			<div class="clearfix">
           <input name="cityTwelve" type="text" value="${userBean.cityTwelve}" id="cityTwelve" required class="col-xs-12 col-sm-6 form-control"  placeholder="City"/>
        </div> </div>
        <div class="col-sm-4 text-center">
         <c:if test="${sessionBean.roleID==3}"> 
         <input type="checkbox" name="city12thApprove" id="city12thApprove" value="2"> Approve <input type="checkbox" name="city12thLock" id="city12thLock" value="1" onclick="checkBlankValue('city12th')"> Lock
        </c:if>
        
        </div>
      </div>
        
      
    <div class="form-group">
     
        <label class="control-label text-left col-xs-12 col-sm-5 no-padding-right" for="School">State*</label>
        <div class="col-xs-12 col-sm-3">
			<div class="clearfix">
           <input name="stateTwelve" type="text" value="${userBean.stateTwelve}" id="stateTwelve" required class="col-xs-12 col-sm-6 form-control"  placeholder="Board"/>
        </div> </div>
        <div class="col-sm-4 text-center">
          <c:if test="${sessionBean.roleID==3}"> 
         <input type="checkbox" id="state12thApprove" name="state12thApprove" value="2"> Approve <input type="checkbox" name="state12thLock" id="state12thLock" value="1" onclick="checkBlankValue('state12th')"> Lock
       </c:if>
        
        </div>
      </div>
        
     <div class="form-group">
     
        <label class="control-label text-left col-xs-12 col-sm-5 no-padding-right" for="School">Board*</label>
        <div class="col-xs-12 col-sm-3">
			<div class="clearfix">
           <input name="boardTwelve" type="text" value="${userBean.boardTwelve}" id="boardTwelve" required class="col-xs-12 col-sm-6 form-control"  placeholder="Board"/>
        </div> </div>
        <div class="col-sm-4 text-center">
           <c:if test="${sessionBean.roleID==3}"> 
         <input type="checkbox" name="board12thApprove" id="board12thApprove" value="2"> Approve <input type="checkbox" name="board12thLock" id="board12thLock" value="1" onclick="checkBlankValue('state12th')"> Lock
       </c:if>
       
        </div>
      </div>
    
      <div class="form-group">
     
        <label class="control-label text-left col-xs-12 col-sm-5 no-padding-right" for="School">School*</label>
        <div class="col-xs-12 col-sm-3">
			<div class="clearfix">
           <input name="schoolTwelve" type="text" value="${userBean.schoolTwelve}" id="schoolTwelve" required class="col-xs-12 col-sm-6 form-control"  placeholder="School"/>
        </div> </div>
        <div class="col-sm-4 text-center">
          <c:if test="${sessionBean.roleID==3}"> 
         <input type="checkbox" name="school12thApprove" id="school12thApprove" value="2"> Approve <input type="checkbox" name="school12thLock" id="school12thLock" value="1" onclick="checkBlankValue('school12th')"> Lock
       </c:if>
        
        </div>
      </div>
    
    
    
      <div class="form-group">
     <label class="control-label text-left col-xs-12 col-sm-5 no-padding-right" for="Percentage">Percentage*</label>
         <div class="col-xs-12 col-sm-3">
			<div class="clearfix">
			 <input name="percenatageTwelve" type="text" value="${userBean.percenatageTwelve}" id="percenatageTwelve" required class="col-xs-12 col-sm-6 form-control"  placeholder="Percentage" onblur="checkNumber('percenatageTwelve','Please enter only numaric values')"/>
				</div></div>
                
       
         <div class="col-sm-4 text-center">
            <c:if test="${sessionBean.roleID==3}"> 
          <input type="checkbox" name="percentage12thApprove" id="percentage12thApprove" value="2"> Approve <input type="checkbox" name="percentage12thLock" id="percentage12thLock" value="1" onclick="checkBlankValue('percentage12th')"> Lock
        </c:if>
         
        </div>
      </div>
    
    
        
    	</div>
    </div> 
     <div class="space"></div>  
   <!--  <button class="btn btn-primary prevBtn btn-lg pull-left" type="button">Previous</button>
      <button class="btn btn-primary nextBtn btn-lg pull-right" type="button" >Next</button> -->
    </div>
     <button class="btn btn-primary prevBtn btn-lg pull-left" type="button">Previous</button>
     <button class="btn btn-primary nextBtn btn-lg pull-right" type="button" >Next</button>
        </div>
        
      					    <div class="row setup-content" id="step-3">
        
       <%--  <div class="main_box section_one panel panel-primary">
     <div class="panel-heading">
        12th standard
        </div>
    
    
    	<div class="panel-body">
        <div class="row">
    
      
      
        <div class="form-group">
     
        <label class="control-label text-left col-xs-12 col-sm-5 no-padding-right" for="School">City</label>
        <div class="col-xs-12 col-sm-3">
			<div class="clearfix">
           <input name="cityTwelve" type="text" value="${userBean.cityTwelve}" id="cityTwelve" required class="col-xs-12 col-sm-6 form-control"  placeholder="City"/>
        </div> </div>
        <div class="col-sm-4 text-center">
         <c:if test="${sessionBean.roleID==3}"> 
         <input type="checkbox" name="city12thApprove" id="city12thApprove" value="2"> Approve <input type="checkbox" name="city12thLock" id="city12thLock" value="1" onclick="checkBlankValue('city12th')"> Lock
        </c:if>
        
        </div>
      </div>
        
      
    <div class="form-group">
     
        <label class="control-label text-left col-xs-12 col-sm-5 no-padding-right" for="School">State</label>
        <div class="col-xs-12 col-sm-3">
			<div class="clearfix">
           <input name="stateTwelve" type="text" value="${userBean.stateTwelve}" id="stateTwelve" required class="col-xs-12 col-sm-6 form-control"  placeholder="Board"/>
        </div> </div>
        <div class="col-sm-4 text-center">
          <c:if test="${sessionBean.roleID==3}"> 
         <input type="checkbox" id="state12thApprove" name="state12thApprove" value="2"> Approve <input type="checkbox" name="state12thLock" id="state12thLock" value="1" onclick="checkBlankValue('state12th')"> Lock
       </c:if>
        
        </div>
      </div>
        
     <div class="form-group">
     
        <label class="control-label text-left col-xs-12 col-sm-5 no-padding-right" for="School">Board</label>
        <div class="col-xs-12 col-sm-3">
			<div class="clearfix">
           <input name="boardTwelve" type="text" value="${userBean.boardTwelve}" id="boardTwelve" required class="col-xs-12 col-sm-6 form-control"  placeholder="Board"/>
        </div> </div>
        <div class="col-sm-4 text-center">
           <c:if test="${sessionBean.roleID==3}"> 
         <input type="checkbox" name="board12thApprove" id="board12thApprove" value="2"> Approve <input type="checkbox" name="board12thLock" id="board12thLock" value="1" onclick="checkBlankValue('state12th')"> Lock
       </c:if>
       
        </div>
      </div>
    
      <div class="form-group">
     
        <label class="control-label text-left col-xs-12 col-sm-5 no-padding-right" for="School">School</label>
        <div class="col-xs-12 col-sm-3">
			<div class="clearfix">
           <input name="schoolTwelve" type="text" value="${userBean.schoolTwelve}" id="schoolTwelve" required class="col-xs-12 col-sm-6 form-control"  placeholder="School"/>
        </div> </div>
        <div class="col-sm-4 text-center">
          <c:if test="${sessionBean.roleID==3}"> 
         <input type="checkbox" name="school12thApprove" id="school12thApprove" value="2"> Approve <input type="checkbox" name="school12thLock" id="school12thLock" value="1" onclick="checkBlankValue('school12th')"> Lock
       </c:if>
        
        </div>
      </div>
    
    
    
      <div class="form-group">
     <label class="control-label text-left col-xs-12 col-sm-5 no-padding-right" for="Percentage">Percentage</label>
         <div class="col-xs-12 col-sm-3">
			<div class="clearfix">
			 <input name="percenatageTwelve" type="text" value="${userBean.percenatageTwelve}" id="percenatageTwelve" required class="col-xs-12 col-sm-6 form-control"  placeholder="Percentage" onblur="checkNumber('percenatageTwelve','Please enter only numaric values')"/>
				</div></div>
                
       
         <div class="col-sm-4 text-center">
            <c:if test="${sessionBean.roleID==3}"> 
          <input type="checkbox" name="percentage12thApprove" id="percentage12thApprove" value="2"> Approve <input type="checkbox" name="percentage12thLock" id="percentage12thLock" value="1" onclick="checkBlankValue('percentage12th')"> Lock
        </c:if>
         
        </div>
      </div>
    
    
        
    	</div>
    </div> 
     <div class="space"></div>  
    <button class="btn btn-primary prevBtn btn-lg pull-left" type="button">Previous</button>
      <button class="btn btn-primary nextBtn btn-lg pull-right" type="button" >Next</button>
    </div> --%>
        
           </div>
           
        <div class="row setup-content" id="step-4">
        
        <div class="main_box section_one panel panel-primary">
     <div class="panel-heading">
       Graduate Degree
        </div>
    	<div class="panel-body">
       <div class="form-group">
     <label class="control-label text-left col-xs-12 col-sm-5 no-padding-right" for="Percentage">Percentage*</label>
         <div class="col-xs-12 col-sm-3">
			<div class="clearfix">
			 <input name="percentageGraduate"  type="text" value="${userBean.percentageGraduate}" id="percentageGraduate" required class="col-xs-12 col-sm-6 form-control"  placeholder="Percentage" onblur="checkNumber('percentageGraduate','Please enter only numaric values')"/>
				</div></div>
                
       
         <div class="col-sm-4 text-center">
          <c:if test="${sessionBean.roleID==3}"> 
          <input type="checkbox" name="graduatePercentageApprove" id="graduatePercentageApprove" value="2"> Approve <input type="checkbox" name="graduatePercentageLock" id="graduatePercentageLock" value="1" onclick="checkBlankValue('graduatePercentage')"> Lock
        </c:if>
          
        </div>
      </div>
      
           <div class="form-group">
       <label class="control-label text-left col-xs-12 col-sm-5 no-padding-right" for="Graduate Degree">Graduate Degree*</label>
      
        <div class="col-sm-3 inpur_box">
        	<div class="clearfix">
          <!-- <select class="form-control" id="form-field-select-1" name="graduteDegree">
            <option value="volvo">Yes</option>
           
            <option value="saab">No</option>
          </select> -->
           <input name="graduteDegree"  type="text" value="${userBean.graduteDegree}" id="graduteDegree" required class="col-xs-12 col-sm-6 form-control"  placeholder="Degree"/>
        </div> </div>
        <div class="col-sm-4 text-center">
         <c:if test="${sessionBean.roleID==3}"> 
       <input type="checkbox" name="graduateDregreeApprove" id="graduateDregreeApprove" value="2"> Approve <input type="checkbox" name="graduateDregreeLock" id="graduateDregreeLock" value="1" onclick="checkBlankValue('graduateDregree')"> Lock
       </c:if>
       
        </div>
      </div>
  
    <div class="form-group">
       <label class="control-label text-left col-xs-12 col-sm-5 no-padding-right" for="Department">Department*</label>
      
        <div class="col-sm-3 inpur_box">
        	<div class="clearfix">
        <!--  <select class="form-control" id="form-field-select-1" name="department">
            <option value="volvo">Select</option>
            
            <option value="saab">CS</option>
          </select> -->
           <input name="department"  type="text" value="${userBean.department}" id="graduteDepartment" required class="col-xs-12 col-sm-6 form-control"  placeholder="Department"/>
        </div> </div>
        <div class="col-sm-4 text-center">
           <c:if test="${sessionBean.roleID==3}"> 
       <input type="checkbox" name="graduateDepartmentApprove" id="graduateDepartmentApprove" value="2"> Approve <input type="checkbox" name="graduateDepartmentLock" id="graduateDepartmentLock" value="1" onclick="checkBlankValue('graduateDepartment')"> Lock
       </c:if>
       
        </div>
      </div>
    
    
        <div class="form-group">
     <label class="control-label text-left col-xs-12 col-sm-5 no-padding-right" for="Institute">Institute*</label>
         <div class="col-xs-12 col-sm-3">
			<div class="clearfix">
			 <input type="text" required  value="${userBean.institute}" id="institute" class="col-xs-12 col-sm-6 form-control"  placeholder="Institute" name="institute"/>
				</div></div>
                
       
         <div class="col-sm-4 text-center">
           <c:if test="${sessionBean.roleID==3}"> 
          <input type="checkbox" name="grdauateInstituteApprove" id="graduatedInstituteApprove" value="2"> Approve <input value="1" type="checkbox" name="graduatedInstituteLock" id="graduatedInstituteLock" onclick="checkBlankValue('graduatedInstitute')"> Lock
        </c:if>
        
        </div>
      </div>
      
       <div class="form-group">
     <label class="control-label text-left col-xs-12 col-sm-5 no-padding-right" for="Abbreviation">Abbreviation*</label>
         <div class="col-xs-12 col-sm-3">
			<div class="clearfix">
			
			 <input type="text" value="${userBean.abbreviation}" id="Abbreviation" required class="col-xs-12 col-sm-6 form-control"  placeholder="Abbreviation" name="Abbreviation"/>
				</div></div>
                
       
         <div class="col-sm-4 text-center">
           <c:if test="${sessionBean.roleID==3}"> 
          <input type="checkbox" name="grdaduateAbbrivationApprove" id="grdaduateAbbrivationApprove" value="2"> Approve <input type="checkbox" value="1" name="grdaduateAbbrivationLock" id="grdaduateAbbrivationLock" onclick="checkBlankValue('grdaduateAbbrivation')"> Lock
        </c:if>
         
        </div>
      </div>
       <div class="form-group">
     <label class="control-label text-left col-xs-12 col-sm-5 no-padding-right" for="Location">Location*</label>
         <div class="col-xs-12 col-sm-3">
			<div class="clearfix">
			 <input type="text" value="${userBean.location}" id="location" required class="col-xs-12 col-sm-6 form-control"  placeholder="Location" name="location"/>
				</div></div>
                
       
         <div class="col-sm-4 text-center">
          <c:if test="${sessionBean.roleID==3}"> 
          <input type="checkbox" name="graduateLocationApprove" id="graduateLocationApprove" value="2"> Approve <input type="checkbox" name="graduateLocationLock" id="graduateLocationLock" value="1" onclick="checkBlankValue('graduateLocation')"> Lock
       		</c:if>
       		
        </div>
      </div>
      
       <div class="form-group">
     <label class="control-label text-left col-xs-12 col-sm-5 no-padding-right" for="CGPA">CGPA</label>
         <div class="col-xs-12 col-sm-3">
			<div class="clearfix">
			 <input type="text" value="${userBean.cgpa}" id="cgpa"  class="col-xs-12 col-sm-6 form-control"  placeholder="CGPA" name="cgpa" onblur="checkNumber('cgpa','Please enter only numaric values')"/>
				</div></div>
                
       
         <div class="col-sm-4 text-center">
           <c:if test="${sessionBean.roleID==3}"> 
          <input type="checkbox" name="graduateCgpaApprove" id="graduateCgpaApprove" value="2"> Approve <input type="checkbox" name="graduateCgpaLock" id="graduateCgpaLock" value="1" onclick="checkBlankValue('graduateCgpa')"> Lock
        </c:if>
        
        </div>
      </div>
      
       <div class="form-group">
     <label class="control-label text-left col-xs-12 col-sm-5 no-padding-right" for="CGPA Scale">CGPA Scale</label>
         <div class="col-xs-12 col-sm-3">
			<div class="clearfix">
			 <input type="text" value="${userBean.cgpaScale}" id="cgpaScale"  class="col-xs-12 col-sm-6 form-control"  placeholder="CGPA Scale" name="cgpaScale" onblur="checkNumber('cgpaScale','Please enter only numaric values')"/>
				</div></div>
                
       
         <div class="col-sm-4 text-center">
           <c:if test="${sessionBean.roleID==3}"> 
          <input type="checkbox" name="graduateCgpaScaleApprove" id="graduateCgpaScaleApprove" value="2"> Approve <input type="checkbox" name="graduateCgpaScaleLock" id="graduateCgpaScaleLock" value="1" onclick="checkBlankValue('graduateCgpaScale')"> Lock
        </c:if>
        
        </div>
      </div>
      
      
    	</div>
    </div>
     <label class="control-label text-left col-xs-12 col-sm-2 no-padding-right" for="CGPA Scale"> Postgraduate Degree</label>
    <input type="checkbox" name="postGrdCheckBox" id="postGrdCheckBox" onclick="openPg()"/><br/><br/>
            <div class="main_box section_one panel panel-primary" style="display: none;" id="pgDiv">
     <div class="panel-heading">
       Postgraduate Degree
        </div>
    
    
    	<div class="panel-body">
        
        <div class="form-group">
     <label class="control-label text-left col-xs-12 col-sm-5 no-padding-right" for="Postgraduate Degree">Postgraduate Degree*</label>
         <div class="col-xs-12 col-sm-3">
			<div class="clearfix">
			 <input type="text" name="postgraduateDegree" value="${userBean.postgraduateDegree}" id="postgraduateDegree" required class="col-xs-12 col-sm-6 form-control"  placeholder="Postgraduate Degree"/>
				</div></div>
                
       
         <div class="col-sm-4 text-center">
         <c:if test="${sessionBean.roleID==3}"> 
          <input type="checkbox" name="pgDegreeApprove" id="pgDegreeApprove" value="2"> Approve <input type="checkbox" name="pgDegreeLock" id="pgDegreeLock" value="1" onclick="checkBlankValue('pgDegree')"> Lock
        </c:if>
        
        </div>
      </div>
       <div class="form-group">
     <label class="control-label text-left col-xs-12 col-sm-5 no-padding-right" for="Department">Department*</label>
         <div class="col-xs-12 col-sm-3">
			<div class="clearfix">
			 <input name="postgraduateDepartment" type="text" value="${userBean.postgraduateDepartment}" id="postgraduateDepartment" required class="col-xs-12 col-sm-6 form-control"  placeholder="Department"/>
				</div></div>
                
       
         <div class="col-sm-4 text-center">
          <c:if test="${sessionBean.roleID==3}"> 
          <input type="checkbox" name="pgDepartmentApprove" value="2" id="pgDepartmentApprove"> Approve <input type="checkbox" name="pgDepartmentLock" id="pgDepartmentLock" value="1" onclick="checkBlankValue('pgDepartment')"> Lock
        </c:if>
       
        </div>
      </div>
      
      <div class="form-group">
     <label class="control-label text-left col-xs-12 col-sm-5 no-padding-right" for="Institute">Institute*</label>
         <div class="col-xs-12 col-sm-3">
			<div class="clearfix">
			 <input name="postgraduateInstitute" type="text" value="${userBean.postgraduateInstitute}" id="postgraduateInstitute" required class="col-xs-12 col-sm-6 form-control"  placeholder="Institute"/>
				</div></div>
              
         <div class="col-sm-4 text-center">
          <c:if test="${sessionBean.roleID==3}"> 
          <input type="checkbox" name="pgInstituteApprove" id="pgInstituteApprove" value="2"> Approve <input type="checkbox" name="pgInstituteLock" id="pgInstituteLock" value="1" onclick="checkBlankValue('pgInstitute')"> Lock
        </c:if>
       
        </div>
      </div>
      
        <div class="form-group">
     <label class="control-label text-left col-xs-12 col-sm-5 no-padding-right" for="Abbreviation">Abbreviation*</label>
         <div class="col-xs-12 col-sm-3">
			<div class="clearfix">
			 <input name="postgraduateAbbreviation" type="text" value="${userBean.postgraduateAbbreviation}" id="postgraduateAbbreviation" required class="col-xs-12 col-sm-6 form-control"  placeholder="Abbreviation"/>
				</div></div>
              
         <div class="col-sm-4 text-center">
           <c:if test="${sessionBean.roleID==3}"> 
          <input type="checkbox" name="pgAbbrivationApprove" id="pgAbbrivationApprove" value="2"> Approve <input type="checkbox" name="pgAbbrivationLock" id="pgAbbrivationLock" value="1" onclick="checkBlankValue('pgAbbrivation')"> Lock
       </c:if>
        
        </div>
      </div>
      
       <div class="form-group">
     <label class="control-label text-left col-xs-12 col-sm-5 no-padding-right" for="CGPA">CGPA</label>
         <div class="col-xs-12 col-sm-3">
			<div class="clearfix">
			 <input name="postgraduateCGPA" type="text" value="${userBean.postgraduateCGPA}" id="postgraduateCGPA"  class="col-xs-12 col-sm-6 form-control"  placeholder="CGPA" onblur="checkNumber('postgraduateCGPA','Please enter only numaric values')"/>
				</div></div>
              
         <div class="col-sm-4 text-center">
            <c:if test="${sessionBean.roleID==3}"> 
          <input type="checkbox" name="pgCgpaApprove" id="pgCgpaApprove" value="2"> Approve <input type="checkbox" name="pgCgpaLock" id="pgCgpaLock" value="1" onclick="checkBlankValue('pgCgpa')"> Lock
        </c:if>
         
        </div>
      </div>
       <div class="form-group">
     <label class="control-label text-left col-xs-12 col-sm-5 no-padding-right" for="CGPA Scale">CGPA Scale</label>
         <div class="col-xs-12 col-sm-3">
			<div class="clearfix">
			 <input name="postgraduateCGPAScale" value="${userBean.postgraduateCGPAScale}" id="postgraduateCGPAScale" type="text"  class="col-xs-12 col-sm-6 form-control"  placeholder="CGPA Scale" onblur="checkNumber('postgraduateCGPAScale','Please enter only numaric values')"/>
				</div></div>
              
         <div class="col-sm-4 text-center">
           <c:if test="${sessionBean.roleID==3}"> 
          <input type="checkbox" name="pgcgpaScaleApprove" id="cgpaScaleApprove" value="2"> Approve <input type="checkbox" name="pgcgpaScaleLock" id="cgpaScaleLock" value="1" onclick="checkBlankValue('cgpaScale')"> Lock
        </c:if>
         
        </div>
      </div>
      
        <div class="form-group">
     <label class="control-label text-left col-xs-12 col-sm-5 no-padding-right" for="Percentage">Percentage</label>
         <div class="col-xs-12 col-sm-3">
			<div class="clearfix">
			 <input name="postgraduatePercentage" value="${userBean.postgraduatePercentage}" id="postgraduatePercentage" type="text"  class="col-xs-12 col-sm-6 form-control"  placeholder="Percentage" onblur="checkNumber('postgraduatePercentage','Please enter only numaric values')"/>
				</div></div>
              
         <div class="col-sm-4 text-center">
          <c:if test="${sessionBean.roleID==3}"> 
          <input type="checkbox" name="pgPercentageApprove" id="pgPercentageApprove" value="2"> Approve <input type="checkbox" name="pgPercentageLock" id="pgPercentageLock" value="1" onclick="checkBlankValue('pgPercentage')"> Lock
       </c:if>
         
        </div>
      </div>
        
        
        
    	</div>
    </div>
     <button class="btn btn-primary prevBtn btn-lg pull-left" type="button">Previous</button>
        <button class="btn btn-primary nextBtn btn-lg pull-right" type="button" >Next</button>
           </div>
           <div class="row setup-content" id="step-5">
        
       <%--  <div class="main_box section_one panel panel-primary">
     <div class="panel-heading">
       Postgraduate Degree
        </div>
    
    
    	<div class="panel-body">
        
        <div class="form-group">
     <label class="control-label text-left col-xs-12 col-sm-5 no-padding-right" for="Postgraduate Degree">Postgraduate Degree</label>
         <div class="col-xs-12 col-sm-3">
			<div class="clearfix">
			 <input type="text" name="postgraduateDegree" value="${userBean.postgraduateDegree}" id="postgraduateDegree" required class="col-xs-12 col-sm-6 form-control"  placeholder="Postgraduate Degree"/>
				</div></div>
                
       
         <div class="col-sm-4 text-center">
         <c:if test="${sessionBean.roleID==3}"> 
          <input type="checkbox" name="pgDegreeApprove" id="pgDegreeApprove" value="2"> Approve <input type="checkbox" name="pgDegreeLock" id="pgDegreeLock" value="1" onclick="checkBlankValue('pgDegree')"> Lock
        </c:if>
        
        </div>
      </div>
       <div class="form-group">
     <label class="control-label text-left col-xs-12 col-sm-5 no-padding-right" for="Department">Department</label>
         <div class="col-xs-12 col-sm-3">
			<div class="clearfix">
			 <input name="postgraduateDepartment" type="text" value="${userBean.postgraduateDepartment}" id="postgraduateDepartment" required class="col-xs-12 col-sm-6 form-control"  placeholder="Department"/>
				</div></div>
                
       
         <div class="col-sm-4 text-center">
          <c:if test="${sessionBean.roleID==3}"> 
          <input type="checkbox" name="pgDepartmentApprove" value="2" id="pgDepartmentApprove"> Approve <input type="checkbox" name="pgDepartmentLock" id="pgDepartmentLock" value="1" onclick="checkBlankValue('pgDepartment')"> Lock
        </c:if>
       
        </div>
      </div>
      
      <div class="form-group">
     <label class="control-label text-left col-xs-12 col-sm-5 no-padding-right" for="Institute">Institute</label>
         <div class="col-xs-12 col-sm-3">
			<div class="clearfix">
			 <input name="postgraduateInstitute" type="text" value="${userBean.postgraduateInstitute}" id="postgraduateInstitute" required class="col-xs-12 col-sm-6 form-control"  placeholder="Institute"/>
				</div></div>
              
         <div class="col-sm-4 text-center">
          <c:if test="${sessionBean.roleID==3}"> 
          <input type="checkbox" name="pgInstituteApprove" id="pgInstituteApprove" value="2"> Approve <input type="checkbox" name="pgInstituteLock" id="pgInstituteLock" value="1" onclick="checkBlankValue('pgInstitute')"> Lock
        </c:if>
       
        </div>
      </div>
      
        <div class="form-group">
     <label class="control-label text-left col-xs-12 col-sm-5 no-padding-right" for="Abbreviation">Abbreviation</label>
         <div class="col-xs-12 col-sm-3">
			<div class="clearfix">
			 <input name="postgraduateAbbreviation" type="text" value="${userBean.postgraduateAbbreviation}" id="postgraduateAbbreviation" required class="col-xs-12 col-sm-6 form-control"  placeholder="Abbreviation"/>
				</div></div>
              
         <div class="col-sm-4 text-center">
           <c:if test="${sessionBean.roleID==3}"> 
          <input type="checkbox" name="pgAbbrivationApprove" id="pgAbbrivationApprove" value="2"> Approve <input type="checkbox" name="pgAbbrivationLock" id="pgAbbrivationLock" value="1" onclick="checkBlankValue('pgAbbrivation')"> Lock
       </c:if>
        
        </div>
      </div>
      
       <div class="form-group">
     <label class="control-label text-left col-xs-12 col-sm-5 no-padding-right" for="CGPA">CGPA</label>
         <div class="col-xs-12 col-sm-3">
			<div class="clearfix">
			 <input name="postgraduateCGPA" type="text" value="${userBean.postgraduateCGPA}" id="postgraduateCGPA" required class="col-xs-12 col-sm-6 form-control"  placeholder="CGPA" onblur="checkNumber('postgraduateCGPA','Please enter only numaric values')"/>
				</div></div>
              
         <div class="col-sm-4 text-center">
            <c:if test="${sessionBean.roleID==3}"> 
          <input type="checkbox" name="pgCgpaApprove" id="pgCgpaApprove" value="2"> Approve <input type="checkbox" name="pgCgpaLock" id="pgCgpaLock" value="1" onclick="checkBlankValue('pgCgpa')"> Lock
        </c:if>
         
        </div>
      </div>
       <div class="form-group">
     <label class="control-label text-left col-xs-12 col-sm-5 no-padding-right" for="CGPA Scale">CGPA Scale</label>
         <div class="col-xs-12 col-sm-3">
			<div class="clearfix">
			 <input name="postgraduateCGPAScale" value="${userBean.postgraduateCGPAScale}" id="postgraduateCGPAScale" type="text" required class="col-xs-12 col-sm-6 form-control"  placeholder="CGPA Scale" onblur="checkNumber('postgraduateCGPAScale','Please enter only numaric values')"/>
				</div></div>
              
         <div class="col-sm-4 text-center">
           <c:if test="${sessionBean.roleID==3}"> 
          <input type="checkbox" name="pgcgpaScaleApprove" id="cgpaScaleApprove" value="2"> Approve <input type="checkbox" name="pgcgpaScaleLock" id="cgpaScaleLock" value="1" onclick="checkBlankValue('cgpaScale')"> Lock
        </c:if>
         
        </div>
      </div>
      
        <div class="form-group">
     <label class="control-label text-left col-xs-12 col-sm-5 no-padding-right" for="Percentage">Percentage</label>
         <div class="col-xs-12 col-sm-3">
			<div class="clearfix">
			 <input name="postgraduatePercentage" value="${userBean.postgraduatePercentage}" id="postgraduatePercentage" type="text" required class="col-xs-12 col-sm-6 form-control"  placeholder="Percentage" onblur="checkNumber('postgraduatePercentage','Please enter only numaric values')"/>
				</div></div>
              
         <div class="col-sm-4 text-center">
          <c:if test="${sessionBean.roleID==3}"> 
          <input type="checkbox" name="pgPercentageApprove" id="pgPercentageApprove" value="2"> Approve <input type="checkbox" name="pgPercentageLock" id="pgPercentageLock" value="1" onclick="checkBlankValue('pgPercentage')"> Lock
       </c:if>
         
        </div>
      </div>
        
        
        
    	</div>
    </div> --%>
   
        <button class="btn btn-primary prevBtn btn-lg pull-left" type="button">Previous</button>
          <button class="btn btn-primary nextBtn btn-lg pull-right" type="button" >Next</button>
           </div>
            <div class="row setup-content" id="step-6">
           <!--   <div class="main_box section_one panel panel-primary"> -->
            <c:choose>
            <c:when test="${groupId==2}">
           <c:if test="${empty userBean.company}">
                 <div class="main_box section_one panel panel-primary"> 
          <div class="panel-heading"> <div class="row">   <div class="col-sm-3">  Summer Internship</div> 
      <div class="col-sm-4 pull-right text-right">
      
          <input type="checkbox" disabled="disabled"">  Approve  <input type="checkbox" disabled="disabled"> Lock
        </div>
        </div>

        </div>
		<div id="internshipboxBlank">
    	<div class="panel-body" >
        
        
              <div class="row">
              <div class="form-group">
     <label class="control-label text-left col-xs-12 col-sm-3 no-padding-right" for="Company">Company*</label>
         <div class="col-xs-12 col-sm-3">
			<div class="clearfix">
			 <input type="text" required class="col-xs-12 col-sm-3 form-control"  placeholder="Company" name="company"/>
				</div></div>
              
         <label class="control-label text-left col-xs-12 col-sm-3 no-padding-right" for="Role">Role*</label>
         <div class="col-xs-12 col-sm-3">
			<div class="clearfix">
			 <input type="text" required class="col-xs-12 col-sm-3 form-control"  placeholder="Role" name="role" maxlength="50"/>
				</div></div>
      </div> 
            </div> 
            <div class="row">
              <div class="form-group">
     <label class="control-label text-left col-xs-12 col-sm-3 no-padding-right" for="Location">Location*</label>
         <div class="col-xs-12 col-sm-3">
			<div class="clearfix">
			 <input name="locationSummerInternship" type="text" required class="col-xs-12 col-sm-3 form-control"  placeholder="Location"  maxlength="100"/>
				</div></div>
              
         <label class="control-label text-left col-xs-12 col-sm-3 no-padding-right" for="Duration">Duration(in weeks)*</label>
         <div class="col-xs-12 col-sm-3">
			<div class="clearfix">
			 <input name="DurationSummerInternship" id="DurationSummerInternship" pattern="[0-9]" type="text" required class="col-xs-12 col-sm-3 form-control"  placeholder="Duration" maxlength="30" onblur="checkNumber('DurationSummerInternship','Please enter only numaric values')">
				</div></div>
      </div> 
     
            </div>
    	</div>
    	 <input type="hidden" name="summerInternshipPkId" value="0"/>
        </div>
        </div>
           </c:if>
           <c:if test="${not empty userBean.company}">
           
         <c:forEach items="${userBean.company}" var="comp" varStatus="status">
         <div class="main_box section_one panel panel-primary" id="summ${status.count}"> 
           	<div class="panel-heading"> <div class="row">   <div class="col-sm-3">  Summer Internship</div> 
      <div class="col-sm-4 pull-right text-right">
      	<c:if test="${userBean.summerApproveId[status.index]!=1}">
     <%--  	<a style="color: white;" href="deleteSummerInternship?sumPkId=${userBean.summerInternshipPkId[status.index]}">Delete</a> --%>
      		<c:choose>
	      		<c:when test="${userBean.profileLoack=='UnLocked'}">
					<a style="color: white;" href="#" onclick="deleteSummerInternship(${userBean.summerInternshipPkId[status.index]},'summ${status.count}')">Delete</a>
				</c:when>
				<c:otherwise>
	         		<a style="color: white;" href="#">Delete</a>
	         	</c:otherwise>
      		</c:choose>
        </c:if>
       <c:if test="${sessionBean.roleID==3}"> 
          <input id="summerInternship${status.count}Approve" type="checkbox" name="summerInternshipApprove" value="2" onclick="setApproveValueSummer(${status.count})">  Approve  <input id="summerInternship${status.count}Lock" type="checkbox" name="summerInternShipLock" value="1" onclick="checkBlankValue('summerInternship${status.count}');setValues(${status.count},'summerInternShip','summerIdHidden')"> Lock
        </c:if>
        
        </div>
        </div>

        </div>
		<div id="internshipbox">
    	<div class="panel-body" >
        
        
              <div class="row">
              <div class="form-group">
     <label class="control-label text-left col-xs-12 col-sm-3 no-padding-right" for="Company">Company*</label>
         <div class="col-xs-12 col-sm-3">
			<div class="clearfix">
			 <input type="text" id="companyName${status.count}" value="${comp}" required class="col-xs-12 col-sm-3 form-control"  placeholder="Company" name="company"/>
				</div></div>
              
         <label class="control-label text-left col-xs-12 col-sm-3 no-padding-right" for="Role">Role*</label>
         <div class="col-xs-12 col-sm-3">
			<div class="clearfix">
			 <input type="text"  id="role${status.count}" value="${userBean.role[status.index]}" required class="col-xs-12 col-sm-3 form-control"  placeholder="Role" name="role" maxlength="50"/>
				</div></div>
      </div> 
            </div> 
            <div class="row">
              <div class="form-group">
     <label class="control-label text-left col-xs-12 col-sm-3 no-padding-right" for="Location">Location*</label>
         <div class="col-xs-12 col-sm-3">
			<div class="clearfix">
			 <input  id="location${status.count}" name="locationSummerInternship" value="${userBean.locationSummerInternship[status.index]}" type="text" required class="col-xs-12 col-sm-3 form-control"  placeholder="Location"/>
				</div></div>
              
         <label class="control-label text-left col-xs-12 col-sm-3 no-padding-right" for="Duration">Duration(in weeks)*</label>
         <div class="col-xs-12 col-sm-3">
			<div class="clearfix">
			 <input id="Duration${status.count}" name="DurationSummerInternship"   value="${userBean.durationSummerInternship[status.index]}" type="text" required class="col-xs-12 col-sm-3 form-control"  placeholder="Duration" title="Please enter only numaric value as months" maxlength="100" onblur="checkNumber('Duration${status.count}','Please enter only numaric values')"/>
				</div></div>
      </div> 
     
            </div>
    	</div>
        </div>
        <input type="hidden" id="summerInternshipPkId" name="summerInternshipPkId" value="${userBean.summerInternshipPkId[status.index]}"/>
       <input type="hidden" id="summerIdHidden${status.count}" name="summerIdHidden" value="0"/>
       <input type="hidden" id="summerApproveId${status.count}" name="summerApproveId" value="0"/>
        </div>
        </c:forEach>
           </c:if>
            <div id="internships"></div>
           <!--   <input type="button" value="Remove Summer Internship" id="remove-panel" class="btn btn-primary btn-lg"  style="margin-left:10px;"/>
           <input type="button" value="Add Summer Internship" id="add-panel" class="btn btn-primary btn-lg"  style="margin-left:10px;"/> -->
             <button class="btn btn-primary prevBtn btn-lg pull-left" type="button">Previous</button>
     <button class="btn btn-primary nextBtn btn-lg pull-right" type="button">Next</button>
           </c:when>
           <c:otherwise>
              <div class="main_box section_one panel panel-primary"> 
          <div class="panel-heading"> <div class="row">   <div class="col-sm-3">  Summer Internship</div> </div></div><br/><b>This is only for PGP2</b><br/></div>
           		
           		
           		  <button class="btn btn-primary prevBtn btn-lg pull-left" type="button">Previous</button>
     <button class="btn btn-primary nextBtn btn-lg pull-right" type="button">Next</button>
           </c:otherwise>
           </c:choose>
            <input type="hidden" id="hiddenSummarInstershipId" value=""/>
           
    <div id="internships"></div>
    <!-- <input type="button" value="Add Summer Internship" id="add-panel" class="btn btn-primary btn-lg"  style="margin-left:10px;"/>
      <button class="btn btn-primary prevBtn btn-lg pull-left" type="button">Previous</button>
     <button class="btn btn-primary nextBtn btn-lg pull-right" type="button">Next</button> -->
        
           </div>
           
           
            <div class="row setup-content" id="step-7">
           <c:if test="${empty userBean.otherInternShipCompany}"> 
        <div class="main_box section_one panel panel-primary">
     <div class="panel-heading"> <div class="row"> <div class="col-sm-3"> Other Internship's</div> 
      <div class="col-sm-4 pull-right text-right">
          <input type="checkbox" disabled="disabled">  Approve  <input type="checkbox" disabled="disabled"> Lock
        </div></div>
        </div>
        <div id="internshipbox1">
    	<div class="panel-body">
        
        <div class="row">
              <div class="form-group">
     <label class="control-label text-left col-xs-12 col-sm-2 no-padding-right" for="Internship 1 Company">Internship 1 Company</label>
         <div class="col-xs-12 col-sm-2">
			<div class="clearfix">
			 <input name="otherInternShipCompany" id="otherInternShipCompany" type="text"  class="col-xs-12 col-sm-12 form-control"  placeholder="Internship 1 Company" onblur="setRequiredFieldForOther('otherInternShipCompany','otherInternShipDuration','otherInternShipLocation');"/>
				</div></div>
              
         <label class="control-label text-left col-xs-12 col-sm-2 no-padding-right" for="Duration">Duration(in weeks)</label>
         <div class="col-xs-12 col-sm-2">
			<div class="clearfix">
			 <input name="otherInternShipDuration"  id="otherInternShipDuration"  type="text"  class="col-xs-12 col-sm-12 form-control"  placeholder="Duration" maxlength="30" onblur="checkNumber('otherInternShipDuration','Please enter only numaric values')"/>
				</div></div> 
                
          <label class="control-label text-left col-xs-12 col-sm-2 no-padding-right" for="Location">Location</label>
         <div class="col-xs-12 col-sm-2">
			<div class="clearfix">
			 <input name="otherInternShipLocation" id="otherInternShipLocation" type="text"  class="col-xs-12 col-sm-12 form-control"  placeholder="Location"/>
				</div> </div>
                
      </div> 
     
            </div>
            
        
        
        <input type="hidden" name="otherInternshipPkId" value="0"/>
    	</div>   
        </div>  
    </div>
    </c:if>
    <c:if test="${not empty userBean.otherInternShipCompany}">
     <c:forEach items="${userBean.otherInternShipCompany}" var="otherCompany" varStatus="status">
      <div class="main_box section_one panel panel-primary" id="otherIntern${status.count}">
     <div class="panel-heading"> <div class="row"> <div class="col-sm-3"> Other Internship's</div> 
      <div class="col-sm-4 pull-right text-right">
       <c:if test="${userBean.otherHiddenApprove[status.index]!=1}">
       		<c:choose>
	      		<c:when test="${userBean.profileLoack=='UnLocked'}">
					<a style="color: white;" href="#" onclick="deleteOtherInternship(${userBean.otherInternshipPkId[status.index]},'otherIntern${status.count}')">Delete</a>
	     			</c:when>
				<c:otherwise>
	         		<a style="color: white;" href="#">Delete</a>
	         	</c:otherwise>
     		</c:choose>
       </c:if>
        <c:if test="${sessionBean.roleID==3}"> 
          <input type="checkbox" name="otherInternshipApprove" id="otherInternship${status.count}Approve" onclick="setOtherInternshipApprove(${status.count})">  Approve  <input type="checkbox" name="otherInternshipLock" id="otherInternship${status.count}Lock" onclick="checkBlankValue('otherInternship${status.count}');setOtherInternshipValue(${status.count})"> Lock
        </c:if>
       
        </div></div>
        </div>
        <div id="internshipbox1">
    	<div class="panel-body">
        
        <div class="row">
              <div class="form-group">
     <label class="control-label text-left col-xs-12 col-sm-2 no-padding-right" for="Internship 1 Company">Internship 1 Company</label>
         <div class="col-xs-12 col-sm-2">
			<div class="clearfix">
			 <input name="otherInternShipCompany" id="otherCompanyName${status.count}" value="${otherCompany}" type="text"  class="col-xs-12 col-sm-12 form-control"  placeholder="Internship 1 Company" onblur="setRequiredFieldForOther('otherCompanyName${status.count}','otherDuration${status.count},'otherLocation${status.count}');"/>
				</div></div>
              
         <label class="control-label text-left col-xs-12 col-sm-2 no-padding-right" for="Duration">Duration(in weeks)</label>
         <div class="col-xs-12 col-sm-2">
			<div class="clearfix">
			 <input name="otherInternShipDuration" onblur="checkNumber('otherDuration${status.count}','Please enter only numaric values')" id="otherDuration${status.count}" value="${userBean.otherInternShipDuration[status.index]}" type="text"  class="col-xs-12 col-sm-12 form-control"  placeholder="Duration" maxlength="30"/>
				</div></div> 
                
          <label class="control-label text-left col-xs-12 col-sm-2 no-padding-right" for="Location">Location</label>
         <div class="col-xs-12 col-sm-2">
			<div class="clearfix">
			 <input name="otherInternShipLocation" id="otherLocation${status.count}" value="${userBean.otherInternShipLocation[status.index]}" type="text"  class="col-xs-12 col-sm-12 form-control"  placeholder="Location" />
				</div> </div>
                
      </div> 
     
            </div>
            
        
        
            <input type="hidden" name="otherInternshipPkId" value="${userBean.otherInternshipPkId[status.index]}"/>
            <input type="hidden" name="otherHiddenApprove" id="otherHiddenApprove${status.count}" value="0"/>
            <input type="hidden" name="otherHiddenLock" id="otherHiddenLock${status.count}" value="0"/>
    	</div>   
        </div>  
    </div> 
    </c:forEach>
    </c:if>
    
    <div class="clearfix"></div>
    <div id="internships1"></div>
    <!--  <input type="button" value="Add Other Internship" id="add-panel1" class="btn btn-primary btn-lg" style="margin-left:10px;" />
     <input type="button" value="Remove Other Internship" id="remove-panel1" class="btn btn-primary btn-lg" style="margin-left:10px;" /> -->
      <button class="btn btn-primary prevBtn btn-lg pull-left" type="button">Previous</button>
         <button class="btn btn-primary nextBtn btn-lg pull-right" type="button" >Next</button>
           </div>
           
           
            <div class="row setup-content" id="step-8">
         <c:if test="${empty userBean.companyNameWorkExperience}">
         <div class="main_box section_one panel panel-primary">
     <div class="panel-heading"> <div class="row"> <div class="col-sm-3"> Work Experience </div> 
      <div class="col-sm-4 pull-right text-right">
          <input type="checkbox" onclick="checkEmptyValue()" id="workExperienceApprove">  Approve  <input type="checkbox" onclick="checkEmptyValue()" id="workExperienceLock"> Lock
        </div></div>
   
        </div>
        
        <div id="internshipbox2">
    	<div class="panel-body">
        <div class="row">
              <div class="form-group">
     <label class="control-label text-left col-xs-12 col-sm-2 no-padding-right" for="Company Name">Company Name</label>
         <div class="col-xs-12 col-sm-2">
			<div class="clearfix">
			 <input name="companyNameWorkExperience" id="companyNameWorkExperience" type="text" class="col-xs-12 col-sm-12 form-control"  placeholder="Company Name" onblur="requiredFunction('companyNameWorkExperience','designationWorkExperience','durationWorkExperience','industryWorkExperience','functionAreaWorkExperience')"/>
				</div></div>
              
         <label class="control-label text-left col-xs-12 col-sm-2 no-padding-right" for="Designation">Designation</label>
         <div class="col-xs-12 col-sm-2">
			<div class="clearfix">
			 <input name="designationWorkExperience" id="designationWorkExperience" type="text"  class="col-xs-12 col-sm-12 form-control"  placeholder="Designation" onblur="requiredFunction('companyNameWorkExperience','designationWorkExperience','durationWorkExperience','industryWorkExperience','functionAreaWorkExperience')"/>
				</div></div> 
                
          <label class="control-label text-left col-xs-12 col-sm-2 no-padding-right" for="Duration">Duration(in months)*</label>
         <div class="col-xs-12 col-sm-2">
			<div class="clearfix">
			 <input name="durationWorkExperience" id="durationWorkExperience" type="text"  class="col-xs-12 col-sm-12 form-control"  placeholder="Duration" maxlength="30" onblur="requiredFunction('companyNameWorkExperience','designationWorkExperience','durationWorkExperience','industryWorkExperience','functionAreaWorkExperience');checkNumber('durationWorkExperience','Please enter only numaric values');addDuration();"/>
				</div> </div>
                
      </div> 
     
            </div> 
            
            <div class="row">
              <div class="form-group">
     <label class="control-label text-left col-xs-12 col-sm-2 no-padding-right" for="Industry">Industry</label>
         <div class="col-xs-12 col-sm-2">
			<div class="clearfix">
			 <input name="industryWorkExperience" id="industryWorkExperience" type="text"  class="col-xs-12 col-sm-12 form-control"  placeholder="Industry" onblur="requiredFunction('companyNameWorkExperience','designationWorkExperience','durationWorkExperience','industryWorkExperience','functionAreaWorkExperience')"/>
				</div></div>
              
         <label class="control-label text-left col-xs-12 col-sm-2 no-padding-right" for="Function Area">Function Area</label>
         <div class="col-xs-12 col-sm-2">
			<div class="clearfix">
			 <input name="functionAreaWorkExperience" id="functionAreaWorkExperience" type="text"  class="col-xs-12 col-sm-12 form-control"  placeholder="Function Area" onblur="requiredFunction('companyNameWorkExperience','designationWorkExperience','durationWorkExperience','industryWorkExperience','functionAreaWorkExperience')"/>
				</div></div> 
                
         <!--  <label class="control-label text-left col-xs-12 col-sm-2 no-padding-right" for="Total Work Months">Total Work Months</label>
         <div class="col-xs-12 col-sm-2">
			<div class="clearfix">
			 	 <input name="totalWorkMonthsWorkExperience" id="totalWorkMonthsWorkExperience"  maxlength="100" type="text" required class="col-xs-12 col-sm-12 form-control"  placeholder="Total Work Months" onblur="checkNumber('totalWorkMonthsWorkExperience','Please enter only numaric values')"/>
				</div> </div> -->
                
      </div> 
     
            </div>
        <input type="hidden" value="0" name="workExperiencePkId"/>  
        
    	</div> 
        </div>
        
    </div>
    </c:if>
   <c:if test="${not empty userBean.companyNameWorkExperience}">
   		  <c:forEach items="${userBean.companyNameWorkExperience}" var="workExperience" varStatus="status">
   		   <div class="main_box section_one panel panel-primary" id="workExp${status.count}">
     <div class="panel-heading"> <div class="row"> <div class="col-sm-3"> Work Experience </div> 
      <div class="col-sm-4 pull-right text-right">
       <c:if test="${userBean.workHiddenApprove[status.index]!=1}">
       		<c:choose>
	      		<c:when test="${userBean.profileLoack=='UnLocked'}">
					<a style="color: white;" href="#" onclick="deleteWorkExperience(${userBean.workExperiencePkId[status.index]},'workExp${status.count}','workDuration${status.count}')">Delete</a>
       			</c:when>
				<c:otherwise>
	         		<a style="color: white;" href="#">Delete</a>
	         	</c:otherwise>
     		</c:choose>
       	
       </c:if>
        <c:if test="${sessionBean.roleID==3}"> 
          <input type="checkbox" name="workExperineceApprove" id="workExperience${status.count}Approve" onclick="setWorkApproveValue(${status.count})">  Approve  <input type="checkbox" name="workExperienceLock" id="workExperience${status.count}Lock" onclick="checkBlankValue('workExperience${status.count}');setWorkLockValues(${status.count})"> Lock
        </c:if>
       
        </div></div>
   
        </div>
        
        <div id="internshipbox2">
    	<div class="panel-body">
        <div class="row">
              <div class="form-group">
     <label class="control-label text-left col-xs-12 col-sm-2 no-padding-right" for="Company Name">Company Name*</label>
         <div class="col-xs-12 col-sm-2">
			<div class="clearfix">
			 <input name="companyNameWorkExperience" id="workCompanyName${status.count}" value="${workExperience}" type="text"  class="col-xs-12 col-sm-12 form-control"  placeholder="Company Name" onblur="requiredFunction('workCompanyName${status.count}','workDesignation${status.count}','workDuration${status.count}','workIndustry${status.count}','workFunctionalArea${status.count}')"/>
				</div></div>
              
         <label class="control-label text-left col-xs-12 col-sm-2 no-padding-right" for="Designation">Designation*</label>
         <div class="col-xs-12 col-sm-2">
			<div class="clearfix">
			 <input name="designationWorkExperience" id="workDesignation${status.count}" value="${userBean.designationWorkExperience[status.index]}" type="text"  class="col-xs-12 col-sm-12 form-control"  placeholder="Designation" onblur="requiredFunction('workCompanyName${status.count}','workDesignation${status.count}','workDuration${status.count}','workIndustry${status.count}','workFunctionalArea${status.count}')"/>
				</div></div> 
                
          <label class="control-label text-left col-xs-12 col-sm-2 no-padding-right" for="Duration">Duration(in months)*</label>
         <div class="col-xs-12 col-sm-2">
			<div class="clearfix">
			 <input name="durationWorkExperience" id="workDuration${status.count}" value="${userBean.durationWorkExperience[status.index]}" type="text"  class="col-xs-12 col-sm-12 form-control"  placeholder="Duration" maxlength="30" onblur="requiredFunction('workCompanyName${status.count}','workDesignation${status.count}','workDuration${status.count}','workIndustry${status.count}','workFunctionalArea${status.count}');checkNumber('workDuration${status.count}','Please enter only numaric values');addDuration();"/>
				</div> </div>
                
      </div> 
     
            </div> 
            
            <div class="row">
              <div class="form-group">
     <label class="control-label text-left col-xs-12 col-sm-2 no-padding-right" for="Industry">Industry*</label>
         <div class="col-xs-12 col-sm-2">
			<div class="clearfix">
			 <input name="industryWorkExperience" id="workIndustry${status.count}" value="${userBean.industryWorkExperience[status.index]}" type="text"  class="col-xs-12 col-sm-12 form-control"  placeholder="Industry" onblur="requiredFunction('workCompanyName${status.count}','workDesignation${status.count}','workDuration${status.count}','workIndustry${status.count}','workFunctionalArea${status.count}')"/>
				</div></div>
              
         <label class="control-label text-left col-xs-12 col-sm-2 no-padding-right" for="Function Area">Function Area*</label>
         <div class="col-xs-12 col-sm-2">
			<div class="clearfix">
			 <input name="functionAreaWorkExperience" id="workFunctionalArea${status.count}" value="${userBean.functionAreaWorkExperience[status.index]}" type="text"  class="col-xs-12 col-sm-12 form-control"  placeholder="Function Area" onblur="requiredFunction('workCompanyName${status.count}','workDesignation${status.count}','workDuration${status.count}','workIndustry${status.count}','workFunctionalArea${status.count}')"/>
				</div></div> 
                
          <%-- <label class="control-label text-left col-xs-12 col-sm-2 no-padding-right" for="Total Work Months">Total Work Months</label>
         <div class="col-xs-12 col-sm-2">
			<div class="clearfix">
			<input name="totalWorkMonthsWorkExperience"  id="workTotal${status.count}" value="${userBean.totalWorkMonthsWorkExperience[status.index]}" type="text" required class="col-xs-12 col-sm-12 form-control"  placeholder="Total Work Months" maxlength="30" onblur="checkNumber('workTotal${status.count}','Please enter only numaric values')"/>
				</div> </div> --%>
                
      </div> 
     
            </div>
        <input type="hidden" value="${userBean.workExperiencePkId[status.index]}" name="workExperiencePkId"/>  
        <input type="hidden" value="0" name="workHiddenApprove" id="whApprove${status.count}"/>  
        <input type="hidden" value="0" name="workHiddenLock" id="whLock${status.count}"/>  
    	</div> 
        </div>
        
    </div>
   		  </c:forEach>
   </c:if>
   
    <div class="clearfix"></div>
       <div id="internships2"></div>
       <%-- <input type="button" value="Remove Work Experience" id="remove-panel2" class="btn btn-primary btn-lg" style="margin-left:10px;"/>
        <input type="button" value="Add Work Experience" id="add-panel2" class="btn btn-primary btn-lg" style="margin-left:10px;"/>
       <label  for="Total Work Exp" style="margin-left:184px;">Total Work Exp</label>
         <input type="text" id="totalWorkExp" name="totalWorkExp" value="${userBean.totalWorkExperience}"/>
          <button class="btn btn-primary prevBtn btn-lg pull-left" type="button">Previous</button>
         <button class="btn btn-primary nextBtn btn-lg pull-right" type="button" >Next</button> --%>
         
         
        <c:forEach items="${userBean.workHiddenApprove}" var="lock"> 
     <c:choose>
        <c:when test="${lock eq 1}">
         <input type="button" value="Remove Work Experience" id="remove-panel2" class="btn btn-lg" disabled style="margin-left:10px;color: white;" />
         <input type="button" value="Add Work Experience" id="add-panel2" class="btn btn-lg" disabled style="margin-left:10px;color: white;" />
        </c:when>
        <c:when test="${lock ne 1}">
         <input type="button" value="Remove Work Experience" id="remove-panel2" class="btn btn-primary btn-lg" style="margin-left:10px;"/>
         <input type="button" value="Add Work Experience" id="add-panel2" class="btn btn-primary btn-lg" style="margin-left:10px;"/>
         </c:when>         
     </c:choose>    
      </c:forEach>
      
      <c:if test="${empty userBean.workHiddenApprove}">
      <input type="button" value="Remove Work Experience" id="remove-panel2" class="btn btn-primary btn-lg" style="margin-left:10px;" />
      <input type="button" value="Add Work Experience" id="add-panel2" class="btn btn-primary btn-lg" style="margin-left:10px;"/>    
      </c:if>
      
        <label  for="Total Work Exp" style="margin-left:184px;">Total Work Exp</label>
         <input type="text" id="totalWorkExp" name="totalWorkExp" value="${userBean.totalWorkExperience}"/>
          <button class="btn btn-primary prevBtn btn-lg pull-left" type="button" >Previous</button>
         <button class="btn btn-primary nextBtn btn-lg pull-right" type="button">Next</button>
        
           </div>
           
                <div class="row setup-content" id="step-9">
         <c:if test="${empty userBean.cvFileName}">
        <div class="main_box section_one panel panel-primary">
     <div class="panel-heading">
  
  <div class="row"> <div class="col-sm-3"> CV </div> 
      <div class="col-sm-4 pull-right text-right">
          <input type="checkbox" disabled="disabled">  Approve  <input type="checkbox" disabled="disabled"> Lock
        </div></div>
        </div>
          <div id="internshipbox3">
    	<div class="panel-body">
        <div class="row">  
      <div class="  border"> 
        <div class="col-sm-4"><label class=" col-sm-4">CV Title*</label><input id="id-input-file-2" type="text" required id="cvTitle" name=cvTitle value="${userBean.cvTitle[status.index]}" onblur="checkNullValue('cvTitleId')"/></div>
        <div class="col-sm-3 inpur_box">
         <!--  <label class="ace-file-input"><input id="id-input-file-2" type="file" name="cvFile"><span class="ace-file-container" data-title="Choose"><span class="ace-file-name"><i class=" ace-icon fa fa-upload"></i></span></span><a class="remove" href="#"><i class=" ace-icon fa fa-times"></i></a></label> -->
          <!-- <label class="ace-file-input"><input id="id-input-file-2" type="file"  name="cvFile" required onchange="checkFileType('uploadCvFile')"><span class="ace-file-container" data-title="Choose"><span class="ace-file-name"><i class=" ace-icon fa fa-upload"></i></span></span><a class="remove" href="#"><i class=" ace-icon fa fa-times"></i></a></label> -->
       <input id="uploadCvFile" type="file"  name="cvFile"  onchange="checkFileType('uploadCvFile')">
        </div>
        
    <div class="col-sm-3"><p></p></div>
      <div class="col-sm-3">
        
        <!--  <label class="radio-inline">
      <input type="radio" name="optradio">Yes
    </label>
    <label class="radio-inline">
      <input type="radio" name="optradio">No
    </label>  -->
       <input type="hidden" value="0" name="cvPkId"/>
      </div>
      </div>
    </div>
        
    </div>
    
    </div>
    </div>
    </c:if>
      <c:if test="${not empty userBean.cvFileName}">
        <c:forEach items="${userBean.cvFileName}" var="cvFile" varStatus="status">
      	  <div class="main_box section_one panel panel-primary" id="cvDiv${status.count}">
     <div class="panel-heading">
  <div class="row"> <div class="col-sm-3"> CV </div> 
      <div class="col-sm-4 pull-right text-right">
      <c:if test="${userBean.cvApproveHidden[status.index]!=1}">
      		<c:choose>
	      		<c:when test="${userBean.profileLoack=='UnLocked'}">
					 <a style="color: white;" href="#" onclick="deleteCv(${userBean.cvFileId[status.index]},'cvDiv${status.count}')">Delete</a>
       			</c:when>
				<c:otherwise>
	         		<a style="color: white;" href="#">Delete</a>
	         	</c:otherwise>
     		</c:choose>
      
       </c:if>
      <c:if test="${sessionBean.roleID==3}"> 
          <input type="checkbox" name="cvApprove" id="cv${status.count}Approve" onclick="setcvApprove(${status.count})">  Approve  <input type="checkbox" id="cv${status.count}Lock" onclick="checkBlankValue('cv${status.count}');setcvLock(${status.count})"> Lock
       </c:if>
       
        </div></div>
        </div>
          <div id="internshipbox3">
    	<div class="panel-body">
        <div class="row">  
      <div class="  border"> 
        <div class="col-sm-4"><label class=" col-sm-4">CV Title</label><input type="text"  id="cvTitle${status.count}" name=cvTitle value="${userBean.cvTitle[status.index]}"  maxlength="50" onblur="checkNullValue('cvTitle${status.count}')"/></div>
        <div class="col-sm-3 inpur_box">
         <!--  <label class="ace-file-input"><input id="id-input-file-2" type="file" name="cvFile"><span class="ace-file-container" data-title="Choose"><span class="ace-file-name"><i class=" ace-icon fa fa-upload"></i></span></span><a class="remove" href="#"><i class=" ace-icon fa fa-times"></i></a></label> -->
         <%--  <label class="ace-file-input"><input  type="file"  name="cvFile" id="cvFile${status.count}" onchange="checkFileType('cvFile${status.count}')"><span class="ace-file-container" data-title="Choose"><span class="ace-file-name"><i class=" ace-icon fa fa-upload"></i></span></span><a class="remove" href="#"><i class=" ace-icon fa fa-times"></i></a></label> --%>
       <input   type="file"  name="cvFile" id="cvFile${status.count}" onchange="checkFileType('cvFile${status.count}')"><span class="ace-file-container" data-title="Choose">
        </div>
         	<c:set var="cvPkId" value="${userBean.cvFileId[status.index]}"/>
         <%
         String uid = UUID.randomUUID().toString();
 		 String randomId=uid+"pKiD";
 		String cvPkId=(String)pageContext.getAttribute("cvPkId");
		Hashids hashids = new Hashids("comp role id",10);
		String hash = hashids.encode(Integer.parseInt(cvPkId==null?"0":cvPkId));
        %>
        <div><a href="downloadDocument?cvpkId=<%=randomId %><%=hash%>&fileName=${cvFile}" target="_blank">${cvFile}</a></div>
         <div class="col-sm-3"><p></p></div>
            <div class="col-sm-3">
					
							<!-- <label class="radio-inline"> <input type="radio"
								name="optradio">Yes
							</label> <label class="radio-inline"> <input type="radio"
								name="optradio">No
							</label> -->
							
						<input type="hidden" value="${userBean.cvFileId[status.index]}" name="cvPkId"/>
						<input type="hidden" value="0" name="cvApproveHidden" id="cvApproveHidden${status.count}"/>
						<input type="hidden" value="0" name="cvLockHidden" id="cvLockHidden${status.count}"/>
					</div>
      </div>
    </div>
        
    </div>
    
    </div>
    </div>
    </c:forEach>
      </c:if>
      
    <div class="clearfix"></div>
     <div id="internships3"></div>
     <!-- <input type="button" value="Remove Resume" id="remove-panel3" class="btn btn-primary btn-lg" style="margin-left:10px;" />
       <input type="button" value="Add Resume" id="add-panel3" class="btn btn-primary btn-lg" style="margin-left:10px;" /> -->
          <button class="btn btn-primary prevBtn btn-lg pull-left" type="button">Previous</button>
         <button class="btn btn-primary nextBtn btn-lg pull-right" type="button" >Next</button>
           </div>
        
         <div class="row setup-content" id="step-10">
         <div class="main_box section_one panel panel-primary">
     <div class="panel-heading">
     <div class="row"> <div class="col-sm-3">Add Resume </div> 
      <div class="col-sm-4 pull-right text-right">
       <c:if test="${sessionBean.roleID==3}"> 
          <input type="checkbox" name="resmueApprove" id="resumeApprove" value="2">  Approve  <input type="checkbox" name="resumeLock" id="resumeLock" value="1" onclick="checkBlankValue('resume')"> Lock
       </c:if>
       
        </div>
        </div>
  
        </div>
        
        
        <div id="internshipbox4">
        
    	<div class="panel-body">
       
        <div class="row">
        <div class="  border">
          <div class="col-sm-3"><p>Photo Upload*</p></div>
          <div class="col-sm-3 inpur_box">
        <!--   <label class="ace-file-input"><input id="photoUpload" type="file" name="photoUpload" required onchange="checkFileTypeForPhoto('photoUpload')"><span class="ace-file-container" data-title="Choose"><span class="ace-file-name" data-title="No File ..."><i class=" ace-icon fa fa-upload"></i></span></span><a class="remove" href="#"><i class=" ace-icon fa fa-times"></i></a></label> -->
            <input id="photoUpload" type="file" name="photoUpload" required onchange="checkFileTypeForPhoto('photoUpload')">
         	<br/> <a href="downloadphotoUploafFile?pkid=${userBean.photoUploadPkId}&fileName=${userBean.photoUploadFileName}">${userBean.photoUploadFileName}</a>
          </div>
         
         
       
           <div class="col-sm-3"><p>Profile Writeup*</p></div>
        <div class="col-sm-3 inpur_box"><textarea name="profileWriteUp" required class="form-control" id="profileWriteUp">${userBean.profileWriteUp}</textarea></div>
        </div>
      </div>
       
        <div class="row">
        <div class="  border">
          <div class="col-sm-3"><p>DA Writeup</p></div>
          <div class="col-sm-3 inpur_box">
           <%--  <textarea class="form-control" name="daWriteUp" id="daWriteUp">${userBean.daWriteUp}</textarea> --%>
           <c:set var="dapkId" value="${userBean.photoUploadPkId}"/>
             <%
    	//UUID uid = UUID.fromString("38400000-8cf0-11bd-b23e-10b96e4ef00d");
      
            String daPkId=(String)pageContext.getAttribute("dapkId");
     		Hashids hashids = new Hashids("comp role id",10);
     		String hashId = hashids.encode(Integer.parseInt(daPkId==null?"0":daPkId));
        %>
          
          
            <div class="col-sm-3 inpur_box"> <input id="daWriteUp" type="file" name="daWriteUp" required onchange="checkFileType('daWriteUp')"></div>
         	<br/> <br/><a href="downloadDaWriteUp?pkid=<%=hashId%>&fileName=${userBean.daWriteUpFileName}" target="_blank">${userBean.daWriteUpFileName}</a>
          </div>
           <div class="col-sm-3"><p>Profile Lock*</p></div>
            <div class="col-sm-3 inpur_box">
             <%--  <input id="profileLoack" value="${userBean.profileLoack}" required name="profileLoack" type="text" class="form-control"> --%>
           <label>${userBean.profileLoack}</label>
              <input type="hidden" value="${userBean.profileLoack}" name="profileLockValue" id="profileLockValue"/>
            </div>
        </div>
        <input type="hidden" value="${userBean.shareProfile}" name="shareProfile"/>
      </div>
      
      <div class="row">
        <div class="  border">
          <div class="col-sm-3"><p>Lock Profile*</p></div>
          <div class="col-sm-3 inpur_box">
           <%--  <textarea class="form-control" name="daWriteUp" id="daWriteUp">${userBean.daWriteUp}</textarea> --%>
             <c:choose>
            <c:when test="${userBean.lockProfileByMentor==1}">
            <div class="col-sm-3 inpur_box"><input type="checkbox" name="lockProfileByMentor" id="lockProfileByMentor" checked></div>
           </c:when>
         <c:otherwise>
           <div class="col-sm-3 inpur_box"><input type="checkbox" name="lockProfileByMentor" id="lockProfileByMentor"></div>
         </c:otherwise>
         </c:choose> 
          </div>
        </div>
      </div>
        <div class="row">
          <div class="  border">
           
          </div>
        </div>
        </div> 
        
        <input type="hidden" value="${studentId}" name="StudentId" id="StudentId"/>
    </div> 
     
  <div class="clearfix"></div>
     <div id="internships4"></div>
        <div class="space"></div>
        <c:choose>
         <c:when test="${userBean.profileLoack=='Locked'}">
               <div class="pull-left"><span style='color:red;'>Profile is alreday locked by KM admin. You can't update your profile</span></div>
           		<button type="button" disabled="disabled" class="btn btn-success btn-lg pull-right">Submit</button>
              
           </c:when>
           <c:otherwise>
           <button type="button" class="btn btn-success btn-lg pull-right" onclick="getValue()">Submit</button>
           </c:otherwise>
       </c:choose>
        

    </div></div></div>
  </form>
    <div class="clearfix"></div>
  
 <div class="space"></div>

		<!-- <script src="assets/js/jquery-2.1.4.min.js"></script>

		
		<script src="assets/js/bootstrap.min.js"></script> -->

		<!-- page specific plugin scripts -->
		 <jsp:include page="commonJsp/Footer.jsp" />
		<script src="assets/js/wizard.min.js"></script>
		<script src="assets/js/jquery.validate.min.js"></script>
		<script src="assets/js/jquery-additional-methods.min.js"></script>
		<script src="assets/js/bootbox.js"></script>
		<script src="assets/js/jquery.maskedinput.min.js"></script>
		<script src="assets/js/select2.min.js"></script>
        <script src="assets/js/bootstrap-datepicker.min.js"></script>
        
		<!-- ace scripts -->
		<script src="assets/js/ace-elements.min.js"></script>
		<script src="assets/js/ace.min.js"></script>
		<script type="text/javascript" src="iimJs/UserProfile.js"></script>

		 <script>
        
        
        $('.date-picker').datepicker({
					autoclose: true,
					todayHighlight: true
				})
				//show datepicker when clicking on the icon
				.next().on(ace.click_event, function(){
					$(this).prev().focus();
				});
        </script>
        
        
        <script>

  $(document).ready(function() {
	
	  var x=1;  
    $("#add-panel").click(function() {
		
    	 var wrapper=$('#internships');
  	   //var data=$('#internshipbox').html();
  	 
  	    var data='<div id="internshipboxBlank"> <div class="panel-body" > <div class="row"> <div class="form-group"> <label class="control-label text-left col-xs-12 col-sm-3 no-padding-right" for="Company">Company</label>'
		  data+='<div class="col-xs-12 col-sm-3">	<div class="clearfix"> <input type="text" required class="col-xs-12 col-sm-3 form-control"  placeholder="Company" name="company"/>'
		  data+='</div></div> <label class="control-label text-left col-xs-12 col-sm-3 no-padding-right" for="Role">Role</label> <div class="col-xs-12 col-sm-3"><div class="clearfix">'
		  data+='<input type="text" required class="col-xs-12 col-sm-3 form-control"  placeholder="Role" name="role"/></div></div> </div> </div> <div class="row"><div class="form-group">'
		  data+='<label class="control-label text-left col-xs-12 col-sm-3 no-padding-right" for="Location">Location</label><div class="col-xs-12 col-sm-3"><div class="clearfix">'
		  data+='<input name="locationSummerInternship" type="text" required class="col-xs-12 col-sm-3 form-control"  placeholder="Location"/></div></div><label class="control-label text-left col-xs-12 col-sm-3 no-padding-right" for="Duration">Duration(in weeks)</label>'
		  data+='<div class="col-xs-12 col-sm-3"><div class="clearfix"><input name="DurationSummerInternship"  id="durationSummerInternNew" type="text" required class="col-xs-12 col-sm-3 form-control"  placeholder="Duration" onblur="checkNumber(\'durationSummerInternNew\',\'Please enter only numaric values\')"/>'
		  data+='</div></div</div></div></div><input type="hidden" name="summerInternshipPkId" value="0"/></div>'; 
		if(x<11)
		{
        var domElement = $('<div class="main_box section_one panel panel-primary"><div class="panel-heading"><div class="row"><div class="col-sm-3">Summer Internship '+x+'</div><div class="col-sm-4 pull-right text-right"><input type="checkbox">  Approve  <input type="checkbox"> Lock</div></div></div>'+data+'</div></div> ');
		
		
		
        $(wrapper).append(domElement);
		x++;
		}
				
    }); 
    $("#remove-panel")
	.click(
			function() {
				var main = document
						.getElementById("internships");
				x--;
				if (main.children.length > 0) {
					main.lastChild.remove();
				}
			});
	});
	
	
	  $(document).ready(function() {
	    var x=1;  
    $("#add-panel1").click(function() {

		//var wrapper=$('#internships1');
	// var data1=$('#internshipbox1').html();
	  /*  var data1='<div id="internshipbox1"><div class="panel-body"><div class="row">  <div class="form-group"><label class="control-label text-left col-xs-12 col-sm-2 no-padding-right" for="Internship 1 Company">Internship 1 Company</label>'
		 data1+='<div class="col-xs-12 col-sm-2"><div class="clearfix"><input name="otherInternShipCompany" type="text" required class="col-xs-12 col-sm-12 form-control"  placeholder="Internship 1 Company"/></div></div>'
data1+='<label class="control-label text-left col-xs-12 col-sm-2 no-padding-right" for="Duration">Duration</label> <div class="col-xs-12 col-sm-2"><div class="clearfix">'
data1+='<input name="otherInternShipDuration" id="otherInternShipDurationNew" type="text" required class="col-xs-12 col-sm-12 form-control"  placeholder="Duration" onblur="checkNumber(\'otherInternShipDurationNew\',\'Please enter only numaric values\')"/></div></div><label class="control-label text-left col-xs-12 col-sm-2 no-padding-right" for="Location">Location</label>'
data1+='<div class="col-xs-12 col-sm-2"><div class="clearfix"> <input name="otherInternShipLocation" type="text" required class="col-xs-12 col-sm-12 form-control"  placeholder="Location"/>'
data1+='</div></div><input type="hidden" name="otherInternshipPkId" value="0"/></div></div></div></div>'; */ 

var wrapper=$('#internships1');
// var data1=$('#internshipbox1').html();
   var data1='<div id="internshipbox1"><div class="panel-body"><div class="row">  <div class="form-group"><label class="control-label text-left col-xs-12 col-sm-2 no-padding-right" for="Internship 1 Company">Internship 1 Company</label>'
	 data1+='<div class="col-xs-12 col-sm-2"><div class="clearfix"><input name="otherInternShipCompany" id="otherInternShipCompanyNew" type="text" class="col-xs-12 col-sm-12 form-control"  placeholder="Internship 1 Company" onblur="setRequiredFieldForOther(\'otherInternShipCompanyNew\',\'otherInternShipDurationNew\',\'otherInternShipLocationNew\')"/></div></div>'
data1+='<label class="control-label text-left col-xs-12 col-sm-2 no-padding-right" for="Duration">Duration(in weeks)</label> <div class="col-xs-12 col-sm-2"><div class="clearfix">'
data1+='<input name="otherInternShipDuration" id="otherInternShipDurationNew" type="text" class="col-xs-12 col-sm-12 form-control"  placeholder="Duration" onblur="setRequiredFieldForOther(\'otherInternShipCompanyNew\',\'otherInternShipDurationNew\',\'otherInternShipLocationNew\');checkNumber(\'otherInternShipDurationNew\',\'Please enter only numaric values\')"/></div></div><label class="control-label text-left col-xs-12 col-sm-2 no-padding-right" for="Location">Location</label>'
data1+='<div class="col-xs-12 col-sm-2"><div class="clearfix"> <input name="otherInternShipLocation" id="otherInternShipLocationNew" type="text" class="col-xs-12 col-sm-12 form-control"  placeholder="Location" onblur="setRequiredFieldForOther(\'otherInternShipCompanyNew\',\'otherInternShipDurationNew\',\'otherInternShipLocationNew\')"/>'
data1+='</div></div><input type="hidden" name="otherInternshipPkId" value="0"/></div></div></div></div>';   
		if(x<11)
		{
       var domElement = $('<div class="main_box section_one panel panel-primary"><div class="panel-heading"><div class="row"><div class="col-sm-3">Other Internship '+x+'</div><div class="col-sm-4 pull-right text-right"><input type="checkbox" disabled="disabled">  Approve  <input type="checkbox" disabled="disabled"> Lock</div></div></div>'+data1+'</div></div> ');
		
		
        $(wrapper).append(domElement);
		x++;
		}
				
    });
    $("#remove-panel1")
	.click(
			function() {
				var main = document
						.getElementById("internships1");
				if(x>1)
					x--;
				if (main.children.length > 0) {
					main.lastChild.remove();
				}
			});
	
    
});


 $(document).ready(function() {
	 var worklist = ${userBean.durationWorkExperience};
	
	// alert("worklist " +worklist+" dfdgf "+ worklist.length);
	  var x=1;  
	    $("#add-panel2").click(function() {
	    	var count= worklist.length+x;
	      /* var wrapper=$('#internships2');
		  // var data2=$('#internshipbox2').html(); 
		var data2=' <div id="internshipbox2"><div class="panel-body"> <div class="row">  <div class="form-group">'
		data2+='<label class="control-label text-left col-xs-12 col-sm-2 no-padding-right" for="Company Name">Company Name</label> <div class="col-xs-12 col-sm-2"><div class="clearfix">'
		data2+='<input name="companyNameWorkExperience" type="text" required class="col-xs-12 col-sm-12 form-control"  placeholder="Company Name"/></div></div>'
		data2+='<label class="control-label text-left col-xs-12 col-sm-2 no-padding-right" for="Designation">Designation</label><div class="col-xs-12 col-sm-2"><div class="clearfix">'
		data2+='<input name="designationWorkExperience" type="text" required class="col-xs-12 col-sm-12 form-control"  placeholder="Designation"/></div></div><label class="control-label text-left col-xs-12 col-sm-2 no-padding-right" for="Duration">Duration</label>'
		data2+='<div class="col-xs-12 col-sm-2"><div class="clearfix"> <input name="durationWorkExperience" id="durationWorkExperienceNew" type="text" required class="col-xs-12 col-sm-12 form-control"  placeholder="Duration" onblur="checkNumber(\'durationWorkExperienceNew\',\'Please enter only numaric values\')"/>'
		data2+='</div> </div>  </div> </div> <div class="row"> <div class="form-group"><label class="control-label text-left col-xs-12 col-sm-2 no-padding-right" for="Industry">Industry</label>'
		data2+='<div class="col-xs-12 col-sm-2"><div class="clearfix"> <input name="industryWorkExperience" type="text" required class="col-xs-12 col-sm-12 form-control"  placeholder="Industry"/>'
		data2+='</div></div><label class="control-label text-left col-xs-12 col-sm-2 no-padding-right" for="Function Area">Function Area</label><div class="col-xs-12 col-sm-2">	<div class="clearfix">'
		data2+='<input name="functionAreaWorkExperience" type="text" required class="col-xs-12 col-sm-12 form-control"  placeholder="Function Area"/></div></div> '
		/* data2+='<label class="control-label text-left col-xs-12 col-sm-2 no-padding-right" for="Total Work Months">Total Work Months</label><div class="col-xs-12 col-sm-2"><div class="clearfix">'
		data2+='<input name="totalWorkMonthsWorkExperience" type="text" required class="col-xs-12 col-sm-12 form-control"  id="totalWorkMonthNew" placeholder="Total Work Months" onblur="checkNumber(\'totalWorkMonthNew\',\'Please enter only numaric values\')"/></div> </div> </div>' */
		/*data2+='<input type="hidden" value="0" name="workExperiencePkId"/></div></div> </div>';  */
		
		
		  var wrapper=$('#internships2');
	  // var data2=$('#internshipbox2').html(); 
	var data2=' <div id="internshipbox2"><div class="panel-body"> <div class="row">  <div class="form-group">'
		data2+='<label class="control-label text-left col-xs-12 col-sm-2 no-padding-right" for="Company Name">Company Name*</label> <div class="col-xs-12 col-sm-2"><div class="clearfix">'
		data2+='<input name="companyNameWorkExperience" id="companyNameWorkExperience'+count+'" type="text" class="col-xs-12 col-sm-12 form-control"  placeholder="Company Name" onblur="requiredFunction(\'companyNameWorkExperience'+count+'\',\'designationWorkExperience'+count+'\',\'durationWorkExperience'+count+'\',\'industryWorkExperience'+count+'\',\'functionAreaWorkExperience'+count+'\')"/></div></div>'
		data2+='<label class="control-label text-left col-xs-12 col-sm-2 no-padding-right" for="Designation">Designation*</label><div class="col-xs-12 col-sm-2"><div class="clearfix">'
		data2+='<input name="designationWorkExperience" id="designationWorkExperience'+count+'" type="text" class="col-xs-12 col-sm-12 form-control"  placeholder="Designation" onblur="requiredFunction(\'companyNameWorkExperience'+count+'\',\'designationWorkExperience'+count+'\',\'durationWorkExperience'+count+'\',\'industryWorkExperience'+count+'\',\'functionAreaWorkExperience'+count+'\')"/></div></div><label class="control-label text-left col-xs-12 col-sm-2 no-padding-right" for="Duration">Duration(in months)*</label>'
		data2+='<div class="col-xs-12 col-sm-2"><div class="clearfix"> <input name="durationWorkExperience" id="durationWorkExperience'+count+'" type="text"  class="col-xs-12 col-sm-12 form-control"  placeholder="Duration" onblur="requiredFunction(\'companyNameWorkExperience'+count+'\',\'designationWorkExperience'+count+'\',\'durationWorkExperience'+count+'\',\'industryWorkExperience'+count+'\',\'functionAreaWorkExperience'+count+'\');checkNumber(\'durationWorkExperience'+count+'\',\'Please enter only numaric values\');addDuration()"/>'
		data2+='</div> </div>  </div> </div> <div class="row"> <div class="form-group"><label class="control-label text-left col-xs-12 col-sm-2 no-padding-right" for="Industry">Industry*</label>'
		data2+='<div class="col-xs-12 col-sm-2"><div class="clearfix"> <input name="industryWorkExperience" id="industryWorkExperience'+count+'" type="text"  class="col-xs-12 col-sm-12 form-control"  placeholder="Industry" onblur="requiredFunction(\'companyNameWorkExperience'+count+'\',\'designationWorkExperience'+count+'\',\'durationWorkExperience'+count+'\',\'industryWorkExperience'+count+'\',\'functionAreaWorkExperience'+count+'\')"/>'
		data2+='</div></div><label class="control-label text-left col-xs-12 col-sm-2 no-padding-right" for="Function Area">Function Area*</label><div class="col-xs-12 col-sm-2">	<div class="clearfix">'
		data2+='<input name="functionAreaWorkExperience" id="functionAreaWorkExperience'+count+'" type="text"  class="col-xs-12 col-sm-12 form-control"  placeholder="Function Area" onblur="requiredFunction(\'companyNameWorkExperience'+count+'\',\'designationWorkExperience'+count+'\',\'durationWorkExperience'+count+'\',\'industryWorkExperience'+count+'\',\'functionAreaWorkExperience'+count+'\')"/></div></div> '
		/* data2+='<label class="control-label text-left col-xs-12 col-sm-2 no-padding-right" for="Total Work Months">Total Work Months</label><div class="col-xs-12 col-sm-2"><div class="clearfix">'
		data2+='<input name="totalWorkMonthsWorkExperience" type="text" required class="col-xs-12 col-sm-12 form-control"  id="totalWorkMonthNew" placeholder="Total Work Months" onblur="checkNumber(\'totalWorkMonthNew\',\'Please enter only numaric values\')"/></div> </div> </div>' */
		data2+='<input type="hidden" value="0" name="workExperiencePkId"/></div></div> </div>'; 
	 
		if(x<5)	{
       
 		var domElement = $('<div class="main_box section_one panel panel-primary"><div class="panel-heading"><div class="row"><div class="col-sm-3">Work Experience</div><div class="col-sm-4 pull-right text-right"><input type="checkbox" id="workExperience'+count+'Approve"  onclick="setWorkApproveValue('+count+')">  Approve  <input type="checkbox" id="workExperience'+count+'Lock" onclick="checkBlankValue(\'workExperience'+count+'\');setWorkLockValues('+count+')"> Lock</div></div></div>'+data2+'</div></div> ');
	   
	    $(wrapper).append(domElement);
		x++;
		}
				
    });
	    $("#remove-panel2")
		.click(
				function() {
					var main = document
							.getElementById("internships2");
					if(x>1)
						x--;
					if (main.children.length > 0) {
						main.lastChild.remove();
					}
				});
	});

$(document).ready(function() {
	
	  var x=1;  
    $("#add-panel3").click(function() {
		
    	
        var wrapper=$('#internships3');
  	//   var data3=$('#internshipbox3').html(); 
  	 data3='<div id="internshipbox3"><div class="panel-body"> <div class="row"><div class="  border"> <div class="col-sm-4"><label class=" col-sm-4">CV Title</label><input id="cvTitle" type="text"  id="cvTitle" name=cvTitle value="${userBean.cvTitle[status.index]}" placeholder="Title" required="required" onclick="checkNullValue(\'cvTitle\')"/></div><div class="col-sm-3 inpur_box">'
	/* data3+='<label class="ace-file-input"><input required id="cvUploadFileNew" type="file"  name="cvFile" onchange="checkFileType(\'cvUploadFileNew\')"><span class="ace-file-container" data-title="Choose"><span class="ace-file-name">'
	data3+='<i class=" ace-icon fa fa-upload"></i></span></span><a class="remove" href="#"><i class=" ace-icon fa fa-times"></i></a></label></div>' */
	data3+='<input required id="cvUploadFileNew" type="file"  name="cvFile" onchange="checkFileType(\'cvUploadFileNew\')">'
	/* data3+='<div class="col-sm-3"><p>Do you wish to share your CV?</p></div><div class="col-sm-3"><label class="radio-inline"> <input type="radio"name="optradio">Yes</label>'
	data3+='<label class="radio-inline"> <input type="radio"name="optradio">No</label> */
	data3+='<input type="hidden" value="0" name="cvPkId"/></div>'
	data3+='</div></div></div></div>'; 
	var cvlimit=${cvlimit};
		if(x<=cvlimit)
		{
       
		var domElement = $('<div class="main_box section_one panel panel-primary"><div class="panel-heading"><div class="row"><div class="col-sm-3">Add Resume '+x+'</div><div class="col-sm-4 pull-right text-right"><input type="checkbox">  Approve  <input type="checkbox"> Lock</div></div></div>'+data3+'</div></div> ');
		
        $(wrapper).append(domElement);
		x++;
		}
				
    });
    $("#remove-panel3")
	.click(
			function() {
				var main = document
						.getElementById("internships3");
				if(x>1)
					x--;
				if (main.children.length > 0) {
					main.lastChild.remove();
				}
			});
	
	
	$('.date-picker').datepicker({
					autoclose: true,
					todayHighlight: true
				})
				//show datepicker when clicking on the icon
				.next().on(ace.click_event, function(){
					$(this).prev().focus();
				});
	
	
	
	
	});

      $(document).ready(function() {
	
	  var x=1;  
    $("#add-panel4").click(function() {
		
      var wrapper=$('#internships4');
	  var data4=$('#internshipbox4').html();
	 
		if(x<4)
		{
        var domElement = $('<div class="main_box section_one panel panel-primary"><div class="panel-heading"><div class="row"><div class="col-sm-3">Add Resume '+x+'</div><div class="col-sm-4 pull-right text-right"><input type="checkbox">  Approve  <input type="checkbox"> Lock</div></div></div>'+data4+'</div></div> ');
		
		
		
        $(wrapper).append(domElement);
		x++;
		}
				
    });
	});
	 
	  
	 
       </script> 


<script type="text/javascript">
  $(document).ready(function () {
  var navListItems = $('div.setup-panel div a'),
		  allWells = $('.setup-content'),
		  allNextBtn = $('.nextBtn');
  var allPrevBtn = $('.prevBtn');		

  allWells.hide();

  navListItems.click(function (e) {
	  e.preventDefault();
	  var $target = $($(this).attr('href')),
			  $item = $(this);

	  if (!$item.hasClass('disabled')) {
		  navListItems.removeClass('btn-primary').addClass('btn-default');
		  $item.addClass('btn-primary');
		  allWells.hide();
		  $target.show();
		  $target.find('input:eq(0)').focus();
	  }
  });
  
  allPrevBtn.click(function(){
	  var curStep = $(this).closest(".setup-content"),
	      curStepBtn = curStep.attr("id"),
	      prevStepWizard = $('div.setup-panel div a[href="#' + curStepBtn + '"]').parent().prev().children("a");
	      prevStepWizard.removeAttr('disabled').trigger('click');
	}); 

  allNextBtn.click(function(){
	  var curStep = $(this).closest(".setup-content"),
		  curStepBtn = curStep.attr("id"),
		  nextStepWizard = $('div.setup-panel div a[href="#' + curStepBtn + '"]').parent().next().children("a"),
		  curInputs = curStep.find("input[type='text'],input[type='file'],input[type='url'],textarea[textarea],select"),
		  isValid = true;

	  $(".form-group").removeClass("has-error");
	  for(var i=0; i<curInputs.length; i++){
		  if (!curInputs[i].validity.valid){
			  isValid = false;
			  $(curInputs[i]).closest(".form-group").addClass("has-error");
		  }
	  }

	  if (isValid)
		  nextStepWizard.removeAttr('disabled').trigger('click');
  });

  $('div.setup-panel div a.btn-primary').trigger('click');
  
});
  
	function getValue()
	{
		document.getElementById("userProfile").action = "mentoreSubmit";	
		document.getElementById("userProfile").method = "post";
		document.forms["userProfile"].submit();
	}
	
	var postGraduateDegree=document.getElementById("postgraduateDegree").value;
	  if(postGraduateDegree==""){
		  document.getElementById("postgraduateDegree").required = false;
		  document.getElementById("postgraduateDepartment").required = false;
		  document.getElementById("postgraduateInstitute").required = false;
		  document.getElementById("postgraduateAbbreviation").required = false;
		  document.getElementById("pgDiv").style.display="none";
	  }else{
		  document.getElementById("postgraduateDegree").required = true;
		  document.getElementById("postgraduateDepartment").required = true;
		  document.getElementById("postgraduateInstitute").required = true;
		  document.getElementById("postgraduateAbbreviation").required = true;
		  document.getElementById("pgDiv").style.display="block"; 
	  }
	
  </script>
  
  </body>
  <script>
  	function setCheckedValues(){
  		userProfilePage();
  	}
  	 function setWorkApproveValue(count ){
  		var id = "#workExperience"+count+"Approve";
   		if ($(id).is(":checked")){
	  		if (document.getElementById("companyNameWorkExperience"+count).value == ""
				|| document.getElementById("designationWorkExperience"+count).value == ""
				|| document.getElementById("durationWorkExperience"+count).value == ""
				|| document.getElementById("industryWorkExperience"+count).value == ""
				|| document.getElementById("functionAreaWorkExperience"+count).value == "") {
			  
			 	alert("Please fill up required details of work experince.");
				$("workExperience"+count+"Approve").prop('checked', false);
				$(id).prop('checked', false);
	  		}
   		}
  		
     }
  	 
  	 function checkEmptyValue(){
  		var id = "#workExperienceApprove";
   		if ($(id).is(":checked")){
	  		if (document.getElementById("companyNameWorkExperience").value == ""
				|| document.getElementById("designationWorkExperience").value == ""
				|| document.getElementById("durationWorkExperience").value == ""
				|| document.getElementById("industryWorkExperience").value == ""
				|| document.getElementById("functionAreaWorkExperience").value == "") {
			  
			 	alert("Please fill up required details of work experince.");
				$("workExperienceApprove").prop('checked', false);
				$(id).prop('checked', false);
	  		}
   		} 
  	 }
  	 
  	 
  	function userProfilePage(){
  		//var rollId=${sessionBean.mentRoleID}
  		var rollId=${sessionBean.roleID}
  		var fullName=${userBean.fullNameApprove}
  		var rollNumber=${userBean.rollNumberApprove};
  		if(rollNumber==1){
  			$('#rollNumberLock').prop('checked', true);
  			$('#rollNumberApprove').prop('checked', true);
  			
  			if(rollId==3){
  				$('#rollNumber').prop('readonly','readonly');
  			}
  		}else if(rollNumber==2){
  			$('#rollNumberApprove').prop('checked', true);
  			
  		}
  		
  		 var fullName=${userBean.fullNameApprove};
  		if(fullName==1){
  			$('#fullNameLock').prop('checked', true);
  			$('#fullNameApprove').prop('checked', true);
  			
  			if(rollId==3){
  				$('#fullName').prop('readonly','readonly');
  			}
  		}else if(fullName==2){
  			$('#fullNameApprove').prop('checked', true);
  			
  		}
  		
  		var cvName=${userBean.cvNameApprove};
  		if(cvName==1){
  			$('#cvNameLock').prop('checked', true);
  			$('#cvNameApprove').prop('checked', true);
  			
  			if(rollId==3){
  				$('#cvName').prop('readonly','readonly');
  			}
  		}else if(cvName==2){
  			$('#cvNameApprove').prop('checked', true);
  			
  		}
  		
  		 var mentor=${userBean.mentorApprove}
  		if(mentor==1){
  			$('#mentorLock').prop('checked', true);
  			$('#mentorApprove').prop('checked', true);
  			
  			if(rollId==3){
  				$('#mentor').prop('readonly','readonly');
  			}
  		}else if(mentor==2){
  			$('#mentorApprove').prop('checked', true);
  			
  		}
  		
  		var gender=${userBean.genderApprove}
  		if(gender==1){
  			$('#genderApprove').prop('checked', true);
  			$('#genderLock').prop('checked', true);
  			
  			if(rollId==3){
  				$('#mentor').prop('readonly','readonly');
  			}
  		}else if(gender==2){
  			$('#genderApprove').prop('checked', true);
  			
  		}
  		
  		var dateOfBirth=${userBean.dateOfBirthApprove}
  		if(dateOfBirth==1){
  			$('#dateOfBirthApprove').prop('checked', true);
  			$('#dateOfBirthLock').prop('checked', true);
  			
  			if(rollId==3){
  				$('#dateOfBirth').prop('readonly','readonly');
  			}
  		}else if(dateOfBirth==2){
  			$('#dateOfBirthApprove').prop('checked', true);
  			
  		}
  		
  		var phoneExt=${userBean.phoneExtApprove}
  		if(phoneExt==1){
  			$('#phoneExtApprove').prop('checked', true);
  			$('#phoneExtLock').prop('checked', true);
  			
  			if(rollId==3){
  				$('#phoneEtn').prop('readonly','readonly');
  			}
  		}else if(phoneExt==2){
  			$('#phoneExtApprove').prop('checked', true);
  			
  		}
  		
  		var mobile=${userBean.mobileApprove}
  		if(mobile==1){
  			$('#mobileApprove').prop('checked', true);
  			$('#mobileLock').prop('checked', true);
  			
  			if(rollId==3){
  				$('#mobile').prop('readonly','readonly');
  			}
  		}else if(mobile==2){
  			$('#mobileApprove').prop('checked', true);
  			
  		}
  		
  		var alternativeEmail=${userBean.alternativeEmailApprove}
  		if(alternativeEmail==1){
  			$('#alternativeEmailApprove').prop('checked', true);
  			$('#alternativeEmailLock').prop('checked', true);
  			
  			if(rollId==3){
  				$('#alternativeEmailid').prop('readonly','readonly');
  			}
  		}else if(alternativeEmail==2){
  			$('#alternativeEmailApprove').prop('checked', true);
  			  		}  
  		
  		var domainRoom=${userBean.domianRoomApproved}
  		if(domainRoom==1){
  			$('#domianRoomApprove').prop('checked', true);
  			$('#domianRoomLock').prop('checked', true);
  			
  			if(rollId==3){
  				$('#dormin').prop('readonly','readonly');
  			}
  		}else if(domainRoom==2){
  			$('#domianRoomApprove').prop('checked', true);
  			
  		}
  		
  		var emailAddress=${userBean.emailAddressApprove}
  		if(emailAddress==1){
  			$('#emailAddressApprove').prop('checked', true);
  			$('#emailAddressLock').prop('checked', true);
  			
  			if(rollId==3){
  				$('#email').prop('readonly','readonly');
  			}
  		}else if(emailAddress==2){
  			$('#emailAddressApprove').prop('checked', true);
  			
  		}
  		
  		var city10th=${userBean.city10thApprove}
  		if(city10th==1){
  			$('#city10thApprove').prop('checked', true);
  			$('#city10thLock').prop('checked', true);
  			
  			if(rollId==3){
  				$('#city').prop('readonly','readonly');
  			}
  		}else if(city10th==2){
  			$('#city10thApprove').prop('checked', true);
  			
  		}
  		
  		var state10th=${userBean.state10thApprove}
  		if(state10th==1){
  			$('#state10thApprove').prop('checked', true);
  			$('#state10thLock').prop('checked', true);
  			
  			if(rollId==3){
  				$('#state').prop('readonly','readonly');
  			}
  		}else if(state10th==2){
  			$('#state10thApprove').prop('checked', true);
  			
  		}
  		
  		var board10th=${userBean.board10thApprove}
  		if(board10th==1){
  			$('#board10thApprove').prop('checked', true);
  			$('#board10thLock').prop('checked', true);
  			
  			if(rollId==3){
  				$('#board').prop('readonly','readonly');
  			}
  		}else if(board10th==2){
  			$('#board10thApprove').prop('checked', true);
  			
  		}
  		
  		var school10th=${userBean.school10thApprove}
  		if(school10th==1){
  			$('#school10thApprove').prop('checked', true);
  			$('#school10thLock').prop('checked', true);
  			
  			if(rollId==3){
  				$('#school').prop('readonly','readonly');
  			}
  		}else if(school10th==2){
  			$('#school10thApprove').prop('checked', true);
  			
  		}
  		
  		var percentage10th=${userBean.percentage10thApprove};
  		if(percentage10th==1){
  			$('#percentage10thApprove').prop('checked', true);
  			$('#percentage10thLock').prop('checked', true);
  			
  			if(rollId==3){
  				$('#percentage').prop('readonly','readonly');
  			}
  		}else if(percentage10th==2){
  			$('#percentage10thApprove').prop('checked', true);
  			
  		}
  		
  		var city12th=${userBean.city12thApprove}
  		if(city12th==1){
  			$('#city12thApprove').prop('checked', true);
  			$('#city12thLock').prop('checked', true);
  			
  			if(rollId==3){
  				$('#cityTwelve').prop('readonly','readonly');
  			}
  		}else if(city12th==2){
  			$('#city12thApprove').prop('checked', true);
  			
  		}
  		
  		var state12th=${userBean.state12thApprove}
  		if(state12th==1){
  			$('#state12thApprove').prop('checked', true);
  			$('#state12thLock').prop('checked', true);
  			
  			if(rollId==3){
  				$('#stateTwelve').prop('readonly','readonly');
  			}
  		}else if(state12th==2){
  			$('#state12thApprove').prop('checked', true);
  			
  		}
  		
  		var board12th=${userBean.board12thApprove}
  		if(board12th==1){
  			$('#board12thApprove').prop('checked', true);
  			$('#board12thLock').prop('checked', true);
  			
  			if(rollId==3){
  				$('#boardTwelve').prop('readonly','readonly');
  			}
  		}else if(board12th==2){
  			$('#board12thApprove').prop('checked', true);
  			
  			
  		}
  		
  		var school12th=${userBean.school12thApprove}
  		if(school12th==1){
  			$('#school12thApprove').prop('checked', true);
  			$('#school12thLock').prop('checked', true);
  			
  			if(rollId==3){
  				$('#schoolTwelve').prop('readonly','readonly');
  			}
  		}else if(school12th==2){
  			$('#school12thApprove').prop('checked', true);
  			
  		}
  		
  		var percent12th=${userBean.percentage12thApprove}
  		if(percent12th==1){
  			$('#percentage12thApprove').prop('checked', true);
  			$('#percentage12thLock').prop('checked', true);
  			
  			if(rollId==3){
  				$('#percenatageTwelve').prop('readonly','readonly');
  			}
  		}else if(percent12th==2){
  			$('#percentage12thApprove').prop('checked', true);
  			
  		}
  		
  		var gradPer=${userBean.graduatePercentageApprove}
  		if(gradPer==1){
  			$('#graduatePercentageApprove').prop('checked', true);
  			$('#graduatePercentageLock').prop('checked', true);
  			
  			if(rollId==3){
  				$('#percentageGraduate').prop('readonly','readonly');
  			}
  		}else if(gradPer==2){
  			$('#graduatePercentageApprove').prop('checked', true);
  			
  		}
  		
  		var grdaDegree=${userBean.graduateDregreeApprove}
  		if(grdaDegree==1){
  			$('#graduateDregreeApprove').prop('checked', true);
  			$('#graduateDregreeLock').prop('checked', true);
  			
  			if(rollId==3){
  				$('#graduteDegree').prop('readonly','readonly');
  			}
  		}else if(grdaDegree==2){
  			$('#graduateDregreeApprove').prop('checked', true);
  			
  		}
  		
  		var gradDepartment=${userBean.graduateDepartmentApprove}
  		if(gradDepartment==1){
  			$('#graduateDepartmentApprove').prop('checked', true);
  			$('#graduateDepartmentLock').prop('checked', true);
  			
  			if(rollId==3){
  				$('#graduteDepartment').prop('readonly','readonly');
  			}
  		}else if(gradDepartment==2){
  			$('#graduateDepartmentApprove').prop('checked', true);
  			
  		}
  		
  		var gradInstitute=${userBean.grdauateInstituteApprove}
  		if(gradInstitute==1){
  			$('#graduatedInstituteApprove').prop('checked', true);
  			$('#graduatedInstituteLock').prop('checked', true);
  			
  			if(rollId==3){
  				$('#institute').prop('readonly','readonly');
  			}
  		}else if(gradInstitute==2){
  			$('#graduatedInstituteApprove').prop('checked', true);
  			
  		}
  		
  		var Grdabbrivation=${userBean.grdaduateAbbrivationApprove}
  		if(Grdabbrivation==1){
  			$('#grdaduateAbbrivationApprove').prop('checked', true);
  			$('#grdaduateAbbrivationLock').prop('checked', true);
  			
  			if(rollId==3){
  				$('#Abbreviation').prop('readonly','readonly');
  			}
  		}else if(Grdabbrivation==2){
  			$('#grdaduateAbbrivationApprove').prop('checked', true);
  			
  		}
  		
  		var graduationLocation=${userBean.graduateLocationApprove}
  		if(graduationLocation==1){
  			$('#graduateLocationApprove').prop('checked', true);
  			$('#graduateLocationLock').prop('checked', true);
  			
  			if(rollId==3){
  				$('#location').prop('readonly','readonly');
  			}
  		}else if(graduationLocation==2){
  			$('#graduateLocationApprove').prop('checked', true);
  			
  		}
  		
  		var grdaCgpa=${userBean.graduateCgpaApprove}
  		if(grdaCgpa==1){
  			$('#graduateCgpaApprove').prop('checked', true);
  			$('#graduateCgpaLock').prop('checked', true);
  			
  			if(rollId==3){
  				$('#cgpa').prop('readonly','readonly');
  			}
  		}else if(grdaCgpa==2){
  			$('#graduateCgpaApprove').prop('checked', true);
  			
  		}
  		
  		var cgpaScale=${userBean.graduateCgpaScaleApprove}
  		if(cgpaScale==1){
  			$('#graduateCgpaScaleApprove').prop('checked', true);
  			$('#graduateCgpaScaleLock').prop('checked', true);
  			
  			if(rollId==3){
  				$('#cgpaScale').prop('readonly','readonly');
  			}
  		}else if(cgpaScale==2) {
  			$('#graduateCgpaScaleApprove').prop('checked', true);
  			
  		}
  		
  		var postGraduateDegree=${userBean.pgDegreeApprove}
  		if(postGraduateDegree==1){
  			$('#pgDegreeApprove').prop('checked', true);
  			$('#pgDegreeLock').prop('checked', true);
  			
  			if(rollId==3){
  				$('#postgraduateDegree').prop('readonly','readonly');
  			}
  		}else if(postGraduateDegree==2){
  			$('#pgDegreeApprove').prop('checked', true);
  			
  		}
  		
  		var pgDepatment=${userBean.pgDepartmentApprove}
  		if(pgDepatment==1){
  			$('#pgDepartmentApprove').prop('checked', true);
  			$('#pgDepartmentLock').prop('checked', true);
  			
  			if(rollId==3){
  				$('#postgraduateDepartment').prop('readonly','readonly');
  			}
  		}else if(pgDepatment==2) {
  			$('#pgDepartmentApprove').prop('checked', true);
  			
  		}
  		
  		var pgInstitute=${userBean.pgInstituteApprove}
  		if(pgInstitute==1){
  			$('#pgInstituteApprove').prop('checked', true);
  			$('#pgInstituteLock').prop('checked', true);
  			
  			if(rollId==3){
  				$('#postgraduateInstitute').prop('readonly','readonly');
  			}
  		}else if(pgInstitute==2){
  			$('#pgInstituteApprove').prop('checked', true);
  			
  		}
  		
  		var pgAbbrivation=${userBean.pgAbbrivationApprove}
  		if(pgAbbrivation==1){
  			$('#pgAbbrivationApprove').prop('checked', true);
  			$('#pgAbbrivationLock').prop('checked', true);
  			
  			if(rollId==3){
  				$('#postgraduateAbbreviation').prop('readonly','readonly');
  			}
  		}else if(pgAbbrivation==2){
  			$('#pgAbbrivationApprove').prop('checked', true);
  			
  		}
  		
  		var pgCgpa=${userBean.pgCgpaApprove}
  		if(pgCgpa==1){
  			$('#pgCgpaApprove').prop('checked', true);
  			$('#pgCgpaLock').prop('checked', true);
  			
  			if(rollId==3){
  				$('#postgraduateCGPA').prop('readonly','readonly');
  			}
  		}else if(pgCgpa==2){
  			$('#pgCgpaApprove').prop('checked', true);
  			
  		}
  		
  		var pgCgpaScale=${userBean.pgcgpaScaleApprove}
  		if(pgCgpaScale==1){
  			$('#cgpaScaleApprove').prop('checked', true);
  			$('#cgpaScaleLock').prop('checked', true);
  			
  			if(rollId==3){
  				$('#postgraduateCGPAScale').prop('readonly','readonly');
  			}
  		}else if(pgCgpaScale==2){
  			$('#cgpaScaleApprove').prop('checked', true);
  			
  		}

  		var pgPercentage=${userBean.pgPercentageApprove}
  		if(pgPercentage==1){
  			$('#pgPercentageApprove').prop('checked', true);
  			$('#pgPercentageLock').prop('checked', true);
  			
  			if(rollId==3){
  				$('#postgraduatePercentage').prop('readonly','readonly');
  			}
  		}else if(pgPercentage==2){
  			$('#pgPercentageApprove').prop('checked', true);
  		
  		}
  		
  		var summerIntershipStatusList=${userBean.summerApproveId};
  		if(summerIntershipStatusList.length>0){
  			for(var i=0;i<summerIntershipStatusList.length;i++){
  				if(summerIntershipStatusList[i]==1){
  					$('#summerInternship'+[i+1]+'Approve').prop('checked', true);
  					$('#summerInternship'+[i+1]+'Lock').prop('checked', true);
  					$('#summerIdHidden'+[i+1]).val(summerIntershipStatusList[i]);
  					$('#summerApproveId'+[i+1]).val(0);
  					
  		  			if(rollId==3){
  		  			$('#companyName'+[i+1]).prop('readonly','readonly');
		  				$('#role'+[i+1]).prop('readonly','readonly');
		  				$('#location'+[i+1]).prop('readonly','readonly');
		  				$('#Duration'+[i+1]).prop('readonly','readonly');
  		  			}
  				}else if(summerIntershipStatusList[i]==2){
  					$('#summerInternship'+[i+1]+'Approve').prop('checked', true);
  					$('#summerIdHidden'+[i+1]).val(0);
  					$('#summerApproveId'+[i+1]).val(summerIntershipStatusList[i]);
  					
  				}
  			}
  		}
  		
  		var otherInternship=${userBean.otherHiddenApprove}
  		if(otherInternship.length>0){
  			for(var i=0;i<otherInternship.length;i++){
  				if(otherInternship[i]==1){
  					$('#otherInternship'+[i+1]+'Approve').prop('checked', true);
  					$('#otherInternship'+[i+1]+'Lock').prop('checked', true);
  					$('#otherHiddenLock'+[i+1]).val(otherInternship[i]);
  					$('#otherHiddenApprove'+[i+1]).val(0);
  					
  		  			if(rollId==3){
  		  			$('#otherCompanyName'+[i+1]).prop('readonly','readonly');
		  				$('#otherDuration'+[i+1]).prop('readonly','readonly');
		  				$('#otherLocation'+[i+1]).prop('readonly','readonly');
  		  			}
  				}else if(otherInternship[i]==2){
  					$('#otherInternship'+[i+1]+'Approve').prop('checked', true);
  					$('#otherHiddenLock'+[i+1]).val(0);
  					$('#otherHiddenApprove'+[i+1]).val(otherInternship[i]);
  					
  				}
  				
  			}
  		}
  	
  		var workExperience=${userBean.workHiddenApprove}
  		if(workExperience.length>0){
  			for(var i=0;i<workExperience.length;i++){
  				if(workExperience[i]==1){
  					$('#workExperience'+[i+1]+'Approve').prop('checked', true);
  					$('#workExperience'+[i+1]+'Lock').prop('checked', true);
  					$('#whLock'+[i+1]).val(workExperience[i]);
  					$('#whApprove'+[i+1]).val(0);
  					
  		  			if(rollId==3){
  		  			$('#workCompanyName'+[i+1]).prop('readonly','readonly');
		  				$('#workDesignation'+[i+1]).prop('readonly','readonly');
		  				$('#workDuration'+[i+1]).prop('readonly','readonly');
		  			    $('#workIndustry'+[i+1]).prop('readonly','readonly');
	  				$('#workFunctionalArea'+[i+1]).prop('readonly','readonly');
	  				$('#workTotal'+[i+1]).prop('readonly','readonly');
  		  			}
  				}else if(workExperience[i]==2){
  					$('#workExperience'+[i+1]+'Approve').prop('checked', true);
  					$('#whLock'+[i+1]).val(0);
  					$('#whApprove'+[i+1]).val(workExperience[i]);
  					
  				}
  				
  			}
  		}
  		
  		var cvStatus=${userBean.cvApproveHidden}
  		if(cvStatus.length>0){
  			for(var i=0;i<cvStatus.length;i++){
  				if(cvStatus[i]==1){
  					$('#cv'+[i+1]+'Approve').prop('checked', true);
  					$('#cv'+[i+1]+'Lock').prop('checked', true);
  					$('#cvLockHidden'+[i+1]).val(cvStatus[i]);
  					$('#cvApproveHidden'+[i+1]).val(0);
  		  			if(rollId==3){
  		  			$('#cvTitle'+[i+1]).prop('readonly','readonly');
		  				$('#cvFile'+[i+1]).prop('readonly','readonly');
  		  			}
  				}else if(cvStatus[i]==2){
  					$('#cv'+[i+1]+'Approve').prop('checked', true);
  					$('#cvLockHidden'+[i+1]).val(0);
  					$('#cvApproveHidden'+[i+1]).val(cvStatus[i]);
  					
  				}
  				
  			}
  		}
  		
  		var resumeApprove=${userBean.resmueApprove}
  		if(resumeApprove==1){
  			$('#resumeApprove').prop('checked', true);
  			$('#resumeLock').prop('checked', true);
	  			if(rollId==3){
	  				$('#photoUpload').prop('readonly','readonly');
	  				$('#profileWriteUp').prop('readonly','readonly');
	  				$('#daWriteUp').prop('readonly','readonly');
	  				$('#profileLoack').prop('readonly','readonly');
	  				$('#profileWriteUp').css("color", "F5F5F5");
	  				$('#daWriteUp').css("color", "F5F5F5")
	  			}
  		}else if(resumeApprove==2){
  			$('#resumeApprove').prop('checked', true);
  		}
  	
  	}	
  		
  	
  </script>
  <script>
   /* $(function(){
	  var a=${studentId}
	    var roleValue=${sessionBean.roleID}
	  	if(roleValue==3){
	  		if(a==0){
	  		  document.getElementById( 'userProfile' ).style.display = 'none';
	  		}else{
	  			 document.getElementById( 'userProfile' ).style.display = 'block';
	  		}
	  
	  	}else{
	  	  document.getElementById( 'selectBox' ).style.display = 'none';
	  	} 
    });  */
  
   
   var submitFlag=document.getElementById("hiddenConfirmFlag").value;
   if(submitFlag=="submit"){
   	alert("Profile submitted successfully.")
   }
  
  </script>
  
  <script>

	  $(function(){
  	  	$(document).keydown(function(event){
	    	if(event.keyCode==123){
			    return false;
		   	}
			else if((event.ctrlKey && event.shiftKey && event.keyCode==73)||
					(event.ctrlKey && event.shiftKey && event.keyCode==67)||
					(event.ctrlKey && event.shiftKey && event.keyCode==81)|| 
					(event.ctrlKey && event.shiftKey && event.keyCode==75)){         
			      return false;  //Prevent from ctrl+shift+i
			   }
			});
	  });

    Object.defineProperty(window, "console", {
        value: console,
        writable: false,
        configurable: false
    });

    var i = 0;
    function showWarningAndThrow() {
        if (!i) {
            setTimeout(function () {
                console.log("%cWarning message", "font: 2em sans-serif; color: yellow; background-color: red;");
            }, 1);
            i = 1;
        }
        throw "Console is disabled";
    }

    var l, n = {
        set: function (o) {
            l = o;
        },
        get: function () {
            showWarningAndThrow();
            return l;
        }
    };
    Object.defineProperty(console, "_commandLineAPI", n);
    Object.defineProperty(console, "__commandLineAPI", n);

  </script>
</html>