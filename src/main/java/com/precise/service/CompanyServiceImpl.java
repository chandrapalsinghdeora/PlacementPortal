package com.precise.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.precise.dao.CompanyDAO;
import com.precise.model.CVReleted;
import com.precise.model.Company;
import com.precise.model.PPO;
import com.precise.model.RoleCompany;

@Service("companyService")
@Transactional
public class CompanyServiceImpl implements CompanyService {

	@Autowired
	private CompanyDAO commpanydao;
		
	@Override
	public List<Company> getAllCompanyDetail(int userId,String status) {
		//System.out.println("CompanyServiceImpl.getAllCompanyDetail");
		return commpanydao.getAllCompanyDetail(userId,status);
	}

	@Override
	public List<CVReleted> getAllCVReleted(int userId) {
		// TODO Auto-generated method stub
		return commpanydao.getAllCVReleted(userId);
	}

	@Override
	public List<RoleCompany> getAllCompanyRole(int compId) {
		// TODO Auto-generated method stub
		return commpanydao.getAllCompanyRole(compId);
	}

	@Override
	public void insertCompanyForm(Company company,String commanCoverTitleName) {
		//System.out.println("CompanyServiceImpl.insertCompanyForm()");
		commpanydao.insertCompanyForm(company,commanCoverTitleName);
		
	}

	@Override
	public void insertCompanyPrefence(Company company) {
		//System.out.println("CompanyServiceImpl.insertCompanyPrefence()");
		commpanydao.insertCompanyPrefence(company);
	}
	
	public Company getCompanyDetail(int cmpId){
		return commpanydao.getCompanyDetail(cmpId);
	}
	
	@Override
	public void withdarw(Company company) {
		//System.out.println("CompanyServiceImpl.withdarw()");
		commpanydao.withdarw(company);
		
	}

	@Override
	public String getStudentTitleName(int userId, String cmpName) {
		return commpanydao.getStudentTitleName(userId,cmpName);
	}

	@Override
	public String getRoleName(int roleId) {
		return commpanydao.getRoleName(roleId);
	}

	@Override
	public int checkForLateral(int userId) {
		return commpanydao.checkForLateral(userId);
	}

	@Override
	public int workExp(int userId) {
		return commpanydao.workExp(userId);
	}

	@Override
	public String checkStatus(int userId) {
		return commpanydao.checkStatus(userId);
	}

	@Override
	public List<PPO> getCount(int userId) {
		return commpanydao.getCount(userId);
	}

	@Override
	public List<PPO> sameCohort(int userId) {
		return commpanydao.sameCohort(userId);
	}

	@Override
	public List<Company> getOfferStatus(int userId) {
		return commpanydao.getOfferStatus(userId);
		
	}

	@Override
	public List<Company> getApplyList(int userId) {
		return commpanydao.getApplyList(userId);
	}

	@Override
	public void withdrawFirm(Company company) {
		commpanydao.withdrawFirm(company);
		
	}

	@Override
	public int getApplyNumber(int userId) {
		return commpanydao.getApplyNumber(userId);
	}

	@Override
	public void withdrawAll(Company company) {
		commpanydao.withdrawAll(company);
		
	}

	@Override
	public String withdrawPartial(Company company) {
		return commpanydao.withdrawPartial(company);
		
	}

	@Override
	public String getClusterName(Company company) {
		return commpanydao.getClusterName(company);
	}
	

}
