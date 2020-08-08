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
<!-- 
<!-- Bootstrap -->
<link href="css/bootstrap.css" rel="stylesheet">


<link href="css/custom.css" rel="stylesheet">
<link rel="stylesheet" href="css/font-awesome.css">


<link rel="stylesheet" type="text/css" href="css/style.css" media="screen" />
<link rel="stylesheet" type="text/css" href="css/dataTables.bootstrap.min.css" media="screen" />
<style>

.modal-footer { margin-top: 5px; padding: 5px 20px 5px;}
.modal-header { padding: 5px 10px; }
</style>
</head>
<body>
<jsp:include page="commonJsp/Header.jsp" />

 <div class="clearfix"></div>
<div class="breadcumb-wrapper">
            <div class="clearfix">
                <div class="pull-left">
                    <ul class="list-inline link-list">
                        <li><a href="#">Home</a></li>
                         <li><a href="#">Firm Management</a></li>
                        <li><a href="#">Open Status Schedule</a></li>
                        
                        <li>Message</li>
                    </ul>
                    
                    
                </div>
             
            </div>
        </div>
    <div class="clearfix"></div>
<div class="clearfix"></div>
<div class="container">
<div class="row">
<div class="col-md-12"><h3> Firm Name - Role Name  </h3></div>


 </div>
  <div class="row"> 
    
    <!-- panel preview -->
    
    <div class="container-fluid btn-container">
  
    <a href="#" class="btn btn-primary pull-right" data-toggle="modal" data-target="#rm-status-schedule-msg" onclick="setMessageValues();">Add Message</a>
     </div>
    <div class="clearfix"></div>
    <table id="main-fourum" class="table table-striped table-bordered">
      <thead>
        <tr>
         
          <th>Sr. No.</th>
          <th>Subject</th>
          <th>Message</th>
          <th>Edit</th>
          <th>Delete</th>
        
        </tr>
      </thead>
      <tbody>
      <c:forEach var="msg" items="${messages}" varStatus="status">
        <tr>         
          <td>${status.count }</td>
          <td>${msg.subject}</td>
          <td>${msg.message}</td>
          <td  align='center'>
          <input type="button" class="btn btn-primary" data-toggle="modal" data-target="#rm-status-schedule-msg" value="Edit" onclick="getMessageById('${msg.messageId}');">
           
          </td>
          <td  align='center'><a href="#" class="btn btn-primary" style="color:white;" onclick="deleteMsg(${msg.messageId});">Delete</a></td>
        </tr>
     </c:forEach>  
       
      </tbody>
    </table>
     <form method="post" id="deleteMsgForm">
   		<input type="hidden" name="messageId" id="messageId" value=""/>
   	</form>
    <div class="modal fade" id="rm-status-schedule-msg" role="dialog">
      <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
      <form id="messageFormId">
      <input type="hidden" name="messageId" value="0" id="hiddenMsgId"/>      
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Message</h4>
        </div>
        <div class="modal-body">
      
        <div class="row">
        <label class="col-md-6">Subject</label>
        <div class="col-md-6"><input type="text" name="subject" value="" id="subjectId" class="form-control"/></div>
        </div>
        
        
       <div class="row">
      <label class="col-md-6">Message</label>
       <div class="col-md-6"><textarea name="message" class="form-control" rows="2" value="" id="messageId"></textarea></div>
    </div>
        
       
         
        <div class="modal-footer">
         <button type="button" class="btn btn-default" onclick="saveMessage();">Save</button>
          <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
        </div>
      </div>
     </form> 
    </div>
  </div>
    
    <!-- / panel preview --> 
    
  </div>
</div>

<div class="space"></div>
			 <div class="row">
<div class="col-md-12"><a href="getRMSchedulePage" class="btn btn-primary">Back</a></div>


 </div>

<jsp:include page="commonJsp/Footer.jsp" />

<!-- get jQuery from the google apis --> 

<script>
   function saveMessage(){
	    //alert("saveMessage");
	    document.getElementById("messageFormId").action = "saveMessage";
	    document.getElementById("messageFormId").method = "post";
	    document.getElementById("messageFormId").submit(); 
   }
   
   function getMessageById(msgId){
	  // alert(msgId);
	    
		 var xhttp = new XMLHttpRequest();
		 xhttp.onreadystatechange = function() {
		 if (this.readyState == 4 && this.status == 200) {
			 var i;
		     var obj = JSON.parse(this.responseText);	
		   //  alert(obj.subject+" :: "+obj.message+"::"+obj.messageId)
		     $("#subjectId").val(obj.subject) ; 	
		     $("textarea#messageId").val(obj.message); 
		     $("#hiddenMsgId").val(obj.messageId) ; 	
		   //  this.responseText;
		 }
		 };
		 var action="getMessagesByMessageId?msgId="+msgId;
		 xhttp.open("POST", action, true);
		 xhttp.send();  
	  
   }
   function setMessageValues(){
	    $("#subjectId").val("") ; 	
	    $("textarea#messageId").val("") ; 
	    return true;
   }
  
   function deleteMsg(id){
		//deleteCluster
		$("#messageId").val(id);
		document.getElementById("deleteMsgForm").action = "deleteMessge";
		$("#deleteMsgForm").submit();
	}
</script>

</body>
</html>