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
<title>Indian Institute of Management Ahmedabad (IIMA) | Home -
	IIMA</title>

<!-- Bootstrap -->
<!-- <link href="css/bootstrap.css" rel="stylesheet"> -->

<!-- Custom -->
<link href="css/custom.css" rel="stylesheet">
<link href="css/style.css" rel="stylesheet">

<link rel="stylesheet" href="css/font-awesome.css">
<!-- CSS STYLE-->
<link rel="stylesheet" type="text/css" href="css/style.css" media="screen" />
<link rel="stylesheet" type="text/css" href="css/dataTables.bootstrap.min.css" media="screen" />
<link rel="stylesheet" type="text/css" href="css/buttons.dataTables.min.css" media="screen" />
<link rel="stylesheet" type="text/css" href="css/jquery.dataTables.min.css" media="screen" />
<link rel="stylesheet" type="text/css" href="assets/css/bootstrap-datetimepicker.min.css" media="screen" />
<link rel="stylesheet" type="text/css" href="assets/css/bootstrap.min.css" media="screen" />
<!-- CSS STYLE-->
<!-- <link rel="stylesheet" type="text/css" href="css/style.css" media="screen" /> -->

<!-- SLIDER REVOLUTION 4.x CSS SETTINGS -->
<link rel="stylesheet" type="text/css" href="rs-plugin/css/settings.css"
	media="screen" />

<script src="assets/js/jquery-2.1.4.min.js"></script> 

