package com.precise.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.precise.model.Cluster;
import com.precise.model.Cohort;
import com.precise.model.CompanyMaster;
import com.precise.model.Designation;
import com.precise.model.ManageApp;
import com.precise.model.ProcessManagement;
import com.precise.model.RoleCompany;
import com.precise.model.SelectionRoundMaster;
import com.precise.model.SessionBean;
import com.precise.model.YearMaster;
import com.precise.service.ManageAppService;

@Controller
public class ManageAppController {
	@Autowired
	ManageAppService manageAppService;

	@RequestMapping(value = "/appManage", method = RequestMethod.GET)
	public ModelAndView getApplication(HttpSession session) {
		int roleId,userid;
		try
		{
			SessionBean sbean=(SessionBean)session.getAttribute("sessionBean");
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
		//System.out.println("roleId"+sb.getUserID());
		List<ManageApp> allManageAppData = manageAppService.getApplication(userid);
		return new ModelAndView("manageApp", "manageApp", allManageAppData);
	}

	@RequestMapping(value="/getRMList")
	public void getDesignationLsist(HttpServletResponse response) throws IOException{
		PrintWriter pw=response.getWriter();
		pw.print(manageAppService.getAllRMData());
		pw.flush();
		pw.close();
	}
	
	@RequestMapping(value="/shareRoleWith")
	public String editDesignation(ManageApp manageApp,HttpServletRequest request) throws SQLException{
		System.out.println("app id::"+request.getParameter("appShareid"));
		System.out.println("user id::"+request.getParameter("rmlist"));
		//System.out.println("designation description::"+manageApp.get);
		//System.out.println("companyRoleId::"+request.getParameter("compnayRolePkId"));
		manageAppService.updateShareWith(manageApp,request.getParameter("appShareid"),request.getParameter("rmlist"));
		return "redirect:appManage"; 
	}
	
	
	@RequestMapping(value="/updatePanelMailStatus", method=RequestMethod.POST )
	public @ResponseBody String updatePanelMailStatus(HttpSession session, ManageApp manageApp,HttpServletRequest request){
		int mailStatus = manageApp.getPanelStatus();
		int appid = manageApp.getAppid();
		SessionBean sbean = (SessionBean) session.getAttribute("sessionBean");
		int userId =  sbean.getUserID();
		manageApp.setUserId(userId);
		System.out.println("mailStatus : "+mailStatus+" appID : "+appid+" userId : "+userId);
		String result = manageAppService.updatePanelMailStatus(manageApp);
		//return "redirect:appManage"; 
		return result;
	}
	
}
