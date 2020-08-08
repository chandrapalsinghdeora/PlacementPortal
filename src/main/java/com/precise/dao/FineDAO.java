package com.precise.dao;

import java.util.List;

import com.precise.model.Fine;


public interface FineDAO {

	public List<Fine> getAllFineDetail(int userid, int roleId );
	
	public void fineAdd(Fine fineBean,int userid);
}
