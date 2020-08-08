package com.precise.dao;

import java.util.List;

import com.precise.model.AssignRole;

public interface AssingRoleDAO {
	List<AssignRole> getAssignRoles();

	void saveAssignedRoles(AssignRole role);

	AssignRole getRolesByStudentId(int studentId);

	void updateAssignedRoles(AssignRole role);
}
