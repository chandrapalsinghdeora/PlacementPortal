   package com.precise.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.util.SystemOutLogger;
import org.hashids.Hashids;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.precise.fileManager.FileUtil;
import com.precise.model.Label;
import com.precise.model.RmSetting;
import com.precise.model.SessionBean;
import com.precise.model.StudentBean;
import com.precise.model.UserProfile;
import com.precise.service.ProfileService;
import com.precise.service.RmSettingService;

@Controller
public class profileController {

	@Autowired
	ProfileService profileService;

	@Autowired
	RmSettingService rmsettingservice;

	@RequestMapping(value = { "/userprofile" }, method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView userProfile(HttpServletRequest request) {
		
		ModelAndView modelView = new ModelAndView("UserProfile");
		UserProfile userProfile1 = new UserProfile();
		String StudentId = request.getParameter("studentId");
		int stuId = 0;
		if (StudentId == null || StudentId.isEmpty()) {
			SessionBean sessionBean = (SessionBean) request.getSession().getAttribute("sessionBean");
			stuId = sessionBean.getUserID();
			modelView.addObject("studentId", 0);
		} else {
			stuId = Integer.parseInt(StudentId);
			modelView.addObject("studentId", stuId);
		}
		System.out.println("csddddddddd:::"+stuId);
		SessionBean sessionBean = (SessionBean) request.getSession().getAttribute("sessionBean");
		UserProfile userProfileValues = profileService.getUserProfileValue(stuId, userProfile1);
		List<StudentBean> studentBena = profileService.getStudentListForMentors(sessionBean.getUserID());
		List<Label> mentor = profileService.getMentoreList();
		List<RmSetting> rmsetting = rmsettingservice.getAllSetting();
		String mentorname = profileService.getMentoreName(stuId);

		System.out.println("mentor name :: " + mentorname);

		int cvlimit = 0;
		for (RmSetting rmSetting2 : rmsetting) {
			if (rmSetting2.getOptionName().equals("NoOfCV")) {
				cvlimit = rmSetting2.getOptionValue();
			}
		}
		String emailId = sessionBean.getEmail();
		System.out.println("email id :: " + emailId);
		modelView.addObject("emailId", emailId);
		modelView.addObject("mentorname", mentorname);

		if (userProfileValues.getRollNumber() == null) {
			userProfileValues.setEmailAddress(emailId);
		}

		String val = profileService.getExperienceData(stuId);
		String[] valSplit = val.split("@@");
		System.out.println("value0::" + valSplit[0]);
		userProfileValues.setInternshipEnable(valSplit[0]);
		userProfileValues.setInterviewEnable(valSplit[1]);
		modelView.addObject("userBean", userProfileValues);
		modelView.addObject("studentList", studentBena);
		modelView.addObject("mentoreList", mentor);
		int cvLimitValue = 0;
		System.out.println("cvfileId::" + userProfileValues.getCvFileId().size());
		if (userProfileValues.getCvFileId().size() > 0) {
			System.out.println("inside if condition::");
			cvLimitValue = cvlimit - (userProfileValues.getCvFileId().size());
		} else {
			cvLimitValue = (cvlimit - (userProfileValues.getCvFileId().size()));
		}
		System.out.println("cv limit value::" + cvLimitValue);
		modelView.addObject("cvlimit", cvLimitValue);
		modelView.addObject("submitFlag", "noSubmit");
		return modelView;
	}

	@RequestMapping(value = { "/submitUserProfile" }, method = { RequestMethod.POST })
	public ModelAndView submitUserProfile(UserProfile userProfile, HttpServletRequest request) {
		System.out.println("inside method lock value::" + userProfile.getRollNumberLock());
		ModelAndView modelView = new ModelAndView("UserProfile");
		SessionBean sessionBean = (SessionBean) request.getSession().getAttribute("sessionBean");
		String studentId = request.getParameter("StudentId");
		int studentIdValue = 0;
		if (studentId.equalsIgnoreCase("0") || studentId.isEmpty()) {
			studentIdValue = sessionBean.getUserID();
		} else {
			studentIdValue = Integer.parseInt(studentId);
		}
		try {
			// upload file here save into database
			String userName = this.getUserNameBasedOnId(studentIdValue);
			List<String> uploadedFileslist = this.doUpload(request, userProfile, userName);
			System.out.println("save upload file list :: " + uploadedFileslist.size());
			userProfile.setCvFilePathList(uploadedFileslist);
			String uploadedPhotot = this.doUploadPhoto(request, userProfile, userName);
			userProfile.setPhototUploadFilePath(uploadedPhotot);
			String daWriteUp = this.doUploadDAWriteUp(request, userProfile, userName);
			userProfile.setDaWriteUpFilePath(daWriteUp);
			// ---------------------------------------
			String returnValue = "";

			// String returnValue =
			// profileService.submitProfileData(userProfile,
			// studentIdValue,sessionBean.getRoleID());
			if (userProfile.getProfileLockValue().equalsIgnoreCase("Locked")) {
				returnValue = "success";
			} else {
				if (userProfile.getLockProfileByMentor().equals("1")) {
					returnValue = "success";
				} else {
					returnValue = profileService.submitProfileData(userProfile, studentIdValue,
							sessionBean.getRoleID());
				}
			}

			if (returnValue.equalsIgnoreCase("success")) {
				UserProfile userProfile1 = new UserProfile();
				UserProfile userProfileValues = profileService.getUserProfileValue(studentIdValue, userProfile1);
				System.out.print("size of company::" + userProfileValues.getCompany().size());
				modelView.addObject("userBean", userProfileValues);
				List<StudentBean> studentBena = profileService.getStudentListForMentors(sessionBean.getUserID());
				List<Label> mentor = profileService.getMentoreList();
				modelView.addObject("mentoreList", mentor);
				modelView.addObject("studentList", studentBena);
				List<RmSetting> rmsetting = rmsettingservice.getAllSetting();
				int cvlimit = 0;
				for (RmSetting rmSetting2 : rmsetting) {
					if (rmSetting2.getOptionName().equals("NoOfCV")) {
						cvlimit = rmSetting2.getOptionValue();
					}
				}
				int cvLimitValue = 0;
				if (userProfileValues.getCvFileId().size() > 0) {
					cvLimitValue = cvlimit - (userProfileValues.getCvFileId().size());
				} else {
					cvLimitValue = (cvlimit - (userProfileValues.getCvFileId().size())) - 1;
				}
				System.out.println("cv limit value::" + cvLimitValue);
				modelView.addObject("cvlimit", cvLimitValue);
				String mentorname = profileService.getMentoreName(studentIdValue);
				modelView.addObject("mentorname", mentorname);
				modelView.addObject("submitFlag", "submit");
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		System.out.println("request value::" + request.getParameter("daWriteUp"));

		return modelView;
	}

	@RequestMapping(value = "/downloadDocument", method = { RequestMethod.GET ,RequestMethod.POST})
	public void downloadDocument(HttpServletResponse response, HttpServletRequest request) throws IOException {
		// System.out.println("primary key::" + request.getParameter("cvpkId"));
		String[] pkId = request.getParameter("cvpkId").split("pKiD");
		Hashids hashids1 = new Hashids("comp role id",10);
		long[] numbers = hashids1.decode(pkId[1]);
		// System.out.println("number::"+numbers[0]);
		int pkIdNum = (int) (numbers[0]);
		// System.out.println("pkIdNum:::"+pkIdNum);
		String mimeType = null;
		File f = null;
		try {
			f = new File(profileService.getImageFile(pkIdNum));
			mimeType = FileUtil.getMimeType(f.getCanonicalPath());
		} catch (Exception e) {
			mimeType = null;
		}
		if (mimeType == null) {
			mimeType = "application/octet-stream";
		}
		response.setContentType(mimeType);
		response.setHeader("Content-Disposition", "inline;filename=\"" + f.getName() + "\"");
		response.setContentLength((int) f.length());
		InputStream is = null;
		try {
			is = new FileInputStream(f);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		ServletOutputStream outStream = null;
		try {
			outStream = response.getOutputStream();
			org.apache.commons.io.IOUtils.copy(is, outStream);
			is.close();
			outStream.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}	

		/*byte[] bytes = profileService.getImageByteData(pkIdNum);
		OutputStream oImage = response.getOutputStream();
		String fileName = (request.getParameter("fileName") == null) ? "document" : request.getParameter("fileName");
		System.out.println(fileName);
		response.setHeader("Content-Disposition", "filename=" + fileName);
		if (fileName.contains(".docx")) {
			response.setContentType("application/docx");
		} else if (fileName.contains(".pdf")) {
			response.setContentType("application/pdf");
		} else if (fileName.contains(".png") || fileName.contains(".PNG")) {
			response.setContentType("image/png");
		} else if (fileName.contains("jpeg")) {
			response.setContentType("image/jpeg");
		} else if (fileName.contains("jpg")) {
			response.setContentType("image/jpg");
		}
		oImage.write(bytes);*/


	private List<String> doUpload(HttpServletRequest request, UserProfile fileUploadForm, String userName) {
		String uploadRootPath = request.getServletContext().getInitParameter("saveUserFile");
		System.out.println("uploadRootPath=" + uploadRootPath);

		File uploadRootDir = new File(uploadRootPath + userName);
		if (!uploadRootDir.exists()) {
			uploadRootDir.mkdirs();
		}
		List<MultipartFile> fileDatas = fileUploadForm.getCvFile();
		//
		List<String> uploadedFiles = new ArrayList<String>();
		for (MultipartFile fileData : fileDatas) {
			String name = fileData.getOriginalFilename();
			//System.out.println("Client File Name = " + name);
			if (name != null && name.length() > 0) {
				try {
					File serverFile = new File(uploadRootDir.getAbsolutePath() + File.separator + name);
					BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
					stream.write(fileData.getBytes());
					stream.close();
				//	System.out.println(	"Write file: " + serverFile.getCanonicalPath() + " :: " + serverFile.getAbsolutePath());
					uploadedFiles.add(serverFile.getCanonicalPath());
				//	System.out.println("Write file: " + serverFile);
				} catch (Exception e) {
					System.out.println("Error Write file: " + name);
					e.printStackTrace();
				}
			} else {
				uploadedFiles.add(null);
			}
		}
		return uploadedFiles;
	}

	private String doUploadPhoto(HttpServletRequest request, UserProfile fileUploadForm, String userId) {
		String uploadRootPath = request.getServletContext().getInitParameter("saveUserFile");
		System.out.println("uploadRootPath=" + uploadRootPath);

		File uploadRootDir = new File(uploadRootPath + userId);
		if (!uploadRootDir.exists()) {
			uploadRootDir.mkdirs();
		}
		MultipartFile fileData = fileUploadForm.getPhotoUpload();
		//
		String uploadedFiles = "";
		String name = fileData.getOriginalFilename();
		System.out.println("Client File Name = " + name);

		if (name != null && name.length() > 0) {
			try {
				File serverFile = new File(uploadRootDir.getAbsolutePath() + File.separator + name);
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
				stream.write(fileData.getBytes());
				stream.close();
				System.out.println(
						"Write file: " + serverFile.getCanonicalPath() + " :: " + serverFile.getAbsolutePath());
				uploadedFiles = serverFile.getCanonicalPath();
				System.out.println("Write file: " + serverFile);
			} catch (Exception e) {
				System.out.println("Error Write file: " + name);
				e.printStackTrace();
			}
		} else {
			uploadedFiles = null;
		}
		// }
		return uploadedFiles;
	}

	private String doUploadDAWriteUp(HttpServletRequest request, UserProfile fileUploadForm, String userId) {
		System.out.println("inside doUploadDAWriteUp method::");
		String uploadRootPath = request.getServletContext().getInitParameter("DAWriteUp");
		System.out.println("uploadRootPath=" + uploadRootPath);

		File uploadRootDir = new File(uploadRootPath + userId);
		if (!uploadRootDir.exists()) {
			uploadRootDir.mkdirs();
		}
		MultipartFile fileData = fileUploadForm.getDaWriteUp();
		//
		String uploadedFiles = "";
		String name = fileData.getOriginalFilename();
		System.out.println("Client File Name = " + name);

		if (name != null && name.length() > 0) {
			try {
				File serverFile = new File(uploadRootDir.getAbsolutePath() + File.separator + name);
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
				stream.write(fileData.getBytes());
				stream.close();
				System.out.println(
						"Write file: " + serverFile.getCanonicalPath() + " :: " + serverFile.getAbsolutePath());
				uploadedFiles = serverFile.getCanonicalPath();
				System.out.println("Write file: " + serverFile);
			} catch (Exception e) {
				System.out.println("Error Write file: " + name);
				e.printStackTrace();
			}
		} else {
			uploadedFiles = null;
		}
		// }
		return uploadedFiles;
	}

	@RequestMapping("/downloadphotoUploafFile")
	public void downloadphotoUploafFile(HttpServletResponse response, HttpServletRequest request) throws IOException {
		System.out.println("primary key::" + request.getParameter("pkid"));
		byte[] bytes = profileService.getPhotoUploadData(Integer.parseInt(request.getParameter("pkid")));
		OutputStream oImage = response.getOutputStream();
		String fileName = (request.getParameter("fileName") == null) ? "document" : request.getParameter("fileName");
		System.out.println(fileName);
		response.setHeader("Content-Disposition", "filename=" + fileName);
		if (fileName.contains(".docx")) {
			response.setContentType("application/docx");
		} else if (fileName.contains(".pdf")) {
			response.setContentType("application/pdf");
		} else if (fileName.contains(".png") || fileName.contains(".PNG")) {
			response.setContentType("image/png");
		} else if (fileName.contains("jpeg")) {
			response.setContentType("image/jpeg");
		} else if (fileName.contains("jpg")) {
			response.setContentType("image/jpg");
		}
		oImage.write(bytes);
	}

	@RequestMapping("/downloadDaWriteUp")
	public void downloadDaWriteUp(HttpServletResponse response, HttpServletRequest request) throws IOException {
		System.out.println("primary key::" + request.getParameter("pkid"));
		Hashids hashids1 = new Hashids("comp role id",10);
		long[] numbers = hashids1.decode(request.getParameter("pkid"));
		System.out.println("number::"+numbers[0]);
		int  pkIdNum=(int)(numbers[0]);
		System.out.println("pkIdNum:::"+pkIdNum);
		byte[] bytes = profileService.getDaWriteUp(pkIdNum);
		OutputStream oImage = response.getOutputStream();
		String fileName = (request.getParameter("fileName") == null) ? "document" : request.getParameter("fileName");
		System.out.println(fileName);
		response.setHeader("Content-Disposition", "filename=" + fileName);
		if (fileName.contains(".docx")) {
			response.setContentType("application/docx");
		} else if (fileName.contains(".pdf")) {
			System.out.println("inside pdf condition::");
			response.setContentType("application/pdf");
		} else if (fileName.contains(".png") || fileName.contains(".PNG")) {
			response.setContentType("image/png");
		} else if (fileName.contains("jpeg")) {
			response.setContentType("image/jpeg");
		} else if (fileName.contains("jpg")) {
			response.setContentType("image/jpg");
		}
		oImage.write(bytes);
	}

	@RequestMapping(value = "/MentorProfile", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView MentorProfile(HttpServletRequest request) {
		// ModelAndView modelView = new ModelAndView("MentorProfile");
		UserProfile userProfile1 = new UserProfile();
		String StudentId = request.getParameter("studentId");
		ModelAndView modelView = null;
		int stuId = 0;
		SessionBean sessionBean = (SessionBean) request.getSession().getAttribute("sessionBean");
		if (StudentId == null || StudentId.isEmpty()) {
			modelView = new ModelAndView("MentorProfile");
			// SessionBean sessionBean = (SessionBean)
			// request.getSession().getAttribute("sessionBean");
			stuId = sessionBean.getUserID();
			modelView.addObject("studentId", 0);
		} else {
			modelView = new ModelAndView("MentorProfile");
			stuId = Integer.parseInt(StudentId);
			modelView.addObject("studentId", stuId);
			UserProfile userProfileValues = profileService.getUserProfileValue(stuId, userProfile1);
			// System.out.println("userProfileValues : "+userProfileValues);
			modelView.addObject("userBean", userProfileValues);
			int cvlimit = 0;
			List<RmSetting> rmsetting = rmsettingservice.getAllSetting();
			for (RmSetting rmSetting2 : rmsetting) {
				if (rmSetting2.getOptionName().equals("NoOfCV")) {
					cvlimit = rmSetting2.getOptionValue();
				}
			}
			int cvLimitValue = 0;
			if (userProfileValues.getCvFileId().size() > 0) {
				cvLimitValue = cvlimit - (userProfileValues.getCvFileId().size());
			} else {
				cvLimitValue = (cvlimit - (userProfileValues.getCvFileId().size())) - 1;
			}
			String mentorname = profileService.getMentoreName(stuId);
			modelView.addObject("mentorname", mentorname);
			System.out.println("mentor name :: " + mentorname);
			modelView.addObject("cvlimit", cvLimitValue);
		}
		String gropupValue = profileService.getGroupValues(stuId);
		System.out.println("gropupValue::" + gropupValue);
		List<Label> mentor = profileService.getMentoreList();
		List<StudentBean> studentBena = profileService.getStudentListForMentors(sessionBean.getUserID());
		System.out.println("mentore list::" + mentor.toString());
		modelView.addObject("mentoreList", mentor);
		modelView.addObject("studentList", studentBena);
		modelView.addObject("groupId", gropupValue);
		modelView.addObject("submitFlag", "nosubmit");
		return modelView;
	}

	@RequestMapping(value = { "/mentoreSubmit" }, method = { RequestMethod.POST })
	public ModelAndView m(UserProfile userProfile, HttpServletRequest request) {
		System.out.println("inside method lock value::" + userProfile.getRollNumberLock());
		ModelAndView modelView = new ModelAndView("MentorProfile");
		SessionBean sessionBean = (SessionBean) request.getSession().getAttribute("sessionBean");
		String studentId = request.getParameter("StudentId");
		int studentIdValue = 0;
		userProfile.setUserRoleType("mentor");
		if (studentId.equalsIgnoreCase("0") || studentId.isEmpty()) {
			studentIdValue = sessionBean.getUserID();
			modelView.addObject("studentId", studentIdValue);
		} else {
			studentIdValue = Integer.parseInt(studentId);
			modelView.addObject("studentId", studentIdValue);
		}
		try {
			// upload file here save into database
			String userName = this.getUserNameBasedOnId(studentIdValue);
			List<String> uploadedFileslist = this.doUpload(request, userProfile, userName);
			System.out.println("save upload file list :: " + uploadedFileslist.size());
			userProfile.setCvFilePathList(uploadedFileslist);
			String uploadedPhotot = this.doUploadPhoto(request, userProfile, userName);
			userProfile.setPhototUploadFilePath(uploadedPhotot);
			String daWriteUp = this.doUploadDAWriteUp(request, userProfile, userName);
			userProfile.setDaWriteUpFilePath(daWriteUp);
			// ---------------------------------------
			String returnValue = "";
			if (userProfile.getProfileLockValue().equalsIgnoreCase("Locked")) {
				returnValue = "success";
			} else {
				returnValue = profileService.submitProfileData(userProfile, studentIdValue,
						sessionBean.getRoleID());
			}
			if (returnValue.equalsIgnoreCase("success")) {
				UserProfile userProfile1 = new UserProfile();
				UserProfile userProfileValues = profileService.getUserProfileValue(studentIdValue, userProfile1);
				System.out.print("size of company::" + userProfileValues.getCompany().size());
				modelView.addObject("userBean", userProfileValues);
				List<StudentBean> studentBena = profileService.getStudentListForMentors(sessionBean.getUserID());
				modelView.addObject("studentList", studentBena);
				int cvlimit = 0;
				List<RmSetting> rmsetting = rmsettingservice.getAllSetting();
				for (RmSetting rmSetting2 : rmsetting) {
					if (rmSetting2.getOptionName().equals("NoOfCV")) {
						cvlimit = rmSetting2.getOptionValue();
					}
				}
				int cvLimitValue = 0;
				if (userProfileValues.getCvFileId().size() > 0) {
					cvLimitValue = cvlimit - (userProfileValues.getCvFileId().size());
				} else {
					cvLimitValue = (cvlimit - (userProfileValues.getCvFileId().size())) - 1;
				}
				modelView.addObject("cvlimit", cvLimitValue);
				modelView.addObject("submitFlag", "submit");
			}
			List<Label> mentor = profileService.getMentoreList();
			modelView.addObject("mentoreList", mentor);
			String gropupValue = profileService.getGroupValues(studentIdValue);
			System.out.println("gropupValue::" + gropupValue);
			modelView.addObject("groupId", gropupValue);
			String mentorname = profileService.getMentoreName(studentIdValue);
			modelView.addObject("mentorname", mentorname);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("request value::" + request.getParameter("daWriteUp"));
		return modelView;
	}

	public String getUserNameBasedOnId(int userId) {
		return profileService.getUserName(userId);
	}

	@RequestMapping(value = { "deleteSummerInternShip" }, method = { RequestMethod.POST })
	public void deleteSummerInternShip(HttpServletRequest request, HttpServletResponse response) {
		try {
			String responseValue = profileService.deleteSummerInternsgipData(request.getParameter("pkId"));
			PrintWriter pw = response.getWriter();
			pw.print(responseValue);
			pw.flush();
			pw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@RequestMapping(value = { "otherInternshipDelete" }, method = { RequestMethod.POST })
	public void deleteOtherInternshipData(HttpServletRequest request, HttpServletResponse response) {
		try {
			System.out.println("inside otherInternshipDelete method::");
			profileService.deleteOtherInternsShip(request.getParameter("pkId"));
			PrintWriter pw = response.getWriter();
			pw.print("success");
			pw.flush();
			pw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@RequestMapping(value = { "workExperienceDelete" }, method = { RequestMethod.POST })
	public void deleteWorkExperinece(HttpServletRequest request, HttpServletResponse response) {
		try {
			System.out.println("inside otherInternshipDelete method::");
			profileService.deleteWorkExperinece(request.getParameter("pkId"));
			PrintWriter pw = response.getWriter();
			pw.print("success");
			pw.flush();
			pw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@RequestMapping(value = { "cvDelete" }, method = { RequestMethod.POST })
	public void cvDelete(HttpServletRequest request, HttpServletResponse response) {
		try {
			System.out.println("inside otherInternshipDelete method::");
			profileService.deleteCvFile(request.getParameter("pkId"));
			PrintWriter pw = response.getWriter();
			pw.print("success");
			pw.flush();
			pw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
