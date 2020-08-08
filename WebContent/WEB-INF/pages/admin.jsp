<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
</head>
<body>
<jsp:include page="commonJsp/Header.jsp" />

<div class="clearfix"></div>
<div class="breadcumb-wrapper">
            <div class="clearfix">
                <div class="pull-left">
                    <ul class="list-inline link-list">
                        <li><a href="forumHomePage">Home</a></li>
                        
                        <li>Team</li>
                    </ul>
                </div>
             
            </div>
        </div>
    <div class="clearfix"></div>
<div class="clearfix"></div>
<div class="container" style="min-height:400px">
  <div class="row"> 
    
    <div class="col-md-6">
    <h2>Member Assign to Placement Team</h2>
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
     <div id="error"></div>
     <form name="studentForm"  class="km-admin" id="studentFormId">  
     <div class="panel panel-primary">
    <div class="panel-heading"><!-- <input type="checkbox" id="selectall"/> -->  IIM Student</div>
    
    <div class="panel-body" id="showStudent">
    
	    <c:forEach var="stu" items="${studentList}">
	    <p><input type="checkbox" class="case" name="students" value="${stu.key}" />  ${stu.value}</p>
	    </c:forEach>
    
        
      
    </div>
    
    </div>
 <div class="col-md-12 text-center"> <input type="button" class="btn btn-primary" value="Add" onclick="saveStudentId();"/></div>
    </form>
    
    </div>
    <div class="col-md-6">
        <h2>Placement Team</h2>
    <%-- <div class="col-md-12" >
    <label class="control-label" >Group</label>
   <select class="form-group" name="prefix" onchange="getPlaceComStudentByPrefix();" id="selectRemovePrefixId">
   <option value="">select</option>
     <c:forEach var="prefix" items="${prefix}">
      <option value="${prefix.key}">
      <c:if test="${prefix.key eq 1}">PG1</c:if>
      <c:if test="${prefix.key eq 2}">PG2</c:if>
      <c:if test="${prefix.key eq 3}">Wild Card</c:if>
      </option>
     </c:forEach>
    </select>
    
    </div>
     --%>
     <div class="clearfix"></div>
     <form id="removeStudentId" class="km-admin">
     <div class="panel panel-primary">
    <div class="panel-heading"><!--  <input type="checkbox" class="case1" id="selectall1"/> -->Placecommer</div>
  


	
	   <div class="panel-body" id="showByPrefix">	
			<c:forEach var="stu" items="${comStudent}">
				<p><input type="checkbox" class="case1" name="comstudents"
					value="${stu.key}" required/>  ${stu.value}</p>
			</c:forEach>
		
		</div>	
   
  </div>
  <div class="col-md-12 text-center">
  <input type="button" value="Remove" class="btn btn-primary" onclick="removeStudentId();" />
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
   function saveStudentId(){
	  // alert("hello");
	   /* var stuIds=document.getElementsByName("students");
	    var i;
	    var txt=[];
	    for (i = 0; i < stuIds.length; i++) {
	      
	        if (stuIds[i].checked) {	        	
	            if(txt==''){	            	
	            	 txt[txt.length]=stuIds[i].value;	            	 
	            }else{	            	
	            	 txt[txt.length]=stuIds[i].value;
	            }
	        }
	    }
	    alert(txt); */
	   
	    var numberOfChecked = $('input:checkbox:checked').length;
	    var totalCheckboxes = $('input:checkbox').length;
	    var numberNotChecked = totalCheckboxes - numberOfChecked;
	   // alert("numberOfChecked"+numberOfChecked);
	    if(numberOfChecked>0){
	    document.getElementById("studentFormId").action = "saveStudent";
	    document.getElementById("studentFormId").method = "post";
	    document.getElementById("studentFormId").submit();  
	    }else{
	    	alert("select atleast one mentor!!");
	    	//document.getElementById("error").innerHTML="<font color='red'>select at least one mentor!!</font>";
	    	return false;
	    }
	    
	    // alert("after form");
	    
	   /*  var xhttp = new XMLHttpRequest();
		 xhttp.onreadystatechange = function() {
		 if (this.readyState == 4 && this.status == 200) {
			 var i;
		     var obj = JSON.parse(this.responseText);
		     var studentList='';		     
		      for(i=0; i < obj.length; i++){
		         //alert("obj age "+obj[i].placeComStudentName);	          
		         studentList+=obj[i].placeComStudentName;
		    
		     }  		    
		     document.getElementById("placeComStuId").innerHTML= studentList;		     		   
		     this.responseText;
		 }
		 };
		 var action="saveStudent?studentIds="+txt;
		 xhttp.open("POST", action, true);
		 xhttp.send();  */
	  
    }

	function removeStudentId(){		
		var numberOfChecked = $('input:checkbox:checked').length;
		if(numberOfChecked>0){
	    document.getElementById("removeStudentId").action = "removeStudent";
	    document.getElementById("removeStudentId").method = "post";
	    document.getElementById("removeStudentId").submit(); 
		 }
		 else{
			 alert("select atleast one mentor!!");
			return false; 
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
		         //alert("obj age "+obj[i].studentName);   
		         
		        stu+= "<p><input type='checkbox' class='case' name='students' value="+obj[i].prefixId+ ">   "+obj[i].studentName+"  </p> "
		     }  		    
		     document.getElementById("showStudent").innerHTML= stu;  //+ " <input type='button' value='add' onclick=saveStudentId()>"		     		   
		     this.responseText;
		 }
		 };
		 var action="getStudentByPrefixId?prefixId="+prefixId;
		 xhttp.open("POST", action, true);
		 xhttp.send();  
		
	}
	
	function getPlaceComStudentByPrefix(){		
		var select = document.getElementById("selectRemovePrefixId");
		var prefixId = select.options[select.selectedIndex].value;
		//alert("selected--"+prefixId);
		 var xhttp = new XMLHttpRequest();
		 xhttp.onreadystatechange = function() {
		 if (this.readyState == 4 && this.status == 200) {
			 var i;
		     var obj = JSON.parse(this.responseText);
		    
		     var stu="";
		      for(i=0; i < obj.length; i++){
		         //alert("obj age "+obj[i].studentName);   
		         
		        stu+= "<p><input type='checkbox' class='case1' name='comstudents' value="+obj[i].prefixId+ ">   "+obj[i].studentName+"</p> "
		     }  		    
		     document.getElementById("showByPrefix").innerHTML= stu ; //+ " <input type='button' value='Remove' onclick=removeStudentId()>"		     		   
		     this.responseText;
		 }
		 };
		 var action="getPlaceComStudentByPrefixId?prefixId="+prefixId;
		 xhttp.open("POST", action, true);
		 xhttp.send();  
		
	}
	

</script>

</body>
</html>