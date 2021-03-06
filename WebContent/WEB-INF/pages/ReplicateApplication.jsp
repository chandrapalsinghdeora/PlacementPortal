<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<link rel="stylesheet" type="text/css"
	href="css/dataTables.bootstrap.min.css" media="screen" />
<link rel="stylesheet" type="text/css"
	href="css/buttons.dataTables.min.css" media="screen" />
<link rel="stylesheet" type="text/css"
	href="css/jquery.dataTables.min.css" media="screen" />

</head>
<body>
	<jsp:include page="commonJsp/Header.jsp" />

<div class="clearfix"></div>
<div class="breadcumb-wrapper">
            <div class="clearfix">
                <div class="pull-left">
                    <ul class="list-inline link-list">
                        <li><a href="#">Home</a></li>
                        
                        <li>Replicate Application</li>
                    </ul>
                </div>
             
            </div>
        </div> 


<div class="container-fluid">
<div class="content">
		                 

	
	<div class="container">
	<div class="row">
	<div class="col-sm-5 ">

  <center class="table_head">
                
  <h3>Copy From</h3></br>
                                
<table id="table1" class="display table table-bordered" cellspacing="0" width="100%">
        <thead>
            <tr>
                <th></th>
                <th>Firm</th>
                <th>Role</th>
                <th>R M Name</th>
            </tr>
        </thead>
  
        <tbody>
           <c:forEach items="${allCompanyDetails}" var="shrt">
            <tr>
                <td><input type="radio" name="csd" value="" onclick="getRoleId(${shrt.roleId},${shrt.firmId})"></td>
                <td>${shrt.firmName}</td>
                <td>${shrt.roleName}</td>
                <td>${shrt.rmName}</td>
            </tr>
            </c:forEach>
          
           
			</table>

</center>

</div>
	<div class="col-sm-2">
     <center>
                        
     </center>
	</div>
	<div class="col-sm-5">
	<center class="table_head">
                       <h3 >Copy To</h3></br>
                      
					   
                          
 
<table id="table2" class="display table table-bordered" cellspacing="0" width="100%">
        <thead>
            <tr>
                <th></th>
                <th>Firm</th>
                <th>Role</th>
                <th>R M Name</th>
                
            </tr>
        </thead>
  
        <tbody>
            <c:forEach items="${allCompanyDetails}" var="shrt">
            <tr>
                <td><input type="checkbox" name="" value="${shrt.roleId}##${shrt.firmId}##${shrt.noOfRound}" class="checkbox replicate11"></td>
                <td>${shrt.firmName}</td>
                <td>${shrt.roleName}</td>
                <td>${shrt.rmName}</td>
            </tr>
            </c:forEach>
           
			</table>
 

 
 
 </center>
</div>

	</div>
	<div class="space"></div>
	<div class="row">
	<div class="col-sm-3">
	</div>
	<div class="col-sm-3" style="margin-right: -60px;
    margin-left: 59px;">
<p><b>	What would you like to copy? </b></p>
	</div>
	<div class="col-sm-3 ">
	<input type="checkbox"  name="" value="" onclick="apply();" class="apply"> Applied  &nbsp;&nbsp; &nbsp;&nbsp;
	<input type="checkbox"  name="" value=""  onclick="cl();"  class="cl"> CL  &nbsp;&nbsp; &nbsp;&nbsp;
	<input type="checkbox"  name="" value=""  onclick="sl();"  class="sl"> SL &nbsp;&nbsp; &nbsp;&nbsp;
	<input type="checkbox"  name="" value=""  onclick="hl();"  class="hl"> HL &nbsp;&nbsp; &nbsp;&nbsp;
	</div>
	</div>
	
	
	<div class="row">
	<div class="col-sm-2 "></div>
	<div class="col-sm-2" style="margin-right: -60px;
    margin-left: 59px;">
<p><b>	Source Shortlist :</b></p>
	</div>
	<div class="col-sm-2 ">
	 <select name="" class="form-control" id="sourcesl" disabled onchange="setssvalue(this);">
        <option value="3">select</option>
        </select>
	</div>
	<div class="col-sm-2" style="margin-right: -60px;
    margin-left: 59px;">
<p><b>	Target Shortlist :</b></p>
	</div>
	<div class="col-sm-2 ">
	 <select name="" class="form-control" id="targetsl"  disabled onchange="settsvalue(this);">
        <option value="3">select</option>
       <option value="1">PI</option>
       <option value="0">GD</option>
        </select>
	</div>
	
	</div>
	
	
	<div class="col-sm-12 ">
	<div class="form-group col-md-12 text-center" style="margin-top:10px;">  
               <input type="submit" value="Submit" class="btn btn-primary" onclick="replicateApp()">
                    </div>
	</div>
	
</div>
			
