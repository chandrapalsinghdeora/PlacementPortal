package com.precise.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import com.precise.model.Cluster;
import com.precise.model.Cohort;
import com.precise.model.CompanyMaster;
import com.precise.model.FirmManagementForm;
import com.precise.model.ProcessManagement;
import com.precise.model.RoleCompany;
import com.precise.model.SelectionRoundMaster;
import com.precise.model.SessionBean;
import com.precise.model.YearMaster;
import com.precise.service.FirmManagementService;

@Controller
public class FirmManagementFormController {
	
	@Autowired
	FirmManagementService firmManagementService;
	
	@RequestMapping(value = "/getFirmMangement", method = RequestMethod.GET)
	public ModelAndView getCreateFirmForm(ModelAndView model, HttpServletRequest req,HttpServletResponse res) {
		int roleId;
		try
		{
		SessionBean sbean=(SessionBean)req.getSession().getAttribute("sessionBean");
		roleId=sbean.getUserID();
		}catch(Exception e)
		{
			return new ModelAndView("LoginPage");
		}
		List<ProcessManagement> ProcessList = firmManagementService.getAllProcess();
		req.setAttribute("processList", ProcessList);
		List<Cluster> ClusterList = firmManagementService.getAllCluster();
		req.setAttribute("clusterList", ClusterList);		
		/*List<Cohort> CohortList = firmManagementService.getAllCohort();
		req.setAttribute("cohortList", CohortList);*/
		List<RoleCompany> RoleList = firmManagementService.getAllRole();
		req.setAttribute("roleList", RoleList);
		List<CompanyMaster> CompanyList = firmManagementService.getAllCompany();
		req.setAttribute("companyList", CompanyList);
		List<YearMaster> YearMasterList = firmManagementService.getAllYear();
		req.setAttribute("yearMasterList", YearMasterList);
		List<SelectionRoundMaster> SelectionRoundMasterList = firmManagementService.getAllRound();
		req.setAttribute("selectionRoundMasterList", SelectionRoundMasterList);
		model.setViewName("firmManagement");
				
		JSONArray array=new JSONArray();
			for(CompanyMaster cmp:CompanyList){	
					JSONObject jobj=new JSONObject();					
					jobj.put("companyName", cmp.getCompanyName());
					jobj.put("companyId", cmp.getCompanyId());
					array.put(jobj);
			}
			req.setAttribute("compArray", array);
			//System.out.println("json company array"+array.toString());
			JSONArray processArray=new JSONArray();			
				for(ProcessManagement proces:ProcessList){						
						JSONObject jobj=new JSONObject();					
						jobj.put("processName", proces.getProcessName());
						jobj.put("processId", proces.getProcessId());
						processArray.put(jobj);				  
				}			
		req.setAttribute("processArray", processArray);
		//System.out.println("processArray role array"+processArray.toString());
		
       JSONArray clusterArray=new JSONArray();
			for(Cluster cluster:ClusterList){	
					JSONObject jobj=new JSONObject();					
					jobj.put("clusterName", cluster.getClusterName());
					jobj.put("clusterId", cluster.getClusterId());
					clusterArray.put(jobj);
			}
			req.setAttribute("clusterArray", clusterArray);
			
			
			/*JSONArray cohortArray=new JSONArray();
				for(Cohort cohort:CohortList){	
						JSONObject jobj=new JSONObject();					
						jobj.put("cohortName", cohort.getCohortName());
						jobj.put("cohortId", cohort.getCohortId());
						cohortArray.put(jobj);
				}
				req.setAttribute("cohortArray", cohortArray);*/
				//System.out.println("chort array:::"+cohortArray);
		
		
		JSONArray roleArray=new JSONArray();
		for(RoleCompany role:RoleList){	
			JSONObject jobj=new JSONObject();	
			jobj.put("roleCompany", role.getRoleCompany());
			jobj.put("roleCompanyId", role.getRoleCompanyId());
			roleArray.put(jobj);
		}
		req.setAttribute("roleArray", roleArray);
		//System.out.println("json role array"+roleArray.toString());
		
		/*JSONArray yearArray=new JSONArray();
		for(YearMaster yearMaster:YearMasterList){	
			JSONObject jobj=new JSONObject();	
			jobj.put("yearName", yearMaster.getYear());
			jobj.put("yearId", yearMaster.getYearId());
			roleArray.put(jobj);
		}
		req.setAttribute("yearArray", yearArray);
		//System.out.println("json year array"+yearArray.toString());
*/	
		
		return model;
	}
	
	
	@RequestMapping(value = "/getCohortByClusterName", method = RequestMethod.GET)
	public void getCohortNameByCid(Model model, HttpServletRequest req,HttpServletResponse res) throws IOException {
		System.out.println("FirmManagementFormController.getCohortNameByCid()");
		String cname=req.getParameter("cname");
		//System.out.println("cid--"+cid);
		//int clusterId=74;
		List<Cohort> cohortListBycid = firmManagementService.getCohortByClusterId(cname);		
		JSONArray array=new JSONArray();
		
			for(Cohort c:cohortListBycid){					
					JSONObject jobj=new JSONObject();					
					jobj.put("cohortName",c.getCohortName()) ;
					jobj.put("cohortId",c.getCohortId());
					array.put(jobj);
			    }
		//System.out.println("json cohort array"+array.toString());
		//System.out.println("json array length--"+array.length());	
		res.setContentType("application/json");
		res.getWriter().write(array.toString());
			
	}
	
	
	@RequestMapping(value = "/searchCompany", method = RequestMethod.GET)
	public void searchCompany(Model model, HttpServletRequest req,HttpServletResponse res) throws IOException {
		System.out.println("FirmManagementFormController.searchCompany()");
		String compName=req.getParameter("company");
		System.out.println("compName--"+compName);
		List<CompanyMaster> CompanyList=new ArrayList<CompanyMaster>();
		JSONArray array=new JSONArray();
		if(compName!=null&&compName.trim().length()>0){
			for(CompanyMaster cmp:CompanyList){	
				if(cmp.getCompanyName().startsWith(compName)){
					JSONObject jobj=new JSONObject();					
					jobj.put("companyName", cmp.getCompanyName());
					jobj.put("companyId", cmp.getCompanyId());
					array.put(jobj);
			    }
			}
		}
		System.out.println("json company array"+array.toString());
		res.setContentType("application/json");
		res.getWriter().write(array.toString());
		System.out.println(array.length());		
	}