<style>
.content {
	min-height: 500px;
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

.border_top {
	
}

.draw_block input {
	width: 40px;
	border: 1px solid #000;
	margin-bottom: 2px;
}

.draw_block a {
	display: block;
	border: 1px solid #000;
	width: 40px;
	height: 30px;
	background-color: #4388cc;
	margin-top: 4px;
}

.btn-primary {
	width: 89%;
	height: 32px;
}

.draw_block_large {
	height: 70px;
}

.margin_inner {
	
}

.set_drop_center {
	margin-top: 111px;
	padding-left: 5px;
}

.draw_block2 {
	display: block;
	border: 1px solid #000;
	width: 90px;
	height: 30px;
	background-color: #4388cc;
	margin: 10px 6px 10px 14px;
}

.float_left {
	float: left;
}

.float_right {
	float: right;
}

.button_size {
	width: 100px;
}

.set_button_center {
	margin-top: 432px;
}

.save_fin_btn {
	margin: 25px auto;
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
					<li>Assign Firms And Negos</li>
				</ul>
			</div>
		</div>
	</div>
	<div class="container-fluid">
		<div class="content">
			<div class="drop_cluster margin_left_top">
				<div>
					<b>Cluster: </b>
				</div>
				<select name="clusterId"  class="form-control" id="form-field-select-1" onchange="setCluster(this);getTheDetails(this)">
					<option value="0">Select cluster</option>
							<c:forEach items="${clusterList}" var="clist">
								<option value="${clist.clusterId}">${clist.clusterName}</option>
							</c:forEach>
				</select>
				 <input type="hidden" name="clusterName" value="" id="clusterNameId">
			</div>
			
			
			
			
			<div class="container">
	
	<form id="savedetails">
	<input type="hidden" id="clusterid2" name="clusterId">
				<div class="row">
					
					<div class="col-sm-12">
						<div class="row">
							<div class="col-sm-3 border_top margin_inner">
							
								<div class="row">
									<center>
										<b>Wing 9</b>
									</center>
									
									<div class="col-sm-3 draw_block ">
										<c:forEach begin ="0" end="6" items="${wing9}" var="wing9list" varStatus="j"> 
											<input type="button" class="btn btn-primary"
											data-toggle="modal" data-target="#wingRoomDialog" value="${j.index+1}" id="${wing9list.wingRoomId }" data-description="${wing9list.wingRoomId }" onclick="csd(this);getFirmName();">
										</c:forEach>
											
									</div>
									<input type="hidden" name="wingId1" value="9">
									<input type="hidden" name="saveupdate" id="saveupdate9" value="0">
									<div class="col-sm-6 set_drop_center">
										<select name="nego" id="placecomerId9" class="form-control">
					<option value="0">Select</option>
							<c:forEach items="${placecomerList}" var="clist">
								<option value="${clist.key}">${clist.value}</option>
							</c:forEach>
				</select>
				<br> <br> <select name="schedular" id="sedulerId9" class="form-control">
					<option value="0">Select</option>
							<c:forEach items="${sedulerList}" var="clist">
								<option value="${clist.sedulerId}">${clist.sedulerName}</option>
							</c:forEach>
				</select>
									</div>
									<div class="col-sm-3 draw_block ">
										<c:forEach  begin ="7" end="13" items="${wing9}" var="wing9list" varStatus="j"> 
											<input type="button" class="btn btn-primary"
											data-toggle="modal" data-target="#wingRoomDialog" value="${j.index+1}" id="${wing9list.wingRoomId }" data-description="${wing9list.wingRoomId }" onclick="csd(this);getFirmName();">
										
										</c:forEach>
									</div>
									
								</div>
								<div class="row ">
									
									<c:forEach  begin ="14" end="14" items="${wing9}" var="wing9list" varStatus="j"> 
											<input type="button" class="col-sm-4 draw_block2 float_left btn btn-primary"
											data-toggle="modal" data-target="#wingRoomDialog" value="${j.index+1}" data-description="${wing9list.wingRoomId }" id="${wing9list.wingRoomId }" onclick="csd(this);getFirmName();">
										
										</c:forEach>
											<a></a>
											<div class="col-md-2"></div>
												<c:forEach  begin ="15" end="15" items="${wing9}" var="wing9list" varStatus="j"> 
											<input type="button" class="col-sm-4 draw_block2 float_left btn btn-primary"
											data-toggle="modal" data-target="#wingRoomDialog" data-description="${wing9list.wingRoomId }" value="${j.index+1}" id="${wing9list.wingRoomId }" onclick="csd(this);getFirmName();">
										
										</c:forEach>
											<a></a>
								</div>
								
								
							</div>
							<div class="col-sm-3 border_top margin_inner">
							
							
								<div class="row">
									<center>
										<b>Wing 10</b>
									</center>
									<div class="col-sm-3 draw_block ">
										<c:forEach  begin ="0" end="6" items="${wing10}" var="wing10list" varStatus="j"> 
											<input type="button" class="btn btn-primary"
											data-toggle="modal" data-target="#wingRoomDialog" value="${j.index+1}" id="${wing10list.wingRoomId }"  data-description="${wing10list.wingRoomId }" onclick="csd(this);getFirmName();">
										</c:forEach>
									</div>
										<input type="hidden" name="wingId1" value="10">
										<input type="hidden" name="saveupdate" id="saveupdate10" value="0">
									<div class="col-sm-6 set_drop_center">
										
										<select name="nego" id="placecomerId10" class="form-control">
					<option value="0">Select</option>
							<c:forEach items="${placecomerList}" var="clist">
								<option value="${clist.key}">${clist.value}</option>
							</c:forEach>
				</select>
				<br> <br> <select name="schedular" id="sedulerId10" class="form-control">
					<option value="0">Select</option>
							<c:forEach items="${sedulerList}" var="clist">
								<option value="${clist.sedulerId}">${clist.sedulerName}</option>
							</c:forEach>
				</select>
									</div>
									<div class="col-sm-3 draw_block ">
										<c:forEach  begin ="7" end="13" items="${wing10}" var="wing10list" varStatus="j"> 
											<input type="button" class="btn btn-primary"
											data-toggle="modal" data-target="#wingRoomDialog" value="${j.index+1}" id="${wing10list.wingRoomId }" data-description="${wing10list.wingRoomId }" onclick="csd(this);getFirmName();">
										</c:forEach>
									</div>
								</div>
								<div class="row ">
								<c:forEach  begin ="14" end="14" items="${wing10}" var="wing10list" varStatus="j"> 
											<input type="button" class="col-sm-4 draw_block2 float_left btn btn-primary"
											data-toggle="modal" data-target="#wingRoomDialog" value="${j.index+1}" id="${wing10list.wingRoomId }" data-description="${wing10list.wingRoomId }" onclick="csd(this);getFirmName();">
										</c:forEach>
										<a></a>
									
									<div class="col-md-2"></div>
									<c:forEach  begin ="15" end="15" items="${wing10}" var="wing10list" varStatus="j"> 
											<input type="button" class="col-sm-4 draw_block2 float_left btn btn-primary"
											data-toggle="modal" data-target="#wingRoomDialog" value="${j.index+1}" id="${wing10list.wingRoomId }" data-description="${wing10list.wingRoomId }" onclick="csd(this);getFirmName();">
										</c:forEach>
										<a></a>
								</div>
								
								
							</div>
							<div class="col-sm-3 border_top margin_inner">
							  
							 
								<div class="row">
									<center>
										<b>Wing 11</b>
									</center>
									<div class="col-sm-3 draw_block ">
										<c:forEach  begin ="0" end="6" items="${wing11}" var="wing11list" varStatus="j"> 
											<input type="button" class="btn btn-primary"
											data-toggle="modal" data-target="#wingRoomDialog" value="${j.index+1}" id="${wing11list.wingRoomId }" data-description="${wing11list.wingRoomId }" onclick="csd(this);getFirmName();">
										</c:forEach>
									</div>
										<input type="hidden" name="wingId1" value="11">
										<input type="hidden" name="saveupdate" id="saveupdate11" value="0">
									<div class="col-sm-6 set_drop_center">
										<select name="nego" id="placecomerId11" class="form-control">
					<option value="0">Select</option>
							<c:forEach items="${placecomerList}" var="clist">
								<option value="${clist.key}">${clist.value}</option>
							</c:forEach>
				</select>
				<br> <br> <select name="schedular" id="sedulerId11" class="form-control">
					<option value="0">Select</option>
							<c:forEach items="${sedulerList}" var="clist">
								<option value="${clist.sedulerId}">${clist.sedulerName}</option>
							</c:forEach>
				</select>
									</div>
									<div class="col-sm-3 draw_block ">
										<c:forEach  begin ="7" end="13" items="${wing11}" var="wing11list" varStatus="j"> 
											<input type="button" class="btn btn-primary"
											data-toggle="modal" data-target="#wingRoomDialog" value="${j.index+1}" id="${wing11list.wingRoomId }" data-description="${wing11list.wingRoomId }" onclick="csd(this);getFirmName();">
										</c:forEach>
									</div>
								</div>
								<div class="row ">
								<c:forEach  begin ="14" end="14" items="${wing11}" var="wing11list" varStatus="j"> 
											<input type="button" class="col-sm-4 draw_block2 float_left btn btn-primary"
											data-toggle="modal" data-target="#wingRoomDialog" value="${j.index+1}" id="${wing11list.wingRoomId }" data-description="${wing11list.wingRoomId }" onclick="csd(this);getFirmName();">
										</c:forEach>
										<a></a>
									<div class="col-md-2"></div>
									<c:forEach  begin ="15" end="15" items="${wing11}" var="wing11list" varStatus="j"> 
											<input type="button" class="col-sm-4 draw_block2 float_right btn btn-primary"
											data-toggle="modal" data-target="#wingRoomDialog" value="${j.index+1}" id="${wing11list.wingRoomId }" data-description="${wing11list.wingRoomId }" onclick="csd(this);getFirmName();">
										</c:forEach>
										<a></a>
									
								</div>
								
							</div>
							<div class="col-sm-3 border_top margin_inner">
							
								<div class="row">
									<center>
										<b>Wing 12</b>
									</center>
									<div class="col-sm-3 draw_block ">
										<c:forEach  begin ="0" end="4" items="${wing12}" var="wing12list" varStatus="j"> 
											<input type="button" class="btn btn-primary"
											data-toggle="modal" data-target="#wingRoomDialog" value="${j.index+1}" id="${wing12list.wingRoomId }" data-description="${wing12list.wingRoomId }" onclick="csd(this);getFirmName();">
										</c:forEach>
									</div>

                                	<input type="hidden" name="wingId1" value="12">
                                	<input type="hidden" name="saveupdate" id="saveupdate12" value="0">
									<div class="col-sm-6 set_drop_center">
										<select name="nego" id="placecomerId12" class="form-control">
					<option value="0">Select</option>
							<c:forEach items="${placecomerList}" var="clist">
								<option value="${clist.key}">${clist.value}</option>
							</c:forEach>
				</select>
				<br> <br> <select name="schedular" id="sedulerId12" class="form-control">
					<option value="0">Select</option>
							<c:forEach items="${sedulerList}" var="clist">
								<option value="${clist.sedulerId}">${clist.sedulerName}</option>
							</c:forEach>
				</select>
									</div>
									<div class="col-sm-3 draw_block ">
										<c:forEach  begin ="5" end="9" items="${wing12}" var="wing12list" varStatus="j"> 
											<input type="button" class="btn btn-primary"
											data-toggle="modal" data-target="#wingRoomDialog" value="${j.index+1}" id="${wing12list.wingRoomId }" data-description="${wing12list.wingRoomId }" onclick="csd(this);getFirmName();">
										</c:forEach>
									</div>
								</div>
								<div class="row ">
								<br>
									<c:forEach  begin ="10" end="10" items="${wing12}" var="wing12list" varStatus="j"> 
											<input type="button" class="col-sm-4 draw_block2 float_left btn btn-primary"
											data-toggle="modal" data-target="#wingRoomDialog" value="${j.index+1}" id="${wing12list.wingRoomId }" data-description="${wing12list.wingRoomId }" onclick="csd(this);getFirmName();">
										</c:forEach>
										<a></a>
									<div class="col-md-2"></div>
									<c:forEach  begin ="11" end="11" items="${wing12}" var="wing12list" varStatus="j"> 
											<input type="button" class="col-sm-4 draw_block2 float_right btn btn-primary"
											data-toggle="modal" data-target="#wingRoomDialog" value="${j.index+1}" id="${wing12list.wingRoomId }" data-description="${wing12list.wingRoomId }" onclick="csd(this);getFirmName();">
										</c:forEach>
										<a></a>
								</div>
								
							</div>
							
						</div>
						</br> </br> </br>
						<div class="row">
							<div class="col-sm-3 border_top">
							
								<div class="row">
									<center>
										<b>Wing 5</b>
									</center>
									<div class="col-sm-3 draw_block ">
										<c:forEach  begin ="0" end="6" items="${wing5}" var="wing5list" varStatus="j"> 
											<input type="button" class="btn btn-primary"
											data-toggle="modal" data-target="#wingRoomDialog" value="${j.index+1}" id="${wing5list.wingRoomId }" data-description="${wing5list.wingRoomId }" onclick="csd(this);getFirmName();">
										</c:forEach>
									</div>
										<input type="hidden" name="wingId1" value="5">
										<input type="hidden" name="saveupdate" id="saveupdate5" value="0">
									<div class="col-sm-6 set_drop_center">
										<select name="nego" id="placecomerId5" class="form-control">
					<option value="0">Select</option>
							<c:forEach items="${placecomerList}" var="clist">
								<option value="${clist.key}">${clist.value}</option>
							</c:forEach>
				</select>
				<br> <br> <select name="schedular" id="sedulerId5" class="form-control">
					<option value="0">Select</option>
							<c:forEach items="${sedulerList}" var="clist">
								<option value="${clist.sedulerId}">${clist.sedulerName}</option>
							</c:forEach>
				</select>
									</div>
									<div class="col-sm-3 draw_block ">
										<c:forEach  begin ="7" end="13" items="${wing5}" var="wing5list" varStatus="j"> 
											<input type="button" class="btn btn-primary"
											data-toggle="modal" data-target="#wingRoomDialog" value="${j.index+1}" id="${wing5list.wingRoomId }" data-description="${wing5list.wingRoomId }" onclick="csd(this);getFirmName();">
										</c:forEach>
									</div>
								</div>
								<div class="row ">
								<c:forEach  begin ="14" end="14" items="${wing5}" var="wing5list" varStatus="j"> 
											<input type="button" class="col-sm-4 draw_block2 float_left btn btn-primary"
											data-toggle="modal" data-target="#wingRoomDialog" value="${j.index+1}" id="${wing5list.wingRoomId }" data-description="${wing5list.wingRoomId }" onclick="csd(this);getFirmName();">
										</c:forEach>
										<a></a>
									<div class="col-md-2"></div>
									<c:forEach  begin ="15" end="15" items="${wing5}" var="wing5list" varStatus="j"> 
											<input type="button" class="col-sm-4 draw_block2 float_right btn btn-primary"
											data-toggle="modal" data-target="#wingRoomDialog" value="${j.index+1}" id="${wing5list.wingRoomId }" data-description="${wing5list.wingRoomId }" onclick="csd(this);getFirmName();">
										</c:forEach>
										<a></a>
									
								</div>
								
							</div>
							<div class="col-sm-3 border_top">
							 
								<div class="row">
									<center>
										<b>Wing 6</b>
									</center>
									<div class="col-sm-3 draw_block ">
										<c:forEach  begin ="0" end="4" items="${wing6}" var="wing6list" varStatus="j"> 
											<input type="button" class="btn btn-primary"
											data-toggle="modal" data-target="#wingRoomDialog" value="${j.index+1}" id="${wing6list.wingRoomId }" data-description="${wing6list.wingRoomId }" onclick="csd(this);getFirmName();">
										</c:forEach>
									</div>
										<input type="hidden" name="wingId1" value="6">
										<input type="hidden" name="saveupdate" id="saveupdate6" value="0">
									<div class="col-sm-6 set_drop_center">
										<select name="nego" id="placecomerId6" class="form-control">
					<option value="0">Select</option>
							<c:forEach items="${placecomerList}" var="clist">
								<option value="${clist.key}">${clist.value}</option>
							</c:forEach>
				</select>
				<br> <br> <select name="schedular" id="sedulerId6" class="form-control">
					<option value="0">Select</option>
							<c:forEach items="${sedulerList}" var="clist">
								<option value="${clist.sedulerId}">${clist.sedulerName}</option>
							</c:forEach>
				</select>
									</div>
									<div class="col-sm-3 draw_block ">
										<c:forEach  begin ="5" end="9" items="${wing6}" var="wing6list" varStatus="j"> 
											<input type="button" class="btn btn-primary"
											data-toggle="modal" data-target="#wingRoomDialog" value="${j.index+1}" id="${wing6list.wingRoomId }" data-description="${wing6list.wingRoomId }" onclick="csd(this);getFirmName();">
										</c:forEach>
									</div>
								</div>
							<div class="row ">
							<br>
									<c:forEach  begin ="10" end="10" items="${wing6}" var="wing6list" varStatus="j"> 
											<input type="button" class="col-sm-4 draw_block2 float_left btn btn-primary"
											data-toggle="modal" data-target="#wingRoomDialog" value="${j.index+1}" id="${wing6list.wingRoomId }" data-description="${wing6list.wingRoomId }" onclick="csd(this);getFirmName();">
										</c:forEach>
										<a></a>
										
									<div class="col-md-2"></div>
									<c:forEach  begin ="11" end="11" items="${wing6}" var="wing6list" varStatus="j"> 
											<input type="button" class="col-sm-4 draw_block2 float_right btn btn-primary"
											data-toggle="modal" data-target="#wingRoomDialog" value="${j.index+1}" id="${wing6list.wingRoomId }" data-description="${wing6list.wingRoomId }" onclick="csd(this);getFirmName();">
										</c:forEach>
										<a></a>
								</div>
								
							</div>
							<div class="col-sm-3 border_top">
							
								<div class="row">
									<center>
										<b>Wing 7</b>
									</center>
									<div class="col-sm-3 draw_block ">
										<c:forEach  begin ="0" end="6" items="${wing7}" var="wing7list" varStatus="j"> 
											<input type="button" class="btn btn-primary"
											data-toggle="modal" data-target="#wingRoomDialog" value="${j.index+1}" id="${wing7list.wingRoomId }" data-description="${wing7list.wingRoomId }" onclick="csd(this);getFirmName();">
										</c:forEach>
									</div>
										<input type="hidden" name="wingId1" value="7">
										<input type="hidden" name="saveupdate" id="saveupdate7" value="0">
									<div class="col-sm-6 set_drop_center">
										<select name="nego" id="placecomerId7" class="form-control">
					<option value="0">Select</option>
							<c:forEach items="${placecomerList}" var="clist">
								<option value="${clist.key}">${clist.value}</option>
							</c:forEach>
				</select>
				<br> <br> <select name="schedular" id="sedulerId7" class="form-control">
					<option value="0">Select</option>
							<c:forEach items="${sedulerList}" var="clist">
								<option value="${clist.sedulerId}">${clist.sedulerName}</option>
							</c:forEach>
				</select>
									</div>
									<div class="col-sm-3 draw_block ">
										<c:forEach  begin ="7" end="13" items="${wing7}" var="wing7list" varStatus="j"> 
											<input type="button" class="btn btn-primary"
											data-toggle="modal" data-target="#wingRoomDialog" value="${j.index+1}" id="${wing7list.wingRoomId }" data-description="${wing7list.wingRoomId }" onclick="csd(this);getFirmName();">
										</c:forEach>
									</div>
								</div>
									<div class="row ">
								<c:forEach  begin ="14" end="14" items="${wing7}" var="wing7list" varStatus="j"> 
											<input type="button" class="col-sm-4 draw_block2 float_left btn btn-primary"
											data-toggle="modal" data-target="#wingRoomDialog" value="${j.index+1}" id="${wing7list.wingRoomId }" data-description="${wing7list.wingRoomId }" onclick="csd(this);getFirmName();">
										</c:forEach>
										<a></a>
									<div class="col-md-2"></div>
									<c:forEach  begin ="15" end="15" items="${wing7}" var="wing7list" varStatus="j"> 
											<input type="button" class="col-sm-4 draw_block2 float_right btn btn-primary"
											data-toggle="modal" data-target="#wingRoomDialog" value="${j.index+1}" id="${wing7list.wingRoomId }" data-description="${wing7list.wingRoomId }" onclick="csd(this);getFirmName();">
										</c:forEach>
										<a></a>
									
								</div>
							</div>
							<div class="col-sm-3 border_top">
							
								<div class="row">
									<center>
										<b>Wing 8</b>
									</center>
									<div class="col-sm-3 draw_block ">
										<c:forEach  begin ="0" end="6" items="${wing8}" var="wing8list" varStatus="j"> 
											<input type="button" class="btn btn-primary"
											data-toggle="modal" data-target="#wingRoomDialog" value="${j.index+1}" id="${wing8list.wingRoomId }" data-description="${wing8list.wingRoomId }" onclick="csd(this);getFirmName();">
										</c:forEach>
									</div>
										<input type="hidden" name="wingId1" value="8">
										<input type="hidden" name="saveupdate" id="saveupdate8" value="0">
									<div class="col-sm-6 set_drop_center">
										<select name="nego" id="placecomerId8" class="form-control">
					<option value="0">Select</option>
							<c:forEach items="${placecomerList}" var="clist">
								<option value="${clist.key}">${clist.value}</option>
							</c:forEach>
				</select>
				<br> <br> <select name="schedular" id="sedulerId8" class="form-control">
					<option value="0">Select</option>
							<c:forEach items="${sedulerList}" var="clist">
								<option value="${clist.sedulerId}">${clist.sedulerName}</option>
							</c:forEach>
				</select>
									</div>
									<div class="col-sm-3 draw_block ">
										<c:forEach  begin ="7" end="13" items="${wing8}" var="wing8list" varStatus="j"> 
											<input type="button" class="btn btn-primary"
											data-toggle="modal" data-target="#wingRoomDialog" value="${j.index+1}" id="${wing8list.wingRoomId }" data-description="${wing8list.wingRoomId }" onclick="csd(this);getFirmName();">
										</c:forEach>
									</div>
								</div>
									<div class="row ">
								<c:forEach  begin ="14" end="14" items="${wing8}" var="wing8list" varStatus="j"> 
											<input type="button" class="col-sm-4 draw_block2 float_left btn btn-primary"
											data-toggle="modal" data-target="#wingRoomDialog" value="${j.index+1}" id="${wing8list.wingRoomId }" data-description="${wing8list.wingRoomId }" onclick="csd(this);getFirmName();">
										</c:forEach>
										<a></a>
									<div class="col-md-2"></div>
									<c:forEach  begin ="15" end="15" items="${wing8}" var="wing8list" varStatus="j"> 
											<input type="button" class="col-sm-4 draw_block2 float_right btn btn-primary"
											data-toggle="modal" data-target="#wingRoomDialog" value="${j.index+1}" id="${wing8list.wingRoomId }" data-description="${wing8list.wingRoomId }" onclick="csd(this);getFirmName();">
										</c:forEach>
										<a></a>
									
								</div>
							
							</div>
						</div>
						</br> </br> </br>
						<div class="row">
							<div class="col-sm-3 border_top">
							
								<div class="row">
									<center>
										<b>Wing 1</b>
									</center>
									<div class="col-sm-3 draw_block ">
										<c:forEach  begin ="0" end="6" items="${wing1}" var="wing1list" varStatus="j"> 
											<input type="button" class="btn btn-primary"
											data-toggle="modal" data-target="#wingRoomDialog" value="${j.index+1}" id="${wing1list.wingRoomId }" data-description="${wing1list.wingRoomId }" onclick="csd(this);getFirmName();">
										</c:forEach>
									</div>
										<input type="hidden" name="wingId1" value="1">
										<input type="hidden" name="saveupdate" id="saveupdate1" value="0">
									<div class="col-sm-6 set_drop_center">
										<select name="nego" id="placecomerId1" class="form-control">
					<option value="0">Select</option>
							<c:forEach items="${placecomerList}" var="clist">
								<option value="${clist.key}">${clist.value}</option>
							</c:forEach>
				</select>
				<br> <br> <select name="schedular" id="sedulerId1" class="form-control">
					<option value="0">Select</option>
							<c:forEach items="${sedulerList}" var="clist">
								<option value="${clist.sedulerId}">${clist.sedulerName}</option>
							</c:forEach>
				</select>
									</div>
									<div class="col-sm-3 draw_block ">
										<c:forEach  begin ="7" end="13" items="${wing1}" var="wing1list" varStatus="j"> 
											<input type="button" class="btn btn-primary"
											data-toggle="modal" data-target="#wingRoomDialog" value="${j.index+1}" id="${wing1list.wingRoomId }" data-description="${wing1list.wingRoomId }" onclick="csd(this);getFirmName();">
										</c:forEach>
									</div>
								</div>
									<div class="row ">
								<c:forEach  begin ="14" end="14" items="${wing1}" var="wing1list" varStatus="j"> 
											<input type="button" class="col-sm-4 draw_block2 float_left btn btn-primary"
											data-toggle="modal" data-target="#wingRoomDialog" value="${j.index+1}" id="${wing1list.wingRoomId }" data-description="${wing1list.wingRoomId }" onclick="csd(this);getFirmName();">
										</c:forEach>
										<a></a>
									<div class="col-md-2"></div>
									<c:forEach  begin ="15" end="15" items="${wing1}" var="wing1list" varStatus="j"> 
											<input type="button" class="col-sm-4 draw_block2 float_right btn btn-primary"
											data-toggle="modal" data-target="#wingRoomDialog" value="${j.index+1}" id="${wing1list.wingRoomId }" data-description="${wing1list.wingRoomId }" onclick="csd(this);getFirmName();">
										</c:forEach>
										<a></a>
									
								</div>
							
							</div>
							<div class="col-sm-3 border_top">
							
								<div class="row">
									<center>
										<b>Wing 2</b>
									</center>
									<div class="col-sm-3 draw_block ">
										<c:forEach  begin ="0" end="6" items="${wing2}" var="wing2list" varStatus="j"> 
											<input type="button" class="btn btn-primary"
											data-toggle="modal" data-target="#wingRoomDialog" value="${j.index+1}" id="${wing2list.wingRoomId }" data-description="${wing2list.wingRoomId }" onclick="csd(this);getFirmName();">
										</c:forEach>
									</div>
										<input type="hidden" name="wingId1" value="2">
										<input type="hidden" name="saveupdate" id="saveupdate2" value="0">
									<div class="col-sm-6 set_drop_center">
										<select name="nego" id="placecomerId2" class="form-control">
					<option value="0">Select</option>
							<c:forEach items="${placecomerList}" var="clist">
								<option value="${clist.key}">${clist.value}</option>
							</c:forEach>
				</select>
				<br> <br> <select name="schedular" id="sedulerId2" class="form-control">
					<option value="0">Select</option>
							<c:forEach items="${sedulerList}" var="clist">
								<option value="${clist.sedulerId}">${clist.sedulerName}</option>
							</c:forEach>
				</select>
									</div>
									<div class="col-sm-3 draw_block ">
										<c:forEach  begin ="7" end="13" items="${wing2}" var="wing2list" varStatus="j"> 
											<input type="button" class="btn btn-primary"
											data-toggle="modal" data-target="#wingRoomDialog" value="${j.index+1}" id="${wing2list.wingRoomId }"  data-description="${wing2list.wingRoomId }" onclick="csd(this);getFirmName();">
										</c:forEach>
									</div>
								</div>
									<div class="row ">
								<c:forEach  begin ="14" end="14" items="${wing2}" var="wing2list" varStatus="j"> 
											<input type="button" class="col-sm-4 draw_block2 float_left btn btn-primary"
											data-toggle="modal" data-target="#wingRoomDialog" value="${j.index+1}" id="${wing2list.wingRoomId }"  data-description="${wing2list.wingRoomId }" onclick="csd(this);getFirmName();">
										</c:forEach>
										<a></a>
									<div class="col-md-2"></div>
									<c:forEach  begin ="15" end="15" items="${wing2}" var="wing2list" varStatus="j"> 
											<input type="button" class="col-sm-4 draw_block2 float_right btn btn-primary"
											data-toggle="modal" data-target="#wingRoomDialog" value="${j.index+1}" id="${wing2list.wingRoomId }"  data-description="${wing2list.wingRoomId }" onclick="csd(this);getFirmName();">
										</c:forEach>
										<a></a>
									
								</div>
								
							</div>
							<div class="col-sm-3 border_top">
							
								<div class="row">
									<center>
										<b>Wing 3</b>
									</center>
									<div class="col-sm-3 draw_block ">
										<c:forEach  begin ="0" end="6" items="${wing3}" var="wing3list" varStatus="j"> 
											<input type="button" class="btn btn-primary"
											data-toggle="modal" data-target="#wingRoomDialog" value="${j.index+1}" id="${wing3list.wingRoomId }"  data-description="${wing3list.wingRoomId }" onclick="csd(this);getFirmName();">
										</c:forEach>
									</div>
										<input type="hidden" name="wingId1" value="3">
										<input type="hidden" name="saveupdate" id="saveupdate3" value="0">
									<div class="col-sm-6 set_drop_center">
										<select name="nego" id="placecomerId3" class="form-control">
					<option value="0">Select</option>
							<c:forEach items="${placecomerList}" var="clist">
								<option value="${clist.key}">${clist.value}</option>
							</c:forEach>
				</select>
				<br> <br> <select name="schedular" id="sedulerId3" class="form-control">
					<option value="0">Select</option>
							<c:forEach items="${sedulerList}" var="clist">
								<option value="${clist.sedulerId}">${clist.sedulerName}</option>
							</c:forEach>
				</select>
									</div>
									<div class="col-sm-3 draw_block ">
										<c:forEach  begin ="7" end="13" items="${wing3}" var="wing3list" varStatus="j"> 
											<input type="button" class="btn btn-primary"
											data-toggle="modal" data-target="#wingRoomDialog" value="${j.index+1}" id="${wing3list.wingRoomId }"  data-description="${wing3list.wingRoomId }" onclick="csd(this);getFirmName();">
										</c:forEach>
									</div>
								</div>
								<div class="row ">
								<c:forEach  begin ="14" end="14" items="${wing3}" var="wing3list" varStatus="j"> 
											<input type="button" class="col-sm-4 draw_block2 float_left btn btn-primary"
											data-toggle="modal" data-target="#wingRoomDialog" value="${j.index+1}" id="${wing3list.wingRoomId }"  data-description="${wing3list.wingRoomId }" onclick="csd(this);getFirmName();">
										</c:forEach>
										<a></a>
									<div class="col-md-2"></div>
									<c:forEach  begin ="15" end="15" items="${wing3}" var="wing3list" varStatus="j"> 
											<input type="button" class="col-sm-4 draw_block2 float_right btn btn-primary"
											data-toggle="modal" data-target="#wingRoomDialog" value="${j.index+1}" id="${wing3list.wingRoomId }"  data-description="${wing3list.wingRoomId }" onclick="csd(this);getFirmName();">
										</c:forEach>
										<a></a>
									
								</div>
								
							</div>
							<div class="col-sm-3 border_top">
							
								<div class="row">
									<center>
										<b>Wing 4</b>
									</center>
									<div class="col-sm-3 draw_block ">
										<c:forEach  begin ="0" end="6" items="${wing4}" var="wing4list" varStatus="j"> 
											<input type="button" class="btn btn-primary"
											data-toggle="modal" data-target="#wingRoomDialog" value="${j.index+1}" id="${wing4list.wingRoomId }"  data-description="${wing4list.wingRoomId }" onclick="csd(this);getFirmName();">
										</c:forEach>
									</div>
										<input type="hidden" name="wingId1" value="4">
										<input type="hidden" name="saveupdate" id="saveupdate4" value="0">
									<div class="col-sm-6 set_drop_center">
										<select name="nego" id="placecomerId4" class="form-control">
					<option value="0">Select</option>
							<c:forEach items="${placecomerList}" var="clist">
								<option value="${clist.key}">${clist.value}</option>
							</c:forEach>
				</select>
				<br> <br> <select name="schedular" id="sedulerId4" class="form-control">
					<option value="0">Select</option>
							<c:forEach items="${sedulerList}" var="clist">
								<option value="${clist.sedulerId}">${clist.sedulerName}</option>
							</c:forEach>
				</select>
									</div>
									<div class="col-sm-3 draw_block ">
										<c:forEach  begin ="7" end="13" items="${wing4}" var="wing4list" varStatus="j"> 
											<input type="button" class="btn btn-primary"
											data-toggle="modal" data-target="#wingRoomDialog" value="${j.index+1}" id="${wing4list.wingRoomId }" data-description="${wing4list.wingRoomId }" onclick="csd(this);getFirmName();">
										</c:forEach>
									</div>
								</div>
									<div class="row ">
								<c:forEach  begin ="14" end="14" items="${wing4}" var="wing4list" varStatus="j"> 
											<input type="button" class="col-sm-4 draw_block2 float_left btn btn-primary"
											data-toggle="modal" data-target="#wingRoomDialog" value="${j.index+1}" id="${wing4list.wingRoomId }" data-description="${wing4list.wingRoomId }" onclick="csd(this);getFirmName();">
										</c:forEach>
										<a></a>
									<div class="col-md-2"></div>
									<c:forEach  begin ="15" end="15" items="${wing4}" var="wing4list" varStatus="j"> 
											<input type="button" class="col-sm-4 draw_block2 float_right btn btn-primary"
											data-toggle="modal" data-target="#wingRoomDialog" value="${j.index+1}" id="${wing4list.wingRoomId }" data-description="${wing4list.wingRoomId }" onclick="csd(this);getFirmName();">
										</c:forEach>
										<a></a>
									
								</div>
								
							</div>
						</div>
					</div>
					<div class="col-sm-12">
						<center class="save_fin_btn">
							<button class="button_size" type="button" id=""
								class="btn btn-success btn-block" onclick="wingSave()">Save</button>
							<!-- <button class="button_size" type="submit" id=""
								class="btn btn-success btn-block">Finalise</button> -->
						</center>
					</div>
					
				</div>
				
				</form>	
			</div>
		
		</div>

	</div>


	<!-- Modal -->
	<div class="modal fade" id="wingRoomDialog" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Firm Choose</h4>
				</div>
				<div class="modal-body">
				<form id="roomAllotmentFormId" name="roomAllotment">
				<input type="hidden" name="hidden" value="" id="xxxx">
				<input type="hidden" id="csd11" value="0" >
				<input type="hidden" id="csd33" value="0" >
				<input type="hidden" id="extraroom1" value="0" >
				<input type="hidden" id="clusterid1" name="clusterId">
					<div class="row">
						<label class="col-md-3">Firm</label>
						<div class="col-md-9">
							<select name="firmId" class="form-control" id="wingFirmSelectBoxId" onchange="getRoleName(this)">
								
							</select>
						</div>
					</div>
					<p></p>
					<div class="row">
						<label class="col-md-3">Roles </label>
						<div class="col-md-9">
							<select name="roleId" class="form-control" id="wingRoleSelectBoxId" onchange="getTheTime(this);getPanelTime(this);roomvalidationforfirm();">
								
							</select>
						</div>
					</div>

                  <div class="row">
						<div class="  border">
							<label class="col-sm-3"> Panel Start Time </label>
							<div class="col-sm-3 inpur_box">
								<div class="form-group">
										<input type='text' class="form-control" name="startTime1" id="startTime1" disabled/> 
								</div>
							</div>
							<label class="col-sm-3">Panel End Time </label>
							<div class="col-sm-3 inpur_box">
								<div class="form-group">
										<input type='text' class="form-control" name="endTime1" id="endTime1" disabled/> 
								</div>
							</div>
						</div>
					</div> 
                  

					<div class="row">
						<div class="  border">
							<label class="col-sm-3">Start Time </label>
							<div class="col-sm-3 inpur_box">
								<div class="form-group">
									<div class='input-group date' id='datetimepicker3'>
										<input type='text' class="form-control" name="startTime" id="startTime"/> <span
											class="input-group-addon"> <span
											class="glyphicon glyphicon-time"></span>
										</span>
									</div>
								</div>
							</div>
							<label class="col-sm-3">End Time </label>
							<div class="col-sm-3 inpur_box">
								<div class="form-group">
									<div class='input-group date' id='datetimepicker4'>
										<input type='text' class="form-control" name="endTime" id="endTime"/> <span
											class="input-group-addon"> <span
											class="glyphicon glyphicon-time"></span>
										</span>
									</div>
								</div>
							</div>
						</div>
					</div>

                </form>
                <form id="deleteroomallocation">
				<input type="hidden" name="firmId" id="deletefirm">
				<input type="hidden" name="roleId" id="deleterole">
				<input type="hidden" name="wingRoomId" id="deleteroom">
				</form>
				</div>
				
				
				<div class="modal-footer">
					<!-- <button type="button" class="btn btn-default">Add Firm</button> -->
					<!-- <button type="button" class="btn btn-default" data-dismiss="modal">Save</button> -->
					<button type="button" class="btn btn-default" onclick="submitRoomAllotment();">Save</button>
					<button type="button" class="btn btn-default" onclick="deleteRoomAllotment();">Delete</button>
				</div>
			</div>

		</div>
	</div>


	<!-- <script src="assets/js/bootstrap.min.js"></script> -->
	<!-- <script src="assets/js/bootstrap-datepicker.min.js"></script>
	<script src="assets/js/bootstrap-timepicker.min.js"></script>
	<script src="assets/js/moment.min.js"></script>
	<script src="assets/js/bootstrap-datetimepicker.min.js"></script> -->

	<!-- ace scripts -->
	<!--<script src="assets/js/ace-elements.min.js"></script>-->
	<!-- <script src="assets/js/ace.min.js"></script> -->
	<!--For Tiem picker only -->
	
	
	<jsp:include page="commonJsp/Footer.jsp" />
	
	<script type="text/javascript" src="js/jquery.dataTables.min.js"></script> 
<script type="text/javascript" src="js/dataTables.buttons.min.js"></script> 
<script type="text/javascript" src="js/buttons.flash.min.js"></script> 
<script type="text/javascript" src="js/jszip.min.js"></script> 
<script type="text/javascript" src="js/pdfmake.min.js"></script> 
<script type="text/javascript" src="js/vfs_fonts.js"></script> 
<script type="text/javascript" src="js/buttons.html5.min.js"></script> 
<script type="text/javascript" src="js/buttons.print.min.js"></script> 
<!-- <script type="text/javascript" src="js/bootstrap.js"></script>  -->

<!--For datepicker --> 
<!--For Tiem picker only --> 

        <script src="assets/js/bootstrap-datepicker.min.js"></script>
                <script src="assets/js/bootstrap-timepicker.min.js"></script>
                <script src="assets/js/moment.min.js"></script>
                        <script src="assets/js/bootstrap-datetimepicker.min.js"></script>
<!-- ace scripts -->


<script src="assets/js/ace.min.js"></script>
	
	
	
	
	
	
<script type="text/javascript">
		$(function() {
			$('#datetimepicker3').datetimepicker({
				//format : 'LT'
					format: 'HH:mm'
				//pick24HourFormat: true 
			});
		});

		$(function() {
			$('#datetimepicker4').datetimepicker({
				format: 'HH:mm'
			});
		});
	</script>
	
	<script>
	
	function csd(id){
	    $("#xxxx").val($(id).data('description'));
	    var id=document.getElementById('xxxx').value;
	    $("#wingRoomDialog").show();
	    document.getElementById('wingFirmSelectBoxId').value='';
	    document.getElementById('wingRoleSelectBoxId').value='';
	    document.getElementById('startTime').value='';
	    document.getElementById('endTime').value='';
	    document.getElementById('startTime1').value='';
	    document.getElementById('endTime1').value='';
		  	
	}

	
	
	</script>
	
	
	<script >
	
	function setCluster(cid)
	{
		var clusterId=cid.value;
	 document.getElementById('clusterid1').value=clusterId;
	  document.getElementById('clusterid2').value=clusterId;
	}
	
	function getFirmName(){
		   //var clusterId=cid.value;	
		   var clusterId=document.getElementById('form-field-select-1').value;
		   var roomId=document.getElementById('xxxx').value;
		  // alert(clusterId+"-----"+roomId);
		  // document.getElementById('clusterid1').value=clusterId;
		   //document.getElementById('clusterid2').value=clusterId;
		   var xhttp = new XMLHttpRequest();
			 xhttp.onreadystatechange = function() {
			 if (this.readyState == 4 && this.status == 200) {	 	       
			     var i;
			     var obj = JSON.parse(this.responseText);
			     var firmlist='';
			     for(i=0; i < obj.length; i++){
			    	firmlist+="<option value="+obj[i].firmId+ ">"+obj[i].firmName+"</option>";
			     }			  
			     document.getElementById("wingFirmSelectBoxId").innerHTML="<option value='0'>Select</option>"+firmlist;		    
			 }
			 };
			 var action="getFirmNameByCid?cid="+clusterId+"&roomid="+roomId;
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
			     document.getElementById("wingRoleSelectBoxId").innerHTML="<option value='0'>Select</option>"+rolelist;	
			   
			     
			 }
			 };
			 var action="getRoleNameByAppId?appId="+appId;
			 xhttp.open("POST", action, true);
			 xhttp.send(); 	
	   }
	
	function getTheTime(appId){
		
		   var appId=appId.value;
		   var id=document.getElementById('xxxx').value;
		   var id1=0;
		  // alert(appId);
		  	var xhttp = new XMLHttpRequest();
			 xhttp.onreadystatechange = function() {
			 if (this.readyState == 4 && this.status == 200) {	 	       
			     var i;
			     var obj = JSON.parse(this.responseText);
			     for(i=0; i < obj.length; i++){
			    	 id1=1;
			    	 document.getElementById("csd11").value=1;
			    	 document.getElementById("startTime").value=obj[i].starttime;
			    	 document.getElementById("endTime").value=obj[i].endtime;
			     }
			     if(id1==0)
			    	 {
			    	 document.getElementById("startTime").value='';
			    	 document.getElementById("endTime").value='';
			    	 }
			 }
			 };
			 var action="getThetime?appId="+appId+"csd"+id;
			 xhttp.open("POST", action, true);
			 xhttp.send(); 	
	   }
		
	function getPanelTime(appId)
	{
		 var appId=appId.value;
		   var id=document.getElementById('xxxx').value;
		   var id1=0;
		  // alert(appId);
		  	var xhttp = new XMLHttpRequest();
			 xhttp.onreadystatechange = function() {
			 if (this.readyState == 4 && this.status == 200) {	 	       
			     var i;
			     var obj = JSON.parse(this.responseText);
			     for(i=0; i < obj.length; i++){
			    	 id1=1;
			    	 document.getElementById("startTime1").value=obj[i].starttime;
			    	 document.getElementById("endTime1").value=obj[i].endtime;
			     }
			     if(id1==0)
			    	 {
			    	 document.getElementById("startTime1").value='';
			    	 document.getElementById("endTime1").value='';
			    	 }
			 }
			 };
			 var action="getThePaneltime?appId="+appId+"csd"+id;
			 xhttp.open("POST", action, true);
			 xhttp.send(); 	
	}
	</script>
	<c:set var="placecomMap" value="${list4}" />  
	<c:set var="sedulerList" value="${list1}" />  
