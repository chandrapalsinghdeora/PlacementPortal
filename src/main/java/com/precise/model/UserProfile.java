package com.precise.model;

import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author dilip.v
 *
 */
public class UserProfile {
	
	private String rollNumber;
	private String fullName;
	private String cvName;
	private String mentor;
	private String gender;
	private String dateOfBirth;
	private String domainRoom;
	private String phoneEtn;
	private String mobile;
	private String emailAddress;
	private String alternativeEmailid;
	private String city;
	private String state;
	private String board;
	private String school;
	private String percentage;
	private String cityTwelve;
	private String stateTwelve;
	private String boardTwelve;
	private String schoolTwelve;
	private String percenatageTwelve;
	private String percentageGraduate;
	private String graduteDegree;
	private String department;
	private String institute;
	private String Abbreviation;
	private String location;
	private String cgpa;
	private String cgpaScale;
	private String postgraduateDegree;
	private String postgraduateDepartment;
	private String postgraduateInstitute;
	private String postgraduateAbbreviation;
	private String postgraduateCGPA;
	private String postgraduateCGPAScale;
	private String postgraduatePercentage;
	private List company;
	private List role;
	private List locationSummerInternship;
	private List durationSummerInternship;
	private List otherInternShipCompany;
	private String userId;
	private List<String> cvTitle;
	private List otherInternShipDuration;
	private List otherInternShipLocation;
	private List companyNameWorkExperience;
	private List designationWorkExperience;
	private List durationWorkExperience;
	private List industryWorkExperience;
	private List functionAreaWorkExperience;
	private List totalWorkMonthsWorkExperience;
	private List<MultipartFile> cvFile;
	private List<String> cvFileName;
	private List<String> cvFileId;
	private List<String> summerInternshipPkId;
	private List<String> otherInternshipPkId;
	private List<String> workExperiencePkId;
	private List<String> cvPkId;
	private int userProfilePkId;
	private String profileWriteUp;
	private MultipartFile daWriteUp;
	private String profileLoack;
	MultipartFile photoUpload;
	private List<String> cvFilePathList;
	private String rollNumberLock;
	private String rollNumberApprove;
	private String fullNameLock;
	private String fullNameApprove;
	private String cvNameLock;
	private String cvNameApprove;
	private String mentorLock;
	private String mentorApprove;
	private String genderLock;
	private String genderApprove;
	private String dateOfBirthLock;
	private String dateOfBirthApprove;
	private String domainRoomLocked;
	private String domianRoomApproved;
	private String phoneExtLock;
	private String phoneExtApprove;
	private String mobileLock;
	private String mobileApprove;
	private String emailAddressLock;
	private String emailAddressApprove;
	private String alternativeEmailLock;
	private String alternativeEmailApprove;
	List<String> summerInternshipApprove;
	List<String> summerInternShipLock;
	private String city10thApprove;
	private String city10thLock;
	private String state10thApprove;
	private String state10thLock;
	private String board10thApprove;
	private String board10thLock;
	private String school10thApprove;
	private String school10thLock;
	private String percentage10thApprove;
	private String percentage10thLock;
	private String city12thApprove;
	private String city12thLock;
	private String state12thApprove;
	private String state12thLock;
	private String board12thApprove;
	private String board12thLock;
	private String school12thApprove;
	private String school12thLock;
	private String percentage12thApprove;
	private String percentage12thLock;
	private String graduatePercentageApprove;
	private String graduatePercentageLock;
	private String graduateDregreeApprove;
	private String graduateDregreeLock;
	private String graduateDepartmentApprove;
	private String graduateDepartmentLock;
	private String grdauateInstituteApprove;
	private String graduatedInstituteLock;
	private String grdaduateAbbrivationApprove;
	private String grdaduateAbbrivationLock;
	private String graduateLocationApprove;
	private String graduateLocationLock;
	private String graduateCgpaApprove;
	private String graduateCgpaLock;
	private String graduateCgpaScaleApprove;
	private String graduateCgpaScaleLock;
	private String pgDegreeApprove;
	private String pgDegreeLock;
	private String pgDepartmentApprove;
	private String pgDepartmentLock;
	private String pgInstituteApprove;
	private String pgInstituteLock;
	private String pgAbbrivationApprove;
	private String pgAbbrivationLock;
	private String pgCgpaApprove;
	private String pgCgpaLock;
	private String pgcgpaScaleApprove;
	private String pgcgpaScaleLock;
	private String pgPercentageApprove;
	private String pgPercentageLock;
	private List<String> summerIdHidden;
	private List<String> summerApproveId;
	private List<String> otherInternshipApprove;
	private List<String> otherInternshipLock;
	private List<String> otherHiddenApprove;
	private List<String> otherHiddenLock;
	private List<String> workExperineceApprove;
	private List<String> workExperienceLock;
	private List<String> workHiddenApprove;
	private List<String> workHiddenLock;
	private List<String> cvApprove;
	private List<String> cvLock;
	private List<String> cvApproveHidden;
	private List<String> cvLockHidden;
	private String resmueApprove;
	private String resumeLock;
	private String StudentId;
	private String photoUploadFileName;
	private String photoUploadPkId;
	private String userRoleType;
	private String phototUploadFilePath;
	private String internshipEnable;
	private String interviewEnable;
	private String shareProfile;
	private Integer totalWorkExperience;
	private String adminLock;
	private String daWriteUpFileName;
	private String daWriteUpFilePath;
	private String profileLockValue;
	private String lockProfileByMentor;
	
	
	
