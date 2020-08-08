package com.precise.dao;

import java.util.List;

import org.json.JSONArray;

import com.precise.model.CloseStatus;
import com.precise.model.Cluster;

public interface GetReportDao {
public List<Cluster> getAllCluster();
public List<CloseStatus> getAllRoleId(int cmpid,int clusterid,int roleid);
public JSONArray getfirms(int clusterid);
public void getDownloadCL(List<CloseStatus> list, int clusterid,String downloadCLFile);
 public JSONArray getroles(int firmid, int clusterid);
 public void getDownloadSL1(List<CloseStatus> list, int clusterid,String downloadSL1File);
 public void getDownloadSL2(List<CloseStatus> list, int clusterid,String downloadSL2File);
 public void getDownloadHL(List<CloseStatus> list, int clusterid,String downloadHLFile);
 public void getDownloadPF(List<CloseStatus> list, int clusterid,String downloadPFFile);
}
