package com.precise.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.precise.dao.ShareProfileDAO;
import com.precise.model.ShareProfile;

@Service("shareProfileservice")
@Transactional
public class ShareProfileServiceImpl implements ShareProfileService{

	@Autowired
	ShareProfileDAO shareProfileDAO;
	
	@Override
	public List<ShareProfile> getAllShareProfile() {
		return shareProfileDAO.getAllShareProfile();
	}

}
