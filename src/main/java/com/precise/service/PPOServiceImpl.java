package com.precise.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.precise.dao.PPODao;

@Service
public class PPOServiceImpl implements PPOService {
	@Autowired
	PPODao ppoDao;

	@Override
	public void insertIntoPPO(Object[] a, int userid) {
		ppoDao.insertIntoPPO(a,userid);
	}

}
