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
				<li>Share Profile List</li>
			</ul>
		</div>

	</div>
</div>
<div class="clearfix"></div>

<div class="container min-height">

	<div class="row">

		<!-- panel preview -->
		<div class="col-md-12 btn-container"></div>
		<div class="clearfix"></div>
		<div class="table-responsive">
		<table id="main-fourum" class="table table-striped table-bordered">
			<thead>
				<tr>
					<th>S. No.</th>
					<th>Student Name</th>
					<!--  <th>Total Work Experience</th>  -->
					 <th>Work Experience Company</th> 
					 <!-- <th>Total SummnerIntership Experience</th>  -->
					 <th>Internship Company</th> 
					<th>Graduate Degree</th>
					<!-- <th>CV</th> -->
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${profilelist}" var="fm" varStatus="loop">
					<tr>
						<td>${loop.index+1}.</td>
						<td>${fm.studentName}</td>
						<td>${fm.wpCompany}</td>
						<td>${fm.internCompany}</td> 
						<%--  <td>${fm.totalWorkExp}</td> --%>
						<%--  <td>${fm.totalSummnerExp}</td>  --%>
						<td>${fm.graduateDegree}</td> 
						<%-- <td>
							<c:forEach items="${fm.cvNameList}" var="cvName" varStatus="status">
								<a href="downloadDocument?cvpkId=${fm.cvPkId[status.index]}&fileName=${cvName}" target='_blank'>${cvName}</a> |
							</c:forEach>
						</td> --%>
					</tr>
				</c:forEach>
			</tbody>
		</table></div>
	</div>
	<!-- / panel preview -->
</div>

<jsp:include page="commonJsp/Footer.jsp" />

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
</script>