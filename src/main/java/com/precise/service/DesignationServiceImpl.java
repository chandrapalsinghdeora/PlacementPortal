package com.precise.service;

import java.sql.SQLException;
import java.util.List;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.JsonArray;
import com.precise.dao.DesignationDao;
import com.precise.model.Designation;
import com.precise.model.MailCredential;


@Service
public class DesignationServiceImpl implements DesignationService{

	
	@Autowired
	DesignationDao designationdao;
	
	
	@Override
	public List<Designation> getAllDesignation() {
		// TODO Auto-generated method stub
		return designationdao.getAllDesignation();
	}

	@Override
	public String saveDesignation(Designation designation) throws SQLException {
		// TODO Auto-generated method stub
		return designationdao.saveDesignation(designation);
	}

	@Override
	public String editDesignation(Designation designation) throws SQLException {
		// TODO Auto-generated method stub
		return designationdao.editDesignation(designation);
	}

	@Override
	public String deleteDesignationData(Designation designation) {
		// TODO Auto-generated method stub
		return designationdao.deleteDesignationData(designation);
	}
	
	@Override
	public JSONArray getAllDesignationData(){
		return designationdao.getAllDesignationData();
	}

	@Override
	public String updateDesignation(Designation designation,String companyRoleId) throws SQLException{
		return designationdao.updateDesignation(designation,companyRoleId);
	}

	@Override
	public List<MailCredential> getAllMail() {
		return designationdao.getAllMail();
	}

	@Override
	public void updateMailingpwd(int userid,MailCredential designation) {
		designationdao.updateMailingpwd(userid,designation);
		
	}
}
