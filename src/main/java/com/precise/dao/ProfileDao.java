package com.precise.dao;

import java.sql.SQLException;
import java.util.List;

import com.precise.model.Label;
import com.precise.model.StudentBean;
import com.precise.model.UserProfile;

public interface ProfileDao {
	
	public String submitUserData(UserProfile userProfile,int userId,int roleId) throws SQLException;
	
	public UserProfile getUsersDetails(int userId,UserProfile userProfile);
	
	//public byte[] getByteData(int pkId);
	
	public List<StudentBean> getStudentList(int userId);
	
	byte[] getPhotoUploadData(int pkId);
	
	public String getUserName(int userId);
	
	public String getExperineceValues(int userId);
	
	public String deleteSummerInternship(String pkId);
	
	public String deleteOtherInternshipData(String pkId);
	
	public String deleteWorkExperience(String pkId);
	
	public String deleteCvFile(String pkId);
	
	public List<Label> getMenoreList();
	
	public String getMentoreName(int stuId);
	
	public String getGrupValues(int studentId);
	
	public byte[] getDAWriteUp(int pkId);

	public String getImageFile(int pkId);

}
