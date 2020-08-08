<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page isELIgnored="false"%>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Indian Institute of Management Ahmedabad (IIMA) | Home -
	IIMA</title>

<!-- Bootstrap -->
<link href="css/bootstrap.css" rel="stylesheet">

<!-- Custom -->
<link href="css/custom.css" rel="stylesheet">


<link
	href='http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800'
	rel='stylesheet' type='text/css'>
<link rel="stylesheet" href="css/font-awesome.css">

<!-- CSS STYLE-->
<link rel="stylesheet" type="text/css" href="css/style.css"
	media="screen" />

<!-- SLIDER REVOLUTION 4.x CSS SETTINGS -->
<link rel="stylesheet" type="text/css" href="rs-plugin/css/settings.css"
	media="screen" />
<style>
.content {
	min-height: 300px;
	width: 100%;
	background-color: #fff !important;
	padding-top: 50px;
}

.drop_cluster {
	margin: 20px 0px 20px 0px;
}

.drop_cluster select {
	width: 150px;
}

.drop_firm {
	margin: 20px 0px 20px 0px;
}

.drop_firm select {
	width: 150px;
	margin-left: 22px;
}

.button_margin {
	margin-top: 20px;
}

.margin_left_top {
	margin-left: 615px;
}
</style>

</head>
<body>

	<jsp:include page="commonJsp/Header.jsp" />

	<div class="clearfix"></div>
	<div class="breadcumb-wrapper">
		<div class="clearfix">

			<div class="pull-left">
				<ul class="list-inline link-list">
					<li><a href="#">Home</a></li>

					<li>Download</li>
				</ul>
			</div>

		</div>
	</div>

	<div class="container">
		<div class="content">
			<div class="row">
				<div class="col-md-offset-4 col-md-4">
					<form id="generate1">
						<input type="hidden" id="firmid1" name="cmpId" value="-1">
						<input type="hidden" id="clusterid1" name="clusterReportId" value="-1">
						<input type="hidden" id="roleid1" name="roleId" value="-1">
					</form>

					<label class="col-md-3"><h4>
							<b>Cluster:</b>
						</h4></label>
					<div class="col-md-6">
						<select class="form-control" name="cluster" id="clusterId"
							onchange="GetAllFirm(this);">
							<option value="0">Select cluster</option>
							<c:forEach items="${teamlist1}" var="team1">
								<option value="${team1.clusterId}">${team1.clusterName}</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="col-md-offset-4 col-md-4">
					<label class="col-md-3"><h4>
							<b>Firm:</b>
						</h4></label>
					<div class="col-md-6">
						<select class="form-control" name="firm" id="firmId"
							onchange="getFirmId(this),getRole(this)">
							<option value="0" selected>select firm</option>
						</select>
					</div>
				</div>
			</div>
			<div class="col-md-offset-4 col-md-4">
				<label class="col-md-3"><h4>
						<b>Role:</b>
					</h4></label>
				<div class="col-md-6">
					<select class="form-control" name="role" id="roleId"
						onchange="getRoleId(this);">
						<option value="0" selected>select role</option>
					</select>
				</div>
			</div>
		</div>

		<div class="row">
			<div class="col-sm-3"  style="text-align: center;">
				<h4>
					<b>Consideration List</b>
				</h4>
				</br> </br> <input type="button" value="Download CL"
					class="btn btn-primary button_margin" onclick="csd()">
			</div>

			<div class="col-sm-3" style="text-align: center;">
				<h4  >
					<b>Shortlist</b>
				</h4>
				</br> <input type="radio" name="shortlist" id="shortlist1" value="1"
					checked> <b>1</b> <input type="radio" name="shortlist"
					id="shortlist2" value="2"><b> 2</b><br> <input
					type="button" value="Download Shortlist"
					class="btn btn-primary button_margin" onclick="csd1()">

			</div>
			<div class="col-sm-3" style="text-align: center;">
				<h4 >
					<b>Hotlist</b>
				</h4>
				</br> </br> <input type="button" value="Download Hotlist"
					class="btn btn-primary button_margin" onclick="csd2()">
			</div>
			<div class="col-sm-3" style="text-align: center;">
				<h4>
					<b>Preference list</b>
				</h4>
				</br> </br> <input type="button" value="Download Preference"
					class="btn btn-primary button_margin" onclick="csd3()">

			</div>


		</div>

	</div>
	<div class="space"></div>
	<jsp:include page="commonJsp/Footer.jsp" />

	<!-- get jQuery from the google apis -->
	<script>
