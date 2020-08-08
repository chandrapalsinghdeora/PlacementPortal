package com.precise.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.precise.model.OfferProcessor;

@Repository
public class OfferProcessorDaoImpl implements OfferProcessorDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	@Override
	public List<OfferProcessor> getOfferedStudent() {
		List<OfferProcessor> list = new ArrayList<OfferProcessor>();
		final String procedureCall = "{call Proc_WingMapping(?)}";
		Connection connection=null;
		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "offered");
			ResultSet rs = callableSt.executeQuery();
			int x=1;
			while (rs.next()) {
				OfferProcessor offerProcessor=new OfferProcessor();
				offerProcessor.setRollNumber(rs.getInt("Fk_StudentId"));
				offerProcessor.setStudentName(rs.getString("StudentName"));
				offerProcessor.setFirmName(rs.getString("CompanyName"));
				offerProcessor.setRoleName(rs.getString("DesignationName"));
				offerProcessor.setFirmOfferStatus(rs.getString("CompanyStatus"));
				offerProcessor.setCandidateOfferStatus(rs.getString("StudentStatus"));
				offerProcessor.setCandidateLocation("xxx");
				//offerProcessor.setTimeToOfferExpiry(new Date("2017-08-03"));
				offerProcessor.setCandidatePref(rs.getInt("EffectivePreference"));
				list.add(offerProcessor);
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
		return list;
		
	}

	@Override
	public List<OfferProcessor> getAwaitingStudent() {
		List<OfferProcessor> list = new ArrayList<OfferProcessor>();
		final String procedureCall = "{call Proc_WingMapping(?)}";
		Connection connection=null;
		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "awaiting");
			ResultSet rs = callableSt.executeQuery();
			int x=1;
			while (rs.next()) {
				OfferProcessor offerProcessor=new OfferProcessor();
				offerProcessor.setRollNumber(rs.getInt("Fk_StudentId"));
				offerProcessor.setRoleid(rs.getInt("Fk_roleId"));
				offerProcessor.setStudentName(rs.getString("StudentName"));
				offerProcessor.setFirmName(rs.getString("CompanyName"));
				offerProcessor.setRoleName(rs.getString("DesignationName"));
				offerProcessor.setFirmOfferStatus(rs.getString("CompanyStatus"));
				offerProcessor.setCandidateOfferStatus(rs.getString("StudentStatus"));
				offerProcessor.setCountpending(rs.getInt("PendingCount"));
				offerProcessor.setCandidateLocation("xxx");
				offerProcessor.setTimeToOfferExpiry(rs.getString("HoldTime"));
				offerProcessor.setCandidatePref(rs.getInt("EffectivePreference"));
				list.add(offerProcessor);
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
		return list;
		
	}

	@Override
	public List<OfferProcessor> getSummary() {
		List<OfferProcessor> list = new ArrayList<OfferProcessor>();
		final String procedureCall = "{call Proc_WingMapping(?)}";
		Connection connection=null;
		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "summary");
			ResultSet rs = callableSt.executeQuery();
			int x=1;
			while (rs.next()) {
				OfferProcessor offerProcessor=new OfferProcessor();
				offerProcessor.setRollNumber(rs.getInt("Fk_StudentId"));
				offerProcessor.setRoleid(rs.getInt("Fk_roleId"));
				offerProcessor.setStudentName(rs.getString("StudentName"));
				offerProcessor.setFirmName(rs.getString("CompanyName"));
				offerProcessor.setRoleName(rs.getString("DesignationName"));
				offerProcessor.setFirmOfferStatus(rs.getString("CompanyStatus"));
				offerProcessor.setCandidateOfferStatus(rs.getString("StudentStatus"));
				offerProcessor.setCountpending(rs.getInt("PendingCount"));
				offerProcessor.setCandidateLocation("xxx");
				offerProcessor.setTimeToOfferExpiry(rs.getString("HoldTime"));
				offerProcessor.setCandidatePref(rs.getInt("EffectivePreference"));
				list.add(offerProcessor);
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
		return list;
		

	}

	@Override
	public List<OfferProcessor> getOfferRejects() {
		List<OfferProcessor> list = new ArrayList<OfferProcessor>();
		final String procedureCall = "{call Proc_WingMapping(?)}";
		Connection connection=null;
		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "OfferRejects");
			ResultSet rs = callableSt.executeQuery();
			int x=1;
			while (rs.next()) {
				OfferProcessor offerProcessor=new OfferProcessor();
				offerProcessor.setRollNumber(rs.getInt("Fk_StudentId"));
				offerProcessor.setRoleid(rs.getInt("Fk_roleId"));
				offerProcessor.setStudentName(rs.getString("StudentName"));
				offerProcessor.setFirmName(rs.getString("CompanyName"));
				offerProcessor.setRoleName(rs.getString("DesignationName"));
				offerProcessor.setFirmOfferStatus(rs.getString("CompanyStatus"));
				offerProcessor.setCandidateOfferStatus(rs.getString("StudentStatus"));
				offerProcessor.setCountpending(rs.getInt("PendingCount"));
				offerProcessor.setCandidateLocation("xxx");
				offerProcessor.setTimeToOfferExpiry(rs.getString("HoldTime"));
				offerProcessor.setCandidatePref(rs.getInt("EffectivePreference"));
				list.add(offerProcessor);
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
		return list;
		

		
	}

	@Override
	public List<OfferProcessor> getAwaitingOffer() {
		List<OfferProcessor> list4 = new ArrayList<OfferProcessor>();
		List<OfferProcessor> list = new ArrayList<OfferProcessor>();
		final String procedureCall = "{call Proc_WingMapping(?)}";
		Connection connection=null;
		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "AwaitingOffer");
			ResultSet rs = callableSt.executeQuery();
			int x=1;
			while (rs.next()) {
				OfferProcessor offerProcessor=new OfferProcessor();
				offerProcessor.setRollNumber(rs.getInt("Fk_StudentId"));
				offerProcessor.setRoleid(rs.getInt("Fk_roleId"));
				offerProcessor.setStudentName(rs.getString("StudentName"));
				offerProcessor.setFirmName(rs.getString("CompanyName"));
				offerProcessor.setRoleName(rs.getString("DesignationName"));
				offerProcessor.setFirmOfferStatus(rs.getString("CompanyStatus"));
				offerProcessor.setCandidateOfferStatus(rs.getString("StudentStatus"));
				offerProcessor.setCountpending(rs.getInt("PendingCount"));
				offerProcessor.setCandidateLocation("xxx");
				offerProcessor.setTimeToOfferExpiry(rs.getString("HoldTime"));
				offerProcessor.setCandidatePref(rs.getInt("EffectivePreference"));
				list.add(offerProcessor);
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
		return list;
		

	}

	@Override
	public List<OfferProcessor> getFirmList() {
		List<OfferProcessor> list = new ArrayList<OfferProcessor>();
		final String procedureCall = "{call Proc_WingMapping(?)}";
		Connection connection=null;
		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "getfirminofferprocessor");
			ResultSet rs = callableSt.executeQuery();
			while (rs.next()) {
				OfferProcessor offerProcessor=new OfferProcessor();
				offerProcessor.setFirmName(rs.getString("CompanyName"));
				offerProcessor.setFirmId(rs.getInt("Fk_companyId"));
				list.add(offerProcessor);
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
	public JSONArray getOpRoleName(int cmpid) {
		JSONArray firmarray = new JSONArray();
		final String procedureCall = "{call Proc_WingMapping(?,?)}";
		Connection connection=null;
		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "getroleinofferprocessor");
			callableSt.setInt(2, cmpid);
			ResultSet rs = callableSt.executeQuery();
			while (rs.next()) {
				JSONObject jobj=new JSONObject();
				jobj.put("roleid",rs.getInt("Fk_roleId"));
				jobj.put("rolename",rs.getString("DesignationName"));
				firmarray.put(jobj);
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
		return firmarray;
	}

	@Override
	public JSONArray getOpStudentName(int roleid) {
		JSONArray firmarray = new JSONArray();
		final String procedureCall = "{call Proc_WingMapping(?,?)}";
		Connection connection=null;
		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "getstudentinofferprocessor");
			callableSt.setInt(2, roleid);
			ResultSet rs = callableSt.executeQuery();
			while (rs.next()) {
				JSONObject jobj=new JSONObject();
				jobj.put("studentrollno",rs.getInt("Fk_StudentId"));
				jobj.put("studentname",rs.getString("StudentName"));
				firmarray.put(jobj);
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
		return firmarray;
	}

	@Override
	public JSONObject getOpStudentStatus(int roleid, int stdId) {
		final String procedureCall = "{call Proc_WingMapping(?,?,?)}";
		Connection connection=null;
		JSONObject jobj=new JSONObject();
		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "getstdStatusofferprocessor");
			callableSt.setInt(2, roleid);
			callableSt.setInt(3, stdId);
			ResultSet rs = callableSt.executeQuery();
			if (rs.next()) {
				jobj.put("firmstatus",rs.getString("CompanyStatus"));
				jobj.put("stdstatus",rs.getString("StudentStatus"));
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
		return jobj;
	}

	@Override
	public void updateststatus(OfferProcessor op){
		final String procedureCall = "{call Proc_WingMapping(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
		Connection connection=null;
		try
		{
		connection = jdbcTemplate.getDataSource().getConnection();
		CallableStatement callableSt = connection.prepareCall(procedureCall);
		callableSt.setString(1, "updateststatus");
		callableSt.setInt(2,op.getRoleid());
		callableSt.setInt(3, op.getRollNumber());
		callableSt.setInt(4,0);
		callableSt.setInt(5,0);
		callableSt.setInt(6,0);
		callableSt.setInt(7,0);
		callableSt.setInt(8,0);
		callableSt.setInt(9,0);
		callableSt.setDate(10,null);
		callableSt.setInt(11,0);
		callableSt.setDate(12,null);
		callableSt.setString(13,op.getFirmOfferStatus());
		callableSt.setString(14,op.getCandidateOfferStatus());
		callableSt.execute();
		}
		catch(Exception e)
		{
			
		}
	}

	@Override
	public void plcstatusupdate(OfferProcessor op) {
		final String procedureCall = "{call Proc_WingMapping(?,?,?)}";
		Connection connection=null;
		try
		{
		connection = jdbcTemplate.getDataSource().getConnection();
		CallableStatement callableSt = connection.prepareCall(procedureCall);
		callableSt.setString(1, "plcstatusupdate");
		callableSt.setInt(2,op.getRoleid());
		callableSt.setInt(3, op.getRollNumber());
		callableSt.execute();
		}
		catch(Exception e)
		{
			
		}
	}
}