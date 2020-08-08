<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<link href="css/bootstrap.css" rel="stylesheet">

<!-- Custom -->
<link href="css/custom.css" rel="stylesheet">

<!-- <link rel="icon" href="images/favicon.ico" type="image/x-icon"/> -->
<%-- <link rel="shortcut icon" href="${pageContext.request.contextPath}/image/favicon.ico" type="image/x-icon"/> --%>
  
<link rel="stylesheet" href="css/font-awesome.css">

<!-- CSS STYLE-->
<link rel="stylesheet" type="text/css" href="css/style.css"
	media="screen" />
<link rel="stylesheet" type="text/css" href="css/responsive.css"
	media="screen" />

<link rel="stylesheet" type="text/css"
	href="css/dataTables.bootstrap.min.css" media="screen" />
<style>
.top-menu-bar  ul.dropdown-menu {
	background-color: #000080;
	border: 3px solid #7171ae;
	border-top: none;
	top: 42px;
	border-radius: 0 0 2px 2px;
	right: 0;
}

.top-menu-bar ul li {
	width: 100%;
}

.top-menu-bar ul li a span {
	margin: 0 2px;
}

.top-menu-bar ul li a:hover {
	text-decoration: none;
	color: #ccc
}

</style>
<c:if test="${empty sessionBean.userID}">
	<header>
		<div class="top-menu-bar">
			<div class="col-sm-3 col-md-3 pull-right">
				<ul>
					<!-- <li><a href="#"> Site | </a></li>
					<li><a href="login"><img src="images/avatar.jpg"
							class="img-circle" alt=""> Log In </a></li> -->
				</ul>

			</div>

			<span class="logo-title">PGP CAREER CONNECT</span>
		</div>
		<div class="bottom-nav-menu">
			<!--<a href="#" id="logo"> <img src="images/logo.png"> </a> -->

			<div class="navbar-wrapper">
				<div class="container-fluid">
					<nav class="navbar navbar-default">
						<div class="container">
							<div class="navbar-header">
								<button type="button" class="navbar-toggle collapsed"
									data-toggle="collapse" data-target="#navbar"
									aria-expanded="false" aria-controls="navbar">
									<span class="sr-only">Toggle navigation</span> <span
										class="icon-bar"></span> <span class="icon-bar"></span> <span
										class="icon-bar"></span>
								</button>
								<a class="navbar-brand" href="https://www.iima.ac.in/web/iima" id="logo" target="_blank"><img
									src="images/logo.png"></a>
							</div>
						</div>
					</nav>
				</div>
			</div>
		</div>

	</header>
</c:if>

