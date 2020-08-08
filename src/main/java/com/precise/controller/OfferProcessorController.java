package com.precise.controller;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.precise.model.OfferProcessor;
import com.precise.model.SessionBean;
import com.precise.service.OfferProcessorService;

@Controller
public class OfferProcessorController {
	@Autowired
	OfferProcessorService offerProcessorService;
	
	@RequestMapping(value = "/getOfferProcessor", method = RequestMethod.GET)
	public ModelAndView getOfferProcessor(ModelAndView model, HttpServletRequest req,HttpSession session) {
		int roleId,userid;
		try
		{
		SessionBean sbean=(SessionBean)req.getSession().getAttribute("sessionBean");
		userid=sbean.getUserID();
		roleId=sbean.getRoleID();
		if(roleId!=8)
		{
			return new ModelAndView("LoginPage");
		}
		}catch(Exception e)
		{
			return new ModelAndView("LoginPage");
		}
		List<OfferProcessor> offeredDetails = offerProcessorService.getOfferedStudent();
		List<OfferProcessor> awaitingInterview = offerProcessorService.getAwaitingStudent();
		List<OfferProcessor> summary = offerProcessorService.getSummary();
		List<OfferProcessor> offerRejects = offerProcessorService.getOfferRejects();
		List<OfferProcessor> awaitingOffer = offerProcessorService.getAwaitingOffer();
		List<OfferProcessor> firmlist = offerProcessorService.getFirmList();
		req.setAttribute("firmlist", firmlist);
		req.setAttribute("offeredDetails", offeredDetails);
		req.setAttribute("awaitingInterview", awaitingInterview);
		req.setAttribute("summary", summary);
		req.setAttribute("offerRejects", offerRejects);
		req.setAttribute("awaitingOffer", awaitingOffer);
		model.setViewName("OfferProcessor");
		return model;
		
	}

	
	@RequestMapping(value = "/getOpRoleName")
	public void getOpRoleName(HttpServletRequest req,HttpServletResponse res) throws Exception {
		int cmpid=Integer.parseInt(req.getParameter("cmpId"));
		PrintWriter pw=res.getWriter();
		pw.print(offerProcessorService.getOpRoleName(cmpid));
		pw.flush();
		pw.close();
	}
	
	
	
	@RequestMapping(value = "/getOpStudentName")
	public void getOpStudentName(HttpServletRequest req,HttpServletResponse res) throws Exception {
		int roleid=Integer.parseInt(req.getParameter("roleId"));
		PrintWriter pw=res.getWriter();
		pw.print(offerProcessorService.getOpStudentName(roleid));
		pw.flush();
		pw.close();
	}
	
	@RequestMapping(value = "/getOpStudentStatus")
	public void getOpStudentStatus(HttpServletRequest req,HttpServletResponse res) throws Exception {
		int roleid=Integer.parseInt(req.getParameter("roleId"));
		int stdno=Integer.parseInt(req.getParameter("stdno"));
		PrintWriter pw=res.getWriter();
		pw.print(offerProcessorService.getOpStudentStatus(roleid,stdno));
		pw.flush();
		pw.close();
	}
	
	@RequestMapping(value = "/plcstatusupdate")
	public @ResponseBody String plcstatusupdate(HttpServletRequest req,HttpServletResponse res,OfferProcessor op) throws Exception {
		offerProcessorService.plcstatusupdate(op);
		return "success";
	}
	

}
