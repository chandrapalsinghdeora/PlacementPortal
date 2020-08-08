package com.precise.controller;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hashids.Hashids;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.precise.mail.SendMail;
import com.precise.model.CloseStatus;
import com.precise.model.HotList;
import com.precise.model.SessionBean;
import com.precise.model.UserProfile;
import com.precise.service.CloseStatusService;
import com.precise.service.HotListService;
import com.precise.service.RMService;

@Controller
public class HotListController {
	
	 @Autowired
     HotListService hotListservice;
     @Autowired
 	 CloseStatusService closeStatusService;
     @Autowired
     RMService rmService;
     @Autowired
 	 SendMail sendMail;
    
     @RequestMapping(value = "/gethotlistReceivePage")
     public ModelAndView getshotlistPage(HttpServletRequest req, CloseStatus closeStatus, Model model) {
		// System.out.println("RMController.getShortlistPage()--");
		SessionBean sessionBean = (SessionBean) req.getSession().getAttribute("sessionBean");
		int cmpRoleId = req.getParameter("cmpRoleId") == null ? 0 : Integer.parseInt(req.getParameter("cmpRoleId"));
		if (cmpRoleId != 0) {
			sessionBean.setCmpRoleId(cmpRoleId);
		} else {
			cmpRoleId = sessionBean.getCmpRoleId();
		}
		// System.out.println("cmpRoleId in savesend mail: "+cmpRoleId);
		if (closeStatus.getRankFlag() == null) {
			closeStatus.setRankFlag(false);
		}
		CloseStatus header = closeStatusService.getCloseStatusHeader(cmpRoleId);
		if (cmpRoleId != 0) {
			header.setRoleId(cmpRoleId);
		}
		closeStatus.setRoleId(cmpRoleId);
		model.addAttribute("closeStatus", header);
		model.addAttribute("cmpRoleId", cmpRoleId);
		List<HotList> shortlist = hotListservice.getShortlistedDataByRole(cmpRoleId);// role
		// System.out.println("list shotlist-"+shortlist);
		return new ModelAndView("hotListReceive", "shortlist", shortlist);
	}
	 
	 
     @RequestMapping(value="/getUserDetailsByRolNo1")
     public void getUserDetailsByRolNo(HttpSession session, HttpServletRequest req, HttpServletResponse res) {
		// SessionBean sbean=(SessionBean)session.getAttribute("sessionBean");

		int rollNumber = Integer.parseInt(req.getParameter("rollNumber"));
		List<UserProfile> userValue = closeStatusService.getUserValues(rollNumber);
		for (UserProfile in : userValue) {
			System.out.println("getUserValues user val" + in.getDateOfBirth());
		}

		// JSONObject responseDetailsJson = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		for (UserProfile in : userValue) {
			JSONObject userDetailsJson = new JSONObject();
			userDetailsJson.put("emailId", in.getEmailAddress());
			userDetailsJson.put("fullname", in.getFullName());
			userDetailsJson.put("cvName", in.getCvName());
			userDetailsJson.put("mentor", in.getMentor());
			userDetailsJson.put("gender", in.getGender());
			userDetailsJson.put("percentage", in.getPercentage());
			userDetailsJson.put("percenatageTwelve", in.getPercenatageTwelve());
			userDetailsJson.put("percentageGraduate", in.getPercentageGraduate());
			userDetailsJson.put("postgraduatePercentage", in.getPostgraduatePercentage());

			jsonArray.put(userDetailsJson);
		}
		System.out.println("json array- " + jsonArray);
		try {
			PrintWriter out = res.getWriter();
			out.print(jsonArray);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

     }
     