	public String getLockProfileByMentor() {
		return lockProfileByMentor;
	}
	public void setLockProfileByMentor(String lockProfileByMentor) {
		this.lockProfileByMentor = lockProfileByMentor;
	}
	public String getProfileLockValue() {
		return profileLockValue;
	}
	public void setProfileLockValue(String profileLockValue) {
		this.profileLockValue = profileLockValue;
	}
	public String getDaWriteUpFileName() {
		return daWriteUpFileName;
	}
	public void setDaWriteUpFileName(String daWriteUpFileName) {
		this.daWriteUpFileName = daWriteUpFileName;
	}
	public String getDaWriteUpFilePath() {
		return daWriteUpFilePath;
	}
	public void setDaWriteUpFilePath(String daWriteUpFilePath) {
		this.daWriteUpFilePath = daWriteUpFilePath;
	}
	public MultipartFile getDaWriteUp() {
		return daWriteUp;
	}
	public void setDaWriteUp(MultipartFile daWriteUp) {
		this.daWriteUp = daWriteUp;
	}
	public String getAdminLock() {
		return adminLock;
	}
	public void setAdminLock(String adminLock) {
		this.adminLock = adminLock;
	}
	public Integer getTotalWorkExperience() {
		return totalWorkExperience;
	}
	public void setTotalWorkExperience(Integer totalWorkExperience) {
		this.totalWorkExperience = totalWorkExperience;
	}
	public String getShareProfile() {
		return shareProfile;
	}
	public void setShareProfile(String shareProfile) {
		this.shareProfile = shareProfile;
	}
	public String getInternshipEnable() {
		return internshipEnable;
	}
	public void setInternshipEnable(String internshipEnable) {
		this.internshipEnable = internshipEnable;
	}
	public String getInterviewEnable() {
		return interviewEnable;
	}
	public void setInterviewEnable(String interviewEnable) {
		this.interviewEnable = interviewEnable;
	}
	public String getPhototUploadFilePath() {
		return phototUploadFilePath;
	}
	public void setPhototUploadFilePath(String phototUploadFilePath) {
		this.phototUploadFilePath = phototUploadFilePath;
	}
	public String getUserRoleType() {
		return userRoleType;
	}
	public void setUserRoleType(String userRoleType) {
		this.userRoleType = userRoleType;
	}
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getPhotoUploadPkId() {
		return photoUploadPkId;
	}
	public void setPhotoUploadPkId(String photoUploadPkId) {
		this.photoUploadPkId = photoUploadPkId;
	}
	public String getPhotoUploadFileName() {
		return photoUploadFileName;
	}
	public void setPhotoUploadFileName(String photoUploadFileName) {
		this.photoUploadFileName = photoUploadFileName;
	}
	public String getStudentId() {
		return StudentId;
	}
	public void setStudentId(String studentId) {
		StudentId = studentId;
	}
	public String getDomainRoomLocked() {
		return domainRoomLocked;
	}
	public void setDomainRoomLocked(String domainRoomLocked) {
		this.domainRoomLocked = domainRoomLocked;
	}
	public String getDomianRoomApproved() {
		return domianRoomApproved;
	}
	public void setDomianRoomApproved(String domianRoomApproved) {
		this.domianRoomApproved = domianRoomApproved;
	}
	public String getResmueApprove() {
		return resmueApprove;
	}
	public void setResmueApprove(String resmueApprove) {
		this.resmueApprove = resmueApprove;
	}
	public String getResumeLock() {
		return resumeLock;
	}
	public void setResumeLock(String resumeLock) {
		this.resumeLock = resumeLock;
	}
	public List<String> getCvApprove() {
		return cvApprove;
	}
	public void setCvApprove(List<String> cvApprove) {
		this.cvApprove = cvApprove;
	}
	public List<String> getCvLock() {
		return cvLock;
	}
	public void setCvLock(List<String> cvLock) {
		this.cvLock = cvLock;
	}
	public List<String> getCvApproveHidden() {
		return cvApproveHidden;
	}
	public void setCvApproveHidden(List<String> cvApproveHidden) {
		this.cvApproveHidden = cvApproveHidden;
	}
	public List<String> getCvLockHidden() {
		return cvLockHidden;
	}
	public void setCvLockHidden(List<String> cvLockHidden) {
		this.cvLockHidden = cvLockHidden;
	}
	public List<String> getWorkExperineceApprove() {
		return workExperineceApprove;
	}
	public void setWorkExperineceApprove(List<String> workExperineceApprove) {
		this.workExperineceApprove = workExperineceApprove;
	}
	public List<String> getWorkExperienceLock() {
		return workExperienceLock;
	}
	public void setWorkExperienceLock(List<String> workExperienceLock) {
		this.workExperienceLock = workExperienceLock;
	}
	public List<String> getWorkHiddenApprove() {
		return workHiddenApprove;
	}
	public void setWorkHiddenApprove(List<String> workHiddenApprove) {
		this.workHiddenApprove = workHiddenApprove;
	}
	public List<String> getWorkHiddenLock() {
		return workHiddenLock;
	}
	public void setWorkHiddenLock(List<String> workHiddenLock) {
		this.workHiddenLock = workHiddenLock;
	}
	public List<String> getOtherInternshipApprove() {
		return otherInternshipApprove;
	}
	public void setOtherInternshipApprove(List<String> otherInternshipApprove) {
		this.otherInternshipApprove = otherInternshipApprove;
	}
	public List<String> getOtherInternshipLock() {
		return otherInternshipLock;
	}
	public void setOtherInternshipLock(List<String> otherInternshipLock) {
		this.otherInternshipLock = otherInternshipLock;
	}
	public List<String> getOtherHiddenApprove() {
		return otherHiddenApprove;
	}
	public void setOtherHiddenApprove(List<String> otherHiddenApprove) {
		this.otherHiddenApprove = otherHiddenApprove;
	}
	public List<String> getOtherHiddenLock() {
		return otherHiddenLock;
	}
	public void setOtherHiddenLock(List<String> otherHiddenLock) {
		this.otherHiddenLock = otherHiddenLock;
	}
	public List<String> getSummerApproveId() {
		return summerApproveId;
	}
	public void setSummerApproveId(List<String> summerApproveId) {
		this.summerApproveId = summerApproveId;
	}
	public List<String> getSummerIdHidden() {
		return summerIdHidden;
	}
	public void setSummerIdHidden(List<String> summerIdHidden) {
		this.summerIdHidden = summerIdHidden;
	}
	public String getPgcgpaScaleApprove() {
		return pgcgpaScaleApprove;
	}
	public void setPgcgpaScaleApprove(String pgcgpaScaleApprove) {
		this.pgcgpaScaleApprove = pgcgpaScaleApprove;
	}
	public String getPgcgpaScaleLock() {
		return pgcgpaScaleLock;
	}
	public void setPgcgpaScaleLock(String pgcgpaScaleLock) {
		this.pgcgpaScaleLock = pgcgpaScaleLock;
	}
	public String getPgDegreeApprove() {
		return pgDegreeApprove;
	}
	public void setPgDegreeApprove(String pgDegreeApprove) {
		this.pgDegreeApprove = pgDegreeApprove;
	}
	public String getPgDegreeLock() {
		return pgDegreeLock;
	}
	public void setPgDegreeLock(String pgDegreeLock) {
		this.pgDegreeLock = pgDegreeLock;
	}
	public String getPgDepartmentApprove() {
		return pgDepartmentApprove;
	}
	public void setPgDepartmentApprove(String pgDepartmentApprove) {
		this.pgDepartmentApprove = pgDepartmentApprove;
	}
	public String getPgDepartmentLock() {
		return pgDepartmentLock;
	}
	public void setPgDepartmentLock(String pgDepartmentLock) {
		this.pgDepartmentLock = pgDepartmentLock;
	}
	public String getPgInstituteApprove() {
		return pgInstituteApprove;
	}
	public void setPgInstituteApprove(String pgInstituteApprove) {
		this.pgInstituteApprove = pgInstituteApprove;
	}
	public String getPgInstituteLock() {
		return pgInstituteLock;
	}
	public void setPgInstituteLock(String pgInstituteLock) {
		this.pgInstituteLock = pgInstituteLock;
	}
	public String getPgAbbrivationApprove() {
		return pgAbbrivationApprove;
	}
	public void setPgAbbrivationApprove(String pgAbbrivationApprove) {
		this.pgAbbrivationApprove = pgAbbrivationApprove;
	}
	public String getPgAbbrivationLock() {
		return pgAbbrivationLock;
	}
	public void setPgAbbrivationLock(String pgAbbrivationLock) {
		this.pgAbbrivationLock = pgAbbrivationLock;
	}
	public String getPgCgpaApprove() {
		return pgCgpaApprove;
	}
	public void setPgCgpaApprove(String pgCgpaApprove) {
		this.pgCgpaApprove = pgCgpaApprove;
	}
	public String getPgCgpaLock() {
		return pgCgpaLock;
	}
	public void setPgCgpaLock(String pgCgpaLock) {
		this.pgCgpaLock = pgCgpaLock;
	}
	public String getPgPercentageApprove() {
		return pgPercentageApprove;
	}
	public void setPgPercentageApprove(String pgPercentageApprove) {
		this.pgPercentageApprove = pgPercentageApprove;
	}
	public String getPgPercentageLock() {
		return pgPercentageLock;
	}
	public void setPgPercentageLock(String pgPercentageLock) {
		this.pgPercentageLock = pgPercentageLock;
	}
	public String getGraduateLocationLock() {
		return graduateLocationLock;
	}
	public void setGraduateLocationLock(String graduateLocationLock) {
		this.graduateLocationLock = graduateLocationLock;
	}
	
