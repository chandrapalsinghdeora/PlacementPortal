package com.precise.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.precise.model.ManageApp;

@Repository
public class ManageAppDAOImpl implements ManageAppDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<ManageApp> getApplication(int userId) {
		// TODO Auto-generated method stub

		System.out.println("ManageAppDAOImpl.getApplication()");
		List<ManageApp> list = new ArrayList<ManageApp>();
		final String procedureCall = "{call sp_InsertCompany(?,?)}";
		Connection connection = null;
		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "ManageApp");
			callableSt.setInt(2, userId);
			ResultSet rs = callableSt.executeQuery();
			int x = 1;
			while (rs.next()) {
				ManageApp manageApp = new ManageApp();
				manageApp.setSnum(x);
				manageApp.setAppid(rs.getInt("Pk_CompanyRoleId"));
				manageApp.setPanelStatus(getNotification(rs.getInt("Pk_CompanyRoleId")));
				manageApp.setAppShareid(rs.getInt("Pk_ApplicationId"));
				manageApp.setFirm(rs.getString("CompanyName"));
				manageApp.setRole(rs.getString("DesignationName"));
				manageApp.setAppStatus(rs.getString("ApplicationStatus"));
				manageApp.setCompid(rs.getInt("Pk_CompanyId"));
				manageApp.setRmName(rs.getString("userName"));
				manageApp.setNoOfSelectionRounds(rs.getInt("NoOfSelectionRound"));
				// System.out.println(rs.getString("ApplicationStatus")+"appstatus");
				String manage;
				if (rs.getString("ApplicationStatus").toString().equals("pending")) {
					manageApp.setManage(" ");
					manageApp.setEdit(" ");
					manageApp.setShare(" ");
					manageApp.setQuery(" ");
					manageApp.setVerify(" ");
				} else {
					manageApp.setManage("Manage");
					manageApp.setEdit("Edit");
					manageApp.setShare("Share");
					manageApp.setQuery("Query");
					manageApp.setVerify("Verify");
				}

				list.add(manageApp);
				// System.out.println("forumname---- " +
				// rs.getString("CompanyName"));
				x = x + 1;
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
		return list;
	}

	public int getNotification(int companyRoleId){
		int status = 0;
		try{
			String procedureCall = "{call proc_PanelAlocation(?,?)}";
			Connection connection = null;
			connection = jdbcTemplate.getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "getNotificationstatus");
			callableSt.setInt(2, companyRoleId);
			
			ResultSet rs = callableSt.executeQuery();
			if (rs.next()) {
				if(rs.getString("NotificationStatus").equals("Send To HR"))
					status = 1;
			}
		}catch(Exception e ){
			e.printStackTrace();
		}
		return status;
	}
	
	public JSONArray getAllRMData() {
		final String procedureCall = "{call proc_UserRelation(?)}";
		Connection connection = null;
		JSONArray jsonArray = new JSONArray();
		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "selectRM");
			ResultSet rs = callableSt.executeQuery();
			int x = 1;
			while (rs.next()) {
				JSONObject jObject = new JSONObject();
				jObject.put("userId", rs.getInt("FK_IIMStudent"));
				jObject.put("userName", rs.getString("EmailId"));
				jsonArray.put(jObject);
			}
		} catch (Exception e) {
			System.out.println("Exception in getAllRMData ::" + e.getMessage());
			e.printStackTrace();
		}
		return jsonArray;
	}

	public String updateShareWith(ManageApp manageApp, String appid, String userid) throws SQLException {
		try {
			Connection connection = jdbcTemplate.getDataSource().getConnection();
			String procedureCall = "{call sp_InsertCompany(?,?,?,?,?,?,?,?,?,?, ?,?,?,?,?,?,?,?,?,?, ?,?,?,?,?,?,?,?,?,?, ?,?,?,?,?,?)}";
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "Share");
			callableSt.setInt(2, Integer.parseInt(appid));
			callableSt.setInt(3, 0);
			callableSt.setInt(4, 0);
			callableSt.setInt(5, 0);
			callableSt.setInt(6, 0);
			callableSt.setString(7, null);
			callableSt.setInt(8, 0);
			callableSt.setString(9, null);
			callableSt.setString(10, null);
			callableSt.setInt(11, 0);
			callableSt.setInt(12, 0);
			callableSt.setBoolean(13, false);
			callableSt.setInt(14, 0);
			callableSt.setString(15, null);
			callableSt.setString(16, null);
			callableSt.setString(17, null);
			callableSt.setString(18, null);
			callableSt.setInt(19, 0);
			callableSt.setInt(20, 0);
			callableSt.setString(21, null);
			callableSt.setInt(22, 0);
			callableSt.setString(23, null);
			callableSt.setString(24, null);
			callableSt.setString(25, null);
			callableSt.setInt(26, 0);
			callableSt.setBoolean(27, true);
			callableSt.setString(28, null);
			callableSt.setString(29, null);
			callableSt.setString(30, null);
			callableSt.setInt(31, 0);
			callableSt.setString(32, null);
			callableSt.setInt(33, 1);
			callableSt.setInt(34, Integer.parseInt(userid));
			callableSt.setInt(35, 0);
			callableSt.setString(36, null);

			boolean abc = callableSt.execute();
		} catch (Exception e) {
			System.out.println("share with data");
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String updatePanelMailStatus(ManageApp manageApp) {
		String procedureCall = "";
		Connection connection = null;
		String result = "error";
		if(manageApp.getPanelStatus()==1){
			try {
				connection = jdbcTemplate.getDataSource().getConnection();
				connection.setAutoCommit(false);
				procedureCall = "{call proc_PanelAlocation(?,?,?)}";
				CallableStatement callableSt = connection.prepareCall(procedureCall);
				callableSt.setString(1, "updatePanelstatus");
				callableSt.setInt(2, manageApp.getAppid());
				callableSt.setInt(3, manageApp.getUserId());
				callableSt.execute();
				connection.commit();
				result = "success";
			} catch (Exception e) {
				e.printStackTrace();
				try {
					connection.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} finally {
				if (connection != null)
					try {
						connection.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
			}
		}
		return result;
	}

}