     @RequestMapping(value = "/saveHotlisted")
     public String saveHotlisted(HttpServletRequest req, HotList hotlist, HttpSession session) throws SQLException {
		// System.out.println("RMController.saveSchedule()--"+hotlist.getHotListIdsSelected());
		SessionBean sessionBean = (SessionBean) session.getAttribute("sessionBean");
		String shtIds = req.getParameter("hotListIdsSelected");
		System.out.println("shtIds::::" + shtIds);
		List<Integer> shorlistedIds = new ArrayList<Integer>();
		if (shtIds == "") {
			System.out.println("not checeked--");
		} else {
			String[] num = shtIds.split(",");
			for (String str : num) {
				System.out.println("hottlisted idss" + str);
				shorlistedIds.add(Integer.parseInt(str));
			}
		}

		String notShortListIds = req.getParameter("notShortlisted");
		List<Integer> notselecteddIds = new ArrayList<Integer>();
		if (notShortListIds == "") {
			System.out.println(" notHotListIds not checeked--");
		} else {
			String[] notshort = notShortListIds.split(",");
			for (String ns : notshort) {
				System.out.println("hotlisted idss" + ns);
				notselecteddIds.add(Integer.parseInt(ns));
			}
		}

		hotlist.setNotSelected(notselecteddIds);
		hotlist.setHotListIdslist(shorlistedIds);
		hotListservice.saveHotlist(hotlist);
		// System.out.println("shorlistedIds
		// list-"+shorlistedIds+"contrats--"+hotlist.getGreetingsHotlist());
		for (int i = 0; i < shorlistedIds.size(); i++) {
			System.out.println("greetings:::" + hotlist.getGreetingsHotlist());
			System.out.println("sessionBean::" + sessionBean.getUserID());
			hotListservice.selectUsersInformationForHotList(shorlistedIds.get(i), hotlist.getGreetingsHotlist(),
					sessionBean.getUserID(), "shortlist@iima.ac.in");

		}
		// return "redirect:gethotlistReceivePage";
		return "redirect:getAllCloseStatusDetails";
     }
	   
	   
	   
