<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Indian Institute of Management Ahmedabad (IIMA) | Home - IIMA</title>
    <link rel="icon" href="images/favicon.ico" type="image/x-icon"/>

<style>
.mail-box .table-inbox .fa.fa-star.activstar {
	color: #f78a09;
}
</style>
<link rel="stylesheet" type="text/css" href="css/pignose.calendar.css" />

</head>
<body>
	<!-- Header started -->
	<jsp:include page="commonJsp/Header.jsp" />
	<!-- Header ended -->
	<div class="clearfix"></div>
	<div class="breadcumb-wrapper">
			<div class="clearfix">
				
			</div>
		</div>
	
	<div class="clearfix"></div>
	<div class="container-fluid">
		<div class="">

			<!-- panel preview -->
			<div class="clearfix"></div>
			<div class="container-fluid">
				<div class="mail-box">
					<aside class="sm-side">

						<ul class="inbox-nav inbox-divider">
							<li><a href="getInboxData"><i class="fa fa-inbox"></i>
									Inbox <c:if test="${count ne 0}">
										<span class="label label-danger pull-right">${count}</span>
									</c:if></a> 

							</li>
							<li><a href="#create-label" data-toggle="modal"
								title="Label"><i class="fa fa-envelope-o"></i> Create New
									Label</a></li>
							<li>

								<div aria-hidden="true" aria-labelledby="myModalLabel"
									role="dialog" tabindex="-1" id="create-label"
									class="modal fade" style="display: none;">
									<div class="modal-dialog modal-sm"">
										<div class="modal-content row">
											<div class="modal-header">
												<button aria-hidden="true" type="button" class="close"
													data-dismiss="modal">&times;</button>
												<h4 class="modal-title">New Label</h4>
											</div>
											<div class="modal-body">
												<form role="form" class="form-horizontal" action="addLabel"
													method="post">
													<div class="form-group">
														<label class="col-lg-12 control-label">Please
															enter a new label name</label>
														<div class="col-lg-12">
															<input type="text" placeholder="" class="form-control"
																name="labelName">
														</div>
													</div>
													<!--  <div class="form-group">
                                                  <label class="col-lg-12 control-label"><input type="checkbox"> Nest Label Under</label>
                                                  <div class="col-lg-12">
                                                      <input type="text" placeholder="" id="cc" class="form-control" name="labelType">
                                                  </div>
                                              </div> -->

													<div class="form-group">
														<div class="col-lg-10">
															<button class="btn btn-primary" type="submit">Create</button>
															<button class="btn btn-primary" type="button"
																data-dismiss="modal">Cancel</button>
														</div>
													</div>
												</form>
											</div>
										</div>
										<!-- /.modal-content -->
									</div>
									<!-- /.modal-dialog -->
								</div>

							</li>

							<li><a href="getCalander" title="Label"><i
									class="fa fa-calendar"></i> Calendar </a></li>
							<li class="active"><a href="getAllFine" title="Label"><i
									class="fa fa-file-text"></i> Fine </a></li>

						</ul>
						<%-- <ul class="nav nav-pills nav-stacked labels-info inbox-divider">
							<li class="open-label">
								<h4>
									Labels <i class="btn btn-primary fa fa-plus" aria-hidden="true"></i>
								</h4>
							</li>
							<li>
								<div class="collapse label-box">
									<ul>
										<c:forEach items="${label}" var="lab">
											<li><a href="#"
												onclick="getInboxDataByLabel('${lab.key}')">${lab.value.labelName }</a></li>
										</c:forEach>
									</ul>
								</div>
							</li>
						</ul> --%>

						<!-- <div id="wrapper">
							<div id="basic" class="article">
								<div class="calendar"></div>
							</div>
						</div> -->

						<ul class="nav nav-pills nav-stacked labels-info ">

						</ul>

					</aside>
					<aside class="lg-side">
						<div class="inbox-head">
							<h3>Fine</h3>
							<%-- <form action="#" class="pull-right position">
								<div class="input-append">
									<input type="text" class="sr-input" placeholder="Search Mail">
									<button class="btn sr-btn" type="button">
										<i class="fa fa-search"></i>
									</button>
								</div>
							</form> --%>
						</div>
						<div class="inbox-body">

							<div class="clearfix"></div>
							<div class="table-responsive">

							<table id="main-fourum"
								class="table table-striped table-bordered">
								<thead>
									<tr>
										<th>S No.</th>
										<th>Date &amp; Time</th>
										<th>Name</th>
										<th>Fine Status (Paid/Not Paid)</th>
										<th>Amount</th>

									</tr>
								</thead>
								<tbody>
									<c:forEach items="${fineDetails}" var="fm">
										<tr>
											<td>${fm.rowCount}</td>
											<td>${fm.createdDate}</td>
											<td>${fm.fineReason}</td>
											<td>${fm.fineStatus==null?'Not Paid':'Paid' }</td>
											<td>${fm.amount}</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>

						</div></div>
					</aside>
				</div>

			</div>
			<!-- / panel preview -->
		</div>
	</div>

	<jsp:include page="commonJsp/Footer.jsp" />

	<script type="text/javascript" src="js/pignose.calendar.js"></script>
	<script type="text/javascript">
	//<![CDATA[
	           
	     $(document).ready(function(){
	      $(".star").click(function(){
	        $(".fa-star").toggleClass("activstar");
	      });
	      $(".open-label").click(function(){
		        $(".labels-info .collapse").toggleClass("in");
		      });
	     }); 
	    
 
	$(function() {
		

		function onClickHandler(date, obj) {
		
			var $calendar = obj.calendar;
			var $box = $calendar.parent().siblings('.box').show();
			var text = 'You choose date ';

			if(date[0] !== null) {
				text += date[0].format('YYYY-MM-DD');
			}

			if(date[0] !== null && date[1] !== null) {
				text += ' ~ ';
			} else if(date[0] === null && date[1] == null) {
				text += 'nothing';
			}

			if(date[1] !== null) {
				text += date[1].format('YYYY-MM-DD');
			}

			$box.text(text);
		}

		// Default Calendar
		$('.calendar').pignoseCalendar({
			select: onClickHandler
		});

		// This use for DEMO page tab component.
		$('.menu .item').tab();
	});
	//]]>
	</script>
	<!-- <script type="text/javascript" src="js/bootstrap.js"></script> -->

	<!-- Latest compiled JavaScript  -->
	<script>
    
 function changeStatus(inboxId){
	 
	// alert("change status");
	 
	 var xhttp = new XMLHttpRequest();
	 xhttp.onreadystatechange = function() {
	 if (this.readyState == 4 && this.status == 200) {
			     
	 }
	 };
	 
	 var action="changeStatus?inboxId="+inboxId;	 
	 xhttp.open("POST", action, true);
	 xhttp.send(); 
	 
	 
	/* document.getElementById("unreadStauts"+inboxId).action = "changeStatus";
    document.getElementById("unreadStauts"+inboxId).method = "post";
    document.getElementById("unreadStauts"+inboxId).submit();  */
    
 }	   
	
 function getInboxBody(inboxId){
	 //alert("inboxId "+inboxId);
     var xhttp = new XMLHttpRequest();
	 xhttp.onreadystatechange = function() {
	 if (this.readyState == 4 && this.status == 200) {
		 var obj = JSON.parse(this.responseText);
		 $("#tableId").html(obj.inboxBody) ; 
	 	}
	 };
	 var action="getInboxBodyByInboxId?inboxId="+inboxId;	 
	 xhttp.open("GET", action, true);
	 xhttp.send(); 
 }
 
 function toggleCheckbox(element)
 {
     element.checked = !element.checked;
 }
 
 function changeLabel(labelId){	
	    var x = document.getElementsByName("inboxIds");	 
	    var i;
	    var txt=[];
	    for (i = 0; i < x.length; i++) {
	       /*  if (x[i].type == "checkbox") {
	            x[i].checked = true;
	        } */
	        if (x[i].checked) {	        	
	            if(txt==''){	            	
	            	 txt[txt.length]=x[i].value;	            	 
	            }else{	            	
	            	 txt[txt.length]=x[i].value;
	            }
	        }
	    }
	     //alert("idsssss-"+txt);
	     var xhttp = new XMLHttpRequest();
		 xhttp.onreadystatechange = function() {
		 if (this.readyState == 4 && this.status == 200) {
		    // document.getElementById("demo").innerHTML =
		     this.responseText;
		 }
		 };
		 var action="assignLevel?labelId="+labelId+'&inboxIds='+txt;
		 xhttp.open("POST", action, true);
		 xhttp.send(); 
	
 }
 
 function getInboxDataByLabel(labelId){	
     var xhttp = new XMLHttpRequest();
	 xhttp.onreadystatechange = function() {
	 if (this.readyState == 4 && this.status == 200) {	   
	     this.responseText;	     
	     var i;
	     var obj = JSON.parse(this.responseText);
	     //obj.inboxSubject = eval("(" + obj.inboxSubject + ")");
	     var inboxData='';
	     for(i=0; i < obj.length; i++){
	         // alert("obj age "+obj[i].inboxSubject+" "+obj.length);	          
	        //  inboxData+="<tbody><tr><td><input type='checkbox' class='mail-checkbox'></td><td class='inbox-small-cells star'><i class='fa fa-star'></i></td><td>"+obj[i].inboxSubject+"</td><td>"+obj[i].inboxText+"</td><td class='view-message inbox-small-cells'><i class='fa fa-paperclip'></i></td><td>"+obj[i].createdDate+"</td></tr></tbody>";
	    	 inboxData+="<tbody><tr><td><input type='checkbox' class='mail-checkbox'></td><td>"+obj[i].inboxSubject+"</td><td>"+obj[i].inboxText+"</td><td class='view-message inbox-small-cells'></td><td>"+obj[i].createdDate+"</td></tr></tbody>";
	  	   
	     }
	    
	     document.getElementById("tableId").innerHTML=inboxData; 
	     
	 }
	 };
	 var action="getInboxDataByLebel?labelId="+labelId;
	 xhttp.open("POST", action, true);
	 xhttp.send(); 		
  }
 
 function refreshPage() {
	    location.reload();
 }
 
 function deleteInboxData(){
	   // alert("Deleted");
	    var x = document.getElementsByName("inboxIds");	 
	    var i;
	    var txt=[];
	    for (i = 0; i < x.length; i++) {	      
	        if (x[i].checked) {	        	
	            if(txt==''){	            	
	            	 txt[txt.length]=x[i].value;	            	 
	            }else{	            	
	            	 txt[txt.length]=x[i].value;
	            }
	        }
	   }
	
	
	/*  var xhttp = new XMLHttpRequest();
	 xhttp.onreadystatechange = function() {
	 if (this.readyState == 4 && this.status == 200) {
		 //var obj = JSON.parse(this.responseText);
		// document.getElementById("tableId").innerHTML="<tbody><tr> <td height='475' align='center'>"+ obj.inboxBody + "</td></tr></tbody>"; 
	     
	 }
	 };
	 var action="deleteInboxData?inboxIds="+txt;	 
	 xhttp.open("POST", action, true);
	 xhttp.send();  */
	    
	    var inboxIds=document.getElementById("deleteId");
	    inboxIds.value=txt;
	    document.getElementById("deleteInboxDATA").action = "deleteInboxData";
	    document.getElementById("deleteInboxDATA").method = "post";
	    document.getElementById("deleteInboxDATA").submit();  
	
 }
 
</script>

	<script type="text/javascript" src="js/moment.latest.min.js"></script>

	<script type="text/javascript" src="js/pignose.calendar.js"></script>
	<script type="text/javascript">
	//<![CDATA[
	$(function() {	

		function onClickHandler(date, obj) {		
			var $calendar = obj.calendar;
			var $box = $calendar.parent().siblings('.box').show();
			var text = 'You choose date ';

			if(date[0] !== null) {
				text += date[0].format('YYYY-MM-DD');
			}

			if(date[0] !== null && date[1] !== null) {
				text += ' ~ ';
			} else if(date[0] === null && date[1] == null) {
				text += 'nothing';
			}

			if(date[1] !== null) {
				text += date[1].format('YYYY-MM-DD');
			}

			$box.text(text);
		}

		// Default Calendar
		$('.calendar').pignoseCalendar({
			select: onClickHandler
		});

		// This use for DEMO page tab component.
		$('.menu .item').tab();
	});
	//]]>
	</script>


</body>
</html>