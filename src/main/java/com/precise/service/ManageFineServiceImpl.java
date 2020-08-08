package com.precise.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.precise.dao.FineDAO;
import com.precise.dao.ManageFineDAO;
import com.precise.model.Fine;

@Service("manageFineService")
@Transactional
public class ManageFineServiceImpl implements ManageFineService {

	@Autowired
	private ManageFineDAO manageFinedao;
		
	@Override
	public List<Fine> getAllFineDetail() {
		
		System.out.println("FineServiceImpl.getAllFineDetail");
		return manageFinedao.getAllFineDetail();
	}

	@Override
	public void updateFineStatus(int fineValue,int fineId){
		
		manageFinedao.updateFineStatus(fineValue,fineId);
	}
}
