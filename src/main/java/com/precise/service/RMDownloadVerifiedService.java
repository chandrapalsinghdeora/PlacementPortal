package com.precise.service;

import java.util.List;

import com.precise.model.RMDownloadVerifiedList;
import com.precise.model.UserProfile;

public interface RMDownloadVerifiedService {
public List<RMDownloadVerifiedList> getRMVerifiedDetail();
	
	public List<UserProfile> getUserValues(int rollNumber);

}
