package com.precise.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.precise.mail.SendMail;
import com.precise.model.Cluster;
import com.precise.model.Nego;
import com.precise.model.PanelAllocation;
import com.precise.model.ProcessManagement;
import com.precise.model.SessionBean;
import com.precise.model.WingTracker;
import com.precise.service.AdminService;
import com.precise.service.ClusterService;
import com.precise.service.PanelAllocationService;
import com.precise.service.ProcessService;
import com.precise.service.WingTrackerService;

@Controller
public class PanelAllocationController {
	@Autowired
	ClusterService clusterService;
	@Autowired
	ProcessService processService;
	@Autowired
	PanelAllocationService pservice;
	@Autowired
	SendMail sendMail;
	@Autowired
	AdminService adminService;
	@Autowired
	WingTrackerService wingTrackerService;  
	@RequestMapping(value = "/getPanelAllocationPage")
 	public ModelAndView getPanelAllocationPage(HttpServletRequest req) {
 		//System.out.println("PanelAllocationController.getPanelAllocationPage()"); 
		int roleId,userid;
		try
		{
		SessionBean sbean=(SessionBean)req.getSession().getAttribute("sessionBean");
		userid=sbean.getUserID();
		roleId=sbean.getRoleID();
		System.out.println("csddddddddddddd::"+roleId);
		if(roleId!=21)
		{
			return new ModelAndView("LoginPage");
		}
		}catch(Exception e)
		{
			return new ModelAndView("LoginPage");
		}
		List<ProcessManagement> ProcessList = processService.getAllProcess();
 		//List<Cluster> ClusterList = clusterService.getAllCluster(); 
 		List<PanelAllocation> firmList=pservice.getPanelAllotedList();
 		req.setAttribute("firmList", firmList);
 		List<Cluster> ClusterList = clusterService.getAllCluster();
		req.setAttribute("clusterList", ClusterList);
 		return new ModelAndView("panelAllocation","processList",ProcessList);
 	}
	
	
	@RequestMapping(value = "/getClusterNameByCid")
	public void getClusterNameByProcessId(HttpServletRequest req,HttpServletResponse res){
		int pid=Integer.parseInt(req.getParameter("pid"));		
		List<PanelAllocation> clusterlist=pservice.getClusterNameByPid(pid);		
		JSONArray firmArray=new JSONArray();
		for(PanelAllocation pa:clusterlist){
			JSONObject jobj=new JSONObject();
			jobj.put("clusterId", pa.getClusterId());
			jobj.put("clusterName", pa.getClusterName());
			firmArray.put(jobj);
		}
		//System.out.println("firm array--"+firmArray);
		
		 try {
				PrintWriter out=res.getWriter();
				out.print(firmArray);
				out.flush();
				out.close();
			} catch (IOException e) {
				System.out.println("exception occured in firm"+e);
				e.printStackTrace();
			}
	}
	
	
	@RequestMapping(value = "/getFirmNameByCid")
	public void getFirmNameByClusterId(HttpServletRequest req,HttpServletResponse res){
		int cid=Integer.parseInt(req.getParameter("cid"));	
		int roomid=Integer.parseInt(req.getParameter("roomid"));
		System.out.println(cid+"::csd::"+roomid);
		List<PanelAllocation> firmList=pservice.getFirmNameByCid(cid,roomid);		
		JSONArray firmArray=new JSONArray();
		for(PanelAllocation pa:firmList){
			JSONObject jobj=new JSONObject();
			jobj.put("firmId", pa.getFirmId());
			jobj.put("firmName", pa.getFirmName());
			firmArray.put(jobj);
		}
		//System.out.println("firm array--"+firmArray);
		
		 try {
				PrintWriter out=res.getWriter();
				out.print(firmArray);
				out.flush();
				out.close();
			} catch (IOException e) {
				System.out.println("exception occured in firm"+e);
				e.printStackTrace();
			}
	}
	
