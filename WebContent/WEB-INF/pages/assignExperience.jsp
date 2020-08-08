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
                        
                        <li>Assign Experience</li>
                    </ul>
                </div>
             
            </div>
        </div>
    <div class="clearfix"></div>
<div class="clearfix"></div>
<div class="container" style="min-height:400px">
  <div class="row"> 
   <form id="assignExpFormId" name="assignExpForm">
   <input type="hidden"  name="pgpId" id="pgpId"> 
    <div class="col-md-6">
    <h4>Assign Experience</h4>    
     <div class="clearfix"> </div>   
     <div class="panel panel-primary">     
     <%--  <c:if test="${token eq 0}"> 
       <select class="form-group" id="selectGroupId" name="groupId" >
        <option value="">select</option>     
        <option value="1"> PGP1</option>
        <option value="2"> PGP2 </option>    
       </select>
    </c:if> --%>
    
       <select class="form-group" id="selectGroupId" name="groupId" onchange="getExperience(this);" >
        <option value="0">select</option> 
        
        <c:if test="${PGP eq 0}"> 
        <option value="1"> PGP1</option>
        <option value="2"> PGP2</option>
        </c:if> 
        <c:if test="${empty PGP}">  
        <option value="1"> PGP1</option>
        <option value="2"> PGP2</option>
       </c:if>
        <c:if test="${PGP eq 1}">    
        <option value="1" selected> PGP1</option>
         <option value="2"> PGP2</option>
        </c:if>
         <c:if test="${PGP eq 2}"> 
         <option value="1"> PGP1</option>
        <option value="2" selected> PGP2 </option> 
        </c:if>   
       </select>
    </div>
    
   
    <c:if test="${not empty Experince[0].groupId}">
    <c:if test="${Experince[0].interviewExperience eq true}">
    <p><input type="checkbox" class="case" name="interviewExperience"  checked/>  Interview Experience</p>
    </c:if> 
     <c:if test="${Experince[0].interviewExperience eq false}">
    <p><input type="checkbox" class="case" name="interviewExperience"  />  Interview Experience</p>
    </c:if> 
     <c:if test="${Experince[0].internshipExperience eq true}">
    <p><input type="checkbox" class="case" name="internshipExperience"  checked/>  Internship Experience</p>
     </c:if>
     <c:if test="${Experince[0].internshipExperience eq false}">
    <p><input type="checkbox" class="case" name="internshipExperience"  />  Internship Experience</p>
     </c:if> 
    </c:if> 
    <c:if test="${empty Experince[0].groupId}">
    <p><input type="checkbox" class="case" name="interviewExperience"  />  Interview Experience</p>
     <p><input type="checkbox" class="case" name="internshipExperience"  />  Internship Experience</p>
    </c:if>
      <div class="col-md-12 text-center"> <input type="button" class="btn btn-primary" value="Assign Experience" onclick="assignExperience();"/></div>
   
    </div>
   </form>
  </div>
</div>

<div class="space"></div>

<jsp:include page="commonJsp/Footer.jsp" />
<script type="text/javascript" src="js/jquery.min.js"></script>


<script>
      function assignExperience(){
    	if($("#selectGroupId option:selected").val()==0){
    		alert("Please select one group!!");
    		return false;
    	}  
	    var numberOfChecked = $('input:checkbox:checked').length;
	    var totalCheckboxes = $('input:checkbox').length;
	    var numberNotChecked = totalCheckboxes - numberOfChecked;	  
	    if(numberOfChecked>0){
	    	document.getElementById("assignExpFormId").action = "saveAssignExperience";
		    document.getElementById("assignExpFormId").method = "post";
		    document.getElementById("assignExpFormId").submit(); 
	    }else{
	    	alert("select atleast one Experience!!");	    	
	    	return false;
	    } 
	    
	   
    }	
      
    function getExperience(pgpId){
    	//alert(pgpId.value);
    	var pgp=document.getElementById("pgpId");
    	pgp=pgpId.value;    	
    	document.getElementById("assignExpFormId").action = "getExperienceByPGP?pgpId="+pgp;
 	    document.getElementById("assignExpFormId").method = "post";
 	    document.getElementById("assignExpFormId").submit();
    	
    }

</script>

</body>
</html>