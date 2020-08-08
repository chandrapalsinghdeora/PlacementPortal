package com.precise.service;

import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import com.precise.model.EventCalander;
import com.precise.model.Inbox;
import com.precise.model.Label;

public interface InboxService {

   public void saveInboxData(Inbox inbox);

   public List<Inbox> getInboxData(int receiverId);

   public void updateReadStatus(int statusId);

   public int getUnreadMailCount(int receiverId);

   public void saveLabel(Label label);

   public Map<Integer, Label> getLabelByUserId(int createdBy);
  
   public List<Inbox> getInboxDataByUserIdAndStatusId(int userId,int statusId);

   public void updateInboxLabelIds(int receiverId, int labelId, List<Integer> inbox);

   public List<Inbox> getInboxDataByLabelIdAndUserId(int receiverId, int labelId);

   public String getInboxBodyByInboxId(int userId, int inboxId);

   public void deleteInboxData(List<Integer> inbox, int receiverId);

   public String getAttachementPathByInboxId(int inboxId);

public JSONObject getEventList(int receiverId);

public JSONObject getCompanyEventList(int receiverId);

public void deleteLabel(Label label) throws Exception;

}
