package com.precise.service;

import java.sql.SQLException;
import java.util.List;

import com.precise.model.HotList;
import com.precise.model.Message;
import com.precise.model.Schedule;

public interface HotListService {
	
	void saveMessage(Message msg);

	List<Message> getAllMessages();

	Message getMessagesByMessageId(int msgId);

	void updateMessage(Message msg);
	public void deleteMessage(Message msg);

	void saveSchedule(Schedule schedule);

	List<Schedule> getAllSchedules();

	void deleteSchedule(int scheduleId);

	Schedule getScheduleByScheduleId(int scheduleId);

	void updateSchedule(Schedule schedule);

	List<HotList> getShortlistedDataByRole(int role);

	void saveHotlist(HotList hotlist) throws SQLException;

	List<HotList> getHotReleaseCompany(int role);

	void hotlistRMRelease(HotList hotlist);
	
	public String selectUsersInformationForHotList(int applyId,String greeting,int userId, String sender_email);

	void processDoneHotlisted(HotList hotlist);

	void saveHotlistedByHR(HotList hotlist) throws SQLException;

	void processDoneHRHotlisted(HotList hotlist);

	void removeHotlistedByHR(HotList hotlist);

}
