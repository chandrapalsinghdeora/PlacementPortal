package com.precise.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.precise.dao.RmSettingDao;
import com.precise.model.RmSetting;

@Service
public class RmSettingServiceImpl implements RmSettingService{

	@Autowired
	RmSettingDao rmsettingdao;

	@Override
	public List<RmSetting> getAllSetting() {
		return rmsettingdao.getAllSetting();
	}

	@Override
	public String editSetting(RmSetting rmsetting) throws SQLException {
		return rmsettingdao.editSetting(rmsetting);
	}

}
