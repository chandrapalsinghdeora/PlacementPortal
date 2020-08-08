package com.precise.model;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

public class Register {	   
  	
	//PK_UserId	int	Unchecked
	private String userEnrollmentNumber;
	private String loginPassword;
	private String userName;
	private String userNickName;
	private Date dateOfBirth;
	private String guardianName;
	private String userAddress;
	private String userCity;
	private String userState;
	private String userCountry;
	private String userZIPCode;
	private String userPhone;
	private String userEmailId;
	private String userMobileNumber;
	private String userGender;
	private String userPhoto;
	private Boolean isActive=true;
	/*private String createTimeStamp;
	private String updateTimeStamp;*/
	private int createdBy;
	private int updatedBy;
	private String userRoleType;
	

public Register() {
	System.out.println("Register.Register() d.c.");
   }


public String getUserEnrollmentNumber() {
	return userEnrollmentNumber;
}


public void setUserEnrollmentNumber(String userEnrollmentNumber) {
	this.userEnrollmentNumber = userEnrollmentNumber;
}


public String getLoginPassword() {
	return loginPassword;
}


public void setLoginPassword(String loginPassword) {
	this.loginPassword = loginPassword;
}


public String getUserName() {
	return userName;
}


public void setUserName(String userName) {
	this.userName = userName;
}


public String getUserNickName() {
	return userNickName;
}


public void setUserNickName(String userNickName) {
	this.userNickName = userNickName;
}


public String getGuardianName() {
	return guardianName;
}


public void setGuardianName(String guardianName) {
	this.guardianName = guardianName;
}


public String getUserAddress() {
	return userAddress;
}


public void setUserAddress(String userAddress) {
	this.userAddress = userAddress;
}


public String getUserCity() {
	return userCity;
}


public void setUserCity(String userCity) {
	this.userCity = userCity;
}


public String getUserState() {
	return userState;
}


public void setUserState(String userState) {
	this.userState = userState;
}


public String getUserCountry() {
	return userCountry;
}


public void setUserCountry(String userCountry) {
	this.userCountry = userCountry;
}


public String getUserZIPCode() {
	return userZIPCode;
}


public void setUserZIPCode(String userZIPCode) {
	this.userZIPCode = userZIPCode;
}


public String getUserPhone() {
	return userPhone;
}


public void setUserPhone(String userPhone) {
	this.userPhone = userPhone;
}


public String getUserEmailId() {
	return userEmailId;
}


public void setUserEmailId(String userEmailId) {
	this.userEmailId = userEmailId;
}


public String getUserMobileNumber() {
	return userMobileNumber;
}


public void setUserMobileNumber(String userMobileNumber) {
	this.userMobileNumber = userMobileNumber;
}


public String getUserGender() {
	return userGender;
}


public void setUserGender(String userGender) {
	this.userGender = userGender;
}


public String getUserPhoto() {
	return userPhoto;
}


public void setUserPhoto(String userPhoto) {
	this.userPhoto = userPhoto;
}


public Boolean getIsActive() {
	return isActive;
}


public void setIsActive(Boolean isActive) {
	this.isActive = isActive;
}



public Date getDateOfBirth() {
	return dateOfBirth;
}


public void setDateOfBirth(Date dateOfBirth) {
	this.dateOfBirth = dateOfBirth;
}


public int getCreatedBy() {
	return createdBy;
}


public void setCreatedBy(int createdBy) {
	this.createdBy = createdBy;
}


public int getUpdatedBy() {
	return updatedBy;
}


public void setUpdatedBy(int updatedBy) {
	this.updatedBy = updatedBy;
}


public String getUserRoleType() {
	return userRoleType;
}


public void setUserRoleType(String userRoleType) {
	this.userRoleType = userRoleType;
}
   
   
   
}
