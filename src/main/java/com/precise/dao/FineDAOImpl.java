package com.precise.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


import com.precise.model.Fine;
import com.precise.model.Forum;
import com.precise.model.ThreadBlog;

@Repository("fineRepo")
public class FineDAOImpl implements FineDAO{

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	
	@Override
	public List<Fine> getAllFineDetail(int userid, int roleId ) {
		
		List<Fine> listFine=new ArrayList<Fine>();		
		final String procedureCall = "{call sp_InsertFine(?,?,?,?)}";
		Connection connection = null;
		try {
			System.out.println("userid : "+userid+" role : "+roleId);
			connection = jdbcTemplate.getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "Select");
			callableSt.setInt(2,userid);
			callableSt.setString(3,null);
			callableSt.setInt(4,roleId);
			ResultSet rs = callableSt.executeQuery();
			int x=1;
			Fine fine = null;
			while (rs.next()) {
				fine = new Fine();
				fine.setFineReason(rs.getString("FineReason"));
				fine.setFineStatus(rs.getString("FineStatus"));
				System.out.println(rs.getString("FineReason"));
				fine.setCreatedDate(rs.getTimestamp("CreatedDate"));
				fine.setRowCount(x);
				fine.setAmount(rs.getDouble("Amount"));
				listFine.add(fine);	
				x=x+1;
			}
		
		}catch(
		SQLException e)
		{
			e.printStackTrace();
		}finally
		{
			if (connection != null)
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return listFine;
		}
	
	@Override
	public void fineAdd(Fine fineBean,int userid) {
		
		
		final String procedureCall = "{call sp_InsertFine(?,?,?,?,?,?,?,?,?,?)}";
		Connection connection = null;
		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "Insert");
			callableSt.setInt(2, fineBean.getType());
			callableSt.setString(3,fineBean.getIndividual());
			callableSt.setInt(4,fineBean.getRoleId());
			DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
			Date date = formatter.parse(fineBean.getFineCreatedDate());
			java.sql.Date sqlTimeStamp = new java.sql.Date(date.getTime());
			System.out.println("date::"+sqlTimeStamp);
			callableSt.setDate(5,sqlTimeStamp);
			callableSt.setString(6, fineBean.getFineReason());
			callableSt.setString(7, fineBean.getEvent());
			callableSt.setDouble(8, fineBean.getAmount());
			callableSt.setInt(9,userid);
			callableSt.setInt(10, userid);
			callableSt.execute();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}catch (ParseException e) {
			e.printStackTrace();
			
		}
		finally {
			if (connection != null)
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}
	
}
