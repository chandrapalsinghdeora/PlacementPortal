 package com.precise.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hashids.Hashids;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.precise.mail.SendMail;
import com.precise.model.CloseStatus;
import com.precise.model.Message;
import com.precise.model.SessionBean;
import com.precise.model.ShortList;
import com.precise.service.CloseStatusService;
import com.precise.service.CloseStatusService1;
import com.precise.service.HotListService1;
import com.precise.service.RMService;
import com.precise.service.RMService1;

@Controller
public class RMController1 {
	@Autowired
	RMService1 rmService1;
	@Autowired
	RMService rmService;
	@Autowired
	CloseStatusService1 closeStatusService1;
	@Autowired
	CloseStatusService closeStatusService;
	@Autowired
	HotListService1 hotListservice1;
	@Autowired
	SendMail sendMail;
	String emailId=new String();
	String emailId1=new String();
	@RequestMapping(value = "/saveMessage1")
	public String saveMessage(HttpServletRequest req, Message msg) {
		System.out.println("RMController.saveMessage()");
		if (msg.getMessageId() == 0) {
			rmService1.saveMessage(msg);
		} else if (msg.getMessageId() != 0) {
			System.out.println("elseeeeesss -" + msg.getMessageId() + msg.getMessage());
			System.out.println("RMController.saveMessage() else--");
			rmService1.updateMessage(msg);
		}

		return "redirect:getRMMessagePage";
	}

	@RequestMapping(value = "/getshortlistReceivePage1")
	public String getShortlistPage(HttpServletRequest req, CloseStatus closeStatus, Model model) {
		System.out.println("RMController.getShortlistPage()--");

		SessionBean sessionBean = (SessionBean) req.getSession().getAttribute("sessionBean");
		int userid = sessionBean.getUserID();
		System.out.println("+::" + userid);
		int cmpRoleId = req.getParameter("cmpRoleId") == null ? 0 : Integer.parseInt(req.getParameter("cmpRoleId"));
		// cmpRoleId = 46;
		if (cmpRoleId != 0) {
			sessionBean.setCmpRoleId(cmpRoleId);
		} else {
			cmpRoleId = sessionBean.getCmpRoleId();
		}
		System.out.println("cmpRoleId in savesend mail: " + cmpRoleId);
		if (closeStatus.getRankFlag() == null) {
			closeStatus.setRankFlag(false);
		}
		CloseStatus header = closeStatusService1.getCloseStatusHeader(cmpRoleId);
		if (cmpRoleId != 0) {
			header.setRoleId(cmpRoleId);
		}
		// System.out.println("company name-"+header.getFirmName() + "role
		// name::" + header.getRoleName() );
		// req.setAttribute("closeStatus", header);
		closeStatus.setRoleId(cmpRoleId);
		model.addAttribute("closeStatus", header);
		Integer appid = cmpRoleId;
		Hashids hashids = new Hashids("comp role id",10);
	//	String hash = "abdcr" + hashids.encode(appid);
		String hash = hashids.encode(appid);
		model.addAttribute("cmpRoleId", hash);
		List<ShortList> shortlist = rmService1.getShortlistedDataByRole(cmpRoleId);// role
		System.out.println("list shotlist-" + shortlist);

		return "redirect:getAllCloseStatusDetails1";
	}

	@RequestMapping(value = "/saveShortlisted1")
	public String saveShortlisted(HttpServletRequest req, ShortList shortlist) throws SQLException {
		System.out.println("RMController.saveShortlisted()--" + shortlist.getShortListIdsSelected() + "not selected-"
				+ shortlist.getNotSelected());
		String shtIds = req.getParameter("shortListIdsSelected");
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
		rmService1.shortlistRelease(shortlist);
		System.out.println("shorlistedIds list-" + shorlistedIds + "contrats--" + shortlist.getGreetings()
				+ "shorlistedIds.size():::" + shorlistedIds.size());

		return "redirect:getshortlistReceivePage1";
	}

