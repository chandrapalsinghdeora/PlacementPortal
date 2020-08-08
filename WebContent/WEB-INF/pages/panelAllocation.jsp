<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
<link rel="stylesheet" type="text/css" href="css/style.css" media="screen" />
<link rel="stylesheet" type="text/css" href="css/dataTables.bootstrap.min.css" media="screen" />
<link rel="stylesheet" type="text/css" href="css/buttons.dataTables.min.css" media="screen" />
<link rel="stylesheet" type="text/css" href="css/jquery.dataTables.min.css" media="screen" />
<link rel="stylesheet" type="text/css" href="assets/css/bootstrap-datetimepicker.min.css" media="screen" />
<link rel="stylesheet" type="text/css" href="assets/css/bootstrap.min.css" media="screen" />

 <style>
.panel_allocation{ border:none !important; border-bottom: 1px solid #ddd !important;}
.panel_allocation li{ display:inline-table !important;}
.panel_allocation_txt p{ line-height:35px; margin-bottom:0px;}
</style>

</head>
<body>
<jsp:include page="commonJsp/Header.jsp" />

<div class="breadcumb-wrapper">
  <div class="clearfix">
    <div class="pull-left">
      <ul class="list-inline link-list">
        <li><a href="#">Home</a></li>
        <li><a href="#">Firm Managment</a></li>
        <li><a href="#">Manage Application</a></li>
        <li>Closed Status</li>
      </ul>
    </div>
  </div>
</div>
<!-- <div class="space"></div> -->
<div class="closed-status">
  <aside class="col-md-12">
    <nav class="nav-sidebar panel_allocation">
      <ul class="nav tabs">
        <li class="active"><a href="#tab_six" data-toggle="tab">Individual Firms</a></li>
        <li class=""><a href="#tab_seven" data-toggle="tab">Summary</a></li>
      </ul>
    </nav>
  </aside>
  <form name="panelAllocationForm" id="panelAllocationFormId">
  <input type="hidden" value="0" name="panelAllocationId" id="panelAllocationId">
  <div class="col-lg-12">
    <div class="tab-content panel_allocation_txt">
      <div class="tab-pane active text-style" id="tab_six">
        <div>
          <div class="col-md-6 col-md-offset-3">
            <div class="step-content pos-rel">
              <div class="step-pane active" data-step="1">
                <div class="main_box section_one panel panel-primary">
                  <div class="panel-heading"> Panel Allocation </div>
                  <div class="panel-body">
                  
                  <div class="row">
                      <div class="  border">
                        <div class="col-sm-3">
                          <p>Process</p>
                        </div>
                        <div class="col-sm-9 inpur_box">
                          <select name="processId" class="form-control" id="form-field-select-2" onchange="getClusterName(this);">
                           <option value="0">Select</option>
                          <c:forEach var="clist" items="${processList}">
                          <option value="${clist.processId}">${clist.processName} </option>
                          </c:forEach>                          
                          </select>
                       <!--    <input type="hidden" name="processName" value="" id="processNameId"> -->
                        </div>
                      </div>
                    </div>
                  
                  
                    <div class="row">
                      <div class="  border">
                        <div class="col-sm-3">
                          <p>Cluster</p>
                        </div>
                        <div class="col-sm-9 inpur_box">
                          <select name="clusterId" class="form-control" id="form-field-select-1" onchange="getFirmName(this);">
                           <option value="0">Select</option>
                          <%-- <c:forEach var="clist" items="${clusterList}">
                          <option value="${clist.clusterId}">${clist.clusterName} </option>
                          </c:forEach>    --%>                       
                          </select>
                          <input type="hidden" name="clusterName" value="" id="clusterNameId">
                        </div>
                      </div>
                    </div>
                    
                    <div class="row">
                      <div class="  border">
                        <div class="col-sm-3">
                          <p>Firm</p>
                        </div>
                        <div class="col-sm-9 inpur_box">
                          <select name="firmId" class="form-control" id="firmSelectBoxId" required onchange="getRMNameByFirmId(this);getPanelDeatilsByAppId(this);getRoleName(this);getRoleInDD(this)"> 
                           <!--  <option value="0">Select</option>  -->                           
                          </select>
                          <input type="hidden" name="firmName" value="" id="firmNameId">
                        </div>
                      </div>
                    </div>
                    
                      <div class="row">
                      <div class="  border">
                        <div class="col-sm-3">
                          <p>Role</p>
                        </div>
                        <div class="col-sm-9 inpur_box">
                          <select name="roleId" class="form-control" id="roleSelectBoxId" > 
                           <!--  <option value="0">Select</option>  -->                           
                          </select>
                          <input type="hidden" name="roleName" value="" id="roleNameId">
                        </div>
                      </div>
                    </div>

                    <br>

                    <b>Details</b>
                    
                    <div class="space"></div>
                    
                    <div class="row">
                      <div class="  border">
                        <div class="col-sm-3">
                          <p>RM Name</p>
                        </div>
                        <div class="col-sm-9 inpur_box">
                          <input name="rmName" type="text" class="form-control" placeholder="Dynamic update" id="rmId">
                           <input name="emailId" type="hidden" id="emailId">
                        </div>
                      </div>
                    </div>
                    
                    
                    <div class="row">
                      <div class="  border">
                        <div class="col-sm-3">
                          <p>Date</p>
                        </div>
                        <div class="col-sm-9 inpur_box">
                          <div class="input-group">
                            <input class="form-control date-picker" id="id-date-picker-1" data-date-format="dd-mm-yyyy" type="text" name="panelDate" required>
                            <span class="input-group-addon"> <i class="fa fa-calendar bigger-110"></i> </span> </div>
                        </div>
                      </div>
                    </div>
                    
                    <div class="row">
                      <div class="  border">
                        <div class="col-sm-3">
                          <p>Start Time</p>
                        </div>
                        <div class="col-sm-3 inpur_box">
                        <!-- <input type="text" class="form-control" placeholder="Start time" name="panelStartTime"> -->
                        
			               <div class="form-group">
			                <div class='input-group date' id='datetimepicker3'>
			                    <input type='text' class="form-control" name="panelStartTime" id="panelStartTimeId"/>
			                    <span class="input-group-addon">
			                        <span class="glyphicon glyphicon-time"></span>
			                    </span>
			                </div>
			              </div>  			                                 
                        </div>
                                           
                        <div class="col-sm-3">
                          <p>End Time</p>
                        </div>
                        <div class="col-sm-3 inpur_box">
                        <!-- <input type="text" class="form-control" placeholder="End time" name="panelEndTime"> -->
                         <div class="form-group">
			                <div class='input-group date' id='datetimepicker4'>
			                    <input type='text' class="form-control" name="panelEndTime" id="panelEndTimeId"/>
			                    <span class="input-group-addon">
			                        <span class="glyphicon glyphicon-time"></span>
			                    </span>
			                </div>
			            </div>
                        
                        </div>                        
                      </div>
                    </div>
                    
                    
                    <div class="row">
                      <div class="  border">
                        <div class="col-sm-3">
                          <p>Panel</p>
                        </div>
                        <div class="col-sm-9 inpur_box">
                        <input type="text" class="form-control" placeholder="Panel" name="panel" id="panelId">
                        </div>
                      </div>
                    </div>
                    
                    <div class="row">
                      <div class="  border">
                        <div class="col-sm-3">
                          <p>Extra Room</p>
                        </div>
                        <div class="col-sm-9 inpur_box">
                        <input type="text" class="form-control" placeholder="Panel" name="extraRooms" id="extraRoomsId">
                        </div>
                      </div>
                    </div>
                    
                    
                     <div class="row">
                      <div class="  border">
                        <div class="col-sm-3">
                          <p>Mapped Role</p>
                        </div>
                        <div class="col-sm-9 inpur_box">
                          <select name="mappedRoleId" class="form-control" id="mapRoleId" > 
                           <!--  <option value="0">Select</option>  -->                           
                          </select>
                           <input type="hidden" name="mapRoleName" value="" id="maproleNameId"> 
                        </div>
                      </div>
                    </div>
                    

                    <br>

                    <div class="form-group col-md-12 text-center">
                     
                    <!--  <input type="submit" value="Update RM" class="btn btn-primary"> -->
                     <input type="button" value="Update RM" class="btn btn-primary" onclick="submitPanelAllocationForm();" id="updateButtonId">
                    <!--  <input type="button" value="Update RM" class="btn btn-primary" id="updateButtonId" disabled> -->
                     
                     </div>
					
                    <br>


					<div class="row">
                      <div class="  border">
                        <div class="col-sm-3">
                          <p>Notification Status</p>
                        </div>
                        <div class="col-sm-9 inpur_box">
	                        <input type="text" class="form-control" placeholder="Dynamic Notificaiotn update" name="notificationStatus" id="notificationId"><br>
                            <input type="checkbox" name="isLocked"> Locked  <!-- name="isLocked" -->
                            
                        </div>
                      </div>
                    </div>

                  
                  </div>
                </div>
              </div>
            </div>
          </div>
          
          <!-- panel preview -->
          
          <div class="space"></div>
        </div>
      </div>
      
      
      <div class="tab-pane text-style" id="tab_seven">
        <div>
        
				<div class="row">
                      <div class="col-sm-12 border">
                        <div class="col-sm-1">
                          <p>Cluster</p>
                        </div>
                        <div class="col-sm-3 inpur_box">                         
                          <select name="clusterId" class="form-control" id="form-field-select-1" onchange="getFirmList(this);">
                           <option value="0">Select</option>
                          <c:forEach var="clist" items="${clusterList}">
                          <option value="${clist.clusterId}">${clist.clusterName}</option>
                          </c:forEach>
                          </select>
                          
                        </div>
                      </div>
                    </div>        
        
        
          
          <!-- panel preview -->
          
          <div class="clearfix"></div>
          <div class="col-sm-12">
          <table id="main-fourum1" class="table table-bordered display nowrap">
            <thead>
              <tr>
                <th>Name of Firm</th>
                <th>Notificaiton Status</th>
                <th>Locked</th>
               <!--  <th>Action</th> -->
              </tr>
            </thead>
            <tbody id="firmListId">
              <c:forEach var="panel" items="${firmList}">
              <tr>
                <td>${panel.firmName}</td>
                <td>${panel.notificationStatus}</td>               
		    	<c:if test="${panel.isLocked eq true}">
                <td> Locked </td>
                </c:if>
                 <c:if test="${panel.isLocked eq false}">
                <td> Open</td>
                </c:if>               
              </tr>
              </c:forEach>
              
            </tbody>
          </table>
          </div>
          
          <div class="space"></div>
          <!-- <div class="col-md-12">
            <input type="button" value="varify" class="btn btn-primary pull-right"/>
          </div> -->
          <div class="space"></div>
        </div>
      </div>
    </div>
  </div>
  </form>
  <!-- panel preview --> 
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
<!-- <script type="text/javascript" src="js/bootstrap.js"></script> --> 

<!--For datepicker --> 
<!--For Tiem picker only --> 
<script src="assets/js/bootstrap-datepicker.min.js"></script>
<script src="assets/js/bootstrap-timepicker.min.js"></script>
<script src="assets/js/moment.min.js"></script>
<script src="assets/js/bootstrap-datetimepicker.min.js"></script>

<!-- ace scripts -->

<script src="assets/js/ace.min.js"></script>

<script type="text/javascript">
            $(function () {
                $('#datetimepicker3').datetimepicker({
                	format: 'HH:mm'
                });
            });
			
			$(function () {
                $('#datetimepicker4').datetimepicker({
                	format: 'HH:mm'
                });
            });
 </script>   
 


<script type="text/javascript">

	$('.form_time').datetimepicker({
        language:  'fr',
        weekStart: 1,
        todayBtn:  1,
		autoclose: 1,
		todayHighlight: 1,
		startView: 1,
		minView: 0,
		maxView: 1,
		forceParse: 0
    });
</script>
<!--For datepicker -->

<script type="text/javascript">
			jQuery(function($) {
				
				
			
			$('#timepicker1').timepicker({
				
				 showOn: "button",
    showSecond: true,
    dateFormat: "dd-mm-yy", 
    timeFormat: "HH:mm:ss",
				// format: 'HH:mm',
      //  pickDate: false,
//        pickSeconds: false,
    //  pick12HourFormat: true  ,
					//minuteStep: 1,
			//	showSeconds: true,
				//	showMeridian: false,
			//	disableFocus: true,
				 use24hours: true,
       // format: 'HH:mm',
					icons: {
						up: 'fa fa-chevron-up',
						down: 'fa fa-chevron-down'
					}
				}).on('focus', function() {
					$('#timepicker1').timepicker('showWidget');
				}).next().on(ace.click_event, function(){
					$(this).prev().focus();
				});
				
				
				
				$('.date-picker').datepicker({
					autoclose: true,
					todayHighlight: true
				})
				//show datepicker when clicking on the icon
				.next().on(ace.click_event, function(){
					$(this).prev().focus();
				});
			
			});
		</script>




        
<script>

  $(document).ready(function() {
	
	  var x=1;  
    $("#add-panel").click(function() {
		
      var wrapper=$('#internships');
	  var data=$('#internshipbox').html();
	 
		if(x<11)
		{
        var domElement = $('<div class="main_box section_one panel panel-primary"><div class="panel-heading"><div class="row"><div class="col-sm-3">Summer Internship '+x+'</div><div class="col-sm-4 pull-right text-right"><input type="checkbox">  Approve  <input type="checkbox"> Lock</div></div></div>'+data+'</div></div> ');
		
		
		
        $(wrapper).append(domElement);
		x++;
		}
				
    });
	});
	
	
	  $(document).ready(function() {
	    var x=1;  
    $("#add-panel1").click(function() {
		
		var wrapper=$('#internships1');
	  var data1=$('#internshipbox1').html();
	    
		if(x<11)
		{
       var domElement = $('<div class="main_box section_one panel panel-primary"><div class="panel-heading"><div class="row"><div class="col-sm-3">Other Internship '+x+'</div><div class="col-sm-4 pull-right text-right"><input type="checkbox">  Approve  <input type="checkbox"> Lock</div></div></div>'+data1+'</div></div> ');
		
		
        $(wrapper).append(domElement);
		x++;
		}
				
    });
	
	
    
});


 $(document).ready(function() {
	
	  var x=1;  
    $("#add-panel2").click(function() {
		
      var wrapper=$('#internships2');
	  var data2=$('#internshipbox2').html();
	 
		if(x<5)
		{
       
 var domElement = $('<div class="main_box section_one panel panel-primary"><div class="panel-heading"><div class="row"><div class="col-sm-3">Add Work Experience '+x+'</div><div class="col-sm-4 pull-right text-right"><input type="checkbox">  Approve  <input type="checkbox"> Lock</div></div></div>'+data2+'</div></div> ');
	   
	   
	   
	   
	    $(wrapper).append(domElement);
		x++;
		}
				
    });
	});

$(document).ready(function() {
	
	  var x=1;  
    $("#add-panel3").click(function() {
		
      var wrapper=$('#internships3');
	  var data3=$('#internshipbox3').html();
	 
		if(x<5)
		{
       
		var domElement = $('<div class="main_box section_one panel panel-primary"><div class="panel-heading"><div class="row"><div class="col-sm-3">Add Resume '+x+'</div><div class="col-sm-4 pull-right text-right"><input type="checkbox">  Approve  <input type="checkbox"> Lock</div></div></div>'+data3+'</div></div> ');
		
        $(wrapper).append(domElement);
		x++;
		}
				
    });
	
	
	$('.date-picker').datepicker({
					autoclose: true,
					todayHighlight: true
				})
				//show datepicker when clicking on the icon
				.next().on(ace.click_event, function(){
					$(this).prev().focus();
				});
	
	
	
	
	});

      $(document).ready(function() {
	
	  var x=1;  
    $("#add-panel4").click(function() {
		
      var wrapper=$('#internships4');
	  var data4=$('#internshipbox4').html();
	 
		if(x<4)
		{
        var domElement = $('<div class="main_box section_one panel panel-primary"><div class="panel-heading"><div class="row"><div class="col-sm-3">Add Resume '+x+'</div><div class="col-sm-4 pull-right text-right"><input type="checkbox">  Approve  <input type="checkbox"> Lock</div></div></div>'+data4+'</div></div> ');
		
		
		
        $(wrapper).append(domElement);
		x++;
		}
				
    });
	});

 </script>
       
<!--For datepicker -->       

<script>
       
	  $(document).ready(function() {
    $('#main-fourum').DataTable( {
        dom: 'Bfrtip',
        buttons: [
            'excel', 'pdf', 'print'
        ]
    } );
} );
 $(document).ready(function() {
	 
	 
    $('#main-fourum2').DataTable( {
        dom: 'Bfrtip',
        buttons: [
            'excel', 'pdf', 'print'
        ]
    } );
	
	 $('#main-fourum3').DataTable( {
        dom: 'Bfrtip',
        buttons: [
            'excel', 'pdf', 'print'
        ]
    } );
	
	 $('#main-fourum4').DataTable( {
        dom: 'Bfrtip',
        buttons: [
            'excel', 'pdf', 'print'
        ]
    } );
	
	
	
	
} );
</script>

<script>

function getClusterName(pid){
	   var processId=pid.value;	
	   var xhttp = new XMLHttpRequest();
		 xhttp.onreadystatechange = function() {
		 if (this.readyState == 4 && this.status == 200) {	 	       
		     var i;
		     var obj = JSON.parse(this.responseText);
		     var clustlist='';
		     for(i=0; i < obj.length; i++){
		    	 clustlist+="<option value="+obj[i].clusterId+ ">"+obj[i].clusterName+"</option>";
		     }			  
		     document.getElementById("form-field-select-1").innerHTML="<option value='0'>Select</option>"+clustlist;		    
		 }
		 };
		 var action="getClusterNameByCid?pid="+processId;
		 xhttp.open("POST", action, true);
		 xhttp.send(); 	
}



   function getFirmName(cid){
	   var clusterId=cid.value;	
	   var xhttp = new XMLHttpRequest();
		 xhttp.onreadystatechange = function() {
		 if (this.readyState == 4 && this.status == 200) {	 	       
		     var i;
		     var obj = JSON.parse(this.responseText);
		     var firmlist='';
		     for(i=0; i < obj.length; i++){
		    	firmlist+="<option value="+obj[i].firmId+ ">"+obj[i].firmName+"</option>";
		     }			  
		     document.getElementById("firmSelectBoxId").innerHTML="<option value='0'>Select</option>"+firmlist;		    
		 }
		 };
		 var action="getFirmNameByCidInPanel?cid="+clusterId;
		 xhttp.open("POST", action, true);
		 xhttp.send(); 	
   }
   
   function getRoleName(appId){
	   var appId=appId.value;	
	  	var xhttp = new XMLHttpRequest();
		 xhttp.onreadystatechange = function() {
		 if (this.readyState == 4 && this.status == 200) {	 	       
		     var i;
		     var obj = JSON.parse(this.responseText);
		     var rolelist='';
		     for(i=0; i < obj.length; i++){
		    	 
		    	 rolelist+="<option value="+obj[i].roleId+ ">"+obj[i].roleName+"</option>";
		     }			  
		     document.getElementById("roleSelectBoxId").innerHTML="<option value='0'>Select</option>"+rolelist;	
		     document.getElementById("mapRoleId").innerHTML="<option value='0'>Select</option>"+rolelist;	
		     
		 }
		 };
		 var action="getRoleNameByAppIdInPanel?appId="+appId;
		 xhttp.open("POST", action, true);
		 xhttp.send(); 	
   }
   
   
   
   
   function getRMNameByFirmId(firmId){	  
	       var firmId=firmId.value;	
		   var xhttp = new XMLHttpRequest();
			 xhttp.onreadystatechange = function() {
			 if (this.readyState == 4 && this.status == 200) {	 			    
			     var i;
			     var obj = JSON.parse(this.responseText);		    
			     document.getElementById("rmId").value=obj.rmName;	
			     document.getElementById("emailId").value=obj.emailId;	
			 }
			 };			 
			 var action="getRMNameByFirmId?firmId="+firmId;
			 xhttp.open("POST", action, true);
			 xhttp.send(); 	
   }
   
   function getPanelDeatilsByAppId(appId){
	  
	   var firmId=appId.value;		
	   var xhttp = new XMLHttpRequest();
		 xhttp.onreadystatechange = function() {
		 if (this.readyState == 4 && this.status == 200) {	 			    
		     var i;		   
		     var obj = JSON.parse(this.responseText);		    
		     if(obj.panelAllocationId!=0){
		    	 var pdate  =obj.panelDate;		   
		    	 var arr= pdate.split("-");		     
		     document.getElementById("panelAllocationId").value=obj.panelAllocationId;
		     document.getElementById("id-date-picker-1").value= arr[2]+"-"+arr[1]+"-"+arr[0];	
		     document.getElementById("panelStartTimeId").value=obj.panelStartTime;	
		     document.getElementById("panelEndTimeId").value=obj.panelEndTime;	
		     document.getElementById("panelId").value=obj.panel;	
		     document.getElementById("extraRoomsId").value=obj.extraRooms;	
		     document.getElementById("notificationId").value=obj.notification;	
		     /* document.getElementById("updateButtonId").value=obj.isLocked;*/
		    
		     if(obj.isLocked==true){
		    	
		     $('#updateButtonId').prop('disabled', 'disabled');
		     }
		     }
		     if(obj.panelAllocationId==0){		    		    			     
		     document.getElementById("panelAllocationId").value=0;
		     document.getElementById("id-date-picker-1").value= '';	
		     document.getElementById("panelStartTimeId").value='';
		     document.getElementById("panelEndTimeId").value='';
		     document.getElementById("panelId").value=0;	
		     document.getElementById("extraRoomsId").value=0;	
		     document.getElementById("notificationId").value='';	
		     }
		 }
		 };			 
		 var action="getPanelDetailsByFirmId?firmId="+firmId;
		 xhttp.open("POST", action, true);
		 xhttp.send(); 
	   
   }
   
   function submitPanelAllocationForm(){
	  // var processName=document.getElementById("form-field-select-1");
	  // var pname=processName.options[processName.selectedIndex].text;
	   
	   //alert("submita");
	   var clusterName = document.getElementById("form-field-select-1");
	    var cname = clusterName.options[clusterName.selectedIndex].text;
	    var firmName = document.getElementById("firmSelectBoxId");
	    var roleName=document.getElementById("roleSelectBoxId");
	    var maproleName=document.getElementById("mapRoleId");
	    var fname = firmName.options[firmName.selectedIndex].text;
	    var rname = roleName.options[roleName.selectedIndex].text;
	    var mname=  maproleName.options[maproleName.selectedIndex].text;
	    var setcname = document.getElementById("clusterNameId");
	    $("#clusterNameId").val(cname);
	    var setfName = document.getElementById("firmNameId");
	    $("#firmNameId").val(fname); 
	    var setrName=document.getElementById("roleNameId");
	    $("#roleNameId").val(rname); 
	    var setmName=document.getElementById("maproleNameId");
	    $("#maproleNameId").val(mname); 
	    
	    document.getElementById("panelAllocationFormId").action = "savePanelAllocation";	
		document.getElementById("panelAllocationFormId").method = "post";
		document.forms["panelAllocationFormId"].submit();
   }
   
   function getFirmList(cid){
	   var clusterId=cid.value;	
	   var xhttp = new XMLHttpRequest();
		 xhttp.onreadystatechange = function() {
		 if (this.readyState == 4 && this.status == 200) {	 	       
		   /*   var i;
		     var obj = JSON.parse(this.responseText);
		     var firmlist='';
		     for(i=0; i < obj.length; i++){
		    	 if(obj[i].isLocked==true){
		    		 locked="Locked";
		    	 }
		    	 if(obj[i].isLocked==false){
		    		 locked="Open";
		    	 }
		    	firmlist+="<tr><td>"+obj[i].firmName+"</td><td>"+obj[i].notificationStatus+"</td><td>"+locked+"</td></tr>"
		     }			  
		     document.getElementById("firmListId").innerHTML=firmlist; */	
			 document.getElementById("firmListId").innerHTML = this.responseText;
		 }
		 };
		 var action="getPanelsByCid?cid="+clusterId;
		 xhttp.open("POST", action, true);
		 xhttp.send(); 	
   }
   
   function getRoleInDD(appId){
	   var firmId=appId.value;
	   var id1=document.getElementById("roleSelectBoxId");
	   var id2=document.getElementById("mapRoleId");
	   var id3,id4;
	  	var xhttp = new XMLHttpRequest();
		 xhttp.onreadystatechange = function() {
		 if (this.readyState == 4 && this.status == 200) {	 	       
		     var i;
		     var obj = JSON.parse(this.responseText);
		     if(obj.length!=0)
		     {
		    	 for(i=0; i < obj.length; i++){
			    	id3=obj[i].roleid;
			    	id4=obj[i].maproleid;
			    	for(var j=0;j<id1.options.length;j++)
			    		{
			    		 if(id1.options[j].value==id3)
			    			 {
			    			 id1.options[j].selected=true;
			    			 }
			    		}
			    	if(id4!=0)
			    		{
			    		for(var j=0;j<id2.options.length;j++)
			    		{
			    		 if(id2.options[j].value==id4)
			    			 {
			    			 id2.options[j].selected=true;
			    			 }
			    		}
			    		}
				    	
			     }			  
		     }
		 }
		 };
		 var action="getRoleInDD?firmid="+firmId;
		 xhttp.open("POST", action, true);
		 xhttp.send(); 	
   }
   
     
</script>


</body>
</html>