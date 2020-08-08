package com.precise.scheduler;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class announcementScheduler {
	
	@Autowired
	SendAnnouncement sendAnnouncement;
	
	@Scheduled(cron = "0 0-59 * * * *")
	public void announcementSchedulerMail(){
		//System.out.println("method call afetr 1 min:::::");
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		String currentDate=dateFormat.format(date);
		//System.out.println("currentDate:::::"+currentDate);
		sendAnnouncement.getAnnouncementData(currentDate);
	}
	
	@Scheduled(cron = "0 0-59 * * * *")
	public void sendMessage(){
	//	System.out.println("inside send message method");
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		Date date = new Date();
		String currentDate=dateFormat.format(date);
		sendAnnouncement.setMessage(currentDate);
	}

}
