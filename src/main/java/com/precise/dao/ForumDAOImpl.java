package com.precise.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.precise.model.Forum;
import com.precise.model.Reply;
import com.precise.model.SessionBean;
import com.precise.model.ThreadBlog;

@Repository("forumRepo")
public class ForumDAOImpl implements ForumDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	@Override
	public void saveForum(Forum forum) {
		System.out.println("ForumDAOImpl.saveForum()" + forum.getForumName());
		final String procedureCall = "{call proc_Forum(?,?,?,?,?,?,?)}";
		Connection connection = null;
		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "insert");
			callableSt.setInt(2, 0);
			callableSt.setString(3, forum.getForumName());
			callableSt.setInt(4, forum.getPostPermission());
			callableSt.setInt(5, forum.getEditPermission());
			callableSt.setInt(6, forum.getDeletePermission());
			System.out.println("replyper ::"+forum.getReplyPermission() );
			callableSt.setInt(7, forum.getReplyPermission());
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
	}

	@Override
	public List<Forum> getAllFourm(int roleId) {
		System.out.println("ForumDAOImpl.getAllFourm()--");
		
		List<Forum> list = new ArrayList<Forum>();
		final String procedureCall = "{call proc_Forum(?)}";
		Connection connection = null;
		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "select");
			ResultSet rs = callableSt.executeQuery();
			int x=1;
			while (rs.next()) {
				
				if(roleId==2){
				if(rs.getString("ForumName").equalsIgnoreCase("Application Queries")){
					
				}
				else{
				Forum forum = new Forum();
				forum.setForumId(rs.getInt("PK_ForumId"));
				
				forum.setForumName(rs.getString("ForumName"));
				System.out.println(rs.getString("ForumName"));
				forum.setPostPermission(rs.getInt("PostPermission"));
				forum.setEditPermission(rs.getInt("EditPermission"));
				forum.setReplyPermission(rs.getInt("ReplyPermission"));
				forum.setThreadCount(rs.getInt("threadcount"));
				forum.setDeletePermission(rs.getInt("DeletePermission"));
				forum.setCreatedDate(rs.getTimestamp("CreatedDate"));
				forum.setRowCount(x);
				list.add(forum);
				System.out.println("forumname---- " + rs.getString("ForumName"));
				x=x+1;
				}
			}
				else{
					
					Forum forum = new Forum();
					forum.setForumId(rs.getInt("PK_ForumId"));
					
					forum.setForumName(rs.getString("ForumName"));
					System.out.println(rs.getString("ForumName"));
					forum.setPostPermission(rs.getInt("PostPermission"));
					forum.setEditPermission(rs.getInt("EditPermission"));
					forum.setReplyPermission(rs.getInt("ReplyPermission"));
					forum.setThreadCount(rs.getInt("threadcount"));
					forum.setDeletePermission(rs.getInt("DeletePermission"));
					forum.setCreatedDate(rs.getTimestamp("CreatedDate"));
					forum.setRowCount(x);
					list.add(forum);
					System.out.println("forumname---- " + rs.getString("ForumName"));
					x=x+1;
				}
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

	@Override
	public List<ThreadBlog> getPostPermission() {
		String sql = " SELECT FK_IIMstudentId,userName FROM tbl_trn_placeCommer pc "
				+ " inner join tbl_trn_IIMStd iim on Pk_IIMStdId=FK_IIMstudentId "
				+ " where pc.isActive=1 and iim.IsActive=1 ";
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
	public void updateForum(Forum forum) {
		System.out.println("ForumDAOImpl.updateForum()" + forum.getForumId() + "fname=" + forum.getForumName());
		final String procedureCall = "{call proc_Forum(?,?,?,?,?,?,?)}";
		Connection connection = null;
		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "update");
			callableSt.setInt(2, forum.getForumId());
			callableSt.setString(3, forum.getForumName());
			callableSt.setInt(4, forum.getEditPermission());
			callableSt.setInt(5, forum.getDeletePermission());
			callableSt.setInt(6, forum.getPostPermission());
			callableSt.setInt(7, forum.getReplyPermission());
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
	}

	@Override
	public void deleteForum(int forumId) {
		System.out.println("ForumDAOImpl.deleteForum()" + forumId);
		final String procedureCall = "{call proc_Forum(?,?,?,?,?,?,?)}";
		Connection connection = null;
		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "delete");
			callableSt.setInt(2, forumId);
			callableSt.setString(3, "");
			callableSt.setInt(4, 0);
			callableSt.setInt(5, 0);
			callableSt.setInt(6, 0);
			callableSt.setInt(7, 0);
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
	}

	@Override
	public void saveReply(Reply reply) {
		System.out.println("ForumDAOImpl.saveReply()" + reply.getReply());
		final String procedureCall = "{call proc_reply(?,?,?,?)}";
		Connection connection = null;
		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "insert");
			callableSt.setInt(2, 0);
			callableSt.setString(3, reply.getReply());
			callableSt.setInt(4, reply.getQuestionId());
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
 
	}

	@Override
	public List<ThreadBlog> getReplyId(int threadreplyId){
		
		List<ThreadBlog> replylist = new ArrayList<ThreadBlog>();
		final String procedureCall = "{call proc_Thread(?,?)}";
		Connection connection = null;
		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "GetReplyById");
			callableSt.setInt(2, threadreplyId);
			
			ResultSet rs = callableSt.executeQuery();
			while (rs.next()) {
				ThreadBlog rep = new ThreadBlog();
				//rep.setThreadId(rs.getInt("PK_ThreadId"));
				rep.setReplyPermissionId(rs.getInt("ReplyPermission"));
				rep.setReplyPermissionShareRMId(rs.getInt("ReplyPermissionShareRM"));
				replylist.add(rep);
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
		return replylist;

	}
		
	
	
	@Override
	public List<Reply> getAllReplyByQuestionId(int qid) {
		System.out.println("ForumDAOImpl.getAllReplyByQuestionId()" + qid);
		List<Reply> questionlist = new ArrayList<Reply>();
		final String procedureCall = "{call proc_Reply(?,?,?,?)}";
		Connection connection = null;
		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "selectReplyQuesId");
			callableSt.setInt(2, 0);
			callableSt.setString(3, "");
			callableSt.setInt(4, qid);
			ResultSet rs = callableSt.executeQuery();
			while (rs.next()) {
				Reply rep = new Reply();
				rep.setReplyId(rs.getInt("PK_replyId"));
				rep.setQuestionId(rs.getInt("FK_questionId"));
				rep.setQuestionName(rs.getString("Question"));
				rep.setReply(rs.getString("replyContent"));
				rep.setCreatedDate(rs.getDate("createdDate")); // CONVERT(VARCHAR(19),GETDATE())
				rep.setCreatedDate(rs.getDate("createdDate")); 
				rep.setUserName(rs.getString("UserName")==null?"":(rs.getString("UserName").substring(0,1).toUpperCase() +rs.getString("UserName").substring(1)));
				rep.setRoleName(rs.getString("RoleName"));
				System.out.println("rid-" + rs.getInt("PK_replyId") + "qid-  " + rs.getInt("FK_questionId"));
				questionlist.add(rep);
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
		return questionlist;

	}

	@Override
	public String getQuestionByQuestionId(int qid) {


		String questionsById = "";

		final String procedureCall = "{call proc_question(?,?,?)}";
		Connection connection = null;

		try {		
		connection = jdbcTemplate.getDataSource().getConnection();
		CallableStatement callableSt = connection.prepareCall(procedureCall);	
		callableSt.setString(1,"selectQuestionByQid");
		callableSt.setInt(2, qid);		
		callableSt.setString(3, "");		
		System.out.println("question in dao "+questionsById);
		ResultSet rs= callableSt.executeQuery();
		if(rs.next()){
			 System.out.println("question in dao uppp ");
		     questionsById=rs.getString("Question");
		     System.out.println("question in dao "+questionsById);
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
		return questionsById;

	}

	public Map<String,String> getForumData(String forumId) {
		final String procedureCall = "{call proc_Forum(?,?)}";
		Connection connection = null;
		Map<String,String> mapValues=new LinkedHashMap<String,String>();
		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "selectForumData");
			callableSt.setInt(2, Integer.parseInt(forumId));
			ResultSet rs = callableSt.executeQuery();
			while (rs.next()) {
				mapValues.put("forumId",(rs.getString("PK_ForumId")));
				System.out.println("Hello result ser value fouram name:::"+rs.getString("ForumName"));
				mapValues.put("ForumName",rs.getString("ForumName"));
				mapValues.put("editPermission",rs.getString("EditPermission"));
				mapValues.put("deletePermission",rs.getString("DeletePermission"));
				mapValues.put("postPermission",rs.getString("PostPermission"));
				mapValues.put("replyPermission",rs.getString("ReplyPermission"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapValues;
	}
}
