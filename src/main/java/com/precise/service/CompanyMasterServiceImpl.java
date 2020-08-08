package com.precise.service;

import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.precise.dao.CompanyMasterDAO;
import com.precise.model.CompanyMaster;

@Service("companyservice")
@Transactional
public class CompanyMasterServiceImpl implements CompanyMasterService {
	@Autowired
	CompanyMasterDAO companyMasterDAO;
	
	public String submitCompany(CompanyMaster companyMaster) throws SQLException{
		return companyMasterDAO.submitCompany(companyMaster) ;
	}
	
	@Override
	public List<CompanyMaster> getAllCompany() {
		return companyMasterDAO.getAllCompany();
	}
	
	@Override
	public String editCompanyMasterForm(CompanyMaster companyMaster) throws SQLException {
		return companyMasterDAO.editCompanyMasterForm(companyMaster) ;
	}
	@Override
	public String deleteCompanyMasterData(CompanyMaster companyMaster){
		return companyMasterDAO.deleteCompanyMasterData(companyMaster);
	}
	
	
}
