package com.precise.service;

import java.sql.SQLException;
import java.util.List;

import com.precise.model.Interview;

public interface InterviewService {

	public List<Interview> getAllInterview();
	public void saveFirmManagementForm(Interview inter)throws SQLException;
	public Interview getCompanyName(int cmpId) ;
}
