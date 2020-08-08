package com.precise.service;

import java.util.List;

import org.json.JSONArray;

import com.precise.model.CloseStatus;
import com.precise.model.InfoCloseStatus;
import com.precise.model.UserProfile;

public interface CloseStatusService {

	public List<CloseStatus> getCloseStatusDetail(int cmpRoleId);
	public List<CloseStatus> getApproveCloseStatusList(int cmpRoleId);
	public List<CloseStatus> getUnApproveCloseStatusList(int cmpRoleId);
	public List<String> getDownloadCVList(List<CloseStatus> downloadCvlist);
	public CloseStatus getCloseStatusHeader( int cmpRoleId);
	
	public List<UserProfile> getUserValues(int rollNumber);
	
	public JSONArray getInternshipValues(int rollNumber);
	public JSONArray getExperienceList(int rollNumber);
	public JSONArray getCVList(int rollNumber);
	
	public InfoCloseStatus getInfoValues(int roleId);
	public void updateFlag(List<CloseStatus> closeStatuslist);
	
	public void saveShortList(List<CloseStatus> closeStatuslist,int userid);
	public void saveHRList (String email,int cmpRoleId,String MailDes,String RmemailId,String hrName,int userid);
	public void saveGenerateShortLinkMail(CloseStatus closeStatus,int userid);
	public String getSelectedUserValues(String shortListId,int compRoleId, boolean rankFlag);
	public String getSelectedUserHRList(String shortListId,int compRoleId);
	public String getSelectedUserRMList(String shortListId,int compRoleId);
	public String getSelectedUserHotList(String hotListId,int compRoleId);
	public String getSelectedUserHRHotList(String shortListId,int compRoleId);

}
