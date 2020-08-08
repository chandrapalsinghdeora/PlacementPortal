package com.precise.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.precise.fileManager.FileUtil;
import com.precise.model.PotentialStudent;
import com.precise.model.SessionBean;
import com.precise.service.PotentialStudentService;

@Controller
public class PotentialStudentController {

	@Autowired
	PotentialStudentService potentialStudentService;

	@RequestMapping(value = "/getAllPotentialStudent", method = RequestMethod.GET)
	public ModelAndView getAllPotentialStudentDetails(ModelAndView model, HttpServletRequest req) {
		int roleId,userid;
		try
		{
		SessionBean sbean=(SessionBean)req.getSession().getAttribute("sessionBean");
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
		List<PotentialStudent> PotentialStudentDetailslist = potentialStudentService.getAllPotentialStudentDetail();
		// System.out.println("PotentialStudentDetailslist
		// :"+PotentialStudentDetailslist.size());
		req.setAttribute("potentialStudentDetailsDetails", PotentialStudentDetailslist);
		model.setViewName("PotentialStudent");
		return model;

	}

	@RequestMapping(value = { "/submitPotentialStudent" }, method = { RequestMethod.POST })
	public String submitPotentialStudent(PotentialStudent potentialStudent, HttpServletRequest request) {
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
		try {
			if (potentialStudent != null) {
				potentialStudent.setCreatedBy(userid);
				List<PotentialStudent> PotentialStudentDetailslist = potentialStudentService
						.getAllPotentialStudentDetail();
				request.setAttribute("potentialStudentDetailsDetails", PotentialStudentDetailslist);
				if (potentialStudent.getPotentialStudentId() == 0) {
					potentialStudentService.submitPotentialStudent(potentialStudent);
				} else {
					potentialStudentService.editPotentialStudentForm(potentialStudent);
					System.out.println("update PotentialStudent id :: " + potentialStudent.getPotentialStudentId());
				}
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return "redirect:getAllPotentialStudent";
	}

	@RequestMapping(value = "/deletePotentialStudent", method = { RequestMethod.POST })
	public String deletePotentialStudentData(HttpServletRequest request) {
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
		PotentialStudent potentialStudent = new PotentialStudent();
		potentialStudent.setCreatedBy(userid);
		potentialStudent.setPotentialStudentId(Integer.parseInt(request.getParameter("deletepotentialStudentId")));
		potentialStudentService.deletePotentialStudentData(potentialStudent);
		return "redirect:getAllPotentialStudent";
	}

	@RequestMapping(value = "/uploadIIMAStudentExcel", method = { RequestMethod.POST })
	public String uploadIIMAStudentExcel(PotentialStudent potentialStudent, HttpServletRequest request)
			throws Exception {
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
		String uploadRootPath = request.getServletContext().getInitParameter("uploadExcelFile");
		System.out.println("uploadRootPath=" + uploadRootPath);
		SessionBean sessionBean = (SessionBean) request.getSession().getAttribute("sessionBean");
		File uploadRootDir = new File(uploadRootPath + sessionBean.getUserID());
		if (!uploadRootDir.exists()) {
			uploadRootDir.mkdirs();
		}
		MultipartFile fileData = potentialStudent.getFileName();
		//
		String uploadedFiles = "";
		String name = fileData.getOriginalFilename();
		System.out.println("Client File Name = " + name);

		if (name != null && name.length() > 0) {
			File serverFile = new File(uploadRootDir.getAbsolutePath() + File.separator + name);
			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
			stream.write(fileData.getBytes());
			stream.close();
			System.out.println("Write file: " + serverFile.getCanonicalPath() + " :: " + serverFile.getAbsolutePath());
			uploadedFiles = serverFile.getCanonicalPath();
			System.out.println("Write file: " + serverFile);
		}
		// File uploadedFile = (potentialStudent.getFileName());
		// FileInputStream file = new FileInputStream("D:/IIM.xlsx");
		FileInputStream file = new FileInputStream(uploadedFiles);
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		XSSFSheet sheet = workbook.getSheetAt(0);
		Iterator<Row> rowIterator = sheet.iterator();
		List<String[]> l = new ArrayList<String[]>();
		if (rowIterator.hasNext()) {
			rowIterator.next();
		}
		while (rowIterator.hasNext()) {
			System.out.println("inside row ineratir:::");
			Row row = rowIterator.next();
			Iterator<Cell> cellIterator = row.cellIterator();
			String[] d = new String[2];
			int i = 0;

			while (cellIterator.hasNext()) {
				System.out.println("value of i::" + i);
				Cell cell = cellIterator.next();
				d[i] = cell.getStringCellValue();
				i = i + 1;
				if (i == 1) {
					System.out.println("inside if condition to put data in list");
					l.add(d);
				}
			}
			for (int j = 0; j < l.size(); j++) {
				String[] a = (String[]) (l.get(j));
				System.out.println("a[0]::" + a[0] + "a[1]::" + a[1]);
				potentialStudentService.insertIIMStudentInsert(a);
			}

		}
		return "redirect:getAllPotentialStudent";
	}

	// private static final String INTERNAL_FILE = "IIMAStudent.xlsx";
	private static final String INTERNAL_FILE = "resume.pdf";

	@RequestMapping(value = "/downloadDemoFile")
	public void downloadFile(HttpServletResponse response) throws Exception {
		/*ClassLoader classloader = Thread.currentThread().getContextClassLoader();
		File file = new File(classloader.getResource(INTERNAL_FILE).getFile());
		System.out.println("file path:::"+file.getCanonicalPath());
		if(file.exists()){
			
		
		
		String mimeType = FileUtil.getMimeType(INTERNAL_FILE);
		System.out.println("View File mimeType : " + mimeType);
		response.setContentType(mimeType);

		response.setHeader("Content-Disposition", "inline;filename=\""
                + file.getName() + "\"");
        
		response.setContentLength((int) file.length());

		InputStream is = new FileInputStream(file);
		ServletOutputStream outStream = response.getOutputStream();
		org.apache.commons.io.IOUtils.copy(is, outStream);
		is.close();
		outStream.flush();*/
		
		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        System.out.println("jdlink :: " + classloader.getResource(INTERNAL_FILE)+" :: "+classloader.getResource(INTERNAL_FILE).getFile());
 
        File downloadFile = new File(classloader.getResource(INTERNAL_FILE).getFile());
        String mimeType = FileUtil.getMimeType(downloadFile.getName());
        if (mimeType == null) {        
            mimeType = "application/octet-stream";
        }
        
        System.out.println("mimeType: "+mimeType);
        response.setContentType(mimeType);
		response.setHeader("Content-Disposition", "attachment;filename=\"" + downloadFile.getName()
				+ "\"");
		System.out.println("file length::"+downloadFile.length());
		response.setContentLength((int) downloadFile.length());
		
        if(downloadFile.exists()){
	        InputStream is = new FileInputStream(downloadFile);
			ServletOutputStream outStream = response.getOutputStream();  
			org.apache.commons.io.IOUtils.copy(is, outStream);
			is.close();
			outStream.flush();
	        outStream.close();
        }

	

	}

}
