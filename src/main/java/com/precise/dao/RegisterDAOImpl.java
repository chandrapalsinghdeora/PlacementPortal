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
import org.springframework.stereotype.Repository;
import com.precise.model.Question;
import com.precise.model.Register;

@Repository("rep")
public class RegisterDAOImpl implements RegisterDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void saveRegistration(Register reg) {
		System.out.println("RegisterDAOImpl.saveRegistration()" + reg);
		String query = "insert into tbl_mst_user(UserEnrollmentNumber ,LoginPassword ,UserName ,UserNickName"
				+ " ,DateOfBirth,GuardianName ,UserAddress,UserCity ,UserState ,UserCountry"
				+ ",UserZIPCode ,UserPhone ,UserEmailId ,UserMobileNumber,UserGender "
				+ ",IsActive ,CreatedBy,UserRoleType" + ") values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

		Object[] args = new Object[] { reg.getUserEnrollmentNumber(), reg.getLoginPassword(), reg.getUserName(),
				reg.getUserNickName(), reg.getDateOfBirth(), reg.getGuardianName(), reg.getUserAddress(),
				reg.getUserCity(), reg.getUserState(), reg.getUserCountry(), reg.getUserZIPCode(), reg.getUserPhone(),
				reg.getUserEmailId(), reg.getUserMobileNumber(), reg.getUserGender(), reg.getIsActive(), 0,
				reg.getUserRoleType() };

