<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">

<title>Indian Institute of Management Ahmedabad (IIMA) | Home - IIMA</title>
<link rel="icon" href="images/favicon.ico" type="image/x-icon"/>
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>
	$(function() {
		$("#datepicker").datepicker();
	});

	function submitForm() {
		document.getElementById("saveThread").action = "saveThrad";
		document.getElementById("saveThread").submit();
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
					<li><a href="forumHomePage">Home</a></li>
					<li><a href="forumHomePage">Forum</a></li>
					<li>Thread</li>
				</ul>
			</div>

		</div>
	</div>
	<div class="row" style="height:400px">
		<div class="col-sm-offset-3 col-sm-5">

			<div class="panel panel-default add-forum">
					<c:if test="${formType eq 'ADD'}">
					<div class="panel-heading">Add Thread</div>
				      <div class="panel-body form-horizontal">
						<form action="saveThrad" method="post" id="saveThread" commandName="ThreadBlog">
					</c:if>
					<c:if test="${formType eq 'EDIT'}">
						<div class="panel-heading">Edit Thread</div>
				      <div class="panel-body form-horizontal">
						<form action="editThreadData" method="post" id="editThred" commandName="ThreadBlog">
					</c:if>
					<div class="form-group" ${threadName}>
						<label class="col-sm-4 control-label" style=" text-align:left ">Thread Name</label>
						<div class="col-sm-8">
							<input type="text" class="form-control" value="${threadName}"
								placeholder="Thread Name" name="threadName" required maxlength="100" pattern="[a-z A-Z 0-9]+" title="Enter alphanumeric only">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label" style=" text-align:left">Forum Name</label>
						<div class="col-sm-8">
							<select class="form-control" name="forumId" required>
								<option value="">select</option>
								<c:forEach items="${fn}" var="f">
									<option value="${f.forumId}"
										${f.forumId  == forumId ? 'selected' : ' '}>
										${f.forumName}</option>
								</c:forEach>
							</select>
						</div>
					</div>
						
					<div class="form-group">
						<label class="col-sm-4 control-label" style="text-align:left ">Who Can Post</label>
						<div class="col-sm-8">
							<select class="form-control" name="postPermissionId" required>
								<option value="">select</option>
								<c:forEach items="${post}" var="f">
									<option value="${f.postPermissionId }"
										${f.postPermissionId  == postPermission ? 'selected' : ' '}>
										${f.postPermissionName}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					
					<div class="form-group" ${editPermission}>
						<label class="col-sm-4 control-label" style=" text-align:left ">Who Can Edit</label>
						<div class="col-sm-8">
							<select class="form-control" name="editPermissionId" required>
								<option value="">select</option>
								<c:forEach items="${edit}" var="f">
									<option
										value="${f.editPermissionId}" ${f.editPermissionId  == editPermission ? 'selected' : ' '}>
										${f.editPermissionName}</option>
										
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label" style=" text-align:left ">Who Can Delete</label>
						<div class="col-sm-8">
							<select class="form-control" name="deletePermissionId" required>
								<option value="">select</option>
								<c:forEach items="${delete}" var="f">
									<option value="${f.deletePermissionId}"
										${f.deletePermissionId  == deletePermission ? 'selected' : ' '}>
										${f.deletePermissionName}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<%-- <input type="hidden" name="threadId" value="${threadId }"/>
					<input type="hidden" name="forumId" value=${forumId }/> --%>
					<div class="form-group">
						<div class="col-sm-12 text-right">
							<!-- <button type="button" class="btn btn-blue" id="save" onclick="submitForm();">
                                Create
                            </button> -->
							<c:if test="${formType eq 'ADD'}">
								<input type="submit" class="btn btn-blue" value="Create">
							</c:if>
							<c:if test="${formType eq 'EDIT'}">
							<input type="hidden" name="threadId" value="${threadId }"/>
					<input type="hidden" name="forumId" value=${forumId }/>
								<input type="submit" class="btn btn-blue" value="Update">
							</c:if>
							<!-- <button type="button" class="btn btn-blue"  id="update" onclick="OnButton2();">
                                Back
                            </button> -->
						</div>
					</div>
					<%-- <table>
            <tr>
                <td>Thread Name:</td>
                <td><input type="text" name="threadName" required=""  title="enter alphabets only"
                placeholder="Thread Name"/></td>
            </tr>
             <tr>
                <td>Form Name:</td>
                <td>  
                   <select name="forumId">
                    <option value="">--Select One--</option>
		              <c:forEach items="${fn}" var="f">
		              <option value="${f.forumId }"> ${f.forumName} </option>
		              </c:forEach>
            </select>
             </td>
            </tr>
            
             <tr>
                <td>Who Can Post:</td>
                <td>  
                   <select name="postPermissionId">
                    <option value="">--Select One--</option>
		              <c:forEach items="${post}" var="f">
		              <option value="${f.postPermissionId }"> ${f.postPermissionName} </option>
		              </c:forEach>
            </select>
             </td>
            </tr>
            
             <tr>
                <td>Who Can Edit:</td>
                <td>  
                   <select name="editPermissionId">
                    <option value="">--Select One--</option>
		              <c:forEach items="${edit}" var="f">
		              <option value="${f.editPermissionId }"> ${f.editPermissionName} </option>
		              </c:forEach>
            </select>
             </td>
            </tr>
            <tr>
                <td>Who Can Delete:</td>
                <td>  
                   <select name="deletePermissionId">
                    <option value="">--Select One--</option>
		              <c:forEach items="${delete}" var="f">
		              <option value="${f.deletePermissionId }"> ${f.deletePermissionName} </option>
		              </c:forEach>
            </select>
             </td>
            </tr>
			<tr>
                <td colspan="2" align="Left" style="padding-top:30px;">
                    <input type="submit" value="Create">
                </td>
                <td colspan="2" align="Right" style="padding-top:30px;">
                    <input type="submit" value="Back">
                </td>
            </tr>
        </table>  --%>
					</form>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="commonJsp/Footer.jsp" />
	</div>
</body>
</html>