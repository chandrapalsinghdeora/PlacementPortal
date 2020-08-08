package com.precise.model;

import java.util.Date;

public class OfferProcessor {
private int rollNumber;
private String studentName;
private String firmName;
private String roleName;
private String firmOfferStatus;
private String candidateOfferStatus;
private String candidateLocation;
private String timeToOfferExpiry;
private int candidatePref;
private int firmId;
private int roleid;
private int countpending;

public int getCountpending() {
	return countpending;
}
public void setCountpending(int countpending) {
	this.countpending = countpending;
}
public int getRoleid() {
	return roleid;
}
public void setRoleid(int roleid) {
	this.roleid = roleid;
}
public int getFirmId() {
	return firmId;
}
public void setFirmId(int firmId) {
	this.firmId = firmId;
}
public int getRollNumber() {
	return rollNumber;
}
public void setRollNumber(int rollNumber) {
	this.rollNumber = rollNumber;
}
public String getStudentName() {
	return studentName;
}
public void setStudentName(String studentName) {
	this.studentName = studentName;
}
public String getFirmName() {
	return firmName;
}
public void setFirmName(String firmName) {
	this.firmName = firmName;
}
public String getRoleName() {
	return roleName;
}
public void setRoleName(String roleName) {
	this.roleName = roleName;
}
public String getFirmOfferStatus() {
	return firmOfferStatus;
}
public void setFirmOfferStatus(String firmOfferStatus) {
	this.firmOfferStatus = firmOfferStatus;
}
public String getCandidateOfferStatus() {
	return candidateOfferStatus;
}
public void setCandidateOfferStatus(String candidateOfferStatus) {
	this.candidateOfferStatus = candidateOfferStatus;
}
public String getCandidateLocation() {
	return candidateLocation;
}
public void setCandidateLocation(String candidateLocation) {
	this.candidateLocation = candidateLocation;
}
public String getTimeToOfferExpiry() {
	return timeToOfferExpiry;
}
public void setTimeToOfferExpiry(String timeToOfferExpiry) {
	this.timeToOfferExpiry = timeToOfferExpiry;
}
public int getCandidatePref() {
	return candidatePref;
}
public void setCandidatePref(int candidatePref) {
	this.candidatePref = candidatePref;
}


}