     @RequestMapping(value = "/saveHRHotlisted")
     public String saveHRShortlisted(HttpServletRequest req, HotList hotlist) throws SQLException {
		// System.out.println("RMController.saveHRShortlisted()--"+hotlist.getHotListIdsSelected());

		String shtIds = req.getParameter("shortListIdsSelected");
		List<Integer> shorlistedIds = new ArrayList<Integer>();
		if (shtIds == "") {
			System.out.println("not checeked--");
		} else {
			String[] num = shtIds.split(",");

			for (String str : num) {
				String[] appId = str.split("##");
				System.out.println("shortlisted idss" + str + " ::" + appId[0] + ":: " + appId[1]);
				shorlistedIds.add(Integer.parseInt(appId[1]));
			}
		}
		String notShortListIds = req.getParameter("notShortlisted");
		List<Integer> notselecteddIds = new ArrayList<Integer>();
		if (notShortListIds == "") {
			System.out.println(" notShortListIds not checeked--");
		} else {
			String[] notshort = notShortListIds.split(",");
			for (String ns : notshort) {
				System.out.println("shortlisted idss" + ns);
				String[] appId = ns.split("##");
				notselecteddIds.add(Integer.parseInt(appId[1]));
			}
		}

		hotlist.setNotSelected(notselecteddIds);
		hotlist.setHotListIdslist(shorlistedIds);
		hotListservice.saveHotlist(hotlist);

		System.out.println("shorlistedIds list-" + shorlistedIds + "contrats--" + hotlist.getGreetingsHotlist());

		return "redirect:getHotReceiveCompanyHRPage";
     }  
	   
	   
     @RequestMapping(value = "/saveHRHotListedWithoutSession")
     public String saveHRHotListedWithoutSession(HttpServletRequest req, HotList hotlist,CloseStatus closeStatus) throws SQLException {
		// System.out.println("RMController.saveHRShortlisted()--"+hotlist.getShortListIdsSelected());

		String shtIds = req.getParameter("hotListIdsSelected");
		List<Integer> shorlistedIds = new ArrayList<Integer>();
		String mailshorlists = "";
		List<Integer> applyIdListIdslist = new ArrayList<Integer>();

		if (shtIds == "") {
			System.out.println("not checeked--");
		} else {
			String[] num = shtIds.split(",");

			for (String str : num) {
				String[] appId = str.split("##");
				System.out.println("shortlisted idss" + str + " ::" + appId[0] + ":: " + appId[1]);
				shorlistedIds.add(Integer.parseInt(appId[1]));
				applyIdListIdslist.add(Integer.parseInt(appId[0]));
			}
		}
		for (Integer s : shorlistedIds)	{
			mailshorlists += s + ",";
		}
		 if(mailshorlists!=null && !mailshorlists.equals("")){
			 mailshorlists = mailshorlists.substring(0,mailshorlists.length()-1);
		 }
		/*
		 * String notShortListIds=req.getParameter("notShortlisted");
		 * List<Integer> notselecteddIds=new ArrayList<Integer>();
		 * if(notShortListIds==""){ System.out.println(
		 * " notShortListIds not checeked--"); } else{ String[]
		 * notshort=notShortListIds.split(","); for(String ns:notshort){
		 * System.out.println("shortlisted idss"+ns); String[]
		 * appId=ns.split("##");
		 * notselecteddIds.add(Integer.parseInt(appId[1])); } }
		 * 
		 * hotlist.setNotSelected(notselecteddIds);
		 */
		hotlist.setHotListIdslist(shorlistedIds);
		hotlist.setApplyIdListIdslist(applyIdListIdslist);

		hotListservice.saveHotlistedByHR(hotlist);
		int compRoleId = Integer.parseInt(req.getParameter("cmpRoleId"));
		CloseStatus allhrData = rmService.getAllHrList(compRoleId);
		CloseStatus header = closeStatusService.getCloseStatusHeader(compRoleId);
		// String compName=header.getFirmName();
		// String roleName=header.getRoleName();

		// String body="Hello,<br> We have shortlisted the candidates for role
		// "+roleName+" for company "+compName+"This is an auto generated mail,
		// please do not respond to this ID. If you have any queries please
		// contact your RM. <br>Thanks & Regards<br>IIMA Placement Committee";
		Hashids hashids = new Hashids("comp role id",10);
		String hash = hashids.encode(compRoleId);
		System.out.println("hashcode values::" + hash);
		//String returnPath = "getCompanyHRPage?cmpRoleId=" + "abdcr" + hash;
		String returnPath = "getCompanyHRPage?cmpRoleId=" + hash;

		String process = header.getProcess();
		int year = header.getYear();
		String subject = "[IIMA] " + process + "_" + year+"_"+header.getRoleName();

		if (allhrData != null) {
			/*
			 * StringBuffer mailHtml = new StringBuffer(); mailHtml.append(
			 * "<html><body>Dear "+s.getName()+
			 * ",<br/><br/> We have shortlisted the candidates for role "
			 * +roleName+" for company "+compName+" at year "+year+".") .append(
			 * "<br/><br/>This is an auto generated mail, don't respond to this. If you have any issues please reach out to "
			 * +compName+
			 * " HR Team.<br/><br/>Thanks & Regards<br>HR Team <br></br>"
			 * +compName+" </body></html>");
			 * System.out.println("mailHtml:::"+mailHtml.toString());
			 */
			System.out.println("Hrmail :: " + allhrData.getHrEmailId());
			System.out.println("RMEmailId :: " + allhrData.getEmailId());
			String link = req.getRequestURL().toString();
			String[] str = link.split("/IIMForum/");

			/*
			 * UUID uid =
			 * UUID.fromString("38400000-8cf0-11bd-b23e-10b96e4ef00d"); 
			 * String randomId=uid+"crid"+compRoleId;
			 */

			String path = str[0].concat("/IIMForum/" + returnPath);
			closeStatus.setGeneratedLink(path);
			String mailFormat = this.generateMailFormatHotlistHRMail(allhrData.getHrEmailId(), allhrData.getHrName(), path,
					allhrData.getMailDes(), compRoleId, allhrData.getEmailId(), mailshorlists);

			// sendMail.sendMail(body, s.getEmailID(), "Shortlisted generated By HR ");
			sendMail.sendMailHRShortList(mailFormat, allhrData.getHrEmailId(), allhrData.getEmailId(), subject);
		}

		/*
		 * int cmpRoleId=Integer.parseInt(req.getParameter("cmpRoleId")); String
		 * link=req.getRequestURL().toString(); System.out.println(
		 * "request uri in send mail--- "+link); String[]
		 * str=link.split("/IIMForum/"); for(String s:str){
		 * System.out.println("linked--"+s); }
		 * 
		 * String path=str[0].concat("/getCompanyHRPage?cmpRoleId="+cmpRoleId);
		 * 
		 * for(int i=0;i<shorlistedIds.size();i++){
		 * rmService.selectUsersInformation(shorlistedIds.get(i),hotlist.
		 * getGreetingsHotlist(),0);
		 * 
		 * }
		 */

		/*
		 * closeStatus.setGeneratedLink(path); String
		 * mailFormat=this.generateMailFormat(closeStatus.getEmailId(),
		 * path,null,closeStatus.getRankFlag(),closeStatus.getMailDes(),
		 * cmpRoleId); sendMail.sendMail(mailFormat, closeStatus.getEmailId(),
		 * "Hotlist generated");
		 */

		// System.out.println("shorlistedIds
		// list-"+shorlistedIds+"contrats--"+hotlist.getGreetings());

		return "redirect:getCompanyHRPage?cmpRoleId=" + req.getParameter("cmpRoleId");
     }    
     public String generateMailFormatHotlistHRMail(String hremailId, String hrName, String link, String msgDesc,
 			int companyRoleId, String rmemailId, String shortList) {
 		StringBuffer mailHtml = new StringBuffer();
 		// String prefernceValue="";
 		String shortListStudents = "";
 		// shortListStudents=closeStatusService.getSelectedUserValues(shortList,companyRoleId,false)+"<br/>";
 		//shortListStudents = closeStatusService.getSelectedUserHRList(shortList, companyRoleId) + "<br/>";
 		shortListStudents = closeStatusService.getSelectedUserHRHotList(shortList, companyRoleId) + "<br/>";

 		if (msgDesc != null) {
 			msgDesc = msgDesc + "<br/><br/>";
 		} else {
 			msgDesc = "";
 		}
 		mailHtml.append("<html><body>Dear " + hrName + ",<br/><br/> " + "List Of Hotlisted Student<br/><br/>"
 				+ shortListStudents + "<br/><br/>")
 				.append("<br/><br/><i>This is an auto generated mail, do not respond to this ID. If you have any queries please contact your RM "
 						+ rmemailId + " </i><br/><br/>Thanks & Regards<br>IIMA Placement Committee</body></html>");

 		/*
 		 * mailHtml.append("<html><body>Dear "+emailId+",<br/><br/> "
 		 * +msgDesc+prefernceValue+"Please <a href="+link+
 		 * ">Click here</a> for managing applications.") .append(
 		 * "<br/><br/><i>This is an auto generated mail, do not respond to this ID. If you have any queries please contact your RM "
 		 * +rmemailId+
 		 * " </i><br/><br/>Thanks & Regards<br>IIMA Placement Committee</body></html>"
 		 * );
 		 */
 		System.out.println("mailHtml:::" + mailHtml.toString());
 		return mailHtml.toString();
 	}

	   
     @RequestMapping(value = "/getHotReceiveCompanyHRPage")
     public ModelAndView getHotlistReceiveCompanyHRPage(HttpServletRequest req, CloseStatus closeStatus, Model model) {
		System.out.println("RMController.getShortlistReceiveCompanyHRPage()--");

		SessionBean sessionBean = (SessionBean) req.getSession().getAttribute("sessionBean");

		int cmpRoleId = 0;

		if (sessionBean == null) {
			cmpRoleId = Integer.parseInt(req.getParameter("cmpRoleId"));
		} else {
			cmpRoleId = req.getParameter("cmpRoleId") == null ? 0 : Integer.parseInt(req.getParameter("cmpRoleId"));
			if (cmpRoleId != 0) {
				sessionBean.setCmpRoleId(cmpRoleId);
			} else {
				cmpRoleId = sessionBean.getCmpRoleId();
			}
		}
		System.out.println("cmpRoleId in savesend mail: " + cmpRoleId);
		if (closeStatus.getRankFlag() == null) {
			closeStatus.setRankFlag(false);
		}
		CloseStatus header = closeStatusService.getCloseStatusHeader(cmpRoleId);
		if (cmpRoleId != 0) {
			header.setRoleId(cmpRoleId);
		}
		closeStatus.setRoleId(cmpRoleId);
		model.addAttribute("closeStatus", header);
		model.addAttribute(cmpRoleId);

		List<HotList> shortlist = hotListservice.getShortlistedDataByRole(cmpRoleId);
		System.out.println("list shotlist-" + shortlist);

		return new ModelAndView("hotlistReceiveCompanyHRPage", "shortlist", shortlist);
     }  
	 
