package com.precise.service;

import java.util.List;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.precise.dao.CloseStatusDAO;
import com.precise.dao.CloseStatusDAO1;
import com.precise.model.CloseStatus;
import com.precise.model.InfoCloseStatus;
import com.precise.model.UserProfile;



@Service("closeStatusService1")
@Transactional
public class CloseStatusServiceImpl1 implements CloseStatusService1 {

	
	@Autowired
	private CloseStatusDAO1 closeStatusdao;
		
	@Override
	public List<CloseStatus> getCloseStatusDetail(int cmpRoleId) {
		System.out.println("CloseStatusServiceImpl.getCloseStatusDetail");
		return closeStatusdao.getCloseStatusDetail(cmpRoleId);
	}
	
	@Override
	public List<CloseStatus> getApproveCloseStatusList(int cmpRoleId) {
		System.out.println("CloseStatusServiceImpl.getApproveCloseStatusList");
		return closeStatusdao.getApproveCloseStatusList(cmpRoleId);
	}
	@Override
	public List<CloseStatus> getUnApproveCloseStatusList(int cmpRoleId) {
		System.out.println("CloseStatusServiceImpl.getUnApproveCloseStatusList");
		return closeStatusdao.getUnApproveCloseStatusList(cmpRoleId);
	}
	
	@Override
	public CloseStatus getCloseStatusHeader( int cmpRoleId){
		
		return closeStatusdao.getCloseStatusHeader(cmpRoleId);
		
	}
	@Override
	public List<UserProfile> getUserValues(int rollNumber){
		
		return closeStatusdao.getUserValues(rollNumber);
	}
	
	@Override
	public JSONArray getInternshipValues(int rollNumber){
		
		return closeStatusdao.getInternshipValues(rollNumber);
	}
	
	public JSONArray getExperienceList(int rollNumber){
		
		return closeStatusdao.getExperienceList(rollNumber);
	}
	
	public JSONArray getCVList(int rollNumber){
		
		return closeStatusdao.getCVList(rollNumber);
	}
	
	public InfoCloseStatus getInfoValues(int roleId){
		
		return closeStatusdao.getInfoValues(roleId);

	}
	
	public void updateFlag(List<CloseStatus> closeStatuslist){
		
		closeStatusdao.updateFlag(closeStatuslist);
	}
	public void saveShortList(List<CloseStatus> closeStatuslist,int userid){
		closeStatusdao.saveShortList(closeStatuslist,userid);
	}
	
	public void saveHRList (String email,int cmpRoleId,String MailDes,String RmemailId,String hrName,int userid){
		
		closeStatusdao.saveHRList(email,cmpRoleId,MailDes,RmemailId,hrName,userid);
		
	}
	public void saveGenerateShortLinkMail(CloseStatus closeStatus,int userid){
		closeStatusdao.saveGenerateShortLinkMail(closeStatus,userid);
	}
	public List<String> getDownloadCVList(List<CloseStatus> downloadCvlist){
		return closeStatusdao.getDownloadCVList(downloadCvlist);
	}
	
	public String getSelectedUserValues(String shortListId,int compRoleId){
		return closeStatusdao.getUserData(shortListId,compRoleId);
	}

	public String getSelectedUserHROneList(String shortListId,int compRoleId){
		return closeStatusdao.getSelectedUserHROneList(shortListId,compRoleId);
	}
	
	public String getSelectedUserRMList(String shortListId,int compRoleId){
		return closeStatusdao.getSelectedUserRMList(shortListId,compRoleId);
	}

}