function GetAllFirm(val){
	var id=val.value;
	document.getElementById("clusterid1").value=id;
	//alert("csd::"+document.getElementById("clusterid1").value);
	document.getElementById("firmId").innerHTML="";
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		 if (this.readyState == 4 && this.status == 200) {
			var data="<option value="+"0"+" selected>select firm<oprion>";
			var jsonData=JSON.parse(this.responseText);
			data+="<option value='0'>ALL<oprion>";
			for(var i=0;i<jsonData.length;i++)
				data+="<option value="+jsonData[i].companyid+">"+jsonData[i].companyname+"<oprion>"
			  
			document.getElementById("firmId").innerHTML=data;
		} 
	};
	xhttp.open("POST", "GetallFirm?clusterId="+val.value, true);
	xhttp.send();
}

function getFirmId(val)
{
	var id=val.value;
	var id1=document.getElementById("firmId");
	document.getElementById("firmid1").value=id;
	var fname=id1.options[id1.selectedIndex].text;
	//alert("csd::"+fname);
	//alert("csd::"+document.getElementById("firmid1").value);
}
function getRoleId(val)
{
	var id=val.value;
	document.getElementById("roleid1").value=id;
	//alert("csd::"+document.getElementById("roleid1").value);
}

function csd()
{

	var id1=document.getElementById('firmid1').value;
	var id2=document.getElementById('clusterid1').value;
	var id3=document.getElementById('roleid1').value;
	if(id1==-1 || id2==-1 || id3==-1)
		{
		alert("please select cluster firm and role");
		return false;
		}
	else
		{
		//alert("csd");
		document.getElementById('generate1').action="downloadCL";
		document.getElementById('generate1').method="POST";
		document.getElementById('generate1').submit();
		
		}
}

function csd1()
{
	var id1=document.getElementById('shortlist1').checked;
	var id2=document.getElementById('shortlist2').checked;
	var id3=document.getElementById('firmid1').value;
	var id4=document.getElementById('clusterid1').value;
	var id5=document.getElementById('roleid1').value;
	if(id3==-1 || id4==-1 || id5==-1)
	{
	alert("please select cluster firm and role");
	return false;
	}
   else
	{
	   if(id1==true)
		{
			document.getElementById('generate1').action="downloadSL1";
			document.getElementById('generate1').method="POST";
			document.getElementById('generate1').submit();	
		}
	   else
		   {
			document.getElementById('generate1').action="downloadSL2";
			document.getElementById('generate1').method="POST";
			document.getElementById('generate1').submit();	
		   }
	}
}

function csd2()
{
	var id1=document.getElementById('firmid1').value;
	var id2=document.getElementById('clusterid1').value;
	var id3=document.getElementById('roleid1').value;
	if(id1==-1 || id2==-1 || id3==-1)
	{
	alert("please select cluster firm and role");
	return false;
	}
   else
	{
			document.getElementById('generate1').action="downloadHL";
			document.getElementById('generate1').method="POST";
			document.getElementById('generate1').submit();	
	}
}

function csd3()
{
	var id1=document.getElementById('firmid1').value;
	var id2=document.getElementById('clusterid1').value;
	var id3=document.getElementById('roleid1').value;
	if(id1==-1 || id2==-1 || id3==-1)
	{
	alert("please select cluster firm and role");
	return false;
	}
   else
	{
			document.getElementById('generate1').action="downloadPf";
			document.getElementById('generate1').method="POST";
			document.getElementById('generate1').submit();	
	}
}

function getRole(val){
	document.getElementById("roleId").innerHTML="";
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			var data="<option value="+"0"+" selected>select role<oprion>";
			var jsonData=JSON.parse(this.responseText);
			data+="<option value='0'>ALL<oprion>";
			for(var i=0;i<jsonData.length;i++)
					data+="<option value="+jsonData[i].companyid+">"+jsonData[i].companyname+"<oprion>"
			  
			document.getElementById("roleId").innerHTML=data;
		}
	};
	
	xhttp.open("POST", "GetallRole?firmId="+val.value+"clusterId"+document.getElementById('clusterid1').value, true);
	//xhttp.open("POST", "GetallRole?clusterId="+document.getElementById('clusterid1').value, true);
	xhttp.send();
}


</script>


</body>

</html>