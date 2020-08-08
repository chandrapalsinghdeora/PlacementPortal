package com.precise.model;

import java.sql.Timestamp;

public class Fine{
	
	private int fineId;
	private String fineReason;
	private String fineStatus;
	private Timestamp createdDate;
    private int rowCount;
    private int type;
    private int pgpId;
    private String individual;
    private String event;
    private Double amount;
    private String fineCreatedDate;
    private int roleId;
    private String email;
    private String username;
    
    
    
    
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public String getFineCreatedDate() {
		return fineCreatedDate;
	}
	public void setFineCreatedDate(String fineCreatedDate) {
		this.fineCreatedDate = fineCreatedDate;
	}
	public int getFineId() {
		return fineId;
	}
	public void setFineId(int fineId) {
		this.fineId = fineId;
	}
	public String getFineReason() {
		return fineReason;
	}
	public void setFineReason(String fineReason) {
		this.fineReason = fineReason;
	}
	
	public String getFineStatus() {
		return fineStatus;
	}
	public void setFineStatus(String fineStatus) {
		this.fineStatus = fineStatus;
	}
	public Timestamp getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}
	public int getRowCount() {
		return rowCount;
	}
	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getPgpId() {
		return pgpId;
	}
	public void setPgpId(int pgpId) {
		this.pgpId = pgpId;
	}
	public String getIndividual() {
		return individual;
	}
	public void setIndividual(String individual) {
		this.individual = individual;
	}
	public String getEvent() {
		return event;
	}
	public void setEvent(String event) {
		this.event = event;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	
    
}