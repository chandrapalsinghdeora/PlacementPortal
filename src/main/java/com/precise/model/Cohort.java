package com.precise.model;

public class Cohort {
	public int cohortId;
	public String cohortName;
	public String clusterName;
	public String getClusterName() {
		return clusterName;
	}
	public void setClusterName(String clusterName) {
		this.clusterName = clusterName;
	}
	public String cohortDescription;
	public String status;
	public int createdBy;
	public int clusterId;
	public int getCohortId() {
		return cohortId;
	}
	public void setCohortId(int cohortId) {
		this.cohortId = cohortId;
	}
	public String getCohortName() {
		return cohortName;
	}
	public void setCohortName(String cohortName) {
		this.cohortName = cohortName;
	}
	public String getCohortDescription() {
		return cohortDescription;
	}
	public void setCohortDescription(String cohortDescription) {
		this.cohortDescription = cohortDescription;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}
	public int getClusterId() {
		return clusterId;
	}
	public void setClusterId(int clusterId) {
		this.clusterId = clusterId;
	}
    
}
