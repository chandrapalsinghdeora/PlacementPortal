package com.precise.service;

import java.util.List;

import com.precise.model.CVReleted;
import com.precise.model.Company;
import com.precise.model.PPO;
import com.precise.model.RoleCompany;

public interface CompanyService {

	public void insertCompanyForm(Company company,String commanCoverTitleName);
	public List<Company> getAllCompanyDetail(int userId,String status);
	public Company getCompanyDetail(int cmpId);
	public List<CVReleted> getAllCVReleted(int userId);
	public List<RoleCompany> getAllCompanyRole(int compId);
	public void insertCompanyPrefence(Company company);
	public void withdarw(Company company);
	public String getStudentTitleName(int userId,String cmpName);
	public String getRoleName(int roleId);
//	public void updateForum(Forum forum);
//	public void deleteForum(int forumId);
	public int checkForLateral(int userId);
	public int workExp(int userId);
	public String checkStatus(int userId);
	public List<PPO> getCount(int userId);
	public List<PPO> sameCohort(int userId);
	public List<Company> getOfferStatus(int userId);
	public List<Company> getApplyList(int userId);
	public void withdrawFirm(Company company);
	public int getApplyNumber(int userId);
	public void withdrawAll(Company company);
	public String withdrawPartial(Company company);
	public String getClusterName(Company company);

}
