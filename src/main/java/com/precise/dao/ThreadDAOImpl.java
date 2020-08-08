package com.precise.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.precise.model.ThreadBlog;

@Repository("thd")
public class ThreadDAOImpl implements ThradDAO {
	@Autowired
	 private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	
	@Override
	public void saveThrad(ThreadBlog thread) {
		final String procedureCall = "{call proc_Thread(?,?,?,?,?,?,?)}";
		Connection connection = null;
		try {
		//Get Connection instance from dataSource
		connection = jdbcTemplate.getDataSource().getConnection();
		CallableStatement callableSt = connection.prepareCall(procedureCall);
		callableSt.setString(1, "insert");
		callableSt.setInt(2, 0);
		//System.out.println("+++++"+thread.getForumId());
		callableSt.setInt(3, thread.getForumId());
		callableSt.setString(4, thread.getThreadName());
		callableSt.setInt(5, thread.getEditPermissionId());
		callableSt.setInt(6, thread.getDeletePermissionId());
		callableSt.setInt(7, thread.getPostPermissionId());
		//Call Stored Procedure
		callableSt.execute();
		//System.out.println(callableSt.getString(3));
		}catch (SQLException e) {
		e.printStackTrace();
		} finally {
		if(connection != null)
		try {
		connection.close();
		} catch (SQLException e) {
		e.printStackTrace();
		}
		}
	}
	
