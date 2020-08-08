package com.precise.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.precise.model.CompanyStatus;
import com.precise.model.GDPI;
import com.precise.model.KmIIMStudent;
import com.precise.model.Nego;
import com.precise.model.SessionBean;
import com.precise.service.NegoService;

@Controller
public class NegoController {
	@Autowired
	NegoService negoService;
	
	ArrayList<Integer> applyid1 = new ArrayList<Integer>();
	int z1=0;
	@RequestMapping(value = "/getNegoScreen", method = RequestMethod.GET)
	public ModelAndView getNegoScreen( HttpServletRequest req,ModelMap modelMap){
		int roleId,userId;
		try
		{
		SessionBean sbean=(SessionBean)req.getSession().getAttribute("sessionBean");
		userId=sbean.getUserID();
		roleId=sbean.getRoleID();
		if(roleId!=48)
		{
			return new ModelAndView("LoginPage");
		}
		}catch(Exception e)
		{
			return new ModelAndView("LoginPage");
		}
		List<Nego> listCompany=negoService.getCompany(userId);
		List<Nego> listCompany1=negoService.getPICompany(userId);
		List<Nego> listCompany2=negoService.getTheInfo();
		List<Nego> listCompany3=negoService.getgdstatus(userId);
		if(listCompany.size()!=0)
		{
		modelMap.addAttribute("wingid",listCompany.get(0).getWingId());
		}
		if(listCompany1.size()!=0)
		{
		modelMap.addAttribute("wingid1",listCompany1.get(0).getWingId());
		}
		String userName=negoService.getuserName(userId);
		modelMap.addAttribute("name",userName);
		req.setAttribute("companyList",listCompany );
		req.setAttribute("companyList1",listCompany1 );
		req.setAttribute("companyList2",listCompany2 );
		req.setAttribute("companyList3",listCompany3 );
		return new ModelAndView("nego");
	}
	@RequestMapping(value = "/getRmName")
	public void getRmName(HttpServletRequest req,HttpServletResponse res)throws IOException{
		int cid=Integer.parseInt(req.getParameter("cid"));
		SessionBean session=(SessionBean)req.getSession().getAttribute("sessionBean");
		int userid=session.getUserID();
		PrintWriter pw=res.getWriter();
		pw.print(negoService.getRmName(cid,userid));
		pw.flush();
		pw.close();
	}
	
	@RequestMapping(value="/getRoleName")
	public void getRoleName(HttpServletRequest req,HttpServletResponse res)throws IOException
	{
		int cid=Integer.parseInt(req.getParameter("cmpId"));
		PrintWriter pw=res.getWriter();
		pw.print(negoService.getRoleName(cid));
		pw.flush();
		pw.close();
	}
	
	@RequestMapping(value="/getRoleDetails")
	public void getRoleDetails(HttpServletRequest req,HttpServletResponse res)throws IOException
	{
		int cid=Integer.parseInt(req.getParameter("roleid"));
		PrintWriter pw=res.getWriter();
		pw.print(negoService.getRoleDetails(cid));
		pw.flush();
		pw.close();
	}
	
	@RequestMapping(value="/getTheList")
	 public void getTheList(HttpServletRequest request,HttpServletResponse response) throws IOException{
		PrintWriter out = response.getWriter();
		int roleid = Integer.parseInt(request.getParameter("roleid"));
		System.out.println("csd:::"+roleid);
		List<Nego> alldata = negoService.getTheList(roleid);
		System.out.println("csdddd:::"+alldata.size());

		String name[] = new String[alldata.size()];
		String hotlist[] = new String[alldata.size()];
		String status[] = new String[alldata.size()];
		Integer rollno[] = new Integer[alldata.size()];
		
		for (int i = 0; i < alldata.size(); i++) {
			name[i] = alldata.get(i).getName();
			hotlist[i] = alldata.get(i).getHotlist();
			status[i] = alldata.get(i).getStatus();
			rollno[i] = alldata.get(i).getRollNumber();
			System.out.println("csdddd::"+alldata.get(i).getStatus());
			if(status[i].equalsIgnoreCase("not eligible"))
			{
			    applyid1.add(alldata.get(i).getApplyId());
			    z1++;
				System.out.println("csd::"+z1);
			}
		}
		
	    if(alldata.size()==0) {      
	        out.println("<p>No Record Found!</p>");   
	       }
	    else{  
	    
	       out.print("<thead><tr><th>Student ID</th><th>Name</th><th>Hotlist</th><th>Status</th></tr></thead>");
	       out.print("<tbody>");
	       for(int i=0;i<alldata.size();i++)
	       {
	       out.print("<tr><td>"+rollno[i]+"</td><td>"+name[i]+"</td><td>"+hotlist[i]+"</td><td>"+status[i]+"</td></tr>");  
	       }  
	       out.print("</tbody>");  
	       }
	}
	
