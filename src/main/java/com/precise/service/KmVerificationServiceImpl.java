package com.precise.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.precise.dao.KmVerificationDao;
import com.precise.model.kmVerificationForm;

@Service
public class KmVerificationServiceImpl implements KmVerificationService {

	@Autowired
	KmVerificationDao kmverificationdao;
	
	@Override
	public List<kmVerificationForm> getAllKm() {

		return kmverificationdao.getAllKm();
	}

	@Override
	public void updateKm(int kmId, int kmValue, int userId) {
		// TODO Auto-generated method stub
		kmverificationdao.updateKm(kmId, kmValue, userId);
	}
	
	@Override
	public String getClusterAndCohortId(kmVerificationForm kmVerification){
		return kmverificationdao.updateFirmVerfication(kmVerification);
	}

}
