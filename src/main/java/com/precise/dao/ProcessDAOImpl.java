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
import com.precise.model.ProcessManagement;

@Repository
public class ProcessDAOImpl implements ProcessDAO {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public String submitProces(ProcessManagement process) throws SQLException {
		String procedureCall = "";
		Connection connection = null;
		try {

			connection = jdbcTemplate.getDataSource().getConnection();
			connection.setAutoCommit(false);
			procedureCall = "{call proc_Master(?,?,?,?,?,?)}";
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "insertprocess");
			callableSt.setString(2, process.getProcessName());
			callableSt.setString(3, process.getDescription());
			callableSt.setInt(4, 0);
			callableSt.setInt(5, process.getCreatedBy());
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
	public List<ProcessManagement> getAllProcess() {
		
		List<ProcessManagement> list = new ArrayList<ProcessManagement>();
			final String procedureCall = "{call proc_Master(?)}";
			Connection connection=null;
			try {
				connection = jdbcTemplate.getDataSource().getConnection();
				CallableStatement callableSt = connection.prepareCall(procedureCall);
				callableSt.setString(1, "selectprocess");
				ResultSet rs = callableSt.executeQuery();
				int x=1;
				while (rs.next()) {
					ProcessManagement processManagement=new ProcessManagement();
					processManagement.setProcessId(rs.getInt("Pk_ProcessId"));
					processManagement.setProcessName(rs.getString("ProcessName"));
					processManagement.setDescription(rs.getString("ProcessDescription"));
					list.add(processManagement);
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
	
	public String editProcessForm(ProcessManagement process) throws SQLException {
		String procedureCall = "";
		Connection connection = null;
		try {

			connection = jdbcTemplate.getDataSource().getConnection();
			connection.setAutoCommit(false);
			procedureCall = "{call proc_Master(?,?,?,?,?,?)}";
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "updateprocess");
			callableSt.setString(2, process.getProcessName());
			callableSt.setString(3, process.getDescription());
			callableSt.setInt(4, 0);
			callableSt.setInt(5, process.getCreatedBy());
			callableSt.setInt(6, process.getProcessId());
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
	public String deleteProcessData(ProcessManagement process){
		final String procedureCall = "{call proc_Master(?,?,?,?,?,?)}";
		Connection connection = null;
		String returnValue="failed";
		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "deleteprocess");
			callableSt.setString(2, null);
			callableSt.setString(3, null);
			callableSt.setInt(4, 0);
			callableSt.setInt(5, process.getCreatedBy());
			callableSt.setInt(6, process.getProcessId());
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
