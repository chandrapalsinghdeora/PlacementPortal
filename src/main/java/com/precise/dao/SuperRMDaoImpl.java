package com.precise.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hashids.Hashids;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Repository;

import com.precise.dbconnection.ConnectionDao;
import com.precise.model.HotList;
import com.precise.model.ManageApp;
import com.precise.model.ShortList;


@Repository
public class SuperRMDaoImpl extends ConnectionDao implements SuperRMDao {

	@Override
	public JSONArray getRoleByCompanyId(int compId) {
		JSONArray roleList=new JSONArray();
		final String procedureCall = "{call sp_InsertCompany(?,?,?)}";
		Connection connection = null;
		try {
			connection = getJdbcTemplate().getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "selectRoleByCompRoleId");
			callableSt.setInt(2,0);
			callableSt.setInt(3,compId);
			ResultSet rs = callableSt.executeQuery();
			int x=1;
			while (rs.next()) {
				JSONObject jobj=new JSONObject();
				jobj.put("companyRoleId", rs.getInt("Pk_CompanyRoleId"));
				jobj.put("companyRoleName", rs.getString("CompanyRoleName"));
				roleList.put(jobj);
				x=x+1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (connection != null)
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		
		return roleList;
	}

	@Override
	public JSONArray getManageAppByCompanyId(int compId) {
		System.out.println("SuperRMDaoImpl.getManageAppByCompanyId()");		
		JSONArray manageList=new JSONArray();
		final String procedureCall = "{call sp_InsertCompany(?,?,?)}";
		Connection connection = null;
		try {
			connection = getJdbcTemplate().getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "ManageAppByCompId");
			callableSt.setInt(2, 0);
			callableSt.setInt(3, compId);
			ResultSet rs = callableSt.executeQuery();
			int x=1;
	 		
			while (rs.next()) {					
				JSONObject mangApp=new JSONObject();
				mangApp.put("snum", x);
				mangApp.put("appid", rs.getInt("Pk_CompanyRoleId"));
				Hashids hashids = new Hashids("comp role id",10);
		 		//mangApp.put("hashAppID","abdcr"+ hashids.encode(rs.getInt("Pk_CompanyRoleId")));
		 		mangApp.put("hashAppID",hashids.encode(rs.getInt("Pk_CompanyRoleId")));
				mangApp.put("appShareid",rs.getInt("Pk_ApplicationId"));
				mangApp.put("firm",rs.getString("CompanyName"));
				mangApp.put("role",rs.getString("DesignationName"));
				mangApp.put("appStatus",rs.getString("ApplicationStatus"));
				mangApp.put("compid",rs.getInt("Pk_CompanyId"));
				mangApp.put("userName",rs.getString("userName"));
				mangApp.put("noOfSelectionRounds",rs.getString("NoOfSelectionRound")==null?0:rs.getInt("NoOfSelectionRound"));
				String manage;
				if(rs.getString("ApplicationStatus").toString().equals("pending"))
				{				
					mangApp.put("manage", " ");
					mangApp.put("edit", " ");
					mangApp.put("share", " ");
					mangApp.put("query", " ");
					mangApp.put("verify", " ");					
				}
				else
				{
					
					mangApp.put("manage", "Manage");
					mangApp.put("edit", "Edit");
					mangApp.put("share", "Share");
					mangApp.put("query", "Query");
					mangApp.put("verify", "Verify");		
				}
				
				manageList.put(mangApp);
				
				x=x+1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (connection != null)
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return manageList;
	}

	@Override
	public void saveShortlistedBySuperRm1(ShortList shortlist) {
		System.out.println("SuperRMDaoImpl.saveShortlistedBySuperRm()");
		List<Integer> shorlistedIds = shortlist.getShortListIdslist();
		List<Integer> notshorlistedIds = shortlist.getNotSelected();
		System.out.println("shorlistedIds-----in save shortlist" + shorlistedIds + "not selected--" + notshorlistedIds);
		Connection connection = null;
		CallableStatement callableStmt = null;
		try {
			connection = getJdbcTemplate().getDataSource().getConnection();
			connection.setAutoCommit(false);
			callableStmt = connection.prepareCall("{call Sp_ShortList(?,?,?,?,?,?,?,?,?,?,?)}");
			for (int i = 0; i < shorlistedIds.size(); i++) {
				
				System.out.println("inside for loop :::::");
				callableStmt.setString(1, "SuperRM1");
				callableStmt.setInt(2, 0);
				callableStmt.setInt(3, 0);
				callableStmt.setInt(4, shorlistedIds.get(i));
				callableStmt.setBoolean(5,false);
				callableStmt.setString(6,null);
				callableStmt.setBoolean(7, false);
				callableStmt.setInt(8, 0);
				callableStmt.setBoolean(9, true);
				callableStmt.setBoolean(10, false);
				callableStmt.setInt(11,shortlist.getCreatedBy());		
				callableStmt.addBatch();
			}			
			callableStmt.executeBatch();

		} catch (SQLException e) {
			System.out.println("Exception in shortlist" + e);			
			e.printStackTrace();
		}
		try {
			callableStmt = connection.prepareCall("{call [Sp_StdGreeting](?,?,?,?,?)}");
			for (int i = 0; i < shorlistedIds.size(); i++) {
				callableStmt.setString(1, "Insert");
				callableStmt.setString(2, shortlist.getGreetings());
				callableStmt.setString(3, "");
				callableStmt.setString(4, "RM");
				callableStmt.setString(5, "Shortlisted");
				// callableStmt.setDate(6, "");
				callableStmt.addBatch();
			}
			callableStmt.executeBatch();
			connection.commit();
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void saveShortlistedBySuperRm2(ShortList shortlist) {
		System.out.println("SuperRMDaoImpl.saveShortlistedBySuperRm()");
		List<Integer> shorlistedIds = shortlist.getShortListIdslist();
		List<Integer> notshorlistedIds = shortlist.getNotSelected();
		System.out.println("shorlistedIds-----in save shortlist" + shorlistedIds + "not selected--" + notshorlistedIds);
		Connection connection = null;
		CallableStatement callableStmt = null;
		try {
			connection = getJdbcTemplate().getDataSource().getConnection();
			connection.setAutoCommit(false);
			callableStmt = connection.prepareCall("{call Sp_ShortList(?,?,?,?,?,?,?,?,?,?,?,?)}");
			for (int i = 0; i < shorlistedIds.size(); i++) {
				callableStmt.setString(1, "SuperRM2");
				callableStmt.setInt(2, 0);
				callableStmt.setInt(3, 0);
				callableStmt.setInt(4, shorlistedIds.get(i));
				callableStmt.setBoolean(5, true);
				callableStmt.setString(6,null);
				callableStmt.setBoolean(7, false);
				callableStmt.setInt(8, 0);
				callableStmt.setBoolean(9, false);
				callableStmt.setBoolean(10, true);
				callableStmt.setInt(11,0);	
				callableStmt.setInt(12,shortlist.getCreatedBy());	
				callableStmt.addBatch();
			}			
			callableStmt.executeBatch();

		} catch (SQLException e) {
			System.out.println("Exception in shortlist" + e);			
			e.printStackTrace();
		}
		try {
			callableStmt = connection.prepareCall("{call [Sp_StdGreeting](?,?,?,?,?)}");
			for (int i = 0; i < shorlistedIds.size(); i++) {
				callableStmt.setString(1, "Insert");
				callableStmt.setString(2, shortlist.getGreetings());
				callableStmt.setString(3, "");
				callableStmt.setString(4, "RM");
				callableStmt.setString(5, "Shortlisted");
				// callableStmt.setDate(6, "");
				callableStmt.addBatch();
			}
			callableStmt.executeBatch();
			connection.commit();
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		
	}

	@Override
	public List<ShortList> getReleasedShortlistedDataByRole(int cmpRoleId) {
		System.out.println("SuperRMDaoImpl.getReleasedShortlistedDataByRole()-"+cmpRoleId);
		List<ShortList> shortList = new ArrayList<ShortList>();
		String procedureCall = "{call Sp_ShortList(?,?,?,?,?,?,?,?)}";
		Connection connection = null;
		try {
			connection = getJdbcTemplate().getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "ViewShortlistRelease");
			callableSt.setInt(2, 0);
			callableSt.setInt(3, 0);
			callableSt.setInt(4, 0);
			callableSt.setBoolean(5, false);
			callableSt.setString(6, null);
			callableSt.setBoolean(7, false);
			callableSt.setInt(8, cmpRoleId);
			ResultSet rs = callableSt.executeQuery();

			while (rs.next()) {
				ShortList shortlist = new ShortList();
				shortlist.setApplyId(rs.getInt("Pk_ApplyId"));
				shortlist.setShortListId(rs.getInt("PK_ShortListId"));
				shortlist.setEmailID(rs.getString("EmailID"));
				shortlist.setName(rs.getString("Name"));
				shortlist.setPreference(rs.getInt("Preference"));
				shortlist.setRollNumber(rs.getInt("RollNumber"));
				shortlist.setStatus(rs.getString("Status"));
				shortlist.setShortList(rs.getBoolean("IsShortList"));
				shortlist.setCmpRoleId(rs.getInt("Fk_RoleId"));
				shortList.add(shortlist);
				//System.out.println("get shortlistedd---- " + rs.getInt("Pk_ApplyId"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (connection != null)
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return shortList;
	}

	@Override
	public void saveHotlistedBySuperRM1(HotList hotlist) {
		System.out.println("SuperRMDaoImpl.saveHotlistedBySuperRM1()");
		List<Integer> shorlistedIds = hotlist.getHotListIdslist();
		List<Integer> notHotlistedIds = hotlist.getNotSelected();
		System.out.println(
				"HotListDAOImpl.saveHotlistedBySuperRM1() hotlisted ids" + shorlistedIds + "nothotlistedIds--" + notHotlistedIds);
		Connection connection = null;

		CallableStatement callableStmt = null;
		try {
			connection = getJdbcTemplate().getDataSource().getConnection();
			connection.setAutoCommit(false);
			callableStmt = connection.prepareCall("{call Sp_hotList(?,?,?,?,?,?,?,?,?,?,?,?)}");
			for (int i = 0; i < shorlistedIds.size(); i++) {
				callableStmt.setString(1, "SuperRM1");
				callableStmt.setInt(2, shorlistedIds.get(i));
				callableStmt.setInt(3, 0);
				callableStmt.setInt(4, 0);
				callableStmt.setBoolean(5, true);
				callableStmt.setString(6, "RM");
				callableStmt.setBoolean(7, true);
				callableStmt.setInt(8, 0);
				callableStmt.setBoolean(9, false);
				callableStmt.setBoolean(10, true);
				callableStmt.setBoolean(11, false);
				callableStmt.setInt(12, hotlist.getCreatedBy());				          
				callableStmt.addBatch();
			}
			
			callableStmt.executeBatch();

		} catch (SQLException e) {
			System.out.println("Exception in shortlistRelease" + e);
			// connection.rollback();
			e.printStackTrace();
		}

		try {
			callableStmt = connection.prepareCall("{call [Sp_StdGreeting](?,?,?,?,?)}");
			for (int i = 0; i < shorlistedIds.size(); i++) {
				callableStmt.setString(1, "Insert");
				callableStmt.setString(2, hotlist.getGreetingsHotlist());
				callableStmt.setString(3, "");
				callableStmt.setString(4, "Super RM1");
				callableStmt.setString(5, "Hotlisted");
				// callableStmt.setDate(6, "");
				callableStmt.addBatch();
			}
			callableStmt.executeBatch();
			connection.commit();
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}

	@Override
	public void saveHotlistedBySuperRM2(HotList hotlist) {
		System.out.println("SuperRMDaoImpl.saveHotlistedBySuperRM2()");
		List<Integer> shorlistedIds = hotlist.getHotListIdslist();
		List<Integer> notHotlistedIds = hotlist.getNotSelected();
		System.out.println(
				"HotListDAOImpl.saveHotlistedBySuperRM2() hotlisted ids" + shorlistedIds + "nothotlistedIds--" + notHotlistedIds);
		Connection connection = null;

		CallableStatement callableStmt = null;
		try {
			connection = getJdbcTemplate().getDataSource().getConnection();
			connection.setAutoCommit(false);
			callableStmt = connection.prepareCall("{call Sp_hotList(?,?,?,?,?,?,?,?,?,?,?,?,?)}");
			for (int i = 0; i < shorlistedIds.size(); i++) {
				callableStmt.setString(1, "SuperRM2");
				callableStmt.setInt(2, shorlistedIds.get(i));
				callableStmt.setInt(3, 0);
				callableStmt.setInt(4, 0);
				callableStmt.setBoolean(5, true);
				callableStmt.setString(6, "RM");
				callableStmt.setBoolean(7, true);
				callableStmt.setInt(8, 0);
				callableStmt.setBoolean(9, false);
				callableStmt.setBoolean(10, false );
				callableStmt.setBoolean(11, true);
				callableStmt.setInt(12,0);
				callableStmt.setInt(13, hotlist.getCreatedBy());
				callableStmt.addBatch();
			}
			
			callableStmt.executeBatch();

		} catch (SQLException e) {
			System.out.println("Exception in shortlistRelease" + e);
			// connection.rollback();
			e.printStackTrace();
		}

		try {
			callableStmt = connection.prepareCall("{call [Sp_StdGreeting](?,?,?,?,?)}");
			for (int i = 0; i < shorlistedIds.size(); i++) {
				callableStmt.setString(1, "Insert");
				callableStmt.setString(2, hotlist.getGreetingsHotlist());
				callableStmt.setString(3, "");
				callableStmt.setString(4, "Super RM2");
				callableStmt.setString(5, "Hotlisted");
				// callableStmt.setDate(6, "");
				callableStmt.addBatch();
			}
			callableStmt.executeBatch();
			connection.commit();
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}

	@Override
	public List<HotList> saveHotlistedBySuperRM2(int cmpRoleId) {
		System.out.println("HotDaoImpl.getShortlistedDataByRole()");
		List<HotList> hotList = new ArrayList<HotList>();
		String procedureCall = "{call Sp_hotList(?,?,?,?,?,?,?,?)}";
		Connection connection = null;
		try {
			connection = getJdbcTemplate().getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "ViewHotListRelease"); //SelecthotListBySuperRM
			callableSt.setInt(2, 0);
			callableSt.setInt(3, 0);
			callableSt.setInt(4, 0);
			callableSt.setString(5, null);
			callableSt.setBoolean(6, false);
			callableSt.setInt(7, 0);
			callableSt.setInt(8, cmpRoleId);
			ResultSet rs = callableSt.executeQuery();

			while (rs.next()) {
				HotList hot = new HotList();
				hot.setApplyId(rs.getInt("Pk_ApplyId"));
				hot.setHotListId(rs.getInt("Pk_HotListId"));
				hot.setEmailID(rs.getString("EmailID"));
				hot.setName(rs.getString("Name"));
				hot.setPreference(rs.getInt("Preference"));
				hot.setRollNumber(rs.getInt("RollNumber"));
				hot.setStatus(rs.getString("Status"));
				hot.setShortList(rs.getBoolean("IsHostList"));
				hotList.add(hot);
				//System.out.println("get shortlistedd---- " + rs.getInt("Pk_ApplyId"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (connection != null)
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return hotList;
	}

	@Override
	public void removeShortlistedBySuperRm2(ShortList shortlist) {
		System.out.println("SuperRMDaoImpl.saveShortlistedBySuperRm()");
		List<Integer> shorlistedIds = shortlist.getShortListIdslist();
		List<Integer> notshorlistedIds = shortlist.getNotSelected();
		System.out.println("shorlistedIds-----in save shortlist" + shorlistedIds + "not selected--" + notshorlistedIds);
		Connection connection = null;
		CallableStatement callableStmt = null;
		try {
			connection = getJdbcTemplate().getDataSource().getConnection();
			connection.setAutoCommit(false);
			callableStmt = connection.prepareCall("{call Sp_ShortList(?,?,?,?,?,?,?,?,?,?,?,?)}");
			for (int i = 0; i < shorlistedIds.size(); i++) {
				callableStmt.setString(1, "SuperRM2");
				callableStmt.setInt(2, 0);
				callableStmt.setInt(3, 0);
				callableStmt.setInt(4, shorlistedIds.get(i));
				callableStmt.setBoolean(5, true);
				callableStmt.setString(6,null);
				callableStmt.setBoolean(7, false);
				callableStmt.setInt(8, 0);
				callableStmt.setBoolean(9, false);
				callableStmt.setBoolean(10, false);
				callableStmt.setInt(11,0);	
				callableStmt.setInt(12,shortlist.getCreatedBy());	
				callableStmt.addBatch();
			}			
			callableStmt.executeBatch();

		} catch (SQLException e) {
			System.out.println("Exception in shortlist" + e);			
			e.printStackTrace();
		}
		try {
			callableStmt = connection.prepareCall("{call [Sp_StdGreeting](?,?,?,?,?)}");
			for (int i = 0; i < shorlistedIds.size(); i++) {
				callableStmt.setString(1, "Insert");
				callableStmt.setString(2, shortlist.getGreetings());
				callableStmt.setString(3, "");
				callableStmt.setString(4, "RM");
				callableStmt.setString(5, "Shortlisted");
				// callableStmt.setDate(6, "");
				callableStmt.addBatch();
			}
			callableStmt.executeBatch();
			connection.commit();
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		
	}

	@Override
	public void removeShortlistedBySuperRm1(ShortList shortlist) {
		System.out.println("SuperRMDaoImpl.saveShortlistedBySuperRm()");
		List<Integer> shorlistedIds = shortlist.getShortListIdslist();
		List<Integer> notshorlistedIds = shortlist.getNotSelected();
		System.out.println("shorlistedIds-----in save shortlist" + shorlistedIds + "not selected--" + notshorlistedIds);
		Connection connection = null;
		CallableStatement callableStmt = null;
		try {
			connection = getJdbcTemplate().getDataSource().getConnection();
			connection.setAutoCommit(false);
			callableStmt = connection.prepareCall("{call Sp_ShortList(?,?,?,?,?,?,?,?,?,?,?)}");
			for (int i = 0; i < shorlistedIds.size(); i++) {
				
				System.out.println("inside for loop :::::");
				callableStmt.setString(1, "SuperRM1");
				callableStmt.setInt(2, 0);
				callableStmt.setInt(3, 0);
				callableStmt.setInt(4, shorlistedIds.get(i));
				callableStmt.setBoolean(5,false);
				callableStmt.setString(6,null);
				callableStmt.setBoolean(7, false);
				callableStmt.setInt(8, 0);
				callableStmt.setBoolean(9, false);
				callableStmt.setBoolean(10, false);
				callableStmt.setInt(11,shortlist.getCreatedBy());		
				callableStmt.addBatch();
			}			
			callableStmt.executeBatch();

		} catch (SQLException e) {
			System.out.println("Exception in shortlist" + e);			
			e.printStackTrace();
		}
		try {
			callableStmt = connection.prepareCall("{call [Sp_StdGreeting](?,?,?,?,?)}");
			for (int i = 0; i < shorlistedIds.size(); i++) {
				callableStmt.setString(1, "Insert");
				callableStmt.setString(2, shortlist.getGreetings());
				callableStmt.setString(3, "");
				callableStmt.setString(4, "RM");
				callableStmt.setString(5, "Shortlisted");
				// callableStmt.setDate(6, "");
				callableStmt.addBatch();
			}
			callableStmt.executeBatch();
			connection.commit();
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
			e.printStackTrace();		
	}

	}
}


