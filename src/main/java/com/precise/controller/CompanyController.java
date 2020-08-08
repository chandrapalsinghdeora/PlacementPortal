package com.precise.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.precise.fileManager.FileUtil;
import com.precise.model.CVReleted;
import com.precise.model.Company;
import com.precise.model.PPO;
import com.precise.model.SessionBean;
import com.precise.service.CompanyService;

@Controller
public class CompanyController {
	@Autowired
	CompanyService companyService;
	int flag=0;
	String statusFlag =null;
	int x=0;
	int differ=0;
	String s="";
	List<PPO> list=new ArrayList<PPO>();
	List<PPO> list1=new ArrayList<PPO>();

	@RequestMapping(value = "/getAllCompany", method = RequestMethod.GET)
	public ModelAndView getAllCompanyDetail(ModelAndView model, HttpServletRequest req) {

		int userId, roleId, pgpFlag;
		try {
			SessionBean sbean = (SessionBean)req.getSession().getAttribute("sessionBean");
			userId = sbean.getUserID();
			pgpFlag = sbean.getPgpFlag();
			roleId = sbean.getRoleID();
			if (roleId != 1) {
				return new ModelAndView("LoginPage");
			}
		} catch (Exception e) {
			return new ModelAndView("LoginPage");
		}
		/*
		 * String fileURL = null; if(req.getServerPort()==0) fileURL
		 * =req.getScheme()+"://"+req.getServerName()+req.getContextPath()+
		 * "/getFileManager?sort=1&dir="; else fileURL
		 * =req.getScheme()+"://"+req.getServerName()+":"+req.getServerPort()+
		 * req.getContextPath()+"/getFileManager?sort=1&dir=";
		 * req.setAttribute("fileURL", fileURL); System.out.println(
		 * "fileURL :: "+fileURL);
		 */

		List<Company> CompanyDetaillist = companyService.getAllCompanyDetail(userId, "Close");
		req.setAttribute("companyDetails", CompanyDetaillist);
		/*
		 * List<CVReleted> CVReleatedlist =
		 * companyService.getAllCVReleted(userId);
		 * req.setAttribute("cvReleatedlist", CVReleatedlist);
		 */
		req.setAttribute("pgpFlag", pgpFlag);

		model.setViewName("companyView");
		return model;
	}

