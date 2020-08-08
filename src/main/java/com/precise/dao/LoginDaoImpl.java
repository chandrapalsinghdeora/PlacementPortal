package com.precise.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.precise.dbconnection.ConnectionDao;
import com.precise.model.Reply;
import com.precise.model.SessionBean;
import com.precise.model.UserBean;
import com.precise.util.PasswordEncryptionDecryption;


@Repository
public class LoginDaoImpl extends ConnectionDao implements LoginDao {

	private static Logger logger = Logger.getLogger(LoginDaoImpl.class);

	@Autowired
	PasswordEncryptionDecryption passwordEncription;

	public Map<String, String> checkUserLoginData(UserBean userBean, HttpSession sesssion) throws Exception {
		System.out.println("hai");
		Connection connection = null;
		String encriptedPassword=passwordEncription.EncryptText(userBean.getPassword());
		connection = getJdbcTemplate().getDataSource().getConnection();
		System.out.println("userBean.getUserName()"+userBean.getUserName());
		  CallableStatement callableStatement = connection.prepareCall("{call proc_UserRelation(?, ?)}");
		  callableStatement.setString(1, "selectLoginUser");
		  callableStatement.setString(2, userBean.getUserName());
		  Map<String, String> mapValues = new LinkedHashMap<String, String>();
		  ResultSet rs=callableStatement.executeQuery();
		  //System.out.println("rs in logindao-"+rs.getString("UserName"));
		  while(rs.next())
		  {
			  //System.out.println("rsooo---"+rs.getString("UserName"));
				SessionBean sessionBean = new SessionBean();
				sessionBean.setName(rs.getString("UserName"));
				//System.out.println("nasmfh"+rs.getString("UserName"));
				sessionBean.setUserID(rs.getInt("FK_UserID"));
				sessionBean.setRoleID(rs.getInt("FK_RoleID"));
				
				//sessionBean.setId(rs.getString("FK_UserID"));
				mapValues.put("userName", rs.getString("UserName"));
				mapValues.put("password",passwordEncription.DecryptText( rs.getString("LoginPassword")));
				//System.out.println(""+mapValues+"useridpid--"+rs.getString("PK_UserId"));
				sesssion.setAttribute("sessionBean", sessionBean);
		  }
		  return mapValues; 
		/*Map<String, String> mapValues = new LinkedHashMap<String, String>();
		StringBuilder sqlQuery = new StringBuilder();
		try {
			String encriptedPassword=passwordEncription.EncryptText(userBean.getPassword());
			System.out.println("encrypted password::"+encriptedPassword);
			sqlQuery.append(
					"SELECT PK_UserId,USERNAME,LOGINPASSWORD,USERROLETYPE FROM TBL_MST_USER WHERE USERNAME=? AND LOGINPASSWORD=?");
			System.out.println("PrintConnection ::" + getJdbcTemplate());
			List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sqlQuery.toString(),
					new Object[] { userBean.getUserName(), encriptedPassword });
			for (Map<String, Object> row : rows) {
				sesssion.setMaxInactiveInterval(60 * 30);
				SessionBean sessionBean = new SessionBean();
				sessionBean.setName(row.get("USERNAME").toString());
				sessionBean.setUserRoleType(row.get("USERROLETYPE").toString());
				sessionBean.setId(row.get("PK_UserId").toString());
				mapValues.put("userName", row.get("USERNAME").toString());
				mapValues.put("userRole", row.get("USERROLETYPE").toString());
				sesssion.setAttribute("sessionBean", sessionBean);
			}
		} catch (Exception e) {
			logger.error("Exception in checkUserLoginData method of LoginDaoImpl class::" + e.getMessage());
			throw new Exception();
		}
		return mapValues;
		
		/*System.out.println("checkUserLoginData dao");
		Map<String, String> mapValues = new LinkedHashMap<String, String>();	
		
		final String procedureCall = "{call proc_UserRelation(?,?,?)}";
		Connection connection = null;
		try {		
		connection = getJdbcTemplate().getDataSource().getConnection();
		CallableStatement callableSt = connection.prepareCall(procedureCall);
		String encriptedPassword=passwordEncription.EncryptText(userBean.getPassword());
		System.out.println("encrypted password::"+encriptedPassword+"username -"+userBean.getUserName());
		
		callableSt.setString(1,"selectLoginUser");		
		callableSt.setString(2, userBean.getUserName());
		callableSt.setString(3, encriptedPassword);
		ResultSet rs=callableSt.executeQuery();
		
		if(rs.next()){
			System.out.println("session bean---");
			sesssion.setMaxInactiveInterval(60 * 30);
			SessionBean sessionBean = new SessionBean();
			sessionBean.setName(rs.getString("USERNAME"));
			sessionBean.setUserRoleType(rs.getString("USERROLETYPE"));
			sessionBean.setId(rs.getString("PK_UserId"));
			mapValues.put("userName", rs.getString("USERNAME"));
			mapValues.put("userRole", rs.getString("USERROLETYPE"));
			sesssion.setAttribute("sessionBean", sessionBean);
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
		return mapValues;
		*/
		
	

	}

