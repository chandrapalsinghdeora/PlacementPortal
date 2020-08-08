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
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import com.precise.model.Admin;
import com.precise.model.OfferDream;
import com.precise.model.SessionBean;
import com.precise.service.AdminService;
import com.precise.service.PPOService;

@Controller
public class PPOController {
	int x=0;
     @Autowired
     PPOService ppoService;
     
     
    @RequestMapping(value = "/uploadPPO")
 	public ModelAndView uploadPPO(HttpServletRequest req) {
    	if(x==0)
    	req.setAttribute("csd","");	
    	else
    	req.setAttribute("csd","Sorry, File Can't Uploaded ... Wrong data Found");
    	x=0;
 		return new ModelAndView("uploadPPO");
 	}
    
    @RequestMapping(value="/uploadPPO1", method=RequestMethod.POST)
    public String uploadPPO1(ModelMap model, OfferDream od,HttpServletRequest request) throws Exception{
    	int flag=1;
    	Object c=new Object();
    	c=0;
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
    		Object[] d = new Object[5];
    		int i = 0;

    		while (cellIterator.hasNext() && i<5) {
    			Cell cell = cellIterator.next();
    			if(cell!=null ){
                	if(i==0)
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
    		String s=(String)a[4];
    		String s1=(String)a[2];
    			if((s.equalsIgnoreCase("Accept")||s.equalsIgnoreCase("Reject")||s.equalsIgnoreCase("Hold"))&&(s1.equalsIgnoreCase("c1")||s1.equalsIgnoreCase("c2")||s1.equalsIgnoreCase("c3"))){
    				flag=0;
    			}
    		}
    	if(flag==0){
    		for (int j = 0; j < l.size(); j++) {
    			Object[] a = (Object[]) (l.get(j));
    			ppoService.insertIntoPPO(a,userid);
    		}
    }else{
    		System.out.println("sorry, redundency of data occured");
    		x=1;
    		
    	}
    	return "redirect:uploadPPO";
    	
    }
}
