package com.precise.service;

import java.util.List;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.precise.dao.GetReportDao;
import com.precise.model.CloseStatus;
import com.precise.model.Cluster;

@Service
public class GetReportServiceImpl implements GetReportService {
	
	@Autowired
	GetReportDao getreportdao;

	@Override
	public void getDownloadCL(List<CloseStatus> list, int clusterid,String downloadCLFile){
		getreportdao.getDownloadCL(list,clusterid,downloadCLFile);
		
	}

	@Override
	 public void getDownloadSL1(List<CloseStatus> list, int clusterid,String downloadSL1File){
		getreportdao.getDownloadSL1(list,clusterid,downloadSL1File);
		
	}

	@Override
	 public void getDownloadSL2(List<CloseStatus> list, int clusterid,String downloadSL2File){
		getreportdao.getDownloadSL2(list,clusterid,downloadSL2File);
	}

	@Override
	 public void getDownloadHL(List<CloseStatus> list, int clusterid,String downloadHLFile){
		getreportdao.getDownloadHL(list,clusterid,downloadHLFile);
	}

	@Override
	 public void getDownloadPF(List<CloseStatus> list, int clusterid,String downloadPFFile){
		getreportdao.getDownloadPF(list,clusterid,downloadPFFile);
	}

	@Override
	public JSONArray getroles(int firmid,int clusterid) {
		return getreportdao.getroles(firmid,clusterid);
	}
	
	@Override
	public List<Cluster> getAllCluster() {
		return getreportdao.getAllCluster();
	}

	@Override
	public JSONArray getfirms(int clusterid) {
		return getreportdao.getfirms(clusterid);
	}

	@Override
	public List<CloseStatus> getAllRoleId(int cmpid, int clusterid, int roleid) {
		return getreportdao.getAllRoleId(cmpid,clusterid,roleid);
	}

}
