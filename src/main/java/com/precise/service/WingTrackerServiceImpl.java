package com.precise.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.precise.dao.WingTrackerDAO;
import com.precise.model.WingTracker;
@Service
public class WingTrackerServiceImpl implements WingTrackerService{
@Autowired
WingTrackerDAO wtdao;
	@Override
	public List<WingTracker> getAllSchdular() {
		
		return wtdao.getAllSchdular();
	}
	@Override
	public void saveRoomAllocation(WingTracker wing) {
		wtdao.saveRoomAllocation(wing);
		
	}
	@Override
	public List<WingTracker> getWingRoomDetails() {
		return wtdao.getWingRoomDetails();
	}
	@Override
	public void saveNegoAndSchedular(WingTracker wing,int userid) {
		wtdao.saveNegoAndSchedular(wing,userid);
	}
	@Override
	public void updateRoomAllocation(WingTracker wing) {
		wtdao.updateRoomAllocation(wing);
	}
	@Override
	public void deleteroomallocation(WingTracker wing) {
		wtdao.deleteroomallocation(wing);
	}

}
