package com.precise.service;

import java.sql.SQLException;
import java.util.List;
import org.json.JSONArray;
import com.precise.model.ManageApp;

public interface ManageAppService {
	public List<ManageApp> getApplication(int userId);

	public JSONArray getAllRMData();

	public String updateShareWith(ManageApp manageApp, String appid, String userid) throws SQLException;

	public String updatePanelMailStatus(ManageApp manageApp);
}
