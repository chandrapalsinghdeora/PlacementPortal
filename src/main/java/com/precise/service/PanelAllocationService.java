package com.precise.service;

import java.util.List;

import org.json.JSONArray;

import com.precise.model.PanelAllocation;
import com.precise.model.WingTracker;

public interface PanelAllocationService {

	List<PanelAllocation> getFirmNameByCid(int cid,int roomid);
	PanelAllocation getRmNameByFirmId(int firmId);
	void savePanelAllocation(PanelAllocation pallocation);
	List<PanelAllocation> getPanelAllotedListByClusterId(int clusterid);
	List<PanelAllocation> getPanelAllotedList();
	PanelAllocation getPanelDeatilsByAppId(int firmId);
	List<PanelAllocation> getClusterNameByPid(int pid);
	List<PanelAllocation> getRoleNameByFirmId(int rid);
	List<PanelAllocation> getThetime(int roleId, int roomId);
	List<PanelAllocation> geAlltime(int roomId, int cid, int rid);
	List<PanelAllocation> getThePaneltime(int roleId, int roomId);
	JSONArray geAllThePanel(int appId);
	List<PanelAllocation> getFirmNameByCidInPanel(int cid);
	List<PanelAllocation> getRoleNameByAppIdInPanel(int appId);
	List<WingTracker> getTheClusterDetails(int clusterId);
	List<PanelAllocation> getRoleInDD(int firmid);

}
