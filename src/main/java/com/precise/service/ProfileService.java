package com.precise.service;

import java.sql.SQLException;
import java.util.List;

import com.precise.model.Label;
import com.precise.model.StudentBean;
import com.precise.model.UserProfile;

public interface ProfileService {
	
	public String submitProfileData(UserProfile userProfile,int userId,int roleId) throws SQLException;
	
	public UserProfile getUserProfileValue(int userId,UserProfile userProfile);
	
	//public byte[] getImageByteData(int pkId);
	
	public List<StudentBean> getStudentListForMentors(int userId);
	
	public byte[] getPhotoUploadData(int pkId);
	
	public byte[] getDaWriteUp(int pkId);
	
	public String getUserName(int userName);
	
	public String getExperienceData(int studentId);
	
	public String deleteSummerInternsgipData(String pkId);
	
	public String deleteOtherInternsShip(String pkId);
	
	public String deleteWorkExperinece(String pkId);
	
	public String deleteCvFile(String pkId);
	
	public List<Label> getMentoreList();
	
	public String getMentoreName(int stuId);
	
	public String getGroupValues(int studentId);
	 
	public String getImageFile(int pkId);

}
