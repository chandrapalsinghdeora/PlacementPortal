package com.precise.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.precise.mail.SendMail;
import com.precise.model.Announcement;
import com.precise.model.Fine;
import com.precise.model.SessionBean;
import com.precise.service.AnnouncementService;

@Controller
public class AnnouncementController {

	@Autowired
	AnnouncementService announcementService ;
	
	@Autowired
	SendMail sendMail;

	@RequestMapping(value = "/getAnnouncement", method = RequestMethod.GET)
	public String getAnnouncementValues(Announcement announcement, HttpServletRequest request,ModelMap modelMap) {
		int roleId,userid;
		try
		{
			SessionBean sbean=(SessionBean)request.getSession().getAttribute("sessionBean");
		userid=sbean.getUserID();
		 roleId=sbean.getRoleID();
			if(roleId!=4)
			{
				return "redirect:login";
			}
		}catch(Exception e)
		{
			return "redirect:login";
		}
		modelMap.addAttribute("returnValue", "failure");
		return "Announcement";
	}

	@RequestMapping(value ="/getAllAnnouncementDetails",method = RequestMethod.GET)
	public ModelAndView getAllAnnouncementDetails(ModelAndView model,HttpServletRequest req){
		int roleId,userid;
		try
		{
			SessionBean sbean=(SessionBean)req.getSession().getAttribute("sessionBean");
		userid=sbean.getUserID();
		 roleId=sbean.getRoleID();
			if(roleId!=4)
			{
				return new ModelAndView("LoginPage");
			}
		}catch(Exception e)
		{
			return new ModelAndView("LoginPage");
		}
		/*SessionBean sessionBean = (SessionBean) req.getSession().getAttribute("sessionBean");
		int userid = sessionBean.getUserID();
		int roleId = sessionBean.getRmRoleID();*/
		List<Announcement> AnnouncementDetaillist = announcementService.getAllAnnouncementDetails();
		System.out.println("AnnouncementDetaillist :"+AnnouncementDetaillist.size());
		req.setAttribute("announcementDetails", AnnouncementDetaillist);
		
		model.setViewName("AnnouncementMaster");
		return model;
		
	}
	
	@RequestMapping(value = "/addAnnouncement", method = RequestMethod.POST)
	public String addAnnouncement(Announcement announcement, HttpServletRequest request, ModelMap modelMap) {

		int roleId,userid;
		SessionBean sessionBean;
		try
		{
			 sessionBean=(SessionBean)request.getSession().getAttribute("sessionBean");
		userid=sessionBean.getUserID();
		 roleId=sessionBean.getRoleID();
			if(roleId!=4)
			{
				return "redirect:login";
			}
		}catch(Exception e)
		{
			return "redirect:login";
		}
		String userEmail = sessionBean.getEmail();
		System.out.println(announcement.getTitle());
		String sub = "[PLACECOM] "+announcement.getTitle();
		announcement.setTitle(sub);
		System.out.println(announcement.getDateTime());
		System.out.println(announcement.getDescription());
		System.out.println(announcement.getGroupToPost());
		System.out.println(announcement.getOtherPostText());
		List<String> pgpgroup = announcement.getPgpgroup();
		
		String groupToPost  = "";
		if(announcement.getOtherPostText()!=null){
			groupToPost=announcement.getOtherPostText();
		}
		if(pgpgroup.size()>0){
			if(announcementService.getGroupToPost(announcement.getPgpgroup())!=null){
				if(!groupToPost.equals(""))
					groupToPost+=", "; 
				
				groupToPost  = groupToPost+ announcementService.getGroupToPost(announcement.getPgpgroup());
			}
		}
		
		
		
		String uploadedPhotot = this.doUploadPhoto(request, announcement, userid);
		if (announcement.getUrgentFlag() == null) {
			announcement.setUrgentFlag(false);
		}
		announcement.setFilePath(uploadedPhotot);
		System.out.println("group to post id::"+groupToPost);
		announcement.setGroupToPost(groupToPost);
		System.out.println("groupId::"+announcement.getGroupToPost());
		announcement.setCreatedBy(userid);
		announcementService.addAnnouncement(announcement);
		
		if (announcement.getUrgentFlag() == true) {
			System.out.println("urgent flag");
			String mailDesc=this.generateMailFormat(announcement.getDescription(), userEmail);
			if (uploadedPhotot.equals("")) {
				sendMail.sendAnnouncement("", "", announcement.getTitle(), mailDesc,
						announcement.getGroupToPost());
				announcementService.insetIntoInbox(announcement.getGroupToPost(), announcement.getTitle(),
						mailDesc, userid,uploadedPhotot,"pgpplacecom@iima.ac.in");
			} else {
				System.out.println("else condition");
				sendMail.sendAnnouncement(announcement.getFileupload().getOriginalFilename(), uploadedPhotot,
						announcement.getTitle(), mailDesc, announcement.getGroupToPost());
				announcementService.insetIntoInbox(announcement.getGroupToPost(), announcement.getTitle(),
						mailDesc, userid,uploadedPhotot,"pgpplacecom@iima.ac.in");
			}
		}
		modelMap.addAttribute("returnValue", "success");
		return "Announcement";
	}


	private String doUploadPhoto(HttpServletRequest request, Announcement fileUploadForm, int userId) {
		String uploadRootPath = request.getServletContext().getInitParameter("announcementFile");
		System.out.println("uploadRootPath=" + uploadRootPath);

		File uploadRootDir = new File(uploadRootPath + userId);
		if (!uploadRootDir.exists()) {
			uploadRootDir.mkdirs();
		}
		MultipartFile fileData = fileUploadForm.getFileupload();
		//
		String uploadedFiles = "";
		String name = fileData.getOriginalFilename();
		System.out.println("Client File Name = " + name);

		if (name != null && name.length() > 0) {
			try {
				File serverFile = new File(uploadRootDir.getAbsolutePath() + File.separator + name);
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
				stream.write(fileData.getBytes());
				stream.close();
				System.out.println(
						"Write file: " + serverFile.getCanonicalPath() + " :: " + serverFile.getAbsolutePath());
				uploadedFiles = serverFile.getCanonicalPath();
				System.out.println("Write file: " + serverFile);
			} catch (Exception e) {
				System.out.println("Error Write file: " + name);
				e.printStackTrace();
			}
		} else {
			uploadedFiles = "";
		}
		// }
		return uploadedFiles;
	}
	
	public String generateMailFormat(String msgDescription,String userEmail){
		String mailFormat="<html><body>"+msgDescription+"<br/><br/>This is an auto generated mail, don't respond to this. If you have any issues please reach out to "+userEmail+".<br/><br/>Thanks & Regards<br>IIMA Placement Committee</body></html>";
		return mailFormat;
	}
}