	@Override
	public List<ThreadBlog> getAllThread(String forumId) {
		List<ThreadBlog> list = new ArrayList<ThreadBlog>();
		final String procedureCall = "{call proc_Thread(?,?,?)}";
		Connection connection = null;
		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "select");
			callableSt.setInt(2,0);
			callableSt.setInt(3,Integer.parseInt(forumId));
			ResultSet rs = callableSt.executeQuery();
			int x=1;
			while (rs.next()) {
				ThreadBlog threadBlog = new ThreadBlog();	
	    		//threadBlog.setForumId(rs.getInt("PK_ForumId"));
	    		threadBlog.setThreadId(rs.getInt("PK_ThreadId"));
	    		threadBlog.setQuestioncount(rs.getInt("questioncount"));
	    		threadBlog.setThreadName(rs.getString("ThreadName")); 
	    		threadBlog.setPostPermissionId(rs.getInt("PostPermission"));
	    		threadBlog.setEditPermissionId(rs.getInt("EditPermission"));
	    		threadBlog.setDeletePermissionId(rs.getInt("DeletePermission"));
	    		threadBlog.setCreatedDate(rs.getTimestamp("CreatedDate"));
	    		//threadBlog.setRowCount(x);
				list.add(threadBlog);
				System.out.println("threadname---- " + rs.getString("ThreadName"));
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
		
		
		/*System.out.println("forumId::"+forumId);
		String sql = "SELECT * FROM tbl_trn_Thread where FK_ForumId="+forumId+"";
	    List<ThreadBlog> listContact = jdbcTemplate.query(sql, new RowMapper<ThreadBlog>() {
	    	@Override
	        public ThreadBlog mapRow(ResultSet rs, int rowNum) throws SQLException {
	    		ThreadBlog threadBlog = new ThreadBlog();	
	    		//threadBlog.setForumId(rs.getInt("PK_ForumId"));
	    		threadBlog.setThreadId(rs.getInt("PK_ThreadId"));
	    		threadBlog.setThreadId(rs.getInt("questioncount"));
	    		threadBlog.setThreadName(rs.getString("ThreadName")); 
	    		threadBlog.setPostPermissionId(rs.getInt("PostPermission"));
	    		threadBlog.setEditPermissionId(rs.getInt("EditPermission"));
	    		threadBlog.setDeletePermissionId(rs.getInt("DeletePermission"));
	    		threadBlog.setCreatedDate(rs.getTimestamp("CreatedDate"));
	            System.out.println("ThreadName " +rs.getString("ThreadName"));
	            return threadBlog;
	        }*/
	 
	   
	    
	
	@Override
	public List<ThreadBlog> getPost(String forumId) {
		String sql = "SELECT * FROM tbl_trn_Forum where PK_ForumId="+forumId+"";
	    List<ThreadBlog> listContact = jdbcTemplate.query(sql, new RowMapper<ThreadBlog>() {
	        @Override
	        public ThreadBlog mapRow(ResultSet rs, int rowNum) throws SQLException {
	        	ThreadBlog th = new ThreadBlog();
	        	th.setForumId(rs.getInt("PK_ForumId"));
	            th.setForumName(rs.getString("ForumName"));  
	           // System.out.println("fname " +rs.getString("ForumName")+" form id "+rs.getInt("PK_ForumId"));
	            return th;
	        }
	    });
	    return listContact;
	}
	@Override
	public List<ThreadBlog> getPostPermission() {
		String sql = " SELECT FK_IIMstudentId,userName FROM tbl_trn_placeCommer pc "
				+ " inner join tbl_trn_IIMStd iim on Pk_IIMStdId=FK_IIMstudentId "
				+ " where pc.isActive=1 and iim.IsActive=1";
	    List<ThreadBlog> listContact = jdbcTemplate.query(sql, new RowMapper<ThreadBlog>() {
	        @Override
	        public ThreadBlog mapRow(ResultSet rs, int rowNum) throws SQLException {
	        	ThreadBlog th = new ThreadBlog();
	        	th.setPostPermissionId(rs.getInt("FK_IIMstudentId"));
	        	th.setPostPermissionName(rs.getString("userName"));
	           // System.out.println("username " +rs.getString("UserName"));
	            return th;
	        }
	    });
	    return listContact;
	}
	@Override
	public List<ThreadBlog> getEditPermission() {
		String sql = " SELECT FK_IIMstudentId,userName FROM tbl_trn_placeCommer pc "
				+ " inner join tbl_trn_IIMStd iim on Pk_IIMStdId=FK_IIMstudentId "
				+ " where pc.isActive=1 and iim.IsActive=1";
	    List<ThreadBlog> listContact = jdbcTemplate.query(sql, new RowMapper<ThreadBlog>() {
	        @Override
	        public ThreadBlog mapRow(ResultSet rs, int rowNum) throws SQLException {
	        	ThreadBlog th = new ThreadBlog();
	        	th.setEditPermissionId(rs.getInt("FK_IIMstudentId"));
	        	th.setEditPermissionName(rs.getString("userName"));
	           // System.out.println("username " +rs.getString("UserName"));
	            return th;
	        }
	    });
	    return listContact;
	}
	@Override
	public List<ThreadBlog> getDeletePermission() {
		String sql = " SELECT FK_IIMstudentId,userName FROM tbl_trn_placeCommer pc "
				+ " inner join tbl_trn_IIMStd iim on Pk_IIMStdId=FK_IIMstudentId "
				+ " where pc.isActive=1 and iim.IsActive=1";
	    List<ThreadBlog> listContact = jdbcTemplate.query(sql, new RowMapper<ThreadBlog>() {
	        @Override
	        public ThreadBlog mapRow(ResultSet rs, int rowNum) throws SQLException {
	        	ThreadBlog th = new ThreadBlog();
	        	th.setDeletePermissionId(rs.getInt("FK_IIMstudentId"));
	        	th.setDeletePermissionName(rs.getString("userName"));
	           // System.out.println("username " +rs.getString("UserName"));
	            return th;
	        }
	    });
	    return listContact;
	}
	
	@Override
	public Map<String,Object> getForumName(String threadId){
		System.out.println("threadId inside getForumName method"+threadId);
		final String procedureCall = "{call proc_Thread(?,?)}";
		Connection connection = null;
		List<ThreadBlog> forumName=new ArrayList<ThreadBlog>();
		Map<String,Object> parentMap=new LinkedHashMap<String,Object>();
		Map<String,String> childMap=new LinkedHashMap<String,String>();
		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "selectForumName");
			callableSt.setInt(2, Integer.parseInt(threadId));
			ResultSet rs = callableSt.executeQuery();
			while (rs.next()) {
				ThreadBlog fn=new ThreadBlog();
				fn.setForumId(rs.getInt("FK_ForumId"));
				fn.setForumName(rs.getString("ForumName"));
				forumName.add(fn);
				childMap.put("threadName", rs.getString("ThreadName"));
				childMap.put("editPermission", rs.getString("EditPermission"));
				childMap.put("deletePermission", rs.getString("DeletePermission"));
				childMap.put("postPermisssion", rs.getString("PostPermission"));
			}
			parentMap.put("ForumNameList", forumName);
			parentMap.put("permissionData",childMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return parentMap;
	}
	
	@Override
	public String updateThreadData(ThreadBlog threadBlog,String threadId){
		final String procedureCall = "{call proc_Thread(?,?,?,?,?,?,?)}";
		Connection connection = null;
		String returnValue="failed";
		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "updateThreadData");
			callableSt.setInt(2, Integer.parseInt(threadId));
			callableSt.setInt(3, 0);
			callableSt.setString(4, threadBlog.getThreadName());
			callableSt.setInt(5, threadBlog.getEditPermissionId());
			callableSt.setInt(6, threadBlog.getDeletePermissionId());
			callableSt.setInt(7, threadBlog.getPostPermissionId());
			callableSt.execute();
			returnValue="success";
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
	
	@Override
	public String deleteThreadData(String threadId){
		final String procedureCall = "{call proc_Thread(?,?)}";
		Connection connection = null;
		String returnValue="failed";
		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "deleteThread");
			callableSt.setInt(2, Integer.parseInt(threadId));
			callableSt.execute();
			returnValue="success";
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
