package com.precise.dao;

import java.sql.SQLException;
import java.util.List;

import org.json.JSONArray;

import com.precise.model.Cluster;
import com.precise.model.Cohort;
import com.precise.model.CompanyMaster;
import com.precise.model.FirmManagementForm;
import com.precise.model.Forum;
import com.precise.model.ProcessManagement;
import com.precise.model.RoleCompany;
import com.precise.model.SelectionRoundMaster;
import com.precise.model.YearMaster;

public interface FirmManagementDAO {
	public List<ProcessManagement> getAllProcess();
	public List<Cluster> getAllCluster();
	public List<Cohort> getAllCohort();
	public List<CompanyMaster> getAllCompany();
	public List<RoleCompany> getAllRole();
	public String saveFirmManagementForm(FirmManagementForm firmManagementForm,int userId)throws SQLException;
	public List<Cohort> getCohortByClusterId(String cname);
	public List<YearMaster> getAllYear();
	public List<SelectionRoundMaster> getAllRound();	
	public FirmManagementForm getCompanyDetails(int appId,FirmManagementForm firmMangForm);	
	public JSONArray getCohortValues(String clusterId);	
	public String deleteRole(String roleId,String appId) throws Exception;	
	public String updateFirmManagement(FirmManagementForm firmManagementForm);	
	public String updateDeleteUrl(String urlId);
	public String deleteAdditionalFiles(String addFileId);	
	public Boolean checkCompanyClusterCohort(String compName, String cluster, int cohortId,int year);
}
	
