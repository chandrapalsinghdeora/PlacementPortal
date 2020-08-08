package com.precise.controller;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
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

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.hashids.Hashids;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.precise.fileManager.FileUtil;
import com.precise.mail.SendMail;
import com.precise.model.CloseStatus;
import com.precise.model.HotList;
import com.precise.model.Message;
import com.precise.model.Schedule;
import com.precise.model.SessionBean;
import com.precise.model.ShortList;
import com.precise.model.UserProfile;
import com.precise.service.CloseStatusService;
import com.precise.service.CloseStatusService1;
import com.precise.service.HotListService;
import com.precise.service.RMService;
import com.precise.service.RMService1;

@Controller
public class RMController {
	@Autowired
	RMService rmService;
	@Autowired
	RMService1 rmService1;
	@Autowired
	CloseStatusService closeStatusService;
	@Autowired
	HotListService hotListservice;
	@Autowired
	CloseStatusService1 closeStatusService1;
	@Autowired
	SendMail sendMail;
	String emailId=new String();
	String emailId1=new String();
	 @RequestMapping(value = "/getRMMessagePage")
	 public ModelAndView getRMMessagePage(HttpServletRequest req) {
	 		System.out.println("RMController.getRMMessagePage()");	 		
	    	List<Message> messages=rmService.getAllMessages();	    	
	 		return new ModelAndView("RMMessages","messages",messages);
	 }
	 
	 @RequestMapping(value = "/getMessagesByMessageId")
	 public void getMessagesByMessageId(HttpServletRequest req, HttpServletResponse res) {
		SessionBean sessionBean = (SessionBean) req.getSession().getAttribute("sessionBean");
		int msgId = req.getParameter("cmpRoleId") == null ? 0 : Integer.parseInt(req.getParameter("cmpRoleId"));
		// cmpRoleId = 46;
		if (msgId != 0) {
			sessionBean.setCmpRoleId(msgId);
		} else {
			msgId = sessionBean.getCmpRoleId();
		}
		// int msgId=Integer.parseInt(req.getParameter("msgId"));
		System.out.println("RMController.getRMMessagePage()-" + msgId);
		Message messageByMsgId = rmService.getMessagesByMessageId(msgId);
		req.setAttribute("messageByMsgId", messageByMsgId);
		// System.out.println("msg_id-"+messageByMsgId.getMessageId()+"msg
		// sub-"+messageByMsgId.getSubject()+"msg-"+messageByMsgId.getMessage());

		JSONObject formDetailsJson = new JSONObject();
		formDetailsJson.put("messageId", messageByMsgId.getMessageId());
		formDetailsJson.put("subject", messageByMsgId.getSubject());
		formDetailsJson.put("message", messageByMsgId.getMessage());

		try {
			PrintWriter out = res.getWriter();
			out.print(formDetailsJson);
			out.flush();
			out.close();
		} catch (IOException e) {
			System.out.println("excepition in json aararr" + e);
			e.printStackTrace();
		}

	}
	 
	 @RequestMapping(value = "/saveMessage")
	public String saveMessage(HttpServletRequest req, Message msg) {
		System.out.println("RMController.saveMessage()");
		if (msg.getMessageId() == 0) {
			rmService.saveMessage(msg);
		} else if (msg.getMessageId() != 0) {
			System.out.println("elseeeeesss -" + msg.getMessageId() + msg.getMessage());
			System.out.println("RMController.saveMessage() else--");
			rmService.updateMessage(msg);
		}

		return "redirect:getRMMessagePage";
	}
	
	@RequestMapping(value="/deleteMessge",method={ RequestMethod.POST})
	public String deleteMessge(HttpServletRequest request, Message msg) {
		System.out.println("elseeeeesss -" + msg.getMessageId() + msg.getMessage());
		System.out.println("RMController.saveMessage() else--");
		rmService.deleteMessage(msg);
		return "redirect:getRMMessagePage";
	}
	 
	 @RequestMapping(value = "/getRMSchedulePage")
	public ModelAndView getRMSchedulePage(HttpServletRequest req) {
		String firmRoleId = req.getParameter("cmpRoleId");
		System.out.println("cmpRoleId :: "+firmRoleId);
		int cmpRoleId=0;
		if(firmRoleId!=null){
			boolean flag = StringUtils.isNumeric(firmRoleId);
			if(!flag){
				Hashids hashids1 = new Hashids("comp role id",10);
				long[] numbers = hashids1.decode(firmRoleId);
				System.out.println("number::"+numbers[0]);
				cmpRoleId=(int)(numbers[0]);
			}else{
				cmpRoleId=Integer.parseInt(req.getParameter("cmpRoleId"));
			}
		}
		SessionBean sessionBean = (SessionBean) req.getSession().getAttribute("sessionBean");
		if (cmpRoleId != 0) {
			sessionBean.setCmpRoleId(cmpRoleId);
		} else {
			cmpRoleId = sessionBean.getCmpRoleId();
		}
		System.out.println("RMController.getRMSchedulePage()");
		CloseStatus header = closeStatusService.getCloseStatusHeader(cmpRoleId);

		List<Message> messages = rmService.getAllMessages();
		List<Schedule> schedule = rmService.getAllSchedules(header.getCmpId());
		/* List<Schedule> schedule=rmService.getAllSchedules(); */
		req.setAttribute("schedule", schedule);

		header.setRoleId(cmpRoleId);
		System.out.println(header.getFirmName() + "::" + header.getRoleName() + "::" + header.getProcess() + "::"
				+ header.getExperienceReq());
		req.setAttribute("schedulestatus", header);

		return new ModelAndView("RMShedulePage", "messages", messages);
	}
	 
	 @RequestMapping(value = "/saveSchedule")
	public String saveSchedule(HttpServletRequest req, Schedule schedule) {
		System.out.println("RMController.saveSchedule()");

		System.out.println(" swcdule id :: " + schedule.getScheduleId() + " cmpid : " + schedule.getCmpId());
		if (schedule.getScheduleId() == 0) {
			/*
			 * Date schDate=schedule.getScheduleDate(); String dateForMySql =
			 * ""; SimpleDateFormat sdf = new SimpleDateFormat(
			 * "dd-M-yyyy hh:mm a");//dd-M-yyyy hh:mm:ss yyyy-MM-dd dateForMySql
			 * = sdf.format(schDate); schedule.setDateTime(dateForMySql);
			 */

			rmService.saveSchedule(schedule);
		} else {
			/*
			 * Date schDate=schedule.getScheduleDate(); String dateForMySql =
			 * ""; SimpleDateFormat sdf = new SimpleDateFormat(
			 * "dd-M-yyyy hh:mm a");//dd-M-yyyy hh:mm:ss yyyy-MM-dd dateForMySql
			 * = sdf.format(schDate); schedule.setDateTime(dateForMySql);
			 * System.out.println("simple date format"+dateForMySql);
			 */
			rmService.updateSchedule(schedule);
		}

		return "redirect:getRMSchedulePage";
	}
	 
	 @RequestMapping(value="/deleteScheduleData",method={ RequestMethod.POST})
	public String deleteSchedule(HttpServletRequest request, Schedule schedule) {
		System.out.println("RMController.deleteSchedule() " + schedule.getScheduleId());	
		// int scheduleId=Integer.parseInt((String)request.getParameter("scheduleId"));
		int scheduleId = request.getParameter("scheduleId") == null ? 0
				: Integer.parseInt(request.getParameter("scheduleId"));
		rmService.deleteSchedule(schedule.getScheduleId());
		return "redirect:getRMSchedulePage";
	}
	 
	 
	 @RequestMapping(value = "/getScheduleByScheduleId")
	public void getScheduleByScheduleId(HttpServletRequest req, HttpServletResponse res) {

		int scheduleId = Integer.parseInt((String) req.getParameter("scheduleId"));
		System.out.println("RMController.getScheduleByScheduleId()-" + scheduleId);
		Schedule schedule = rmService.getScheduleByScheduleId(scheduleId);
		// req.setAttribute("schedule",schedule );

		JSONObject formDetailsJson = new JSONObject();
		formDetailsJson.put("scheduleId", schedule.getScheduleId());
		formDetailsJson.put("subjectId", schedule.getSubjectId());
		formDetailsJson.put("subject", schedule.getSubject());
		formDetailsJson.put("dateTime", schedule.getDateTime());

		try {
			PrintWriter out = res.getWriter();
			out.print(formDetailsJson);
			out.flush();
			out.close();
		} catch (IOException e) {
			System.out.println("excepition in json aararr" + e);
			e.printStackTrace();
		}

	}
	 
