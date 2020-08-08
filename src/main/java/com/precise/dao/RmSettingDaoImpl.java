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

import com.precise.model.RmSetting;

@Repository
public class RmSettingDaoImpl implements RmSettingDao {


	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public List<RmSetting> getAllSetting() {
		System.out.println("RmSettingDaoImpl.getAllSetting()--");
		List<RmSetting> list = new ArrayList<RmSetting>();
		
		final String procedureCall = "{call proc_Setting(?)}";
		
		Connection connection = null;
		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "select");
			ResultSet rs = callableSt.executeQuery();
			int x=1;
			while (rs.next()) {
				RmSetting kmsetting = new RmSetting();
				kmsetting.setSettingId(rs.getInt("Pk_SettingId"));
				kmsetting.setOptionName(rs.getString("Option_Name"));
				kmsetting.setOptionValue(rs.getInt("Option_Value"));
				
				list.add(kmsetting);
				
				//System.out.println("Pgp1Prefix---- " + rs.getString("PreFix"));
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
	public String editSetting(RmSetting rmsetting) throws SQLException {

		String procedureCall = "";
		Connection connection = null;
		try {

			connection = jdbcTemplate.getDataSource().getConnection();
			connection.setAutoCommit(false);
			procedureCall = "{call proc_Setting(?,?,?,?)}";
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "Update");
			
			callableSt.setInt(2, rmsetting.getOptionValue());
			callableSt.setInt(3, rmsetting.getCreatedId());
			callableSt.setInt(4, rmsetting.getSettingId());
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

}
