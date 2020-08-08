package com.precise.service;

import java.util.List;
import java.util.Map;

import com.precise.model.Admin;

public interface AdminService {

	public Map<Integer, String> getStudentList();

	public void saveStudent(List<Integer> ids);

	public Map<Integer, String> getPlaceComStudentList();

	public void removeStudent(List<Integer> stuIds);

	public Map<Integer, String> getAllPrefix();

	public Map<Integer, String> getStudentListByPrefix(int prefixId);

	public Map<Integer, String> getPlaceComStudentListByPrefixId(int prefixId);

	public Map<Integer, String> getMentor();

	public void saveMentorStudent(Admin admin);

	public Map<Integer, String> getMentorAllStudent();

	public Map<Integer, String> getMentorStudentList();

	public void removeMentorStudent(List<Integer> stuIds);

	public Map<Integer, String> getMentorStudentListByPrefixId(int prefixId);

	public Map<Integer, String> getMentorStudentListByMentorId(int mentorId);

	public Map<Integer, String> getPgpOneStudentList();

	public void convertPgpOneToTwo(Admin admin);
	
	public void convertPgpTwoToOne(Admin admin) ;

	public Map<Integer, String> getPgpTwoStudentList();

	public void assignExperience(Admin admin);

	public List<Admin> getExperienceDataByPGP(int id);

}
