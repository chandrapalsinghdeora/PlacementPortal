<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
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
<!-- <link rel="stylesheet" type="text/css" href="css/pignose.calendar.css" /> -->
<link rel="stylesheet" type="text/css" href="css/style.css" media="screen" />
<link rel="stylesheet" type="text/css" href="css/responsive.css" media="screen" />
<!-- <link rel="stylesheet" href="assets/css/jquery-ui.custom.min.css" /> -->
<link rel="stylesheet" href="assets/css/fullcalendar.min.css" />

<!-- text fonts -->

<!-- ace styles -->
<link rel="stylesheet" href="assets/css/ace.min.css" class="ace-main-stylesheet" id="main-ace-style" />

<!--[if lte IE 9]>
			<link rel="stylesheet" href="assets/css/ace-part2.min.css" class="ace-main-stylesheet" />
		<![endif]-->
<link rel="stylesheet" href="assets/css/ace-skins.min.css" />
<link rel="stylesheet" href="assets/css/ace-rtl.min.css" />

<!--[if lte IE 9]>
		  <link rel="stylesheet" href="assets/css/ace-ie.min.css" />
		<![endif]-->

<!-- inline styles related to this page -->

<!-- ace settings handler -->
<script src="assets/js/ace-extra.min.js"></script>
</head>
<body class="no-skin">
<jsp:include page="commonJsp/Header.jsp" />
<div class="clearfix"></div>
<div class="breadcumb-wrapper">
  <div class="clearfix">
    <div class="pull-left">
      <ul class="list-inline link-list">
        <li><a href="#">Home</a></li>
        <li><a href="#">Forum</a></li>
        <li>User</li>
      </ul>
    </div>
  </div>
</div>
<div class="clearfix"></div>
<div class="clearfix"></div>
<div class="container-fluid">
  <div class=""> 
    
    <!-- panel preview -->
    
    <div class="clearfix"></div>
    <div class="container-fluid">
      <div class="mail-box">
        <aside class="sm-side">
                       <!--  <div class="user-head text-center">
                         
                          <div class="btn-group hidden-phone">
                                 <a data-toggle="dropdown" href="#" class="btn mini blue" aria-expanded="false" title="Open Menu">
                                     Menu
                                    <i class="fa fa-bars" aria-hidden="true"></i>

                                 </a>
                                 <ul class="dropdown-menu">
                                     <li><a href="getInboxData"> Inbox</a></li>
                                      <li><a href="getCalander"> Calender</a></li>
                                     <li><a href="#"> Delete</a></li>
                                 </ul>
                             </div>  
                         
                      </div> 
                      <div class="inbox-body">
                         <!--  <a href="#myModal" data-toggle="modal"  title="Compose"    class="btn btn-compose">
                              Compose
                          </a> 
                          <!-- Modal 
                          <div aria-hidden="true" aria-labelledby="myModalLabel" role="dialog" tabindex="-1" id="myModal" class="modal fade" style="display: none;">
                              <div class="modal-dialog">
                                  <div class="modal-content">
                                      <div class="modal-header">
                                          <button type="button" class="close" data-dismiss="modal">&times;</button>
                                          <h4 class="modal-title">Compose</h4>
                                      </div>
                                      <div class="modal-body">
                                          <form role="form" class="form-horizontal">
                                              <div class="form-group">
                                                  <label class="col-lg-2 control-label">To</label>
                                                  <div class="col-lg-10">
                                                      <input type="text" placeholder="" id="inputEmail1" class="form-control">
                                                  </div>
                                              </div>
                                              <div class="form-group">
                                                  <label class="col-lg-2 control-label">Cc / Bcc</label>
                                                  <div class="col-lg-10">
                                                      <input type="text" placeholder="" id="cc" class="form-control">
                                                  </div>
                                              </div>
                                              <div class="form-group">
                                                  <label class="col-lg-2 control-label">Subject</label>
                                                  <div class="col-lg-10">
                                                      <input type="text" placeholder="" id="inputPassword1" class="form-control">
                                                  </div>
                                              </div>
                                              <div class="form-group">
                                                  <label class="col-lg-2 control-label">Message</label>
                                                  <div class="col-lg-10">
                                                      <textarea rows="3" cols="30" class="form-control" id="" name=""></textarea>
                                                  </div>
                                              </div>

                                              <div class="form-group">
                                                  <div class="col-lg-offset-2 col-lg-10">
                                                      <span class="btn green fileinput-button">
                                                        <i class="fa fa-plus fa fa-white"></i>
                                                        <span>Attachment</span>
                                                        <input type="file" name="files[]" multiple="">
                                                      </span>
                                                      <button class="btn btn-send" type="submit">Send</button>
                                                  </div>
                                              </div>
                                          </form>
                                      </div>
                                  </div><!-- /.modal-content 
                              </div><!-- /.modal-dialog 
                          </div>
                          
                          <!-- /.modal 
                      </div>-->
						<ul class="inbox-nav inbox-divider">
							<li class="active"><a href="getInboxData"><i
									class="fa fa-inbox"></i> Inbox <c:if test="${count ne 0}">
										<span class="label label-danger pull-right">${count}</span>
									</c:if></a> <!--  <a href="inbox-body.jsp"><i class="fa fa-inbox"></i> Inbox body</a> -->

							</li>
							<li><a href="#create-label" data-toggle="modal"
								title="Label" onclick="clearLabel();"><i class="fa fa-envelope-o"></i> Create New
									Label</a></li>
							<li>

								<div aria-hidden="true" aria-labelledby="myModalLabel"
									role="dialog" tabindex="-1" id="create-label"
									class="modal fade" style="display: none;">
									<div class="modal-dialog modal-sm"">
										<div class="modal-content row">
											<div class="modal-header">
												<button aria-hidden="true" type="button" class="close" data-dismiss="modal">&times;</button>
												<h4 class="modal-title">New Label</h4>
											</div>
											<div class="modal-body">
												<form role="form" class="form-horizontal" action="addLabel"
													method="post">
													<div class="form-group">
														<label class="col-lg-12 control-label">Please
															enter a new label name</label>
														<div class="col-lg-12">
															<input type="text" placeholder="" class="form-control"
																name="labelName" id="labelId" maxlength="50"  pattern="[A-Z a-z]+" title="enter alphabets only"  required>
														</div>
													</div>
													<!--  <div class="form-group">
                                                  <label class="col-lg-12 control-label"><input type="checkbox"> Nest Label Under</label>
                                                  <div class="col-lg-12">
                                                      <input type="text" placeholder="" id="cc" class="form-control" name="labelType">
                                                  </div>
                                              </div> -->

													<div class="form-group">
														<div class="col-lg-10">
															<button class="btn btn-primary" type="submit">Create</button>
															<button class="btn btn-primary" type="button"
																data-dismiss="modal">Cancel</button>
														</div>
													</div>
												</form>
											</div>
										</div>
										<!-- /.modal-content -->
									</div>
									<!-- /.modal-dialog -->
								</div>


							</li>

				<li><a href="getCalander" title="Label"><i class="fa fa-calendar"></i> Calendar </a></li>
				<li><a href="getAllFine" title="Label"><i class="fa fa-file-text"></i> Fine </a></li>

						</ul>
						

		<!-- <div id="wrapper">
						
			<div id="basic" class="article">
				<div class="calendar"></div>
			</div>
		</div> -->
                        
                     
      <ul class="nav nav-pills nav-stacked labels-info ">
                          
     </ul>

     </aside>
        <aside class="lg-side">
          <div class="inbox-head">
            <h3>Calendar</h3>
            
          </div>
          <div class="inbox-body table-responsive">
            <div id="calendar"></div>
          </div>
        </aside>
      </div>
    </div>
    <!-- / panel preview --> 
    
  </div>
