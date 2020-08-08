package com.precise.model;

import java.util.List;

public class ShareProfile {
	public int userId;
	public int studentId;
	public String rollNumber;
	public String studentName;
	public int totalWorkExp;
	public int totalSummnerExp;
	public int totalOtherInternshipExp;
	public List<String> cvNameList;
	public List<String> cvTitleList;
	public String graduateDegree;
	private List<Integer> cvPkId;
	public String wpCompany;
	public String internCompany;
	
	public List<Integer> getCvPkId() {
		return cvPkId;
	}
	public void setCvPkId(List<Integer> cvPkId) {
		this.cvPkId = cvPkId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getStudentId() {
		return studentId;
	}
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	public String getRollNumber() {
		return rollNumber;
	}
	public void setRollNumber(String rollNumber) {
		this.rollNumber = rollNumber;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public int getTotalWorkExp() {
		return totalWorkExp;
	}
	public void setTotalWorkExp(int totalWorkExp) {
		this.totalWorkExp = totalWorkExp;
	}
	public int getTotalSummnerExp() {
		return totalSummnerExp;
	}
	public void setTotalSummnerExp(int totalSummnerExp) {
		this.totalSummnerExp = totalSummnerExp;
	}
	public int getTotalOtherInternshipExp() {
		return totalOtherInternshipExp;
	}
	public void setTotalOtherInternshipExp(int totalOtherInternshipExp) {
		this.totalOtherInternshipExp = totalOtherInternshipExp;
	}
	public List<String> getCvNameList() {
		return cvNameList;
	}
	public void setCvNameList(List<String> cvNameList) {
		this.cvNameList = cvNameList;
	}
	public List<String> getCvTitleList() {
		return cvTitleList;
	}
	public void setCvTitleList(List<String> cvTitleList) {
		this.cvTitleList = cvTitleList;
	}
	public String getGraduateDegree() {
		return graduateDegree;
	}
	public void setGraduateDegree(String graduateDegree) {
		this.graduateDegree = graduateDegree;
	}
	public String getWpCompany() {
		return wpCompany;
	}
	public void setWpCompany(String wpCompany) {
		this.wpCompany = wpCompany;
	}
	public String getInternCompany() {
		return internCompany;
	}
	public void setInternCompany(String internCompany) {
		this.internCompany = internCompany;
	}
	
	
}
