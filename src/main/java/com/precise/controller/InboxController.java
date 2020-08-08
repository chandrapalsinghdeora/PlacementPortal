package com.precise.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.precise.fileManager.FileUtil;
import com.precise.model.Inbox;
import com.precise.model.Label;
import com.precise.model.SessionBean;
import com.precise.service.InboxService;

@Controller
public class InboxController {
	@Autowired
	InboxService iservice;
	
	private static Logger logger = Logger.getLogger(InboxController.class);

	public InboxController() {
		System.out.println("Inside login controller::");
	}
	
	@RequestMapping(value="/getInboxSavePage")
	public ModelAndView getInboxSavePage(){
		//System.out.println("InboxController.getInboxSavePage()");
		logger.info("inside getInboxSavePage method ::");
		return new ModelAndView("addInboxData");
	}
	
	@RequestMapping(value="/addInboxData")
	public ModelAndView saveInboxDate(Inbox inbox){
		//System.out.println("InboxController.getInboxSavePage()");
		logger.info("inside getInboxSavePage method ::");
		iservice.saveInboxData(inbox);
		return new ModelAndView("addInboxData");
	}

	@RequestMapping(value="/getInboxData")
	public ModelAndView  getInboxData(HttpSession session,HttpServletRequest req){
		
		//System.out.println("InboxController.getInboxSavePage()");
		logger.info("inside getInboxSavePage method ::");	
		int receiverId,roleId;
		try
		{
		  SessionBean sbean=(SessionBean)req.getSession().getAttribute("sessionBean");
		   receiverId=sbean.getUserID();
		    roleId=sbean.getRoleID();
		if(roleId!=1)
		{
			return new ModelAndView("LoginPage");
		}
		}catch(Exception e)
		{
			return new ModelAndView("LoginPage");
		}
		//System.out.println("receiver id-"+receiverId);
		List<Inbox> inbox =iservice.getInboxData(receiverId);
		/*for(Inbox in:inbox){
			System.out.println("inbox data --"+in);
		}*/
		int unreadCount=iservice.getUnreadMailCount(receiverId);
	//	System.out.println("count mail-"+unreadCount);
		req.setAttribute("count", unreadCount);
		Map<Integer,Label> getLabel=iservice.getLabelByUserId(receiverId);
		req.setAttribute("label",getLabel);
		int checked=0;
		req.setAttribute("checked", checked);
		req.setAttribute("mailDeleted",req.getParameter("deleted"));
		req.setAttribute("assignSuccess", req.getParameter("assignSuccess"));
		req.setAttribute("labelCreated",req.getParameter("labelSuccess"));
		req.setAttribute("labelDelete",req.getParameter("labelDelete"));
		return new ModelAndView("inbox","inbox",inbox);
	}
	
	@RequestMapping(value="/changeStatus")
	public ModelAndView changeInboxStatus(HttpServletRequest req,HttpSession session){
		//System.out.println("InboxController.getInboxSavePage()");
		logger.info("inside getInboxSavePage method ::");
		int inboxId=Integer.parseInt(req.getParameter("inboxId"));
		System.out.println("inboxId - " +inboxId);
		
		iservice.updateReadStatus(inboxId);	
		
		SessionBean sbean=(SessionBean)session.getAttribute("sessionBean");
		int receiverId=sbean.getUserID();
		System.out.println("receiver id-"+receiverId);
		List<Inbox> inbox =iservice.getInboxData(receiverId);
		Map<Integer,Label> getLabel=iservice.getLabelByUserId(receiverId);
		req.setAttribute("label",getLabel);
		int unreadCount=iservice.getUnreadMailCount(receiverId);
		System.out.println("count mail-"+unreadCount);
		req.setAttribute("count", unreadCount);
			
		return new ModelAndView("inbox","inbox",inbox);
	}
	
	
	@RequestMapping(value="/addLabel", method = RequestMethod.POST )
	public String addLabel(Label label,HttpSession session,HttpServletRequest req,Model model ){
		//System.out.println("InboxController.addLabel()");
		logger.info("inside addLabel method ::");
		SessionBean sbean=(SessionBean)session.getAttribute("sessionBean");
		int createdBy=sbean.getUserID();
		label.setCreatedBy(createdBy);
		System.out.println("receiver id-"+createdBy);
		iservice.saveLabel(label);
		model.addAttribute("labelSuccess", "Label created successfully!!");
		return "redirect:getInboxData";
		
	}
	
	
	