<script>
 $(document).ready(function(){
	 /*  $("#wing9room1").click(function(){
		var wing9room1 = $('#wing9room1').val();		
		$('#wing9Room1Id').val("wing9Room"+wing9room1);	       
	  }); 
	  $("#wing9room2").click(function(){
			var wing9room2 = $('#wing9room2').val();		
			$('#wing9Room2Id').val("wing9Room"+wing9room2);	       
		  }); 
	  $("#wing9room3").click(function(){
			var wing9room3 = $('#wing9room3').val();		
			$('#wing9Room3Id').val("wing9Room"+wing9room3);	       
		  }); 
	  $("#wing9room4").click(function(){
			var wing9room4 = $('#wing9room4').val();		
			$('#wing9Room4Id').val("wing9Room"+wing9room4);	       
		  }); 
	  $("#wing9room5").click(function(){
			var wing9room5 = $('#wing9room5').val();		
			$('#wing9Room5Id').val("wing9Room"+wing9room5);	       
		  }); 
	  $("#wing9room6").click(function(){
			var wing9room6 = $('#wing9room6').val();		
			$('#wing9Room6Id').val("wing9Room"+wing9room6);	       
		  }); 
	  $("#wing9room7").click(function(){
			var wing9room7 = $('#wing9room7').val();		
			$('#wing9Room7Id').val("wing9Room"+wing9room7);	       
	 }); 
	  
	  $("#wing9room8").click(function(){
			var wing9room8 = $('#wing9room8').val();		
			$('#wing9Room8Id').val("wing9Room"+wing9room8);	       
	 }); 
	  
	  $("#wing9room9").click(function(){
			var wing9room9 = $('#wing9room9').val();		
			$('#wing9Room9Id').val("wing9Room"+wing9room9);	       
	 });
	  
	  $("#wing9room10").click(function(){
			var wing9room10 = $('#wing9room10').val();		
			$('#wing9Room10Id').val("wing9Room"+wing9room10);	       
	 });
	  
	  $("#wing9room11").click(function(){
			var wing9room11 = $('#wing9room11').val();		
			$('#wing9Room11Id').val("wing9Room"+wing9room11);	       
	 });
	  
	  $("#wing9room12").click(function(){
			var wing9room12 = $('#wing9room12').val();		
			$('#wing9Room12Id').val("wing9Room"+wing9room12);	       
	 });
	  
	  $("#wing9room13").click(function(){
			var wing9room13 = $('#wing9room13').val();		
			$('#wing9Room13Id').val("wing9Room"+wing9room13);	       
	 });
	  $("#wing9room14").click(function(){
			var wing9room14 = $('#wing9room14').val();		
			$('#wing9Room14Id').val("wing9Room"+wing9room14);	       
	 });
	  
	  $("#wing9room15").click(function(){
			var wing9room15 = $('#wing9room15').val();		
			$('#wing9Room15Id').val("wing9Room"+wing9room15);	       
	 });
	  $("#wing9room16").click(function(){
			var wing9room16 = $('#wing9room16').val();		
			$('#wing9Room16Id').val("wing9Room"+wing9room16);	       
	 });
	  $("#wing10room1").click(function(){
			var wing10room1 = $('#wing10room1').val();		
			$('#wing10Room1Id').val("wing9Room"+wing10room1);	       
	 });
	  $("#wing10room2").click(function(){
			var wing10room2 = $('#wing10room2').val();		
			$('#wing10Room2Id').val("wing9Room"+wing10room2);	       
	 });
	  $("#wing10room3").click(function(){
			var wing10room3 = $('#wing10room3').val();		
			$('#wing10Room3Id').val("wing9Room"+wing10room3);	       
	 });
	  $("#wing10room4").click(function(){
			var wing10room4 = $('#wing10room4').val();		
			$('#wing10Room4Id').val("wing9Room"+wing10room4);	       
	 });
	  $("#wing10room5").click(function(){
			var wing10room5 = $('#wing10room5').val();		
			$('#wing10Room5Id').val("wing9Room"+wing10room5);	       
	 });
	  $("#wing10room6").click(function(){
			var wing10room6 = $('#wing10room6').val();		
			$('#wing10Room6Id').val("wing9Room"+wing10room6);	       
	 });
	  $("#wing10room7").click(function(){
			var wing10room7 = $('#wing10room7').val();		
			$('#wing10Room7Id').val("wing9Room"+wing10room7);	       
	 }); */
    
});
</script>

