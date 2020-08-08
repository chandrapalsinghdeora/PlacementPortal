package com.precise.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.precise.model.ProfileLockKM;

@Repository
public class ProfileLockKmDaoImpl implements ProfileLockKMDAO {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public List<ProfileLockKM> getAllProfile() {
		//System.out.println("DesignationDaoImpl.getAllDesignation()--");
		List<ProfileLockKM> list = new ArrayList<ProfileLockKM>();
		final String procedureCall = "{call Proc_AdminLock(?)}";
		Connection connection = null;
		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "Select");
			ResultSet rs = callableSt.executeQuery();
			int x = 1;
			while (rs.next()) {
				ProfileLockKM profileLockKM = new ProfileLockKM();

				profileLockKM.setProfileLockId(rs.getInt("Pk_UserProfileid"));
				profileLockKM.setStudentName(rs.getString("userName"));
				profileLockKM.setEmailId(rs.getString("EmailID"));
				profileLockKM.setAdminLock(rs.getBoolean("AdminLock"));
				list.add(profileLockKM);

				//System.out.println("Designationname---- " + rs.getString("DesignationName"));
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
	
	public  void updateLockedProfile(ProfileLockKM profileLockKM){
		try{
		Connection connection = jdbcTemplate.getDataSource().getConnection();
		String procedureCall = "{call Proc_AdminLock(?,?,?)}";
		CallableStatement callableSt = connection.prepareCall(procedureCall);
		List<Integer> profile=profileLockKM.getSelectedIds();
		for (Integer profile1 : profile) {
			
		callableSt.setString(1, "Update");
		callableSt.setBoolean(2,true);
		callableSt.setInt(3, profile1);
		
		callableSt.addBatch();
			}
			callableSt.executeBatch();
		}catch(Exception e){
			System.out.println("update ProfileLockKM data");
			e.printStackTrace();
		}
		
	}

	@Override
	public List<ProfileLockKM> getpgpStudent(int groupid) {
		// TODO Auto-generated method stub
System.out.println("groupid dao"+groupid);
		List<ProfileLockKM> list = new ArrayList<ProfileLockKM>();
		final String procedureCall = "{call Proc_AdminLock(?,?,?,?)}";
		Connection connection = null;
		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "Select");
			callableSt.setBoolean(2, false);
			callableSt.setInt(3, 0);
			callableSt.setInt(4,groupid);
			ResultSet rs = callableSt.executeQuery();
			int x = 1;
			while (rs.next()) {
				ProfileLockKM profileLockKM = new ProfileLockKM();

				profileLockKM.setProfileLockId(rs.getInt("Pk_UserProfileid"));
				profileLockKM.setStudentName(rs.getString("userName"));
				profileLockKM.setEmailId(rs.getString("EmailID"));
				profileLockKM.setAdminLock(rs.getBoolean("AdminLock"));
				list.add(profileLockKM);

				//System.out.println("Designationname---- " + rs.getString("DesignationName"));
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
	public void unlockDetails(ProfileLockKM profileLockKM) {
		// TODO Auto-generated method stub
		try{
			Connection connection = jdbcTemplate.getDataSource().getConnection();
			String procedureCall = "{call Proc_AdminLock(?,?,?)}";
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			List<Integer> profile=profileLockKM.getSelectedIds();
			for (Integer profile1 : profile) {
				
			callableSt.setString(1, "Update");
			callableSt.setBoolean(2,false);
			callableSt.setInt(3, profile1);
			
			callableSt.addBatch();
				}
				callableSt.executeBatch();
			}catch(Exception e){
				System.out.println("update ProfileLockKM data");
				e.printStackTrace();
			}

	}
	
	

}
