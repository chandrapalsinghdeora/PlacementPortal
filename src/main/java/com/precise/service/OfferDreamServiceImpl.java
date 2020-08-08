package com.precise.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.precise.dao.OfferDreamDao;

@Service
public class OfferDreamServiceImpl implements OfferDreamService{
 @Autowired
 OfferDreamDao odDao;
	@Override
	public void insertIntoOffer(Object[] a,int userid) {
		odDao.insertIntoOffer(a,userid);
	}
	@Override
	public void insertIntoUpdateApplication(Object[] a, int userid) {
		odDao.insertIntoUpdateApplication(a,userid);
	}

}
