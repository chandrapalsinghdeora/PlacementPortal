package com.precise.service;

import java.util.List;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.precise.dao.PanelAllocationDAO;
import com.precise.model.PanelAllocation;
import com.precise.model.WingTracker;
@Service("panelService")
public class PanelAllocationServiceImpl implements PanelAllocationService{
    
	@Autowired
	PanelAllocationDAO panelDao;
	@Override
	public List<PanelAllocation> getFirmNameByCid(int cid,int roomid) {
		
		return panelDao.getFirmNameByCid(cid,roomid);
	}
	@Override
	public PanelAllocation getRmNameByFirmId(int firmId) {
		return panelDao.getRmNameByFirmId(firmId);
	}
	@Override
	public void savePanelAllocation(PanelAllocation pallocation) {
		panelDao.savePanelAllocation(pallocation);
	}
	@Override
	public List<PanelAllocation> getPanelAllotedListByClusterId(int clusterid) {
		
		return panelDao.getPanelAllotedListByClusterId(clusterid);
	}
	@Override
	public List<PanelAllocation> getPanelAllotedList() {
		return panelDao.getPanelAllotedList();
	}
	@Override
	public PanelAllocation getPanelDeatilsByAppId(int firmId) {
		return panelDao.getPanelDeatilsByAppId(firmId);
	}
	@Override
	public List<PanelAllocation> getClusterNameByPid(int pid) {
		// TODO Auto-generated method stub
		return panelDao.getClusterNameByPid(pid);
	}
	@Override
	public List<PanelAllocation> getRoleNameByFirmId(int rid) {
		
		return panelDao.getRoleNameByFirmId(rid);
	}
	@Override
	public List<PanelAllocation> getThetime(int roleId, int roomId) {
		return panelDao.getThetime(roleId,roomId);
	}
	@Override
	public List<PanelAllocation> geAlltime(int roomId,int cid,int rid) {
		return panelDao.geAlltime(roomId,cid,rid);
	}
	@Override
	public List<PanelAllocation> getThePaneltime(int roleId, int roomId) {
		return panelDao.getThePaneltime(roleId,roomId);
	}
	@Override
	public JSONArray geAllThePanel(int appId) {
		return panelDao.geAllThePanel(appId);
	}
	@Override
	public List<PanelAllocation> getFirmNameByCidInPanel(int cid) {
		return panelDao.getFirmNameByCidInPanel(cid);
	}
	@Override
	public List<PanelAllocation> getRoleNameByAppIdInPanel(int appId) {
		return panelDao.getRoleNameByAppIdInPanel(appId);
	}
	@Override
	public List<WingTracker> getTheClusterDetails(int clusterId) {
		return panelDao.getTheClusterDetails(clusterId);
	}
	@Override
	public List<PanelAllocation> getRoleInDD(int firmid) {
	   return panelDao.getRoleInDD(firmid);
	}

}
