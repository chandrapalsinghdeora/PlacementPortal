package com.precise.dao;

import java.sql.SQLException;
import java.util.List;

import com.precise.model.Internship;

public interface InternshipDao {

	public List<Internship> getAllInternship();
	public void saveFirmManagementForm(Internship inter)throws SQLException;
	public Internship getCompanyName(int cmpId) ;
}
