<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Indian Institute of Management Ahmedabad (IIMA) | Home - IIMA</title>
    <link rel="icon" href="images/favicon.ico" type="image/x-icon"/>
<link rel="stylesheet"
	href="assets/css/bootstrap-datetimepicker.min.css" />


</head>
<body>
	<jsp:include page="commonJsp/Header.jsp" />
	<div class="clearfix"></div>
	<div class="breadcumb-wrapper">
		<div class="clearfix">
			<div class="pull-left">
				<ul class="list-inline link-list">
					<li><a href="#">Home</a></li>
					<li>Announcement</li>
				</ul>
			</div>

		</div>
	</div>

	<div class="space"></div>
	<div class="clearfix"></div>

	<div class="container min-height">

		<div class="panel panel-primary">
			<div class="panel-heading">Announcement</div>
			<div class="panel-body">
				
				<form method="post" id="kmAnnouncementId" name="kmAnnouncement"
					class="form-horizontal">
					<div class="form-group">
						<label class="col-sm-2">Subject</label>
						<div class="col-sm-4">
							<input class="form-control" type="text" name="title" id="title"
								required placeholder="Please enter subject"
								maxlength="50">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2">URL</label>
						<div class="col-sm-4">
							<input class="form-control" type="text" name="url" id="url"
								required="required" placeholder="Please enter correct url"
								maxlength="50">
						</div>
					</div>
					<!-- <div class="form-group">
						
						 <label	class="col-sm-2">Description</label>
						<div class="col-sm-4">
							<textarea class="form-control" rows="5" name="description"
								id="description" required="required"
								placeholder="Write your description here." maxlength="300"></textarea>
						</div>

					</div> -->
					
					<div class="form-group col-md-12 text-center">
						<input id="sendButton" type="button" value="Save"
							class="btn btn-primary" data-toggle="modal"
							onclick="submitAnn()" />
						<!-- <input type="button" value="Cancel" class="btn btn-primary"
							onclick="location.href='getKMAnnouncementPage'" /> -->
					</div>
				</form>
			</div>
		</div>
		
		
		<div class="table-responsive">
    <table id="main-fourum" class="table table-striped table-bordered" >
      <thead>
        <tr>
         
           <th>Sr. No.</th>
          <th>Subject</th>
          <th>URL</th>
          <th>Date &amp; Time</th>
          <th></th>
          
        </tr>
      </thead>
      <tbody>
      <c:forEach items="${announce}" var="an" varStatus="a">
      	
        <tr>
         <td>${a.count}</td>
         <td>
          <form id="announmentDeleteForm${an.announmtId}" method="post">
      	 <input type="hidden" value="${an.announmtId}" name="announmtId" id="announmentId${an.announmtId}"/>
         </form>
          ${an.title}</td>
         <td>${an.url}</td>
          <td>${an.createdDate}</td>          
         <td><a href="#" onclick="deleteAnnoucementByKm(${an.announmtId})">Delete</a></td>               
        </tr>
                
       </c:forEach>
      </tbody>
    </table>
    </div>
		
		
		
	</div>

	
	<div class="space"></div>
	<jsp:include page="commonJsp/Footer.jsp" />

	<script src="assets/js/bootstrap-datepicker.min.js"></script>
	<script src="assets/js/bootstrap-timepicker.min.js"></script>
	<script src="assets/js/moment.min.js"></script>
	<script src="assets/js/bootstrap-datetimepicker.min.js"></script>
	<script src="assets/js/ace.min.js"></script>
	
</body>
<script>

	function submitAnn() {
		document.getElementById("kmAnnouncementId").action = "addAnnouncementByKM";
		document.getElementById("kmAnnouncementId").method = "post";
		document.forms["kmAnnouncementId"].submit();
	}
	
	function deleteAnnoucementByKm(announcementId){
		document.getElementById("announmentDeleteForm"+announcementId).action = "deleteKMAnnouncement";	
		document.getElementById("announmentDeleteForm"+announcementId).method = "post";
		document.getElementById("announmentDeleteForm"+announcementId).submit();
	}
	
	
</script>
</html>