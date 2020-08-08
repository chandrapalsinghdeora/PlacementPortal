<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Indian Institute of Management Ahmedabad (IIMA) | Home - IIMA</title>
    <link rel="icon" href="images/favicon.ico" type="image/x-icon"/>
<script type="text/javascript">
function viewQuestions(threadId){
	document.getElementById("viewQuestions"+threadId).action = "viewQuestions";
	document.getElementById("viewQuestions"+threadId).method = "post";
	document.getElementById("viewQuestions"+threadId).submit();
}

function viewQuestionsedit(threadId){
	document.getElementById("viewQuestionsedit"+threadId).action = "viewQuestions";
	document.getElementById("viewQuestionsedit"+threadId).method = "post";
	document.getElementById("viewQuestionsedit"+threadId).submit();
}

function EditThread(threadId){
	document.getElementById("editThread"+threadId).action = "editThread";
	document.getElementById("editThread"+threadId).method = "post";
	document.getElementById("editThread"+threadId).submit();
}

function deleteThread(threadId){
	document.getElementById("deleteThread"+threadId).action = "deleteThread";
	document.getElementById("deleteThread"+threadId).method = "post";
	document.getElementById("deleteThread"+threadId).submit();
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
                        <li><a href="forumHomePage">Forum</a></li>
                        <li><a href="dispayThreadData?forumId=${forumId}">Thread</a></li>
                    </ul>
                </div>
             
            </div>
        </div>
    <div class="clearfix"></div>

    <div class="container">

        <div class="row" >

            <!-- panel preview -->
  <%--  <div class="col-md-12 btn-container">
     <a class="btn btn-primary pull-right" href="getThreadPage?forumId=${forumId}">Create New Thread</a>
   </div> --%>

 <div class="clearfix"></div>
            
  <c:if test="${empty sessionBean.userID}">
    <table id="main-fourum1" class="table table-striped table-bordered" >
      <thead>
        <tr>
         
          
          <th>Thread Name</th>
          <th>Date &amp; Time</th>
          <th>Question Count</th>
          
        </tr>
      </thead>
      <tbody>
      <c:forEach items="${forum}" var="fm">
        <tr>
        
         
          <td>
           <form method="post" id="viewQuestions${fm.threadId}" >
          <input type="hidden" value="${fm.threadId}" name="threadId" id="threadId${fm.threadId}"/>
            <input type="hidden" value="${forumId}" id="forumId" name="forumId"/>
          </form>
          <a href="#" onclick="viewQuestions(${fm.threadId})">${fm.threadName}</a></td>
          <td>${fm.createdDate}</td>
          <td>${fm.questioncount}</td>
        </tr>
       </c:forEach>
      </tbody>
    </table>
  </c:if>
  
  <!-- / panel preview --> 
     <c:if test="${not empty sessionBean.userID}">
     <div class="container-fluid btn-container">
     <div class="col-md-12 btn-container">
     <c:if test="${sessionBean.roleID==6}">
     <a class="btn btn-primary pull-right" href="getThreadPage?forumId=${forumId}">Create New Thread</a>
   	 </c:if>
   	 </div> 
     
    </div>
    <div class="clearfix" style="font-size:16px;color: red;" align="center">${thredDeleted}</div>
      
     <table id="main-fourum" class="table table-bordered" >       
      <thead>
        <tr>          
          <th>Thread Name</th>
          <th>Date &amp; Time</th>
          <th>Question Count</th>
            <c:if test="${sessionBean.roleID==6}">
           <th>Action Edit</th>
          <th>Action Delete</th>
          </c:if>
        </tr>
      </thead>
      <tbody>
      <c:forEach items="${forum}" var="fm" varStatus="i">
        <tr>
          <td>
           <form method="post" id="viewQuestionsedit${fm.threadId}" >
          <input type="hidden" value="${fm.threadId}" name="threadId" id="threadIdedit${fm.threadId}"/>
            <input type="hidden" value="${forumId}" id="forumId" name="forumId"/>
          </form>
          <a href="#" onclick="viewQuestionsedit(${fm.threadId})">${fm.threadName}</a></td>
          <td>${fm.createdDate}</td>
          <td>${fm.questioncount}</td>
          <c:choose>
          <c:when test="${sessionBean.roleID==6}">
           <td>
           <form id="editThread${fm.threadId}">
		  <input type="hidden" value="${fm.threadId}" id="threadId${fm.threadId}" name="threadId"/>
		  <input type="hidden" value="${forumId}" id="forumId" name="forumId"/>
		 <a href="#" onclick="EditThread(${fm.threadId});">Edit</a>
		  </form>
           </td>
          <td>
          <form id="deleteThread${fm.threadId}">
		  <input type="hidden" value="${fm.threadId}" id="threadId${fm.threadId}" name="threadId"/>
		  <input type="hidden" value="${forumId}" id="forumId" name="forumId"/>
		 <%--  <a href="#" onclick="deleteThread(${fm.threadId});">Delete</a> --%>
		  
	<a href="#"  data-toggle="modal" data-target="#confirm-box1${i.count}">Delete</a>
      <div class="modal fade" id="confirm-box1${i.count}" role="dialog">
      <div class="modal-dialog">    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Confirmation Box</h4>
        </div>
        <div class="modal-body">
            <p>Are you sure want to delete thread?</p>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal">No</button>
          <button type="button" onclick="deleteThread(${fm.threadId})" class="btn btn-default" >Yes</button>        
        </div>
      </div>      
      </div>
     </div>
		  
		  
		  		 
         </form>     
         
          </td>
          </c:when>
          <c:otherwise>
         <c:if test="${sessionBean.userID eq fm.editPermissionId}">
          <td>
           <form id="editThread${fm.threadId}">
		  <input type="hidden" value="${fm.threadId}" id="threadId${fm.threadId}" name="threadId"/>
		  <input type="hidden" value="${forumId}" id="forumId" name="forumId"/>
		 <a href="#" onclick="EditThread(${fm.threadId});">Edit</a>
		  </form>
           </td>
           </c:if>
           <c:if test="${sessionBean.userID ne fm.editPermissionId}">
         <!--   <td></td> -->
           </c:if>
            <c:if test="${sessionBean.userID eq fm.deletePermissionId}">
           <td>
          <form id="deleteThread${fm.threadId}">
		  <input type="hidden" value="${fm.threadId}" id="threadId${fm.threadId}" name="threadId"/>
		  <input type="hidden" value="${forumId}" id="forumId" name="forumId"/>
		  <a href="#" onclick="deleteThread(${fm.threadId});">Delete</a>
         </form>
          </td>
          </c:if>
            <c:if test="${sessionBean.userID ne fm.editPermissionId}">
           <!--  <td></td> -->
            </c:if>
          </c:otherwise>
          </c:choose>
        </tr>
        
       </c:forEach>
      </tbody>
    </table>
  </c:if>
       <%--  <div class="panel panel-primary">
  <div class="panel-heading">Thread</div>
  <div class="panel-body">
<div class="main-forum-box">
	<c:forEach items="${forum}" var="fm">
  <div class="row">
  <form method="post" id="viewQuestions">
  <input type="hidden" value="${fm.threadId}" name="threadId"/>
  <div class="col-md-6"><a href="#" onclick="viewQuestions();">${fm.threadName}</a></div>
  </form>
  <div class="col-md-2">${fm.createdDate}</div>
  <form id="editThread${fm.threadId}">
  <input type="hidden" value="${fm.threadId}" id="threadId${fm.threadId}" name="threadId"/>
  <input type="hidden" value="${forumId}" id="forumId" name="forumId"/>
  <div class="col-md-2"><a class="btn btn-info pull-right" href="#" onclick="EditThread(${fm.threadId});">Edit</a></div>
  </form>
  <form id="deleteThread${fm.threadId}">
  <input type="hidden" value="${fm.threadId}" id="threadId${fm.threadId}" name="threadId"/>
  <input type="hidden" value="${forumId}" id="forumId" name="forumId"/>
  <div class="col-md-2"><a class="btn btn-info pull-right" href="#" onclick="deleteThread(${fm.threadId});">Delete</a></div>
  </form>
  </div>
  </c:forEach>
</div>
    </div>  </div> </div>
    	
    
            <!-- / panel preview -->

        </div>
	
   <table border='1px'>
      <tr><td>Thread Name<td>Created Date</td><td></td><td></td></tr>
      <c:forEach items="${forum}" var="fm">
    <tr> <td><a href="#"> ${fm.threadName}</a></td> <td><a href="#">Edit</a></td> <td><a href="#">Delete</a></td></tr>
      </c:forEach>
      
   </table> --%>
   </div>
   </div>
   
   <jsp:include page="commonJsp/Footer.jsp" />
   
   
<script>       
 $(document).ready(function() {
    $('#main-fourum1').DataTable( {
               
        "bFilter": true,
        "bInfo": true,
		"bPaginate": true ,
		
    } );	
} );	
</script> 
   
</body>
</html>