package com.precise.dao;

import java.util.List;

import com.precise.model.Announcement;

public interface AnnouncementDAO {

	public String getGroupToPost(List<String> pgpgroupIds);
	public void addAnnouncement(Announcement announcement);
	public String insertIntoInbox(String groupId,String title,String description,int userId,String filePath, String sender_email);
	public List<Announcement> getAllAnnouncementDetails();
	public String updateMailFlag(int scheduleId);
}
