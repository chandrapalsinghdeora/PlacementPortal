package com.precise.model;

import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class FirmManagementForm {
	public int applicationId;
	public int firmManagementId;
	public String companyName;
	public int companyId;
	public int processId;
	public String processName;
	public int clusterId;
	public String clusterName;
	public int cohortId;
	public String cohortName;
	public List<Integer> roleCompanyId;
	public String roleCompany;
	public List<String> url;
	public List<String> urlDescription;
	public String additionalTextArea;
	public List<Integer> workExp;
	public List<Integer>workExpMax;
	public List<String> compensation;
	public List<Integer> noHire;
	public List<Integer> maxNoHire;
	public List<Integer> avgNoHire;
	public List<Integer> mapRoleId;
	public int yearId;
	public int year;
	public int noOfRoundId;
	public int noOfRound;
	public int noOfRole;
	public int noOfSelectedRounds;
	public String jobDecPath;
	public int createdBy;
	public Date createdDate;
	public int updatedBy;
	public Date updatedDate;
	public boolean preferenceServe;
	public int designationId;
	public int designationName;
	public String interviewExperience;
	public String internshipExperience;
	public boolean multCVRelates;
	public int limitCVNo;
	//public Date openingDate;
	//public Date closeingDate;
	public String openingDate;	
	public String closeingDate;
	private List<Boolean> coverLetter;
	private List<String> notCoverLetter;
	private List<String> selectedCoverLetter;
	MultipartFile jobDescription;
	List<MultipartFile>additionalFile;
	List<MultipartFile>moreAdditionalFile;
	private String jobDescriptionFilePathList;
	private List<String> additionalFilePathList;
	private List<String> moreAdditionalFilePathList;
	private List<Integer> roleId;
	private List<String> roleName;
	private List<Integer> desiationId;
	private List<Integer> urlId;
	private List<Integer> additionalFileId;
	private boolean hrHotlist;
	
	public List<Integer> getUrlId() {
		return urlId;
	}
	public void setUrlId(List<Integer> urlId) {
		this.urlId = urlId;
	}
	public List<Integer> getAdditionalFileId() {
		return additionalFileId;
	}
	public void setAdditionalFileId(List<Integer> additionalFileId) {
		this.additionalFileId = additionalFileId;
	}
	public List<Integer> getDesiationId() {
		return desiationId;
	}
	public void setDesiationId(List<Integer> desiationId) {
		this.desiationId = desiationId;
	}
	public List<Integer> getRoleId() {
		return roleId;
	}
	public void setRoleId(List<Integer> roleId) {
		this.roleId = roleId;
	}
	public List<String> getRoleName() {
		return roleName;
	}
	public void setRoleName(List<String> roleName) {
		this.roleName = roleName;
	}
	public List<MultipartFile> getMoreAdditionalFile() {
		return moreAdditionalFile;
	}
	public void setMoreAdditionalFile(List<MultipartFile> moreAdditionalFile) {
		this.moreAdditionalFile = moreAdditionalFile;
	}
	public List<String> getMoreAdditionalFilePathList() {
		return moreAdditionalFilePathList;
	}
	public void setMoreAdditionalFilePathList(List<String> moreAdditionalFilePathList) {
		this.moreAdditionalFilePathList = moreAdditionalFilePathList;
	}
	//MultipartFile jobDescription;
	//MultipartFile additionalFile;
	
	public int getDesignationId() {
		return designationId;
	}
	public void setDesignationId(int designationId) {
		this.designationId = designationId;
	}
	public int getDesignationName() {
		return designationName;
	}
	public void setDesignationName(int designationName) {
		this.designationName = designationName;
	}
	public boolean isPreferenceServe() {
		return preferenceServe;
	}
	public void setPreferenceServe(boolean preferenceServe) {
		this.preferenceServe = preferenceServe;
	}
	public boolean getPreferenceServe(){
		return preferenceServe;
	}
	public int getFirmManagementId() {
		return firmManagementId;
	}
	public void setFirmManagementId(int firmManagementId) {
		this.firmManagementId = firmManagementId;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public int getCompanyId() {
		return companyId;
	}
	public void setCompanyId(int companyId) {
		this.companyId = companyId;
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
	public int getClusterId() {
		return clusterId;
	}
	public void setClusterId(int clusterId) {
		this.clusterId = clusterId;
	}
	public String getClusterName() {
		return clusterName;
	}
	public void setClusterName(String clusterName) {
		this.clusterName = clusterName;
	}
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
	
	public String getRoleCompany() {
		return roleCompany;
	}
	public void setRoleCompany(String roleCompany) {
		this.roleCompany = roleCompany;
	}
	public List<String> getUrl() {
		return url;
	}
	public void setUrl(List<String> url) {
		this.url = url;
	}
	public List<String> getUrlDescription() {
		return urlDescription;
	}
	public void setUrlDescription(List<String> urlDescription) {
		this.urlDescription = urlDescription;
	}
	public String getAdditionalTextArea() {
		return additionalTextArea;
	}
	public void setAdditionalTextArea(String additionalTextArea) {
		this.additionalTextArea = additionalTextArea;
	}
	
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	/*public Date getOpeningDate() {
		return openingDate;
	}
	public void setOpeningDate(Date openingDate) {
		this.openingDate = openingDate;
	}
	public Date getCloseingDate() {
		return closeingDate;
	}
	public void setCloseingDate(Date closeingDate) {
		this.closeingDate = closeingDate;
	}*/
	public String getOpeningDate() {
		return openingDate;
	}
	public void setOpeningDate(String openingDate) {
		this.openingDate = openingDate;
	}
	public String getCloseingDate() {
		return closeingDate;
	}
	public void setCloseingDate(String closeingDate) {
		this.closeingDate = closeingDate;
	}
	public int getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(int updatedBy) {
		this.updatedBy = updatedBy;
	}
	public Date getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	public List<Integer> getRoleCompanyId() {
		return roleCompanyId;
	}
	public void setRoleCompanyId(List<Integer> roleCompanyId) {
		this.roleCompanyId = roleCompanyId;
	}
	public List<Integer> getWorkExp() {
		return workExp;
	}
	public void setWorkExp(List<Integer> workExp) {
		this.workExp = workExp;
	}
	public List<Integer> getWorkExpMax() {
		return workExpMax;
	}
	public void setWorkExpMax(List<Integer> workExpMax) {
		this.workExpMax = workExpMax;
	}
	public List<String> getCompensation() {
		return compensation;
	}
	public void setCompensation(List<String> compensation) {
		this.compensation = compensation;
	}
	public List<Integer> getNoHire() {
		return noHire;
	}
	public void setNoHire(List<Integer> noHire) {
		this.noHire = noHire;
	}
	public List<Integer> getMaxNoHire() {
		return maxNoHire;
	}
	public void setMaxNoHire(List<Integer> maxNoHire) {
		this.maxNoHire = maxNoHire;
	}
	public List<Integer> getAvgNoHire() {
		return avgNoHire;
	}
	public void setAvgNoHire(List<Integer> avgNoHire) {
		this.avgNoHire = avgNoHire;
	}
	public List<Integer> getMapRoleId() {
		return mapRoleId;
	}
	public void setMapRoleId(List<Integer> mapRoleId) {
		this.mapRoleId = mapRoleId;
	}
	public List<Boolean> getCoverLetter() {
		return coverLetter;
	}
	public void setCoverLetter(List<Boolean> coverLetter) {
		this.coverLetter = coverLetter;
	}
	public MultipartFile getJobDescription() {
		return jobDescription;
	}
	public void setJobDescription(MultipartFile jobDescription) {
		this.jobDescription = jobDescription;
	}
	public List<MultipartFile> getAdditionalFile() {
		return additionalFile;
	}
	public void setAdditionalFile(List<MultipartFile> additionalFile) {
		this.additionalFile = additionalFile;
	}
	public String getInterviewExperience() {
		return interviewExperience;
	}
	public void setInterviewExperience(String interviewExperience) {
		this.interviewExperience = interviewExperience;
	}
	public String getInternshipExperience() {
		return internshipExperience;
	}
	public void setInternshipExperience(String internshipExperience) {
		this.internshipExperience = internshipExperience;
	}
	public boolean isMultCVRelates() {
		return multCVRelates;
	}
	public boolean getMultCVRelates() {
		return multCVRelates;
	}
	public void setMultCVRelates(boolean multCVRelates) {
		this.multCVRelates = multCVRelates;
	}
	public int getLimitCVNo() {
		return limitCVNo;
	}
	public void setLimitCVNo(int limitCVNo) {
		this.limitCVNo = limitCVNo;
	}
	public int getNoOfRound() {
		return noOfRound;
	}
	public void setNoOfRound(int noOfRound) {
		this.noOfRound = noOfRound;
	}
	public int getYearId() {
		return yearId;
	}
	public void setYearId(int yearId) {
		this.yearId = yearId;
	}
	public int getNoOfRoundId() {
		return noOfRoundId;
	}
	public void setNoOfRoundId(int noOfRoundId) {
		this.noOfRoundId = noOfRoundId;
	}
	public String getJobDecPath() {
		return jobDecPath;
	}
	public void setJobDecPath(String jobDecPath) {
		this.jobDecPath = jobDecPath;
	}
	public int getApplicationId() {
		return applicationId;
	}
	public void setApplicationId(int applicationId) {
		this.applicationId = applicationId;
	}
	public int getNoOfRole() {
		return noOfRole;
	}
	public void setNoOfRole(int noOfRole) {
		this.noOfRole = noOfRole;
	}
	public int getNoOfSelectedRounds() {
		return noOfSelectedRounds;
	}
	public void setNoOfSelectedRounds(int noOfSelectedRounds) {
		this.noOfSelectedRounds = noOfSelectedRounds;
	}
	public String getJobDescriptionFilePathList() {
		return jobDescriptionFilePathList;
	}
	public void setJobDescriptionFilePathList(String jobDescriptionFilePathList) {
		this.jobDescriptionFilePathList = jobDescriptionFilePathList;
	}
	public List<String> getAdditionalFilePathList() {
		return additionalFilePathList;
	}
	public void setAdditionalFilePathList(List<String> additionalFilePathList) {
		this.additionalFilePathList = additionalFilePathList;
	}
	public List<String> getNotCoverLetter() {
		return notCoverLetter;
	}
	public void setNotCoverLetter(List<String> notCoverLetter) {
		this.notCoverLetter = notCoverLetter;
	}
	public List<String> getSelectedCoverLetter() {
		return selectedCoverLetter;
	}
	public void setSelectedCoverLetter(List<String> selectedCoverLetter) {
		this.selectedCoverLetter = selectedCoverLetter;
	}
	public boolean isHrHotlist() {
		return hrHotlist;
	}
	public void setHrHotlist(boolean hrHotlist) {
		this.hrHotlist = hrHotlist;
	}
	
	
	
}
