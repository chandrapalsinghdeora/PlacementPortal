package com.precise.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.precise.model.Fine;
import com.precise.model.Label;
import com.precise.model.SessionBean;
import com.precise.service.FineService;
import com.precise.service.InboxService;

@Controller
public class FineController {

	
	@Autowired
	FineService fineService;
	@Autowired
	InboxService iservice;
	
	@RequestMapping(value ="/getAllFine",method = RequestMethod.GET)
	public ModelAndView getAllFineDetails(ModelAndView model,HttpServletRequest req){
		int roleId,userid,roleid;
		try
		{
			
		SessionBean sessionBean=(SessionBean)req.getSession().getAttribute("sessionBean");
        userid = sessionBean.getUserID();
		
		 roleId = sessionBean.getRmRoleID(); 
		 roleid=sessionBean.getRoleID();
		if(roleid!=1)
		{
			return new ModelAndView("LoginPage");
		}
		}catch(Exception e)
		{
			return new ModelAndView("LoginPage");
		}
		
		List<Fine> FineDetaillist = fineService.getAllFineDetail(userid, roleId);
		System.out.println("FineDetaillist :"+FineDetaillist.size());
		req.setAttribute("fineDetails", FineDetaillist);
		Map<Integer,Label> getLabel=iservice.getLabelByUserId(userid);
		req.setAttribute("label",getLabel);
		model.setViewName("Fine");
		return model;
		
	}
	

	
	@RequestMapping(value = "/addfineValues", method = RequestMethod.GET)
	public String addfineValues(Fine fine,HttpServletRequest request) {
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
		//request.getParameter("forumId::"+request.getParameter("forumId"));
		//System.out.println("ForumId value in updateForumValues method::" + forum.getForumId());
		//System.out.println("ForumId value in updateForumValues method::" + forum.getEditPermission());
		//fineService.addfineValues(fine);
		return "addFine";
	}
	
	@RequestMapping(value = "/addFine", method = RequestMethod.POST)
	public String fineAdd(Fine fineBean,HttpServletRequest request,ModelMap modelMap){
		
		SessionBean sessionBean = (SessionBean) request.getSession().getAttribute("sessionBean");
		int userid = sessionBean.getUserID();
		int roleid=sessionBean.getRoleID();
		System.out.println(fineBean.getType());
		System.out.println(fineBean.getEvent());
		System.out.println(fineBean.getFineReason());
		System.out.println(fineBean.getAmount());
		System.out.println(fineBean.getIndividual());
		fineBean.setRoleId(roleid);
		fineService.fineAdd(fineBean,userid);
		
		modelMap.addAttribute("returnValue", "success");
		return "addFine";
	}
	
}