     @RequestMapping(value = "/getHotlistReleaseCompanyHRPage")
     public ModelAndView getshortlistReleaseCompanyHRPage(HttpServletRequest req, CloseStatus closeStatus, Model model) {
		System.out.println("RMController.getShortlistReceiveCompanyHRPage()--");

		SessionBean sessionBean = (SessionBean) req.getSession().getAttribute("sessionBean");
		int cmpRoleId = req.getParameter("cmpRoleId") == null ? 0 : Integer.parseInt(req.getParameter("cmpRoleId"));

		if (cmpRoleId != 0) {
			sessionBean.setCmpRoleId(cmpRoleId);
		} else {
			cmpRoleId = sessionBean.getCmpRoleId();
		}
		System.out.println("cmpRoleId in savesend mail: " + cmpRoleId);
		if (closeStatus.getRankFlag() == null) {
			closeStatus.setRankFlag(false);
		}
		CloseStatus header = closeStatusService.getCloseStatusHeader(cmpRoleId);
		if (cmpRoleId != 0) {
			header.setRoleId(cmpRoleId);
		}
		closeStatus.setRoleId(cmpRoleId);
		model.addAttribute("closeStatus", header);

		List<HotList> shortlist = hotListservice.getHotReleaseCompany(cmpRoleId);

		System.out.println("list shotlist-" + shortlist);

		return new ModelAndView("hotListRelease", "shortlist", shortlist);

     }  
	