	@RequestMapping(value="/getInboxDataByStatusId")
	public ModelAndView  getInboxDataByUserIdAndStatusId(HttpSession session,HttpServletRequest req){
		int statusId=Integer.parseInt(req.getParameter("statusId"));
		//System.out.println("saastattusss--"+statusId);
		//System.out.println("InboxController.getInboxSavePage()");
		logger.info("inside getInboxSavePage method ::");		
		SessionBean sbean=(SessionBean)session.getAttribute("sessionBean");
		int receiverId=sbean.getUserID();
		System.out.println("receiver id-"+receiverId);
		
		List<Inbox> inbox=new ArrayList<Inbox>();
		
		if(statusId==1){
			inbox =iservice.getInboxData(receiverId);
		}else{
		    inbox =iservice.getInboxDataByUserIdAndStatusId(receiverId, statusId);
		}
		
		/*for(Inbox in:inbox){
			System.out.println("inbox data --"+in);
		}*/
		
		int unreadCount=iservice.getUnreadMailCount(receiverId);
		//System.out.println("count mail-"+unreadCount);
		req.setAttribute("count", unreadCount);
		Map<Integer,Label> getLabel=iservice.getLabelByUserId(receiverId);
		req.setAttribute("label",getLabel);
		int checked=1;
		req.setAttribute("checked", checked);
		return new ModelAndView("inbox","inbox",inbox);
	}
	
	@RequestMapping(value="assignLevel")
	public String assignLevel(HttpServletRequest req,Model model){
		//System.out.println("InboxController.assignLevel()---" +req.getParameter("labelId"));
		//System.out.println("InboxController.assignLevel()--stats-" +req.getParameter("inboxIds"));
		
		String inboxIds=req.getParameter("inboxIds");
		
		String[] num=inboxIds.split(",");
		List<Integer> inbox=new ArrayList<Integer>();
		for(String str:num){
			System.out.println("nummmeee-"+str);
			inbox.add(Integer.parseInt(str));
		}
		
		for(Integer i:inbox){
		System.out.println("alisttt "+i);
		}
		
		int labelId=Integer.parseInt(req.getParameter("labelId"));
		
		HttpSession session=req.getSession();
		SessionBean sbean=(SessionBean)session.getAttribute("sessionBean");
		int receiverId=sbean.getUserID();
		System.out.println("receiver id-"+receiverId);
		
		iservice.updateInboxLabelIds(receiverId,labelId,inbox);
		model.addAttribute("assignSuccess","Mail moved to label sussessfully!!");
		logger.info("inside assignLevel method ::");
		return "redirect:getInboxData";
		//return new ModelAndView("inbox");
	}
	
