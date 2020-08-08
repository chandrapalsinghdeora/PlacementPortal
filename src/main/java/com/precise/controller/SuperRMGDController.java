package com.precise.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.precise.model.CompanyMaster;
import com.precise.model.HotList;
import com.precise.model.ManageApp;
import com.precise.model.SessionBean;
import com.precise.model.ShortList;
import com.precise.service.CompanyMasterService;
import com.precise.service.HotListService;
import com.precise.service.ManageAppService;
import com.precise.service.RMService;
import com.precise.service.SuperRMGDService;
import com.precise.service.SuperRMService;

@Controller
public class SuperRMGDController {
	 @Autowired
	 SuperRMGDService superRMSGDervice;
     @Autowired
 	 CompanyMasterService companyMasterService;
     @Autowired    
 	 ManageAppService manageAppService;
     @Autowired
     RMService rmService;
     @Autowired
     HotListService hotListservice;
  
     
     @RequestMapping(value = "/getSuperRMGDPage")
  	public ModelAndView getSuperRMPage(HttpServletRequest req,HttpSession session) {
  		System.out.println("SuperRMController.getSuperRMPage()");
  		SessionBean sb=(SessionBean)session.getAttribute("sessionBean");
 		sb.getUserID();
  		List<CompanyMaster> CompanyList = companyMasterService.getAllCompany();
  		List<ManageApp> allManageAppData = manageAppService.getApplication(sb.getUserID());
 		req.setAttribute("manageApp", allManageAppData);
  		return new ModelAndView("superRMGD","companyList",CompanyList);
  	}
      @RequestMapping(value = "/getSuperRMGDPage2")
   	public ModelAndView getSuperRMPage2(HttpServletRequest req,HttpSession session) {
   		System.out.println("SuperRMController.getSuperRMPage()");
   		SessionBean sb=(SessionBean)session.getAttribute("sessionBean");
  		sb.getUserID();
   		List<CompanyMaster> CompanyList = companyMasterService.getAllCompany();
   		List<ManageApp> allManageAppData = manageAppService.getApplication(sb.getUserID());
  		req.setAttribute("manageApp", allManageAppData);
   		return new ModelAndView("superRMGD2","companyList",CompanyList);
   	}
     
    /* @RequestMapping(value = "/getRoleByCompanyId")
    	public void getRoleByCompanyId(HttpServletRequest req,HttpServletResponse res) {
     	int compId=Integer.parseInt(req.getParameter("compId"));
     	JSONArray compRoleList= superRMSGDervice.getRoleByCompanyId(compId);
     	System.out.println("role list in super rm-"+compId+"data-"+compRoleList);
     	try {
 			PrintWriter out=res.getWriter();
 			out.print(compRoleList);
 			out.flush();
 			out.close();
 		} catch (IOException e) {			
 			e.printStackTrace();
 		}
     }*/
   /*  
     @RequestMapping(value = "/getManageAppDetailsByCompId")
    	public void getManageAppDetailsByCompId(HttpServletRequest req,HttpServletResponse res) {
     	int compId=Integer.parseInt(req.getParameter("compId"));
     	JSONArray manageAppList= superRMSGDervice.getManageAppByCompanyId(compId);
     	System.out.println("getManageAppDetailsByCompId in super rm-"+compId+"data-"+manageAppList);
     	try {
 			PrintWriter out=res.getWriter();
 			out.print(manageAppList);
 			out.flush();
 			out.close();
 		} catch (IOException e) {			
 			e.printStackTrace();
 		}
     }
     */
     @RequestMapping(value = "/saveShortlistedBySuperRMGD")
 	   public String saveShortlistedBySuperRM1(HttpServletRequest req,ShortList shortlist) throws SQLException {
 	    System.out.println("SuperRMController.saveShortlistedBySuperRM1()--"+shortlist.getShortListIdsSelected()+"not selected-"+shortlist.getNotSelected());
 	    String shtIds=req.getParameter("shortListIdsSelected");		
 	    SessionBean sessionBean = (SessionBean) req.getSession().getAttribute("sessionBean");
 	    List<Integer> shorlistedIds=new ArrayList<Integer>();
 	    if(shtIds==""){
 			System.out.println("not checeked--");
 		}else{
 		    String[] num=shtIds.split(",");			
 			for(String str:num){
 				System.out.println("shortlisted idss"+str);
 				shorlistedIds.add(Integer.parseInt(str));
 			}
 		}
 		
 		String notShortListIds=req.getParameter("notShortlisted");
 		List<Integer> notselecteddIds=new ArrayList<Integer>();
 		if(notShortListIds==""){
 				System.out.println(" notShortListIds not checeked--");
 		}
 		else{
 			String[] notshort=notShortListIds.split(",");		
 			for(String ns:notshort){
 				System.out.println("shortlisted idss"+ns);
 				notselecteddIds.add(Integer.parseInt(ns));
 			}
 		}
 		shortlist.setNotSelected(notselecteddIds);
 		shortlist.setShortListIdslist(shorlistedIds);
 		shortlist.setCreatedBy(sessionBean.getUserID());
 		//rmService.shortlistRelease(shortlist);	
 		superRMSGDervice.saveShortlistedBySuperRm1(shortlist);
 		System.out.println("shorlistedIds list-"+shorlistedIds+"contrats--"+shortlist.getGreetings()+"shorlistedIds.size():::"+shorlistedIds.size());
 		String ids="";
 		/*for(int i=0;i<shorlistedIds.size();i++){
 			rmService.selectUsersInformation(shorlistedIds.get(i),shortlist.getGreetings(),sessionBean.getUserID());
 			
 		}*/
 		
 		return "redirect:getAllCloseStatusDetailsForSuperRMGD"; //redirect:getshortlistReceivePage
 	 }
     