     @RequestMapping(value = "/saveReleaseHotlisted")
     public String saveReleaseShortlisted(HttpServletRequest request, HotList hotlist) throws SQLException {
		System.out.println("RMController.saveReleaseShortlisted()--");

		List<Integer> sids = new ArrayList<Integer>();
		List<Integer> applyIdListIdslist = new ArrayList<Integer>();
		String[] shtIds = request.getParameterValues("idlist");
		for (String str : shtIds) {
			String ids[] = str.split("##");
			System.out.println("saveReleaseShortlisted-  " + str);
			sids.add(Integer.parseInt(ids[0]));
			applyIdListIdslist.add(Integer.parseInt(ids[1]));

		}

		System.out.println("shtasasadasdaIds--" + shtIds);

		hotlist.setHotListIdslist(sids);
		hotlist.setApplyIdListIdslist(applyIdListIdslist);

		hotListservice.hotlistRMRelease(hotlist);

		return "redirect:getHotlistReleaseCompanyHRPage";
     } 
	
     @RequestMapping(value = "/processDoneHotlisted")
     public String processDoneHotlisted(HttpServletRequest request, HotList hotlist) throws SQLException {
		System.out.println("RMController.saveReleaseShortlisted()--");

		List<Integer> sids = new ArrayList<Integer>();
		List<Integer> applyIdListIdslist = new ArrayList<Integer>();
		String[] shtIds = request.getParameterValues("idhotlist");
		for (String str : shtIds) {
			String ids[] = str.split("##");
			System.out.println("saveReleaseShortlisted-  " + str);
			sids.add(Integer.parseInt(ids[0]));
			applyIdListIdslist.add(Integer.parseInt(ids[1]));

		}

		System.out.println("shtasasadasdaIds--" + shtIds);

		hotlist.setHotListIdslist(sids);
		hotlist.setApplyIdListIdslist(applyIdListIdslist);

		hotListservice.processDoneHotlisted(hotlist);

		return "redirect:getAllCloseStatusDetails";
		// return "redirect:getHotlistReleaseCompanyHRPage";

     } 
	 