	@RequestMapping(value={"/saveFirmForm"},method={RequestMethod.POST})
	public String saveFirmForm(FirmManagementForm firmManagementForm,HttpServletRequest request){
		 //String shtIds=req.getParameter("shortListIdsSelected");	
		String coverIds=request.getParameter("coverLetterSelected");
		SessionBean sessionBean = (SessionBean) request.getSession()
				.getAttribute("sessionBean");
		int userId=sessionBean.getUserID();
		List<String>coverletterIds=new LinkedList<String>();
		List<Boolean> allCover=new LinkedList<Boolean>();
		if(coverIds==""){
			//System.out.println("not checeked--");
		}else{
		    String[] num=coverIds.split(",");			
			for(String str:num){
				//System.out.println("Cover Letter idss"+str);
				//coverletterIds.add(str);
				allCover.add(Boolean.parseBoolean(str));
			}
		}
		firmManagementForm.setCoverLetter(allCover);
		//System.out.println("cover letter list-"+coverIds+" ::"+coverletterIds);
		String ids="";
		try {
			
			//System.out.println("designation id"+firmManagementForm.getRoleCompanyId());
			//System.out.println("opening date"+firmManagementForm.getCloseingDate());
			
			/*System.out.println("opening date"+firmManagementForm.getOpeningDate());
			java.util.Date date = new java.util.Date();
			date = firmManagementForm.getOpeningDate();
			System.out.println("op date-"+date);
			java.sql.Date od = new java.sql.Date(date.getTime());
			firmManagementForm.setOpeningDate(null);
		
			java.util.Date cdate = new java.util.Date();
            cdate = firmManagementForm.getCloseingDate();
            System.out.println("closdind date "+cdate);
			java.sql.Date cd = new java.sql.Date(cdate.getTime());
			firmManagementForm.setCloseingDate(null);	*/
		//System.out.println("userId"+sessionBean.getUserID());
			List<String> jobDescriptionFilePathList = this.doUpload(request, firmManagementForm,sessionBean.getUserID(),true);
			List<String> additionalFilePathList = this.doUpload(request, firmManagementForm,sessionBean.getUserID(),false);
			if(jobDescriptionFilePathList.size()>0){
				firmManagementForm.setJobDescriptionFilePathList(jobDescriptionFilePathList.get(0));
			}
			if(additionalFilePathList.size()>0)
				firmManagementForm.setAdditionalFilePathList(additionalFilePathList);
			firmManagementForm.setCreatedBy(userId);
			firmManagementService.saveFirmManagementForm(firmManagementForm,sessionBean.getUserID());
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		//System.out.println("request value::"+request.getParameter("daWriteUp"));
	
		
	
		return "redirect:appManage";
	}
	
	public java.sql.Date dateFormatter(Date date) {
		String dateForMySql = "";
		java.sql.Date sqlDate = null;
		try {
			if (date == null) {
				dateForMySql = null;
			} else {
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
						"yyyy-MM-dd");
				dateForMySql = simpleDateFormat.format(date);
				Date stringToDate = simpleDateFormat.parse(dateForMySql);
				sqlDate = new java.sql.Date(stringToDate.getTime());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sqlDate;
	}
	private  List<String> doUpload(HttpServletRequest request, FirmManagementForm fileUploadForm, int userId,boolean flag) {
		 
	      // Root Directory.
	   //   String uploadRootPath = request.getServletContext().getRealPath("saveUserFile");
	   //   System.out.println("uploadRootPath=" + uploadRootPath);
	      String uploadRootPath = request.getServletContext().getInitParameter("saveUserFile");
	     // System.out.println("uploadRootPath=" + uploadRootPath);
	      List<String> uploadedFiles = new ArrayList<String>();
	      File uploadRootDir = new File(uploadRootPath + userId );
	      //
	      // Create directory if it not exists.
	      if (!uploadRootDir.exists()) {
	          uploadRootDir.mkdirs();
	      }
	      List<MultipartFile> fileDatas  = null;
	      if(flag){
	      //
	    	  MultipartFile fileData = fileUploadForm.getJobDescription();
	 
	          // Client File Name
	          String name = fileData.getOriginalFilename();
	          System.out.println("Client File Name = " + name);
	 
	          if (name != null && name.length() > 0) {
	              try {
	                  // Create the file on server
	                  File serverFile = new File(uploadRootDir.getAbsolutePath() + File.separator + name);
	                  // Stream to write data to file in server.
	                  BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
	                  stream.write(fileData.getBytes());
	                  stream.close();
	                  //System.out.println("Write file: " + serverFile.getCanonicalPath()+" :: "+serverFile.getAbsolutePath());
	                  uploadedFiles.add(serverFile.getCanonicalPath());
	                  //System.out.println("Write file: " + serverFile);
	              } catch (Exception e) {
	                  System.out.println("Error Write file: " + name);
	                  e.printStackTrace();
	              }
	          }else{
	        	  uploadedFiles.add(null);
	          }
	      }
	      else {
	       fileDatas = fileUploadForm.getAdditionalFile();//
	      //
	     
	      for (MultipartFile fileData : fileDatas) {
	 
	          // Client File Name
	          String name = fileData.getOriginalFilename();
	          System.out.println("Client File Name = " + name);
	 
	          if (name != null && name.length() > 0) {
	              try {
	                  // Create the file on server
	                  File serverFile = new File(uploadRootDir.getAbsolutePath() + File.separator + name);
	                  // Stream to write data to file in server.
	                  BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
	                  stream.write(fileData.getBytes());
	                  stream.close();
	                 // System.out.println("Write file: " + serverFile.getCanonicalPath()+" :: "+serverFile.getAbsolutePath());
	                  uploadedFiles.add(serverFile.getCanonicalPath());
	                  //System.out.println("Write file: " + serverFile);
	              } catch (Exception e) {
	                  System.out.println("Error Write file: " + name);
	                  e.printStackTrace();
	              }
	          }else{
	        	  uploadedFiles.add(null);
	          }
	      }
	      }
	      return uploadedFiles;
	  }
	
	@RequestMapping(value="/openCompanyEditForm")
	public ModelAndView openEditCompanyForm(HttpServletRequest req,ModelAndView model,FirmManagementForm firmMangForm) throws Exception{
		int roleId;
		try
		{
		SessionBean sbean=(SessionBean)req.getSession().getAttribute("sessionBean");
		roleId=sbean.getUserID();
		}catch(Exception e)
		{
			return new ModelAndView("LoginPage");
		}
		
		String appId=req.getParameter("appId");
		System.out.println("inside openEditCompanyForm method::::"+req.getParameter("appId"));
		List<ProcessManagement> ProcessList = firmManagementService.getAllProcess();
		req.setAttribute("processList", ProcessList);
		List<Cluster> ClusterList = firmManagementService.getAllCluster();
		req.setAttribute("clusterList", ClusterList);		
		List<Cohort> CohortList = firmManagementService.getAllCohort();
		req.setAttribute("cohortList", CohortList);
		List<RoleCompany> RoleList = firmManagementService.getAllRole();
		req.setAttribute("roleList", RoleList);
		List<CompanyMaster> CompanyList = firmManagementService.getAllCompany();
		req.setAttribute("companyList", CompanyList);
		List<YearMaster> YearMasterList = firmManagementService.getAllYear();
		req.setAttribute("yearMasterList", YearMasterList);
		List<SelectionRoundMaster> SelectionRoundMasterList = firmManagementService.getAllRound();
		req.setAttribute("selectionRoundMasterList", SelectionRoundMasterList);
		model.setViewName("EditFirmManagement");
				
		JSONArray array=new JSONArray();
			for(CompanyMaster cmp:CompanyList){	
					JSONObject jobj=new JSONObject();					
					jobj.put("companyName", cmp.getCompanyName());
					jobj.put("companyId", cmp.getCompanyId());
					array.put(jobj);
			}
			req.setAttribute("compArray", array);
			//System.out.println("json company array"+array.toString());
			JSONArray processArray=new JSONArray();			
				for(ProcessManagement proces:ProcessList){						
						JSONObject jobj=new JSONObject();					
						jobj.put("processName", proces.getProcessName());
						jobj.put("processId", proces.getProcessId());
						processArray.put(jobj);				  
				}			
		req.setAttribute("processArray", processArray);
		//System.out.println("processArray role array"+processArray.toString());
		
       JSONArray clusterArray=new JSONArray();
			for(Cluster cluster:ClusterList){	
					JSONObject jobj=new JSONObject();					
					jobj.put("clusterName", cluster.getClusterName());
					jobj.put("clusterId", cluster.getClusterId());
					clusterArray.put(jobj);
			}
			req.setAttribute("clusterArray", clusterArray);
			
			
			JSONArray cohortArray=new JSONArray();
				for(Cohort cohort:CohortList){	
						JSONObject jobj=new JSONObject();					
						jobj.put("cohortName", cohort.getCohortName());
						jobj.put("cohortId", cohort.getCohortId());
						cohortArray.put(jobj);
				}
				req.setAttribute("cohortArray", cohortArray);
		
		
		JSONArray roleArray=new JSONArray();
		for(RoleCompany role:RoleList){	
			JSONObject jobj=new JSONObject();	
			jobj.put("roleCompany", role.getRoleCompany());
			jobj.put("roleCompanyId", role.getRoleCompanyId());
			roleArray.put(jobj);
		}
		req.setAttribute("roleArray", roleArray);
		
		System.out.println("application id in 2 ::"+appId);
		FirmManagementForm firmManagement=firmManagementService.getCompanyDetails(Integer.parseInt(appId),firmMangForm);
		System.out.println("companyName");
		model.addObject("firmMng",firmManagement);
		return model;
	}
	
	@RequestMapping(value="/getCohortValues")
	public void getCohortValues(HttpServletRequest request,HttpServletResponse response){
		try{
			System.out.println("request values::"+request.getParameter("clusterId"));
			PrintWriter pw=response.getWriter();
			pw.print(firmManagementService.getCohortValue(request.getParameter("clusterId")));
			pw.flush();
			pw.close();
		}catch(Exception e){
			System.out.println("Exception in  getCohortValues method::"+e.getMessage());
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="/deleteRoleValue")
	public void deleteRole(HttpServletRequest request,HttpServletResponse response){
		try{
			System.out.println("inside deleteRole method::"+request.getParameter("roleId")+" :: appId::"+request.getParameter("appId"));
			PrintWriter pw=response.getWriter();
			pw.print(firmManagementService.deleteRole(request.getParameter("roleId"),request.getParameter("appId")));
			pw.flush();
			pw.close();
		}catch(Exception e){
			System.out.println("Exception in  deleteRole method::"+e.getMessage());
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="/updateFirmManagement")
	public String updateFirmManagement(HttpServletRequest request,FirmManagementForm firmManagementForm){
		SessionBean sessionBean = (SessionBean) request.getSession()
				.getAttribute("sessionBean");
		//int userId=sessionBean.getUserID();
		//System.out.println("userId from session::");
		List<String> additionalFilePathList = this.doUpload(request, firmManagementForm,sessionBean.getUserID(),false);
		if(additionalFilePathList.size()>0){
		//	System.out.println("inside if condition::");
			firmManagementForm.setAdditionalFilePathList(additionalFilePathList);
		}
		List<String> jobDescriptionFilePathList = this.doUpload(request, firmManagementForm,sessionBean.getUserID(),true);
		if(jobDescriptionFilePathList.size()>0){
			firmManagementForm.setJobDescriptionFilePathList(jobDescriptionFilePathList.get(0));
		}
		
		String coverIds=request.getParameter("coverLetterSelected");	
		List<Boolean> allCover=new LinkedList<Boolean>();
		if(coverIds==""){
			//System.out.println("not checeked--");
		}else{
		    String[] num=coverIds.split(",");			
			for(String str:num){				
				allCover.add(Boolean.parseBoolean(str));
			}
		}
		firmManagementForm.setCoverLetter(allCover);
		firmManagementService.updateFirmManagement(firmManagementForm);
	//	System.out.println("file path list:::"+firmManagementForm.getAdditionalFilePathList().size());
	//	System.out.println(firmManagementForm.getRoleId().toString());
	//	System.out.println("companyName"+firmManagementForm.getCompanyName());
		return "redirect:appManage";
	}
	
	@RequestMapping(value="/deleteUrl")
	public void deleteUrl(HttpServletRequest request,HttpServletResponse response){
		try{
		PrintWriter pw=response.getWriter();
		System.out.println("url Id::"+request.getParameter("urlId"));
		pw.print(firmManagementService.deleteUrl(request.getParameter("urlId")));
		pw.flush();
		pw.close();
		}catch(Exception e){
			System.out.println("Exception in  deleteUrl method::"+e.getMessage());
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="/deleteAddFile")
	public void deleteAdditionalFile(HttpServletRequest request,HttpServletResponse response){
		try{
			PrintWriter pw=response.getWriter();
			System.out.println("addFileId::"+request.getParameter("addFileId"));
			pw.print(firmManagementService.deleteAddFiles(request.getParameter("addFileId")));
			pw.flush();
			pw.close();
			}catch(Exception e){
				System.out.println("Exception in  deleteUrl method::"+e.getMessage());
				e.printStackTrace();
			}
	}
	
	@RequestMapping(value="/checkCompanyClusterCohort")
	public void checkCompanyClusterCohort(HttpServletRequest req,HttpServletResponse res){
		String compName=req.getParameter("compName");
		String cluser=req.getParameter("clusterName");
		int cohortId=Integer.parseInt(req.getParameter("cohortId"));
		int year=Integer.parseInt(req.getParameter("year"));
		System.out.println("compName "+compName+" cluser-"+cluser+" cohortId "+cohortId+"year-"+year);
		Boolean checkComp=firmManagementService.checkCompanyClusterCohort(compName, cluser, cohortId,year);
		System.out.println("checkComp--"+checkComp);
		JSONObject jobj=new JSONObject();					
		jobj.put("checkCompany",checkComp);
		System.out.println("josong object ---"+jobj);
		 try {
				PrintWriter out=res.getWriter();
				out.print(jobj);
				out.flush();
				out.close();
			} catch (IOException e) {				
				e.printStackTrace();
			}		
				
		
		
	}

}
