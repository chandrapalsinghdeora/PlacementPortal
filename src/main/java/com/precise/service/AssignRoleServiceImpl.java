package com.precise.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.precise.dao.AssingRoleDAO;
import com.precise.model.AssignRole;

@Service
public class AssignRoleServiceImpl implements AssignRoleService{
    @Autowired
    AssingRoleDAO assignDao;
	@Override
	public List<AssignRole> getAssignRoles() {
		System.out.println("AssignRoleServiceImpl.getAssignRoles()");
		return assignDao.getAssignRoles();
	}
	@Override
	public void saveAssignedRoles(AssignRole role) {
		assignDao.saveAssignedRoles(role);
		
	}
	@Override
	public AssignRole getRolesByStudentId(int studentId) {
		
		return assignDao.getRolesByStudentId(studentId);
	}
	@Override
	public void updateAssignedRoles(AssignRole role) {
		assignDao.updateAssignedRoles(role);
	}
	
	
   
}
