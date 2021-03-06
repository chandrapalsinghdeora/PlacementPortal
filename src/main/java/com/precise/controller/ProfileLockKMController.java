package com.precise.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.precise.model.ProfileLockKM;
import com.precise.model.SessionBean;
import com.precise.service.ProfileLockKmService;

@Controller
public class ProfileLockKMController {
	
	@Autowired
	ProfileLockKmService profileLockKmService;
	
	@RequestMapping(value = "/getAllProfileDetail", method = RequestMethod.GET)
	public ModelAndView getAllProfileDetail(ModelAndView model, HttpServletRequest req) {
		int roleId,userid;
		try
		{
		SessionBean sbean=(SessionBean)req.getSession().getAttribute("sessionBean");
		userid=sbean.getUserID();
		 roleId=sbean.getRoleID();
		if(roleId!=6)
		{
			return new ModelAndView("LoginPage");
		}
		}catch(Exception e)
		{
			return new ModelAndView("LoginPage");
		}
		List<ProfileLockKM> profilelist = profileLockKmService.getAllProfile();
		req.setAttribute("profilelist", profilelist);
		
		model.setViewName("ProfileLockKM");
		return model;
	}
	
	@RequestMapping(value = "/updateProfileDetail", method = RequestMethod.GET)
	public ModelAndView updateProfileDetail(ModelAndView model, HttpServletRequest req,ProfileLockKM profileLockKM) {
		SessionBean sbean=(SessionBean)req.getSession().getAttribute("sessionBean");
		//int userId=sbean.getUserID();
		
		System.out.println("profile fff"+profileLockKM.getSelectedIds());
		for(Integer i:profileLockKM.getSelectedIds()){
			//System.out.println("idsssisss--"+i);
			
		}
		profileLockKmService.updateLockedProfile(profileLockKM);
		List<ProfileLockKM> profilelist = profileLockKmService.getAllProfile();
		req.setAttribute("profilelist", profilelist);
		
		model.setViewName("ProfileLockKM");
		return model;
	}
	
	@RequestMapping(value="/getpgpStudent",method=RequestMethod.POST)
		public ModelAndView getpgpStudent(ModelAndView model,HttpServletRequest request)
		{
		//System.out.println(""+request.getParameter("groupid"));
		int groupid=Integer.parseInt(request.getParameter("groupid"));
		
		List<ProfileLockKM> profilelist = profileLockKmService.getpgpStudent(groupid);
		request.setAttribute("profilelist", profilelist);
		model.setViewName("ProfileLockKM");
		return model;
		
		}
	@RequestMapping(value="/unlockDetails",method=RequestMethod.GET)
	public ModelAndView unlockDetails(ModelAndView model, HttpServletRequest req,ProfileLockKM profileLockKM) {
		SessionBean sbean=(SessionBean)req.getSession().getAttribute("sessionBean");
		//int userId=sbean.getUserID();
		
		System.out.println("profile fff"+profileLockKM.getSelectedIds());
		for(Integer i:profileLockKM.getSelectedIds()){
			//System.out.println("idsssisss--"+i);
			
		}
		profileLockKmService.unlockDetails(profileLockKM);
		List<ProfileLockKM> profilelist = profileLockKmService.getAllProfile();
		req.setAttribute("profilelist", profilelist);
		
		model.setViewName("ProfileLockKM");
		return model;
	}
}


