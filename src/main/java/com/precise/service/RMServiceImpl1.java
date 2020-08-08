package com.precise.service;

import java.sql.SQLException;
import java.util.List;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.precise.dao.RMDao1;
import com.precise.model.CloseStatus;
import com.precise.model.Message;
import com.precise.model.Schedule;
import com.precise.model.ShortList;

@Service
public class RMServiceImpl1 implements  RMService1{
    @Autowired
    RMDao1 rmDao;
    
    
	@Override
	public void saveMessage(Message msg) {
		System.out.println("RMServiceImpl.saveMessage()");
		rmDao.saveMessage(msg);
	}
	@Override
	public List<Message> getAllMessages() {
		
		return rmDao.getAllMessages();
	}
	@Override
	public Message getMessagesByMessageId(int msgId) {
		
		return rmDao.getMessagesByMessageId(msgId);
	}
	@Override
	public void updateMessage(Message msg) {
		rmDao.updateMessage(msg);
	}
    
	public void deleteMessage(Message msg){
		rmDao.deleteMessage(msg);
	}
	@Override
	public void saveSchedule(Schedule schedule) {
		rmDao.saveSchedule(schedule);		
	}
	@Override
	public List<Schedule> getAllSchedules(int cmpId){		
		return rmDao.getAllSchedules(cmpId);
	}
	@Override
	public void deleteSchedule(int scheduleId) {
		rmDao.deleteSchedule(scheduleId);
		
	}
	@Override
	public Schedule getScheduleByScheduleId(int scheduleId) {
		return rmDao.getScheduleByScheduleId(scheduleId);
	}
	@Override
	public void updateSchedule(Schedule schedule) {
		rmDao.updateSchedule(schedule);
		
	}
	@Override
	public List<ShortList> getShortlistedDataByRole(int role) {
	
		return rmDao.getShortlistedDataByRole(role);
	}
	@Override
	public void shortlistRelease(ShortList shortlist) throws SQLException {
		
		 rmDao.shortlistRelease(shortlist);
	}
	@Override
	public List<String> getRMDownloadCVList(List<CloseStatus> downloadCvlist) {
		// TODO Auto-generated method stub
		return rmDao.getRMDownloadCVList(downloadCvlist);
	}
	@Override
	public List<String> getRMDownloadCoverList(List<CloseStatus> downloadCvlist) {
		// TODO Auto-generated method stub
		return rmDao.getRMDownloadCoverList(downloadCvlist);
	}
	@Override
	public void shortlistRMRelease(ShortList shortlist,int userid) throws SQLException {
		 rmDao.shortlistRMRelease(shortlist,userid);
		
	}
	@Override
	public List<ShortList> getshortlistReleaseCompany(int role) {
		// TODO Auto-generated method stub
		return rmDao.getshortlistReleaseCompany(role);
	}
	
	public String insertDataIntoInbox(){
		return rmDao.insertIntoInbox();
	}
	
	public String selectUsersInformation(int applyId,String greeting,int userId, String sender_email){
		return rmDao.selectUsersInformation(applyId,greeting,userId, sender_email);
	}
	
	@Override
	public JSONArray getGreetings(int cmpRoleId){
		return rmDao.getGreetings(cmpRoleId);
	}
	@Override
	public void saveHRShortlisted(ShortList shortlist) throws SQLException {
		rmDao.saveHRShortlisted(shortlist);
		
	}
	@Override
	public List<ShortList> getShortlistedDataByRoleForHR(int cmpRoleId) {
		
		return rmDao.getShortlistedDataByRoleForHR(cmpRoleId);
	}
	@Override
	public List<ShortList> getAllRM(int compRoleId) {
		
		return rmDao.getAllRM(compRoleId);
	}
	
	@Override
	public CloseStatus getAllHrList(int compRoleId) {
		return rmDao.getAllHrList(compRoleId);
	}
	
	
	@Override
	public List<ShortList> getHRShortlistedDataByRole(int cmpRoleId) {
		
		return rmDao.getHRShortlistedDataByRole(cmpRoleId);
	}
	@Override
	public void releaseHRShortlisted(ShortList shortlist) {
		rmDao.releaseHRShortlisted(shortlist);
		
	}
	@Override
	public String selectUsersInformationByRM(int applyId,String studentGreetingSubject, String greetings, int userID, String sender_email) {
	  return rmDao.selectUsersInformationByRM(applyId,studentGreetingSubject,greetings,userID, sender_email);
		
	}
	@Override
	public CloseStatus getStudentGreetings(int cmpRoleId){
		 return rmDao.getStudentGreetings(cmpRoleId);
	}
	@Override
	public void shortlistOneAfterGD(ShortList shortlist) {
		rmDao.shortlistOneAfterGD(shortlist) ;
		
	}
	@Override
	public void shortlistRemove(ShortList shortlist,int userid) throws SQLException {
		rmDao.shortlistRemove(shortlist,userid);
	}
	@Override
	public boolean getReleaseFlagStatus(int cmpRoleId) {
		return rmDao.getReleaseFlagStatus(cmpRoleId);
	}
	@Override
	public void removeHRShortlisted(ShortList shortlist) {
	 rmDao.removeHRShortlisted(shortlist);
	}
	
	
}
