<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
 <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<title>Indian Institute of Management Ahmedabad (IIMA) | Home - IIMA</title>
    <link rel="icon" href="images/favicon.ico" type="image/x-icon"/>
<jsp:include page="commonJsp/Header.jsp" />

<div class="clearfix"></div>
<div class="breadcumb-wrapper">
	<div class="clearfix">
		<div class="pull-left">
			<ul class="list-inline link-list">
				<li><a href="getInboxData">Home</a></li>
				<li>Company</li>
			</ul>
		</div>

	</div>
</div>
<div class="clearfix"></div>

<div class="container min-height">
<div id="hello" ></div>
	<div class="row">

		<!-- panel preview -->
		<div class="col-md-12 btn-container"></div>
		<div class="clearfix"></div>
		<div class="table-responsive">
		<table id="main-fourum-appl" class="table table-striped table-bordered">
			<thead>
				<tr>
					<th>S. No.</th>
					<th>Company</th>
					<th>Opening Date</th> 
					<th>Closing Date</th>
					<th>Cluster</th>
					<th>Cohort</th>
					<th>Process</th>
					 <th>CV Limit</th>
					<th>Application Status</th>
					<th>Apply Status</th>
					<th>Shortlist info</th>
					<!-- <th>Prefence</th> -->
					<th>Apply</th>
					<%-- <c:if test="${pgpFlag==2}">
					<th>Withdraw</th>
					</c:if> --%> 
					<th>Withdraw</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${companyDetails}" var="fm" varStatus="loop">
					<tr>
						<td>${loop.index+1}.</td>
						<td><a href="#" type="button" data-toggle="modal"
							data-target="#myModal${loop.index+1}">${fm.companyName}</a>

							<div class="modal fade" id="myModal${loop.index+1}" role="dialog" data-backdrop="static" data-keyboard="false">
								<div class="modal-dialog comp-info company-model-withdraw">
									<input type="hidden" value="${fm.companyId}" name="updateCmpId" />
									<!-- Modal content-->
									<div class="modal-content">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal">&times;</button>
											<h4 class="modal-title">Company Info</h4>
										</div>
										<div class="modal-body">

											<div class="row">
												<div class="col-md-4">Query :</div>
												<div class="col-md-8">
													<a href="viewComQuestions?cmpId=${fm.companyId}" target="_blank">Query Link</a>
													<%-- <form id="viewFourm${fm.companyId}" method="get">
											      	 <input type="hidden" value="${fm.companyId}" name="cmpId" id="viewFourm${fm.companyId}"/>
											       <a onclick="getQuery(${fm.companyId})">Query Link</a>
											        </form> --%>
												</div>
											</div>
											<hr>
											<c:if test="${fn:length(fm.listCompanyURL)>0}">
											<div class="row">
												<div class="col-md-4">URL :</div>
												
												<div class="col-md-8">
												<c:forEach items="${fm.listCompanyURL}" var="addfile" varStatus="j">
													<c:set var="urlParts" value="${fn:split(addfile, '##')}" />
													<a href="${urlParts[0]}" target="_blank">${urlParts[1]}</a><br>
												</c:forEach>
												</div>
											</div> 
											<hr>
											</c:if>
											<c:if test="${fm.additionalText!=''}">
											<div class="row">
												<div class="col-md-4">Additional Text :</div>
												<div class="col-md-8">
													${fm.additionalText}
												</div>
											</div>
											</c:if>
											<div class="row">
												<div class="col-md-4">Additional File :</div>
												
												<div class="col-md-8">
												<c:forEach items="${fm.additionalFilePathList}" var="addfile" varStatus="j">
													<form method="post" action="downloadJDFile" style="display: inline-block;">
											       		<input type="hidden" value="${addfile}" name="jdlink" id="jdlink"/>
											       		<button type="submit" title="Additional File" class="btn btn-sm btn-primary" >
											       		Additional File ${j.count}</button>
											       	</form>
												</c:forEach>
												</div>
											</div>  
											
											<hr>
											<c:forEach items="${fm.roleBaseLink}" var="list"
												varStatus="j">

												<%-- <div class="row">
													<div class="col-md-6">RoleName :</div>
													<div class="col-md-6">${list.roleName}</div>
												</div> --%>
												<div class="row">
													<div class="col-md-4">JD :</div>
													<div class="col-md-8">
														<c:choose>
															<c:when test="${list.jdLink eq null}">
																NA
															</c:when>
															<c:otherwise>
																<form method="post" action="downloadJDFile" >
														       		<input type="hidden" value="${list.jdLink}" name="jdlink" id="jdlink"/>
														       		<button type="submit" title="Download Job Description File" class="btn btn-primary" >JD File</button>
														       	</form>
																
															</c:otherwise>
														</c:choose>
														
													</div>
												</div>
												
												<div class="row">
													<div class="col-md-4">Internship Experience :</div>
													<div class="col-md-8">
														<c:choose>
															<c:when test="${list.intershipExpLink eq null or list.intershipExpLink==''}">
																Link not provided
															</c:when>
															<c:otherwise>
																<a href="${list.intershipExpLink}"
																	title="InternShip Experience Link">Internship Exp.
																	Link</a>
															</c:otherwise>
														</c:choose>

													</div>
												</div>
											
												<div class="row">
													<div class="col-md-4">Interview Experience :</div>
													<div class="col-md-6">
														<c:choose>
															<c:when test="${list.interviewExpLink eq null or list.interviewExpLink==''}">
																Link not provided
															</c:when>
															<c:otherwise>
																<a href="${list.interviewExpLink}"
																	title="InterView Experience Link">Interview Exp.
																	Link</a>
															</c:otherwise>
														</c:choose>

													</div>
												</div>

											</c:forEach>
											<div class="modal-footer">
												<button type="button" class="btn btn-primary"
													data-dismiss="modal">Close</button>
											</div>
										</div>
									</div>
								</div>
							</div></td>
						<%-- <td>${fm.roleCompany}</td> --%>
						<td style="font-weight: bold;"><%-- <fmt:formatDate pattern="dd-MM-yyyy hh:MM a"
								value="${fm.opningDatetime}" /> --%>
								${fm.opningDatetime}
								</td>
						<td style="font-weight: bold;"><%-- <fmt:formatDate pattern="dd-MM-yyyy hh:MM a"
								value="${fm.closingDatetime}" /> --%>
								${fm.closingDatetime}
								</td>
						<td>${fm.clusterName}</td>
						<td>${fm.cohortName}</td>
						<td>${fm.processName}</td>
						 <td>${fm.limitCVNo} 
						 <input type="hidden" id="cvlimit${loop.index+1}" value="${fm.limitCVNo}">
						 </td>
						<td>${fm.applicationStatus}</td>
						<td>${fm.applied}</td>
						<td>${fm.shortlist}</td>

						<td>
						 <c:set var="csd23" value="5"/>
						 <c:forEach items="${cohort}" var="list1">
						 <c:choose>
								<c:when
									test="${(list1.cohortName==fm.cohortName and list1.count==0 and fm.processName=='Finals')}">
								 <c:set var="csd23" value="1"/>
							
							</c:when>
							<%-- <c:otherwise>
							<c:set var="csd23" value="5"/>
							</c:otherwise> --%>
								       </c:choose>
								            </c:forEach>  
      
						
						<c:choose>
								<c:when
									test="${(fm.processName=='Laterals' and workExp<=18) or fm.applied=='Applied' or (status==0 and fm.processName=='Finals') or (total==0 and fm.processName=='Finals') or (fm.clusterName=='C1' and c1<=0 and fm.processName=='Finals') or (fm.clusterName=='C2' and c2<fm.limitCVNo and fm.processName=='Finals') or (fm.clusterName=='C3' and c3<fm.limitCVNo and fm.processName=='Finals') or csd23==1 }">
								NA 
							
							</c:when>
							
								<c:otherwise>
								       <%--  <c:forEach items="${cohort}" var="list1">
								         <c:if test="${fm.cohortName==list1.cohortName}">
								           NA
								             </c:if>
								            </c:forEach> --%>
      
										
										<a href="#" type="button" data-toggle="modal"
										data-target="#apply${loop.index+1}" disabled="disabled">Apply </a>

										<div class="modal fade" id="apply${loop.index+1}" role="dialog" data-backdrop="static" data-keyboard="false">
										<div class="modal-dialog company-model-box">

											<!-- Modal content-->
											<div class="modal-content">
												<div class="modal-header">
													<button type="button" class="close" data-dismiss="modal">&times;</button>
													<h4 class="modal-title">Apply Form</h4>
												</div>
												<form id="submitForm" action="insertCompanyForm" onsubmit="return uploadCoverLetter(${loop.index+1});"
													method="post" name="Company" enctype="multipart/form-data">
													<input name="applicationId" type="hidden"
														value="${fm.applicationId}">
														<input name="cluserName" type="hidden"
														value="${fm.clusterName}">
														<input name="limitCVNo" type="hidden"
														value="${fm.limitCVNo}">
														<input name="cohotName" type="hidden"
														value="${fm.cohortName}">
														<input name="processNm" type="hidden"
														value="${fm.processName}">
													<div class="modal-body">
														<c:forEach items="${fm.listCompanyRoles}" var="rolelist" varStatus="j">
															<input name="roleCompanyId" type="hidden" value="${rolelist.roleCompanyId}">
															<input name="roleCover" type="hidden" value="${rolelist.coverLetter}">
															<c:if test="${fm.prefenceSurvey!='Yes'}">	
																<input type="hidden" name="checkBoxList" class="precheckbox" value="${rolelist.roleCompanyId}">
																<input type="hidden" name="rank" value="${j.count}">
															</c:if>
																					
														</c:forEach>
														
														<c:choose>
															<c:when test="${fm.multipleCVCheck}">
																<c:forEach items="${fm.listCompanyRoles}" var="rolelist" varStatus="j">

																	<div class="row model-title">
																		<div class="col-md-3">
																			<p>Company Name</p>
																			<h5>${fm.companyName}</h5>
																		</div>

																		<div class="col-md-2">
																			<p>Cluster</p>
																			<h5>${fm.clusterName}</h5>
																		</div>
																		<div class="col-md-2">
																			<p>Role</p>
																			<h5>${rolelist.roleCompany}</h5>
																		</div>
																		<div class="col-md-2">
																			<p>Cohort</p>
																			<h5>${fm.cohortName}</h5>
																		</div>
																		
																		<input type="hidden" name="mapRoleId" value="${rolelist.mapRoleId}" id="mappedroleID${rolelist.roleCompanyId}">
																		<c:choose>
																			<c:when test="${rolelist.mapRoleId==0}">
																				<div class="col-md-2">
																					<p>CV Relate</p>
																					<div>
																						<select class="form-control" name="cvReleted" id="cvReletedID${rolelist.roleCompanyId}">
																							<option value="">select</option>
																							<c:forEach items="${cvReleatedlist}" var="cv">
																								<option value="${cv.cvReleted}">${cv.cvReletedName}</option>
																							</c:forEach>
																						</select>
																					</div>
																				</div>
																			</c:when>
																			<c:otherwise>
																				<div class="col-md-2">
																					<input type="hidden" name="cvReleted" value="rolemap" id="cvReleted${j.count}" >
																				</div>
																			</c:otherwise>
																		</c:choose>
																		
																	</div>
																	<br>
																</c:forEach>

															</c:when>
															<c:otherwise>
															<c:forEach items="${fm.listCompanyRoles}" var="rolelist" varStatus="j">
																<div class="row model-title">
																	<div class="col-md-3">
																		<p>Company Name</p>
																		<h5>${fm.companyName}</h5>
																	</div>

																	<div class="col-md-2">
																		<p>Cluster</p>
																		<h5>${fm.clusterName}</h5>
																	</div>
																	<div class="col-md-2">
																			<p>Role</p>
																			<h5>${rolelist.roleCompany}</h5>
																		</div>
																	<div class="col-md-2">
																		<p>Cohort</p>
																		<h5>${fm.cohortName}</h5>
																	</div>
																	
																	<input type="hidden" name="mapRoleId" value="${rolelist.mapRoleId}" id="mappedroleID${rolelist.roleCompanyId}">
																		
																	<c:if test="${rolelist.mapRoleId eq 0}">
																		<div class="col-md-2">
																			<p>CV Relate</p>
																			<div>
																				<select class="form-control" name="cvReleted" id="cvReletedID${rolelist.roleCompanyId}">
																					<option value="">select</option>
																					<c:forEach items="${cvReleatedlist}" var="cv">
																						<option value="${cv.cvReleted}">${cv.cvReletedName}</option>
																					</c:forEach>
																				</select>
																			</div>
																		</div>
																	</c:if>
																</div>
																</c:forEach>
															</c:otherwise>
														</c:choose>

														<%-- <c:if test="${fm.coverLetter}"> --%>
															<hr>
															<div class="row">
																<c:choose>
																	<c:when test="${fm.multipleCVCheck}">
																		<c:forEach items="${fm.listCompanyRoles}"
																			var="rolelist" varStatus="j">

																			<div class="col-md-6 cover-letter-box">
																				<h3>Cover Letter</h3>
																				<hr>
																				<div class="row">
																				<div class="col-md-12 mb-10">
																					<div class="col-sm-6">Role</div>
																					<div class="col-sm-6">
																						 <div class="col-sm-12 mb-10">${rolelist.roleCompany}</div> 
																					</div>
																				</div>
																					<!-- <div class="col-md-12 mb-10">
																						<div class="col-sm-6">Title</div>
																						<div class="col-sm-6">
																							<input type="text" name="title" id="title"  />
																						</div>
																					</div>
																					 -->
																					<div class="col-md-12">
																						<div class="col-sm-6">Upload Cover Letter</div>
																						<div class="col-sm-6">
																							<input type="file" name="uploadFile" id="coverid${rolelist.roleCompanyId}" class="upcvltt"
																							accept=".pdf" onchange="checkFileType('coverid${rolelist.roleCompanyId}');" value="Upload File" disabled='true'/>
																						</div>
																					</div>
																				</div>
																				<div class="col-md-6"></div>
																			</div>
																		</c:forEach>

																	</c:when>
																	<c:otherwise>
																	<c:forEach items="${fm.listCompanyRoles}" var="rolelist" varStatus="j">
																	<c:if test="${rolelist.coverLetter==true}">
																		<div class="col-md-6 cover-letter-box">
																			<h3>Cover Letter</h3>
																			<hr>
																			<div class="row">
																			<div class="col-md-12 mb-10">
																					<div class="col-sm-6">Role</div>
																					<div class="col-sm-6">
																						 <div class="col-sm-12 mb-10">${rolelist.roleCompany}</div> 
																					</div>
																				</div>
																			<!-- 																				<div class="col-md-12 mb-10">
																					<div class="col-sm-6">Title</div>
																					<div class="col-sm-6">
																						<input type="text" name="title" id="title"  />
																					</div>
																				</div>
																			 -->
																			
																				<div class="col-md-12">
																					<div class="col-sm-6">Upload Cover Letter</div>
																					<div class="col-sm-6">
																						
																						<input type="file" name="uploadFile" id="coverid${rolelist.roleCompanyId}" class="upcvltt"
																							accept=".pdf" onchange="checkFileType('coverid${rolelist.roleCompanyId}');" value="Upload File" disabled='true'/>
																					</div>
																				</div>
																			</div>
																			<div class="col-md-6"></div>
																		</div>
																		</c:if>
																		
																		<c:if test="${rolelist.coverLetter==false}">
																		<input type="hidden" name="title" id="title" value="NA" />
																		<input type="file" name="uploadFile" style="display: none"/>
																		
																		</c:if>
																	</c:forEach>
																	</c:otherwise>
																</c:choose>
															</div>
														<%-- </c:if> --%>
														
														<c:choose>
														<c:when test="${fm.prefenceSurvey=='Yes'}"> 
															<c:if test="${fn:length(fm.listCompanyRoles) gt 1}">
																	
																<div class="row model-title">
																	<h3 class="col-sm-12">Preference Survey Form </h3>
																	<div class="col-md-4">
																		<div class="col-sm-12 mb-10">
																			<h4>Role</h4>
																		</div>
																	</div>
																	<div class="col-md-4">
																		<div class="col-sm-12 mb-10">
																			<h4>Select</h4>
																		</div>
																	</div>
																	<div class="col-md-4">
																		<div class="col-sm-12 mb-10">
																			<h4>Rank</h4>
																		</div>
																	</div>
																</div>
																
																<c:forEach items="${fm.listCompanyRoles}" var="rolelist" varStatus="x">
		
																	<%-- <input name="roleCompanyId" type="hidden"
																		value="${rolelist.roleCompanyId}"> --%>
																	<div class="row">
																		<div class="col-md-4">
																			<div class="col-sm-12 mb-10">${rolelist.roleCompany}</div>
																		</div>
																		<div class="col-md-4">
																			<div class="col-sm-12 mb-10">
																				<input type="checkbox" name="checkBoxList" class="precheckbox"
																					value="${rolelist.roleCompanyId}" disabled="disabled">
																			</div>
																		</div>
																		<div class="col-md-4">
																			<div class="col-sm-12 mb-10">
																				<select name="rank" class="form-control" id="rankID${rolelist.roleCompanyId}" disabled="disabled">
																				<option value="0">Select</option>
																					<c:forEach items="${fm.listCompanyRoles}"
																						var="rolelist" varStatus="j">
																						<option value="${j.count}">Rank${j.count}</option>
																					</c:forEach>
																				</select>
																			</div>
																		</div>
																	</div>
																</c:forEach>
															</c:if>
															<c:if test="${fn:length(fm.listCompanyRoles) eq 1}">
															
																<c:forEach items="${fm.listCompanyRoles}" var="rolelist" varStatus="j">
																	<input type="hidden" name="checkBoxList" class="precheckbox" value="${rolelist.roleCompanyId}">
																	<input type="hidden" name="rank" value="${j.count}">
																</c:forEach>
															
															</c:if>
														</c:when>
														</c:choose>
														
														<div class="modal-footer">
															 <input type="hidden"
																value="${fm.applyId}" name="applyId" /> <input
																type="hidden" value="${fm.companyId}" name="companyId" />
															<input type="hidden" value="${fm.applicationId}"
																name="applicationId" /> <input type="hidden"
																value="${fm.companyName}" name="companyName" /> <%-- <input
																type="hidden" value="${fm.roleCompanyId}"
																name="roleCompanyId" /> --%> <input type="hidden"
																value="${fm.clusterName}" name="clusterName" /> <input
																type="hidden" value="${fm.cohortName}" name="cohortName" />

															<button type="submit" class="btn btn-primary" >Apply</button>
															<button type="button" class="btn btn-primary"
																data-dismiss="modal" onclick="openPage()">Close</button>
														</div>

													</div>
												</form>
											</div>
										</div>
									</div> 

								</c:otherwise>
							</c:choose></td>
						<c:if test="${pgpFlag==2}">
							<td><c:choose>
								<c:when test="${fm.applied=='Applied' and fm.applicationStatus=='Closed'}">
									<a href="#" type="button" data-toggle="modal"
										data-target="#withdraw${loop.index+1}">Withdraw</a>
								</c:when>
								<c:when test="${fm.applied=='Applied' and fm.applicationStatus=='Open'}">
									<a href="#" type="button" data-toggle="modal"
										data-target="#withdraw${loop.index+1}">Withdraw</a>
								</c:when>
								
								<c:otherwise>
								Withdraw
							  </c:otherwise>
							</c:choose>

							<div class="modal fade" id="withdraw${loop.index+1}"
								role="dialog">
								<div class="modal-dialog modal-sm company-model-withdraw">

									<!-- Modal content-->
									<div class="modal-content">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal">&times;</button>
											<h4 class="modal-title">Withdraw Permission</h4>
										</div>
										<form id="withdrawForm" action="withdarw" method="post"
											name="Company" enctype="multipart/form-data">
											<input name="applicationId" type="hidden"
												value="${fm.applicationId}">
											<div class="modal-body">
												Are You Sure You Want to Withdraw
												<div class="modal-footer">
													<input type="hidden" value="${fm.applyId}" name="applyId" />
													<button type="submit" class="btn btn-primary">Yes</button>
													<button type="button" class="btn btn-primary"
														data-dismiss="modal">No</button>
												</div>
											</div>
										</form>
									</div>
								</div>
							</div></td>
						</c:if>
						<c:if test="${pgpFlag==1}">
							<td><c:choose>
								
								<c:when test="${fm.applied=='Applied' and fm.applicationStatus=='Open'}">
									<a href="#" type="button" data-toggle="modal"
										data-target="#withdraw${loop.index+1}">Withdraw</a>
								</c:when>
								<c:otherwise>
								Withdraw
							  </c:otherwise>
								
							</c:choose>

							<div class="modal fade" id="withdraw${loop.index+1}"
								role="dialog">
								<div class="modal-dialog modal-sm company-model-withdraw">

									<!-- Modal content-->
									<div class="modal-content">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal">&times;</button>
											<h4 class="modal-title">Withdraw Permission</h4>
										</div>
										<form id="withdrawForm" action="withdarw" method="post"
											name="Company" enctype="multipart/form-data">
											<input name="applicationId" type="hidden"
												value="${fm.applicationId}">
											<div class="modal-body">
												Are You Sure You Want to Withdraw
												<div class="modal-footer">
													<input type="hidden" value="${fm.applyId}" name="applyId" />
													<button type="submit" class="btn btn-primary">Yes</button>
													<button type="button" class="btn btn-primary"
														data-dismiss="modal">No</button>
												</div>
											</div>
										</form>
									</div>
								</div>
							</div></td>
						</c:if>
					</tr>
				</c:forEach>
			</tbody>
		</table></div>
		
		
		<form method="post" id="applyRoleForm" >
       		<input type="hidden" name="cmpId" id="cmpId"/>
       	</form>
	</div>
	<!-- / panel preview -->
