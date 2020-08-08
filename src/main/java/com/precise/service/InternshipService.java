package com.precise.service;

import java.sql.SQLException;
import java.util.List;

import com.precise.model.Internship;


public interface InternshipService {
	public List<Internship> getAllInternship();
	public void saveFirmManagementForm(Internship inter)throws SQLException;
	public Internship getCompanyName(int cmpId) ;
}
