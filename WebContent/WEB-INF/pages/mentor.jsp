<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page isELIgnored="false"%>

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
<link rel="stylesheet" type="text/css" href="css/style.css"
	media="screen" />
<link rel="stylesheet" type="text/css" href="assets/css/ace.min.css"
	media="screen" />
<link rel="stylesheet" type="text/css"
	href="css/dataTables.bootstrap.min.css" media="screen" />
</head>
<body>
	<jsp:include page="commonJsp/Header.jsp" />

	<div class="clearfix"></div>
	<div class="breadcumb-wrapper">
		<div class="clearfix">
			<div class="pull-left">
				<ul class="list-inline link-list">
					<li><a href="forumHomePage">Home</a></li>

					<li>Mentor</li>
				</ul>
			</div>

		</div>
	</div>
	<div class="clearfix"></div>
	<div class="clearfix"></div>
	<div class="container" style="min-height: 400px">
		<div class="row">

			<div class="col-md-6">
				<h2>Mentee Assign to Mentor</h2>
				<%-- <div class="col-md-12">
    <label class="control-label">Group</label>
     <select class="form-group" name="prefix" onchange="getStudentByPrefix();" id="selectPrefixId">
     <option value="">select</option>
     <c:forEach var="prefix" items="${prefix}">     
      <option value="${prefix.key}">
      <c:if test="${prefix.key eq 1}">PGP1</c:if>
      <c:if test="${prefix.key eq 2}">PGP2</c:if>
       <c:if test="${prefix.key eq 3}">Wild Card</c:if>
      </option>
     </c:forEach>
    </select>
    </div> --%>

				<div class="clearfix"></div>
				<form name="studentForm" id="studentFormId">
					<div>
						<!-- <div class="panel-heading"><input type="checkbox" id="selectall"/>  Unassigned IIM Student</div>  -->
						<div id="showStudent">
							<table id="table-one" class="table" >
								<thead>
									<tr>
										<th>Unassigned IIM Student</th>
									</tr>
								</thead>
								<tbody class="nnn">

									<c:forEach var="stu" items="${allstudentList}">
										<tr>
											<td style="border-top: none; padding: 2px;">
												<p>
													<input type="checkbox" class="case" name="studentIds"
														value="${stu.key}" />${stu.value}</p>
											</td>
										</tr>
									</c:forEach>

								</tbody>
							</table>
						</div>

					</div>

					<div class="panel-body">
						<label class="control-label">Mentor</label> <select
							class="form-group" name="mentorId" id="mentorId"
							required="required">
							<option value="">select</option>
							<c:forEach var="ment" items="${mentor}">
								<option value="${ment.key}">${ment.value}</option>
							</c:forEach>
						</select>
					</div>


					<div class="col-md-12 text-center">
						<input type="button" class="btn btn-primary" value="Add"
							onclick="saveMentorStudent();" />
					</div>

				</form>

			</div>
			<div class="col-md-6">
				<h2>Mentor remove to mentee</h2>
				<div class="col-md-12">
					<label class="control-label">Mentor</label> <select
						class="form-group" name="prefix"
						onchange="getStudentByMentorId();" id="studentByMentorId">
						<option value="">select</option>
						<c:forEach var="mentor" items="${mentor}">
							<option value="${mentor.key}">${mentor.value}</option>
						</c:forEach>
					</select>

				</div>

				<div class="clearfix"></div>
				<form id="removeStudentId">
					<div>
						<!-- <div class="panel-heading">
							<input type="checkbox" class="case1" id="selectall1"/>
							Assigned IIM Student
						</div> -->

						<div id="showByPrefix">
						
						<table id="table-two" class="table" >
					     <thead>
							<tr><th>Assigned IIM Student</th></tr>
						 </thead>
						<tbody>	
						<c:forEach var="stu" items="${allMentroStudent}">
						<tr>
						 <td style="border-top: none; padding: 2px;">
						    <p>	<input type="checkbox" class="case1" name="studentIds"
										value="${stu.key}" />${stu.value}</p>
						 </td>
						</tr>
					   </c:forEach> 								
						</tbody>
					   </table>
						
												
							<%-- <c:forEach var="stu" items="${allMentroStudent}">
								<p>
									<input type="checkbox" class="case1" name="studentIds"
										value="${stu.key}" />${stu.value}</p>
							</c:forEach> --%>

						</div>

					</div>
					<div class="col-md-12 text-center">
						<input type="button" value="Remove" class="btn btn-primary"
							onclick="removeMentorStudent();" />
					</div>
				</form>
			</div>

		</div>
	</div>


	<div class="space"></div>
	<jsp:include page="commonJsp/Footer.jsp" />


	<script>

