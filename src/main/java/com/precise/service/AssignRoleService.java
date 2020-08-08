package com.precise.service;

import java.util.List;

import com.precise.model.AssignRole;

public interface AssignRoleService {

	List<AssignRole> getAssignRoles();

	void saveAssignedRoles(AssignRole role);

	AssignRole getRolesByStudentId(int studentId);

	void updateAssignedRoles(AssignRole role);

}
