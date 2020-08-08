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
<title>Indian Institute of Management Ahmedabad (IIMA) | Home -	IIMA</title>
<link rel="icon" href="images/favicon.ico" type="image/x-icon" />
<!-- Bootstrap -->
<!-- <link href="css/bootstrap.css" rel="stylesheet"> -->

<!-- Custom -->
<!-- <link href="css/custom.css" rel="stylesheet">
<link rel="stylesheet" href="css/font-awesome.css">

CSS STYLE
<link rel="stylesheet" type="text/css" href="css/style.css"
	media="screen" />
<link rel="stylesheet" type="text/css"
	href="css/dataTables.bootstrap.min.css" media="screen" />
<link rel="stylesheet" type="text/css"
	href="css/buttons.dataTables.min.css" media="screen" />-->
<link rel="stylesheet" type="text/css"
	href="css/jquery.dataTables.min.css" media="screen" /> 
	<link rel="stylesheet" type="text/css"
	href="css/buttons.dataTables.min.css" media="screen" />
	
<!-- Styling for the Upload Excel and Browse buttons -->

</head>
<body>
	<jsp:include page="commonJsp/Header.jsp" />
	<%-- ${closeStatus.noOfSelectionRounds} --%>
	<form method="post" id="downloadCV">
		<input type="hidden" name="downloadCvId" id="downloadCvId" />
		<input type="hidden" name="coverLetter" id="coverLetter" value="0">
	</form>
	
	<div class="space"></div>

	<div class="closed-status">
		<aside class="col-md-2">
			<nav class="nav-sidebar">
				<ul class="nav tabs">
				 	<c:choose>
						<c:when test="${closeStatus.noOfSelectionRounds=='2'}"> 
							<li class="active"><a href="#tab1" data-toggle="tab">Enter GD Shortlist</a></li>
							<li ><a href="#tab5" data-toggle="tab">Enter PI Shortlist</a></li>
						</c:when> 
						<c:otherwise>
							<li class="active" ><a href="#tab5" data-toggle="tab">Enter PI Shortlist</a></li>
						</c:otherwise>
					</c:choose>
					
					<%-- <c:choose>	
					    <c:when test="${closeStatus.hrHotlist==true}"> --%>
							<li class=""><a href="#tab7" data-toggle="tab">Final Shortlist</a></li>
						<%-- </c:when>
						<c:otherwise>
							<li class=""><a href="#tab7" data-toggle="tab">View Shortlisted </a></li>
						</c:otherwise>
					</c:choose> --%>

				</ul>
			</nav>
		</aside>

		<div class="col-md-10">
			<div class="tab-content">
					<c:choose>
						<c:when test="${closeStatus.noOfSelectionRounds=='2'}"> 
							<div class="tab-pane active text-style" id="tab1">
								
								<div style="float: left; padding-right: 12px">
				   						  <input type="file"
									       id="excelfile2" 
									       class="btn btn-primary"/>
								</div>
			
				   				<div style="float: left">
				   					<input type="button" 
							   		  class ="btn btn-primary" 
							   		  id="viewfile" 
							   		  value="Upload Excel File" 
							   		  onclick="Populatecheckbox('main-fourum2','excelfile2')"/>
								</div>
								
								<div class="col-md-12">
									<h3>${closeStatus.firmName}-${closeStatus.roleName} -
										${closeStatus.year}</h3>
								</div>
			
								<div class="clearfix"></div>
								<form name="shortListOneform" id="shortListOneform">
									<input type="hidden" value="" name="shortListOneIds"
									id="shortListOneIds" />
									<input type="hidden" name="cmpRoleId" value="${cmpRoleId}">
									<div class="table-responsive">
														
									<table id="main-fourum2"
										class="table table-bordered ">
										<thead>
											<tr>
												<th>Roll Number</th>
												<th>Email ID</th>
												<th>Name</th>
												<c:if test="${closeStatus.prefrences==1}">
												<th>Preference</th>
												</c:if>
												<th>Status</th>
												<th>Shortlist <input type="checkbox" id="verfiylistbtn112"></th>
											</tr>
										</thead>
										<tbody>
											<c:choose><c:when test="${fn:length(shortlist1)==0}">No Data Available</c:when>
											<c:otherwise>
											<c:forEach items="${shortlist1}" var="shrt">
												<input type="hidden" value="${shrt.shortListId}"
													name="shortListId" />
												<c:choose>
												<c:when test="${shrt.shortListBy=='HR'}">
												<tr style=' background-color: #d1620a;color:white;'>
												</c:when>
												<c:otherwise><tr></c:otherwise>
												</c:choose>
												
													<td>${shrt.rollNumber}</td>
													<td>${shrt.emailID}
													</td>
													<td>${shrt.cvName}</td>
													<c:if test="${closeStatus.prefrences==1}">
													<td>${shrt.preference}</td>
													</c:if>
													<td>${shrt.status}</td>
													<td align="center">
														<c:choose>
															<c:when test="${shrt.status=='Withdrawal' or shrt.shortListBy=='HR'}">
																<input type="checkbox" 
																	value="${shrt.applyId}##${shrt.shortListId}" class="hrshortListOne">
															</c:when>
															<c:otherwise>
																<input type="checkbox"
																name="shortlisted"	value="${shrt.applyId}##${shrt.shortListId}" 
																class="checkbox hrshortListOne">
															</c:otherwise>
														</c:choose>
													</td>
			
												</tr>
											</c:forEach>
											</c:otherwise>
											 </c:choose>
										</tbody>
									</table>
			
									</div>
									<div class="space"></div>
									<div class="col-md-12 text-right">
						<input type="button" id="removeReleaseId1"
											value="Remove Shortlist"	class="btn btn-primary" />							
											
										<div class="modal fade" id="remove-shortlistOne-release" role="dialog">
											<div class="modal-dialog">
			
												<div class="modal-content">
													<div class="modal-header">
														<button type="button" class="close" data-dismiss="modal">&times;</button>
														<h4 class="modal-title text-center">Confirmation</h4>
													</div>
													<div class="modal-body">
			
														<div class="row">
															<div class="col-md-12 text-center">
																<label>Are you sure want to remove shortlist?</label>
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
										<input type="button" value="PDF Bind" class="btn btn-primary"
											id="verifypdfbindOne" /> <input type="button"
											value="Download CV or Cover Letter(zip)" id ="downloadcOne" data-toggle="modal"
											data-target="#cover-letterOne" class="btn btn-primary" />
										
										<div class="modal fade" id="cover-letterOne" role="dialog">
											<div class="modal-dialog">
												<div class="modal-content">
													<div class="modal-header">
														<button type="button" class="close" data-dismiss="modal">&times;</button>
														<h4 class="modal-title text-left">Cover Letter Download</h4>
													</div>
													<div class="modal-body">
			
														<div class="row">
															<div class="col-md-12 text-left">
																<input type="checkbox" name="cvOnel" id="cvOnel">
																Do you want to download Cover letters along with PDFs? 
															</div>
														</div>
			
													</div>
													<div class="modal-footer text-center">
														<button type="button" class="btn btn-primary"
															data-dismiss="modal" onclick="downloadCVsOne();">OK</button>
														<button type="button" class="btn btn-primary"
															data-dismiss="modal">NO</button>
													</div>
												</div>
			
											</div>
										</div>
											
										<input type="button" id="saveReleaseId1"
											value="Send Shortlist Details"	class="btn btn-primary" />							
											
										<div class="modal fade" id="save-shortlistOne-release" role="dialog">
											<div class="modal-dialog">
			
												<div class="modal-content">
													<div class="modal-header">
														<button type="button" class="close" data-dismiss="modal">&times;</button>
														<h4 class="modal-title text-center">Confirmation</h4>
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
															onclick="saveHRShortlistedWithoutSessionOne();">Yes</button>
														<button type="button" class="btn btn-primary"
															data-dismiss="modal">No</button>
													</div>
												</div>
											</div>
										</div>
									</div>
								</form>
			
								<div class="space"></div>
							</div>
						</c:when> 
						
					</c:choose>
				
					
				<c:choose>
						<c:when test="${closeStatus.noOfSelectionRounds=='2'}"> 
							<div class="tab-pane text-style" id="tab5">
						</c:when> 
						<c:otherwise>
							<div class="tab-pane active text-style" id="tab5">
						</c:otherwise>
					</c:choose>			
							
				   <div style="float: left; padding-right: 12px">
				   
						<input type="file"
						       id="excelfile1" 
						       class="btn btn-primary"/>
					</div>
			
				   <div style="float: left">
				   
				   <input type="button" 
				   		  class ="btn btn-primary" 
				   		  id="viewfile" 
				   		  value="Upload Excel File" 
				   		  onclick="Populatecheckbox('main-fourum1','excelfile1')"/>
					</div>
					
					<div class="col-md-12">
						<h3>${closeStatus.firmName}-${closeStatus.roleName} -
							${closeStatus.year}</h3>
					</div>
					<div class="clearfix"></div>
					<form name="downloadCV" id="downloadCVformId">
						<input type="hidden" value="" name="shortListIdsSelected"
						id="shortListIdsSelected" />
						<input type="hidden" name="cmpRoleId" value="${cmpRoleId}">
						<div class="table-responsive">
											
						<table id="main-fourum1"
							class="table table-bordered ">
							<thead>
								<tr>
									<th>Roll Number</th>
									<th>Email ID</th>
									<th>Name</th>
									<c:if test="${closeStatus.prefrences==1}">
									<th>Preference</th>
									</c:if>
									<th>Status</th>
									<th>Shortlist <input type="checkbox" id="verfiylistbtn11"></th>
								</tr>
							</thead>
							<tbody>
							<c:choose><c:when test="${fn:length(shortlist)==0}">No Data Available</c:when>
								<c:otherwise>
								<c:forEach items="${shortlist}" var="shrt">
									
									<input type="hidden" value="${shrt.shortListId}"
										name="shortListId" />
									<c:choose>
									<c:when test="${shrt.shortListBy=='HR'}">
									<tr style=' background-color: #d1620a;color:white;'>
									</c:when>
									<c:otherwise><tr></c:otherwise>
									</c:choose>
									
										<td>${shrt.rollNumber}</td>
										<td>${shrt.emailID}	</td>
										<td>${shrt.cvName}</td>
										<c:if test="${closeStatus.prefrences==1}">
										<td>${shrt.preference}</td></c:if>
										<td>${shrt.status}</td>
										<td align="center">
											<c:choose>
												<c:when test="${shrt.status=='Withdrawal' or shrt.shortListBy=='HR'}">
													<input type="checkbox" 
														value="${shrt.applyId}##${shrt.shortListId}" class="hrshortList">
												</c:when>
												<c:otherwise>
													<input type="checkbox"
													name="shortlisted"	value="${shrt.applyId}##${shrt.shortListId}" 
													class="checkbox hrshortList">
												</c:otherwise>
											</c:choose>
										</td>
									</tr>
								</c:forEach>
								</c:otherwise>
								</c:choose>
							</tbody>
						</table>

						</div>
						<div class="space"></div>
						<div class="col-md-12 text-right">
						<input type="button" id="removeReleaseId"
								value="Remove Shortlist"								
								class="btn btn-primary" />								
								
							<div class="modal fade" id="remove-shortlist-release" role="dialog">
								<div class="modal-dialog">

									<div class="modal-content">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal">&times;</button>
											<h4 class="modal-title text-left">Confirmation</h4>
										</div>
										<div class="modal-body">

											<div class="row">
												<div class="col-md-12 text-center">
													<label>Are you sure want to remove shortlist?</label>
												</div>
											</div>											
                                        </div>
										<div class="modal-footer text-center">
											<button type="button" class="btn btn-primary"
												onclick="shortlistRemovePI();">Yes</button>
											<button type="button" class="btn btn-primary"
												data-dismiss="modal">No</button>
										</div>
									</div>
								</div>
							</div>
							<input type="button" value="PDF Bind" class="btn btn-primary"
								id="verifypdfbind" /> <input type="button"
								value="Download CV or Cover Letter(zip)" id ="downloadc" data-toggle="modal"
								data-target="#cover-letter" class="btn btn-primary" />
							
							<div class="modal fade" id="cover-letter" role="dialog">
								<div class="modal-dialog">

									<div class="modal-content">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal">&times;</button>
											<h4 class="modal-title text-left">Cover Letter Download</h4>
										</div>
										<div class="modal-body">

											<div class="row">
												<div class="col-md-12 text-left">
													<input type="checkbox" name="cvl" id="cvl">
													Do you want to download Cover letters along with PDFs? 
												</div>
											</div>

										</div>
										<div class="modal-footer text-center">
											<button type="button" class="btn btn-primary"
												data-dismiss="modal" onclick="downloadCVs();">OK</button>
											<button type="button" class="btn btn-primary"
												data-dismiss="modal">NO</button>
										</div>
									</div>

								</div>
							</div>
							
							<!-- <input type="button" id="saveReleaseId"
								value="Send Shortlist Details" class="btn btn-primary"  data-toggle="modal"
								data-target="#shortlistSendDetailds"/> 		 -->						
							<input type="button" id="saveReleaseId"
								value="Send Shortlist Details"								
								class="btn btn-primary" />								
								
							<div class="modal fade" id="save-shortlist-release" role="dialog">
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
												onclick="saveHRShortlistedWithoutSession();">Yes</button>
											<button type="button" class="btn btn-primary"
												data-dismiss="modal">No</button>
										</div>
									</div>
								</div>
							</div>
						</div>
					</form>

					<div class="space"></div>
				</div>

				<!-- tab5 end  -->
				<%-- <c:if test="${closeStatus.hrHotlist==true}"> --%>
				<div class="tab-pane text-style" id="tab7">
					
					<div style="float: left; padding-right: 12px">
				   
						<input type="file"
						       id="excelfile3" 
						       class="btn btn-primary"/>
					</div>
			
				   <div style="float: left">
				   
				   <input type="button" 
				   		  class ="btn btn-primary" 
				   		  id="viewfile" 
				   		  value="Upload Excel File" 
				   		  onclick="Populatecheckbox('main-fourum3','excelfile3')"/>
					</div>
					
					<div class="col-md-12">
						<h3>${closeStatus.firmName}-${closeStatus.roleName} -
							${closeStatus.year}</h3>
					</div>

					<div class="clearfix"></div>
					<form name="downloadHRCV" id="downloadHRCVformId">
						<input type="hidden" name="cmpRoleId" value="${cmpRoleId}">
						<input type="hidden" value="" name="hotListIdsSelected"
											id="hotListIdsSelected" />
											
						<div class="table-responsive">
						<table id="main-fourum3"
							class="table table-bordered">
							<thead>
								<tr>
									<th>Roll Number</th>
									<th>Email ID</th>
									<th>Name</th>
									<c:if test="${closeStatus.prefrences==1}">
									<th>Preference</th></c:if>
									<th>Status</th>
									
									<c:choose>	
									    <c:when test="${closeStatus.hrHotlist==true}">
											<th>Hot list <input type="checkbox" id="hostlistbtn"></th>
										</c:when>
										<c:otherwise>
											<th>Select <input type="checkbox" id="hostlistbtn"></th>
										</c:otherwise>
									</c:choose>
									
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${hotlistCompanyHR}" var="shrt">

									<input type="hidden" value="${shrt.hotListId}" name="hotListId" />
								<c:choose>
									<c:when test="${shrt.hotListBy=='HR'}">
									<tr style=' background-color: #d1620a;color:white;'>
								</c:when>
									<c:otherwise><tr></c:otherwise>
								</c:choose>
										<td>${shrt.rollNumber}</td>
										<td>${shrt.emailID}</td>
										<td>${shrt.cvName}</td>
										<c:if test="${closeStatus.prefrences==1}">
										<td>${shrt.preference}</td>
										</c:if>
										<td>${shrt.status}</td>
										<td align="center">
											 <c:choose>
												<c:when test="${shrt.status=='Withdrawal'  or shrt.hotListBy=='HR'}">
													<input type="checkbox" 
														value="${shrt.applyId}##${shrt.hotListId}" class="checkbox hrhotList">
												</c:when>
												<c:otherwise> 
													<input type="checkbox"
													name="hotlisted" value="${shrt.applyId}##${shrt.hotListId}" 
													class="checkbox hrhotList">
												 </c:otherwise>
											</c:choose> 
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						</div>
						
						<div class="space"></div>
						<div class="col-md-12 text-right">
						<c:if test="${closeStatus.hrHotlist==true}">
						<input type="button" value="Remove Hotlist" id="removeReleaseId2"
								 class="btn btn-primary"	 />
								 </c:if>

								
							<div class="modal fade" id="remove-hotlist-release" role="dialog">
								<div class="modal-dialog">

									<div class="modal-content">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal">&times;</button>
											<h4 class="modal-title text-center">Confirmation</h4>
										</div>
										<div class="modal-body">

											<div class="row">
												<div class="col-md-12 text-center">
													<label>Are you sure want to remove final shortlist?</label>
												</div>
											</div>											
										</div>
										<div class="modal-footer text-center">
											<button type="button" class="btn btn-primary"
												onclick="removeHL();">Yes</button>
											<button type="button" class="btn btn-primary"
												data-dismiss="modal">No</button>
										</div>
									</div>
								</div>
							</div>
						
						
							<input type="button" value="PDF Bind" class="btn btn-primary"
								id="verifyhotpdf" /> <input type="button"
								value="Download CV or Cover Letter(zip)"  id ="downloadhot" data-toggle="modal"
								data-target="#cover-letter2" class="btn btn-primary" />
							<div class="modal fade" id="cover-letter2" role="dialog">
								<div class="modal-dialog">

									<div class="modal-content">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal">&times;</button>
											<h4 class="modal-title text-left">Cover Letter Download</h4>
										</div>
										<div class="modal-body">
											<div class="row">
												<div class="col-md-12 text-left">
													<input type="checkbox" name="cvsl" id="cvsl">
													Do you want to download Cover letters along with PDFs? 
												</div>
											</div>

										</div>
										<div class="modal-footer text-center">
											<button type="button" class="btn btn-primary"
												data-dismiss="modal" onclick="downloadCVs1();">OK</button>
											<button type="button" class="btn btn-primary"
												data-dismiss="modal">NO</button>
										</div>
									</div>

								</div>
							</div>
							
							 <c:if test="${closeStatus.hrHotlist==true}">
							<input type="button" value="Save Hotlist Release" id="saveReleaseId2"
								 class="btn btn-primary"	 />
								 </c:if>

								
							<div class="modal fade" id="save-hotlist-release" role="dialog">
								<div class="modal-dialog">

									<div class="modal-content">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal">&times;</button>
											<h4 class="modal-title text-center">Confirmation</h4>
										</div>
										<div class="modal-body">

											<div class="row">
												<div class="col-md-12 text-center">
													<label>Are you sure want to hotlist?</label>
												</div>
											</div>											
										</div>
										<div class="modal-footer text-center">
											<button type="button" class="btn btn-primary"
												onclick="saveHRHotListed();">Yes</button>
											<button type="button" class="btn btn-primary"
												data-dismiss="modal">No</button>
										</div>
									</div>
								</div>
							</div>
						</div>

					</form>

					<div class="space"></div>

				</div>
				<!-- tab7 end -->

				<%-- </c:if> --%>
			</div>
		</div></div>

	

	<jsp:include page="commonJsp/Footer.jsp" />

	<script type="text/javascript" src="js/dataTables.buttons.min.js"></script>
	<script type="text/javascript" src="js/buttons.flash.min.js"></script>
	<script type="text/javascript" src="js/jszip.min.js"></script>
	<script type="text/javascript" src="js/pdfmake.min.js"></script>
	<script type="text/javascript" src="js/vfs_fonts.js"></script>
	<script type="text/javascript" src="js/buttons.html5.min.js"></script>
	<script type="text/javascript" src="js/buttons.print.min.js"></script>


	<script>
		
		$(document).ready(function() {

			$('#main-fourum1').DataTable({
				dom : 'Bfrtip',
				lengthMenu: [
				              [ -1, 10, 25, 50, 100  ],
				              [ 'Show all', '10 rows', '25 rows', '50 rows', '100 rows' ]
				          ],
				buttons : [ 'pageLength','excel', 'print' ]
			});
			
			$('#main-fourum2').DataTable({
				dom : 'Bfrtip',
				lengthMenu: [
				              [ -1, 10, 25, 50, 100  ],
				              [ 'Show all', '10 rows', '25 rows', '50 rows', '100 rows' ]
				          ],
				buttons : [ 'pageLength','excel', 'print' ]
			});

			$('#main-fourum3').DataTable({
				dom : 'Bfrtip',
				lengthMenu: [
				              [ -1, 10, 25, 50, 100  ],
				              [ 'Show all', '10 rows', '25 rows', '50 rows', '100 rows' ]
				          ],
				buttons : [ 'pageLength','excel', 'print' ]
			});

			/* $('#main-fourum4').DataTable({
				dom : 'Bfrtip',
				buttons : [ 'excel', 'print' ]
			}); */

		});
		
		$(function () {
		    $("#verfiylistbtn11").click(function () {
		        $('.hrshortList').prop('checked', this.checked);
		    });

		    $(".hrshortList").click(function () {
		        if ($(".hrshortList").length == $(".case:checked").length) {
		            $("#verfiylistbtn11").prop("checked", "checked");
		        } else {
		            $("#verfiylistbtn11").removeProp("checked");
		        }
		    });
		  
		}); 
		
		$(function () {
		    $("#verfiylistbtn112").click(function () {
		        $('.hrshortListOne').prop('checked', this.checked);
		    });

		    $(".hrshortListOne").click(function () {
		        if ($(".hrshortListOne").length == $(".case:checked").length) {
		            $("#verfiylistbtn112").prop("checked", "checked");
		        } else {
		            $("#verfiylistbtn112").removeProp("checked");
		        }
		    });
		  
		});
		
		$(function () {
		    $("#hostlistbtn").click(function () {
		        $('.hrhotList').prop('checked', this.checked);
		    });

		    $(".hrhotList").click(function () {
		        if ($(".hrhotList").length == $(".case:checked").length) {
		            $("#hostlistbtn").prop("checked", "checked");
		        } else {
		            $("#hostlistbtn").removeProp("checked");
		        }
		    });
		  
		});
		
		
		function getInfoValuesWithoutSession(role) {
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
			var action = "getInfoValuesWithoutSession?cmpRoleId="+role;
			xhttp.open("POST", action, true);
			xhttp.send();
		}
	</script>

	<script>

	   $(function() {
			$('#downloadc').click(function() {
				if ($(".hrshortList").length > 0){
					if ($(".hrshortList")) {
						return true;
				 	}
			}else{
				alert("No data available.");
				return false;
			} 
			});
		});
		
	   $(function() {
			$('#downloadcOne').click(function() {
				if ($(".hrshortListOne").length > 0){
					if ($(".hrshortListOne")) {
						return true;
				 	}
			}else{
				alert("No data available.");
				return false;
			} 
			});
		});
	   $(function() {
			$('#downloadhot').click(function() {
				if ($(".hrhotList").length > 0){
					if ($(".hrhotList").is(":checked")) {
						return true;
				 	}else{
						alert("Please select at least one check box.");
						return false;
					}
				}else{
					alert("No data available.");
					return false;
				}
			  
			});
		});
	   
	var valArrDownload = [];
	var valArr = [];
	function downloadCVsOne(){
   		var coverLetter = 0;
    	
    	if ($('#cvOnel').is(":checked")){
    		coverLetter = 1;
    	}
	  	valArr = [];
	   	if ($(".hrshortListOne").length > 0){
			$('.hrshortListOne:checked').each(function(i) {
				valArr[i] = $(this).val();
			});
			if(valArr.length>0){
				downloadCV(valArr,coverLetter);
			}
	   	}else{
			alert("No data available.");
			return false;
		}
   } 
	
   	function downloadCVs(){
   		var coverLetter = 0;
    	
    	if ($('#cvl').is(":checked")){
    		coverLetter = 1;
    	}
	  	valArr = [];
	   	if ($(".hrshortList").length > 0){
			$('.hrshortList:checked').each(function(i) {
				valArr[i] = $(this).val();
			});
			if(valArr.length>0){
				downloadCV(valArr,coverLetter);
			}
	   	}else{
			alert("No data available.");
			return false;
		}
   } 
   
   function downloadCV(valArrDownload,coverLetter) {
		$("#downloadCvId").val('');
		$("#downloadCvId").val(valArrDownload.toString());
		$("#coverLetter").val(coverLetter);
		document.getElementById("downloadCV").action = "downloadHRCv";
		document.getElementById("downloadCV").method = "post";
		document.getElementById("downloadCV").submit();
	}
   
   $(function() {
		$('#verifypdfbindOne').click(function() {
			valArrDownload = [];
			if ($(".hrshortListOne").is(":checked")){
				var i=0;
				$('.hrshortListOne:checked').each(function(i) {
					valArrDownload[i] = $(this).val();
				});
				pdfBind(valArrDownload);
			}else{
				alert("No data available.");
				return false;
			}
		});
	});
   
  	$(function() {
		$('#verifypdfbind').click(function() {
			valArrDownload = [];
			if ($(".hrshortList").is(":checked")){
				 var i = 0;
				$('.hrshortList:checked').each(function(i)
				{
					valArrDownload[i] = $(this).val();
					i++;
				});
				pdfBind(valArrDownload);
			}else{
				alert("No data available.");
				return false;
			}
			  
		});
	});
  	
  
  	
  	
  	
   function pdfBind(valArrDownload) {
	   	$("#downloadCvId").val('');
		$("#downloadCvId").val(valArrDownload.toString());
		document.getElementById("downloadCV").action = "pdfmergeWOSession";
	  	document.getElementById("downloadCV").method = "post";
	  	document.getElementById("downloadCV").submit(); 
   }
   
   $(function() {
		$('#saveReleaseId1').click(function() {
	    valArrDownload = [];
	    if ($(".hrshortListOne").length > 0){
			if ($(".hrshortListOne").is(":checked")) {
				$('.hrshortListOne').each(function(i) {
					valArrDownload[i] = $(this).val();
				});
				
		 	}else{
				alert("Please select at least one check box.");
				return false;
			} 
	    }
	    
	    if (valArrDownload!=null && valArrDownload.length>0) {
	    	$("#save-shortlistOne-release").modal('show'); 
	    }else{
			alert("No data available.");
			return false;
		}
		});
	});
   
   $(function() {
		$('#saveReleaseId').click(function() {
	    valArrDownload = [];
	    if ($(".hrshortList").length > 0){
			if ($(".hrshortList").is(":checked")) {
				$('.hrshortList:checked').each(function(i) {
					valArrDownload[i] = $(this).val();
				});
				
		 	}else{
				alert("Please select at least one check box.");
				return false;
			} 
	    }
	    if (valArrDownload!=null && valArrDownload.length>0) {
	    	$("#save-shortlist-release").modal('show'); 
	    }else{
			alert("No data available.");
			return false;
		}
		});
	});
   $(function() {
		$('#saveReleaseId2').click(function() {
	    valArrDownload = [];
	    if ($(".hrhotList").length > 0){
			if ($(".hrhotList").is(":checked")) {
				$('.hrhotList:checked').each(function(i) {
					valArrDownload[i] = $(this).val();
				});
				
		 	}else{
				alert("Please select at least one check box.");
				return false;
			} 
	    }
	    if (valArrDownload!=null && valArrDownload.length>0) {
	    	$("#save-hotlist-release").modal('show'); 
	    }else{
			alert("No data available.");
			return false;
		}
		});
	});

   
   function saveHRShortlistedWithoutSession(){
	    valArrDownload = [];
	    if ($(".hrshortList").length > 0){
			if ($(".hrshortList").is(":checked")) {
				$('.hrshortList:checked').each(function(i) {
					valArrDownload[i] = $(this).val();
				});
				//pdfBind(valArrDownload);
		 	}else{
				alert("Please select at least one check box.");
				return false;
			} 
	    }
	    if (valArrDownload!=null && valArrDownload.length>0) {
	    	 $("#shortListIdsSelected").val('');
	    	// alert("val :: "+valArrDownload.toString());
	    	 $("#shortListIdsSelected").val(valArrDownload.toString());
			 document.getElementById("downloadCVformId").action = "saveHRShortlistedWithoutSession";
			 document.getElementById("downloadCVformId").method = "post";
			 document.getElementById("downloadCVformId").submit(); 
	    }else{
			alert("No data available.");
			return false;
		}
   }
   
   function saveHRShortlistedWithoutSessionOne(){
	    valArrDownload = [];
	    if ($(".hrshortListOne").length > 0){
			if ($(".hrshortListOne").is(":checked")) {
				$('.hrshortListOne:checked').each(function(i) {
					valArrDownload[i] = $(this).val();
				});
				//pdfBind(valArrDownload);
		 	}else{
				alert("Please select at least one check box.");
				return false;
			} 
	    }
	    if (valArrDownload!=null && valArrDownload.length>0) {
	    	 $("#shortListOneIds").val('');
	    	// alert("val :: "+valArrDownload.toString());
	    	 $("#shortListOneIds").val(valArrDownload.toString());
			 document.getElementById("shortListOneform").action = "saveHRShortlistedOneWithoutSession";
			 document.getElementById("shortListOneform").method = "post";
			 document.getElementById("shortListOneform").submit(); 
	    }else{
			alert("No data available.");
			return false;
		}
  }
   	function getGreetings(cmpRoleId) {	   
	   var xhttp = new XMLHttpRequest();
		xhttp.onreadystatechange = function() {
			if (this.readyState == 4 && this.status == 200) {					
				var i;
				var obj = JSON.parse(this.responseText);					
				var greetingsData = '';
				for (i = 0; i < obj.length; i++) {						
					greetingsData += "Congratulation you have been Short-list by "+
							 obj[i].CompanyName+ " for "+obj[i].roleName+" in "+ obj[i].ProcessName+" " + obj[i].year;
												 
				}					
				$("#greetingsData").html(greetingsData);					
			}
		};
		var action = "getGreetingsWithoutSession?cmpRoleId=" + cmpRoleId;
		xhttp.open("POST", action, true);
		xhttp.send();
	}
  
   	function sendReleaseToRM(cmpRoleId) {	   
	//alert("cmpRoleId-"+cmpRoleId);
	  document.getElementById("downloadCVformId").action = "getGreetingsWithoutSession?cmpRoleId=" + cmpRoleId;
	  document.getElementById("downloadCVformId").method = "post";
	  document.getElementById("downloadCVformId").submit(); 
		
	}
   	
    $(function() {
		$('#verifyhotpdf').click(function() {
			valArrDownload = [];
			if ($(".hrhotList").length > 0){
				if ($(".hrhotList").is(":checked")) {
					$('.hrhotList:checked').each(function(i) {
						valArrDownload[i] = $(this).val();
					});
					pdfBind(valArrDownload);
			 	}else{
					alert("Please select at least one check box.");
				}
			}else{
				alert("No data available.");
				return false;
			}
			  
		});
	});
    
   function getGreetingsHot(cmpRoleId) {
	    var xhttp = new XMLHttpRequest();
		xhttp.onreadystatechange = function() {
			if (this.readyState == 4 && this.status == 200) {					
				var i;
				var obj = JSON.parse(this.responseText);					
				var greetingsData = '';
				for (i = 0; i < obj.length; i++) {						
					greetingsData += "Congratulation you have been Hot-list by "+
							 obj[i].CompanyName+ " for "+obj[i].roleName+" in "+ obj[i].ProcessName+" " + obj[i].year;
												 
				}					
				$("#greetingsDataHot").html(greetingsData);
				
			}
		};
		var action = "getGreetingsWithoutSession?cmpRoleId=" + cmpRoleId;
		xhttp.open("POST", action, true);
		xhttp.send();
	}

    function downloadCVs1(){
    	  valArr = [];
    	  var coverLetter = 0;
      	
      	if ($('#cvsl').is(":checked")){
      		coverLetter = 1;
      	}
   	    if ($(".hrhotList").length > 0){
   			$('.hrhotList:checked').each(function(i) {
   				valArr[i] = $(this).val();
   			});
   			if(valArr.length>0){
   				downloadCV(valArr, coverLetter);
   			}else{
   				alert("Please select at least one check box");
   				return false;
   			}
   	   	}else{
   			alert("No data available.");
   			return false;
   		}
   }
   
   function saveHRHotListed(){
	   
	   valArrDownload = [];
	    if ($(".hrhotList").length > 0){
			if ($(".hrhotList").is(":checked")) {
				$('.hrhotList:checked').each(function(i) {
					valArrDownload[i] = $(this).val();
				});
				//pdfBind(valArrDownload);
		 	}else{
				alert("Please select at least one check box.");
				return false;
			} 
	    }
	    if (valArrDownload!=null && valArrDownload.length>0) {
    	 	$("#hotListIdsSelected").val(''); 
	    	$("#hotListIdsSelected").val(valArrDownload.toString());
	    	document.getElementById("downloadHRCVformId").action = "saveHRHotListedWithoutSession";
	   	  	document.getElementById("downloadHRCVformId").method = "post";
	   	  	document.getElementById("downloadHRCVformId").submit(); 
	    }else{
			alert("No data available.");
			return false;
		}
   }
   

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

	function getHotlistedUserDetailsByRolNo(rollNo){
	   var xhttp = new XMLHttpRequest();
		xhttp.onreadystatechange = function() {
			if (this.readyState == 4 && this.status == 200) {
				this.responseText;
				var i;
				var obj = JSON.parse(this.responseText);
				document.getElementById("emailId1").innerHTML = obj[0].emailId;
				document.getElementById("fullName1").innerHTML = obj[0].fullname;
				document.getElementById("cvName1").innerHTML = obj[0].cvName;
				document.getElementById("mentorName1").innerHTML = obj[0].mentor;
				document.getElementById("gender1").innerHTML = obj[0].gender;
				document.getElementById("10thPer1").innerHTML = obj[0].percentage;
				document.getElementById("12thPer1").innerHTML = obj[0].percenatageTwelve;
				document.getElementById("grad1").innerHTML = obj[0].percentageGraduate;
				document.getElementById("postGrad1").innerHTML = obj[0].postgraduatePercentage;
			}
		};
		var action = "getUserDetailsByRolNo?rollNumber=" + rollNo;
		xhttp.open("POST", action, true);
		xhttp.send();
	}

	function getReleasedUserDetailsByRolNo(rollNo){
	   var xhttp = new XMLHttpRequest();
		xhttp.onreadystatechange = function() {
			if (this.readyState == 4 && this.status == 200) {
				this.responseText;
				var i;
				var obj = JSON.parse(this.responseText);
				//obj.inboxSubject = eval("(" + obj.inboxSubject + ")");

				document.getElementById("emailIdRelease").innerHTML = obj[0].emailId;
				document.getElementById("fullNameRelease").innerHTML = obj[0].fullname;
				document.getElementById("cvNameRelease").innerHTML = obj[0].cvName;
				document.getElementById("mentorNameRelease").innerHTML = obj[0].mentor;
				document.getElementById("genderRelease").innerHTML = obj[0].gender;
				document.getElementById("10thPerRelease").innerHTML = obj[0].percentage;
				document.getElementById("12thPerRelease").innerHTML = obj[0].percenatageTwelve;
				document.getElementById("gradRelease").innerHTML = obj[0].percentageGraduate;
				document.getElementById("postGradRelease").innerHTML = obj[0].postgraduatePercentage;
			}
		};
		var action = "getUserDetailsByRolNo?rollNumber=" + rollNo;
		xhttp.open("POST", action, true);
		xhttp.send();
	}


	function hotlistedProcessDone(){
	    document.getElementById("shortlistedReleaseFormId").action = "HRprocessDoneHotlisted";
		document.getElementById("shortlistedReleaseFormId").method = "post";
		document.getElementById("shortlistedReleaseFormId").submit();  
   }

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