	 @RequestMapping(value = "/getshortlistReceivePage")
	 public String getShortlistPage(HttpServletRequest req, CloseStatus closeStatus, Model model) {
		System.out.println("RMController.getshortlistReceivePage");
		SessionBean sessionBean = (SessionBean) req.getSession().getAttribute("sessionBean");
		int cmpRoleId = req.getParameter("cmpRoleId") == null ? 0 : Integer.parseInt(req.getParameter("cmpRoleId"));
		// cmpRoleId = 46;
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
		// System.out.println("company name-"+header.getFirmName() + "role
		// name::" + header.getRoleName() );
		// req.setAttribute("closeStatus", header);
		closeStatus.setRoleId(cmpRoleId);
		Integer appid = cmpRoleId;
		Hashids hashids = new Hashids("comp role id",10);
		//String hash = "abdcr" + hashids.encode(appid);
		String hash = hashids.encode(appid);
		model.addAttribute("closeStatus", header);
		model.addAttribute("cmpRoleId", hash);

		/*
		 * model.addAttribute("closeStatus",header);
		 * model.addAttribute("cmpRoleId",cmpRoleId);
		 */

		List<ShortList> shortlist = rmService.getShortlistedDataByRole(cmpRoleId);// role
		System.out.println("list shotlist-" + shortlist);
		// return new
		// ModelAndView("getAllCloseStatusDetails","shortlist",shortlist);
		return "redirect:getAllCloseStatusDetails";
	}
	 
	 
	 @RequestMapping(value="/getUserDetailsByRolNo")
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
	 
	 @RequestMapping(value="/getGreetings")
	public void getGreetings(HttpSession session, HttpServletRequest req, HttpServletResponse res, Model model) {
		System.out.println("geretingg==" + req.getParameter("cmpRoleId"));
		SessionBean sessionBean = (SessionBean) req.getSession().getAttribute("sessionBean");
		int cmpRoleId = req.getParameter("cmpRoleId") == null ? 0 : Integer.parseInt(req.getParameter("cmpRoleId"));

		if (cmpRoleId != 0) {
			// System.out.println("hiif");
			sessionBean.setCmpRoleId(cmpRoleId);
		} else {
			// System.out.println("hielse");
			cmpRoleId = sessionBean.getCmpRoleId();
		}
		model.addAttribute("cmpRoleId", cmpRoleId);
		JSONArray jsonArray = rmService.getGreetings(cmpRoleId);
		try {
			PrintWriter out = res.getWriter();
			out.print(jsonArray);
			out.flush();
			out.close();
		} catch (IOException e) {

			e.printStackTrace();
		}

	}
	   
	 @RequestMapping(value="/getGreetingsWithoutSession")
	 public void getGreetingsWithoutSession(HttpSession session, HttpServletRequest req, HttpServletResponse res,
			Model model) {
		System.out.println("geretingg==" + req.getParameter("cmpRoleId"));

		int cmpRoleId = Integer.parseInt(req.getParameter("cmpRoleId"));

		model.addAttribute("cmpRoleId", cmpRoleId);
		JSONArray jsonArray = rmService.getGreetings(cmpRoleId);
		try {
			PrintWriter out = res.getWriter();
			out.print(jsonArray);
			out.flush();
			out.close();
		} catch (IOException e) {

			e.printStackTrace();
		}

	}
	 
