package com.precise.service;

import java.sql.SQLException;
import java.util.List;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.precise.dao.ManageAppDAO;
import com.precise.model.ManageApp;

@Service
public class ManageAppServiceImpl implements ManageAppService {
	@Autowired
	ManageAppDAO manageAppDao;

	@Override
	public List<ManageApp> getApplication(int userId) {
		// TODO Auto-generated method stub

		return manageAppDao.getApplication(userId);
	}

	public JSONArray getAllRMData() {
		return manageAppDao.getAllRMData();
	}

	public String updateShareWith(ManageApp manageApp, String appid, String userid) throws SQLException {

		return manageAppDao.updateShareWith(manageApp, appid, userid);
	}

	@Override
	public String updatePanelMailStatus(ManageApp manageApp) {
		return manageAppDao.updatePanelMailStatus(manageApp);
	}

}
