package com.precise.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.precise.fileManager.FileInfo;
import com.precise.model.SessionBean;
import com.precise.model.UserProfile;

@Controller
public class FileManagerController {
	
	@RequestMapping(value ="/getFileManager",method = RequestMethod.GET)
	public ModelAndView getFileManagerDetails(ModelAndView model,HttpServletRequest req){
		int roleId,userid;
		try{
			SessionBean sbean=(SessionBean)req.getSession().getAttribute("sessionBean");
			userid=sbean.getUserID();
			roleId=sbean.getRoleID();
			if(roleId!=6 && roleId!=1 && roleId!=20){
				return new ModelAndView("LoginPage");
			}
		}catch(Exception e)	{
			return new ModelAndView("LoginPage");
		}
		
		String sort = req.getParameter("sort");
		String dir = req.getParameter("dir");
		
		String viewFolder = req.getParameter("vf");
		System.out.println("viewFolder :: "+viewFolder);
		if(viewFolder!=null)
			viewFolder = viewFolder.substring(0,1).toUpperCase()+viewFolder.substring(1).replace("_", " ");
		System.out.println("userid :: "+userid+" sort : "+sort+" dir : "+dir+" roleId : "+roleId);
		req.setAttribute("userid", userid);
		req.setAttribute("roleId", roleId);
		req.setAttribute("vf", viewFolder);
		req.setAttribute("browserPath","getFileManager");
		model.setViewName("fileManager");
		return model;
	}
	
	
	@RequestMapping(value ="/getFileManager",method = RequestMethod.POST) 
	public ModelAndView saveFileManager(ModelAndView model,HttpServletRequest req, @RequestParam( value="myFile", required=false) MultipartFile file){
		if(req.getSession()==null){
			model.setViewName("login");
			return model;
		}else{
			SessionBean sessionBean = (SessionBean) req.getSession().getAttribute("sessionBean");
			int userid=sessionBean.getUserID();
			int roleId =sessionBean.getRoleID();
			System.out.println("userid :: "+userid+"roleId::"+roleId);
			String sort = req.getParameter("sort");
			String dir = req.getParameter("dir");
			String myFile = req.getParameter("myFile");
			System.out.println("sort : "+sort+" dir : "+dir+" myFile : "+myFile);
			if(file!=null){
				FileInfo fileInfo = this.doUpload(req,file);
				req.setAttribute("uploadFileInfo", fileInfo);
				String filename = file.getName();
				System.out.println("filename "+ filename);
			}
			String nfile = req.getParameter("nfile");
			String uplMonitor = req.getParameter("uplMonitor");
			String first =req.getParameter("first");
			
			System.out.println("nfile : "+nfile+"  uplMonitor : "+uplMonitor+" first :: "+first+" file :"+file);
			req.setAttribute("userid", userid);
			req.setAttribute("browserPath","getFileManager");
			model.setViewName("fileManager");
			req.setAttribute("roleId", roleId);
			return model;
		}
	}
	
	
	private  FileInfo doUpload(HttpServletRequest request, MultipartFile fileData) {
		FileInfo fileInfo =  new FileInfo();;
	      // Root Directory.
	      String tempFileLocation = request.getServletContext().getInitParameter("tempFileLocation");
	      System.out.println("tempFileLocation=" + tempFileLocation);
	      
	      File uploadRootDir = new File(tempFileLocation);
	      // Create directory if it not exists.
	      if (!uploadRootDir.exists()) {
	          uploadRootDir.mkdirs();
	      }
          // Client File Name
          String name = fileData.getOriginalFilename();
          System.out.println("Client File Name = " + name);
          fileInfo.name = "myfile";
          
          if (name != null && name.length() > 0) {
              try {
                  // Create the file on server
                  File serverFile = new File(uploadRootDir.getAbsolutePath() + File.separator + name);
                  // Stream to write data to file in server.
                  BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
                  stream.write(fileData.getBytes());
                  stream.close();
                  System.out.println("Write file: " + serverFile.getCanonicalPath()+" :: "+serverFile.getAbsolutePath());
                  fileInfo.clientFileName = serverFile.getName();
                  fileInfo.file = serverFile;
                  System.out.println("Write file: " + serverFile);
              } catch (Exception e) {
                  System.out.println("Error Write file: " + name);
                  e.printStackTrace();
              }
          }
      
	      return fileInfo;
	  }
	
	
	@RequestMapping(value ="/fileManagerForData",method = RequestMethod.GET)
	public ModelAndView getFileManagerDetailsForData(ModelAndView model,HttpServletRequest req){
		
		int roleId,userid;
		try
		{
		SessionBean sbean=(SessionBean)req.getSession().getAttribute("sessionBean");
		userid=sbean.getUserID();
		 roleId=sbean.getRoleID();
		 if(roleId!=6 && roleId!=1 && roleId!=20)
		{
			  return new ModelAndView("LoginPage");
		}
		}catch(Exception e)
		 {
	      return new ModelAndView("LoginPage");
		 }
			String sort = req.getParameter("sort");
			String dir = req.getParameter("dir");
			
			String viewFolder = req.getParameter("vf");
			System.out.println("viewFolder :: "+viewFolder);
			if(viewFolder!=null)
				viewFolder = viewFolder.substring(0,1).toUpperCase()+viewFolder.substring(1).replace("_", " ");
			System.out.println("userid :: "+userid+" sort : "+sort+" dir : "+dir+" roleId : "+roleId);
			req.setAttribute("userid", userid);
			req.setAttribute("roleId", roleId);
			req.setAttribute("vf", viewFolder);
			req.setAttribute("browserPath","fileManagerForData");
			model.setViewName("fileManagerForData");
			return model;
		}
	
	
	@RequestMapping(value ="/fileManagerForData",method = RequestMethod.POST) 
	public ModelAndView saveFileManagerForData(ModelAndView model,HttpServletRequest req, @RequestParam( value="myFile", required=false) MultipartFile file){
		if(req.getSession()==null){
			model.setViewName("login");
			return model;
		}else{
			SessionBean sessionBean = (SessionBean) req.getSession().getAttribute("sessionBean");
			int userid=sessionBean.getUserID();
			int roleId =sessionBean.getRoleID();
			System.out.println("userid :: "+userid+"roleId::"+roleId);
			String sort = req.getParameter("sort");
			String dir = req.getParameter("dir");
			String myFile = req.getParameter("myFile");
			System.out.println("sort : "+sort+" dir : "+dir+" myFile : "+myFile);
			if(file!=null){
				FileInfo fileInfo = this.doUpload(req,file);
				req.setAttribute("uploadFileInfo", fileInfo);
				String filename = file.getName();
				System.out.println("filename "+ filename);
			}
			String nfile = req.getParameter("nfile");
			String uplMonitor = req.getParameter("uplMonitor");
			String first =req.getParameter("first");
			
			System.out.println("nfile : "+nfile+"  uplMonitor : "+uplMonitor+" first :: "+first+" file :"+file);
			req.setAttribute("userid", userid);
			req.setAttribute("browserPath","fileManagerForData");
			model.setViewName("fileManagerForData");
			req.setAttribute("roleId", roleId);
			return model;
		}
	}
	
	
	
	
	@RequestMapping(value ="/getFileManagerWithoutSession",method = RequestMethod.GET)
	public ModelAndView getFileManagerWithoutSession(ModelAndView model,HttpServletRequest req){
		
			int userid=5;  //km admin userid because km is handling data                  //sessionBean.getUserID();
			int roleId =6; //km admin roleid because km is handling data                  //sessionBean.getRoleID();
			String sort = req.getParameter("sort");
			String dir = req.getParameter("dir");
			
			String viewFolder = req.getParameter("vf");
			System.out.println("viewFolder :: "+viewFolder);
			if(viewFolder!=null)
				viewFolder = viewFolder.substring(0,1).toUpperCase()+viewFolder.substring(1).replace("_", " ");
			System.out.println("userid :: "+userid+" sort : "+sort+" dir : "+dir+" roleId : "+roleId);
			req.setAttribute("userid", userid);
			req.setAttribute("roleId", roleId);
			req.setAttribute("vf", viewFolder);
			req.setAttribute("browserPath","getFileManagerWithoutSession");
			model.setViewName("fileManagerWithoutLogin");
			return model;
		
	}
	