     @RequestMapping(value = "/saveShortlistedBySuperRMGD2")
 	   public String saveShortlistedBySuperRM2(HttpServletRequest req,ShortList shortlist) throws SQLException {
 	    System.out.println("SuperRMController.saveShortlistedBySuperRM2()--"+shortlist.getShortListIdsSelected()+"not selected-"+shortlist.getNotSelected());
 	    String shtIds=req.getParameter("shortListIdsSelected");		
 	    SessionBean sessionBean = (SessionBean) req.getSession().getAttribute("sessionBean");
 	    List<Integer> shorlistedIds=new ArrayList<Integer>();
 	    if(shtIds==""){
 			System.out.println("not checeked--");
 		}else{
 		    String[] num=shtIds.split(",");			
 			for(String str:num){
 				System.out.println("shortlisted idss"+str);
 				shorlistedIds.add(Integer.parseInt(str));
 			}
 		}
 		
 		String notShortListIds=req.getParameter("notShortlisted");
 		List<Integer> notselecteddIds=new ArrayList<Integer>();
 		if(notShortListIds==""){
 				System.out.println(" notShortListIds not checeked--");
 		}
 		else{
 			String[] notshort=notShortListIds.split(",");		
 			for(String ns:notshort){
 				System.out.println("shortlisted idss"+ns);
 				notselecteddIds.add(Integer.parseInt(ns));
 			}
 		}
 		shortlist.setNotSelected(notselecteddIds);
 		shortlist.setShortListIdslist(shorlistedIds);
 		shortlist.setCreatedBy(sessionBean.getUserID());
 		//rmService.shortlistRelease(shortlist);	
 		superRMSGDervice.saveShortlistedBySuperRm2(shortlist);
 		System.out.println("shorlistedIds list-"+shorlistedIds+"contrats--"+shortlist.getGreetings()+"shorlistedIds.size():::"+shorlistedIds.size());
 		String ids="";
 		/*for(int i=0;i<shorlistedIds.size();i++){
 			rmService.selectUsersInformation(shorlistedIds.get(i),shortlist.getGreetings(),sessionBean.getUserID());
 			
 		}*/
 		
 		return "redirect:getAllCloseStatusDetailsForSuperRMGD2"; //redirect:getshortlistReceivePage
 	 }
     
     
     @RequestMapping(value = "/saveHotlistedBySuperRMGD")
 	   public String saveHotlistedBySuperRM1(HttpServletRequest req,HotList hotlist,HttpSession session) throws SQLException {
 	    //System.out.println("RMController.saveSchedule()--"+hotlist.getHotListIdsSelected());
 	    SessionBean sessionBean=(SessionBean)session.getAttribute("sessionBean");
 	    String shtIds=req.getParameter("hotListIdsSelected");		
 	    System.out.println("shtIds::::"+shtIds);
 	    List<Integer> shorlistedIds=new ArrayList<Integer>();	    
 	    if(shtIds==""){
 			System.out.println("not checeked--");
 		}else{
 		    String[] num=shtIds.split(",");			
 			for(String str:num){
 				//System.out.println("hottlisted idss"+str);
 				shorlistedIds.add(Integer.parseInt(str));
 			}
 		}
 	    		
 		String notShortListIds=req.getParameter("notShortlisted");
 		List<Integer> notselecteddIds=new ArrayList<Integer>();
 		if(notShortListIds==""){
 				System.out.println(" notHotListIds not checeked--");
 		}
 		else{
 			String[] notshort=notShortListIds.split(",");		
 			for(String ns:notshort){				
 				notselecteddIds.add(Integer.parseInt(ns));
 			}
 		}
 		hotlist.setCreatedBy(sessionBean.getUserID());
 		hotlist.setNotSelected(notselecteddIds);		
 		hotlist.setHotListIdslist(shorlistedIds);
 		superRMSGDervice.saveHotlistedBySuperRM1(hotlist);	
 		for(int i=0;i<shorlistedIds.size();i++){			
 			hotListservice.selectUsersInformationForHotList(shorlistedIds.get(i),hotlist.getGreetingsHotlist(),sessionBean.getUserID(),"shortlist@iima.ac.in");
 			
 		}
 		//return "redirect:gethotlistReceivePage";
 		return "redirect:getAllCloseStatusDetailsForSuperRMGD";
 	 }
     
