package com.precise.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.precise.model.CloseStatus;
import com.precise.model.Forum;
import com.precise.model.Inbox;
import com.precise.model.InfoCloseStatus;
import com.precise.model.SessionBean;
import com.precise.model.ThreadBlog;
import com.precise.model.UserProfile;
import com.precise.service.CloseStatusService;

@Controller
public class DownloadCloseStatusController {/*

	@Autowired
	CloseStatusService closeStatusService;

	@RequestMapping(value = "/getDownloadCloseStatus", method = RequestMethod.GET)
	public ModelAndView getAllCloseStatusDetails(ModelAndView model, HttpServletRequest req) {
		SessionBean sessionBean = (SessionBean) req.getSession().getAttribute("sessionBean");
		int cmpRoleId=req.getParameter("cmpRoleId")==null?0:Integer.parseInt(req.getParameter("cmpRoleId"));
		//cmpRoleId = 46;
		if(cmpRoleId!=0){
			sessionBean.setCmpRoleId(cmpRoleId);
		}else{
			cmpRoleId = sessionBean.getCmpRoleId();
		}
		System.out.println("cmpRoleId : "+cmpRoleId);
		//int cmpRoleId = req.getParameter("cmpRoleId") == null ? 0 : Integer.parseInt(req.getParameter("cmpRoleId"));
		//cmpRoleId = 46;

		List<CloseStatus> approveCloseStatusList = closeStatusService.getApproveCloseStatusList();
		System.out.println("FineDetaillist :" + approveCloseStatusList.size());
		req.setAttribute("approveCloseStatusList", approveCloseStatusList);

		CloseStatus header = closeStatusService.getCloseStatusHeader(cmpRoleId);
		header.setRoleId(cmpRoleId);
		System.out.println(header.getFirmName() + "::" + header.getRoleName() + "::" + header.getProcess() + "::"
				+ header.getExperienceReq());
		req.setAttribute("closeStatus", header);
		model.setViewName("DownloadCloseStatus");
		return model;

	}

	@RequestMapping(value = "/getDownloadUserValues")
	public void getUserValues(HttpSession session, HttpServletRequest req, HttpServletResponse res) {

		SessionBean sbean = (SessionBean) session.getAttribute("sessionBean");

		int rollNumber = Integer.parseInt(req.getParameter("rollNumber"));

		List<UserProfile> userValue = closeStatusService.getUserValues(rollNumber);
		for (UserProfile in : userValue) {
			System.out.println("getUserValues" + in);
		}

		// JSONObject responseDetailsJson = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		for (UserProfile in : userValue) {
			JSONObject userDetailsJson = new JSONObject();
			userDetailsJson.put("emailId", in.getEmailAddress());
			userDetailsJson.put("fullname", in.getFullName());
			userDetailsJson.put("cvName", in.getCvName());
			userDetailsJson.put("mentor", in.getMentor());
			userDetailsJson.put("gender", in.getGender());
			userDetailsJson.put("percentage", in.getPercentage());
			userDetailsJson.put("percenatageTwelve", in.getPercenatageTwelve());
			userDetailsJson.put("percentageGraduate", in.getPercentageGraduate());
			userDetailsJson.put("postgraduatePercentage", in.getPostgraduatePercentage());

			jsonArray.put(userDetailsJson);
		}
		System.out.println("json array- " + jsonArray);
		// responseDetailsJson.put("forms", jsonArray);//Here you can see the
		// data in json format

		try {
			PrintWriter out = res.getWriter();
			out.print(jsonArray);
			out.flush();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@RequestMapping(value = "/getDownloadInternshipList", method = RequestMethod.POST)
	public JSONArray getInternshipValues(HttpSession session, HttpServletRequest req, HttpServletResponse res) {

		SessionBean sbean = (SessionBean) session.getAttribute("sessionBean");

		int rollNumber = Integer.parseInt(req.getParameter("rollNumber"));

		// List<UserProfile> userValue
		// =closeStatusService.getInternshipValues(rollNumber);
		JSONArray jsonArray = closeStatusService.getInternshipValues(rollNumber);

		// JSONObject responseDetailsJson = new JSONObject();
		
		 * JSONArray jsonArray = new JSONArray(); for(UserProfile in:userValue)
		 * { JSONObject userDetailsJson = new JSONObject();
		 * userDetailsJson.put("companyname",in.getCompany());
		 * System.out.println("companyname" + in.getCompany());
		 * userDetailsJson.put("duration",in.getDurationSummerInternship());
		 * 
		 * 
		 * jsonArray.put(userDetailsJson); }
		 
		System.out.println("json array- " + jsonArray);
		// responseDetailsJson.put("forms", jsonArray);//Here you can see the
		// data in json format

		try {
			PrintWriter out = res.getWriter();
			out.print(jsonArray);
			out.flush();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonArray;
	}

	@RequestMapping(value = "/getDownloadExperienceList", method = RequestMethod.POST)
	public JSONArray getExperienceList(HttpSession session, HttpServletRequest req, HttpServletResponse res) {

		SessionBean sbean = (SessionBean) session.getAttribute("sessionBean");
		int rollNumber = Integer.parseInt(req.getParameter("rollNumber"));
		JSONArray jsonArray = closeStatusService.getExperienceList(rollNumber);

	//	System.out.println("json array- " + jsonArray);

		try {
			PrintWriter out = res.getWriter();
			out.print(jsonArray);
			out.flush();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonArray;
	}

	@RequestMapping(value = "/getDownloadCVList", method = RequestMethod.POST)
	public JSONArray getCVList(HttpSession session, HttpServletRequest req, HttpServletResponse res) {
		SessionBean sbean = (SessionBean) session.getAttribute("sessionBean");
		int rollNumber = Integer.parseInt(req.getParameter("rollNumber"));
		JSONArray jsonArray = closeStatusService.getCVList(rollNumber);
		System.out.println("json array- " + jsonArray);

		try {
			PrintWriter out = res.getWriter();
			out.print(jsonArray);
			out.flush();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonArray;
	}

	@RequestMapping(value = "/getDownloadInfoValues")
	public void getInfoValues(HttpSession session, HttpServletRequest req, HttpServletResponse res) {
		SessionBean sbean = (SessionBean) session.getAttribute("sessionBean");
		int roleId = 1;
		InfoCloseStatus userValue = closeStatusService.getInfoValues(roleId);
		JSONArray jsonArray = new JSONArray();

		JSONObject infoDetailsJson = new JSONObject();

		infoDetailsJson.put("clusterName", userValue.getClusterName());
		infoDetailsJson.put("cohortName", userValue.getCohortName());
		infoDetailsJson.put("compensation", userValue.getCompensation());
		infoDetailsJson.put("hires", userValue.getHires());
		infoDetailsJson.put("noOfApp", userValue.getNoOfApp());
		infoDetailsJson.put("rollOver", userValue.getRollOver());
		infoDetailsJson.put("verified", userValue.getVerified());
		infoDetailsJson.put("unVerified", userValue.getUnVerified());

		jsonArray.put(infoDetailsJson);

		System.out.println("json array- " + jsonArray);
		// responseDetailsJson.put("forms", jsonArray);//Here you can see the
		// data in json format

		try {
			PrintWriter out = res.getWriter();
			out.print(jsonArray);
			out.flush();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/shortList", method = RequestMethod.POST)
	public String saveShortList(HttpServletRequest request, CloseStatus closeStatus) {
		String shortListId = request.getParameter("updateApplyId");
		SessionBean sessionBean = (SessionBean) request.getSession().getAttribute("sessionBean");
		int userid = sessionBean.getUserID();
		System.out.println("+::" + shortListId);
		List<CloseStatus> closeStatuslist = new ArrayList<CloseStatus>();
		CloseStatus closeStatusLocaL = null;
		String[] idArr = shortListId.split(",");
		for (String id : idArr) {
			closeStatusLocaL = new CloseStatus();
			closeStatusLocaL.setApplyId(Integer.parseInt(id));
			System.out.println("id set value :: " + closeStatusLocaL.getApplyId());
			closeStatuslist.add(closeStatusLocaL);
		}
		closeStatusService.saveShortList(closeStatuslist, userid);
		closeStatusService.saveGenerateShortLinkMail(closeStatus, userid);

		return "redirect:getDownloadCloseStatus";
	}

	@RequestMapping(value = "/downloadCv", produces = "application/zip")
	public void zipFiles(HttpServletResponse response, HttpServletRequest request) throws IOException {
		String downloadCvId = request.getParameter("downloadCvId");
		System.out.println("downloadCvId::" + downloadCvId);
		List<CloseStatus> downloadCvlist = new ArrayList<CloseStatus>();
		CloseStatus closeStatus = null;
		String[] idArr = downloadCvId.split(",");
		for (String id : idArr) {
			closeStatus = new CloseStatus();
			closeStatus.setApplyId(Integer.parseInt(id));
			System.out.println("id set value :: " + closeStatus.getApplyId());
			downloadCvlist.add(closeStatus);
		}

		 List<String> downloadFileNameList=
		 closeStatusService.getDownloadCVList(downloadCvlist);
		List<String> downloadFileNameList = new ArrayList<String>();
		downloadFileNameList.add("D:\\Animal.java");
		// setting headers
		List<File> files = new ArrayList<File>();
		for (String filePath : downloadFileNameList) {
			files.add(new File(filePath));
		}

		try {
			if (files != null && files.size() > 0) {
				byte[] zip = zipFiles(files);
				ServletOutputStream sos = response.getOutputStream();
				response.setContentType("application/zip");
				response.setHeader("Content-Disposition", "attachment; filename=DATA.ZIP");

				sos.write(zip);
				sos.flush();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public byte[] zipFiles(List<File> files) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ZipOutputStream zos = new ZipOutputStream(baos);
		byte bytes[] = new byte[2048];

		for (File fileName : files) {
			if(fileName.exists()){
				FileInputStream fis = new FileInputStream(fileName.getCanonicalFile());
				BufferedInputStream bis = new BufferedInputStream(fis);
	
				zos.putNextEntry(new ZipEntry(fileName.getName()));
	
				int bytesRead;
				while ((bytesRead = bis.read(bytes)) != -1) {
					zos.write(bytes, 0, bytesRead);
				}
				zos.closeEntry();
				bis.close();
				fis.close();
			}
		}
		zos.flush();
		baos.flush();
		zos.close();
		baos.close();

		return baos.toByteArray();
	}

*/}
