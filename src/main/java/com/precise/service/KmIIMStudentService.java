package com.precise.service;

import java.sql.SQLException;
import java.util.List;

import org.json.JSONArray;

import com.precise.model.Announcement;
import com.precise.model.KmIIMStudent;

public interface KmIIMStudentService {

	public List<KmIIMStudent> getAllIIMStudent();
	public String saveStudent(KmIIMStudent cluster) throws SQLException;
	public String editStudent(KmIIMStudent cluster) throws SQLException ;
	public String deleteStudentData(KmIIMStudent cluster);
	public String insertIntoStudent(Object[] studentData) throws SQLException;
	public JSONArray getUsernameList();
	public void saveKMAnnouncement(Announcement announce);
	public List<Announcement> getALLAnnouncementByKm();
	public void delteKMAnnouncement(int announmtId);
		
		
	
}
