package com.precise.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.precise.model.Cluster;
import com.precise.model.SessionBean;
import com.precise.model.WingTracker;
import com.precise.service.AdminService;
import com.precise.service.ClusterService;
import com.precise.service.PanelAllocationService;
import com.precise.service.WingTrackerService;

@Controller
public class WinkTrackerController {
	@Autowired
	ClusterService clusterService;
    @Autowired
    AdminService adminService;
    @Autowired
    WingTrackerService wingTrackerService;  
    @Autowired
	PanelAllocationService pservice;
	
	@RequestMapping(value = "/getWinTracker")
 	public ModelAndView getWinTracker(HttpServletRequest req) {
		int roleId,userid;
		try
		{
		SessionBean sbean=(SessionBean)req.getSession().getAttribute("sessionBean");
		userid=sbean.getUserID();
		roleId=sbean.getRoleID();
		if(roleId!=21)
		{
			return new ModelAndView("LoginPage");
		}
		}catch(Exception e)
		{
			return new ModelAndView("LoginPage");
		}
		List<Cluster> ClusterList = clusterService.getAllCluster();
		Map<Integer,String> placecomStudentList=adminService.getPlaceComStudentList();
		req.setAttribute("placecomerList", placecomStudentList);
		List<WingTracker> sedulerList=wingTrackerService.getAllSchdular();
		req.setAttribute("sedulerList", sedulerList);
	/*	List<String> list1=new ArrayList<String>();
		Set<Integer> list2=placecomStudentList.keySet();
		List<Integer> list5=new ArrayList<Integer>(list2);
		Collection<String> list6=placecomStudentList.values();
		List<String> list3=new ArrayList<String>(list6);
		List<String> list4=new ArrayList<String>();
		for(int i=0;i<4;i++)
		{
			String s1=new String();
			s1=sedulerList.get(i).getSedulerId()+"#"+sedulerList.get(i).getSedulerName();
			list1.add(s1);
		}
		req.setAttribute("list1", list1);
		for(int i=0;i<list2.size();i++)
		{
			String s2=new String();
			s2=list5.get(i)+"#"+list3.get(i);
			list4.add(s2);
		}
		req.setAttribute("list4", list4);
		System.out.println("csd:::"+list4);*/
		List<WingTracker>wingRoomList=wingTrackerService.getWingRoomDetails();
		List<WingTracker>wing1= new ArrayList<>();
		List<WingTracker>wing2= new ArrayList<>();
		List<WingTracker>wing3= new ArrayList<>();
		List<WingTracker>wing4= new ArrayList<>();
		List<WingTracker>wing5= new ArrayList<>();
		List<WingTracker>wing6= new ArrayList<>();
		List<WingTracker>wing7= new ArrayList<>();
		List<WingTracker>wing8= new ArrayList<>();
		List<WingTracker>wing9= new ArrayList<>();
		List<WingTracker>wing10= new ArrayList<>();
		List<WingTracker>wing11= new ArrayList<>();
		List<WingTracker>wing12= new ArrayList<>();
		for (WingTracker wingTracker : wingRoomList) {
			if(wingTracker.getWingId()==1)
				wing1.add(wingTracker);
			if(wingTracker.getWingId()==2)
				wing2.add(wingTracker);
			if(wingTracker.getWingId()==3)
				wing3.add(wingTracker);
			if(wingTracker.getWingId()==4)
				wing4.add(wingTracker);
			if(wingTracker.getWingId()==5)
				wing5.add(wingTracker);
			if(wingTracker.getWingId()==6)
				wing6.add(wingTracker);
			if(wingTracker.getWingId()==7)
				wing7.add(wingTracker);
			if(wingTracker.getWingId()==8)
				wing8.add(wingTracker);
			if(wingTracker.getWingId()==9)
				wing9.add(wingTracker);
			if(wingTracker.getWingId()==10)
				wing10.add(wingTracker);
			if(wingTracker.getWingId()==11)
				wing11.add(wingTracker);
			if(wingTracker.getWingId()==12)
				wing12.add(wingTracker);
			
		}
		req.setAttribute("wing1",wing1);
		req.setAttribute("wing2",wing2);
		req.setAttribute("wing3",wing3);
		req.setAttribute("wing4",wing4);
		req.setAttribute("wing5",wing5);
		req.setAttribute("wing6",wing6);
		req.setAttribute("wing7",wing7);
		req.setAttribute("wing8",wing8);
		req.setAttribute("wing9",wing9);
		req.setAttribute("wing10",wing10);
		req.setAttribute("wing11",wing11);
		req.setAttribute("wing12",wing12);
		
		req.setAttribute("wingRoomList", wingRoomList);
		
		return new ModelAndView("WingTracker","clusterList",ClusterList);
		
	}
	
	@RequestMapping(value = "/saveRoomAllocation")
	public String saveRoomAllocation(HttpServletRequest req,WingTracker wing ){
	System.out.println("clusterid::"+wing.getClusterId());
		wingTrackerService.saveRoomAllocation(wing);
		return "redirect:getWinTracker";
	}
	
	@RequestMapping(value = "/deleteroomallocation")
	public String deleteroomallocation(HttpServletRequest req,WingTracker wing ){
	System.out.println("wingroomid::"+wing.getWingRoomId());
		wingTrackerService.deleteroomallocation(wing);
		return "redirect:getWinTracker";
	}
	@RequestMapping(value = "/updateRoomAllocation")
	public String updateRoomAllocation(HttpServletRequest req,WingTracker wing ){
		wingTrackerService.updateRoomAllocation(wing);
		return "redirect:getWinTracker";
	}

	@RequestMapping(value = "/saveNegoAndSchedular")
	public String saveNegoAndSchedular(HttpServletRequest req,WingTracker wing ){
		SessionBean sbean=(SessionBean)req.getSession().getAttribute("sessionBean");
		int userId=sbean.getUserID();
		/*for(int i=0;i<wing.getNego().size();i++)
		{
			System.out.println("key::"+wing.getSaveupdate().get(i));
		}*/
		wingTrackerService.saveNegoAndSchedular(wing,userId);
		return "redirect:getWinTracker";
	}
}
