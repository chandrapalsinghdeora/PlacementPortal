package com.precise.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.precise.model.SessionBean;
import com.precise.model.ShareProfile;
import com.precise.service.ShareProfileService;

@Controller
public class ShareProfileController {

	@Autowired
	ShareProfileService shareProfileService ;
	
	@RequestMapping(value = "/getShareProfile", method = RequestMethod.GET)
	public ModelAndView getAllCompanyDetail(ModelAndView model, HttpServletRequest req) {
		int userId,roleId;
		try
		{
		SessionBean sbean=(SessionBean)req.getSession().getAttribute("sessionBean");
		userId=sbean.getUserID();
		roleId=sbean.getRoleID();
		if(roleId!=1)
		{
			return new ModelAndView("LoginPage");
		}
		}catch(Exception e)
		{
			return new ModelAndView("LoginPage");
		}
		List<ShareProfile> shareProfilelist = shareProfileService.getAllShareProfile();
		req.setAttribute("profilelist", shareProfilelist);
		
		model.setViewName("shareUserProfile");
		return model;
	}
}
