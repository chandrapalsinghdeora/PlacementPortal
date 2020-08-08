package com.precise.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.precise.dao.RoleDao;
import com.precise.model.Forum;
import com.precise.model.MasterRole;

@Service
public class RoleServiceImpl implements RoleService{

	@Autowired
	RoleDao roleDao;

	@Override
	public void servicesendmethod(MasterRole masterrole) {
	
		 roleDao.daosendmethod(masterrole);
	}

	@Override
	public List<MasterRole> getAllRole() {
		// TODO Auto-generated method stub
		return roleDao.getAllRole();
	}

	@Override
	public MasterRole getRoleInfoWithId(int RoleId, int userId) {
		return roleDao.getRoleInfoWithId(RoleId, userId);
	}

	@Override
	public void updateRole(MasterRole masterrole) {
		// TODO Auto-generated method stub
		 roleDao.updateRole(masterrole);
	}
	public void deleteRole(int RoleId, int userId){
		 roleDao.deleteRole(RoleId, userId);
	}
}
