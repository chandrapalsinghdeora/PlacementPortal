package com.precise.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.precise.dao.InternshipDao;
import com.precise.model.Internship;

@Service
public class InternshipServiceImpl implements InternshipService {

	@Autowired
	InternshipDao internshipdao;
	
	@Override
	public List<Internship> getAllInternship() {
		return internshipdao.getAllInternship();
	}

	@Override
	public void saveFirmManagementForm(Internship inter) throws SQLException {
		internshipdao.saveFirmManagementForm(inter);
	}

	public Internship getCompanyName(int cmpId) {
		return internshipdao.getCompanyName(cmpId);
	}
}
