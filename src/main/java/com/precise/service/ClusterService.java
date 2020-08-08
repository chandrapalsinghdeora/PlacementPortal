package com.precise.service;

import java.sql.SQLException;
import java.util.List;

import com.precise.model.Cluster;

public interface ClusterService {
	public String submitCluster(Cluster cluster) throws SQLException;
	public List<Cluster> getAllCluster();
	public String editClusterForm(Cluster cluster) throws SQLException ;
	public String deleteClusterData(Cluster cluster);
}
