package com.precise.dao;

import java.util.List;

import com.precise.model.Fine;

public interface ManageFineDAO {

	public List<Fine> getAllFineDetail() ;
	
	public void updateFineStatus(int fineValue,int fineId);
}
