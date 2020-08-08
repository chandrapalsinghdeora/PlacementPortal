<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ page isELIgnored="false"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Indian Institute of Management Ahmedabad (IIMA) | Home - IIMA</title>
    <link rel="icon" href="images/favicon.ico" type="image/x-icon"/>

<style>  
    .multiselect {
    width:20em;
    height:15em;
    border:solid 1px #c0c0c0;
    overflow:auto;
}
 
.multiselect label {
    display:block;
}
 
.multiselect-on {
    color:#ffffff;
    background-color:#000099;
}
</style>     
    
</head>
<body>

<form name="studentForm" id="studentFormId">
    <div class="multiselect">
    <c:forEach var="stu" items="${studentList}">
    <label><input type="checkbox" name="students" value="${stu.key}" />${stu.value}</label>
    </c:forEach>
    
    
    
    </div>
    
    <input type="button" value="add" onclick="saveStudentId();"/>
 </form>
 
 
 <hr>
 
 <form id="removeStudentId">
  <div class="multiselect">
    <c:forEach var="stu" items="${comStudent}">
    <label><input type="checkbox" name="comstudents" value="${stu.key}" />${stu.value}</label>
    </c:forEach>
   
    </div>
    
    <input type="button" value="Remove" onclick="removeStudentId();"/>
 </form>
 
 <!-- <div id="placeComStuId">
    <input type="checkbox" name="students" value="" id="" /><br/>
 </div> -->
 
 

</body>

<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>

<script>
  function saveStudentId(){
	 /*  alert("hello");
	   var stuIds=document.getElementsByName("students");
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
	  
	    
	    document.getElementById("studentFormId").action = "saveStudent";
	    document.getElementById("studentFormId").method = "post";
	    document.getElementById("studentFormId").submit();  
	    
	    
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
		    document.getElementById("removeStudentId").action = "removeStudent";
		    document.getElementById("removeStudentId").method = "post";
		    document.getElementById("removeStudentId").submit(); 				  
 }
  
  
  
</script>

</html>