     @RequestMapping(value = "/processDoneHotlistedBySuperRM1")
     public String processDoneHotlistedBySuperRM1(HttpServletRequest request, HotList hotlist) throws SQLException {
		System.out.println("RMController.processDoneHotlistedBySuperRM1()--");

		List<Integer> sids = new ArrayList<Integer>();
		List<Integer> applyIdListIdslist = new ArrayList<Integer>();
		String[] shtIds = request.getParameterValues("idhotlist");
		for (String str : shtIds) {
			String ids[] = str.split("##");
			System.out.println("saveReleaseShortlisted-  " + str);
			sids.add(Integer.parseInt(ids[0]));
			applyIdListIdslist.add(Integer.parseInt(ids[1]));

		}

		System.out.println("shtasasadasdaIds--" + shtIds);

		hotlist.setHotListIdslist(sids);
		hotlist.setApplyIdListIdslist(applyIdListIdslist);

		hotListservice.processDoneHotlisted(hotlist);

		return "redirect:getAllCloseStatusDetailsForSuperRM1";
		// return "redirect:getHotlistReleaseCompanyHRPage";

     } 
     
	 @RequestMapping(value = "/processDoneHotlistedBySuperRM2")
	 public String processDoneHotlistedBySuperRM2(HttpServletRequest request, HotList hotlist) throws SQLException {
		System.out.println("RMController.processDoneHotlistedBySuperRM2()--");

		List<Integer> sids = new ArrayList<Integer>();
		List<Integer> applyIdListIdslist = new ArrayList<Integer>();
		String[] shtIds = request.getParameterValues("idhotlist");
		for (String str : shtIds) {
			String ids[] = str.split("##");
			System.out.println("saveReleaseShortlisted-  " + str);
			sids.add(Integer.parseInt(ids[0]));
			applyIdListIdslist.add(Integer.parseInt(ids[1]));

		}

		System.out.println("shtasasadasdaIds--" + shtIds);

		hotlist.setHotListIdslist(sids);
		hotlist.setApplyIdListIdslist(applyIdListIdslist);

		hotListservice.processDoneHotlisted(hotlist);

		return "redirect:getAllCloseStatusDetailsForSuperRM2";
		// return "redirect:getHotlistReleaseCompanyHRPage";

	 } 
	 
