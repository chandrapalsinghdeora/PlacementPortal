package com.precise.service;

import java.util.List;

import com.precise.model.Fine;

public interface ManageFineService {

	public List<Fine> getAllFineDetail();
	
	public void updateFineStatus(int fineValue,int fineId);
}
