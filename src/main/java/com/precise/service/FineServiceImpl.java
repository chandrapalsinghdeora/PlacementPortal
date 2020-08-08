package com.precise.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.precise.dao.FineDAO;
import com.precise.model.Fine;



@Service("fineService")
@Transactional
public class FineServiceImpl implements FineService{


	@Autowired
	private FineDAO finedao;
		
	@Override
	public List<Fine> getAllFineDetail(int userid, int roleId ) {
		System.out.println("FineServiceImpl.getAllFineDetail");
		return finedao.getAllFineDetail(userid, roleId);
	}

	
	
	public void fineAdd(Fine fineBean,int userid){
		finedao.fineAdd(fineBean,userid);
	}
}
