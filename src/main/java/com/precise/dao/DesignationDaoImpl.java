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

import com.precise.model.Designation;
import com.precise.model.MailCredential;

@Repository
public class DesignationDaoImpl implements DesignationDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<Designation> getAllDesignation() {
		System.out.println("DesignationDaoImpl.getAllDesignation()--");
		List<Designation> list = new ArrayList<Designation>();
		final String procedureCall = "{call proc_Master(?)}";
		Connection connection = null;
		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "selectdesignation");
			ResultSet rs = callableSt.executeQuery();
			int x = 1;
			while (rs.next()) {
				Designation designation = new Designation();

				designation.setDesignationid(rs.getInt("Pk_DesignationId"));
				designation.setDesignationname(rs.getString("DesignationName"));
				designation.setDesignationdescription(rs.getString("DesignationDesc"));
				designation.setIsActive(rs.getBoolean("IsActive"));

				list.add(designation);

				System.out.println("Designationname---- " + rs.getString("DesignationName"));
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

	@Override
	public String saveDesignation(Designation designation) throws SQLException {

		String procedureCall = "";
		Connection connection = null;
		try {

			connection = jdbcTemplate.getDataSource().getConnection();
			connection.setAutoCommit(false);
			procedureCall = "{call proc_Master(?,?,?,?,?,?)}";
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "insertdesignation");
			callableSt.setString(2, designation.getDesignationname());
			callableSt.setString(3, designation.getDesignationdescription());
			callableSt.setInt(4, 0);
			callableSt.setInt(5, designation.getCreatedid());
			callableSt.setInt(6, 0);
			callableSt.execute();

			connection.commit();
		} catch (Exception e) {
			e.printStackTrace();
			connection.rollback();
		} finally {
			if (connection != null)
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return null;

	}

	@Override
	public String editDesignation(Designation designation) throws SQLException {

		String procedureCall = "";
		Connection connection = null;
		try {

			connection = jdbcTemplate.getDataSource().getConnection();
			connection.setAutoCommit(false);
			procedureCall = "{call proc_Master(?,?,?,?,?,?)}";
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "updatedesignation");
			callableSt.setString(2, designation.getDesignationname());
			callableSt.setString(3, designation.getDesignationdescription());
			callableSt.setInt(4, 0);
			callableSt.setInt(5, designation.getCreatedid());
			callableSt.setInt(6, designation.getDesignationid());
			callableSt.execute();

			connection.commit();
		} catch (Exception e) {
			e.printStackTrace();
			connection.rollback();
		} finally {
			if (connection != null)
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return null;

	}

	@Override
	public String deleteDesignationData(Designation designation) {

		final String procedureCall = "{call proc_Master(?,?,?,?,?,?)}";
		Connection connection = null;
		String returnValue = "failed";
		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "deletecompany");
			callableSt.setString(2, null);
			callableSt.setString(3, null);
			callableSt.setInt(4, 0);
			callableSt.setInt(5, designation.getCreatedid());
			callableSt.setInt(6, designation.getDesignationid());
			callableSt.execute();

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
		return returnValue;

	}

	public JSONArray getAllDesignationData() {
		final String procedureCall = "{call proc_Master(?)}";
		Connection connection = null;
		JSONArray jsonArray = new JSONArray();
		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "selectdesignation");
			ResultSet rs = callableSt.executeQuery();
			int x = 1;
			while (rs.next()) {
				JSONObject jObject = new JSONObject();
				jObject.put("designationId", rs.getInt("Pk_DesignationId"));
				jObject.put("designationName", rs.getString("DesignationName"));
				jsonArray.put(jObject);
			}
		} catch (Exception e) {
			System.out.println("Exception in getAllDesignationData ::" + e.getMessage());
			e.printStackTrace();
		}
		return jsonArray;
	}
	
	public String updateDesignation(Designation designation,String companyRoleId){
		try{
		Connection connection = jdbcTemplate.getDataSource().getConnection();
		String procedureCall = "{call proc_designation(?,?,?,?)}";
		CallableStatement callableSt = connection.prepareCall(procedureCall);
		callableSt.setString(1, "updateDesignation");
		callableSt.setInt(2, Integer.parseInt(designation.getDesignationname()));
		callableSt.setString(3, designation.getDesignationdescription());
		callableSt.setInt(4, Integer.parseInt(companyRoleId));
		boolean abc=callableSt.execute();
		}catch(Exception e){
			System.out.println("update designation data");
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<MailCredential> getAllMail() {
		List<MailCredential> list = new ArrayList<MailCredential>();
		final String procedureCall = "{call proc_Master(?)}";
		Connection connection = null;
		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "selectMailingIds");
			ResultSet rs = callableSt.executeQuery();
			while (rs.next()) {
				MailCredential designation = new MailCredential();
				designation.setEmail(rs.getString("EmailId"));
				designation.setPassword(rs.getString("Password"));
				designation.setEditid(rs.getInt("Pk_EmailId"));
				list.add(designation);
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

	@Override
	public void updateMailingpwd(int userid, MailCredential designation) {
		try{
			Connection connection = jdbcTemplate.getDataSource().getConnection();
			String procedureCall = "{call proc_Master(?,?,?,?,?,?)}";
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "updateMailingIds");
			callableSt.setString(2, null);
			callableSt.setString(3,designation.getPassword() );
			callableSt.setInt(4,0);
			callableSt.setInt(5,userid);
			callableSt.setInt(6,designation.getEditid());
			callableSt.execute();
			}catch(Exception e){
				System.out.println("update designation data");
				e.printStackTrace();
			}
		
	}
}
