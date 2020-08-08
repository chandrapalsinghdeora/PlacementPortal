package com.precise.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.precise.dao.RMDownloadVerifiedDAO;
import com.precise.model.RMDownloadVerifiedList;
import com.precise.model.UserProfile;


@Service
@Transactional
public class RMDownloadVerifiedServiceImpl implements RMDownloadVerifiedService {
	
	@Autowired
	private RMDownloadVerifiedDAO rmDownloadVerifiedDAO;
		
	@Override
	public List<RMDownloadVerifiedList> getRMVerifiedDetail() {
		//System.out.println("CloseStatusServiceImpl.getCloseStatusDetail");
		return rmDownloadVerifiedDAO.getRMVerifiedDetail();
	}
	
	@Override
	public List<UserProfile> getUserValues(int rollNumber){
		
		return rmDownloadVerifiedDAO.getUserValues(rollNumber);
	}

}
