package com.precise.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.precise.model.Announcement;
import com.precise.model.Reply;
import com.precise.model.SessionBean;
import com.precise.model.UserBean;
import com.precise.service.ForumService;
import com.precise.service.KmIIMStudentService;
import com.precise.service.LoginService;

@Controller
public class LoginController {

	@Autowired
	LoginService loginService;
	@Autowired
	ForumService forumService;
	@Autowired
	KmIIMStudentService kmiimstudentservice;

	private static Logger logger = Logger.getLogger(LoginController.class);

	public LoginController() {
		System.out.println("Inside login controller::");
	}

	@RequestMapping(value = { "/login" }, method = { RequestMethod.GET, RequestMethod.POST })
	public String loginAction(HttpServletRequest req, ModelMap model) {
		String returnJsp = "";
		logger.info("inside loginAction method ::");
		String fpage = req.getParameter("forumPage");
		String threadId = req.getParameter("threadId");
		String userId = req.getParameter("uid");
		model.addAttribute("fpage", fpage);
		model.addAttribute("thread", threadId);

		System.out.println("question id in login-" + req.getParameter("questionId"));
		model.addAttribute("questionId", req.getParameter("questionId"));

		
			String commingFrom=req.getParameter("commingFrom");
			System.out.println("commingFrom:::"+commingFrom);
			if(commingFrom!=null){
			if(commingFrom.equalsIgnoreCase("question")){
				System.out.println("thread id for questionLL"+threadId);
				model.addAttribute("threadId", threadId);
				model.addAttribute("forumId", req.getParameter("forumId"));
				returnJsp = "addQuestion";
			}else if(commingFrom.equalsIgnoreCase("reply")){
				System.out.println("inside else condition::"+req.getParameter("forumId"));
				model.addAttribute("threadId", req.getParameter("threadId"));
				model.addAttribute("forumId", req.getParameter("forumId"));
				model.addAttribute("questionId", req.getParameter("questionId"));
				List<Reply> allReply = forumService.getAllReplyByQuestionId(Integer.parseInt(req.getParameter("questionId")==null?"0":(String)req.getParameter("questionId")));
				
				model.addAttribute("question", allReply.get(0).getQuestionName()); 
				returnJsp = "postReply";
			}
			}else{
				returnJsp="LoginPage";
			}
			
			List<Announcement> announce=kmiimstudentservice.getALLAnnouncementByKm();			
			model.addAttribute("announcement", announce);
			
		return returnJsp;

	}

