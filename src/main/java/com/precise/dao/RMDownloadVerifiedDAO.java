package com.precise.dao;

import java.util.List;
import com.precise.model.RMDownloadVerifiedList;
import com.precise.model.UserProfile;

public interface RMDownloadVerifiedDAO {
public List<RMDownloadVerifiedList> getRMVerifiedDetail();
	
	public List<UserProfile> getUserValues(int rollNumber);

}
