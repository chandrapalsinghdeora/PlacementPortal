package com.precise.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.precise.model.Announcement;
import com.precise.model.Fine;

@Repository("announceRepo")
public class AnnouncementDAOImpl implements AnnouncementDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	@Override
	public void addAnnouncement(Announcement announcement) {

		final String procedureCall = "{call Sp_Announcement(?,?,?,?,?,?,?,?,?,?,?,?,?)}";
		Connection connection = null;
		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "Insert");
			callableSt.setString(2, announcement.getTitle());
			// System.out.println("date in dao::"+announcement.getDateTime());
			DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
			Date date = formatter.parse(announcement.getDateTime());
			// System.out.println("2nd time date::"+date);
			java.sql.Timestamp sqlTimeStamp = new java.sql.Timestamp(date.getTime());
			// System.out.println("3rd time date::" + sqlTimeStamp);
			callableSt.setTimestamp(3, sqlTimeStamp);
			// System.out.println("date :: " + sqlTimeStamp + " :: " +
			// announcement.getGroupToPost() + " :: "
			// + announcement.getOtherPostText());
			
			callableSt.setString(4, announcement.getGroupToPost());
			callableSt.setString(5, announcement.getOtherPostText());
			callableSt.setString(6, announcement.getDescription());
			callableSt.setBoolean(7, announcement.getUrgentFlag());
			// System.out.println("urgent :: " + announcement.getUrgentFlag());
			/*callableSt.setBytes(8, announcement.getFileupload().getBytes());*/
			callableSt.setBytes(8, null);
			callableSt.setString(9, announcement.getFileupload().getOriginalFilename());
			callableSt.setString(10, announcement.getFilePath());
			callableSt.setBoolean(11, false);
			callableSt.setDate(12, null);
			callableSt.setInt(13, announcement.getCreatedBy());
			callableSt.execute();

		} catch (SQLException e) {

			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();

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
	public List<Announcement> getAllAnnouncementDetails() {

		List<Announcement> listAnnouncement = new ArrayList<Announcement>();
		final String procedureCall = "{call Sp_Announcement(?,?)}";
		Connection connection = null;
		try {

			connection = jdbcTemplate.getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "Select");
			callableSt.setString(2, "0");

			ResultSet rs = callableSt.executeQuery();
			int x = 1;
			Announcement announcement = null;

			while (rs.next()) {
				announcement = new Announcement();

				announcement.setTitle(rs.getString("Title"));
				announcement.setDateTime(rs.getString("Datetime"));
				announcement.setDescription(rs.getString("Description"));
				announcement.setGroupToPost(rs.getString("GroupToPost")==null?"":rs.getString("GroupToPost"));
				if(!announcement.getGroupToPost().equals(""))
					announcement.setOtherPostText((rs.getString("OtherePost")==null?""+rs.getString("GroupToPost"):rs.getString("OtherePost").replaceAll(",",", ")+","+announcement.getGroupToPost()));
				else
					announcement.setOtherPostText((rs.getString("OtherePost")==null?"":rs.getString("OtherePost")).replaceAll(",",", "));
				announcement.setFileName(rs.getString("FileName"));
				announcement.setFilePath(rs.getString("FilePath"));
				announcement.setRowCount(x);

				listAnnouncement.add(announcement);
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
		return listAnnouncement;
	}

	public String insertIntoInbox(String gropId, String title, String description, int userId,String filePath, String sender_email) {
		String returnResult = "";
		try {
			Connection connection = jdbcTemplate.getDataSource().getConnection();
			System.out.println("gropId:::" + gropId);
			// String emailAddresses = "";
			if(gropId!=null){
				String[] id = gropId.split(",");
				System.out.println("id length::" + id.length);
				for (int i = 0; i < id.length; i++) {
				//	System.out.println("eamil id:::"+id[i]);
					// System.out.println("emailAddresses::::" + emailAddresses);
					CallableStatement callableStmtAnno = connection.prepareCall("{call Sp_Announcement(?,?,?,?)}");
					callableStmtAnno.setString(1, "UserIdByMail");
					callableStmtAnno.setString(2, "");
					callableStmtAnno.setString(3, null);
					callableStmtAnno.setString(4, id[i].trim());
					ResultSet rs = callableStmtAnno.executeQuery();
					while (rs.next()) {
						CallableStatement callableStmtForInbox = connection
								.prepareCall("{call proc_inbox(?,?,?,?,?,?,?,?,?,?)}");
						callableStmtForInbox.setString(1, "insert");
						callableStmtForInbox.setInt(2, 0);
						callableStmtForInbox.setString(3, title);
						callableStmtForInbox.setString(4, description);
						callableStmtForInbox.setInt(5, 1);
						callableStmtForInbox.setInt(6, rs.getInt("Pk_IIMStdId"));
						callableStmtForInbox.setInt(7, userId);
						callableStmtForInbox.setInt(8, 4);
						if(filePath!=null){
						    callableStmtForInbox.setString(9, filePath);
						}else{
							callableStmtForInbox.setString(9, null);
						}
						callableStmtForInbox.setString(10, sender_email);
						callableStmtForInbox.execute();
					}
				}
			}
		} catch (Exception e) {
			System.out.println("Exception in insertIntoInbox method::" + e.getMessage());
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public String getGroupToPost(List<String> pgpgroupIds) {
		StringBuffer emailAddresses = new StringBuffer();
		try {
			Connection connection = jdbcTemplate.getDataSource().getConnection();
			ResultSet rs = null;
			System.out.println("pgpgroupIds:::" + pgpgroupIds);
			CallableStatement callableStmtAnno = connection.prepareCall("{call Sp_Announcement(?)}");
			System.out.println("ids:::" + pgpgroupIds);
			for (String ids : pgpgroupIds) {
				if (ids.equals("1"))
					callableStmtAnno.setString(1, "PG1");
				else if (ids.equals("2"))
					callableStmtAnno.setString(1, "PG2");
				if (!ids.equals("3")) {
					rs = callableStmtAnno.executeQuery();
					if (rs.next()) {
						System.out.println("loop email" + emailAddresses + ":::" + rs.getString("Mail"));
						if (emailAddresses.length() > 0) {
							emailAddresses.append(", ");
							emailAddresses.append(rs.getString("Mail"));
						} else
							emailAddresses.append(rs.getString("Mail"));
					}
				}
			}
		} catch (Exception e) {
			System.out.println("Exception in getGroupToPost method::" + e.getMessage());
			e.printStackTrace();
		}
		System.out.println("email address::" + emailAddresses);
		if (emailAddresses.length() > 0)
			return emailAddresses.toString();
		else
			return null;

	}
	
	@Override
	public String updateMailFlag(int scheduleId){
		String updatedValue="";
		try{
		Connection connection = jdbcTemplate.getDataSource().getConnection();
		CallableStatement callableStmtForInbox = connection
				.prepareCall("{call proc_InsertSchedule(?,?)}");
		callableStmtForInbox.setString(1, "updateMailFlag");
		callableStmtForInbox.setInt(2, scheduleId);
		callableStmtForInbox.execute();
		}catch(Exception e){
			System.out.println("Exception in updateMailFlag method::"+e.getMessage());
			e.printStackTrace();		
			}
		return null;
	}

}
