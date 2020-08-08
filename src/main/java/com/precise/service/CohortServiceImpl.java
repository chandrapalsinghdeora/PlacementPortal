package com.precise.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.precise.dao.CohortDAO;
import com.precise.model.Cluster;
import com.precise.model.Cohort;

@Service("cohortservice")
@Transactional
public class CohortServiceImpl implements CohortService {
	@Autowired
	CohortDAO cohortDAO;
	
	public String submitCohort(Cohort cohort) throws SQLException{
		return cohortDAO.submitCohort(cohort) ;
	}
	
	@Override
	public List<Cohort> getAllCohort() {
		return cohortDAO.getAllCohort();
	}
	
	@Override
	public String editCohortForm(Cohort cohort) throws SQLException {
		return cohortDAO.editCohortForm(cohort) ;
	}
	@Override
	public String deleteCohortData(Cohort cohort){
		return cohortDAO.deleteCohortData(cohort);
	}

	@Override
	public Map<Integer, String> getAllClusters() {
		return cohortDAO.getAllClusters();
	}
	
}
