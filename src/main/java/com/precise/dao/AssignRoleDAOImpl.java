package com.precise.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.stereotype.Repository;

import com.precise.dbconnection.ConnectionDao;
import com.precise.model.AssignRole;
@Repository
public class AssignRoleDAOImpl extends ConnectionDao implements AssingRoleDAO{

	@Override
	public List<AssignRole> getAssignRoles() {
		//System.out.println("AssignRoleDAOImpl.getAssignRoles()");
		
		List<AssignRole> list = new ArrayList<AssignRole>();
		String procedureCall = "{call Sp_AssignStdRole(?)}";
		Connection connection = null;
		try {
			connection = getJdbcTemplate().getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "select");
			ResultSet rs = callableSt.executeQuery();
			
			while (rs.next()) {
				AssignRole assign = new AssignRole();
				assign.setStudentId(rs.getInt("FK_IIMstudentId"));
				assign.setStudentName(rs.getString("EmailId"));
				assign.setRmId(rs.getInt("Mentor"));
				assign.setMentorId(rs.getInt("RelationshipManager"));
				assign.setActive(rs.getBoolean("isActive"));
				assign.setSchedulingAdminId(rs.getInt("schedulingAdminId"));
				assign.setNegoId(rs.getInt("negoId"));
				assign.setSideTrackerId(rs.getInt("sideTrackerId"));
				assign.setOfferProcessorId(rs.getInt("offerProcessorId"));
				assign.setSuperRMOnTheDayId(rs.getInt("superRMOnTheDayId"));
				assign.setSuperRMThroughoutTheYearId(rs.getInt("superRMThroughoutTheYearId"));
				assign.setStartoAdminId(rs.getInt("startoAdminId"));
				assign.setPrintingTeamId(rs.getInt("printingTeamId"));
				assign.setSatcomId(rs.getInt("satcomId"));
				assign.setCoordinatorId(rs.getInt("coordinator"));
				assign.setSchedulerId(rs.getInt("scheduler"));
				//System.out.println("getAssignRoles---- " + rs.getString("userName")+"sid"+rs.getInt("FK_IIMstudentId") +"nogo id-"+rs.getInt("negoId")+" allacitvie "+rs.getBoolean("isActive"));
				list.add(assign);
			}
			rs= null;
			for(int i=0;i<list.size();i++){
				AssignRole assign = list.get(i);
				procedureCall = "{call Sp_AssignStdRole(?,?)}";
				connection = getJdbcTemplate().getDataSource().getConnection();
				callableSt = connection.prepareCall(procedureCall);
				callableSt.setString(1, "selectRoles");
				callableSt.setInt(2, assign.getStudentId());
				rs = callableSt.executeQuery();
				
				while (rs.next()) {
					//System.out.println("getAssignRoles---- " + rs.getString("FK_RoleID"));
					if(rs.getInt("FK_RoleID")==3){
						list.get(i).setMentorId(rs.getInt("FK_RoleID"));
						list.get(i).setMentorActive(rs.getBoolean("isActive"));
						//System.out.println("setMentorId---- " + rs.getString("FK_RoleID") +" mentoracitvie "+rs.getBoolean("isActive"));
					}
					else if(rs.getInt("FK_RoleID")==4){
						list.get(i).setRmId(rs.getInt("FK_RoleID"));
						list.get(i).setRmActive(rs.getBoolean("isActive"));
						//System.out.println("setRmId---- " + rs.getString("FK_RoleID")+"   rm active-"+rs.getBoolean("isActive"));
					}
					else if(rs.getInt("FK_RoleID")==9){
						list.get(i).setStartoAdminId(rs.getInt("FK_RoleID"));
						list.get(i).setStartoAdminActive(rs.getBoolean("isActive"));
						//System.out.println("setStartoAdminId---- " + rs.getString("FK_RoleID")+"   schedulingAdminId active-"+rs.getBoolean("isActive"));
					}
					else if(rs.getInt("FK_RoleID")==19){
						list.get(i).setPrintingTeamId(rs.getInt("FK_RoleID"));
						list.get(i).setPrintingTeamActive(rs.getBoolean("isActive"));
						//System.out.println("setPrintingTeamId---- " + rs.getString("FK_RoleID")+"   schedulingAdminId active-"+rs.getBoolean("isActive"));
					}
					else if(rs.getInt("FK_RoleID")==20){
						list.get(i).setSatcomId(rs.getInt("FK_RoleID"));
						list.get(i).setSatcomActive(rs.getBoolean("isActive"));
						//System.out.println("setSatcomId---- " + rs.getString("FK_RoleID")+"   schedulingAdminId active-"+rs.getBoolean("isActive"));
					}
					else if(rs.getInt("FK_RoleID")==21){
						list.get(i).setSchedulingAdminId(rs.getInt("FK_RoleID"));
						list.get(i).setSchedulingAdminActive(rs.getBoolean("isActive"));
						//System.out.println("setSchedulingAdminId---- " + rs.getString("FK_RoleID")+"   schedulingAdminId active-"+rs.getBoolean("isActive"));
					}
					else if(rs.getInt("FK_RoleID")==48){
						list.get(i).setNegoId(rs.getInt("FK_RoleID"));
						list.get(i).setNegoActive(rs.getBoolean("isActive"));
						//System.out.println("setNegoId---- " + rs.getString("FK_RoleID")+"   setNegoId active-"+rs.getBoolean("isActive"));
					}
					else if(rs.getInt("FK_RoleID")==17){
						list.get(i).setSideTrackerId(rs.getInt("FK_RoleID"));
						list.get(i).setSideTrackerActive(rs.getBoolean("isActive"));
						//System.out.println("setNegoId---- " + rs.getString("FK_RoleID")+"   setNegoId active-"+rs.getBoolean("isActive"));
					}
					else if(rs.getInt("FK_RoleID")==8){
						list.get(i).setOfferProcessorId(rs.getInt("FK_RoleID"));
						list.get(i).setOfferProcessorActive(rs.getBoolean("isActive"));
						//System.out.println("setNegoId---- " + rs.getString("FK_RoleID")+"   setNegoId active-"+rs.getBoolean("isActive"));
					}
					else if(rs.getInt("FK_RoleID")==49){
						list.get(i).setSuperRMOnTheDayId(rs.getInt("FK_RoleID"));
						list.get(i).setSuperRMOnTheDayActive(rs.getBoolean("isActive"));
						//System.out.println("setSuperRMOnTheDayId---- " + rs.getString("FK_RoleID")+"   rm active-"+rs.getBoolean("isActive"));
					}
					else if(rs.getInt("FK_RoleID")==50){
						list.get(i).setSuperRMThroughoutTheYearId(rs.getInt("FK_RoleID"));
						list.get(i).setSuperRMThroughoutTheYearActive(rs.getBoolean("isActive"));
						//System.out.println("setSuperRMThroughoutTheYearId---- " + rs.getString("FK_RoleID")+"   rm active-"+rs.getBoolean("isActive"));
					}
					else if(rs.getInt("FK_RoleID")==55){
						list.get(i).setCoordinatorId(rs.getInt("FK_RoleID"));
						list.get(i).setCoordinatorActive(rs.getBoolean("isActive"));
						//System.out.println("setCoordinatorId---- " + rs.getString("FK_RoleID")+"   setCoordinatorId active-"+rs.getBoolean("isActive"));
					}
					else if(rs.getInt("FK_RoleID")==56){
						list.get(i).setSchedulerId(rs.getInt("FK_RoleID"));
						list.get(i).setSchedulerActive(rs.getBoolean("isActive"));
						System.out.println("setSchedulerId---- " + rs.getString("FK_RoleID")+"   setSchedulerId active-"+rs.getBoolean("isActive"));
					}
					
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
	public void saveAssignedRoles(final AssignRole role) {		
		  //System.out.println("AssignRoleDAOImpl.saveAssignedRoles()" +role.getStudentId()+role.getMentorId());
			String procedureCall = "{call Sp_AssignStdRole(?,?,?)}";
			Connection connection = null;
			try {
				connection = getJdbcTemplate().getDataSource().getConnection();
				CallableStatement callableSt = connection.prepareCall(procedureCall);
				if(role.getMentorId()==3){
					//System.out.println("mentor inside");
					callableSt.setString(1, "insertRoles");
					callableSt.setInt(2, role.getStudentId());
					callableSt.setInt(3, role.getMentorId());
					callableSt.execute();
				}
				
				if(role.getRmId()==4){
					//System.out.println("rm inside");
					callableSt.setString(1, "insertRoles");
					callableSt.setInt(2, role.getStudentId());
					callableSt.setInt(3, role.getRmId());
					callableSt.execute();
				}
				if(role.getStartoAdminId()==9){
					//System.out.println("startoAdminId inside");
					callableSt.setString(1, "insertRoles");
					callableSt.setInt(2, role.getStudentId());
					callableSt.setInt(3, role.getStartoAdminId());
					callableSt.execute();
				}
				if(role.getPrintingTeamId()==19){
					//System.out.println("printingTeamId inside");
					callableSt.setString(1, "insertRoles");
					callableSt.setInt(2, role.getStudentId());
					callableSt.setInt(3, role.getPrintingTeamId());
					callableSt.execute();
				}
				if(role.getSatcomId()==20){
					//System.out.println("getSatcomId inside");
					callableSt.setString(1, "insertRoles");
					callableSt.setInt(2, role.getStudentId());
					callableSt.setInt(3, role.getSatcomId());
					callableSt.execute();
				}
				if(role.getSchedulingAdminId()==21){
					//System.out.println("rm inside");
					callableSt.setString(1, "insertRoles");
					callableSt.setInt(2, role.getStudentId());
					callableSt.setInt(3, role.getSchedulingAdminId());
					callableSt.execute();
				}
				if(role.getNegoId()==48){
					//System.out.println("getNegoId inside");
					callableSt.setString(1, "insertRoles");
					callableSt.setInt(2, role.getStudentId());
					callableSt.setInt(3, role.getNegoId());
					callableSt.execute();
				}
				if(role.getSideTrackerId()==17){
					//System.out.println("getNegoId inside");
					callableSt.setString(1, "insertRoles");
					callableSt.setInt(2, role.getStudentId());
					callableSt.setInt(3, role.getSideTrackerId());
					callableSt.execute();
				}
				if(role.getOfferProcessorId()==8){
					//System.out.println("getNegoId inside");
					callableSt.setString(1, "insertRoles");
					callableSt.setInt(2, role.getStudentId());
					callableSt.setInt(3, role.getOfferProcessorId());
					callableSt.execute();
				}
				if(role.getSuperRMOnTheDayId()==49){
					//System.out.println("getSuperRMOnTheDayId inside");
					callableSt.setString(1, "insertRoles");
					callableSt.setInt(2, role.getStudentId());
					callableSt.setInt(3, role.getSuperRMOnTheDayId());
					callableSt.execute();
				}
				if(role.getSuperRMThroughoutTheYearId()==50){
					//System.out.println("getSuperRMThroughoutTheYearId inside");
					callableSt.setString(1, "insertRoles");
					callableSt.setInt(2, role.getStudentId());
					callableSt.setInt(3, role.getSuperRMThroughoutTheYearId());
					callableSt.execute();
				}
				if(role.getCoordinatorId()==55){
					//System.out.println("getCoordinatorId inside");
					callableSt.setString(1, "insertRoles");
					callableSt.setInt(2, role.getStudentId());
					callableSt.setInt(3, role.getCoordinatorId());
					callableSt.execute();
				}
				if(role.getSchedulerId()==56){
					System.out.println("getSchedulerId insert inside");
					callableSt.setString(1, "insertRoles");
					callableSt.setInt(2, role.getStudentId());
					callableSt.setInt(3, role.getSchedulerId());
					callableSt.execute();
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
	public AssignRole getRolesByStudentId(int studentId) {
		AssignRole assign = new AssignRole();
		String procedureCall = "{call Sp_AssignStdRole(?)}";
		Connection connection = null;
		try {
			connection = getJdbcTemplate().getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "selectById");
			callableSt.setInt(2, studentId);
			ResultSet rs = callableSt.executeQuery();
			
			while (rs.next()) {			
				assign.setStudentId(rs.getInt("FK_IIMstudentId"));
				assign.setStudentName(rs.getString("userName"));
				assign.setRmId(rs.getInt("Mentor"));
				assign.setMentorId(rs.getInt("RelationshipManager"));	
				assign.setSchedulingAdminId(rs.getInt("schedulingAdminId"));
				assign.setNegoId(rs.getInt("negoId"));
				assign.setSuperRMOnTheDayId(rs.getInt("superRMOnTheDayId"));
				assign.setSuperRMThroughoutTheYearId(rs.getInt("superRMThroughoutTheYearId"));
				assign.setStartoAdminId(rs.getInt("startoAdminId"));
				assign.setPrintingTeamId(rs.getInt("printingTeamId"));
				assign.setSatcomId(rs.getInt("satcomId"));
				/*assign.setCoordinatorId(rs.getInt("coordinator"));
				assign.setSchedulerId(rs.getInt("scheduler"));*/
				//System.out.println("getAssignRoles---- " + rs.getString("userName")+"sid"+rs.getInt("FK_IIMstudentId"));
				
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
		return assign;

	}

	@Override
	public void updateAssignedRoles(AssignRole role) {
		//System.out.println("AssignRoleDAOImpl.updateAssignedRoles() sid-" +role.getStudentId()+"mid-"+role.getMentorId()+"rid-"+role.getRmId());
		String procedureCall = "{call Sp_AssignStdRole(?,?,?)}";
		Connection connection = null;
		try {
			connection = getJdbcTemplate().getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
						
			if(role.getMentorId()==1){
				//System.out.println("updatetRoles ");
				callableSt.setString(1, "updateRoles");
				callableSt.setInt(2, role.getStudentId());
				callableSt.setInt(3, 3);
				System.out.println("callableSt.execute();--"+callableSt.execute());
				callableSt.execute();
			}
			
			if(role.getRmId()==1){
				//System.out.println("rm updatetRoles-"+ role.getStudentId());
				callableSt.setString(1, "updateRoles");
				callableSt.setInt(2, role.getStudentId());
				callableSt.setInt(3, 4);
				System.out.println("callableSt.execute();--"+callableSt.execute());
				callableSt.execute();
			}
			
			if(role.getStartoAdminId()==1){
				//System.out.println("startoAdminId inside");
				callableSt.setString(1, "updateRoles");
				callableSt.setInt(2, role.getStudentId());
				callableSt.setInt(3, 9);
				callableSt.execute();
			}
			if(role.getPrintingTeamId()==1){
				//System.out.println("printingTeamId inside");
				callableSt.setString(1, "updateRoles");
				callableSt.setInt(2, role.getStudentId());
				callableSt.setInt(3, 19);
				callableSt.execute();
			}
			if(role.getSatcomId()==1){
				//System.out.println("getSatcomId inside");
				callableSt.setString(1, "updateRoles");
				callableSt.setInt(2, role.getStudentId());
				callableSt.setInt(3, 20);
				callableSt.execute();
			}
			if(role.getSchedulingAdminId()==1){
				//System.out.println("getSchedulingAdminId inside");
				callableSt.setString(1, "updateRoles");
				callableSt.setInt(2, role.getStudentId());
				callableSt.setInt(3, 21);
				callableSt.execute();
			}
			if(role.getNegoId()==1){
				//System.out.println("getNegoId inside");
				callableSt.setString(1, "updateRoles");
				callableSt.setInt(2, role.getStudentId());
				callableSt.setInt(3, 48);
				callableSt.execute();
			}
			if(role.getSideTrackerId()==1){
				//System.out.println("getNegoId inside");
				callableSt.setString(1, "updateRoles");
				callableSt.setInt(2, role.getStudentId());
				callableSt.setInt(3, 17);
				callableSt.execute();
			}
			if(role.getOfferProcessorId()==1){
				//System.out.println("getNegoId inside");
				callableSt.setString(1, "updateRoles");
				callableSt.setInt(2, role.getStudentId());
				callableSt.setInt(3, 8);
				callableSt.execute();
			}
			if(role.getSuperRMOnTheDayId()==1){
				//System.out.println("getSuperRMOnTheDayId inside");
				callableSt.setString(1, "updateRoles");
				callableSt.setInt(2, role.getStudentId());
				callableSt.setInt(3, 49);
				callableSt.execute();
			}
			if(role.getSuperRMThroughoutTheYearId()==1){
				//System.out.println("getSuperRMThroughoutTheYearId inside");
				callableSt.setString(1, "updateRoles");
				callableSt.setInt(2, role.getStudentId());
				callableSt.setInt(3, 50);
				callableSt.execute();
			}
			
			if(role.getCoordinatorId()==1){
				//System.out.println("getCoordinatorId inside");
				callableSt.setString(1, "updateRoles");
				callableSt.setInt(2, role.getStudentId());
				callableSt.setInt(3, 55);
				callableSt.execute();
			}
			if(role.getSchedulerId()==1){
				System.out.println("getSchedulerId inside");
				callableSt.setString(1, "updateRoles");
				callableSt.setInt(2, role.getStudentId());
				callableSt.setInt(3, 56);
				callableSt.execute();
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
	
	
    
}