	@RequestMapping(value = { "/userLogin" }, method = { RequestMethod.POST, RequestMethod.GET })
	public String userLogin(@ModelAttribute("UserBean") UserBean userBean, ModelMap modelMap, HttpSession session,
			HttpServletRequest req) {

		//System.out.println("user login controller----");
		//System.out.println(req.getParameter("userName"));
		//String word=null;
		//String text = req.getParameter("userName").toString();
		//Boolean found;

		
		

	//Read more: http://www.java67.com/2012/10/string-contains-and-indexof-example.html#ixzz4lAqHoSfP

		String forumPage = (String) userBean.getForumPage();
		//System.out.println("forum page =" + forumPage + "  " + userBean.getForumPage());

		String returnJsp = "";
		logger.info("UserName::" + userBean.getUserName() + "::Password ::" + userBean.getPassword());
		// System.out.println("test login print::");
		try {
			String threadId = req.getParameter("thread"); 
			String code = req.getParameter("code");
			//System.out.println("Thread id in userLogin method::" + threadId);
			
			if (threadId == null || threadId.isEmpty()) {
				if (code == null) {
					if (forumPage == null || forumPage.isEmpty()) {
						
						Map<String, String> returnValue = loginService.checkValidUser(userBean, session);
						// System.out.println("lmap values"
						// +returnValue.size());
						System.out.println("sfs" + returnValue.get("userName") + "," + returnValue.get("password"));
						if (returnValue.get("userName") == null && returnValue.get("password") == null) {
							System.out.println("hai");
							modelMap.addAttribute("credentials", "Your credentials are incorrect. Please check.");
							returnJsp = "LoginPage";

						} else if ((returnValue.get("userName").equals(req.getParameter("userName")))
								&& (returnValue.get("password").equals(req.getParameter("password")))) {
							returnJsp = "redirect:forumHomePage";
						} else {
							modelMap.addAttribute("credentials", "Your credentials are incorrect. Please check.");
							returnJsp = "LoginPage";
						}
					} else {
						String question = forumService.getQuestionByQuestionId(userBean.getQuestionId());
						modelMap.addAttribute("question", question);
						modelMap.addAttribute("questionId", userBean.getQuestionId());
						return "postReply";
					}
				} else {
					String returnData = getGoogleUserDetails(code, session,userBean);
					if (returnData.equalsIgnoreCase("success")) {
						SessionBean sessionBean =(SessionBean)session.getAttribute("sessionBean");
						if(sessionBean.getRoleID()==1)
						{
							Map<String, String> returnValue = loginService.getStdRoles(session);
							//System.out
							session.setAttribute("roles",returnValue );
							//System.out.println("returnValue--------==="+returnValue);
							returnJsp = "redirect:getInboxData";
						}
						else if(sessionBean.getRoleID()==21){
							//System.out.println("schedule admin");
							returnJsp = "redirect:getScheduleAdminPage";
							}
						else{
							
						returnJsp = "redirect:forumHomePage";
						}
					} else {
						modelMap.addAttribute("credentials", "Your credentials are incorrect. Please check.");
						returnJsp = "LoginPage";
					}
				}
			} else {
				Map<String, String> returnValue = loginService.checkValidUser(userBean, session);
				if ((returnValue.get("userName").equals(req.getParameter("userName")))
						&& (returnValue.get("password").equals(req.getParameter("password")))) {
					returnJsp = "addQuestion";
					modelMap.addAttribute("threadId", threadId);
				} else {
					returnJsp = "LoginPage";
					modelMap.addAttribute("credentials",
							"You do not have permission to add question. Please contact to your admin.");
				}
			}
			/*
			 * String code = req.getParameter("code"); String threadId =
			 * req.getParameter("thread"); System.out.println(
			 * "threadId in method::" + threadId); System.out.println(
			 * "code value ::" + code); if (threadId == null ||
			 * threadId.isEmpty()) { if (code == null) { if (forumPage == null
			 * || forumPage.isEmpty()) { Map<String, String> returnValue =
			 * loginService.checkValidUser(userBean, session);
			 * System.out.println("return value for role::" +
			 * returnValue.get("userRole")); if (returnValue.get("password") !=
			 * null) { if
			 * (returnValue.get("userRole").toString().equalsIgnoreCase("admin")
			 * ) { // modelMap.addAttribute("userName",
			 * returnValue.get("userName")); // returnJsp = "AdminDashBoard";
			 * returnJsp="redirect:forumHomePage"; } else if
			 * (returnValue.get("userRole").toString().equalsIgnoreCase(
			 * "student")) { modelMap.addAttribute("userName",
			 * returnValue.get("userName"));
			 * 
			 * returnJsp = "StudentDashBoard";
			 * 
			 * }
			 * 
			 * } else { System.out.println("inside else condition:"); returnJsp
			 * = "LoginPage"; modelMap.addAttribute("credentials",
			 * "Your credentials are incorrect. Please check."); } } else {
			 * /*String question=forumService.getQuestionByQuestionId(userBean.
			 * getQuestionId()); modelMap.addAttribute("question",question);
			 * modelMap.addAttribute("questionId",userBean.getQuestionId());
			 * return forumPage;
			 * 
			 * Map<String, String> returnValue =
			 * loginService.checkValidUser(userBean, session); if
			 * (returnValue.get("userRole").toString().equalsIgnoreCase(
			 * "student")) { String
			 * question=forumService.getQuestionByQuestionId(userBean.
			 * getQuestionId()); modelMap.addAttribute("question",question);
			 * modelMap.addAttribute("questionId",userBean.getQuestionId());
			 * return forumPage; }else { returnJsp = "Error"; }
			 * 
			 * 
			 * } } else { String returnData = getGoogleUserDetails(code,
			 * session); if (returnData.equalsIgnoreCase("success")) {
			 * //returnJsp = "AdminDashBoard";
			 * returnJsp="redirect:forumHomePage"; } else { returnJsp = "Error";
			 * } } } else { Map<String, String> returnValue =
			 * loginService.checkValidUser(userBean, session); if
			 * (returnValue.get("userRole").toString().equalsIgnoreCase("admin")
			 * ) { returnJsp = "addQuestion"; modelMap.addAttribute("threadId",
			 * threadId); } else { returnJsp = "LoginPage";
			 * modelMap.addAttribute("credentials",
			 * "You do not have permission to add question. Please contact to your admin."
			 * ); }
			 * 
			 * }
			 */

		} catch (Exception e) {
			logger.info("Exception in userLogin method of LoginController class ::" + e);
			e.printStackTrace();
		}
		return returnJsp;
	}

