<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Indian Institute of Management Ahmedabad (IIMA) | Home - IIMA</title>
    <link rel="icon" href="images/favicon.ico" type="image/x-icon"/>
<!-- Bootstrap -->
<link href="css/bootstrap.css" rel="stylesheet">

<!-- Custom -->
<link href="css/custom.css" rel="stylesheet">
<link rel="stylesheet" href="css/font-awesome.css">

<!-- CSS STYLE-->
<link rel="stylesheet" type="text/css" href="css/style.css" media="screen" />
<link rel="stylesheet" type="text/css" href="css/dataTables.bootstrap.min.css" media="screen" />
</head>
<body>
<jsp:include page="commonJsp/Header.jsp" />
 <div class="clearfix"></div>
<div class="breadcumb-wrapper">
            <div class="clearfix">
            <c:if test="${sessionBean.roleID!=4}">
                <div class="pull-left">
                    <ul class="list-inline link-list">
                       <!--   <li><a href="forumHomePage">Forum</a></li> -->
                       	<c:choose>
					 <c:when test="${sessionBean.roleID==1}">
					</c:when>
					<c:otherwise>
						<li><a href="forumHomePage">Forum</a></li>
					</c:otherwise>
					</c:choose>
                         <%-- <li><a href="dispayThreadData?forumId=${forumId}">Thread</a></li>
					<li><a href="viewQuestions?threadId=${threadId}&forumId=${forumId}">Questions</a></li> --%>
					<li><a href="viewQuestions?threadId=${threadId}">Questions</a></li>
					 <li>Reply</li> 
                         <!-- <li><a href="#">Thread</a></li>
                         <li><a href="#">Question</a></li>
                         <li><a href="#">Reply</a></li> -->
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
       <script>
       var a=${threadId};
       var b=${forumId};
       var roleId=${sessionBean.roleID};
      var userid= ${sessionBean.userID};
       var replyId=${replyId};
       var replyShareRMId = ${replyShareRMId};
       </script>
     
       <c:if test="${sessionBean.roleID==6 or sessionBean.userID==replyShareRMId or sessionBean.userID==replyId}"> 
    
     <form name="forumLogin" id="forumLogin" commandName="UserBean">
		<input type="hidden" value="postReply" name="forumPage" id="forumId" />
		<input type="hidden" value="${questionId}" name="questionId"/>
		<input type="hidden" value="reply" name="commingFrom"/>	
		<input type="hidden" value="${forumId}" name="forumId"/>
		<input type="hidden" value="${threadId}" name="threadId"/>
		<input type="button" id="loginBtnId" name="loginForum" class="btn btn-primary pull-right" value="Reply" onclick="login();" /> 		
	</form>
	</c:if>
     
    
     
    </div>
    <div class="clearfix"></div>
   
   
   
    <aside class="col-md-3 col-sm-12 col-xs-12 left-bar">
    <div class="panel">
    <div class="info-stats green-two">
    <div class="icon-type pull-left">
 <i class="fa fa-user"></i>
    </div><div class="sale-num">
    <h4>${userName}</h4><p>${roleName} </p>
    </div></div></div>    
     </aside>
     
    <div class="col-md-9 col-sm-12 col-xs-12">    
     <div class="col-md-12"><strong>Q: ${repList[0].questionName}?</strong></div>
      <div class="clearfix"></div>
    <!--   id="main-fourum" -->
    <table  class="table table-striped table-bordered">
      <thead>
        <tr>
         <th>Replies</th>
          <th>Date & Time</th>
          
        
        </tr>
      </thead>
      <tbody>
               
        
        <c:forEach items="${repList}" var="q">
         <tr> <td><strong>Replies :</strong> ${q.reply}. </td>
          <td>${q.createdDate}</td> </tr>
         </c:forEach>
       
       
      </tbody>
    </table>    
    </div>
    <!-- / panel preview --> 
     <div>
    <input type="button" class="btn btn-primary" value="Back" onclick="javascript:location.href='viewComQuestions?cmpId=<%= request.getParameter("cmpId")%>'">
   </div>
  </div>  
</div>
<jsp:include page="commonJsp/Footer.jsp" />
<style>



</style>
<!-- get jQuery from the google apis --> 
<!--<script type="text/javascript" src="js/jquery.min.js"></script> -->
<script type="text/javascript" src="js/jquery.dataTables.min.js"></script> 
<script type="text/javascript" src="js/dataTables.bootstrap.min.js"></script> 
<script>
     /*   
	   $(document).ready(function() {
    $('#main-fourum').DataTable( {
               
        "bFilter": false,
        "bInfo": false,
		"bPaginate": true ,
		
    } );
	$("#main-fourum_length").hide();
	
} ); */
       
	 
 </script> 
 
 <script>
function login(){
	//var currentLocation = window.location;
	    //document.getElementById('forumId').value='forum';	  
	    document.forumLogin.action = "login";
		document.forumLogin.method = "post";
	    document.forumLogin.submit();        
	
}
function replying(){  
    document.replyForm.action = "saveReply";
    document.replyForm.submit();   
}
function goToAllQuestion(){
	
}
</script>

<!-- Latest compiled JavaScript
<script type="text/javascript" src="js/bootstrap.js"></script>--> 
</body>
</html>