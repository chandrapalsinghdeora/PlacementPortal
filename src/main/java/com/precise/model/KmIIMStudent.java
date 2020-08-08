package com.precise.model;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

public class KmIIMStudent {

	int studentId;
	String prefix;
	String emailid;
    boolean active;
    int groupid;
    String username;
    boolean wildCard;
    int createdid;
    String createddate;
    int updatedid;
    Date updateddate;
    MultipartFile file;

	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}
	public int getGroupid() {
		return groupid;
	}
	public void setGroupid(int groupid) {
		this.groupid = groupid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getStudentId() {
		return studentId;
	}
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	public String getPrefix() {
		return prefix;
	}
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	public String getEmailid() {
		return emailid;
	}
	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}
	
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public boolean isWildCard() {
		return wildCard;
	}
	public void setWildCard(boolean wildCard) {
		this.wildCard = wildCard;
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