<script>
   function submitRoomAllotment(){
	   //alert("submitRoomAllotment");
	    document.getElementById('csd33').value=0;
	   var id=document.getElementById('xxxx').value;
	   var id14=document.getElementById('wingFirmSelectBoxId').value;
	   var id15=document.getElementById('wingRoleSelectBoxId').value;
	   var id13=document.getElementById('extraroom1').value;
	   var id1=document.getElementById("startTime").value;
	   var id2= document.getElementById("endTime").value;
	   var id3=id1.split(":");
	   var id4=(parseInt(id3[0])*60)+(parseInt(id3[1]));
	   var id5=id2.split(":");
	   var id6=(parseInt(id5[0])*60)+(parseInt(id5[1]));
	   var id7=document.getElementById("startTime1").value;
	   var id8= document.getElementById("endTime1").value;
	   var id9=id7.split(":");
	   var id10=(parseInt(id9[0])*60)+(parseInt(id9[1]));
	   var id11=id8.split(":");
	   var id12=(parseInt(id11[0])*60)+(parseInt(id11[1]));
	  // alert("start:"+id4+"end:"+id6);
	   var xhttp = new XMLHttpRequest();
			 xhttp.onreadystatechange = function() {
			 if (this.readyState == 4 && this.status == 200) {	 
			     var i;
			     var obj = JSON.parse(this.responseText);
			     for(i=0; i < obj.length; i++){
			    	// alert("starttime:"+obj[i].starttime+" endtime:"+obj[i].endtime);
			    	 if((id4>=obj[i].starttime && obj[i].endtime>=id4)|| (id6>=obj[i].starttime && obj[i].endtime>=id6))
			    	 {
			    		// alert("CSD");
			    		 document.getElementById('csd33').value=1;
			    	 }
			     }
			     if(id10>id4 || id12<id6)
			    	 {
			    	 alert("this time is not in range of panel allocated time");
			    	 return false;
			    	 }
			     else
			    	 {
			     var id7=document.getElementById('csd11').value;
				   var id8=document.getElementById('csd33').value;
				   if(id13==0)
				  {
				   if(id8==0)
					   {
				   if(id7==0)
					   {
				   document.getElementById("roomAllotmentFormId").action = "saveRoomAllocation";	
				   document.getElementById("roomAllotmentFormId").method = "post";
				   document.forms["roomAllotmentFormId"].submit();
					   }
				   else
					   {
					   document.getElementById("roomAllotmentFormId").action = "updateRoomAllocation";	
					   document.getElementById("roomAllotmentFormId").method = "post";
					   document.forms["roomAllotmentFormId"].submit();
					   } 
					   }
				   else
					   {
					   alert("room already alloted for this time");
					   return false;
					   }
				  }
				   else
					   {
					   alert("no of room more than allocated rooms");
					   return false;
					   }
			    	 }
			 }
			 };
			 var action="geAlltime?roomid="+id+"csd"+id14+"csd"+id15;
			 xhttp.open("POST", action, true);
			 xhttp.send(); 	
   }
   
   function deleteRoomAllotment(){
	   document.getElementById('deleteroom').value=document.getElementById('xxxx').value;
	   document.getElementById('deletefirm').value=document.getElementById('wingFirmSelectBoxId').value;
	   document.getElementById('deleterole').value=document.getElementById('wingRoleSelectBoxId').value;
	   document.getElementById("deleteroomallocation").action = "deleteroomallocation";	
	   document.getElementById("deleteroomallocation").method = "post";
	   document.forms["deleteroomallocation"].submit(); 
   }
 
   function wingSave(){
	  /*  var option=0;
		 var xhttp = new XMLHttpRequest();
		  xhttp.onreadystatechange = function() {
		    if (this.readyState == 4 && this.status == 200) {
		    	var jsonData=JSON.parse(this.responseText);
		    	for(var i=0;i<jsonData.length;i++){
		    		options=jsonData[i].totalPanel;
		    	}
				if (options == 1) {
					alert("number of panel and extra room doesn't match");
					return false;
				} else {
					document.getElementById("savedetails").action = "saveNegoAndSchedular";
					document.getElementById("savedetails").method = "post";
					document.forms["savedetails"].submit();
				}
			}
		};
		xhttp.open("POST", "geAllPanel", true);
		xhttp.send();  */
	   document.getElementById("savedetails").action = "saveNegoAndSchedular";
		document.getElementById("savedetails").method = "post";
		document.forms["savedetails"].submit();
	}
  
 function getTheDetails(id)
{
	 var clusterId=id.value;
	 var rolelist='';
	// alert(clusterId);
	  	var xhttp = new XMLHttpRequest();
		 xhttp.onreadystatechange = function() {
		 if (this.readyState == 4 && this.status == 200) {
		     var id2,id3,id4,id5,i,j,x,id7,id8;
		     var obj1 = JSON.parse(this.responseText);
		     var sedulerList = "${sedulerList}";
		    var placecomMap = "${placecomMap}";
		   // alert("sedulerList:: "+sedulerList); 
		   // alert("placecomMap :: "+placecomMap); 
		    var id1=placecomMap.split(",");
		    //alert("placecomMap:: "+id1.length); 
		    for(var i=0;i<id1.length;i++)
		    	{
		    	id2=id1[i].split("#");
		    	// rolelist+="<option value="+id2[0]+ ">"+id2[1]+"</option>";
		    	 /* rolelist+="<option value="+obj2[j].sid
	    		 obj2[j].sid == obj1[i].schedularid ? "selected" : '' +">"+obj2[j].sname+"</option>";
	    		 alert("csd::"+rolelist); */
		    	}
		    
		     /*  for(i=0; i < obj1.length; i++){
		    	 x=obj1[i].wingid;
		    	 alert(x);
		    	  for(j=0; j < id1.length; j++){
		    		  id2=id1[j].split("#");
		    		  if(j==0)
		    			  {
		    			  id4=id2[0].split("[");
		    			  id2[0]=id4[1];
		    			  }
		    		  if(j== (id1.length)-1)
		    			  {
		    			  id4=id2[1].split("]");
		    			  id2[1]=id4[0];
		    			  }
		    		  alert(id2[0]);
		    		 if(obj1[i].negoid==id2[0])
		    	     {
		    			 id3=id2[1];
		    			 alert(id3);
		    			 
		    		 
		    		 document.getElementById("placecomerId"+x).innerHTML="<option value='0'>"+id3+"</option>"+rolelist;
		    		 
		    	     }
		    	 } 
		    	 
		     }			   
		    */
		    	for(x=1;x<=12;x++)
		    		{
		    	document.getElementById("placecomerId"+x).options[0].selected=true;
		    	document.getElementById("sedulerId"+x).options[0].selected=true;
		    		}
		    	
		    	
		    
		      for(i=0; i < obj1.length; i++){
	    	 x=obj1[i].wingid;
	    	  id7=document.getElementById("placecomerId"+x);
	    	  id8=document.getElementById("sedulerId"+x);
	    	  document.getElementById("saveupdate"+x).value=obj1[i].key;
		    		 for (var j = 0; j < id7.options.length; j++) {
		    			 if (id7.options[j].value==obj1[i].negoid ) {
		    		        	id7.options[j].selected = true;
		    		        }
	    	 } 
		    		 for (var j = 0; j < id8.options.length; j++) {
		    			 if (id8.options[j].value==obj1[i].schedularid ) {
		    		        	id8.options[j].selected = true;
		    		        }
	    	 } 
	     }			   
		    	}
		 
		 };
		 var action="getTheClusterDetails?clusterId="+clusterId;
		 xhttp.open("POST", action, true);
		 xhttp.send(); 	
	
} 
 
 function roomvalidationforfirm()
 {
	 var options=0;
	 var appid=document.getElementById('wingFirmSelectBoxId').value;
	 var xhttp = new XMLHttpRequest();
	  xhttp.onreadystatechange = function() {
	    if (this.readyState == 4 && this.status == 200) {
	    	var jsonData=JSON.parse(this.responseText);
	    	for(var i=0;i<jsonData.length;i++){
	    		options=jsonData[i].totalPanel;
	    	}
			if (options == 1) {
				document.getElementById("extraroom1").value=1;
			}
			else
				{
				 document.getElementById("extraroom1").value=0;
				}
		}
	};
	 var action="geAllPanel?appid="+appid;
	xhttp.open("POST", action, true);
	xhttp.send();  
 }
	
</script>

</body>
</html>