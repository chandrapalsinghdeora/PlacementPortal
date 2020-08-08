package com.precise.service;

import java.util.List;

import com.precise.model.Announcement;


public interface AnnouncementService {
	public String getGroupToPost(List<String> pgpgroupIds);
	public void addAnnouncement(Announcement announcement);
	public String insetIntoInbox(String groupId,String title,String description,int userId,String filePath, String sender_email);
	public List<Announcement> getAllAnnouncementDetails();
	public String updateScheduleMailFlag(int scheduleId);
}
