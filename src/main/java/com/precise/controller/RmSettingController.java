package com.precise.controller;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.precise.model.RmSetting;
import com.precise.model.SessionBean;
import com.precise.service.RmSettingService;

@Controller
public class RmSettingController {

	@Autowired
	RmSettingService rmsettingservice;
	
	@RequestMapping(value = "/getsetting", method = RequestMethod.GET)
	public String getSetting(ModelAndView model, HttpServletRequest request) {
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
		List<RmSetting> rmsetting = rmsettingservice.getAllSetting();
		/*request.setAttribute("IIMStudent", rmsetting);*/
		request.setAttribute("RmSetting", rmsetting);
		return "RmSetting";
		
	}
	
	@RequestMapping(value={"/saveSetting"},method={RequestMethod.POST})
	public String saveIIMStudent(RmSetting rmsetting,HttpServletRequest request) {
		
		SessionBean sessionBean = (SessionBean) request.getSession()
				.getAttribute("sessionBean");
		int userid=sessionBean.getUserID();
		try {
			if(rmsetting!=null){
				rmsetting.setCreatedId(userid);
				System.out.println("update kmiimst id :: "+rmsetting.getSettingId());
				rmsettingservice.editSetting(rmsetting);
				
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		 return "redirect:getsetting";
	}


}
