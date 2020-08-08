package com.precise.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.precise.model.OfferDream;
import com.precise.model.SessionBean;
import com.precise.service.OfferDreamService;

@Controller
public class OfferDreamController {
	@Autowired
	OfferDreamService odservice;
@RequestMapping(value="/offerdream")
public ModelAndView offerdream(HttpServletRequest request)
{
	int roleId;
	try
	{
	SessionBean sbean=(SessionBean)request.getSession().getAttribute("sessionBean");
	roleId=sbean.getRoleID();
	if(roleId!=6)
	{
		return new ModelAndView("LoginPage");
	}
	}catch(Exception e)
	{
		return new ModelAndView("LoginPage");
	}
	ModelAndView model=new ModelAndView("uploadOfferDream");
	return model;
}

@RequestMapping(value="/offerexcel", method=RequestMethod.POST)
public String uploadExcelStudent(ModelMap model, OfferDream od,HttpServletRequest request) throws Exception{
	/*int conflict;
	int flag=0;
	Object c=new Object();
	c=0;
	HashSet<String> set=new HashSet<String>();  
	String uploadRootPath = request.getServletContext().getInitParameter("uploadExcelFile");
	System.out.println("uploadRootPath=" + uploadRootPath);
	SessionBean sessionBean = (SessionBean) request.getSession().getAttribute("sessionBean");
	int userid=sessionBean.getUserID();
	File uploadRootDir = new File(uploadRootPath);
	if (!uploadRootDir.exists()) {
		uploadRootDir.mkdirs();
	}
	MultipartFile fileData = od.getFile();
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
	FileInputStream file = new FileInputStream(uploadedFiles);
	XSSFWorkbook workbook = new XSSFWorkbook(file);
	XSSFSheet sheet = workbook.getSheetAt(0);
	Iterator<Row> rowIterator = sheet.iterator();
	List<Object[]> l = new ArrayList<Object[]>();
	if (rowIterator.hasNext()) {
		rowIterator.next();
	}
	while (rowIterator.hasNext()) {
		Row row = rowIterator.next();
		Iterator<Cell> cellIterator = row.cellIterator();
		Object[] d = new Object[4];
		int i = 0;

		while (cellIterator.hasNext() && i<4) {
			Cell cell = cellIterator.next();
			if(cell!=null ){
            	if(i!=1)
            	{
            		Double doubleValue = cell.getNumericCellValue();
            		d[i] = doubleValue.intValue();
            	}
            	else
            		d[i] = cell.getStringCellValue();
        }
			i = i + 1;
		}
		if((d[0]!=c))
			l.add(d);
	}
	for (int j = 0; j < (l.size()); j++) {
		Object[] a = (Object[]) (l.get(j));
		for (int k = j+1; k < l.size(); k++) {
			Object[] b = (Object[]) (l.get(k));
			if(a[0].equals(b[0])&&a[3].equals(b[3])){
				flag=1;
			}
		}
	}
	conflict = l.size()-set.size();
	if(flag==0){
		for (int j = 0; j < l.size(); j++) {
			Object[] a = (Object[]) (l.get(j));
			if(od.getDiffer()==1)
			odservice.insertIntoOffer(a,userid);
			if(od.getDiffer()==2)
			odservice.insertIntoUpdateApplication(a,userid);
		}
	}else{
		System.out.println("sorry, redundency of data occured");
		model.addAttribute("offerdream","Sorry, File Can't Uploaded ... Redundant data Found");
		model.addAttribute("errorcount",conflict);
	}
	return "redirect:offerdream";*/
	
	int conflict;
	int flag=0;
	Object c=new Object();
	c=0;
	HashSet<String> set=new HashSet<String>();  
	String uploadRootPath = request.getServletContext().getInitParameter("uploadExcelFile");
	System.out.println("uploadRootPath=" + uploadRootPath);
	SessionBean sessionBean = (SessionBean) request.getSession().getAttribute("sessionBean");
	int userid=sessionBean.getUserID();
	File uploadRootDir = new File(uploadRootPath);
	if (!uploadRootDir.exists()) {
		uploadRootDir.mkdirs();
	}
	MultipartFile fileData = od.getFile();
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
	FileInputStream file = new FileInputStream(uploadedFiles);
	XSSFWorkbook workbook = new XSSFWorkbook(file);
	XSSFSheet sheet = workbook.getSheetAt(0);
	Iterator<Row> rowIterator = sheet.iterator();
	List<Object[]> l = new ArrayList<Object[]>();
	if (rowIterator.hasNext()) {
		rowIterator.next();
	}
	if(od.getDiffer()==2)
	{
	while (rowIterator.hasNext()) {
		Row row = rowIterator.next();
		Iterator<Cell> cellIterator = row.cellIterator();
		Object[] d = new Object[4];
		int i = 0;

		while (cellIterator.hasNext() && i<4) {
			Cell cell = cellIterator.next();
			if(cell!=null ){
            	if(i!=1)
            	{
            		Double doubleValue = cell.getNumericCellValue();
            		d[i] = doubleValue.intValue();
            	}
            	else
            		d[i] = cell.getStringCellValue();
        }
			i = i + 1;
		}
		if((d[0]!=c))
			l.add(d);
	}
	for (int j = 0; j < (l.size()); j++) {
		Object[] a = (Object[]) (l.get(j));
		for (int k = j+1; k < l.size(); k++) {
			Object[] b = (Object[]) (l.get(k));
			if(a[0].equals(b[0])&&a[3].equals(b[3])){
				flag=1;
			}
		}
	}
	conflict = l.size()-set.size();
	if(flag==0){
		for (int j = 0; j < l.size(); j++) {
			Object[] a = (Object[]) (l.get(j));
		/*	if(od.getDiffer()==1)
			odservice.insertIntoOffer(a,userid);
			if(od.getDiffer()==2)*/
			odservice.insertIntoUpdateApplication(a,userid);
		}
	}else{
		System.out.println("sorry, redundency of data occured");
		model.addAttribute("offerdream","Sorry, File Can't Uploaded ... Redundant data Found");
		model.addAttribute("errorcount",conflict);
	}
	}
	else
	{
		while (rowIterator.hasNext()) {
			Row row = rowIterator.next();
			Iterator<Cell> cellIterator = row.cellIterator();
			Object[] d = new Object[5];
			int i = 0;

			while (cellIterator.hasNext() && i<5) {
				Cell cell = cellIterator.next();
				if(cell!=null ){
	            	if(i!=1 && i!=4)
	            	{
	            		Double doubleValue = cell.getNumericCellValue();
	            		d[i] = doubleValue.intValue();
	            	}
	            	else
	            		d[i] = cell.getStringCellValue();
	        }
				i = i + 1;
			}
			if((d[0]!=c))
				l.add(d);
		}
		for (int j = 0; j < (l.size()); j++) {
			Object[] a = (Object[]) (l.get(j));
			for (int k = j+1; k < l.size(); k++) {
				Object[] b = (Object[]) (l.get(k));
				if(a[0].equals(b[0])&&a[3].equals(b[3])){
					flag=1;
				}
			}
		}
		conflict = l.size()-set.size();
		if(flag==0){
			for (int j = 0; j < l.size(); j++) {
				Object[] a = (Object[]) (l.get(j));
				//if(od.getDiffer()==1)
				odservice.insertIntoOffer(a,userid);
				/*if(od.getDiffer()==2)
				odservice.insertIntoUpdateApplication(a,userid);*/
			}
		}else{
			System.out.println("sorry, redundency of data occured");
			model.addAttribute("offerdream","Sorry, File Can't Uploaded ... Redundant data Found");
			model.addAttribute("errorcount",conflict);
		}
	}
	return "redirect:offerdream";
	
}
}
