package com.precise.controller;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
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
import com.precise.model.InfoCloseStatus;
import com.precise.model.SessionBean;
import com.precise.model.ShortList;
import com.precise.model.UserProfile;
import com.precise.service.CloseStatusService1;
import com.precise.service.HotListService1;
import com.precise.service.RMService1;
import com.precise.service.SuperRMGDService;

@Controller
public class CloseStatusController1 {
    int i=0;
	@Autowired
	CloseStatusService1 closeStatusService1;
	
	@Autowired
    HotListService1 hotListservice1;
	
	 @Autowired
     RMService1 rmService1;
	 
	@Autowired
	SendMail sendMail;
	 @Autowired
	 SuperRMGDService superRMService;
	
	@RequestMapping(value ="/getAllCloseStatusDetails1",method = RequestMethod.GET)
	public ModelAndView getAllCloseStatusDetails(ModelAndView model,HttpServletRequest req,Model model1){
		/*System.out.println(req.getParameter("cmpRoleId"));
		int cmpRoleId=req.getParameter("cmpRoleId")==null?0:Integer.parseInt(req.getParameter("cmpRoleId"));
		SessionBean sessionBean = (SessionBean) req.getSession().getAttribute("sessionBean");
		if(cmpRoleId!=0){
			sessionBean.setCmpRoleId(cmpRoleId);
		}else{
			cmpRoleId = sessionBean.getCmpRoleId();
		}
		*/
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
		if(cmpRoleId!=0){
			sessionBean.setCmpRoleId(cmpRoleId);
		}else{
			cmpRoleId = sessionBean.getCmpRoleId();
		}
		
		boolean releaseFlag=rmService1.getReleaseFlagStatus(cmpRoleId);	
		if(releaseFlag)
			req.setAttribute("releaseFlag",1);
		else
			req.setAttribute("releaseFlag",0);
		List<CloseStatus> CloseStatusDetaillist = closeStatusService1.getCloseStatusDetail(cmpRoleId);
		System.out.println("FineDetaillist :"+CloseStatusDetaillist.size());
		req.setAttribute("CloseStatusDetails", CloseStatusDetaillist);
		
		List<CloseStatus> approveCloseStatusList = closeStatusService1.getApproveCloseStatusList(cmpRoleId);
		System.out.println("FineDetaillist :"+CloseStatusDetaillist.size());
		req.setAttribute("approveCloseStatusList", approveCloseStatusList);
		
		List<CloseStatus> unApproveCloseStatusList = closeStatusService1.getUnApproveCloseStatusList(cmpRoleId);
		System.out.println("FineDetaillist :"+CloseStatusDetaillist.size());
		req.setAttribute("unApproveCloseStatusList", unApproveCloseStatusList);
		
		List<ShortList> shortlist=rmService1.getShortlistedDataByRole(cmpRoleId);
		System.out.println("list shotlist-"+shortlist);
		req.setAttribute("shortlist1",shortlist);
		
		List<ShortList> viewReleasedShortlist=superRMService.getReleasedShortlistedDataByRole(cmpRoleId);
		System.out.println("view shortlisted-"+viewReleasedShortlist);
		req.setAttribute("viewReleasedShortlist",viewReleasedShortlist);
		
		List<HotList> hotlist=hotListservice1.getShortlistedDataByRole(cmpRoleId);//role
 		System.out.println("list shotlist-"+shortlist);	 	
 		req.setAttribute("hotlist",hotlist);
 		
 		
 		//List<HotList> hotlistRelease=hotListservice1.getHotReleaseCompany(cmpRoleId);//role
 		List<HotList> viewReleased=superRMService.getViewReleasedHotlist(cmpRoleId);//role 	
 		//System.out.println("list shotlist-"+viewReleased);
 		req.setAttribute("hotlistRelease",viewReleased);
 	
 		List<ShortList> shortlistedByHr=rmService1.getHRShortlistedDataByRole(cmpRoleId);
		System.out.println("view shortlistedByHr-"+shortlistedByHr);
		req.setAttribute("shortlistedByHr",shortlistedByHr);
		
		CloseStatus header = closeStatusService1.getCloseStatusHeader(cmpRoleId);
		header.setRoleId(cmpRoleId);
		System.out.println(header.getFirmName()+"::"+header.getRoleName()+"::"+header.getProcess()+"::"+header.getExperienceReq()  );
		req.setAttribute("closeStatus", header);
		model1.addAttribute("cmpRoleId",cmpRoleId);
		model.setViewName("CloseStatus1");
		return model;
		
	}
	
