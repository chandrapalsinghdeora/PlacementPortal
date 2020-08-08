package com.precise.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.precise.model.AssignRole;
import com.precise.model.SessionBean;
import com.precise.service.AssignRoleService;

@Controller
public class SchedulingAdministratorController {
	@Autowired
	AssignRoleService assignRoleService;   
	
	@RequestMapping(value = { "/getScheduleAdminPage" }, method = { RequestMethod.GET})
	public String getSchedulingAdministratorPage(HttpServletRequest req, ModelMap model) {
		int roleId,userid;
		try
		{
		SessionBean sbean=(SessionBean)req.getSession().getAttribute("sessionBean");
		userid=sbean.getUserID();
		roleId=sbean.getRoleID();
		System.out.println("csd::"+roleId);
		if(roleId!=21)
		{
			return "redirect:login";
		}
		}catch(Exception e)
		{
			return "redirect:login";
		}
		System.out.println("SchedulingAdministratorController.getSchedulingAdministratorPage()");
		List<AssignRole> getRole=assignRoleService.getAssignRoles();
		req.setAttribute("assignRole", getRole);
		return "scheduleAdministratorPage";
		
	}
	
	@RequestMapping(value = { "/getDynamicAssignRole" }, method = { RequestMethod.GET})
	public String getDynamicAssignRole(HttpServletRequest req, ModelMap model) {
		try
		{
		SessionBean sbean=(SessionBean)req.getSession().getAttribute("sessionBean");
		int receiverId=sbean.getUserID();
		}catch(Exception e)
		{
			return "redirect:login";
		}
		System.out.println("SchedulingAdministratorController.getSchedulingAdministratorPage()");
		List<AssignRole> getRole=assignRoleService.getAssignRoles();
		req.setAttribute("assignRole", getRole);
		return "assignDynamicRole";
		
	}
	
}
