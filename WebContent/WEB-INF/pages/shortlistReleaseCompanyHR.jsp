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

<!-- Custom -->
<link href="css/custom.css" rel="stylesheet">
<link rel="stylesheet" href="css/font-awesome.css">

<!-- CSS STYLE-->
<link rel="stylesheet" type="text/css" href="css/style.css" media="screen" />
<link rel="stylesheet" type="text/css" href="css/dataTables.bootstrap.min.css" media="screen" />
<link rel="stylesheet" type="text/css" href="css/buttons.dataTables.min.css" media="screen" />
<link rel="stylesheet" type="text/css" href="css/jquery.dataTables.min.css" media="screen" />
</head>
<body>
<jsp:include page="commonJsp/Header.jsp" />


<div class="breadcumb-wrapper">
  <div class="clearfix">
    <div class="pull-left">
      <ul class="list-inline link-list">
        <li><a href="#">Home</a></li>
        <li><a href="#">Firm Managment</a></li>
        <li><a href="#">Manage Application</a></li>
        <li>Short list Release</li>
      </ul>
    </div>
  </div>
</div>
<div class="space"></div>
<div class="container min-height">
  <div class="row">
    <h3> ${closeStatus.firmName} - ${closeStatus.roleName} - ${closeStatus.year} </h3>
  </div>
  
  <!-- panel preview -->
  <div class="container-fluid btn-container"> <a href="#" class="pull-right" data-toggle="modal" data-target="#rm-status-schedule-msg4" onclick="getInfoValues();"> <i class="fa fa-info-circle" aria-hidden="true"></i> </a>
    <label class="pull-right"> [ ${closeStatus.process}] : [${closeStatus.experienceReq} months]  : </label>
    <div class="modal fade" id="rm-status-schedule-msg4" role="dialog">
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
  </div>
  <div class="clearfix"></div>
  <form name="shortlistedReleaseForm" id="shortlistedReleaseFormId"> 
  <table id="main-fourum" class="table table-bordered display nowrap">
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
      <c:forEach items="${shortlist}" var="shrt">
     
        <tr>
       
        <td>${shrt.rollNumber}  <input type="hidden" value="${shrt.shortListId}##${shrt.applyId}" name="idlist"/></td>
        <td><a href="#" data-toggle="modal" data-target="#closed-status-email4" onclick="getUserDetailsByRolNo('${shrt.rollNumber}');"> ${shrt.emailID}</a>
          <div class="modal fade" id="closed-status-email4" role="dialog">
            <div class="modal-dialog">
              <div class="modal-content">
                <div class="modal-header">
                  <button type="button" class="close" data-dismiss="modal">&times;</button>
                  <h4 class="modal-title">
                  <span id="emailId"></span>
                  </h4>
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
                 <!--  <div class="row">
                    <div class="col-md-12 text-center"><a href="#" class="btn btn-primary">View Profile</a></div>
                  </div> -->
                </div>
                <div class="modal-footer">
                  <!-- <button type="button" class="btn btn-primary">Save</button> -->
                  <button type="button" class="btn btn-primary" data-dismiss="modal">Cancel</button>
                </div>
              </div>
            </div>
          </div></td>
         <td> ${shrt.name} </td>
         <td> ${shrt.preference} </td>
         <td>${shrt.status} </td>
         
      </tr>
     </c:forEach> 
    </tbody>
  </table>
  
  <div class="space"></div>
  <div class="col-md-12 text-right">
    <input type="button" value="Shortlist Final" class="btn btn-primary" onclick="saveShortlistReleaseHR();"/>
    <input type="button" value="Generate Hotlist Link" data-toggle="modal" data-target="#generate-shortlist-link" class="btn btn-primary" />
   
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
  <div class="space"></div>
</div>

<!-- panel preview -->

<jsp:include page="commonJsp/Footer.jsp" />


<!-- get jQuery from the google apis --> 
<!--<script type="text/javascript" src="js/jquery-1.12.4.js"></script> --> 

<!-- <script type="text/javascript" src="js/jquery.dataTables.min.js"></script> 
<script type="text/javascript" src="js/dataTables.buttons.min.js"></script> 
<script type="text/javascript" src="js/buttons.flash.min.js"></script> 
<script type="text/javascript" src="js/jszip.min.js"></script> 
<script type="text/javascript" src="js/pdfmake.min.js"></script> 
<script type="text/javascript" src="js/vfs_fonts.js"></script> 
<script type="text/javascript" src="js/buttons.html5.min.js"></script> 
<script type="text/javascript" src="js/buttons.print.min.js"></script> 
<script type="text/javascript" src="js/bootstrap.js"></script>  -->

<!-- <script>
       
	  $(document).ready(function() {
    $('#main-fourum').DataTable( {
        dom: 'Bfrtip',
        buttons: [
            'excel', 'pdf', 'print'
        ]
    } );
} );

</script> -->

<script>
function getUserDetailsByRolNo(rollNo){
	   var xhttp = new XMLHttpRequest();
		xhttp.onreadystatechange = function() {
			if (this.readyState == 4 && this.status == 200) {
				this.responseText;
				var i;
				var obj = JSON.parse(this.responseText);
				//obj.inboxSubject = eval("(" + obj.inboxSubject + ")");

				document.getElementById("emailId").innerHTML = obj[0].emailId;
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

function getInfoValues() {
	  // alert("info values");
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


function saveShortlistReleaseHR(){
    document.getElementById("shortlistedReleaseFormId").action = "saveReleaseShortlisted";
	document.getElementById("shortlistedReleaseFormId").method = "post";
	document.getElementById("shortlistedReleaseFormId").submit(); 
	   
}
 
function saveSendMail(){
	 document.getElementById("shortlistedReleaseFormId").action = "saveSendMail";
	 document.getElementById("shortlistedReleaseFormId").method = "post";
	 document.getElementById("shortlistedReleaseFormId").submit(); 
}

</script>

</body>
</html>