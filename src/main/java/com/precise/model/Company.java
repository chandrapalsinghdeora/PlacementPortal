package com.precise.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public class Company {
	public int companyId;
	public String companyName;
	public String companyDescription;
	public boolean IsActive;
	public int createdBy;
	public Date createdDate;
	public int updatedBy;
	public Date updatedDate;
	public int applicationId;
	public int processId;
	public int year;
	public int roleId;
	public String prefenceSurvey;
	public int jobDescriptionId;
	public int clusterId;
	public String clusterName;
	public String cohortName;
	public String cohotName;
	
	public int cohortId;
	public boolean coverLetter;
	public int urlid;
	public String opningDatetime;
	public String closingDatetime;
	//public Date opningDatetime;
	//public Date closingDatetime;
	public int workExp;
	public double compensation;
	public int mapInterviewExpId;
	public String additionalText;
	public String processName;
	public String companyRoleName;
	public String companyRoleDescription;
	public String jobDescription;
	public String cluserName;
	public String urlText;
	public String applicationStatus;
	//public String filePath;
	public List<String>filePath;
	//public String title;
	public List<String> title;
	public List<String> roleCompanyId;
	public List<Boolean> roleCover;
	public List<String> companyRoleId;
	//public int cvReleted;
	public List<String> cvReleted;
	public String cvReletedName;
	private List<MultipartFile> uploadFile;
	public boolean isApply;
	public boolean isWithdraw;
	List<RoleCompany> listCompanyRoles=new ArrayList<RoleCompany>();	
	public String applied;
	public List<String> rank;
	public List<String> rankList;
	public String roleCompany;
	public boolean verify;
	private List<String> additionalFilePathList;
	private List<String> listCompanyURL;
	//public int applyId;
    public List<Integer> applyId;
    public String shortlist;
    

		/*public List<String> jdList;
		public List<String> roleNameList;*/
		public String queryLink;
		/*public List<String> intershipExpList;
		public List<String> interviewExpList;*/
		public List<Integer> checkBoxList;
		public boolean multipleCVCheck;
		public List<RoleBaseLink> roleBaseLink;
		public int limitCVNo;
		public List<Integer> mapRoleId;
		public String process;
		public int offerCount;
		public int applyID;
        public int totalApplication;
        public String processNm;
	
        public String getProcessNm() {
			return processNm;
		}
		public void setProcessNm(String processNm) {
			this.processNm = processNm;
		}
		public String getCohotName() {
    		return cohotName;
    	}
    	public void setCohotName(String cohotName) {
    		this.cohotName = cohotName;
    	}
	public int getTotalApplication() {
			return totalApplication;
		}
		public void setTotalApplication(int totalApplication) {
			this.totalApplication = totalApplication;
		}
	public int getApplyID() {
			return applyID;
		}
		public void setApplyID(int applyID) {
			this.applyID = applyID;
		}
	public List<String> getListCompanyURL() {
		return listCompanyURL;
	}
	public void setListCompanyURL(List<String> listCompanyURL) {
		this.listCompanyURL = listCompanyURL;
	}
	public List<String> getAdditionalFilePathList() {
		return additionalFilePathList;
	}
	public void setAdditionalFilePathList(List<String> additionalFilePathList) {
		this.additionalFilePathList = additionalFilePathList;
	}
		
	public int getOfferCount() {
		return offerCount;
	}
	public void setOfferCount(int offerCount) {
		this.offerCount = offerCount;
	}
	public List<Integer> getMapRoleId() {
		return mapRoleId;
	}
	public void setMapRoleId(List<Integer> mapRoleId) {
		this.mapRoleId = mapRoleId;
	}
	public int getLimitCVNo() {
		return limitCVNo;
	}
	public void setLimitCVNo(int limitCVNo) {
		this.limitCVNo = limitCVNo;
	}
	public boolean isVerify() {
		return verify;
	}
	public void setVerify(boolean verify) {
		this.verify = verify;
	}
	/*public int getApplyId() {
		return applyId;
	}
	public void setApplyId(int applyId) {
		this.applyId = applyId;
	}
	*/
	public String getRoleCompany() {
		return roleCompany;
	}
	public void setRoleCompany(String roleCompany) {
		this.roleCompany = roleCompany;
	}
	
	public List<String> getRankList() {
		return rankList;
	}
	public void setRankList(List<String> rankList) {
		this.rankList = rankList;
	}
	public String getApplied() {
		return applied;
	}
	public void setApplied(String applied) {
		this.applied = applied;
	}
	public boolean isWithdraw() {
		return isWithdraw;
	}
	public void setWithdraw(boolean isWithdraw) {
		this.isWithdraw = isWithdraw;
	}
	public boolean isApply() {
		return isApply;
	}
	public void setApply(boolean isApply) {
		this.isApply = isApply;
	}
	
	public String getCvReletedName() {
		return cvReletedName;
	}
	public void setCvReletedName(String cvReletedName) {
		this.cvReletedName = cvReletedName;
	}
	
	/*public MultipartFile getUploadFile() {
		return uploadFile;
	}
	public void setUploadFile(MultipartFile uploadFile) {
		this.uploadFile = uploadFile;
	}*/
/*	public int getCvReleted() {
		return cvReleted;
	}
	public void setCvReleted(int cvReleted) {
		this.cvReleted = cvReleted;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}*/
	
	public List<MultipartFile> getUploadFile() {
		return uploadFile;
	}
	public void setUploadFile(List<MultipartFile> uploadFile) {
		this.uploadFile = uploadFile;
	}
	public String getCohortName() {
		return cohortName;
	}
	public void setCohortName(String cohortName) {
		this.cohortName = cohortName;
	}
	public String getClusterName() {
		return clusterName;
	}
	public void setClusterName(String clusterName) {
		this.clusterName = clusterName;
	}
	public String getApplicationStatus() {
		return applicationStatus;
	}
	public void setApplicationStatus(String applicationStatus) {
		this.applicationStatus = applicationStatus;
	}
	public String getUrlText() {
		return urlText;
	}
	public void setUrlText(String urlText) {
		this.urlText = urlText;
	}
	public String getCluserName() {
		return cluserName;
	}
	public void setCluserName(String cluserName) {
		this.cluserName = cluserName;
	}
	public String getCompanyRoleName() {
		return companyRoleName;
	}
	public void setCompanyRoleName(String companyRoleName) {
		this.companyRoleName = companyRoleName;
	}
	public String getCompanyRoleDescription() {
		return companyRoleDescription;
	}
	public void setCompanyRoleDescription(String companyRoleDescription) {
		this.companyRoleDescription = companyRoleDescription;
	}
	public String getJobDescription() {
		return jobDescription;
	}
	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}
	public String getProcessName() {
		return processName;
	}
	public void setProcessName(String processName) {
		this.processName = processName;
	}
	public int getCompanyId() {
		return companyId;
	}
	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getCompanyDescription() {
		return companyDescription;
	}
	public void setCompanyDescription(String companyDescription) {
		this.companyDescription = companyDescription;
	}
	public boolean isIsActive() {
		return IsActive;
	}
	public void setIsActive(boolean isActive) {
		IsActive = isActive;
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
	public int getApplicationId() {
		return applicationId;
	}
	public void setApplicationId(int applicationId) {
		this.applicationId = applicationId;
	}
	public int getProcessId() {
		return processId;
	}
	public void setProcessId(int processId) {
		this.processId = processId;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	
	public String getPrefenceSurvey() {
		return prefenceSurvey;
	}
	public void setPrefenceSurvey(String prefenceSurvey) {
		this.prefenceSurvey = prefenceSurvey;
	}
	public int getJobDescriptionId() {
		return jobDescriptionId;
	}
	public void setJobDescriptionId(int jobDescriptionId) {
		this.jobDescriptionId = jobDescriptionId;
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
	public boolean isCoverLetter() {
		return coverLetter;
	}
	public void setCoverLetter(boolean coverLetter) {
		this.coverLetter = coverLetter;
	}
	public int getUrlid() {
		return urlid;
	}
	public void setUrlid(int urlid) {
		this.urlid = urlid;
	}
	/*public Date getOpningDatetime() {
		return opningDatetime;
	}
	public void setOpningDatetime(Date opningDatetime) {
		this.opningDatetime = opningDatetime;
	}
	public Date getClosingDatetime() {
		return closingDatetime;
	}
	public void setClosingDatetime(Date closingDatetime) {
		this.closingDatetime = closingDatetime;
	}*/
	
	public int getWorkExp() {
		return workExp;
	}
	public String getOpningDatetime() {
		return opningDatetime;
	}
	public void setOpningDatetime(String opningDatetime) {
		this.opningDatetime = opningDatetime;
	}
	public String getClosingDatetime() {
		return closingDatetime;
	}
	public void setClosingDatetime(String closingDatetime) {
		this.closingDatetime = closingDatetime;
	}
	public void setWorkExp(int workExp) {
		this.workExp = workExp;
	}
	public double getCompensation() {
		return compensation;
	}
	public void setCompensation(double compensation) {
		this.compensation = compensation;
	}
	public int getMapInterviewExpId() {
		return mapInterviewExpId;
	}
	public void setMapInterviewExpId(int mapInterviewExpId) {
		this.mapInterviewExpId = mapInterviewExpId;
	}
	public String getAdditionalText() {
		return additionalText;
	}
	public void setAdditionalText(String additionalText) {
		this.additionalText = additionalText;
	}
	public List<RoleCompany> getListCompanyRoles() {
		return listCompanyRoles;
	}
	public void setListCompanyRoles(List<RoleCompany> listCompanyRoles) {
		this.listCompanyRoles = listCompanyRoles;
	}
	public String getShortlist() {
		return shortlist;
	}
	public void setShortlist(String shortlist) {
		this.shortlist = shortlist;
	}
	
	public List<String> getRank() {
		return rank;
	}
	public void setRank(List<String> rank) {
		this.rank = rank;
	}
	
	public List<Integer> getCheckBoxList() {
		return checkBoxList;
	}
	public void setCheckBoxList(List<Integer> checkBoxList) {
		this.checkBoxList = checkBoxList;
	}
	/*public List<String> getJdList() {
		return jdList;
	}
	public void setJdList(List<String> jdList) {
		this.jdList = jdList;
	}
	public List<String> getRoleNameList() {
		return roleNameList;
	}
	public void setRoleNameList(List<String> roleNameList) {
		this.roleNameList = roleNameList;
	}*/
	public String getQueryLink() {
		return queryLink;
	}
	public void setQueryLink(String queryLink) {
		this.queryLink = queryLink;
	}
	/*public List<String> getIntershipExpList() {
		return intershipExpList;
	}
	public void setIntershipExpList(List<String> intershipExpList) {
		this.intershipExpList = intershipExpList;
	}
	public List<String> getInterviewExpList() {
		return interviewExpList;
	}
	public void setInterviewExpList(List<String> interviewExpList) {
		this.interviewExpList = interviewExpList;
	}*/
	
	public List<String> getFilePath() {
		return filePath;
	}
	public List<RoleBaseLink> getRoleBaseLink() {
		return roleBaseLink;
	}
	public void setRoleBaseLink(List<RoleBaseLink> roleBaseLink) {
		this.roleBaseLink = roleBaseLink;
	}
	public void setFilePath(List<String> filePath) {
		this.filePath = filePath;
	}
	public List<String> getTitle() {
		return title;
	}
	public void setTitle(List<String> title) {
		this.title = title;
	}
	public List<String> getRoleCompanyId() {
		return roleCompanyId;
	}
	public void setRoleCompanyId(List<String> roleCompanyId) {
		this.roleCompanyId = roleCompanyId;
	}
	public List<Boolean> getRoleCover() {
		return roleCover;
	}
	public void setRoleCover(List<Boolean> roleCover) {
		this.roleCover = roleCover;
	}
	public List<String> getCompanyRoleId() {
		return companyRoleId;
	}
	public void setCompanyRoleId(List<String> companyRoleId) {
		this.companyRoleId = companyRoleId;
	}
	public List<String> getCvReleted() {
		return cvReleted;
	}
	public void setCvReleted(List<String> cvReleted) {
		this.cvReleted = cvReleted;
	}
	public List<Integer> getApplyId() {
		return applyId;
	}
	public void setApplyId(List<Integer> applyId) {
		this.applyId = applyId;
	}
	public boolean isMultipleCVCheck() {
		return multipleCVCheck;
	}
	public void setMultipleCVCheck(boolean multipleCVCheck) {
		this.multipleCVCheck = multipleCVCheck;
	}
	public String getProcess() {
		return process;
	}
	public void setProcess(String process) {
		this.process = process;
	}

}
