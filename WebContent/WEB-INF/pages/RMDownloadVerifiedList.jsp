<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
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
<link rel="stylesheet" type="text/css" href="css/buttons.dataTables.min.css" media="screen" />
<link rel="stylesheet" type="text/css" href="css/jquery.dataTables.min.css" media="screen" />


</head>
<body>

<jsp:include page="commonJsp/Header.jsp" />

<div class="container min-height">
<div class="col-md-12"><h3>Fourth Firm Name - Role Name - Year  </h3></div>

<!-- panel preview -->
<div class="container-fluid btn-container"><b>Note:</b>Varify status is no than this list is show  
<a href="#" class="pull-right" data-toggle="modal" data-target="#rm-status-schedule-msg4"> 
<i class="fa fa-info-circle" aria-hidden="true"></i> </a><label class="pull-right"> [ Process ] : [10 month]  :  </label>

<div class="modal fade" id="rm-status-schedule-msg4" role="dialog">
         <div class="modal-dialog">
    
     
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Info</h4>
        </div>
        <div class="modal-body">
        <div class="row"><h4 class="col-md-12 title">Company</h4></div>
        <hr>
      
        <div class="row"><div class="col-md-6">Cluster : </div><div class="col-md-6">C1</div></div>
          <div class="row"><div class="col-md-6">Cohort :  </div><div class="col-md-6">HR</div></div>
            <hr>
       
        
       <div class="row"><h4 class="col-md-12 title">Application</h4></div>
        <hr>
       
        <div class="row"><div class="col-md-6">No. of Application  </div><div class="col-md-6">10</div></div>
          <div class="row"><div class="col-md-6">Verify Application</div><div class="col-md-6">10</div></div>
           <div class="row"><div class="col-md-6">Unverify Application</div><div class="col-md-6">10</div></div>
           <div class="row"><div class="col-md-6">No. of Rollover  </div><div class="col-md-6">10</div></div>
           
        </div>
         <div class="modal-footer">
        <button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
        </div>
      </div>
      
    </div>
  </div>

</div>

<div class="clearfix"></div>

<table id="main-fourum4" class="table table-bordered display nowrap">
      <thead>
        <tr>
          <th><input type="checkbox" id="selectall"/></th>
          <th>Roll Number</th>
          <th>Email ID</th>
          <th>Name</th>
          <th>CV</th>
          <th>Preference</th>
          <th>Status</th>
         
          
        
        </tr>
      </thead>
      <tbody>
      <c:forEach items="${rmVerifylist}" var="fm" varStatus="loop">
        <tr>
      
          <td><input type="checkbox" class="case"></td>
          <td>${fm.rollnumber}</td>
          <td><a href="#" data-toggle="modal" data-target="#closed-status-email" onclick="getValues('${fm.rollnumber}');">${fm.emailId}</a>
          
          <div class="modal fade" id="closed-status-email" role="dialog">
         <div class="modal-dialog">
    
     
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">${fm.emailId}</h4>
        </div>
        <div class="modal-body">
     
        
        <div class="row"><label class="col-md-6 text-right">Full Name :  </label><div class="col-md-6" id="fullName"></div></div>
        <div class="row"><label class="col-md-6 text-right">CV Name : </label><div class="col-md-6" id="cvName" ></div></div>
        <div class="row"> <label class="col-md-6 text-right">Mentor Name : </label><div class="col-md-6" id="mentorName"></div></div>
          <div class="row"><label class="col-md-6 text-right">Gender :  </label><div class="col-md-6" id="gender"></div></div>
          <div class="row"><label class="col-md-6 text-right">10% : </label><div class="col-md-6" id="10thPer"></div></div>
          <div class="row"><label class="col-md-6 text-right">12% :</label><div class="col-md-6" id="12thPer"></div></div>
          <div class="row"><label class="col-md-6 text-right">Graduation : </label><div class="col-md-6" id="grad"></div></div>
		<div class="row"><label class="col-md-6 text-right">Post Graduate : </label><div class="col-md-6" id="postGrad"></div></div>
        </div>
        <div class="modal-footer">
         
          <button type="button" class="btn btn-primary" data-dismiss="modal">Cancel</button>
        </div>
      </div>
      
    </div>
  </div> 

          
          </td>
                    
          <td>${fm.name}</td>
          <td><a href="#" data-toggle="modal" data-target="#cv-title-popup">${fm.cv}</a>
          
          <div class="modal fade" id="cv-title-popup" role="dialog">
         <div class="modal-dialog modal-sm">
    
     
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">rajnish@gmail.com</h4>
        </div>
        <div class="modal-body">
                <div class="row"><label class="col-md-6 text-right">CV :  </label><div class="col-md-6">CV Title </div></div>
        <div class="row"><label class="col-md-6 text-right">Cover Letter :  </label><div class="col-md-6">Cover Letter Title</div></div>
        
        </div>
         <div class="modal-footer text-center">
         
          <button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
        </div>
      </div>
      
    </div>
  </div>
          </td>
           <td>${fm.rank}</td>
            <td>${fm.status}</td>
         
        </tr>
       
       </c:forEach>
      </tbody>
    </table>
