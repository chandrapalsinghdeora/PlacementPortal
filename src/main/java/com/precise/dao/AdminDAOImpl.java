package com.precise.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.stereotype.Repository;

import com.precise.dbconnection.ConnectionDao;
import com.precise.model.Admin;

@Repository
public class AdminDAOImpl extends ConnectionDao implements AdminDAO {

	@Override
	public Map<Integer, String> getStudentList() {
		System.out.println("AdminDAOImpl.getStudentList()");
		final String procedureCall = "{call proc_IIMStudent(?)}";
		Connection connection = null;
		Map<Integer,String> student=new LinkedHashMap<Integer,String>();
		try {
			connection = getJdbcTemplate().getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "select");			
			ResultSet rs = callableSt.executeQuery();
			while (rs.next()) {				
				System.out.println("student list in map-"+rs.getString("userName")+"id-  "+rs.getInt("Pk_IIMStdId"));				
				student.put(rs.getInt("Pk_IIMStdId"), rs.getString("EmailId"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return student;
	}

	@Override
	public void saveStudent(final List<Integer> ids) {
		System.out.println("AdminDAOImpl.saveStudent()"+ids);
		
		String query="insert into tbl_trn_placeCommer(FK_IIMstudentId,isActive) values(?,?)";
		
		int[] i=getJdbcTemplate().batchUpdate(query, new BatchPreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				ps.setInt(1, ids.get(i));	
				ps.setBoolean(2, true);
				//ps.setInt(3, 0);
				System.out.println("saveStudent idss in dao-"+ids.get(i));
			}
			
			@Override
			public int getBatchSize() {				
				return  ids.size();
			}
		});
	}

	
	@Override
	public Map<Integer, String> getPlaceComStudentList() {
		System.out.println("AdminDAOImpl.getPlaceComStudentList()");
		final String procedureCall = "{call proc_IIMStudent(?)}";
		Connection connection = null;
		Map<Integer,String> plcaceComStudent=new LinkedHashMap<Integer,String>();
		try {
			connection = getJdbcTemplate().getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "placecomstudent");			
			ResultSet rs = callableSt.executeQuery();
			while (rs.next()) {				
				//System.out.println("place com student list in map-"+rs.getString("userName")+"id-  "+rs.getInt("PK_placeCommerId"));				
				plcaceComStudent.put(rs.getInt("PK_placeCommerId"), rs.getString("EmailId"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return plcaceComStudent;
	}

	@Override
	public void removeStudent(final List<Integer> stuIds) {
		System.out.println("AdminDAOImpl.removeStudent()"+stuIds);
       
		String query="update tbl_trn_placeCommer set isActive=? where PK_placeCommerId=? ";
		
		int[] i=getJdbcTemplate().batchUpdate(query, new BatchPreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				ps.setBoolean(1, false);
				ps.setInt(2, stuIds.get(i));	
				//ps.setInt(3, 0);
				
				System.out.println("removeStudent idss in dao-"+stuIds.get(i));
			}
			
			@Override
			public int getBatchSize() {				
				return  stuIds.size();
			}
		});
		
	}

	@Override
	public Map<Integer, String> getAllPrefix() {
		final String procedureCall = "{call proc_IIMStudent(?)}";
		Connection connection = null;
		Map<Integer,String> prefix=new LinkedHashMap<Integer,String>();
		try {
			connection = getJdbcTemplate().getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "selectPrefix");			
			ResultSet rs = callableSt.executeQuery();
			while (rs.next()) {				
				//System.out.println("prefix in map-"+rs.getString("PreFix")+"id-  "+rs.getInt("groupid"));				
				prefix.put(rs.getInt("groupid"), rs.getString("PreFix"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return prefix;
	}

	@Override
	public Map<Integer, String> getStudentListByPrefix(int prefixId) {
		System.out.println("AdminDAOImpl.getStudentListByPrefix()"+prefixId);
		final String procedureCall = "{call proc_IIMStudent(?,?,?)}";
		Connection connection = null;
		Map<Integer,String> student=new LinkedHashMap<Integer,String>();
		try {
			connection = getJdbcTemplate().getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "selectByPrefix");	
			callableSt.setInt(2, 0);
			callableSt.setInt(3, prefixId);
			ResultSet rs = callableSt.executeQuery();
			while (rs.next()) {				
				System.out.println("student list in prefix map-"+rs.getString("userName")+"id-  "+rs.getInt("Pk_IIMStdId"));				
				student.put(rs.getInt("Pk_IIMStdId"), rs.getString("userName"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return student;
	}

	@Override
	public Map<Integer, String> getPlaceComStudentListByPrefixId(int prefixId) {
		System.out.println("AdminDAOImpl.getPlaceComStudentListByPrefixId()"+prefixId);
		final String procedureCall = "{call proc_IIMStudent(?,?,?)}";
		Connection connection = null;
		Map<Integer,String> student=new LinkedHashMap<Integer,String>();
		try {
			connection = getJdbcTemplate().getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "placecomstudentByPrefix");	
			callableSt.setInt(2, 0);
			callableSt.setInt(3, prefixId);
			ResultSet rs = callableSt.executeQuery();
			while (rs.next()) {				
				System.out.println("student list in prefix map-"+rs.getString("userName")+"id-  "+rs.getInt("PK_placeCommerId"));				
				student.put(rs.getInt("PK_placeCommerId"), rs.getString("userName"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return student;
	}

	@Override
	public Map<Integer, String> getMentor() {
		System.out.println("AdminDAOImpl.getMentor()");
		final String procedureCall = "{call proc_mentor(?)}";
		Connection connection = null;
		Map<Integer,String> mentor=new LinkedHashMap<Integer,String>();
		try {
			connection = getJdbcTemplate().getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "selectMentor");			
			ResultSet rs = callableSt.executeQuery();
			while (rs.next()) {				
				System.out.println("prefix in map-"+rs.getString("userName")+"id-  "+rs.getInt("FK_IIMStudent"));				
				mentor.put(rs.getInt("FK_IIMStudent"), rs.getString("userName"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mentor;
	}

	@Override
	public void saveMentorStudent(final Admin admin) {
		System.out.println("AdminDAOImpl.saveMentorStudent()"+admin.getStudentIds());
        String query="insert into tbl_trn_StdMentor(Fk_StudentId,Fk_MentorId,isActive) values(?,?,?)";
		
        final List<Integer> stuIds=admin.getStudentIds();
        
		int[] i=getJdbcTemplate().batchUpdate(query, new BatchPreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				ps.setInt(1, stuIds.get(i));	
				ps.setInt(2, admin.getMentorId());
				ps.setBoolean(3, true);
				System.out.println("saveStudent idss in dao-"+stuIds.get(i));
			}
			
			@Override
			public int getBatchSize() {				
				return  stuIds.size();
			}
		});
	}

	@Override
	public Map<Integer, String> getMentorAllStudent() {
		System.out.println("AdminDAOImpl.getMentorAllStudent()");
		final String procedureCall = "{call proc_mentor(?)}";
		Connection connection = null;
		Map<Integer,String> student=new LinkedHashMap<Integer,String>();
		try {
			connection = getJdbcTemplate().getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "selectStudentsForMentor");			
			ResultSet rs = callableSt.executeQuery();
			while (rs.next()) {				
				System.out.println("student list in map-"+rs.getString("userName")+"id-  "+rs.getInt("Pk_StdMentor"));				
				student.put(rs.getInt("Pk_StdMentor"), rs.getString("userName"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return student;
	}

	@Override
	public Map<Integer, String> getMentorStudentList() {
		//System.out.println("AdminDAOImpl.getStudentList()");
		final String procedureCall = "{call proc_mentor(?)}";
		Connection connection = null;
		Map<Integer,String> student=new LinkedHashMap<Integer,String>();
		try {
			connection = getJdbcTemplate().getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "selectStudentList");			
			ResultSet rs = callableSt.executeQuery();
			while (rs.next()) {				
				//System.out.println("student list in map-"+rs.getString("userName")+"id-  "+rs.getInt("Pk_IIMStdId"));				
				student.put(rs.getInt("Pk_IIMStdId"), rs.getString("userName"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return student;
	}

	@Override
	public void removeMentorStudent(final List<Integer> stuIds) {
		//System.out.println("AdminDAOImpl.removeMentorStudent()"+stuIds);
			       
		String query="update tbl_trn_StdMentor set isActive=? where Pk_StdMentor=? ";
		
		int[] i=getJdbcTemplate().batchUpdate(query, new BatchPreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				ps.setBoolean(1, false);
				ps.setInt(2, stuIds.get(i));	
				
				System.out.println("removeMentorStudent ids in dao-"+stuIds.get(i));
			}
			
			@Override
			public int getBatchSize() {				
				return  stuIds.size();
			}
		});
		
	}

	@Override
	public Map<Integer, String> getMentorStudentListByPrefixId(int prefixId) {
		System.out.println("AdminDAOImpl.getMentorStudentListByPrefixId()");
		final String procedureCall = "{call proc_mentor(?,?)}";
		Connection connection = null;
		Map<Integer,String> student=new LinkedHashMap<Integer,String>();
		try {
			connection = getJdbcTemplate().getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "selectStudentListByPrefix");
			callableSt.setInt(2, prefixId);
			ResultSet rs = callableSt.executeQuery();
			while (rs.next()) {				
				System.out.println("selectStudentListByPrefix list in map-"+rs.getString("userName")+"id-  "+rs.getInt("Pk_IIMStdId"));				
				student.put(rs.getInt("Pk_IIMStdId"), rs.getString("userName"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return student;
	}

	@Override
	public Map<Integer, String> getMentorStudentListByMentorId(int mentorId) {
		System.out.println("AdminDAOImpl.getMentorStudentListByMentorId()");
		final String procedureCall = "{call proc_mentor(?,?,?)}";
		Connection connection = null;
		Map<Integer,String> student=new LinkedHashMap<Integer,String>();
		try {
			connection = getJdbcTemplate().getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "selectStudentByMentor");
			callableSt.setInt(2,0);
			callableSt.setInt(3, mentorId);			
			ResultSet rs = callableSt.executeQuery();
			while (rs.next()) {				
				System.out.println("getMentorStudentListByMentorId list in map-"+rs.getString("userName")+"id-  "+rs.getInt("Pk_StdMentor"));				
				student.put(rs.getInt("Pk_StdMentor"), rs.getString("userName"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return student;
	}

	@Override
	public Map<Integer, String> getPgpOneStudentList() {
		System.out.println("AdminDAOImpl.getPgpOneStudentList()");
		final String procedureCall = "{call proc_IIMStudent(?)}";
		Connection connection = null;
		Map<Integer,String> student=new LinkedHashMap<Integer,String>();
		try {
			connection = getJdbcTemplate().getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "selectPGP1Student");			
			ResultSet rs = callableSt.executeQuery();
			while (rs.next()) {				
				System.out.println("student list in map-"+rs.getString("userName")+"id-  "+rs.getInt("Pk_IIMStdId"));				
				student.put(rs.getInt("Pk_IIMStdId"), rs.getString("EmailId"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return student;
	}
	
	@Override
	public void convertPgpOneToTwo(Admin admin) {
		System.out.println("AdminDAOImpl.convertPgpOneToTwo()" +admin.getStudentIds());
		final List<Integer> studentList=admin.getStudentIds();
		Connection connection = null;
		CallableStatement callableSt = null;
		try {
			connection = getJdbcTemplate().getDataSource().getConnection();
			String procedureCall = "{call proc_IIMStudent(?,?,?,?)}";
			callableSt = connection.prepareCall(procedureCall);
			if (studentList.size() > 0) {
				for (int i = 0; i < studentList.size(); i++) {					
					callableSt.setString(1, "updatePGP");
					callableSt.setInt(2,0);
					callableSt.setInt(3,2);
					callableSt.setInt(4, studentList.get(i));					
					callableSt.addBatch();
				}
				callableSt.executeBatch();
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
	}

	
	@Override
	public void convertPgpTwoToOne(Admin admin) {
		System.out.println("AdminDAOImpl.convertPgpTwoToOne()" +admin.getStudentIds());
		final List<Integer> studentList=admin.getStudentIds();
		Connection connection = null;
		CallableStatement callableSt = null;
		try {
			connection = getJdbcTemplate().getDataSource().getConnection();
			String procedureCall = "{call proc_IIMStudent(?,?,?,?)}";
			callableSt = connection.prepareCall(procedureCall);
			if (studentList.size() > 0) {
				for (int i = 0; i < studentList.size(); i++) {					
					callableSt.setString(1, "updatePGP");
					callableSt.setInt(2,0);
					callableSt.setInt(3,1);
					callableSt.setInt(4, studentList.get(i));					
					callableSt.addBatch();
				}
				callableSt.executeBatch();
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
	}
	
	@Override
	public Map<Integer, String> getPgpTwoStudentList() {
		System.out.println("AdminDAOImpl.getPgpTwoStudentList()");
		final String procedureCall = "{call proc_IIMStudent(?)}";
		Connection connection = null;
		Map<Integer,String> student=new LinkedHashMap<Integer,String>();
		try {
			connection = getJdbcTemplate().getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "selectPGP2Student");			
			ResultSet rs = callableSt.executeQuery();
			while (rs.next()) {				
				System.out.println("student list in map-"+rs.getString("userName")+"id-  "+rs.getInt("Pk_IIMStdId"));				
				student.put(rs.getInt("Pk_IIMStdId"), rs.getString("EmailId"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return student;
	}

	@Override
	public void assignExperience(Admin admin) {
		System.out.println("AdminDAOImpl.assignExperience()"+admin.getGroupId()+"interv exp-"+admin.getInterviewExperienceVal());
	
		Connection connection = null;
		CallableStatement callableSt = null;
		try {
			connection = getJdbcTemplate().getDataSource().getConnection();
			String procedureCall = "{call proc_IIMStudent(?,?,?,?,?,?)}";
			callableSt = connection.prepareCall(procedureCall);								
			callableSt.setString(1, "assignExperience");
			callableSt.setInt(2, 0);
			callableSt.setInt(3, admin.getGroupId());
			callableSt.setInt(4, 0);
			callableSt.setBoolean(5,admin.getInternshipExperience());
			callableSt.setBoolean(6,admin.getInterviewExperience());						
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
	public List<Admin> getExperienceDataByPGP(int id) {
		System.out.println("AdminDAOImpl.getExperienceDataByPGP-()"+id);
		final String procedureCall = "{call proc_IIMStudent(?,?,?)}";
		Connection connection = null;
		List<Admin> exp=new ArrayList<Admin>();
		try {
			connection = getJdbcTemplate().getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "selectExperince");	
			callableSt.setInt(2,0);
			callableSt.setInt(3,id);
			ResultSet rs = callableSt.executeQuery();
			while (rs.next()) {		
				Admin adm=new Admin();
				System.out.println("pgp in gid-"+rs.getInt("groupid")+"internship-  "+rs.getBoolean("internshipExperience")+"interview-"+rs.getBoolean("interviewExperience"));				
				adm.setGroupId(rs.getInt("groupid"));
				adm.setInternshipExperience(rs.getBoolean("internshipExperience"));
				adm.setInterviewExperience(rs.getBoolean("interviewExperience"));
				exp.add(adm);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return exp;
	}
	
	
	
	
     
}
