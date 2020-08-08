package com.precise.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.precise.mail.SendMail;
import com.precise.model.CloseStatus;
import com.precise.model.Fine;
import com.precise.model.Forum;
import com.precise.model.Inbox;
import com.precise.model.InfoCloseStatus;
import com.precise.model.SessionBean;
import com.precise.model.UserProfile;

@Repository("closeStatusRepo1")
public class CloseStatusDAOImpl1 implements CloseStatusDAO1 {

	@Autowired
	SendMail sendMail;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	@Override
	public List<CloseStatus> getCloseStatusDetail(int cmpRoleId) {

		List<CloseStatus> listCloseStatus = new ArrayList<CloseStatus>();
		final String procedureCall = "{call Sp_ClosedStatus(?,?,?,?,?,?)}";
		Connection connection = null;
		try {
			
			
			connection = jdbcTemplate.getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "Select");
			callableSt.setInt(2, 0);
			callableSt.setString(3, null);
			callableSt.setString(4, null);
			callableSt.setInt(5, 0);
			callableSt.setInt(6, cmpRoleId);
			ResultSet rs = callableSt.executeQuery();
			while (rs.next()) {
				CloseStatus closeStatus = new CloseStatus();
				closeStatus.setApplyId(rs.getInt("Pk_ApplyId"));
				closeStatus.setRollnumber(rs.getInt("RollNumber"));
				closeStatus.setEmailId(rs.getString("EmailID"));
				closeStatus.setInternship(rs.getInt("Internship"));
				closeStatus.setExperience(rs.getString("Experience"));
				closeStatus.setCv(rs.getString("CV"));
				System.out.println("CV" + rs.getString("CV"));
				closeStatus.setRollover(rs.getInt("RollOver"));
				closeStatus.setStatus(rs.getString("Status"));
				closeStatus.setVerify(rs.getString("Verify"));
				closeStatus.setFlag(rs.getString("Flag"));
				closeStatus.setName(rs.getString("Name"));
				listCloseStatus.add(closeStatus);
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
		return listCloseStatus;
	}

	@Override
	public List<CloseStatus> getApproveCloseStatusList(int cmpRoleId) {

		List<CloseStatus> approvelistCloseStatus = new ArrayList<CloseStatus>();
		final String procedureCall = "{call Sp_ClosedStatus(?,?,?,?,?,?)}";
		Connection connection = null;
		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "Select");
			callableSt.setInt(2, 0);
			callableSt.setString(3, "Yes");
			callableSt.setString(4, null);
			callableSt.setInt(5, 0);
			callableSt.setInt(6, cmpRoleId);
			ResultSet rs = callableSt.executeQuery();
			while (rs.next()) {
				CloseStatus closeStatus = new CloseStatus();
				closeStatus.setApplyId(rs.getInt("Pk_ApplyId"));
				closeStatus.setRollnumber(rs.getInt("RollNumber"));
				closeStatus.setEmailId(rs.getString("EmailID"));
				closeStatus.setInternship(rs.getInt("Internship"));
				closeStatus.setExperience(rs.getString("Experience"));
				closeStatus.setCv(rs.getString("CV"));
				closeStatus.setRollover(rs.getInt("RollOver"));
				closeStatus.setStatus(rs.getString("Status"));
				closeStatus.setVerify(rs.getString("Verify"));
				closeStatus.setFlag(rs.getString("Flag"));
				closeStatus.setPrefrences(rs.getInt("Rank"));
				closeStatus.setName(rs.getString("Name"));

				approvelistCloseStatus.add(closeStatus);
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
		return approvelistCloseStatus;
	}

	@Override
	public List<CloseStatus> getUnApproveCloseStatusList(int cmpRoleId) {

		List<CloseStatus> unapproveListCloseStatus = new ArrayList<CloseStatus>();
		final String procedureCall = "{call Sp_ClosedStatus(?,?,?,?,?,?)}";
		Connection connection = null;
		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "Select");
			callableSt.setInt(2, 0);
			callableSt.setString(3, "No");
			callableSt.setString(4, null);
			callableSt.setInt(5, 0);
			callableSt.setInt(6, cmpRoleId);
			
			ResultSet rs = callableSt.executeQuery();
			while (rs.next()) {
				CloseStatus closeStatus = new CloseStatus();
				closeStatus.setApplyId(rs.getInt("Pk_ApplyId"));
				closeStatus.setRollnumber(rs.getInt("RollNumber"));
				closeStatus.setEmailId(rs.getString("EmailID"));
				closeStatus.setInternship(rs.getInt("Internship"));
				closeStatus.setExperience(rs.getString("Experience"));
				closeStatus.setCv(rs.getString("CV"));
				closeStatus.setRollover(rs.getInt("RollOver"));
				closeStatus.setStatus(rs.getString("Status"));
				closeStatus.setVerify(rs.getString("Verify"));
				closeStatus.setFlag(rs.getString("Flag"));
				closeStatus.setName(rs.getString("Name"));
				unapproveListCloseStatus.add(closeStatus);
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
		return unapproveListCloseStatus;
	}

	@Override
	public CloseStatus getCloseStatusHeader(int cmpRoleId) {

		final String procedureCall = "{call Sp_ClosedStatus(?,?,?,?,?,?)}";
		Connection connection = null;
		CloseStatus closeStatus = null;
		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "Header");
			callableSt.setInt(2, 0);
			callableSt.setString(3, null);
			callableSt.setString(4, null);
			callableSt.setInt(5, 0);
			callableSt.setInt(6, cmpRoleId);
			ResultSet rs = callableSt.executeQuery();
			if (rs.next()) {
				closeStatus = new CloseStatus();
				closeStatus.setYear(rs.getInt("Year"));
				closeStatus.setCmpId(rs.getInt("Pk_CompanyId"));
				closeStatus.setFirmName(rs.getString("CompanyName"));
				closeStatus.setRoleName(rs.getString("DesignationName"));
				closeStatus.setProcess(rs.getString("ProcessName"));
				closeStatus.setExperienceReq(rs.getString("WorkExpReq"));
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
		return closeStatus;
	}

	@Override
	public List<UserProfile> getUserValues(int rollNumber) {

		List<UserProfile> userdata = new ArrayList<UserProfile>();

		final String procedureCall = "{call Sp_StdInfo(?,?)}";
		Connection connection = null;
		try {
			connection = getJdbcTemplate().getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "User");
			callableSt.setInt(2, rollNumber);

			ResultSet rs = callableSt.executeQuery();
			while (rs.next()) {

				UserProfile up = new UserProfile();
				up.setEmailAddress(rs.getString("EmailID"));
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

	@Override
	public JSONArray getInternshipValues(int rollNumber) {

		// List<UserProfile> userdata=new ArrayList<UserProfile>();
		JSONArray jsonArrayIntern = new JSONArray();
		JSONArray jsonArray = new JSONArray();
		final String procedureCall = "{call Sp_StdInfo(?,?)}";
		Connection connection = null;
		try {
			connection = getJdbcTemplate().getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "SummerInterShip");
			callableSt.setInt(2, rollNumber);

			ResultSet rs = callableSt.executeQuery();

			while (rs.next()) {
				JSONObject userDetailsJson = new JSONObject();
				userDetailsJson.put("companyname", rs.getString("Company"));
				userDetailsJson.put("duration", rs.getString("Duration")==null?"0":rs.getString("Duration"));
				jsonArray.put(userDetailsJson);
			}

			JSONArray jsonArray1 = new JSONArray();
			final String procedureCall1 = "{call Sp_StdInfo(?,?)}";
			Connection connection1 = null;

			connection1 = getJdbcTemplate().getDataSource().getConnection();
			CallableStatement callableStother = connection1.prepareCall(procedureCall1);
			callableStother.setString(1, "OtherInterShip");
			callableStother.setInt(2, rollNumber);

			ResultSet rsother = callableStother.executeQuery();

			while (rsother.next()) {
				JSONObject userDetailsJson1 = new JSONObject();
				userDetailsJson1.put("otherCompanyname", rsother.getString("ComapnyName"));
				userDetailsJson1.put("otherDuration", rsother.getString("Duration")==null?"0":rsother.getString("Duration"));
				jsonArray1.put(userDetailsJson1);
			}

			JSONObject userDetailsJsonIntern = new JSONObject();
			userDetailsJsonIntern.put("summerIntern", jsonArray);
			userDetailsJsonIntern.put("otherIntern", jsonArray1);
			jsonArrayIntern.put(userDetailsJsonIntern);

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
		return jsonArrayIntern;
	}

	@Override
	public JSONArray getExperienceList(int rollNumber) {

		// List<UserProfile> userdata=new ArrayList<UserProfile>();

		JSONArray jsonArray = new JSONArray();
		final String procedureCall = "{call Sp_StdInfo(?,?)}";
		Connection connection = null;
		try {
			connection = getJdbcTemplate().getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "Experience");
			callableSt.setInt(2, rollNumber);

			ResultSet rs = callableSt.executeQuery();

			while (rs.next()) {
				JSONObject userDetailsJson = new JSONObject();
				userDetailsJson.put("companyname", rs.getString("CompanyName"));
				userDetailsJson.put("duration", rs.getString("Duration"));
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
	public JSONArray getCVList(int rollNumber) {

		// List<UserProfile> userdata=new ArrayList<UserProfile>();

		JSONArray jsonArray = new JSONArray();
		final String procedureCall = "{call Sp_StdInfo(?,?)}";
		Connection connection = null;
		try {
			connection = getJdbcTemplate().getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "CV");
			callableSt.setInt(2, rollNumber);

			ResultSet rs = callableSt.executeQuery();

			while (rs.next()) {
				JSONObject userDetailsJson = new JSONObject();
				userDetailsJson.put("title", rs.getString("Title"));
				userDetailsJson.put("fileName", rs.getString("fileName"));
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
	public InfoCloseStatus getInfoValues(int roleId) {

		InfoCloseStatus info = new InfoCloseStatus();

		final String procedureCall = "{call Sp_StdInfo(?,?)}";
		Connection connection = null;
		try {
			connection = getJdbcTemplate().getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "Company");
			callableSt.setInt(2, roleId);

			ResultSet rs = callableSt.executeQuery();
			if (rs.next()) {
				info.setClusterName(rs.getString("CluserName") == null ? "" : rs.getString("CluserName"));
				System.out.println(rs.getString("CluserName"));
				info.setCohortName(rs.getString("CohortName") == null ? "" : rs.getString("CohortName"));
				info.setCompensation(rs.getString("Compension") == null ? "" : rs.getString("Compension"));
				info.setHires(rs.getString("Hires") == null ? 0 : rs.getInt("Hires"));
			}

			callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "Application");
			callableSt.setInt(2, roleId);

			rs = callableSt.executeQuery();
			if (rs.next()) {
				info.setNoOfApp(rs.getString("NoOfApp") == null ? 0 : rs.getInt("NoOfApp"));
				info.setRollOver(rs.getString("RollOver") == null ? 0 : rs.getInt("RollOver"));
				info.setVerified(rs.getString("Verified") == null ? 0 : rs.getInt("Verified"));
				info.setUnVerified(rs.getString("UnVerified") == null ? 0 : rs.getInt("UnVerified"));
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
		return info;
	}

	@Override
	public void updateFlag(List<CloseStatus> closeStatuslist) {
		// System.out.println("ForumDAOImpl.updateForum()" + forum.getForumId()
		// + "fname=" + forum.getForumName());
		String procedureCall = "{call [Sp_ClosedStatus](?,?,?,?,?)}";
		Connection connection = null;
		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			for (CloseStatus closeStatus : closeStatuslist) {
				callableSt.setString(1, "Update");
				callableSt.setInt(2, 0);
				callableSt.setString(3, null);
				callableSt.setString(4, "M");
				callableSt.setInt(5, closeStatus.getApplyId());

				callableSt.addBatch();
			}
			callableSt.executeBatch();

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

	public void saveShortList(List<CloseStatus> closeStatuslist, int userid) {
		String procedureCall = "{call [Sp_ShortList_One](?,?,?,?,?,?)}";
		System.out.println("CloseStatusDAOImpl.saveShortList()"+userid);
		Connection connection = null;
		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			for (CloseStatus closeStatus : closeStatuslist) {
				callableSt.setString(1, "Insert");
				callableSt.setInt(2, 0);
				callableSt.setInt(3, userid);
				System.out.println("userid---"+userid);
				System.out.println("getApplyId-----"+closeStatus.getApplyId());
				callableSt.setInt(4, closeStatus.getApplyId());
				callableSt.setBoolean(5, false);
				callableSt.setString(6, "RM");
				callableSt.addBatch();
			}
			callableSt.executeBatch();

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

	public void saveHRList (String email,int cmpRoleId,String MailDes,String RmemailId,String hrName, int userid){
		
		String procedureCall = "{call Usp_HrMailid(?,?,?,?,?,?,?,?)}";
		System.out.println("CloseStatusDAOImpl.saveHRList()"+email);
		Connection connection = null;
		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			
				callableSt.setString(1, "Insert");
				callableSt.setInt(2,cmpRoleId);
				callableSt.setString(3,email);
				System.out.println("HRemailid---"+email);
			
				callableSt.setString(4,MailDes);
				callableSt.setString(5,RmemailId);
				callableSt.setBoolean(6,true);
				callableSt.setInt(7,userid);
				callableSt.setString(8,hrName);
				
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
	
	public void saveGenerateShortLinkMail(CloseStatus closeStatus, int userid) {
		String procedureCall = "{call [Sp_SendMail](?,?,?,?,?,?,?,?)}";
		Connection connection = null;
		try {
			System.out.println("hr-email" + closeStatus.getEmailId() + " roleId ::" + closeStatus.getRoleId());
			System.out.println("des" + closeStatus.getMailDes());
			System.out.println("flag" + closeStatus.getRankFlag());
			
			
			
			//sendMail.sendMail(closeStatus.getMailDes(), closeStatus.getEmailId(), "HotList Generated !!!");
			
			
			
			connection = jdbcTemplate.getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "Insert");
			callableSt.setBoolean(2, closeStatus.getRankFlag());
			callableSt.setString(3, closeStatus.getEmailId());
			callableSt.setString(4, closeStatus.getMailDes());
			if (closeStatus.getGeneratedLink() == null) {
				callableSt.setString(5, null);
			}
			if (closeStatus.getGeneratedLink() != null) {
				callableSt.setString(5, closeStatus.getGeneratedLink());
			}
			callableSt.setInt(6, closeStatus.getRoleId());
			callableSt.setDate(7, null);
			callableSt.setInt(8, closeStatus.getRoleId());
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

	public List<String> getDownloadCVList(List<CloseStatus> downloadCvlist) {
		List<String> fileNameList = new ArrayList<String>();
		String procedureCall = "{call [Sp_ApplyApp](?,?,?,?,?,?,?,?,?,?,?)}";
		Connection connection = null;
		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			for (CloseStatus closeStatus : downloadCvlist) {
				callableSt.setString(1, "Resume");
				callableSt.setInt(2, 0);
				callableSt.setInt(3, 0);
				callableSt.setString(4, null);
				callableSt.setString(5, null);
				callableSt.setString(6, null);
				callableSt.setString(7, null);
				callableSt.setBoolean(8, false);
				callableSt.setInt(9, 0);
				callableSt.setBoolean(10, false);
				callableSt.setInt(11, closeStatus.getApplyId());

				ResultSet rs = callableSt.executeQuery();
				if (rs.next()) {
					fileNameList.add(rs.getString("CVName")+"###"+rs.getString("ResumePath"));
					// Pk_ApplyId,Title,,CvFile
				}
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
		return fileNameList;

	}

	public String getSelectedUserHROneList(String shortListId,int compRoleId){
		System.out.println("getSelectedUserHROneList method:::"+compRoleId);
		String table="<table border='1'><tr><th>Email ID</th><th>Name</th></tr>";
		try {
				Connection connection = null;
				connection = getJdbcTemplate().getDataSource().getConnection();
				CallableStatement callableStmt = connection.prepareCall("{call Sp_ShortList_One(?,?,?,?,?,?,?,?)}");
				callableStmt.setString(1, "ShortlistedByHR");
				callableStmt.setInt(2,0);
				callableStmt.setInt(3, 0);
				callableStmt.setInt(4, 0);
				callableStmt.setBoolean(5, true);
				callableStmt.setString(6, null);
				callableStmt.setBoolean(7,true);
				callableStmt.setInt(8, compRoleId);
				ResultSet rs = callableStmt.executeQuery();
				System.out.println("result set ::: "+rs);
				while (rs.next()) {
					table+="<tr><td>"+rs.getString("EmailID")+"</td><td>"+rs.getString("Name")+"</td>"+"</tr>";
				}
				table+="</table>";
		} catch (Exception e) {
			System.out.println("inside getSelectedUserHROneList method::");
			e.printStackTrace();
		}
		return table;
	}
	
	
	/*public String getUserData(String shortListId,int compRoleId) {
		System.out.println("compnayRoleId in getUserData method:::"+compRoleId);
		String table="<table border='1'><tr><th>Email ID</th><th>Name</th><th>Perference</th></tr>";
		try {
			String[] idArr = shortListId.split(",");
			for (String id : idArr) {
				//System.out.println("id::"+id);
				Connection connection = null;
				connection = getJdbcTemplate().getDataSource().getConnection();
				CallableStatement callableStmt = connection.prepareCall("{call Sp_ShortList(?,?,?,?)}");
				callableStmt.setString(1, "SelectShortList");
				callableStmt.setInt(2, 0);
				callableStmt.setInt(3, 0);
				callableStmt.setInt(4, 0);
				callableStmt.setBoolean(5, true);
				callableStmt.setString(6, null);
				callableStmt.setBoolean(7,true);
				callableStmt.setInt(8, compRoleId);
				ResultSet rs = callableStmt.executeQuery();
				while (rs.next()) {
					System.out.println("inside while loop");
					table+="<tr><td>"+rs.getString("EmailID")+"</td><td>"+rs.getString("Name")+"</td><td>"+rs.getString("Preference")+"</td></tr>";
				}
				table+="</table>";
				System.out.println("table::"+table);
			}
		} catch (Exception e) {
			System.out.println("inside selectUsersInformation method::");
			e.printStackTrace();
		}
		return table;
	}*/
	

	public String getUserData(String shortListId,int compRoleId) {
		System.out.println("compnayRoleId in getUserData method:::"+compRoleId);
		
		String table="<table border='1'><tr><th>Email ID</th><th>Name</th><th>Perference</th></tr>";
		try {
			
				Connection connection = null;
				connection = getJdbcTemplate().getDataSource().getConnection();
				CallableStatement callableStmt = connection.prepareCall("{call Sp_ShortList_One(?,?,?,?,?,?,?,?)}");
				callableStmt.setString(1, "SelectShortList");
				callableStmt.setInt(2, 0);
				callableStmt.setInt(3, 0);
				callableStmt.setInt(4, 0);
				callableStmt.setBoolean(5, true);
				callableStmt.setString(6, null);
				callableStmt.setBoolean(7,true);
				callableStmt.setInt(8, compRoleId);
				ResultSet rs = callableStmt.executeQuery();
				while (rs.next()) {
					System.out.println("inside while loop");
					table+="<tr><td>"+rs.getString("EmailID")+"</td><td>"+rs.getString("Name")+"</td><td>"+rs.getString("Preference")+"</td></tr>";
				}
				table+="</table>";
				System.out.println("table::"+table);
			
		} catch (Exception e) {
			System.out.println("inside selectUsersInformation method::");
			e.printStackTrace();
		}
		return table;
	}
	public String getSelectedUserRMList(String shortListId,int compRoleId){
		System.out.println("getSelectedUserHRList method:::"+compRoleId);
		String table="<table border='1'><tr><th>Email ID</th><th>Name</th></tr>";
		try {
			Connection connection = null;
			connection = getJdbcTemplate().getDataSource().getConnection();
			CallableStatement callableStmt = connection.prepareCall("{call Sp_ShortList_One(?,?,?,?,?,?,?,?)}");
			callableStmt.setString(1, "ShortlistedByRM");
			callableStmt.setInt(2,0);
			callableStmt.setInt(3, 0);
			callableStmt.setInt(4, 0);
			callableStmt.setBoolean(5, true);
			callableStmt.setString(6, shortListId);
			callableStmt.setBoolean(7,true);
			callableStmt.setInt(8, compRoleId);
			ResultSet rs = callableStmt.executeQuery();
			System.out.println("result set ::: "+rs);
			while (rs.next()) {
				table+="<tr><td>"+rs.getString("EmailID")+"</td><td>"+rs.getString("Name")+"</td>"+"</tr>";
			}
			table+="</table>";
		} catch (Exception e) {
			System.out.println("inside selectUsersInformation method::");
			e.printStackTrace();
		}
		return table;
	}
	
	
}