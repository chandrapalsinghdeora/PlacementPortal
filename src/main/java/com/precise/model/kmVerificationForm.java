package com.precise.model;

public class kmVerificationForm {

	int applicationId;
	String companyName;
	String designationName;
	String rmName;
	String share;
	Boolean verification;
	String applicationStatus;
	private String clusterName;
	private String cohortName;
	private int clusterId;
	private int cohortId;
	public String jobDescPath;
	
	
	public String getJobDescPath() {
		return jobDescPath;
	}
	public void setJobDescPath(String jobDescPath) {
		this.jobDescPath = jobDescPath;
	}
	public int getClusterId() {
		return clusterId;
	}
	public void setClusterId(int clusterId) {
		this.clusterId = clusterId;
	}
	public int getCohortId() {
		return cohortId;
	}
	public void setCohortId(int cohortId) {
		this.cohortId = cohortId;
	}
	public String getClusterName() {
		return clusterName;
	}
	public void setClusterName(String clusterName) {
		this.clusterName = clusterName;
	}
	public String getCohortName() {
		return cohortName;
	}
	public void setCohortName(String cohortName) {
		this.cohortName = cohortName;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getDesignationName() {
		return designationName;
	}
	public void setDesignationName(String designationName) {
		this.designationName = designationName;
	}
	public String getShare() {
		return share;
	}
	public void setShare(String share) {
		this.share = share;
	}
	public String getApplicationStatus() {
		return applicationStatus;
	}
	public void setApplicationStatus(String applicationStatus) {
		this.applicationStatus = applicationStatus;
	}
	public int getApplicationId() {
		return applicationId;
	}
	public void setApplicationId(int applicationId) {
		this.applicationId = applicationId;
	}
	
	public String getRmName() {
		return rmName;
	}
	public void setRmName(String rmName) {
		this.rmName = rmName;
	}
	public Boolean getVerification() {
		return verification;
	}
	public void setVerification(Boolean verification) {
		this.verification = verification;
	}

	

	
	
}
