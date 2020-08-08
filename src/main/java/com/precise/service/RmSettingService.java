package com.precise.service;

import java.sql.SQLException;
import java.util.List;

import com.precise.model.RmSetting;

public interface RmSettingService {

	public List<RmSetting> getAllSetting();
	public String editSetting(RmSetting rmsetting) throws SQLException ;
}