     @RequestMapping(value = "/saveHotlistedBySuperRMGD2")
 	   public String saveHotlistedBySuperRM2(HttpServletRequest req,HotList hotlist,HttpSession session) throws SQLException {
 	    //System.out.println("RMController.saveSchedule()--"+hotlist.getHotListIdsSelected());
 	    SessionBean sessionBean=(SessionBean)session.getAttribute("sessionBean");
 	    String shtIds=req.getParameter("hotListIdsSelected");		
 	    System.out.println("shtIds::::"+shtIds);
 	    List<Integer> shorlistedIds=new ArrayList<Integer>();	    
 	    if(shtIds==""){
 			//System.out.println("not checeked--");
 		}else{
 		    String[] num=shtIds.split(",");			
 			for(String str:num){
 				//System.out.println("hottlisted idss"+str);
 				shorlistedIds.add(Integer.parseInt(str));
 			}
 		}
 	    		
 		String notShortListIds=req.getParameter("notShortlisted");
 		List<Integer> notselecteddIds=new ArrayList<Integer>();
 		if(notShortListIds==""){
 				System.out.println(" notHotListIds not checeked--");
 		}
 		else{
 			String[] notshort=notShortListIds.split(",");		
 			for(String ns:notshort){				
 				notselecteddIds.add(Integer.parseInt(ns));
 			}
 		}
 		hotlist.setCreatedBy(sessionBean.getUserID());
 		hotlist.setNotSelected(notselecteddIds);		
 		hotlist.setHotListIdslist(shorlistedIds);
 		superRMSGDervice.saveHotlistedBySuperRM2(hotlist);	
 		for(int i=0;i<shorlistedIds.size();i++){			
 			hotListservice.selectUsersInformationForHotList(shorlistedIds.get(i),hotlist.getGreetingsHotlist(),sessionBean.getUserID(),"shortlist@iima.ac.in");
 			
 		}
 		//return "redirect:gethotlistReceivePage";
 		return "redirect:getAllCloseStatusDetailsForSuperRMGD2";
 	 }
     
