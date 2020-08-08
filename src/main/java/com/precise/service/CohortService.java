package com.precise.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.precise.model.Cohort;

public interface CohortService {
	public String submitCohort(Cohort cohort) throws SQLException;
	public List<Cohort> getAllCohort();
	public String editCohortForm(Cohort cohort) throws SQLException ;
	public String deleteCohortData(Cohort cohort);
	public Map<Integer, String> getAllClusters();

}