		jdbcTemplate.update(query, args);
		// System.out.println("query - " + query + "\n arguments-" + args);
		for (Object ob : args) {
			// System.out.println(ob);
		}
	}

	@Override
	public void saveQuestions(Question ques, int userId, int roleId) {
		// System.out
		// .println("RegisterDAOImpl.saveQuestions()" + ques.getQuestion() +
		// "userid" + ques.getPostPermission());

		String query = "insert into tbl_mst_question(Question,userId,FK_RoleId,FK_ThreadId,IsActive) values(?,?,?,?,1)";

		Object[] args = new Object[] { ques.getQuestion(), userId, roleId, ques.getThreadId() };

		jdbcTemplate.update(query, args);

	}

	/*
	 * @Override public List<Question> getAllQuestion() { String sql =
	 * "SELECT * FROM tbl_mst_question"; List<Question> listContact =
	 * jdbcTemplate.query(sql, new RowMapper<Question>() {
	 * 
	 * @Override public Question mapRow(ResultSet rs, int rowNum) throws
	 * SQLException { Question ques = new Question();
	 * ques.setQuestionId(rs.getInt("PK_quesId"));
	 * ques.setQuestion(rs.getString("Question")); System.out.println("fname "
	 * +rs.getString("Question")+" form id "+rs.getInt("PK_quesId")); return
	 * ques; } }); return listContact; }
	 */

	@Override
	public List<Question> getAllQuestion(String threadId) {
		System.out.println("Thread id in getAllQuestion method::"+threadId);
		final String procedureCall = "{call proc_question(?,?)}";
		Connection connection = null;
		List<Question> questionList = new ArrayList<Question>();
		Question ques = null;
		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "SelectQuestions");
			callableSt.setInt(2, Integer.parseInt(threadId));
			ResultSet rs = callableSt.executeQuery();
			while (rs.next()) {
				ques = new Question();
				ques.setQuestionId(rs.getInt("PK_quesId"));
				ques.setQuestion(rs.getString("Question"));
				ques.setQuestionCount(rs.getString("COUNT"));
				ques.setUserId(rs.getString("PK_UserId"));
				ques.setUserName(rs.getString("UserName"));
				ques.setReplyContent(rs.getString("replyContent"));
			//	ques.setThreadId(rs.getInt("PK_ThreadId"));
				ques.setCompanyName(rs.getString("CompanyName"));
				ques.setProcessName(rs.getString("ProcessName"));
				ques.setYear(rs.getInt("Year"));
				questionList.add(ques);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return questionList;
		/*
		 * StringBuilder sqlQuery = new StringBuilder();
		 * System.out.println("threadId::"+threadId); sqlQuery.append(
		 * "select PK_UserId,UserName,PK_quesId,Question,count(FK_questionId)as COUNT  from tbl_mst_question q"
		 * ) .append(
		 * " left join [tbl_trn_reply] r  on q.PK_quesId=r.FK_questionId inner join tbl_mst_user u on"
		 * ) .append(" u.PK_UserId=q.userId and FK_ThreadId="+threadId+
		 * " and q.IsActive=1 group by PK_quesId,Question,PK_UserId,UserName");
		 * System.out.println("sqlQuery:::"+sqlQuery.toString()); List<Question>
		 * listContact = jdbcTemplate.query(sqlQuery.toString(), new
		 * RowMapper<Question>() {
		 * 
		 * @Override public Question mapRow(ResultSet rs, int rowN1um) throws
		 * SQLException { Question ques = new Question();
		 * ques.setQuestionId(rs.getInt("PK_quesId"));
		 * ques.setQuestion(rs.getString("Question"));
		 * ques.setQuestionCount(rs.getString("COUNT"));
		 * ques.setUserId(rs.getString("PK_UserId"));
		 * ques.setUserName(rs.getString("UserName")); return ques; } }); return
		 * listContact;
		 */
	}

	@Override
	public Map<String, String> getQuestionData(String questionId) {
		final String procedureCall = "{call proc_question(?,?,?)}";
		Connection connection = null;
		Map<String, String> mapValues = new LinkedHashMap<String, String>();
		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "selectQuestionByQid");
			callableSt.setInt(2, Integer.parseInt(questionId));
			callableSt.setString(3, "");
			ResultSet rs = callableSt.executeQuery();
			while (rs.next()) {
				mapValues.put("questionId", rs.getString("PK_quesId"));
				mapValues.put("question", rs.getString("Question"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapValues;
	}

	@Override
	public String updateQuestionData(String question, String questionId) {
		final String procedureCall = "{call proc_question(?,?,?)}";
		Connection connection = null;
		String returnValue = "failed";
		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "updateQuestionValues");
			callableSt.setInt(2, Integer.parseInt(questionId));
			callableSt.setString(3, question);
			callableSt.execute();
			returnValue = "success";
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

	public String deleteQuestion(String questionId) {
		final String procedureCall = "{call proc_question(?,?,?)}";
		Connection connection = null;
		String returnValue = "failed";
		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "deleteQuestionValues");
			callableSt.setInt(2, Integer.parseInt(questionId));
			callableSt.setString(3, "");
			callableSt.execute();
			returnValue = "success";
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
	public List<Question> getComQuestions(String parameter) {
		final String procedureCall = "{call proc_question(?,?)}";
		Connection connection = null;
		List<Question> questionList = new ArrayList<Question>();
		Question ques = null;
		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "SelectCompQuest");
			callableSt.setInt(2, Integer.parseInt(parameter));
			ResultSet rs = callableSt.executeQuery();
			while (rs.next()) {
				ques = new Question();
				ques.setQuestionId(rs.getInt("PK_quesId"));
				ques.setQuestion(rs.getString("Question"));
				ques.setQuestionCount(rs.getString("COUNT"));
				ques.setUserId(rs.getString("userId"));
				ques.setUserName(rs.getString("UserName"));
				ques.setReplyContent(rs.getString("replyContent"));
				ques.setThreadId(rs.getInt("PK_ThreadId"));
				ques.setCompanyName(rs.getString("CompanyName"));
				ques.setProcessName(rs.getString("ProcessName"));
				ques.setYear(rs.getInt("Year"));
			
				questionList.add(ques);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return questionList;
		// return null;
	}
	public String getThreadId(String companyId) throws SQLException {
		final String procedureCall = "{call proc_question(?,?)}";
		Connection connection = null;
		connection = jdbcTemplate.getDataSource().getConnection();
		CallableStatement callableSt = connection.prepareCall(procedureCall);
		callableSt.setString(1, "selectThreadId");
		callableSt.setInt(2, Integer.parseInt(companyId));
		ResultSet rs = callableSt.executeQuery();
		String threadId="";
		while (rs.next()) {
			threadId=rs.getString("PK_ThreadId");
		}
		return threadId;
	}
}