<c:if test="${not empty sessionBean.userID}">

	<header>
		<div class="top-menu-bar">
			<div class="pull-right">
				<ul>
					<%-- <li Style="color: white"><c:out value="${sessionBean.name}" />   |  <c:out value="${sessionBean.userRoleType}" />|
						
						<a href="logout"><img src="images/avatar.jpg"
							class="img-circle" alt=""> Log Out </a></li> --%>



					<li class=" dropdown"><a href="#" class="dropdown-toggle "
						data-toggle="dropdown" role="button" aria-haspopup="true"
						aria-expanded="false"> <span><c:out
									value="${sessionBean.name}" /> </span> <span> | </span>
									<span>
									<span class="time" style="font-size: smaller;">
							<span id="date"> <script type="text/javascript">
				                                    var d = new Date()
				                                    var weekday = new Array("Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday")
				                                    var monthname = new Array("Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec")
				                                    document.write(weekday[d.getDay()] + ", ")
				                                    document.write(monthname[d.getMonth()] + " ")
				                                     document.write(d.getDate() + "  , ")
				                                    document.write(d.getFullYear())
				                                </script>
						</span> <span id="clockDisplay"> <script type="text/javascript">
			                                        function renderTime() {
			                                            var currentTime = new Date();
			                                            var diem = "AM";
			                                            var h = currentTime.getHours();
			                                            var m = currentTime.getMinutes();
			                                            var s = currentTime.getSeconds();
			                                            setTimeout('renderTime()', 1000);
			                                            if (h == 0) {
			                                                h = 12;
			                                            } else if (h > 12) {
			                                                h = h - 12;
			                                                diem = "PM";
			                                            }
			                                            if (h < 10) {
			                                                h = "0" + h;
			                                            }
			                                            if (m < 10) {
			                                                m = "0" + m;
			                                            }
			                                            if (s < 10) {
			                                                s = "0" + s;
			                                            }
			                                            var myClock = document.getElementById('clockDisplay');
			                                            myClock.textContent = h + ":" + m + ":" + s + " " + diem;
			                                            myClock.innerText = h + ":" + m + ":" + s + " " + diem;
			                                        }
			                                        renderTime();
			                                    </script>
						</span>

					</span>
									</span><span> | </span>
									 <span>
								<c:out value="${sessionBean.userRoleType}" />
						</span> <span> | </span> <img src="images/avatar.jpg" class="img-circle"
							alt="">
					</a> <br> 

						<ul class="dropdown-menu">
							<!--  <li><a
								href="https://www.google.com/accounts/Logout?continue=https://appengine.google.com/_ah/logout?continue=http://pgpplacements.iima.ac.in/IIMForum/logout"><i
									class="fa fa-power-off" aria-hidden="true"></i> Logout</a></li>  -->
							
							<li><a
								href="https://www.google.com/accounts/Logout?continue=https://appengine.google.com/_ah/logout?continue=http://localhost:1000/IIMForum/logout"><i
									class="fa fa-power-off" aria-hidden="true"></i> Logout</a></li>
									
							<!--  <li><a href="logout"><i class="fa fa-power-off" aria-hidden="true"></i> Logout</a></li> -->
							<c:if
								test="${sessionBean.roleID==3 || sessionBean.roleID==4 || sessionBean.roleID==1 || sessionBean.roleID==20  || sessionBean.roleID==48 || sessionBean.roleID==49 || sessionBean.roleID==50|| sessionBean.roleID==9|| sessionBean.roleID==8}">

								<c:forEach var="role" items="${roles}">

									<li><a href='getRole?role=${role.key}'
										onclick="sendRole('${role.key}');"><i class="fa fa-user"
											aria-hidden="true"></i> ${role.value}</a></li>
								</c:forEach>
								<c:if test="${sessionBean.roleID!=1}">
								 
								
								<li><a href='getRole?role=1' onclick="sendRole('1');"><i
										class="fa fa-user" aria-hidden="true"></i> IIMA Student</a></li>
								</c:if>

							</c:if>


						</ul></li>

				</ul>

			</div>