	@RequestMapping(value = { "/logout" })
	public String logout(HttpSession session, ModelMap model) {
		System.out.println("inside logout method:::");
		session.removeAttribute("sessionBean");
		session.invalidate();
		List<Announcement> announce=kmiimstudentservice.getALLAnnouncementByKm();			
		model.addAttribute("announcement", announce);
		return "LoginPage";
	}

	public String getGoogleUserDetails(String code, HttpSession session, UserBean userBean) {
		System.out.println("google login :: "+code);
		String returnValue = null ;
		try {
			// google login for precise ip
			/*String urlParameters = "code=" + code
					+ "&client_id=125253313199-evdktdteflmo7r9i4os7cahrcspcj48t.apps.googleusercontent.com"
					+ "&client_secret=-whu-Wb6aOMZbxLRsGAUep1o"
					+ "&redirect_uri=http://vendor.jarvtest.tk:1000/IIMForum/userLogin" + "&grant_type=authorization_code";*/
			
			//local 
			String urlParameters = "code=" + code
					+ "&client_id=295000811422-45ncm8uev4je1dpatemg9816hb1iecub.apps.googleusercontent.com"
					+ "&client_secret=48AuxIXP9MwHHV420GuVoo9z"
					+ "&redirect_uri=http://localhost:1000/IIMForum/userLogin" + "&grant_type=authorization_code";
			
			
			// google login for IIM Cloud
			/*String urlParameters = "code=" + code
					+ "&client_id=564793402551-ncic8ta5jpfvlbppvinbhcmij9coi9qa.apps.googleusercontent.com"
					+ "&client_secret=vMdITysHsHFVUrbm9CZjiwMI"
					+ "&redirect_uri=http://www.jarvtest.tk:1000/IIMForum/userLogin" + "&grant_type=authorization_code";
			*/
			//PGP placement login
			/*String urlParameters = "code=" + code
					+ "&client_id=417444571966-dg8s6gtbee3kmoo3kv977uj65dnmsr00.apps.googleusercontent.com"
					+ "&client_secret=tHfp9kgxqnNQiV3VtMNE-5BN"
					+ "&redirect_uri=http://pgpplacements.iima.ac.in:1000/IIMForum/userLogin" + "&grant_type=authorization_code";
			*/
			
			// DomainName without port and project name
			/*String urlParameters = "code=" + code
					+ "&client_id=466519002203-n5f1f1bs2b9tabeg4aa2tjdinridqvc8.apps.googleusercontent.com"
					+ "&client_secret=AuieUdFRnq2DKvIf-bJ5on4s"
					+ "&redirect_uri=http://pgpplacements.iima.ac.in/IIMForum/userLogin" + "&grant_type=authorization_code";
			*/
			
			//System.out.println("urlParameters::" + urlParameters);
			URL url = new URL("https://accounts.google.com/o/oauth2/token");
			URLConnection urlConn = url.openConnection();
			urlConn.setDoOutput(true);
			OutputStreamWriter writer = new OutputStreamWriter(urlConn.getOutputStream());
			writer.write(urlParameters);
			writer.flush();

			String line, outputString = "";
			BufferedReader reader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
			while ((line = reader.readLine()) != null) {
				outputString += line;
			}
			//System.out.println("outputString ::"+outputString);

			// get Access Token
			JsonObject json = (JsonObject) new JsonParser().parse(outputString);
			String access_token = json.get("access_token").toString();
			System.out.println("access_token :: "+access_token);

			// get User Info
			access_token = access_token.substring(1, access_token.length() - 1);
			//System.out.println("access_token::" + access_token);
			url = new URL("https://www.googleapis.com/oauth2/v1/userinfo?access_token=" + access_token);
			urlConn = url.openConnection();
			outputString = "";
			reader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
			while ((line = reader.readLine()) != null) {
				outputString += line;
			}
			System.out.println("outputString : "+outputString);

			// Convert JSON response into Pojo class
			SessionBean sessionBean = new Gson().fromJson(outputString, SessionBean.class);
			//System.out.println("print data email id::::" + sessionBean.getEmail());
			//System.out.println("Name ::: " + sessionBean.getName());
			
			  userBean.setUserEmailId(sessionBean.getEmail());
	           Map<String, String> getValues = loginService.loginGoogle(userBean, session);
	        if(getValues.get("emailid").equals(sessionBean.getEmail()))
	        {
	        	sessionBean.setName(getValues.get("userName"));
	        	sessionBean.setUserRoleType(getValues.get("roletype"));
	        	//System.out.println(getValues.get("roletype"));
	        	sessionBean.setPgpFlag(Integer.parseInt(getValues.get("pgpflag")));
	        	sessionBean.setUserID(Integer.parseInt(getValues.get("userid")));
	        	sessionBean.setRoleID(Integer.parseInt(getValues.get("roleid")));
	        	
	        /*	if(getValues.get("roletype").equals("IIMA Student(RM)"))
	        	{
	        	//System.out.println(getValues.get("rmid"));
	        		sessionBean.setRmRoleID(Integer.parseInt(getValues.get("rmid")));
	        	}
	        	else if(getValues.get("roletype").equals("IIMA Student(Mentor)"))
	        	{
	        	//System.out.println(getValues.get("rmid"));
	        		sessionBean.setMentRoleID(Integer.parseInt(getValues.get("menterid")));
	        	}
	        	else if(getValues.get("roletype").equals("IIMA Student(SATCOM)"))
	        	{
	        		System.out.println(getValues.get("menterid"));
	        		sessionBean.setRoleID(Integer.parseInt(getValues.get("roleid")));
	        		//System.out.println(getValues.get("stcomid"));
	        		sessionBean.setSetcomID(Integer.parseInt(getValues.get("stcomid")));
	        	}
	        	else if(getValues.get("roletype").equals("IIMA Student(RM,Mentor)"))
	        	{
	        	//System.out.println(getValues.get("rmid"));
	        		sessionBean.setMentRoleID(Integer.parseInt(getValues.get("menterid")));
	        		sessionBean.setRmRoleID(Integer.parseInt(getValues.get("rmid")));
	        	}*/
	        	
	        	returnValue="success";
	        }
			
			session.setAttribute("sessionBean", sessionBean);
			writer.close();
			reader.close();

		} catch (Exception e) {
			logger.info("Exception in getGoogleUserDetails method ::" + e.getMessage());
			e.printStackTrace();
			return "failure";
		}
		return returnValue;
	}
	
