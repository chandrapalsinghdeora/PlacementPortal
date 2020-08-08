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

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Repository;

import com.precise.dbconnection.ConnectionDao;
import com.precise.model.PanelAllocation;
import com.precise.model.WingTracker;
@Repository("panelDao")
public class PanelAllocationDAOImpl extends ConnectionDao implements PanelAllocationDAO{

	@Override
	public List<PanelAllocation> getFirmNameByCid(int cid,int roomid) {
		//System.out.println("PanelAllocationDAOImpl.getFirmNameByCid()"+cid);
		List<PanelAllocation> list = new ArrayList<PanelAllocation>();
		final String procedureCall = "{call proc_PanelAlocation(?,?,?)}";
		Connection connection=null;
		try {
			connection = getJdbcTemplate().getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "firmInRoomAllocation");
			callableSt.setInt(2, roomid);
			callableSt.setInt(3, cid);
			ResultSet rs = callableSt.executeQuery();
			
			while (rs.next()) {
				PanelAllocation panelAllocation=new PanelAllocation();
				panelAllocation.setClusterId(rs.getInt("Fk_ClusterId"));
				panelAllocation.setFirmId(rs.getInt("Fk_ApplicationId"));
				panelAllocation.setFirmName(rs.getString("CompanyName"));
				list.add(panelAllocation);
				
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
	public PanelAllocation getRmNameByFirmId(int firmId) {
		System.out.println("PanelAllocationDAOImpl.getRmNameByFirmId()"+firmId);		
		PanelAllocation panelAllocation=new PanelAllocation();	
		final String procedureCall = "{call proc_PanelAlocation(?,?,?,?)}";
		Connection connection=null;
		try {
			connection = getJdbcTemplate().getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "selectRMByFirmId");
			callableSt.setInt(2, 0);
			callableSt.setInt(3, 0);
			callableSt.setInt(4, firmId);
			ResultSet rs = callableSt.executeQuery();
			
			while (rs.next()) {							
				panelAllocation.setRmName(rs.getString("RmName"));	
				panelAllocation.setEmailId(rs.getString("EmailId"));
				
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
		return panelAllocation;
	}

	@Override
	public void savePanelAllocation(PanelAllocation pallocation) {
		//System.out.println("PanelAllocationDAOImpl.savePanelAllocation()"+pallocation.getIsLocked());
		
		final String procedureCall = "{call proc_PanelAlocation(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
		Connection connection = null;
		try {
			connection = getJdbcTemplate().getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			if(pallocation.getPanelAllocationId()==0)
			callableSt.setString(1, "insert");
			else
			callableSt.setString(1, "updateAll");
			
			callableSt.setInt(2, pallocation.getPanelAllocationId());
			callableSt.setInt(3, pallocation.getClusterId());
			callableSt.setInt(4, pallocation.getFirmId());
			callableSt.setInt(5, 0);
			callableSt.setString(6, pallocation.getRmName());
			
			DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
			Date date=null;
			try {
				date = formatter.parse(pallocation.getPanelDate());
				
			} catch (ParseException e) {
				
				e.printStackTrace();
			}
			java.sql.Date paneldate = new java.sql.Date(date.getTime());
	        //System.out.println("panel-date-"+date+"sqldate-"+paneldate);
			callableSt.setDate(7, paneldate);			
			//callableSt.setString(7,pallocation.getPanelDate() );
			//System.out.println("pallocation.getPanelEndTime()-"+pallocation.getPanelEndTime()+"pallocation.getPanelStartTime() "+pallocation.getPanelStartTime());
			callableSt.setString(8,pallocation.getPanelStartTime());//,pallocation.getPanelStartTime()
			callableSt.setString(9,pallocation.getPanelEndTime());//pallocation.getPanelEndTime()
			callableSt.setInt(10, pallocation.getPanel());
			callableSt.setInt(11, pallocation.getExtraRooms());
			callableSt.setBoolean(12, true);
			callableSt.setInt(13, pallocation.getCreatedId());
			callableSt.setInt(14, pallocation.getCreatedId());
			callableSt.setString(15,pallocation.getNotificationStatus());
			callableSt.setBoolean(16, pallocation.getIsLocked());
			callableSt.setInt(17, pallocation.getProcessId());
			callableSt.setInt(18, pallocation.getRoleId());
			callableSt.setInt(19, pallocation.getMappedRoleId());
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
	public List<PanelAllocation> getPanelAllotedListByClusterId(int cid) {
		//System.out.println("PanelAllocationDAOImpl.getFirmNameByCid()"+cid);
		List<PanelAllocation> list = new ArrayList<PanelAllocation>();
		final String procedureCall = "{call proc_PanelAlocation(?,?,?)}";
		Connection connection=null;
		try {
			connection = getJdbcTemplate().getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "selectPanelByCid");
			callableSt.setInt(2, 0);
			callableSt.setInt(3, cid);
			ResultSet rs = callableSt.executeQuery();
			
			while (rs.next()) {
				PanelAllocation panelAllocation=new PanelAllocation();
				panelAllocation.setClusterId(rs.getInt("Fk_ClusterId"));
				panelAllocation.setFirmName(rs.getString("CompanyName"));
				panelAllocation.setNotificationStatus(rs.getString("NotificationStatus"));
				panelAllocation.setIsLocked(rs.getBoolean("isLocked"));
				list.add(panelAllocation);
				
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
	public List<PanelAllocation> getPanelAllotedList() {
		//System.out.println("PanelAllocationDAOImpl.getPanelAllotedList()");
		List<PanelAllocation> list = new ArrayList<PanelAllocation>();
		final String procedureCall = "{call proc_PanelAlocation(?,?)}";
		Connection connection=null;
		try {
			connection = getJdbcTemplate().getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "selectAllPanel");
			callableSt.setInt(2, 0);
			
			ResultSet rs = callableSt.executeQuery();
			
			while (rs.next()) {
				PanelAllocation panelAllocation=new PanelAllocation();
				panelAllocation.setClusterId(rs.getInt("Fk_ClusterId"));				
				panelAllocation.setFirmName(rs.getString("CompanyName"));
				panelAllocation.setNotificationStatus(rs.getString("NotificationStatus"));
				panelAllocation.setIsLocked(rs.getBoolean("isLocked"));
				list.add(panelAllocation);
				
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
	public PanelAllocation getPanelDeatilsByAppId(int firmId) {
		//System.out.println("PanelAllocationDAOImpl.getPanelDeatilsByAppId()"+firmId);		
		PanelAllocation panelAllocation=new PanelAllocation();	
		final String procedureCall = "{call proc_PanelAlocation(?,?,?,?)}";
		Connection connection=null;
		try {
			connection = getJdbcTemplate().getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "selectPanelDetailByFirmId");
			callableSt.setInt(2, 0);
			callableSt.setInt(3, 0);
			callableSt.setInt(4, firmId);
			ResultSet rs = callableSt.executeQuery();
			
			while (rs.next()) {		
				panelAllocation.setPanelAllocationId(rs.getInt("PK_PanelAllocationId"));
				panelAllocation.setProcessId(rs.getInt("FK_ProcessId"));
				panelAllocation.setClusterId(rs.getInt("FK_clusterId"));
				panelAllocation.setFirmId(rs.getInt("FK_Applicationid"));				
				panelAllocation.setPanelDate(rs.getString("PanelAllocationDate"));
				panelAllocation.setPanelStartTime(rs.getString("panelAllocationStarttime"));
				panelAllocation.setPanelEndTime(rs.getString("PanelAllocationEndtime"));
				panelAllocation.setRoleId(rs.getInt("Fk_CompanyRoleId"));
				panelAllocation.setPanel(rs.getInt("Panel"));
				panelAllocation.setExtraRooms(rs.getInt("ExtraRooms"));
				panelAllocation.setMappedRoleId(rs.getInt("MappedRoleId"));
				panelAllocation.setNotificationStatus(rs.getString("NotificationStatus"));
				panelAllocation.setIsLocked(rs.getBoolean("isLocked"));
				
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
		return panelAllocation;
	}

	@Override
	public List<PanelAllocation> getClusterNameByPid(int pid) {
		List<PanelAllocation> list = new ArrayList<PanelAllocation>();
		final String procedureCall = "{call proc_PanelAlocation(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
		Connection connection=null;
		try {
			connection = getJdbcTemplate().getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "selectClusterByPid");
			callableSt.setInt(2, 0);
			callableSt.setInt(3, 0);
			callableSt.setInt(4, 0);
			callableSt.setInt(5, 0);
			callableSt.setString(6, null);
			callableSt.setDate(7, null);
			callableSt.setString(7, null);
			callableSt.setString(8, null);
			callableSt.setString(9, null);
			callableSt.setInt(10, 0);
			callableSt.setInt(11, 0);
			callableSt.setBoolean(12, true);
			callableSt.setInt(13, 0);
			callableSt.setInt(14, 0);
			callableSt.setString(15, null);
			callableSt.setBoolean(16, true);
			callableSt.setInt(17, pid);
			
			ResultSet rs = callableSt.executeQuery();
			
			while (rs.next()) {
				PanelAllocation panelAllocation=new PanelAllocation();
				panelAllocation.setClusterId(rs.getInt("Pk_ClusterId"));
				panelAllocation.setClusterName(rs.getString("CluserName"));
				
				list.add(panelAllocation);
				
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
	public List<PanelAllocation> getRoleNameByFirmId(int rid) {
		System.out.println("appid in dao-"+rid);
		List<PanelAllocation> list = new ArrayList<PanelAllocation>();
		final String procedureCall = "{call proc_PanelAlocation(?,?,?,?)}";
		Connection connection=null;
		try {
			connection = getJdbcTemplate().getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "selectRoleName");
			callableSt.setInt(2, 0);
			callableSt.setInt(3, 0);
			callableSt.setInt(4, rid);
			
			
			ResultSet rs = callableSt.executeQuery();
			
			while (rs.next()) {
				PanelAllocation panelAllocation=new PanelAllocation();
				panelAllocation.setRoleId(rs.getInt("Fk_CompanyRoleId"));
				panelAllocation.setRoleName(rs.getString("DesignationName"));
				
				list.add(panelAllocation);
				
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
	public List<PanelAllocation> getThetime(int roleId, int roomId) {
		
		List<PanelAllocation> list = new ArrayList<PanelAllocation>();
		final String procedureCall = "{call Proc_WingMapping(?,?,?,?,?,?,?)}";
		Connection connection=null;
		try {
			connection = getJdbcTemplate().getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "getTime");
			callableSt.setInt(2, 0);
			callableSt.setInt(3, 0);
			callableSt.setInt(4, 0);
			callableSt.setInt(5, roomId);
			callableSt.setInt(6, 0);
			callableSt.setInt(7, roleId);
			ResultSet rs = callableSt.executeQuery();
			
			while (rs.next()) {		
				PanelAllocation panelAllocation=new PanelAllocation();
				panelAllocation.setPanelEndTime(rs.getString("EndTime"));	
				panelAllocation.setPanelStartTime(rs.getString("StartTime"));
				list.add(panelAllocation);
				
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
	public List<PanelAllocation> geAlltime(int roomId,int cid,int rid) {
		List<PanelAllocation> list = new ArrayList<PanelAllocation>();
		final String procedureCall = "{call Proc_WingMapping(?,?,?,?,?)}";
		Connection connection=null;
		try {
			connection = getJdbcTemplate().getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "getAllTime");
			callableSt.setInt(2, cid);
			callableSt.setInt(3, rid);
			callableSt.setInt(4, 0);
			callableSt.setInt(5, roomId);
			ResultSet rs = callableSt.executeQuery();
			
			while (rs.next()) {		
				PanelAllocation panelAllocation=new PanelAllocation();
				panelAllocation.setPanelEndTime(rs.getString("EndTime"));	
				panelAllocation.setPanelStartTime(rs.getString("StartTime"));
				list.add(panelAllocation);
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
	public List<PanelAllocation> getThePaneltime(int roleId, int roomId) {
		List<PanelAllocation> list = new ArrayList<PanelAllocation>();
		final String procedureCall = "{call Proc_WingMapping(?,?,?,?,?,?,?)}";
		Connection connection=null;
		try {
			connection = getJdbcTemplate().getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "getPanelTime");
			callableSt.setInt(2, 0);
			callableSt.setInt(3, 0);
			callableSt.setInt(4, 0);
			callableSt.setInt(5, 0);
			callableSt.setInt(6, 0);
			callableSt.setInt(7, roleId);
			ResultSet rs = callableSt.executeQuery();
			
			while (rs.next()) {		
				PanelAllocation panelAllocation=new PanelAllocation();
				panelAllocation.setPanelEndTime(rs.getString("PanelAllocationEndtime"));	
				panelAllocation.setPanelStartTime(rs.getString("panelAllocationStarttime"));
				list.add(panelAllocation);
				
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
	public JSONArray geAllThePanel(int appId) {
		int x=0;
		System.out.println("xxxxxxxxxxxxxxxxxxxxxx");
		final String procedureCall = "{call Proc_WingMapping(?,?)}";
		Connection connection=null;
		JSONArray jsonArray = new JSONArray();
		try {
			connection = getJdbcTemplate().getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "getPanelValidation");
			callableSt.setInt(2, appId);
			ResultSet rs = callableSt.executeQuery();
			while (rs.next()) {
				int x1 = rs.getInt("FirmCount");
				int x2 = rs.getInt("Panel");
				int x3 = rs.getInt("ExtraRooms");
				if (x1 == (x2 + x3)) {
					x = 1;
					break;
				}
			}
			JSONObject jObject=new JSONObject();	
			jObject.put("totalPanel", x);
			jsonArray.put(jObject);
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
		
		
		return jsonArray;
	}

	@Override
	public List<PanelAllocation> getFirmNameByCidInPanel(int cid) {
		List<PanelAllocation> list = new ArrayList<PanelAllocation>();
		final String procedureCall = "{call proc_PanelAlocation(?,?,?)}";
		Connection connection=null;
		try {
			connection = getJdbcTemplate().getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "selectFirmByCid");
			callableSt.setInt(2, 0);
			callableSt.setInt(3, cid);
			ResultSet rs = callableSt.executeQuery();
			
			while (rs.next()) {
				PanelAllocation panelAllocation=new PanelAllocation();
				panelAllocation.setClusterId(rs.getInt("Fk_ClusterId"));
				panelAllocation.setFirmId(rs.getInt("Pk_ApplicationId"));
				panelAllocation.setFirmName(rs.getString("CompanyName"));
				list.add(panelAllocation);
				
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
	public List<PanelAllocation> getRoleNameByAppIdInPanel(int appId) {
		List<PanelAllocation> list = new ArrayList<PanelAllocation>();
		final String procedureCall = "{call proc_PanelAlocation(?,?,?,?)}";
		Connection connection=null;
		try {
			connection = getJdbcTemplate().getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "selectRoleByfid");
			callableSt.setInt(2, 0);
			callableSt.setInt(3, 0);
			callableSt.setInt(4, appId);
			
			
			ResultSet rs = callableSt.executeQuery();
			
			while (rs.next()) {
				PanelAllocation panelAllocation=new PanelAllocation();
				panelAllocation.setRoleId(rs.getInt("Pk_CompanyRoleId"));
				panelAllocation.setRoleName(rs.getString("DesignationName"));
				
				list.add(panelAllocation);
				
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
	public List<WingTracker> getTheClusterDetails(int clusterId) {
		List<WingTracker> list = new ArrayList<WingTracker>();
		final String procedureCall = "{call Proc_WingMapping(?,?)}";
		Connection connection=null;
		try {
			connection = getJdbcTemplate().getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "selectclusterdetails");
			callableSt.setInt(2, clusterId);
			ResultSet rs = callableSt.executeQuery();
			while (rs.next()) {
				WingTracker wing=new WingTracker();
				wing.setPlacecomerId(rs.getInt("Fk_NegoId"));
				wing.setSedulerId1(rs.getInt("Fk_SchedulerId"));
				wing.setWingId(rs.getInt("FK_WingId"));
				wing.setWingRoomId(rs.getInt("Pk_WingMapId"));
				list.add(wing);
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
	public List<PanelAllocation> getRoleInDD(int firmid) {
		List<PanelAllocation> list=new ArrayList<PanelAllocation>();
		final String procedureCall="{call proc_PanelAlocation(?,?)}";
		Connection connection=null;
		try
		{
			connection=getJdbcTemplate().getDataSource().getConnection();
			CallableStatement callableStatement=connection.prepareCall(procedureCall);
			callableStatement.setString(1,"getRoleAndMappedRole");
			callableStatement.setInt(2, firmid);
			ResultSet rs=callableStatement.executeQuery();
			while(rs.next())
			{
				PanelAllocation pa=new PanelAllocation();
				pa.setRoleId(rs.getInt("Fk_CompanyRoleId"));
				pa.setMappedRoleId(rs.getInt("MappedRoleId"));
				pa.setRoleName(rs.getString("rolename"));
				pa.setMapRoleName(rs.getString("maprolename"));
				list.add(pa);
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
	
	

