package com.precise.dao;

import java.sql.SQLException;
import java.util.List;

import com.precise.model.PotentialStudent;

public interface PotentialStudentDAO {

	
	public String submitPotentialStudent(PotentialStudent potentialStudent) throws SQLException;
	public List<PotentialStudent> getAllPotentialStudentDetail();
	public String editPotentialStudentForm(PotentialStudent potentialStudent) throws SQLException ;
	public String deletePotentialStudentData(PotentialStudent potentialStudent);
	public String insertStudentData(String[] studentData) throws SQLException, Exception;
}
