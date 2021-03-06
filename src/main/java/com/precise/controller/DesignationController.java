package com.precise.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.precise.model.Designation;
import com.precise.model.MailCredential;
import com.precise.model.SessionBean;
import com.precise.service.DesignationService;

@Controller
public class DesignationController {

	@Autowired
	DesignationService designationservice;
	
	@RequestMapping(value = "/emailcredentials", method = RequestMethod.GET)
	public ModelAndView getMailCredentials( HttpServletRequest request) {
		int roleId,userid;
		try
		{
		SessionBean sbean=(SessionBean)request.getSession().getAttribute("sessionBean");
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
		List<MailCredential> designationdata = designationservice.getAllMail();
		request.setAttribute("Designationdata1", designationdata);
		return new ModelAndView("MailCredentials");
		
	}
	
	@RequestMapping(value = "/updateMailingpwd", method = RequestMethod.POST)
	public String Update(MailCredential designation, HttpServletRequest request) {
		SessionBean sessionBean=(SessionBean)request.getSession().getAttribute("sessionBean");
		int userid=sessionBean.getUserID();
		designationservice.updateMailingpwd(userid, designation);
		return "redirect:emailcredentials";
	}
	
	@RequestMapping(value = "/getdesignation", method = RequestMethod.GET)
	public String getCreateIIMStudent(ModelAndView model, HttpServletRequest request) {
		int roleId,userid;
		try
		{
		SessionBean sbean=(SessionBean)request.getSession().getAttribute("sessionBean");
		 userid=sbean.getUserID();
		 roleId=sbean.getRoleID();
		if(roleId!=6 && roleId!=9)
		{
			return "redirect:login";
		}
		}catch(Exception e)
		{
			return "redirect:login";
		}
		List<Designation> designationdata = designationservice.getAllDesignation();
		request.setAttribute("Designationdata", designationdata);
		return "AddDesignation";
		
	}
	
	@RequestMapping(value={"/saveDesignation"},method={RequestMethod.POST})
	public String saveIIMStudent(Designation designation,HttpServletRequest request) {
		
		SessionBean sessionBean = (SessionBean) request.getSession()
				.getAttribute("sessionBean");
		int userid=sessionBean.getUserID();
		try {
			if(designation!=null){
				designation.setCreatedid(userid);
			//	List<KmIIMStudent> iimstudentdata = kmiimstudentservice.getAllIIMStudent();
			//	request.setAttribute("IIMStudent", iimstudentdata);
				if(designation.getDesignationid()==0){
					designationservice.saveDesignation(designation);
				}else{
					System.out.println("update designation id :: "+designation.getDesignationid());
					designationservice.editDesignation(designation);
					
				}
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		 return "redirect:getdesignation";
	}
	
	@RequestMapping(value="/deleteDesignation",method={ RequestMethod.POST})
	public String deletekmiimstData(HttpServletRequest request){
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
		Designation designation = new Designation();
		designation.setCreatedid(userid);
		designation.setDesignationid(Integer.parseInt(request.getParameter("deleteDesignationId")));
		designationservice.deleteDesignationData(designation);
		return "redirect:getdesignation";
	}
	
	@RequestMapping(value="/getDesignationList")
	public void getDesignationLsist(HttpServletResponse response) throws IOException{
		PrintWriter pw=response.getWriter();
		pw.print(designationservice.getAllDesignationData());
		pw.flush();
		pw.close();
	}
	
	@RequestMapping(value="/editDesignationRole")
	public void editDesignation(Designation designation,HttpServletRequest request) throws SQLException{
		System.out.println("designation name::"+designation.getDesignationname());
		System.out.println("designation description::"+designation.getDesignationdescription());
		System.out.println("companyRoleId::"+request.getParameter("compnayRolePkId"));
		designationservice.updateDesignation(designation,request.getParameter("compnayRolePkId"));
	}
	
	
	
}
