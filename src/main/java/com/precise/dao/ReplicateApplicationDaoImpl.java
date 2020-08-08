package com.precise.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.precise.model.ReplicateApplication;
import com.precise.model.UpdateApplication;
@Repository
public class ReplicateApplicationDaoImpl implements ReplicateApplicationDao{

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public List<ReplicateApplication> getAllDetails() {
		List<ReplicateApplication> list = new ArrayList<ReplicateApplication>();
		final String procedureCall = "{call proc_ReplicateApplication(?)}";
		Connection connection=null;
		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "List");
			ResultSet rs = callableSt.executeQuery();
			int x=1;
			while (rs.next()) {
				ReplicateApplication replicateApplication=new ReplicateApplication();
				replicateApplication.setFirmName(rs.getString("CompanyName"));
				replicateApplication.setRmId(rs.getInt("Pk_IIMStdId"));
				replicateApplication.setFirmId(rs.getInt("Pk_ApplicationId"));
				replicateApplication.setRmName(rs.getString("userName"));
				replicateApplication.setRoleId(rs.getInt("Pk_CompanyRoleId"));
				replicateApplication.setRoleName(rs.getString("DesignationName"));
				replicateApplication.setNoOfRound(rs.getInt("NoOfSelectionRound"));
				list.add(replicateApplication);
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

	@Override
	public int countsl(int applyid) {
		int x=0;
		final String procedureCall = "{call proc_ReplicateApplication(?,?)}";
		Connection connection=null;
		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "countSL");
			callableSt.setInt(2,applyid);
			ResultSet rs = callableSt.executeQuery();
			if (rs.next()) {
				x=rs.getInt("NoOfSelectionRound");
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
	public String checkhldata(String roleid,ReplicateApplication rA) {
		String x="";
		final String procedureCall = "{call proc_ReplicateApplication(?,?,?,?,?,?,?,?)}";
		Connection connection=null;
		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "checkHL");
			callableSt.setInt(2,0);
			callableSt.setInt(3,0);
			callableSt.setInt(4,0);
			callableSt.setInt(5,0);
			callableSt.setInt(6,rA.getSourceSL());
			callableSt.setInt(7,rA.getTargetSL());
			callableSt.setString(8,roleid);
			ResultSet rs = callableSt.executeQuery();
			if (rs.next()) {
				x=rs.getString("Message");
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
	public String checkcldata(String roleid) {
		String x="";
		final String procedureCall = "{call proc_ReplicateApplication(?,?,?,?,?,?,?,?)}";
		Connection connection=null;
		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "checkCL");
			callableSt.setInt(2,0);
			callableSt.setInt(3,0);
			callableSt.setInt(4,0);
			callableSt.setInt(5,0);
			callableSt.setInt(6,0);
			callableSt.setInt(7,1);
			callableSt.setString(8,roleid);
			ResultSet rs = callableSt.executeQuery();
			if (rs.next()) {
				x=rs.getString("Message");
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
	public String checksldata(String roleid) {
		String x="";
		final String procedureCall = "{call proc_ReplicateApplication(?,?,?,?,?,?,?,?)}";
		Connection connection=null;
		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "checkCL");
			callableSt.setInt(2,0);
			callableSt.setInt(3,0);
			callableSt.setInt(4,0);
			callableSt.setInt(5,0);
			callableSt.setInt(6,0);
			callableSt.setInt(7,1);
			callableSt.setString(8,roleid);
			ResultSet rs = callableSt.executeQuery();
			if (rs.next()) {
				x=rs.getString("Message");
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
	public void applylist(List<ReplicateApplication> destinationList, ReplicateApplication rA) {
		final String procedureCall = "{call proc_ReplicateApplication(?,?,?,?)}";
		Connection connection=null;
		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			for(int i=0;i<destinationList.size();i++)
			{
			callableSt.setString(1, "Applied");
			callableSt.setInt(2,destinationList.get(i).getFirmId());
			callableSt.setInt(3,rA.getSourceRoleId());
			callableSt.setInt(4,destinationList.get(i).getSourceRoleId());
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
	public void cllist(List<ReplicateApplication> destinationList, ReplicateApplication rA,int x) {
		final String procedureCall = "{call proc_ReplicateApplication(?,?,?,?,?,?,?)}";
		Connection connection=null;
		int y=0,z=0;
		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			for(int i=0;i<destinationList.size();i++)
			{
				/*for(int j=0;i<details.size();j++)
				{
					if(details.get(j).getFirmId()==destinationList.get(i).getFirmId())
					{
						noofround=details.get(j).getNoOfRound();
						break;
					}
				}*/
				if(destinationList.get(i).getNoOfRound()==1 && x==2)
				{
				 y=1;
				 z=0;
				}
				if(destinationList.get(i).getNoOfRound()==2 && x==1)
				{
				 y=0;
				 z=1;
				}
				if(destinationList.get(i).getNoOfRound()==1 && x==1)
				{
				 y=1;
				 z=1;
				}
					callableSt.setString(1, "CL");
					callableSt.setInt(2,destinationList.get(i).getFirmId());
					callableSt.setInt(3,rA.getSourceRoleId());
					callableSt.setInt(4,destinationList.get(i).getSourceRoleId());
					callableSt.setInt(5,0);
					callableSt.setInt(6,z);
					callableSt.setInt(7,y);
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
	public void sllist(List<ReplicateApplication> destinationList, ReplicateApplication rA) {
		final String procedureCall = "{call proc_ReplicateApplication(?,?,?,?,?,?,?)}";
		Connection connection=null;
		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			for(int i=0;i<destinationList.size();i++)
			{
				/*int noofround=0;
				for(int y=0;y<details.size();y++)
				{
					if(destinationList.get(i).getFirmId()==details.get(y).getFirmId())
					{
					noofround=details.get(i).getNoOfRound();
					break;
					}
				}*/
			if(destinationList.get(i).getNoOfRound()==2 && rA.getSourceSL()==0 && rA.getTargetSL()==0)
			{
			callableSt.setString(1, "SL");
			callableSt.setInt(2,destinationList.get(i).getFirmId());
			callableSt.setInt(3,rA.getSourceRoleId());
			callableSt.setInt(4,destinationList.get(i).getSourceRoleId());
			callableSt.setInt(5,0);
			callableSt.setInt(6,0);
			callableSt.setInt(7,0);
			callableSt.execute();
			}
			else if(destinationList.get(i).getNoOfRound()==2 && rA.getSourceSL()==0 && rA.getTargetSL()==1)
			{
			callableSt.setString(1, "SL");
			callableSt.setInt(2,destinationList.get(i).getFirmId());
			callableSt.setInt(3,rA.getSourceRoleId());
			callableSt.setInt(4,destinationList.get(i).getSourceRoleId());
			callableSt.setInt(5,0);
			callableSt.setInt(6,0);
			callableSt.setInt(7,0);
			callableSt.execute();
			callableSt.setString(1, "SL");
			callableSt.setInt(2,destinationList.get(i).getFirmId());
			callableSt.setInt(3,rA.getSourceRoleId());
			callableSt.setInt(4,destinationList.get(i).getSourceRoleId());
			callableSt.setInt(5,0);
			callableSt.setInt(6,0);
			callableSt.setInt(7,1);
			callableSt.execute();
			}
			else if(destinationList.get(i).getNoOfRound()==2 && rA.getSourceSL()==1 && rA.getTargetSL()==0)
			{
			callableSt.setString(1, "SL");
			callableSt.setInt(2,destinationList.get(i).getFirmId());
			callableSt.setInt(3,rA.getSourceRoleId());
			callableSt.setInt(4,destinationList.get(i).getSourceRoleId());
			callableSt.setInt(5,0);
			callableSt.setInt(6,1);
			callableSt.setInt(7,0);
			callableSt.execute();
			}
			else if(destinationList.get(i).getNoOfRound()==2 && rA.getSourceSL()==1 && rA.getTargetSL()==1)
			{
			callableSt.setString(1, "SL");
			callableSt.setInt(2,destinationList.get(i).getFirmId());
			callableSt.setInt(3,rA.getSourceRoleId());
			callableSt.setInt(4,destinationList.get(i).getSourceRoleId());
			callableSt.setInt(5,0);
			callableSt.setInt(6,1);
			callableSt.setInt(7,0);
			callableSt.execute();
			callableSt.setString(1, "SL");
			callableSt.setInt(2,destinationList.get(i).getFirmId());
			callableSt.setInt(3,rA.getSourceRoleId());
			callableSt.setInt(4,destinationList.get(i).getSourceRoleId());
			callableSt.setInt(5,0);
			callableSt.setInt(6,1);
			callableSt.setInt(7,1);
			callableSt.execute();
			}
			else if(destinationList.get(i).getNoOfRound()==1 && rA.getSourceSL()==0)
			{
			callableSt.setString(1, "SL");
			callableSt.setInt(2,destinationList.get(i).getFirmId());
			callableSt.setInt(3,rA.getSourceRoleId());
			callableSt.setInt(4,destinationList.get(i).getSourceRoleId());
			callableSt.setInt(5,0);
			callableSt.setInt(6,0);
			callableSt.setInt(7,1);
			callableSt.execute();
			}
			else 
			{
			callableSt.setString(1, "SL");
			callableSt.setInt(2,destinationList.get(i).getFirmId());
			callableSt.setInt(3,rA.getSourceRoleId());
			callableSt.setInt(4,destinationList.get(i).getSourceRoleId());
			callableSt.setInt(5,0);
			callableSt.setInt(6,1);
			callableSt.setInt(7,1);
			callableSt.execute();
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
	}

	@Override
	public void hllist(List<ReplicateApplication> destinationList, ReplicateApplication rA) {
		final String procedureCall = "{call proc_ReplicateApplication(?,?,?,?,?,?,?)}";
		Connection connection=null;
		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			for(int i=0;i<destinationList.size();i++)
			{
			callableSt.setString(1, "HL");
			callableSt.setInt(2,destinationList.get(i).getFirmId());
			callableSt.setInt(3,rA.getSourceRoleId());
			callableSt.setInt(4,destinationList.get(i).getSourceRoleId());
			callableSt.setInt(5,0);
			callableSt.setInt(6,0);
			callableSt.setInt(7,0);
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
	public List<UpdateApplication> getAllList() {
		List<UpdateApplication> list = new ArrayList<UpdateApplication>();
		final String procedureCall = "{call proc_ReplicateApplication(?)}";
		Connection connection=null;
		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "selectAll");
			ResultSet rs = callableSt.executeQuery();
			int x=1;
			while (rs.next()) {
				UpdateApplication updateApplication=new UpdateApplication();
				updateApplication.setFirmName(rs.getString("CompanyName"));
				updateApplication.setFirmId(rs.getInt("Pk_ApplicationId"));
				updateApplication.setClusterId(rs.getInt("Pk_ClusterId"));
				updateApplication.setClusterName(rs.getString("CluserName"));
				updateApplication.setRoleId(rs.getInt("Pk_CompanyRoleId"));
				updateApplication.setRoleName(rs.getString("DesignationName"));
				list.add(updateApplication);
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

	@Override
	public void updatelistsend(UpdateApplication uA) {
		final String procedureCall = "{call Proc_WingMapping(?,?)}";
		Connection connection=null;
		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			String[] s=uA.getAllroleid().split(",");
			for(int i=0;i<s.length;i++)
			{
			 callableSt.setString(1, "updateFirmByExcel");
			 callableSt.setInt(2,  Integer.parseInt(s[i]));
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

