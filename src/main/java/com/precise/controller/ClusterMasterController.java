package com.precise.controller;

import java.sql.SQLException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.precise.model.Cluster;
import com.precise.model.SessionBean;
import com.precise.service.ClusterService;


@Controller
public class ClusterMasterController {
	@Autowired
	ClusterService clusterService;
	
	@RequestMapping(value={"/getCluster"},method={RequestMethod.GET})
	public String getCluster(Cluster cluster,HttpServletRequest request) {
		int roleId,userid;
		try
		{
		SessionBean sbean=(SessionBean)request.getSession().getAttribute("sessionBean");
		 userid=sbean.getUserID();
		 roleId=sbean.getRoleID();
		if(roleId!=6 && roleId!=9)
		{
			return "redirect:login";
		}
		}catch(Exception e)
		{
			return "redirect:login";
		}
		List<Cluster> ClusterList = clusterService.getAllCluster();
		request.setAttribute("clusterList", ClusterList);
		return "clusterMaster";
	}
	
	@RequestMapping(value={"/getCluster"},method={RequestMethod.POST})
	public String submitCluster(Cluster cluster,HttpServletRequest request) {
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
				cluster.setCreatedBy(userid);
				List<Cluster> ClusterList = clusterService.getAllCluster();
				request.setAttribute("clusterList", ClusterList);
				if(cluster.getClusterId()==0){
					clusterService.submitCluster(cluster);
				}else{
					clusterService.editClusterForm(cluster);
					System.out.println("update cluster id :: "+cluster.getClusterId());
				}
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		 return "redirect:getCluster";
	}
	
	@RequestMapping(value="/deleteCluster",method={ RequestMethod.POST})
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
		Cluster cluster = new Cluster();
		cluster.setCreatedBy(userid);
		cluster.setClusterId(Integer.parseInt(request.getParameter("deleteClusterId")));
		clusterService.deleteClusterData(cluster);
		return "redirect:getCluster";
	}
	
}
