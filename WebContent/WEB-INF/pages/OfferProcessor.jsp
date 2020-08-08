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
<title>Indian Institute of Management Ahmedabad (IIMA) | Home -
	IIMA</title>
<!-- Bootstrap -->

<!-- CSS STYLE-->
<link rel="stylesheet" type="text/css" href="css/style.css"
	media="screen" />
<!-- SLIDER REVOLUTION 4.x CSS SETTINGS -->
<link rel="stylesheet" type="text/css" href="rs-plugin/css/settings.css"
	media="screen" />
<style>
.content {
	min-height: 500px;
	width: 100%;
	background-color: #fff !important;
	padding-top: 50px;
}

.button_width {
	width: 200px;
}
ul.status li.active .btn-primary {
    color: #fff;
    background-color: #63de63;
    border-color:#63de63;
	
}
li{list-style:none;}
</style>

<script>
function checktime11(id,id1)
{
	 var countDownDate = new Date(id).getTime();

	// Update the count down every 1 second
	var x = setInterval(function() {

	  // Get todays date and time
	  var now = new Date().getTime();

	  // Find the distance between now an the count down date
	  var distance = countDownDate-now;
	  //alert(distance);

	  // Time calculations for days, hours, minutes and seconds
	//  var days = Math.floor(distance / (1000 * 60 * 60 * 24));
	  var hours = Math.floor((distance % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
	  var minutes = Math.floor((distance % (1000 * 60 * 60)) / (1000 * 60));
	  var seconds = Math.floor((distance % (1000 * 60)) / 1000);

	  // Display the result in the element with id="demo"
	 // document.getElementById("checkexpiry").innerHTML = days + "d " + hours + "h "
	 // + minutes + "m " + seconds + "s ";
	  document.getElementById("checkexpiry"+id1).innerHTML = hours + "h "
	  + minutes + "m " + seconds + "s ";

	  // If the count down is finished, write some text 
	  
	  if (hours==0 && minutes==0 && seconds == 0) {
		  var id3=document.getElementById("roll"+id1).value;
		  var id4=document.getElementById("role"+id1).value;
		  var dataString='roleid='+id4+'&rollNumber='+id3;
		  $.ajax({
	    		type:'POST',
	    		data:dataString,
	    		url:'plcstatusupdate',
	    		success:function(data){
	    			if(data=="success")
	    				{
	    				window.location.href = "getOfferProcessor";
	    				}
	    		}
	    	})
	  }
	}, 1000);
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
					<li><a href="#">Home</a></li>

					<li>Offer Processor</li>
				</ul>
			</div>

		</div>
	</div>
        </div> 
<div class="space">		</div>

<div class="container">
 
  <ul class="nav nav-pills">
    
    <li class="active"><a data-toggle="pill" href="#offered">Offered</a></li>
    <li><a data-toggle="pill" href="#awaiting_interview">Awaiting Interview</a></li>
    <li><a data-toggle="pill" href="#summary">Summary</a></li>
	 <li><a data-toggle="pill" href="#offer_rejects">Offer Rejects</a></li>
    <li><a data-toggle="pill" href="#awaiting_offer">Awaiting Offer</a></li>
    <li><a data-toggle="pill" href="#change_offer_status">Change Offer status</a></li>
  </ul>
  
  <div class="tab-content">
    <div id="offered" class="tab-pane fade in active">
	<div class="space"></div>
	<div class="table-responsive">   				
	<table id="example" class="table table-bordered">
    <thead>
	
		 <tr>
                <th>Roll Number</th>
                <th>Student Name</th>
                <th>Firm Name</th>
                <th>Role Name</th>
                <th>Firm Offer Status</th>
                
                <th>Candidate Offer Status</th>
                <th>Candidate Location</th>
                <th>Time to offer Expiry</th>
				 <th>Candidate Pref</th>
				  <th>Opportunities Left</th>
                                                             
             </tr>
        </thead>
        <tbody>
        <c:forEach items="${offeredDetails}" var="fm"
						varStatus="loop">
            <tr>
             <input type="hidden" id="name${loop.index+1}" value="${fm.studentName}">
               <input type="hidden" id="cos${loop.index+1}" value="${fm.candidateOfferStatus}">
                <input type="hidden" id="loc${loop.index+1}" value="${fm.candidateLocation}">
                 <input type="hidden" id="pending1${loop.index+1}" value="${fm.countpending}">
                <td>${fm.rollNumber}</td>
                <td>${fm.studentName}</td>
                <td>${fm.firmName}</td>
                <td>${fm.roleName} </td>
                <td>${fm.firmOfferStatus}</td>
				
				<td>${fm.candidateOfferStatus}</td>
                <td>${fm.candidateLocation}</td>
                <td>xxxx</td>
                <td>${fm.candidatePref}</td>
			    <td><input type="button" class="btn btn-primary" data-toggle="modal" data-target="#C-role-edit" value="Total No. of Interview" onclick="Offer('${loop.index+1}')"></td>
            </tr>
            </c:forEach>
           </tbody> 
         </table>
</div>
    <div class="modal fade" id="C-role-edit" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Total Number of Interview</h4>
        </div>
        <div class="modal-body">
      
        <div class="row">
        <label class="col-md-3">Student Name</label>
        <div class="col-md-9">
        <input type="text" class="form-control" id="tnname1" placeholder="Student Name">  
        </div></div>
        <p></p>
         <div class="row">
         <label class="col-md-3">Interview Pending </label>
        <div class="col-md-9">
       <input type="text" class="form-control" id="tnpending1" placeholder="Interview Pending"> 
        </div></div>
		
		 <div class="row">
         <label class="col-md-3">Offer Status Pending </label>
        <div class="col-md-9">
       <input type="text" class="form-control" id="tnospending1" placeholder="Offer Status Pending"> 
        </div></div>
		
		<div class="row">
         <label class="col-md-3">Current Location</label>
        <div class="col-md-9">
       <input type="text" class="form-control" id="tnlocation1" placeholder="Current Location"> 
        </div></div>
        </div>
      </div>
    </div>
  </div>
  
 </div>
	
	
	
	<div id="awaiting_interview" class="tab-pane fade">
	<div class="space"></div>
   <div class="table-responsive">   				
	<table id="example3" class="table table-bordered">
    <thead>
	
		 <tr>
                <th>Roll Number</th>
                <th>Student Name</th>
                <th>Firm Name</th>
                <th>Role Name</th>
                <th>Firm Offer Status</th>
                
                <th>Candidate Offer Status</th>
                <th>Candidate Location</th>
                <th>Time to offer Expiry</th>
				 <th>Candidate Pref</th>
				  <th>Opportunities Left</th>
				   
                                                             
             </tr>
        </thead>
        <tbody>
           <c:forEach items="${awaitingInterview}" var="ai"
						varStatus="loop">
            <tr id="hiderow${loop.index+1}">
               <input type="hidden" id="role${loop.index+1}" value="${ai.roleid}">
               <input type="hidden" id="roll${loop.index+1}" value="${ai.rollNumber}">
                <input type="hidden" id="name${loop.index+1}" value="${ai.studentName}">
               <input type="hidden" id="cos${loop.index+1}" value="${ai.candidateOfferStatus}">
                <input type="hidden" id="loc${loop.index+1}" value="${ai.candidateLocation}">
                <%--  <input type="hidden" id="pending${loop.index+1}" value="${ai.countpending}"> --%>
                <td >${ai.rollNumber}</td>
                <td>${ai.studentName}</td>
                <td>${ai.firmName}</td>
                <td>${ai.roleName} </td>
                <td>${ai.firmOfferStatus}</td>
				
				<td>${ai.candidateOfferStatus}</td>
                <td>${ai.candidateLocation}</td>
                <td id="checkexpiry${loop.index+1}"></td>
                <td>${ai.candidatePref}
                <script>checktime11('${ai.timeToOfferExpiry}','${loop.index+1}');</script>
                </td>
			    <td><input type="button" class="btn btn-primary" data-toggle="modal" data-target="#modal4" value="Total No. of Interview" onclick="AI('${loop.index+1}')"></td>
			    
            </tr>
            </c:forEach>
            </tbody>
            
         </table>

</div>
   
 <div class="modal fade" id="modal4" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Total Number of Interview</h4>
        </div>
        <div class="modal-body">
      
        <div class="row">
        <label class="col-md-3">Student Name</label>
        <div class="col-md-9">
        <input type="text" class="form-control"  id="tnname" placeholder="Student Name">  
        </div></div>
        <p></p>
         <div class="row">
         <label class="col-md-3">Interview Pending </label>
        <div class="col-md-9">
       <input type="text" class="form-control" id="tnpending" placeholder="Interview Pending"> 
        </div></div>
		
		 <div class="row">
         <label class="col-md-3">Offer Status Pending </label>
        <div class="col-md-9">
       <input type="text" class="form-control" id="tnospending" placeholder="Offer Status Pending"> 
        </div></div>
		
		<div class="row">
         <label class="col-md-3">Current Location</label>
        <div class="col-md-9">
       <input type="text" class="form-control" id="tnlocation" placeholder="Current Location"> 
        </div></div>
        
        </div>
        
     
        
      </div>
      
    </div>
  </div>
   </div>
   
   
<div id="summary" class="tab-pane fade">
<div class="space"></div>
   <div class="table-responsive">   				
	<table id="example2" class="table table-bordered">
    <thead>
	
		 <tr>
                <th>Roll Number</th>
                <th>Student Name</th>
                <th>Firm Name</th>
                <th>Role Name</th>
                <th>Firm Offer Status</th>
                
                <th>Candidate Offer Status</th>
                <th>Candidate Location</th>
                <th>Time to offer Expiry</th>
				 <th>Candidate Pref</th>
				  <th>Opportunities Left</th>
                                                             
             </tr>
        </thead>
        <tbody>
            <c:forEach items="${summary}" var="sm"
						varStatus="loop">
            <tr>
              <input type="hidden" id="name${loop.index+1}" value="${sm.studentName}">
               <input type="hidden" id="cos${loop.index+1}" value="${sm.candidateOfferStatus}">
                <input type="hidden" id="loc${loop.index+1}" value="${sm.candidateLocation}">
                  <input type="hidden" id="pending2${loop.index+1}" value="${sm.countpending}">
                <td>${sm.rollNumber}</td>
                <td>${sm.studentName}</td>
                <td>${sm.firmName}</td>
                <td>${sm.roleName} </td>
                <td>${sm.firmOfferStatus}</td>
				
				<td>${sm.candidateOfferStatus}</td>
                <td>${sm.candidateLocation}</td>
                <td>${sm.timeToOfferExpiry}</td>
                <td>${sm.candidatePref}</td>
			    <td><input type="button" class="btn btn-primary" data-toggle="modal" data-target="#modal3" value="Total No. of Interview" onclick="summary('${loop.index+1}')"></td>
            </tr>
            </c:forEach>
           </tbody> 
            
         </table>

</div>
   
 <div class="modal fade" id="modal3" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Total Number of Interview</h4>
        </div>
        <div class="modal-body">
      
        <div class="row">
        <label class="col-md-3">Student Name</label>
        <div class="col-md-9">
        <input type="text" class="form-control" id="smname" placeholder="Student Name">  
        </div></div>
        <p></p>
         <div class="row">
         <label class="col-md-3">Interview Pending </label>
        <div class="col-md-9">
       <input type="text" class="form-control" id="smpending" placeholder="Interview Pending"> 
        </div></div>
		
		 <div class="row">
         <label class="col-md-3">Offer Status Pending </label>
        <div class="col-md-9">
       <input type="text" class="form-control" id="smospending" placeholder="Offer Status Pending"> 
        </div></div>
		
		<div class="row">
         <label class="col-md-3">Current Location</label>
        <div class="col-md-9">
       <input type="text" class="form-control" id="smlocation" placeholder="Current Location"> 
        </div></div>
        
        </div>
        
       
        
      </div>
      
    </div>
  </div>
	</div>
   
    
<div id="offer_rejects" class="tab-pane fade">
<div class="space"></div>
<div class="table-responsive">   				
	<table id="example1" class="table table-bordered">
    <thead>
	
		 <tr>
                <th>Roll Number</th>
                <th>Student Name</th>
                <th>Firm Name</th>
                <th>Role Name</th>
                <th>Firm Offer Status</th>
                
                <th>Candidate Offer Status</th>
                <th>Candidate Location</th>
                <th>Time to offer Expiry</th>
				 <th>Candidate Pref</th>
				  <th>Opportunities Left</th>
                                                             
             </tr>
        </thead>
        <tbody>
         <c:forEach items="${offerRejects}" var="ore"
						varStatus="loop">
        <tr>
                <input type="hidden" id="name${loop.index+1}" value="${ore.studentName}">
               <input type="hidden" id="cos${loop.index+1}" value="${ore.candidateOfferStatus}">
                <input type="hidden" id="loc${loop.index+1}" value="${ore.candidateLocation}">
               <input type="hidden" id="pending3${loop.index+1}" value="${ore.countpending}"> 
                <td>${ore.rollNumber}</td>
                <td>${ore.studentName}</td>
                <td>${ore.firmName}</td>
                <td>${ore.roleName} </td>
                <td>${ore.firmOfferStatus}</td>
				
				<td>${ore.candidateOfferStatus}</td>
                <td>${ore.candidateLocation}</td>
                <td>${ore.timeToOfferExpiry}</td>
                <td>${ore.candidatePref}</td>
			    <td><input type="button" class="btn btn-primary" data-toggle="modal" data-target="#modal2" value="Total No. of Interview" onclick="OR('${loop.index+1}')"></td>
            </tr>
          </c:forEach>
           </tbody> 
            
         </table>

</div>
 <div class="modal fade" id="modal2" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Total Number of Interview</h4>
        </div>
        <div class="modal-body">
      
        <div class="row">
        <label class="col-md-3">Student Name</label>
        <div class="col-md-9">
        <input type="text" class="form-control" id="orname" placeholder="Student Name">  
        </div></div>
        <p></p>
         <div class="row">
         <label class="col-md-3">Interview Pending </label>
        <div class="col-md-9">
       <input type="text" class="form-control" id="orpending" placeholder="Interview Pending"> 
        </div></div>
		
		 <div class="row">
         <label class="col-md-3">Offer Status Pending </label>
        <div class="col-md-9">
       <input type="text" class="form-control" id="orospending" placeholder="Offer Status Pending"> 
        </div></div>
		
		<div class="row">
         <label class="col-md-3">Current Location</label>
        <div class="col-md-9">
       <input type="text" class="form-control" id="orlocation" placeholder="Current Location"> 
        </div></div>
        
        </div>
        
       
        
      </div>
      
    </div>
  </div>

	</div>
	
	
	
	<div id="awaiting_offer" class="tab-pane fade">
<div class="space"></div>
<div class="table-responsive">   				
	<table id="example4" class="table table-bordered">
    <thead>
	
		 <tr>
                <th>Roll Number</th>
                <th>Student Name</th>
                <th>Firm Name</th>
                <th>Role Name</th>
                <th>Firm Offer Status</th>
                
                <th>Candidate Offer Status</th>
                <th>Candidate Location</th>
                <th>Time to offer Expiry</th>
				 <th>Candidate Pref</th>
				  <th>Opportunities Left</th>
                                                             
             </tr>
        </thead>
        <tbody>
             <c:forEach items="${awaitingOffer}" var="ao"
						varStatus="loop">
        <tr>
         <input type="hidden" id="name${loop.index+1}" value="${ao.studentName}">
               <input type="hidden" id="cos${loop.index+1}" value="${ao.candidateOfferStatus}">
                <input type="hidden" id="loc${loop.index+1}" value="${ao.candidateLocation}">
                <input type="hidden" id="pending4${loop.index+1}" value="${ao.countpending}">
                <td>${ao.rollNumber}</td>
                <td>${ao.studentName}</td>
                <td>${ao.firmName}</td>
                <td>${ao.roleName} </td>
                <td>${ao.firmOfferStatus}</td>
				
				<td>${ao.candidateOfferStatus}</td>
                <td>${ao.candidateLocation}</td>
                <td>${ao.timeToOfferExpiry}</td>
                <td>${ao.candidatePref}</td>
			    <td><input type="button" class="btn btn-primary" data-toggle="modal" data-target="#modal5" value="Total No. of Interview" onclick="AO('${loop.index+1}')"></td>
            </tr>	
            </c:forEach>
           </tbody> 
            
         </table>

</div>
 <div class="modal fade" id="modal5" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Total Number of Interview</h4>
        </div>
        <div class="modal-body">
      
        <div class="row">
        <label class="col-md-3">Student Name</label>
        <div class="col-md-9">
        <input type="text" class="form-control" id="aoname" placeholder="Student Name">  
        </div></div>
        <p></p>
         <div class="row">
         <label class="col-md-3">Interview Pending </label>
        <div class="col-md-9">
       <input type="text" class="form-control" id="aopending" placeholder="Interview Pending"> 
        </div></div>
		
		 <div class="row">
         <label class="col-md-3">Offer Status Pending </label>
        <div class="col-md-9">
       <input type="text" class="form-control" id="aoospending" placeholder="Offer Status Pending"> 
        </div></div>
		
		<div class="row">
         <label class="col-md-3">Current Location</label>
        <div class="col-md-9">
       <input type="text" class="form-control" id="aolocation" placeholder="Current Location"> 
        </div></div>
        
        </div>
        
       
        
      </div>
      
    </div>
  </div>


	  </div>
	
	<div id="change_offer_status" class="tab-pane fade">
  

<div class="space"></div>
  
   <div class="col-md-11">
 
    <div class="tab-content panel_allocation_txt" style="border:none;">
      <div class="tab-pane active text-style" id="tab_six">
        <div>
          <div class="col-md-8 col-md-offset-3">
            <div class="step-content pos-rel">
              <div class="step-pane active" data-step="1">
                <div class="main_box section_one panel panel-primary">
                  <div class="panel-heading"> Update Status </div>
                  <div class="panel-body">
                  <form action="updateststatus" method="post">
                  <input type="hidden" name="firmOfferStatus" id="firmstatusid1">
                   <input type="hidden" name="candidateOfferStatus" id="candidatestatusid1">
                   <div class="row">
        <label class="col-md-3">Firm</label>
        <div class="col-md-9">
        <select class="form-control" name="firmId" onchange="getRole(this);">
        <option value="0">select</option>
        <c:forEach items="${firmlist}" var="fl">
        <option value="${fl.firmId }">${fl.firmName}</option>
         </c:forEach>
        </select>
        </div></div>
        <br>
		<div class="margin_top"></div>
		<div class="row">
        <label class="col-md-3">Role</label>
        <div class="col-md-9">
        <select name="roleid" class="form-control" id="oproleid" onchange="getStudent(this)">
        <option value="0">select role</option>
        </select>
        </div></div>
        <br>
		<div class="margin_top"></div>
		<div class="row">
        <label class="col-md-3">Students</label>
        <div class="col-md-9">
        <select name="rollNumber" class="form-control" id="opstudentid" onchange="getStudentStatus(this)">
        <option value="0">Select Student</option>
        </select>
        </div></div>
        <br>
		<div class="margin_top"></div>
		<div class="row">
		<label class="col-md-3">Firm Offer Status</label>
        <div class="col-md-3">
         <span id="firmstatusid"></span> 
        </div>
        <label class="col-md-3">Student Offer Status</label>
        <div class="col-md-3">
         <span id="stdstatusid" ></span>
        </div></div>
        <br>
<div class="margin_top"></div>
		<div class="row">
        <label class="col-md-3">Change Firm Offer Status</label>
        <div class="col-md-3">
        <select id="firmstatusid2" class="form-control" onchange="setfirmstatusname(this);">
       <option value="0">select</option>
		<option value="1">Offered</option>
		<option value="2">Rejected</option>
		<option value="3">Waitlist</option>
		<option value="4">Next Round</option>
        </select>
        </div>
		<label class="col-md-3">Change Student Offer Status</label>
        <div class="col-md-3">
        <select id="candidatestatusid" class="form-control"  onchange="setcandidatestatusname(this);">
      <option value="0">select</option>
		<option value="1">Accept</option>
		<option value="2">Hold</option>
		<option value="3">Reject</option>
        </select>
        </div>
		</div>
		
		
		<div class="margin_top"></div>
		<div class="form-group col-md-12 text-center" style="margin-top:10px;">  
               <input type="submit" value="Update" class="btn btn-primary">
                    </div>
</form>

	  </div>
  </div>
            
                </div>
              </div>
            </div>
          </div>
          
          <!-- panel preview -->
        
    </div>
</div>
  </div>
  
  </div>
  
  </div>
  
</div>

<div class="space">		</div>

	<jsp:include page="commonJsp/Footer.jsp" />
<script>
			$(document).ready(function() {
            $('#example').DataTable();			
} );


$(document).ready(function() {
            $('#example1').DataTable();			
} );



			$(document).ready(function() {
            $('#example2').DataTable();			
} );

$(document).ready(function() {
            $('#example3').DataTable();			
} );

$(document).ready(function() {
            $('#example4').DataTable();			
} );

//Showing current time
function checkTime(i) {
  if (i < 10) {
    i = "0" + i;
  }
  return i;
}

function startTime() {
  var today = new Date();
  var h = today.getHours();
  var m = today.getMinutes();
  var s = today.getSeconds();
  // add a zero in front of numbers<10
  m = checkTime(m);
  s = checkTime(s);
  document.getElementById('time').innerHTML = h + ":" + m + ":" + s;
  t = setTimeout(function() {
    startTime()
  }, 500);
}
startTime();

//checkbox in dropdown

        $(function () {
            $('#select_student').multiselect({
                includeSelectAllOption: true
            });
            
        });
    
	$(document).ready(function(){
  $('.status li').click(function(){
    $('li').removeClass("active");
    $(this).addClass("active");
});
});
</script>

<script>
	/* function checkTime(j) {
  if (j < 10) {
    j = "0" + j;
  }
  return j;
}

function startoneTime() {
  var today = new Date();
  var h = today.getHours();
  var m = today.getMinutes();
  var s = today.getSeconds();
  // add a zero in front of numbers<10
  m = checkTime(m);
  s = checkTime(s);
  document.getElementById('time_status').innerHTML = h + ":" + m + ":" + s;
  t = setTimeout(function() {
    startoneTime()
  }, 500);
}
startoneTime();
	 */
	 
function getRole(id)
	 {
		 var firmid=id.value;
		 var xhttp=new XMLHttpRequest();
		 xhttp.onreadystatechange=function(){
			 if(this.readyState==4 && this.status==200)
				 {
				 var obj=JSON.parse(this.responseText);
				 var rolelist='';
				 for(var i=0;i<obj.length;i++)
					 {
						rolelist += "<option value="+obj[i].roleid+ ">"
						+ obj[i].rolename + "</option>";
					 }
				 document.getElementById("oproleid").innerHTML = "<option value='0'>Select</option>"
						+ rolelist;
				 }
		 };
			var action = "getOpRoleName?cmpId=" +firmid;
			xhttp.open("POST", action, true);
			xhttp.send();
	 }
	 
	 
	 function getStudent(id)
	 {
		 var roleid=id.value;
		 var xhttp=new XMLHttpRequest();
		 xhttp.onreadystatechange=function(){
			 if(this.readyState==4 && this.status==200)
				 {
				 var obj=JSON.parse(this.responseText);
				 var rolelist='';
				 for(var i=0;i<obj.length;i++)
					 {
						rolelist += "<option value="+obj[i].studentrollno+ ">"
						+ obj[i].studentname + "</option>";
					 }
				 document.getElementById("opstudentid").innerHTML = "<option value='0'>Select</option>"
						+ rolelist;
				 }
		 };
			var action = "getOpStudentName?roleId=" +roleid;
			xhttp.open("POST", action, true);
			xhttp.send();
	 }
	 
	 function getStudentStatus(id)
	 {
		 var stdno=id.value;
		 var roleid=document.getElementById('oproleid').value;
		 var xhttp=new XMLHttpRequest();
		 xhttp.onreadystatechange=function(){
			 if(this.readyState==4 && this.status==200)
			 {
				 var obj=JSON.parse(this.responseText);
				 $('#firmstatusid').html(obj.firmstatus);
				 $('#stdstatusid').html(obj.stdstatus);
			 }
		 };
			var action = "getOpStudentStatus?roleId=" +roleid+"&stdno="+stdno;
			xhttp.open("POST", action, true);
			xhttp.send();
	 }
	 
	 function setfirmstatusname(id)
	 {
		 var id1=id.value;
		 var id2=document.getElementById('firmstatusid2');
		 var name=id2.options[id2.selectedIndex].text;
		 document.getElementById('firmstatusid1').value=name;
	 }
	 
	 function setcandidatestatusname(id)
	 {
		 var id1=id.value;
		 var id2=document.getElementById('candidatestatusid');
		 var name=id2.options[id2.selectedIndex].text;
		 document.getElementById('candidatestatusid1').value=name;
	 }
	 
	 function AI(id)
	 {
	  alert( document.getElementById('pending'+id).value);
	  document.getElementById('tnname').value=document.getElementById('name'+id).value;
	  document.getElementById('tnospending').value=document.getElementById('cos'+id).value;
	  document.getElementById('tnlocation').value=document.getElementById('loc'+id).value;
	  document.getElementById('tnpending').value=document.getElementById('pending'+id).value;
	 }
	 
	 function Offer(id)
	 {
	  document.getElementById('tnname1').value=document.getElementById('name'+id).value;
	  document.getElementById('tnospending1').value=document.getElementById('cos'+id).value;
	  document.getElementById('tnlocation1').value=document.getElementById('loc'+id).value;
	  document.getElementById('tnpending1').value=document.getElementById('pending1'+id).value;
	 }
	 
	 function summary(id)
	 {
	  document.getElementById('smname').value=document.getElementById('name'+id).value;
	  document.getElementById('smospending').value=document.getElementById('cos'+id).value;
	  document.getElementById('smlocation').value=document.getElementById('loc'+id).value;
	  document.getElementById('smpending').value=document.getElementById('pending2'+id).value;
	 }
	
	 function OR(id)
	 {
	  document.getElementById('orname').value=document.getElementById('name'+id).value;
	  document.getElementById('orospending').value=document.getElementById('cos'+id).value;
	  document.getElementById('orlocation').value=document.getElementById('loc'+id).value;
	  document.getElementById('orpending').value=document.getElementById('pending3'+id).value;
	 }
	 
	 function AO(id)
	 {
	  document.getElementById('aoname').value=document.getElementById('name'+id).value;
	  document.getElementById('aoospending').value=document.getElementById('cos'+id).value;
	  document.getElementById('aolocation').value=document.getElementById('loc'+id).value;
	  document.getElementById('aopending').value=document.getElementById('pending4'+id).value;
	 }
</script>
</body>
</html>