function shortlistRemove(){
	  valArrDownload = [];
	    if ($(".hrshortListOne").length > 0){
			if ($(".hrshortListOne").is(":checked")) {
				$('.hrshortListOne:checked').each(function(i) {
					valArrDownload[i] = $(this).val();
				});
				//pdfBind(valArrDownload);
		 	}else{
				alert("Please select at least one check box.");
				return false;
			} 
	    }
	    if (valArrDownload!=null && valArrDownload.length>0) {
	    	 $("#shortListOneIds").val('');
	    	// alert("val :: "+valArrDownload.toString());
	    	 $("#shortListOneIds").val(valArrDownload.toString());
			 document.getElementById("shortListOneform").action = "shortlistHR1Remove";
			 document.getElementById("shortListOneform").method = "post";
			 document.getElementById("shortListOneform").submit(); 
	    }else{
			alert("No data available.");
			return false;
		}
 }
 
function shortlistRemovePI(){
	valArrDownload = [];
    if ($(".hrshortList").length > 0){
		if ($(".hrshortList").is(":checked")) {
			$('.hrshortList:checked').each(function(i) {
				valArrDownload[i] = $(this).val();
			});
			//pdfBind(valArrDownload);
	 	}else{
			alert("Please select at least one check box.");
			return false;
		} 
    }
    if (valArrDownload!=null && valArrDownload.length>0) {
    	 $("#shortListIdsSelected").val('');
    	// alert("val :: "+valArrDownload.toString());
    	 $("#shortListIdsSelected").val(valArrDownload.toString());
		 document.getElementById("downloadCVformId").action = "shortlistHR2Remove";
		 document.getElementById("downloadCVformId").method = "post";
		 document.getElementById("downloadCVformId").submit(); 
    }else{
		alert("No data available.");
		return false;
	}
}

