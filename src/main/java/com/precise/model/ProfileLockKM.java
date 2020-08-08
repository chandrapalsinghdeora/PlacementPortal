package com.precise.model;

import java.util.List;

public class ProfileLockKM {
	private int profileLockId;
	private String studentName;
	private String emailId;
	private boolean checkBox;
	private boolean adminLock;
	private List<Integer> selectedIds;
	
	public int getProfileLockId() {
		return profileLockId;
	}
	public void setProfileLockId(int profileLockId) {
		this.profileLockId = profileLockId;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public boolean isCheckBox() {
		return checkBox;
	}
	public void setCheckBox(boolean checkBox) {
		this.checkBox = checkBox;
	}
	
	
	public boolean isAdminLock() {
		return adminLock;
	}
	public void setAdminLock(boolean adminLock) {
		this.adminLock = adminLock;
	}
	public List<Integer> getSelectedIds() {
		return selectedIds;
	}
	public void setSelectedIds(List<Integer> selectedIds) {
		this.selectedIds = selectedIds;
	}
	
    
}
