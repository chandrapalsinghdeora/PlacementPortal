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

import com.precise.model.kmVerificationForm;


@Repository
public class KmVerificationDaoImpl implements KmVerificationDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public List<kmVerificationForm> getAllKm() {
		List<kmVerificationForm> list = new ArrayList<kmVerificationForm>();
		String procedureCall = "{call sp_VerifyComapny(?)}";
		Connection connection = null;
		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "select");
			ResultSet rs = callableSt.executeQuery();
			int x=1;
			while (rs.next()) {
				kmVerificationForm kmverificationform = new kmVerificationForm();
				kmverificationform.setApplicationId(rs.getInt("Pk_ApplicationId"));
				kmverificationform.setCompanyName(rs.getString("CompanyName"));
				kmverificationform.setDesignationName(rs.getString("DesignationName"));
				kmverificationform.setRmName(rs.getString("RmName"));
				kmverificationform.setApplicationStatus(rs.getString("ApplicationStatus"));
				kmverificationform.setShare(rs.getString("Share"));
				kmverificationform.setVerification(rs.getBoolean("Verify"));
				kmverificationform.setClusterId(Integer.parseInt(rs.getString("Pk_ClusterId")));
				kmverificationform.setClusterName(rs.getString("CluserName"));
				kmverificationform.setCohortId(Integer.parseInt(rs.getString("Pk_CphortId")));
				kmverificationform.setCohortName(rs.getString("CohortName"));
				kmverificationform.setJobDescPath(rs.getString("JobDescPath"));
				list.add(kmverificationform);
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
	public void updateKm(int kmId, int kmValue, int userId) {
		
		final String procedureCall = "{call sp_VerifyComapny(?,?,?,?)}";
		Connection connection = null;
		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "Update");
			callableSt.setInt(2, kmId);
			if(kmValue==1){
				callableSt.setBoolean(3, true);
			}else
				callableSt.setBoolean(3, false);
			
			callableSt.setInt(4, userId);
			
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
		
	}
	
	public String updateFirmVerfication(kmVerificationForm kmVerification){
		System.out.println("cohortName:::"+kmVerification.getCohortName());
		final String procedureCall = "{call sp_VerifyComapny(?,?,?,?)}";
		Connection connection = null;
		int clusterId=0,cohortId=0;
		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "selectCohortAndClusterId");
			callableSt.setInt(2,kmVerification.getCohortId());
			callableSt.setBoolean(3, true);
			callableSt.setInt(4, 0);
		
			ResultSet rs = callableSt.executeQuery();
			while (rs.next()) {
				cohortId=rs.getInt("Pk_CphortId");
				clusterId=rs.getInt("Fk_ClusterId");
			}
			
			System.out.println("cohortId::::"+cohortId+" :::clusterId::"+clusterId);
			
			final String procedureCall2 = "{call sp_VerifyComapny(?,?,?,?,?,?,?,?)}";
			CallableStatement callableSt1 = connection.prepareCall(procedureCall2);
			callableSt1.setString(1, "updateVerification");
			callableSt1.setInt(2,0);
			callableSt1.setBoolean(3, true);
			callableSt1.setInt(4, 0);
			callableSt1.setString(5, null);
			callableSt1.setInt(6, cohortId);
			callableSt1.setInt(7, clusterId);
			callableSt1.setInt(8, kmVerification.getApplicationId());
			callableSt1.execute();
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
		return null;
	}
}
