package com.precise.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.precise.controller.RoleController;
import com.precise.model.Forum;
import com.precise.model.MasterRole;
import com.precise.model.SessionBean;

public interface RoleDao {

	public void daosendmethod(MasterRole masterrole);
	public List<MasterRole> getAllRole();
	public void updateRole(MasterRole masterrole);
	public  MasterRole getRoleInfoWithId(int RoleId, int userId);
	public void deleteRole(int RoleId, int userId);
	      
}
