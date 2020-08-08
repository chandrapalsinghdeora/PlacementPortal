package com.precise.dao;

import java.sql.SQLException;
import java.util.List;

import com.precise.model.RmSetting;

public interface RmSettingDao {

	public List<RmSetting> getAllSetting();
	public String editSetting(RmSetting rmsetting) throws SQLException ;
}
