<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Indian Institute of Management Ahmedabad (IIMA) | Home - IIMA</title>
    <link rel="icon" href="images/favicon.ico" type="image/x-icon"/>

<link rel="stylesheet" href="assets/css/bootstrap-datetimepicker.min.css" />
<link rel="stylesheet" href="assets/css/ace.min.css" class="ace-main-stylesheet" id="main-ace-style" />
</head>
<body>
	<!-- Header started -->
	<jsp:include page="commonJsp/Header.jsp" />
	<div class="clearfix"></div>
	<div class="breadcumb-wrapper">
		<div class="clearfix">
			<div class="pull-left">
				<ul class="list-inline link-list">
					<li><a href="addfineValues">Home</a></li>
					<li><a href="appManage">Firm Managment</a></li>
					<li>Open Status Schedule</li>
				</ul>
			</div>

		</div>
	</div>
	<div class="clearfix"></div>
	<div class="clearfix"></div>
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<h3> ${schedulestatus.firmName} - ${schedulestatus.roleName} - ${schedulestatus.year} </h3>
			</div>


		</div>
		<div class="row">

			<!-- panel preview -->

			<div class="container-fluid btn-container">
				<a href="getRMMessagePage" class="btn btn-primary pull-right">Message</a>
				 <a	href="#" class="btn btn-primary pull-right" data-toggle="modal"
					data-target="#rm-status-schedule" onclick="clearFields();">Add Schedule</a>
			</div>
			<div class="clearfix"></div>

			<table id="main-fourum" class="table table-striped table-bordered">
				<thead>
					<tr>

						<th>Sr. No.</th>
						<th>Date/Time</th>
						<th>Subject</th>
						<th>Edit</th>
						<th>Delete</th>

					</tr>
				</thead>
				<tbody>
					<c:forEach var="sch" items="${schedule}" varStatus="d">
						<tr>

							<td>${d.count}</td>
							<td>${sch.dateTime}</td>
							<td>${sch.subject}</td>
							<td><input type="button" class="btn btn-primary"
								data-toggle="modal" data-target="#rm-status-schedule"
								value="Edit"
								onclick="getScheduleByScheduleId('${sch.scheduleId}');">

							</td>
							<td align='center'><a href="#" class="btn btn-primary"
								style="color: white;" onclick="deleteSch('${sch.scheduleId}');">
									Delete</a></td>

						</tr>
					</c:forEach>

				</tbody>
			</table>

			<form method="post" id="deleteScheduleForm">
				<input type="hidden" name="scheduleId" id="scheduleId" value="" />
			</form>

			<div class="modal fade" id="rm-status-schedule" role="dialog">
				<div class="modal-dialog">
					<!-- Modal content-->
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal">&times;</button>
							<h4 class="modal-title">Schedule</h4>
						</div>
						<div class="modal-body">
							<form id="scheduleForm">
								<input type="hidden" name="scheduleId" value="0"
									id="hiddenscheduleId" />
								<input type="hidden" name="cmpId" value="${schedulestatus.cmpId}"
									id="cmpId" />
									
								<div class="row">
									<label class="col-md-6">Date/Time</label>
									<div class="col-md-6">
										<!-- <input type="date" name="dateTime" value="" id="dateTime" class="form-control" /> -->
											 <div class="input-group">
															<input id="date-timepicker1" class="form-control" name="scheduleDate" type="text">
															<span class="input-group-addon">
																<i class="fa fa-clock-o bigger-110"></i>
															</span>
														</div>
									</div>
								</div>

								<div class="row">
									<label class="col-md-6">Subject</label>
									<div class="col-md-6 selectSubject">
										<select class="form-control" id="subjectId" name="subjectId">
											<option value="">---Select Subject Title -----</option>
											<c:forEach var="msg" items="${messages}">
												<option value="${msg.messageId}">${msg.subject}</option>
											</c:forEach>

										</select>
									</div>
								</div>


								<div class="modal-footer">
									<button type="button" class="btn btn-default"
										onclick="saveSchedule();">Save</button>
									<button type="button" class="btn btn-default"
										data-dismiss="modal">Cancel</button>
								</div>
							</form>
						</div>

					</div>
				</div>

			</div>
			
			
			<div class="space"></div>

			 <div class="row">
<div class="col-md-12"><a href="appManage" class="btn btn-primary">Back</a></div>


 </div>
			<!-- / panel preview -->

		</div>
	</div>

	<div class="space"></div>

	
	
		<jsp:include page="commonJsp/Footer.jsp" />
		<script src="assets/js/bootstrap-datepicker.min.js"></script>
		<script src="assets/js/bootstrap-timepicker.min.js"></script>
		<script src="assets/js/moment.min.js"></script>
	    <script src="assets/js/bootstrap-datetimepicker.min.js"></script>
		 <script src="assets/js/ace.min.js"></script>
          <script type="text/javascript">
			jQuery(function($) {
				
				
			
			$('#timepicker1').timepicker({
					minuteStep: 1,
					showSeconds: true,
					showMeridian: false,
					disableFocus: true,
					icons: {
						up: 'fa fa-chevron-up',
						down: 'fa fa-chevron-down'
					}
				}).on('focus', function() {
					$('#timepicker1').timepicker('showWidget');
				}).next().on(ace.click_event, function(){
					$(this).prev().focus();
				});
				
				
				if(!ace.vars['old_ie']) 
				
				$('#date-timepicker1').datetimepicker({
				 //format: 'MM/DD/YYYY h:mm:ss A',//use this option to display seconds
				 format : 'DD-MM-YYYY HH:mm:ss',
				 icons: {
					time: 'fa fa-clock-o',
					date: 'fa fa-calendar',
					up: 'fa fa-chevron-up',
					down: 'fa fa-chevron-down',
					previous: 'fa fa-chevron-left',
					next: 'fa fa-chevron-right',
					today: 'fa fa-arrows ',
					clear: 'fa fa-trash',
					close: 'fa fa-times'
				 },
					defaultDate : moment(),
				}).next().on(ace.click_event, function(){
					$(this).prev().focus();
				});
				
			
			});
		</script>
		
		<script>
   function saveSchedule(){
	    document.getElementById("scheduleForm"). action="saveSchedule";
	    document.getElementById("scheduleForm").method = "post";
	    document.getElementById("scheduleForm").submit(); 
   }
 
   function deleteSch(id){
		$("#scheduleId").val(id);
		document.getElementById("deleteScheduleForm").action = "deleteScheduleData";
		$("#deleteScheduleForm").submit();
	}
   
   function getScheduleByScheduleId(scheduleId){
		 var xhttp = new XMLHttpRequest();
		 xhttp.onreadystatechange = function() {
			 if (this.readyState == 4 && this.status == 200) {
				
			     var obj = JSON.parse(this.responseText);	
			    // alert(obj.dateTime+" :: "+obj.subject+"::"+obj.subjectId+"::"+obj.scheduleId)
			     $("#dateTime").val(obj.dateTime) ;
			     $("#hiddenscheduleId").val(obj.scheduleId);
			     $("div.selectSubject select").val(obj.subjectId);
			 }
		 };
		 var action="getScheduleByScheduleId?scheduleId="+scheduleId;
		 xhttp.open("POST", action, true);
		 xhttp.send();  
		  
	   }
   
   function clearFields(){
	   document.getElementById("date-timepicker1").value=" "; 	
	   document.getElementById("subjectId").value=" " ; 	   
   }
   
</script>
</body>
</html>