</div>
<c:set var="hy" value="${csd}"/>
<jsp:include page="commonJsp/Footer.jsp" />

	<script type="text/javascript" src="js/buttons.flash.min.js"></script>
	<script type="text/javascript" src="js/jszip.min.js"></script>
	<script type="text/javascript" src="js/pdfmake.min.js"></script>
	<script type="text/javascript" src="js/buttons.html5.min.js"></script>
	<script type="text/javascript" src="js/buttons.print.min.js"></script>
<script>       
 $(document).ready(function() {
    $('#main-fourum-appl').DataTable( {
               
        "bFilter": true,
        "bInfo": true,
		"bPaginate": true ,
		lengthMenu: [
		              [ -1, 10, 25, 50, 100  ],
		              [ 'Show all', '10 rows', '25 rows', '50 rows', '100 rows' ]
		          ],
		 "order": [[ 8, "desc" ]],
		 "responsive": true
		
    } );	
} );    
</script>
<script>
	function uploadCoverLetter(index) {
		var cvlimit = $("#cvlimit"+index).val();
		//alert("cv limit:"+cvlimit+" index : "+index)
		
		var totalSelectValue=0;
		if( $('select[name=cvReleted]').length>0) {
			$('select[name=cvReleted]').each(function(i) {
			//	alert("cvReleted value "+$(this).val())
				if($(this).val()!=null && $(this).val().trim()!='')
				{
					totalSelectValue++;
					id=$(this).attr('id').replace("cvReletedID","");
					totalSelectValue= totalSelectValue+ $("[id^='mappedroleID'][value='"+id+"']").length;
				}
			});
		
		}
		
	//	alert("totalSelectValue : "+totalSelectValue);
		//alert($(".upcvltt").length);
	 	if(totalSelectValue>cvlimit){
			alert("You can't do more than "+cvlimit+" CV Relate.");
			return false;
		}
	 	
	 	var totalCoverLetter=0;
		if ($(".upcvltt").length > 0){
			
			$('.upcvltt').each(function(i) {
				//alert("cover value "+$(this).val())
				if($(this).val()!=null && $(this).val().trim()!=''){
					totalCoverLetter++;
				}
			});
			
		}
	//	alert("totalCoverLetter : "+totalCoverLetter);
		 if(totalCoverLetter>cvlimit){
			alert("You can't upload more than "+cvlimit+" cover letter.");
			return false;
		}  
		
		//alert("pre val :: "+$('.precheckbox').length);
		
		var totalCheckValue=0;
		if ($(".precheckbox").length > 0){
			//alert("cover letter ");
			$('.precheckbox:checked').each(function(i) {
			//	alert("checkBoxList value "+$(this).val())
				if($(this).val()!=null && $(this).val().trim()!=''){
					totalCheckValue++;
				}
			});
			
		}
		
	//	alert("totalCheckValue :: "+totalCheckValue)
		if(totalCheckValue>cvlimit){
			alert("You can't give  more than "+cvlimit+" preference.");
			return false;
		}
		//alert("done");
	//	document.getElementById("submitForm").submit();
	
		//Check for all enabled ranks to be filled
		var totalranks=0;
		if( $("[id^='rankID']").length > 0 ) {
			$("[id^='rankID']").each(function(i) {
				if($(this).val()!= 0)
					totalranks++;
			});	
			
		}
		if(totalCheckValue>totalranks)
			{
				alert("Please enter preference for all Checked roles");
				return false;
			}
		
		return true;
		
		
	}