	@RequestMapping(value = "/getFirmNameByCidInPanel")
	public void getFirmNameByCidInPanel(HttpServletRequest req,HttpServletResponse res){
		int cid=Integer.parseInt(req.getParameter("cid"));		
		List<PanelAllocation> firmList=pservice.getFirmNameByCidInPanel(cid);		
		JSONArray firmArray=new JSONArray();
		for(PanelAllocation pa:firmList){
			JSONObject jobj=new JSONObject();
			jobj.put("firmId", pa.getFirmId());
			jobj.put("firmName", pa.getFirmName());
			firmArray.put(jobj);
		}
		//System.out.println("firm array--"+firmArray);
		
		 try {
				PrintWriter out=res.getWriter();
				out.print(firmArray);
				out.flush();
				out.close();
			} catch (IOException e) {
				System.out.println("exception occured in firm"+e);
				e.printStackTrace();
			}
	}
	
	@RequestMapping(value = "/getRoleNameByAppId")
	public void getRoleNameByFirmId(HttpServletRequest req,HttpServletResponse res){
		int appId=Integer.parseInt(req.getParameter("appId"));
		System.out.println("aappiidd-"+appId);
		List<PanelAllocation> roleList=pservice.getRoleNameByFirmId(appId);		
		JSONArray firmArray=new JSONArray();
		for(PanelAllocation pa:roleList){
			JSONObject jobj=new JSONObject();
			jobj.put("roleId", pa.getRoleId());
			jobj.put("roleName", pa.getRoleName());
			firmArray.put(jobj);
		}
		//System.out.println("firm array--"+firmArray);
		
		 try {
				PrintWriter out=res.getWriter();
				out.print(firmArray);
				out.flush();
				out.close();
			} catch (IOException e) {
				System.out.println("exception occured in firm"+e);
				e.printStackTrace();
			}
	}
	
	@RequestMapping(value = "/getTheClusterDetails")
	public void getTheClusterDetails(HttpServletRequest req,HttpServletResponse res){
		int clusterId=Integer.parseInt(req.getParameter("clusterId"));
		System.out.println("clusterId-"+clusterId);
		List<WingTracker> list=pservice.getTheClusterDetails(clusterId);
		//List<WingTracker> sedulerList=wingTrackerService.getAllSchdular();;
		JSONArray firmArray=new JSONArray();
		//JSONArray firmArray2=new JSONArray();
	    //  firmArray[0]=new JSONArray();
	     // firmArray[1]=new JSONArray();
		for(WingTracker pa:list){
			JSONObject jobj=new JSONObject();
			jobj.put("negoid", pa.getPlacecomerId());
			jobj.put("schedularid", pa.getSedulerId1());
			jobj.put("wingid", pa.getWingId());
			jobj.put("key", pa.getWingRoomId());
			System.out.println("csddddddddd:::"+ pa.getWingRoomId());
			firmArray.put(jobj);
		}
		/*for(WingTracker pa:sedulerList){
			JSONObject jobj=new JSONObject();
			jobj.put("sid", pa.getSedulerId());
			jobj.put("sname", pa.getSedulerName());
			firmArray[1].put(jobj);
		}*/
		//System.out.println("firm array--"+firmArray);
		
		 try {
				PrintWriter out=res.getWriter();
				out.print(firmArray);
				//out.print(firmArray2);
				out.flush();
				out.close();
			} catch (IOException e) {
				System.out.println("exception occured in firm"+e);
				e.printStackTrace();
			}
	}
	
	@RequestMapping(value = "/getRoleNameByAppIdInPanel")
	public void getRoleNameByAppIdInPanel(HttpServletRequest req,HttpServletResponse res){
		int appId=Integer.parseInt(req.getParameter("appId"));
		System.out.println("aappiidd-"+appId);
		List<PanelAllocation> roleList=pservice.getRoleNameByAppIdInPanel(appId);		
		JSONArray firmArray=new JSONArray();
		for(PanelAllocation pa:roleList){
			JSONObject jobj=new JSONObject();
			jobj.put("roleId", pa.getRoleId());
			jobj.put("roleName", pa.getRoleName());
			firmArray.put(jobj);
		}
		//System.out.println("firm array--"+firmArray);
		
		 try {
				PrintWriter out=res.getWriter();
				out.print(firmArray);
				out.flush();
				out.close();
			} catch (IOException e) {
				System.out.println("exception occured in firm"+e);
				e.printStackTrace();
			}
	}
	
