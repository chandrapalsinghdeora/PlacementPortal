package com.precise.service;

import java.util.List;

import com.precise.model.Fine;
import com.precise.model.Forum;;

public interface FineService {

	public List<Fine> getAllFineDetail(int userid, int roleId );
	
	//public void addfineValues(Fine fine);
	public void fineAdd(Fine fineBean,int userid);
}
