package com.precise.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.precise.model.CompanyStatus;
import com.precise.model.GDPI;
import com.precise.model.Nego;

@Repository
public class NegoDaoImpl implements NegoDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<Nego> getCompany(int negoId) {
		

		List<Nego> list = new ArrayList<Nego>();
			final String procedureCall = "{call Proc_WingMapping(?,?)}";
			Connection connection=null;
			try {
				connection = jdbcTemplate.getDataSource().getConnection();
				CallableStatement callableSt = connection.prepareCall(procedureCall);
				callableSt.setString(1, "getNegoCompany");
				callableSt.setInt(2, negoId);
				ResultSet rs = callableSt.executeQuery();
				while (rs.next()) {
					Nego nego=new Nego();
					nego.setCompanyId(rs.getInt("FK_FirmId"));
					nego.setCompanyName(rs.getString("CompanyName"));
					nego.setWingId(rs.getInt("FK_WingId"));
					list.add(nego);
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
	public String getuserName(int userId) {
		String str = new String();
		final String procedureCall = "{call Proc_WingMapping(?,?)}";
		Connection connection=null;
		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "getNegoName");
			callableSt.setInt(2, userId);
			ResultSet rs = callableSt.executeQuery();
			while (rs.next()) {
				
				str=rs.getString("userName");
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
		return str;
	
	}

	@Override
	public JSONArray getRmName(int cid,int userid) {
		
		String str="";
		String str1="";
		int x=0;
		final String procedureCall = "{call Proc_WingMapping(?,?)}";
		Connection connection=null;
		JSONArray jsonArray = new JSONArray();
		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "getCompanyRM");
			callableSt.setInt(2, cid);
			ResultSet rs = callableSt.executeQuery();
			while (rs.next()) {
				str=rs.getString("RmName");
				str1=rs.getString("username");
				x=rs.getInt("Pk_IIMStdId");
			}
			JSONObject jObject=new JSONObject();	
			jObject.put("rmName", str);
			jObject.put("username", str1);
			jObject.put("negoid", x);
			jObject.put("loginid", userid);
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
	public JSONArray getRoleName(int cid) {
		final String procedureCall = "{call Proc_WingMapping(?,?)}";
		Connection connection=null;
		JSONArray jsonArray = new JSONArray();
		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "getCompanyRole");
			callableSt.setInt(2, cid);
			ResultSet rs = callableSt.executeQuery();
			while (rs.next()) {
				JSONObject jObject=new JSONObject();	
				jObject.put("designationname", rs.getString("DesignationName"));
				jObject.put("cmproleid", rs.getInt("Fk_CompanyRoleId"));
				jsonArray.put(jObject);
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
		
		return jsonArray;
	}

	@Override
	public JSONArray getRoleDetails(int cid) {
		float y=0,z=0,p=0;
		String x="";
		final String procedureCall = "{call Proc_WingMapping(?,?)}";
		Connection connection=null;
		JSONArray jsonArray = new JSONArray();
		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "getCompanyRoleDetails");
			callableSt.setInt(2, cid);
			ResultSet rs = callableSt.executeQuery();
			while (rs.next()) {
				x=rs.getString("Compension");
				y=rs.getFloat("WorkExpReq");
				z=rs.getFloat("WorkExpReqMax");
				p=rs.getFloat("Hires");
			}
			JSONObject jObject=new JSONObject();	
			jObject.put("compensation", x);
			jObject.put("workexp", y);
			jObject.put("workreqmax", z);
			jObject.put("hires", p);
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
	public List<Nego> getPICompany(int userId) {
		List<Nego> list = new ArrayList<Nego>();
		final String procedureCall = "{call Proc_WingMapping(?,?)}";
		Connection connection=null;
		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "getNegoPICompany");
			callableSt.setInt(2, userId);
			ResultSet rs = callableSt.executeQuery();
			while (rs.next()) {
				Nego nego=new Nego();
				nego.setCompanyId(rs.getInt("FK_FirmId"));
				nego.setCompanyName(rs.getString("CompanyName"));
				nego.setWingId(rs.getInt("FK_WingId"));
				list.add(nego);
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
	public List<Nego> getTheList(int roleid) {
		List<Nego> list = new ArrayList<Nego>();
		final String procedureCall = "{call Proc_WingMapping(?,?)}";
		Connection connection=null;
		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "getNegoPI");
			callableSt.setInt(2, roleid);
			ResultSet rs = callableSt.executeQuery();
			while (rs.next()) {
				Nego nego=new Nego();
				nego.setRollNumber(rs.getInt("RollNumber"));
				nego.setName(rs.getString("Name"));
				nego.setHotlist(rs.getString("HotListStatus"));
				nego.setStatus(rs.getString("Status"));
				nego.setApplyId(rs.getInt("Pk_ApplyId"));
				list.add(nego);
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
	public List<Nego> getTheGDList(int roleid) {
		List<Nego> list = new ArrayList<Nego>();
		final String procedureCall = "{call Proc_WingMapping(?,?)}";
		Connection connection=null;
		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "getNegoGD");
			callableSt.setInt(2, roleid);
			ResultSet rs = callableSt.executeQuery();
			while (rs.next()) {
				Nego nego=new Nego();
				nego.setRollNumber(rs.getInt("RollNumber"));
				nego.setName(rs.getString("Name"));
				nego.setHotlist(rs.getString("HotListStatus"));
				nego.setStatus(rs.getString("GDStatus"));
				list.add(nego);
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
	public void updatedescription(GDPI gdpi, int userid) {
		final String procedureCall="{call Proc_WingMapping(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
		Connection connection=null;
		try
		{
			connection=jdbcTemplate.getDataSource().getConnection();
			CallableStatement callableSt=connection.prepareCall(procedureCall);
			if(gdpi.getInsertupdatedescription()==0)
			   callableSt.setString(1,"InsertDescription");
			else
				callableSt.setString(1,"UpdateDescription");	
			callableSt.setInt(2,gdpi.getDiffer());
			callableSt.setInt(3,0);
			callableSt.setInt(4,userid);
			callableSt.setInt(5,0);
			callableSt.setInt(6,gdpi.getCompanyId());
			callableSt.setInt(7,gdpi.getRoleId());
			callableSt.setBoolean(8,true);
			callableSt.setInt(9,userid);
			callableSt.setString(10,null);
			callableSt.setInt(11,0);
			callableSt.setString(12,null);
			callableSt.setString(13,null);
			callableSt.setString(14,null);
			callableSt.setInt(15,0);
			callableSt.setInt(16,0);
			callableSt.setString(17,gdpi.getDescription());
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
	public List<GDPI> getTheDescription(int roleid, int round) {
		List<GDPI> list = new ArrayList<GDPI>();
		final String procedureCall = "{call Proc_WingMapping(?,?,?)}";
		Connection connection=null;
		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "getTheDescription");
			callableSt.setInt(2, roleid);
			callableSt.setInt(3, round);
			ResultSet rs = callableSt.executeQuery();
			while (rs.next()) {
				GDPI gdpi=new GDPI();
				gdpi.setDescription(rs.getString("NegoDescription"));;
				list.add(gdpi);
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
	public void updateplacementstatus(CompanyStatus cs,int userid) {
		final String procedureCall="{call Proc_WingMapping(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
		Connection connection=null;
		try
		{
			connection=jdbcTemplate.getDataSource().getConnection();
			CallableStatement callableSt=connection.prepareCall(procedureCall);
			callableSt.setString(1,"InsertCompanyStatus");	
			callableSt.setInt(2,cs.getStudentId());
			callableSt.setInt(3,0);
			callableSt.setInt(4,userid);
			callableSt.setInt(5,0);
			callableSt.setInt(6,cs.getCompanyId());
			callableSt.setInt(7,cs.getCompanyRoleId());
			callableSt.setBoolean(8,true);
			callableSt.setInt(9,0);
			callableSt.setString(10,null);
			callableSt.setInt(11,0);
			callableSt.setString(12,null);
			callableSt.setString(13,null);
			callableSt.setString(14,null);
			callableSt.setInt(15,0);
			callableSt.setInt(16,0);
			callableSt.setString(17,null);
			callableSt.setString(18,cs.getStudentName());
			callableSt.setString(19,null);
			callableSt.setString(20,cs.getCompanyStatus());
			callableSt.execute();
		}
		catch (SQLException e) {
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
	public void studentstatusupdate(Nego cs) {
		final String procedureCall="{call Proc_WingMapping(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
		Connection connection=null;
		try
		{
			connection=jdbcTemplate.getDataSource().getConnection();
			CallableStatement callableSt=connection.prepareCall(procedureCall);
			callableSt.setString(1,"UpdateStudentStatus");	
			callableSt.setInt(2,cs.getRollNumber());
			callableSt.setInt(3,cs.getEp());
			callableSt.setInt(4,0);
			callableSt.setInt(5,0);
			callableSt.setInt(6,cs.getCompanyId());
			callableSt.setInt(7,cs.getCompanyRoleId());
			callableSt.setBoolean(8,true);
			callableSt.setInt(9,0);
			callableSt.setString(10,null);
			callableSt.setInt(11,0);
			callableSt.setString(12,null);
			callableSt.setString(13,null);
			callableSt.setString(14,null);
			callableSt.setInt(15,0);
			callableSt.setInt(16,0);
			callableSt.setString(17,null);
			callableSt.setString(18,null);
			callableSt.setString(19,cs.getStudentUpdateStatus());
			callableSt.execute();
		}
		catch (SQLException e) {
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
	public int getNegoValidation(int appid, int userid) {
		final String procedureCall="{call Proc_WingMapping(?,?,?)}";
		Connection connection=null;
		int count=0;
		try
		{
			connection=jdbcTemplate.getDataSource().getConnection();
			CallableStatement callableSt=connection.prepareCall(procedureCall);
			callableSt.setString(1,"getNegoValidation");
			callableSt.setInt(2,userid);	
			callableSt.setInt(3,appid);
			ResultSet rs=callableSt.executeQuery();
			
			while(rs.next())
			{
				System.out.println("csdddddddddddd");
				count=1;
				break;
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (connection != null)
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return count;
	}

	@Override
	public JSONArray getwing(int cid) {
		int x=0;
		final String procedureCall = "{call Proc_WingMapping(?,?)}";
		Connection connection=null;
		JSONArray jsonArray = new JSONArray();
		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "getWingId");
			callableSt.setInt(2, cid);
			ResultSet rs = callableSt.executeQuery();
			if (rs.next()) {
				x=rs.getInt("FK_WingId");
			}
			JSONObject jObject=new JSONObject();	
			jObject.put("wingid", x);
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
	public int noofpanel(int cid) {
		int x=0;
		final String procedureCall = "{call Proc_WingMapping(?,?)}";
		Connection connection=null;
		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "getnoofpanel");
			callableSt.setInt(2, cid);
			ResultSet rs = callableSt.executeQuery();
			if (rs.next()) {
				x=rs.getInt("Panel");
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
		return x;
	}

	@Override
	public List<Nego> getTheInfo() {

		List<Nego> list = new ArrayList<Nego>();
			final String procedureCall = "{call Proc_WingMapping(?)}";
			Connection connection=null;
			try {
				connection = jdbcTemplate.getDataSource().getConnection();
				CallableStatement callableSt = connection.prepareCall(procedureCall);
				callableSt.setString(1, "selectall");
				ResultSet rs = callableSt.executeQuery();
				while (rs.next()) {
					Nego nego=new Nego();
					nego.setCompanyId(rs.getInt("FK_FirmId"));
					nego.setCompanyRoleMapId(rs.getString("Fk_CompanyRoleId"));
					nego.setCompanyName(rs.getString("CompanyName"));
					nego.setCompanyRoleName(rs.getString("DesignationName"));
					nego.setWingId(rs.getInt("FK_WingId"));
					nego.setRmName(rs.getString("RmName"));
					nego.setNegoName(rs.getString("userName"));
					nego.setNoofpanel(rs.getInt("Panel"));
					list.add(nego);
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
	public List<Nego> getgdstatus(int userid) {
		List<Nego> list = new ArrayList<Nego>();
		final String procedureCall = "{call Proc_WingMapping(?,?)}";
		Connection connection=null;
		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "selectgdstatus");
			callableSt.setInt(2, userid);
			ResultSet rs = callableSt.executeQuery();
			while (rs.next()) {
				Nego nego=new Nego();
				nego.setCompanyName(rs.getString("CompanyName"));
				nego.setCompanyRoleName(rs.getString("DesignationName"));
				nego.setStudentName(rs.getString("Name"));
				nego.setRollNumber(rs.getInt("RollNumber"));
				nego.setGdStatus(rs.getString("GDStatus"));
				nego.setCompanyId(rs.getInt("Fk_ApplicatinId"));
				nego.setCompanyRoleId(rs.getInt("Fk_RoleId"));
				nego.setRollNumber(rs.getInt("RollNumber"));
				list.add(nego);
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
	public String insertIntoStudent(Object[] studentData,int userid) throws SQLException{
		System.out.println("inside function::");
		String procedureCall = "";
		Connection connection = null;
		try {
			System.out.println("String) studentData[0]:::"+(Integer) studentData[0]);
			connection = jdbcTemplate.getDataSource().getConnection();
			connection.setAutoCommit(false);
			procedureCall = "{call Proc_WingMapping(?,?,?,?,?,?,?,?,?)}";
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "Excel");
			callableSt.setInt(2, (Integer) studentData[0]);
			callableSt.setInt(3,0);
			callableSt.setInt(4, (Integer)studentData[2]);
			callableSt.setInt(5, 0);
			callableSt.setInt(6, 0);
			callableSt.setInt(7, (Integer)studentData[1]);
			callableSt.setBoolean(8,  true);
			callableSt.setInt(9, userid);
			callableSt.execute(); 
			connection.commit();
		} catch (Exception e) {
			e.printStackTrace();
			connection.rollback();
		} finally {
			if (connection != null)
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return null;

	}

	@Override
	public void gdstatusupdate(Nego nego, int userid) {
		 String procedureCall="";
		Connection connection=null;
		try{
			procedureCall="{call Proc_WingMapping(?,?,?,?,?)}";
			connection=jdbcTemplate.getDataSource().getConnection();
			CallableStatement callablest=connection.prepareCall(procedureCall);
			String st=nego.getGdupdatestatus();
			String st1[]=st.split(",");
			int x[]={0,0,0};
			for(int i=0;i<st1.length;i++)
			{
				String st2[]=st1[i].split("##");
				for(int j=0;j<3;j++)
				{
					x[j]=Integer.parseInt(st2[j]);
					System.out.println(x[j]);
				}
				callablest.setString(1,"InsertGdStatus");
				callablest.setInt(2,x[1]);
				callablest.setInt(3,x[2]);
				callablest.setInt(4,x[0]);
				callablest.setInt(5,userid);
				callablest.execute();
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
		
	}

	@Override
	public JSONArray geteffectivepreference(int rollno, int roleid) {
		final String procedureCall = "{call Proc_WingMapping(?,?,?)}";
		Connection connection=null;
		JSONArray jsonArray = new JSONArray();
		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "effectivePreference");
			callableSt.setInt(2, rollno);
			callableSt.setInt(3, roleid);
			ResultSet rs = callableSt.executeQuery();
			if (rs.next()) {
				JSONObject jObject=new JSONObject();	
				jObject.put("effective",rs.getInt("effectivePreference"));	
				jsonArray.put(jObject);
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
		
		
		return jsonArray;
	}

	@Override
	public void updatefirminfo(ArrayList<Integer> applyid1,int roleid) {
		final String procedureCall = "{call Proc_WingMapping(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
		String s="";
		int i=0;
		Connection connection=null;
		for(i=0;i<(applyid1.size())-1;i++)
		{
			s+=applyid1.get(i)+",";
		}
		if(applyid1.size()>0)
		s+=applyid1.get(i);
		System.out.println("csd:::"+s);
		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "updateNegoStd");
			callableSt.setInt(2,roleid);
			callableSt.setInt(3,0);
			callableSt.setInt(4,0);
			callableSt.setInt(5,0);
			callableSt.setInt(6,0);
			callableSt.setInt(7,0);
			callableSt.setBoolean(8,true);
			callableSt.setInt(9,0);
			callableSt.setString(10,null);
			callableSt.setInt(11,0);
			callableSt.setString(12,null);
			callableSt.setString(13,null);
			callableSt.setString(14,null);
			callableSt.setInt(15,0);
			callableSt.setInt(16,0);
			callableSt.setString(17,null);
			callableSt.setString(18,null);
			callableSt.setString(19,null);
			callableSt.setString(20,null);
			callableSt.setString(21,s);
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
	public JSONArray getfirmoffer(int cid, int roleid, int sid) {
		final String procedureCall = "{call Proc_WingMapping(?,?,?,?)}";
		Connection connection=null;
		JSONArray jsonArray = new JSONArray();
		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "getfirmoffer");
			callableSt.setInt(2, cid);
			callableSt.setInt(3, roleid);
			callableSt.setInt(4, sid);
			ResultSet rs = callableSt.executeQuery();
			if (rs.next()) {
				JSONObject jObject=new JSONObject();	
				jObject.put("status", rs.getString("CompanyStatus"));
				jsonArray.put(jObject);
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
		return jsonArray;
	}


}
