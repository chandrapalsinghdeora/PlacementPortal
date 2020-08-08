package com.precise.model;

import java.io.File;

import javax.mail.Multipart;

import org.springframework.web.multipart.MultipartFile;

public class PotentialStudent {
	
	private int potentialStudentId;
	private String name;
	private String emailId;
	private Boolean isActive;
	public int createdBy;
	public String status;
	public MultipartFile fileName;
	
	public MultipartFile getFileName() {
		return fileName;
	}
	public void setFileName(MultipartFile fileName) {
		this.fileName = fileName;
	}
	public int getPotentialStudentId() {
		return potentialStudentId;
	}
	public void setPotentialStudentId(int potentialStudentId) {
		this.potentialStudentId = potentialStudentId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public Boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	public int getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
