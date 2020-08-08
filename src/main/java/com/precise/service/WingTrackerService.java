package com.precise.service;

import java.util.List;

import com.precise.model.WingTracker;

public interface WingTrackerService {
	List<WingTracker> getAllSchdular();

	void saveRoomAllocation(WingTracker wing);
	void saveNegoAndSchedular(WingTracker wing,int userid);
	List<WingTracker> getWingRoomDetails();

	void updateRoomAllocation(WingTracker wing);

	void deleteroomallocation(WingTracker wing);

}
