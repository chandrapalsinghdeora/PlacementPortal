package com.precise.service;

import java.sql.SQLException;
import java.util.List;


import com.precise.model.PotentialStudent;


public interface PotentialStudentService {

	public String submitPotentialStudent(PotentialStudent potentialStudent) throws SQLException;
	public List<PotentialStudent> getAllPotentialStudentDetail();
	public String editPotentialStudentForm(PotentialStudent potentialStudent) throws SQLException ;
	public String deletePotentialStudentData(PotentialStudent potentialStudent);
	public String insertIIMStudentInsert(String[] studentData) throws SQLException, Exception;
	
}
