package com.precise.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.precise.model.CompanyMaster;
import com.precise.model.Interview;

@Repository
public class InterviewDaoImpl implements InterviewDao{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<Interview> getAllInterview() {
		String sql = "SELECT * FROM tbl_mst_company where [IsActive]=1";
		List<Interview> listRole = jdbcTemplate.query(sql, new RowMapper<Interview>() {
		@Override
			public Interview mapRow(ResultSet rs, int rowNum) throws SQLException {
                    Interview inter = new Interview();
                    inter.setCompanyId(rs.getInt("Pk_CompanyId"));
                    inter.setCompanyName(rs.getString("CompanyName"));
                    return inter;
			}
		});
		return listRole;
		
	}

	@Override
	public void saveFirmManagementForm(Interview inter) throws SQLException {
		System.out.println("interviewDaoImpl.saveFirmManagementForm()");
		Connection connection = null;
		try{
			connection = jdbcTemplate.getDataSource().getConnection();
			connection.setAutoCommit(false);
			
			List<String> questionList=inter.getQuestion();
		 	List<String> answerList=inter.getAnswer();
            
		 	CallableStatement callableSt =  connection.prepareCall("{call sP_InretViewQuestions(?,?,?,?,?,?,?,?,?,?,?,?)}");
			
            for(int i=0;i<questionList.size();i++){
            //	System.out.println(i+"th value is:"+inter.getCompanyId()+"  "+inter.getYear()+"  "+questionList.get(i)+"  "+answerList.get(i));
				callableSt.setString(1, "INSERT");
				callableSt.setString(2, questionList.get(i));	
				callableSt.setString(3, answerList.get(i));
				callableSt.setInt(4,inter.getCompanyId());
				callableSt.setInt(5, inter.getYear());
				callableSt.setInt(6, inter.getCreatedBy());
				callableSt.setInt(7, 0);
				callableSt.setDate(8, null);
				callableSt.setInt(9, inter.getCreatedBy());
				callableSt.setDate(10, null);
				callableSt.setInt(11, 0);
				callableSt.setString(12, "interview");
			//	System.out.println("\n");
				callableSt.addBatch();
			}
            callableSt.executeBatch();
					
            connection.commit();
		}catch (Exception e) {
			e.printStackTrace();
			connection.rollback();
		}finally {
			if (connection != null)
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}

	
	@Override
	public Interview getCompanyName(int cmpId) {
		Interview list = null;
		final String procedureCall = "{call sP_InretViewQuestions(?,?,?,?)}";
		Connection connection=null;
		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "selectcompany");
			callableSt.setString(2, null);
			callableSt.setString(3, null);
			callableSt.setInt(4, cmpId);
			ResultSet rs = callableSt.executeQuery();
			if (rs.next()) {
				list=new Interview();
				list.setCompanyName(rs.getString("CompanyName"));
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
}
