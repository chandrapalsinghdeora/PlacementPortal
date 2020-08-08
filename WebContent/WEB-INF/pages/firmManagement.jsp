<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
	<div class="clearfix"></div>
	<div class="breadcumb-wrapper">
            <div class="clearfix">
                <div class="pull-left">
                    <ul class="list-inline link-list">
                        <li><a href="addfineValues">Home</a></li>
                        <li><a href="appManage">Manage Application</a></li>
                        <li>Add Firm</li>
                    </ul>
                </div>
             
            </div>
        </div>
	<div class="space"></div>
	<div class="container">
		<div class="col-sm-12">
			<input type="button" class="btn btn-primary pull-right"
				onclick="clearCompanyForm();" data-toggle="modal"
				data-target="#C-role-edit" value="Add New Firm">
		</div>
	</div>
	<div class="space"></div>
	<form id="firmManagement" method="post"
		name="FirmManagementForm" enctype="multipart/form-data">

		<div class="container">
			<div class="main_box section_one panel panel-primary">
				<div class="panel-heading">Firm Management</div>
				<div class="panel-body">

					<div class="row">
						<div class="  border">
							<div class="col-sm-3 ui-widget">
								<p>Name of the Firm <font color='red'>*</font></p>
							</div>

							<div class="col-sm-3 inpur_box">
								<div id="error" align="center"></div>
								<input id="compId" class="form-control" name="companyName"
									placeholder="Name of the Firm " required 
									onblur="checkCompanyName(this)"/>
									
								<%-- <select class="form-control" name="companyId" required>
								<option>select</option>
								<c:forEach items="${companyList}" var="cluster">
									<option value="${cluster.companyId}">${cluster.companyName}</option>
								</c:forEach>
							</select> --%>

							</div>
							<div id="compError"></div>
						</div>
					</div>

					<div class="row">
						<div class="  border">

						<!-- <div class="col-sm-3 inpur_box">
								<input type="text" class="form-control" name="firmName"
									placeholder="Firm Name" required>
							</div> -->

							<div class="col-sm-3 ui-widget">
								<p>Process <font color='red'>*</font></p>
							</div>
							<div class="col-sm-3 inpur_box">
								<div id="error" align="center"></div>
							<!--<div class="ui-widget">
		                         	<input id="procId" class="form-control" name="processName" 
		                            	placeholder="Process "  />
								</div> -->

								<select class="form-control" name="processId" required>
									<option value="">Select</option>
									<c:forEach items="${processList}" var="process">
										<option value="${process.processId}">${process.processName}</option>
									</c:forEach>
								</select>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="  border">
							<div class="col-sm-3 ui-widget">
								<p>Year <font color='red'>*</font></p>
							</div>

						<!--<div id="error" align="center"></div>
                             <div class="ui-widget">
                             	<input id="yearId" class="form-control" name="year" placeholder="year " />
								</div> 
							</div>-->

							<div class="col-sm-3 inpur_box">
								<div id="error" align="center"></div>
								<select class="form-control" name="year" id="yearId">
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
								<p>Cluster <font color='red'>*</font></p>
							</div>

							<div class="col-sm-3 inpur_box">
						<!-- 	<input id="clustId" class="form-control" name="clusterName"
								placeholder="Cluster" onchange="getCohortNameByCName(this);"
									required /> 
						
								<div class="col-sm-3">
									<p>Cluster *</p>
								</div>-->

								<select class="form-control" id="clusterId" name="clusterName"
									onchange="getCohortNameByCName(this);">
									<option value="">Select</option>
									<c:forEach items="${clusterList}" var="cluster"
										varStatus="loop">
										<option value="${cluster.clusterName}">${cluster.clusterName}</option>
									</c:forEach>
								</select>

							</div>
						</div>
					</div>
					<div class="row">
						<div class="  border">
							<div class="col-sm-3 ui-widget">
								<p>Cohort <font color='red'>*</font></p>
							</div>

							<div class="col-sm-3 inpur_box">

								<select class="form-control" id="cohortId" name="cohortName">
									<option value="">Select</option>
								</select>
							</div>
						</div>
					</div>

					<div id="internshipsbox1">
						<div class="row">
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
					</div>

					<div id="internships"></div>
					<div class="col-sm-offset-9 col-sm-3 text-center">
						<button class="btn btn-primary" id="add">
							<i class="fa fa-plus" aria-hidden="true"></i>
						</button>
						<button class="btn btn-primary" id="remove">
							<i class="fa fa-minus" aria-hidden="true"></i>
						</button>
					</div>
					<div class="row">
						<div class="  border">
							<div class="col-sm-3">
								<p>Preference</p>
							</div>
							<div class="col-sm-3">
								<input type="checkbox" name="preferenceServe"
									placeholder="Preference">
							</div>
							
							
							<div class="col-sm-3">
								<p>HR Hotlist</p>
							</div>
							<div class="col-sm-3">
								<input type="checkbox" name="hrHotlist">
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
									placeholder="Multiple CV Relates" >
							</div>

						</div>
					</div> -->

					 <div class="row">
						<div class="  border">
							<div class="col-sm-3">
								<p>Number of Roles <font color='red'>*</font></p>
							</div>
							<div class="col-sm-3 inpur_box">
								<input type="text" class="form-control" name="limitCVNo"
									placeholder="Number of Roles" required>
							</div>
						</div>
					</div> 

					<div class="row">
						<div class="border">
							<div class="col-sm-3">
								<p>No.Of Selection Round (GD/Test/Forms) <font color='red'>*</font></p>
							</div>

							<div class="col-sm-3 inpur_box">
								<div id="error" align="center"></div>
								<div class="ui-widget">
									<input id="noOfRoundId" class="form-control" name="noOfRound"
										placeholder="Selection Round " required="required" />
								</div>
							</div>
						</div>
					</div>

					<div class="row">
						<div class="  border">
							<div class="col-sm-3">
								<p>Opening Date & Time<font color='red'>*</font></p>
							</div>
							<div class="col-sm-3 inpur_box">
								<div class="input-group">
									<input id="date-timepicker1"
										class="form-control input-group date" type="text"
										name="openingDate" required="required"> <span
										class="input-group-addon"> <i
										class="fa fa-clock-o bigger-110"></i>
									</span>
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="  border">
							<div class="col-sm-3">
								<p>Closing Date & Time <font color='red'>*</font></p>
							</div>
							<div class="col-sm-3 inpur_box">
								<div class="input-group">
									<input id="closeingDate" class="form-control input-group date"
										type="text" name="closeingDate" required="required"> <span
										class="input-group-addon"> <i
										class="fa fa-clock-o bigger-110"></i>
									</span>
								</div>
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
									placeholder="Extra Details(if any) that need to be posted"> </textarea>
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
									placeholder="Interview Experience URL">
							</div>
						</div>
					</div>
					<div class="row">
						<div class="  border">
							<div class="col-sm-3">
								<p>Internship Experience URL</p>
							</div>
							<div class="col-sm-3 inpur_box">
								<input type="text" class="form-control"
									name="internshipExperience"
									placeholder="Internship Experience URL">
							</div>
						</div>
					</div>
					<div class="row">
						<div class="  border">
							<div class="col-sm-3 tsize">Job Description</div>
							<div class="col-sm-3 tsize">
								<input id="id-input-file-2" type="file" name="jobDescription">
							</div>
						</div>
					</div>
					<div id="additionalbox">
						<div class="row">
							<div class="border">
								<div class="col-sm-3 tsize">Additional Files</div>
								<div class="col-sm-3 tsize">
									<input id="id-input-file-2" type="file" name="additionalFile">
								</div>
							</div>
						</div>
					</div>

					<div id="additional"></div>
					<div class="col-md-offset-6 col-md-12 col-sm-12">
						<button class="btn btn-primary" id="add-additional">
							<i class="fa fa-plus" aria-hidden="true"></i>
						</button>
						<button class="btn btn-primary" id="remove-additional">
							<i class="fa fa-minus" aria-hidden="true"></i>
						</button>
						<!-- <input type="button" value="+" class="btn btn-primary"
							id="add-additional" /> <input type="button" value="-"
							class="btn btn-primary" id="remove-additional" /> -->
					</div>
				</div>
			</div>
			<div class="main_box section_one panel panel-primary role">
				<div class="panel-heading">Role 1</div>
				<div id="internshipbox1">
					<div class="panel-body">
						<div class="row">
							<div class="  border">
								<div class="col-sm-3 ui-widget">
									<p>Role <font color='red'>*</font></p>
								</div>
								<div class="col-sm-3 inpur_box">
									<!-- <input  id="roleId"  class="form-control" name="roleCompany" required/>  -->
									<select class="form-control" name="roleCompanyId" required>
										<option value="">select</option>
										<c:forEach items="${roleList}" var="role">
											<option value="${role.roleCompanyId}">${role.roleCompany}</option>
										</c:forEach>
									</select>
								</div>
								<div class="col-sm-3 ui-widget rolemapping">
									<p>Role CV Mapping</p>
								</div>
								<div class="col-sm-3 rolemapping">
									<select class="form-control" name="mapRoleId" id="mapRoleId">
										<option value="0">Select Role</option>
										<option value="1">Role 1</option>
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
							<div class="border">
								<div class="col-sm-3">
									<p>Cover Letter</p>
								</div>
								<div class="col-sm-3 inpur_box">
								
								<input type="hidden" name="coverLetterSelected" value="" id="coverLetterSelected"> 	
									<input type="checkbox" name="coverLetter" 
										placeholder="Cover Letter" >
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
										placeholder="Minimum Work Exp Requirement">
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
										placeholder="Maximum Work Exp Requirement">
								</div>
								Only Months
							</div>
						</div>


						<div class="row">
							<div class="  border">
								<div class="col-sm-3">
									<p>CTC/Stipend</p>
								</div>
								<div class="col-sm-3 inpur_box">
									<input type="text" class="form-control" name="compensation"
										placeholder="Unit of payment (Lakhs)">
								</div>
							</div>
						</div>

						<div class="row">
							<div class="  border">
								<div class="col-sm-3">
									<p>Minimum Expected No. Offers</p>
								</div>
								<div class="col-sm-3 inpur_box">
									<input type="number" class="form-control" name="noHire"
										value="0" placeholder="Minimum Expected No. Of Offers">
								</div>
							</div>
						</div>
						<div class="row">
							<div class="  border">
								<div class="col-sm-3">
									<p>Maximum Expected No. Offers</p>
								</div>
								<div class="col-sm-3 inpur_box">
									<input type="number" class="form-control" name="maxNoHire"
										value="0" placeholder="Maximum Expected No. Of Offers">
								</div>
							</div>
						</div>
						<div class="row">
							<div class="  border">
								<div class="col-sm-3">
									<p>Average No. Of Offers</p>
								</div>
								<div class="col-sm-3 inpur_box">
									<input type="number" class="form-control" name="avgNoHire"
										value="0" placeholder="Average Expected No. Of Offers">
								</div>
							</div>
						</div>
						<!-- <div class="row">
						<div class="  border">
							<div class="col-sm-3">Map Interview Experience</div>
							<div class="col-sm-3">
								<select class="form-control">
									<option>Mapped To SAT com Date</option>
									<option>Laterals</option>
									<option>Summers</option>
									<option>Finals</option>
									<option>Offcampus</option>
								</select>
							</div>
						</div>
					</div> -->
						<!-- div class="row">
						<div class="  border">
							<div class="col-sm-3">Map Internship Experience</div>
							<div class="col-sm-3">
								<select class="form-control">
									<option>Mapped to SAT com Date</option>
									<option>Laterals</option>
									<option>Summers</option>
									<option>Finals</option>
									<option>Offcampus</option>
								</select>
							</div>
						</div>
					</div> -->

						<!-- <div class="row">
						<div class="  border">
							<div class="col-sm-3">
								<p>Interview Experience URL</p>
							</div>
							<div class="col-sm-3 inpur_box">
								<input type="text" class="form-control" name="interviewExperience"
									placeholder="Interview Experience URL" required>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="  border">
							<div class="col-sm-3">
								<p>InternShip Experience URL</p>
							</div>
							<div class="col-sm-3 inpur_box">
								<input type="text" class="form-control" name="internshipExperience"
									placeholder="Internship Experience URL" required>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="  border">
							<div class="col-sm-3 tsize">Job Description</div>
							<div class="col-sm-3 tsize">
								<input id="id-input-file-2" type="file" name="jobDescription">
							</div>
						</div>
					</div>
					<div class="row">
						<div class="  border">
							<div class="col-sm-3 tsize">Additional Files</div>
							<div class="col-sm-3 tsize">
								<input id="id-input-file-2" type="file" name="additionalFile">
							</div>
						</div>
					</div> -->
					</div>
				</div>
			</div>
			<div id="internships1"></div>
			<div class="col-sm-offset-9 col-sm-3 text-center">
				<!-- <button class="btn btn-primary" id="add-panel">Add Role</button>
				<button class="btn btn-primary" id="remove-panel">Remove
					Role</button> -->
				<a href="javascript:void(0)" class="btn btn-primary" id="add-panel">Add
					Role</a> <a href="javascript:void(0)" class="btn btn-primary"
					id="remove-panel">Remove Role</a>
			</div>
			<!-- <div class="marg">
				<input type="submit" value="Submit" class="btn btn-primary" /> <input
					type="button" value="Cancel" class="btn btn-primary" />
			</div> -->

			<div class="space"></div>
			<div class="col-md-12 text-center">
				<!-- <input type="submit" value="Submit" class="btn btn-primary" /> -->
				 <button type="button" class="btn btn-default" onclick="saveFirmForm();">Submit</button>
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
				<div id="compError1"></div>
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
									required="required" onblur="checkCompanyName1(this)" >
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
					<div class="modal-footer text-center">
						<button type="submit" id="submit1" class="btn btn-default" disabled>Submit</button>
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

