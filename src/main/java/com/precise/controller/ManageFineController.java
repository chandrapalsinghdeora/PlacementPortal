package com.precise.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.precise.model.Fine;
import com.precise.model.SessionBean;
import com.precise.model.kmVerificationForm;
import com.precise.service.ManageFineService;

@Controller
public class ManageFineController {

	@Autowired
	ManageFineService manageFineService;
	
	@RequestMapping(value ="/getAllFineDetails",method = RequestMethod.GET)
	public ModelAndView getAllFineDetails(ModelAndView model,HttpServletRequest req){
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
		/*SessionBean sessionBean = (SessionBean) req.getSession().getAttribute("sessionBean");
		int userid = sessionBean.getUserID();
		int roleId = sessionBean.getRmRoleID();*/
		List<Fine> FineDetaillist = manageFineService.getAllFineDetail();
		System.out.println("FineDetaillist :"+FineDetaillist.size());
		req.setAttribute("fineDetails", FineDetaillist);
		
		model.setViewName("manageFine");
		return model;
		
	}
	
	@RequestMapping(value = "/updateFineStatus", method = RequestMethod.POST)
	public String updateFineStatus(Fine fine, HttpServletRequest request) {
		
		System.out.println("inside controller ::");
		//int kmId =  request.getParameter("kmId")==null?0:Integer.parseInt(request.getParameter("kmId"));
		int fineValue =request.getParameter("fineValue")==null?0:Integer.parseInt(request.getParameter("fineValue"));
		int fineId =request.getParameter("fineId")==null?0:Integer.parseInt(request.getParameter("fineId"));
		
		System.out.println("value for the fineId and fineValue is : " + fineId+ " "+fineValue);
		/*SessionBean sessionBean = (SessionBean)request.getSession().getAttribute("sessionBean");
		int userId = sessionBean.getUserID();*/
		
		manageFineService.updateFineStatus(fineValue,fineId);
		return "redirect:getAllFineDetails";
	}
}
