package com.precise.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.precise.fileManager.FileUtil;
import com.precise.model.Cluster;
import com.precise.model.Cohort;
import com.precise.model.SessionBean;
import com.precise.model.kmVerificationForm;
import com.precise.service.ClusterService;
import com.precise.service.CohortService;
import com.precise.service.KmVerificationService;

@Controller
public class KmVerification {

	@Autowired
    KmVerificationService kmVerificationService;
	
	@Autowired
	CohortService cohortService;
	
	@Autowired
	ClusterService clusterService;
	
	@RequestMapping(value = "/getkmverification", method = RequestMethod.GET)
	public ModelAndView getCreateKmVerificationPage(ModelAndView model, HttpServletRequest req) {
		int roleId,userid;
		try
		{
		SessionBean sbean=(SessionBean)req.getSession().getAttribute("sessionBean");
		 userid=sbean.getUserID();
		 roleId=sbean.getRoleID();
		if(roleId!=6  && roleId!=9)
		{
			return new ModelAndView("LoginPage"); 
		}
		}catch(Exception e)
		{
			return new ModelAndView("LoginPage"); 
		}
		ModelAndView model1=new ModelAndView("kmVerificationForm");
		List<kmVerificationForm> kmdata = kmVerificationService.getAllKm();
		List<Cohort> CohortList = cohortService.getAllCohort();
		List<Cluster> ClusterList = clusterService.getAllCluster();
		model1.addObject("km", kmdata);
		model1.addObject("cluster", ClusterList);
		//System.out.println("ClusterList::"+ClusterList.toString());
		//System.out.println("cohort::"+CohortList.toString());
		model1.addObject("cohort", CohortList);
		/*return new ModelAndView("kmVerificationForm", "km", kmdata);*/
		return model1;
	}
	
	
	@RequestMapping(value = "/updateKm", method = RequestMethod.POST)
	public String updatekm(kmVerificationForm kmverificationform, HttpServletRequest request) {
		int kmId =  request.getParameter("kmId")==null?0:Integer.parseInt(request.getParameter("kmId"));
		int kmValue =request.getParameter("kmValue")==null?0:Integer.parseInt(request.getParameter("kmValue"));
		//System.out.println("value for the KmId and KmValue is:"+kmId+" "+kmValue);
		int roleId,userId;
		try
		{
		SessionBean sbean=(SessionBean)request.getSession().getAttribute("sessionBean");
		userId=sbean.getUserID();
		 roleId=sbean.getRoleID();
		if(roleId!=6 && roleId!=9)
		{
			return "redirect:login";
		}
		}catch(Exception e)
		{
			return "redirect:login";
		}
		
		kmVerificationService.updateKm(kmId,kmValue,userId);
		return "redirect:getkmverification";
	}
	
	@RequestMapping(value="/editFirmVerfication" ,method= RequestMethod.POST)
	public String editFirmVerfication(HttpServletRequest request,kmVerificationForm kmVerification){
		//System.out.println("cluster name::"+kmVerification.getClusterName());
		//System.out.println("Cohort Name:::"+kmVerification.getCohortName());
		//System.out.println("appliication id:::"+kmVerification.getApplicationId());
		kmVerificationService.getClusterAndCohortId(kmVerification);
		return "redirect:getkmverification";
	}
	@RequestMapping(value="/downloadJDFile1", method = {RequestMethod.POST})
	public void downloadJDFile(HttpServletRequest request,
            HttpServletResponse response) throws IOException {
        
        String jdlink = request.getParameter("jdlink1")==null?"":request.getParameter("jdlink1");
        //System.out.println("jdlink :: " + jdlink);
 
        File downloadFile = new File(jdlink);
        String mimeType = FileUtil.getMimeType(downloadFile.getName());
        if (mimeType == null) {        
            mimeType = "application/octet-stream";
        }
        
        System.out.println("mimeType: "+mimeType);
        response.setContentType(mimeType);
		response.setHeader("Content-Disposition", "attachment;filename=\"" + downloadFile.getName()
				+ "\"");
		response.setContentLength((int) downloadFile.length());
		
        if(downloadFile.exists()){
        // get MIME type of the file
	        InputStream is = new FileInputStream(downloadFile);
			ServletOutputStream outStream = response.getOutputStream();  
			org.apache.commons.io.IOUtils.copy(is, outStream);
			is.close();
			outStream.flush();
	        outStream.close();
        }
	}
}
