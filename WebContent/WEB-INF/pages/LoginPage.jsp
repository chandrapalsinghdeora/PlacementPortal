<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Indian Institute of Management Ahmedabad (IIMA) | Home - IIMA</title>
    <link rel="icon" href="images/favicon.ico" type="image/x-icon"/>
<link rel="stylesheet" href="css/bootstrap.css">

<!-- jQuery library -->
<script src="js/jquery.min.js"></script>

<!-- Latest compiled JavaScript -->
<script src="js/bootstrap.js"></script>

<link rel="stylesheet" type="text/css" href="css/login.css">
<link rel="stylesheet" type="text/css" href="css/responsive.css" media="screen" />
<link rel="stylesheet" type="text/css" href="css/font-awesome.css">
<link rel="icon" href="images/favicon.ico" type="image/x-icon"/>
<script type="text/javascript" src="iimJs/LoginJs.js"></script>
<script src="http://www.jqueryscript.net/demo/Text-Scrolling-Plugin-for-jQuery-Marquee/jquery.marquee.js"></script>
<script>
function callGoogleAPi() {
		//alert("inside fucntion:::");
	// IIM Local Server
	 document.location.href = "https://accounts.google.com/o/oauth2/auth?scope=email&redirect_uri=http://localhost:1000/IIMForum/userLogin&response_type=code&client_id=295000811422-45ncm8uev4je1dpatemg9816hb1iecub.apps.googleusercontent.com&approval_prompt=force";
	// IIM Precise Server
	// document.location.href = "https://accounts.google.com/o/oauth2/auth?scope=email&redirect_uri=http://vendor.jarvtest.tk:1000/IIMForum/userLogin&response_type=code&client_id=125253313199-evdktdteflmo7r9i4os7cahrcspcj48t.apps.googleusercontent.com&approval_prompt=force"; 
	//IIM Cloud Server
	//  document.location.href = "https://accounts.google.com/o/oauth2/auth?scope=email&redirect_uri=http://www.jarvtest.tk:1000/IIMForum/userLogin&response_type=code&client_id=564793402551-ncic8ta5jpfvlbppvinbhcmij9coi9qa.apps.googleusercontent.com&approval_prompt=force";   

	//PGP placement login
	//document.location.href = "https://accounts.google.com/o/oauth2/auth?scope=email&redirect_uri=http://pgpplacements.iima.ac.in:1000/IIMForum/userLogin&response_type=code&client_id=417444571966-dg8s6gtbee3kmoo3kv977uj65dnmsr00.apps.googleusercontent.com&approval_prompt=force"

  // DomainName without port and project name														
	//  document.location.href = "https://accounts.google.com/o/oauth2/auth?scope=email&redirect_uri=http://pgpplacements.iima.ac.in/IIMForum/userLogin&response_type=code&client_id=466519002203-n5f1f1bs2b9tabeg4aa2tjdinridqvc8.apps.googleusercontent.com&approval_prompt=force"
}

$(document).ready(function(){ 
$('.marquee-with-options').marquee({
	
	speed: 10000,
	gap: 50,
	delayBeforeStart: 0,
	direction: 'left',
	duplicated: false,
	pauseOnHover: true
});

});

</script>

<style>

.js-marquee {  padding: 8px 0; }
.js-marquee  a{ color:#fff;}

</style>

</head>

<body class="login">
 
<div class="carousel slide carousel-fade" data-ride="carousel">

    <!-- Wrapper for slides -->
    <div class="carousel-inner" role="listbox">
        <div class="item active">
        </div>
        <div class="item">
        </div>
        <div class="item">
        </div>
         <div class="item">
        </div>
    </div>
</div>


	<header>

	
	<div class="bottom-nav-menu">
		<a href="https://www.iima.ac.in/web/iima" id="logo" target="_blank"> <img src="images/logo1.png" class="img-responsive">	</a> 
		
		<span class="logo-title">PGP CAREER CONNECT</span>
		<ul class="top-btn-sec">
					<li><a href="getFileManagerWithoutSession" class="btn btn-primary"> Data</a></li>
					<!-- <li><a href="#" class="btn btn-primary">Announcements</a></li> -->
					<li><a href="https://web.iima.ac.in/iprs/download.php" class="btn btn-primary" target="_blank"> Report</a></li>
							<li><a href="#" class="btn btn-danger" onclick="callGoogleAPi();"><i class="fa fa-google-plus"
							aria-hidden="true"></i> Google</a></li>

				</ul>
		
		
		
	</div>
 <div class="horiz-news-ticker"> <span class="news-highlights">Latest News<span class="arrow-right"></span></span>
   <%--  <marquee onmouseover="this.setAttribute('scrollamount',0);" onmouseout="this.setAttribute('scrollamount',5);">  <p>
   <c:forEach items="${announcement}" var="announce" varStatus="i">
      <span>${announce.title}</span>      
      <c:if test="${not i.last}">||</c:if>
   </c:forEach>
   </p></marquee> --%>
      
<div class='marquee-with-options'>
 <c:forEach items="${announcement}" var="announce" varStatus="i">
      <a href="${announce.url}" target="_blank"><span>${announce.title}</span></a>      
      <c:if test="${not i.last}">||</c:if>
   </c:forEach>
 
 </div>

   
   </div>

	</header>

	<%-- <div class="col-md-offset-3 col-md-6 col-sm-6 col-xs-12">

		<div class="panel panel-primary login-side-container">
			<div class="panel-heading">IIMA Placements</div>

			<div class="panel-body">
				<ul>
					<li><a href="#"><i class="fa fa-arrow-circle-right"
							aria-hidden="true"></i> Data</a></li>
					<li><a href="#"><i class="fa fa-arrow-circle-right"
							aria-hidden="true"></i> Announcements</a></li>
					<li><a href="#"><i class="fa fa-arrow-circle-right"
							aria-hidden="true"></i> Report</a></li>

				</ul>
				
				<div class="space">
				<label style="color: red">${credentials}</label>
				</div>
			</div>
			<div>
			<%-- <form   method="POST" id="LoginForm" action="userEmailLogin">
				<input type="text" placeholder="Enter Emailid" name="emailid" id="emailid"
					required>
					<button type="submit" class="login">Login</button>
                </form> 
			</div>
		</div>
		<div class="google-login">
			<a href="#" class="btn btn-danger" onclick="callGoogleAPi();"><i class="fa fa-google-plus"></i>
				Login With Google Plus</a>
		</div>

	</div> --%>
	<!--  
	<div class="col-md-6 col-sm-6 col-xs-12">
		<form class="login-form" commandName="UserBean" method="POST" id="LoginForm" action="userLogin">
			<div class="login-header">Login to IIMA</div>

			<div class="login-inner">
               <input type="hidden" name="forumPage" value="${fpage}"/>
               <c:if test="${not empty questionId}">
                <input type="hidden" name="questionId" value="${questionId}" /> 
               </c:if>
				<input type="text" placeholder="Enter Username" name="userName"
					required> <input type="password"
					placeholder="Enter Password" name="password" required>
				<input type="hidden" id="threadId" value="${thread}"
					name="thread" required>
				
				<button type="submit" class="login">Login</button>
			
				<input type="checkbox" checked="checked"> Remember me
				<label style="color: red">${credentials}</label>
			</div>

			<div class="login-footer">
				<button type="button" class="signup">Sign Up</button>
				<span class="psw"><a href="#">Forgot password?</a> || <a href="getRegistrationPage">New User</a></span> 
			</div>
		</form>
	</div>	-->
    
</body>
</html>

 
 
  