	@RequestMapping(value="/getRoleInDD")
	public void getRoleInDD(HttpServletRequest request,HttpServletResponse response)
	{
		int firmid=Integer.parseInt(request.getParameter("firmid"));
		System.out.println("firmid::"+firmid);
		List<PanelAllocation> list=pservice.getRoleInDD(firmid);
		System.out.println("list::"+list.size());
		JSONArray firmArray=new JSONArray();
		for(PanelAllocation pa:list)
		{
			JSONObject jobj=new JSONObject();
			jobj.put("roleid",pa.getRoleId());
			jobj.put("rolename",pa.getRoleName());
			jobj.put("maproleid",pa.getMappedRoleId());
			jobj.put("maprolename",pa.getMapRoleName());
			firmArray.put(jobj);
		}
		try
		{
		 PrintWriter pout=response.getWriter();
		 pout.print(firmArray);
		 pout.flush();
		 pout.close();
		}catch (IOException e) {
			System.out.println("exception occured in firm"+e);
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/getThetime")
	public void getThetime(HttpServletRequest req,HttpServletResponse res){
		String s=req.getParameter("appId");
		String s1[]=s.split("csd");
		int roleId=Integer.parseInt(s1[0]);
		int roomId=Integer.parseInt(s1[1]);
		System.out.println("roleId-"+roleId+" roomId"+roomId);
		List<PanelAllocation> roleList=pservice.getThetime(roleId,roomId);	
		//System.out.println("csdstarttime:::"+roleList.get(0).getPanelStartTime());
		JSONArray firmArray=new JSONArray();
		for(PanelAllocation pa:roleList){
			JSONObject jobj=new JSONObject();
			jobj.put("starttime", pa.getPanelStartTime());
			jobj.put("endtime", pa.getPanelEndTime());
			firmArray.put(jobj);
		}
		//System.out.println("firm array--"+firmArray);
		
		 try {
				PrintWriter out=res.getWriter();
				out.print(firmArray);
				out.flush();
				out.close();
			} catch (IOException e) {
				System.out.println("exception occured in firm"+e);
				e.printStackTrace();
			}
	}
	
	@RequestMapping(value = "/getThePaneltime")
	public void getThePaneltime(HttpServletRequest req,HttpServletResponse res){
		String s=req.getParameter("appId");
		String s1[]=s.split("csd");
		int roleId=Integer.parseInt(s1[0]);
		int roomId=Integer.parseInt(s1[1]);
		System.out.println("roleId-"+roleId+" roomId"+roomId);
		List<PanelAllocation> roleList=pservice.getThePaneltime(roleId,roomId);	
		//System.out.println("csdstarttime:::"+roleList.get(0).getPanelStartTime());
		JSONArray firmArray=new JSONArray();
		for(PanelAllocation pa:roleList){
			JSONObject jobj=new JSONObject();
			jobj.put("starttime", pa.getPanelStartTime());
			jobj.put("endtime", pa.getPanelEndTime());
			firmArray.put(jobj);
		}
		//System.out.println("firm array--"+firmArray);
		
		 try {
				PrintWriter out=res.getWriter();
				out.print(firmArray);
				out.flush();
				out.close();
			} catch (IOException e) {
				System.out.println("exception occured in firm"+e);
				e.printStackTrace();
			}
	}
	
	@RequestMapping(value = "/geAlltime")
	public void geAlltime(HttpServletRequest req,HttpServletResponse res){
		//int roomId=Integer.parseInt(req.getParameter("roomid"));
		String appid=req.getParameter("roomid");
		String[] str=appid.split("csd");
		int roomId=Integer.parseInt(str[0]);
		int cid=Integer.parseInt(str[1]);
		int rid=Integer.parseInt(str[2]);
		String s1[],s2[];
		int x,y,z,p,r,s;
		System.out.println("csdddddddddddroomId::"+roomId+"firmid::"+cid+"roleid::"+rid);
		List<PanelAllocation> roleList=pservice.geAlltime(roomId,cid,rid);	
		//System.out.println("csdstarttime:::"+roleList.get(0).getPanelStartTime());
		JSONArray firmArray=new JSONArray();
		for(PanelAllocation pa:roleList){
			JSONObject jobj=new JSONObject();
			s1= pa.getPanelStartTime().split(":");
			s2= pa.getPanelEndTime().split(":");
			x=Integer.parseInt(s1[0]);
			y=Integer.parseInt(s1[1]);
			z=Integer.parseInt(s2[0]);
			p=Integer.parseInt(s2[1]);
			s=60*x+y;
			r=60*z+p;
			System.out.println("csdddstarttime::"+s);
			jobj.put("starttime", s);
			jobj.put("endtime", r);
			firmArray.put(jobj);
		}
		 try {
				PrintWriter out=res.getWriter();
				out.print(firmArray);
				out.flush();
				out.close();
			} catch (IOException e) {
				System.out.println("exception occured in firm"+e);
				e.printStackTrace();
			}
	}
	
	/*@RequestMapping(value="/geAllPanel", method=RequestMethod.POST )
		public @ResponseBody int getAllPanel(HttpServletRequest req,HttpServletResponse res){
		int x=pservice.geAllPanel();
		System.out.println("value::"+x);
		return x;
	}*/
	
	@RequestMapping(value="/geAllPanel" , method=RequestMethod.POST)
	public void getDesignationLsist(HttpServletRequest req,HttpServletResponse response) throws IOException{
		int appId=Integer.parseInt(req.getParameter("appid"));
		PrintWriter pw=response.getWriter();
		pw.print(pservice.geAllThePanel(appId));
		pw.flush();
		pw.close();
	}

	
	/*@RequestMapping(value = "/geAllPanel")
	public void geAllPanel(HttpServletRequest req,HttpServletResponse res){
		int x=pservice.geAllPanel();
		System.out.println("value::"+x);
		JSONObject jobj=new JSONObject();			
		jobj.put("csd", x);	
		 try {
				PrintWriter out=res.getWriter();
				out.print(jobj);
				out.flush();
				out.close();
			} catch (IOException e) {
				System.out.println("exception occured in RM"+e);
				e.printStackTrace();
			}
		
	}*/
	
	@RequestMapping(value = "/getRMNameByFirmId")
	public void getRMNameByFirmId(HttpServletRequest req,HttpServletResponse res){
		int firmId=Integer.parseInt(req.getParameter("firmId"));			
		PanelAllocation rm=pservice.getRmNameByFirmId(firmId);		
		JSONObject jobj=new JSONObject();			
		jobj.put("rmName", rm.getRmName());	
		jobj.put("emailId", rm.getEmailId());				
		 try {
				PrintWriter out=res.getWriter();
				out.print(jobj);
				out.flush();
				out.close();
			} catch (IOException e) {
				System.out.println("exception occured in RM"+e);
				e.printStackTrace();
			}
	}
	
	@RequestMapping(value = "/getPanelDetailsByFirmId")
	public void getPanelDeatilsByAppId(HttpServletRequest req,HttpServletResponse res){
		int firmId=Integer.parseInt(req.getParameter("firmId"));			
		//System.out.println("firmIdin panel update-"+firmId);
		PanelAllocation panel=pservice.getPanelDeatilsByAppId(firmId);
		JSONObject jobj=new JSONObject();
		if(panel.getPanelAllocationId()!=0){
			jobj.put("panelAllocationId", panel.getPanelAllocationId());	
			jobj.put("panelDate", panel.getPanelDate());	
			jobj.put("panelStartTime",panel.getPanelStartTime());
			jobj.put("panelEndTime",panel.getPanelEndTime());
			jobj.put("panel",panel.getPanel());
			jobj.put("extraRooms",panel.getExtraRooms());
			jobj.put("notification",panel.getNotificationStatus());
			jobj.put("isLocked",panel.getIsLocked());
		}else{
			jobj.put("panelAllocationId", panel.getPanelAllocationId());	
		}
		System.out.println("panel details json-"+jobj);
		 try {
				PrintWriter out=res.getWriter();
				out.print(jobj);
				out.flush();
				out.close();
			} catch (IOException e) {
				System.out.println("exception occured in RM"+e);
				e.printStackTrace();
			}
	}

	@RequestMapping(value = "/savePanelAllocation")
	public String savePanelAllocationeByFirmId(HttpServletRequest req,HttpServletResponse res,PanelAllocation pallocation){
		//System.out.println("PanelAllocationController.savePanelAllocationeByFirmId()");
		HttpSession session=req.getSession();
		SessionBean sbean=(SessionBean)session.getAttribute("sessionBean");
		int userId=sbean.getUserID();
		pallocation.setCreatedId(userId);
		String emailid=pallocation.getEmailId();			
		String subject="Panel Alloocation Details";		
		StringBuffer str = new StringBuffer();		
		str.append("Hello "+pallocation.getRmName()+"<br>");
		//str.append("Please find the panel details of  :"+ pallocation.getFirmName() +"<br>");
		//str.append("Cluster Name: "+pallocation.getClusterName()+"<br>");
		str.append("Please find the panel details of  :"+pallocation.getFirmName()+"<br>");
		//str.append("Panel Date: "+pallocation.getPanelDate()+"<br>");
		//System.out.println("+++++++++++++"+pallocation.getRoleName());
		str.append("Role: "+pallocation.getRoleName()+"<br>");
		str.append("Time To Start: "+pallocation.getPanelStartTime()+"<br>");
		//str.append("End Time: "+pallocation.getPanelEndTime()+"<br>");
		str.append("Number of panels: "+pallocation.getPanel()+"<br>");
		str.append("MapRole: "+pallocation.getMapRoleName()+"<br>");
		str.append("Time To Start: "+pallocation.getPanelStartTime()+"<br>");
		//str.append("End Time: "+pallocation.getPanelEndTime()+"<br>");
		str.append("Number of panels: "+pallocation.getPanel()+"<br>");
		//str.append("Extra Rooms: "+pallocation.getExtraRooms()+"<br>");		
		//System.out.println("panleld iddd-"+pallocation.getPanelAllocationId());
		if(pallocation.getIsLocked()==null){
			pallocation.setIsLocked(false);
		}else if(pallocation.getIsLocked()==true){
			pallocation.setIsLocked(true);
		}
		pservice.savePanelAllocation(pallocation);		
		sendMail.sendMail(str.toString(), emailid, subject);
		
		
	    //System.out.println("---- panel is locked--"+pallocation.getIsLocked()+pallocation.getPanel()+"locked--"+req.getParameter("isLocked"));
			
		
		return "redirect:getPanelAllocationPage";
	}
	
	@RequestMapping(value = "/getPanelsByCid")
	public void getPanelsByCid(HttpServletRequest req,HttpServletResponse res) throws Exception{
	/*	int cid=Integer.parseInt(req.getParameter("cid"));		
		//System.out.println("cluster id--"+cid);		
		List<PanelAllocation> firmList=pservice.getPanelAllotedListByClusterId(cid);
		//System.out.println("firmlist"+firmList);
		JSONArray firmArray=new JSONArray();
		for(PanelAllocation pa:firmList){
			JSONObject jobj=new JSONObject();
			jobj.put("firmId", pa.getFirmId());
			jobj.put("firmName", pa.getFirmName());
			jobj.put("notificationStatus", pa.getNotificationStatus());
			jobj.put("isLocked", pa.getIsLocked());
			firmArray.put(jobj);
		}
		
		 try {
				PrintWriter out=res.getWriter();
				out.print(firmArray);
				out.flush();
				out.close();
			} catch (IOException e) {
				System.out.println("exception occured in firm"+e);
				e.printStackTrace();
			}
	}*/
	PrintWriter out = res.getWriter();
	int cid=Integer.parseInt(req.getParameter("cid"));
	List<PanelAllocation> firmList=pservice.getPanelAllotedListByClusterId(cid);
	System.out.println("csdddd:::"+firmList.size());

	String firmname[] = new String[firmList.size()];
	String status[] = new String[firmList.size()];
	String locked[] = new String[firmList.size()];
	
	for (int i = 0; i < firmList.size(); i++) {
		firmname[i] = firmList.get(i).getFirmName();
		status[i] = firmList.get(i).getNotificationStatus();
		boolean y= firmList.get(i).getIsLocked();
		if(y==true)
		locked[i]="locked";
		else
			locked[i]="open";
	}
    if(firmList.size()==0) {      
        out.println("<p>No Record Found!</p>");   
       }
    else{  
       for(int i=0;i<firmList.size();i++)
       {
       out.print("<tr><td>"+firmname[i]+"</td><td>"+status[i]+/*"</td><td>"+hotlist[i]+*/"</td><td>"+locked[i]+"</td></tr>");  
       }  
       }
}
}