</div>

<jsp:include page="commonJsp/Footer.jsp" />
<!-- get jQuery from the google apis --> 

<script src="assets/js/jquery-2.1.4.min.js"></script> 
<script type="text/javascript" src="js/moment.latest.min.js"></script> 
<script type="text/javascript" src="js/pignose.calendar.js"></script> 
<!-- <script>
$.noConflict();
jQuery( document ).ready(function( $ ) {
 $(function() { 
 function onClickHandler(date, obj) {
		
			var $calendar = obj.calendar;
			var $box = $calendar.parent().siblings('.box').show();
			var text = 'You choose date ';

			if(date[0] !== null) {
				text += date[0].format('YYYY-MM-DD');
			}

			if(date[0] !== null && date[1] !== null) {
				text += ' ~ ';
			} else if(date[0] === null && date[1] == null) {
				text += 'nothing';
			}

			if(date[1] !== null) {
				text += date[1].format('YYYY-MM-DD');
			}

			$box.text(text);
		}

		// Default Calendar
		$('.calendar').pignoseCalendar({
			select: onClickHandler
		});

		// This use for DEMO page tab component.
		$('.menu .item').tab();
	});
});
// Code that uses other library's $ can follow here.
</script>  -->
<!-- <script src="assets/js/jquery-2.1.4.min.js"></script> 
<script src="assets/js/bootstrap.min.js"></script>  -->

<!-- page specific plugin scripts --> 

<script src="assets/js/moment.min.js"></script> 
<script src="assets/js/fullcalendar.min.js"></script> 
<script src="assets/js/bootbox.js"></script> 

<!-- ace scripts --> 
<script src="assets/js/ace-elements.min.js"></script> 
<script src="assets/js/ace.min.js"></script> 

<!-- inline scripts related to this page --> 
<script>
$.noConflict();
jQuery( document ).ready(function( $ ) {
  
  jQuery(function($) {


	 

	/* initialize the calendar
	-----------------------------------------------------------------*/

	var date = new Date();
	var d = date.getDate();
	var m = date.getMonth();
	var y = date.getFullYear();
	 var eventlist=${eventlist};
	 var event=null;
	 var geteventdate=null;
	//var db=${abc}
	var eventlist=${eventlist};
var eventcompanylist=${eventscomlist};
//alert(JSON.stringify(eventlist.events));
var jsonArray=eventcompanylist.events;
//var comjsonarray=eventcompanylist.events;


	var calendar = $('#calendar').fullCalendar({
	

		buttonHtml: {
			prev: '<i class="ace-icon fa fa-chevron-left"></i>',
			next: '<i class="ace-icon fa fa-chevron-right"></i>'
		},
	
		header: {
			left: 'prev,next today',
			center: 'title',
			right: 'month,agendaWeek,agendaDay'
		},
		events:jsonArray,
		//events:comjsonarray
	
	})

})
  
  
});
/* $(document).ready(function(){
    $(".star").click(function(){
      $(".fa-star").toggleClass("activstar");
    });
    $(".open-label").click(function(){
	        $(".labels-info .collapse").toggleClass("in");
	      });
   });  */
</script>
</body>

</html>