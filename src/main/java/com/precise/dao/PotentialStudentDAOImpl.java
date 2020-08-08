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

import com.precise.model.PotentialStudent;

@Repository("potentialStudentRepo")
public class PotentialStudentDAOImpl implements PotentialStudentDAO{

	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	
	public String submitPotentialStudent(PotentialStudent potentialStudent) throws SQLException {
		String procedureCall = "";
		Connection connection = null;
		try {

			connection = jdbcTemplate.getDataSource().getConnection();
			connection.setAutoCommit(false);
			procedureCall = "{call proc_PotentialStudent(?,?,?,?,?)}";
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "insert");
			callableSt.setInt(2,potentialStudent.getPotentialStudentId());
			callableSt.setString(3, potentialStudent.getName());
			callableSt.setString(4,potentialStudent.getEmailId());
			callableSt.setInt(5,potentialStudent.getCreatedBy());
			
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
	public List<PotentialStudent> getAllPotentialStudentDetail() {
		
		List<PotentialStudent> list = new ArrayList<PotentialStudent>();
			final String procedureCall = "{call proc_PotentialStudent(?)}";
			Connection connection=null;
			try {
				connection = jdbcTemplate.getDataSource().getConnection();
				CallableStatement callableSt = connection.prepareCall(procedureCall);
				callableSt.setString(1, "select");
				ResultSet rs = callableSt.executeQuery();
				int x=1;
				while (rs.next()) {
					PotentialStudent potentialStudent=new PotentialStudent();
					potentialStudent.setPotentialStudentId(rs.getInt("Pk_poltentialStdId"));
					potentialStudent.setName(rs.getString("StdName"));
					potentialStudent.setEmailId(rs.getString("EmailId"));
					list.add(potentialStudent);
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
	
	public String editPotentialStudentForm(PotentialStudent potentialStudent) throws SQLException {
		String procedureCall = "";
		Connection connection = null;
		try {

			connection = jdbcTemplate.getDataSource().getConnection();
			connection.setAutoCommit(false);
			procedureCall = "{call proc_PotentialStudent(?,?,?,?,?)}";
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "update");
			callableSt.setInt(2,potentialStudent.getPotentialStudentId());
			callableSt.setString(3, potentialStudent.getName());
			callableSt.setString(4,potentialStudent.getEmailId());
			callableSt.setInt(5, potentialStudent.getCreatedBy());
			
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
	public String deletePotentialStudentData(PotentialStudent potentialStudent){
		final String procedureCall = "{call proc_PotentialStudent(?,?,?,?,?)}";
		Connection connection = null;
		String returnValue="failed";
		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "delete");
			callableSt.setInt(2,potentialStudent.getPotentialStudentId());
			callableSt.setString(3, null);
			callableSt.setString(4,null);
			callableSt.setInt(5, potentialStudent.getCreatedBy());
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
	
	public String insertStudentData(String[] studentData) throws Exception{
		String procedureCall = "";
		Connection connection = null;
		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			connection.setAutoCommit(false);
			procedureCall = "{call proc_PotentialStudent(?,?,?,?)}";
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "Email");
			callableSt.setInt(2,0);
			callableSt.setString(3, studentData[0]);
			callableSt.setString(4,studentData[1]);
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
	
}
