package com.precise.model;

public class SessionBean {

	private String userName;
	private String userRoleType;
	private String id;
	private int userID;
	private int roleID;
	private int mentRoleID;
	private int rmRoleID;
	private int setcomID;
	private String email;
	private boolean verified_email;
	private String name;
	private String given_name;
	private String family_name;
	private int cmpRoleId;
	private int pgpFlag;
	
	public int getPgpFlag() {
		return pgpFlag;
	}

	public void setPgpFlag(int pgpFlag) {
		this.pgpFlag = pgpFlag;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public int getRoleID() {
		return roleID;
	}

	public void setRoleID(int roleID) {
		this.roleID = roleID;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isVerified_email() {
		return verified_email;
	}

	public void setVerified_email(boolean verified_email) {
		this.verified_email = verified_email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGiven_name() {
		return given_name;
	}

	public void setGiven_name(String given_name) {
		this.given_name = given_name;
	}

	public String getFamily_name() {
		return family_name;
	}

	public void setFamily_name(String family_name) {
		this.family_name = family_name;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserRoleType() {
		return userRoleType;
	}

	public void setUserRoleType(String userRoleType) {
		this.userRoleType = userRoleType;
	}


	public int getMentRoleID() {
		return mentRoleID;
	}

	public void setMentRoleID(int mentRoleID) {
		this.mentRoleID = mentRoleID;
	}

	public int getRmRoleID() {
		return rmRoleID;
	}

	public void setRmRoleID(int rmRoleID) {
		this.rmRoleID = rmRoleID;
	}

	public int getCmpRoleId() {
		return cmpRoleId;
	}

	public void setCmpRoleId(int cmpRoleId) {
		this.cmpRoleId = cmpRoleId;
	}

	public int getSetcomID() {
		return setcomID;
	}

	public void setSetcomID(int setcomID) {
		this.setcomID = setcomID;
	}
	
}
