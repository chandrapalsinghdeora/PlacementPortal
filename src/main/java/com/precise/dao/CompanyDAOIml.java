package com.precise.dao;

import java.io.File;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.precise.model.CVReleted;
import com.precise.model.Company;
import com.precise.model.PPO;
import com.precise.model.RoleBaseLink;
import com.precise.model.RoleCompany;

@Repository("companyRepo")
public class CompanyDAOIml implements CompanyDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	@Override
	public List<Company> getAllCompanyDetail(int userId,String status) {		
		List<Company> listCompany=new ArrayList<Company>();		
		List<Company> listFinalCompany=new ArrayList<Company>();		
		String procedureCall = null;
		Connection connection = null;
		try
		{
			connection = jdbcTemplate.getDataSource().getConnection();
			try{
				procedureCall = "{call sp_InsertCompany(?,?,?)}";
				CallableStatement callableSt = connection.prepareCall(procedureCall);
				if(status.equals("Close"))
				callableSt.setString(1, "SelectCompany");
				else
				callableSt.setString(1, "SelectOpenFirm");	
				callableSt.setInt(2, 0);
				callableSt.setInt(3, userId);
				ResultSet rs = callableSt.executeQuery();
				while (rs.next()) {
					Company company = new Company();
					company.setCompanyId(rs.getInt("Pk_CompanyId"));
				//System.out.println("application id--"+rs.getInt("Pk_ApplicationId"));
					company.setListCompanyRoles(getAllCompanyRole(rs.getInt("Pk_ApplicationId")));
					company.setAdditionalFilePathList(getAllAdditionalFile(rs.getInt("Pk_ApplicationId")));
					company.setListCompanyURL(getAllFirmURL(rs.getInt("Pk_ApplicationId")));
					//System.out.println("app iddd-"+rs.getInt("Pk_ApplicationId"));
					//System.out.println("OpningDatetime :: "+rs.getTimestamp("OpningDatetime"));
					//System.out.println("ClosingDatetime :: "+rs.getTimestamp("ClosingDatetime"));
					company.setApplicationId(rs.getInt("Pk_ApplicationId"));
					company.setCompanyName(rs.getString("CompanyName"));
					company.setProcessName(rs.getString("ProcessName"));
					company.setYear(rs.getInt("year"));
					company.setAdditionalText(rs.getString("AdditionalText")==null?"":rs.getString("AdditionalText").trim());
					//company.setRoleCompanyId(rs.getInt("Pk_CompanyRoleId"));
					//company.setRoleCompany(rs.getString("Rolename"));
					//company.setCompanyRoleDescription(rs.getString("Description"));
					//System.out.println("PrefenceSurvey"+rs.getString("PrefenceSurvey"));
					company.setPrefenceSurvey(rs.getString("PrefenceSurvey"));
					//company.setJobDescription(rs.getString("JobDescription"));
					company.setClusterName(rs.getString("CluserName"));
					company.setCohortName(rs.getString("CohortName"));
					//company.setCoverLetter(rs.getBoolean("CoverLatter"));
					//company.setUrlText(rs.getString("UrlText"));
					company.setOpningDatetime(rs.getString("OpningDatetime"));
					company.setClosingDatetime(rs.getString("ClosingDatetime"));
					//company.setWorkExp(rs.getInt("WorkExp"));
					//company.setCompensation(rs.getDouble("Compensation"));
					company.setApplicationStatus(rs.getString("Status"));
					company.setApplied(rs.getString("Applied"));
					company.setShortlist(rs.getString("Shortlist"));
					//company.setApplyId(rs.getInt("Pk_ApplyId"));
					//company.setRank(rs.getInt("Rank"));
					company.setMultipleCVCheck(rs.getBoolean("CVReleated"));
					company.setLimitCVNo(rs.getInt("CVReleatedNo"));
				//	System.out.println("flafg "+rs.getBoolean("CVReleated"));
					listCompany.add(company);				
				}
			}catch(SQLException e){
				e.printStackTrace();
			}
			/*List<String> jdList = new ArrayList<String>();
			List<String> roleNameList = new ArrayList<String>();
			List<String> intershipExpList = new ArrayList<String>();
			List<String> interviewExpList = new ArrayList<String>();*/
			List<RoleBaseLink> roleBaseLinkList = null;
			
		//	DesignationName	JobDesc	JobDescPath	InterViewExp	InternshipExp
			
			try{
				for(Company cmp : listCompany){
					roleBaseLinkList =  new ArrayList<RoleBaseLink>();
					procedureCall = "{call sp_InsertCompany(?,?)}";
					CallableStatement callableSt = connection.prepareCall(procedureCall);
					callableSt.setString(1, "Companylink");
				//	System.out.println("application id :: "+cmp.getApplicationId());
					callableSt.setInt(2,cmp.getApplicationId());
					ResultSet rs = callableSt.executeQuery();
					RoleBaseLink rolelink = null;
					while (rs.next()) {
						rolelink = new RoleBaseLink();
						rolelink.setJdLink(rs.getString("JobDescPath"));
						/*rolelink.setRoleName(rs.getString("DesignationName"));*/
						rolelink.setInterviewExpLink(rs.getString("InterViewExp"));
						rolelink.setIntershipExpLink(rs.getString("InternshipExp"));
						roleBaseLinkList.add(rolelink);
					}
				//	System.out.println("roleBaseLinkList : "+roleBaseLinkList.size());
					cmp.setRoleBaseLink(roleBaseLinkList);
					listFinalCompany.add(cmp);
				}
			}catch(SQLException e){
				e.printStackTrace();
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally
		{
			if (connection != null)
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return listFinalCompany;
	}

	@Override
	public List<CVReleted> getAllCVReleted(int userId) {
		//String sql = "SELECT * FROM tbl_trn_Cv where Status=1 and Fk_IIMStdId="+userId+"" ;
		String sql = "SELECT * FROM tbl_trn_Cv where IsActive =1 and fileName  not like '%cv1%' and Fk_IIMStdId="+userId+"" ;
	    List<CVReleted> listCv = jdbcTemplate.query(sql, new RowMapper<CVReleted>() {
	    	@Override
	        public CVReleted mapRow(ResultSet rs, int rowNum) throws SQLException {
	    		CVReleted cvReleted = new CVReleted();	
	    		cvReleted.setCvReleted(rs.getInt("Pk_CvId"));
	    		cvReleted.setCvReletedName(rs.getString("Title")); 
	            return cvReleted;
	        }
	    });	 
	    return listCv;		
	}
		
	
	@Override
	public Company getCompanyDetail(int cmpId) {		
		String procedureCall = null;
		Connection connection = null;
		Company company = new Company();
		try
		{
			connection = jdbcTemplate.getDataSource().getConnection();
			try{
				procedureCall = "{call sp_InsertCompany(?,?,?)}";
				CallableStatement callableSt = connection.prepareCall(procedureCall);
				callableSt.setString(1, "SelectCmpById");
				callableSt.setInt(2, 0);
				callableSt.setInt(3, cmpId);
				ResultSet rs = callableSt.executeQuery();
				
				if (rs.next()) {
					company.setCompanyId(rs.getInt("Pk_CompanyId"));
				//	System.out.println("Application ID:::"+rs.getInt("Pk_ApplicationId"));
					company.setListCompanyRoles(getAllCompanyRole(rs.getInt("Pk_ApplicationId")));
					company.setApplicationId(rs.getInt("Pk_ApplicationId"));
					company.setCompanyName(rs.getString("CompanyName"));
					company.setProcessName(rs.getString("ProcessName"));
					company.setYear(rs.getInt("year"));
					company.setPrefenceSurvey(rs.getString("PrefenceSurvey"));
					company.setClusterName(rs.getString("CluserName"));
					company.setCohortName(rs.getString("CohortName"));
					company.setCoverLetter(rs.getBoolean("CoverLatter"));
					company.setOpningDatetime(rs.getString("OpningDatetime"));
					company.setClosingDatetime(rs.getString("ClosingDatetime"));
					company.setApplicationStatus(rs.getString("Status"));
					company.setApplied(rs.getString("Applied"));
					company.setShortlist(rs.getString("Shortlist"));
					company.setMultipleCVCheck(rs.getBoolean("CVReleated"));
					company.setLimitCVNo(rs.getInt("CVReleatedNo"));
				}
			}catch(SQLException e){
				e.printStackTrace();
			}
		
			List<RoleBaseLink> roleBaseLinkList = null;
			
			try{
				
				roleBaseLinkList =  new ArrayList<RoleBaseLink>();
				procedureCall = "{call sp_InsertCompany(?,?)}";
				CallableStatement callableSt = connection.prepareCall(procedureCall);
				callableSt.setString(1, "Companylink");
			//	System.out.println("application id :: "+cmp.getApplicationId());
				callableSt.setInt(2,company.getApplicationId());
				ResultSet rs = callableSt.executeQuery();
				RoleBaseLink rolelink = null;
				while (rs.next()) {
					rolelink = new RoleBaseLink();
					rolelink.setJdLink(rs.getString("JobDescPath"));
				//	rolelink.setRoleName(rs.getString("DesignationName"));
					rolelink.setInterviewExpLink(rs.getString("InterViewExp"));
					rolelink.setIntershipExpLink(rs.getString("InternshipExp"));
					roleBaseLinkList.add(rolelink);
				}
			//	System.out.println("roleBaseLinkList : "+roleBaseLinkList.size());
				company.setRoleBaseLink(roleBaseLinkList);
				
			}catch(SQLException e){
				e.printStackTrace();
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally
		{
			if (connection != null)
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return company;
	}

	
	@Override
	public List<RoleCompany> getAllCompanyRole(int applicationId) {
	//	System.out.println("CompanyDAOIml.getAllCompanyRole()--"+applicationId);
		List<RoleCompany> listCompanyRoles=new ArrayList<RoleCompany>();	
		try{
			String procedureCall = "{call sp_InsertCompany(?,?)}";
			Connection connection = null;
			connection = jdbcTemplate.getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "SelectRole");
			callableSt.setInt(2, applicationId);
			
			ResultSet rs = callableSt.executeQuery();
			RoleCompany roleCompany =null;
			while (rs.next()) {
				roleCompany = new RoleCompany();
				roleCompany.setRoleCompany(rs.getString("DesignationName"));
				roleCompany.setRoleCompanyId(rs.getInt("Pk_CompanyRoleId"));
				roleCompany.setMapRoleId(rs.getInt("RoleMap"));
				roleCompany.setCoverLetter(rs.getBoolean("CoverLatter"));
			//	System.out.println(rs.getString("Pk_CompanyRoleId")+" :: "+rs.getString("DesignationName"));
				listCompanyRoles.add(roleCompany);		
			}
		}catch(Exception e ){
			e.printStackTrace();
		}
	    return listCompanyRoles;		
	}
	
	
	public List<String> getAllAdditionalFile(int applicationId) {
	//	System.out.println("CompanyDAOIml.getAllAdditionalFile()--"+applicationId);
		List<String> listCompanyRoles=new ArrayList<String>();	
		try{
			String procedureCall = "{call Sp_SelectCompany(?,?)}";
			Connection connection = null;
			connection = jdbcTemplate.getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "Additional");
			callableSt.setInt(2, applicationId);
			
			ResultSet rs = callableSt.executeQuery();
			while (rs.next()) {
				listCompanyRoles.add(rs.getString("FilePath")==null?"":rs.getString("FilePath"));		
			}
		}catch(Exception e ){
			e.printStackTrace();
		}
	    return listCompanyRoles;		
	}
	
	public List<String> getAllFirmURL(int applicationId) {
		//	System.out.println("CompanyDAOIml.getAllAdditionalFile()--"+applicationId);
			List<String> listCompanyURL=new ArrayList<String>();	
			try{
				String procedureCall = "{call Sp_SelectCompany(?,?)}";
				Connection connection = null;
				connection = jdbcTemplate.getDataSource().getConnection();
				CallableStatement callableSt = connection.prepareCall(procedureCall);
				callableSt.setString(1, "URL");
				callableSt.setInt(2, applicationId);
				ResultSet rs = callableSt.executeQuery();
				while (rs.next()) {
					if(rs.getString("UrlText")!=null && !rs.getString("UrlText").equals(""))
						listCompanyURL.add(rs.getString("UrlText")+"##"+rs.getString("Description"));		
				}
			}catch(Exception e ){
				e.printStackTrace();
			}
		    return listCompanyURL;		
		}
	
	@Override
	public void insertCompanyForm(Company company,String commanCoverTitleName) {
		System.out.println("companyName : "+company.getCompanyName());
		List<String> cvReleted = company.getCvReleted();
		//System.out.println("company.getCvReleted()--IN DOA--"+company.getCvReleted());
		List<Integer> mapRoleId = company.getMapRoleId();
		//List<String> roleCompanyId = company.getRoleCompanyId();
		List<String> rank = company.getRank();
		List<String> title = company.getTitle();
		List<String> filePath = company.getFilePath();
		List<Integer> checkboxlist = company.getCheckBoxList();
		List<Boolean> roleCover = company.getRoleCover();
		List<Integer> role_id = new ArrayList<Integer>();
		Connection connection = null;
		CallableStatement callableSt = null;
		
		
		
		if(checkboxlist.size()>0){
			/*try {
				File deletefile  = null;
				String file = "";
				connection = jdbcTemplate.getDataSource().getConnection();
				String procedureCall = "{call sp_InsertCompany(?,?,?)}";
				for (int i = 0; i < checkboxlist.size(); i++) {
					callableSt = connection.prepareCall(procedureCall);
					callableSt.setString(1, "FilePath");
					callableSt.setInt(2, checkboxlist.get(i));
					callableSt.setInt(3, company.getCreatedBy());
					ResultSet rs1 = callableSt.executeQuery();
					if (rs1.next()) {
						file = rs1.getString("Filepath")==null?"":rs1.getString("Filepath");
						if(!file.equals("")){
							deletefile = new File(file);
							if (deletefile.exists() && !deletefile.isDirectory())
								deletefile.delete();
							else
								System.out.println("File::" + file + " not exists");
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				if (connection != null)
					try {
						connection.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
			}
		*/
			for(int i=0;i<checkboxlist.size();i++){
				for(int j=0;j<mapRoleId.size();j++)	{
					System.out.println("map role id:::"+mapRoleId.get(j).toString());
					System.out.println("checkbox list:::"+checkboxlist.get(i));
					if((mapRoleId.get(j).toString()).equals(checkboxlist.get(i).toString()))
						cvReleted.add(j,cvReleted.get(i));
				}
			}
		}
		
		if(cvReleted.size()>0 && cvReleted.size()==checkboxlist.size() ){
			int cntdel =0;
			for(int i=0;i<cvReleted.size();i++){
				if(!cvReleted.get(i).equals("")){
					role_id.add(checkboxlist.get(i));
				}else{
					//rank.remove(i);
					roleCover.remove(i-cntdel);
					cntdel++;
					//filePath.remove(i);
				}
			}
		}else{
			role_id = checkboxlist;
			int cntdel=0;
			for(int i=0;i<cvReleted.size();i++){
				if(cvReleted.get(i).equals("")){
					//rank.remove(i);
					roleCover.remove(i-cntdel);
					cntdel++;
					//filePath.remove(i);
				}
			}
		}
		
		
		
		cvReleted.removeAll(Arrays.asList(null,""));
	//	filePath.removeAll(Arrays.asList(null,""));
	//	title.removeAll(Arrays.asList("NA",""));
		//System.out.println("cv relete size:::"+cvReleted.size());
		/*System.out.println("chseck -" + checkboxlist);
		 List<Integer> applyId=company.getApplyId();
		System.out.println("roleCompanyId=" + roleCompanyId);*/
		
		int applicationID = company.getApplicationId();
		java.util.Date closingDate = null;
		
		try{
			connection = jdbcTemplate.getDataSource().getConnection();
			String procedureCall = "{call sp_InsertCompany(?,?)}";
			callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "CloseDate");
			callableSt.setInt(2, applicationID);
			ResultSet rs = callableSt.executeQuery();
			if (rs.next()) {
				closingDate = rs.getTimestamp("ClosingDatetime");
			//	System.out.println("closingDate : "+closingDate);
			}
		}catch(Exception e ){
			e.printStackTrace();
		}
		finally {
			if (connection != null)
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		
		Date today = Calendar.getInstance().getTime();  
	//	System.out.println("closingDate : "+closingDate+" today Date: " + today+" :: "+today.before(closingDate)+" :: "+today.after(closingDate));
		
		if(today.before(closingDate)) {
			try {
				connection = jdbcTemplate.getDataSource().getConnection();
				String procedureCall = "{call Sp_ApplyApp(?,?,?,?,?,?,?,?,?,?,?,?)}";
				callableSt = connection.prepareCall(procedureCall);
				if (cvReleted!=null && cvReleted.size() > 0) {
				//if (checkboxlist!=null && checkboxlist.size() > 0) {
					int index =0 ;
					for (int i = 0; i < cvReleted.size(); i++) {
					//for (int i = 0; i < checkboxlist.size(); i++) {
					//	System.out.println("index :: "+mapRoleId.get(i)+" :: "+checkboxlist.get(i));
						callableSt.setString(1, "Apply");
						if(cvReleted!=null && cvReleted.size()>0){
							if(cvReleted.get(i).equals("rolemap")){
								//System.out.println("index :: "+mapRoleId.get(i)+" :: "+checkboxlist.get(i));
								index = checkboxlist.indexOf(mapRoleId.get(i));
								System.out.println("index :: "+checkboxlist.indexOf(mapRoleId.get(i))+" :: "+cvReleted.get(index));
								//if(!cvReleted.get(index).isEmpty()||cvReleted.get(index)!="")
								
								callableSt.setInt(2, Integer.parseInt(cvReleted.get(index)==null?"0":cvReleted.get(index)));
							}else{
								//if(!cvReleted.get(index).isEmpty()||cvReleted.get(index)!="")
								callableSt.setInt(2, Integer.parseInt(cvReleted.get(i)==null?"0":cvReleted.get(i)));
							}
						}else{
							callableSt.setInt(2, 0);
						}
						
						callableSt.setInt(3, role_id.get(i));
						if(rank!=null && rank.size()>0){
							callableSt.setInt(4, Integer.parseInt(rank.get(i)==null?"0":rank.get(i)));
						}else{
							callableSt.setInt(4, 0);
						}
						if(roleCover.get(i)){
							callableSt.setString(5, commanCoverTitleName+"_"+getRoleName(role_id.get(i)));
							callableSt.setString(6, filePath.get(i));
						}
						else{
							callableSt.setString(5, null);
							callableSt.setString(6, null);
						}
						
						/*if(title!=null && title.size()>i){
							callableSt.setString(5, commanCoverTitleName+"_"+getRoleName(role_id.get(i)));
							
						}else{
							callableSt.setString(5, null);
						}
						
						if(filePath!=null && filePath.size()>i){
							callableSt.setString(6, filePath.get(i));
						}else{
							callableSt.setString(6, null);
						}*/
						
						callableSt.setDate(7, null);
						callableSt.setBoolean(8, true);
						callableSt.setInt(9, company.getCreatedBy());
						callableSt.setBoolean(10, company.isVerify());
						callableSt.setInt(11,0);
						callableSt.setInt(12,company.getApplicationId());
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
	}
	
	@Override
	public void insertCompanyPrefence(Company company) {/*
		//List<Integer> rank=company.getRankList();
		//List<Integer> roleCompanyId=company.getCompanyRoleId();
		System.out.println("Rank"+company.getRankList());
		System.out.println("roleCompanyId"+company.getCompanyRoleId());
		final String procedureCall = "{call Sp_ApplyApp(?,?,?,?,?,?,?,?,?,?,?)}";
		Connection connection = null;
		try {
		connection = jdbcTemplate.getDataSource().getConnection();
		//for(int i=0;i<rank.size();i++){
		CallableStatement callableSt = connection.prepareCall(procedureCall);
		callableSt.setString(1, "Preference");
		callableSt.setInt(2, 0);
		callableSt.setInt(3, company.getRoleCompanyId());
		callableSt.setInt(4, company.getRank());
		callableSt.setString(5, null);
		callableSt.setString(6, null);
		callableSt.setDate(7, null);
		callableSt.setBoolean(8, true);
		callableSt.setInt(9, 0);
		callableSt.setBoolean(10, true);
		callableSt.setInt(11,company.getApplyId());
		callableSt.execute();
		//}
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
	*/}
	@Override
	public void withdarw(Company company) {
		System.out.println("ApplicationId :: "+company.getApplicationId());
		String procedureCall = "{call Sp_ApplyApp(?,?,?,?,?,?,?,?,?,?,?,?)}";
		
		Connection connection = null;
		CallableStatement callableSt =null;
		
		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "Withdraw");
			callableSt.setInt(2, 0);
			callableSt.setInt(3, 0);
			callableSt.setInt(4, 0);
			callableSt.setString(5, null);
			callableSt.setString(6, null);
			callableSt.setDate(7, null);
			callableSt.setBoolean(8, true);
			callableSt.setInt(9, company.getCreatedBy());
			callableSt.setBoolean(10, true);
			callableSt.setInt(11,0);
			callableSt.setInt(12,company.getApplicationId());
			callableSt.execute();
		
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
	
	public String getStudentTitleName(int userId,String cmpName){
		Connection connection = null;
		String companyName = null;
		try{
			connection = jdbcTemplate.getDataSource().getConnection();
			String procedureCall = "{call proc_userProfile(?,?)}";
			CallableStatement callable = connection.prepareCall(procedureCall);
			callable.setString(1, "select");
			callable.setInt(2, userId);
			ResultSet rs = callable.executeQuery();
			if (rs.next()) 
				companyName = rs.getString("RollNumber")+"_"+rs.getString("CVName")+"_"+cmpName;
			System.out.println("companyName : "+companyName);
		}catch(Exception e ){
			e.printStackTrace();
		}finally {
			if (connection != null)
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return companyName;
	}
	
	public String getRoleName(int roleId){
		String roleName=null;
		Connection connection = null;
		try{
			connection = jdbcTemplate.getDataSource().getConnection();
			String procedureCall = "{call sp_InsertCompany(?,?)}";
			CallableStatement role_callable = connection.prepareCall(procedureCall);
			role_callable.setString(1, "selectRoleName");
			role_callable.setInt(2, roleId);
			ResultSet rs = role_callable.executeQuery();
			if (rs.next()) 
				roleName = rs.getString("RoleName");
			System.out.println("roleName : "+roleName);
		}catch(Exception e ){
			e.printStackTrace();
		}
		finally {
			if (connection != null)
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return roleName;
	}

	@Override
	public int checkForLateral(int userId) {
		int flag=0;
		Connection connection = null;
		try{
			connection = jdbcTemplate.getDataSource().getConnection();
			String procedureCall = "{call sp_InsertCompany(?,?)}";
			CallableStatement role_callable = connection.prepareCall(procedureCall);
			role_callable.setString(1, "checkForLateral");
			role_callable.setInt(2, userId);
			ResultSet rs = role_callable.executeQuery();
			if (rs.next()) 
				flag = rs.getInt("count");
		}catch(Exception e ){
			e.printStackTrace();
		}
		finally {
			if (connection != null)
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return flag;
	}

	@Override
	public int workExp(int userId) {
		int exp=0;
		Connection connection = null;
		try{
			connection = jdbcTemplate.getDataSource().getConnection();
			String procedureCall = "{call sp_InsertCompany(?,?)}";
			CallableStatement role_callable = connection.prepareCall(procedureCall);
			role_callable.setString(1, "workExp");
			role_callable.setInt(2, userId);
			ResultSet rs = role_callable.executeQuery();
			if (rs.next()) 
				exp = rs.getInt("TotalWork");
		}catch(Exception e ){
			e.printStackTrace();
		}
		finally {
			if (connection != null)
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return exp;
	}

	@Override
	public String checkStatus(int userId) {
		String status="";
		Connection connection = null;
		try{
			connection = jdbcTemplate.getDataSource().getConnection();
			String procedureCall = "{call sp_InsertCompany(?,?)}";
			CallableStatement role_callable = connection.prepareCall(procedureCall);
			role_callable.setString(1, "checkStatus");
			role_callable.setInt(2, userId);
			ResultSet rs = role_callable.executeQuery();
			if (rs.next()) 
				status = rs.getString("Status");
		}catch(Exception e ){
			e.printStackTrace();
		}
		finally {
			if (connection != null)
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return status;
	}

	@Override
	public List<PPO> getCount(int userId) {
		List<PPO> list=new ArrayList<PPO>();
		Connection connection = null;
		try{
			connection = jdbcTemplate.getDataSource().getConnection();
			String procedureCall = "{call sp_InsertCompany(?,?)}";
			CallableStatement role_callable = connection.prepareCall(procedureCall);
			role_callable.setString(1, "getcount");
			role_callable.setInt(2, userId);
			ResultSet rs = role_callable.executeQuery();
			while (rs.next()) 
			{
				PPO ppo=new PPO();
				ppo.setTotalApp(rs.getInt("TotalApplication"));
				ppo.setC1App(rs.getInt("C1"));
				ppo.setC2App(rs.getInt("C2"));
				ppo.setC3App(rs.getInt("C3"));
				ppo.setOfferCount(rs.getInt("OfferCount"));
				ppo.setTotal(rs.getInt("Total"));
				ppo.setFlag(rs.getInt("flag"));
				list.add(ppo);
			}
		}catch(Exception e ){
			e.printStackTrace();
		}
		finally {
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
	public List<PPO> sameCohort(int userId) {
		List<PPO> list=new ArrayList<PPO>();
		Connection connection = null;
		try{
			connection = jdbcTemplate.getDataSource().getConnection();
			String procedureCall = "{call sp_InsertCompany(?,?)}";
			CallableStatement role_callable = connection.prepareCall(procedureCall);
			role_callable.setString(1, "sameCohort");
			role_callable.setInt(2, userId);
			ResultSet rs = role_callable.executeQuery();
			while (rs.next()) 
			{
				PPO ppo=new PPO();
				ppo.setCohortName(rs.getString("CohortName"));
				ppo.setCount(rs.getInt("Count"));
				list.add(ppo);
			}
		}catch(Exception e ){
			e.printStackTrace();
		}
		finally {
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
	public List<Company> getOfferStatus(int userId) {
		int offercount=0;
		Connection connection = null;
		List<Company> comp=new ArrayList<Company>();
		try{
			connection = jdbcTemplate.getDataSource().getConnection();
			String procedureCall = "{call sp_InsertCompany(?,?)}";
			CallableStatement role_callable = connection.prepareCall(procedureCall);
			role_callable.setString(1, "offercount");
			role_callable.setInt(2, userId);
			ResultSet rs = role_callable.executeQuery();
			if (rs.next()) 
			{
				Company company = new Company(); 				
				company.setCompanyName(rs.getString("CompanyName"));
				company.setOfferCount(rs.getInt("OfferCount"));	
				company.setTotalApplication(rs.getInt("TotalApplication"));
				System.out.println("offercount : "+offercount);
				comp.add(company);
			}	
			}catch(Exception e ){
			e.printStackTrace();
		}
		finally {
			if (connection != null)
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return comp;
	}

	@Override
	public List<Company> getApplyList(int userId) {
		List<Company> list=new ArrayList<Company>();
		String procedureCall = "{call sp_InsertCompany(?,?)}";
		Connection connection = null;
		try {
			connection = getJdbcTemplate().getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "applycompany");
			callableSt.setInt(2, userId);
			ResultSet rs = callableSt.executeQuery();
			while (rs.next()) {
				Company company = new Company(); 				
				company.setCompanyName(rs.getString("CompanyName"));
				company.setCluserName(rs.getString("CluserName"));				
				company.setCohortName(rs.getString("CohortName"));
				company.setCompanyId(rs.getInt("fk_Company"));
				company.setRoleId(rs.getInt("Fk_RoleId"));
				company.setApplyID(rs.getInt("Pk_ApplyId"));
			    company.setCompanyRoleName(rs.getString("DesignationName"));
				list.add(company);
			}
	}catch (SQLException e) {
		e.printStackTrace();
	}		
	finally {
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
	public void withdrawFirm(Company company) {
		System.out.println("ApplicationId :: "+company.getApplicationId());
		String procedureCall = "{call sp_InsertCompany(?,?)}";
		
		Connection connection = null;
		CallableStatement callableSt =null;
		
		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "WithdrawFirm");
			callableSt.setInt(2, company.getApplyID());
			callableSt.setInt(3, 0);
			callableSt.setInt(4, 0);
			callableSt.setString(5, null);
			callableSt.setString(6, null);
			callableSt.setDate(7, null);
			callableSt.setBoolean(8, true);
			callableSt.setInt(9, company.getCreatedBy());
			callableSt.setBoolean(10, true);
			callableSt.setInt(11,0);
			callableSt.setInt(12,0);
			callableSt.execute();
		
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
	public int getApplyNumber(int userId) {
		int number=0;
		String procedureCall = "{call sp_InsertCompany(?,?)}";
		Connection connection = null;
		try {
			connection = getJdbcTemplate().getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "totalapply");
			callableSt.setInt(2, userId);
			ResultSet rs = callableSt.executeQuery();
			if(rs.next()) {
				number=rs.getInt("applyno");
			}
	}catch (SQLException e) {
		e.printStackTrace();
	}		
	finally {
		if (connection != null)
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}		
	return number;	
 }

	@Override
	public void withdrawAll(Company company) {
		System.out.println("ApplicationId :: "+company.getApplicationId());
		String procedureCall = "{call sp_InsertCompany(?,?)}";
		Connection connection = null;
		CallableStatement callableSt =null;
		
		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "WithdrawAll");
			callableSt.setInt(2, company.getApplyID());
			callableSt.execute();
		
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
	public String withdrawPartial(Company company) {
		int number=0;
		String procedureCall = "{call sp_InsertCompany(?,?)}";
		Connection connection = null;
		Connection connection1 = null;
		try {
			connection = getJdbcTemplate().getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "withdrawSelected");
			callableSt.setInt(2, company.getApplyID());
			ResultSet rs = callableSt.executeQuery();
			if(rs.next()) {
				number=rs.getInt("Status");
			}
	}catch (SQLException e) {
		e.printStackTrace();
	}		
	finally {
		if (connection != null)
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
		if(number==1)
			return "1";
		else
		{
			System.out.println("ApplicationId :: "+company.getApplicationId());
			String procedureCall1 = "{call sp_InsertCompany(?,?)}";
			
			CallableStatement callableSt1 =null;
			
			try {
				connection1 = jdbcTemplate.getDataSource().getConnection();
				callableSt1 = connection1.prepareCall(procedureCall1);
				callableSt1.setString(1, "WithdrawAll");
				callableSt1.setInt(2, company.getApplyID());
				callableSt1.execute();
			
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
			return "0";
		}
			
			
			
 }

	@Override
	public String getClusterName(Company company) {
		String s="";
		String procedureCall = "{call sp_InsertCompany(?,?)}";
		Connection connection = null;
		try {
			connection = getJdbcTemplate().getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "getCluster");
			callableSt.setInt(2, company.getApplyID());
			ResultSet rs = callableSt.executeQuery();
			if(rs.next()) {
				s=rs.getString("CluserName");
			}
	}catch (SQLException e) {
		e.printStackTrace();
	}		
	finally {
		if (connection != null)
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}		
	return s;	
	}
}
