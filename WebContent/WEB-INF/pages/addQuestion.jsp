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
						    <li><a href="forumHomePage">Home</a></li>
						     <li><a href="dispayThreadData?forumId=${forumId}">Thread</a></li>
					</c:otherwise>
					</c:choose>
                       
					<li><a href="viewQuestions?threadId=${threadId}">Questions</a></li>
                        
                    </ul>
                </div>
                </c:if>
             
            </div>
        </div>
    
<div class="space"></div>
<div class="container">
  <div class="row"> 
    
    <!-- panel preview -->
    <!-- <div class="container-fluid btn-container">
     <input type="button" class="btn btn-primary pull-right"  value="Delete"/>
      <input type="button" class="btn btn-primary pull-right" value="Create" /> 
     
    </div> -->
    <div class="clearfix"></div>
   
   
   
    <!-- <aside class="col-md-3 col-sm-12 col-xs-12 left-bar">
    <div class="panel">
    <div class="info-stats green-two">
    <div class="icon-type pull-left">
    <i class="fa fa-user"></i>
    </div>
    <div class="sale-num">
    <h4>Mr. N.S. Chandra</h4><p>Students </p>
    </div>
    </div>
    </div>    
    </aside> -->
    
    <div class="col-md-9 col-sm-12 col-xs-12">
    
    <div class="clearfix"></div>
    <div class="container main-container">


           	<div class="row" style="height:400px">
	 <div class="col-sm-offset-3 col-sm-5">
          
            <div class="panel panel-default add-forum">
           
             <input type="hidden" value="${question}" id="question"/>
               <div class="form-horizontal">
               <c:choose>
               	<c:when test="${formType eq 'EDIT'}">
               	  <div class="panel-heading">Edit Question</div>
               	     <div class="form-horizontal">
               	 <form action="editQuestionvalues" method="post" >
               	</c:when>
               	<c:otherwise>
               	 <div class="panel-heading">Add Question</div>
               	     <div class="panel-body form-horizontal">
               	<form action="addQuestion" method="post" >
               	</c:otherwise>
               </c:choose>
		<table>
			<tr>
				<td class="col-md-4"><label >Write questions:</label></td>
			<td class="col-md-8"><textarea class="form-control" rows="4" id="questionValue" cols="50" name="question" placeholder="Write your question here" required maxlength='500'>
       			Write your question here.
       </textarea>
       </td>
      
        <input type="hidden" name="threadId" value="${threadId}"/> 
			</tr>
			
			<tr>
				<td colspan="2" align="center">
				<c:choose>
				<c:when test="${formType eq 'EDIT'}">
				<input type="hidden" id="questionId" name="questionId" value="${questionId}"/>
				<input type="hidden" id="threadId" name="threadId" value="${threadId}"/>
				<input class="btn btn-blue" type="submit" value="Update"/>
				</c:when>
				
				<c:otherwise>
					<input class="btn btn-blue" type="submit" value="Save" style="margin-top:20px;"/>
				</c:otherwise>
				</c:choose>
				</td>
			</tr>
		</table>
	</form>
	</div></div></div></div></div>
    
    <!-- / panel preview --> 
    
  </div>
    </div>  </div>
</div>
<jsp:include page="commonJsp/Footer.jsp" />
<!-- get jQuery from the google apis 
<script type="text/javascript" src="js/jquery.min.js"></script> 
<script type="text/javascript" src="js/jquery.dataTables.min.js"></script> 
<script type="text/javascript" src="js/dataTables.bootstrap.min.js"></script> 
<script>
       
	   $(document).ready(function() {
    $('#main-fourum').DataTable( {
               
        "bFilter": false,
        "bInfo": false,
		"bPaginate": true ,
		
    } );
	$("#main-fourum_length").hide();
	
} );
       
	 
      

<!-- Latest compiled JavaScript 
<script type="text/javascript" src="js/bootstrap.js"></script> --> 
<script type="text/javascript">
 var question=document.getElementById("question").value;
 document.getElementById("questionValue").innerHTML=question;
</script>

</body>
</html>