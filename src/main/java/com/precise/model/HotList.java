package com.precise.model;

import java.util.List;

public class HotList {
    private int applyId;
    private int hotListId;
    private String idhotlist;
   
	private int rollNumber;
    private String emailID;
    private String name;
    private String cvName;
	private String status;
    private int preference;
    private boolean shortList;
    private int roleId;
    private String hotListIdsSelected;
    private String greetingsHotlist;
    private List<Integer>  hotListIdslist;
    private List<Integer>  applyIdListIdslist;
    private int coverLetter;
    private List<Integer> notSelected;
    private String shortListIdsSelected;
    private List<Integer> shortListIdslist;
    private String greetings;
    private int shortListId;
    private int createdBy;
    private String rmStatus;
    private int cmpRoleId;
    private String hotListBy;
    public String getCvName() {
		return cvName;
	}

	public void setCvName(String cvName) {
		this.cvName = cvName;
	}
	public String getHotListBy() {
		return hotListBy;
	}

	public void setHotListBy(String hotListBy) {
		this.hotListBy = hotListBy;
	}

	public int getCmpRoleId() {
		return cmpRoleId;
	}

	public void setCmpRoleId(int cmpRoleId) {
		this.cmpRoleId = cmpRoleId;
	}

	public int getShortListId() {
		return shortListId;
	}

	public void setShortListId(int shortListId) {
		this.shortListId = shortListId;
	}

	public List<Integer> getShortListIdslist() {
		return shortListIdslist;
	}

	public void setShortListIdslist(List<Integer> shortListIdslist) {
		this.shortListIdslist = shortListIdslist;
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

	public void setIdhotlist(String idhotlist) {
		this.idhotlist = idhotlist;
	}
	
	public String getIdhotlist() {
		return idhotlist;
	}

	public List<Integer> getApplyIdListIdslist() {
		return applyIdListIdslist;
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
	
	public int getHotListId() {
		return hotListId;
	}
	public void setHotListId(int hotListId) {
		this.hotListId = hotListId;
	}
	
	
	public String getHotListIdsSelected() {
		return hotListIdsSelected;
	}
	public void setHotListIdsSelected(String hotListIdsSelected) {
		this.hotListIdsSelected = hotListIdsSelected;
	}
	public String getGreetingsHotlist() {
		return greetingsHotlist;
	}
	public void setGreetingsHotlist(String greetingsHotlist) {
		this.greetingsHotlist = greetingsHotlist;
	}
	
	
	public List<Integer> getHotListIdslist() {
		return hotListIdslist;
	}
	public void setHotListIdslist(List<Integer> hotListIdslist) {
		this.hotListIdslist = hotListIdslist;
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
	
}

