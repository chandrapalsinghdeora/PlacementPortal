package com.precise.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;

import com.precise.model.CompanyStatus;
import com.precise.model.GDPI;
import com.precise.model.Nego;

public interface NegoService {
	public List<Nego> getCompany(int negoId);

	public String getuserName(int userId);

	public JSONArray getRmName(int cid, int userid);

	public JSONArray getRoleName(int cid);

	public JSONArray getRoleDetails(int cid);

	public List<Nego> getPICompany(int userId);

	public List<Nego> getTheList(int roleid);

	public List<Nego> getTheGDList(int roleid);

	public void updatedescription(GDPI gdpi, int userid);

	public List<GDPI> getTheDescription(int roleid, int round);

	public void updateplacementstatus(CompanyStatus cs, int userid);

	public void studentstatusupdate(Nego cs);

	public int getNegoValidation(int appid, int userid);

	public JSONArray getwing(int cid);

	public int noofpanel(int cid);

	public List<Nego> getTheInfo();

	public List<Nego> getgdstatus(int userId);
	public String insertIntoStudent(Object[] studentData,int userid) throws SQLException;

	public void gdstatusupdate(Nego nego, int userid);

	public JSONArray geteffectivepreference(int rollno, int roleid);

	public void updatefirminfo(ArrayList<Integer> applyid1, int roleid);

	public  JSONArray getfirmoffer(int cid, int roleid, int sid);

	

}
