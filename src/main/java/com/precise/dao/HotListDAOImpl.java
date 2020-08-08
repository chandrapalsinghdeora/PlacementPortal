package com.precise.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.precise.dbconnection.ConnectionDao;
import com.precise.mail.SendMail;
import com.precise.model.HotList;
import com.precise.model.Message;
import com.precise.model.Schedule;
import com.precise.model.ShortList;

@Repository
public class HotListDAOImpl extends ConnectionDao implements HotListDAO {
	
	@Autowired
	SendMail sendMail;

	@Override
	public void saveMessage(Message msg) {
		System.out.println("HotListDAOImpl.saveMessage()" + msg.getMessage());
		final String procedureCall = "{call proc_insertMessage(?,?,?,?)}";
		Connection connection = null;
		try {
			connection = getJdbcTemplate().getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "Insert");
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
		System.out.println("HotListDAOImpl.getAllMessages()");
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
		System.out.println("HotListDAOImpl.getMessagesByMessageId()" + msgId);

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
		System.out.println("HotListDAOImpl.updateMessage()" + msg.getMessage());
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
		System.out.println("HotListDAOImpl.deleteMessage()" + msg.getMessage());
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
		System.out.println("HotListDAOImpl.saveSchedule()" + schedule.getSubjectId());
		final String procedureCall = "{call proc_InsertSchedule(?,?,?,?)}";
		Connection connection = null;
		try {
			connection = getJdbcTemplate().getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "Insert");
			callableSt.setInt(2, 0);
			callableSt.setInt(3, schedule.getSubjectId());
			callableSt.setString(4, schedule.getDateTime());
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
	public List<Schedule> getAllSchedules() {
		System.out.println("HotListDAOImpl.getAllSchedules()");
		List<Schedule> schedules = new ArrayList<Schedule>();
		final String procedureCall = "{call proc_InsertSchedule(?)}";
		Connection connection = null;
		try {
			connection = getJdbcTemplate().getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "Select");
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
		System.out.println("HotListDAOImpl.deleteSchedule()" + scheduleId);
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
		System.out.println("HotListDAOImpl.getScheduleByScheduleId()" + scheduleId);
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
		System.out.println("HotListDAOImpl.updateSchedule()" + schedule.getScheduleId());
		final String procedureCall = "{call proc_InsertSchedule(?,?,?,?)}";
		Connection connection = null;
		try {
			connection = getJdbcTemplate().getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "Update");
			callableSt.setInt(2, schedule.getScheduleId());
			callableSt.setInt(3, schedule.getSubjectId());
			callableSt.setString(4, schedule.getDateTime());
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
	public List<HotList> getShortlistedDataByRole(int role) {
		System.out.println("HotListDAOImpl.getShortlistedDataByRole()--" + role);
		List<HotList> hotList = new ArrayList<HotList>();
		String procedureCall = "{call [Sp_hotList](?,?,?,?,?,?,?,?)}";
		Connection connection = null;
		try {
			connection = getJdbcTemplate().getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "SelecthotList");
			callableSt.setInt(2, 0);
			callableSt.setInt(3, 0);
			callableSt.setInt(4, 0);
			callableSt.setBoolean(5, false);
			callableSt.setString(6, null);
			callableSt.setBoolean(7, false);
			callableSt.setInt(8, role);
			ResultSet rs = callableSt.executeQuery();

			while (rs.next()) {
				HotList hotlist = new HotList();
				hotlist.setApplyId(rs.getInt("Pk_ApplyId"));
				hotlist.setHotListId(rs.getInt("Pk_HotListId"));
				hotlist.setEmailID(rs.getString("EmailID"));
				hotlist.setName(rs.getString("Name"));
				hotlist.setCvName(rs.getString("CVName"));
				hotlist.setPreference(rs.getInt("Preference"));
				hotlist.setRollNumber(rs.getInt("RollNumber"));
				hotlist.setStatus(rs.getString("Status"));
				hotlist.setShortList(rs.getBoolean("IsHostList"));
				hotlist.setRmStatus(rs.getString("RMStatus"));
				hotlist.setHotListBy(rs.getString("HotListBy")==null?"":rs.getString("HotListBy"));
				
				hotList.add(hotlist);
				System.out.println(
						"get HostList---- " + rs.getInt("Pk_ApplyId") + "rs.getString(Name)--" + rs.getString("Name"));
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
		return hotList;
	}

	@Override
	public void saveHotlist(HotList hotlist) throws SQLException {
		System.out.println("HotListDAOImpl.saveHotlist()");
		List<Integer> shorlistedIds = hotlist.getHotListIdslist();
		List<Integer> notHotlistedIds = hotlist.getNotSelected();
		System.out.println(
				"HotListDAOImpl.saveHotlist() hotlisted ids" + shorlistedIds + "nothotlistedIds--" + notHotlistedIds);
		Connection connection = null;

		CallableStatement callableStmt = null;
		try {
			connection = getJdbcTemplate().getDataSource().getConnection();
			connection.setAutoCommit(false);
			callableStmt = connection.prepareCall("{call Sp_hotList(?,?,?,?,?,?,?)}");
			for (int i = 0; i < shorlistedIds.size(); i++) {
				callableStmt.setString(1, "hotList");
				callableStmt.setInt(2, shorlistedIds.get(i));
				callableStmt.setInt(3, 0);
				callableStmt.setInt(4, 0);
				callableStmt.setBoolean(5, true);
				callableStmt.setString(6, "RM");
				callableStmt.setBoolean(7, true);
				callableStmt.addBatch();
			}
			//callableStmt.executeBatch();

			/*for (int i = 0; i < notHotlistedIds.size(); i++) {
				callableStmt.setString(1, "hotList");
				callableStmt.setInt(2, notHotlistedIds.get(i));
				callableStmt.setInt(3, 0);
				callableStmt.setInt(4, 0);
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
				callableStmt.setString(2, hotlist.getGreetingsHotlist());
				callableStmt.setString(3, "");
				callableStmt.setString(4, "RM");
				callableStmt.setString(5, "Hotlisted");
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
	public List<HotList> getHotReleaseCompany(int role) {
		System.out.println("HotDaoImpl.getShortlistedDataByRole()"+role);
		List<HotList> hotList = new ArrayList<HotList>();
		String procedureCall = "{call Sp_hotList(?,?,?,?,?,?,?,?)}";
		Connection connection = null;
		try {
			connection = getJdbcTemplate().getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "SelecthotListRelease");
			callableSt.setInt(2, 0);
			callableSt.setInt(3, 0);
			callableSt.setInt(4, 0);
			callableSt.setString(5, null);
			callableSt.setBoolean(6, false);
			callableSt.setInt(7, 0);
			callableSt.setInt(8, role);
			ResultSet rs = callableSt.executeQuery();

			while (rs.next()) {
				HotList hot = new HotList();
				hot.setApplyId(rs.getInt("Pk_ApplyId"));
				hot.setHotListId(rs.getInt("Pk_HotListId"));
				hot.setEmailID(rs.getString("EmailID"));
				hot.setName(rs.getString("Name"));
				hot.setPreference(rs.getInt("Preference"));
				hot.setRollNumber(rs.getInt("RollNumber"));
				hot.setStatus(rs.getString("Status"));
				hot.setShortList(rs.getBoolean("IsHostList"));
				hotList.add(hot);
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
		return hotList;
	}

	@Override
	public void hotlistRMRelease(HotList hotlist) {
		//System.out.println("RMDaoImpl.hotlistRMRelease()");
		List<Integer> shorlistedIds = hotlist.getHotListIdslist();
		List<Integer> applyIdListIdslist = hotlist.getApplyIdListIdslist();
		Connection connection = null;
		System.out.println("shorlistedIds -" + shorlistedIds + "applyIdListIdslist-" + applyIdListIdslist);
		CallableStatement callableInsert = null;
		try {
			connection = getJdbcTemplate().getDataSource().getConnection();

			/*
			 * callableStmt = connection.prepareCall(
			 * "{call Sp_hotList(?,?,?,?,?,?,?)}"); for(int
			 * i=0;i<shorlistedIds.size();i++){
			 * callableStmt.setString(1,"SelecthotListRelease");
			 * callableStmt.setInt(2, shorlistedIds.get(i));
			 * callableStmt.setInt(3, 0); callableStmt.setInt(4,0);
			 * callableStmt.setBoolean(5,false ); callableStmt.setString(6,
			 * "RM"); callableStmt.setBoolean(7,true); callableStmt.addBatch();
			 * } callableStmt.executeBatch();
			 */

			callableInsert = connection.prepareCall("{call Sp_hotList(?,?,?,?,?,?,?)}");
			for (int i = 0; i < shorlistedIds.size(); i++) {
				callableInsert.setString(1, "hotListRelease");
				callableInsert.setInt(2, shorlistedIds.get(i));
				callableInsert.setInt(3, 0);
				callableInsert.setInt(4, 0);
				callableInsert.setBoolean(5, true);
				callableInsert.setString(6, "RM");
				callableInsert.setBoolean(7, true);
				callableInsert.addBatch();
			}
			callableInsert.executeBatch();

		} catch (SQLException e) {
			System.out.println("Exception in hotlistRMRelease" + e);
			// connection.rollback();
			e.printStackTrace();
		}

	}

	@Override
	public String hotListUserInformation(int applyId, String greeting, int userId, String sender_email) {
		System.out.println("inside hotListUserInformation method::"+applyId);
		try {
			Connection connection = null;
			connection = getJdbcTemplate().getDataSource().getConnection();
			CallableStatement callableStmt = connection.prepareCall("{call Sp_hotList(?,?,?,?)}");
			callableStmt.setString(1, "selectHotListUserValue");
			callableStmt.setInt(2, 0);
			callableStmt.setInt(3, 0);
			callableStmt.setInt(4, applyId);
			ResultSet rs = callableStmt.executeQuery();
			while (rs.next()) {
				System.out.println("select value::" + rs.getString("EmailID"));
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
		} catch (Exception e) {
			System.out.println("inside selectUsersInformation method::");
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Object processDoneHotlisted(HotList hotlist) {
		// TODO Auto-generated method stub
		List<Integer> applyIdListIdslist = hotlist.getApplyIdListIdslist();
		List<Integer> shorlistedIds = hotlist.getHotListIdslist();
		Connection connection = null;
		System.out.println("shorlistedIds -" + shorlistedIds + "applyIdListIdslist-" + applyIdListIdslist);
		CallableStatement callableInsert = null;
		try {
			connection = getJdbcTemplate().getDataSource().getConnection();

			/*
			 * callableStmt = connection.prepareCall(
			 * "{call Sp_hotList(?,?,?,?,?,?,?)}"); for(int
			 * i=0;i<shorlistedIds.size();i++){
			 * callableStmt.setString(1,"SelecthotListRelease");
			 * callableStmt.setInt(2, shorlistedIds.get(i));
			 * callableStmt.setInt(3, 0); callableStmt.setInt(4,0);
			 * callableStmt.setBoolean(5,false ); callableStmt.setString(6,
			 * "RM"); callableStmt.setBoolean(7,true); callableStmt.addBatch();
			 * } callableStmt.executeBatch();
			 */

			callableInsert = connection.prepareCall("{call Sp_hotList(?,?,?,?,?,?,?,?,?)}");
			for (int i = 0; i < shorlistedIds.size(); i++) {
				callableInsert.setString(1, "ProcessDone");
				System.out.println(shorlistedIds.get(i));
				callableInsert.setInt(2, shorlistedIds.get(i));
				callableInsert.setInt(3, 0);
				callableInsert.setInt(4, 0);
				callableInsert.setBoolean(5, false);
				callableInsert.setString(6, null);
				callableInsert.setBoolean(7, false);
				callableInsert.setInt(8, 0);
				callableInsert.setBoolean(9, true);
				callableInsert.addBatch();
			}
			callableInsert.executeBatch();

		} catch (SQLException e) {
			System.out.println("Exception in hotlistRMRelease" + e);
			// connection.rollback();
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void saveHotlistedByHR(HotList hotlist) throws SQLException {
		System.out.println("HotListDAOImpl.saveHotlistedByHR()");
		List<Integer> shorlistedIds = hotlist.getHotListIdslist();
	//	List<Integer> notHotlistedIds = hotlist.getNotSelected();
		//System.out.println("HotListDAOImpl.saveHotlistedByHR() hotlisted ids" + shorlistedIds + "nothotlistedIds--" + notHotlistedIds);
		System.out.println(	"HotListDAOImpl.saveHotlistedByHR() hotlisted ids" + shorlistedIds);
		
		Connection connection = null;

		CallableStatement callableStmt = null;
		try {
			connection = getJdbcTemplate().getDataSource().getConnection();
			connection.setAutoCommit(false);
			callableStmt = connection.prepareCall("{call Sp_hotList(?,?,?,?,?,?)}");
			for (int i = 0; i < shorlistedIds.size(); i++) {
				callableStmt.setString(1, "hotList");
				callableStmt.setInt(2, shorlistedIds.get(i));
				callableStmt.setInt(3, 0);
				callableStmt.setInt(4, 0);
				callableStmt.setBoolean(5, true);
				callableStmt.setString(6, "HR");
				//callableStmt.setBoolean(7, true);
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
				callableStmt.setString(2, hotlist.getGreetings());
				callableStmt.setString(3, "");
				callableStmt.setString(4, "HR");
				callableStmt.setString(5, "Hotlisted");
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
	public void processDoneHRHotlisted(HotList hotlist) {
				List<Integer> applyIdListIdslist = hotlist.getApplyIdListIdslist();
				List<Integer> shorlistedIds = hotlist.getShortListIdslist();
				Connection connection = null;
				System.out.println("shorlistedIds -" + shorlistedIds + "applyIdListIdslist-" + applyIdListIdslist);
				CallableStatement callableInsert = null;
				try {
					connection = getJdbcTemplate().getDataSource().getConnection();
					callableInsert = connection.prepareCall("{call Sp_hotList(?,?,?,?,?,?,?,?,?)}");
					for (int i = 0; i < shorlistedIds.size(); i++) {
						callableInsert.setString(1, "ProcessDone");
						System.out.println(shorlistedIds.get(i));
						callableInsert.setInt(2, shorlistedIds.get(i));
						callableInsert.setInt(3, 0);
						callableInsert.setInt(4, 0);
						callableInsert.setBoolean(5, false);
						callableInsert.setString(6, null);
						callableInsert.setBoolean(7, false);
						callableInsert.setInt(8, 0);
						callableInsert.setBoolean(9, true);
						callableInsert.addBatch();
					}
					callableInsert.executeBatch();

				} catch (SQLException e) {
					System.out.println("Exception in hotlistRMRelease" + e);					
					e.printStackTrace();
				}
				
	}

	@Override
	public void removeHotlistedByHR(HotList hotlist) {
		System.out.println("HotListDAOImpl.saveHotlistedByHR()");
		List<Integer> shorlistedIds = hotlist.getHotListIdslist();
	//	List<Integer> notHotlistedIds = hotlist.getNotSelected();
		//System.out.println("HotListDAOImpl.saveHotlistedByHR() hotlisted ids" + shorlistedIds + "nothotlistedIds--" + notHotlistedIds);
		System.out.println(	"HotListDAOImpl.saveHotlistedByHR() hotlisted ids" + shorlistedIds);
		
		Connection connection = null;

		CallableStatement callableStmt = null;
		try {
			connection = getJdbcTemplate().getDataSource().getConnection();
			connection.setAutoCommit(false);
			callableStmt = connection.prepareCall("{call Sp_hotList(?,?,?,?,?,?)}");
			for (int i = 0; i < shorlistedIds.size(); i++) {
				callableStmt.setString(1, "hotList");
				callableStmt.setInt(2, shorlistedIds.get(i));
				callableStmt.setInt(3, 0);
				callableStmt.setInt(4, 0);
				callableStmt.setBoolean(5, false);
				callableStmt.setString(6, "RM");
				//callableStmt.setBoolean(7, true);
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