	@Override
	public Map<String, String> loginGoogle(UserBean userBean, HttpSession session) {
		// TODO Auto-generated method stub
		//System.out.println("useid "+userBean.getUserEmailId());
		//System.out.println("hai");
		Connection connection = null;
		//String encriptedPassword=passwordEncription.EncryptText(userBean.getPassword());
		try {
			connection = getJdbcTemplate().getDataSource().getConnection();
		
		//System.out.println("userBean.email()"+userBean.getUserEmailId());
		  CallableStatement callableStatement = connection.prepareCall("{call proc_UserRelation(?,?,?)}");
		  String text=userBean.getUserEmailId();
		  System.out.println("emailtext"+text);
		  if(text.contains("gmail")){
		  	           
		  
		  callableStatement.setString(1, "selectPotentialid");
		  //System.out.printf("Great '%s' contains word 'Hello' %n" , text);
 	       }
		  else if(text.contains("iima")){
			  //System.out.println("userBean.email()"+userBean.getUserEmailId());
			  String prifix=text.substring(0,3);
	    	   if(Pattern.matches("[a-zA-Z]{1}[0-9]{2,3}",prifix))
	    			   {
	    		 //  System.out.println(" if userBean.email()"+userBean.getUserEmailId());
			  callableStatement.setString(1, "selectIIMASID");
	    			   }
	    	   else
	    	   {
	    		   System.out.println("else userBean.email()"+userBean.getUserEmailId());
	    		   callableStatement.setString(1, "selectIIMAEmp");
	    	   }
		  }
		  callableStatement.setString(2, null);
		  callableStatement.setString(3, userBean.getUserEmailId());
		  Map<String, String> mapValues = new LinkedHashMap<String, String>();
		  ResultSet rs=callableStatement.executeQuery();
		  //System.out.println("rs in logindao-"+rs.getString("UserName"));
		  while(rs.next())
		  {
			  //System.out.println("rsooo---"+rs.getString("UserName"));
			  System.out.println(" group id : "+rs.getString("groupid"));
				SessionBean sessionBean = new SessionBean();
				mapValues.put("pgpflag",rs.getString("groupid")==null?"0":rs.getString("groupid"));
				//sessionBean.setRoleID(rs.getInt("FK_RoleID"));
				 if(text.contains("gmail")){
					 
					 //sessionBean.setName(rs.getString("StdName"));
						//System.out.println("nasmfh"+rs.getString("UserName"));
						//sessionBean.setUserID();
						
				//sessionBean.setUserRoleType("Potential Student");
				
				mapValues.put("userName", rs.getString("StdName"));
				mapValues.put("emailid",rs.getString("EmailId"));
				mapValues.put("userid",rs.getString("Pk_poltentialStdId"));
				mapValues.put("roleid",rs.getString("roleid"));
				mapValues.put("roletype","Potential Student");
				 }
				  else if(text.contains("iima")){
					  String prifix=text.substring(0,3);
					  if(Pattern.matches("[a-zA-Z]{1}[0-9]{2,3}",prifix))
			    			   {
							System.out.println(rs.getString("RoleId"));
					
							mapValues.put("roleid",rs.getString("RoleId"));
							mapValues.put("roletype","IIMA Student");
							mapValues.put("userName", rs.getString("userName"));
							mapValues.put("userid",rs.getString("Pk_IIMStdId"));
							mapValues.put("emailid",rs.getString("EmailId"));
					   }
					  else if(text.contains("plcscheduling")){ //for scheduling administrator
						  System.out.println("schdule admin");
			    		   mapValues.put("roleid",rs.getString("FK_RoleID"));
							mapValues.put("userName", rs.getString("userName"));
							mapValues.put("userid",rs.getString("PK_UserId"));
							mapValues.put("roletype","Scheduling Admin");
							mapValues.put("emailid",rs.getString("UserEmailId"));	 
					  }
			    	   else
			    	   {
			    		   System.out.println("kmadmin");
			    		   mapValues.put("roleid",rs.getString("FK_RoleID"));
							mapValues.put("userName", rs.getString("userName"));
							mapValues.put("userid",rs.getString("PK_UserId"));
							mapValues.put("roletype","KM Admin");
							mapValues.put("emailid",rs.getString("UserEmailId"));	
			    		   
			    	   }
				  }
				  
				  
				//sessionBean.setId(rs.getString("FK_UserID"));
				
				//System.out.println(""+mapValues+"useridpid--"+rs.getString("PK_UserId"));
				session.setAttribute("sessionBean", sessionBean);
		  }
		  return mapValues; 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		 
			
		
	}

	@Override
	public Map<String, String> getStdRoles(HttpSession session) {
		// TODO Auto-generated method stub
		SessionBean sessionBean =(SessionBean)session.getAttribute("sessionBean");
		//System.out.println("getstudetRole"+sessionBean.getUserID());
		Connection connection = null;
		//String encriptedPassword=passwordEncription.EncryptText(userBean.getPassword());
		try {
			connection = getJdbcTemplate().getDataSource().getConnection();
		
		//System.out.println("userBean.email()"+userBean.getUserEmailId());
		  CallableStatement callableStatement = connection.prepareCall("{call proc_UserRelation(?,?,?,?,?)}");
		  callableStatement.setString(1, "selectIIMRoles");
		  callableStatement.setString(2,null);
		  callableStatement.setString(3,null);
		  callableStatement.setInt(4,sessionBean.getUserID());
		  callableStatement.setInt(5,sessionBean.getRoleID());
		  System.out.println("sessionBean.getRoleID()"+sessionBean.getRoleID());
		  ResultSet rs=callableStatement.executeQuery();
		  //System.out.println("rs in logindao-"+rs.getString("UserName"));
		  Map<String, String> mapValues=new LinkedHashMap<String, String>();
		  while(rs.next())
		  {
			//  System.out.println("rs.getString"+rs.getString("FK_RoleID"));;
			 // mapValues=new LinkedHashMap<String, String>();
			  mapValues.put(rs.getString("FK_RoleID"),rs.getString("RoleName"));
			 		  }
		  System.out.println("return value map--"+mapValues);
		  return mapValues;
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
}
	
