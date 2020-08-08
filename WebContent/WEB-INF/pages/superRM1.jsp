<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="org.hashids.Hashids" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ page isELIgnored="false"%>


<!DOCTYPE html>

<html>
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
<link rel="stylesheet" type="text/css" href="assets/css/ace.min.css" media="screen" />
<link rel="stylesheet" type="text/css" href="css/dataTables.bootstrap.min.css" media="screen" />

<script type="text/javascript">
function getManageID(appid){
	//alert(appid);
	document.getElementById("viewFourm"+appid).action = "getAllCloseStatusDetailsForSuperRM1";	
	document.getElementById("viewFourm"+appid).method = "get";
	document.getElementById("viewFourm"+appid).submit();
	
} 


function getManageGDID(appid){
	//alert(appid);
	document.getElementById("viewFourm"+appid).action = "getAllCloseStatusDetailsForSuperRMGD";	
	document.getElementById("viewFourm"+appid).method = "get";
	document.getElementById("viewFourm"+appid).submit();
	
} 
function getQuery(compid)
{
	//alert(compid)
	document.getElementById("viewFourm"+compid).action = "viewComQuestions";	
	document.getElementById("viewFourm"+compid).method = "get";
    document.getElementById("viewFourm"+compid).submit();
	}
function getShort(appid)
{
	document.getElementById("viewFourm"+appid).action = "getshortlistReceivePage";	
	document.getElementById("viewFourm"+appid).method = "get";
	document.getElementById("viewFourm"+appid).submit();
	}
function getHot(appid)
{
	//alert(appid);
	document.getElementById("viewFourm"+appid).action = "gethotlistReceivePage";	
	document.getElementById("viewFourm"+appid).method = "get";
	document.getElementById("viewFourm"+appid).submit();
	}
	function getShortRele(appid)
	{
		document.getElementById("viewFourm"+appid).action = "getshortlistReleaseCompanyHRPage";	
		document.getElementById("viewFourm"+appid).method = "get";
		document.getElementById("viewFourm"+appid).submit();
	}
	
	function getHotRele(appid)
	{
		document.getElementById("viewFourm"+appid).action = "getHotlistReleaseCompanyHRPage";	
		document.getElementById("viewFourm"+appid).method = "get";
		document.getElementById("viewFourm"+appid).submit();
	}
function getDropDownData(){
	 var xhttp = new XMLHttpRequest();
	  xhttp.onreadystatechange = function() {
	    if (this.readyState == 4 && this.status == 200) {
	    	var jsonData=JSON.parse(this.responseText);
	    	var options="<option>---Select RM Name-----</option>";
	    	for(var i=0;i<jsonData.length;i++){
	    		options+="<option value="+jsonData[i].designationId+">"+jsonData[i].designationName+"</option>"
	    	}
	    	document.getElementById("designation").innerHTML=options;
	    }
	  };
	  xhttp.open("POST", "getDesignationList", true);
	  xhttp.send();
}
function getRMDropDownData(appShareid){
	
		//alert("appid :: " +appShareid)
		document.getElementById("appShareid").value=appShareid;
	 var xhttp = new XMLHttpRequest();
	  xhttp.onreadystatechange = function() {
	    if (this.readyState == 4 && this.status == 200) {
	    	var jsonData=JSON.parse(this.responseText);
	    	var options="<option>Share With</option>";
	    	for(var i=0;i<jsonData.length;i++){
	    		options+="<option value="+jsonData[i].userId+">"+jsonData[i].userName+"</option>"
	    		
	    	}
	    	document.getElementById("rmlist").innerHTML=options;
	    }
	  };
	  xhttp.open("POST", "getRMList", true);
	  xhttp.send();
}

