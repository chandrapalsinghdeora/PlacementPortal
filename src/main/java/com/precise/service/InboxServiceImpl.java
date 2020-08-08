package com.precise.service;

import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.precise.dao.InboxDAO;
import com.precise.dao.LoginDao;
import com.precise.model.EventCalander;
import com.precise.model.Inbox;
import com.precise.model.Label;

@Service
public class InboxServiceImpl implements InboxService{
	
	@Autowired
	InboxDAO inboxDao;

	@Override
	public void saveInboxData(Inbox inbox) {
		System.out.println("InboxServiceImpl.saveInboxData()");
		inboxDao.saveInboxData(inbox);
	}

	@Override
	public List<Inbox> getInboxData(int receiverId) {
		System.out.println("InboxServiceImpl.getInboxData()");
		return inboxDao.getInboxData(receiverId);
	}

	@Override
	public void updateReadStatus(int statusId) {
		System.out.println("InboxServiceImpl.updateReadStatus()"+statusId);	
		inboxDao.updateReadStatus(statusId);
	}

	@Override
	public int getUnreadMailCount(int receiverId) {
		System.out.println("InboxServiceImpl.getUnreadMailCount()"+receiverId);
		return inboxDao.getUnreadMailCount(receiverId);
	}

	@Override
	public void saveLabel(Label label) {
		System.out.println("InboxServiceImpl.saveLabel()"+label.getCreatedBy());
		inboxDao.saveLabel(label);
	}

	@Override
	public Map<Integer, Label> getLabelByUserId(int createdBy) {
		System.out.println("InboxServiceImpl.getLabelByUserId() "+createdBy);
		return inboxDao.getLabelByUserId(createdBy);
	}
   
	@Override
	public List<Inbox> getInboxDataByUserIdAndStatusId(int userId, int statusId) {
		System.out.println("InboxServiceImpl.getInboxDataByUserIdAndStatusId()");
		return inboxDao.getInboxDataByUserIdAndStatusId(userId,statusId);
	}
	
	@Override
	public void updateInboxLabelIds(int receiverId, int labelId, List<Integer> inbox) {
		System.out.println("InboxServiceImpl.updateInboxLabelIds() recid-"+receiverId+"label- "+labelId+"inbox-"+inbox);
		inboxDao.updateInboxLabelIds(receiverId,labelId,inbox);
	}

	@Override
	public List<Inbox> getInboxDataByLabelIdAndUserId(int receiverId, int labelId) {
		System.out.println("InboxServiceImpl.getInboxDataByLabelIdAndUserId()" +receiverId+" "+labelId);
		return inboxDao.getInboxDataByLabelIdAndUserId(receiverId,labelId);
	}

	@Override
	public String getInboxBodyByInboxId(int userId, int inboxId) {
		System.out.println("InboxServiceImpl.getInboxBodyByInboxId() userId-"+userId+"inbox id-"+inboxId);
		return inboxDao.getInboxBodyByInboxId(userId,inboxId);
	}
	
	@Override
	public void deleteInboxData(List<Integer> inboxId, int receiverId) {
		System.out.println("InboxServiceImpl.deleteInboxData( ) "+inboxId+"reciv id- "+receiverId);
		inboxDao.deleteInboxData(inboxId,receiverId);
	}

	@Override
	public String getAttachementPathByInboxId(int inboxId) {
		
		return inboxDao.getAttachementPathByInboxId(inboxId);
	}

	@Override
	public JSONObject getEventList(int receiverId) {		
		return inboxDao.getEventList(receiverId);
	}

	@Override
	public JSONObject getCompanyEventList(int receiverId) {		
		return inboxDao.getCompanyEventList(receiverId);
	}

	@Override
	public void deleteLabel(Label label) throws Exception {		
		inboxDao.deleteLabel(label);
	}
	
	
}