</div>
</div>
<form id="replicate">
<input type="hidden" id="sourceroleid" name="sourceRoleId">
<input type="hidden" id="sourceappid" name="sourceAppId">
<input type="hidden" id="destinationroleid" name="destinationRoleId">
<input type="hidden" id="applyid" name="apply" value="0">
<input type="hidden" id="clid" name="CL" value="0">
<input type="hidden" id="slid" name="SL" value="0">
<input type="hidden" id="hlid" name="HL" value="0">
<input type="hidden" id="sourcesl1" name="sourceSL" value="3">
<input type="hidden" id="targetsl1" name="targetSL" value="3">
</form>


<jsp:include page="commonJsp/Footer.jsp" />
<script src="js/jquery-1.12.4.js"></script>
<script src="js/jquery.dataTables.min.js"></script>	
<script>
       
$(document).ready(function() {
    $('#table1').DataTable({
	"bFilter": true,
		 	 "bInfo": false,
		 	 "scrollY":        "200px",
		     "scrollCollapse": true,
		     "paging":         false,
				
		        dom: 'Bfrtip',
		        language: {
        searchPlaceholder: "Search"
    }

	});

	$('#table2').DataTable({
		"bFilter": true,
		 	 "bInfo": false,
		 	 "scrollY":        "200px",
		     "scrollCollapse": true,
		     "paging":         false,
				
		        dom: 'Bfrtip',
				        language: {
        searchPlaceholder: "Search"
    }	
		       
	
} );

} );

function getRoleId(id1,id2)
{
document.getElementById('sourceroleid').value=id1;
document.getElementById('sourceappid').value=id2;
//alert(document.getElementById('sourceroleid').value+"     "+document.getElementById('sourceappid').value)
}

function replicateApp()
{
	  valArrDownload = [];
	    if ($(".replicate11").length > 0){
			if ($(".replicate11").is(":checked")) {
				$('.replicate11:checked').each(function(i) {
					valArrDownload[i] = $(this).val();
				});
				$("#destinationroleid").val(valArrDownload.toString());
/* 	var id=document.getElementById('applyid').value;
	var id1=document.getElementById('clid').value;
	var id2=document.getElementById('slid').value;
	var id3=document.getElementById('hlid').value;
	var id4=document.getElementById('destinationroleid').value;
	var id5=document.getElementById('sourcesl1').value;
	var id6=document.getElementById('targetsl1').value;
	 var dataString = 'apply=' + id + '&CL='
     + id1 + '&SL=' + id2 + '&HL=' + id3 +'&destinationRoleId='+id4+'&sourceSL='+id5+'&targetSL='+id6;
	 */
				if(valArrDownload.length>0){
					/*   $.ajax({
							type : 'POST',
							data : dataString,
							url : 'checkdata',
							success : function(data) {
								if (data == 0) { */
									document.getElementById('replicate').action="replicateApp";
									document.getElementById('replicate').method="post";
									document.getElementById('replicate').submit();
								/* }
								if(data==1)
									{
									alert("Please first complete copy for Previous List");
									return false;
									}
							}
						});*/
				} 
		 	}else{
				alert("Please select at least one check box.");
				return false;
			} 
	    }
}

function apply()
{
	if ($(".apply").is(":checked")) {
		document.getElementById('applyid').value=1;
	}
	else
		{
		document.getElementById('applyid').value=0;
		}
}

function cl()
{
	if ($(".cl").is(":checked")) {
		document.getElementById('clid').value=1;
		$(".apply").prop("checked",true);
	}
	else
		{
		document.getElementById('clid').value=0;
		}
}

function sl()
{
	if ($(".sl").is(":checked")) {
		var id=document.getElementById('sourceappid').value;
		document.getElementById('slid').value=1;
		$(".apply").prop("checked",true);
		$(".cl").prop("checked",true);
		document.getElementById('sourcesl').disabled=false;
		document.getElementById('targetsl').disabled=false;
		var dataString= 'firmId=' +id;
		var data1="<option value="+"3"+" selected>select<oprion>";
		data1+="<option value='1'>PI<oprion>";
		data1+="<option value='0'>GD<oprion>";
		var data2="<option value="+"3"+" selected>select<oprion>";
		data2+="<option value='1'>PI<oprion>";
    $.ajax({
			type : 'POST',
			data : dataString,
			url : 'countsl',
			success : function(data) {
				if (data == 1) {
					document.getElementById("sourcesl").innerHTML=data2;
				}
				if(data==2)
					{
					document.getElementById("sourcesl").innerHTML=data1;
					}
			}
		});

	}
	else
		{
		document.getElementById('slid').value=0;
		document.getElementById('sourcesl').disabled=true;
		document.getElementById('targetsl').disabled=true;
		}
}

function hl()
{
	if ($(".hl").is(":checked")) {
		$(".apply").prop("checked",true);
		$(".cl").prop("checked",true);
		$(".sl").prop("checked",true);
		document.getElementById('sourcesl').disabled=false;
		document.getElementById('targetsl').disabled=false;
		document.getElementById('hlid').value=1;
	}
	else
		{
		document.getElementById('hlid').value=0;
		}
}

function setssvalue(id)
{
	document.getElementById('sourcesl1').value=id.value;	
}

function settsvalue(id)
{
	document.getElementById('targetsl1').value=id.value;	
}
</script>


</body>

</html>