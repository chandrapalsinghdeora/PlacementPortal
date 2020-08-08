package com.precise.service;

import java.sql.SQLException;
import java.util.List;
import com.precise.model.CompanyMaster;

public interface CompanyMasterService {
	public String submitCompany(CompanyMaster companyMaster) throws SQLException;
	public List<CompanyMaster> getAllCompany();
	public String editCompanyMasterForm(CompanyMaster companyMaster) throws SQLException ;
	public String deleteCompanyMasterData(CompanyMaster companyMaster);

}