	@RequestMapping(value = "/insertCompanyForm", method = { RequestMethod.POST })
	public String insertCompanyForm(ModelAndView model, Company company, HttpServletRequest request)
			throws ParseException {
		/*
		 * System.out.println("CompanyId::"+request.getParameter("companyId")+
		 * ":: "+company.getCompanyId());
		 * System.out.println("applicationId::"+request.getParameter(
		 * "applicationId")+"::"+company.getApplicationId());
		 * System.out.println("companyName::"+request.getParameter("companyName"
		 * )+"::"+company.getCompanyName());
		 * System.out.println("clusterName::"+request.getParameter("clusterName"
		 * )+"::"+company.getClusterName());
		 * System.out.println("roleCompanyId::"+request.getParameter(
		 * "roleCompanyId")+"::"+company.getRoleCompanyId());
		 * System.out.println("companyRoleName"+request.getParameter(
		 * "companyRoleName")+"::"+company.getCompanyRoleName());
		 * System.out.println("cohortName::"+request.getParameter("cohortName")+
		 * "::"+company.getCohortName()); System.out.println("cv value::"
		 * +request.getParameter("cvReleted")+"::"+company.getCvReleted());
		 * System.out.println("title:::"+request.getParameter("title"));
		 * System.out.println("title from bean::"+company.getTitle());
		 * System.out.println("Upload File::"
		 * +request.getParameter("uploadFile")+"::"+company.getUploadFile());
		 */
		try {
			int count=0;
			int chortCount=10000;
			SessionBean sbean = (SessionBean) request.getSession().getAttribute("sessionBean");
			int userId = sbean.getUserID();
			String commanCoverTitleName = companyService.getStudentTitleName(userId, company.getCompanyName());
			List<String> uploadFilelist = this.doUpload(request, company, userId, commanCoverTitleName);
			company.setFilePath(uploadFilelist);
			company.setCreatedBy(userId);
			for(int i=0;i<list1.size();i++)
			{
				if(list1.get(i).getCohortName().equalsIgnoreCase(company.getCohotName()))
				{
					chortCount=list1.get(i).getCount();
					break;
				}
			}

			List<String> cvReleted = company.getCvReleted();
			for(int i=0;i<cvReleted.size();i++)
				if(!cvReleted.get(i).equals(""))
					count++;
			System.out.println("cluster::"+company.getCohotName()+"limilt::"+company.getLimitCVNo()+"size::"+count);
			if(list.size()>0)
			{
				if(company.getProcessNm().equalsIgnoreCase("Finals"))
				{
			if(((company.getCluserName().equalsIgnoreCase("c1")) && count>list.get(0).getC1App()) || ((company.getCluserName().equalsIgnoreCase("c2")) && count>list.get(0).getC2App()) || 
					((company.getCluserName().equalsIgnoreCase("c3")) && count>list.get(0).getC3App()) || ((list.get(0).getTotalApp())+(list.get(0).getOfferCount())-(list.get(0).getTotal())-(list.get(0).getFlag()))<count/* || count>chortCount*/)
					differ=1;
			}
			}
			if(differ==0)
			companyService.insertCompanyForm(company, commanCoverTitleName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:getAllOpenCompany";

	}

	@RequestMapping(value = "/insertCompanyPrefence", method = { RequestMethod.POST })
	public String insertCompanyPrefence(ModelAndView model, Company company, HttpServletRequest request)
			throws ParseException {
		/*
		 * SessionBean
		 * sbean=(SessionBean)request.getSession().getAttribute("sessionBean");
		 * int userId=sbean.getUserID();
		 */
		companyService.insertCompanyPrefence(company);
		/*
		 * System.out.println("CompanyId::"+request.getParameter("companyId")+
		 * ":: "+company.getCompanyId());
		 * System.out.println("applicationId::"+request.getParameter(
		 * "applicationId")+"::"+company.getApplicationId());
		 * System.out.println("roleCompanyId::"+request.getParameter(
		 * "roleCompanyId")+"::"+company.getRoleCompanyId());
		 * System.out.println("companyRoleName"+request.getParameter(
		 * "roleCompany")+"::"+company.getRoleCompany());
		 */

		return "redirect:getAllCompany";

	}

	@RequestMapping(value = "/withdarw", method = { RequestMethod.POST })
	public String withdarw(ModelAndView model, Company company, HttpServletRequest request) throws ParseException {
		SessionBean sbean = (SessionBean) request.getSession().getAttribute("sessionBean");
		int userId = sbean.getUserID();
		company.setCreatedBy(userId);
		companyService.withdarw(company);
		/*
		 * System.out.println("CompanyId::"+request.getParameter("companyId")+
		 * ":: "+company.getCompanyId());
		 * System.out.println("applicationId::"+request.getParameter(
		 * "applicationId")+"::"+company.getApplicationId());
		 * System.out.println("roleCompanyId::"+request.getParameter(
		 * "roleCompanyId")+"::"+company.getRoleCompanyId());
		 * System.out.println("companyRoleName"+request.getParameter(
		 * "roleCompany")+"::"+company.getRoleCompany());
		 */

		return "redirect:getAllOpenCompany";

	}

	private List<String> doUpload(HttpServletRequest request, Company company, int userId,
			String commanCoverTitleName) {

		List<String> uploadedFiles = new ArrayList<String>();
		List<MultipartFile> fileDatas = company.getUploadFile();
		List<Integer> checkboxlist = company.getCheckBoxList();
		List<Boolean> roleCover = company.getRoleCover();

		if (fileDatas != null && fileDatas.size() > 0) {
			String uploadRootPath = request.getServletContext().getInitParameter("saveUserFile");
			// System.out.println("uploadRootPath=" + uploadRootPath);
			File uploadRootDir = new File(uploadRootPath + userId);
			//
			// Create directory if it not exists.
			if (!uploadRootDir.exists()) {
				uploadRootDir.mkdirs();
			}
			int count = 0, check = 0;
			for (MultipartFile fileData : fileDatas) {

				// Client File Name
				String name = fileData.getOriginalFilename();
				// System.out.println("Client File Name = " + name);
				// String name =
				// commanCoverTitleName+"_"+companyService.getRoleName(checkboxlist.get(count));
				// name =
				// name+"."+FilenameUtils.getExtension(fileData.getOriginalFilename());

				if (roleCover.get(check) && name != null && name.length() > 0) {
					name = commanCoverTitleName + "_" + companyService.getRoleName(checkboxlist.get(count));
					// name =
					// name+"."+fileData.getOriginalFilename().substring(fileData.getOriginalFilename().lastIndexOf(".")+1);
					name = name + "." + FilenameUtils.getExtension(fileData.getOriginalFilename());
					try {
						// Create the file on server
						File serverFile = new File(uploadRootDir.getAbsolutePath() + File.separator + name);
						File checkFile = new File(uploadRootDir.getAbsolutePath() + File.separator + name);
						if (checkFile.exists())
							checkFile.delete();
						// Stream to write data to file in server.
						BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
						stream.write(fileData.getBytes());
						stream.close();
						// System.out.println(
						// "Write file: " + serverFile.getCanonicalPath() + " ::
						// " + serverFile.getAbsolutePath());
						uploadedFiles.add(serverFile.getCanonicalPath());
						System.out.println("Write file: " + serverFile);
						count++;
					} catch (Exception e) {
						System.out.println("Error Write file: " + name);
						e.printStackTrace();
					}
				} else {
					uploadedFiles.add(null);
				}
				check++;
			}
		} else {
			uploadedFiles.add(null);
		}
		return uploadedFiles;
	}

	@RequestMapping(value = "/getStudentCompanyApply", method = { RequestMethod.POST })
	public ModelAndView StudentCompanyApply(HttpServletRequest request) {
		SessionBean sbean = (SessionBean) request.getSession().getAttribute("sessionBean");
		int userId = sbean.getUserID();
		int cmpId = Integer.parseInt(request.getParameter("cmpId") == null ? "0" : request.getParameter("cmpId"));
		/*
		 * String fileURL = null; if(request.getServerPort()==0) fileURL
		 * =request.getScheme()+"://"+request.getServerName()+request.
		 * getContextPath()+"/getFileManager?sort=1&dir="; else fileURL
		 * =request.getScheme()+"://"+request.getServerName()+":"+request.
		 * getServerPort()+request.getContextPath()+
		 * "/getFileManager?sort=1&dir=";
		 * 
		 * request.setAttribute("fileURL", fileURL); System.out.println(
		 * "fileURL :: "+fileURL+" cmpId : "+cmpId+" userId : "+userId);
		 */
		// System.out.println("cmpId : "+cmpId+" userId : "+userId);
		Company applyCmp = companyService.getCompanyDetail(cmpId);
		// System.out.println("role ::"+applyCmp.getListCompanyRoles().size());
		request.setAttribute("fm", applyCmp);

		List<CVReleted> CVReleatedlist = companyService.getAllCVReleted(userId);
		System.out.println("CVReleatedlist ::" + CVReleatedlist.size());
		request.setAttribute("cvReleatedlist", CVReleatedlist);

		return new ModelAndView("StudentCompanyApply");
	}

	@RequestMapping(value = "/getStudentCompanyApply", method = { RequestMethod.GET })
	public String getBackToCompanyPage(HttpServletRequest request) {
		return "redirect:getAllCompany";
	}

	@RequestMapping(value = "/downloadJDFile", method = { RequestMethod.POST })
	public void downloadJDFile(HttpServletRequest request, HttpServletResponse response) throws IOException {

		String jdlink = request.getParameter("jdlink") == null ? "" : request.getParameter("jdlink");
		// System.out.println("jdlink :: " + jdlink);

		File downloadFile = new File(jdlink);
		String mimeType = FileUtil.getMimeType(downloadFile.getName());
		if (mimeType == null) {
			mimeType = "application/octet-stream";
		}

		// System.out.println("mimeType: "+mimeType);
		response.setContentType(mimeType);
		response.setHeader("Content-Disposition", "attachment;filename=\"" + downloadFile.getName() + "\"");
		response.setContentLength((int) downloadFile.length());

		if (downloadFile.exists()) {
			// get MIME type of the file
			InputStream is = new FileInputStream(downloadFile);
			ServletOutputStream outStream = response.getOutputStream();
			org.apache.commons.io.IOUtils.copy(is, outStream);
			is.close();
			outStream.flush();
			outStream.close();
		}
	}

	@RequestMapping(value = "/getAllOpenCompany", method = RequestMethod.GET)
	public ModelAndView getAllOpenCompanyDetail(ModelAndView model, HttpServletRequest req) {
		
		int userId,roleId,pgpFlag;
		String status="";
		try
		{
		SessionBean sbean=(SessionBean)req.getSession().getAttribute("sessionBean");
		userId=sbean.getUserID();
		 pgpFlag = sbean.getPgpFlag();
		 roleId=sbean.getRoleID();
		 if(roleId!=1)
		 {
			 return new ModelAndView("LoginPage");
		 }
		}catch(Exception e)
		{
			return new ModelAndView("LoginPage");
		}
		int flag=companyService.checkForLateral(userId);
		int workexp=companyService.workExp(userId);
		if(flag!=0)
			 status=companyService.checkStatus(userId);
		List<Company> CompanyDetaillist = companyService.getAllCompanyDetail(userId,"Open");
		req.setAttribute("companyDetails", CompanyDetaillist);
		List<CVReleted> CVReleatedlist = companyService.getAllCVReleted(userId);
		req.setAttribute("cvReleatedlist", CVReleatedlist);
		req.setAttribute("pgpFlag", pgpFlag); 
		req.setAttribute("Flag", flag); 
		req.setAttribute("workExp", workexp);  
		if(status.equalsIgnoreCase("Accept"))
			req.setAttribute("status", 0); 
		else if(status.equalsIgnoreCase("Hold"))
		{
			list=companyService.getCount(userId);
			list1=companyService.sameCohort(userId);
			if(list.size()>0)
			{
			int total=list.get(0).getTotalApp();
			int totalapplied=list.get(0).getTotal();
			int offercount=list.get(0).getOfferCount();
			int flag1=list.get(0).getFlag();
			if( total==0 || totalapplied>=total+offercount-flag1)
				req.setAttribute("total", 0); 
			//(totalapplied+offercount-flag1>=total) ||
			else
			{
					req.setAttribute("c1",list.get(0).getC1App()); 
					req.setAttribute("c2",list.get(0).getC2App()); 
					req.setAttribute("c3",list.get(0).getC3App());
					//req.setAttribute("cohort",list1);
			}
			}
		}
		else if(status.equalsIgnoreCase(""))
		{
			list=new ArrayList<PPO>();;
			list1=new ArrayList<PPO>();;
		}
		if(differ==1)
		{
			req.setAttribute("csd","sorry you cann't apply as you tried to apply more than your remaining apply count");
			differ=0;
		}
		model.setViewName("OpenCompanyView");
		return model;
	}
	
	@RequestMapping(value={"/appMatrix"},method={RequestMethod.GET})
	public ModelAndView appMatrix(HttpServletRequest request) {
		int userId,roleId;
		try
		{
		SessionBean sbean=(SessionBean)request.getSession().getAttribute("sessionBean");
         userId=sbean.getUserID();
		 roleId=sbean.getRoleID();
		 if(roleId!=1)
		 {
			 return new ModelAndView("LoginPage");
		 }
		}catch(Exception e)
		{
			return new ModelAndView("LoginPage");
		}
		
		List<Company> offercount=companyService.getOfferStatus(userId);
		List<Company> applylist=companyService.getApplyList(userId);
		List<PPO> list3=companyService.getCount(userId);
		int apply=companyService.getApplyNumber(userId);
		request.setAttribute("applylist", applylist);
		if(offercount.size()>0)
		{	
			
		 request.setAttribute("offercount", offercount.get(0).getOfferCount());
		 request.setAttribute("companyName", offercount.get(0).getCompanyName());
		 if( offercount.get(0).getTotalApplication()<apply)
		    flag=1;
		 if((s.equalsIgnoreCase("c1")&&list3.get(0).getC1App()<0) || (s.equalsIgnoreCase("c2")&&list3.get(0).getC2App()<0) || (s.equalsIgnoreCase("c3")&&list3.get(0).getC3App()<0))
			 flag=1;
		 
		 System.out.println("flag"+flag);
		 request.setAttribute("flag", flag);
		}
		if(x==1)
			 request.setAttribute("flagMessage", "Cannot Withdraw from the company");
		x=0;
		 return new ModelAndView("ApplicationMatrix");
	}
	
	@RequestMapping(value = "/withdrawFirm", method = {RequestMethod.POST})
	public String withdrawFirm(ModelAndView model,Company company, HttpServletRequest request) throws ParseException {
		SessionBean sbean=(SessionBean)request.getSession().getAttribute("sessionBean");
		int userId=sbean.getUserID();
		s=companyService.getClusterName(company);
		company.setCreatedBy(userId);
		if(flag==1)
		{
		companyService.withdrawAll(company);
		flag=0;
		}
		if(flag==0)
		{
			 statusFlag = companyService.withdrawPartial(company);
			 if(statusFlag.equals("1"))
				 x=1;
		}
		return "redirect:appMatrix";
	}
}
