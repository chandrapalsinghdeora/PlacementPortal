<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Indian Institute of Management Ahmedabad (IIMA) | Home - IIMA</title>
    <link rel="icon" href="images/favicon.ico" type="image/x-icon"/>

<!-- Bootstrap -->
<link href="css/bootstrap.css" rel="stylesheet">
<!-- Custom -->
<link href="css/custom.css" rel="stylesheet">
<link
	href='http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800'
	rel='stylesheet' type='text/css'>
<link rel="stylesheet" href="css/font-awesome.css">

<!-- CSS STYLE-->
<link rel="stylesheet" type="text/css" href="css/style.css"
	media="screen" />

<!-- SLIDER REVOLUTION 4.x CSS SETTINGS -->
<link rel="stylesheet" type="text/css" href="rs-plugin/css/settings.css"
	media="screen" />
<script type="text/javascript">

function getUpdatefineStatus(fineId,vFlag)
{  
	
	var choosed = vFlag.value;
	var retVal = confirm("Do you want to change the status to Payment?");
	//alert("fineId : "+fineId+" :: "+vFlag.value);
	if( retVal == true ){
		$("#fineId").val(fineId);
       	$("#fineValue").val(vFlag.value);
       	$("#fineupdate").submit();
       	alert("Status of payment is successfully");
       	return true;
       }
	else{
		if(choosed==0){
			$("#paymentID"+fineId).val(1);
		}else if(choosed==1){
			$("#paymentID"+fineId).val(0);
		}
		return false;
	}
		

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
					<li><a href="#">Company</a></li>
					<!--  <li>Question</li> -->
				</ul>
			</div>

		</div>
	</div>
	<div class="container">
		<div class="row">
			<!-- panel preview -->
			<div class="space"></div>
			<div class="container-fluid btn-container">
			
				
			</div>
			<div class="clearfix"></div>
			<table id="main-fourum" class="table table-striped table-bordered">
				<thead>
					<tr>
						<th>Sr. No.</th>
						<th>Student Name</th>
						<th>E-mail ID</th>
						<th>Fine Reason</th>
						<th>Date &amp; Time</th>
						<th>Amount</th>
						<th>Status</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${fineDetails}" var="fm" varStatus="loop">
						<tr>
							<td>${fm.rowCount}.</td>
							<td>${fm.username}</td>
							<td>${fm.email}</td>
							<td>${fm.fineReason}</td>
							<td>${fm.createdDate}</td>
							<td>${fm.amount}</td>
							<td>
							<c:choose>
								<c:when test="${fm.fineStatus=='Paid'}">
									<select id="paymentID${fm.fineId}" onchange="getUpdatefineStatus(${fm.fineId},this)">	
										<option  value="0">Not Paid</option>
										<option  value="1"  selected="selected">Paid</option>
									</select>
								</c:when>
								<c:otherwise>
									<select id="paymentID${fm.fineId}" onchange="getUpdatefineStatus(${fm.fineId},this)">	
										<option  value="0" selected="selected">Not Paid</option>
										<option  value="1">Paid</option>
									</select>
								</c:otherwise>
							</c:choose>
							
							
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<form id="fineupdate" method="post" action="updateFineStatus">
				<input type="hidden" value="" name="fineValue" id="fineValue" /> 
				<input type="hidden" value="" name="fineId" id="fineId" /> 
				
			</form>
			
					</div>
				</div>
			
	<jsp:include page="commonJsp/Footer.jsp" />
</body>

</html>