	@RequestMapping(value = "/saveShortlisted")
	public String saveShortlisted(HttpServletRequest req, ShortList shortlist) throws SQLException {
		System.out.println("RMController.saveShortlisted()--" + shortlist.getShortListIdsSelected() + "not selected-"
				+ shortlist.getNotSelected());
		String shtIds = req.getParameter("shortListIdsSelected");
		SessionBean sessionBean = (SessionBean) req.getSession().getAttribute("sessionBean");
		int userid = sessionBean.getUserID();
		List<Integer> shorlistedIds = new ArrayList<Integer>();
		if (shtIds == "") {
			System.out.println("not checeked--");
		} else {
			String[] num = shtIds.split(",");
			for (String str : num) {
				System.out.println("shortlisted idss" + str);
				shorlistedIds.add(Integer.parseInt(str));
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
				notselecteddIds.add(Integer.parseInt(ns));
			}
		}
		shortlist.setNotSelected(notselecteddIds);
		shortlist.setShortListIdslist(shorlistedIds);
		rmService.shortlistRelease(shortlist,userid);
		System.out.println("shorlistedIds list-" + shorlistedIds + "contrats--" + shortlist.getGreetings()
				+ "shorlistedIds.size():::" + shorlistedIds.size());
		String ids = "";
		/*
		 * for(int i=0;i<shorlistedIds.size();i++){
		 * rmService.selectUsersInformation(shorlistedIds.get(i),shortlist.
		 * getGreetings(),sessionBean.getUserID());
		 * 
		 * }
		 */

		return "redirect:getshortlistReceivePage";
	}
	
	
	@RequestMapping(value = "/shortlistRemove")
	public String saveRemoveShortlisted(HttpServletRequest req, ShortList shortlist) throws SQLException {
		System.out.println("RMController.saveShortlisted()--" + shortlist.getShortListIdsSelected() + "not selected-"
				+ shortlist.getNotSelected());
		String shtIds = req.getParameter("shortListIdsSelected");
		SessionBean sessionBean = (SessionBean) req.getSession().getAttribute("sessionBean");
		int userid=sessionBean.getUserID();
		List<Integer> shorlistedIds = new ArrayList<Integer>();
		if (shtIds == "") {
			System.out.println("not checeked--");
		} else {
			String[] num = shtIds.split(",");
			for (String str : num) {
				System.out.println("shortlisted idss" + str);
				shorlistedIds.add(Integer.parseInt(str));
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
				notselecteddIds.add(Integer.parseInt(ns));
			}
		}
		shortlist.setNotSelected(notselecteddIds);
		shortlist.setShortListIdslist(shorlistedIds);
		rmService.shortlistRemove(shortlist,userid);
		System.out.println("shorlistedIds list-" + shorlistedIds + "contrats--" + shortlist.getGreetings()
				+ "shorlistedIds.size():::" + shorlistedIds.size());
		String ids = "";
		/*
		 * for(int i=0;i<shorlistedIds.size();i++){
		 * rmService.selectUsersInformation(shorlistedIds.get(i),shortlist.
		 * getGreetings(),sessionBean.getUserID());
		 * 
		 * }
		 */

		return "redirect:getshortlistReceivePage";
	}
	  
	  
	@RequestMapping(value = "/saveShortlistedOne") // shortlistOne after release shotlist
	public String saveShortlistedOne(HttpServletRequest req, ShortList shortlist) throws SQLException {
		System.out.println("RMController.saveShortlisted()--" + shortlist.getShortListIdsSelected() + "not selected-"
				+ shortlist.getNotSelected());
		
		SessionBean sessionBean = (SessionBean) req.getSession().getAttribute("sessionBean");
		String shtIds = req.getParameter("shortListOneIdsSelected");
		String applyIdListOneIds = req.getParameter("applyIdListOneIds");
		List<Integer> applyIdslist = new ArrayList<Integer>();
		List<Integer> shorlistedIds = new ArrayList<Integer>();
		if (shtIds == "") {
			System.out.println("not checeked for gd shortlist after release");
		} else {
			String[] num = shtIds.split(",");
			for (String str : num) {
				System.out.println("shortlisted idss" + str);
				shorlistedIds.add(Integer.parseInt(str));
			}
			String[] applyIdList = applyIdListOneIds.split(",");
			for (String ns : applyIdList) {
				System.out.println("applyId idss" + ns);
				applyIdslist.add(Integer.parseInt(ns));
			}
		}
		
		shortlist.setApplyIdListIdslist(applyIdslist);
		int userId=sessionBean.getUserID();
		shortlist.setCreatedBy(userId);
		shortlist.setShortListIdslist(shorlistedIds);
		rmService1.shortlistOneAfterGD(shortlist);
		int cmpRoleId = req.getParameter("cmpRoleId") == null ? 0 : Integer.parseInt(req.getParameter("cmpRoleId"));
		if (cmpRoleId != 0) {
			// System.out.println("hiif");
			sessionBean.setCmpRoleId(cmpRoleId);
		} else {
			// System.out.println("hielse");
			cmpRoleId = sessionBean.getCmpRoleId();
		}
		
		
		CloseStatus allhrData = rmService.getAllHrList(cmpRoleId);
		CloseStatus header = closeStatusService1.getCloseStatusHeader(cmpRoleId);
		String process = header.getProcess();
		int year = header.getYear();
		String subject = "[IIMA] GD Shortlist_" + process + "_" + year+"_"+header.getRoleName();
		
		Hashids hashids = new Hashids("comp role id",10);
		String hash = hashids.encode(cmpRoleId);
		System.out.println("hashcode values::" + hash);
		//String returnPath = "getCompanyHRPage?cmpRoleId=" + "abdcr" + hash;
		String returnPath = "getCompanyHRPage?cmpRoleId=" + hash;
		String link = req.getRequestURL().toString();
		String[] str = link.split("/IIMForum/");
		String path = str[0].concat("/IIMForum/" + returnPath);
		String mailshorlists = "";
		for (Integer s : applyIdslist)	{
			mailshorlists += s + ",";
		}
		 if(mailshorlists!=null && !mailshorlists.equals("")){
			 mailshorlists = mailshorlists.substring(0,mailshorlists.length()-1);
		 }
		if (allhrData != null) {
			
			String mailFormat = this.generateMailFormatRMShortlistMail(allhrData.getHrEmailId(), allhrData.getHrName(),path,
					allhrData.getMailDes(), cmpRoleId, allhrData.getEmailId(), mailshorlists,1);

			sendMail.sendMailHRShortList(mailFormat, allhrData.getHrEmailId(), allhrData.getEmailId(), subject);
			
		}

		
		// model.addAttribute("cmpRoleId",cmpRoleId);
		CloseStatus studentGreeting = rmService1.getStudentGreetings(cmpRoleId);
		String studentGreetingSubject = "GD Shortlisted in " + studentGreeting.getFirmName() + "_"
				+ studentGreeting.getRoleName();
		int size=applyIdslist.size();
		int loop=size/80;
		int count=0;
		int i;
		for(int j=0;j<loop;j++)
		{
			for ( i = j*80; i<(j*80)+80; i++) {
				emailId=emailId.concat(rmService1.selectUsersInformationByRM(applyIdslist.get(i), studentGreetingSubject, shortlist.getGreetings(),
						sessionBean.getUserID(), "shortlist@iima.ac.in")+",");
				
			}
			
			//System.out.println("parth : "+emailId);
			String emailIdlist=emailId.toString();
			emailIdlist= emailIdlist.substring(0, emailIdlist.length()-1).trim();
			//System.out.println("parth111"+emailIdlist);
			sendMail.sendMailShortList(shortlist.getGreetings(),emailIdlist,"",studentGreetingSubject);
			emailId=new String();
			count=i;
		}
		//System.out.println("csdddd"+count);
		for ( i = count; i<size; i++) {
			emailId=emailId.concat(rmService1.selectUsersInformationByRM(applyIdslist.get(i), studentGreetingSubject, shortlist.getGreetings(),
					sessionBean.getUserID(), "shortlist@iima.ac.in")+",");
		
		}
		//System.out.println("parth : "+emailId);
		String emailIdlist=emailId.toString();
		emailIdlist= emailIdlist.substring(0, emailIdlist.length()-1).trim();
		//System.out.println("parth111"+emailIdlist);
		sendMail.sendMailShortList(shortlist.getGreetings(),emailIdlist,"",studentGreetingSubject);
		emailId=new String();
		
		
		return "redirect:getAllCloseStatusDetails";
	}
	   
	   
	   @RequestMapping(value = "/releaseHRShortlisted")
	public String releaseHRShortlisted(HttpServletRequest req, ShortList shortlist) throws SQLException {
		System.out.println("RMController.releaseHRShortlisted()--" + shortlist.getShortListIdsSelected()
				+ "not selected-" + shortlist.getNotSelected());
		// String shtIds=req.getParameter("shortListIdsSelected");
		SessionBean sessionBean = (SessionBean) req.getSession().getAttribute("sessionBean");
		int userid=sessionBean.getUserID();
		List<Integer> shorlistedIds = new ArrayList<Integer>();
		List<Integer> applyId = new ArrayList<Integer>();
		String mailshorlists="";
		/*
		 * if(shtIds==""){ System.out.println("not checeked--"); }else{ String[]
		 * num=shtIds.split(","); for(String str:num){ System.out.println(
		 * "shortlisted idss"+str); shorlistedIds.add(Integer.parseInt(str)); }
		 * }
		 * 
		 * String notShortListIds=req.getParameter("notShortlisted");
		 * List<Integer> notselecteddIds=new ArrayList<Integer>();
		 * if(notShortListIds==""){ System.out.println(
		 * " notShortListIds not checeked--"); } else{ String[]
		 * notshort=notShortListIds.split(","); for(String ns:notshort){
		 * System.out.println("shortlisted idss"+ns);
		 * notselecteddIds.add(Integer.parseInt(ns)); } }
		 */
		System.out.println("comp role id in hr srel--" + shortlist.getCmpRoleId());
		List<ShortList> shortlistedByHr = rmService.getHRShortlistedDataByRole(shortlist.getCmpRoleId());

		for (ShortList s : shortlistedByHr) {
			shorlistedIds.add(s.getShortListId());
			applyId.add(s.getApplyId());
		}
		for (Integer s : shorlistedIds)	{
			mailshorlists += s + ",";
		}
		 if(mailshorlists!=null && !mailshorlists.equals("")){
			 mailshorlists = mailshorlists.substring(0,mailshorlists.length()-1);
		 }

		// shortlist.setNotSelected(notselecteddIds);
		shortlist.setShortListIdslist(shorlistedIds);
		shortlist.setApplyIdListIdslist(applyId);
		// rmService.shortlistRelease(shortlist);11
		System.out.println("shorlistedIds list-" + shorlistedIds + "contrats--" + shortlist.getGreetings()
				+ "shorlistedIds.size():::" + shorlistedIds.size());
		String ids = "";

		int cmpRoleId = req.getParameter("cmpRoleId") == null ? 0 : Integer.parseInt(req.getParameter("cmpRoleId"));
		if (cmpRoleId != 0) {
			// System.out.println("hiif");
			sessionBean.setCmpRoleId(cmpRoleId);
		} else {
			// System.out.println("hielse");
			cmpRoleId = sessionBean.getCmpRoleId();
		}
		CloseStatus allhrData = rmService.getAllHrList(cmpRoleId);
		// System.out.println("allhrData:::::::::::"+allhrData);

		CloseStatus header = closeStatusService.getCloseStatusHeader(cmpRoleId);
		// model.addAttribute("cmpRoleId",cmpRoleId);
		Hashids hashids = new Hashids("comp role id",10);
		String hash = hashids.encode(cmpRoleId);
		System.out.println("hashcode values::" + hash);
		//String returnPath = "getCompanyHRPage?cmpRoleId=" + "abdcr" + hash;
		String returnPath = "getCompanyHRPage?cmpRoleId=" + hash;

		String process = header.getProcess();
		int year = header.getYear();
		String subject = "[IIMA] PI Shortlist_" + process + "_" + year+"_"+header.getRoleName();
		System.out.println("subject :::::::"+subject);
		String link = req.getRequestURL().toString();
		String[] str = link.split("/IIMForum/");
		String path = str[0].concat("/IIMForum/" + returnPath);
		if (allhrData != null) {
			String mailFormat = this.generateMailFormatHRMail(allhrData.getHrEmailId(), allhrData.getHrName(), path,
					allhrData.getMailDes(), cmpRoleId, allhrData.getEmailId(), mailshorlists);
			sendMail.sendMailHRShortList(mailFormat, allhrData.getHrEmailId(), allhrData.getEmailId(), subject);
		}
		rmService.releaseHRShortlisted(shortlist,userid);
		CloseStatus studentGreeting = rmService.getStudentGreetings(cmpRoleId);
		String studentGreetingSubject = "[PLACECOM][SHORTLIST] " + studentGreeting.getFirmName() + "_"
				+ studentGreeting.getRoleName();
		int size=applyId.size();
		int loop=size/80;
		int count=0;
		int i;
		for(int j=0;j<loop;j++)
		{
			for ( i = j*80; i<(j*80)+80; i++) {
				emailId=emailId.concat(rmService.selectUsersInformationByRM(applyId.get(i), studentGreetingSubject, shortlist.getGreetings(),
						sessionBean.getUserID(), "shortlist@iima.ac.in")+",");
				
			}
			
			//System.out.println("parth : "+emailId);
			String emailIdlist=emailId.toString();
			emailIdlist= emailIdlist.substring(0, emailIdlist.length()-1).trim();
			//System.out.println("parth111"+emailIdlist);
			sendMail.sendMailShortList(shortlist.getGreetings(),emailIdlist,"",studentGreetingSubject);
			emailId=new String();
			count=i;
		}
		//System.out.println("csdddd"+count);
		for ( i = count; i<size; i++) {
			emailId=emailId.concat(rmService.selectUsersInformationByRM(applyId.get(i), studentGreetingSubject, shortlist.getGreetings(),
					sessionBean.getUserID(), "shortlist@iima.ac.in")+",");
		
		}
		//System.out.println("parth : "+emailId);
		String emailIdlist=emailId.toString();
		emailIdlist= emailIdlist.substring(0, emailIdlist.length()-1).trim();
		//System.out.println("parth111"+emailIdlist);
		sendMail.sendMailShortList(shortlist.getGreetings(),emailIdlist,"",studentGreetingSubject);
		emailId=new String();
		return "redirect:getshortlistReceivePage";
	}
	// @RequestMapping(value = "/saveHRShortlisted")
	// public String saveHRShortlisted(HttpServletRequest req,ShortList
	// shortlist) throws SQLException {
	// System.out.println("RMController.saveHRShortlisted()--"+shortlist.getShortListIdsSelected());
	// String shtIds=req.getParameter("shortListIdsSelected");
	// List<Integer> shorlistedIds=new ArrayList<Integer>();
	// if(shtIds==""){
	// System.out.println("shortlisted idss"+shtIds);
	// }
	// else{
	// String[] num=shtIds.split(",");
	//
	// for(String str:num){
	// String[] appId=str.split("##");
	// System.out.println("shortlisted idss"+str+" ::"+appId[0]+":: "+appId[1]);
	// shorlistedIds.add(Integer.parseInt(appId[0]));
	// }
	// }
	//
	// String notShortListIds=req.getParameter("notShortlisted");
	// List<Integer> notselecteddIds=new ArrayList<Integer>();
	// if(notShortListIds==""){
	// System.out.println(" notShortListIds not checeked--");
	// }
	// else{
	// String[] notshort=notShortListIds.split(",");
	// for(String ns:notshort){
	// System.out.println("shortlisted idss"+ns);
	// String[] appId=ns.split("##");
	// notselecteddIds.add(Integer.parseInt(appId[0]));
	// }
	// }
	//
	// shortlist.setNotSelected(notselecteddIds);
	//
	// shortlist.setShortListIdslist(shorlistedIds);
	//
	// rmService.shortlistRelease(shortlist);
	//
	//
	// System.out.println("shorlistedIds
	// list-"+shorlistedIds+"contrats--"+shortlist.getGreetings());
	//
	// return "redirect:getshortlistReceiveCompanyHRPage";
	// }