$(function(){

	// add multiple select / deselect functionality
	$("#selectall").click(function () {
		  $('.case').attr('checked', this.checked);
		 
	});
	$(".case").click(function(){
		/*  alert( */
				$("input[name=case]:checked").map(
			     function () {return this.value;}).get().join(",")
			     /* ) */
		if($(".case").length == $(".case:checked").length) {
			$("#selectall").attr("checked", "checked");
			
		} else {
			$("#selectall").removeAttr("checked");
		}

	});
	
	$("#selectall1").click(function () {
		  $('.case1').attr('checked', this.checked);
		 
	});
	$(".case1").click(function(){
		/*  alert( */
				$("input[name=case1]:checked").map(
			     function () {return this.value;}).get().join(",")
			     /* ) */
		if($(".case1").length == $(".case1:checked").length) {
			$("#selectall1").attr("checked", "checked");
			
		} else {
			$("#selectall1").removeAttr("checked");
		}

	});

	// if all checkbox are selected, check the selectall checkbox
	// and viceversa
	
});
</script>






	<script>
   function saveMentorStudent(){
	 //alert("saved");
	 
	   var numberOfChecked = $('input:checkbox:checked').length;
       var totalCheckboxes = $('input:checkbox').length;
       var numberNotChecked = totalCheckboxes - numberOfChecked;
       var mentor=$("#mentorId").attr("selectedIndex");
       var selected_option = $('#mentorId option:selected');
	   
      
       
       if(numberOfChecked>0){
    	   var sel = document.getElementById("mentorId");
    	   var text= sel.options[sel.selectedIndex].text;
    	  // alert("text::"+text);
    	   if(text=='select'){
    		   alert("no mentor is selected");
    	   }
    	   else{
		        document.getElementById("studentFormId").action = "saveMentorStudent";
			    document.getElementById("studentFormId").method = "post";
			    document.getElementById("studentFormId").submit();
    	   }
	   }else{
		   alert("Please choose atleast one!!!");
		   return false;
	   }
	   
    }

	function removeMentorStudent(){	
	    var numberOfChecked = $('input:checkbox:checked').length;	   
	    if(numberOfChecked>0){
	    document.getElementById("removeStudentId").action = "removeMentorStudent";
	    document.getElementById("removeStudentId").method = "post";
	    document.getElementById("removeStudentId").submit(); 
	    }else{
	    	alert("Please select atleast one mentor!!!");
	    }
	}
	
	function getStudentByPrefix(){		
		var select = document.getElementById("selectPrefixId");
		var prefixId = select.options[select.selectedIndex].value;
		//alert("selected--"+prefixId);
		var xhttp = new XMLHttpRequest();
		 xhttp.onreadystatechange = function() {
		 if (this.readyState == 4 && this.status == 200) {
			 var i;
		     var obj = JSON.parse(this.responseText);
		    
		     var stu="";
		      for(i=0; i < obj.length; i++){
		         //alert("obj name- "+obj[i].STUName+"id -"+obj[i].STUID);  		         
		        stu+= "<p><input type='checkbox' class='case' name='studentIds' value="+obj[i].STUID+ ">  "+obj[i].STUName+"  </p> "
		     }  		    
		     document.getElementById("showStudent").innerHTML= stu;      
		     this.responseText;
		 }
		 };
		 var action="getMentorStudentListByPrefixId?prefixId="+prefixId;
		 xhttp.open("POST", action, true);
		 xhttp.send();  
		
	}
	
	function getStudentByMentorId(){		
		var select = document.getElementById("studentByMentorId");
		var mentorId = select.options[select.selectedIndex].value;
		//alert("mentorId--"+mentorId);
		 var xhttp = new XMLHttpRequest();
		 xhttp.onreadystatechange = function() {
		 if (this.readyState == 4 && this.status == 200) {
			 var i;
		     var obj = JSON.parse(this.responseText);
		    
		     var stu="";
		       for(i=0; i < obj.length; i++){
		    	   
		    	  // <tr><td style="border-top: none; padding: 2px;">
		         //alert("obj age "+obj[i].studentName);   		         
		    	  // stu+= "<p><input type='checkbox' class='case' name='studentIds' value="+obj[i].studentId+ ">  "+obj[i].studentName+"  </p> "
		    	   stu+= "<tr role='row' class='odd'><td style='border-top: none; padding: 2px;' class='sorting_1'><p><input type='checkbox' class='case1' name='studentIds' value="+obj[i].studentId+ ">  "+obj[i].studentName+" </p> </td></tr> "
		       }  		   
		    // document.getElementById("showByPrefix").innerHTML= stu;   
		     document.getElementById("table-two").innerHTML=stu;
		     this.responseText;
		 }
		 };
		 var action="getStudentByMentorId?mentorId="+mentorId;
		 xhttp.open("POST", action, true);
		 xhttp.send();  
		
	}
	

</script>

	<script>
       
	  $(document).ready(function() {
    $('#table-one').DataTable( {
    	
     "bFilter": true,
 	 "bInfo": false,
 	 "scrollY":        "271px",
     "scrollCollapse": true,
     "paging":         false,
		
        dom: 'Bfrtip',
        buttons: [
           
        ]
    } );
} );
 
	  $(document).ready(function() {
		    $('#table-two').DataTable( {
		    	
		     "bFilter": true,
		 	 "bInfo": false,
		 	 "scrollY":        "200px",
		     "scrollCollapse": true,
		     "paging":         false,
				
		        dom: 'Bfrtip',
		        buttons: [
		           
		        ]
		    } );
		} );
		   
	 
       </script>



</body>
</html>