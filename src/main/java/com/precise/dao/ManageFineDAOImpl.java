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

import com.precise.model.Fine;

@Repository("manageFineRepo")
public class ManageFineDAOImpl implements ManageFineDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	
	
	@Override
	public List<Fine> getAllFineDetail() {
		
		List<Fine> listFine=new ArrayList<Fine>();		
		final String procedureCall = "{call proc_Fine(?)}";
		Connection connection = null;
		try {
			
			connection = jdbcTemplate.getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "select");
			
			ResultSet rs = callableSt.executeQuery();
			int x=1;
			Fine fine = null;
			while (rs.next()) {
				fine = new Fine();
				fine.setFineId(rs.getInt("PK_FineId"));
				fine.setFineReason(rs.getString("FineReason"));
				fine.setFineStatus(rs.getString("FineStatus"));
				//System.out.println(rs.getString("FineReason"));
				fine.setCreatedDate(rs.getTimestamp("CreatedDate"));
				fine.setRowCount(x);
				fine.setAmount(rs.getDouble("Amount"));
				fine.setEmail(rs.getString("Individual"));
				fine.setUsername(rs.getString("userName"));
				
				listFine.add(fine);	
				x=x+1;
			}
		
		}catch(
		SQLException e)
		{
			e.printStackTrace();
		}finally
		{
			if (connection != null)
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return listFine;
		}
	
	
	@Override
	public void updateFineStatus(int fineValue,int fineId) {
		
		final String procedureCall = "{call [proc_Fine](?,?,?,?)}";
		Connection connection = null;
		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "Update");
			//System.out.println("fineValue ::"+fineValue+ "fineId ::: " +fineId);
			callableSt.setInt(2, fineId);
			callableSt.setString(3, null);
			callableSt.setInt(4, fineValue);
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
		
	}
}
