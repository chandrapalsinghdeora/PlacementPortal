package com.precise.dao;

import java.util.List;

import org.json.JSONArray;

import com.precise.model.HotList;
import com.precise.model.ShortList;

public interface SuperRMGDDAO {
	JSONArray getRoleByCompanyId(int compId);

	JSONArray getManageAppByCompanyId(int compId);

	void saveShortlistedBySuperRm1(ShortList shortlist);

	void saveShortlistedBySuperRm2(ShortList shortlist);

	List<ShortList> getReleasedShortlistedDataByRole(int cmpRoleId);

	void saveHotlistedBySuperRM1(HotList hotlist);

	void saveHotlistedBySuperRM2(HotList hotlist);

	List<HotList> saveHotlistedBySuperRM2(int cmpRoleId);

	void removeShortlistedBySuperRMGD1(ShortList shortlist);

	void removeShortlistedBySuperRMGD2(ShortList shortlist);

}
