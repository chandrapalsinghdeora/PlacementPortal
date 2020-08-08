package com.precise.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.precise.dao.AnnouncementDAO;

import com.precise.model.Announcement;


@Service("announcmentService")
@Transactional
public class AnnouncementServiceImpl implements AnnouncementService{

	
	@Autowired
	private AnnouncementDAO announcmentdao;
	
	public void addAnnouncement(Announcement announcement){
		announcmentdao.addAnnouncement(announcement);
	}
	
	public String insetIntoInbox(String groupId,String title,String description,int userId,String filePath, String sender_email){
		return announcmentdao.insertIntoInbox(groupId,title,description,userId,filePath, sender_email);
	}
	
	public String getGroupToPost(List<String> pgpgroupIds){
		return announcmentdao.getGroupToPost(pgpgroupIds);
	}
	public List<Announcement> getAllAnnouncementDetails(){
		
		return announcmentdao.getAllAnnouncementDetails();
	}
	
	public String updateScheduleMailFlag(int scheduleId){
		return announcmentdao.updateMailFlag(scheduleId);
	}
}
