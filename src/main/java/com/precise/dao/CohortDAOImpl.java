package com.precise.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.GeneratorStrategy;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.precise.model.Cluster;
import com.precise.model.Cohort;

@Repository
public class CohortDAOImpl implements CohortDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	public String submitCohort(Cohort cohort) throws SQLException {
		String procedureCall = "";
		Connection connection = null;
		try {

			connection = jdbcTemplate.getDataSource().getConnection();
			connection.setAutoCommit(false);
			procedureCall = "{call proc_Master(?,?,?,?,?,?,?)}";
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "insertcohort");
			callableSt.setString(2, cohort.getCohortName());
			callableSt.setString(3, cohort.getCohortDescription());
			callableSt.setInt(4, 0);
			callableSt.setInt(5, cohort.getCreatedBy());
			callableSt.setInt(6, 0);
			callableSt.setInt(7, cohort.getClusterId());
			callableSt.execute();

			connection.commit();
		} catch (Exception e) {
			e.printStackTrace();
			connection.rollback();
		} finally {
			if (connection != null)
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return null;

	}
	
	@Override
	public List<Cohort> getAllCohort() {
		
		List<Cohort> list = new ArrayList<Cohort>();
			final String procedureCall = "{call proc_Master(?)}";
			Connection connection=null;
			try {
				connection = jdbcTemplate.getDataSource().getConnection();
				CallableStatement callableSt = connection.prepareCall(procedureCall);
				callableSt.setString(1, "selectcohort");
				ResultSet rs = callableSt.executeQuery();
				int x=1;
				while (rs.next()) {
					Cohort cohort=new Cohort();
					cohort.setCohortId(rs.getInt("Pk_CphortId"));
					cohort.setCohortName(rs.getString("CohortName"));
					cohort.setCohortDescription(rs.getString("CohortDescription"));
					//clusterName
					cohort.setClusterName(rs.getString("CluserName"));
					list.add(cohort);
					x=x+1;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				if (connection != null)
					try {
						connection.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
			}
			return list;
			
	}
	public String editCohortForm(Cohort cohort) throws SQLException {
		String procedureCall = "";
		Connection connection = null;
		try {

			connection = jdbcTemplate.getDataSource().getConnection();
			connection.setAutoCommit(false);
			procedureCall = "{call proc_Master(?,?,?,?,?,?,?)}";
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "updatecohort");
			callableSt.setString(2, cohort.getCohortName());
			callableSt.setString(3, cohort.getCohortDescription());
			callableSt.setInt(4, 0);
			callableSt.setInt(5, cohort.getCreatedBy());
			callableSt.setInt(6, cohort.getCohortId());
			callableSt.setInt(7, cohort.getClusterId());
			callableSt.execute();

			connection.commit();
		} catch (Exception e) {
			e.printStackTrace();
			connection.rollback();
		} finally {
			if (connection != null)
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return null;

	}
	
	@Override
	public String deleteCohortData(Cohort cohort){
		final String procedureCall = "{call proc_Master(?,?,?,?,?,?)}";
		Connection connection = null;
		String returnValue="failed";
		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "deletecohort");
			callableSt.setString(2, null);
			callableSt.setString(3, null);
			callableSt.setInt(4, 0);
			callableSt.setInt(5, cohort.getCreatedBy());
			callableSt.setInt(6, cohort.getCohortId());
			callableSt.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (connection != null)
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return returnValue;
	}

	@Override
	public Map<Integer, String> getAllClusters() {
		Map<Integer, String> clusterlist = new HashMap<Integer, String>();
		final String procedureCall = "{call proc_Master(?)}";
		Connection connection=null;
		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "selectcluster");
			ResultSet rs = callableSt.executeQuery();
			
			while (rs.next()) {
				clusterlist.put(rs.getInt("Pk_ClusterId"), rs.getString("CluserName"));	
				System.out.println("cluster id-"+rs.getInt("Pk_ClusterId")+"cluster name-"+ rs.getString("CluserName"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (connection != null)
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		System.out.println("map cluster-"+clusterlist);
		return clusterlist;
	}
	
	

}
