package com.precise.service;

import java.sql.SQLException;
import java.util.List;

import org.json.JSONArray;

import com.precise.model.Designation;
import com.precise.model.MailCredential;

public interface DesignationService {

	public List<Designation> getAllDesignation();
	public List<MailCredential> getAllMail();
	public void updateMailingpwd(int userid,MailCredential designation);
	public String saveDesignation(Designation designation) throws SQLException;
	public String editDesignation(Designation designation) throws SQLException ;
	public String deleteDesignationData(Designation designation);
	public JSONArray getAllDesignationData();
	public String updateDesignation(Designation designation,String companyRoleId) throws SQLException;
}
