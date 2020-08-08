package com.precise.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.precise.model.Cluster;
import com.precise.model.CompanyMaster;

@Repository
public class CompanyMasterDAOImpl implements CompanyMasterDAO {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	
	public String submitCompany(CompanyMaster companyMaster) throws SQLException {
		String procedureCall = "";
		Connection connection = null;
		try {

			connection = jdbcTemplate.getDataSource().getConnection();
			connection.setAutoCommit(false);
			procedureCall = "{call proc_Master(?,?,?,?,?,?)}";
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "insertcompany");
			callableSt.setString(2, companyMaster.getCompanyName());
			callableSt.setString(3, companyMaster.getCompanyDescription());
			callableSt.setInt(4, 0);
			callableSt.setInt(5, companyMaster.getCreatedBy());
			callableSt.setInt(6, 0);
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
	public List<CompanyMaster> getAllCompany() {
		
		List<CompanyMaster> list = new ArrayList<CompanyMaster>();
			final String procedureCall = "{call proc_Master(?)}";
			Connection connection=null;
			try {
				connection = jdbcTemplate.getDataSource().getConnection();
				CallableStatement callableSt = connection.prepareCall(procedureCall);
				callableSt.setString(1, "selectcompany");
				ResultSet rs = callableSt.executeQuery();
				int x=1;
				while (rs.next()) {
					CompanyMaster companyMaster=new CompanyMaster();
					companyMaster.setCompanyId(rs.getInt("Pk_CompanyId"));
					companyMaster.setCompanyName(rs.getString("CompanyName"));
					companyMaster.setCompanyDescription(rs.getString("CompanyDescription"));
					companyMaster.setCluster(rs.getString("CluserName"));
					companyMaster.setCohort(rs.getString("CohortName"));
					list.add(companyMaster);
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

	
	
	public String editCompanyMasterForm(CompanyMaster companyMaster) throws SQLException {
		String procedureCall = "";
		Connection connection = null;
		try {

			connection = jdbcTemplate.getDataSource().getConnection();
			connection.setAutoCommit(false);
			procedureCall = "{call proc_Master(?,?,?,?,?,?)}";
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "updatecompany");
			callableSt.setString(2, companyMaster.getCompanyName());
			callableSt.setString(3, companyMaster.getCompanyDescription());
			callableSt.setInt(4, 0);
			callableSt.setInt(5, companyMaster.getCreatedBy());
			callableSt.setInt(6, companyMaster.getCompanyId());
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
	public String deleteCompanyMasterData(CompanyMaster companyMaster){
		final String procedureCall = "{call proc_Master(?,?,?,?,?,?)}";
		Connection connection = null;
		String returnValue="failed";
		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "deletecompany");
			callableSt.setString(2, null);
			callableSt.setString(3, null);
			callableSt.setInt(4, 0);
			callableSt.setInt(5, companyMaster.getCreatedBy());
			callableSt.setInt(6, companyMaster.getCompanyId());
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
	
	

}
