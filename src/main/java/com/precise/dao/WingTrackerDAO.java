package com.precise.dao;

import java.util.List;

import com.precise.model.WingTracker;

public interface WingTrackerDAO {
	List<WingTracker>getAllSchdular();

	void saveRoomAllocation(WingTracker wing);
	void saveNegoAndSchedular(WingTracker wing,int userid);
	List<WingTracker> getWingRoomDetails();

	void updateRoomAllocation(WingTracker wing);

	void deleteroomallocation(WingTracker wing);
}
