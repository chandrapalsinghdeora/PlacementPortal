<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Indian Institute of Management Ahmedabad (IIMA) | Home - IIMA</title>
    <link rel="icon" href="images/favicon.ico" type="image/x-icon"/>
</head>
<body>
	<jsp:include page="commonJsp/Header.jsp" />
	<div class="breadcumb-wrapper">
		<div class="clearfix">
			<div class="pull-left">
				<ul class="list-inline link-list">
					<li><a href="forumHomePage">Home</a></li>
					<li><a href="getAllCompany">Company</a></li>
					<li>Apply Roles</li>
				</ul>
			</div>

		</div>
	</div>

	<div class="container">
		<div class="row">

			<!-- panel preview -->
			<div class="clearfix"></div>

			<h2>${fm.companyName} Role Apply</h2>

			<form id="submitForm" action="insertCompanyForm" method="post"
				name="Company" enctype="multipart/form-data">
				<input name="applicationId" type="hidden"
					value="${fm.applicationId}">
				<div class="row">
					<div class="col-md-12">
					<div class="table-responsive">
						<table class="table table-bordered">
							<thead>
								<tr>
									<th>Company Name</th>
									<th>Role</th>
									<th>Cluster</th>
									<th>CoHort</th>
									<th>CV Relate</th>
								</tr>
							</thead>
							<tbody>
								<c:choose>
									<c:when test="${fm.multipleCVCheck}">
										<c:forEach items="${fm.listCompanyRoles}" var="rolelist">
											<tr>
												<td>${fm.companyName}</td>
												<td>${rolelist.roleCompany}</td>
												<td>${fm.clusterName}</td>
												<td class="col-md-2">${fm.cohortName}</td>
												<td><select class="form-control" name="cvReleted">
														<option>select</option>
														<c:forEach items="${cvReleatedlist}" var="cv">
															<option value="${cv.cvReleted}">${cv.cvReletedName}</option>
														</c:forEach>
												</select></td>
											</tr>
											<br>
										</c:forEach>

									</c:when>
									<c:otherwise>
										<tr>
											<td>${fm.companyName}</td>
											<td><c:forEach items="${fm.listCompanyRoles}"
													var="rolelist">
									${rolelist}
								</c:forEach></td>
											<td>${fm.clusterName}</td>
											<td class="col-md-2">${fm.cohortName}</td>
											<td><select class="form-control" name="cvReleted">
													<option>select</option>
													<c:forEach items="${cvReleatedlist}" var="cv">
														<option value="${cv.cvReleted}">${cv.cvReletedName}</option>
													</c:forEach>
											</select></td>
										</tr>

									</c:otherwise>

								</c:choose>
							</tbody>
						</table>
						</div>
					</div>
				</div>
				<hr>

				<div class="row">
					<c:if test="${fm.coverLetter}">
						<div class="col-md-6">
							<h2>Cover Letter</h2>
							<table class="table table-bordered">
								<thead>
									<tr>
										<th>Title</th>
										<th>Upload Cover Letter</th>
									</tr>
								</thead>
								<tbody>
									<c:choose>
										<c:when test="${fm.multipleCVCheck}">
											<c:forEach items="${fm.listCompanyRoles}" var="rolelist">
												<tr>
													<td><input type="text" class="form-control"
														name="title" id="title" /></td>
													<td><input type="file" name="uploadFile"
														value="Upload File" /></td>
												</tr>
											</c:forEach>

										</c:when>
										<c:otherwise>
											<tr>
												<td><input type="text" class="form-control"
													name="title" id="title" /></td>
												<td><input type="file" name="uploadFile"
													value="Upload File" /></td>
											</tr>

										</c:otherwise>
									</c:choose>
								</tbody>
							</table>
						</div>
					</c:if>


					<div class="col-md-6">
						<h2 class="col-md-12">Preference Survey Form</h2>
						<table class="table table-bordered">
							<thead>
								<tr>
									<th>Role</th>
									<th>Select</th>
									<th>Rank</th>

								</tr>
							</thead>
							<tbody>

								<c:forEach items="${fm.listCompanyRoles}" var="rolelist">
									<input name="roleCompanyId" type="hidden"
										value="${rolelist.roleCompanyId}">
									<tr>
										<td><span>${rolelist.roleCompany}</span></td>
										<td><input type="checkbox" name="checkBoxList"
											value="${rolelist.roleCompanyId}"></td>
										<td><select name="rank" class="form-control">
												<c:forEach items="${fm.listCompanyRoles}" varStatus="j">
													<option value="${j.count}">Rank${j.count}</option>
												</c:forEach>
										</select></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
				<div class="container-fluid btn-container">
					<input type="hidden" value="${fm.coverLetter}" name="coverLetter" />
					<input type="hidden" value="${fm.applyId}" name="applyId" /> <input
						type="hidden" value="${fm.companyId}" name="companyId" /> <input
						type="hidden" value="${fm.applicationId}" name="applicationId" />
					<input type="hidden" value="${fm.companyName}" name="companyName" />
					<input type="hidden" value="${fm.roleCompanyId}"
						name="roleCompanyId" /> <input type="hidden"
						value="${fm.clusterName}" name="clusterName" /> <input
						type="hidden" value="${fm.cohortName}" name="cohortName" /> <a
						href="getAllCompany" class="btn btn-primary">Back</a>

					<button type="submit" class="btn btn-primary">Apply</button>

				</div>


			</form>

		</div>
	</div>
	<jsp:include page="commonJsp/Footer.jsp" />

</body>
</html>