     @RequestMapping(value = "/removeShortlistedBySuperRMGD1")
	   public String removeShortlistedBySuperRMGD1(HttpServletRequest req,ShortList shortlist) throws SQLException {
	    System.out.println("SuperRMController.saveShortlistedBySuperRM1()--"+shortlist.getShortListIdsSelected()+"not selected-"+shortlist.getNotSelected());
	    String shtIds=req.getParameter("shortListIdsSelected");		
	    SessionBean sessionBean = (SessionBean) req.getSession().getAttribute("sessionBean");
	    List<Integer> shorlistedIds=new ArrayList<Integer>();
	    if(shtIds==""){
			System.out.println("not checeked--");
		}else{
		    String[] num=shtIds.split(",");			
			for(String str:num){
				System.out.println("shortlisted idss"+str);
				shorlistedIds.add(Integer.parseInt(str));
			}
		}
		
		String notShortListIds=req.getParameter("notShortlisted");
		List<Integer> notselecteddIds=new ArrayList<Integer>();
		if(notShortListIds==""){
				System.out.println(" notShortListIds not checeked--");
		}
		else{
			String[] notshort=notShortListIds.split(",");		
			for(String ns:notshort){
				System.out.println("shortlisted idss"+ns);
				notselecteddIds.add(Integer.parseInt(ns));
			}
		}
		shortlist.setNotSelected(notselecteddIds);
		shortlist.setShortListIdslist(shorlistedIds);
		shortlist.setCreatedBy(sessionBean.getUserID());
		//rmService.shortlistRelease(shortlist);	
		superRMSGDervice.removeShortlistedBySuperRMGD1(shortlist);
		System.out.println("shorlistedIds list-"+shorlistedIds+"contrats--"+shortlist.getGreetings()+"shorlistedIds.size():::"+shorlistedIds.size());
		String ids="";
		/*for(int i=0;i<shorlistedIds.size();i++){
			rmService.selectUsersInformation(shorlistedIds.get(i),shortlist.getGreetings(),sessionBean.getUserID());
			
		}*/
		
		return "redirect:getAllCloseStatusDetailsForSuperRMGD"; //redirect:getshortlistReceivePage
	 }
     
     @RequestMapping(value = "/removeShortlistedBySuperRMGD2")
	   public String removeShortlistedBySuperRMGD2(HttpServletRequest req,ShortList shortlist) throws SQLException {
	    System.out.println("SuperRMController.saveShortlistedBySuperRM2()--"+shortlist.getShortListIdsSelected()+"not selected-"+shortlist.getNotSelected());
	    String shtIds=req.getParameter("shortListIdsSelected");		
	    SessionBean sessionBean = (SessionBean) req.getSession().getAttribute("sessionBean");
	    List<Integer> shorlistedIds=new ArrayList<Integer>();
	    if(shtIds==""){
			System.out.println("not checeked--");
		}else{
		    String[] num=shtIds.split(",");			
			for(String str:num){
				System.out.println("shortlisted idss"+str);
				shorlistedIds.add(Integer.parseInt(str));
			}
		}
		
		String notShortListIds=req.getParameter("notShortlisted");
		List<Integer> notselecteddIds=new ArrayList<Integer>();
		if(notShortListIds==""){
				System.out.println(" notShortListIds not checeked--");
		}
		else{
			String[] notshort=notShortListIds.split(",");		
			for(String ns:notshort){
				System.out.println("shortlisted idss"+ns);
				notselecteddIds.add(Integer.parseInt(ns));
			}
		}
		shortlist.setNotSelected(notselecteddIds);
		shortlist.setShortListIdslist(shorlistedIds);
		shortlist.setCreatedBy(sessionBean.getUserID());
		//rmService.shortlistRelease(shortlist);	
		superRMSGDervice.removeShortlistedBySuperRMGD2(shortlist);
		System.out.println("shorlistedIds list-"+shorlistedIds+"contrats--"+shortlist.getGreetings()+"shorlistedIds.size():::"+shorlistedIds.size());
		String ids="";
		/*for(int i=0;i<shorlistedIds.size();i++){
			rmService.selectUsersInformation(shorlistedIds.get(i),shortlist.getGreetings(),sessionBean.getUserID());
			
		}*/
		
		return "redirect:getAllCloseStatusDetailsForSuperRMGD2"; //redirect:getshortlistReceivePage
	 }
}
