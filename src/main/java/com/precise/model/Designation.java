package com.precise.model;

import java.util.Date;

public class Designation {
	
	int designationid;
	String designationname;
	String designationdescription;
	boolean IsActive;
    int createdid;
    String createddate;
    int updatedid;
    Date updateddate;
    
    
	public int getDesignationid() {
		return designationid;
	}
	public void setDesignationid(int designationid) {
		this.designationid = designationid;
	}
	public String getDesignationname() {
		return designationname;
	}
	public void setDesignationname(String designationname) {
		this.designationname = designationname;
	}
	public String getDesignationdescription() {
		return designationdescription;
	}
	public void setDesignationdescription(String designationdescription) {
		this.designationdescription = designationdescription;
	}
	public boolean isIsActive() {
		return IsActive;
	}
	public void setIsActive(boolean isActive) {
		IsActive = isActive;
	}
	public int getCreatedid() {
		return createdid;
	}
	public void setCreatedid(int createdid) {
		this.createdid = createdid;
	}
	public String getCreateddate() {
		return createddate;
	}
	public void setCreateddate(String createddate) {
		this.createddate = createddate;
	}
	public int getUpdatedid() {
		return updatedid;
	}
	public void setUpdatedid(int updatedid) {
		this.updatedid = updatedid;
	}
	public Date getUpdateddate() {
		return updateddate;
	}
	public void setUpdateddate(Date updateddate) {
		this.updateddate = updateddate;
	}

}
