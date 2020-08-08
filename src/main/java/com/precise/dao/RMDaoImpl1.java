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
import org.springframework.stereotype.Repository;

import com.precise.dbconnection.ConnectionDao;
import com.precise.mail.SendMail;
import com.precise.model.CloseStatus;
import com.precise.model.Message;
import com.precise.model.Schedule;
import com.precise.model.ShortList;

@Repository
public class RMDaoImpl1 extends ConnectionDao implements RMDao1 {

	@Autowired
	SendMail sendMail;
	
	@Override
	public void saveMessage(Message msg) {
		System.out.println("RMDaoImpl.saveMessage()" + msg.getMessage());
		final String procedureCall = "{call proc_insertMessage(?,?,?,?)}";
		Connection connection = null;
		try {
			connection = getJdbcTemplate().getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "insert");
			callableSt.setInt(2, 0);
			callableSt.setString(3, msg.getSubject());
			callableSt.setString(4, msg.getMessage());
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

	@Override
	public List<Message> getAllMessages() {
		System.out.println("RMDaoImpl.getAllMessages()");
		List<Message> messages = new ArrayList<Message>();
		final String procedureCall = "{call proc_insertMessage(?)}";
		Connection connection = null;
		try {
			connection = getJdbcTemplate().getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "Select");
			ResultSet rs = callableSt.executeQuery();

			while (rs.next()) {
				Message msg = new Message();
				msg.setMessageId(rs.getInt("Pk_MessageId"));
				msg.setSubject(rs.getString("Subject"));
				msg.setMessage(rs.getString("Message"));
				messages.add(msg);
				System.out.println("getAllMessages---- " + rs.getString("Subject"));
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
		return messages;
	}

	@Override
	public Message getMessagesByMessageId(int msgId) {
		System.out.println("RMDaoImpl.getMessagesByMessageId()" + msgId);

		Message messages = new Message();
		final String procedureCall = "{call proc_insertMessage(?,?)}";
		Connection connection = null;
		try {
			connection = getJdbcTemplate().getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "SelectByMsgId");
			callableSt.setInt(2, msgId);
			ResultSet rs = callableSt.executeQuery();

			while (rs.next()) {
				messages.setMessageId(rs.getInt("Pk_MessageId"));
				messages.setSubject(rs.getString("Subject"));
				messages.setMessage(rs.getString("Message"));
				System.out.println("getAllMessages---- " + rs.getString("Subject"));
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
		return messages;
	}

	@Override
	public void updateMessage(Message msg) {
		System.out.println("RMDaoImpl.updateMessage()" + msg.getMessage());
		final String procedureCall = "{call proc_insertMessage(?,?,?,?)}";
		Connection connection = null;
		try {
			connection = getJdbcTemplate().getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "Update");
			callableSt.setInt(2, msg.getMessageId());
			callableSt.setString(3, msg.getSubject());
			callableSt.setString(4, msg.getMessage());
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

	@Override
	public void deleteMessage(Message msg) {
		System.out.println("RMDaoImpl.deleteMessage()" + msg.getMessage());
		final String procedureCall = "{call proc_insertMessage(?,?)}";
		Connection connection = null;
		try {
			connection = getJdbcTemplate().getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "delete");
			callableSt.setInt(2, msg.getMessageId());
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

	@Override
	public void saveSchedule(Schedule schedule) {
		System.out.println("RMDaoImpl.saveSchedule()" + schedule.getSubjectId());
		final String procedureCall = "{call proc_InsertSchedule(?,?,?,?,?,?,?)}";
		Connection connection = null;
		try {
			System.out.println("inside saveSchedule method :::"+schedule.getScheduleDate());
			connection = getJdbcTemplate().getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "Insert");
			callableSt.setInt(2, 0);
			callableSt.setInt(3, schedule.getSubjectId());
			callableSt.setString(4, schedule.getScheduleDate());
			callableSt.setBoolean(5, true);
			callableSt.setBoolean(6, false);
			callableSt.setInt(7, schedule.getCmpId());
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

	@Override
	public List<Schedule> getAllSchedules(int cmpId) {
		System.out.println("RMDaoImpl.getAllSchedules()");
		List<Schedule> schedules = new ArrayList<Schedule>();
		final String procedureCall = "{call proc_InsertSchedule(?,?)}";
		
		Connection connection = null;
		try {
			connection = getJdbcTemplate().getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "Select");
			callableSt.setInt(2, cmpId);
			ResultSet rs = callableSt.executeQuery();

			while (rs.next()) {
				Schedule sch = new Schedule();
				sch.setScheduleId(rs.getInt("Pk_ScheduleId"));
				sch.setSubjectId(rs.getInt("fk_Subjectid"));
				sch.setSubject(rs.getString("Subject"));
				sch.setDateTime(rs.getString("DateTime"));
				schedules.add(sch);
				System.out.println("getAllMessages---- " + rs.getInt("fk_Subjectid"));
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
		return schedules;
	}

	@Override
	public void deleteSchedule(int scheduleId) {
		System.out.println("RMDaoImpl.deleteSchedule()" + scheduleId);
		final String procedureCall = "{call proc_InsertSchedule(?,?)}";
		Connection connection = null;
		try {
			connection = getJdbcTemplate().getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "Delete");
			callableSt.setInt(2, scheduleId);
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

	@Override
	public Schedule getScheduleByScheduleId(int scheduleId) {
		System.out.println("RMDaoImpl.getScheduleByScheduleId()" + scheduleId);
		Schedule schedule = new Schedule();
		final String procedureCall = "{call proc_InsertSchedule(?,?)}";
		Connection connection = null;
		try {
			connection = getJdbcTemplate().getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "SelectByScheduleId");
			callableSt.setInt(2, scheduleId);
			ResultSet rs = callableSt.executeQuery();

			while (rs.next()) {
				schedule.setScheduleId(rs.getInt("Pk_ScheduleId"));
				schedule.setSubjectId(rs.getInt("fk_Subjectid"));
				schedule.setSubject(rs.getString("Subject"));
				schedule.setDateTime(rs.getString("DateTime"));
				System.out.println("schedule-daio-" + rs.getInt("fk_Subjectid"));
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
		return schedule;
	}

	@Override
	public void updateSchedule(Schedule schedule) {
		System.out.println("RMDaoImpl.updateSchedule()" + schedule.getScheduleId());
		final String procedureCall = "{call proc_InsertSchedule(?,?,?,?,?,?,?)}";
		Connection connection = null;
		try {
			connection = getJdbcTemplate().getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "Update");
			callableSt.setInt(2, schedule.getScheduleId());
			callableSt.setInt(3, schedule.getSubjectId());
			callableSt.setString(4, schedule.getDateTime());
			callableSt.setBoolean(5, true);
			callableSt.setBoolean(6, false);
			callableSt.setInt(7, schedule.getCmpId());
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

	@Override
	public List<ShortList> getShortlistedDataByRole(int role) {
	//	System.out.println("csddddddddddddddddddddddddddddddddddd");
		List<ShortList> shortList1 = new ArrayList<ShortList>();
		String procedureCall = "{call Sp_ShortList_One(?,?,?,?,?,?,?,?)}";
		Connection connection = null;
		try {
			connection = getJdbcTemplate().getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "SelectShortList");
			callableSt.setInt(2, 0);
			callableSt.setInt(3, 0);
			callableSt.setInt(4, 0);
			callableSt.setBoolean(5, false);
			callableSt.setString(6, null);
			callableSt.setBoolean(7, false);
			callableSt.setInt(8, role);
			ResultSet rs = callableSt.executeQuery();

			while (rs.next()) {
				ShortList shortlist = new ShortList();
				shortlist.setApplyId(rs.getInt("Pk_ApplyId"));
				shortlist.setShortListId(rs.getInt("PK_ShortListIdOne"));
				shortlist.setEmailID(rs.getString("EmailID"));
				shortlist.setName(rs.getString("Name"));
				shortlist.setCvName(rs.getString("CVName"));
				shortlist.setPreference(rs.getInt("Preference"));
				shortlist.setRollNumber(rs.getInt("RollNumber"));
				shortlist.setStatus(rs.getString("Status"));
				shortlist.setShortList(rs.getBoolean("IsShortListOne"));
				shortlist.setRmStatus(rs.getString("RMStatus"));
				shortlist.setCmpRoleId(rs.getInt("Fk_RoleId"));
				shortlist.setShortListBy(rs.getString("ShortListByOne"));
				shortList1.add(shortlist);
				//System.out.println("get shortlistedd---- " + rs.getInt("Pk_ApplyId"));
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
		return shortList1;
	}

	@Override
	public List<ShortList> getShortlistedDataByRoleForHR(int cmpRoleId) {
		//System.out.println("RMDaoImpl.getShortlistedDataByRole()");
				List<ShortList> shortList = new ArrayList<ShortList>();
				String procedureCall = "{call Sp_ShortList_One(?,?,?,?,?,?,?,?)}";
				Connection connection = null;
				try {
					connection = getJdbcTemplate().getDataSource().getConnection();
					CallableStatement callableSt = connection.prepareCall(procedureCall);
					callableSt.setString(1, "SelectHrShortlist");
					callableSt.setInt(2, 0);
					callableSt.setInt(3, 0);
					callableSt.setInt(4, 0);
					callableSt.setBoolean(5, false);
					callableSt.setString(6, null);
					callableSt.setBoolean(7, false);
					callableSt.setInt(8, cmpRoleId);
					ResultSet rs = callableSt.executeQuery();

					while (rs.next()) {
						ShortList shortlist = new ShortList();
						shortlist.setApplyId(rs.getInt("Pk_ApplyId"));
						shortlist.setShortListId(rs.getInt("PK_ShortListIdOne"));
						shortlist.setEmailID(rs.getString("EmailID"));
						shortlist.setName(rs.getString("Name"));
						shortlist.setPreference(rs.getInt("Preference"));
						shortlist.setRollNumber(rs.getInt("RollNumber"));
						shortlist.setStatus(rs.getString("Status"));
						shortlist.setShortList(rs.getBoolean("IsShortListOne"));					
						shortList.add(shortlist);
						//System.out.println("get shortlistedd---- " + rs.getInt("Pk_ApplyId"));
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
				return shortList;
	}
	
	@Override
	public void shortlistRelease(ShortList shortlist) throws SQLException {
		System.out.println("RMDaoImpl.shortlistRelease()");
		List<Integer> shorlistedIds = shortlist.getShortListIdslist();
		List<Integer> notshorlistedIds = shortlist.getNotSelected();
		System.out.println("shorlistedIds-----in save shortlist" + shorlistedIds + "not selected--" + notshorlistedIds);
		Connection connection = null;

		CallableStatement callableStmt = null;
		try {
			connection = getJdbcTemplate().getDataSource().getConnection();
			connection.setAutoCommit(false);
			callableStmt = connection.prepareCall("{call Sp_ShortList_One(?,?,?,?,?,?,?)}");
			for (int i = 0; i < shorlistedIds.size(); i++) {
				callableStmt.setString(1, "ShortList");
				callableStmt.setInt(2, 0);
				callableStmt.setInt(3,shortlist.getCreatedBy());
				callableStmt.setInt(4, shorlistedIds.get(i));
				callableStmt.setBoolean(5,true);
				callableStmt.setString(6, "RM");
				callableStmt.setBoolean(7, false);
				callableStmt.addBatch();
			}
			//callableStmt.executeBatch();
/*
			for (int i = 0; i < notshorlistedIds.size(); i++) {
				callableStmt.setString(1, "ShortList");
				callableStmt.setInt(2, 0);
				callableStmt.setInt(3, 0);
				callableStmt.setInt(4, notshorlistedIds.get(i));
				callableStmt.setBoolean(5, false);
				callableStmt.setString(6, "RM");
				callableStmt.setBoolean(7, false);
				callableStmt.addBatch();
			}*/
			callableStmt.executeBatch();

		} catch (SQLException e) {
			System.out.println("Exception in shortlistRelease" + e);
			// connection.rollback();
			e.printStackTrace();
		}

		// procedureCall = "{call sp_InsertCompany(?)}";
		try {
			callableStmt = connection.prepareCall("{call [Sp_StdGreeting](?,?,?,?,?)}");
			for (int i = 0; i < shorlistedIds.size(); i++) {
				callableStmt.setString(1, "Insert");
				callableStmt.setString(2, shortlist.getGreetings());
				callableStmt.setString(3, "");
				callableStmt.setString(4, "RM");
				callableStmt.setString(5, "Shortlisted");
				// callableStmt.setDate(6, "");
				callableStmt.addBatch();
			}
			callableStmt.executeBatch();
			connection.commit();
		} catch (SQLException e) {
			connection.rollback();
			e.printStackTrace();
		}

	}

	@Override
	public JSONArray getGreetings(int cmpRoleId) {

		//List<CloseStatus> userdata = new ArrayList<CloseStatus>();

		JSONArray jsonArray = new JSONArray();
		final String procedureCall = "{call Sp_ClosedStatus(?,?,?,?,?,?)}";
		Connection connection = null;
		
		try {
			connection = getJdbcTemplate().getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "Header");
			callableSt.setInt(2, 0);
			callableSt.setString(3, null);
			callableSt.setString(4, null);
			callableSt.setInt(5, 0);
			callableSt.setInt(6, cmpRoleId);

			ResultSet rs = callableSt.executeQuery();
			while (rs.next()) {
				JSONObject userDetailsJson = new JSONObject();
				userDetailsJson.put("year", rs.getInt("Year"));
				userDetailsJson.put("CompanyName", rs.getString("CompanyName"));
				userDetailsJson.put("ProcessName", rs.getString("ProcessName"));
				userDetailsJson.put("WorkExpReq", rs.getString("WorkExpReq"));
				userDetailsJson.put("roleName", rs.getString("DesignationName"));
				
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
	public List<String> getRMDownloadCVList(List<CloseStatus> downloadCvlist) {
		List<String> fileNameList = new ArrayList<String>();
		String procedureCall = "{call [Sp_ApplyApp](?,?,?,?,?,?,?,?,?,?,?)}";
		Connection connection = null;
		try {
			connection = getJdbcTemplate().getDataSource().getConnection();
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
					//fileNameList.add(rs.getString("ResumePath"));
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

	@Override
	public List<String> getRMDownloadCoverList(List<CloseStatus> downloadCvlist) {
		List<String> fileNameList = new ArrayList<String>();
		String procedureCall = "{call [Sp_ApplyApp](?,?,?,?,?,?,?,?,?,?,?)}";
		Connection connection = null;
		try {
			connection = getJdbcTemplate().getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			for (CloseStatus closeStatus : downloadCvlist) {
				callableSt.setString(1, "Coverlatter");
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
					//fileNameList.add(rs.getString("ResumePath"));
					fileNameList.add(rs.getString("CVName")+"###"+rs.getString("ResumePath"));
					if(rs.getString("CoverPath")!=null){
						fileNameList.add(rs.getString("CoverTitle")
							+"###"+rs.getString("CoverPath")==null?"":rs.getString("CoverPath"));
					}
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

	@Override
	public void shortlistRMRelease(ShortList shortlist,int userid) throws SQLException {
		System.out.println("RMDaoImpl.shortlistRelease()");
		List<Integer> shorlistedIds = shortlist.getShortListIdslist();
		List<Integer> applyIdListIdslist = shortlist.getApplyIdListIdslist();
		System.out.println("Apply id list ::::::::: "+applyIdListIdslist);
		Connection connection = null;

		CallableStatement callableStmt = null, callableInsert = null;
		try {
			connection = getJdbcTemplate().getDataSource().getConnection();

			callableStmt = connection.prepareCall("{call Sp_ShortList_One(?,?,?,?,?,?,?)}");
			for (int i = 0; i < shorlistedIds.size(); i++) {
				callableStmt.setString(1, "ShortListRelease");
				callableStmt.setInt(2, shorlistedIds.get(i));
				callableStmt.setInt(3, userid);
				callableStmt.setInt(4, 0);
				callableStmt.setBoolean(5, false);
				callableStmt.setString(6, "RM");
				callableStmt.setBoolean(7, true);
				callableStmt.addBatch();
			}
			callableStmt.executeBatch();

			callableInsert = connection.prepareCall("{call Sp_ShortList(?,?,?,?)}");
			for (int i = 0; i < shorlistedIds.size(); i++) {
				callableInsert.setString(1, "Insert");
				callableInsert.setInt(2, 0);
				callableInsert.setInt(3,shortlist.getCreatedBy() );
				callableInsert.setInt(4, applyIdListIdslist.get(i));
				
				callableInsert.addBatch();
			}
			callableInsert.executeBatch();

		} catch (SQLException e) {
			System.out.println("Exception in shortlistRelease" + e);
			// connection.rollback();
			e.printStackTrace();
		}

		/*
		 * try { callableStmt = connection.prepareCall(
		 * "{call [Sp_StdGreeting](?,?,?,?,?)}"); for(int
		 * i=0;i<shorlistedIds.size();i++){ callableStmt.setString(1,"Insert");
		 * callableStmt.setString(2,shortlist.getGreetings());
		 * callableStmt.setString(3,""); callableStmt.setString(4, "HR");
		 * callableStmt.setString(5, "Shortlisted"); //callableStmt.setDate(6,
		 * ""); callableStmt.addBatch(); } callableStmt.executeBatch();
		 * connection.commit(); } catch (SQLException e) {
		 * connection.rollback(); e.printStackTrace(); }
		 */
	}
	
	@Override
	public void shortlistOneAfterGD(ShortList shortlist) {
		System.out.println("RMDaoImpl.shortlistOneAfterGD()");
		List<Integer> shorlistedIds = shortlist.getShortListIdslist();
		List<Integer> applyIdListIdslist = shortlist.getApplyIdListIdslist();
		System.out.println("Apply id list ::::::::: "+applyIdListIdslist);
		Connection connection = null;

		CallableStatement callableStmt = null, callableInsert = null;
		try {
			connection = getJdbcTemplate().getDataSource().getConnection();

			callableStmt = connection.prepareCall("{call Sp_ShortList_One(?,?,?,?,?,?,?)}");
			for (int i = 0; i < shorlistedIds.size(); i++) {
				callableStmt.setString(1, "ShortListAfterGD");
				callableStmt.setInt(2, shorlistedIds.get(i));
				callableStmt.setInt(3, shortlist.getCreatedBy());
				callableStmt.setInt(4, 0);
				callableStmt.setBoolean(5,true);
				callableStmt.setString(6, "RM");
				callableStmt.setBoolean(7, true);
				callableStmt.addBatch();
			}
			callableStmt.executeBatch();
			
			callableInsert = connection.prepareCall("{call Sp_ShortList(?,?,?,?)}");
			for (int i = 0; i < applyIdListIdslist.size(); i++) {
				callableInsert.setString(1, "Insert");
				callableInsert.setInt(2, 0);
				callableInsert.setInt(3,shortlist.getCreatedBy() );
				callableInsert.setInt(4, applyIdListIdslist.get(i));
				callableInsert.addBatch();
			}
			callableInsert.executeBatch();

		} catch (SQLException e) {
			System.out.println("Exception in shortlistOneAfterGD" + e);
			// connection.rollback();
			e.printStackTrace();
		}

		
	}
	

	@Override
	public List<ShortList> getshortlistReleaseCompany(int role) {
		System.out.println("RMDaoImpl.getShortlistedDataByRole()");
		List<ShortList> shortList = new ArrayList<ShortList>();
		String procedureCall = "{call Sp_ShortList_One(?,?,?,?,?,?,?,?)}";
		Connection connection = null;
		try {
			connection = getJdbcTemplate().getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "SelectShortListRelease");
			callableSt.setInt(2, 0);
			callableSt.setInt(3, 0);
			callableSt.setInt(4, 0);
			callableSt.setBoolean(5, false);
			
			callableSt.setString(6, null);
			
			callableSt.setBoolean(7, false);
			callableSt.setInt(8, role);
			ResultSet rs = callableSt.executeQuery();

			while (rs.next()) {
				ShortList shortlist = new ShortList();
				shortlist.setApplyId(rs.getInt("Pk_ApplyId"));
				shortlist.setShortListId(rs.getInt("PK_ShortListIdOne"));
				shortlist.setEmailID(rs.getString("EmailID"));
				shortlist.setName(rs.getString("Name"));
				shortlist.setPreference(rs.getInt("Preference"));
				shortlist.setRollNumber(rs.getInt("RollNumber"));
				shortlist.setStatus(rs.getString("Status"));
				shortlist.setShortList(rs.getBoolean("IsShortListOne"));
				shortList.add(shortlist);				
				//System.out.println("get shortlistedd---- " + rs.getInt("Pk_ApplyId"));
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
		return shortList;
	}

	public String insertIntoInbox() {
		System.out.println("inside insertIntoInbox method::");
		
		return null;
	}
	
	public String selectUsersInformation(int applyId,String greeting,int userId, String sender_email){
		System.out.println("inside method"+applyId);
		try{
			Connection connection = null;
			connection = getJdbcTemplate().getDataSource().getConnection();
			CallableStatement callableStmt = connection.prepareCall("{call Sp_ShortList_One(?,?,?,?)}");
			callableStmt.setString(1, "selectSortListUserValues");
			callableStmt.setInt(2, 0);
			callableStmt.setInt(3, 0);
			callableStmt.setInt(4, applyId);
			ResultSet rs = callableStmt.executeQuery();
			while(rs.next()){
				System.out.println("select value::"+rs.getString("EmailID"));
				sendMail.sendMail(greeting, rs.getString("EmailID"), "Congratulations !!!");
				CallableStatement callableStmtForInbox = connection.prepareCall("{call proc_inbox(?,?,?,?,?,?,?,?,?,?)}");
				callableStmtForInbox.setString(1, "insert");
				callableStmtForInbox.setInt(2, 0);
				callableStmtForInbox.setString(3, "Congratulations !!!");
				callableStmtForInbox.setString(4, greeting);
				callableStmtForInbox.setInt(5, 1);
				callableStmtForInbox.setInt(6, rs.getInt("FK_UserId"));
				callableStmtForInbox.setInt(7, userId);
				callableStmtForInbox.setInt(8, 4);
				callableStmtForInbox.setString(9, null);
				callableStmtForInbox.setString(10, sender_email);
				callableStmtForInbox.execute();
			}
			}catch(Exception e){
				System.out.println("inside selectUsersInformation method::");
				e.printStackTrace();
			}
		return null;
	}
	
	
	
	
	@Override
	public String selectUsersInformationByRM(int applyId,String studentGreetingSubject,String greetings, int userID, String sender_email) {
		System.out.println("inside method"+applyId);
		String str="";
		try{
			Connection connection = null;
			connection = getJdbcTemplate().getDataSource().getConnection();
			CallableStatement callableStmt = connection.prepareCall("{call Sp_ShortList_One(?,?,?,?)}");
			callableStmt.setString(1, "selectSortListUserValues");
			callableStmt.setInt(2, 0);
			callableStmt.setInt(3, 0);
			callableStmt.setInt(4, applyId);
			ResultSet rs = callableStmt.executeQuery();
			while(rs.next()){
				System.out.println("select value::"+rs.getString("EmailID"));
				str=rs.getString("EmailID");
				CallableStatement callableStmtForInbox = connection.prepareCall("{call proc_inbox(?,?,?,?,?,?,?,?,?,?)}");
				callableStmtForInbox.setString(1, "insert");
				callableStmtForInbox.setInt(2, 0);
				callableStmtForInbox.setString(3,studentGreetingSubject);
				callableStmtForInbox.setString(4, greetings);
				callableStmtForInbox.setInt(5, 1);
				callableStmtForInbox.setInt(6, rs.getInt("FK_UserId"));
				callableStmtForInbox.setInt(7, userID);
				callableStmtForInbox.setInt(8, 4);
				callableStmtForInbox.setString(9, null);
				callableStmtForInbox.setString(10, sender_email);
				callableStmtForInbox.execute();
			}
			//sendMail.sendMailShortList(greetings,str.toString(),"",studentGreetingSubject);
			
			/*CallableStatement callableInsert = connection.prepareCall("{call Sp_hotList(?,?,?,?,?)}");
				System.out.println("apply id::"+applyIdListIdslist.get(i));
				callableInsert.setString(1, "Insert");
				callableInsert.setInt(2, 0);
				callableInsert.setInt(3, 0);
				callableInsert.setInt(4, applyId);
				callableInsert.setBoolean(5,false);
				callableInsert.execute();*/
			
				CallableStatement callableUpdate = connection.prepareCall("{call Sp_ShortList_One(?,?,?,?,?,?,?)}");
				/*System.out.println("apply id::"+applyIdListIdslist.get(i));*/
				callableUpdate.setString(1, "ShortList");
				callableUpdate.setInt(2, 0);
				callableUpdate.setInt(3, 0);
				callableUpdate.setInt(4, applyId);
				callableUpdate.setBoolean(5, true);
				callableUpdate.setString(6, "HR");
				callableUpdate.setBoolean(7, true);
				callableUpdate.execute();
			
			//callableInsert.executeBatch();
			}catch(Exception e){
				System.out.println("inside selectUsersInformation method::");
				e.printStackTrace();
			}
		return str;
	}

	@Override
	public void saveHRShortlisted(ShortList shortlist) throws SQLException {
		System.out.println("RMDaoImpl.saveHRShortlisted()");
		List<Integer> shorlistedIds = shortlist.getShortListIdslist();
	//	List<Integer> notshorlistedIds = shortlist.getNotSelected();
	//	System.out.println("shorlisted" + shorlistedIds + "not selected--" + notshorlistedIds);
		Connection connection = null;

		CallableStatement callableStmt = null;
		//CallableStatement callableInsert=null;
		try {
			connection = getJdbcTemplate().getDataSource().getConnection();
			callableStmt = connection.prepareCall("{call Sp_ShortList_One(?,?,?,?,?,?,?)}");
			System.out.println("shorlistedIds:::"+shorlistedIds.size());
			for (int i = 0; i < shorlistedIds.size(); i++) {
				System.out.println("shorlistedIds.get(i)::"+shorlistedIds.get(i));
				callableStmt.setString(1, "ShortListHR");
				callableStmt.setInt(2, shorlistedIds.get(i));//
				callableStmt.setInt(3, 0);
				callableStmt.setInt(4, 0);//shorlistedIds.get(i)
				callableStmt.setBoolean(5, true);
				callableStmt.setString(6, "HR");
				callableStmt.setBoolean(7, false);
				callableStmt.addBatch();
			}
			
			callableStmt.executeBatch();

		} catch (SQLException e) {
			System.out.println("Exception in shortlistRelease" + e);
			// connection.rollback();
			e.printStackTrace();
		}
		
		try {
			callableStmt = connection.prepareCall("{call [Sp_StdGreeting](?,?,?,?,?)}");
			for (int i = 0; i < shorlistedIds.size(); i++) {
				callableStmt.setString(1, "Insert");
				callableStmt.setString(2, shortlist.getGreetings());
				callableStmt.setString(3, "");
				callableStmt.setString(4, "RM");
				callableStmt.setString(5, "Shortlisted");				
				callableStmt.addBatch();
			}
			callableStmt.executeBatch();
			//connection.commit();
		} catch (SQLException e) {
			connection.rollback();
			e.printStackTrace();
		}
		
		/* 
		List<Integer> applyIdListIdslist = shortlist.getApplyIdListIdslist();
		for(Integer i:applyIdListIdslist){
			System.out.println("apply id in sve har"+i);
		}
		try {
		callableInsert = connection.prepareCall("{call Sp_hotList(?,?,?,?,?)}");
		for (int i = 0; i < shorlistedIds.size(); i++) {
			System.out.println("apply id::"+applyIdListIdslist.get(i));
			callableInsert.setString(1, "Insert");
			callableInsert.setInt(2, 0);
			callableInsert.setInt(3, 0);
			callableInsert.setInt(4, applyIdListIdslist.get(i));
			callableInsert.setBoolean(5, true);
			callableInsert.addBatch();
		}
		System.out.println("hiiillllee");
		callableInsert.executeBatch();
		
		}catch (SQLException e) {
			
			e.printStackTrace();
		}*/
	}

	@Override
	public List<ShortList> getAllRM(int compRoleId) {
	
		System.out.println("getAllRM" + compRoleId);
		List<ShortList> shortList = new ArrayList<ShortList>();
		String procedureCall = "{call Sp_ShortList_One(?,?,?,?,?,?,?,?,?,?,?,?,?)}";
		Connection connection = null;
		try {
			connection = getJdbcTemplate().getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "selectAllRM");
			callableSt.setInt(2, 0);
			callableSt.setInt(3, 0);
			callableSt.setInt(4, 0);
			callableSt.setBoolean(5, false);			
			callableSt.setString(6, null);			
			callableSt.setBoolean(7, false);
			callableSt.setInt(8, 0);
			callableSt.setBoolean(9, false);
			callableSt.setBoolean(10, false);
			callableSt.setInt(11, 0);
			callableSt.setInt(12, 0);
			callableSt.setInt(13, compRoleId);			
			ResultSet rs = callableSt.executeQuery();
			while (rs.next()) {
				ShortList shortlist = new ShortList();				
				shortlist.setEmailID(rs.getString("RMEmail"));
				shortlist.setName(rs.getString("RMName"));				
				shortList.add(shortlist);
				if(rs.getString("ShareRMEmail")!=null){
					ShortList shortlist1 = new ShortList();				
					shortlist1.setEmailID(rs.getString("ShareRMEmail"));
					shortlist1.setName(rs.getString("ShareRMName"));				
					shortList.add(shortlist1);
				}
			}
			String procedureCall1 = "{call Sp_ShortList_One(?)}";
			callableSt = connection.prepareCall(procedureCall1);
			callableSt.setString(1, "selectAllSuperRM");			
			rs = callableSt.executeQuery();
			while (rs.next()) {
				ShortList shortlist = new ShortList();				
				shortlist.setEmailID(rs.getString("RMEmail"));
				shortlist.setName(rs.getString("RMName"));				
				shortList.add(shortlist);					
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		finally {
			if (connection != null)
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}		
		return shortList;		
	}

	@Override
	public CloseStatus getAllHrList(int compRoleId) {
	
		System.out.println("getAllHR" + compRoleId);
		String procedureCall = "{call Usp_HrMailid(?,?)}";
		Connection connection = null;
		CloseStatus closeStatus=null;
		try {
			connection = getJdbcTemplate().getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "Select");
			callableSt.setInt(2,compRoleId);
					
			ResultSet rs = callableSt.executeQuery();
			if (rs.next()) {
				closeStatus= new CloseStatus();
				closeStatus.setHrEmailId(rs.getString("HrMailId"));
				closeStatus.setHrName(rs.getString("HrName"));
				closeStatus.setEmailId(rs.getString("RmMailId"));
				closeStatus.setMailDes(rs.getString("MailText"));

				//hrList.add(closeStatus);

				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		finally {
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
	public List<ShortList> getHRShortlistedDataByRole(int cmpRoleId) {
		System.out.println("RMDaoImpl.getHRShortlistedDataByRole())))))))))))))"+cmpRoleId);
		List<ShortList> shortList = new ArrayList<ShortList>();
		String procedureCall = "{call Sp_ShortList_One(?,?,?,?,?,?,?,?)}";
		Connection connection = null;
		try {
			connection = getJdbcTemplate().getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "ShortlistedByHR");
			callableSt.setInt(2, 0);
			callableSt.setInt(3, 0);
			callableSt.setInt(4, 0);
			callableSt.setBoolean(5, false);
			callableSt.setString(6, null);
			callableSt.setBoolean(7, false);
			callableSt.setInt(8, cmpRoleId);
			ResultSet rs = callableSt.executeQuery();

			while (rs.next()) {
				System.out.println("in rs())))))))))))))"+rs.getInt("Pk_ApplyId"));
				ShortList shortlist = new ShortList();
				shortlist.setApplyId(rs.getInt("Pk_ApplyId"));
				shortlist.setShortListId(rs.getInt("PK_ShortListIdOne"));
				shortlist.setEmailID(rs.getString("EmailID"));
				shortlist.setName(rs.getString("Name"));
				shortlist.setPreference(rs.getInt("Preference"));
				shortlist.setRollNumber(rs.getInt("RollNumber"));
				shortlist.setStatus(rs.getString("Status"));
				shortlist.setShortList(rs.getBoolean("IsShortListOne"));
				shortlist.setCmpRoleId(rs.getInt("Fk_RoleId"));
				shortList.add(shortlist);
			System.out.println("get shortlistedd---- " +shortList.toString());
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
		return shortList;
	}

	@Override
	public void releaseHRShortlisted(ShortList shortlist) {
		System.out.println("RMDaoImpl.releaseHRShortlisted()");
		List<Integer> shorlistedIds = shortlist.getShortListIdslist();
		List<Integer> applyIdListIdslist = shortlist.getApplyIdListIdslist();
		//List<Integer> notshorlistedIds = shortlist.getNotSelected();
		System.out.println("shorlistedIds-----in save shortlist" + shorlistedIds);
		Connection connection = null;

		CallableStatement callableStmt = null,callableInsert = null;;
		try {
			connection = getJdbcTemplate().getDataSource().getConnection();
			//connection.setAutoCommit(false);
			callableStmt = connection.prepareCall("{call Sp_ShortList_One(?,?,?,?,?,?,?)}");
			for (int i = 0; i < shorlistedIds.size(); i++) {
				callableStmt.setString(1, "ShortListRelease");
				callableStmt.setInt(2, shorlistedIds.get(i));
				callableStmt.setInt(3, 0);
				callableStmt.setInt(4,0);
				callableStmt.setBoolean(5, false);
				callableStmt.setString(6, null);
				callableStmt.setBoolean(7, true);
				callableStmt.addBatch();
			}
			
			callableStmt.executeBatch();
			callableInsert = connection.prepareCall("{call Sp_ShortList(?,?,?,?,?)}");
			for (int i = 0; i < applyIdListIdslist.size(); i++) {
				callableInsert.setString(1, "Insert");
				callableInsert.setInt(2, 0);
				callableInsert.setInt(3, 0);
				callableInsert.setInt(4, applyIdListIdslist.get(i));
				callableInsert.setBoolean(5,false);
				
				callableInsert.addBatch();
			}
			callableInsert.executeBatch();
		} catch (SQLException e) {
			System.out.println("Exception in shortlistRelease" + e);
			// connection.rollback();
			e.printStackTrace();
		}

		
		try {
			callableStmt = connection.prepareCall("{call [Sp_StdGreeting](?,?,?,?,?)}");
			for (int i = 0; i < shorlistedIds.size(); i++) {
				callableStmt.setString(1, "Insert");
				callableStmt.setString(2, shortlist.getGreetings());
				callableStmt.setString(3, "");
				callableStmt.setString(4, "RM");
				callableStmt.setString(5, "Shortlisted");
				// callableStmt.setDate(6, "");
				callableStmt.addBatch();
			}
			callableStmt.executeBatch();
			//connection.commit();
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		
	}

	
	@Override
	public CloseStatus getStudentGreetings(int cmpRoleId) {

		//List<CloseStatus> userdata = new ArrayList<CloseStatus>();

		CloseStatus closeStatus = null;
		final String procedureCall = "{call Sp_ClosedStatus(?,?,?,?,?,?)}";
		Connection connection = null;
		
		try {
			connection = getJdbcTemplate().getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "Header");
			callableSt.setInt(2, 0);
			callableSt.setString(3, null);
			callableSt.setString(4, null);
			callableSt.setInt(5, 0);
			callableSt.setInt(6, cmpRoleId);

			ResultSet rs = callableSt.executeQuery();
			while (rs.next()) {
				closeStatus=new CloseStatus();
				
				closeStatus.setFirmName(rs.getString("CompanyName"));
				closeStatus.setRoleName( rs.getString("DesignationName"));
				
				
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
	public void shortlistRemove(ShortList shortlist,int userid) throws SQLException {
		System.out.println("RMDaoImpl.shortlistRelease()");
		List<Integer> shorlistedIds = shortlist.getShortListIdslist();
		List<Integer> notshorlistedIds = shortlist.getNotSelected();
	//	System.out.println("shorlistedIds-----in save shortlist" + shorlistedIds + "not selected--" + notshorlistedIds);
		Connection connection = null;
		CallableStatement callableStmt = null;
		try {
			connection = getJdbcTemplate().getDataSource().getConnection();
			connection.setAutoCommit(false);
			callableStmt = connection.prepareCall("{call Sp_ShortList_One(?,?,?,?,?,?,?)}");
			for (int i = 0; i < shorlistedIds.size(); i++) {
				callableStmt.setString(1, "ShortList");
				callableStmt.setInt(2, 0);
				callableStmt.setInt(3, userid);
				callableStmt.setInt(4, shorlistedIds.get(i));
				callableStmt.setBoolean(5, false);
				callableStmt.setString(6,"");
				callableStmt.setBoolean(7, false);
				callableStmt.addBatch();
			}
			callableStmt.executeBatch();

		} catch (SQLException e) {
			System.out.println("Exception in shortlistRelease" + e);
			// connection.rollback();
			e.printStackTrace();
		}
		try {
			callableStmt = connection.prepareCall("{call [Sp_StdGreeting](?,?,?,?,?)}");
			for (int i = 0; i < shorlistedIds.size(); i++) {
				callableStmt.setString(1, "Insert");
				callableStmt.setString(2, shortlist.getGreetings());
				callableStmt.setString(3, "");
				callableStmt.setString(4, "RM");
				callableStmt.setString(5, "Shortlisted");
				// callableStmt.setDate(6, "");
				callableStmt.addBatch();
			}
			callableStmt.executeBatch();
			connection.commit();
		} catch (SQLException e) {
			connection.rollback();
			e.printStackTrace();
		}
	}

	@Override
	public boolean getReleaseFlagStatus(int cmpRoleId) {
		boolean flag=false;
		final String procedureCall = "{call Sp_ShortList_One(?,?)}";
		Connection connection = null;
		
		try {
			connection = getJdbcTemplate().getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "checkReleaseShortlist");
			callableSt.setInt(2, cmpRoleId);
			ResultSet rs = callableSt.executeQuery();
			if (rs.next()) {
				flag=rs.getBoolean("flag");
				System.out.println(flag+"  getReleaseFlagStatus");
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
		return flag;

	}

	@Override
	public void removeHRShortlisted(ShortList shortlist)  {
		System.out.println("RMDaoImpl.shortlistRelease()");
		List<Integer> shorlistedIds = shortlist.getShortListIdslist();
		List<Integer> notshorlistedIds = shortlist.getNotSelected();
		System.out.println("shorlistedIds-----in save shortlist" + shorlistedIds + "not selected--" + notshorlistedIds);
		Connection connection = null;
		CallableStatement callableStmt = null;
		try {
			connection = getJdbcTemplate().getDataSource().getConnection();
			callableStmt = connection.prepareCall("{call Sp_ShortList_One(?,?,?,?,?,?,?)}");
			System.out.println("shorlistedIds:::"+shorlistedIds.size());
			for (int i = 0; i < shorlistedIds.size(); i++) {
				System.out.println("shorlistedIds.get(i)::"+shorlistedIds.get(i));
				callableStmt.setString(1, "ShortListHR");
				callableStmt.setInt(2, shorlistedIds.get(i));//
				callableStmt.setInt(3, 0);
				callableStmt.setInt(4, 0);//shorlistedIds.get(i)
				callableStmt.setBoolean(5, false);
				callableStmt.setString(6, "RM");
				callableStmt.setBoolean(7, false);
				callableStmt.addBatch();
			}
			
			callableStmt.executeBatch();

		} catch (SQLException e) {
			System.out.println("Exception in shortlistRelease" + e);
			// connection.rollback();
			e.printStackTrace();
		}

		
	}

}