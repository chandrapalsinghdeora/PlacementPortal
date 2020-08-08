package com.precise.dao;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.precise.model.OfferProcessor;

public interface OfferProcessorDao {

	List<OfferProcessor> getOfferedStudent();

	List<OfferProcessor> getAwaitingStudent();

	List<OfferProcessor> getSummary();

	List<OfferProcessor> getOfferRejects();

	List<OfferProcessor> getAwaitingOffer();

	List<OfferProcessor> getFirmList();

	JSONArray getOpRoleName(int cmpid);

	JSONArray getOpStudentName(int roleid);
	JSONObject getOpStudentStatus(int roleid,int stdId);

	void updateststatus(OfferProcessor op);

	void plcstatusupdate(OfferProcessor op);
}
