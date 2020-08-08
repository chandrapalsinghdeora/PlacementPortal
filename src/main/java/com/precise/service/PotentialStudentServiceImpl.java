package com.precise.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.precise.dao.PotentialStudentDAO;
import com.precise.model.PotentialStudent;


@Service("potentialStudentService")
@Transactional
public class PotentialStudentServiceImpl implements PotentialStudentService{

	@Autowired
	private PotentialStudentDAO potentialStudentDao;
	
	
	public String submitPotentialStudent(PotentialStudent potentialStudent) throws SQLException{
		return potentialStudentDao.submitPotentialStudent(potentialStudent) ;
		
	}
	public List<PotentialStudent> getAllPotentialStudentDetail(){
		return potentialStudentDao.getAllPotentialStudentDetail();
		
	}
	public String editPotentialStudentForm(PotentialStudent potentialStudent) throws SQLException {
		return potentialStudentDao.editPotentialStudentForm(potentialStudent) ;
	}
	
	public String deletePotentialStudentData(PotentialStudent potentialStudent){
		return potentialStudentDao.deletePotentialStudentData(potentialStudent);
	}
	
	public String insertIIMStudentInsert(String[] studentData) throws SQLException, Exception{
		return potentialStudentDao.insertStudentData(studentData);
	}
}
