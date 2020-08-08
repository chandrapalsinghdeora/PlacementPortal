package com.precise.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.precise.dao.ProfileLockKMDAO;
import com.precise.model.ProfileLockKM;

@Service
public class ProfileLockServiceImpl implements ProfileLockKmService {
	@Autowired
	ProfileLockKMDAO profileLockKMDAO;
	

	@Override
	public List<ProfileLockKM> getAllProfile() {
		// TODO Auto-generated method stub
		return profileLockKMDAO.getAllProfile();
	}
	
	
	

	@Override
	public void updateLockedProfile(ProfileLockKM profileLockKM){
		// TODO Auto-generated method stub
		 profileLockKMDAO.updateLockedProfile(profileLockKM);
	}




	@Override
	public List<ProfileLockKM> getpgpStudent(int groupid) {
		// TODO Auto-generated method stub
		return profileLockKMDAO.getpgpStudent(groupid);
	}




	@Override
	public void unlockDetails(ProfileLockKM profileLockKM) {
		// TODO Auto-generated method stub
		 profileLockKMDAO.unlockDetails(profileLockKM);
	}

}
