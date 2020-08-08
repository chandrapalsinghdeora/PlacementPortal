package com.precise.scheduler;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.websocket.server.ServerEndpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.precise.mail.SendMail;
import com.precise.model.SessionBean;
import com.precise.service.AnnouncementService;

@Component
public class SendAnnouncement {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private SendMail sendMail;

	@Autowired
	private AnnouncementService announcementService;

	public String getAnnouncementData(String currentDate) {
		//System.out.println("inside getAnnouncementData method" + currentDate);
		try {
			final String procedureCall = "{call Sp_Announcement(?)}";
			Connection connection = jdbcTemplate.getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "selectSchedulerAnnouncement");
			ResultSet rs = callableSt.executeQuery();
		//	String subject = "[PLACECOM] ";
			String groupToPost  = "";
			String subject = "";
			while (rs.next()) {
				String[] currentD = currentDate.split(":");
				currentDate = currentD[0] + ":" + currentD[1];
				String[] dateTime = rs.getString("Datetime").split(":");
				String dateTimeD = dateTime[0] + ":" + dateTime[1];
				//System.out.println("date time::"+dateTimeD);
				subject = rs.getString("Title");
				if (dateTimeD.equalsIgnoreCase(currentDate)) {
					System.out.println("announcementSchedulerMail time matched");
//This is wrong function	//sendMail.sendMimePart(rs.getString("FileName"), rs.getString("FilePath"), subject,
							//rs.getString("Description"), rs.getString("GroupToPost"));
					
					
					//	System.out.println("before update flag method::");
					String mailId=rs.getString("EmailId")==null?"pgpplacecom@iima.ac.in":rs.getString("EmailId");
					groupToPost  = "";
					if(rs.getString("OtherePost")!=null){
						groupToPost=rs.getString("OtherePost");
					}
					groupToPost  = groupToPost+(rs.getString("GroupToPost")==null?"":", "+rs.getString("GroupToPost"));
					String mailDesc=this.generateMailFormat(rs.getString("Description"),mailId );//"pgpplacecom@iima.ac.in"
					
					sendMail.sendAnnouncement(rs.getString("FileName"), rs.getString("FilePath"), subject , mailDesc,
							rs.getString("GroupToPost"));
					
					if(rs.getString("FilePath")==null || rs.getString("FilePath").equals("")){
						announcementService.insetIntoInbox(groupToPost, rs.getString("Title"),
								mailDesc, 0,null,"pgpplacecom@iima.ac.in");    //mailId
					}else{
						announcementService.insetIntoInbox(groupToPost, rs.getString("Title"),
								mailDesc, 0,rs.getString("FilePath"),"pgpplacecom@iima.ac.in");// mailId
					}
					
					this.updateFlagInAnnouncment(rs.getString("Pk_AnnouncementId"));
				}
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public String updateFlagInAnnouncment(String annoId) {
		System.out.println("updateFlagInAnnouncment:::" + annoId);
		final String procedureCall = "{call Sp_updateAnnouncement(?,?,?)}";
		Connection connection;
		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "updateAnnouncement");
			callableSt.setInt(2, Integer.parseInt(annoId));
			callableSt.setInt(3, 1);
			callableSt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
	
	
	public String setMessage(String currentDate){
		final String procedureCall = "{call proc_InsertSchedule(?)}";
		Connection connection;
		String email = "";
		try {
			List<String> strarr = new ArrayList<>();
			connection = jdbcTemplate.getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "selectScheduleTosendMail");
			ResultSet rs = callableSt.executeQuery();
			while(rs.next()){
				String[] currentD = currentDate.split(":");
				currentDate = currentD[0] + ":" + currentD[1];
				String[] dateTime = rs.getString("Datetime").split(":");
				//String dateTimeD = dateTime[0].substring(6, 8)+"-"+dateTime[0].substring(4, 6)+dateTime[0].substring(0, 4) + ":" + dateTime[1];
				String dateTimeD = dateTime[0]+ ":" + dateTime[1];
			//	System.out.println("currentDate : "+currentDate+" dateTimeD : "+dateTimeD);
				//if ("1".equalsIgnoreCase("1")) {
				if (dateTimeD.equalsIgnoreCase(currentDate)) {
					if(rs.getString("ProcessName").equals("pgp2")){
						strarr.add("2");
						email = announcementService.getGroupToPost(strarr);
					}else if(rs.getString("ProcessName").equals("pgp1")){
						strarr.add("1");
						email = announcementService.getGroupToPost(strarr);
					}
					email=email.replaceAll("''", "").trim();
					String retuenMsg=sendMail.sendMailForMessage(rs.getString("Message"), email, rs.getString("Subject"));
					announcementService.insetIntoInbox(email, rs.getString("Subject"),
							rs.getString("Message"), 0,null,"pgpplacecom@iima.ac.in");
					if(retuenMsg.equalsIgnoreCase("MessageSent")){
						System.out.println("scheduleId::"+rs.getInt("Pk_ScheduleId"));
						announcementService.updateScheduleMailFlag(rs.getInt("Pk_ScheduleId"));
					}
					announcementService.insetIntoInbox(email, rs.getString("Subject"),
							rs.getString("Message"), 0,null,"pgpplacecom@iima.ac.in");
					System.out.println("scheduleId::"+rs.getInt("Pk_ScheduleId"));
					announcementService.updateScheduleMailFlag(rs.getInt("Pk_ScheduleId"));
					
				}
					
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public String generateMailFormat(String msgDescription,String userEmail){
		String mailFormat="<html><body>"+msgDescription+"<br/><br/>This is an auto generated mail, don't respond to this. If you have any issues please reach out to "+userEmail+".<br/><br/>Thanks & Regards<br>IIMA Placement Committee</body></html>";
		return mailFormat;
	}

}
