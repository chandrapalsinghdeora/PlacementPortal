package com.precise.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class OfferDreamDaoImpl implements OfferDreamDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public void insertIntoOffer(Object[] a,int userid) {
		final String procedureCall="{call Proc_WingMapping(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
		Connection connection=null;
		try
		{
			/*connection=jdbcTemplate.getDataSource().getConnection();
			CallableStatement callableSt=connection.prepareCall(procedureCall);
			callableSt.setString(1,"insertintooffer");	
			callableSt.setInt(2, (Integer) a[0]);
			callableSt.setInt(3,(Integer) a[2]);
			callableSt.setInt(4,(Integer) a[3]);
			callableSt.setInt(5,userid);
			callableSt.setInt(6,0);
			callableSt.setInt(7,0);
			callableSt.setBoolean(8,true);
			callableSt.setInt(9,0);
			callableSt.setString(10,null);
			callableSt.setInt(11,0);
			callableSt.setString(12,null);
			callableSt.setString(13,(String) a[1]);
			callableSt.execute();
			connection.commit();*/
			connection=jdbcTemplate.getDataSource().getConnection();
			CallableStatement callableSt=connection.prepareCall(procedureCall);
			callableSt.setString(1,"insertintooffer");	
			callableSt.setInt(2, (Integer) a[0]);
			callableSt.setInt(3,(Integer) a[2]);
			callableSt.setInt(4,(Integer) a[3]);
			callableSt.setInt(5,userid);
			callableSt.setInt(6,0);
			callableSt.setInt(7,0);
			callableSt.setBoolean(8,true);
			callableSt.setInt(9,0);
			callableSt.setString(10,null);
			callableSt.setInt(11,0);
			callableSt.setString(12,null);
			callableSt.setString(13,(String) a[1]);
			callableSt.setString(14,(String) a[4]);
			callableSt.execute();
			connection.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (connection != null)
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}

	@Override
	public void insertIntoUpdateApplication(Object[] a, int userid) {
		System.out.println("inside function::");
		String procedureCall = "";
		Connection connection = null;
		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			connection.setAutoCommit(false);
			procedureCall = "{call Proc_WingMapping(?,?,?,?,?,?,?,?,?,?,?,?,?)}";
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "insertintoupdateapp");
			callableSt.setInt(2, (Integer) a[0]);
			callableSt.setInt(3,(Integer) a[2]);
			callableSt.setInt(4, (Integer)a[3]);
			callableSt.setInt(5,userid);
			callableSt.setInt(6,0);
			callableSt.setInt(7,0);
			callableSt.setBoolean(8,true);
			callableSt.setInt(9,0);
			callableSt.setString(10,null);
			callableSt.setInt(11,0);
			callableSt.setString(12,null);
			callableSt.setString(13,(String) a[1]);
			callableSt.execute(); 
			connection.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (connection != null)
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}
	
	


}