	@RequestMapping(value="/getInboxDataByLebel")
	public void  getInboxDataByLabelIdAndUserId(HttpSession session,HttpServletRequest req,HttpServletResponse res){
		
		//System.out.println("InboxController.getInboxDataByLabelIdAndUserId()");
		logger.info("inside getInboxDataByLabelIdAndUserId method ::");		
		SessionBean sbean=(SessionBean)session.getAttribute("sessionBean");
		int receiverId=sbean.getUserID();		
		int labelId=Integer.parseInt(req.getParameter("labelId"));
		System.out.println("receiver id-"+receiverId);		
		List<Inbox> inbox =iservice.getInboxDataByLabelIdAndUserId(receiverId,labelId);
		for(Inbox in:inbox){
			System.out.println("getInboxDataByLabelIdAndUserId"+in);
		}
		
		//JSONObject responseDetailsJson = new JSONObject();
	    JSONArray jsonArray = new JSONArray();	   
	    for(Inbox in:inbox) {	        
	        JSONObject formDetailsJson = new JSONObject();	        
	        formDetailsJson.put("inboxId", in.getInboxId());
	        formDetailsJson.put("inboxSubject",in.getInboxSubject());	
	        formDetailsJson.put("inboxText",in.getInboxText());	
	        formDetailsJson.put("labelId",in.getLabelId());	
	        formDetailsJson.put("receiverId",in.getReceiverId());	
	        formDetailsJson.put("senderId",in.getSenderId());	
	        formDetailsJson.put("statusId",in.getStatusId());	
	        formDetailsJson.put("createdBy",in.getCreatedBy());
	        formDetailsJson.put("createdDate",in.getCreatedDate()); 	       
	        jsonArray.put(formDetailsJson);
	    }
	    System.out.println("json array- "+jsonArray);
	     // responseDetailsJson.put("forms", jsonArray);//Here you can see the data in json format

	    try {
			PrintWriter out=res.getWriter();
			out.print(jsonArray);
			out.flush();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	}
	
	@RequestMapping(value="/getInboxBodyByInboxId")
	public void  getInboxBodyByInboxId(HttpSession session,HttpServletRequest req,HttpServletResponse res){
		//System.out.println("InboxController.getInboxBodyByInboxId()");
		logger.info("inside getInboxBodyByInboxId method ::");	
		SessionBean sbean=(SessionBean)session.getAttribute("sessionBean");
		int userId=sbean.getUserID();
		int inboxId=Integer.parseInt(req.getParameter("inboxId"));
		//System.out.println("getInboxBodyByInboxId --inboxId - " +inboxId);		
		String inboxBody=iservice.getInboxBodyByInboxId(userId,inboxId);
		
		//JSONArray jsonArray = new JSONArray();	  
		JSONObject formDetailsJson = new JSONObject();	
		if(inboxBody.equals("")){
			inboxBody ="<tbody><tr> <td><p align='left' style='height:450px;padding-top: 15px;' >No Record Found</p></td></tr></tbody>";
		}else{
			String str[] = inboxBody.split("###");
			if(str.length==1)
				inboxBody ="<tbody><tr><td><p align='left' style='min-height:450px;padding-top: 15px;' > "+ str[0]+ "</p></td></tr></tbody>";
			else if(str.length==2){
				inboxBody ="<tbody><tr> <td align='left'><strong> Subject: "+ str[0]+ " </strong></td></tr>"+
			"<tr><td style='min-height:450px;'><p align='left' style='padding-top: 15px;'>"+ str[1]+ "</p> </td></tr></tbody>";
			}
		}
		
		formDetailsJson.put("inboxBody", inboxBody);
		 try {
				PrintWriter out=res.getWriter();
				out.print(formDetailsJson);
				out.flush();
				out.close();
			} catch (IOException e) {
				System.out.println("errors occured- "+e);
				e.printStackTrace();
			}
		//System.out.println("inbox body--"+inboxBody);
	}
	
	@RequestMapping(value="/deleteInboxData")
	public String deleteInboxData(HttpServletRequest req,HttpSession session,Model model){
		//System.out.println("InboxController.deleteInboxData()");
		logger.info("inside deleteInboxData method ::");
		
		SessionBean sbean=(SessionBean)session.getAttribute("sessionBean");
		int receiverId=sbean.getUserID();				
        String inboxIds=req.getParameter("inboxIds");		
		String[] num=inboxIds.split(",");
		List<Integer> inbox=new ArrayList<Integer>();
		for(String str:num){
			System.out.println("ddeleeteee nummmeee-"+str);
			inbox.add(Integer.parseInt(str));
		}
		
		for(Integer i:inbox){
		System.out.println("alisttt "+i);
		}
		
		iservice.deleteInboxData(inbox,receiverId);	
		
		int unreadCount=iservice.getUnreadMailCount(receiverId);
		//System.out.println("count mail-"+unreadCount);
		req.setAttribute("count", unreadCount);
		
		//System.out.println("receiver id-"+receiverId);
		List<Inbox> inboxData =iservice.getInboxData(receiverId);
		Map<Integer,Label> getLabel=iservice.getLabelByUserId(receiverId);
		req.setAttribute("label",getLabel);
		int checked=0;
		req.setAttribute("checked", checked);
		model.addAttribute("deleted","Mail deleted successfully!!");
		//return new ModelAndView("inbox","inbox",inboxData);
		return "redirect:getInboxData";
	}
	
	
	@RequestMapping(value="/downloadAttachment")
	public void downloadAttachment(HttpServletRequest req,HttpServletResponse res) throws IOException{
		//System.out.println("InboxController.downloadAttachment()");
		logger.info("inside downloadAttachment method ::");
		
		int inboxId=Integer.parseInt(req.getParameter("inboxId"));
		String pathfromdb=iservice.getAttachementPathByInboxId(inboxId);
	
		 String f[]=pathfromdb.split("\\\\");		
		
		 String uploadRootPath = req.getServletContext().getInitParameter("announcementFile");
	     File file=new File(uploadRootPath+"/"+f[3]+"/"+f[4]); 
		
		String mimeType = FileUtil.getMimeType(file.getCanonicalPath());
        if (mimeType == null) {        
            mimeType = "application/octet-stream";
        }
        
      	res.setContentType(mimeType);
		res.setHeader("Content-Disposition", "attachment;filename=\"" +file.getName() );
		res.setContentLength((int) file.length());
		InputStream is = new FileInputStream(file);
		ServletOutputStream outStream = res.getOutputStream();  
		org.apache.commons.io.IOUtils.copy(is, outStream);
		is.close();
		
	}
	
	
	 private ServletContext servletContext;
	 public void setServletContext(ServletContext servletContext) {
	        this.servletContext=servletContext;
	        
	 }
	 
	 @RequestMapping(value="/deleteLabel", method = RequestMethod.POST )
		public String deleteLabel(Label label,HttpSession session,HttpServletRequest req,Model model ) throws Exception{
			//System.out.println("InboxController.deleteLabel()");
			logger.info("inside addLabel method ::");
			SessionBean sbean=(SessionBean)session.getAttribute("sessionBean");
			int createdBy=sbean.getUserID();
			label.setCreatedBy(createdBy);
			//System.out.println("receiver id-"+createdBy);			
			iservice.deleteLabel(label);
			model.addAttribute("labelDelete", "Label deleted successfully!!");
			return "redirect:getInboxData";
			
		}
	
	
	
}