	@RequestMapping(value="/getTheGDList")
	 public void getTheGDList(HttpServletRequest request,HttpServletResponse response) throws IOException{
		PrintWriter out = response.getWriter();
		int roleid = Integer.parseInt(request.getParameter("roleid"));
		System.out.println("csd:::"+roleid);
		List<Nego> alldata = negoService.getTheGDList(roleid);
		System.out.println("csdddd:::"+alldata.size());

		String name[] = new String[alldata.size()];
		String hotlist[] = new String[alldata.size()];
		String status[] = new String[alldata.size()];
		Integer rollno[] = new Integer[alldata.size()];
		
		for (int i = 0; i < alldata.size(); i++) {
			name[i] = alldata.get(i).getName();
			hotlist[i] = alldata.get(i).getHotlist();
			status[i] = alldata.get(i).getStatus();
			rollno[i] = alldata.get(i).getRollNumber();
		}
	    if(alldata.size()==0) {      
	        out.println("<p>No Record Found!</p>");   
	       }
	    else{  
	    
	       out.print("<thead><tr><th>Student ID</th><th>Name</th><th>Status</th></tr></thead>");
	       out.print("<tbody>");
	       for(int i=0;i<alldata.size();i++)
	       {
	       out.print("<tr><td>"+rollno[i]+"</td><td>"+name[i]+/*"</td><td>"+hotlist[i]+*/"</td><td>"+status[i]+"</td></tr>");  
	       }  
	       out.print("</tbody>");  
	       }
	}
	@RequestMapping(value="/updatedescription")
	public String updatedescription(HttpServletRequest req,GDPI gdpi)
	{
		System.out.println("companyid::"+gdpi.getCompanyId()+"round::"+gdpi.getDiffer()+"roleid::"+gdpi.getRoleId()+"des::"+gdpi.getDescription());
		SessionBean sbean=(SessionBean)req.getSession().getAttribute("sessionBean");
	    int userid=sbean.getUserID();
	    negoService.updatedescription(gdpi,userid);
	    return "redirect:getNegoScreen";
	}
	
	
	@RequestMapping(value="/getTheDescription")
	public void getTheDescription(HttpServletRequest req,HttpServletResponse res) throws Exception
	{
		String s1[];
		String s=req.getParameter("roleid");
		s1=s.split("csd");
		int roleid=Integer.parseInt(s1[0]);
		int round=Integer.parseInt(s1[1]);
	    List<GDPI> list=negoService.getTheDescription(roleid,round);
	    JSONArray firmArray=new JSONArray();
	    for(GDPI gd:list)
	    {
	    	JSONObject jobj=new JSONObject();
	    	jobj.put("description",gd.getDescription());
	    	firmArray.put(jobj);
	    }
	    	PrintWriter out=res.getWriter();
	    	out.print(firmArray);
	    	out.flush();
	    	out.close();
	}
	
	@RequestMapping(value="/getStudent")
	public void getStudent(HttpServletRequest req,HttpServletResponse res) throws Exception
	{
		int roleid=Integer.parseInt(req.getParameter("roleid"));
		List<Nego> studentdata = negoService.getTheList(roleid);
		JSONArray firmArray=new JSONArray();
		for(Nego na:studentdata)
		{
			JSONObject jobj=new JSONObject();
			jobj.put("rollno", na.getRollNumber());
			jobj.put("studentname",na.getName());
			firmArray.put(jobj);
		}
		PrintWriter pw=res.getWriter();
		pw.print(firmArray);
		pw.flush();
		pw.close();
	}
  
	@RequestMapping(value="/updateplacementstatus",method=RequestMethod.POST)
	public  @ResponseBody String updateplacementstatus(HttpServletRequest req,HttpServletResponse res,CompanyStatus cs) 
	{
		SessionBean session=(SessionBean)req.getSession().getAttribute("sessionBean");
		int userid=session.getUserID();
		negoService.updateplacementstatus(cs,userid);
		return "success";
	}
	

	@RequestMapping(value="/epupdate",method=RequestMethod.POST)
	public @ResponseBody String studentstatusupdate(HttpServletRequest req,HttpServletResponse res,Nego cs) 
	{
		negoService.studentstatusupdate(cs);
		return "success";
	}
	
