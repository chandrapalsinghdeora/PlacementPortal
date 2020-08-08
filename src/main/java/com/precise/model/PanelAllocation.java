package com.precise.model;

public class PanelAllocation {
	private int firmId;
	private int roleId;
	private int clusterId;
	private int panelAllocationId;
	private String firmName;
	private String roleName;
	private String rmName;
	private String panelDate;
	private String panelStartTime;
	private String panelEndTime;
	private int panel;
	private int extraRooms;
	private int createdId;
	private String emailId;
	private String notificationStatus;
	private Boolean isLocked;	
	private String clusterName;
	private int processId;
	private String processName;
	private int mappedRoleId;
	private String mapRoleName;
	
	public String getMapRoleName() {
		return mapRoleName;
	}

	public void setMapRoleName(String mapRoleName) {
		this.mapRoleName = mapRoleName;
	}

	public int getPanelAllocationId() {
		return panelAllocationId;
	}

	public void setPanelAllocationId(int panelAllocationId) {
		this.panelAllocationId = panelAllocationId;
	}

	public int getFirmId() {
		return firmId;
	}

	public void setFirmId(int firmId) {
		this.firmId = firmId;
	}

	public int getClusterId() {
		return clusterId;
	}

	public void setClusterId(int clusterId) {
		this.clusterId = clusterId;
	}

	public String getFirmName() {
		return firmName;
	}

	public void setFirmName(String firmName) {
		this.firmName = firmName;
	}

	public String getRmName() {
		return rmName;
	}

	public void setRmName(String rmName) {
		this.rmName = rmName;
	}

	public String getPanelDate() {
		return panelDate;
	}

	public void setPanelDate(String panelDate) {
		this.panelDate = panelDate;
	}

	public int getPanel() {
		return panel;
	}

	public void setPanel(int panel) {
		this.panel = panel;
	}

	public int getExtraRooms() {
		return extraRooms;
	}

	public void setExtraRooms(int extraRooms) {
		this.extraRooms = extraRooms;
	}

	public String getPanelStartTime() {
		return panelStartTime;
	}

	public void setPanelStartTime(String panelStartTime) {
		this.panelStartTime = panelStartTime;
	}

	public String getPanelEndTime() {
		return panelEndTime;
	}

	public void setPanelEndTime(String panelEndTime) {
		this.panelEndTime = panelEndTime;
	}

	public int getCreatedId() {
		return createdId;
	}

	public void setCreatedId(int createdId) {
		this.createdId = createdId;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getNotificationStatus() {
		return notificationStatus;
	}

	public void setNotificationStatus(String notificationStatus) {
		this.notificationStatus = notificationStatus;
	}

	public Boolean getIsLocked() {
		return isLocked;
	}

	public void setIsLocked(Boolean isLocked) {
		this.isLocked = isLocked;
	}

	public String getClusterName() {
		return clusterName;
	}

	public void setClusterName(String clusterName) {
		this.clusterName = clusterName;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public int getProcessId() {
		return processId;
	}

	public void setProcessId(int processId) {
		this.processId = processId;
	}

	public String getProcessName() {
		return processName;
	}

	public void setProcessName(String processName) {
		this.processName = processName;
	}

	public int getMappedRoleId() {
		return mappedRoleId;
	}

	public void setMappedRoleId(int mappedRoleId) {
		this.mappedRoleId = mappedRoleId;
	}
    
	
}