	@RequestMapping(value ="/getAllCloseStatusDetailsForSuperRMGD",method = RequestMethod.GET)
	public ModelAndView getAllCloseStatusDetailsForSuperRM(ModelAndView model,HttpServletRequest req,Model model1){
		//System.out.println(req.getParameter("cmpRoleId"));
		/*int cmpRoleId=req.getParameter("cmpRoleId")==null?0:Integer.parseInt(req.getParameter("cmpRoleId"));
		SessionBean sessionBean = (SessionBean) req.getSession().getAttribute("sessionBean");
		if(cmpRoleId!=0){
			sessionBean.setCmpRoleId(cmpRoleId);
		}else{
			cmpRoleId = sessionBean.getCmpRoleId();
		}
		*/
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
		if(cmpRoleId!=0){
			sessionBean.setCmpRoleId(cmpRoleId);
		}else{
			cmpRoleId = sessionBean.getCmpRoleId();
		}
		List<CloseStatus> CloseStatusDetaillist = closeStatusService1.getCloseStatusDetail(cmpRoleId);
		//System.out.println("FineDetaillist :"+CloseStatusDetaillist.size());
		req.setAttribute("CloseStatusDetails", CloseStatusDetaillist);
		
		List<CloseStatus> approveCloseStatusList = closeStatusService1.getApproveCloseStatusList(cmpRoleId);
		System.out.println("FineDetaillist :"+CloseStatusDetaillist.size());
		req.setAttribute("approveCloseStatusList", approveCloseStatusList);
		
		List<CloseStatus> unApproveCloseStatusList = closeStatusService1.getUnApproveCloseStatusList(cmpRoleId);
		//System.out.println("FineDetaillist :"+CloseStatusDetaillist.size());
		req.setAttribute("unApproveCloseStatusList", unApproveCloseStatusList);
		
		List<ShortList> shortlist=rmService1.getShortlistedDataByRole(cmpRoleId);
		//System.out.println("list shotlist-"+shortlist);
		req.setAttribute("shortlist",shortlist);
		
		List<ShortList> viewReleasedShortlist=superRMService.getReleasedShortlistedDataByRole(cmpRoleId);
		System.out.println("view shortlisted-"+viewReleasedShortlist);
		req.setAttribute("viewReleasedShortlist",viewReleasedShortlist);
		
		List<HotList> hotlist=hotListservice1.getShortlistedDataByRole(cmpRoleId);//role
 		//System.out.println("list shotlist-"+shortlist);	 	
 		req.setAttribute("hotlist",hotlist);
 		
 		
 		//List<HotList> hotlistRelease=hotListservice1.getHotReleaseCompany(cmpRoleId);//role 	
 		List<HotList> viewReleased=superRMService.getViewReleasedHotlist(cmpRoleId);//role 	
 		//System.out.println("list shotlist-"+hotlistRelease);
 		req.setAttribute("hotlistRelease",viewReleased);
 	
		
		CloseStatus header = closeStatusService1.getCloseStatusHeader(cmpRoleId);
		header.setRoleId(cmpRoleId);
		System.out.println(header.getFirmName()+"::"+header.getRoleName()+"::"+header.getProcess()+"::"+header.getExperienceReq()  );
		req.setAttribute("closeStatus", header);
		model1.addAttribute("cmpRoleId",cmpRoleId);
		model.setViewName("closeStatusSuperRMGD");
		return model;
		
	}
	
