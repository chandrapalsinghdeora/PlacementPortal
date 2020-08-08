<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Indian Institute of Management Ahmedabad (IIMA) | Home - IIMA</title>
    <link rel="icon" href="images/favicon.ico" type="image/x-icon"/>
<script type="text/javascript">
function getFormId(froumid){
	//alert(froumid);
	document.getElementById("viewFourm"+froumid).action = "dispayThreadData";	
	document.getElementById("viewFourm"+froumid).method = "post";
	document.getElementById("viewFourm"+froumid).submit();
	/* var param =froumid;
    var myForm = document.forms["viewFourm"];
    myForm.elements["forumId"].value = param;
    myForm.submit(); */
}

function getFormEditId(froumid){
	//alert(froumid);
	document.getElementById("forumedit"+froumid).action = "dispayThreadData";	
	document.getElementById("forumedit"+froumid).method = "post";
	document.getElementById("forumedit"+froumid).submit();
	/* var param =froumid;
    var myForm = document.forms["viewFourm"];
    myForm.elements["forumId"].value = param;
    myForm.submit(); */
}

function getCreateForum()
{
	document.getElementById("forumcreate").action = "getCreateForumPage";	
	document.getElementById("forumcreate").method = "post";
	document.getElementById("forumcreate").submit();
	}
function getEditForum(froumid)
{
	document.getElementById("forumedit"+froumid).action = "editForum";	
	document.getElementById("forumedit"+froumid).method = "post";
	document.getElementById("forumedit"+froumid).submit();
	}
function getDeletForum(froumid)
{
	document.getElementById("forumedit"+froumid).action = "deleteForum";	
	document.getElementById("forumedit"+froumid).method = "post";
	document.getElementById("forumedit"+froumid).submit();
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
					<li><a href="#">Forum</a></li>
				</ul>
			</div>

		</div>
	</div>
	<div class="clearfix"></div>
   
   
	
	<div class="container">

		<div class="row" style="min-height:400px">
		
 <!-- panel preview -->
<br>
    <div class="clearfix"></div>
    
   <c:if test="${empty sessionBean.userID}">
   <div class="table-responsive">
    <table id="main-fourum" class="table table-striped table-bordered" >
      <thead>
        <tr>
         
        <!--    <th>Sr. No.</th>-->
          <th>Forum Name</th>
          <th>Date &amp; Time</th>
          <th>Thread Count</th>
          
        </tr>
      </thead>
      <tbody>
      <c:forEach items="${forum}" var="fm">
      	
        <tr>
              
           
         <!--  <td>${fm.rowCount}</td> -->
         <td>
          <form id="viewFourm${fm.forumId}" method="post">
      	 <input type="hidden" value="${fm.forumId}" name="forumId" id="forumId${fm.forumId}"/>
         </form>
         <a href="#" onclick="getFormId(${fm.forumId})"> ${fm.forumName}</a></td>
        
          <td>${fm.createdDate}</td>
          
         <td><a href="#" onclick="getFormId(${fm.forumId})"> ${fm.threadCount}</a></td>
          
         
         
         
        </tr>
        
        
       </c:forEach>
      </tbody>
    </table>
    </div>
  </c:if>
    <!-- / panel preview --> 
     <c:if test="${not empty sessionBean.userID}">
     
     <div class="container-fluid btn-container">
      <c:if test="${sessionBean.roleID==6}">
      <div class="text-center">
     <span id="error" style="font-size:16px;color: red;">${forumDeleted}</span>                              
    </div>
     <form id="forumcreate" method="post">
       
      <input type="button" id="createForum" class="btn btn-primary pull-right" value="Create"  onclick="getCreateForum()"/> 
       </form>
       </c:if>
     
        
      
       
    </div>
    <div class="clearfix"></div>
    <div class="table-responsive">
    <table id="main-fourum" class="table table-striped table-bordered" >
      <thead>
        <tr>
         
        <!--    <th>Sr. No.</th>-->
          <th>Forum Name</th>
          <th>Date &amp; Time</th>
          <th>Thread Count</th>
        
          
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
          <form id="forumedit${fm.forumId}" method="post">
          <input type="hidden" value="${fm.forumId}" name="forumId" id="forumId${fm.forumId}"/>
         </form>
         <a href="#" onclick="getFormEditId(${fm.forumId})"> ${fm.forumName}</a></td>
        
          <td>${fm.createdDate}</td>
          
         <td><a href="#" onclick="getFormEditId(${fm.forumId})"> ${fm.threadCount}</a></td>
          <c:if test="${sessionBean.roleID==6}">
         
         <td><a href="#" onclick="getEditForum(${fm.forumId})">Edit</a></td>
         
         
         
          <td>
          
        <%--  <a href="#" onclick="getDeletForum(${fm.forumId})">${fm.forumId}= Delete</a> --%>         
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
            <p>Are you sure want to delete forum?</p>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal">No</button>
          <button type="button" onclick="getDeletForum(${fm.forumId})" class="btn btn-default" >Yes</button>        
        </div>
      </div>      
      </div>
     </div>
     
      </td>
          
      
     
          </c:if>
          <c:if test="${sessionBean.roleID==1}">
        
         <td> <c:if test="${sessionBean.userID eq fm.editPermission}"><a href="javascript:void(0)" onclick="getEditForum(${fm.forumId})">Edit</a></c:if></td>
         
          <td>
            <c:if test="${sessionBean.userID eq fm.deletePermission}">
            <%--  <a href="#" onclick="getDeletForum(${fm.forumId})">${fm.forumId}= Delete</a> --%>         
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
		            <p>Are you sure want to delete forum?</p>
		        </div>
		        <div class="modal-footer">
		          <button type="button" class="btn btn-default" data-dismiss="modal">No</button>
		          <button type="button" onclick="getDeletForum(${fm.forumId})" class="btn btn-default" >Yes</button>        
		        </div>
		      </div>      
		      </div>
		     </div>
           
          <!--    <a href="javascript:void(0)"  data-toggle="modal" data-target="#confirm-box">Delete</a> -->
       </c:if>
     
          </td>
        
          </c:if>
          
        </tr>
        
       </c:forEach>
      </tbody>
    </table>
       </div> 
  </c:if>
  <!-- <a href="getPanelAllocationPage">panel allocation</a> -->
  </div>
			
		</div>
	  
	
	<jsp:include page="commonJsp/Footer.jsp" />

</body>
</html>