	@RequestMapping(value="/getNegoValidation",method=RequestMethod.POST)
	public @ResponseBody int getNegoValidation(HttpServletRequest req,HttpServletResponse res) 
	{
		int appid=Integer.parseInt(req.getParameter("appid"));
		SessionBean session=(SessionBean)req.getSession().getAttribute("sessionBean");
		int userid=session.getUserID();
		int result=negoService.getNegoValidation(appid,userid);
		return result;
	}
	
	@RequestMapping(value = "/getwing")
	public void getwing(HttpServletRequest req,HttpServletResponse res)throws IOException{
		int cid=Integer.parseInt(req.getParameter("cid"));
		PrintWriter pw=res.getWriter();
		pw.print(negoService.getwing(cid));
		pw.flush();
		pw.close();
	}
	
	@RequestMapping(value="/getnoofpanel1")
	public void noofpanel(HttpServletRequest req,HttpServletResponse res)throws IOException{
		int cid=Integer.parseInt(req.getParameter("appid"));
		int x=negoService.noofpanel(cid);
		System.out.println("hello:::"+x);
		JSONArray firmArray=new JSONArray();
		JSONObject jobj=new JSONObject();
		jobj.put("noofpanel", x);
		firmArray.put(jobj);
		PrintWriter pw=res.getWriter();
		pw.print(firmArray);
		pw.flush();
		pw.close();
		
	}
	
	@RequestMapping(value="/uploadExcelNego", method=RequestMethod.POST)
	public String uploadExcelStudent(ModelMap model, KmIIMStudent student,HttpServletRequest request) throws Exception{
		SessionBean session=(SessionBean)request.getSession().getAttribute("sessionBean");
		int userid=session.getUserID();
		Object c=new Object();
		c=0;
		String uploadRootPath = request.getServletContext().getInitParameter("uploadExcelFile");
		System.out.println("uploadRootPath=" + uploadRootPath);
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
			Object[] d = new Object[3];
			int i = 0;

			while (cellIterator.hasNext() && i<3) {
				Cell cell = cellIterator.next();
 				if(cell!=null ){
 	            		Double doubleValue = cell.getNumericCellValue();
	            		d[i] = doubleValue.intValue();
	        }
  				i = i + 1;
			}
			if((d[0]!=c))
				l.add(d);
		}
			for (int j = 0; j < l.size(); j++) {
				Object[] a = (Object[]) (l.get(j));
				negoService.insertIntoStudent(a,userid);
			}
		
		return "redirect:getNegoScreen";
		
	}
	
	@RequestMapping(value="/gdstatusupdate")
	public String gdstatusupdate(Nego nego,HttpServletRequest req,HttpServletResponse res){
		String csd=nego.getGdupdatestatus();
		SessionBean session=(SessionBean)req.getSession().getAttribute("sessionBean");
		int userid=session.getUserID();
		System.out.println("hello:::"+csd);
		negoService.gdstatusupdate(nego,userid);
		return "redirect:getNegoScreen";
	}
	
	@RequestMapping(value="/geteffectivepreference")
	public void geteffectivepreference(HttpServletRequest req,HttpServletResponse res)throws IOException
	{
		String s=req.getParameter("rollno");
		String s1[]=s.split("csd");
		int rollno=(Integer.parseInt(s1[0]));
		int roleid=(Integer.parseInt(s1[1]));
		PrintWriter pw=res.getWriter();
		pw.print(negoService.geteffectivepreference(rollno,roleid));
		pw.flush();
		pw.close();
	}
	
	@RequestMapping(value = "/updatefirminfo" )
	public String updatefirminfo(HttpServletRequest req,HttpServletResponse res,Nego nego) throws Exception {
 		int roleid=Integer.parseInt(nego.getUpdateinforoleid());
 		System.out.println("cs:::::::"+applyid1.size()+"roleid::"+roleid);
	    negoService.updatefirminfo(applyid1,roleid);
	    return "redirect:getNegoScreen";
	}
	
	@RequestMapping(value="/getfirmoffer")
	public void getfirmoffer(HttpServletRequest req,HttpServletResponse res)throws IOException
	{
		int cid=Integer.parseInt(req.getParameter("cid"));
		int roleid=Integer.parseInt(req.getParameter("roleid"));
		int sid=Integer.parseInt(req.getParameter("sid"));
		PrintWriter pw=res.getWriter();
		pw.print(negoService.getfirmoffer(cid,roleid,sid));
		pw.flush();
		pw.close();
	}
}
