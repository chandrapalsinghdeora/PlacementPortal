package com.precise.service;

import java.sql.SQLException;
import java.util.List;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.precise.dao.FirmManagementDAO;
import com.precise.model.Cluster;
import com.precise.model.Cohort;
import com.precise.model.CompanyMaster;
import com.precise.model.FirmManagementForm;
import com.precise.model.ProcessManagement;
import com.precise.model.RoleCompany;
import com.precise.model.SelectionRoundMaster;
import com.precise.model.YearMaster;

@Service("FirmManagementService")
@Transactional
public class FirmManagementServiceImpl implements FirmManagementService {
	@Autowired
	private FirmManagementDAO firmManagementDAO;
	
	@Override
	public List<ProcessManagement> getAllProcess() {
		return firmManagementDAO.getAllProcess();
	}
	
	@Override
	public List<Cluster> getAllCluster() {
		return firmManagementDAO.getAllCluster();
	}
	@Override
	public List<Cohort> getAllCohort() {
		return firmManagementDAO.getAllCohort();
	}
	
	@Override
	public List<RoleCompany> getAllRole() {
		return firmManagementDAO.getAllRole();
	}

	@Override
	public List<CompanyMaster> getAllCompany() {
		return firmManagementDAO.getAllCompany();
	}
	
	
	@Override
	public String saveFirmManagementForm(FirmManagementForm firmManagementForm, int userId) throws SQLException {
		
		return firmManagementDAO.saveFirmManagementForm(firmManagementForm,userId);
	}

	@Override
	public List<Cohort> getCohortByClusterId(String cname) {
		return firmManagementDAO.getCohortByClusterId(cname);
	}
	
	@Override
	public List<YearMaster> getAllYear() {
		return firmManagementDAO.getAllYear();
	}
	@Override
	public List<SelectionRoundMaster> getAllRound() {
		return firmManagementDAO.getAllRound();
	}
	
	@Override
	public FirmManagementForm getCompanyDetails(int appId,FirmManagementForm firmMangForm){
		return firmManagementDAO.getCompanyDetails(appId,firmMangForm);
	}
	
	@Override
	public JSONArray getCohortValue(String clusterId){
		return firmManagementDAO.getCohortValues(clusterId);
	}
	
	@Override
	public String deleteRole(String roleId,String appId) throws Exception{
		return firmManagementDAO.deleteRole(roleId,appId);
	}
	
	@Override
	public String updateFirmManagement(FirmManagementForm firmMangForm){
		return firmManagementDAO.updateFirmManagement(firmMangForm);
	}
	
	@Override
	public String deleteUrl(String url){
		return firmManagementDAO.updateDeleteUrl(url);
	}
	
	@Override
	public String deleteAddFiles(String addFileId){
		return firmManagementDAO.deleteAdditionalFiles(addFileId);
	}

	@Override
	public Boolean checkCompanyClusterCohort(String compName, String cluster, int cohortId,int year) {
		
		return firmManagementDAO.checkCompanyClusterCohort(compName,cluster,cohortId,year);
	}
	
	
}
