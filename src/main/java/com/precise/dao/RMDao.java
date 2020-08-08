package com.precise.dao;

import java.sql.SQLException;
import java.util.List;

import org.json.JSONArray;

import com.precise.model.CloseStatus;
import com.precise.model.HotList;
import com.precise.model.Message;
import com.precise.model.Schedule;
import com.precise.model.ShortList;

public interface RMDao {

	void saveMessage(Message msg);

	List<Message> getAllMessages();

	Message getMessagesByMessageId(int msgId);

	void updateMessage(Message msg);

	public void deleteMessage(Message msg);

	void saveSchedule(Schedule schedule);

	List<Schedule> getAllSchedules(int cmpId);

	void deleteSchedule(int scheduleId);

	Schedule getScheduleByScheduleId(int scheduleId);

	void updateSchedule(Schedule schedule);

	List<ShortList> getShortlistedDataByRole(int role);
	
	List<HotList> getHRHotListedDataByRole(int cmpRoleId);

	void shortlistRelease(ShortList shortlist,int id) throws SQLException;
	
	void shortlistRemove(ShortList shortlist,int id) throws SQLException;

	List<String> getRMDownloadCVList(List<CloseStatus> downloadCvlist);

	List<String> getRMDownloadCoverList(List<CloseStatus> downloadCvlist);

	void shortlistRMRelease(ShortList shortlist,int id) throws SQLException;

	List<ShortList> getshortlistReleaseCompany(int role);

	public String insertIntoInbox();

	public String selectUsersInformation(int applyId, String greeting, int userId, String sender_email);

	JSONArray getGreetings(int cmpRoleId);

	void saveHRShortlisted(ShortList shortlist) throws SQLException;

	List<ShortList> getShortlistedDataByRoleForHR(int cmpRoleId);

	List<ShortList> getAllRM(int compRoleId);

	CloseStatus getAllHrList(int compRoleId);

	List<ShortList> getHRShortlistedDataByRole(int cmpRoleId);

	void releaseHRShortlisted(ShortList shortlist,int userid);

	String selectUsersInformationByRM(int applyId, String studentGreetingSubject, String greetings, int userID,
			String sender_email);

	public CloseStatus getStudentGreetings(int cmpRoleId);
	public void hotlistRMRelease(HotList hotlist) ;

	boolean getReleaseFlagStatus(int role);

	List<HotList> getFinalOffer(int cmpRoleId);

	void shortlistHR1Remove(ShortList shortlist);

	void shortlistHRRemoveHL(ShortList shortlist);

	void shortlistHR2Remove(ShortList shortlist);

	void removeHRShortlisted(ShortList shortlist);
}
