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
</head>
<body>
	<jsp:include page="commonJsp/Header.jsp" />
	<div class="clearfix"></div>
	<div class="breadcumb-wrapper">
		<div class="clearfix">
			<div class="pull-left">
				<ul class="list-inline link-list">
					<li><a href="getInboxData">Home</a></li>
					<li>Internship Experience</li>
				</ul>
			</div>
		</div>
	</div>

	<div class="container">
		<div class="space"></div>
		<div class="row"></div>

		<form class="form-horizontal" id="savetheinternship" method="post">

			<div class="border">
				<div class="col-sm-2">Company</div>
				<div class="col-sm-3">
					<select class="form-control" name="companyId" id="companyId">
						<option value="0">Select</option>
						<c:forEach items="${cmpList}" var="inter">
							<option value="${inter.companyId}">${inter.companyName}</option>
						</c:forEach>
					</select>
					<span id="cmperror" style="color: red"></span>
				</div>
				<div class="col-sm-1"></div>
				<div class="col-sm-1">Year</div>
				<div class="col-sm-2">
					<select class="form-control" name="year" id="year">
						<option value="0">Select Year</option>
						<option value="2016">2016</option>
						<option value="2017">2017</option>
						<option value="2018">2018</option>
					</select>
					<span id="yearerror" style="color: red"></span>
				</div>
				
			</div>

			<div class="panel panel-primary">
				<div class="panel-heading">Internship Question & Answers</div>
				<div id="interviewbox">
					<div class="panel-body">
						<div class="form-group">
							<div class="col-sm-3">Question</div>
							<div class="col-sm-9">
								<textarea rows="2" class="form-control" name="question"
									id="question"></textarea>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-3">Answer</div>
							<div class="col-sm-9">
								<textarea rows="2" class="form-control" name="answer"
									id="answer"></textarea>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div id="internshipid"></div>
		</form>

		<div class="col-sm-offset-7 col-sm-5 text-center">
			<button class="btn btn-primary" id="add-panel">Add Question
				& Answer</button>
			<button class="btn btn-primary" id="remove-panel">Remove
				Question & Answer</button>
		</div>

		<div class="marg">
			<input type="button" value="Submit" onclick="saveit()"
				class="btn btn-primary" /> 
			<input type="button" id="cancel" 
			value="Cancel" class="btn btn-primary"  onclick="javascript:location.href='getpreparation'" />  
		</div>
		<div class="space"></div>
	</div>

	<jsp:include page="commonJsp/Footer.jsp" />
</body>

<script>
	
		function saveit(){
			$("#cmperror").html("");
			$("#yearerror").html("");
			if($("#companyId").val()==0 ){
				$("#cmperror").html("* Please select company name");
			}else if($("#year").val()==0){
				$("#yearerror").html("* Please select year");
			}
			else{
				document.getElementById("savetheinternship").action = "saveinternship";
				document.getElementById("savetheinternship").method = "post";
				document.getElementById("savetheinternship").submit();
			}
		}
	 
		 $(document).ready(function() {
		    var x=1;  
	    	$("#add-panel").click(function() {
				var wrapper=$('#internshipid');
		  		var data1=$('#interviewbox').html();
				if(x<11) {
					var domElement = $(' <div class="panel panel-primary"><div class="panel-heading">Internship Question & Answers  ' +x+ '</div>'+data1+'</div></div>');
					$(wrapper).append(domElement);
					x++;	
				}
	    	});
		
	    	
		 	$("#remove-panel").click(function() {
			 	var main = document.getElementById("internshipid");
			 	x--;	
			 	if (main.children.length > 0) {           
				 	main.lastChild.remove();  
				}
	    	});
		 	
		 	$("#cancel").click(function(){
				$("textarea" ).val( "" );
			  	$("#companyId").val('0');
			  	$("#year").val('0');
			  	var main = document.getElementById("internshipid");
				if(x>2){
			  		x=1;	
				 	while (main.children.length > 0) {           
					 	main.lastChild.remove();  
			 		}
				}
			});
		});
	 
    </script>

</html>