<div class="space"></div>
<div class="col-md-12 text-right"><input type="button" value="Download CV File" class="btn btn-primary"/>
<input type="button" value="Genrate Shortlist link" class="btn btn-primary" data-toggle="modal" data-target="#shortlist-link-popup" />


<div class="modal fade" id="shortlist-link-popup" role="dialog">
         <div class="modal-dialog">
    
     
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title text-left">List Send to Firm</h4>
        </div>
        <div class="modal-body">
                <div class="row"><label class="col-md-6 text-right">Firm HR Email Id :  </label><div class="col-md-6">
                <input type="text" class="form-control"> </div></div>
        <div class="row"><div class="col-md-12 text-left"><input type="checkbox" /> Send Prefrence</div></div>
         <div class="row"><div class="col-md-12 text-left"><textarea rows="3" class="form-control"></textarea></div></div>
        
        </div>
         <div class="modal-footer text-center">
         
          <button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
        </div>
      </div>
      
    </div>
  </div>

</div>

</div>
<jsp:include page="commonJsp/Footer.jsp" />

<script type="text/javascript" src="js/jquery.dataTables.min.js"></script> 
<script type="text/javascript" src="js/dataTables.buttons.min.js"></script>
<script type="text/javascript" src="js/buttons.flash.min.js"></script> 
<script type="text/javascript" src="js/jszip.min.js"></script>
<script type="text/javascript" src="js/pdfmake.min.js"></script> 
<script type="text/javascript" src="js/vfs_fonts.js"></script>
<script type="text/javascript" src="js/buttons.html5.min.js"></script> 
<script type="text/javascript" src="js/buttons.print.min.js"></script> 
<script type="text/javascript" src="js/bootstrap.js"></script>


    

  
<script>
 $(document).ready(function() {
	 $('#main-fourum4').DataTable( {
        dom: 'Bfrtip',
        buttons: [
            'excel', 'pdf', 'print'
        ]
    } );
} );
     
 $(function(){

		// add multiple select / deselect functionality
		$("#selectall").click(function () {
			  $('.case').attr('checked', this.checked);
			 
		});

		// if all checkbox are selected, check the selectall checkbox
		// and viceversa
		$(".case").click(function(){
			 $("input[name=case]:checked").map(
				     function () {return this.value;}).get().join(",")
			if($(".case").length == $(".case:checked").length) {
				$("#selectall").attr("checked", "checked");
				
			} else {
				$("#selectall").removeAttr("checked");
			}

		});
	});

 
 function getValues(rollNumber){	
     var xhttp = new XMLHttpRequest();
	 xhttp.onreadystatechange = function() {
	 if (this.readyState == 4 && this.status == 200) {  	    
	     var i;
	     var obj = JSON.parse(this.responseText);
	     document.getElementById("fullName").innerHTML=obj[0].fullname; 
	     document.getElementById("cvName").innerHTML=obj[0].cvName;
	     document.getElementById("mentorName").innerHTML=obj[0].mentor; 
	     document.getElementById("gender").innerHTML=obj[0].gender; 
	     document.getElementById("10thPer").innerHTML=obj[0].percentage; 
	     document.getElementById("12thPer").innerHTML=obj[0].percenatageTwelve; 
	     document.getElementById("grad").innerHTML=obj[0].percentageGraduate; 
	     document.getElementById("postGrad").innerHTML=obj[0].postgraduatePercentage; 
	     
	 }
	 };
	 var action="getUserDetails?rollNumber="+rollNumber;
	 xhttp.open("POST", action, true);
	 xhttp.send(); 		
  }
 
	 
       </script> 


</body>
</html>