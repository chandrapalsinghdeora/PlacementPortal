package com.precise.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.precise.model.CloseStatus;
import com.precise.model.InfoCloseStatus;
import com.precise.model.SessionBean;
import com.precise.model.UserProfile;
import com.precise.service.CloseStatusService;

@Controller
public class RemoveCloseStatusController {/*

	@Autowired
	CloseStatusService closeStatusService;
	
	@RequestMapping(value ="/getUnApprovedCloseStatus",method = RequestMethod.GET)
	public ModelAndView getAllCloseStatusDetails(ModelAndView model,HttpServletRequest req){
		
	//	int cmpRoleId=req.getParameter("cmpRoleId")==null?0:Integer.parseInt(req.getParameter("cmpRoleId"));
	//	cmpRoleId = 46;
		SessionBean sessionBean = (SessionBean) req.getSession().getAttribute("sessionBean");
		int cmpRoleId=req.getParameter("cmpRoleId")==null?0:Integer.parseInt(req.getParameter("cmpRoleId"));
		//cmpRoleId = 46;
		if(cmpRoleId!=0){
			sessionBean.setCmpRoleId(cmpRoleId);
		}else{
			cmpRoleId = sessionBean.getCmpRoleId();
		}
		System.out.println("cmpRoleId : "+cmpRoleId);	
		List<CloseStatus> unApproveCloseStatusList = closeStatusService.getUnApproveCloseStatusList();
		System.out.println("FineDetaillist :"+unApproveCloseStatusList.size());
		req.setAttribute("unApproveCloseStatusList", unApproveCloseStatusList);
		
		CloseStatus header = closeStatusService.getCloseStatusHeader(cmpRoleId);
		header.setRoleId(cmpRoleId);
		System.out.println(header.getFirmName()+"::"+header.getRoleName()+"::"+header.getProcess()+"::"+header.getExperienceReq()  );
		req.setAttribute("closeStatus", header);
		model.setViewName("RemovedCloseStatus");
		return model;
		
	}
	
	@RequestMapping(value="/getUnApproveUserValues")
	public void  getUserValues(HttpSession session,HttpServletRequest req,HttpServletResponse res){
		
		SessionBean sbean=(SessionBean)session.getAttribute("sessionBean");
		
		int rollNumber=Integer.parseInt(req.getParameter("rollNumber"));
		
		List<UserProfile> userValue =closeStatusService.getUserValues(rollNumber);
		for(UserProfile in:userValue){
			System.out.println("getUserValues"+in);
		}
		
		//JSONObject responseDetailsJson = new JSONObject();
	    JSONArray jsonArray = new JSONArray();	   
	    for(UserProfile in:userValue) {	        
	        JSONObject userDetailsJson = new JSONObject();	
	        userDetailsJson.put("emailId",in.getEmailAddress());
	        userDetailsJson.put("fullname",in.getFullName());
	        userDetailsJson.put("cvName",in.getCvName());
	        userDetailsJson.put("mentor",in.getMentor());
	        userDetailsJson.put("gender",in.getGender());
	        userDetailsJson.put("percentage",in.getPercentage());
	        userDetailsJson.put("percenatageTwelve",in.getPercenatageTwelve());
	        userDetailsJson.put("percentageGraduate",in.getPercentageGraduate());
	        userDetailsJson.put("postgraduatePercentage",in.getPostgraduatePercentage());
	      
	        jsonArray.put(userDetailsJson);
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
		
	@RequestMapping(value="/getUnApproveInternshipList" ,method = RequestMethod.POST)
	public JSONArray getInternshipValues(HttpSession session,HttpServletRequest req,HttpServletResponse res){
		
		SessionBean sbean=(SessionBean)session.getAttribute("sessionBean");
		
		int rollNumber=Integer.parseInt(req.getParameter("rollNumber"));
		
		//List<UserProfile> userValue =closeStatusService.getInternshipValues(rollNumber);
		 JSONArray jsonArray=closeStatusService.getInternshipValues(rollNumber);
		
		
		//JSONObject responseDetailsJson = new JSONObject();
	    JSONArray jsonArray = new JSONArray();	   
	    for(UserProfile in:userValue) {	        
	        JSONObject userDetailsJson = new JSONObject();	
	        userDetailsJson.put("companyname",in.getCompany());
	        System.out.println("companyname" + in.getCompany());
	        userDetailsJson.put("duration",in.getDurationSummerInternship());
	        
	      
	        jsonArray.put(userDetailsJson);
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
	    return jsonArray;
	}
	
	@RequestMapping(value="/getUnApproveExperienceList" ,method = RequestMethod.POST)
	public JSONArray getExperienceList(HttpSession session,HttpServletRequest req,HttpServletResponse res){
		
		SessionBean sbean=(SessionBean)session.getAttribute("sessionBean");
		
		int rollNumber=Integer.parseInt(req.getParameter("rollNumber"));
		
		//List<UserProfile> userValue =closeStatusService.getInternshipValues(rollNumber);
		 JSONArray jsonArray=closeStatusService.getExperienceList(rollNumber);
		
	        
	      
	        
	  
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
	    return jsonArray;
	}
	
	@RequestMapping(value="/getUnApproveCVList" ,method = RequestMethod.POST)
	public JSONArray getCVList(HttpSession session,HttpServletRequest req,HttpServletResponse res){
		
		SessionBean sbean=(SessionBean)session.getAttribute("sessionBean");
		
		int rollNumber=Integer.parseInt(req.getParameter("rollNumber"));
		
		//List<UserProfile> userValue =closeStatusService.getInternshipValues(rollNumber);
		 JSONArray jsonArray=closeStatusService.getCVList(rollNumber);
	  
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
	    return jsonArray;
	}
	
	@RequestMapping(value="/getUnApproveInfoValues")
	public void  getInfoValues(HttpSession session,HttpServletRequest req,HttpServletResponse res){
		
		SessionBean sbean=(SessionBean)session.getAttribute("sessionBean");
		
		//int rollNumber=Integer.parseInt(req.getParameter("rollNumber"));
		
		int roleId=1;
		InfoCloseStatus userValue =closeStatusService.getInfoValues(roleId);
		
		//JSONObject responseDetailsJson = new JSONObject();
	    JSONArray jsonArray = new JSONArray();	   
	    
	        JSONObject infoDetailsJson = new JSONObject();
	        
	        infoDetailsJson.put("clusterName",userValue.getClusterName());
	        infoDetailsJson.put("cohortName",userValue.getCohortName());
	        infoDetailsJson.put("compensation",userValue.getCompensation());
	        infoDetailsJson.put("hires",userValue.getHires());
	        infoDetailsJson.put("noOfApp",userValue.getNoOfApp());
	        infoDetailsJson.put("rollOver",userValue.getRollOver());
	        infoDetailsJson.put("verified",userValue.getVerified());
	        infoDetailsJson.put("unVerified",userValue.getUnVerified());
	        
	        jsonArray.put(infoDetailsJson);
	    
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
	
	
*/}
