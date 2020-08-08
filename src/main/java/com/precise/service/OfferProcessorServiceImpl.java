package com.precise.service;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.precise.dao.OfferProcessorDao;
import com.precise.model.OfferProcessor;

@Service
public class OfferProcessorServiceImpl implements OfferProcessorService{
	@Autowired
	OfferProcessorDao offerProcessorDao;

	@Override
	public List<OfferProcessor> getOfferedStudent() {
		return offerProcessorDao.getOfferedStudent();
	}

	@Override
	public List<OfferProcessor> getAwaitingStudent() {
		return offerProcessorDao.getAwaitingStudent();
	}

	@Override
	public List<OfferProcessor> getSummary() {
		return offerProcessorDao.getSummary();
	}

	@Override
	public List<OfferProcessor> getOfferRejects() {
		return offerProcessorDao.getOfferRejects();
	}

	@Override
	public List<OfferProcessor> getAwaitingOffer() {
		return offerProcessorDao.getAwaitingOffer();
	}

	@Override
	public List<OfferProcessor> getFirmList() {
		return offerProcessorDao.getFirmList();
	}

	@Override
	public JSONArray getOpRoleName(int cmpid) {
		return  offerProcessorDao.getOpRoleName(cmpid);
	}

	@Override
	public JSONArray getOpStudentName(int roleid) {
		return  offerProcessorDao.getOpStudentName(roleid);
	}

	@Override
	public JSONObject getOpStudentStatus(int roleid, int stdId) {
		return  offerProcessorDao.getOpStudentStatus(roleid,stdId);
	}

	@Override
	public void updateststatus(OfferProcessor op) {
		offerProcessorDao.updateststatus(op);
	}

	@Override
	public void plcstatusupdate(OfferProcessor op) {
		offerProcessorDao.plcstatusupdate(op);
	}

}
