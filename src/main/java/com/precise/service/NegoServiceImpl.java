package com.precise.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.precise.dao.NegoDAO;
import com.precise.model.CompanyStatus;
import com.precise.model.GDPI;
import com.precise.model.Nego;

@Service
public class NegoServiceImpl implements NegoService {
	@Autowired
	NegoDAO negodao;

	@Override
	public List<Nego> getCompany(int negoId) {
		
		return negodao.getCompany(negoId);
	}

	@Override
	public String getuserName(int userId) {
		// TODO Auto-generated method stub
		return negodao.getuserName(userId);
	}

	@Override
	public JSONArray getRmName(int cid,int userid) {
		// TODO Auto-generated method stub
		return negodao.getRmName(cid,userid);
	}

	@Override
	public JSONArray getRoleName(int cid) {
		return negodao.getRoleName(cid);
	}

	@Override
	public JSONArray getRoleDetails(int cid) {
		return negodao.getRoleDetails(cid);
	}

	@Override
	public List<Nego> getPICompany(int userId) {
		return negodao.getPICompany(userId);
	}

	@Override
	public List<Nego> getTheList(int roleid) {
		return negodao.getTheList(roleid);
	}

	@Override
	public List<Nego> getTheGDList(int roleid) {
		return negodao.getTheGDList(roleid);
	}

	@Override
	public void updatedescription(GDPI gdpi, int userid) {
	  negodao.updatedescription(gdpi,userid);
	}

	@Override
	public List<GDPI> getTheDescription(int roleid, int round) {
		return negodao.getTheDescription(roleid,round);
	}

	@Override
	public void updateplacementstatus(CompanyStatus cs,int userid) {
		 negodao.updateplacementstatus(cs,userid);
	}

	@Override
	public void studentstatusupdate(Nego cs) {
		 negodao.studentstatusupdate(cs);
	}

	@Override
	public int getNegoValidation(int appid, int userid) {
		return negodao.getNegoValidation(appid,userid);
	}

	@Override
	public JSONArray getwing(int cid) {
		return negodao.getwing(cid);
	}

	@Override
	public int noofpanel(int cid) {
		return negodao.noofpanel(cid);
	}

	@Override
	public List<Nego> getTheInfo() {
		return negodao.getTheInfo();
	}

	@Override
	public List<Nego> getgdstatus(int userid) {
		return negodao.getgdstatus(userid);
	}

	@Override
	public String insertIntoStudent(Object[] studentData,int userid) throws SQLException {
		return negodao.insertIntoStudent(studentData,userid);
	}

	@Override
	public void gdstatusupdate(Nego nego, int userid) {
		negodao.gdstatusupdate(nego,userid);
	}

	@Override
	public JSONArray geteffectivepreference(int rollno, int roleid) {
		return negodao.geteffectivepreference(rollno,roleid);
	}

	@Override
	public void updatefirminfo(ArrayList<Integer> applyid1,int roleid) {
		negodao.updatefirminfo(applyid1,roleid);
	}

	@Override
	public JSONArray getfirmoffer(int cid, int roleid, int sid) {
		return negodao.getfirmoffer(cid,roleid,sid);
	}
	


}
