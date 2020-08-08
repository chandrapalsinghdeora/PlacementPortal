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
import com.precise.model.RMDownloadVerifiedList;
import com.precise.model.UserProfile;

@Repository
public class RMDownloadVerifiedDAOImpl implements RMDownloadVerifiedDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	@Override
	public List<RMDownloadVerifiedList> getRMVerifiedDetail(){
		
		List<RMDownloadVerifiedList> listCloseStatus =new ArrayList<RMDownloadVerifiedList>();		
		final String procedureCall = "{call Sp_ClosedStatus(?)}";
		Connection connection = null;
		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "Select");
			ResultSet rs = callableSt.executeQuery();
			int x=1;
			RMDownloadVerifiedList rmverify = null;
			while (rs.next()) {
				if(rs.getString("Verify").equals("Yes")){
				rmverify = new RMDownloadVerifiedList();
				rmverify.setRollnumber(rs.getInt("RollNumber"));
				rmverify.setEmailId(rs.getString("EmailID"));
				rmverify.setName(rs.getString("Name"));
				rmverify.setCv(rs.getString("CV"));
				rmverify.setRank(rs.getString("Rank"));
				rmverify.setStatus(rs.getString("Verify"));
				listCloseStatus.add(rmverify);	
				x=x+1;
				}
				
			}
		
		}catch(
		SQLException e)
		{
			e.printStackTrace();
		}finally
		{
			if (connection != null)
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return listCloseStatus;
		}
	
	@Override
	public List<UserProfile> getUserValues(int rollNumber){
		
		List<UserProfile> userdata=new ArrayList<UserProfile>();
		
		final String procedureCall = "{call Sp_StdInfo(?,?)}";
		Connection connection = null;
		try {		
		connection = getJdbcTemplate().getDataSource().getConnection();
		CallableStatement callableSt = connection.prepareCall(procedureCall);	
		callableSt.setString(1,"User");
		callableSt.setInt(2, rollNumber);
	
		ResultSet rs=callableSt.executeQuery();
			while (rs.next()) {
				
				UserProfile up= new UserProfile();
				
				up.setFullName(rs.getString("Name"));
				up.setCvName(rs.getString("CVName"));
				up.setMentor(rs.getString("MentorName"));
				up.setGender(rs.getString("Gender"));
				up.setPercentage(rs.getString("10%"));
				up.setPercenatageTwelve(rs.getString("12%"));
				up.setPercentageGraduate(rs.getString("Graduation"));
				up.setPostgraduatePercentage(rs.getString("PostGraduation"));
				userdata.add(up);
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
		return userdata;  
	}
	
	
	
}
