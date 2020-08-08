
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Indian Institute of Management Ahmedabad (IIMA) | Home - IIMA</title>
    <link rel="icon" href="images/favicon.ico" type="image/x-icon"/>
<link rel="icon" href="images/favicon.ico" type="image/x-icon"/>
<!-- Bootstrap -->
<link href="css/bootstrap.css" rel="stylesheet">

<!-- Custom -->
<link href="css/custom.css" rel="stylesheet">
<link rel="stylesheet" href="css/font-awesome.css">
<link rel="stylesheet" type="text/css" href="css/pignose.calendar.css" />
<!-- CSS STYLE-->
<link rel="stylesheet" type="text/css" href="css/style.css" media="screen" />
<link rel="stylesheet" type="text/css" href="css/dataTables.bootstrap.min.css" media="screen" />
<style>
.mail-box .table-inbox .fa.fa-star.activstar { color: #f78a09; }

.inbox-table-head{ display:none;}



</style>
</head>
<body>
<!-- Header started -->
<jsp:include page="commonJsp/Header.jsp" />
<!-- Header ended -->
 <div class="clearfix"></div>
 <div class="breadcumb-wrapper">
          <!--  <div class="clearfix">
                <div class="pull-left">
                    <ul class="list-inline link-list">
                        <li><a href="#">Home</a></li>
                         <li><a href="#">Forum</a></li>
                        <li>User</li>
                    </ul>
                </div>
             
            </div> -->
        </div>
    <div class="clearfix"></div>
<div class="clearfix"></div>
<div class="container-fluid">
  <div class=""> 
    
    <!-- panel preview -->
  
    <div class="clearfix"></div>
   
    
    <div class="container-fluid">
   

 <div class="mail-box">
                  <aside class="sm-side">
                       <!--  <div class="user-head text-center">
                         
                          <div class="btn-group hidden-phone">
                                 <a data-toggle="dropdown" href="#" class="btn mini blue" aria-expanded="false" title="Open Menu">
                                     Menu
                                    <i class="fa fa-bars" aria-hidden="true"></i>

                                 </a>
                                 <ul class="dropdown-menu">
                                     <li><a href="getInboxData"> Inbox</a></li>
                                      <li><a href="getCalander"> Calender</a></li>
                                     <li><a href="#"> Delete</a></li>
                                 </ul>
                             </div>  
                         
                      </div> 
                      <div class="inbox-body">
                         <!--  <a href="#myModal" data-toggle="modal"  title="Compose"    class="btn btn-compose">
                              Compose
                          </a> 
                          <!-- Modal 
                          <div aria-hidden="true" aria-labelledby="myModalLabel" role="dialog" tabindex="-1" id="myModal" class="modal fade" style="display: none;">
                              <div class="modal-dialog">
                                  <div class="modal-content">
                                      <div class="modal-header">
                                          <button type="button" class="close" data-dismiss="modal">&times;</button>
                                          <h4 class="modal-title">Compose</h4>
                                      </div>
                                      <div class="modal-body">
                                          <form role="form" class="form-horizontal">
                                              <div class="form-group">
                                                  <label class="col-lg-2 control-label">To</label>
                                                  <div class="col-lg-10">
                                                      <input type="text" placeholder="" id="inputEmail1" class="form-control">
                                                  </div>
                                              </div>
                                              <div class="form-group">
                                                  <label class="col-lg-2 control-label">Cc / Bcc</label>
                                                  <div class="col-lg-10">
                                                      <input type="text" placeholder="" id="cc" class="form-control">
                                                  </div>
                                              </div>
                                              <div class="form-group">
                                                  <label class="col-lg-2 control-label">Subject</label>
                                                  <div class="col-lg-10">
                                                      <input type="text" placeholder="" id="inputPassword1" class="form-control">
                                                  </div>
                                              </div>
                                              <div class="form-group">
                                                  <label class="col-lg-2 control-label">Message</label>
                                                  <div class="col-lg-10">
                                                      <textarea rows="3" cols="30" class="form-control" id="" name=""></textarea>
                                                  </div>
                                              </div>

                                              <div class="form-group">
                                                  <div class="col-lg-offset-2 col-lg-10">
                                                      <span class="btn green fileinput-button">
                                                        <i class="fa fa-plus fa fa-white"></i>
                                                        <span>Attachment</span>
                                                        <input type="file" name="files[]" multiple="">
                                                      </span>
                                                      <button class="btn btn-send" type="submit">Send</button>
                                                  </div>
                                              </div>
                                          </form>
                                      </div>
                                  </div><!-- /.modal-content 
                              </div><!-- /.modal-dialog 
                          </div>
                          
                          <!-- /.modal 
                      </div>-->
						<ul class="inbox-nav inbox-divider">
							<li class="active"><a href="getInboxData"><i
									class="fa fa-inbox"></i> Inbox <c:if test="${count ne 0}">
										<span class="label label-danger pull-right">${count}</span>
									</c:if></a>
							</li>
							
							<li><a href="#create-label" data-toggle="modal" title="Label" onclick="clearLabel();">
							   <i class="fa fa-envelope-o"></i> Create New Label</a>
							</li>
							<li><a href="#manage-label" data-toggle="modal" title="Label" onclick="clearDeleteLabel();">
							   <i class="fa-envelope"></i>Manage Label</a>
							</li>
							<li>

								<div aria-hidden="true" aria-labelledby="myModalLabel"
									role="dialog" tabindex="-1" id="create-label"
									class="modal fade" style="display: none;">
									<div class="modal-dialog modal-sm">
										<div class="modal-content row">
											<div class="modal-header">
												<button aria-hidden="true" type="button" class="close" data-dismiss="modal">&times;</button>
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
																name="labelName" id="labelId" maxlength="50"  pattern="[A-Z a-z]+" title="enter alphabets only"  required>
														</div>
													</div>
													
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
							
							<li>

								<div aria-hidden="true" aria-labelledby="myModalLabel"
									role="dialog" tabindex="-1" id="manage-label"
									class="modal fade" style="display: none;">
									<div class="modal-dialog modal-sm">
										<div class="modal-content row">
											<div class="modal-header">
												<button aria-hidden="true" type="button" class="close" data-dismiss="modal">&times;</button>
												<h4 class="modal-title">Manage Label</h4>
											</div>
											<div class="modal-body">
												<form role="form" class="form-horizontal" id="labelDeleteform">
													<div id="deleteLabelError" style="color:red;"></div>
													<div class="form-group">
														<!-- <label class="col-lg-8 control-label">Label</label> -->
														<div class="col-lg-12">
														<c:forEach items="${label}" var="lab" varStatus="i">
                                                         <input type="checkbox" name="labelIds" id="deleteLabelId${i.count}" value="${lab.key}" >&nbsp;&nbsp;${lab.value.labelName}<br/>
                                                         </c:forEach>														
														</div>
													</div>
													
													<div class="form-group">
														<div class="col-lg-10">
															<button class="btn btn-primary" type="button" onclick="deleteLabel();">Delete</button>
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

				<li><a href="getCalander" title="Label"><i class="fa fa-calendar"></i> Calendar </a></li>
				<li><a href="getAllFine" title="Label"><i class="fa fa-file-text"></i> Fine </a></li>

						</ul>
						<ul class="nav nav-pills nav-stacked labels-info inbox-divider">
					<li class="open-label">

					<h4>
						Labels <i class="fa fa-plus pull-right" aria-hidden="true"></i>
					</h4>

					</li>
			<li class="col-md-12">
                <div class="collapse label-box">   
                <ul>
               
                <c:forEach items="${label}" var="lab">
                            <li><a href="#" onclick="getInboxDataByLabel('${lab.key}')">${lab.value.labelName }</a></li>
                 </c:forEach>
                 
                </ul> 
                            
                </div>  
             </li> 
		 </ul>

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
                          <h3>Inbox</h3>
                         <%--  <form action="#" class="pull-right position">
                              <div class="input-append">
                                  <input type="text" class="sr-input" placeholder="Search Mail">
                                  <button class="btn sr-btn" type="button"><i class="fa fa-search"></i></button>
                              </div>
                          </form> --%>
                      </div>
                      <div class="inbox-body">
                         <div class="mail-option">
                             <div class="chk-all">
                             	<c:choose>
                              	<c:when test="${checked eq 1 or checked eq 3 or checked eq 4}">
                                     <input type="checkbox" class="mail-checkbox mail-group-checkbox" checked>
                                  </c:when>
                                 <c:otherwise>
                                    <input type="checkbox" class="mail-checkbox mail-group-checkbox">
                                  </c:otherwise>
                                 </c:choose>
                                 <div class="btn-group">
                                     <a data-toggle="dropdown" href="#" class="btn mini all" aria-expanded="false">
                                         <i class="fa fa-angle-down "></i>
                                     </a>
                                     <ul class="dropdown-menu">
                                      <li><a href="getInboxDataByStatusId?statusId=1"> All </a></li>
                                         <li><a href="getInboxData"> None</a></li>
                                         <li><a href="getInboxDataByStatusId?statusId=3"> Read</a></li>
                                         <li><a href="getInboxDataByStatusId?statusId=4"> Unread</a></li>
                                        <!--  <li><a href="getInboxDataByStatusId?statusId=5">Starred</a></li>
                                         <li><a href="getInboxDataByStatusId?statusId=6">Unstarred</a></li> -->
                                     </ul>
                                 </div>
                             </div>

                             <div class="btn-group">
                                 <a data-original-title="Refresh" data-placement="top" data-toggle="dropdown" href="#" class="btn mini tooltips" onclick="refreshPage();">
                                     <i class=" fa fa-refresh"></i>
                                 </a>
                             </div>
                             <div class="btn-group">
                             <form id="deleteInboxDATA">
                                 <a data-original-title="trash" data-placement="top" data-toggle="dropdown" href="#" class="btn mini tooltips" onclick="deleteInboxData();" >
                                    <i class="fa fa-trash-o"></i>
                                 </a>
                                 <input type="hidden" name="inboxIds" value="" id='deleteId'/>
                             </form>    
                             </div>
                             
                             <!-- <div class="btn-group hidden-phone">
                                 <a data-toggle="dropdown" href="#" class="btn mini blue" aria-expanded="false">
                                     More
                                     <i class="fa fa-angle-down "></i>
                                 </a>
                                 <ul class="dropdown-menu">
                                     <li><a href="#"><i class="fa fa-pencil"></i> Mark as Read</a></li>
                                     <li><a href="#"><i class="fa fa-ban"></i> Spam</a></li>
                                     <li class="divider"></li>
                                     <li><a href="#"><i class="fa fa-trash-o"></i> Delete</a></li>
                                 </ul>
                             </div> -->
                             
                             <div class="btn-group">
                                 <a data-toggle="dropdown" href="#" class="btn mini blue">
                                     Move to
                                     <i class="fa fa-angle-down "></i>
                                 </a>
                                 <ul class="dropdown-menu">

										<li>
										<form id="changeLabelFormId" name="changeLabelForm">
										<c:forEach items="${label}" var="lab">
												<a href="#" onclick="changeLabel('${lab.key}')">&nbsp;&nbsp;${lab.value.labelName}</a> <br/>
											</c:forEach>
										</form>
										</li>

									<!--  <li><a href="#"><i class="fa fa-pencil"></i> Mark as Read</a></li>
                                     <li><a href="#"><i class="fa fa-ban"></i> Spam</a></li>
                                     <li class="divider"></li>
                                     <li><a href="#"><i class="fa fa-trash-o"></i> Delete</a></li>
                                  -->
                                 </ul>
                            
                             </div> 
                             
                             <div class="btn-group">
                              <span id="error" style="font-size:16px;color: red;">${mailDeleted}</span>
                              <span id="assignSUCCESS" style="font-size:16px;color: green;">${assignSuccess}</span>
                               <span id="labelSUCCESS" style="font-size:16px;color: green;">${labelCreated}</span>
                               <span id="deleteSUCCESS" style="font-size:16px;color: red;">${labelDelete}</span>
                             </div>                          
                             
                            <!--  <ul class="unstyled inbox-pagination">
                                 <li><span>1-50 of 234</span></li>
                                 <li>
                                     <a class="np-btn" href="#"><i class="fa fa-angle-left  pagination-left"></i></a>
                                 </li>
                                 <li>
                                     <a class="np-btn" href="#"><i class="fa fa-angle-right pagination-right"></i></a>
                                 </li>
                             </ul> -->
                         </div>
                        <div class="table-responsive">
                       
                          <table class="table table-inbox table-hover"  id="tableId">
                          <thead class="inbox-table-head"><tr><th>rtrt</th>
                          <th>rtrt</th>
                          <th>rtrt</th>
                          <th>rtrt</th>
                          <th>rtrt</th></tr></thead>
                            <tbody>                      
                          <c:if test="${not empty inbox}">
                            <c:forEach items="${inbox}" var="inb" varStatus="count">                           
                             
                             
                            
                              
                              <c:if test="${inb.statusId eq 3}">
                                  <tr class="" >
                                   <td class="inbox-small-cells">
                                   <c:if test="${checked eq 0 }">
                                      <input type="checkbox" class="mail-checkbox" name="inboxIds" id="delete${count.count}" value="${inb.inboxId}">
                                   </c:if>
                                   <c:if test="${checked eq 1 }">
                                      <input type="checkbox" name="inboxIds" class="mail-checkbox" id="checked${count.count}" value="${inb.inboxId}" checked>
                                   </c:if>
                                  </td>
                                   <td class="view-message text-left" onclick="getInboxBody('${inb.inboxId}')">${inb.sender_email}</td>
                                   
                                 <!--  <td class="inbox-small-cells"><i class="fa fa-star"></i></td>  -->                               
                                  <td class="view-message  dont-show" onclick="getInboxBody('${inb.inboxId}')">
                                   <form id="unreadStauts${inb.inboxId}" method="post">
                                    <input type="hidden" id="unreadStauts${inb.inboxId}" name="inboxId" value="${inb.inboxId}"/>
                                    <a href="#" onclick="changeStatus(${inb.inboxId});">
                                     ${inb.inboxSubject}
                                    </a>
                                    </form>
                                   </td>                                  
                                 <%--  <td class="view-message " onclick="getInboxBody('${inb.inboxId}')">${inb.inboxText}</td> --%>
                                 
                                   <c:choose>
                                   	<c:when test="${inb.attachmentPath != ''}">
                                  	<td class="view-message  inbox-small-cells"><a href="downloadAttachment?inboxId=${inb.inboxId}" ><i class="fa fa-paperclip"></i></a></td>
                                  </c:when>
                                  <c:otherwise><td class="view-message"></td> </c:otherwise>
                                   </c:choose>
                                  <td class="view-message  text-right" onclick="getInboxBody('${inb.inboxId}')">${inb.datetime}</td>
                              </tr>
                              </c:if>
                             
                             <c:if test="${inb.statusId eq 4}">                             
                              <tr class="unread" onclick="changeStatus('${inb.inboxId}');">
                                 
                                  <td class="inbox-small-cells "> <!-- star -->
                                  <c:if test="${checked eq 0}" >
                                      <input type="checkbox" class="mail-checkbox" name="inboxIds" id="delete${count.count}" value="${inb.inboxId}">
                                   </c:if>
                                   <c:if test="${checked eq 1}" >
                                      <input type="checkbox" name="inboxIds" class="mail-checkbox" checked="checked" id="checked${count.count}" value="${inb.inboxId}">
                                   </c:if>
                                   
                                  </td>
                                 
                                  <td class="view-message text-left" onclick="getInboxBody('${inb.inboxId}')">${inb.sender_email}</td>
                                  <%-- <td class="inbox-small-cells star" onclick="getInboxBody('${inb.inboxId}');"><i class="fa fa-star"></i></td> --%>                                
                                  <td class="view-message  dont-show" onclick="getInboxBody('${inb.inboxId}');">
                                   <form id="unreadStauts${inb.inboxId}" method="post">
                                    <input type="hidden" id="unreadStauts${inb.inboxId}" name="inboxId" value="${inb.inboxId}"/>
                                    <a href="#" onclick="changeStatus(${inb.inboxId}); getInboxBody('${inb.inboxId}');">
                                     ${inb.inboxSubject}
                                    </a>
                                    </form>
                                   </td>                                  
                              		<%-- <td class="view-message " onclick="getInboxBody('${inb.inboxId}');">${inb.inboxText}</td> --%>

							<c:choose>
								<c:when test="${inb.attachmentPath != ''}">
									<td class="view-message  inbox-small-cells"
										onclick="getInboxBody('${inb.inboxId}');"><i
										class="fa fa-paperclip"></i></td>
								</c:when>
								<c:otherwise>
									<td class="view-message"></td>
								</c:otherwise>
							</c:choose>

								<td class="view-message  text-right" onclick="getInboxBody('${inb.inboxId}');">${inb.datetime}</td>
                              </tr>
                              </c:if>                              
                              
                                           
                              </c:forEach>
                             </c:if>
                          </tbody>
                          </table>
                    </div>  </div>
                  </aside>
              </div>

    
    </div>
    <!-- / panel preview --> 
    
  </div>
</div>

<jsp:include page="commonJsp/Footer.jsp" />



 <script>       
 $(document).ready(function() {
   $('#tableId').DataTable( {
        "bFilter": true,
        "bInfo": true,
		"bPaginate": true,
		"ordering": false
    } );  
} );    
</script>

<!-- <script type="text/javascript" src="js/pignose.calendar.js"></script> -->
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
 
 function getInboxDataByLabel(labelId){	
     var xhttp = new XMLHttpRequest();
	 xhttp.onreadystatechange = function() {
	 if (this.readyState == 4 && this.status == 200) {	   
	     this.responseText;	     
	     var i;
	     var obj = JSON.parse(this.responseText);	    
	     var inboxData='';
	     for(i=0; i < obj.length; i++){	        	          
	        //  inboxData+="<tbody><tr><td><input type='checkbox' class='mail-checkbox'></td><td class='inbox-small-cells star'><i class='fa fa-star'></i></td><td>"+obj[i].inboxSubject+"</td><td>"+obj[i].inboxText+"</td><td class='view-message inbox-small-cells'><i class='fa fa-paperclip'></i></td><td>"+obj[i].createdDate+"</td></tr></tbody>";
	    	// inboxData+="<tbody><tr><td><input type='checkbox' name='inboxIds' value="+obj[i].inboxId+" class='mail-checkbox'></td><td onclick='getInboxBody("+obj[i].inboxId+")'>"+obj[i].inboxSubject+"</td><td onclick='getInboxBody("+obj[i].inboxId+")'>"+obj[i].inboxText+"</td><td class='view-message inbox-small-cells'></td><td onclick='getInboxBody("+obj[i].inboxId+")'>"+obj[i].createdDate+"</td></tr></tbody>";
	    	 inboxData+="<tbody><tr><td><input type='checkbox' name='inboxIds' value="+obj[i].inboxId+" class='mail-checkbox'></td><td onclick='getInboxBody("+obj[i].inboxId+")'>"+obj[i].inboxSubject+"</td><td class='view-message inbox-small-cells'></td><td onclick='getInboxBody("+obj[i].inboxId+")'>"+obj[i].createdDate+"</td></tr></tbody>";
		    	
	     }	    
	     document.getElementById("tableId").innerHTML=inboxData;
	     document.getElementById("assignSUCCESS").innerHTML=''; 
	     document.getElementById("error").innerHTML='';
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
	    if(inboxIds.value==''){
	    	alert("Please select atleast one mail !!");
	    	return false;
	    }	    
	    document.getElementById("deleteInboxDATA").action = "deleteInboxData";
	    document.getElementById("deleteInboxDATA").method = "post";
	    document.getElementById("deleteInboxDATA").submit();  
	
 }
 
 function clearLabel(){	
	 var label=document.getElementById("labelId");	 
	 label.value='';
 }
 
 
</script> 
 
<script>
function changeLabel(labelId){	
    var x = document.getElementsByName("inboxIds");	    
     $(document).ready(function(){
   	 $('.mail-checkbox').change(function(){
   	 
   		 if(this.checked)
   			$('#error').hide();
   	 	   //$('#error').fadeOut('slow');
   		 /*  else
   	       $('#error').fadeIn('slow'); */
   	 });
   	 }); 
     
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
    
	 	
	 if(txt==''){
		 //alert("Please select atleast one mail!!");
		 document.getElementById("error").innerHTML="Please select atleast one mail!!";
		 return false;
	 }
	
	 document.getElementById("changeLabelFormId").action = "assignLevel?labelId="+labelId+'&inboxIds='+txt;
	 document.getElementById("changeLabelFormId").method = "post";
	 document.getElementById("changeLabelFormId").submit(); 
}

function deleteLabel(){	
   // var y = document.getElementById("deleteLabelId");  
    var x = document.getElementsByName("labelIds");	 
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
	if(txt!=''){    	 
       	 document.getElementById("labelDeleteform").action = "deleteLabel";
    	 document.getElementById("labelDeleteform").method = "post";
    	 document.getElementById("labelDeleteform").submit(); 
    }
    else{  
     document.getElementById("deleteLabelError").innerHTML="Please select atleast one label!!";
	 return false;	 	 
    }
    

}
function clearDeleteLabel(){	
	 var label=document.getElementsByName("labelIds");	 
	 label.value='';	
	 
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