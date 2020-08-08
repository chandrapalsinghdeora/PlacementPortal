package com.precise.dao;

import java.sql.CallableStatement;

import com.precise.dbconnection.ConnectionDao; 

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

//import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.precise.controller.RoleController;
import com.precise.model.Forum;
import com.precise.model.MasterRole;
import com.precise.model.SessionBean;

@Repository
public class RoleDaoImpl extends ConnectionDao implements RoleDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public void daosendmethod(MasterRole masterrole) {
		// TODO Auto-generated method stub
		
		 Connection connection = null;
		 try{
		 connection = getJdbcTemplate().getDataSource().getConnection();
		 
		 CallableStatement callableStatement = connection.prepareCall("{call proc_Master(?, ?, ?, ?, ?, ?)}");
		 
		 callableStatement.setString(1, "insert");
		 callableStatement.setString(2, masterrole.getRolename());
		 callableStatement.setString(3, masterrole.getRoledescription());
		 callableStatement.setInt(4, masterrole.getModuleid());
		 callableStatement.setInt(5, masterrole.getCreatedid());
		 callableStatement.setInt(6, 0);
		 callableStatement.execute();
		 }catch (SQLException e) {
			    e.printStackTrace();
		} finally {
		if(connection != null)
		try {
		connection.close();
		} catch (SQLException e) {
		e.printStackTrace();
		}
		}
	}

	@Override
	public List<MasterRole> getAllRole() {
		System.out.println("RoleDAOImpl.getAllRole()--");
		List<MasterRole> list = new ArrayList<MasterRole>();
		final String procedureCall = "{call proc_Master(?)}";
		Connection connection = null;
		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "select");
			ResultSet rs = callableSt.executeQuery();
			int x=1;
			while (rs.next()) {
				MasterRole masterrole = new MasterRole();
				masterrole.setPkroleid(rs.getInt("PK_RoleId"));
				masterrole.setRolename(rs.getString("RoleName"));
		        masterrole.setRoledescription(rs.getString("RoleDecription"));
		        masterrole.setModuleid(rs.getInt("ModuleID"));
		        masterrole.setIsactive(rs.getBoolean("isActive"));

				list.add(masterrole);
				System.out.println("rolename---- " + rs.getString("RoleName"));
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

	@Override
	public MasterRole getRoleInfoWithId(int RoleId, int userId) {
		final String procedureCall = "{call proc_Master(?,?,?,?,?,?)}";
		MasterRole masterrole = null;
		Connection connection = null;
		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "selectById");
			callableSt.setString(2, null);
			callableSt.setString(3, null);
			callableSt.setInt(4, 0);
			callableSt.setInt(5, userId);
			callableSt.setInt(6, RoleId);
			
			ResultSet rs = callableSt.executeQuery();
			if (rs.next()) {
				masterrole = new MasterRole();
				masterrole.setPkroleid(rs.getInt("PK_RoleId"));
				masterrole.setRolename(rs.getString("RoleName"));
		        masterrole.setRoledescription(rs.getString("RoleDecription"));
		        masterrole.setModuleid(rs.getInt("ModuleID"));
		        masterrole.setIsactive(rs.getBoolean("isActive"));
				System.out.println("rolename---- " + rs.getString("RoleName"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return masterrole;
	}

	@Override
	public void updateRole(MasterRole masterrole) {
		// TODO Auto-generated method stub
		System.out.println("RoleDaoImpl.updateForum():: pkroleid " + masterrole.getPkroleid() + "rolename=" + masterrole.getRolename());
		final String procedureCall = "{call proc_Master(?,?,?,?,?,?)}";
		Connection connection = null;
		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "update");
			callableSt.setString(2, masterrole.getRolename());
			callableSt.setString(3, masterrole.getRoledescription());
			callableSt.setInt(4, masterrole.getModuleid());
			callableSt.setInt(5, masterrole.getCreatedid());
			callableSt.setInt(6, masterrole.getPkroleid());
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

	@Override
	public void deleteRole(int RoleId, int userId) {
		// TODO Auto-generated method stub
		System.out.println("RoleDaoImpl.updateForum():: RoleId " + RoleId+ "userId=" + userId);
		final String procedureCall = "{call proc_Master(?,?,?,?,?,?)}";
		Connection connection = null;
		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "delete");
			callableSt.setString(2, null);
			callableSt.setString(3, null);
			callableSt.setInt(4, 0);
			callableSt.setInt(5, userId);
			callableSt.setInt(6, RoleId);
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