	@RequestMapping(value ="/getAllCloseStatusDetailsForSuperRMGD2",method = RequestMethod.GET)
	public ModelAndView getAllCloseStatusDetailsForSuperRM2(ModelAndView model,HttpServletRequest req,Model model1){
		/*System.out.println(req.getParameter("cmpRoleId"));
		int cmpRoleId=req.getParameter("cmpRoleId")==null?0:Integer.parseInt(req.getParameter("cmpRoleId"));
		SessionBean sessionBean = (SessionBean) req.getSession().getAttribute("sessionBean");
		if(cmpRoleId!=0){
			sessionBean.setCmpRoleId(cmpRoleId);
		}else{
			cmpRoleId = sessionBean.getCmpRoleId();
		}*/
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
		if(cmpRoleId!=0){
			sessionBean.setCmpRoleId(cmpRoleId);
		}else{
			cmpRoleId = sessionBean.getCmpRoleId();
		}
		
		List<CloseStatus> CloseStatusDetaillist = closeStatusService1.getCloseStatusDetail(cmpRoleId);
		System.out.println("list CloseStatusDetaillist :"+CloseStatusDetaillist.size());
		req.setAttribute("CloseStatusDetails", CloseStatusDetaillist);
		
		List<CloseStatus> approveCloseStatusList = closeStatusService1.getApproveCloseStatusList(cmpRoleId);
		System.out.println("list approveCloseStatusList :"+approveCloseStatusList.size());
		req.setAttribute("approveCloseStatusList", approveCloseStatusList);
		
		List<CloseStatus> unApproveCloseStatusList = closeStatusService1.getUnApproveCloseStatusList(cmpRoleId);
		System.out.println("list unApproveCloseStatusList :"+unApproveCloseStatusList.size());
		req.setAttribute("unApproveCloseStatusList", unApproveCloseStatusList);
		
		List<ShortList> viewReleasedShortlist=superRMService.getReleasedShortlistedDataByRole(cmpRoleId);
		System.out.println("list viewReleasedShortlist-"+viewReleasedShortlist.size());
		req.setAttribute("viewReleasedShortlist",viewReleasedShortlist);
		
		List<ShortList> shortlist=rmService1.getShortlistedDataByRole(cmpRoleId);
		System.out.println("list shotlist-"+shortlist.size());
		req.setAttribute("shortlist",shortlist);
		
		List<HotList> hotlist=hotListservice1.getShortlistedDataByRole(cmpRoleId);//role
 		System.out.println("list hotlist-"+hotlist.size());	 	
 		req.setAttribute("hotlist",hotlist);
 		
 		
 		//List<HotList> hotlistRelease=hotListservice1.getHotReleaseCompany(cmpRoleId);//role
 		List<HotList> hotlistRelease=superRMService.getViewReleasedHotlist(cmpRoleId);//role 	
 		System.out.println("list viewReleased-"+hotlistRelease.size());
 		req.setAttribute("hotlistRelease",hotlistRelease);
 	
		
		CloseStatus header = closeStatusService1.getCloseStatusHeader(cmpRoleId);
		header.setRoleId(cmpRoleId);
		System.out.println(header.getFirmName()+"::"+header.getRoleName()+"::"+header.getProcess()+"::"+header.getExperienceReq()  );
		req.setAttribute("closeStatus", header);
		model1.addAttribute("cmpRoleId",cmpRoleId);
		model.setViewName("closeStatusSuperRMGD2");
		return model;
		
	}
	