function comanycheck(){
	 
        if(document.getElementById("companyName").value == ""){
            document.getElementById("companynameError").innerHTML = "You must enter a Company Name";
            return false;
        }
	 
        if(document.getElementById("companyDescription").value == ""){
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
				var data = $('#additionalbox').html();
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
				var data1 = $('#internshipsbox1').html();
				if (x < 11) {
					var domElement = $('' + data1 + '');
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
		 /* $('#date-timepicker1').datetimepicker().on('dp.change', function (e) {
             var incrementDay = moment(new Date(e.date));
             incrementDay.add(1, 'days');
             $('#closeingDate').data('DateTimePicker').minDate(incrementDay);
             $(this).data("DateTimePicker").hide();
         });

         $('#closeingDate').datetimepicker().on('dp.change', function (e) {
             var decrementDay = moment(new Date(e.date));
             decrementDay.subtract(30, 'days');
             $('#closeingDate').data('DateTimePicker').maxDate(decrementDay);
              $(this).data("DateTimePicker").hide();
         }); */
         
         
         
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
		$(document)
				.ready(
						function() {
							var x = 1;
							$("#add-panel")
									.click(
											function() {
												var wrapper = $('#internships1');
												var data1 = $('#internshipbox1')
														.html();

												if (x < 11) {
													var domElement = $(' <div class="main_box section_one panel panel-primary"><div class="panel-heading">Role'
															+ (x+1)
															+ '</div>'
															+ data1
															+ '</div></div>');

													/*  if(x>0 && x<2)
														domElement.append('<div class="clearfix"></div><div class="col-sm-12 chkpref"><input type="checkbox"  name="preferenceServe" />Preference</div>');
													 */$(wrapper).append(
															domElement);
													x++;
												}
												
												if(x>1)	{
													$('#mapRoleId').append( '<option value="'+x+'">'+'Role '+(x)+'</option>' );
												}

											});
							 $("#remove-panel").click(function() {
									
								 var main = document.getElementById("internships1");
								 var main1 = document.getElementById("mapRoleId");
								 if(x>1)
								 x--;	
								 if (main.children.length > 0) {           
									 main.lastChild.remove(); }
									  if (main1.children.length > 0) {           
									 main1.lastChild.remove(); }
										
						    });

						});
	</script>



	<script>
	var arr = [];
	var arr1=[];
		$(function() {
			var jsonData = ${compArray}	;
			
			for ( var x in jsonData) {
				//arr.push(jsonData[x].companyId);
				arr.push(jsonData[x].companyName);
				arr1.push(jsonData[x].companyName);
			}
			//alert($("#compId").val());
			$("#compId").autocomplete({
				source : arr
			});
			
			$("#companyName").autocomplete({
				source : arr1
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
		
		/* $(function() {
			var cohortData = ${cohortArray};
			var cohortarr = [];
			for ( var x in cohortData) {
				alert(cohortData[x].cohortId+" ::: "+cohortData[x].cohortName);
				cohortarr.push(cohortData[x].cohortId);
			}
			alert("cohortarr :: "+cohortarr);
			$("#cohortId").autocomplete({
				source : cohortarr
			}); 

		}); */
		
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
			 var yeararr = ["2016","2017","2018"];
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
			 var roundarr = ["1","2"];
			/* for ( var x in yearData) {
				
				yeararr.push(yearData[x].year);
			} */
			$("#noOfRoundId").autocomplete({
				source : roundarr
			}); 
			

		});
		
	</script>
	<script>

		 function getCohortNameByCName(cname) {
				document.getElementById("cohortId").innerHTML="";
				//alert("inside method::");
				var clusterName=cname.value;
				//alert(" clusetid ::"+clusterName);
				var xhttp = new XMLHttpRequest();
				xhttp.onreadystatechange = function() {
					if (this.readyState == 4 && this.status == 200) {
					var options="";
						var obj = JSON.parse(this.responseText);
						var cohortList = '';
						for ( var x in obj) {
							var valueIs=obj[x].cohortName;
							options+='<option value='+obj[x].cohortId+'>'+valueIs+'</option>';
						}
						//alert("options :: "+ options);
						document.getElementById("cohortId").innerHTML=options;
					}
					
				};
				var action = "getCohortByClusterName?cname=" + clusterName;
				xhttp.open("GET", action, true);
				xhttp.send(); 
			}
		 
		 
		 
		 function checkCompanyName(cname) {
			//alert(cname);
			if(arr.length>0){ 
				$("#compError").html('');
				for(i=0;i<arr.length;i++){
					//alert("cname--"+arr[i]+"name"+cname.value);
					 if(arr[i]==cname.value){
						 $("#compError").html('');
						 return false;
					}
					else{
						$("#compError").html("<font color='red'>Company name does not exist!!</font>");
					} 
				}
			 }else{
				$("#compError").html("<font color='red'>Company name does not exist!!</font>");
				return false;
			 }
		}
		 
		 
		 function checkCompanyName1(cname) {
				if(arr1.length>0){ 
					$("#compError1").html('');
					for(i=0;i<arr1.length;i++){
						//alert("cname--"+arr[i]+"name"+cname.value);
						 if((arr1[i].toUpperCase())==cname.value.toUpperCase()){
							 $("#compError1").html("<font color='red'>Company name already exist!!</font>");
							 document.getElementById('submit1').disabled=true;
							 return false;
						}
						else{
							 $("#compError1").html('');
							 document.getElementById('submit1').disabled=false;
						} 
					}
				 }else{
					 $("#compError1").html('');
					 document.getElementById('submit1').disabled=false;
				 }
			}
		 
		 
		function saveFirmForm(){			 
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
		    			
		    var coverLetter= document.getElementById("coverLetterSelected");
		  	coverLetter.value=txt; 
			//----------------------------------------------------------------------
						
		  	var compName=document.getElementById("compId");			  
			var cohortId=document.getElementById("cohortId");
			var clusterName=document.getElementById("clusterId");		
			var cohortName = cohortId.options[cohortId.selectedIndex].text;
			var year=document.getElementById("yearId");
			//var yearVal=year.options[year.selectedIndex].text;
			//alert(yearVal+"  "+year.value);
			var checkCompany=false;
			var xhttp = new XMLHttpRequest();
			xhttp.onreadystatechange = function() {
				if (this.readyState == 4 && this.status == 200) {				
				var obj = JSON.parse(this.responseText);								
				checkCompany=obj.checkCompany;				
				if(checkCompany==true){
					alert("Comany-"+compName.value+", Cluster-"+clusterName.value+" and Cohort-"+cohortName+" for the year-"+year.value+" already exist !! Please choose different combination !!");
					return false;
				}else{
					document.getElementById("firmManagement").action = "saveFirmForm";
				    document.getElementById("firmManagement").method = "post";
				  	document.getElementById("firmManagement").submit();  
				}				
				}				
			};
			var action = "checkCompanyClusterCohort?compName="+compName.value+"&clusterName="+clusterName.value+"&cohortId="+cohortId.value+"&year="+year.value;
			xhttp.open("GET", action, true);
			xhttp.send(); 			
			//----------------------------------------------------------------------
		  	
			//document.getElementById("firmManagement").action = "saveFirmForm";
		    //document.getElementById("firmManagement").method = "post";
		  	//document.getElementById("firmManagement").submit();  			  
			  
		}
	</script>


</body>
</html>