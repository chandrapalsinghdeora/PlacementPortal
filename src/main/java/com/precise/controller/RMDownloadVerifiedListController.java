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
import com.precise.model.RMDownloadVerifiedList;
import com.precise.model.SessionBean;
import com.precise.model.UserProfile;
import com.precise.service.RMDownloadVerifiedService;

@Controller
public class RMDownloadVerifiedListController {
	@Autowired
	RMDownloadVerifiedService rmDownloadVerifiedService;
	
	@RequestMapping(value={"/getRMDownload"},method={RequestMethod.GET})
	public ModelAndView getRMDownload(ModelAndView model,HttpServletRequest request) {
		List<RMDownloadVerifiedList> RMVerifylist = rmDownloadVerifiedService.getRMVerifiedDetail();
		System.out.println("FineDetaillist :"+RMVerifylist.size());
		request.setAttribute("rmVerifylist", RMVerifylist);
		model.setViewName("RMDownloadVerifiedList");
		return model;
	}
	
	@RequestMapping(value="/getUserDetails")
	public void  getUserDetails(HttpSession session,HttpServletRequest req,HttpServletResponse res){
		
		SessionBean sbean=(SessionBean)session.getAttribute("sessionBean");
		
		int rollNumber=Integer.parseInt(req.getParameter("rollNumber"));
		
		List<UserProfile> userValue =rmDownloadVerifiedService.getUserValues(rollNumber);
		for(UserProfile in:userValue){
			//System.out.println("getUserValues"+in);
		}
		
		//JSONObject responseDetailsJson = new JSONObject();
	    JSONArray jsonArray = new JSONArray();	   
	    for(UserProfile in:userValue) {	        
	        JSONObject userDetailsJson = new JSONObject();	
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

}
