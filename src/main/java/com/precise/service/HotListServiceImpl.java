package com.precise.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.precise.dao.HotListDAO;
import com.precise.model.HotList;
import com.precise.model.Message;
import com.precise.model.Schedule;
@Service
public class HotListServiceImpl implements HotListService {
	@Autowired
    HotListDAO hotListDAO;
	
	
	@Override
	public void saveMessage(Message msg) {
		//System.out.println("HotListServiceImpl.saveMessage()");
		hotListDAO.saveMessage(msg);
	}
	@Override
	public List<Message> getAllMessages() {
		
		return hotListDAO.getAllMessages();
	}
	@Override
	public Message getMessagesByMessageId(int msgId) {
		
		return hotListDAO.getMessagesByMessageId(msgId);
	}
	@Override
	public void updateMessage(Message msg) {
		hotListDAO.updateMessage(msg);
	}
    
	public void deleteMessage(Message msg){
		hotListDAO.deleteMessage(msg);
	}
	@Override
	public void saveSchedule(Schedule schedule) {
		hotListDAO.saveSchedule(schedule);		
	}
	@Override
	public List<Schedule> getAllSchedules(){		
		return hotListDAO.getAllSchedules();
	}
	@Override
	public void deleteSchedule(int scheduleId) {
		hotListDAO.deleteSchedule(scheduleId);
		
	}
	@Override
	public Schedule getScheduleByScheduleId(int scheduleId) {
		return hotListDAO.getScheduleByScheduleId(scheduleId);
	}
	@Override
	public void updateSchedule(Schedule schedule) {
		hotListDAO.updateSchedule(schedule);
		
	}
	@Override
	public List<HotList> getShortlistedDataByRole(int role) {
	
		return hotListDAO.getShortlistedDataByRole(role);
	}
	@Override
	public void saveHotlist(HotList hotlist) throws SQLException {
		
		hotListDAO.saveHotlist(hotlist);
	}
	@Override
	public List<HotList> getHotReleaseCompany(int role) {
		
		return hotListDAO.getHotReleaseCompany(role);
	}
	@Override
	public void hotlistRMRelease(HotList hotlist) {
		hotListDAO.hotlistRMRelease(hotlist);
		
	}
	
	
	@Override
	public String selectUsersInformationForHotList(int applyId,String greeting,int userId, String sender_email){
		return hotListDAO.hotListUserInformation(applyId,greeting,userId,sender_email);
	}
	@Override
	public void processDoneHotlisted(HotList hotlist) {
		
		 hotListDAO.processDoneHotlisted(hotlist);
	}
	@Override
	public void saveHotlistedByHR(HotList hotlist) throws SQLException {

		hotListDAO.saveHotlistedByHR(hotlist);
	}
	@Override
	public void processDoneHRHotlisted(HotList hotlist) {
		hotListDAO.processDoneHRHotlisted(hotlist);
	}
	@Override
	public void removeHotlistedByHR(HotList hotlist) {
		hotListDAO.removeHotlistedByHR(hotlist);
	}
	
	

}
