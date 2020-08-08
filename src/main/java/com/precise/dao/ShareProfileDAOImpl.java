package com.precise.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.precise.model.Cluster;
import com.precise.model.ShareProfile;

@Repository("shareProfile")
public class ShareProfileDAOImpl implements ShareProfileDAO{

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	
	@Override
	public List<ShareProfile> getAllShareProfile(){
		List<ShareProfile> list = new ArrayList<ShareProfile>();
		String procedureCall = "{call proc_userProfile(?)}";
		Connection connection=null;
		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "shareprofile");
			ResultSet rs = callableSt.executeQuery();
			int x=1;
			int userId =0, totalWorkExp =0 , totalSummnerExp=0, totalOtherInternshipExp= 0; 
			String wpCompany="",internCompany="";
			while (rs.next()) {
				ShareProfile shareProfile = new ShareProfile();
				shareProfile.setUserId(rs.getInt("FK_IIMStdId"));
				userId = rs.getInt("FK_IIMStdId"); 
				shareProfile.setStudentId(rs.getInt("Pk_UserProfileid"));
				shareProfile.setRollNumber(rs.getString("RollNumber"));
				shareProfile.setStudentName(rs.getString("Name"));
				
				CallableStatement callableStGraduate = connection.prepareCall("{call proc_Graduate(?,?,?)}");
				callableStGraduate.setString(1, "select");
				callableStGraduate.setInt(2, userId);
				callableStGraduate.setInt(3, 0);
				ResultSet rsGraduate = callableStGraduate.executeQuery();
				if (rsGraduate.next()) {
					shareProfile.setGraduateDegree(rsGraduate.getString("Degree"));
				}
				
				CallableStatement summerInernship = connection.prepareCall("{call proc_SummerInternship(?,?)}");
				summerInernship.setString(1, "select");
				summerInernship.setInt(2, userId);
				ResultSet rsSummerInternship = summerInernship.executeQuery();
				totalSummnerExp = 0;
				while (rsSummerInternship.next()) {
					totalSummnerExp=totalSummnerExp+(rsSummerInternship.getInt("Duration"));
					//internCompany = internCompany+" "+(rsSummerInternship.getString("Company"));
				}
				shareProfile.setTotalSummnerExp(totalSummnerExp);
				
				CallableStatement otherInernship = connection.prepareCall("{call proc_otherInternShip(?,?)}");
				otherInernship.setString(1, "select");
				otherInernship.setInt(2, userId);
				ResultSet rsOtherInternship = otherInernship.executeQuery();
				totalOtherInternshipExp=0;
				internCompany = "";
				while (rsOtherInternship.next()) {
					totalOtherInternshipExp= totalOtherInternshipExp+(rsOtherInternship.getInt("Duration"));
					internCompany = internCompany+(rsOtherInternship.getString("ComapnyName")==null?"":rsOtherInternship.getString("ComapnyName"));
				}
				shareProfile.setTotalOtherInternshipExp(totalOtherInternshipExp);
				shareProfile.setInternCompany(internCompany);
				
				CallableStatement workExperience = connection.prepareCall("{call proc_workExperience(?,?)}");
				workExperience.setString(1, "select");
				workExperience.setInt(2, userId);
				ResultSet rsWorkExperience = workExperience.executeQuery();
				totalWorkExp = 0;
				wpCompany = "";
				while (rsWorkExperience.next()) {
					totalWorkExp = totalWorkExp+(rsWorkExperience.getInt("Duration"));
					wpCompany = wpCompany+(rsWorkExperience.getString("CompanyName")==null?"":rsWorkExperience.getString("CompanyName"));
					//rs.getString("CohortName")==null?"":rs.getString("CohortName")
				}
				shareProfile.setTotalWorkExp(totalWorkExp);
				shareProfile.setWpCompany(wpCompany);
				
				CallableStatement cvFile = connection.prepareCall("{call Proc_cvFile(?,?,?)}");
				cvFile.setString(1, "select");
				cvFile.setInt(2, 0);
				cvFile.setInt(3, userId);
				ResultSet rsCVFile = cvFile.executeQuery();
				List<String> cvFileName = new LinkedList<String>();
				List<String> cvFileTitle = new LinkedList<String>();
				List<Integer> cvPkId=new LinkedList<Integer>();
				while (rsCVFile.next()) {
					cvFileName.add(rsCVFile.getString("fileName"));
					cvFileTitle.add(rsCVFile.getString("Title"));
					cvPkId.add(rsCVFile.getInt("Pk_CvId"));
				}
				shareProfile.setCvNameList(cvFileName);
				shareProfile.setCvTitleList(cvFileTitle);
				shareProfile.setCvPkId(cvPkId);
				
				list.add(shareProfile);
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
}
