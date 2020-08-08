package com.precise.service;

import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.precise.dao.ClusterDAO;
import com.precise.model.Cluster;
@Service("clusterservice")
@Transactional
public class ClusterServiceImpl implements ClusterService{
	@Autowired
	ClusterDAO clusterDAO;
	
	public String submitCluster(Cluster cluster) throws SQLException{
		return clusterDAO.submitCluster(cluster) ;
	}
	@Override
	public List<Cluster> getAllCluster() {
		return clusterDAO.getAllCluster();
	}
	
	@Override
	public String editClusterForm(Cluster cluster) throws SQLException {
		return clusterDAO.editClusterForm(cluster) ;
	}
	@Override
	public String deleteClusterData(Cluster cluster){
		return clusterDAO.deleteClusterData(cluster);
	}
	
}
