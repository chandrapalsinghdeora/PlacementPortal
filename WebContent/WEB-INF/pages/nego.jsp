
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
	border-color: #63de63;
}

li {
	list-style: none;
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
					<li><a href="#">Home</a></li>

					<li>Nego</li>
				</ul>
			</div>

		</div>
	</div>
	<%--         
<div class="clearfix"></div>


<div class="container-fluid">
<div class="content">
  <center>  <h2><b>Nego</b></h2></center> 
</div>
</div> --%>

	<div class="space"></div>

	<div class="container">

		<ul class="nav nav-pills">
             <li  class="active"><a data-toggle="pill" href="#firminfo">Firm Info</a></li>
			<li><a data-toggle="pill" href="#my_firm" onclick="clearvalue();">My
					Firms</a></li>
			<li><a data-toggle="pill" href="#gdstatus">GD Status</a></li>
			<li><a data-toggle="pill" href="#status" onclick="clearvalue();">Status</a></li>
			<li><a data-toggle="pill" href="#logs">Logs</a></li>
			<li><a data-toggle="pill" href="#print">Print</a></li>
			<li><a data-toggle="pill" href="#document">Document</a></li>
			<li><a data-toggle="pill" href="#undo">Undo</a></li>
		 	<li><a data-toggle="pill" href="#uploadExcel">Upload Excel</a></li>
		</ul>

		<div class="tab-content">
			<div id="my_firm" class="tab-pane fade">
				<div class="space"></div>
				<ul class="nav nav-pills">
					<li class="active"><a data-toggle="pill" href="#gd"
						onclick="csd1();clearvalue()"><b>GD</b></a></li>
					<li><a data-toggle="pill" href="#pi" onclick="csd2();clearvalue()"><b>PI</b></a></li>

				</ul>

				<div class="tab-content">
					<div class="space"></div>
					<div id="gd" class="tab-pane fade in active">
						<div class="row">
							<div class="col-md-4">
								<input type="hidden" id="csd5" value="1"> <select
									class="form-control" style="width: 60%;" id="companyid"
									onchange="getRM(this);getRole(this);getWing(this);getnoofpanel(this);">
									<option value="0">Select Company</option>
									<c:forEach items="${companyList}" var="com">
										<option value="${com.companyId}">${com.companyName}</option>
									</c:forEach>

								</select>
							</div>

							<div class="col-md-4">
								<select class="form-control" style="width: 60%;" id="roleid"
									onchange="getRoleDetails(this);getList1(this);getDescription(this);">
									<option value="">Select Role</option>

								</select>
							</div>
						</div>

						<table class="table table-bordered">
							<colgroup>

								<col style="background-color: #edeaea">
								<col style="background-color: white">
								<col style="background-color: #edeaea">
								<col style="background-color: white">
								<col style="background-color: #edeaea">
								<col style="background-color: white">
								<col style="background-color: #edeaea">
							</colgroup>
							<tr>
								<th class="font_weight">RM</th>
								<td id="rmid"></td>
								<th class="font_weight">Wing</th>
								<td id="wingid"></td>
								<th class="font_weight">Nego.</th>
								<td id="negoid"></td>
								<th class="font_weight">No Of Panel</th>
								<td id="panelid"></td>
							</tr>
							<!-- <tr>
								<th>Exp. Count</th>
								<td id="hire"></td>
								<th>Min Count</th>
								<td id="workexpreq"></td>
								<th>Max Count</th>
								<td id="workexpmax"></td>
								<th>CTC Fixed</th>
								<td id="compensation"></td>
							</tr> -->

							<!-- <tr>
								
								<td>CTC Variable</td>
								<td>2.3</td>
								<td>CTC - One Time</td>

								<td>2</td>
							</tr>  -->
						</table>


						<div class="table-responsive" id="gdtable">
							<table id="example1" class="table table-bordered">
							</table>

						</div>



						<div class="row" id="desid">
							<div class="col-md-2"></div>
							<div class="col-md-4">
								<textarea class="form-control" id="description1" placeholder=""> </textarea>
							</div>
							<div class="col-md-2">
								<div class="form-group col-md-12 text-center">
									<input type="submit" value="Update" class="btn btn-primary"
										onclick="csd()">
								</div>
							</div>
							<div class="col-md-3">
								<div class="form-group col-md-12 text-center">
									<input type="submit" value="Update Firm Info"
										class="btn btn-primary">
								</div>
							</div>

						</div>
					</div>




					<div id="pi" class="tab-pane fade" >

						<div class="row">
							<div class="col-md-4">


								<select class="form-control" style="width: 60%;" id="companyid1"
									onchange="getRM1(this);getRole1(this);getWing1(this)">
									<option value="0">Select Company</option>
									<c:forEach items="${companyList1}" var="com">
										<option value="${com.companyId}">${com.companyName}</option>
									</c:forEach>

								</select>
							</div>

							<div class="col-md-4">
								<select class="form-control" style="width: 60%;" id="roleid1"
									onchange="getRoleDetails1(this);getList(this);getDescription(this)">
									<option value="">Select Role</option>

								</select>
							</div>
						</div>

						<table class="table table-bordered">
							<colgroup>

								<col style="background-color: #edeaea">
								<col style="background-color: white">
								<col style="background-color: #edeaea">
								<col style="background-color: white">
								<col style="background-color: #edeaea">
							</colgroup>


							<tr>
								<th class="font_weight">RM</th>
								<td id="rmid1"></td>
								<th class="font_weight">Wing</th>
								<td id="wingid1"></td>
								<th class="font_weight">Nego.</th>
								<td  id="negoid1"></td>
							</tr>
							<tr>
								<th>Exp. Count</th>
								<td id="hire1"></td>
								<th>Min Count</th>
								<td id="workexpreq1"></td>
								<th>Max Count</th>
								<td id="workexpmax1"></td>
							</tr>

							<tr>
								<th>CTC Fixed</th>
								<td id="compensation1"></td>
								<!-- <td>CTC Variable</td>
								<td>2.3</td>
								<td>CTC - One Time</td>

								<td>2</td> -->
							</tr>

						</table>
                     <div class="table-responsive" id="gdtable1">
						<table id="example2" class="table table-bordered">
						</table>
						</div>

						<div class="row">
							<div class="col-md-2"></div>
							<div class="col-md-4">
								<textarea class="form-control" id="description2" placeholder=""></textarea>
							</div>
							<div class="col-md-2">
								<div class="form-group col-md-12 text-center">
									<input type="submit" value="Update" class="btn btn-primary"
										onclick="csd3()">
								</div>
							</div>
                            <form id="updatefirminfo" >
							<input type="hidden" id="updatefirminfohidden" name="updateinforoleid">
							</form>
							<div class="col-md-3">
								<div class="form-group col-md-12 text-center">
									<input type="submit" value="Update Firm Info"
										class="btn btn-primary" onclick="updatePI()">
								</div>
								
							</div>

						</div>


					</div>


				</div>

			</div>

		<%-- 	














			
 --%>


			<div id="firminfo" class="tab-pane fade in active">
				<div class="table-responsive">
					<table id="example" class="table table-bordered">
						<thead>

							<tr>
								<!-- 		<th>CompanyId</th> -->
								<th>Company Name</th>
								<!-- <th>RoleId</th> -->
								<th>Role Name</th>
								<th>Wing Name</th>
								<th>RM Name</th>
								<th>Nego Name</th>
								<th>No Of Panel</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${companyList2}" var="com">
								<tr>
									<%-- <td>${com.companyId}</td> --%>
									<td>${com.companyName}</td>
									<%-- <td>${com.companyRoleId}</td> --%>
									<td>${com.companyRoleName}</td>
									<td>${com.wingId}</td>
									<td>${com.rmName}</td>
									<td>${com.negoName}</td>
									<td>${com.noofpanel}</td>

								</tr>
							</c:forEach>


						</tbody>

					</table>

				</div>


			</div>


<div id="uploadExcel" class="tab-pane fade">
			
			<div class="row">
					<div class="space"></div>
					
					<div class="col-md-12"><form id="upladFile" name="KmIIMStudent" action="uploadExcelNego" method="POST" enctype="multipart/form-data">
						<div class="col-md-6"><input type="file" name="file" id="excelFile" onchange="checkFileTypeForUpload();" /></div>
						<div class="col-md-3"><input class="btn btn-primary" type="button" onclick="submitform();"  value="Upload" name="uplaodFile" /></div>
					</form>
					<form id="excelform" method="get">
					<input type="hidden" name="excel12" id="excel" value="2">
					</form>
					</div>
					
					<div class="space"></div>
					<!-- <a href="downloadDemoFile">Download demo file</a> -->
					<span style="color:red"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;*** Note: Click the below thumbnail to view the format of the file to be uploaded.  
                  <br/>
                   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                   
					
					<a href="images/Capture6.PNG" target="_blank"> 
					<img   src="images/IIMStudentUploadThumbnail.PNG"/> 
					</a>
 
                   </span>
					<div class="space"></div>
					</div>
		</div>

			<div id="gdstatus" class="tab-pane fade ">
				<div class="table-responsive">
					<table id="example5" class="table table-bordered">
						<thead>

							<tr>
							    <th><input type="checkbox" id="main1" ></th>
								<th>Roll Number</th>
								<th>Student Name</th>
								<th>Company Name</th>
								<th>Role Name</th>
								<th>GD Status</th>

							</tr>
						</thead>
						<tbody>
							<c:forEach items="${companyList3}" var="com" varStatus="i">
							   <input type="hidden" name="companyId" id="" >
								<tr>
								   <td><input type="checkbox" 	class="close1_Checkbox checkbox" id=checkbox${com.companyId } value="${com.companyRoleId }##${com.companyId }##${com.rollNumber}"></td>
									<td>${com.rollNumber}</td>
									<td>${com.studentName}</td>
									<td>${com.companyName}</td>
									<td>${com.companyRoleName}</td>
									<td>${com.gdStatus}</td>
								</tr>
					
							</c:forEach>


						</tbody>

					</table>
	<input type="button" value="Update" class="btn btn-primary" id="updatebtn11" onclick="csdd()"/>
				</div>


			</div>

			<div id="status" class="tab-pane fade">

				<div class="space"></div>
				<strong><div id="time_status"
						style="font-size: 18px; float: right; margin-bottom: 10px;">
					</div> </strong>
				<div class="tab-content panel_allocation_txt" style="border: none;">
					<div class="tab-pane active text-style" id="tab_six">
						<div>
							<div class="col-md-6 col-md-offset-3">
								<div class="step-content pos-rel">
									<div class="step-pane active" data-step="1">
										<div class="main_box section_one panel panel-primary">
											<div class="panel-heading">Status</div>
											<div class="panel-body">
												<div class="row">
													<div class="col-md-5">
														<select class="form-control" style="width: 80%;"
															id="statuscompanyid"
															onchange="setvalue();getStatusRole(this);">
															<option value="0">Select Company</option>
															<c:forEach items="${companyList1}" var="com">
																<option value="${com.companyId}">${com.companyName}</option>
															</c:forEach>
														</select>
													</div>
													<div class="col-md-5">
														<input type="text" class="form-control"
															id="statuscompanyname" placeholder="Company Name" disabled>
													</div>


												</div>

												<div class="row" style="margin-top: 10px;">
													<div class="col-md-5">
														<select class="form-control" style="width: 80%;"
															id="statusrole"
															onchange="setrolevalue();getstudent(this);">
															<option value="0">Select Role</option>
														</select>
													</div>
													<div class="col-md-5">
														<input type="text" class="form-control"
															id="statusrolename" placeholder="Role  Name" disabled>
													</div>
												</div>
												<div class="row" style="margin-top: 10px;">
													<div class="col-md-5">
														<select class="form-control" style="width: 80%;"
															id="statusstudent" onchange="setstatusstudent();seteffectivep(this);setfirmoffer();">
															<option value="0">Select Student</option>
														</select>
													</div>
													<div class="col-md-5">
														<input type="text" class="form-control"
															id="statusstudentname" placeholder="Student Name" disabled>
													</div>
												</div>
												<div class="row" style="margin-top: 10px;">
													<div class="col-md-5">
														<p>
															<b>Firm Offer</b>
														</p>

													</div>
													<div class="col-md-5">
														<select class="form-control" style="width: 80%;"
															id="companystatus1">
															<option value="0">select</option>
															<option value="1">Offered</option>
															<option value="2">Rejected</option>
															<option value="3">Waitlist</option>
															<option value="4">Next Round</option>
														</select>
													</div>
												</div>
												<div class="form-group col-md-12 text-center"
													style="margin-top: 10px;">
													<input type="submit" value="Update" class="btn btn-primary" id="update1111"
														onclick="savePlacementStatus();">
												</div>
												<div class="form-group col-md-6">
												
												</div>
												<div class="form-group col-md-6">
												<p>Show update status: <input type="checkbox" id="checkid" onclick="csd12();"></p>
													
												</div>
												<%-- </form> --%>
											</div>
										</div>
									</div>
								</div>
							</div>

							<!-- panel preview -->

						</div>
					</div>
				</div>

				<div class="row">
					<div class="col-md-6">
						<div class="tab-content panel_allocation_txt"
							style="border: none;">
							<div class="tab-pane active text-style" id="tab_six">
								<div>
									<div class="col-md-8 col-md-offset-3" id="offered1"
										style="display: none">
										<div class="step-content pos-rel">
											<div class="step-pane active" data-step="1">
												<div class="main_box section_one panel panel-primary">
													<div class="panel-heading">Student Status</div>
													<div class="panel-body">
														<div class="row">
															<ul class="status">
																<li class="col-md-4">
																	<div
																		class="form-group col-md-12 text-center panel_margin_left ">
																		<input type="submit" value="Accept" id="accept"
																			onclick="acceptstatus()" class="btn btn-primary">
																	</div>
																</li>
																<li class="col-md-4">

																	<div
																		class="form-group col-md-12 text-center panel_margin_left">
																		<input type="submit" value="Hold" id="hold"
																			onclick="holdstatus()" class="btn btn-primary">
																	</div>
																</li>
																<li class="col-md-4">

																	<div
																		class="form-group col-md-12 text-center panel_margin_left">
																		<input type="submit" value="Reject" id="reject"
																			onclick="rejectstatus()" class="btn btn-primary">
																	</div>
																</li>

															</ul>
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

					<div class="col-md-6" id="effectiveid" style="display:none">
                   
						<div class="tab-content panel_allocation_txt"
							style="border: none;">
							<div class="tab-pane active text-style" id="tab_six">
								<div>
									<div class="col-md-8 col-md-offset-3">
										<div class="step-content pos-rel">
											<div class="step-pane active" data-step="1">
												<div class="main_box section_one panel panel-primary">
													<div class="panel-heading">Update Status</div>
													<div class="panel-body">
                                             
														<div class="row" style="margin-top: 10px;">
															<div class="col-md-5">
																<p>
																	<b>Effective Preference</b>
																</p>

															</div>
															<div class="col-md-5">

																<input type="text" class="form-control" placeholder="" id="ep" 
																	disabled>
															</div>

														</div>
														<div class="row">
															<div class="col-md-5">
																<input type="text" class="form-control"
																	id="statusupdate" placeholder="Updated Status" disabled>
															</div>

															<div class="col-md-5">

																<div class="form-group col-md-12 text-center"
																	style="margin-top: 10px;">
																	<input type="submit" value="Update" onclick="epupdate11()"
																		class="btn btn-primary">
																</div>
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
			</div>


			<div id="logs" class="tab-pane fade">


				<strong><div id="time"
						style="font-size: 18px; float: right; margin-bottom: 10px;">
					</div> </strong>


				<div class="table-responsive">
					<table id="example" class="table table-bordered">
						<thead>

							<tr>
								<th>Company</th>
								<th>Student ID</th>
								<th>Name</th>
								<th>Check-In Time</th>
								<th>Interview Start Time</th>

								<th>Interview End Time</th>
								<th>Interview Duration</th>
								<th>Check-out Time</th>

							</tr>
						</thead>
						<tbody>
							<tr>
								<td>Company 1</td>
								<td>46354</td>
								<td>Ram</td>
								<td>8:30</td>
								<td>8:35</td>

								<td>9:35</td>
								<td>60 mins</td>
								<td>9:38</td>

							</tr>

							<tr>
								<td>Company 1</td>
								<td>46354</td>
								<td>Ram</td>
								<td>8:30</td>
								<td>8:35</td>

								<td>9:35</td>
								<td>60 mins</td>
								<td>9:38</td>

							</tr>

							<tr>
								<td>Company 1</td>
								<td>46354</td>
								<td>Ram</td>
								<td>8:30</td>
								<td>8:35</td>

								<td>9:35</td>
								<td>60 mins</td>
								<td>9:35</td>

							</tr>

							<tr>
								<td>Company 1</td>
								<td>46354</td>
								<td>Ram</td>
								<td>8:30</td>
								<td>8:35</td>

								<td>9:35</td>
								<td>60 mins</td>
								<td>9:36</td>

							</tr>
						</tbody>

					</table>

				</div>
			</div>

			<div id="print" class="tab-pane fade">
				<div class="space"></div>
				<div class="row">
					<div class="col-md-2">
						<select class="form-control" style="width: 100%;">
							<option value="">Select Company</option>
							<option value="">Company 1</option>
							<option value="">Company 2</option>
							<option value="">Company 3</option>
						</select>
					</div>
					<div class="col-md-3">
						<div class="form-group col-md-12 text-center">
							<input type="submit" value="Print Offer Summary"
								class="btn btn-primary">
						</div>
					</div>
				</div>
				<div class="clearfix"></div>
				<div class="row">
					<div class="col-md-5">
						<div class="tab-content panel_allocation_txt"
							style="border: none;">
							<div class="tab-pane active text-style" id="tab_six">
								<div>

									<div class="step-content pos-rel">
										<div class="step-pane active" data-step="1">
											<div class="main_box section_one panel panel-primary">
												<div class="panel-heading">Add</div>
												<div class="panel-body">
													<div class="row">
														<div class="col-md-4">
															<div class="form-group col-md-12 text-center list_view">
																<input type="checkbox" class="pull-left" value="">
																<input type="submit" value="CL" class="btn btn-primary">
															</div>
														</div>

														<div class="col-md-4">
															<div class="form-group col-md-12 text-center">
																<input type="checkbox" class="pull-left" value="">
																<input type="submit" value="SL" class="btn btn-primary">
															</div>
														</div>

														<div class="col-md-4">
															<div class="form-group col-md-12 text-center">
																<input type="checkbox" class="pull-left" value="">
																<input type="submit" value="HL" class="btn btn-primary">
															</div>
														</div>
													</div>

													<div class="row">
														<div class="col-md-6">
															<div class="form-group col-md-12 text-center list_view">
																<input type="checkbox" class="pull-left" value="">
																<input type="submit" value="Interviewed"
																	class="btn btn-primary">
															</div>
														</div>

														<div class="col-md-6">
															<div class="form-group col-md-12 text-center">
																<input type="checkbox" class="pull-left" value="">
																<input type="submit" value="Not Eligiable"
																	class="btn btn-primary">
															</div>
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

					<div class="col-md-5">
						<div class="tab-content panel_allocation_txt"
							style="border: none;">
							<div class="tab-pane active text-style" id="tab_six">
								<div>

									<div class="step-content pos-rel">
										<div class="step-pane active" data-step="1">
											<div class="main_box section_one panel panel-primary">
												<div class="panel-heading">Subtract</div>
												<div class="panel-body">
													<div class="row">
														<div class="col-md-4">
															<div class="form-group col-md-12 text-center list_view">
																<input type="checkbox" class="pull-left" value="">
																<input type="submit" value="CL" class="btn btn-primary">
															</div>
														</div>

														<div class="col-md-4">
															<div class="form-group col-md-12 text-center">
																<input type="checkbox" class="pull-left" value="">
																<input type="submit" value="SL" class="btn btn-primary">
															</div>
														</div>

														<div class="col-md-4">
															<div class="form-group col-md-12 text-center">
																<input type="checkbox" class="pull-left" value="">
																<input type="submit" value="HL" class="btn btn-primary">
															</div>
														</div>
													</div>

													<div class="row">
														<div class="col-md-6">
															<div class="form-group col-md-12 text-center list_view">
																<input type="checkbox" class="pull-left" value="">
																<input type="submit" value="Interviewed"
																	class="btn btn-primary">
															</div>
														</div>

														<div class="col-md-6">
															<div class="form-group col-md-12 text-center">
																<input type="checkbox" class="pull-left" value="">
																<input type="submit" value="Not Eligiable"
																	class="btn btn-primary">
															</div>
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
				<div class="row">
					<div class="col-md-2">
						<p>
							<b> Select Student : </b>
						</p>
					</div>
					<div class="col-md-2">
						<select id="select_student" multiple="multiple">
							<option>John</option>
							<option>Manisha</option>
							<option>Yash</option>
							<option>Karsh</option>
						</select>
					</div>

					<div class="col-md-3">
						<select class="form-control" id="form-field-select-1">
							<option>Select Printer</option>
							<option>Wing 1</option>
							<option>Wing 2</option>
							<option>Wing 3</option>
							<option>Wing 4</option>
							<option>Wing 5</option>
							<option>KM Room</option>
							<option>Printing Room</option>
							<option>Upper Hall</option>
						</select>
					</div>
				</div>
				<div class="space"></div>
				<div class="row">
					<div class="col-md-4">&nbsp;</div>
					<div class="col-md-2">
						<div class="form-group col-md-12 text-center">

							<input type="submit" value="Print List" class="btn btn-primary">
						</div>
					</div>

					<div class="col-md-2">
						<div class="form-group col-md-12 text-center">

							<input type="submit" value="Print CV" class="btn btn-primary">
						</div>
					</div>

				</div>
			</div>

			<div id="document" class="tab-pane fade">
				<div class="space"></div>
				<div class="col-md-3">
					<select class="form-control" id="form-field-select-1">
						<option>Select Company</option>
						<option>Company 1</option>
						<option>TCS</option>
						<option>Amazon</option>
						<option>Coca Cola</option>
						<option>Thumbs Up</option>
						<option>Wipro</option>

					</select>
				</div>

			</div>

			<div id="undo" class="tab-pane fade">Undo</div>
		</div>
	</div>
	<form id="updatedescription">
		<input type="hidden" id="companyid5" name="companyId"> <input
			type="hidden" id="roleid5" name="roleId"> <input
			type="hidden" id="description5" name="description"> <input
			type="hidden" id="csd55" name="differ"> <input type="hidden"
			id="insertupdatedescription" name="insertupdatedescription" value="0">
	</form>
	<input type="hidden" id="desid1" value="0">
	<form id="gdstatusupdate">
	<input type="hidden" id="gd11" name="gdupdatestatus">
	</form>
	  <form id="epupdate" >
       <input type="hidden" name="companyId" id="epcompanyid">
        <input type="hidden" name="companyRoleId" id="eproleid">
        <input type="hidden" name="rollNumber" id="epstudentid">
          <input type="hidden" name="studentUpdateStatus"  id="epupdate1id">
            <input type="hidden" name="ep" id="epupdate2id">
        </form>
	<div class="space"></div>
	<jsp:include page="commonJsp/Footer.jsp" />

	<script>
		
	</script>
	<script>
		$(document).ready(function() {
			$('#example').DataTable();
		});
		
		$(document).ready(function() {
			$('#example5').DataTable();
		});

		$(document).ready(function() {
			$('.status li').click(function() {
				$('li').removeClass("active");
				$(this).addClass("active");
			});
		});

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

		$(function() {
			$('#select_student').multiselect({
				includeSelectAllOption : true
			});

		});

		$(document).ready(function() {
			$('.status li').click(function() {
				$('li').removeClass("active");
				$(this).addClass("active");
			});
		});

		/*
		 $(document).ready(function() {
		 $('#gd').click(function() {
		 $('#pi').css("display", "none");
		 });
		 });
		 */
	</script>

	<script>
		function checkTime(j) {
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
			document.getElementById('time_status').innerHTML = h + ":" + m
					+ ":" + s;
			t = setTimeout(function() {
				startoneTime()
			}, 500);
		}
		startoneTime();
	</script>

	<script type="text/javascript">
		function getRM(comId) {
			var id = comId.value;
			var options;
			var options1;
			var options2;
			var options3;
			$('#roleid').prop("disabled", false);
			document.getElementById('desid1').value = 0;
			var xhttp = new XMLHttpRequest();
			xhttp.onreadystatechange = function() {
				if (this.readyState == 4 && this.status == 200) {
					var jsonData = JSON.parse(this.responseText);
					for (var i = 0; i < jsonData.length; i++) {
						options = jsonData[i].rmName;
						options1 = jsonData[i].username;
						options2 = jsonData[i].negoid;
						options3 = jsonData[i].loginid;
					}
					document.getElementById("rmid").innerHTML = options;
					document.getElementById("negoid").innerHTML = options1;
					/*   if(options2!=options3)
					 	 {
					 	 $('#roleid').prop("disabled",true);
					 	 }
					  else
					 	 {
					 	 document.getElementById('desid1').value=1;
					 	 } */
				}
			};
			var action = "getRmName?cid=" + id;
			xhttp.open("POST", action, true);
			xhttp.send();
		}

		function getRM1(comId) {
			var id = comId.value;
			var options;
			var xhttp = new XMLHttpRequest();
			xhttp.onreadystatechange = function() {
				if (this.readyState == 4 && this.status == 200) {
					var jsonData = JSON.parse(this.responseText);
					for (var i = 0; i < jsonData.length; i++) {
						options = jsonData[i].rmName;
						options1 = jsonData[i].username;
					}
					document.getElementById("rmid1").innerHTML = options;
					document.getElementById("negoid1").innerHTML = options1;
				}
			};
			var action = "getRmName?cid=" + id;
			xhttp.open("POST", action, true);
			xhttp.send();
		}

		function getWing(comId) {
			var id = comId.value;
			var options;
			var xhttp = new XMLHttpRequest();
			xhttp.onreadystatechange = function() {
				if (this.readyState == 4 && this.status == 200) {
					var jsonData = JSON.parse(this.responseText);
					for (var i = 0; i < jsonData.length; i++) {
						options = jsonData[i].wingid;
					}
					document.getElementById("wingid").innerHTML = options;
				}
			};
			var action = "getwing?cid=" + id;
			xhttp.open("POST", action, true);
			xhttp.send();
		}
        
		function getWing1(comId) {
			var id = comId.value;
			var options;
			var xhttp = new XMLHttpRequest();
			xhttp.onreadystatechange = function() {
				if (this.readyState == 4 && this.status == 200) {
					var jsonData = JSON.parse(this.responseText);
					for (var i = 0; i < jsonData.length; i++) {
						options = jsonData[i].wingid;
					}
					document.getElementById("wingid1").innerHTML = options;
				}
			};
			var action = "getwing?cid=" + id;
			xhttp.open("POST", action, true);
			xhttp.send();
		}
		
		function getRole(comId) {
			var id = comId.value;
			var xhttp = new XMLHttpRequest();
			xhttp.onreadystatechange = function() {
				if (this.readyState == 4 && this.status == 200) {
					var i;
					var obj = JSON.parse(this.responseText);
					var rolelist = '';
					for (i = 0; i < obj.length; i++) {

						rolelist += "<option value="+obj[i].cmproleid+ ">"
								+ obj[i].designationname + "</option>";
					}
					document.getElementById("roleid").innerHTML = "<option value='0'>Select</option>"
							+ rolelist;
					//  document.getElementById('desid').style="display:none";
					//document.getElementById('gdtable').style="display:none";
				}
			};
			var action = "getRoleName?cmpId=" + id;
			xhttp.open("POST", action, true);
			xhttp.send();
		}
		function getRole1(comId) {
			var id = comId.value;
			var xhttp = new XMLHttpRequest();
			xhttp.onreadystatechange = function() {
				if (this.readyState == 4 && this.status == 200) {
					var i;
					var obj = JSON.parse(this.responseText);
					var rolelist = '';
					for (i = 0; i < obj.length; i++) {

						rolelist += "<option value="+obj[i].cmproleid+ ">"
								+ obj[i].designationname + "</option>";
					}
					document.getElementById("roleid1").innerHTML = "<option value='0'>Select</option>"
							+ rolelist;
				}
			};
			var action = "getRoleName?cmpId=" + id;
			xhttp.open("POST", action, true);
			xhttp.send();
		}
		function getRoleDetails(id) {
			var id1 = id.value;
			var x, y, z, p;
			var xhttp = new XMLHttpRequest();
			xhttp.onreadystatechange = function() {
				if (this.readyState == 4 && this.status == 200) {
					var jsonData = JSON.parse(this.responseText);
					for (var i = 0; i < jsonData.length; i++) {
						x = jsonData[i].compensation;
						y = jsonData[i].workexp;
						z = jsonData[i].workreqmax;
						p = jsonData[i].hires;
					}
					document.getElementById("compensation").innerHTML = x;
					document.getElementById("workexpreq").innerHTML = y;
					document.getElementById("workexpmax").innerHTML = z;
					document.getElementById("hire").innerHTML = p;
				}
			};
			var action = "getRoleDetails?roleid=" + id1;
			xhttp.open("POST", action, true);
			xhttp.send();

		}

		function getRoleDetails1(id) {
			var id1 = id.value;
			//alert(id1);
			var x, y, z, p;
			var xhttp = new XMLHttpRequest();
			xhttp.onreadystatechange = function() {
				if (this.readyState == 4 && this.status == 200) {
					var jsonData = JSON.parse(this.responseText);
					for (var i = 0; i < jsonData.length; i++) {
						x = jsonData[i].compensation;
						y = jsonData[i].workexp;
						z = jsonData[i].workreqmax;
						p = jsonData[i].hires;
					}
					document.getElementById("compensation1").innerHTML = x;
					document.getElementById("workexpreq1").innerHTML = y;
					document.getElementById("workexpmax1").innerHTML = z;
					document.getElementById("hire1").innerHTML = p;
				}
			};
			var action = "getRoleDetails?roleid=" + id1;
			xhttp.open("POST", action, true);
			xhttp.send();

		}

		function getList(id) {
			var id1 = id.value;
			var xhttp = new XMLHttpRequest();
			xhttp.onreadystatechange = function() {
				if (this.readyState == 4 && this.status == 200) {
					document.getElementById("example2").innerHTML = this.responseText;
					$('#example2').DataTable();
				}
			};
			xhttp.open("POST", "getTheList?roleid=" + id1, true);
			xhttp.send();
		}

		function getList1(id) {
			var id1 = id.value;
			var xhttp = new XMLHttpRequest();
			xhttp.onreadystatechange = function() {
				if (this.readyState == 4 && this.status == 200) {
					document.getElementById("example1").innerHTML = this.responseText;
					$('#example1').DataTable();
				}
			};
			xhttp.open("POST", "getTheGDList?roleid=" + id1, true);
			xhttp.send();
		}

		function csd() {
			var id1 = document.getElementById("companyid").value;
			var id2 = document.getElementById("roleid").value;
			var id3 = document.getElementById("description1").value;
			var id4 = document.getElementById("csd5").value;
			document.getElementById("companyid5").value = id1;
			document.getElementById("roleid5").value = id2;
			document.getElementById("description5").value = id3;
			document.getElementById("csd55").value = id4;
			document.getElementById("updatedescription").action = "updatedescription";
			document.getElementById("updatedescription").method = "post";
			document.forms["updatedescription"].submit();
		}

		function csd1() {
			document.getElementById("csd5").value = 1;
		}
		function csd2() {
			document.getElementById("csd5").value = 2;
		}
		function csd3() {
			var id1 = document.getElementById("companyid1").value;
			var id2 = document.getElementById("roleid1").value;
			var id3 = document.getElementById("description2").value;
			var id4 = document.getElementById("csd5").value;
			document.getElementById("companyid5").value = id1;
			document.getElementById("roleid5").value = id2;
			document.getElementById("description5").value = id3;
			document.getElementById("csd55").value = id4;
			document.getElementById("updatedescription").action = "updatedescription";
			document.getElementById("updatedescription").method = "post";
			document.forms["updatedescription"].submit();
		}

		function getDescription(id) {
			var roleid = id.value;
			var id1 = document.getElementById('csd5').value;
			document.getElementById('description1').value = '';
			document.getElementById('description2').value = '';
			document.getElementById('insertupdatedescription').value = 0;
			var xhttp = new XMLHttpRequest();
			xhttp.onreadystatechange = function() {
				if (this.readyState == 4 && this.status == 200) {
					var jsonData = JSON.parse(this.responseText);
					for (var i = 0; i < jsonData.length; i++) {
						if (id1 == 1) {
							document.getElementById('description1').value = jsonData[i].description;
							document.getElementById('insertupdatedescription').value = 1;
						} else {
							document.getElementById('description2').value = jsonData[i].description;
							document.getElementById('insertupdatedescription').value = 1;
						}
					}
				}
			};
			var action = "getTheDescription?roleid=" + roleid + "csd" + id1;
			xhttp.open("POST", action, true);
			xhttp.send();
		}

		function setvalue() {
			var cname = document.getElementById('statuscompanyid');
			var cname1 = cname.options[cname.selectedIndex].text;
			document.getElementById('statuscompanyname').value = cname1;
		}

		function getStatusRole(comId) {
			var id = comId.value;
			var xhttp = new XMLHttpRequest();
			xhttp.onreadystatechange = function() {
				if (this.readyState == 4 && this.status == 200) {
					var i;
					var obj = JSON.parse(this.responseText);
					var rolelist = '';
					for (i = 0; i < obj.length; i++) {

						rolelist += "<option value="+obj[i].cmproleid+ ">"
								+ obj[i].designationname + "</option>";
					}
					document.getElementById("statusrole").innerHTML = "<option value='0'>Select</option>"
							+ rolelist;
				}
			};
			var action = "getRoleName?cmpId=" + id;
			xhttp.open("POST", action, true);
			xhttp.send();
		}

		function setrolevalue() {
			var rolename = document.getElementById('statusrole');
			var rolename1 = rolename.options[rolename.selectedIndex].text;
			document.getElementById('statusrolename').value = rolename1;
		}

		function getstudent(id) {
			var roleid = id.value;
			var xhttp = new XMLHttpRequest();
			xhttp.onreadystatechange = function() {
				if (this.readyState == 4 && this.status == 200) {
					var obj = JSON.parse(this.responseText);
					var rolelist = '';
					for (var i = 0; i < obj.length; i++) {
						rolelist += "<option value="+obj[i].rollno+">"
								+ obj[i].studentname + "</option>";
					}
					document.getElementById('statusstudent').innerHTML = "<option value='0'>select</option>"
							+ rolelist;
				}
			};
			var action = "getStudent?roleid=" + roleid;
			xhttp.open("POST", action, true);
			xhttp.send();
		}

		function setstatusstudent() {
			var sname = document.getElementById('statusstudent');
			var sname1 = sname.options[sname.selectedIndex].text;
			document.getElementById('statusstudentname').value = sname1;
		}

		function acceptstatus() {
			var r = confirm("are you sure you want to accept");
			if (r == true) {
							document.getElementById('statusupdate').value = "accept";
							}
						
		else {
				return false;
			}
		}

		function holdstatus() {
			var r = confirm("are you sure you want to hold");
			if (r == true) {
									document.getElementById('statusupdate').value = "hold";
							}
			else {
				return false;
			}
		}

		function rejectstatus() {
			var r = confirm("are you sure you want to reject");
			if (r == true) {
									document.getElementById('statusupdate').value = "reject";
							}
						
			 else {
				return false;
			}
		}

		function savePlacementStatus() {
			var companyid = document.getElementById('statuscompanyid').value;
			var roleid = document.getElementById('statusrole').value;
			var studentid = document.getElementById('statusstudent').value;
			var stname = document.getElementById('companystatus1');
			var stname1 = stname.options[stname.selectedIndex].text;
			var sname = document.getElementById('statusstudent');
			var sname1 = sname.options[sname.selectedIndex].text;
			var dataString = 'companyId=' + companyid + '&companyRoleId='
					+ roleid + '&studentId=' + studentid + '&studentName='
					+ sname1 + '&companyStatus=' + stname1;

			$
					.ajax({
						type : 'POST',
						data : dataString,
						url : 'updateplacementstatus',
						success : function(data) {
							if (data == 'success') {
								if (document.getElementById('companystatus1').value == 1) {
									$("#companystatus1").prop("disabled", true);
									$("#offered1").show();
								}
							}
						}
					});
		};

		/* function getNegoValidation(id)
		{
			var appid=id.value;
			var dataString='appid='+appid;
			$.ajax({
				type:'POST',
				data:dataString,
				url:'getNegoValidation',
				success:function(data)
				{
					if(data==1)
						{
						 $('#description1').prop("disabled",true);
						}
						
				}
			});
		} */

		/* function showdiv()
		{
			var id=document.getElementById('desid1').value;
			if(id==1)
				{
				$('#desid').show();
				$('#gdtable').show();
				}
		} */

		function getnoofpanel(id) {
			var appid = id.value;
			document.getElementById('panelid').innerHTML = '';
			var xhttp = new XMLHttpRequest();
			xhttp.onreadystatechange = function() {
				if (this.readyState == 4 && this.status == 200) {
					var obj = JSON.parse(this.responseText);
					for (var i = 0; i < obj.length; i++) {
						document.getElementById('panelid').innerHTML = obj[i].noofpanel;
					}
				}
			};
			var action = "getnoofpanel1?appid=" + appid;
			xhttp.open("POST", action, true);
			xhttp.send();
		}
		
		function csd12()
		{
			document.getElementById('effectiveid').style="display:none";
			if($('#checkid').prop("checked")==true)
				{
				$('#effectiveid').show();
				}
		}
		function checkFileTypeForUpload(){
			 var fileName=document.getElementById("excelFile").value;
			    var ext = $('#excelFile').val().split('.').pop().toLowerCase();
			    if($.inArray(ext, ['xlsx','xls']) == -1) {
		            alert("Only .xlsx .xls File are allowed to upload.")
		            document.getElementById("excelFile").value="";
			    }else{
			            
			    }
		}
		
		function submitform(){
			var id=document.getElementById("excelFile").value;
			if(id==''){
				alert("Please select file to upload.")
			}
			else{
				document.getElementById("upladFile").submit();
			}
			
		}
		
		
		function csdd(){
	    	  valArr = [];
	   	    if ($(".close1_Checkbox").length > 0){
	   			$('.close1_Checkbox:checked').each(function(i) {
	   			 var c=$(this).val();
	   				valArr[i] =c;
	   				
	   			});
	   			if(valArr.length>0){
	   				gdupdate(valArr);
	   				
	   			}else{
	   				alert("Please select at least one check box");
	   				return false;
	   			}
	   	   	}
	   }
		
	function gdupdate(valArr)
	{
		document.getElementById('gd11').value=valArr.toString();
		document.getElementById('gdstatusupdate').action="gdstatusupdate";
		document.getElementById('gdstatusupdate').method="POST";
		document.getElementById('gdstatusupdate').submit();
	}
	
	
	 $("#main1").click(function () {
        $('.close1_Checkbox').prop('checked', this.checked);
    });

    $(".close1_Checkbox").click(function () {
        if ($(".close1_Checkbox").length == $(".case:checked").length) {
            $("#main1").prop("checked", "checked");
        } else {
            $("#main1").removeProp("checked");
        }
    });
    
    
    function clearvalue()
    {
    	document.getElementById('companyid').value='0';
    	document.getElementById('roleid').value='0';
    	document.getElementById('description1').value='';
    	document.getElementById('rmid').innerHTML='';
    	document.getElementById('wingid').innerHTML='';
    	document.getElementById('negoid').innerHTML='';
    	document.getElementById('panelid').innerHTML='';
    	document.getElementById('example1').innerHTML='';
    	document.getElementById('companyid1').value='0';
    	document.getElementById('roleid1').value='0';
    	document.getElementById('description2').value='';
    	document.getElementById('rmid1').innerHTML='';
    	document.getElementById('wingid1').innerHTML='';
    	document.getElementById('negoid1').innerHTML='';
    	document.getElementById('hire1').innerHTML='';
    	document.getElementById('workexpreq1').innerHTML='';
    	document.getElementById('workexpmax1').innerHTML='';
    	document.getElementById('compensation1').innerHTML='';
    	document.getElementById('example2').innerHTML='';
    	document.getElementById('statuscompanyid').value='0';
    	document.getElementById('statusrole').value='0';
    	document.getElementById('statuscompanyname').value='';
    	document.getElementById('statusrolename').value='';
    	document.getElementById('statusstudent').value='0';
    	document.getElementById('statusstudentname').value='';
    	document.getElementById('companystatus1').value='0';
    	$('#checkid').prop("checked",false);
    	document.getElementById('ep').value='';
    	document.getElementById('statusupdate').value='';
    	document.getElementById('effectiveid').style="display:none"
    }
    
    function seteffectivep(id)
    {
    	var rollno=id.value;
    	var roleid=document.getElementById('statusrole').value;
    	var xhttp = new XMLHttpRequest();
		xhttp.onreadystatechange = function() {
			if (this.readyState == 4 && this.status == 200) {
				var obj = JSON.parse(this.responseText);
				for (var i = 0; i < obj.length; i++) {
					document.getElementById('ep').value = obj[i].effective;
				}
			}
		};
		var action = "geteffectivepreference?rollno="+rollno+"csd"+roleid;
		xhttp.open("POST", action, true);
		xhttp.send();
    }
    
    function epupdate11()
    {
    	var epcompanyid=document.getElementById('statuscompanyid').value;
    	var eproleid=document.getElementById('statusrole').value;
    	var epstudentid=document.getElementById('statusstudent').value;
    	var epstatus=document.getElementById('statusupdate').value;
    	var ep1=document.getElementById('ep').value;
    	/* document.getElementById('epupdate').action="epupdate";
    	document.getElementById('epupdate').method="post";
    	document.getElementById('epupdate').submit(); */
    	var dataString='companyId='+epcompanyid+'&companyRoleId='+eproleid+'&rollNumber='+epstudentid
                   +'&studentUpdateStatus='+epstatus+'&ep='+ep1;
    	$.ajax({
    		type:'POST',
    		data:dataString,
    		url:'epupdate',
    		success:function(data){
    			if(data=="success")
    				{
    				document.getElementById('statuscompanyid').value='0';
    				document.getElementById('statusrole').value='0';
    				document.getElementById('statusstudent').value='0';
    				document.getElementById('statusupdate').value='';
    				document.getElementById('companystatus1').value='0';
    				document.getElementById('ep').value='';
    				document.getElementById('statuscompanyname').value='';
    				document.getElementById('statusrolename').value='';
    				document.getElementById('statusstudentname').value='';
    				document.getElementById('effectiveid').style="display:none"
    				document.getElementById('offered1').style="display:none"
    				$('#checkid').prop("checked",false);
    				$('#companystatus1').prop("disabled",false);
    				}
    		}
    	})
    }
    
    function updatePI()
    {
    	var id=document.getElementById('roleid1').value;
    	document.getElementById('updatefirminfohidden').value=id;
    	document.getElementById('updatefirminfo').action="updatefirminfo";
		document.getElementById('updatefirminfo').method="POST";
		document.getElementById('updatefirminfo').submit();
    }
    
    function setfirmoffer()
    {
    	document.getElementById('update1111').disabled=false;
		document.getElementById('companystatus1').disabled=false;
		document.getElementById('companystatus1').value=0;
    	var id=document.getElementById('statuscompanyid').value;
    	var id1=document.getElementById('statusrole').value;
    	var id2=document.getElementById('statusstudent').value;
    	var xhttp = new XMLHttpRequest();
		xhttp.onreadystatechange = function() {
			if (this.readyState == 4 && this.status == 200) {
				var obj = JSON.parse(this.responseText);
				if(obj.length>0)
					{
				var status=obj[0].status;
				var sname = document.getElementById('companystatus1');
				var sname1;
				 for (var j = 0; j < sname.options.length; j++) {
					  sname1 = sname.options[j].text;
	    			 if (sname1==status ) {
	    				 sname.options[j].selected = true;
	    				 if(status=="Offered")
	    					 {
	    					  document.getElementById('update1111').disabled=true;
	    					  document.getElementById('companystatus1').disabled=true;
	    					 }
	    		        }
    	           } 
					}
			}
		};
		var action = "getfirmoffer?cid="+id+"&roleid="+id1+"&sid="+id2;
		xhttp.open("POST", action, true);
		xhttp.send();
    }
	</script>
	

</body>

</html>