	@RequestMapping(value ="/getFileManagerWithoutSession",method = RequestMethod.POST) 
	public ModelAndView saveFileManagerWithoutSession(ModelAndView model,HttpServletRequest req, @RequestParam( value="myFile", required=false) MultipartFile file){
		
			int userid=5;     //sessionBean.getUserID(); //km admin userid because km is handling data 
			int roleId =6;    //sessionBean.getRoleID(); //km admin userid because km is handling data 
			System.out.println("userid :: "+userid+"roleId::"+roleId);
			String sort = req.getParameter("sort");
			String dir = req.getParameter("dir");
			String myFile = req.getParameter("myFile");
			System.out.println("sort : "+sort+" dir : "+dir+" myFile : "+myFile);
			if(file!=null){
				FileInfo fileInfo = this.doUpload(req,file);
				req.setAttribute("uploadFileInfo", fileInfo);
				String filename = file.getName();
				System.out.println("filename "+ filename);
			}
			String nfile = req.getParameter("nfile");
			String uplMonitor = req.getParameter("uplMonitor");
			String first =req.getParameter("first");
			
			System.out.println("nfile : "+nfile+"  uplMonitor : "+uplMonitor+" first :: "+first+" file :"+file);
			req.setAttribute("userid", userid);
			req.setAttribute("browserPath","getFileManagerWithoutSession");
			model.setViewName("fileManagerWithoutLogin");
			req.setAttribute("roleId", roleId);
			return model;		
	}
}