	   @RequestMapping(value = "/saveHRShortlisted")
	public String saveHRShortlisted(HttpServletRequest req, ShortList shortlist) throws SQLException {
		System.out.println("RMController.saveHRShortlisted()--" + shortlist.getShortListIdsSelected());
		String shtIds = req.getParameter("shortListIdsSelected");
		SessionBean sessionBean = (SessionBean) req.getSession().getAttribute("sessionBean");
		int userid = sessionBean.getUserID();
		List<Integer> shorlistedIds = new ArrayList<Integer>();
		if (shtIds == "") {
			System.out.println("shortlisted idss" + shtIds);
		} else {
			String[] num = shtIds.split(",");

			for (String str : num) {
				String[] appId = str.split("##");
				System.out.println("shortlisted idss" + str + " ::" + appId[0] + ":: " + appId[1]);
				shorlistedIds.add(Integer.parseInt(appId[0]));
			}
		}
		System.out.println("apply id:::" + shorlistedIds.toString());
		String notShortListIds = req.getParameter("notShortlisted");
		List<Integer> notselecteddIds = new ArrayList<Integer>();
		if (notShortListIds == "") {
			System.out.println(" notShortListIds not checeked--");
		} else {
			String[] notshort = notShortListIds.split(",");
			for (String ns : notshort) {
				System.out.println("shortlisted idss" + ns);
				String[] appId = ns.split("##");
				notselecteddIds.add(Integer.parseInt(appId[0]));
			}
		}

		shortlist.setNotSelected(notselecteddIds);

		shortlist.setShortListIdslist(shorlistedIds);

		rmService.shortlistRelease(shortlist,userid);

		for (int i = 0; i < shorlistedIds.size(); i++) {
			System.out.println("greetings:::" + shortlist.getGreetings());
			// System.out.println("sessionBean::"+sessionBean.getUserID());
			rmService.selectUsersInformation(shorlistedIds.get(i), shortlist.getGreetings(), 0, "shortlist@iima.ac.in");
		}

		System.out.println("shorlistedIds list-" + shorlistedIds + "contrats--" + shortlist.getGreetings());

		return "redirect:getshortlistReceiveCompanyHRPage?cmpRoleId=" + req.getParameter("cmpRoleId");
	}  
	   
	 
   @RequestMapping(value = "/saveHRShortlistedWithoutSession")
   public String saveHRShortlistedWithoutSession(HttpServletRequest req, ShortList shortlist, CloseStatus closeStatus)
			throws SQLException {
		System.out.println("RMController.saveHRShortlistedWithoutSession()--" + shortlist.getShortListIdsSelected());
		String shtIds = req.getParameter("shortListIdsSelected")==null?"":req.getParameter("shortListIdsSelected");
		List<Integer> shorlistedIds = new ArrayList<Integer>();
		List<Integer> applyIdListIdslist = new ArrayList<Integer>();
		if (shtIds.equals("")) {
			System.out.println("shortlisted ids : " + shtIds);
		} else {
			String[] num = shtIds.split(",");
			for (String str : num) {
				String[] appId = str.split("##");
				System.out.println("shortlisted ids in save hr :: " + str + " ::" + appId[0] + ":: " + appId[1]);
				shorlistedIds.add(Integer.parseInt(appId[1]));
				applyIdListIdslist.add(Integer.parseInt(appId[0]));
			}
		}
		
		System.out.println("shorlisted id:::" + shorlistedIds.toString());
		/*
		 * String notShortListIds=req.getParameter("notShortlisted");
		 * List<Integer> notselecteddIds=new ArrayList<Integer>();
		 * if(notShortListIds==""){ System.out.println(
		 * " notShortListIds not checeked--"); } else{ String[]
		 * notshort=notShortListIds.split(","); for(String ns:notshort){
		 * System.out.println("shortlisted idss"+ns); String[]
		 * appId=ns.split("##");
		 * notselecteddIds.add(Integer.parseInt(appId[0])); } }
		 * 
		 * shortlist.setNotSelected(notselecteddIds);
		 */

		shortlist.setShortListIdslist(shorlistedIds);
		shortlist.setApplyIdListIdslist(applyIdListIdslist);
		rmService.saveHRShortlisted(shortlist);
		int compRoleId = Integer.parseInt(req.getParameter("cmpRoleId"));

		// System.out.println("cmp Role id ::::: " +compRoleId );
		// List<ShortList> allrm=rmService.getAllRM(compRoleId);
		CloseStatus allhrData = rmService.getAllHrList(compRoleId);
		// System.out.println("allhrData:::::::::::"+allhrData);

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
		String subject = "[IIMA] PI Shortlist " + process + "_" + year+"_"+header.getRoleName();
		System.out.println("subject :::::::"+subject);
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
			/*String mailFormat = this.generateMailFormatHRMail(allhrData.getHrEmailId(), allhrData.getHrName(), path,
					allhrData.getMailDes(), compRoleId, allhrData.getEmailId(), mailshorlists);

			// sendMail.sendMail(body, s.getEmailID(), "Shortlisted generated By HR ");
			sendMail.sendMailHRShortList(mailFormat, allhrData.getHrEmailId(), allhrData.getEmailId(), subject);*/
		}

		/*
		 * for(int i=0;i<shorlistedIds.size();i++){
		 * rmService.selectUsersInformation(shorlistedIds.get(i),shortlist.
		 * getGreetings(),0); }
		 */

		// System.out.println("shorlistedIds
		// list-"+shorlistedIds+"contrats--"+shortlist.getGreetings());