</script>
<script>
$(function(){
    
	  $("[id^='cvReletedID']").change(function() 
			  {
	        
		    //Get role ID from ID attrubute of CV Relete select box and trim it to the number
	        var roleid=$(this).attr('id');
	        roleid= roleid.replace("cvReletedID","");
	        
			//Disbale and untick Checkboxs when no cv is selected or enable when they are
			$(".precheckbox[value='"+roleid+"']").prop('checked', !($(this).val() == "")).change();
	        $(".precheckbox[value='"+roleid+"']").attr('required', !($(this).val() == ""));
	        $(".precheckbox[value='"+roleid+"']").attr('disabled', ($(this).val() == ""));
			
	      	//Disable and clear upload buttons when no CV is selected or enable when they are
	      	$("[id='coverid"+roleid+"']").attr('required', !($(this).val() == ""));
	      	$("[id='coverid"+roleid+"']").attr('disabled', ($(this).val() == ""));
	      	if(($(this).val() == ""))
	        	$("[id='coverid"+roleid+"']").val("");
	      	
	        //Get mapped roles ID which are mapped to this role and update their conditions accordingly
	        mappedroles=$("[name='mapRoleId'][value="+roleid+"]");
	        mainrole=$(this); //To be used inside the each loop
	      	mappedroles.each( function(i){
	      		mroleid= $(this).attr('id').replace("mappedroleID","");
	        	
	        	//Disbale and untick Checkboxs when no cv is selected or enable when they are
				$(".precheckbox[value='"+mroleid+"']").prop('checked', !(mainrole.val() == "")).change();
		        $(".precheckbox[value='"+mroleid+"']").attr('required', !(mainrole.val() == ""));
		        $(".precheckbox[value='"+mroleid+"']").attr('disabled', (mainrole.val() == ""));
		        
		      //Disable and clear upload buttons when no CV is selected or enable when they are
		      	$("[id='coverid"+mroleid+"']").attr('required', !(mainrole.val() == ""));
		      	$("[id='coverid"+mroleid+"']").attr('disabled', (mainrole.val() == ""));
		        if((mainrole.val() == ""))
		        	$("[id='coverid"+mroleid+"']").val("");
	        });
	        
	 			
			 });
	  
		$(".precheckbox").change(function() 
			  {
	          //Disable Dropdown with Ranks if checkbox isn't checked and set them to Select
	          $('#rankID'+$(this).val()).attr('disabled', !$(this).is(':checked'));
	          $('#rankID'+$(this).val()).val( ($(this).is(':checked')) ? 1 : 0 );
	      });
});
</script>
<script>
	function submitApplyForm(flag) {
		$(document).ready(function() {
			//document.companyApplyForm.action = "insertCompanyForm";
			$('#companyApplyForm' + flag).attr("action", "insertCompanyForm");
			$('#companyApplyForm' + flag).submit();
			// document.companyApplyForm.submit(); // Submit the page
		});
	}
