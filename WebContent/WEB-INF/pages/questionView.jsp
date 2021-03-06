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
<script type="text/javascript">
	function questionReplyDisplay(questionId) {
		document.getElementById("displayQuestionReply"+questionId).action = "questionReplyDisplay";
		document.getElementById("displayQuestionReply"+questionId).method = "post";
		document.getElementById("displayQuestionReply"+questionId).submit();
	}
	
	function editQuestion(questionId){
		document.getElementById("edit"+questionId).action = "editQuestions";
		document.getElementById("edit"+questionId).method = "post";
		document.getElementById("edit"+questionId).submit();
	}
	
	function deleteQuestion(questionId){
		document.getElementById("delete"+questionId).action = "deleteQuestion";
		document.getElementById("delete"+questionId).method = "post";
		document.getElementById("delete"+questionId).submit();
	}
	
</script>
</head>
<body>

	<jsp:include page="commonJsp/Header.jsp" />	
	<div class="clearfix"></div>
	<div class="breadcumb-wrapper">
		<div class="clearfix">
		<c:if test="${sessionBean.roleID!=4}">
			<div class="pull-left">
				<ul class="list-inline link-list">
				<!-- 	<li><a href="forumHomePage">Forum</a></li> -->
				<c:choose>
					 <c:when test="${sessionBean.roleID==1}">
					</c:when>
					<c:otherwise>
						<li><a href="forumHomePage">Forum</a></li>
					</c:otherwise>
					</c:choose>
				<%-- 	<li><a href="dispayThreadData?forumId=${forumId}">Thread</a></li> --%>
					<li><a href="viewQuestions?threadId=${threadId}">Questions</a></li>
				</ul>
			</div>
</c:if>
		</div>
	</div>
	<div class="clearfix"></div>
	<div class="clearfix"></div>
	<div class="container">
		<div class="row" style="height:400px">

			<!-- panel preview -->
			<div class="container-fluid btn-container">
				<!--  <input type="button" class="btn btn-primary pull-right"  value="Delete"/> -->
				<!-- <input type="button" class="btn btn-primary pull-right" value="Create" />  -->
				<%-- <a class="btn btn-primary pull-right" href="login?threadId=${threadId}+&uid=${userName}">Create</a> --%>
				<form id="submitForm" method="POST" action="login">
					<input type="hidden" value=${threadId } name="threadId" /> <input
						type="hidden" value="${userName}" name="uid" />
						<input type="hidden" value="question" name="commingFrom"/>
						<input type="hidden" name="forumId" value="${forumId}" />
						 <input	type="submit" class="btn btn-primary pull-right" value="Ask a Question" />
				</form>

			</div>
			<div class="clearfix"></div>
			<table id="main-fourum" class="table table-striped table-bordered">
				<thead>
					<tr>

						<th>Avtar</th>
						<th>Company</th>
						<th>Process</th>
						<th>Year</th>
						<th>Question</th>
						<th>Answer</th>
						<th>No. Of Answers</th>
						<c:if test="${sessionBean.roleID==6}">
							<th>Action Edit</th>
							<th>Action Delete</th>
						</c:if>
					</tr>
				</thead>
				<tbody>
				
				
					<c:forEach items="${quesiton}" var="fm">
						<tr>

							<td><div class="col-md-12 text-center">
									<%-- 	<a href="callIconData(${fm.userId});"><img
										src="images/avatar.jpg" class=""></a> --%>
									<a href="#"><img src="images/avatar.jpg" class=""></a>
								</div>
								<div class="clearfix"></div>
								<td>${fm.companyName}</td>
				<td>${fm.processName}</td>
				<td>${fm.year}</td>
								<%-- <div class="col-md-12 text-center">${fm.userName}</div></td> --%>
							<form id="displayQuestionReply${fm.questionId}" method="post">
								<input type="hidden" name="question" value="${fm.questionId}" />
								<input type="hidden" name="threadId" value="${threadId}" />
								<input type="hidden" name="forumId" value="${forumId}" />
								<c:set var="compID" value='<%=request.getParameter("cmpId")%>' scope="application" />
								<input type="hidden" name="cmpId" value="${compID}">								
								<td>
								<a href="#" onclick="questionReplyDisplay(${fm.questionId});">${fm.question}</a>
								</td>
								<td>${fm.replyContent}<br/>
								<c:if test="${fm.questionCount>0}">
								<a href="#" onclick="questionReplyDisplay(${fm.questionId});" style="float: right;">More</a>
								</c:if>
								</td>
								
							</form>
							<td>${fm.questionCount}</td>
							<c:if test="${sessionBean.roleID==6}">
								<form id="edit${fm.questionId}" method="post">
									<input type="hidden" value="${fm.questionId}" name="questionId"
										id="questionId" /> <input type="hidden" value=${threadId
										} name="threadId" />
									<td><a href="#" onclick="editQuestion(${fm.questionId});">Edit</a></td>
								</form>
								<form id="delete${fm.questionId}" method="post">
									<input type="hidden" value="${fm.questionId}" name="questionId"
										id="questionId" /> <input type="hidden" value=${threadId
										} name="threadId" />
									<td><a href="#"
										onclick="deleteQuestion(${fm.questionId});">delete</a></td>
								</form>
							</c:if>
						</tr>
					</c:forEach>


				</tbody>
			</table>

			<!-- / panel preview -->

		</div>
	</div>
	<%--  <table border='1px'>
      <h1>Question page</h1>
      <tr><td>Question</td><td>No Of Answers</td><td>Edit Action</td><td>Delete Action</td></tr>
     
      <c:forEach items="${quesiton}" var="fm">
    <tr> <td><a href="#"> ${fm.question}</a></td><td></td> <td><a href="#">Edit</a></td> <td><a href="#">Delete</a></td></tr>
      </c:forEach>
   </table> --%>
	<jsp:include page="commonJsp/Footer.jsp" />
</body>
</html>