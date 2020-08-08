package com.precise.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.precise.dao.ReplicateApplicationDao;
import com.precise.model.ReplicateApplication;
import com.precise.model.UpdateApplication;
@Service
public class ReplicateApplicationServiceImpl implements ReplicateApplicationService{
@Autowired
ReplicateApplicationDao radao;

	@Override
	public List<ReplicateApplication> getAllDetails() {
		return radao.getAllDetails();
	
	}

	@Override
	public int countsl(int applyid) {
		return radao.countsl(applyid);
	}


	@Override
	public String checksldata(String roleid) {
		return radao.checksldata(roleid);
	}

	@Override
	public String checkcldata(String roleid) {
		return radao.checkcldata(roleid);	
		}

	@Override
	public void applylist(List<ReplicateApplication> destinationList, ReplicateApplication rA) {
		radao.applylist(destinationList,rA);	
	}

	@Override
	public void cllist(List<ReplicateApplication> destinationList, ReplicateApplication rA,int x) {
		radao.cllist(destinationList,rA,x);	
	}

	@Override
	public void sllist(List<ReplicateApplication> destinationList, ReplicateApplication rA) {
		radao.sllist(destinationList,rA);	
	}

	@Override
	public void hllist(List<ReplicateApplication> destinationList, ReplicateApplication rA) {
		radao.hllist(destinationList,rA);	
	}

	@Override
	public String checkhldata(String roleid, ReplicateApplication rA) {
		return radao.checkhldata(roleid,rA);
	}

	@Override
	public List<UpdateApplication> getAllList() {
		return radao.getAllList();
	}

	@Override
	public void updatelistsend(UpdateApplication uA) {
		radao.updatelistsend(uA);
	}

}
