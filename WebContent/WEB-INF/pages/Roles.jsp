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

function getCreateMasterRole()
{
	document.getElementById("masterrolecreate").action = "openForm";	
	document.getElementById("masterrolecreate").method = "post";
	document.getElementById("masterrolecreate").submit();
	}
	
	
	
function getEditRole(roleid)
{
	document.getElementById("roleEdit"+roleid).action = "editRole";	
	document.getElementById("roleEdit"+roleid).method = "post";
	document.getElementById("roleEdit"+roleid).submit();
}
function getDeleteRole(roleid)
{
	document.getElementById("roleDelete"+roleid).action = "deleteRole";	
	document.getElementById("roleDelete"+roleid).method = "post";
	document.getElementById("roleDelete"+roleid).submit();
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
					<li><a href="#">User Role</a></li>
				</ul>
			</div>

		</div>
	</div>
	<div class="clearfix"></div>

	<div class="container">

		<div class="row" style="height:400px">
 <!-- panel preview -->
<br>
    <div class="clearfix"></div>

  
    <!-- / panel preview --> 
     <c:if test="${not empty sessionBean.userID}">
     
      
     <div class="container-fluid btn-container">
       
      <c:if test="${sessionBean.roleID==6}">
    
	     <form id="masterrolecreate" method="post">
	        <input type="button" id="createForum" class="btn btn-primary pull-right" value="Create"  onclick="getCreateMasterRole()"/> 
	     </form>
	     
      </c:if>
      
      <c:if test="${sessionBean.roleID==1}">
      
         <form id="masterrolecreate" method="post">
            <input type="button" id="createForum" class="btn btn-primary pull-right" value="Create"  onclick="getCreateMasterRole()" disabled/> 
         </form>
         
      </c:if>
      
    </div>
    
    
    <div class="clearfix"></div>
    
    
    <table id="main-fourum" class="table table-striped table-bordered" >
      <thead>
        <tr>
         
        <!--    <th>Sr. No.</th>-->
          <th>Role Name</th>
          <th>Role Description</th>
          <th>Module Id</th>
  
          <th>Action Edit</th>
          <th>Action Delete</th>
          
        </tr>
      </thead>
      <tbody>
      <c:forEach items="${role}" var="mr" varStatus="loop">
     
      <tr>
          <td>
	          ${mr.rolename}
          </td>
        
          <td>
              ${mr.roledescription}
          </td>
          
          <td>
              ${mr.moduleid} 
             <%--  <a href="#" onclick="getFormEditId(${fm.forumId})"> ${fm.threadCount}</a> --%>
          </td>
         
         <c:if test="${sessionBean.roleID==6}">
 	          <td>
 	          	<form id="roleEdit${mr.pkroleid}" method="post">
	             	<input type="hidden" value="${mr.pkroleid}" name="roleId" id="roleId${mr.pkroleid}"/>
	          	</form>
           		<a href="#" onclick="getEditRole(${mr.pkroleid})">Edit</a>
	           
	          </td>
	      
	          <td>
	           <form id="roleDelete${mr.pkroleid}" method="post">
	             <input type="hidden" value="${mr.pkroleid}" name="roleId" id="roleId${mr.pkroleid}"/>
	          </form>
	           
	             <a href="#"  data-toggle="modal" data-target="#confirm-box1${loop.count}">Delete</a>
								 <div class="modal fade" id="confirm-box1${loop.count}" role="dialog">
      <div class="modal-dialog">    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Confirmation Box</h4>
        </div>
        <div class="modal-body">
            <p>Are you sure want to delete role?</p>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal">No</button>
          <button type="button" onclick="getDeleteRole(${mr.pkroleid})" class="btn btn-default" >Yes</button>        
        </div>
      </div>      
      </div>
     </div>
	          </td>
         </c:if>
         
      </tr>
        
        
       </c:forEach>
      </tbody>
    </table>
  </c:if>
  </div>
			
		</div>
	
	
	<jsp:include page="commonJsp/Footer.jsp" />

</body>
</html>