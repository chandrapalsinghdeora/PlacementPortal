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

import com.precise.controller.AnnouncementController;
import com.precise.model.Announcement;
import com.precise.model.KmIIMStudent;

@Repository
public class KmIIMStudentDaoImpl implements KmIIMStudentDao{

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	@Override
	public List<KmIIMStudent> getAllIIMStudent() {
		System.out.println("KmIIMStudentDaoImpl.getAllIIMStudent()--");
		List<KmIIMStudent> list = new ArrayList<KmIIMStudent>();
		final String procedureCall = "{call Sp_IimStd(?)}";
		Connection connection = null;
		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "Select");
			ResultSet rs = callableSt.executeQuery();
			int x=1;
			while (rs.next()) {
				KmIIMStudent kmiimstudent = new KmIIMStudent();
				kmiimstudent.setStudentId(rs.getInt("Pk_IIMStdId"));
				kmiimstudent.setPrefix(rs.getString("PreFix"));
				kmiimstudent.setEmailid(rs.getString("EmailId"));
				kmiimstudent.setActive(rs.getBoolean("IsActive"));
				kmiimstudent.setWildCard(rs.getBoolean("wilecard"));
				kmiimstudent.setUsername(rs.getString("userName"));
				kmiimstudent.setGroupid(rs.getInt("groupid"));
				list.add(kmiimstudent);
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
	public String saveStudent(KmIIMStudent cluster) throws SQLException {
		String procedureCall = "";
		Connection connection = null;
		try {

			connection = jdbcTemplate.getDataSource().getConnection();
			connection.setAutoCommit(false);
			procedureCall = "{call Sp_IimStd(?,?,?,?,?,?,?,?)}";
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "Insert");
			callableSt.setInt(2, cluster.getStudentId());
			callableSt.setString(3, cluster.getPrefix());
			callableSt.setString(4, cluster.getEmailid());
			callableSt.setInt(5, cluster.getCreatedid());
			callableSt.setInt(6, 0);
			callableSt.setString(7, cluster.getUsername());
			callableSt.setInt(8, cluster.getGroupid());
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
	public String editStudent(KmIIMStudent cluster) throws SQLException {

		String procedureCall = "";
		Connection connection = null;
		try {

			connection = jdbcTemplate.getDataSource().getConnection();
			connection.setAutoCommit(false);
			procedureCall = "{call Sp_IimStd(?,?,?,?,?,?,?,?)}";
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "Update");
			callableSt.setInt(2, cluster.getStudentId());
			callableSt.setString(3, cluster.getPrefix());
			callableSt.setString(4, cluster.getEmailid());
			callableSt.setInt(5, cluster.getCreatedid());
			callableSt.setBoolean(6, cluster.isWildCard());
			callableSt.setString(7, cluster.getUsername());
			callableSt.setInt(8, cluster.getGroupid());
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
	public String deleteStudentData(KmIIMStudent cluster) {
		final String procedureCall = "{call Sp_IimStd(?,?,?,?,?)}";
		Connection connection = null;
		String returnValue="failed";
		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "Delete");
			callableSt.setInt(2, cluster.getStudentId());
			callableSt.setString(3, cluster.getPrefix());
			callableSt.setString(4, cluster.getEmailid());
			callableSt.setInt(5, cluster.getCreatedid());
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
	
	@Override
	public String insertIntoStudets(Object[] studentData) throws SQLException{
		System.out.println("inside function::");
		String procedureCall = "";
		Connection connection = null;
		try {
			System.out.println("String) studentData[0]:::"+(String) studentData[0]);
			connection = jdbcTemplate.getDataSource().getConnection();
			connection.setAutoCommit(false);
			procedureCall = "{call Sp_IimStd(?,?,?,?,?,?,?,?)}";
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "Email");
			callableSt.setInt(2, 0);
			callableSt.setString(3,(String) studentData[0]);
			callableSt.setString(4, (String)studentData[1]);
			callableSt.setInt(5, 0);
			callableSt.setInt(6, 0);
			callableSt.setString(7, (String)studentData[3]);
			callableSt.setInt(8,  (Integer)studentData[2]);
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
	public JSONArray getUsernameList(){
		
		JSONArray jsonArray = new JSONArray();
		final String procedureCall = "{call Sp_IimStd(?)}";
		Connection connection = null;
		try {
			connection = getJdbcTemplate().getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "Select");
			

			ResultSet rs = callableSt.executeQuery();

			while (rs.next()) {
				JSONObject userDetailsJson = new JSONObject();
				
				userDetailsJson.put("userName", rs.getString("userName"));
				jsonArray.put(userDetailsJson);
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
		return jsonArray;
	}
	
	@Override
	public void saveKMAnnouncement(Announcement announce) {
		System.out.println("saveKMAnnouncement dao::"+announce.getTitle());
		String procedureCall = "";
		Connection connection = null;
		try {			
			connection = jdbcTemplate.getDataSource().getConnection();
			
			procedureCall = "{call Usp_kmAnnouncement(?,?,?,?,?,?,?)}";
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "Insert");
			callableSt.setInt(2, 0);
			callableSt.setString(3, announce.getDescription());
			callableSt.setString(4, announce.getUrl());
			callableSt.setString(5, announce.getTitle());
			callableSt.setTime(6, null);
			callableSt.setInt(7, announce.getCreatedBy());
			callableSt.execute(); 
			
		} catch (Exception e) {
			e.printStackTrace();			
		} finally {
			if (connection != null)
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	
		
	}
	@Override
	public List<Announcement> getALLAnnouncementByKm() {
		List<Announcement> announceList=new ArrayList<Announcement>();
		final String procedureCall = "{call Usp_kmAnnouncement(?)}";
		Connection connection = null;
		try {
			connection = getJdbcTemplate().getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "select");
			ResultSet rs = callableSt.executeQuery();
			while (rs.next()) {
				Announcement ann=new Announcement();
				ann.setAnnounmtId(rs.getInt("Pk_KMAnnouncementId"));
				ann.setTitle(rs.getString("Title"));
				ann.setUrl(rs.getString("url"));
				ann.setDescription(rs.getString("Subject"));
				ann.setCreatedDate(rs.getString("CreatedDate"));
				announceList.add(ann);
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
		return announceList;
	}
	@Override
	public void delteKMAnnouncement(int announmtId) {
		System.out.println("delteKMAnnouncement dao::"+announmtId);
		String procedureCall = "";
		Connection connection = null;
		try {			
			connection = jdbcTemplate.getDataSource().getConnection();			
			procedureCall = "{call Usp_kmAnnouncement(?,?)}";
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "Delete");
			callableSt.setInt(2, announmtId);			
			callableSt.execute(); 
			
		} catch (Exception e) {
			e.printStackTrace();			
		} finally {
			if (connection != null)
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	
	}
	
	
	
}