		return "redirect:" + returnPath;
	}  
   
	public String generateMailFormatRMShortlistMail(String hremailId, String hrName, String link, String msgDesc,
			int companyRoleId, String rmemailId, String shortList,int x) {
		StringBuffer mailHtml = new StringBuffer();
		// String prefernceValue="";
		String shortListStudents = "";
		// shortListStudents=closeStatusService.getSelectedUserValues(shortList,companyRoleId,false)+"<br/>";
		shortListStudents = closeStatusService1.getSelectedUserRMList(shortList, companyRoleId) + "<br/>";

		if (msgDesc != null) {
			msgDesc = msgDesc + "<br/><br/>";
		} else {
			msgDesc = "";
		}
		if(x==0)
		{
		mailHtml.append("<html><body>Dear " + hrName + ",<br/><br/> " + "List of PI Shortlisted Students<br/><br/>"
				+ shortListStudents + "<br/><br/>")
				.append("<br/><br/><i>This is an auto generated mail, do not respond to this ID. If you have any queries please contact your RM "
						+ rmemailId + " </i><br/><br/>Thanks & Regards<br>IIMA Placement Committee</body></html>");
		}
		else
		{
			mailHtml.append("<html><body>Dear " + hrName + ",<br/><br/> " + "List of Shortlisted Students for GD<br/><br/>"
					+ shortListStudents + "<br/><br/>")
					.append("<br/><br/><i>This is an auto generated mail, do not respond to this ID. If you have any queries please contact your RM "
							+ rmemailId + " </i><br/><br/>Thanks & Regards<br>IIMA Placement Committee</body></html>");
		}

		/*
		 * mailHtml.append("<html><body>Dear "+emailId+",<br/><br/> "
		 * +msgDesc+prefernceValue+"Please <a href="+link+
		 * ">Click here</a> for managing applications.") .append(
		 * "<br/><br/><i>This is an auto generated mail, do not respond to this ID. If you have any queries please contact your RM "
		 * +rmemailId+
		 * " </i><br/><br/>Thanks & Regards<br>IIMA Placement Committee</body></html>"
		 * );
		 */
		System.out.println("RMShortlist mailHtml:::" + mailHtml.toString());
		return mailHtml.toString();
	}

	public String generateMailFormatRMHotlistMail(String hremailId, String hrName, String link, String msgDesc,
			int companyRoleId, String rmemailId, String hotList) {
		StringBuffer mailHtml = new StringBuffer();
		// String prefernceValue="";
		String shortListStudents = "";
		//shortListStudents = closeStatusService.getSelectedUserRMList(hotList, companyRoleId) + "<br/>";
		shortListStudents = closeStatusService.getSelectedUserHotList(hotList, companyRoleId) + "<br/>";

		if (msgDesc != null) {
			msgDesc = msgDesc + "<br/><br/>";
		} else {
			msgDesc = "";
		}
		mailHtml.append("<html><body>Dear " + hrName + ",<br/><br/> " + "List Of Hotlisted Students<br/><br/>"
				+ shortListStudents + "<br/><br/>")
				.append("<br/><br/><i>This is an auto generated mail, do not respond to this ID. If you have any queries please contact your RM "
						+ rmemailId + " </i><br/><br/>Thanks & Regards<br>IIMA Placement Committee</body></html>");

		System.out.println("mailHtml:::" + mailHtml.toString());
		return mailHtml.toString();
	}

	
	public String generateMailFormatHRMail(String hremailId, String hrName, String link, String msgDesc,
			int companyRoleId, String rmemailId, String shortList) {
		StringBuffer mailHtml = new StringBuffer();
		// String prefernceValue="";
		String shortListStudents = "";
		// shortListStudents=closeStatusService.getSelectedUserValues(shortList,companyRoleId,false)+"<br/>";
		shortListStudents = closeStatusService.getSelectedUserHRList(shortList, companyRoleId) + "<br/>";

		if (msgDesc != null) {
			msgDesc = msgDesc + "<br/><br/>";
		} else {
			msgDesc = "";
		}
		mailHtml.append("<html><body>Dear " + hrName + ",<br/><br/> " + "List Of Shortlisted Student for PI<br/><br/>"
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

	/*
	 * @RequestMapping(value = "/getshortlistReceiveCompanyHRPage") public
	 * ModelAndView getShortlistReceiveCompanyHRPage(HttpServletRequest
	 * req,CloseStatus closeStatus,Model model) {
	 * System.out.println("RMController.getShortlistReceiveCompanyHRPage()--");
	 * int cmpRoleId=0; SessionBean sessionBean = (SessionBean)
	 * req.getSession().getAttribute("sessionBean"); //int userid =
	 * sessionBean.getUserID(); //System.out.println("+::"+userid);
	 * if(sessionBean==null){
	 * cmpRoleId=Integer.parseInt(req.getParameter("cmpRoleId")); }else{
	 * cmpRoleId=req.getParameter("cmpRoleId")==null?0:Integer.parseInt(req.
	 * getParameter("cmpRoleId")); //cmpRoleId = 46; if(cmpRoleId!=0){
	 * sessionBean.setCmpRoleId(cmpRoleId); }else{ cmpRoleId =
	 * sessionBean.getCmpRoleId(); } } System.out.println(
	 * "cmpRoleId in savesend mail: "+cmpRoleId);
	 * if(closeStatus.getRankFlag()==null){ closeStatus.setRankFlag(false); }
	 * CloseStatus header = closeStatusService.getCloseStatusHeader(cmpRoleId);
	 * if(cmpRoleId!=0){ header.setRoleId(cmpRoleId); } //System.out.println(
	 * "company name-"+header.getFirmName() + "role name::" +
	 * header.getRoleName() ); //req.setAttribute("closeStatus", header);
	 * closeStatus.setRoleId(cmpRoleId);
	 * model.addAttribute("closeStatus",header);
	 * 
	 * //int rmRoleId=sessionBean.getRmRoleID(); //System.out.println(
	 * "rm role id-"+rmRoleId); List<ShortList>
	 * shortlist=rmService.getShortlistedDataByRole(cmpRoleId);//role
	 * System.out.println("list shotlist-"+shortlist);
	 * 
	 * int cmpRoleId=req.getParameter("cmpRoleId")==null?0:Integer.parseInt(req.
	 * getParameter("cmpRoleId")); cmpRoleId = 46; CloseStatus header =
	 * closeStatusService.getCloseStatusHeader(cmpRoleId);
	 * req.setAttribute("closeStatus", header);
	 * 
	 * return new
	 * ModelAndView("shortlistReceiveCompanyHRPage","shortlist",shortlist); }
	 */

   @RequestMapping(value = "/getshortlistReceiveCompanyHRPage")
	public ModelAndView getShortlistReceiveCompanyHRPage(HttpServletRequest req, CloseStatus closeStatus, Model model) {
		ModelAndView modelView = new ModelAndView("shortlistReceiveCompanyHRPage");
		System.out.println("RMController.getShortlistReceiveCompanyHRPage()--");
		int cmpRoleId = 0;
		SessionBean sessionBean = (SessionBean) req.getSession().getAttribute("sessionBean");
		// int userid = sessionBean.getUserID();
		// System.out.println("+::"+userid);
		if (sessionBean == null) {
			cmpRoleId = Integer.parseInt(req.getParameter("cmpRoleId"));
		} else {
			cmpRoleId = req.getParameter("cmpRoleId") == null ? 0 : Integer.parseInt(req.getParameter("cmpRoleId"));
			// cmpRoleId = 46;
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
		// System.out.println("company name-"+header.getFirmName() + "role
		// name::" + header.getRoleName() );
		// req.setAttribute("closeStatus", header);
		closeStatus.setRoleId(cmpRoleId);
		model.addAttribute("closeStatus", header);

		// int rmRoleId=sessionBean.getRmRoleID();
		// System.out.println("rm role id-"+rmRoleId);
		List<ShortList> shortlist = rmService.getShortlistedDataByRole(cmpRoleId);// role
		System.out.println("list shotlist-" + shortlist);

		/*
		 * int
		 * cmpRoleId=req.getParameter("cmpRoleId")==null?0:Integer.parseInt(req.
		 * getParameter("cmpRoleId")); cmpRoleId = 46; CloseStatus header =
		 * closeStatusService.getCloseStatusHeader(cmpRoleId);
		 * req.setAttribute("closeStatus", header);
		 */

		// return new
		// ModelAndView("shortlistReceiveCompanyHRPage","shortlist",shortlist);
		modelView.addObject("shortlist", shortlist);
		modelView.addObject("compRoleId", cmpRoleId);
		return modelView;
	}  
	 
	 
	 @RequestMapping(value = "/getshortlistReleaseCompanyHRPage")
	public ModelAndView getshortlistReleaseCompanyHRPage(HttpServletRequest req, CloseStatus closeStatus, Model model) {
		System.out.println("RMController.getShortlistReceiveCompanyHRPage()--");
		int cmpRoleId = 0;
		SessionBean sessionBean = (SessionBean) req.getSession().getAttribute("sessionBean");
		if (sessionBean == null) {
			cmpRoleId = Integer.parseInt(req.getParameter("cmpRoleId"));
		} else {
			/* int userid = sessionBean.getUserID(); */
			/* System.out.println("+::"+userid); */
			cmpRoleId = req.getParameter("cmpRoleId") == null ? 0 : Integer.parseInt(req.getParameter("cmpRoleId"));
			// cmpRoleId = 46;
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
		// System.out.println("company name-"+header.getFirmName() + "role
		// name::" + header.getRoleName() );
		// req.setAttribute("closeStatus", header);
		closeStatus.setRoleId(cmpRoleId);
		model.addAttribute("closeStatus", header);

		System.out.println("cmpRoleId:::" + cmpRoleId);
		// int role=46;
		List<ShortList> shortlist = rmService.getshortlistReleaseCompany(cmpRoleId);// role

		System.out.println("list shotlist-" + shortlist);

		/*
		 * int
		 * cmpRoleId=req.getParameter("cmpRoleId")==null?0:Integer.parseInt(req.
		 * getParameter("cmpRoleId")); cmpRoleId = 46; CloseStatus header =
		 * closeStatusService.getCloseStatusHeader(cmpRoleId);
		 * req.setAttribute("closeStatus", header);
		 */

		return new ModelAndView("shortlistReleaseCompanyHR", "shortlist", shortlist);
	}  
	
	 
	 @RequestMapping(value = "/downloadRMCv", produces = "application/zip")
	public void zipFiles(HttpServletResponse response, HttpServletRequest request) throws IOException {
		System.out.println("RMController.zipFiles()--cvleter-" + request.getParameter("coverLetter"));
		String downloadCvId = request.getParameter("downloadCvId");
		String[] idArr = downloadCvId.split(",");
		List<CloseStatus> downloadCvlist = new ArrayList<CloseStatus>();
		CloseStatus closeStatus = null;
		int coverletter = request.getParameter("coverLetter") == null ? 0
				: Integer.parseInt(request.getParameter("coverLetter"));
		for (String id : idArr) {
			String[] appId = id.split("##");
			System.out.println("shortlisted ids ::::" + id + " ::" + appId[0] + ":: " + appId[1]);
			closeStatus = new CloseStatus();
			closeStatus.setApplyId(Integer.parseInt(appId[0]));
			System.out.println("id set value :: " + closeStatus.getApplyId());
			downloadCvlist.add(closeStatus);
		}

		List<String> downloadFileNameList = null;

		if (coverletter == 0) {
			downloadFileNameList = rmService.getRMDownloadCVList(downloadCvlist);
		} else {
			downloadFileNameList = rmService.getRMDownloadCoverList(downloadCvlist);
		}

		List<File> files = new ArrayList<File>();
		List<String> filename = new ArrayList<String>();
		for (String filePath : downloadFileNameList) {
			String arr[] = filePath.split("###");
			files.add(new File(arr[1]));
			filename.add(arr[0]);
		}

		try {
			if (files != null && files.size() > 0) {
				byte[] zip = zipFiles(files, filename);
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
	 
	public byte[] zipFiles(List<File> files, List<String> fileNameList) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ZipOutputStream zos = new ZipOutputStream(baos);
		byte bytes[] = new byte[2048];
		File file = null;
		String fileName = "";
		for (int i = 0; i < files.size(); i++) {
			file = files.get(i);
			fileName = fileNameList.get(i);
			if (file.exists()) {
				FileInputStream fis = new FileInputStream(file.getCanonicalFile());
				BufferedInputStream bis = new BufferedInputStream(fis);
				zos.putNextEntry(new ZipEntry(fileName + "." + FilenameUtils.getExtension(file.getName())));
				int bytesRead;
				while ((bytesRead = bis.read(bytes)) != -1) {
					zos.write(bytes, 0, bytesRead);
				}
				zos.closeEntry();
				bis.close();
				fis.close();
			}else{
				System.out.println("File Not Found :: "+files.get(i)+" with name :: "+fileName);
			}
		}
		zos.flush();
		baos.flush();
		zos.close();
		baos.close();

		return baos.toByteArray();
	}
    
	
	@RequestMapping(value = "/pdfmergeWOSession", produces = "application/pdf")
	public void pdfFiles(HttpServletResponse response, HttpServletRequest request) throws Exception {
		String downloadCvId = request.getParameter("downloadCvId");
		String[] idArr = downloadCvId.split(",");
		
		List<CloseStatus> downloadCvlist = new ArrayList<CloseStatus>();
		CloseStatus closeStatus = null;

		for (String id : idArr) {
			String[] appId = id.split("##");
			//System.out.println("shortlisted ids ::::" + id + " ::" + appId[0] + ":: " + appId[1]);
			closeStatus = new CloseStatus();
			closeStatus.setApplyId(Integer.parseInt(appId[0]));
			System.out.println("CV id :: " + closeStatus.getApplyId());
			downloadCvlist.add(closeStatus);
		}

		String pdfBinderLocation = request.getServletContext().getInitParameter("tempFileLocation");
		List<String> downloadFileNameList = rmService.getRMDownloadCVList(downloadCvlist);
		List<File> files = new ArrayList<File>();
		List<String> filename = new ArrayList<String>();
		for (String filePath : downloadFileNameList) {
			String arr[] = filePath.split("###");
			// System.out.println("PDF Binder :: CV Name : "+arr[0]+" file path
			// :: "+arr[1]);
			files.add(new File(arr[1]));
			filename.add(arr[0]);
		}
		String path = getPDFBinder(files, filename, pdfBinderLocation);
		try {
			if (files != null && files.size() > 0) {

				File f = new File(path);
				String mimeType = FileUtil.getMimeType(f.getCanonicalPath());
				if (mimeType == null) {
					mimeType = "application/octet-stream";
				}

				// System.out.println("mimeType: "+mimeType);
				response.setContentType(mimeType);
				response.setHeader("Content-Disposition", "attachment;filename=\"" + f.getName() + "\"");
				response.setContentLength((int) f.length());
				InputStream is = new FileInputStream(f);
				ServletOutputStream outStream = response.getOutputStream();
				org.apache.commons.io.IOUtils.copy(is, outStream);
				is.close();
				outStream.flush();

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public String getPDFBinder(List<File> files, List<String> fileNameList, String pdfBinderLocation) throws Exception {
		// System.out.println("CloseStatusController.pdfBinder()");
		PDDocument doc;
		PDFMergerUtility PDFmerger = new PDFMergerUtility();
		String path = pdfBinderLocation + "PDFBinder.pdf";
		File checkFile = new File(path);
		if (checkFile.exists()) {
			checkFile.delete();
		}
		PDFmerger.setDestinationFileName(path);
		File file1 = null;
		// String fileName = null;

		for (int i = 0; i < files.size(); i++) {
			file1 = files.get(i);
			// fileName = fileNameList.get(i);
			doc = PDDocument.load(file1);
			PDFmerger.addSource(file1);
			doc.close();
		}
		PDFmerger.mergeDocuments();
		return path;
	}

	@RequestMapping(value = "/saveReleaseShortlisted")
	 public String saveReleaseShortlisted(HttpServletRequest request,ShortList shortlist,CloseStatus closeStatus) throws SQLException {
	    System.out.println("RMController.saveReleaseShortlisted()--");
	    SessionBean sessionBean = (SessionBean) request.getSession().getAttribute("sessionBean");
	    int userid=sessionBean.getUserID();
	    List<Integer> sids=new ArrayList<Integer>();
	    List<Integer> applyIdListIdslist=new ArrayList<Integer>();
	    List<Integer> applyId=new ArrayList<Integer>();
	    String mailshorlists = "";
	    String[] shtIds=request.getParameterValues("idlist");
	    for(String str:shtIds){
	    	String ids[] = str.split("##");
	    	System.out.println("saveReleaseShortlisted-  "+str);
	    	sids.add(Integer.parseInt(ids[0]));
	    /*	applyId.add(str.getApplyId());*/

	    	applyIdListIdslist.add(Integer.parseInt(ids[1]));
	    	
	    }
	  
	    for (Integer s : applyIdListIdslist)	{
			mailshorlists += s + ",";
		}
		 if(mailshorlists!=null && !mailshorlists.equals("")){
			 mailshorlists = mailshorlists.substring(0,mailshorlists.length()-1);
		 }
	    System.out.println("shtasasadasdaIds--"+shtIds.toString()+" applyIdListIdslist :: "+applyIdListIdslist.toString()+" mailshorlists : "+mailshorlists);	    
	  
		shortlist.setShortListIdslist(sids);
		shortlist.setApplyIdListIdslist(applyIdListIdslist);
	
		rmService.shortlistRMRelease(shortlist,userid);
		
		int cmpRoleId=request.getParameter("cmpRoleId")==null?0:Integer.parseInt(request.getParameter("cmpRoleId"));
		
		if(cmpRoleId!=0){
			//System.out.println("hiif");
			sessionBean.setCmpRoleId(cmpRoleId);
		}else{
			//out.println("hielse");
			cmpRoleId = sessionBean.getCmpRoleId();
		}
		//model.addAttribute("cmpRoleId",cmpRoleId);	
		CloseStatus allhrData = rmService.getAllHrList(cmpRoleId);
		// System.out.println("allhrData:::::::::::"+allhrData);
		CloseStatus header = closeStatusService.getCloseStatusHeader(cmpRoleId);
		String process = header.getProcess();
		int year = header.getYear();
		String subject = "[IIMA] " + process + "_" + year+"_"+header.getRoleName();
		
		Hashids hashids = new Hashids("comp role id",10);
		String hash = hashids.encode(cmpRoleId);
		System.out.println("hashcode values::" + hash);
		//String returnPath = "getCompanyHRPage?cmpRoleId=" + "abdcr" + hash;
		String returnPath = "getCompanyHRPage?cmpRoleId=" + hash;
		String link = request.getRequestURL().toString();
		String[] str = link.split("/IIMForum/");
		String path = str[0].concat("/IIMForum/" + returnPath);
		closeStatus.setGeneratedLink(path);
		if (allhrData != null) {
			
			String mailFormat = this.generateMailFormatRMShortlistMail(allhrData.getHrEmailId(), allhrData.getHrName(),path,
					allhrData.getMailDes(), cmpRoleId, allhrData.getEmailId(), mailshorlists,0);

			
			sendMail.sendMailHRShortList(mailFormat, allhrData.getHrEmailId(), allhrData.getEmailId(), subject);
		}
		
		CloseStatus studentGreeting = rmService.getStudentGreetings(cmpRoleId);
		String studentGreetingSubject = "[PLACECOM][SHORTLIST] " +studentGreeting.getFirmName() + "_" + studentGreeting.getRoleName();
		
		int size=applyIdListIdslist.size();
		int loop=size/80;
		int count=0;
		int i;
		for(int j=0;j<loop;j++)	{
			for ( i = j*80; i<(j*80)+80; i++) {
				emailId=emailId.concat(rmService.selectUsersInformationByRM(applyIdListIdslist.get(i), studentGreetingSubject, shortlist.getGreetings(),
						sessionBean.getUserID(), "shortlist@iima.ac.in")+",");
			}
			String emailIdlist=emailId.toString();
			emailIdlist= emailIdlist.substring(0, emailIdlist.length()-1).trim();
			sendMail.sendMailShortList(shortlist.getGreetings(),emailIdlist,"",studentGreetingSubject);
			emailId=new String();
			count=i;
		}
		for ( i = count; i<size; i++) {
			emailId=emailId.concat(rmService.selectUsersInformationByRM(applyIdListIdslist.get(i), studentGreetingSubject, shortlist.getGreetings(),
					sessionBean.getUserID(), "shortlist@iima.ac.in")+",");
		}
		String emailIdlist=emailId.toString();
		emailIdlist= emailIdlist.substring(0, emailIdlist.length()-1).trim();
		sendMail.sendMailShortList(shortlist.getGreetings(),emailIdlist,"",studentGreetingSubject);
		emailId=new String();
		return "redirect:getAllCloseStatusDetails";
		
		
	 }

	@RequestMapping(value = "/saveReleaseShortlistedBySuperRm1")
	public String saveReleaseShortlistedBySuperRm1(HttpServletRequest request, ShortList shortlist)
			throws SQLException {
		System.out.println("RMController.saveReleaseShortlistedBySuperRm1()--");
		SessionBean sessionBean = (SessionBean) request.getSession().getAttribute("sessionBean");
		int userid=sessionBean.getUserID();
		List<Integer> sids = new ArrayList<Integer>();
		List<Integer> applyIdListIdslist = new ArrayList<Integer>();
		String[] shtIds = request.getParameterValues("idlist");
		for (String str : shtIds) {
			String ids[] = str.split("##");
			System.out.println("saveReleaseShortlisted-  " + str);
			sids.add(Integer.parseInt(ids[0]));
			applyIdListIdslist.add(Integer.parseInt(ids[1]));

		}

		shortlist.setShortListIdslist(sids);
		shortlist.setApplyIdListIdslist(applyIdListIdslist);

		rmService.shortlistRMRelease(shortlist,userid);

		/*
		 * for(int i=0;i<sids.size();i++){
		 * rmService.selectUsersInformationByRM(sids.get(i),shortlist.
		 * getGreetings(),sessionBean.getUserID(),"shortlist@iima.ac.in"); }
		 */

		return "redirect:getAllCloseStatusDetailsForSuperRM1";
	}

	@RequestMapping(value = "/saveReleaseShortlistedBySuperRm2")
	public String saveReleaseShortlistedBySuperRm2(HttpServletRequest request, ShortList shortlist)
			throws SQLException {
		System.out.println("RMController.saveReleaseShortlisted()--");
		SessionBean sessionBean = (SessionBean) request.getSession().getAttribute("sessionBean");
		int userid=sessionBean.getUserID();
		List<Integer> sids = new ArrayList<Integer>();
		List<Integer> applyIdListIdslist = new ArrayList<Integer>();
		String[] shtIds = request.getParameterValues("idlist");
		for (String str : shtIds) {
			String ids[] = str.split("##");
			System.out.println("saveReleaseShortlisted-  " + str);
			sids.add(Integer.parseInt(ids[0]));
			applyIdListIdslist.add(Integer.parseInt(ids[1]));

		}

		shortlist.setShortListIdslist(sids);
		shortlist.setApplyIdListIdslist(applyIdListIdslist);
		rmService.shortlistRMRelease(shortlist,userid);
		/*
		 * for(int i=0;i<sids.size();i++){
		 * rmService.selectUsersInformationByRM(sids.get(i),shortlist.
		 * getGreetings(),sessionBean.getUserID(),"shortlist@iima.ac.in"); }
		 */

		return "redirect:getAllCloseStatusDetailsForSuperRM2";
	}

	@RequestMapping(value = "/saveSendMail")
	public String saveSendMail(HttpServletRequest request, CloseStatus closeStatus) throws SQLException {
		// System.out.println("RMController.saveSendMail()--");
		SessionBean sessionBean = (SessionBean) request.getSession().getAttribute("sessionBean");
		int userid = sessionBean.getUserID();
		int cmpRoleId = request.getParameter("cmpRoleId") == null ? 0
				: Integer.parseInt(request.getParameter("cmpRoleId"));
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
		header.setRoleId(cmpRoleId);
		request.setAttribute("closeStatus", header);
		closeStatus.setRoleId(cmpRoleId);
		String link = request.getRequestURL().toString();
		System.out.println("request uri in send mail--- " + link);
		String[] str = link.split("/IIMForum/");
		for (String s : str) {
			System.out.println("linked--" + s);
		}
		/*
		 * UUID uid = UUID.fromString("38400000-8cf0-11bd-b23e-10b96e4ef00d");
		 * String randomId=uid+"crid"+cmpRoleId;
		 */
		Hashids hashids = new Hashids("comp role id",10);
		String hash = hashids.encode(cmpRoleId);
		System.out.println("hashcode values::" + hash);
		//String path = str[0].concat("/IIMForum/getCompanyHRPage?cmpRoleId=" + "abdcr" + hash);
		String path = str[0].concat("/IIMForum/getCompanyHRPage?cmpRoleId=" + hash);
		closeStatus.setGeneratedLink(path);
		String mailFormat = this.generateMailFormat(closeStatus.getEmailId(), path, null, closeStatus.getRankFlag(),
				closeStatus.getMailDes(), cmpRoleId);
		sendMail.sendMail(mailFormat, closeStatus.getEmailId(), "Hotlist generated");
		closeStatusService.saveGenerateShortLinkMail(closeStatus, userid);
		return "redirect:getAllCloseStatusDetails";

	}

	@RequestMapping(value = "/saveSendMailBySuperRM1")
	public String saveSendMailBySuperRM1(HttpServletRequest request, CloseStatus closeStatus) throws SQLException {
		// System.out.println("RMController.saveSendMail()--");
		SessionBean sessionBean = (SessionBean) request.getSession().getAttribute("sessionBean");
		int userid = sessionBean.getUserID();
		int cmpRoleId = request.getParameter("cmpRoleId") == null ? 0
				: Integer.parseInt(request.getParameter("cmpRoleId"));
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
		header.setRoleId(cmpRoleId);
		request.setAttribute("closeStatus", header);
		closeStatus.setRoleId(cmpRoleId);
		String link = request.getRequestURL().toString();
		System.out.println("request uri in send mail--- " + link);
		String[] str = link.split("/IIMForum/");
		for (String s : str) {
			System.out.println("linked--" + s);
		}
		/*
		 * UUID uid = UUID.fromString("38400000-8cf0-11bd-b23e-10b96e4ef00d");
		 * String randomId=uid+"crid"+cmpRoleId;
		 */
		Hashids hashids = new Hashids("comp role id",10);
		String hash = hashids.encode(cmpRoleId);
		System.out.println("hashcode values::" + hash);
		//String path = str[0].concat("/IIMForum/getCompanyHRPage?cmpRoleId=" + "abdcr" + hash);
		String path = str[0].concat("/IIMForum/getCompanyHRPage?cmpRoleId=" + hash);
		closeStatus.setGeneratedLink(path);
		String mailFormat = this.generateMailFormat(closeStatus.getEmailId(), path, null, closeStatus.getRankFlag(),
				closeStatus.getMailDes(), cmpRoleId);
		sendMail.sendMail(mailFormat, closeStatus.getEmailId(), "Hotlist generated");
		closeStatusService.saveGenerateShortLinkMail(closeStatus, userid);
		return "redirect:getAllCloseStatusDetailsForSuperRM1";

	}

	@RequestMapping(value = "/saveSendMailBySuperRM2")
	public String saveSendMailBySuperRM2(HttpServletRequest request, CloseStatus closeStatus) throws SQLException {
		// System.out.println("RMController.saveSendMail()--");
		SessionBean sessionBean = (SessionBean) request.getSession().getAttribute("sessionBean");
		int userid = sessionBean.getUserID();
		int cmpRoleId = request.getParameter("cmpRoleId") == null ? 0
				: Integer.parseInt(request.getParameter("cmpRoleId"));
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
		header.setRoleId(cmpRoleId);
		request.setAttribute("closeStatus", header);
		closeStatus.setRoleId(cmpRoleId);
		String link = request.getRequestURL().toString();
		System.out.println("request uri in send mail--- " + link);
		String[] str = link.split("/IIMForum/");
		for (String s : str) {
			System.out.println("linked--" + s);
		}
		/*
		 * UUID uid = UUID.fromString("38400000-8cf0-11bd-b23e-10b96e4ef00d");
		 * String randomId=uid+"crid"+cmpRoleId;
		 */
		Hashids hashids = new Hashids("comp role id",10);
		String hash = hashids.encode(cmpRoleId);
		System.out.println("hashcode values::" + hash);
		//String path = str[0].concat("/IIMForum/getCompanyHRPage?cmpRoleId=" + "abdcr" + hash);
		String path = str[0].concat("/IIMForum/getCompanyHRPage?cmpRoleId="+ hash);
		closeStatus.setGeneratedLink(path);
		String mailFormat = this.generateMailFormat(closeStatus.getEmailId(), path, null, closeStatus.getRankFlag(),
				closeStatus.getMailDes(), cmpRoleId);
		sendMail.sendMail(mailFormat, closeStatus.getEmailId(), "Hotlist generated");
		closeStatusService.saveGenerateShortLinkMail(closeStatus, userid);
		return "redirect:getAllCloseStatusDetailsForSuperRM2";

	}
	
	
	@RequestMapping(value = "/saveHotListReleaseRM" , method = RequestMethod.POST)
	 public String saveReleaseHotlisted(HttpServletRequest request,HotList hotlist) throws SQLException {
		    System.out.println("RMController.saveReleaseHotlisted()--");
		    SessionBean sessionBean = (SessionBean) request.getSession().getAttribute("sessionBean");
		    int cmpRoleId=request.getParameter("cmpRoleId")==null?0:Integer.parseInt(request.getParameter("cmpRoleId"));
			
			if(cmpRoleId!=0){
				sessionBean.setCmpRoleId(cmpRoleId);
			}else{
				cmpRoleId = sessionBean.getCmpRoleId();
			}
		  //  List<Integer> applyIdListIdslist=new ArrayList<Integer>();
		    String mailshorlists = "";
		 
		    String shtIds = request.getParameter("hotListIdsSelected");
			List<Integer> hotistedIds = new ArrayList<Integer>();
			if (shtIds == "") {
				System.out.println("not checeked--");
			} else {
				String[] num = shtIds.split(",");
				for (String str : num) {
					System.out.println("shortlisted idss" + str);
					hotistedIds.add(Integer.parseInt(str));
				}
			}

			String notHotListIds = request.getParameter("notHotlisted");
			List<Integer> notselecteddIds = new ArrayList<Integer>();
			if (notHotListIds == "") {
				System.out.println(" notShortListIds not checeked--");
			} else {
				String[] notshort = notHotListIds.split(",");
				for (String ns : notshort) {
					System.out.println("shortlisted idss" + ns);
					notselecteddIds.add(Integer.parseInt(ns));
				}
			}
		    
		    /*String[] applyhotListId = request.getParameter("applyhotListId").split(",");
		    for (Integer s : applyIdListIdslist){
				mailshorlists += s + ",";
			}
		    if(mailshorlists!=null && !mailshorlists.equals("")){
				 mailshorlists = mailshorlists.substring(0,mailshorlists.length()-1);
		    }*/
		    
		    hotlist.setHotListIdslist(hotistedIds);
		    hotlist.setNotSelected(notselecteddIds);
		 //   hotlist.setApplyIdListIdslist(applyIdListIdslist);
		
			rmService.hotlistRMRelease(hotlist);
			
			return "redirect:getAllCloseStatusDetails";
	 }
	
	
	@RequestMapping(value = "/getCompanyHRPage")
	public ModelAndView getCompanyHRPage(HttpServletRequest req, CloseStatus closeStatus, Model model) {
		String firmRoleId = req.getParameter("cmpRoleId");
		int cmpRoleId=0;
		if(firmRoleId!=null){
			boolean flag = StringUtils.isNumeric(firmRoleId);
			if(!flag){
				Hashids hashids1 = new Hashids("comp role id",10);
				long[] numbers = hashids1.decode(firmRoleId);
				System.out.println("number::"+numbers[0]);
				cmpRoleId=(int)(numbers[0]);
			}else{
				cmpRoleId=Integer.parseInt(req.getParameter("cmpRoleId"));
			}
		}
		System.out.println("RMController.getCompanyHRPage() cmpRoleId " + cmpRoleId);
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
		ModelAndView modelView = new ModelAndView("companyHR");
		// List<ShortList>
		// shortlist=rmService.getShortlistedDataByRole(cmpRoleId);//previously
		// used
		List<ShortList> shortlist = rmService.getShortlistedDataByRoleForHR(cmpRoleId);
		modelView.addObject("shortlist", shortlist);
		modelView.addObject("compRoleId", cmpRoleId);
		System.out.println("List, shortlist : "+shortlist.size());
		if (closeStatus.getRankFlag() == null) {
			closeStatus.setRankFlag(false);
		}
		
		if(header.getNoOfSelectionRounds()==2){
			List<ShortList> shortlistone = rmService1.getShortlistedDataByRole(cmpRoleId);
			req.setAttribute("shortlist1", shortlistone);
			System.out.println("List, shortlistone-" + shortlistone.size());
		}
		/*List<ShortList> shortlistRelease = rmService.getshortlistReleaseCompany(cmpRoleId);
		modelView.addObject("shortlistRelease", shortlistRelease);*/
		
			List<HotList> hotlistCompHr = hotListservice.getShortlistedDataByRole(cmpRoleId);
			modelView.addObject("hotlistCompanyHR", hotlistCompHr);
			System.out.println("List,hotlist : "+hotlistCompHr.size());
		
		
		
		
		/*List<HotList> hotlistRelease = hotListservice.getHotReleaseCompany(cmpRoleId);
		modelView.addObject("hotlistRelease", hotlistRelease);*/
		return modelView;
	}

	public String generateMailFormat(String emailId, String link, String shortList, Boolean rankFlag, String msgDesc,
			int companyRoleId) {
		StringBuffer mailHtml = new StringBuffer();
		String prefernceValue = "";
		if (rankFlag != null) {
			if (rankFlag) {
				prefernceValue = closeStatusService.getSelectedUserValues(shortList, companyRoleId, rankFlag) + "<br/>";
			} else {
				prefernceValue = "";
			}
		}
		if (msgDesc != null) {
			msgDesc = msgDesc + "<br/><br/>";
		} else {
			msgDesc = "";
		}
		mailHtml.append("<html><body>Dear " + emailId + ",<br/><br/> " + msgDesc + prefernceValue
				+ "Please click on link to check hotlist.")
				.append("<a href=" + link
						+ ">Click here</a><br/><br/>This is an auto generated mail, don't respond to this. If you have any issues please reach out to pgpplacecom@iima.ac.in.<br/><br/>Thanks & Regards<br>IIMA</body></html>");
		System.out.println("mailHtml:::" + mailHtml.toString());
		return mailHtml.toString();
	}
	
	 @RequestMapping(value = "/downloadHRCv", produces = "application/zip")
		public void zipHRFiles(HttpServletResponse response, HttpServletRequest request) throws IOException {
			System.out.println("RMController.zipFiles()--cvleter-" + request.getParameter("coverLetter"));
			String downloadCvId = request.getParameter("downloadCvId");
			String[] idArr = downloadCvId.split(",");
			List<CloseStatus> downloadCvlist = new ArrayList<CloseStatus>();
			CloseStatus closeStatus = null;
			int coverletter = request.getParameter("coverLetter") == null ? 0
					: Integer.parseInt(request.getParameter("coverLetter"));
			for (String id : idArr) {
				String[] appId = id.split("##");
				System.out.println("shortlisted ids ::::" + id + " ::" + appId[0] + ":: " + appId[1]);
				closeStatus = new CloseStatus();
				closeStatus.setApplyId(Integer.parseInt(appId[0]));
				System.out.println("id set value :: " + closeStatus.getApplyId());
				downloadCvlist.add(closeStatus);
			}

			List<String> downloadFileNameList = null;

			if (coverletter == 0) {
				downloadFileNameList = rmService.getRMDownloadCVList(downloadCvlist);
			} else {
				downloadFileNameList = rmService.getRMDownloadCoverList(downloadCvlist);
			}

			List<File> files = new ArrayList<File>();
			List<String> filename = new ArrayList<String>();
			for (String filePath : downloadFileNameList) {
				String arr[] = filePath.split("###");
				files.add(new File(arr[1]));
				filename.add(arr[0]);
			}

			try {
				if (files != null && files.size() > 0) {
					byte[] zip = zipFiles(files, filename);
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
	 
	 @RequestMapping(value = "/shortlistHR2Remove")
	   public String shortlistHR2Remove(HttpServletRequest req, ShortList shortlist, CloseStatus closeStatus)
				throws SQLException {
			System.out.println("RMController.saveHRShortlistedWithoutSession()--" + shortlist.getShortListIdsSelected());
			String shtIds = req.getParameter("shortListIdsSelected")==null?"":req.getParameter("shortListIdsSelected");
			List<Integer> shorlistedIds = new ArrayList<Integer>();
			List<Integer> applyIdListIdslist = new ArrayList<Integer>();
			if (shtIds.equals("")) {
				System.out.println("shortlisted ids : " + shtIds);
			} else {
				String[] num = shtIds.split(",");
				for (String str : num) {
					String[] appId = str.split("##");
					System.out.println("shortlisted ids in save hr :: " + str + " ::" + appId[0] + ":: " + appId[1]);
					shorlistedIds.add(Integer.parseInt(appId[1]));
					applyIdListIdslist.add(Integer.parseInt(appId[0]));
				}
			}
			
			System.out.println("shorlisted id:::" + shorlistedIds.toString());
			/*
			 * String notShortListIds=req.getParameter("notShortlisted");
			 * List<Integer> notselecteddIds=new ArrayList<Integer>();
			 * if(notShortListIds==""){ System.out.println(
			 * " notShortListIds not checeked--"); } else{ String[]
			 * notshort=notShortListIds.split(","); for(String ns:notshort){
			 * System.out.println("shortlisted idss"+ns); String[]
			 * appId=ns.split("##");
			 * notselecteddIds.add(Integer.parseInt(appId[0])); } }
			 * 
			 * shortlist.setNotSelected(notselecteddIds);
			 */

			shortlist.setShortListIdslist(shorlistedIds);
			shortlist.setApplyIdListIdslist(applyIdListIdslist);
			rmService.removeHRShortlisted(shortlist);
			int compRoleId = Integer.parseInt(req.getParameter("cmpRoleId"));

			// System.out.println("cmp Role id ::::: " +compRoleId );
			// List<ShortList> allrm=rmService.getAllRM(compRoleId);
			CloseStatus allhrData = rmService.getAllHrList(compRoleId);
			// System.out.println("allhrData:::::::::::"+allhrData);

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
			String subject = "[IIMA] PI Shortlist " + process + "_" + year+"_"+header.getRoleName();
			System.out.println("subject :::::::"+subject);
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
				/*String mailFormat = this.generateMailFormatHRMail(allhrData.getHrEmailId(), allhrData.getHrName(), path,
						allhrData.getMailDes(), compRoleId, allhrData.getEmailId(), mailshorlists);

				// sendMail.sendMail(body, s.getEmailID(), "Shortlisted generated By HR ");
				sendMail.sendMailHRShortList(mailFormat, allhrData.getHrEmailId(), allhrData.getEmailId(), subject);*/
			}

			/*
			 * for(int i=0;i<shorlistedIds.size();i++){
			 * rmService.selectUsersInformation(shorlistedIds.get(i),shortlist.
			 * getGreetings(),0); }
			 */

			// System.out.println("shorlistedIds
			// list-"+shorlistedIds+"contrats--"+shortlist.getGreetings());

			return "redirect:" + returnPath;
		}  
}
