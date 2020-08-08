package com.precise.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.precise.dao.RoleDao;
import com.precise.model.Forum;
import com.precise.model.MasterRole;

public interface RoleService {
	
	
	  public void servicesendmethod(MasterRole masterrole);
	  public List<MasterRole> getAllRole();
	  public void updateRole(MasterRole masterrole);
	  public MasterRole getRoleInfoWithId(int RoleId, int userId);
	  public void deleteRole(int RoleId, int userId);
}
