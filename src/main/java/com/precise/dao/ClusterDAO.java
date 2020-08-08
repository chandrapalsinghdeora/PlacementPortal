package com.precise.dao;

import java.sql.SQLException;
import java.util.List;

import com.precise.model.Cluster;
import com.precise.model.ThreadBlog;

public interface ClusterDAO {
	public String submitCluster(Cluster cluster) throws SQLException;
	public List<Cluster> getAllCluster();
	public String editClusterForm(Cluster cluster) throws SQLException ;
	public String deleteClusterData(Cluster cluster);
	
}
