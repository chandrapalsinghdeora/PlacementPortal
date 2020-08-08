package com.precise.service;

import java.util.List;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.precise.dao.SuperRMDao;
import com.precise.dao.SuperRMGDDAO;
import com.precise.model.HotList;
import com.precise.model.ShortList;

@Service
public class SuperRMGDServiceImpl implements SuperRMGDService {
	
	 @Autowired
     SuperRMGDDAO superGDDao;

	@Override
	public JSONArray getRoleByCompanyId(int compId) {
		
		return superGDDao.getRoleByCompanyId(compId);
	}

	@Override
	public JSONArray getManageAppByCompanyId(int compId) {
		return superGDDao.getManageAppByCompanyId(compId);
	}

	@Override
	public void saveShortlistedBySuperRm1(ShortList shortlist) {
		superGDDao.saveShortlistedBySuperRm1(shortlist);
	}
	@Override
	public void saveShortlistedBySuperRm2(ShortList shortlist) {
		superGDDao.saveShortlistedBySuperRm2(shortlist);
	}

	@Override
	public List<ShortList> getReleasedShortlistedDataByRole(int cmpRoleId) {		
		return superGDDao.getReleasedShortlistedDataByRole(cmpRoleId);
	}

	@Override
	public void saveHotlistedBySuperRM1(HotList hotlist) {
		superGDDao.saveHotlistedBySuperRM1(hotlist);
	}

	@Override
	public void saveHotlistedBySuperRM2(HotList hotlist) {
		superGDDao.saveHotlistedBySuperRM2(hotlist);
	}

	@Override
	public List<HotList> getViewReleasedHotlist(int cmpRoleId) {
		return superGDDao.saveHotlistedBySuperRM2(cmpRoleId);
	}

	@Override
	public void removeShortlistedBySuperRMGD1(ShortList shortlist) {
		superGDDao.removeShortlistedBySuperRMGD1(shortlist);
	}

	@Override
	public void removeShortlistedBySuperRMGD2(ShortList shortlist) {
		superGDDao.removeShortlistedBySuperRMGD2(shortlist);
	}
	
	

}
