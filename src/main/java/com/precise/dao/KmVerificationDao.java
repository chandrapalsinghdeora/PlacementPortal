package com.precise.dao;

import java.util.List;

import com.precise.model.kmVerificationForm;


public interface KmVerificationDao {

	public List<kmVerificationForm> getAllKm();
	public void updateKm(int kmId, int kmValue, int userId);
	public String updateFirmVerfication(kmVerificationForm kmVerification);
}
