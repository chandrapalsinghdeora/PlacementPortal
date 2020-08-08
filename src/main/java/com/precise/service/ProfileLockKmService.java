package com.precise.service;

import java.util.List;

import com.precise.model.ProfileLockKM;

public interface ProfileLockKmService {
	public List<ProfileLockKM> getAllProfile();
	public void updateLockedProfile(ProfileLockKM profileLockKM);
	public List<ProfileLockKM> getpgpStudent(int groupid);
	public void unlockDetails(ProfileLockKM profileLockKM);

}
