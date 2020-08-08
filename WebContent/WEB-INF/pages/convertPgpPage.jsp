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
                        
                        <li>PGP1 to PGP2</li>
                    </ul>
                </div>
             
            </div>
        </div>
    <div class="clearfix"></div>
<div class="clearfix"></div>
<div class="container" style="min-height:400px">
  <div class="row"> 
    
    <div class="col-md-6">
    <h2>PGP 1 Student List</h2>
    
     <div class="clearfix"></div>
     <div id="error"></div>
     <form name="studentForm" id="studentFormId">  
     <div class="panel panel-primary">
    <div class="panel-heading"><input type="checkbox" id="selectall"/>  PGP 1 Students</div>
    
    <div class="panel-body" id="showPGP1Student">
	    <c:forEach var="stu" items="${studentList}">
	    <p><input type="checkbox" class="case" name="students" value="${stu.key}" />  ${stu.value}</p>
	    </c:forEach>
    </div>
    
    </div>
 <div class="col-md-12 text-center"> <input type="button" class="btn btn-primary" value="Convert to PGP 2" onclick="convertPgpOneToTwo();"/></div>
    </form>
    
    </div>
      <form name="studentForm" id="studentFormIdPG2">  
    <div class="col-md-6">
        <h2>PGP 2 Student List</h2>
   
     <div class="clearfix"></div>
     <form id="removeStudentId">
     <div class="panel panel-primary">
    <div class="panel-heading"><input type="checkbox" class="case1" id="selectall1"/> PGP 2 Student</div>
  
	   <div class="panel-body" id="showPGP1Student">	
			<c:forEach var="stu" items="${pgp2studentList}">
				<p> <input type="checkbox" class="case1" name="pgp2students"
					value="${stu.key}" required/>  ${stu.value}</p> 
				<%-- <p> ${stu.value} </p> --%>	
			</c:forEach>
		
		</div>
		<div class="space"></div>	
   		<div class="col-md-12 text-center">
  <input type="button" value="Convert to PG1" class="btn btn-primary" onclick="convertPgpTwoToOne();" />
  </div>
  </form>
  </div>
  
   </form>
    </div>
    
  </div>
</div>

<div class="space"></div>

<jsp:include page="commonJsp/Footer.jsp" />
<script type="text/javascript" src="js/jquery.min.js"></script>

<script>

$(function(){

	$("#selectall").click(function () {
		  $('.case').attr('checked', this.checked);
		 
	});
	
	$("#selectall1").click(function () {
		  $('.case1').attr('checked', this.checked);
		 
	});
	$(".case").click(function(){
		 //alert(
				 $("input[name=students]:checked").map(
			     function () {return this.value;}).get().join(",")   //)
		if($(".case").length == $(".case:checked").length) {
			$("#selectall").attr("checked", "checked");
			
		} else {
			$("#selectall").removeAttr("checked");
		}

	});
	
	
});

$(".case").click(function(){
	 //alert(
			 $("input[name=pgp2students]:checked").map(
		     function () {return this.value;}).get().join(",")   //)
	if($(".case").length == $(".case:checked").length) {
		$("#selectall").attr("checked", "checked");
		
	} else {
		$("#selectall").removeAttr("checked");
	}

});



</script>


<script>
      function convertPgpOneToTwo(){	   
	    var numberOfChecked = $('input:checkbox:checked').length;
	    var totalCheckboxes = $('input:checkbox').length;
	    var numberNotChecked = totalCheckboxes - numberOfChecked;	  
	    if(numberOfChecked>0){
		    document.getElementById("studentFormId").action = "convertStudentPgp";
		    document.getElementById("studentFormId").method = "post";
		    document.getElementById("studentFormId").submit();  
	    }else{
	    	alert("select atleast one student!!");	    	
	    	return false;
	    }
	    
	   
    }	

      
      function convertPgpTwoToOne(){	   
  	    var numberOfChecked = $('input:checkbox:checked').length;
  	    var totalCheckboxes = $('input:checkbox').length;
  	    var numberNotChecked = totalCheckboxes - numberOfChecked;	  
  	    if(numberOfChecked>0){
  		    document.getElementById("studentFormIdPG2").action = "convertStudenttoPgp1";
  		    document.getElementById("studentFormIdPG2").method = "post";
  		    document.getElementById("studentFormIdPG2").submit();  
  	    }else{
  	    	alert("select atleast one student!!");	    	
  	    	return false;
  	    }
  	    
  	   
      }	
</script>

</body>
</html>