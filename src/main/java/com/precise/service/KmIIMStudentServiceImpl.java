package com.precise.service;

import java.sql.SQLException;
import java.util.List;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.precise.dao.KmIIMStudentDao;
import com.precise.model.Announcement;
import com.precise.model.KmIIMStudent;

@Service
public class KmIIMStudentServiceImpl implements KmIIMStudentService {

	@Autowired
	KmIIMStudentDao kmiimstudentdao;
	
	@Override
	public List<KmIIMStudent> getAllIIMStudent() {
		// TODO Auto-generated method stub
		return kmiimstudentdao.getAllIIMStudent();
	}

	@Override
	public String saveStudent(KmIIMStudent cluster) throws SQLException {
		return kmiimstudentdao.saveStudent(cluster) ;
	}

	@Override
	public String editStudent(KmIIMStudent cluster) throws SQLException {
		return kmiimstudentdao.editStudent(cluster) ;
	}

	@Override
	public String deleteStudentData(KmIIMStudent cluster) {
		return kmiimstudentdao.deleteStudentData(cluster);
	}

	@Override
	public String insertIntoStudent(Object[] studentData) throws SQLException{
		return kmiimstudentdao.insertIntoStudets(studentData);
	}
	
	@Override
	public JSONArray getUsernameList(){
		return kmiimstudentdao.getUsernameList();
		
	}

	@Override
	public void saveKMAnnouncement(Announcement announce) {
		kmiimstudentdao.saveKMAnnouncement(announce);
	}

	@Override
	public List<Announcement> getALLAnnouncementByKm() {
		
		return kmiimstudentdao.getALLAnnouncementByKm();
	}
	
	@Override
	public void delteKMAnnouncement(int announmtId) {
		kmiimstudentdao.delteKMAnnouncement(announmtId);
		
	}
	
}