	public String getGraduatePercentageApprove() {
		return graduatePercentageApprove;
	}
	public void setGraduatePercentageApprove(String graduatePercentageApprove) {
		this.graduatePercentageApprove = graduatePercentageApprove;
	}
	public String getGraduatePercentageLock() {
		return graduatePercentageLock;
	}
	public void setGraduatePercentageLock(String graduatePercentageLock) {
		this.graduatePercentageLock = graduatePercentageLock;
	}
	public String getGraduateDregreeApprove() {
		return graduateDregreeApprove;
	}
	public void setGraduateDregreeApprove(String graduateDregreeApprove) {
		this.graduateDregreeApprove = graduateDregreeApprove;
	}
	public String getGraduateDregreeLock() {
		return graduateDregreeLock;
	}
	public void setGraduateDregreeLock(String graduateDregreeLock) {
		this.graduateDregreeLock = graduateDregreeLock;
	}
	public String getGraduateDepartmentApprove() {
		return graduateDepartmentApprove;
	}
	public void setGraduateDepartmentApprove(String graduateDepartmentApprove) {
		this.graduateDepartmentApprove = graduateDepartmentApprove;
	}
	public String getGraduateDepartmentLock() {
		return graduateDepartmentLock;
	}
	public void setGraduateDepartmentLock(String graduateDepartmentLock) {
		this.graduateDepartmentLock = graduateDepartmentLock;
	}
	public String getGrdauateInstituteApprove() {
		return grdauateInstituteApprove;
	}
	public void setGrdauateInstituteApprove(String grdauateInstituteApprove) {
		this.grdauateInstituteApprove = grdauateInstituteApprove;
	}
	public String getGraduatedInstituteLock() {
		return graduatedInstituteLock;
	}
	public void setGraduatedInstituteLock(String graduatedInstituteLock) {
		this.graduatedInstituteLock = graduatedInstituteLock;
	}
	public String getGrdaduateAbbrivationApprove() {
		return grdaduateAbbrivationApprove;
	}
	public void setGrdaduateAbbrivationApprove(String grdaduateAbbrivationApprove) {
		this.grdaduateAbbrivationApprove = grdaduateAbbrivationApprove;
	}
	public String getGrdaduateAbbrivationLock() {
		return grdaduateAbbrivationLock;
	}
	public void setGrdaduateAbbrivationLock(String grdaduateAbbrivationLock) {
		this.grdaduateAbbrivationLock = grdaduateAbbrivationLock;
	}
	public String getGraduateLocationApprove() {
		return graduateLocationApprove;
	}
	public void setGraduateLocationApprove(String graduateLocationApprove) {
		this.graduateLocationApprove = graduateLocationApprove;
	}
	public String getGraduateCgpaApprove() {
		return graduateCgpaApprove;
	}
	public void setGraduateCgpaApprove(String graduateCgpaApprove) {
		this.graduateCgpaApprove = graduateCgpaApprove;
	}
	public String getGraduateCgpaLock() {
		return graduateCgpaLock;
	}
	public void setGraduateCgpaLock(String graduateCgpaLock) {
		this.graduateCgpaLock = graduateCgpaLock;
	}
	public String getGraduateCgpaScaleApprove() {
		return graduateCgpaScaleApprove;
	}
	public void setGraduateCgpaScaleApprove(String graduateCgpaScaleApprove) {
		this.graduateCgpaScaleApprove = graduateCgpaScaleApprove;
	}
	public String getGraduateCgpaScaleLock() {
		return graduateCgpaScaleLock;
	}
	public void setGraduateCgpaScaleLock(String graduateCgpaScaleLock) {
		this.graduateCgpaScaleLock = graduateCgpaScaleLock;
	}
	public String getCity12thApprove() {
		return city12thApprove;
	}
	public void setCity12thApprove(String city12thApprove) {
		this.city12thApprove = city12thApprove;
	}
	public String getCity12thLock() {
		return city12thLock;
	}
	public void setCity12thLock(String city12thLock) {
		this.city12thLock = city12thLock;
	}
	public String getState12thApprove() {
		return state12thApprove;
	}
	public void setState12thApprove(String state12thApprove) {
		this.state12thApprove = state12thApprove;
	}
	public String getState12thLock() {
		return state12thLock;
	}
	public void setState12thLock(String state12thLock) {
		this.state12thLock = state12thLock;
	}
	public String getBoard12thApprove() {
		return board12thApprove;
	}
	public void setBoard12thApprove(String board12thApprove) {
		this.board12thApprove = board12thApprove;
	}
	public String getBoard12thLock() {
		return board12thLock;
	}
	public void setBoard12thLock(String board12thLock) {
		this.board12thLock = board12thLock;
	}
	public String getSchool12thApprove() {
		return school12thApprove;
	}
	public void setSchool12thApprove(String school12thApprove) {
		this.school12thApprove = school12thApprove;
	}
	public String getSchool12thLock() {
		return school12thLock;
	}
	public void setSchool12thLock(String school12thLock) {
		this.school12thLock = school12thLock;
	}
	public String getPercentage12thApprove() {
		return percentage12thApprove;
	}
	public void setPercentage12thApprove(String percentage12thApprove) {
		this.percentage12thApprove = percentage12thApprove;
	}
	public String getPercentage12thLock() {
		return percentage12thLock;
	}
	public void setPercentage12thLock(String percentage12thLock) {
		this.percentage12thLock = percentage12thLock;
	}
	public String getCity10thApprove() {
		return city10thApprove;
	}
	public void setCity10thApprove(String city10thApprove) {
		this.city10thApprove = city10thApprove;
	}
	public String getCity10thLock() {
		return city10thLock;
	}
	public void setCity10thLock(String city10thLock) {
		this.city10thLock = city10thLock;
	}
	public String getState10thApprove() {
		return state10thApprove;
	}
	public void setState10thApprove(String state10thApprove) {
		this.state10thApprove = state10thApprove;
	}
	public String getState10thLock() {
		return state10thLock;
	}
	public void setState10thLock(String state10thLock) {
		this.state10thLock = state10thLock;
	}
	public String getBoard10thApprove() {
		return board10thApprove;
	}
	public void setBoard10thApprove(String board10thApprove) {
		this.board10thApprove = board10thApprove;
	}
	public String getBoard10thLock() {
		return board10thLock;
	}
	public void setBoard10thLock(String board10thLock) {
		this.board10thLock = board10thLock;
	}
	public String getSchool10thApprove() {
		return school10thApprove;
	}
	public void setSchool10thApprove(String school10thApprove) {
		this.school10thApprove = school10thApprove;
	}
	public String getSchool10thLock() {
		return school10thLock;
	}
	public void setSchool10thLock(String school10thLock) {
		this.school10thLock = school10thLock;
	}
	public String getPercentage10thApprove() {
		return percentage10thApprove;
	}
	public void setPercentage10thApprove(String percentage10thApprove) {
		this.percentage10thApprove = percentage10thApprove;
	}
	public String getPercentage10thLock() {
		return percentage10thLock;
	}
	public void setPercentage10thLock(String percentage10thLock) {
		this.percentage10thLock = percentage10thLock;
	}
	public List<String> getSummerInternshipApprove() {
		return summerInternshipApprove;
	}
	public void setSummerInternshipApprove(List<String> summerInternshipApprove) {
		this.summerInternshipApprove = summerInternshipApprove;
	}
	public List<String> getSummerInternShipLock() {
		return summerInternShipLock;
	}
	public void setSummerInternShipLock(List<String> summerInternShipLock) {
		this.summerInternShipLock = summerInternShipLock;
	}
	public String getEmailAddressLock() {
		return emailAddressLock;
	}
	public void setEmailAddressLock(String emailAddressLock) {
		this.emailAddressLock = emailAddressLock;
	}
	public String getEmailAddressApprove() {
		return emailAddressApprove;
	}
	public void setEmailAddressApprove(String emailAddressApprove) {
		this.emailAddressApprove = emailAddressApprove;
	}
	
