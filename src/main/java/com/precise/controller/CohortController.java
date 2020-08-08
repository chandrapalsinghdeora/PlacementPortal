package com.precise.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.precise.model.Cluster;
import com.precise.model.Cohort;
import com.precise.model.SessionBean;
import com.precise.service.ClusterService;
import com.precise.service.CohortService;

@Controller
public class CohortController {
	@Autowired
	CohortService cohortService;
	
	@RequestMapping(value={"/getCohort"},method={RequestMethod.GET})
	public String getCohort(Cohort cohort,HttpServletRequest request) {
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
		List<Cohort> CohortList = cohortService.getAllCohort();
		Map<Integer,String> clusterMap=cohortService.getAllClusters();
		request.setAttribute("cluster", clusterMap);
		request.setAttribute("cohortList", CohortList);
		return "CohortMaster";
	}

	@RequestMapping(value={"/getCohort"},method={RequestMethod.POST})
	public String submitCohort(Cohort cohort,HttpServletRequest request) {
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
			if(cohort!=null){
				cohort.setCreatedBy(userid);
				List<Cohort> CohortList = cohortService.getAllCohort();
				request.setAttribute("cohortList", CohortList);
				if(cohort.getCohortId()==0){
					cohortService.submitCohort(cohort);
				}else{
					cohortService.editCohortForm(cohort);
					System.out.println("update cluster id :: "+cohort.getCohortId());
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		 return "redirect:getCohort";
	}
	
	@RequestMapping(value="/deleteCohort",method={ RequestMethod.POST})
	public String deleteCohortData(HttpServletRequest request){
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
		Cohort cohort = new Cohort();
		cohort.setCreatedBy(userid);
		cohort.setCohortId(Integer.parseInt(request.getParameter("deleteCohortId")));
		cohortService.deleteCohortData(cohort);
		return "redirect:getCohort";
	}
	
}