function editCompany(value){
	document.getElementById("editApplication"+value).action = "openCompanyEditForm";
	document.getElementById("editApplication"+value).submit();  
}
function getScheduleID(appid){
	//alert(appid);
	document.getElementById("viewFourm"+appid).action = "getRMSchedulePage";	
	document.getElementById("viewFourm"+appid).method = "get";
	document.getElementById("viewFourm"+appid).submit();
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
                       <!--  <li><a href="forumHomePage">Home</a></li> -->
                        
                        <li>Super RM</li>
                    </ul>
                </div>
             
            </div>
        </div>
    <div class="clearfix"></div>
<div class="clearfix"></div>
<div class="container" style="min-height:400px">
  <div class="row"> 
   <div class="space"></div>
   <div class="form-group">
						<label class="col-sm-4 control-label" >Company Name</label>
						<div class="col-sm-8">
							<select class="form" onchange="getManageAppDetailsByCompId(this);" name="" required>
								<option value="" >select</option>
								<c:forEach var="comp" items="${companyList}">
								  <option value="${comp.companyId}">${comp.companyName}</option>
								</c:forEach>
							</select>
						</div>						 
						
	</div>
	
	<!-- stated -->
	
	<!-- panel preview -->
    <!-- <div class="space"></div> -->
    <!-- <div class="container-fluid btn-container"><a href="getFirmMangement " class="btn btn-primary pull-right">Add Firm</a></div> -->
    <div class="clearfix"></div>
    <div class="table-responsive">
    <table id="main-fourum" class="table table-striped table-bordered">
    
      <thead>
        <tr>
         
          <th>Sr. No.</th>
          <th>RM Name</th>
          <th>Firm</th>
          <th>Role</th>
           <th>Application Status</th>
          <th>Manage</th>
           <th>Share</th>
        
          <th>Edit</th> 
            <th>Query</th>
         <!-- <th>Verify</th>-->
        </tr>
      </thead>
      <tbody id="manageAppId">
      
       <c:forEach items="${manageApp}" var="ma">
      	
        <tr>
        <td>${ma.snum}</td>
        <td>${ma.rmName}</td>
        <td>${ma.firm}</td>
        <td>${ma.role}</td>
        <td>${ma.appStatus}</td>
         <c:set var="roleVal" value="${ma.appid}"/>
         <%	Integer appid=pageContext.getAttribute("roleVal")==null?0:(Integer)pageContext.getAttribute("roleVal");
 		Hashids hashids = new Hashids("comp role id",10);
 		String hash = hashids.encode(appid); %>
 		<td>
	 		<c:choose>
		        <c:when test="${ma.appStatus=='Pending'|| ma.appStatus=='Process Done'}">
	       	 		${ma.manage}
		        </c:when>
		        <c:when test="${ma.appStatus=='Open'}">
			        <form id="viewFourm${ma.appid}" method="get">
			      	 	<input type="hidden" value="<%=hash%>" name="cmpRoleId" id="viewFourm${ma.appid}"/>
			        	<a href="#" onclick="getScheduleID(${ma.appid})">${ma.manage}</a>
			        </form>
		        </c:when>
		        <c:when test="${ma.appStatus=='Close'}">
			        <c:choose>
						<c:when test="${ma.noOfSelectionRounds=='2' }">
							<form id="viewFourm${ma.appid}" method="get">
				      	 		<input type="hidden" value="<%=hash%>" name="cmpRoleId" id="viewFourm${ma.appid}"/>
				        		<a href="#" onclick="getManageGDID(${ma.appid})">${ma.manage}</a>
				        	</form>
						</c:when>
						<c:otherwise>
							<form id="viewFourm${ma.appid}" method="get">
				      	 		<input type="hidden" value="<%=hash%>" name="cmpRoleId" id="viewFourm${ma.appid}"/>
				        		<a href="#"  onclick="getManageID(${ma.appid})">${ma.manage}</a>
				        	</form>
						</c:otherwise>
					</c:choose>
		        </c:when>
		        <c:when test="${ma.appStatus=='Short List Received-1' || ma.appStatus=='Sent to HR Shortlist-1'}">
			        <form id="viewFourm${ma.appid}" method="get">
		      	 		<input type="hidden" value="<%=hash%>" name="cmpRoleId" id="viewFourm${ma.appid}"/>
		        		<a href="#" onclick="getManageGDID(${ma.appid})">${ma.manage}</a>
		        	</form>
		        </c:when>
		        <c:otherwise>
		        	<form id="viewFourm${ma.appid}" method="get">
			      	 	<input type="hidden" value="<%=hash%>" name="cmpRoleId" id="viewFourm${ma.appid}"/>
			        	<a href="#"  onclick="getManageID(${ma.appid})">${ma.manage}</a>
		        	</form>
		        </c:otherwise>
	        </c:choose>
     	</td>    
       	<td> <input type="button" class="btn btn-primary" data-toggle="modal" data-target="#rm-ma-share" value="Share" onclick="getRMDropDownData(${ma.appShareid});">
        <td>
       			<form  action="openCompanyEditForm" method="post" id="editApplication${ma.appShareid}">
								<input type="hidden" value="${ma.appShareid}" id="appId" name="appId"/>
								<input type="button" name="Edit" value="Edit" class="btn btn-primary" onclick="editCompany(${ma.appShareid})"/>
						
				</form>
		</td>
		<td>
        <c:choose>
	        <c:when test="${ma.appStatus=='Pending'|| ma.appStatus=='Process Done'}">
       	 	 	${ma.query}
	        </c:when>
	        <c:otherwise>
	        	<a href="viewComQuestions?cmpId=${ma.compid}" target="_blank">${ma.query}</a>
	        	<%-- <form id="viewFourm${ma.compid}" method="get">
      	 			<input type="hidden" value="${ma.compid}" name="cmpId" id="viewFourm${ma.compid}"/>
        			<a href="#"  onclick="getQuery(${ma.compid})">${ma.query}</a>
        		</form> --%>
	        </c:otherwise>
        </c:choose>
        
        </td>
        </tr>
        
        
       </c:forEach>
       
       
      </tbody>
    </table>
    </div>
    <!-- / panel preview -->
			<div class="modal fade" id="C-role-edit" role="dialog">
				<div class="modal-dialog">

					<!-- Modal content-->
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal">&times;</button>
							<h4 class="modal-title">Edit Role</h4>
						</div>
						<form id="submitKmIIMStudent" action="editDesignationRole" method="POST"
							name="Designation">
							
							<input type="hidden"  name="designationid" id="designationid" value="0"/>
							
							<div class="modal-body">
								
								<div class="row">
									<label class="col-md-4">Designation Name</label>
									<div class="col-md-8">
											<select class="form-control" id="designation" name="designationname">
											</select>
									</div>
								</div>
								<p></p>
								<div class="row">
									<label class="col-md-4">Designation Description</label>
									<div class="col-md-8">
										<input class="form-control" id="designationdescription" placeholder="Designation Description"
											type="text" name="designationdescription" required>
									</div>
								</div>
								<input type="hidden" name="compnayRolePkId" id="companyRolePkId"/>
							</div>
							<div class="modal-footer">
								<button type="submit" class="btn btn-default">Submit</button>
								<button type="button" class="btn btn-default"
									data-dismiss="modal">Cancel</button>
							</div>
						</form>
					</div>
				</div>
			</div>
    
    
    		<div class="modal fade" id="rm-ma-share" role="dialog">
    <div class="modal-dialog">
    
      Modal content
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Share Application</h4>
        </div>
        <form id="saveShare" action="shareRoleWith" method="POST"
							name="RmName">
							<!-- <input type="hidden" name="appid" value="0"/> -->
        <div class="modal-body">
      <input type="hidden"  name="userid" id="userid" value="rmlist.useid"/>
        <div class="row">
        <label class="col-md-6">Share With</label>
        <div class="col-md-6">
        <select class="form-control" id="rmlist" name="rmlist">
										</select>
        </div></div>
       </div>
         <input type="hidden" name="appShareid" id="appShareid" />
        <div class="modal-footer">
         <button type="submit" class="btn btn-default">Add</button>
          <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
        </div>
        </form>
      </div>
      
    </div>
  </div>
	
	
	
	<!-- ended  -->
	
	
	
	
	
  </div>
</div>





<jsp:include page="commonJsp/Footer.jsp" />
<!-- <script type="text/javascript" src="js/jquery.min.js"></script> -->

<script>
   function getRoleByCompanyId(compId){
	//   alert(compId.value);
	   var xhttp = new XMLHttpRequest();
		 xhttp.onreadystatechange = function() {
		 if (this.readyState == 4 && this.status == 200) {	   
		     this.responseText;	     
		     var i;
		     var obj = JSON.parse(this.responseText);	    
		     var compRole='';
		     for(i=0; i < obj.length; i++){	          
		        
		    	 compRole+="<option value='"+obj[i].companyRoleId+"'>"+obj[i].companyRoleName+"</option>";
		    	
		     }	    
		     document.getElementById("compRoleId").innerHTML="<option>Select</option>"+compRole;
		    
		 }
		 };
		 var action="getRoleByCompanyId?compId="+compId.value;
		 xhttp.open("POST", action, true);
		 xhttp.send(); 		
   }
   
   function getManageAppDetailsByCompId(compId){
	  
	   var xhttp = new XMLHttpRequest();
		 xhttp.onreadystatechange = function() {
		 if (this.readyState == 4 && this.status == 200) {	   
		     this.responseText;	     
		     var i;
		     var obj = JSON.parse(this.responseText);	
		     var manage=" ";
		     var manageApp='';
		     var edit=" ";	   
             var share=" ";
             var query=" ";
             var rmName="";
                                      
		     for(i=0; i < obj.length; i++){
		        
		    	  edit="<form  action='openCompanyEditForm' method='post' id=editApplication"+obj[i].appShareid+">"
					  +"<input type='hidden' value="+obj[i].appShareid+" id='appId' name='appId'/>"
					  +"<input type='button' name='Edit' value='Edit' class='btn btn-primary' onclick=editCompany("+obj[i].appShareid+")></form>";
			     share="<input type='button' class='btn btn-primary' data-toggle='modal' data-target='#rm-ma-share' value='Share' onclick=getRMDropDownData("+obj[i].appShareid+")>";
		    	
			     if(obj[i].appStatus=='Pending' || obj[i].appStatus=='Process Done'){
		    		 manage=obj[i].manage;
		    	 }
			     else if(obj[i].appStatus=='Close'){
		    		 if(obj[i].noOfSelectionRounds=='2'){
		    			 manage="<form id='viewFourm"+obj[i].appid+"' method='get'>"
		    		       +"<input type='hidden' value="+obj[i].hashAppID+" name='cmpRoleId' id='viewFourm"+obj[i].appid+"'/>"
		    		       +"<a href='#' onclick=getManageGDID("+obj[i].appid+")>"+obj[i].manage+"</a></form>";
		    		 }else{
		    			 manage="<form id='viewFourm"+obj[i].appid+"' method='get'>"
		    		       +"<input type='hidden' value="+obj[i].hashAppID+" name='cmpRoleId' id='viewFourm"+obj[i].appid+"'/>"
		    		       +"<a href='#' onclick=getManageID("+obj[i].appid+")>"+obj[i].manage+"</a></form>"; 
		    		 }
		    		 
		    	 }
		    	 else if(obj[i].appStatus=='Short List Received-1' || obj[i].appStatus=='Sent to HR Shortlist-1'){
	    			 manage="<form id='viewFourm"+obj[i].appid+"' method='get'>"
	    		       +"<input type='hidden' value="+obj[i].hashAppID+" name='cmpRoleId' id='viewFourm"+obj[i].appid+"'/>"
	    		       +"<a href='#' onclick=getManageGDID("+obj[i].appid+")>"+obj[i].manage+"</a></form>";
		    	 }
		    	 else{
		    		 manage="<form id='viewFourm"+obj[i].appid+"' method='get'>"		             
		           	        +"<input type='hidden' value='"+obj[i].hashAppID+"' name='cmpRoleId' id='viewFourm"+obj[i].appid+"'/>"
		                    +"<a href='#'  onclick=getManageID("+obj[i].appid+")>"+obj[i].manage+" </a></form>";
		    	 }
			     
		    	 if(obj[i].appStatus=='Pending' || obj[i].appStatus=='Process Done'){
		    		 query=obj[i].query;
		    	 }
		    	 else{
                  	query=" <a href='viewComQuestions?cmpId='"+obj[i].compid+"' target='_blank'>"+obj[i].query+"</a>";
		    	 }
		    			  		
		  		if(obj[i].userName==null || obj[i].userName==''){
		  			rmName='';
		  		}else if(obj[i].userName!=null){
		  			rmName=obj[i].userName;
		  		}
		    	 manageApp+="<tr><td>"+ (i+1) +"</td><td>"+rmName+"</td><td>"+obj[i].firm+"</td><td>"+obj[i].role+"</td><td>"+obj[i].appStatus+"</td><td>"+manage+"</td><td>"+share+"</td><td>"+edit+"</td><td>"+query+"</td></tr>";
		    	
		     }	    
		     document.getElementById("manageAppId").innerHTML=manageApp;
		    
		 }
		 };
		 var action="getManageAppDetailsByCompId?compId="+compId.value;
		 xhttp.open("POST", action, true);
		 xhttp.send(); 	
	 
   }
   
</script>
 <script type="text/javascript">

$(document).on("click", "#edit", function () {
  
    var Id = $(this).data('id');
    $("#designationid").val($(this).data('id'));
    $("#designationname").val($(this).data('name'));
   // $("#designationdescription").val($(this).data('description')); 
    $("#companyRolePkId").val(Id); 
    $("#C-role-edit").show();
});
</script>
 
 
</body>
</html>