	public String getFullNameLock() {
		return fullNameLock;
	}
	public void setFullNameLock(String fullNameLock) {
		this.fullNameLock = fullNameLock;
	}
	public String getFullNameApprove() {
		return fullNameApprove;
	}
	public void setFullNameApprove(String fullNameApprove) {
		this.fullNameApprove = fullNameApprove;
	}
	public String getCvNameLock() {
		return cvNameLock;
	}
	public void setCvNameLock(String cvNameLock) {
		this.cvNameLock = cvNameLock;
	}
	public String getCvNameApprove() {
		return cvNameApprove;
	}
	public void setCvNameApprove(String cvNameApprove) {
		this.cvNameApprove = cvNameApprove;
	}
	public String getMentorLock() {
		return mentorLock;
	}
	public void setMentorLock(String mentorLock) {
		this.mentorLock = mentorLock;
	}
	public String getMentorApprove() {
		return mentorApprove;
	}
	public void setMentorApprove(String mentorApprove) {
		this.mentorApprove = mentorApprove;
	}
	public String getGenderLock() {
		return genderLock;
	}
	public void setGenderLock(String genderLock) {
		this.genderLock = genderLock;
	}
	public String getGenderApprove() {
		return genderApprove;
	}
	public void setGenderApprove(String genderApprove) {
		this.genderApprove = genderApprove;
	}
	public String getDateOfBirthLock() {
		return dateOfBirthLock;
	}
	public void setDateOfBirthLock(String dateOfBirthLock) {
		this.dateOfBirthLock = dateOfBirthLock;
	}
	public String getDateOfBirthApprove() {
		return dateOfBirthApprove;
	}
	public void setDateOfBirthApprove(String dateOfBirthApprove) {
		this.dateOfBirthApprove = dateOfBirthApprove;
	}
	public String getPhoneExtLock() {
		return phoneExtLock;
	}
	public void setPhoneExtLock(String phoneExtLock) {
		this.phoneExtLock = phoneExtLock;
	}
	public String getPhoneExtApprove() {
		return phoneExtApprove;
	}
	public void setPhoneExtApprove(String phoneExtApprove) {
		this.phoneExtApprove = phoneExtApprove;
	}
	public String getMobileLock() {
		return mobileLock;
	}
	public void setMobileLock(String mobileLock) {
		this.mobileLock = mobileLock;
	}
	public String getMobileApprove() {
		return mobileApprove;
	}
	public void setMobileApprove(String mobileApprove) {
		this.mobileApprove = mobileApprove;
	}
	public String getAlternativeEmailLock() {
		return alternativeEmailLock;
	}
	public void setAlternativeEmailLock(String alternativeEmailLock) {
		this.alternativeEmailLock = alternativeEmailLock;
	}
	public String getAlternativeEmailApprove() {
		return alternativeEmailApprove;
	}
	public void setAlternativeEmailApprove(String alternativeEmailApprove) {
		this.alternativeEmailApprove = alternativeEmailApprove;
	}
	public List<String> getCvFilePathList() {
		return cvFilePathList;
	}
	public String getRollNumberLock() {
		return rollNumberLock;
	}
	public void setRollNumberLock(String rollNumberLock) {
		this.rollNumberLock = rollNumberLock;
	}
	public String getRollNumberApprove() {
		return rollNumberApprove;
	}
	public void setRollNumberApprove(String rollNumberApprove) {
		this.rollNumberApprove = rollNumberApprove;
	}
	public void setCvFilePathList(List<String> cvFilePathList) {
		this.cvFilePathList = cvFilePathList;
	}
	public List<String> getCvTitle() {
		return cvTitle;
	}
	public void setCvTitle(List<String> cvTitle) {
		this.cvTitle = cvTitle;
	}
	public MultipartFile getPhotoUpload() {
		return photoUpload;
	}
	public void setPhotoUpload(MultipartFile photoUpload) {
		this.photoUpload = photoUpload;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public List<String> getCvFileId() {
		return cvFileId;
	}
	public void setCvFileId(List<String> cvFileId) {
		this.cvFileId = cvFileId;
	}
	public List<String> getOtherInternshipPkId() {
		return otherInternshipPkId;
	}
	public int getUserProfilePkId() {
		return userProfilePkId;
	}
	public void setUserProfilePkId(int userProfilePkId) {
		this.userProfilePkId = userProfilePkId;
	}
	public void setOtherInternshipPkId(List<String> otherInternshipPkId) {
		this.otherInternshipPkId = otherInternshipPkId;
	}
	public List<String> getWorkExperiencePkId() {
		return workExperiencePkId;
	}
	public void setWorkExperiencePkId(List<String> workExperiencePkId) {
		this.workExperiencePkId = workExperiencePkId;
	}
	public List<String> getCvPkId() {
		return cvPkId;
	}
	public void setCvPkId(List<String> cvPkId) {
		this.cvPkId = cvPkId;
	}
	public List<String> getSummerInternshipPkId() {
		return summerInternshipPkId;
	}
	public void setSummerInternshipPkId(List<String> summerInternshipPkId) {
		this.summerInternshipPkId = summerInternshipPkId;
	}
	public List getCvFileName() {
		return cvFileName;
	}
	public void setCvFileName(List cvFileName) {
		this.cvFileName = cvFileName;
	}
	
	public List getDesignationWorkExperience() {
		return designationWorkExperience;
	}
	public void setDesignationWorkExperience(List designationWorkExperience) {
		this.designationWorkExperience = designationWorkExperience;
	}
	public String getRollNumber() {
		return rollNumber;
	}
	public void setRollNumber(String rollNumber) {
		this.rollNumber = rollNumber;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getCvName() {
		return cvName;
	}
	public void setCvName(String cvName) {
		this.cvName = cvName;
	}
	public String getMentor() {
		return mentor;
	}
	public void setMentor(String mentor) {
		this.mentor = mentor;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getDomainRoom() {
		return domainRoom;
	}
	public void setDomainRoom(String domainRoom) {
		this.domainRoom = domainRoom;
	}
	public String getPhoneEtn() {
		return phoneEtn;
	}
	public void setPhoneEtn(String phoneEtn) {
		this.phoneEtn = phoneEtn;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public String getAlternativeEmailid() {
		return alternativeEmailid;
	}
	public void setAlternativeEmailid(String alternativeEmailid) {
		this.alternativeEmailid = alternativeEmailid;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public List getDurationSummerInternship() {
		return durationSummerInternship;
	}
	public void setDurationSummerInternship(List durationSummerInternship) {
		this.durationSummerInternship = durationSummerInternship;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getBoard() {
		return board;
	}
	public void setBoard(String board) {
		this.board = board;
	}
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	public String getPercentage() {
		return percentage;
	}
	public void setPercentage(String percentage) {
		this.percentage = percentage;
	}
	public String getCityTwelve() {
		return cityTwelve;
	}
	public void setCityTwelve(String cityTwelve) {
		this.cityTwelve = cityTwelve;
	}
	public String getStateTwelve() {
		return stateTwelve;
	}
	public void setStateTwelve(String stateTwelve) {
		this.stateTwelve = stateTwelve;
	}
	public String getBoardTwelve() {
		return boardTwelve;
	}
	public void setBoardTwelve(String boardTwelve) {
		this.boardTwelve = boardTwelve;
	}
	public String getSchoolTwelve() {
		return schoolTwelve;
	}
	public void setSchoolTwelve(String schoolTwelve) {
		this.schoolTwelve = schoolTwelve;
	}
	public String getPercenatageTwelve() {
		return percenatageTwelve;
	}
	public void setPercenatageTwelve(String percenatageTwelve) {
		this.percenatageTwelve = percenatageTwelve;
	}
	public String getPercentageGraduate() {
		return percentageGraduate;
	}
	public void setPercentageGraduate(String percentageGraduate) {
		this.percentageGraduate = percentageGraduate;
	}
	public String getGraduteDegree() {
		return graduteDegree;
	}
	public void setGraduteDegree(String graduteDegree) {
		this.graduteDegree = graduteDegree;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getInstitute() {
		return institute;
	}
	public void setInstitute(String institute) {
		this.institute = institute;
	}
	public String getAbbreviation() {
		return Abbreviation;
	}
	public void setAbbreviation(String abbreviation) {
		Abbreviation = abbreviation;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getCgpa() {
		return cgpa;
	}
	public void setCgpa(String cgpa) {
		this.cgpa = cgpa;
	}
	public String getCgpaScale() {
		return cgpaScale;
	}
	public void setCgpaScale(String cgpaScale) {
		this.cgpaScale = cgpaScale;
	}
	public String getPostgraduateDegree() {
		return postgraduateDegree;
	}
	public void setPostgraduateDegree(String postgraduateDegree) {
		this.postgraduateDegree = postgraduateDegree;
	}
	public String getPostgraduateDepartment() {
		return postgraduateDepartment;
	}
	public void setPostgraduateDepartment(String postgraduateDepartment) {
		this.postgraduateDepartment = postgraduateDepartment;
	}
	public String getPostgraduateInstitute() {
		return postgraduateInstitute;
	}
	public void setPostgraduateInstitute(String postgraduateInstitute) {
		this.postgraduateInstitute = postgraduateInstitute;
	}
	public String getPostgraduateAbbreviation() {
		return postgraduateAbbreviation;
	}
	public void setPostgraduateAbbreviation(String postgraduateAbbreviation) {
		this.postgraduateAbbreviation = postgraduateAbbreviation;
	}
	public String getPostgraduateCGPA() {
		return postgraduateCGPA;
	}
	public void setPostgraduateCGPA(String postgraduateCGPA) {
		this.postgraduateCGPA = postgraduateCGPA;
	}
	public String getPostgraduateCGPAScale() {
		return postgraduateCGPAScale;
	}
	public void setPostgraduateCGPAScale(String postgraduateCGPAScale) {
		this.postgraduateCGPAScale = postgraduateCGPAScale;
	}
	public String getPostgraduatePercentage() {
		return postgraduatePercentage;
	}
	public void setPostgraduatePercentage(String postgraduatePercentage) {
		this.postgraduatePercentage = postgraduatePercentage;
	}
	public List getCompany() {
		return company;
	}
	public void setCompany(List company) {
		this.company = company;
	}
	public List getRole() {
		return role;
	}
	public void setRole(List role) {
		this.role = role;
	}
	public List getLocationSummerInternship() {
		return locationSummerInternship;
	}
	public void setLocationSummerInternship(List locationSummerInternship) {
		this.locationSummerInternship = locationSummerInternship;
	}
	
	public List getOtherInternShipCompany() {
		return otherInternShipCompany;
	}
	public void setOtherInternShipCompany(List otherInternShipCompany) {
		this.otherInternShipCompany = otherInternShipCompany;
	}
	public List getOtherInternShipDuration() {
		return otherInternShipDuration;
	}
	public void setOtherInternShipDuration(List otherInternShipDuration) {
		this.otherInternShipDuration = otherInternShipDuration;
	}
	public List getOtherInternShipLocation() {
		return otherInternShipLocation;
	}
	public void setOtherInternShipLocation(List otherInternShipLocation) {
		this.otherInternShipLocation = otherInternShipLocation;
	}
	public List getCompanyNameWorkExperience() {
		return companyNameWorkExperience;
	}
	public void setCompanyNameWorkExperience(List companyNameWorkExperience) {
		this.companyNameWorkExperience = companyNameWorkExperience;
	}
	public List getDurationWorkExperience() {
		return durationWorkExperience;
	}
	public void setDurationWorkExperience(List durationWorkExperience) {
		this.durationWorkExperience = durationWorkExperience;
	}
	public List getIndustryWorkExperience() {
		return industryWorkExperience;
	}
	public void setIndustryWorkExperience(List industryWorkExperience) {
		this.industryWorkExperience = industryWorkExperience;
	}
	public List getFunctionAreaWorkExperience() {
		return functionAreaWorkExperience;
	}
	public void setFunctionAreaWorkExperience(List functionAreaWorkExperience) {
		this.functionAreaWorkExperience = functionAreaWorkExperience;
	}
	public List getTotalWorkMonthsWorkExperience() {
		return totalWorkMonthsWorkExperience;
	}
	public void setTotalWorkMonthsWorkExperience(List totalWorkMonthsWorkExperience) {
		this.totalWorkMonthsWorkExperience = totalWorkMonthsWorkExperience;
	}

	public List<MultipartFile> getCvFile() {
		return cvFile;
	}
	public void setCvFile(List<MultipartFile> cvFile) {
		this.cvFile = cvFile;
	}
	public String getProfileWriteUp() {
		return profileWriteUp;
	}
	public void setProfileWriteUp(String profileWriteUp) {
		this.profileWriteUp = profileWriteUp;
	}
	/*public String getDaWriteUp() {
		return daWriteUp;
	}
	public void setDaWriteUp(String daWriteUp) {
		this.daWriteUp = daWriteUp;
	}*/
	public String getProfileLoack() {
		return profileLoack;
	}
	public void setProfileLoack(String profileLoack) {
		this.profileLoack = profileLoack;
	}
	
}
