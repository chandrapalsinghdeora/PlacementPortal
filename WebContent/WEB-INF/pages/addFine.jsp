<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Indian Institute of Management Ahmedabad (IIMA) | Home - IIMA</title>
<link rel="icon" href="images/favicon.ico" type="image/x-icon"/>
	
</head>
<body>
	<jsp:include page="commonJsp/Header.jsp" />
	<div class="clearfix"></div>
	<div class="breadcumb-wrapper">
		<div class="clearfix">
			<div class="clearfix">
			<div class="pull-left">
				<ul class="list-inline link-list">
					<li><a href="addfineValues">Home</a></li>
					
				</ul>
			</div>

		</div>
		</div>
	</div>
	
	<div class="space"></div>
	<div class="clearfix"></div>
	
	<div class="container min-height">
		<div class="panel panel-primary">
			<div class="panel-heading">Fine</div>
			<div class="panel-body">

				<!-- <form role="form" class="form-horizontal"> -->
				<form method="post" id="fineId" name="fine" action="addFine"
					name="Fine">
					<!--  <div class="form-group">
      <label class="col-sm-2">Type</label>
      <div class="col-sm-4">
      <input type="radio"  name="type" value="0"
      onclick="disableindiTextBox();" >Group </div>
	   <div class="col-sm-4">
	   <input type="radio"  name="type" value="1"
	   onclick="disabletextBox();">Individual</div>
    
      
    </div>
     -->
					<div class="form-group">

						<label class="col-sm-2">To</label>
						<!-- <div class="col-sm-4"><select class="form-control" id="groupValue"  disabled="disabled" name="pgpId">
    <option value="1">PGP1</option>
    <option value="2">PGP2</option>
  </select></div> -->
						<div class="col-sm-4">
							<input class="form-control" type="text" id="individualValue"
								name="individual" placeholder="Please enter email address of students." required maxlength="100">
						</div>

					</div>
					<div class="form-group">

						<label class="col-sm-2">Date</label>
						<div class="col-sm-4">

							<div class="input-group">
							<!--  <input type='text' id='txtDate' />-->
								<input class="form-control date-picker" type="text"
									data-date-format="dd-mm-yyyy" name="fineCreatedDate" id="fineCreatedDate" placeholder="Please select date" required/> <span
									class="input-group-addon"> <i
									class="fa fa-calendar bigger-110"></i>
								</span>
							</div>
						</div>

					</div>

					<div class="form-group">
						<label class="col-sm-2">Event</label>
						<div class="col-sm-4">
							<input class="form-control" type="text" id="event" name="event"  placeholder="Please enter event" required maxlength="200">
						</div>
					</div>

					<div class="form-group">
						<label class="col-sm-2">Reason</label>
						<div class="col-sm-4">
							<textarea class="form-control" rows="3" id="fineReason"
								name="fineReason" placeholder="Please enter reason." required maxlength="300"></textarea>

						</div>

					</div>

					<div class="form-group">
						<label class="col-sm-2">Amount</label>${returnValue}
						<div class="col-sm-4">
							<input class="form-control" type="text" id="amount" name="amount" placeholder="Please enter amount." required pattern="[0-9]+" title="Please enter only numaric values." maxlength="10">
						</div>
						<input type="hidden" value="${returnValue}" id="returnValue" />
					</div>
					
					<div class="form-group col-md-12 text-center">
						<input type="submit" id="check_var" value="Submit"
							class="btn btn-primary"/>
							 <input type="reset" value="Reset"
							class="btn btn-primary" onclick="location.href='addfineValues'"/>

					</div>

				</form>

			</div>
		</div>

	</div>

	<div class="space"></div>
	<jsp:include page="commonJsp/Footer.jsp" />
	
	<script src="assets/js/bootstrap-datepicker.min.js"></script>
	<script src="assets/js/bootstrap-timepicker.min.js"></script>
	<script src="assets/js/moment.min.js"></script>

	<script src="assets/js/bootstrap-datetimepicker.min.js"></script>

	<!-- ace scripts -->
	<!--<script src="assets/js/ace-elements.min.js"></script>-->
	<script src="assets/js/ace.min.js"></script>
	
	<script>
  $('#check_var').click(function() {
    alert($("input:radio['name=group']:checked").val());
  });
  
  function disabletextBox(){
	  
	  document.getElementById('individualValue').removeAttribute('disabled');
	//  document.getElementById('groupValue').addAttribute('disabled');
	  $('#groupValue').prop('disabled','disabled');
  }
  
function disableindiTextBox(){
	  
	  document.getElementById('groupValue').removeAttribute('disabled');
	//  document.getElementById('groupValue').addAttribute('disabled');
	  $('#individualValue').prop('disabled','disabled');
  }
</script>
	

	

	<script>
	$(document).ready(function() {
	    $('#fineCreatedDate').datepicker();
	    $('#fineCreatedDate').datepicker('setDate', 'today');
	});
        
        $('.date-picker').datepicker({
					autoclose: true,
					todayHighlight: true
				})
				//show datepicker when clicking on the icon
				.next().on(ace.click_event, function(){
					$(this).prev().focus();
					
				});
       
       
    	   
    	  
	        var retVal = document.getElementById('returnValue').value;
	        
	    	if (retVal == 'failure') {
	
	    	} else if (retVal == 'success') {
	    		alert("Fine sent successfully");
	    	}
     
        </script>





</body>
</html>