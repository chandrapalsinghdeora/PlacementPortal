package com.precise.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.precise.model.Announcement;
import com.precise.model.KmIIMStudent;
import com.precise.model.SessionBean;
import com.precise.service.KmIIMStudentService;

@Controller
public class KmIIMStudentController {

	@Autowired
	KmIIMStudentService kmiimstudentservice;
	
	@RequestMapping(value = "/getiimstudent", method = RequestMethod.GET)
	public ModelAndView getCreateIIMStudent(  HttpServletRequest request) {
		int roleId,userid;
		try
		{
		SessionBean sbean=(SessionBean)request.getSession().getAttribute("sessionBean");
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
		int y=1;
		 String x;
		 String excelerror = request.getParameter("excelerror");
		 String errorcount=request.getParameter("errorcount");
		List<KmIIMStudent> iimstudentdata = kmiimstudentservice.getAllIIMStudent();
		System.out.println("excel12 value::"+request.getParameter("excel12"));
		if(excelerror==null)
			x=request.getParameter("excel12")==null?"2":request.getParameter("excel12");
		else
	     x=request.getParameter("excel12")==null?"1":request.getParameter("excel12");
	    if(x.equals("2"))
	    	y=0;
		request.setAttribute("IIMStudent", iimstudentdata);
		
		System.out.println("excelerror::"+excelerror);
		ModelAndView model=new ModelAndView("IIMStudent");
		model.addObject("excelerror",excelerror);
		model.addObject("excelvalue",y);
		model.addObject("errorcount",errorcount);
		return model;
	}
	
	@RequestMapping(value={"/saveIIMStudent"},method={RequestMethod.POST})
	public String saveIIMStudent(KmIIMStudent cluster,HttpServletRequest request) {
		
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
			if(cluster!=null){
				cluster.setCreatedid(userid);
			//	List<KmIIMStudent> iimstudentdata = kmiimstudentservice.getAllIIMStudent();
			//	request.setAttribute("IIMStudent", iimstudentdata);
				if(cluster.getStudentId()==0){
					kmiimstudentservice.saveStudent(cluster);
				}else{
					System.out.println("update cluster id :: "+cluster.getStudentId()+" wildcard :: "+cluster.isWildCard());
					kmiimstudentservice.editStudent(cluster);
					
				}
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		 return "redirect:getiimstudent";
	}
	
	@RequestMapping(value="/deleteStudent",method={RequestMethod.POST})
	public String deleteClusterData(HttpServletRequest request){
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
		String shtIds = request.getParameter("deleteStudentId");
		System.out.println("delete"+shtIds);
		KmIIMStudent cluster = new KmIIMStudent();
		System.out.println("not checeked--");
		if (shtIds == "") {
			System.out.println("not checeked--");
		} else {
			String[] num = shtIds.split(",");
			System.out.println("ids"+num);
			for (String str : num) {
				System.out.println(str);
				cluster.setCreatedid(userid);
				cluster.setStudentId(Integer.parseInt(str));
				//kmiimstudentservice.deleteStudentData(cluster);
			}
		}
		return "redirect:getiimstudent";
	}

	@RequestMapping(value = "/getOfferStatus", method = RequestMethod.GET)
	public String getOfferStatus(ModelAndView model, HttpServletRequest request) {
		
		return "offerStatus";
		
	}
	
	@RequestMapping(value="/uploadExcelStudent", method=RequestMethod.POST)
	public String uploadExcelStudent(ModelMap model, KmIIMStudent student,HttpServletRequest request) throws Exception{
		int conflict;
		int flag=0;
		HashSet<String> set=new HashSet<String>();  
		String uploadRootPath = request.getServletContext().getInitParameter("uploadExcelFile");
		System.out.println("uploadRootPath=" + uploadRootPath);
		SessionBean sessionBean = (SessionBean) request.getSession().getAttribute("sessionBean");
		File uploadRootDir = new File(uploadRootPath);
		if (!uploadRootDir.exists()) {
			uploadRootDir.mkdirs();
		}
		MultipartFile fileData = student.getFile();
		//
		String uploadedFiles = "";
		String name = fileData.getOriginalFilename();
		System.out.println("Client File Name = " + name);

		if (name != null && name.length() > 0) {
			 File serverFile = new File(uploadRootDir.getAbsolutePath() + File.separator + name);
			 if(!serverFile.exists()){
				 serverFile.createNewFile();
			 }
             BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
             stream.write(fileData.getBytes());
             stream.close();
             System.out.println("Write file: " + serverFile.getCanonicalPath()+" :: "+serverFile.getAbsolutePath());
             uploadedFiles=serverFile.getCanonicalPath();
             System.out.println("Write file: " + serverFile);
		}
		// File uploadedFile = (potentialStudent.getFileName());
		//FileInputStream file = new FileInputStream("D:/IIM.xlsx");
		FileInputStream file = new FileInputStream(uploadedFiles);
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		XSSFSheet sheet = workbook.getSheetAt(0);
		Iterator<Row> rowIterator = sheet.iterator();
		List<Object[]> l = new ArrayList<Object[]>();
		if (rowIterator.hasNext()) {
			rowIterator.next();
		}
		while (rowIterator.hasNext()) {
			System.out.println("inside row ineratir:::");
			Row row = rowIterator.next();
			Iterator<Cell> cellIterator = row.cellIterator();
			Object[] d = new Object[4];
			int i = 0;

			while (cellIterator.hasNext()) {
				System.out.println("value of i::" + i);
				Cell cell = cellIterator.next();
				switch (cell.getCellType()) {
                case Cell.CELL_TYPE_STRING:
                	d[i] = cell.getStringCellValue();
                    break;
                case Cell.CELL_TYPE_NUMERIC:
                	Double doubleValue=cell.getNumericCellValue();
                	d[i] = doubleValue.intValue();
                    break;
            }
				i = i + 1;
				if (i == 1) {
					System.out.println("inside if condition to put data in list");
					l.add(d);
				}
			}
		}
	/*	for (int j = 0; j < l.size(); j++) {
			Object[] a = (Object[]) (l.get(j));
			System.out.println("a[0]::" + a[0] + "a[1]::" + a[1]+":: a[2]::"+a[2]+":::a[3]"+a[3]);
			kmiimstudentservice.insertIntoStudent(a);
		}*/
		    
		//System.out.println("KmIIMStudentController.uploadExcelStudent() :: "+l.size());
		for (int j = 0; j < (l.size()); j++) {
			Object[] a = (Object[]) (l.get(j));
			//System.out.println("a[0]::" + a[0] + "a[1]::" + a[1]+":: a[2]::"+a[2]+":::a[3]"+a[3]);
			set.add((String)a[3]);
			for (int k = j+1; k < l.size(); k++) {
				Object[] b = (Object[]) (l.get(k));
				if(a[3].equals(b[3])){
					//System.out.println("hello "+a[3]+"  and "+b[3]);
					flag=1;
				}
			}
		}
			//System.out.println("listsize::"+l.size());
			//System.out.println("setsize::"+set.size());
			//System.out.println("Conflicts::"+(l.size()-set.size()));
		conflict = l.size()-set.size();
			
		if(flag==0){
			for (int j = 0; j < l.size(); j++) {
				Object[] a = (Object[]) (l.get(j));
				//System.out.println("a[0]::" + a[0] + "a[1]::" + a[1]+":: a[2]::"+a[2]+":::a[3]"+a[3]);
				kmiimstudentservice.insertIntoStudent(a);
			}
		}else{
			System.out.println("sorry, redundency of data occured");
			model.addAttribute("excelerror","Sorry, File Can't Uploaded ... Redundant UserName Found");
			System.out.println(conflict);
			model.addAttribute("errorcount",conflict);
		}
		return "redirect:getiimstudent";
		
	}
	
	@RequestMapping(value="/getUsernameList" ,method = RequestMethod.POST)
	public void getUsernameList(HttpSession session,HttpServletRequest req,HttpServletResponse res){
		//int rollNumber = Integer.parseInt(req.getParameter("rollNumber"));
		JSONArray jsonArray = kmiimstudentservice.getUsernameList();
		System.out.println("getCVList() :: json array- " + jsonArray);

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
	
	@RequestMapping(value="/getKMAnnouncementPage" ,method = RequestMethod.GET)
	public ModelAndView getKMAnnouncementPage(HttpSession session,HttpServletRequest req){
		int roleId,userId;
		try
		{
		SessionBean sbean=(SessionBean)req.getSession().getAttribute("sessionBean");
		userId=sbean.getUserID();
		 roleId=sbean.getRoleID();
		if(roleId!=6)
		{
			return new ModelAndView("LoginPage");
		}
		}catch(Exception e)
		{
			return new ModelAndView("LoginPage");
		}
		System.out.println("KmIIMStudentController.getKMAnnouncementPage()");
		List<Announcement> allKmAnnouncement= kmiimstudentservice.getALLAnnouncementByKm();
		for(Announcement a:allKmAnnouncement){
			System.out.println("title"+a.getTitle()+"  "+a.getCreatedDate());
		}
		return new ModelAndView("KMAnnouncement","announce",allKmAnnouncement);			
	}
	
	@RequestMapping(value="/addAnnouncementByKM" ,method = RequestMethod.POST)
	public String saveKMAnnouncement(HttpSession session,HttpServletRequest req,Announcement announce){
		System.out.println("KmIIMStudentController.saveKMAnnouncement()");
		SessionBean sessionBean = (SessionBean) req.getSession().getAttribute("sessionBean");		
		announce.setCreatedBy(sessionBean.getUserID());
		kmiimstudentservice.saveKMAnnouncement(announce);
		
		return "redirect:getKMAnnouncementPage";			
	}
	
	@RequestMapping(value="/deleteKMAnnouncement" ,method = RequestMethod.POST)
	public String delteKMAnnouncement(HttpSession session,HttpServletRequest req,Announcement announce){
		System.out.println("KmIIMStudentController.deleteKMAnnouncement()");
		SessionBean sessionBean = (SessionBean) req.getSession().getAttribute("sessionBean");		
		announce.setCreatedBy(sessionBean.getUserID());
		kmiimstudentservice.delteKMAnnouncement(announce.getAnnounmtId());
		
		return "redirect:getKMAnnouncementPage";			
	}
	
}
