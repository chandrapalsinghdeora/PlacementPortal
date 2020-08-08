package com.precise.dao;

import java.sql.SQLException;
import java.util.List;

import com.precise.model.ProfileLockKM;

public interface ProfileLockKMDAO {
	public List<ProfileLockKM> getAllProfile();
	public void updateLockedProfile(ProfileLockKM profileLockKM);
	public List<ProfileLockKM> getpgpStudent(int groupid);
	public void unlockDetails(ProfileLockKM profileLockKM);
}