	@RequestMapping(value = "/releaseHRShortlisted1")
	public String releaseHRShortlisted(HttpServletRequest req, ShortList shortlist) throws SQLException {
		System.out.println("RMController.releaseHRShortlisted()--" + shortlist.getShortListIdsSelected()
				+ "not selected-" + shortlist.getNotSelected());
		// String shtIds=req.getParameter("shortListIdsSelected");
		SessionBean sessionBean = (SessionBean) req.getSession().getAttribute("sessionBean");
		List<Integer> shorlistedIds = new ArrayList<Integer>();
	//	List<Integer> applyIdListIdslist = new ArrayList<Integer>();
		List<Integer> applyId = new ArrayList<Integer>();
		String mailshorlists = "";
		int cmpRoleId = req.getParameter("cmpRoleId") == null ? 0 : Integer.parseInt(req.getParameter("cmpRoleId"));
		if (cmpRoleId != 0) {
			sessionBean.setCmpRoleId(cmpRoleId);
		} else {
			cmpRoleId = sessionBean.getCmpRoleId();
		}
		System.out.println("releaseHRShortlisted() comp role id :: " +shortlist.getCmpRoleId() );
		List<ShortList> shortlistedByHr = rmService1.getHRShortlistedDataByRole(shortlist.getCmpRoleId());

		for (ShortList s : shortlistedByHr) {
			shorlistedIds.add(s.getShortListId());
			applyId.add(s.getApplyId());
		}
		for (Integer s : applyId)	{
			mailshorlists += s + ",";
		}
		 if(mailshorlists!=null && !mailshorlists.equals("")){
			 mailshorlists = mailshorlists.substring(0,mailshorlists.length()-1);
		 }
		// shortlist.setNotSelected(notselecteddIds);
		shortlist.setShortListIdslist(shorlistedIds);
		shortlist.setApplyIdListIdslist(applyId);
		// rmService1.shortlistRelease(shortlist);

		rmService1.releaseHRShortlisted(shortlist);
		System.out.println("shorlistedIds list-" + shorlistedIds + "contrats--" + shortlist.getGreetings()
				+ "shorlistedIds.size():::" + shorlistedIds.size());

		CloseStatus allhrData = rmService.getAllHrList(cmpRoleId);
		// System.out.println("allhrData:::::::::::"+allhrData);
		CloseStatus header = closeStatusService1.getCloseStatusHeader(cmpRoleId);
		String process = header.getProcess();
		int year = header.getYear();
		String subject = "[IIMA] " + process + "_" + year+"_"+header.getRoleName();
		
		Hashids hashids = new Hashids("comp role id",10);
		String hash = hashids.encode(cmpRoleId);
		System.out.println("hashcode values::" + hash);
		//String returnPath = "getCompanyHRPage?cmpRoleId=" + "abdcr" + hash;
		String returnPath = "getCompanyHRPage?cmpRoleId=" + hash;
		String link = req.getRequestURL().toString();
		String[] str = link.split("/IIMForum/");
		String path = str[0].concat("/IIMForum/" + returnPath);
		//closeStatus.setGeneratedLink(path);
		if (allhrData != null) {
			
			String mailFormat = this.generateMailFormatRMShortlistOneMail(allhrData.getHrEmailId(), allhrData.getHrName(),path,
					allhrData.getMailDes(), cmpRoleId, allhrData.getEmailId(), mailshorlists);

			
			sendMail.sendMailHRShortList(mailFormat, allhrData.getHrEmailId(), allhrData.getEmailId(), subject);
		}
		
		CloseStatus studentGreeting = rmService1.getStudentGreetings(cmpRoleId);
		String studentGreetingSubject = "[PLACECOM][SHORTLIST] " + studentGreeting.getFirmName() + "_"
				+ studentGreeting.getRoleName();
          //StringBuffer emailId=new StringBuffer();
		int size=applyId.size();
		int loop=size/80;
		int count=0;
		int i;
		
		for(int j=0;j<loop;j++)
		{
			for ( i = j*80; i<(j*80)+80; i++) {
				emailId=emailId.concat(rmService1.selectUsersInformationByRM(applyId.get(i), studentGreetingSubject, shortlist.getGreetings(),
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
			emailId=emailId.concat(rmService1.selectUsersInformationByRM(applyId.get(i), studentGreetingSubject, shortlist.getGreetings(),
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

	/*@RequestMapping(value = "/saveHRShortlisted1")
	public String saveHRShortlisted(HttpServletRequest req, ShortList shortlist) throws SQLException {
		System.out.println("RMController.saveHRShortlisted()--" + shortlist.getShortListIdsSelected());
		String shtIds = req.getParameter("shortListIdsSelected");
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
		rmService1.shortlistRelease(shortlist);

		for (int i = 0; i < shorlistedIds.size(); i++) {
			System.out.println("greetings:::" + shortlist.getGreetings());
			rmService1.selectUsersInformation(shorlistedIds.get(i), shortlist.getGreetings(), 0,
					"shortlist@iima.ac.in");
		}
		System.out.println("shorlistedIds list-" + shorlistedIds + "contrats--" + shortlist.getGreetings());

		return "redirect:getshortlistReceiveCompanyHRPage?cmpRoleId=" + req.getParameter("cmpRoleId");
	}*/

	@RequestMapping(value = "/saveHRShortlistedOneWithoutSession")
	public String saveHRShortlistedWithoutSession(HttpServletRequest req, ShortList shortlist, CloseStatus closeStatus)
			throws SQLException {
		System.out.println("RMController.saveHRShortlistedWithoutSession()--" + shortlist.getShortListIdsSelected());
		String shtIds = req.getParameter("shortListOneIds") == null ? "" : req.getParameter("shortListOneIds");
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

		shortlist.setShortListIdslist(shorlistedIds);
		shortlist.setApplyIdListIdslist(applyIdListIdslist);
		rmService1.saveHRShortlisted(shortlist);
		int compRoleId = Integer.parseInt(req.getParameter("cmpRoleId"));

		// System.out.println("cmp Role id ::::: " +compRoleId );
		// List<ShortList> allrm=rmService1.getAllRM(compRoleId);
		CloseStatus allhrData = rmService1.getAllHrList(compRoleId);
		// System.out.println("allhrData:::::::::::"+allhrData);

		CloseStatus header = closeStatusService1.getCloseStatusHeader(compRoleId);
		// String compName=header.getFirmName();
		// String roleName=header.getRoleName();

		Hashids hashids = new Hashids("comp role id",10);
		String hash = hashids.encode(compRoleId);
		System.out.println("hashcode values::" + hash);
	//	String returnPath = "getCompanyHRPage?cmpRoleId=" + "abdcr" + hash;
		String returnPath = "getCompanyHRPage?cmpRoleId=" + hash;

		String process = header.getProcess();
		int year = header.getYear();
		String subject = "[IIMA] GD Shortlist " + process + "_" + year+"_"+header.getRoleName();
		System.out.println("Subject1 ::::::"+subject);

		if (allhrData != null) {

			System.out.println("Hrmail :: " + allhrData.getHrEmailId());
			System.out.println("RMEmailId :: " + allhrData.getEmailId());
			String link = req.getRequestURL().toString();
			String[] str = link.split("/IIMForum/");

			String path = str[0].concat("/IIMForum/" + returnPath);
			closeStatus.setGeneratedLink(path);
		/*	String mailFormat = this.generateMailFormatHRMail(allhrData.getHrEmailId(), allhrData.getHrName(), path,
					allhrData.getMailDes(), compRoleId, allhrData.getEmailId(), shorlistedIds.toString());

			sendMail.sendMailHRShortList(mailFormat, allhrData.getHrEmailId(), allhrData.getEmailId(), subject);*/
		}

		return "redirect:" + returnPath;
	}

	 public String generateMailFormatHRMail(String hremailId,String hrName,String link,String msgDesc,int companyRoleId,String rmemailId,String shortList){
			StringBuffer mailHtml=new StringBuffer();
			//String prefernceValue="";
			String shortListStudents="";
		//	shortListStudents=closeStatusService1.getSelectedUserValues(shortList,companyRoleId,false)+"<br/>";
			shortListStudents =closeStatusService1.getSelectedUserHROneList(shortList, companyRoleId) + "<br/>";
			
			if(msgDesc!=null){
				msgDesc=msgDesc+"<br/><br/>";
			}else{
				msgDesc="";
			}
			mailHtml.append("<html><body>Dear "+hrName+",<br/><br/> "+"List of Round 1 Shortlisted Student<br/><br/>"+shortListStudents+"<br/><br/>")
		    .append("<br/><br/><i>This is an auto generated mail, do not respond to this ID. If you have any queries please contact your RM "+rmemailId+" </i><br/><br/>Thanks & Regards<br>IIMA Placement Committee</body></html>");
			System.out.println("mailHtml:::"+mailHtml.toString());
			return mailHtml.toString();
		}
	/*public String generateMailFormat(String hremailId, String hrName, String link, String msgDesc, int companyRoleId,
			String rmemailId, String shortList) {
		StringBuffer mailHtml = new StringBuffer();
		String prefernceValue = "";
		String shortListStudents = "";
		shortListStudents = closeStatusService1.getSelectedUserValues(shortList, companyRoleId) + "<br/>";
		
		 * if(rankFlag!=null){ if(rankFlag){
		 * prefernceValue=closeStatusService1.getSelectedUserValues(shortList,
		 * companyRoleId)+"<br/>"; }else{ prefernceValue=""; }}
		 
		if (msgDesc != null) {
			msgDesc = msgDesc + "<br/><br/>";
		} else {
			msgDesc = "";
		}
		mailHtml.append("<html><body>Dear " + hrName + ",<br/><br/> " + "<br/><br/>" + shortListStudents + "<br/><br/>"
				+ "Please <a href=" + link + ">Click here</a> for managing applications.")
				.append("<br/><br/><i>This is an auto generated mail, do not respond to this ID. If you have any queries please contact your RM "
						+ rmemailId + " </i><br/><br/>Thanks & Regards<br>IIMA Placement Committee</body></html>");

		System.out.println("mailHtml:::" + mailHtml.toString());
		return mailHtml.toString();
	}*/

	/*@RequestMapping(value = "/getshortlistReceiveCompanyHRPage1")
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
		CloseStatus header = closeStatusService1.getCloseStatusHeader(cmpRoleId);
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
		List<ShortList> shortlist = rmService1.getShortlistedDataByRole(cmpRoleId);// role
		System.out.println("list shotlist-" + shortlist);

		modelView.addObject("shortlist", shortlist);
		modelView.addObject("compRoleId", cmpRoleId);
		return modelView;
	}*/

	/*@RequestMapping(value = "/getshortlistReleaseCompanyHRPage1")
	public ModelAndView getshortlistReleaseCompanyHRPage(HttpServletRequest req, CloseStatus closeStatus, Model model) {
		System.out.println("RMController.getShortlistReceiveCompanyHRPage()--");
		int cmpRoleId = 0;
		SessionBean sessionBean = (SessionBean) req.getSession().getAttribute("sessionBean");
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
		CloseStatus header = closeStatusService1.getCloseStatusHeader(cmpRoleId);
		if (cmpRoleId != 0) {
			header.setRoleId(cmpRoleId);
		}
		// System.out.println("company name-"+header.getFirmName() + "role
		// name::" + header.getRoleName() );
		closeStatus.setRoleId(cmpRoleId);
		model.addAttribute("closeStatus", header);
		System.out.println("cmpRoleId:::" + cmpRoleId);
		List<ShortList> shortlist = rmService1.getshortlistReleaseCompany(cmpRoleId);// role
		System.out.println("list shotlist-" + shortlist);
		return new ModelAndView("shortlistReleaseCompanyHR", "shortlist", shortlist);
	}*/

	/*@RequestMapping(value = "/downloadRMCv1", produces = "application/zip")
	public void zipFiles(HttpServletResponse response, HttpServletRequest request) throws IOException {
		System.out.println("RMController.zipFiles()--cvleter-" + request.getParameter("coverLetter"));
		String downloadCvId = request.getParameter("downloadCvId");
		String[] idArr = downloadCvId.split(",");

		List<CloseStatus> downloadCvlist = new ArrayList<CloseStatus>();
		CloseStatus closeStatus = null;

		for (String id : idArr) {
			String[] appId = id.split("##");
			System.out.println("shortlisted ids ::::" + id + " ::" + appId[0] + ":: " + appId[1]);
			closeStatus = new CloseStatus();
			closeStatus.setApplyId(Integer.parseInt(appId[0]));
			System.out.println("id set value :: " + closeStatus.getApplyId());
			downloadCvlist.add(closeStatus);
		}

		List<String> downloadFileNameList = null;

		if (request.getParameter("coverLetter") == null) {
			downloadFileNameList = rmService1.getRMDownloadCVList(downloadCvlist);
		} else {
			downloadFileNameList = rmService1.getRMDownloadCoverList(downloadCvlist);
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
	}*/

	/*public byte[] zipFiles(List<File> files, List<String> fileNameList) throws IOException {
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
			}
		}
		zos.flush();
		baos.flush();
		zos.close();
		baos.close();

		return baos.toByteArray();
	}
*/
	/*@RequestMapping(value = "/pdfmergeWOSession1", produces = "application/pdf")
	public void pdfFiles(HttpServletResponse response, HttpServletRequest request) throws Exception {
		String downloadCvId = request.getParameter("downloadCvId");
		String[] idArr = downloadCvId.split(",");

		List<CloseStatus> downloadCvlist = new ArrayList<CloseStatus>();
		CloseStatus closeStatus = null;

		for (String id : idArr) {
			String[] appId = id.split("##");
			// System.out.println("shortlisted ids ::::" + id + " ::" + appId[0]
			// + ":: " + appId[1]);
			closeStatus = new CloseStatus();
			closeStatus.setApplyId(Integer.parseInt(appId[0]));
			System.out.println("CV id :: " + closeStatus.getApplyId());
			downloadCvlist.add(closeStatus);
		}

		String pdfBinderLocation = request.getServletContext().getInitParameter("tempFileLocation");
		List<String> downloadFileNameList = rmService1.getRMDownloadCVList(downloadCvlist);
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

	}*/

	/*public String getPDFBinder(List<File> files, List<String> fileNameList, String pdfBinderLocation) throws Exception {
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
*/
	@RequestMapping(value = "/saveReleaseShortlisted1")
	public String saveReleaseShortlisted(HttpServletRequest request, ShortList shortlist,CloseStatus closeStatus) throws SQLException {
		System.out.println("RMController.saveReleaseShortlisted()--");
		SessionBean sessionBean = (SessionBean) request.getSession().getAttribute("sessionBean");
		int userid=sessionBean.getUserID();
		List<Integer> sids = new ArrayList<Integer>();
		List<Integer> applyIdListIdslist = new ArrayList<Integer>();
		/*List<Integer> applyId = new ArrayList<Integer>();*/
		String mailshorlists = "";
		String[] shtIds = request.getParameterValues("idlist");
		for (String str : shtIds) {
			String ids[] = str.split("##");
			System.out.println("saveReleaseShortlisted-  " + str);
			sids.add(Integer.parseInt(ids[0]));
			/* applyId.add(str.getApplyId()); */
			applyIdListIdslist.add(Integer.parseInt(ids[1]));

		}
		for (Integer s : applyIdListIdslist)	{
			mailshorlists += s + ",";
		}
		 if(mailshorlists!=null && !mailshorlists.equals("")){
			 mailshorlists = mailshorlists.substring(0,mailshorlists.length()-1);
		 }
		//System.out.println("shtasasadasdaIds--" + shtIds);

		shortlist.setShortListIdslist(sids);
		shortlist.setApplyIdListIdslist(applyIdListIdslist);
		shortlist.setCreatedBy(sessionBean.getUserID());
		rmService1.shortlistRMRelease(shortlist,userid);

		int cmpRoleId = request.getParameter("cmpRoleId") == null ? 0
				: Integer.parseInt(request.getParameter("cmpRoleId"));
		if (cmpRoleId != 0) {
			sessionBean.setCmpRoleId(cmpRoleId);
		} else {
			cmpRoleId = sessionBean.getCmpRoleId();
		}
		
		CloseStatus allhrData = rmService.getAllHrList(cmpRoleId);
		// System.out.println("allhrData:::::::::::"+allhrData);
		CloseStatus header = closeStatusService1.getCloseStatusHeader(cmpRoleId);
		String process = header.getProcess();
		int year = header.getYear();
		String subject = "[IIMA] GD Shortlist " + process + "_" + year+"_"+header.getRoleName();
		
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
					allhrData.getMailDes(), cmpRoleId, allhrData.getEmailId(), mailshorlists);

			
			sendMail.sendMailHRShortList(mailFormat, allhrData.getHrEmailId(), allhrData.getEmailId(), subject);
		}
		
		// model.addAttribute("cmpRoleId",cmpRoleId);
		CloseStatus studentGreeting = rmService1.getStudentGreetings(cmpRoleId);
		String studentGreetingSubject = "[PLACECOM][SHORTLIST] " + studentGreeting.getFirmName() + "_"
				+ studentGreeting.getRoleName();
		int size=applyIdListIdslist.size();
		int loop=size/80;
		int count=0;
		int i;
		for(int j=0;j<loop;j++)	{
			for ( i = j*80; i<(j*80)+80; i++) {
				emailId=emailId.concat(rmService1.selectUsersInformationByRM(applyIdListIdslist.get(i), studentGreetingSubject, shortlist.getGreetings(),
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
			emailId=emailId.concat(rmService1.selectUsersInformationByRM(applyIdListIdslist.get(i), studentGreetingSubject, shortlist.getGreetings(),
					sessionBean.getUserID(), "shortlist@iima.ac.in")+",");
		}
		//System.out.println("parth : "+emailId);
		String emailIdlist=emailId.toString();
		emailIdlist= emailIdlist.substring(0, emailIdlist.length()-1).trim();
		//System.out.println("parth111"+emailIdlist);
		sendMail.sendMailShortList(shortlist.getGreetings(),emailIdlist,"",studentGreetingSubject);
		emailId=new String();

		return "redirect:getAllCloseStatusDetails";

		/*for (int i = 0; i < sids.size(); i++) {
			emailId1=emailId1.concat(rmService1.selectUsersInformationByRM(applyIdListIdslist.get(i), studentGreetingSubject, shortlist.getGreetings(),
					sessionBean.getUserID(), "shortlist@iima.ac.in")+",");
		}
		for(int i = 0; i < 200; i++){
			emailId1 = emailId1.concat("abcdefgh"+i+"@gmail.com"+",");
		}
		System.out.println("emailId1 : "+emailId1);
		String emailIdlist=emailId1.toString();
		emailIdlist= emailIdlist.substring(0, emailIdlist.length()-1).trim();
		System.out.println("parth"+emailIdlist);
		sendMail.sendMailShortList(shortlist.getGreetings(),emailIdlist,"",studentGreetingSubject);
		// return "redirect:getshortlistReleaseCompanyHRPage";
		return "redirect:getAllCloseStatusDetails";*/
	}

	@RequestMapping(value = "/saveReleaseShortlistedBySuperRmGD")
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
		rmService1.shortlistRMRelease(shortlist,userid);

		return "redirect:getAllCloseStatusDetailsForSuperRM1";
	}

	@RequestMapping(value = "/saveReleaseShortlistedBySuperRmGD2")
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
		rmService1.shortlistRMRelease(shortlist,userid);

		return "redirect:getAllCloseStatusDetailsForSuperRM2";
	}

	@RequestMapping(value = "/saveSendMail1")
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

		CloseStatus header = closeStatusService1.getCloseStatusHeader(cmpRoleId);
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
		closeStatusService1.saveGenerateShortLinkMail(closeStatus, userid);
		return "redirect:getAllCloseStatusDetails";

	}

	@RequestMapping(value = "/saveSendMailBySuperRMGD")
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

		CloseStatus header = closeStatusService1.getCloseStatusHeader(cmpRoleId);
		header.setRoleId(cmpRoleId);
		request.setAttribute("closeStatus", header);
		closeStatus.setRoleId(cmpRoleId);
		String link = request.getRequestURL().toString();
		System.out.println("request uri in send mail--- " + link);
		String[] str = link.split("/IIMForum/");
		for (String s : str) {
			System.out.println("linked--" + s);
		}

		Hashids hashids = new Hashids("comp role id",10);
		String hash = hashids.encode(cmpRoleId);
		System.out.println("hashcode values::" + hash);
		//String path = str[0].concat("/IIMForum/getCompanyHRPage?cmpRoleId=" + "abdcr" + hash);
		String path = str[0].concat("/IIMForum/getCompanyHRPage?cmpRoleId="+ hash);
		closeStatus.setGeneratedLink(path);
		String mailFormat = this.generateMailFormat(closeStatus.getEmailId(), path, null, closeStatus.getRankFlag(),
				closeStatus.getMailDes(), cmpRoleId);
		sendMail.sendMail(mailFormat, closeStatus.getEmailId(), "Hotlist generated");
		closeStatusService1.saveGenerateShortLinkMail(closeStatus, userid);
		return "redirect:getAllCloseStatusDetailsForSuperRM1";

	}

	@RequestMapping(value = "/saveSendMailBySuperRMGD2")
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

		CloseStatus header = closeStatusService1.getCloseStatusHeader(cmpRoleId);
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
		closeStatusService1.saveGenerateShortLinkMail(closeStatus, userid);
		return "redirect:getAllCloseStatusDetailsForSuperRM2";

	}

	public String generateMailFormat(String emailId, String link, String shortList, Boolean rankFlag, String msgDesc,
			int companyRoleId) {
		StringBuffer mailHtml = new StringBuffer();
		String prefernceValue = "";
		if (rankFlag != null) {
			if (rankFlag) {
				prefernceValue = closeStatusService1.getSelectedUserValues(shortList, companyRoleId) + "<br/>";
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

	public String generateMailFormatRMShortlistOneMail(String hremailId,String hrName,String link,String msgDesc,int companyRoleId,String rmemailId,String shortList){
		StringBuffer mailHtml=new StringBuffer();
		//String prefernceValue="";
		String shortListStudents="";
	shortListStudents=closeStatusService.getSelectedUserValues(shortList,companyRoleId,false)+"<br/>";
		//shortListStudents=closeStatusService1.getSelectedUserRMList(shortList,companyRoleId)+"<br/>";
		
		if(msgDesc!=null){
			msgDesc=msgDesc+"<br/><br/>";
		}else{
			msgDesc="";
		}
		mailHtml.append("<html><body>Dear "+hrName+",<br/><br/> "+"List Of Shortlisted Students for GD<br/><br/>"+shortListStudents+"<br/><br/>")
	    .append("<br/><br/><i>This is an auto generated mail, do not respond to this ID. If you have any queries please contact your RM "+rmemailId+" </i><br/><br/>Thanks & Regards<br>IIMA Placement Committee</body></html>");
		
		/*mailHtml.append("<html><body>Dear "+emailId+",<br/><br/> "+msgDesc+prefernceValue+"Please <a href="+link+">Click here</a> for managing applications.")
	    .append("<br/><br/><i>This is an auto generated mail, do not respond to this ID. If you have any queries please contact your RM "+rmemailId+" </i><br/><br/>Thanks & Regards<br>IIMA Placement Committee</body></html>");*/
		System.out.println("mailHtml:::"+mailHtml.toString());
		return mailHtml.toString();
	}
	
	public String generateMailFormatRMShortlistMail(String hremailId,String hrName,String link,String msgDesc,int companyRoleId,String rmemailId,String shortList){
		StringBuffer mailHtml=new StringBuffer();
		//String prefernceValue="";
		String shortListStudents="";
	//shortListStudents=closeStatusService.getSelectedUserValues(shortList,companyRoleId,false)+"<br/>";
		shortListStudents=closeStatusService1.getSelectedUserRMList(shortList,companyRoleId)+"<br/>";
		
		if(msgDesc!=null){
			msgDesc=msgDesc+"<br/><br/>";
		}else{
			msgDesc="";
		}
		mailHtml.append("<html><body>Dear "+hrName+",<br/><br/> "+"List Of Shortlisted Students for GD<br/><br/>"+shortListStudents+"<br/><br/>")
	    .append("<br/><br/><i>This is an auto generated mail, do not respond to this ID. If you have any queries please contact your RM "+rmemailId+" </i><br/><br/>Thanks & Regards<br>IIMA Placement Committee</body></html>");
		
		/*mailHtml.append("<html><body>Dear "+emailId+",<br/><br/> "+msgDesc+prefernceValue+"Please <a href="+link+">Click here</a> for managing applications.")
	    .append("<br/><br/><i>This is an auto generated mail, do not respond to this ID. If you have any queries please contact your RM "+rmemailId+" </i><br/><br/>Thanks & Regards<br>IIMA Placement Committee</body></html>");*/
		System.out.println("RMShortlist1 mailHtml:::"+mailHtml.toString());
		return mailHtml.toString();
	}
	
	@RequestMapping(value = "/shortlistRemove1")
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
		rmService1.shortlistRemove(shortlist,userid);
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

		return "redirect:getshortlistReceivePage1";
	}
	
	@RequestMapping(value = "/shortlistHR1Remove")
	public String shortlistHR1Remove(HttpServletRequest req, ShortList shortlist, CloseStatus closeStatus)
			throws SQLException {
		System.out.println("RMController.saveHRShortlistedWithoutSession()--" + shortlist.getShortListIdsSelected());
		String shtIds = req.getParameter("shortListOneIds") == null ? "" : req.getParameter("shortListOneIds");
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

		shortlist.setShortListIdslist(shorlistedIds);
		shortlist.setApplyIdListIdslist(applyIdListIdslist);
		rmService1.removeHRShortlisted(shortlist);
		int compRoleId = Integer.parseInt(req.getParameter("cmpRoleId"));

		// System.out.println("cmp Role id ::::: " +compRoleId );
		// List<ShortList> allrm=rmService1.getAllRM(compRoleId);
		CloseStatus allhrData = rmService1.getAllHrList(compRoleId);
		// System.out.println("allhrData:::::::::::"+allhrData);

		CloseStatus header = closeStatusService1.getCloseStatusHeader(compRoleId);
		// String compName=header.getFirmName();
		// String roleName=header.getRoleName();

		Hashids hashids = new Hashids("comp role id",10);
		String hash = hashids.encode(compRoleId);
		System.out.println("hashcode values::" + hash);
	//	String returnPath = "getCompanyHRPage?cmpRoleId=" + "abdcr" + hash;
		String returnPath = "getCompanyHRPage?cmpRoleId=" + hash;

		String process = header.getProcess();
		int year = header.getYear();
		String subject = "[IIMA] GD Shortlist " + process + "_" + year+"_"+header.getRoleName();
		System.out.println("Subject1 ::::::"+subject);

		if (allhrData != null) {

			System.out.println("Hrmail :: " + allhrData.getHrEmailId());
			System.out.println("RMEmailId :: " + allhrData.getEmailId());
			String link = req.getRequestURL().toString();
			String[] str = link.split("/IIMForum/");

			String path = str[0].concat("/IIMForum/" + returnPath);
			closeStatus.setGeneratedLink(path);
		/*	String mailFormat = this.generateMailFormatHRMail(allhrData.getHrEmailId(), allhrData.getHrName(), path,
					allhrData.getMailDes(), compRoleId, allhrData.getEmailId(), shorlistedIds.toString());

			sendMail.sendMailHRShortList(mailFormat, allhrData.getHrEmailId(), allhrData.getEmailId(), subject);*/
		}

		return "redirect:" + returnPath;
	}
}

