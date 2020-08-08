<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
 <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
 
 <title>Indian Institute of Management Ahmedabad (IIMA) | Home - IIMA</title>
    <link rel="icon" href="images/favicon.ico" type="image/x-icon"/>
 
 
<jsp:include page="commonJsp/Header.jsp" />

<div class="clearfix"></div>
<div class="breadcumb-wrapper">
	<div class="clearfix">
		<div class="pull-left">
			<ul class="list-inline link-list">
				<li><a href="forumHomePage">Home</a></li>
				<li>Profile Lock</li>
			</ul>
		</div>

	</div>
</div>
<div class="clearfix"></div>

<div class="container min-height">

	<div class="row">
    <form  id="lockedProfile">
    
		<!-- panel preview -->
		<div class="col-md-12 btn-container"></div>
		<div class="clearfix"></div>
		<div class="panel-body" >
						<label class="control-label">Student Group:</label> <select
							 name="groupid" id="groupid" onchange="getpgpStudent();">
							<option value="0">select</option>
							<option value="1">PGP1</option>
							<option value="2">PGP2</option>
						</select>
					</div>
		<div class="table-responsive">
		<table id="main-fourum1" class=" table table-striped table-bordered">
			<thead>
				<tr>
				<th><input type="checkbox" id="selectall"></th>
					<th>S. No.</th>
					<th>Student Name</th>
					<th>Email Id</th>
					<th>Status</th>
					
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${profilelist}" var="fm" varStatus="loop">
					<tr>
					<td>
					<%-- <c:if test="${fm.adminLock eq true}">
					<input type="checkbox" class="case" name="selectedIds" value="${fm.profileLockId}" checked/>
					</c:if>
					<c:if test="${fm.adminLock eq false}">
					<input type="checkbox" class="case" name="selectedIds" value="${fm.profileLockId}"/>
					</c:if> --%>
					<input type="checkbox" class="case" name="selectedIds" value="${fm.profileLockId}"/>
					</td>
						<td>${loop.index+1}.</td>
						<td>${fm.studentName}</td>
						<td>${fm.emailId}</td>
						<c:if test="${fm.adminLock eq true}">
						<td>Locked</td>
						</c:if>
						<c:if test="${fm.adminLock eq false}">
						<td>Unlocked</td>
						</c:if>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		</div>
		<div class="col-md-12 text-right">
		<input type="button" onclick="getCheckdVal();" value="Lock" class="btn btn-primary">
		<input type="button" onclick="getUnlock();" value="Unlock" class="btn btn-primary">
		<!-- <input type="submit" value="Unlocked" class="btn btn-primary"> -->
		</div>
		</form>
		 
		<!-- <div class="col-md-12 text-right"><button class="btn btn-primary">Locked</button></div> -->
		
	</div>
	<!-- / panel preview -->
</div>

<jsp:include page="commonJsp/Footer.jsp" />
<script type="text/javascript" src="js/jquery.min.js"></script>

<script>

$(function(){
	
	
	$("#selectall").click(function () {
		  $('.case').attr('checked', this.checked);
		 
	});
	$(".case").click(function(){
		 //alert(
				 $("input[name=students]:checked").map(
			     function () {return this.value;}).get().join(",")  //)
		if($(".case").length == $(".case:checked").length) {
			$("#selectall").attr("checked", "checked");
			
		} else {
			$("#selectall").removeAttr("checked");
		}

	});
	
	
});


function lockedProfile(id){
	//deleteCluster
	$("#lockedProfileId").val(id);
	document.getElementById("deleteDesignation").action = "deleteDesignation";
	document.getElementById("deleteDesignation").method = "post";
	document.getElementById("deleteDesignation").submit();
}

function getpgpStudent()
{
	//alert(document.getElementById("groupid").value);
	document.getElementById("lockedProfile").action = "getpgpStudent";
	document.getElementById("lockedProfile").method = "post";
	document.getElementById("lockedProfile").submit();
	}
	
	function getCheckdVal()
	{
		// action="updateProfileDetail"
		   var numberOfChecked = $('input:checkbox:checked').length;
	       var totalCheckboxes = $('input:checkbox').length;
	       var numberNotChecked = totalCheckboxes - numberOfChecked;
	       //var mentor=$("#mentorId").attr("selectedIndex");
	       //var selected_option = $('#mentorId option:selected');
		   
	      
	       
	       if(numberOfChecked>0){
	    	   //var sel = document.getElementById("mentorId");
	    	  // var text= sel.options[sel.selectedIndex].text;
	    	  // alert("text::"+text);
	    	 
			        document.getElementById("lockedProfile").action = "updateProfileDetail";
				    document.getElementById("lockedProfile").method = "get";
				    document.getElementById("lockedProfile").submit();
	    	   
		   }else{
			   alert("Please choose atleast one!!!");
			   return false;
		   }
	}
	
	function getUnlock()
	{
		// action="updateProfileDetail"
		   var numberOfChecked = $('input:checkbox:checked').length;
	       var totalCheckboxes = $('input:checkbox').length;
	       var numberNotChecked = totalCheckboxes - numberOfChecked;
	       //var mentor=$("#mentorId").attr("selectedIndex");
	       //var selected_option = $('#mentorId option:selected');
		   
	      
	       
	       if(numberOfChecked>0){
	    	   //var sel = document.getElementById("mentorId");
	    	  // var text= sel.options[sel.selectedIndex].text;
	    	  // alert("text::"+text);
	    	 
			        document.getElementById("lockedProfile").action = "unlockDetails";
				    document.getElementById("lockedProfile").method = "get";
				    document.getElementById("lockedProfile").submit();
	    	   
		   }else{
			   alert("Please choose atleast one!!!");
			   return false;
		   }
	}
	
</script>
	
