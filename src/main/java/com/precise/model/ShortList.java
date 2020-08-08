package com.precise.model;

import java.util.List;

public class ShortList {
	private int applyId;
	private int shortListId;
	private String idlist;
	private int rollNumber;
	private String emailID;
	private String name;
	private String cvName;
	
	private String status;
	private int preference;
	private boolean shortList;
	private int roleId;
	private String shortListIdsSelected;
	private String greetings;
	private List<Integer> shortListIdslist;
	private List<Integer> applyIdListIdslist;
	private int coverLetter;
	private List<Integer> notSelected;
	private int createdBy;
	private String rmStatus;
	private int cmpRoleId;
	private String shortListBy;

	public String getShortListBy() {
		return shortListBy;
	}

	public void setShortListBy(String shortListBy) {
		this.shortListBy = shortListBy;
	}

	public String getIdlist() {
		return idlist;
	}

	public void setIdlist(String idlist) {
		this.idlist = idlist;
	}

	public List<Integer> getApplyIdListIdslist() {
		return applyIdListIdslist;
	}
	public String getCvName() {
		return cvName;
	}

	public void setCvName(String cvName) {
		this.cvName = cvName;
	}


	public void setApplyIdListIdslist(List<Integer> applyIdListIdslist) {
		this.applyIdListIdslist = applyIdListIdslist;
	}
	

	public int getApplyId() {
		return applyId;
	}

	public void setApplyId(int applyId) {
		this.applyId = applyId;
	}

	public int getRollNumber() {
		return rollNumber;
	}

	public void setRollNumber(int rollNumber) {
		this.rollNumber = rollNumber;
	}

	public String getEmailID() {
		return emailID;
	}

	public void setEmailID(String emailID) {
		this.emailID = emailID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getPreference() {
		return preference;
	}

	public void setPreference(int preference) {
		this.preference = preference;
	}

	public boolean isShortList() {
		return shortList;
	}

	public void setShortList(boolean shortList) {
		this.shortList = shortList;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public int getShortListId() {
		return shortListId;
	}

	public void setShortListId(int shortListId) {
		this.shortListId = shortListId;
	}

	public String getShortListIdsSelected() {
		return shortListIdsSelected;
	}

	public void setShortListIdsSelected(String shortListIdsSelected) {
		this.shortListIdsSelected = shortListIdsSelected;
	}

	public String getGreetings() {
		return greetings;
	}

	public void setGreetings(String greetings) {
		this.greetings = greetings;
	}

	public List<Integer> getShortListIdslist() {
		return shortListIdslist;
	}

	public void setShortListIdslist(List<Integer> shortListIdslist) {
		this.shortListIdslist = shortListIdslist;
	}

	public int getCoverLetter() {
		return coverLetter;
	}

	public void setCoverLetter(int coverLetter) {
		this.coverLetter = coverLetter;
	}

	public List<Integer> getNotSelected() {
		return notSelected;
	}

	public void setNotSelected(List<Integer> notSelected) {
		this.notSelected = notSelected;
	}

	public int getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}

	public String getRmStatus() {
		return rmStatus;
	}

	public void setRmStatus(String rmStatus) {
		this.rmStatus = rmStatus;
	}

	public int getCmpRoleId() {
		return cmpRoleId;
	}

	public void setCmpRoleId(int cmpRoleId) {
		this.cmpRoleId = cmpRoleId;
	}

}
