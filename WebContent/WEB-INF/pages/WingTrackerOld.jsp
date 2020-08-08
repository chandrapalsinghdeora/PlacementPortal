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
				<select name="clusterId"  class="form-control" id="form-field-select-1" onchange="getFirmName(this);">
					<option value="0">Select cluster</option>
							<c:forEach items="${clusterList}" var="clist">
								<option value="${clist.clusterId}">${clist.clusterName}</option>
							</c:forEach>
				</select>
				 <input type="hidden" name="clusterName" value="" id="clusterNameId">
			</div>
			
			<form action=""></form>
			<div class="container">
				<div class="row">
					<div class="col-sm-12">
						<div class="row">
							<div class="col-sm-3 border_top margin_inner">
								<div class="row">
									<center>
										<b>Wing 9</b>
									</center>
									<div class="col-sm-3 draw_block ">
									
										<input type="button" class="btn btn-primary"
											data-toggle="modal" data-target="#wingRoomDialog" value="1" id="wing9room1">
										<input type="button" class="btn btn-primary"
											data-toggle="modal" data-target="#wingRoomDialog" value="2" id="wing9room2">
										<input type="button" class="btn btn-primary"
											data-toggle="modal" data-target="#wingRoomDialog" value="3" id="wing9room3">
										<input type="button" class="btn btn-primary"
											data-toggle="modal" data-target="#wingRoomDialog" value="4" id="wing9room4">
										<input type="button" class="btn btn-primary"
											data-toggle="modal" data-target="#wingRoomDialog" value="5" id="wing9room5">
										<input type="button" class="btn btn-primary"
											data-toggle="modal" data-target="#wingRoomDialog" value="6" id="wing9room6">
										<input type="button" class="btn btn-primary"
											data-toggle="modal" data-target="#wingRoomDialog" value="7" id="wing9room7">
										<!--  <input type="button" class="btn btn-primary"
											data-toggle="modal" data-target="#wingRoomDialog" value="15" id="wing9room15"> 	 -->
											
									</div>
									<div class="col-sm-6 set_drop_center">
										<select name="placecomer" id="placecomerId" class="form-control">
					<option value="0">Select</option>
							<c:forEach items="${placecomerList}" var="clist">
								<option value="${clist.key}">${clist.value}</option>
							</c:forEach>
				</select>
				<br> <br> <select name="seduler" id="sedulerId" class="form-control">
					<option value="0">Select</option>
							<c:forEach items="${sedulerList}" var="clist">
								<option value="${clist.sedulerId}">${clist.sedulerName}</option>
							</c:forEach>
				</select>
									</div>
									<div class="col-sm-3 draw_block ">
										<input type="button" class="btn btn-primary"
											data-toggle="modal" data-target="#wingRoomDialog" value="8" id="wing9room8">
										<input type="button" class="btn btn-primary"
											data-toggle="modal" data-target="#wingRoomDialog" value="9" id="wing9room9">
										<input type="button" class="btn btn-primary"
											data-toggle="modal" data-target="#wingRoomDialog" value="10" id="wing9room10">
										<input type="button" class="btn btn-primary"
											data-toggle="modal" data-target="#wingRoomDialog" value="11" id="wing9room11">
										<input type="button" class="btn btn-primary"
											data-toggle="modal" data-target="#wingRoomDialog" value="12" id="wing9room12">
										<input type="button" class="btn btn-primary"
											data-toggle="modal" data-target="#wingRoomDialog" value="13" id="wing9room13">
										 <input type="button" class="btn btn-primary"
											data-toggle="modal" data-target="#wingRoomDialog" value="14" id="wing9room14"> 
									</div>
								</div>
								<div class="row ">
									 <!-- <div class="col-sm-4 draw_block2 float_left "> -->
									 <input type="button" class="col-sm-4 draw_block2 float_left btn btn-primary"
											data-toggle="modal" data-target="#wingRoomDialog" value="15" id="wing9room15"> 
										<a></a>
									<!--  </div>  -->
									<div class="col-md-2"></div>
									<!-- <div class="col-sm-4 draw_block2 float_right"> -->
										<a></a>
										<input type="button" class="col-sm-4 draw_block2 float_left btn btn-primary"
											data-toggle="modal" data-target="#wingRoomDialog" value="16" id="wing9room16"> 
									<!-- </div> -->
								</div>
							</div>
							<div class="col-sm-3 border_top margin_inner">
								<div class="row">
									<center>
										<b>Wing 10</b>
									</center>
									<div class="col-sm-3 draw_block ">
										<input type="button" class="btn btn-primary"
											data-toggle="modal" data-target="#wingRoomDialog" value="1" id="wing10room1">
										<input type="button" class="btn btn-primary"
											data-toggle="modal" data-target="#wingRoomDialog" value="2" id="wing10room2">
										<input type="button" class="btn btn-primary"
											data-toggle="modal" data-target="#wingRoomDialog" value="3" id="wing10room3">
										<input type="button" class="btn btn-primary"
											data-toggle="modal" data-target="#wingRoomDialog" value="4" id="wing10room4">
										<input type="button" class="btn btn-primary"
											data-toggle="modal" data-target="#wingRoomDialog" value="5" id="wing10room5">
										<input type="button" class="btn btn-primary"
											data-toggle="modal" data-target="#wingRoomDialog" value="6" id="wing10room6">
										<input type="button" class="btn btn-primary"
											data-toggle="modal" data-target="#wingRoomDialog" value="7" id="wing10room7">
									</div>
									<div class="col-sm-6 set_drop_center">
										
										<select name="placecomer" id="placecomerId" class="form-control">
					<option value="0">Select</option>
							<c:forEach items="${placecomerList}" var="clist">
								<option value="${clist.key}">${clist.value}</option>
							</c:forEach>
				</select>
				<br> <br> <select name="seduler" id="sedulerId" class="form-control">
					<option value="0">Select</option>
							<c:forEach items="${sedulerList}" var="clist">
								<option value="${clist.sedulerId}">${clist.sedulerName}</option>
							</c:forEach>
				</select>
									</div>
									<div class="col-sm-3 draw_block ">
										<input type="button" class="btn btn-primary"
											data-toggle="modal" data-target="#wingRoomDialog" value="8" id="wing10room8">
										<input type="button" class="btn btn-primary"
											data-toggle="modal" data-target="#wingRoomDialog" value="9" id="wing10room9">
										<input type="button" class="btn btn-primary"
											data-toggle="modal" data-target="#wingRoomDialog" value="10" id="wing10room10">
										<input type="button" class="btn btn-primary"
											data-toggle="modal" data-target="#wingRoomDialog" value="11" id="wing10room11">
										<input type="button" class="btn btn-primary"
											data-toggle="modal" data-target="#wingRoomDialog" value="12" id="wing10room12">
										<input type="button" class="btn btn-primary"
											data-toggle="modal" data-target="#wingRoomDialog" value="13" id="wing10room13">
										<input type="button" class="btn btn-primary"
											data-toggle="modal" data-target="#wingRoomDialog" value="14" id="wing10room14">
									</div>
								</div>
								<div class="row ">
									<!-- <div class="col-sm-4 draw_block2 float_left "> -->
									 <input type="button" class="col-sm-4 draw_block2 float_left btn btn-primary"
											data-toggle="modal" data-target="#wingRoomDialog" value="15" id="wing10room15"> 
										<a></a>
									<!-- </div> -->
									<div class="col-md-2"></div>
									<!-- <div class="col-sm-4 draw_block2 float_right"> -->
									<input type="button" class="col-sm-4 draw_block2 float_left btn btn-primary"
											data-toggle="modal" data-target="#wingRoomDialog" value="16" id="wing10room16">
										<a></a>
									<!-- </div> -->
								</div>
							</div>
							<div class="col-sm-3 border_top margin_inner">
								<div class="row">
									<center>
										<b>Wing 11</b>
									</center>
									<div class="col-sm-3 draw_block ">
										<input type="button" class="btn btn-primary"
											data-toggle="modal" data-target="#C-role-edit" value="">
										<input type="button" class="btn btn-primary"
											data-toggle="modal" data-target="#C-role-edit" value="">
										<input type="button" class="btn btn-primary"
											data-toggle="modal" data-target="#C-role-edit" value="">
										<input type="button" class="btn btn-primary"
											data-toggle="modal" data-target="#C-role-edit" value="">
										<input type="button" class="btn btn-primary"
											data-toggle="modal" data-target="#C-role-edit" value="">
										<input type="button" class="btn btn-primary"
											data-toggle="modal" data-target="#C-role-edit" value="">
										<input type="button" class="btn btn-primary"
											data-toggle="modal" data-target="#C-role-edit" value="">
									</div>
									<div class="col-sm-6 set_drop_center">
										<select name="placecomer" id="placecomerId" class="form-control">
					<option value="0">Select</option>
							<c:forEach items="${placecomerList}" var="clist">
								<option value="${clist.key}">${clist.value}</option>
							</c:forEach>
				</select>
				<br> <br> <select name="seduler" id="sedulerId" class="form-control">
					<option value="0">Select</option>
							<c:forEach items="${sedulerList}" var="clist">
								<option value="${clist.sedulerId}">${clist.sedulerName}</option>
							</c:forEach>
				</select>
									</div>
									<div class="col-sm-3 draw_block ">
										<input type="button" class="btn btn-primary"
											data-toggle="modal" data-target="#C-role-edit" value="">
										<input type="button" class="btn btn-primary"
											data-toggle="modal" data-target="#C-role-edit" value="">
										<input type="button" class="btn btn-primary"
											data-toggle="modal" data-target="#C-role-edit" value="">
										<input type="button" class="btn btn-primary"
											data-toggle="modal" data-target="#C-role-edit" value="">
										<input type="button" class="btn btn-primary"
											data-toggle="modal" data-target="#C-role-edit" value="">
										<input type="button" class="btn btn-primary"
											data-toggle="modal" data-target="#C-role-edit" value="">
										<input type="button" class="btn btn-primary"
											data-toggle="modal" data-target="#C-role-edit" value="">
									</div>
								</div>
								<div class="row ">
									<div class="col-sm-4 draw_block2 float_left ">
										<a></a>
									</div>
									<div class="col-md-2"></div>
									<div class="col-sm-4 draw_block2 float_right">
										<a></a>
									</div>
								</div>
							</div>
							<div class="col-sm-3 border_top margin_inner">
								<div class="row">
									<center>
										<b>Wing 12</b>
									</center>
									<div class="col-sm-3 draw_block ">
										<input type="button" class="btn btn-primary"
											data-toggle="modal" data-target="#C-role-edit" value="">
										<input type="button" class="btn btn-primary"
											data-toggle="modal" data-target="#C-role-edit" value="">
										<input type="button" class="btn btn-primary"
											data-toggle="modal" data-target="#C-role-edit" value="">
										<input type="button" class="btn btn-primary draw_block_large"
											data-toggle="modal" data-target="#C-role-edit" value="">
										<input type="button" class="btn btn-primary draw_block_large"
											data-toggle="modal" data-target="#C-role-edit" value="">
									</div>


									<div class="col-sm-6 set_drop_center">
										<select name="placecomer" id="placecomerId" class="form-control">
					<option value="0">Select</option>
							<c:forEach items="${placecomerList}" var="clist">
								<option value="${clist.key}">${clist.value}</option>
							</c:forEach>
				</select>
				<br> <br> <select name="seduler" id="sedulerId" class="form-control">
					<option value="0">Select</option>
							<c:forEach items="${sedulerList}" var="clist">
								<option value="${clist.sedulerId}">${clist.sedulerName}</option>
							</c:forEach>
				</select>
									</div>
									<div class="col-sm-3 draw_block ">
										<input type="button" class="btn btn-primary"
											data-toggle="modal" data-target="#C-role-edit" value="">
										<input type="button" class="btn btn-primary"
											data-toggle="modal" data-target="#C-role-edit" value="">
										<input type="button" class="btn btn-primary"
											data-toggle="modal" data-target="#C-role-edit" value="">
										<input type="button" class="btn btn-primary draw_block_large"
											data-toggle="modal" data-target="#C-role-edit" value="">
										<input type="button" class="btn btn-primary draw_block_large"
											data-toggle="modal" data-target="#C-role-edit" value="">
									</div>
								</div>
								<div class="row ">
									<div class="col-sm-4 draw_block2 float_left ">
										<a></a>
									</div>
									<div class="col-md-2"></div>
									<div class="col-sm-4 draw_block2 float_right">
										<a></a>
									</div>
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
										<input type="button" class="btn btn-primary"
											data-toggle="modal" data-target="#C-role-edit" value="">
										<input type="button" class="btn btn-primary"
											data-toggle="modal" data-target="#C-role-edit" value="">
										<input type="button" class="btn btn-primary"
											data-toggle="modal" data-target="#C-role-edit" value="">
										<input type="button" class="btn btn-primary"
											data-toggle="modal" data-target="#C-role-edit" value="">
										<input type="button" class="btn btn-primary"
											data-toggle="modal" data-target="#C-role-edit" value="">
										<input type="button" class="btn btn-primary"
											data-toggle="modal" data-target="#C-role-edit" value="">
										<input type="button" class="btn btn-primary"
											data-toggle="modal" data-target="#C-role-edit" value="">
									</div>
									<div class="col-sm-6 set_drop_center">
										<select name="placecomer" id="placecomerId" class="form-control">
					<option value="0">Select</option>
							<c:forEach items="${placecomerList}" var="clist">
								<option value="${clist.key}">${clist.value}</option>
							</c:forEach>
				</select>
				<br> <br> <select name="seduler" id="sedulerId" class="form-control">
					<option value="0">Select</option>
							<c:forEach items="${sedulerList}" var="clist">
								<option value="${clist.sedulerId}">${clist.sedulerName}</option>
							</c:forEach>
				</select>
									</div>
									<div class="col-sm-3 draw_block ">
										<input type="button" class="btn btn-primary"
											data-toggle="modal" data-target="#C-role-edit" value="">
										<input type="button" class="btn btn-primary"
											data-toggle="modal" data-target="#C-role-edit" value="">
										<input type="button" class="btn btn-primary"
											data-toggle="modal" data-target="#C-role-edit" value="">
										<input type="button" class="btn btn-primary"
											data-toggle="modal" data-target="#C-role-edit" value="">
										<input type="button" class="btn btn-primary"
											data-toggle="modal" data-target="#C-role-edit" value="">
										<input type="button" class="btn btn-primary"
											data-toggle="modal" data-target="#C-role-edit" value="">
										<input type="button" class="btn btn-primary"
											data-toggle="modal" data-target="#C-role-edit" value="">
									</div>
								</div>
								<div class="row ">
									<div class="col-sm-4 draw_block2 float_left ">
										<a></a>
									</div>
									<div class="col-md-2"></div>
									<div class="col-sm-4 draw_block2 float_right">
										<a></a>
									</div>
								</div>
							</div>
							<div class="col-sm-3 border_top">
								<div class="row">
									<center>
										<b>Wing 6</b>
									</center>
									<div class="col-sm-3 draw_block ">
										<input type="button" class="btn btn-primary"
											data-toggle="modal" data-target="#C-role-edit" value="">
										<input type="button" class="btn btn-primary"
											data-toggle="modal" data-target="#C-role-edit" value="">
										<input type="button" class="btn btn-primary"
											data-toggle="modal" data-target="#C-role-edit" value="">
										<input type="button" class="btn btn-primary draw_block_large"
											data-toggle="modal" data-target="#C-role-edit" value="">
										<input type="button" class="btn btn-primary draw_block_large"
											data-toggle="modal" data-target="#C-role-edit" value="">
									</div>
									<div class="col-sm-6 set_drop_center">
										<select name="placecomer" id="placecomerId" class="form-control">
					<option value="0">Select</option>
							<c:forEach items="${placecomerList}" var="clist">
								<option value="${clist.key}">${clist.value}</option>
							</c:forEach>
				</select>
				<br> <br> <select name="seduler" id="sedulerId" class="form-control">
					<option value="0">Select</option>
							<c:forEach items="${sedulerList}" var="clist">
								<option value="${clist.sedulerId}">${clist.sedulerName}</option>
							</c:forEach>
				</select>
									</div>
									<div class="col-sm-3 draw_block ">
										<input type="button" class="btn btn-primary"
											data-toggle="modal" data-target="#C-role-edit" value="">
										<input type="button" class="btn btn-primary"
											data-toggle="modal" data-target="#C-role-edit" value="">
										<input type="button" class="btn btn-primary"
											data-toggle="modal" data-target="#C-role-edit" value="">
										<input type="button" class="btn btn-primary draw_block_large"
											data-toggle="modal" data-target="#C-role-edit" value="">
										<input type="button" class="btn btn-primary draw_block_large"
											data-toggle="modal" data-target="#C-role-edit" value="">
									</div>
								</div>
								<div class="row ">
									<div class="col-sm-4 draw_block2 float_left ">
										<a></a>
									</div>
									<div class="col-md-2"></div>
									<div class="col-sm-4 draw_block2 float_right">
										<a></a>
									</div>
								</div>
							</div>
							<div class="col-sm-3 border_top">
								<div class="row">
									<center>
										<b>Wing 7</b>
									</center>
									<div class="col-sm-3 draw_block ">
										<input type="button" class="btn btn-primary"
											data-toggle="modal" data-target="#C-role-edit" value="">
										<input type="button" class="btn btn-primary"
											data-toggle="modal" data-target="#C-role-edit" value="">
										<input type="button" class="btn btn-primary"
											data-toggle="modal" data-target="#C-role-edit" value="">
										<input type="button" class="btn btn-primary"
											data-toggle="modal" data-target="#C-role-edit" value="">
										<input type="button" class="btn btn-primary"
											data-toggle="modal" data-target="#C-role-edit" value="">
										<input type="button" class="btn btn-primary"
											data-toggle="modal" data-target="#C-role-edit" value="">
										<input type="button" class="btn btn-primary"
											data-toggle="modal" data-target="#C-role-edit" value="">
									</div>
									<div class="col-sm-6 set_drop_center">
										<select name="placecomer" id="placecomerId" class="form-control">
					<option value="0">Select</option>
							<c:forEach items="${placecomerList}" var="clist">
								<option value="${clist.key}">${clist.value}</option>
							</c:forEach>
				</select>
				<br> <br> <select name="seduler" id="sedulerId" class="form-control">
					<option value="0">Select</option>
							<c:forEach items="${sedulerList}" var="clist">
								<option value="${clist.sedulerId}">${clist.sedulerName}</option>
							</c:forEach>
				</select>
									</div>
									<div class="col-sm-3 draw_block ">
										<input type="button" class="btn btn-primary"
											data-toggle="modal" data-target="#C-role-edit" value="">
										<input type="button" class="btn btn-primary"
											data-toggle="modal" data-target="#C-role-edit" value="">
										<input type="button" class="btn btn-primary"
											data-toggle="modal" data-target="#C-role-edit" value="">
										<input type="button" class="btn btn-primary"
											data-toggle="modal" data-target="#C-role-edit" value="">
										<input type="button" class="btn btn-primary"
											data-toggle="modal" data-target="#C-role-edit" value="">
										<input type="button" class="btn btn-primary"
											data-toggle="modal" data-target="#C-role-edit" value="">
										<input type="button" class="btn btn-primary"
											data-toggle="modal" data-target="#C-role-edit" value="">
									</div>
								</div>
								<div class="row ">
									<div class="col-sm-4 draw_block2 float_left ">
										<a></a>
									</div>
									<div class="col-md-2"></div>
									<div class="col-sm-4 draw_block2 float_right">
										<a></a>
									</div>
								</div>
							</div>
							<div class="col-sm-3 border_top">
								<div class="row">
									<center>
										<b>Wing 8</b>
									</center>
									<div class="col-sm-3 draw_block ">
										<input type="button" class="btn btn-primary"
											data-toggle="modal" data-target="#C-role-edit" value="">
										<input type="button" class="btn btn-primary"
											data-toggle="modal" data-target="#C-role-edit" value="">
										<input type="button" class="btn btn-primary"
											data-toggle="modal" data-target="#C-role-edit" value="">
										<input type="button" class="btn btn-primary"
											data-toggle="modal" data-target="#C-role-edit" value="">
										<input type="button" class="btn btn-primary"
											data-toggle="modal" data-target="#C-role-edit" value="">
										<input type="button" class="btn btn-primary"
											data-toggle="modal" data-target="#C-role-edit" value="">
										<input type="button" class="btn btn-primary"
											data-toggle="modal" data-target="#C-role-edit" value="">
									</div>
									<div class="col-sm-6 set_drop_center">
										<select name="placecomer" id="placecomerId" class="form-control">
					<option value="0">Select</option>
							<c:forEach items="${placecomerList}" var="clist">
								<option value="${clist.key}">${clist.value}</option>
							</c:forEach>
				</select>
				<br> <br> <select name="seduler" id="sedulerId" class="form-control">
					<option value="0">Select</option>
							<c:forEach items="${sedulerList}" var="clist">
								<option value="${clist.sedulerId}">${clist.sedulerName}</option>
							</c:forEach>
				</select>
									</div>
									<div class="col-sm-3 draw_block ">
										<input type="button" class="btn btn-primary"
											data-toggle="modal" data-target="#C-role-edit" value="">
										<input type="button" class="btn btn-primary"
											data-toggle="modal" data-target="#C-role-edit" value="">
										<input type="button" class="btn btn-primary"
											data-toggle="modal" data-target="#C-role-edit" value="">
										<input type="button" class="btn btn-primary"
											data-toggle="modal" data-target="#C-role-edit" value="">
										<input type="button" class="btn btn-primary"
											data-toggle="modal" data-target="#C-role-edit" value="">
										<input type="button" class="btn btn-primary"
											data-toggle="modal" data-target="#C-role-edit" value="">
										<input type="button" class="btn btn-primary"
											data-toggle="modal" data-target="#C-role-edit" value="">
									</div>
								</div>
								<div class="row ">
									<div class="col-sm-4 draw_block2 float_left ">
										<a></a>
									</div>
									<div class="col-md-2"></div>
									<div class="col-sm-4 draw_block2 float_right">
										<a></a>
									</div>
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
										<input type="button" class="btn btn-primary"
											data-toggle="modal" data-target="#C-role-edit" value="">
										<input type="button" class="btn btn-primary"
											data-toggle="modal" data-target="#C-role-edit" value="">
										<input type="button" class="btn btn-primary"
											data-toggle="modal" data-target="#C-role-edit" value="">
										<input type="button" class="btn btn-primary"
											data-toggle="modal" data-target="#C-role-edit" value="">
										<input type="button" class="btn btn-primary"
											data-toggle="modal" data-target="#C-role-edit" value="">
										<input type="button" class="btn btn-primary"
											data-toggle="modal" data-target="#C-role-edit" value="">
										<input type="button" class="btn btn-primary"
											data-toggle="modal" data-target="#C-role-edit" value="">
									</div>
									<div class="col-sm-6 set_drop_center">
										<select name="placecomer" id="placecomerId" class="form-control">
					<option value="0">Select</option>
							<c:forEach items="${placecomerList}" var="clist">
								<option value="${clist.key}">${clist.value}</option>
							</c:forEach>
				</select>
				<br> <br> <select name="seduler" id="sedulerId" class="form-control">
					<option value="0">Select</option>
							<c:forEach items="${sedulerList}" var="clist">
								<option value="${clist.sedulerId}">${clist.sedulerName}</option>
							</c:forEach>
				</select>
									</div>
									<div class="col-sm-3 draw_block ">
										<input type="button" class="btn btn-primary"
											data-toggle="modal" data-target="#C-role-edit" value="">
										<input type="button" class="btn btn-primary"
											data-toggle="modal" data-target="#C-role-edit" value="">
										<input type="button" class="btn btn-primary"
											data-toggle="modal" data-target="#C-role-edit" value="">
										<input type="button" class="btn btn-primary"
											data-toggle="modal" data-target="#C-role-edit" value="">
										<input type="button" class="btn btn-primary"
											data-toggle="modal" data-target="#C-role-edit" value="">
										<input type="button" class="btn btn-primary"
											data-toggle="modal" data-target="#C-role-edit" value="">
										<input type="button" class="btn btn-primary"
											data-toggle="modal" data-target="#C-role-edit" value="">
									</div>
								</div>
								<div class="row ">
									<div class="col-sm-4 draw_block2 float_left ">
										<a></a>
									</div>
									<div class="col-md-2"></div>
									<div class="col-sm-4 draw_block2 float_right">
										<a></a>
									</div>
								</div>
							</div>
							<div class="col-sm-3 border_top">
								<div class="row">
									<center>
										<b>Wing 2</b>
									</center>
									<div class="col-sm-3 draw_block ">
										<input type="button" class="btn btn-primary"
											data-toggle="modal" data-target="#C-role-edit" value="">
										<input type="button" class="btn btn-primary"
											data-toggle="modal" data-target="#C-role-edit" value="">
										<input type="button" class="btn btn-primary"
											data-toggle="modal" data-target="#C-role-edit" value="">
										<input type="button" class="btn btn-primary"
											data-toggle="modal" data-target="#C-role-edit" value="">
										<input type="button" class="btn btn-primary"
											data-toggle="modal" data-target="#C-role-edit" value="">
										<input type="button" class="btn btn-primary"
											data-toggle="modal" data-target="#C-role-edit" value="">
										<input type="button" class="btn btn-primary"
											data-toggle="modal" data-target="#C-role-edit" value="">
									</div>
									<div class="col-sm-6 set_drop_center">
										<select name="placecomer" id="placecomerId" class="form-control">
					<option value="0">Select</option>
							<c:forEach items="${placecomerList}" var="clist">
								<option value="${clist.key}">${clist.value}</option>
							</c:forEach>
				</select>
				<br> <br> <select name="seduler" id="sedulerId" class="form-control">
					<option value="0">Select</option>
							<c:forEach items="${sedulerList}" var="clist">
								<option value="${clist.sedulerId}">${clist.sedulerName}</option>
							</c:forEach>
				</select>
									</div>
									<div class="col-sm-3 draw_block ">
										<input type="button" class="btn btn-primary"
											data-toggle="modal" data-target="#C-role-edit" value="">
										<input type="button" class="btn btn-primary"
											data-toggle="modal" data-target="#C-role-edit" value="">
										<input type="button" class="btn btn-primary"
											data-toggle="modal" data-target="#C-role-edit" value="">
										<input type="button" class="btn btn-primary"
											data-toggle="modal" data-target="#C-role-edit" value="">
										<input type="button" class="btn btn-primary"
											data-toggle="modal" data-target="#C-role-edit" value="">
										<input type="button" class="btn btn-primary"
											data-toggle="modal" data-target="#C-role-edit" value="">
										<input type="button" class="btn btn-primary"
											data-toggle="modal" data-target="#C-role-edit" value="">
									</div>
								</div>
								<div class="row ">
									<div class="col-sm-4 draw_block2 float_left ">
										<a></a>
									</div>
									<div class="col-md-2"></div>
									<div class="col-sm-4 draw_block2 float_right">
										<a></a>
									</div>
								</div>
							</div>
							<div class="col-sm-3 border_top">
								<div class="row">
									<center>
										<b>Wing 3</b>
									</center>
									<div class="col-sm-3 draw_block ">
										<input type="button" class="btn btn-primary"
											data-toggle="modal" data-target="#C-role-edit" value="">
										<input type="button" class="btn btn-primary"
											data-toggle="modal" data-target="#C-role-edit" value="">
										<input type="button" class="btn btn-primary"
											data-toggle="modal" data-target="#C-role-edit" value="">
										<input type="button" class="btn btn-primary"
											data-toggle="modal" data-target="#C-role-edit" value="">
										<input type="button" class="btn btn-primary"
											data-toggle="modal" data-target="#C-role-edit" value="">
										<input type="button" class="btn btn-primary"
											data-toggle="modal" data-target="#C-role-edit" value="">
										<input type="button" class="btn btn-primary"
											data-toggle="modal" data-target="#C-role-edit" value="">
									</div>
									<div class="col-sm-6 set_drop_center">
										<select name="placecomer" id="placecomerId" class="form-control">
					<option value="0">Select</option>
							<c:forEach items="${placecomerList}" var="clist">
								<option value="${clist.key}">${clist.value}</option>
							</c:forEach>
				</select>
				<br> <br> <select name="seduler" id="sedulerId" class="form-control">
					<option value="0">Select</option>
							<c:forEach items="${sedulerList}" var="clist">
								<option value="${clist.sedulerId}">${clist.sedulerName}</option>
							</c:forEach>
				</select>
									</div>
									<div class="col-sm-3 draw_block ">
										<input type="button" class="btn btn-primary"
											data-toggle="modal" data-target="#C-role-edit" value="">
										<input type="button" class="btn btn-primary"
											data-toggle="modal" data-target="#C-role-edit" value="">
										<input type="button" class="btn btn-primary"
											data-toggle="modal" data-target="#C-role-edit" value="">
										<input type="button" class="btn btn-primary"
											data-toggle="modal" data-target="#C-role-edit" value="">
										<input type="button" class="btn btn-primary"
											data-toggle="modal" data-target="#C-role-edit" value="">
										<input type="button" class="btn btn-primary"
											data-toggle="modal" data-target="#C-role-edit" value="">
										<input type="button" class="btn btn-primary"
											data-toggle="modal" data-target="#C-role-edit" value="">
									</div>
								</div>
								<div class="row ">
									<div class="col-sm-4 draw_block2 float_left ">
										<a></a>
									</div>
									<div class="col-md-2"></div>
									<div class="col-sm-4 draw_block2 float_right">
										<a></a>
									</div>
								</div>
							</div>
							<div class="col-sm-3 border_top">
								<div class="row">
									<center>
										<b>Wing 4</b>
									</center>
									<div class="col-sm-3 draw_block ">
										<input type="button" class="btn btn-primary"
											data-toggle="modal" data-target="#C-role-edit" value="">
										<input type="button" class="btn btn-primary"
											data-toggle="modal" data-target="#C-role-edit" value="">
										<input type="button" class="btn btn-primary"
											data-toggle="modal" data-target="#C-role-edit" value="">
										<input type="button" class="btn btn-primary"
											data-toggle="modal" data-target="#C-role-edit" value="">
										<input type="button" class="btn btn-primary"
											data-toggle="modal" data-target="#C-role-edit" value="">
										<input type="button" class="btn btn-primary"
											data-toggle="modal" data-target="#C-role-edit" value="">
										<input type="button" class="btn btn-primary"
											data-toggle="modal" data-target="#C-role-edit" value="">
									</div>
									<div class="col-sm-6 set_drop_center">
										<select name="placecomer" id="placecomerId" class="form-control">
					<option value="0">Select</option>
							<c:forEach items="${placecomerList}" var="clist">
								<option value="${clist.key}">${clist.value}</option>
							</c:forEach>
				</select>
				<br> <br> <select name="seduler" id="sedulerId" class="form-control">
					<option value="0">Select</option>
							<c:forEach items="${sedulerList}" var="clist">
								<option value="${clist.sedulerId}">${clist.sedulerName}</option>
							</c:forEach>
				</select>
									</div>
									<div class="col-sm-3 draw_block ">
										<input type="button" class="btn btn-primary"
											data-toggle="modal" data-target="#C-role-edit" value="">
										<input type="button" class="btn btn-primary"
											data-toggle="modal" data-target="#C-role-edit" value="">
										<input type="button" class="btn btn-primary"
											data-toggle="modal" data-target="#C-role-edit" value="">
										<input type="button" class="btn btn-primary"
											data-toggle="modal" data-target="#C-role-edit" value="">
										<input type="button" class="btn btn-primary"
											data-toggle="modal" data-target="#C-role-edit" value="">
										<input type="button" class="btn btn-primary"
											data-toggle="modal" data-target="#C-role-edit" value="">
										<input type="button" class="btn btn-primary"
											data-toggle="modal" data-target="#C-role-edit" value="">
									</div>
								</div>
								<div class="row">
									<div class="col-sm-4 draw_block2 float_left ">
										<a></a>
									</div>
									<div class="col-md-2"></div>
									<div class="col-sm-4 draw_block2 float_right">
										<a></a>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="col-sm-12">
						<center class="save_fin_btn">
							<button class="button_size" type="submit" id=""
								class="btn btn-success btn-block">Save</button>
							<button class="button_size" type="submit" id=""
								class="btn btn-success btn-block">Finalise</button>
						</center>
					</div>
				</div>
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
				<input type="hidden" name="wing9Room1" value="" id="wing9Room1Id">
				<input type="hidden" name="wing9Room2" value="" id="wing9Room2Id">
				<input type="hidden" name="wing9Room3" value="" id="wing9Room3Id">
				<input type="hidden" name="wing9Room4" value="" id="wing9Room4Id">
				<input type="hidden" name="wing9Room5" value="" id="wing9Room5Id">
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
							<select name="roleId" class="form-control" id="wingRoleSelectBoxId">
								
							</select>
						</div>
					</div>



					<div class="row">
						<div class="  border">
							<label class="col-sm-3">Start Time </label>
							<div class="col-sm-3 inpur_box">
								<div class="form-group">
									<div class='input-group date' id='datetimepicker3'>
										<input type='text' class="form-control" /> <span
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
										<input type='text' class="form-control" /> <span
											class="input-group-addon"> <span
											class="glyphicon glyphicon-time"></span>
										</span>
									</div>
								</div>
							</div>
						</div>
					</div>

                </form>
				</div>
				
				<div class="modal-footer">
					<!-- <button type="button" class="btn btn-default">Add Firm</button> -->
					<!-- <button type="button" class="btn btn-default" data-dismiss="modal">Save</button> -->
					<button type="button" class="btn btn-default" onclick="submitRoomAllotment();">Save</button>
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
<script type="text/javascript" src="js/bootstrap.js"></script> 

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
				format : 'LT'
			});
		});

		$(function() {
			$('#datetimepicker4').datetimepicker({
				format : 'LT'
			});
		});
	</script>
	
	
	<script >
	
	function getFirmName(cid){
		//alert("cid-"+cid.value);
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
			     document.getElementById("wingFirmSelectBoxId").innerHTML="<option value='0'>Select</option>"+firmlist;		    
			 }
			 };
			 var action="getFirmNameByCid?cid="+clusterId;
			 xhttp.open("POST", action, true);
			 xhttp.send(); 	
	   }
	
	
	function getRoleName(appId){
		   var appId=appId.value;
		   alert(appId);
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
		
	</script>
	
<script>
 $(document).ready(function(){
	  $("#wing9room1").click(function(){
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
	 });
    
});
</script>

<script>
   function submitRoomAllotment(){
	   alert("submitRoomAllotment");
	   document.getElementById("roomAllotmentFormId").action = "saveRoomAllocation";	
	   document.getElementById("roomAllotmentFormId").method = "post";
	   document.forms["roomAllotmentFormId"].submit();
   }
</script>

</body>
</html>