function removeHL(){
	valArrDownload = [];
    if ($(".hrhotList").length > 0){
		if ($(".hrhotList").is(":checked")) {
			$('.hrhotList:checked').each(function(i) {
				valArrDownload[i] = $(this).val();
			});
			//pdfBind(valArrDownload);
	 	}else{
			alert("Please select at least one check box.");
			return false;
		} 
    }
    if (valArrDownload!=null && valArrDownload.length>0) {
	 	$("#hotListIdsSelected").val(''); 
    	$("#hotListIdsSelected").val(valArrDownload.toString());
    	document.getElementById("downloadHRCVformId").action = "shortlistRemoveHL";
   	  	document.getElementById("downloadHRCVformId").method = "post";
   	  	document.getElementById("downloadHRCVformId").submit(); 
    }else{
		alert("No data available.");
		return false;
	}
}


$(function() {
	$('#removeReleaseId1').click(function() {
    valArrDownload = [];
    if ($(".hrshortListOne").length > 0){
		if ($(".hrshortListOne").is(":checked")) {
			$('.hrshortListOne').each(function(i) {
				valArrDownload[i] = $(this).val();
			});
			
	 	}else{
			alert("Please select at least one check box.");
			return false;
		} 
    }
    
    if (valArrDownload!=null && valArrDownload.length>0) {
    	$("#remove-shortlistOne-release").modal('show'); 
    }else{
		alert("No data available.");
		return false;
	}
	});
});

$(function() {
	$('#removeReleaseId').click(function() {
    valArrDownload = [];
    if ($(".hrshortList").length > 0){
		if ($(".hrshortList").is(":checked")) {
			$('.hrshortList').each(function(i) {
				valArrDownload[i] = $(this).val();
			});
			
	 	}else{
			alert("Please select at least one check box.");
			return false;
		} 
    }
    
    if (valArrDownload!=null && valArrDownload.length>0) {
    	$("#remove-shortlist-release").modal('show'); 
    }else{
		alert("No data available.");
		return false;
	}
	});
});

$(function() {
	$('#removeReleaseId2').click(function() {
    valArrDownload = [];
    if ($(".hrhotList").length > 0){
		if ($(".hrhotList").is(":checked")) {
			$('.hrhotList').each(function(i) {
				valArrDownload[i] = $(this).val();
			});
			
	 	}else{
			alert("Please select at least one check box.");
			return false;
		} 
    }
    
    if (valArrDownload!=null && valArrDownload.length>0) {
    	$("#remove-hotlist-release").modal('show'); 
    }else{
		alert("No data available.");
		return false;
	}
	});
});
</script>
</body>
</html>