</script>
<script>
	function submitPreferenceForm(flag) {
		$(document).ready(
		function() {
			var a = $("#title").val();
			$('#preferenceApplyForm' + flag).attr("action",
					"insertCompanyPrefence");
			$('#preferenceApplyForm' + flag).submit();
		});
		
	}
	function getQuery(compid){
		document.getElementById("viewFourm"+compid).action = "viewComQuestions";	
		document.getElementById("viewFourm"+compid).method = "get";
	    document.getElementById("viewFourm"+compid).submit();
	}
	
	function getApplyForm(cmpId){
		$("#cmpId").val(cmpId);
		document.getElementById("applyRoleForm").action = "getStudentCompanyApply";	
		document.getElementById("applyRoleForm").method = "post";
	    document.getElementById("applyRoleForm").submit();
	}
	 function checkApplyForm(){
	//	 alert("value :: "+$("select[name=cvReleted]").val());
		if($("select[name=cvReleted]").val()=='0'){
			alert("Please select CV Releate");
			return false;
		}else{
		 	document.getElementById("applyRoleForm").action = "getStudentCompanyApply";	
			document.getElementById("applyRoleForm").method = "post";
		    document.getElementById("applyRoleForm").submit();
		}
	 }
	 
	 function openPage(){
		 window.location.href='getAllOpenCompany';
	 }
	 
	 function checkFileType(id){
			var fileName = document.getElementById(id).value;
			var ext = fileName.split('.').pop().toLowerCase();
			if ($.inArray(ext, [ 'pdf' ]) == -1) {
				alert("Only PDF File are allowed to upload.");
				var fileval = document.getElementById(id);
				fileval.value = '';

			}
		}

	 document.addEventListener("contextmenu", function(e){
		    e.preventDefault();
		}, false);
	
	 jQuery(document).ready(function() {
			var x="${hy}";
			document.getElementById('hello').innerHTML=x; 
			 setTimeout(function() {
			     document.getElementById('hello').innerHTML='';  
			    }, 10000);
		});
	 
</script>