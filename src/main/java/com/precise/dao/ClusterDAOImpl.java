package com.precise.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.precise.model.Cluster;

@Repository
public class ClusterDAOImpl implements ClusterDAO {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	
	public String submitCluster(Cluster cluster) throws SQLException {
		String procedureCall = "";
		Connection connection = null;
		try {

			connection = jdbcTemplate.getDataSource().getConnection();
			connection.setAutoCommit(false);
			procedureCall = "{call proc_Master(?,?,?,?,?,?)}";
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "insertcluster");
			callableSt.setString(2, cluster.getClusterName());
			callableSt.setString(3, cluster.getDescription());
			callableSt.setInt(4, 0);
			callableSt.setInt(5, cluster.getCreatedBy());
			callableSt.setInt(6, 0);
			callableSt.execute();

			connection.commit();
		} catch (Exception e) {
			e.printStackTrace();
			connection.rollback();
		} finally {
			if (connection != null)
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return null;

	}
	
	@Override
	public List<Cluster> getAllCluster() {
		
		List<Cluster> list = new ArrayList<Cluster>();
			final String procedureCall = "{call proc_Master(?)}";
			Connection connection=null;
			try {
				connection = jdbcTemplate.getDataSource().getConnection();
				CallableStatement callableSt = connection.prepareCall(procedureCall);
				callableSt.setString(1, "selectcluster");
				ResultSet rs = callableSt.executeQuery();
				int x=1;
				while (rs.next()) {
					Cluster cluster=new Cluster();
					cluster.setClusterId(rs.getInt("Pk_ClusterId"));
					cluster.setClusterName(rs.getString("CluserName"));
					cluster.setDescription(rs.getString("ClusterDescription"));
					list.add(cluster);
					x=x+1;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				if (connection != null)
					try {
						connection.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
			}
			return list;
			
	}
	
	public String editClusterForm(Cluster cluster) throws SQLException {
		String procedureCall = "";
		Connection connection = null;
		try {

			connection = jdbcTemplate.getDataSource().getConnection();
			connection.setAutoCommit(false);
			procedureCall = "{call proc_Master(?,?,?,?,?,?)}";
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "updatecluster");
			callableSt.setString(2, cluster.getClusterName());
			callableSt.setString(3, cluster.getDescription());
			callableSt.setInt(4, 0);
			callableSt.setInt(5, cluster.getCreatedBy());
			callableSt.setInt(6, cluster.getClusterId());
			callableSt.execute();

			connection.commit();
		} catch (Exception e) {
			e.printStackTrace();
			connection.rollback();
		} finally {
			if (connection != null)
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return null;

	}
	
	@Override
	public String deleteClusterData(Cluster cluster){
		final String procedureCall = "{call proc_Master(?,?,?,?,?,?)}";
		Connection connection = null;
		String returnValue="failed";
		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "deletecluster");
			callableSt.setString(2, null);
			callableSt.setString(3, null);
			callableSt.setInt(4, 0);
			callableSt.setInt(5, cluster.getCreatedBy());
			callableSt.setInt(6, cluster.getClusterId());
			callableSt.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (connection != null)
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return returnValue;
	}

}