	@RequestMapping(value="/getUserValues1")
	public void  getUserValues(HttpSession session,HttpServletRequest req,HttpServletResponse res){
		int rollNumber = Integer.parseInt(req.getParameter("rollNumber"));
		List<UserProfile> userValue = closeStatusService1.getUserValues(rollNumber);
		for (UserProfile in : userValue) {
			System.out.println("getUserValues" + in);
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	}
	
	@RequestMapping(value="/getInternshipList1" ,method = RequestMethod.POST)
	public JSONArray getInternshipListValues(HttpSession session,HttpServletRequest req,HttpServletResponse res){
		
		int rollNumber = Integer.parseInt(req.getParameter("rollNumber"));
		JSONArray jsonArray = closeStatusService1.getInternshipValues(rollNumber);

		System.out.println("getInternshipListValues() json array- " + jsonArray);
		try {
			PrintWriter out = res.getWriter();
			out.print(jsonArray);
			out.flush();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonArray;
	}
	
	@RequestMapping(value="/getExperienceList1" ,method = RequestMethod.POST)
	public JSONArray getExperienceList(HttpSession session,HttpServletRequest req,HttpServletResponse res){
		int rollNumber = Integer.parseInt(req.getParameter("rollNumber"));
		JSONArray jsonArray = closeStatusService1.getExperienceList(rollNumber);

		try {
			PrintWriter out = res.getWriter();
			out.print(jsonArray);
			out.flush();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonArray;
	}
	
	@RequestMapping(value="/getCVList1" ,method = RequestMethod.POST)
	public JSONArray getCVList(HttpSession session,HttpServletRequest req,HttpServletResponse res){
		int rollNumber = Integer.parseInt(req.getParameter("rollNumber"));
		JSONArray jsonArray = closeStatusService1.getCVList(rollNumber);
		System.out.println("getCVList() :: json array- " + jsonArray);

		try {
			PrintWriter out = res.getWriter();
			out.print(jsonArray);
			out.flush();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonArray;
	}
	
	@RequestMapping(value="/getInfoValues1")
	public void  getInfoValues(HttpSession session,HttpServletRequest req,HttpServletResponse res){
		SessionBean sbean = (SessionBean) session.getAttribute("sessionBean");
		int roleId =  sbean.getCmpRoleId();
		InfoCloseStatus userValue = closeStatusService1.getInfoValues(roleId);
		JSONArray jsonArray = new JSONArray();

		JSONObject infoDetailsJson = new JSONObject();

		infoDetailsJson.put("clusterName", userValue.getClusterName());
		infoDetailsJson.put("cohortName", userValue.getCohortName());
		infoDetailsJson.put("compensation", userValue.getCompensation());
		infoDetailsJson.put("hires", userValue.getHires());
		infoDetailsJson.put("noOfApp", userValue.getNoOfApp());
		infoDetailsJson.put("rollOver", userValue.getRollOver());
		infoDetailsJson.put("verified", userValue.getVerified());
		infoDetailsJson.put("unVerified", userValue.getUnVerified());

		jsonArray.put(infoDetailsJson);

		System.out.println("getInfoValues() :: json array- " + jsonArray);

		try {
			PrintWriter out = res.getWriter();
			out.print(jsonArray);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="/getInfoValuesWithoutSession1")
	public void  getInfoValuesWithoutSession(HttpSession session,HttpServletRequest req,HttpServletResponse res){
		String cid=req.getParameter("cmpRoleId");
		System.out.println("ciddde--"+cid);
		int cmpRoleId=Integer.parseInt(req.getParameter("cmpRoleId"));
		System.out.println("comprole id in clsoetstat-"+cmpRoleId);
		/*SessionBean sbean = (SessionBean) session.getAttribute("sessionBean");
		int roleId =  sbean.getCmpRoleId();*/
		
		InfoCloseStatus userValue = closeStatusService1.getInfoValues(cmpRoleId);
		JSONArray jsonArray = new JSONArray();
		JSONObject infoDetailsJson = new JSONObject();
		infoDetailsJson.put("clusterName", userValue.getClusterName());
		infoDetailsJson.put("cohortName", userValue.getCohortName());
		infoDetailsJson.put("compensation", userValue.getCompensation());
		infoDetailsJson.put("hires", userValue.getHires());
		infoDetailsJson.put("noOfApp", userValue.getNoOfApp());
		infoDetailsJson.put("rollOver", userValue.getRollOver());
		infoDetailsJson.put("verified", userValue.getVerified());
		infoDetailsJson.put("unVerified", userValue.getUnVerified());

		jsonArray.put(infoDetailsJson);

		System.out.println("getInfoValues() :: json array- " + jsonArray);

		try {
			PrintWriter out = res.getWriter();
			out.print(jsonArray);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	@RequestMapping(value = "/updateFlag1", method = RequestMethod.POST)
	public String updateFlag(HttpServletRequest request) {
		String updateApplyId =request.getParameter("updateApplyId");
		System.out.println("updateApplyId::" + updateApplyId);
		List<CloseStatus> closeStatuslist = new ArrayList<CloseStatus>();
		CloseStatus closeStatus = null;
		String[] idArr = updateApplyId.split(",");
		for(String id:idArr){
			closeStatus = new CloseStatus();
			closeStatus.setApplyId(Integer.parseInt(id));
			System.out.println("id set value :: " +closeStatus.getApplyId());
			closeStatuslist.add(closeStatus);
		}
		//System.out.println("ForumId value in updateForumValues method::" + forum.getEditPermission());
		closeStatusService1.updateFlag(closeStatuslist);
		return "redirect:getAllCloseStatusDetails";
	}
	
	@RequestMapping(value = "/updateFlagBySuperRMGD", method = RequestMethod.POST)
	public String updateFlagBySuperRM1(HttpServletRequest request) {
		String updateApplyId =request.getParameter("updateApplyId");
		System.out.println("updateApplyId::" + updateApplyId);
		List<CloseStatus> closeStatuslist = new ArrayList<CloseStatus>();
		CloseStatus closeStatus = null;
		String[] idArr = updateApplyId.split(",");
		for(String id:idArr){
			closeStatus = new CloseStatus();
			closeStatus.setApplyId(Integer.parseInt(id));
			System.out.println("id set value :: " +closeStatus.getApplyId());
			closeStatuslist.add(closeStatus);
		}
		int cmpRoleId=request.getParameter("cmpRoleId")==null?0:Integer.parseInt(request.getParameter("cmpRoleId"));
		System.out.println("ForumId value in updateForumValues methods::bysuper rm 1" + cmpRoleId);
		closeStatusService1.updateFlag(closeStatuslist);
		
		return "redirect:getAllCloseStatusDetailsForSuperRM1?cmpRoleId="+cmpRoleId; //getAllCloseStatusDetails  
	}
	
	@RequestMapping(value = "/updateFlagBySuperRMGD2", method = RequestMethod.POST)
	public String updateFlagBySuperRM2(HttpServletRequest request) {
		String updateApplyId =request.getParameter("updateApplyId");
		System.out.println("updateApplyId::" + updateApplyId);
		List<CloseStatus> closeStatuslist = new ArrayList<CloseStatus>();
		CloseStatus closeStatus = null;
		String[] idArr = updateApplyId.split(",");
		for(String id:idArr){
			closeStatus = new CloseStatus();
			closeStatus.setApplyId(Integer.parseInt(id));
			System.out.println("id set value :: " +closeStatus.getApplyId());
			closeStatuslist.add(closeStatus);
		}
		int cmpRoleId=request.getParameter("cmpRoleId")==null?0:Integer.parseInt(request.getParameter("cmpRoleId"));
		System.out.println("ForumId value in updateForumValues method:: by super rm2 " + cmpRoleId+request.getParameter("cmpRoleId"));
		closeStatusService1.updateFlag(closeStatuslist);
		
		return "redirect:getAllCloseStatusDetailsForSuperRM2?cmpRoleId="+cmpRoleId;  //  getAllCloseStatusDetails
	}
	
	
	@RequestMapping(value = "/shortList1", method = RequestMethod.POST)
	public String saveShortList(HttpServletRequest request, CloseStatus closeStatus) {
		//System.out.println("csdddddd");
		String shortListId = request.getParameter("updateApplyId");
		SessionBean sessionBean = (SessionBean) request.getSession().getAttribute("sessionBean");
		int userid = sessionBean.getUserID();
		String emailId= sessionBean.getEmail();
		
		//System.out.println("Rm EmailId Is :: ");
		//System.out.println("+::" + shortListId);
		List<CloseStatus> closeStatuslist = new ArrayList<CloseStatus>();
		CloseStatus closeStatusLocaL = null;
		String[] idArr = shortListId.split(",");
		int cmpRoleId=request.getParameter("cmpRoleId")==null?0:Integer.parseInt(request.getParameter("cmpRoleId"));
		if(cmpRoleId!=0){
			sessionBean.setCmpRoleId(cmpRoleId);
		}else{
			cmpRoleId = sessionBean.getCmpRoleId();
		}
		for (String id : idArr) {
			closeStatusLocaL = new CloseStatus();
			closeStatusLocaL.setApplyId(Integer.parseInt(id));
			//System.out.println("id set value :: " + closeStatusLocaL.getApplyId());
			closeStatuslist.add(closeStatusLocaL);
		}
		//System.out.println("rank values:::"+closeStatus.getRankFlag());
		closeStatusService1.saveShortList(closeStatuslist, userid);
		closeStatusService1.saveHRList(closeStatus.getEmailId(),cmpRoleId,closeStatus.getMailDes(),emailId,closeStatus.getHrName(),userid);
		
		String link=request.getRequestURL().toString();
		String[] str=link.split("/IIMForum/");
		Hashids hashids = new Hashids("comp role id",10);
		String hash = hashids.encode(cmpRoleId);
		System.out.println("hashcode values::"+hash);
		//String path=str[0].concat("/IIMForum/getCompanyHRPage?cmpRoleId="+"abdcr"+hash);
		String path=str[0].concat("/IIMForum/getCompanyHRPage?cmpRoleId="+hash);
		closeStatus.setGeneratedLink(path);
		String mailFormat=this.generateMailFormat(closeStatus.getEmailId(),closeStatus.getHrName(), path,shortListId,closeStatus.getRankFlag(),closeStatus.getMailDes(),cmpRoleId,emailId);
		
		CloseStatus header = closeStatusService1.getCloseStatusHeader(cmpRoleId);
		String process=header.getProcess();
		int year=header.getYear();
		String subject= "[IIMA] "+process+"_"+year+"_"+header.getRoleName();
		//System.out.println("subject of mail  :: " +subject);
		sendMail.sendMailHR(mailFormat,closeStatus.getEmailId(),subject);
		return "redirect:getAllCloseStatusDetails1";
	}
	
	
	@RequestMapping(value = "/shortListBySuperRMGD", method = RequestMethod.POST)
	public String saveshortListBySuperRM1(HttpServletRequest request, CloseStatus closeStatus) {
		String shortListId = request.getParameter("updateApplyId");
		SessionBean sessionBean = (SessionBean) request.getSession().getAttribute("sessionBean");
		int userid = sessionBean.getUserID();
		System.out.println("+::" + shortListId);
		List<CloseStatus> closeStatuslist = new ArrayList<CloseStatus>();
		CloseStatus closeStatusLocaL = null;
		String[] idArr = shortListId.split(",");
		int cmpRoleId=request.getParameter("cmpRoleId")==null?0:Integer.parseInt(request.getParameter("cmpRoleId"));
		if(cmpRoleId!=0){
			sessionBean.setCmpRoleId(cmpRoleId);
		}else{
			cmpRoleId = sessionBean.getCmpRoleId();
		}
		for (String id : idArr) {
			closeStatusLocaL = new CloseStatus();
			closeStatusLocaL.setApplyId(Integer.parseInt(id));
			System.out.println("id set value :: " + closeStatusLocaL.getApplyId());
			closeStatuslist.add(closeStatusLocaL);
		}
		System.out.println("rank values:::"+closeStatus.getRankFlag());
		closeStatusService1.saveShortList(closeStatuslist, userid);
		return "redirect:getAllCloseStatusDetailsForSuperRM1?cmpRoleId="+cmpRoleId;
	}
	
	@RequestMapping(value = "/shortListBySuperRMGD2", method = RequestMethod.POST)
	public String saveshortListBySuperRM2(HttpServletRequest request, CloseStatus closeStatus) {
		String shortListId = request.getParameter("updateApplyId");
		SessionBean sessionBean = (SessionBean) request.getSession().getAttribute("sessionBean");
		int userid = sessionBean.getUserID();
		System.out.println("+::" + shortListId);
		List<CloseStatus> closeStatuslist = new ArrayList<CloseStatus>();
		CloseStatus closeStatusLocaL = null;
		String[] idArr = shortListId.split(",");
		int cmpRoleId=request.getParameter("cmpRoleId")==null?0:Integer.parseInt(request.getParameter("cmpRoleId"));
		if(cmpRoleId!=0){
			sessionBean.setCmpRoleId(cmpRoleId);
		}else{
			cmpRoleId = sessionBean.getCmpRoleId();
		}
		for (String id : idArr) {
			closeStatusLocaL = new CloseStatus();
			closeStatusLocaL.setApplyId(Integer.parseInt(id));
			System.out.println("id set value :: " + closeStatusLocaL.getApplyId());
			closeStatuslist.add(closeStatusLocaL);
		}
		System.out.println("rank values:::"+closeStatus.getRankFlag());
		closeStatusService1.saveShortList(closeStatuslist, userid);
		return "redirect:getAllCloseStatusDetailsForSuperRM2?cmpRoleId="+cmpRoleId;
	}
	
	@RequestMapping(value = "/downloadCv1", produces = "application/zip")
	public void zipFiles(HttpServletResponse response, HttpServletRequest request) throws IOException {
		String downloadCvId = request.getParameter("downloadCvId");
		List<CloseStatus> downloadCvlist = new ArrayList<CloseStatus>();
		CloseStatus closeStatus = null;
		String[] idArr = downloadCvId.split(",");
		for (String id : idArr) {
			closeStatus = new CloseStatus();
			closeStatus.setApplyId(Integer.parseInt(id));
			downloadCvlist.add(closeStatus);
		}

		List<String> downloadFileNameList = closeStatusService1.getDownloadCVList(downloadCvlist);
		List<File> files = new ArrayList<File>();
		List<String> filename = new ArrayList<String>();
		for (String filePath : downloadFileNameList) {
			String arr[] = filePath.split("###");
			//System.out.println("CV Name : "+arr[0]+" file path :: "+arr[1]);
			files.add(new File(arr[1]));
/*			if(filename.contains(arr[0])){
				System.out.println("file name already exixts :: "+arr[0]);
				filename.add("d_"+arr[0]);
			}else
				filename.add(arr[0]);*/
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
		for( int i=0; i <files.size(); i++){
			file = files.get(i);
			fileName = fileNameList.get(i);
			if (file.exists()) {
				FileInputStream fis = new FileInputStream(file.getCanonicalFile());
				BufferedInputStream bis = new BufferedInputStream(fis);
				zos.putNextEntry(new ZipEntry(fileName+"."+FilenameUtils.getExtension(file.getName())));
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
	
	public String generateMailFormat(String emailId,String hrName,String link,String shortList,Boolean rankFlag,String msgDesc,int companyRoleId,String rmemailId){
		StringBuffer mailHtml=new StringBuffer();
		String prefernceValue="";
		if(msgDesc!=null){
			msgDesc=msgDesc+"<br/><br/>";
		}else{
			msgDesc="";
		}
		
		if(rankFlag!=null){
			if(rankFlag){
				//System.out.println("inside if :::::::::::");
				prefernceValue=closeStatusService1.getSelectedUserValues(shortList,companyRoleId)+"<br/>";
			}else{
				//System.out.println("inside else :::::::::::");
				prefernceValue="";
			}
		}
		
		if(!prefernceValue.equals(""))
			prefernceValue=" Preference List :</br></br>"+prefernceValue;
		
		mailHtml.append("<html><body>Dear "+hrName+",<br/><br/> "+msgDesc+prefernceValue+"Please <a href="+link+">Click here</a> for managing applications.");
	    mailHtml.append("<br/><br/><i>This is an auto generated mail, do not respond to this ID. If you have any queries please contact your RM "+rmemailId+" </i><br/><br/>Thanks & Regards<br>IIMA Placement Committee</body></html>");
		//System.out.println("mailHtml:::"+mailHtml.toString());
		return mailHtml.toString();
	}
	

	@RequestMapping(value = "/pdfmerge1", produces = "application/pdf")
	public void pdfFiles(HttpServletResponse response, HttpServletRequest request) throws Exception {
		String downloadCvId = request.getParameter("bindId");
		String pdfBinderLocation = request.getServletContext().getInitParameter("tempFileLocation");
		List<CloseStatus> downloadCvlist = new ArrayList<CloseStatus>();
		CloseStatus closeStatus = null;
		String[] idArr = downloadCvId.split(",");
		for (String id : idArr) {
			closeStatus = new CloseStatus();
			closeStatus.setApplyId(Integer.parseInt(id));
			downloadCvlist.add(closeStatus);
		}

		List<String> downloadFileNameList = closeStatusService1.getDownloadCVList(downloadCvlist);
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
		//String fileName = null;

		for (int i = 0; i < files.size(); i++) {
			file1 = files.get(i);
			//fileName = fileNameList.get(i);
			doc = PDDocument.load(file1);
			PDFmerger.addSource(file1);
			doc.close();
		}
		PDFmerger.mergeDocuments();
		return path;
	}
	
}