<span class="logo-title">PGP CAREER CONNECT</span>
		</div>
		<div class="bottom-nav-menu">
			<!--<a href="#" id="logo"> <img src="images/logo.png"> </a> -->

			<div class="navbar-wrapper">
				<div class="container-fluid">
					<nav class="navbar navbar-default">
						<div class="container">
							<div class="navbar-header">
								<button type="button" class="navbar-toggle collapsed"
									data-toggle="collapse" data-target="#navbar"
									aria-expanded="false" aria-controls="navbar">
									<span class="sr-only">Toggle navigation</span> <span
										class="icon-bar"></span> <span class="icon-bar"></span> <span
										class="icon-bar"></span>
								</button>
								<a class="navbar-brand" href="https://www.iima.ac.in/web/iima" id="logo" target="_blank"><img
									src="images/logo.png"></a>
							</div>
							<c:if test="${sessionBean.roleID==6}">
								<div id="navbar" class="navbar-collapse collapse">
									<ul class="nav navbar-nav">
										<!--   <li class="active"><a href="#" class="">Company Role</a></li>-->
										<li class=" dropdown"><a href="#"
											class="dropdown-toggle " data-toggle="dropdown" role="button"
											aria-haspopup="true" aria-expanded="false">Company <span
												class="caret"></span></a>
											<ul class="dropdown-menu">

												<li><a href="getCompanyMaster">Company</a></li>
												<li><a href="getdesignation">Role</a></li>

												<li><a href="getCluster">Cluster</a></li>
												<li><a href="getCohort">Cohort</a></li>
												<li><a href="getProcess">Process</a></li>
												<li><a href="getkmverification">Firm Verification</a></li>
												<li><a href="emailcredentials">Email Credentials</a></li>

											</ul></li>
										<li class=" dropdown"><a href="#"
											class="dropdown-toggle " data-toggle="dropdown" role="button"
											aria-haspopup="true" aria-expanded="false">Users <span
												class="caret"></span></a>
											<ul class="dropdown-menu">
												<li><a href="getAllPotentialStudent">Potential
														Student</a></li>
												<li><a href="getiimstudent">IIM Student</a></li>
												<li><a href="kmadmin">Team </a></li>
												<li><a href="getConvertPgpPage">PGP1 to PGP2</a></li>
												<!--	<li> <a href="#">Wildcard</a> </li> -->

											</ul>
										<li class=" dropdown"><a href="#"
											class="dropdown-toggle " data-toggle="dropdown" role="button"
											aria-haspopup="true" aria-expanded="false">Placecommer <span
												class="caret"></span></a>
											<ul class="dropdown-menu">
												<li><a href="getRolePage">Assign Role</a></li>
												<li><a href="getmasterrole">User Roles</a></li>
											</ul></li>
										<li class=" dropdown"><a href="#"
											class="dropdown-toggle " data-toggle="dropdown" role="button"
											aria-haspopup="true" aria-expanded="false">Resources <span
												class="caret"></span></a>
											<ul class="dropdown-menu">
												<li><a href="getFileManager">All Folder</a></li>
												<li><a href="getFileManager?vf=preparation_Folder">Preparation
														Folder</a></li>
												<!-- <li><a href="getFileManager?vf=past_CVs">Past CVs</a></li> -->
												<li><a href="getFileManager?vf=sample_Covers">Sample
														Covers</a></li>
												<li><a href="getFileManager?vf=past_Experiences">Past
														Experiences</a></li>
												<li><a href="getFileManager?vf=other_Documents">Other
														Documents</a></li>
											</ul></li>
										<li class=" dropdown"><a href="#"
											class="dropdown-toggle " data-toggle="dropdown" role="button"
											aria-haspopup="true" aria-expanded="false">Mentor <span
												class="caret"></span></a>
											<ul class="dropdown-menu">
												<li><a href="getMentorPage">Mentor</a></li>
												<li><a href="getAssignExperiencePage">Enable
														Experience</a></li>
												<li><a href="getAllFineDetails">Manage Fine</a></li>
											</ul> <!-- <li><a href="#">Student Profile</a></li> -->
										<li class=" dropdown"><a href="#"
											class="dropdown-toggle " data-toggle="dropdown" role="button"
											aria-haspopup="true" aria-expanded="false">Setting <span
												class="caret"></span></a>
											<ul class="dropdown-menu">
												<!-- <li><a href="#">General</a></li> -->
												<li><a href="getsetting">Setting</a></li>
												<li><a href="getAllProfileDetail">Profile Lock</a></li>

											</ul></li>
										<li><a href="forumHomePage">Forum</a></li>
										<li><a href="getKMAnnouncementPage">Announcement</a></li>
										<li><a href="fileManagerForData">Data</a></li>
										<li><a href="replicateApplication">Replicate</a></li>
										<li><a href="offerdream">Offer</a></li>
										<li><a href="updateApplication">Update Application</a></li>
										<li><a href="uploadPPO">Upload PPO</a></li>
										
									</ul>

								</div>
							</c:if>
							<!--   RM admin header 
							<c:if test="${sessionBean.roleID==4}">
								<div id="navbar" class="navbar-collapse collapse">
									<ul class="nav navbar-nav">

										<li class=" dropdown"><a href="#"
											class="dropdown-toggle " data-toggle="dropdown" role="button"
											aria-haspopup="true" aria-expanded="false">Firm
												Management <span class="caret"></span>
										</a>
											<ul class="dropdown-menu">
												<li><a href="appManage">Manage Application </a></li>
												
											</ul></li>
										<li class="active"><a href="getRMMessagePage">RM
												Message</a></li>
										<li class="active"><a href="getRMSchedulePage">RM
												Schedule</a></li>
										<li class=" dropdown"><a href="#"
											class="dropdown-toggle " data-toggle="dropdown" role="button"
											aria-haspopup="true" aria-expanded="false">Resources <span
												class="caret"></span></a>
											<ul class="dropdown-menu">
											<li><a href="getFileManager">All Folder</a></li>
												<li><a href="getFileManager?vf=preparation_Folder">Preparation
														Folder</a></li>
												<li><a href="getFileManager?vf=past_CVs">Past CVs</a></li>
												<li><a href="getFileManager?vf=sample_Covers">Sample
														Covers</a></li>
												<li><a href="getFileManager?vf=past_Experiences">Past
														Experiences</a></li>
												<li><a href="getFileManager?vf=other_Documents">Other
														Documents</a></li>

 											</ul></li>

										<li class=" dropdown"><a href="#"
											class="dropdown-toggle " data-toggle="dropdown" role="button"
											aria-haspopup="true" aria-expanded="false">Closed Status<span
												class="caret"></span></a>
											<ul class="dropdown-menu">

												<li><a href="getAllCloseStatusDetails">Verify
														Application</a></li>
												<li><a href="#">Approved Application List</a></li>
												<li><a href="#">Removed Candidates</a></li>
												<li><a href="getRMDownload">Download Verified List</a>
												</li>
											</ul></li>
									</ul>
								
								</div>
							</c:if>-->
							<!--   Mentor header 
							<c:if test="${sessionBean.mentRoleID==3}">
								<div id="navbar" class="navbar-collapse collapse">
									<ul class="nav navbar-nav">
										<li class="active"><a href="#">Mentor Login</a></li>
									</ul>

								</div>
							</c:if>-->
							<c:if test="${ sessionBean.roleID==1}">




								<div id="navbar" class="navbar-collapse collapse">
									<ul class="nav navbar-nav">


										<li class="active"><a href="getInboxData" class="">Inbox</a></li>
										<!-- <li class=" dropdown"><a href="getAllCompany">Company
										</a></li> -->
										<li class=" dropdown">
										<a href="#"
											class="dropdown-toggle " data-toggle="dropdown" role="button"
											aria-haspopup="true" aria-expanded="false">Application <span
												class="caret"></span></a>
											<ul class="dropdown-menu">
												<li><a href="getAllOpenCompany">Open Application</a></li>
												<li><a href="getAllCompany">Close Application</a></ul></li>
										<!--  <li class=" dropdown"><a href="#"
											class="dropdown-toggle " data-toggle="dropdown" role="button"
											aria-haspopup="true" aria-expanded="false">Experiences <span
												class="caret"></span></a>
											<ul class="dropdown-menu">
												<li><a href="getpreparation">Interview Experience</a></li>
												<li><a href="getinternship">Internship Experience</a></li>
											</ul></li>-->
										<!--  <li class=" dropdown"><a href="#"
											class="dropdown-toggle " data-toggle="dropdown" role="button"
											aria-haspopup="true" aria-expanded="false">OfferStatus <span
												class="caret"></span></a></li>-->
										<li class=" dropdown"><a href="#"
											class="dropdown-toggle " data-toggle="dropdown" role="button"
											aria-haspopup="true" aria-expanded="false">Resources <span
												class="caret"></span></a>
											<ul class="dropdown-menu">
												<li><a href="getFileManager">All Folder</a></li>
												<li><a href="getFileManager?vf=preparation_Folder">Preparation
														Folder</a></li>
												<!-- <li><a href="getFileManager?vf=past_CVs">Past CVs</a></li> -->
												<li><a href="getFileManager?vf=sample_Covers">Sample
														Covers</a></li>
												<li><a href="getFileManager?vf=past_Experiences">Past
														Experiences</a></li>
												<li><a href="getFileManager?vf=other_Documents">Other
														Documents</a></li>

											</ul></li>
										<!-- <li class="active"><a href="getOfferStatus">Offer
												Status</a></li> -->
										<li class="active"><a href="userprofile" class=""> My Profile</a></li>
										<li class="active"><a href="getShareProfile" class="">CV Repository</a></li>
										<li class="active"><a href="appMatrix" class="">Application Matrix</a></li>
										<!-- <li class=" dropdown"><a href="forumHomePage">Forum
										</a></li> -->



									</ul>
								</div>
							</c:if>
							<c:if test="${sessionBean.roleID==3}">
								<div id="navbar" class="navbar-collapse collapse">
									<ul class="nav navbar-nav">

										<!--	<li><a href="getFileManager">All Folder</a></li> -->
										<!--<li class=" dropdown"><a href="MentorProfile" class="">Mentor
												Profile</a></li> -->





									</ul>
								</div>
							</c:if>
							<c:if test="${sessionBean.roleID==20}">
								<div id="navbar" class="navbar-collapse collapse">
									<ul class="nav navbar-nav">
										<li class=" dropdown"><a href="#"
											class="dropdown-toggle " data-toggle="dropdown" role="button"
											aria-haspopup="true" aria-expanded="false">SATCOM
												Resources <span class="caret"></span>
										</a>
											<ul class="dropdown-menu">
												<li><a href="getFileManager">All Folder</a></li>
												<li><a href="getFileManager?vf=preparation_Folder">Preparation
														Folder</a></li>
												<!-- <li><a href="getFileManager?vf=past_CVs">Past CVs</a></li> -->
												<li><a href="getFileManager?vf=sample_Covers">Sample
														Covers</a></li>
												<li><a href="getFileManager?vf=past_Experiences">Past
														Experiences</a></li>
												<li><a href="getFileManager?vf=other_Documents">Other
														Documents</a></li>

											</ul></li>
									</ul>
								</div>
							</c:if>
							<c:if test="${sessionBean.roleID==4}">
								<div id="navbar" class="navbar-collapse collapse">

									<ul class="nav navbar-nav">
										<li class="dropdown"><a href="appManage">Manage
												Application </a></li>

										<li class="dropdown"><a href="addfineValues">Fine</a></li>

										<li class=" dropdown"><a href="#"
											class="dropdown-toggle " data-toggle="dropdown" role="button"
											aria-haspopup="true" aria-expanded="false">Announcement <span
												class="caret"></span></a>
											<ul class="dropdown-menu">
												<li class=" dropdown"><a href="getAnnouncement">Add Announcement
												</a></li>
												<li class=" dropdown"><a href="getAllAnnouncementDetails">View
												Announcement </a></li>
											</ul>
										</li>                                      
									</ul>

								</div>
							</c:if>
							
							<c:if test="${sessionBean.roleID==49}">
								<div id="navbar" class="navbar-collapse collapse">

									<ul class="nav navbar-nav">										

										<li class=" dropdown"><a href="getSuperRMPage1">Manage
												Application </a></li>
									                                      
									</ul>

								</div>
							</c:if>
							
							<c:if test="${sessionBean.roleID==50}">
								<div id="navbar" class="navbar-collapse collapse">

									<ul class="nav navbar-nav">										

										<li class=" dropdown"><a href="getSuperRMPage2">Manage
												Application </a></li>
									                                      
									</ul>

								</div>
							</c:if>
							<c:if test="${sessionBean.roleID==9}">
							
                               	<div id="navbar" class="navbar-collapse collapse">
									<ul class="nav navbar-nav">
										<!--   <li class="active"><a href="#" class="">Company Role</a></li>-->
										<li class=" dropdown"><a href="#"
											class="dropdown-toggle " data-toggle="dropdown" role="button"
											aria-haspopup="true" aria-expanded="false">Company <span
												class="caret"></span></a>
											<ul class="dropdown-menu">

												<li><a href="getCompanyMaster">Company</a></li>
												<li><a href="getdesignation">Role</a></li>

												<li><a href="getCluster">Cluster</a></li>
												<li><a href="getCohort">Cohort</a></li>
												<li><a href="getProcess">Process</a></li>
												<li><a href="getkmverification">Firm Verification</a></li>
											
											</ul></li>
									
									</ul>

								</div>
							</c:if>
							<c:if test="${sessionBean.roleID==21}">
								<div id="navbar" class="navbar-collapse collapse">

									<ul class="nav navbar-nav">										

										<li class=" dropdown"><a href="getDynamicAssignRole">Assign Dynamic Role</a></li>
										<li class=" dropdown"><a href="getPanelAllocationPage">Panel Allocation</a></li>
										<li class=" dropdown"><a href="getWinTracker">Assign Firm & Negos</a></li>
									     <li><a href="getreport">Reports</a></li>                                
									</ul>

								</div>
							</c:if>
						</div>
					</nav>
				</div>
			</div>
		</div>
	</header>
</c:if>
