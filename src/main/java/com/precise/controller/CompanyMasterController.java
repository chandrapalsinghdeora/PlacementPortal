package com.precise.controller;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.precise.model.Cluster;
import com.precise.model.CompanyMaster;
import com.precise.model.SessionBean;
import com.precise.service.CompanyMasterService;

@Controller
public class CompanyMasterController {
	
	@Autowired
	CompanyMasterService companyMasterService;
	
	@RequestMapping(value={"/getCompanyMaster"},method={RequestMethod.GET})
	public String getCompanyMaster(CompanyMaster companyMaster,HttpServletRequest request) {
		int roleId,userid;
		try
		{
		SessionBean sbean=(SessionBean)request.getSession().getAttribute("sessionBean");
		userid=sbean.getUserID();
		roleId=sbean.getRoleID();
		if(roleId!=6 && roleId!=4 && roleId!=9 )
		{
			return "redirect:login";
		}
		}catch(Exception e)
		{
			return "redirect:login";
		}
		List<CompanyMaster> CompanyList = companyMasterService.getAllCompany();
		request.setAttribute("companyList", CompanyList);
		return "CompanyMaster";
	}
	
	@RequestMapping(value={"/getCompanyMaster"},method={RequestMethod.POST})
	public String submitCompany(CompanyMaster companyMaster,HttpServletRequest request) {
		int roleId,userid;
		try
		{
		SessionBean sbean=(SessionBean)request.getSession().getAttribute("sessionBean");
		 userid=sbean.getUserID();
		roleId=sbean.getRoleID();
		if(roleId!=6 && roleId!=4)
		{
			return "redirect:login";
		}
		}catch(Exception e)
		{
			return "redirect:login";
		}
		
		try {
			if(companyMaster!=null){
				companyMaster.setCreatedBy(userid);
				List<CompanyMaster> CompanyList = companyMasterService.getAllCompany();
				request.setAttribute("companyList", CompanyList);
				if(companyMaster.getCompanyId()==0){
					companyMasterService.submitCompany(companyMaster);
				}else{
					companyMasterService.editCompanyMasterForm(companyMaster);
					//System.out.println("update cluster id :: "+cluster.getClusterId());
				}
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		 return "redirect:getFirmMangement";
	}
	
	@RequestMapping(value="/deleteCompany",method={ RequestMethod.POST})
	public String deleteCompanyMasterData(HttpServletRequest request){
		int roleId,userid;
		try
		{
		SessionBean sbean=(SessionBean)request.getSession().getAttribute("sessionBean");
		 userid=sbean.getUserID();
		 roleId=sbean.getRoleID();
		if(roleId!=6)
		{
			return "redirect:login";
		}
		}catch(Exception e)
		{
			return "redirect:login";
		}
		
		CompanyMaster companyMaster = new CompanyMaster();
		companyMaster.setCreatedBy(userid);
		companyMaster.setCompanyId(Integer.parseInt(request.getParameter("deleteCompanyId")));
		companyMasterService.deleteCompanyMasterData(companyMaster);
		return "redirect:getCompanyMaster";
	}
}
