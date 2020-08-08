package com.precise.model;

import java.util.Date;

public class MasterRole {
	 int pkroleid;
     String rolename;
     String roledescription;
     int moduleid;
     Boolean isactive;
     int createdid;
     String createddate;
     int updatedid;
     Date updateddate;
	
	public int getModuleid() {
		return moduleid;
	}
	public void setModuleid(int moduleid) {
		this.moduleid = moduleid;
	}
	public String getRolename() {
		return rolename;
	}
	public void setRolename(String rolename) {
		this.rolename = rolename;
	}
	public String getRoledescription() {
		return roledescription;
	}
	public void setRoledescription(String roledescription) {
		this.roledescription = roledescription;
	}

	public String getCreateddate() {
		return createddate;
	}
	public void setCreateddate(String createddate) {
		this.createddate = createddate;
	}

	public int getCreatedid() {
		return createdid;
	}
	public void setCreatedid(int createdid) {
		this.createdid = createdid;
	}
	public Date getUpdateddate() {
		return updateddate;
	}
	public void setUpdateddate(Date updateddate) {
		this.updateddate = updateddate;
	}
	public int getPkroleid() {
		return pkroleid;
	}
	public void setPkroleid(int pkroleid) {
		this.pkroleid = pkroleid;
	}
	public Boolean getIsactive() {
		return isactive;
	}
	public void setIsactive(Boolean isactive) {
		this.isactive = isactive;
	}
	public int getUpdatedid() {
		return updatedid;
	}
	public void setUpdatedid(int updatedid) {
		this.updatedid = updatedid;
	}

}
