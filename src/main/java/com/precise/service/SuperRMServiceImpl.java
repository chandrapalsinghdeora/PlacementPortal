package com.precise.service;

import java.util.List;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.precise.dao.SuperRMDao;
import com.precise.model.HotList;
import com.precise.model.ShortList;

@Service
public class SuperRMServiceImpl implements SuperRMService {
     @Autowired
     SuperRMDao superDao;

	@Override
	public JSONArray getRoleByCompanyId(int compId) {
		
		return superDao.getRoleByCompanyId(compId);
	}

	@Override
	public JSONArray getManageAppByCompanyId(int compId) {
		return superDao.getManageAppByCompanyId(compId);
	}

	@Override
	public void saveShortlistedBySuperRm1(ShortList shortlist) {
		superDao.saveShortlistedBySuperRm1(shortlist);
	}
	@Override
	public void saveShortlistedBySuperRm2(ShortList shortlist) {
		superDao.saveShortlistedBySuperRm2(shortlist);
	}

	@Override
	public List<ShortList> getReleasedShortlistedDataByRole(int cmpRoleId) {		
		return superDao.getReleasedShortlistedDataByRole(cmpRoleId);
	}

	@Override
	public void saveHotlistedBySuperRM1(HotList hotlist) {
		superDao.saveHotlistedBySuperRM1(hotlist);
	}

	@Override
	public void saveHotlistedBySuperRM2(HotList hotlist) {
		superDao.saveHotlistedBySuperRM2(hotlist);
	}

	@Override
	public List<HotList> getViewReleasedHotlist(int cmpRoleId) {
		return superDao.saveHotlistedBySuperRM2(cmpRoleId);
	}

	@Override
	public void removeShortlistedBySuperRm2(ShortList shortlist) {
		superDao.removeShortlistedBySuperRm2(shortlist);
		
	}

	@Override
	public void removeShortlistedBySuperRm1(ShortList shortlist) {
		superDao.removeShortlistedBySuperRm1(shortlist);
		
	}
	
	
	
}
