package com.precise.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.precise.dao.InterviewDao;
import com.precise.model.Interview;

@Service
public class InterviewServiceImpl implements InterviewService {

	@Autowired
	InterviewDao interviewdao;
	
	@Override
	public List<Interview> getAllInterview() {
        return interviewdao.getAllInterview();
	}

	@Override
	public void saveFirmManagementForm(Interview inter) throws SQLException {
		 interviewdao.saveFirmManagementForm(inter);
	}
	public Interview getCompanyName(int cmpId) {
		 return interviewdao.getCompanyName(cmpId);
	}

}
