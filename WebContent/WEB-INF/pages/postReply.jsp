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
                <div class="pull-left">
                    <ul class="list-inline link-list">
                       <li><a href="forumHomePage">Forum</a></li>
					<%-- <li><a href="dispayThreadData?forumId=${forumId}">Thread</a></li> --%>
						<li><a href="viewQuestions?threadId=${threadId}">Thread</a></li>
                    </ul>
                </div>
             
            </div>
        </div>
    <div class="clearfix"></div>
<div class="clearfix"></div>
<div class="container">
  <div class="row" style="height:400px"> 
    
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
     <div class="container">


           	<div class="row">
	 <div class="col-sm-offset-3 col-sm-5">
          
            <div class="panel panel-default add-forum">
             <div class="panel-heading">Reply</div>
               <div class="panel-body form-horizontal">
    <form method="post" action="saveReply">
    <input type="hidden" value="${questionId}" name="questionId"/>
    <table  class="table table-striped table-bordered">
     
      <tbody> 
      	<tr>
         <td colspan="3"><strong>Q. ${question}?</strong> </td>          
        </tr>     
        <tr>
          <td colspan="3"><textarea name="reply" style="width: 100%" placeholder="write your replies here"></textarea></td>
         
        </tr>
        <tr><td colspan="3" align="center"><input type="submit" value="Post"></td></tr>
      </tbody>
    </table>
    <input type="hidden" name="forumId" value="${forumId}"/>
     <input type="hidden" name="threadId" value="${threadId}"/>
      <input type="hidden" name="questionId" value="${questionId}"/>
    </form>
    </div></div></div></div></div>
    </div>
    <!-- / panel preview --> 
    
  </div>
</div>
<jsp:include page="commonJsp/Footer.jsp" />
<style>



</style>
<!-- get jQuery from the google apis --> 
<script type="text/javascript" src="js/jquery.min.js"></script> 
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

<!-- Latest compiled JavaScript --> 
<script type="text/javascript" src="js/bootstrap.js"></script>
</body>
</html>