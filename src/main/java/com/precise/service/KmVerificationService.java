package com.precise.service;

import java.util.List;

import com.precise.model.kmVerificationForm;

public interface KmVerificationService {

	public List<kmVerificationForm> getAllKm();
	public void updateKm(int kmId, int kmValue, int userId);
	public String getClusterAndCohortId(kmVerificationForm kmVerification);

}