	 @RequestMapping(value = "/HRprocessDoneHotlisted")
	 public String HRprocessDoneHotlisted(HttpServletRequest request, HotList hotlist) throws SQLException {
		System.out.println("RMController.HRprocessDoneHotlisted()--");

		List<Integer> sids = new ArrayList<Integer>();
		List<Integer> applyIdListIdslist = new ArrayList<Integer>();
		String[] shtIds = request.getParameterValues("idlist");
		for (String str : shtIds) {
			String ids[] = str.split("##");
			System.out.println("HRprocessDoneHotlisted-  " + str);
			sids.add(Integer.parseInt(ids[0]));
			applyIdListIdslist.add(Integer.parseInt(ids[1]));

		}

		hotlist.setShortListIdslist(sids);
		hotlist.setApplyIdListIdslist(applyIdListIdslist);

		hotListservice.processDoneHRHotlisted(hotlist);

		return "redirect:getCompanyHRPage?cmpRoleId=" + request.getParameter("cmpRoleId");
	 } 
	 
	 
	 @RequestMapping(value = "/downloadHotRMCv", produces = "application/zip")
	 public void zipFiles(HttpServletResponse response, HttpServletRequest request) throws IOException {
		System.out.println("RMController.zipFiles()--cvleter-" + request.getParameter("coverLetter"));
		String shtIds = request.getParameter("shortListIdsSelected");
		String[] num = shtIds.split(",");
		List<Integer> shorlistedIds = new ArrayList<Integer>();
		for (String str : num) {
			String[] appId = str.split("##");
			System.out.println("shortlisted idss" + str + " ::" + appId[0] + ":: " + appId[1]);
			shorlistedIds.add(Integer.parseInt(appId[1]));
		}

		String downloadCvId = request.getParameter("downloadCvId");
		System.out.println("downloadCvId::" + downloadCvId);
		List<CloseStatus> downloadCvlist = new ArrayList<CloseStatus>();
		CloseStatus closeStatus = null;

		for (String id : num) {
			String[] appId = id.split("##");
			System.out.println("shortlisted idss ::::" + id + " ::" + appId[0] + ":: " + appId[1]);
			closeStatus = new CloseStatus();
			closeStatus.setApplyId(Integer.parseInt(appId[0]));
			System.out.println("id set value :: " + closeStatus.getApplyId());
			downloadCvlist.add(closeStatus);
		}

		List<String> downloadFileNameList = null;

		if (request.getParameter("coverLetter") == null) {
			downloadFileNameList = rmService.getRMDownloadCVList(downloadCvlist);
		} else {
			downloadFileNameList = rmService.getRMDownloadCoverList(downloadCvlist);
		}

		/*
		 * List<String> downloadFileNameList = new ArrayList<String>();
		 * downloadFileNameList.add("D:\\Animal.java");
		 */
		// setting headers
		List<File> files = new ArrayList<File>();
		for (String filePath : downloadFileNameList) {
			files.add(new File(filePath));
		}

		try {
			if (files != null && files.size() > 0) {
				byte[] zip = zipFiles(files);
				ServletOutputStream sos = response.getOutputStream();
				response.setContentType("application/zip");
				response.setHeader("Content-Disposition", "attachment; filename=DATA.ZIP");

				sos.write(zip);
				sos.flush();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	 }

	 public byte[] zipFiles(List<File> files) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ZipOutputStream zos = new ZipOutputStream(baos);
		byte bytes[] = new byte[2048];

		for (File fileName : files) {
			if (fileName.exists()) {
				FileInputStream fis = new FileInputStream(fileName.getCanonicalFile());
				BufferedInputStream bis = new BufferedInputStream(fis);

				zos.putNextEntry(new ZipEntry(fileName.getName()));

				int bytesRead;
				while ((bytesRead = bis.read(bytes)) != -1) {
					zos.write(bytes, 0, bytesRead);
				}
				zos.closeEntry();
				bis.close();
				fis.close();
			}
		}
		zos.flush();
		baos.flush();
		zos.close();
		baos.close();

		return baos.toByteArray();
	 }
	
	 
	  @RequestMapping(value = "/shortlistRemoveHL")
	     public String shortlistRemoveHL(HttpServletRequest req, HotList hotlist,CloseStatus closeStatus) throws SQLException {
			// System.out.println("RMController.saveHRShortlisted()--"+hotlist.getShortListIdsSelected());

			String shtIds = req.getParameter("hotListIdsSelected");
			List<Integer> shorlistedIds = new ArrayList<Integer>();
			String mailshorlists = "";
			List<Integer> applyIdListIdslist = new ArrayList<Integer>();

			if (shtIds == "") {
				System.out.println("not checeked--");
			} else {
				String[] num = shtIds.split(",");

				for (String str : num) {
					String[] appId = str.split("##");
					System.out.println("shortlisted idss" + str + " ::" + appId[0] + ":: " + appId[1]);
					shorlistedIds.add(Integer.parseInt(appId[1]));
					applyIdListIdslist.add(Integer.parseInt(appId[0]));
				}
			}
			for (Integer s : shorlistedIds)	{
				mailshorlists += s + ",";
			}
			 if(mailshorlists!=null && !mailshorlists.equals("")){
				 mailshorlists = mailshorlists.substring(0,mailshorlists.length()-1);
			 }
			/*
			 * String notShortListIds=req.getParameter("notShortlisted");
			 * List<Integer> notselecteddIds=new ArrayList<Integer>();
			 * if(notShortListIds==""){ System.out.println(
			 * " notShortListIds not checeked--"); } else{ String[]
			 * notshort=notShortListIds.split(","); for(String ns:notshort){
			 * System.out.println("shortlisted idss"+ns); String[]
			 * appId=ns.split("##");
			 * notselecteddIds.add(Integer.parseInt(appId[1])); } }
			 * 
			 * hotlist.setNotSelected(notselecteddIds);
			 */
			hotlist.setHotListIdslist(shorlistedIds);
			hotlist.setApplyIdListIdslist(applyIdListIdslist);

			hotListservice.removeHotlistedByHR(hotlist);
			int compRoleId = Integer.parseInt(req.getParameter("cmpRoleId"));
			CloseStatus allhrData = rmService.getAllHrList(compRoleId);
			CloseStatus header = closeStatusService.getCloseStatusHeader(compRoleId);
			// String compName=header.getFirmName();
			// String roleName=header.getRoleName();

			// String body="Hello,<br> We have shortlisted the candidates for role
			// "+roleName+" for company "+compName+"This is an auto generated mail,
			// please do not respond to this ID. If you have any queries please
			// contact your RM. <br>Thanks & Regards<br>IIMA Placement Committee";
			Hashids hashids = new Hashids("comp role id",10);
			String hash = hashids.encode(compRoleId);
			System.out.println("hashcode values::" + hash);
			//String returnPath = "getCompanyHRPage?cmpRoleId=" + "abdcr" + hash;
			String returnPath = "getCompanyHRPage?cmpRoleId=" + hash;

			String process = header.getProcess();
			int year = header.getYear();
			String subject = "[IIMA] " + process + "_" + year+"_"+header.getRoleName();

			if (allhrData != null) {
				/*
				 * StringBuffer mailHtml = new StringBuffer(); mailHtml.append(
				 * "<html><body>Dear "+s.getName()+
				 * ",<br/><br/> We have shortlisted the candidates for role "
				 * +roleName+" for company "+compName+" at year "+year+".") .append(
				 * "<br/><br/>This is an auto generated mail, don't respond to this. If you have any issues please reach out to "
				 * +compName+
				 * " HR Team.<br/><br/>Thanks & Regards<br>HR Team <br></br>"
				 * +compName+" </body></html>");
				 * System.out.println("mailHtml:::"+mailHtml.toString());
				 */
				System.out.println("Hrmail :: " + allhrData.getHrEmailId());
				System.out.println("RMEmailId :: " + allhrData.getEmailId());
				String link = req.getRequestURL().toString();
				String[] str = link.split("/IIMForum/");

				/*
				 * UUID uid =
				 * UUID.fromString("38400000-8cf0-11bd-b23e-10b96e4ef00d"); 
				 * String randomId=uid+"crid"+compRoleId;
				 */

				String path = str[0].concat("/IIMForum/" + returnPath);
				closeStatus.setGeneratedLink(path);
				String mailFormat = this.generateMailFormatHotlistHRMail(allhrData.getHrEmailId(), allhrData.getHrName(), path,
						allhrData.getMailDes(), compRoleId, allhrData.getEmailId(), mailshorlists);

				// sendMail.sendMail(body, s.getEmailID(), "Shortlisted generated By HR ");
				sendMail.sendMailHRShortList(mailFormat, allhrData.getHrEmailId(), allhrData.getEmailId(), subject);
			}

			/*
			 * int cmpRoleId=Integer.parseInt(req.getParameter("cmpRoleId")); String
			 * link=req.getRequestURL().toString(); System.out.println(
			 * "request uri in send mail--- "+link); String[]
			 * str=link.split("/IIMForum/"); for(String s:str){
			 * System.out.println("linked--"+s); }
			 * 
			 * String path=str[0].concat("/getCompanyHRPage?cmpRoleId="+cmpRoleId);
			 * 
			 * for(int i=0;i<shorlistedIds.size();i++){
			 * rmService.selectUsersInformation(shorlistedIds.get(i),hotlist.
			 * getGreetingsHotlist(),0);
			 * 
			 * }
			 */

			/*
			 * closeStatus.setGeneratedLink(path); String
			 * mailFormat=this.generateMailFormat(closeStatus.getEmailId(),
			 * path,null,closeStatus.getRankFlag(),closeStatus.getMailDes(),
			 * cmpRoleId); sendMail.sendMail(mailFormat, closeStatus.getEmailId(),
			 * "Hotlist generated");
			 */

			// System.out.println("shorlistedIds
			// list-"+shorlistedIds+"contrats--"+hotlist.getGreetings());

			return "redirect:getCompanyHRPage?cmpRoleId=" + req.getParameter("cmpRoleId");
	     }    
	    
}
