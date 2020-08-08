package com.precise.dao;

import java.util.List;

import com.precise.model.ReplicateApplication;
import com.precise.model.UpdateApplication;

public interface ReplicateApplicationDao {

	List<ReplicateApplication> getAllDetails();

	int countsl(int applyid);

	String checkhldata(String roleid, ReplicateApplication rA);
	String checkcldata(String roleid);
	String checksldata(String roleid);
	void applylist(List<ReplicateApplication> destinationList, ReplicateApplication rA);
	void cllist(List<ReplicateApplication> destinationList, ReplicateApplication rA, int x);
	void sllist(List<ReplicateApplication> destinationList, ReplicateApplication rA);
	void hllist(List<ReplicateApplication> destinationList, ReplicateApplication rA);
	
	List<UpdateApplication> getAllList();

	void updatelistsend(UpdateApplication uA);

}
