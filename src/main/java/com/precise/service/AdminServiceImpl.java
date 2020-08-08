package com.precise.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.precise.dao.AdminDAO;
import com.precise.model.Admin;

@Service
public class AdminServiceImpl implements AdminService {
	@Autowired
	AdminDAO adminDao;

	@Override
	public Map<Integer, String> getStudentList() {
		System.out.println("AdminServiceImpl.getStudentList()");
		return adminDao.getStudentList();
	}

	@Override
	public void saveStudent(List<Integer> ids) {
		System.out.println("AdminServiceImpl.saveStudent()");

		adminDao.saveStudent(ids);

	}

	@Override
	public Map<Integer, String> getPlaceComStudentList() {
		System.out.println("AdminServiceImpl.getPlaceComStudentList()");
		return adminDao.getPlaceComStudentList();
	}

	@Override
	public void removeStudent(List<Integer> stuIds) {
		adminDao.removeStudent(stuIds);
	}

	@Override
	public Map<Integer, String> getAllPrefix() {
		System.out.println("AdminServiceImpl.getAllPrefix()");
		return adminDao.getAllPrefix();
	}

	@Override
	public Map<Integer, String> getStudentListByPrefix(int prefixId) {
		System.out.println("AdminServiceImpl.getStudentListByPrefix()" + prefixId);
		return adminDao.getStudentListByPrefix(prefixId);
	}

	@Override
	public Map<Integer, String> getPlaceComStudentListByPrefixId(int prefixId) {
		System.out.println("AdminServiceImpl.getPlaceComStudentList()" + prefixId);
		return adminDao.getPlaceComStudentListByPrefixId(prefixId);
	}

	@Override
	public Map<Integer, String> getMentor() {
		System.out.println("AdminServiceImpl.getMentor()");
		return adminDao.getMentor();
	}

	@Override
	public void saveMentorStudent(Admin admin) {
		System.out.println("AdminServiceImpl.saveMentorStudent()");
		adminDao.saveMentorStudent(admin);
	}

	@Override
	public Map<Integer, String> getMentorAllStudent() {
	    System.out.println("AdminServiceImpl.getMentorAllStudent()");
		return adminDao.getMentorAllStudent();
	}

	@Override
	public Map<Integer, String> getMentorStudentList() {
		System.out.println("AdminServiceImpl.getMentorStudentList()");
		return adminDao.getMentorStudentList();
	}

	@Override
	public void removeMentorStudent(List<Integer> stuIds) {
		System.out.println("AdminServiceImpl.removeMentorStudent()"+stuIds);
		adminDao.removeMentorStudent(stuIds);
		
	}

	@Override
	public Map<Integer, String> getMentorStudentListByPrefixId(int prefixId) {
		System.out.println("AdminServiceImpl.getMentorStudentListByPrefixId()" + prefixId);
		return adminDao.getMentorStudentListByPrefixId(prefixId);
	}

	@Override
	public Map<Integer, String> getMentorStudentListByMentorId(int mentorId) {
		System.out.println("AdminServiceImpl.getMentorStudentListByMentorId()" + mentorId);
		return adminDao.getMentorStudentListByMentorId(mentorId);
	}

	@Override
	public Map<Integer, String> getPgpOneStudentList() {
		return adminDao.getPgpOneStudentList();
	}

	@Override
	public void convertPgpOneToTwo(Admin admin) {
		adminDao.convertPgpOneToTwo(admin);
		
	}

	@Override
	public void convertPgpTwoToOne(Admin admin) {
		adminDao.convertPgpTwoToOne(admin);
		
	}

	
	@Override
	public Map<Integer, String> getPgpTwoStudentList() {
		return adminDao.getPgpTwoStudentList();
	}

	@Override
	public void assignExperience(Admin admin) {
		adminDao.assignExperience(admin);
		
	}

	@Override
	public List<Admin> getExperienceDataByPGP(int id) {
		return adminDao.getExperienceDataByPGP(id);
	}
   
	
	
	
	
}
