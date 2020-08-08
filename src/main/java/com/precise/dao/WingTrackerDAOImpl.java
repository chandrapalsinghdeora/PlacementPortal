package com.precise.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;
import com.precise.dbconnection.ConnectionDao;
import com.precise.model.WingTracker;
@Repository
public class WingTrackerDAOImpl extends ConnectionDao implements WingTrackerDAO{

    @Override
	public List<WingTracker> getAllSchdular() {
		List<WingTracker> list = new ArrayList<WingTracker>();
		String procedureCall = "{call Sp_AssignStdRole(?)}";
		Connection connection = null;
		try {
			connection = getJdbcTemplate().getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "SelectSchedular");
			ResultSet rs = callableSt.executeQuery();
			
			while (rs.next()) {
				WingTracker wingTracker = new WingTracker();
				//wingTracker.setStudentId(rs.getInt("FK_IIMstudentId"));
				wingTracker.setSedulerName(rs.getString("EmailId"));
				wingTracker.setSedulerId(rs.getInt("PK_placeCommerId"));
				wingTracker.setIimStdId(rs.getInt("Pk_RoleId"));
				//System.out.println("getAssignRoles---- " + rs.getString("userName")+"sid"+rs.getInt("FK_IIMstudentId") +"nogo id-"+rs.getInt("negoId")+" allacitvie "+rs.getBoolean("isActive"));
				list.add(wingTracker);
			}		
	   
		}catch (SQLException e) {
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
	public void saveRoomAllocation(WingTracker wing) {
		//System.out.println("WingTrackerDAOImpl.saveRoomAllocation()");
		final String procedureCall = "{call Proc_WingMapping(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
		Connection connection = null;
		try {	
		connection = getJdbcTemplate().getDataSource().getConnection();
		CallableStatement callableSt = connection.prepareCall(procedureCall);
		callableSt.setString(1, "InsertRoom");
		callableSt.setInt(2, 0);
		callableSt.setInt(3, 0);
		callableSt.setInt(4, 0);
		callableSt.setInt(5, wing.getHidden());
		callableSt.setInt(6, wing.getFirmId());
		callableSt.setInt(7, wing.getRoleId());
		callableSt.setBoolean(8, true);
		callableSt.setInt(9, 0);
		callableSt.setString(10, null);
		callableSt.setInt(11, 0);
		callableSt.setString(12, null);
		callableSt.setString(13, wing.getStartTime());
		callableSt.setString(14, wing.getEndTime());
		callableSt.setInt(15, 0);
		callableSt.setInt(16, wing.getClusterId());
	//	callableSt.setString(3, wing.getWing9Room1());
		
		callableSt.execute();
		
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
	public List<WingTracker> getWingRoomDetails() {
		
		List<WingTracker> list = new ArrayList<WingTracker>();
		String procedureCall = "{call Proc_WingMapping(?)}";
		Connection connection = null;
		try {
			connection = getJdbcTemplate().getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "WingRoomInfo");
			ResultSet rs = callableSt.executeQuery();
			
			while (rs.next()) {
				WingTracker wingTracker = new WingTracker();
				wingTracker.setWingRoomId(rs.getInt("PK_WingRoomId"));
				wingTracker.setWingId(rs.getInt("FK_WingId"));
				wingTracker.setRoomName(rs.getString("RoomName"));
				wingTracker.setWingName(rs.getString("WingName"));
				list.add(wingTracker);
			}		
	   
		}catch (SQLException e) {
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
	public void saveNegoAndSchedular(WingTracker wing,int userid) {
		final String procedureCall = "{call Proc_WingMapping(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
		Connection connection = null;
		try {	
		for(int i=0;i<wing.getNego().size();i++)
		{
			//System.out.println("csd");
		connection = getJdbcTemplate().getDataSource().getConnection();
		CallableStatement callableSt = connection.prepareCall(procedureCall);
		callableSt.setString(1, "Insert");
		callableSt.setInt(2, wing.getSaveupdate().get(i));
		callableSt.setInt(3, wing.getWingId1().get(i));
		callableSt.setInt(4, wing.getNego().get(i));
		callableSt.setInt(5, 0);
		callableSt.setInt(6, 0);
		callableSt.setInt(7, 0);
		callableSt.setBoolean(8, true);
		callableSt.setInt(9, userid);
		callableSt.setString(10, null);
		callableSt.setInt(11, 0);
		callableSt.setString(12, null);
		callableSt.setString(13, null);
		callableSt.setString(14, null);
		callableSt.setInt(15, wing.getSchedular().get(i));
		callableSt.setInt(16, wing.getClusterId());
	//	callableSt.setString(3, wing.getWing9Room1());
		if( (wing.getNego().get(i))!=0 &&  (wing.getSchedular().get(i))!=0)
		callableSt.execute();
		}
		
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
	public void updateRoomAllocation(WingTracker wing) {
		final String procedureCall = "{call Proc_WingMapping(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
		Connection connection = null;
		try {	
		connection = getJdbcTemplate().getDataSource().getConnection();
		CallableStatement callableSt = connection.prepareCall(procedureCall);
		callableSt.setString(1, "updateTime");
		callableSt.setInt(2, 0);
		callableSt.setInt(3, 0);
		callableSt.setInt(4, 0);
		callableSt.setInt(5, wing.getHidden());
		callableSt.setInt(6, 0);
		callableSt.setInt(7, wing.getRoleId());
		callableSt.setBoolean(8, true);
		callableSt.setInt(9, 0);
		callableSt.setString(10, null);
		callableSt.setInt(11, 0);
		callableSt.setString(12, null);
		callableSt.setString(13, wing.getStartTime());
		callableSt.setString(14, wing.getEndTime());
	//	callableSt.setString(3, wing.getWing9Room1());
		
		callableSt.execute();
		
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
	public void deleteroomallocation(WingTracker wing) {
		final String procedureCall = "{call Proc_WingMapping(?,?,?,?)}";
		Connection connection = null;
		try {	
		connection = getJdbcTemplate().getDataSource().getConnection();
		CallableStatement callableSt = connection.prepareCall(procedureCall);
		callableSt.setString(1, "deleteroom");
		callableSt.setInt(2, wing.getWingRoomId());
		callableSt.setInt(3, wing.getFirmId());
		callableSt.setInt(4, wing.getRoleId());
		callableSt.execute();
		
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
	
	
	
}
