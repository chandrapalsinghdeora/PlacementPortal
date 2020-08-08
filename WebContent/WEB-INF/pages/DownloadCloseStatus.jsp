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
		<div class="space"></div>
	
		<div class="closed-status">
	
			<div class="col-md-12">
					
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
				
						<table id="main-fourum4"
							class="table table-bordered display nowrap">
							<thead>
								<tr>
									<th><input type="checkbox"></th>
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
										<td><input type="checkbox" name="selector[]" class="close1_Checkbox" id="Checkbox${fm.applyId}" value="${fm.applyId}"></td>
										<td>${fm.rollnumber}</td>
										<td><a href="#" data-toggle="modal"
											data-target="#closed-status-email"
											onclick="getValues('${fm.rollnumber}');">${fm.emailId}</a></td>
										<td>${fm.name}</td>
										<td><a href="#" data-toggle="modal"
											data-target="#cv-title-popup"
											onclick="getCVValues('${fm.rollnumber}');">${fm.cv}</a></td>
										<td>${fm.prefrences}</td>
										<td>${fm.status}</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						<div class="space"></div>
						<div class="col-md-12 text-right">
							<input type="button" value="Download CV File"
								class="btn btn-primary" id="downloadBtn"/>
							<input type="button"
								value="Genrate Shortlist link" class="btn btn-primary"
								data-toggle="modal"  id="shortListBtn" data-target="#shortlist-link-popup" />
				
							<div class="modal fade" id="shortlist-link-popup" role="dialog">
								<div class="modal-dialog">
									<div class="modal-content">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal">&times;</button>
											<h4 class="modal-title text-left">List Send to Firm</h4>
										</div>
										<form id="insertShortList" action="shortList" method="POST"	name="CloseStatus">
											<input type="hidden"  name="updateApplyId" id="updateApplyId" value="0"/>
											
											<input type="hidden"  name="roleId" id="roleId" value=" ${closeStatus.roleId}"/>
											<div class="modal-body">
												<div class="row">
													<label class="col-md-6 text-right">Firm HR Email Id :
													</label>
													<div class="col-md-6">
														<input type="text" class="form-control" id="emailId" name="emailId">
													</div>
												</div>
												<div class="row">
													<div class="col-md-12 text-left">
														<input type="checkbox" id="rankFlag" name="rankFlag"/> Send Prefrences
													</div>
												</div>
												<div class="row">
													<div class="col-md-12 text-left">
														<textarea rows="3"  class="form-control" id="mailDes" name="mailDes"></textarea>
													</div>
												</div>
											</div>
											<div class="modal-footer text-center">
											 	<button type="submit" class="btn btn-primary" id="insertList" >Save</button>
												<button type="button" class="btn btn-primary"
													data-dismiss="modal">Close</button>
											</div>
										</form>
									</div>
								</div>
							</div>
						</div>
				
						<div class="space"></div>
				
					
				
			</div>
	
		</div>
	
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
	
						<!-- <div class="row">
							<div class="col-md-12 text-center">
								<a href="#" class="btn btn-primary">View Profile</a>
							</div>
						</div> -->
					</div>
					<div class="modal-footer">
						
						<button type="button" class="btn btn-primary" data-dismiss="modal">Cancel</button>
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
			<div class="modal-dialog modal-sm">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 class="modal-title">rajnish@gmail.com</h4>
					</div>
					<div class="modal-body" id="CVData"></div>
					<div class="modal-footer text-center">
						<button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
					</div>
				</div>
	
			</div>
		</div>
	
		<jsp:include page="commonJsp/Footer.jsp" />
		<!-- get jQuery from the google apis -->
				<!--<script type="text/javascript" src="js/jquery-1.12.4.js"></script> -->
		<!-- <script type="text/javascript" src="js/jquery.min.js"></script>
		<script type="text/javascript" src="js/jquery.dataTables.min.js"></script> -->
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
					buttons : [ 'excel', 'pdf', 'print' ]
				});
				
				$(function() {
				    $('.checkbox').click(function() {
				        if ($('.checkbox:checked').length > 0) {
				            $('#verifyBtn').removeAttr('disabled');
				        } else {
				            $('#verifyBtn').attr('disabled', 'disabled');
				        }
				    });
				});     
				
			});
			$(document).ready(function() {
	
				$('#main-fourum2').DataTable({
					dom : 'Bfrtip',
					buttons : [ 'excel', 'pdf', 'print' ]
				});
	
				$('#main-fourum3').DataTable({
					dom : 'Bfrtip',
					buttons : [ 'excel', 'pdf', 'print' ]
				});
	
				$('#main-fourum4').DataTable({
					dom : 'Bfrtip',
					buttons : [ 'excel', 'pdf', 'print' ]
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
				var action = "getDownloadUserValues?rollNumber=" + rollNumber;
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
				var action = "getDownloadInternshipList?rollNumber=" + rollNumber;
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
				var action = "getDownloadExperienceList?rollNumber=" + rollNumber;
				xhttp.open("POST", action, true);
				xhttp.send();
			}
	
			function getCVValues(rollNumber) {
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
						$("#CVData").html(CVData);
					}
				};
				var action = "getDownloadCVList?rollNumber=" + rollNumber;
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
				var action = "getDownloadInfoValues";
				xhttp.open("POST", action, true);
				xhttp.send();
			}
	
			var valArrDownload = [];
			$(function() {
				$('#downloadBtn').click(function() {
					valArrDownload = [];
					$('.close1_Checkbox:checked').each(function(i) {
						valArrDownload[i] = $(this).val();
					});
					//alert(valArrDownload);
					downloadCV(valArrDownload);
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
			var valArrShortlist = [];
			$(function() {
				$('#shortListBtn').click(function() {
					valArrShortlist = [];
					$("#updateApplyId").val();
					$('.close1_Checkbox:checked').each(function(i) {
						valArrShortlist[i] = $(this).val();
					});
					//alert("generate link :: "+valArrShortlist);
					$("#updateApplyId").val(valArrShortlist.toString());
					//alert("value :: "+$("#updateApplyId").val());
				});
			});
	
			
		</script>
	
	</body>
</html>