	@RequestMapping(value = { "/getRole" })
	public String getRoles(HttpSession session,HttpServletRequest req) {
		System.out.println("inside getRoles method:::");
		int role=Integer.parseInt(req.getParameter("role"));
		SessionBean sb=(SessionBean)session.getAttribute("sessionBean");
		sb.setRoleID(role);
		//session.setAttribute("", role);
		System.out.println("role---- in login coontoll-"+role);
		String returnvalue = null;
		if(role==3)
		{
			sb.setUserRoleType("IIMA Student(Mentor)");
			returnvalue= "redirect:MentorProfile";
		}
		else if(role==4)
		{
			sb.setUserRoleType("IIMA Student(RM)");
			returnvalue= "redirect:appManage";
		}
		else if(role==1)
		{
			sb.setUserRoleType("IIMA Student");
			returnvalue= "redirect:getInboxData";
		}
		else if(role==8)
		{
			sb.setUserRoleType("IIMA Student(OfferProcessor)");
			returnvalue= "redirect:getOfferProcessor";
		}
		else if(role==9)
		{
			sb.setUserRoleType("IIMA Student(STRATO)");
			returnvalue= "redirect:getCompanyMaster";
		}
		else if(role==20)
		{
			sb.setUserRoleType("IIMA Student(SATCOM)");
			returnvalue= "redirect:getFileManager";
		}
		else if(role==48)
		{
			sb.setUserRoleType("IIMA Student(Nego)");
			returnvalue= "redirect:getNegoScreen";
		}
		
		else if(role==49)
		{
			sb.setUserRoleType("IIMA Student(SUPER RM on the day)");
			returnvalue= "redirect:getSuperRMPage1";
		}
		else if(role==50)
		{
			sb.setUserRoleType("IIMA Student(SUPER RM throughout the year)");
			returnvalue= "redirect:getSuperRMPage2";
		}
		return returnvalue;
	}
	@RequestMapping(value = { "/userEmailLogin" }, method = { RequestMethod.POST})
	public String userEmailLogin(HttpSession session,HttpServletRequest req, UserBean userBean)
	{
		String returnJsp = "";
		//System.out.println("email"+req.getParameter("emailid"));
		
		// SessionBean secb=(SessionBean)session.getAttribute("sessionBean");
		//SessionBean secb=(SessionBean)req.getSession().getAttribute("sessionBean");
		SessionBean secb=new SessionBean();
		 userBean.setUserEmailId(req.getParameter("emailid"));
         Map<String, String> getValues = loginService.loginGoogle(userBean, session);
         
         //System.out.println("getvalue"+getValues.get("emailid")+"session email"+req.getParameter("emailid"));
      if(getValues.get("emailid").equals(req.getParameter("emailid")))
      {
    	  //System.out.println("getvalue"+getValues.get("emailid"));
    	  //System.out.println("uid"+getValues.get("userid"));
    	 // System.out.println("pgp"+getValues.get("pgpflag"));
    	  //System.out.println("role"+getValues.get("roleid"));
    	  secb.setName(getValues.get("userName"));
      	//System.out.println("username"+sessionBean.getUserName());
    	  String roletype=getValues.get("roletype");
    	  System.out.println(roletype+"  asdfasdf");
    	  secb.setUserRoleType(roletype);
      	 int pgpflag=Integer.parseInt(getValues.get("pgpflag"));
    	  secb.setPgpFlag(pgpflag);
    	  int userid=Integer.parseInt(getValues.get("userid"));
    	  secb.setUserID(userid);
    	  int roleId=Integer.parseInt(getValues.get("roleid"));
    	  secb.setRoleID(roleId);
      	System.out.println("roleid"+secb.getRoleID()+roleId+userid+pgpflag);
          
      	session.setAttribute("sessionBean", secb);
		
      	
      }
    SessionBean secba=(SessionBean)session.getAttribute("sessionBean");
      if(secba.getRoleID()==1)
		{
			Map<String, String> returnValue = loginService.getStdRoles(session);
			//System.out
			session.setAttribute("roles",returnValue );
			//System.out.println("returnValue--------==="+returnValue);
			returnJsp = "redirect:getInboxData";
		}
       else if(secba.getRoleID()==21){
			System.out.println("role schedule");
  		returnJsp = "redirect:scheduleAdministratorPage";
  		}
		else{
			
		returnJsp = "redirect:forumHomePage";
		}
	
      return returnJsp;
      	
	}
	
	
}
