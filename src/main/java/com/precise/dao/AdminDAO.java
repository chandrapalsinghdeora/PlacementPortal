package com.precise.dao;

import java.util.List;
import java.util.Map;

import com.precise.model.Admin;

public interface AdminDAO {

	Map<Integer, String> getStudentList();

	void saveStudent(List<Integer> ids);

	Map<Integer, String> getPlaceComStudentList();

	void removeStudent(List<Integer> stuIds);

	Map<Integer, String> getAllPrefix();

	Map<Integer, String> getStudentListByPrefix(int prefixId);

	Map<Integer, String> getPlaceComStudentListByPrefixId(int prefixId);

	Map<Integer, String> getMentor();

	void saveMentorStudent(Admin admin);

	Map<Integer, String> getMentorAllStudent();

	Map<Integer, String> getMentorStudentList();

	void removeMentorStudent(List<Integer> stuIds);

	Map<Integer, String> getMentorStudentListByPrefixId(int prefixId);

	Map<Integer, String> getMentorStudentListByMentorId(int mentorId);

	Map<Integer, String> getPgpOneStudentList();

	void convertPgpOneToTwo(Admin admin);
	
	public void convertPgpTwoToOne(Admin admin) ;

	Map<Integer, String> getPgpTwoStudentList();

	void assignExperience(Admin admin);

	List<Admin> getExperienceDataByPGP(int id);

}
