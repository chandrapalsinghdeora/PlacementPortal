package com.precise.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.stereotype.Repository;

import com.google.gson.JsonObject;
import com.precise.dbconnection.ConnectionDao;
import com.precise.model.Company;
import com.precise.model.EventCalander;
import com.precise.model.Inbox;
import com.precise.model.Label;
import com.precise.model.Reply;

@Repository
public class InboxDAOImpl extends ConnectionDao implements InboxDAO{

	@Override
	public void saveInboxData(Inbox inbox) {
		//System.out.println("InboxDAOImpl.saveInboxData()"+inbox.getInboxSubject());
				
		final String procedureCall = "{call proc_inbox(?,?,?,?,?,?,?,?,?,?)}";
		Connection connection = null;
		try {		
		connection = getJdbcTemplate().getDataSource().getConnection();
		CallableStatement callableSt = connection.prepareCall(procedureCall);
		callableSt.setString(1, "insert");
		callableSt.setInt(2, 0);
		callableSt.setString(3, inbox.getInboxSubject());
		callableSt.setString(4, inbox.getInboxText());
		callableSt.setInt(5, inbox.getLabelId());
		callableSt.setInt(6, inbox.getReceiverId());	
		callableSt.setInt(7, inbox.getSenderId());
		callableSt.setInt(8, inbox.getStatusId());	
		callableSt.setString(9, null);
		callableSt.setString(10, inbox.getSender_email());
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
	public List<Inbox> getInboxData(int receiverId) {
		//System.out.println("InboxDAOImpl.getInboxData()");
		
		List<Inbox> inboxData=new ArrayList<Inbox>();
		final String procedureCall = "{call proc_inbox(?,?,?,?,?,?,?,?)}";
		Connection connection = null;
		try {		
		connection = getJdbcTemplate().getDataSource().getConnection();
		CallableStatement callableSt = connection.prepareCall(procedureCall);	
		callableSt.setString(1,"inboxDataByRecvID");
		callableSt.setInt(2, 0);
		callableSt.setString(3,null);
		callableSt.setString(4,null);
		callableSt.setInt(5, 0);
		callableSt.setInt(6,receiverId);
		callableSt.setInt(7, 0);
		callableSt.setInt(8, 0);
		ResultSet rs=callableSt.executeQuery();
			while (rs.next()) {
				Inbox inb=new Inbox();
				inb.setInboxId(rs.getInt("Pk_InboxId"));
	        	inb.setInboxSubject(rs.getString("InboxSubject"));
	        	inb.setInboxText(rs.getString("InboxText"));
	        	inb.setAttachmentPath(rs.getString("AttachmentPath")==null?"":rs.getString("AttachmentPath"));
	        	inb.setLabelId(rs.getInt("Fk_labelId"));
	        	inb.setReceiverId(rs.getInt("Fk_ReceiverId"));
	        	inb.setSenderId(rs.getInt("Fk_SenderId"));
	        	inb.setStatusId(rs.getInt("Fk_StatusId"));
	        	Timestamp timestamp = rs.getTimestamp("CreatedDate");
	        	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy' 'HH:mm:ss");

	          //  System.out.println("timestamp :: "+ simpleDateFormat.format(timestamp));
	        	//inb.setCreatedDate((rs.getTimestamp("CreatedDate")));// CONVERT(VARCHAR(19),GETDATE())	        	
	             inb.setDatetime(simpleDateFormat.format(timestamp));
	             inb.setSender_email(rs.getString("sender_email")==null?"":rs.getString("sender_email"));
	             
	        	inboxData.add(inb);
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
		return inboxData;  
		
	
	}

	@Override
	public void updateReadStatus(int inboxId) {
		//System.out.println("InboxDAOImpl.updateReadStatus()" +inboxId);
		
		
		final String procedureCall = "{call proc_inbox(?,?,?,?,?,?,?,?)}";
		Connection connection = null;
		try {
			connection = getJdbcTemplate().getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "changeReadStatus");
			callableSt.setInt(2,inboxId );
			callableSt.setString(3, null);
			callableSt.setString(4, null);
			callableSt.setInt(5, 0);
			callableSt.setInt(6, 0);
			callableSt.setInt(7, 0);
			callableSt.setInt(8, 3);
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
	public int getUnreadMailCount(int receiverId) {
		//System.out.println("InboxDAOImpl.getUnreadMailCount()"+receiverId);
		int count=0;
		final String procedureCall = "{call proc_inbox(?,?,?,?,?,?,?,?)}";
		Connection connection = null;

		try {		
		connection = getJdbcTemplate().getDataSource().getConnection();
		CallableStatement callableSt = connection.prepareCall(procedureCall);	
		callableSt.setString(1,"unreadMailCount");	
		callableSt.setInt(2,0);
		callableSt.setString(3,null);
		callableSt.setInt(4,0);
		callableSt.setInt(5,0);
		callableSt.setInt(6,receiverId);
		callableSt.setInt(7,0);
		callableSt.setInt(8,0);
		ResultSet rs= callableSt.executeQuery();
		if(rs.next()){			
			count=rs.getInt("unread");
		    System.out.println("count in dao- "+count);
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
		
		return count;
	}

	@Override
	public void saveLabel(Label label) {
		//System.out.println("InboxDAOImpl.saveLabel()"+label.getLabelName()+"createdby"+label.getCreatedBy());
		final String procedureCall = "{call proc_label(?,?,?,?,?)}";
		Connection connection = null;
		try {		
		connection = getJdbcTemplate().getDataSource().getConnection();
		CallableStatement callableSt = connection.prepareCall(procedureCall);
		callableSt.setString(1, "insert");
		callableSt.setInt(2, 0);
		callableSt.setString(3, label.getLabelName());
		callableSt.setString(4, label.getLabelType());
		callableSt.setInt(5, label.getCreatedBy());
		
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
	public Map<Integer, Label> getLabelByUserId(int createdBy) {
		//System.out.println("InboxDAOImpl.getLabelByUserId()"+createdBy);
		
		Map<Integer,Label>	label=new LinkedHashMap<Integer,Label>();
		final String procedureCall = "{call proc_label(?,?,?,?,?)}";
		Connection connection = null;
		try {		
		connection = getJdbcTemplate().getDataSource().getConnection();
		CallableStatement callableSt = connection.prepareCall(procedureCall);	
		callableSt.setString(1, "select");
		callableSt.setInt(2, 0);
		callableSt.setString(3, null);
		callableSt.setString(4, null);
		callableSt.setInt(5, createdBy);
		
		ResultSet rs=callableSt.executeQuery();
			while (rs.next()) {
				Label lab=new Label();
				lab.setLabelId(rs.getInt("Pk_LabelId"));
	        	lab.setLabelName(rs.getString("LabelName"));
	        	lab.setLabelType(rs.getString("LabelType"));
	        	label.put(rs.getInt("Pk_LabelId"), lab);
	            //System.out.println("label in dao "+rs.getInt("Pk_LabelId"));
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
		System.out.println("map label "+label);
		return label;
	}

	@Override
	public List<Inbox> getInboxDataByUserIdAndStatusId(int userId, int statusId) {
		//System.out.println("InboxDAOImpl.getInboxDataByUserIdAndStatusId()-userid-"+userId+" status id -"+statusId);
		List<Inbox> inboxData=new ArrayList<Inbox>();
		final String procedureCall = "{call proc_inbox(?,?,?,?,?,?,?,?)}";
		Connection connection = null;
		try {		
		connection = getJdbcTemplate().getDataSource().getConnection();
		CallableStatement callableSt = connection.prepareCall(procedureCall);	
		callableSt.setString(1,"inboxDataByRecvIDAndUserId");
		callableSt.setInt(2, 0);
		callableSt.setString(3,null);
		callableSt.setString(4,null);
		callableSt.setInt(5, 0);
		callableSt.setInt(6,userId);
		callableSt.setInt(7, 0);
		callableSt.setInt(8, statusId);
		ResultSet rs=callableSt.executeQuery();
			while (rs.next()) {
				Inbox inb=new Inbox();
				inb.setInboxId(rs.getInt("Pk_InboxId"));
	        	inb.setInboxSubject(rs.getString("InboxSubject"));
	        	inb.setInboxText(rs.getString("InboxText"));
	        	inb.setAttachmentPath(rs.getString("AttachmentPath"));
	        	inb.setLabelId(rs.getInt("Fk_labelId"));
	        	inb.setReceiverId(rs.getInt("Fk_ReceiverId"));
	        	inb.setSenderId(rs.getInt("Fk_SenderId"));
	        	inb.setStatusId(rs.getInt("Fk_StatusId"));
	        	inb.setCreatedDate(rs.getTimestamp("CreatedDate")); // CONVERT(VARCHAR(19),GETDATE())	        	
	        	 inb.setSender_email(rs.getString("sender_email")==null?"":rs.getString("sender_email"));
	        	inboxData.add(inb);
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
		return inboxData;  
	
	}

	@Override
	public void updateInboxLabelIds(int receiverId, int labelId, final List<Integer> inbox) {
		System.out.println("InboxDAOImpl.updateInboxLabelIds()recid-"+receiverId+"label- "+labelId+"inbox-"+inbox );
		//boolean status=false;		
		String query="update tbl_trn_Inbox set Fk_labelId="+labelId+" where Fk_ReceiverId= "+receiverId+" and Pk_InboxId=?";
		
		int[] i=getJdbcTemplate().batchUpdate(query, new BatchPreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				ps.setInt(1, inbox.get(i));		
				System.out.println("inbox idss in dao-"+inbox.get(i));
			}
			
			@Override
			public int getBatchSize() {
				
				return  inbox.size();
			}
		});
		
		/*if(i.length==inbox.size()){
			status=true;
		}
		
		return status;
		*/
	}

	@Override
	public List<Inbox> getInboxDataByLabelIdAndUserId(int receiverId, int labelId) {
		//System.out.println("InboxDAOImpl.getInboxDataByLabelIdAndUserId()"+receiverId+" "+labelId);
		List<Inbox> inboxData=new ArrayList<Inbox>();
		final String procedureCall = "{call proc_inbox(?,?,?,?,?,?,?,?)}";
		Connection connection = null;
		try {		
		connection = getJdbcTemplate().getDataSource().getConnection();
		CallableStatement callableSt = connection.prepareCall(procedureCall);	
		callableSt.setString(1,"inboxDataByRecvIDAndLabelId");
		callableSt.setInt(2, 0);
		callableSt.setString(3,null);
		callableSt.setString(4,null);
		callableSt.setInt(5, labelId);
		callableSt.setInt(6,receiverId);
		callableSt.setInt(7, 0);
		callableSt.setInt(8, 0);
		ResultSet rs=callableSt.executeQuery();
			while (rs.next()) {
				Inbox inb=new Inbox();
				inb.setInboxId(rs.getInt("Pk_InboxId"));
	        	inb.setInboxSubject(rs.getString("InboxSubject"));
	        	inb.setInboxText(rs.getString("InboxText"));
	        	inb.setLabelId(rs.getInt("Fk_labelId"));
	        	inb.setReceiverId(rs.getInt("Fk_ReceiverId"));
	        	inb.setSenderId(rs.getInt("Fk_SenderId"));
	        	inb.setStatusId(rs.getInt("Fk_StatusId"));
	        	inb.setAttachmentPath(rs.getString("AttachmentPath"));
	        	inb.setCreatedDate(rs.getTimestamp("CreatedDate")); // CONVERT(VARCHAR(19),GETDATE())	        	
	        	inb.setSender_email(rs.getString("sender_email")==null?"":rs.getString("sender_email"));
	        	inboxData.add(inb);
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
		return inboxData;  
	}

	@Override
	public String getInboxBodyByInboxId(int userId, int inboxId) {
		//System.out.println("InboxDAOImpl.getInboxBodyByInboxId( ) uid--"+userId+" inboxId- "+inboxId);

		String inboxBody = "";
		final String procedureCall = "{call proc_inbox(?,?,?,?,?,?,?,?)}";
		Connection connection = null;
		try {		
		connection = getJdbcTemplate().getDataSource().getConnection();
		CallableStatement callableSt = connection.prepareCall(procedureCall);	
				
		callableSt.setString(1,"selectInboxBody");
		callableSt.setInt(2,inboxId );	
		callableSt.setString(3,null);
		callableSt.setString(4,null);
		callableSt.setInt(5, 0);
		callableSt.setInt(6, userId);
		callableSt.setInt(7, 0);
		callableSt.setInt(8, 0);
		System.out.println("question in dao "+inboxBody);
		ResultSet rs= callableSt.executeQuery();
		if(rs.next()){
		//	 System.out.println("question in dao uppp ");
			 inboxBody=rs.getString("InboxSubject")==null?"":rs.getString("InboxSubject");
			 inboxBody=inboxBody+"###"+ (rs.getString("InboxText")==null?"":rs.getString("InboxText"));

		     System.out.println("question in dao "+inboxBody+" :: "+rs.getString("InboxText"));
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
		return inboxBody;
	}

	@Override
	public void deleteInboxData(final List<Integer> inboxIds, int receiverId) {
	   //System.out.println("InboxDAOImpl.deleteInboxData() inboxid-"+inboxIds+"  rev id-"+receiverId);
		
       String query="update tbl_trn_Inbox set isActive=0 where Fk_ReceiverId= "+receiverId+" and Pk_InboxId=?";
		
		int[] i=getJdbcTemplate().batchUpdate(query, new BatchPreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				ps.setInt(1, inboxIds.get(i));		
				System.out.println("inbox idss in dao-"+inboxIds.get(i));
			}
			
			@Override
			public int getBatchSize() {
				
				return  inboxIds.size();
			}
		});
		
	}

	@Override
	public String getAttachementPathByInboxId(int inboxId) {
		//System.out.println("InboxDAOImpl.getAttachementPathByInboxId( ) inbox id--"+inboxId );

		String filepath = "";
		final String procedureCall = "{call proc_inbox(?,?)}";
		Connection connection = null;
		try {		
		connection = getJdbcTemplate().getDataSource().getConnection();
		CallableStatement callableSt = connection.prepareCall(procedureCall);	
				
		callableSt.setString(1,"getAttachementPath");
		callableSt.setInt(2,inboxId );	
		
		ResultSet rs= callableSt.executeQuery();
		if(rs.next()){		
			filepath=rs.getString("AttachmentPath");			 
		    System.out.println("path in dao "+filepath);
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
		return filepath;
	}

	@Override
	public JSONObject getEventList(int receiverId) {
		// TODO Auto-generated method stub
		JSONArray array=new JSONArray();
		JSONObject mainObject=new JSONObject();
		final String procedureCall = "{call proc_inbox(?,?,?,?,?,?)}";
		List<EventCalander> eventCal=new ArrayList<EventCalander>();
		Connection connection = null;
		Boolean flag=false;
		try {		
		connection = getJdbcTemplate().getDataSource().getConnection();
		CallableStatement callableSt = connection.prepareCall(procedureCall);	
				
		callableSt.setString(1,"selectEvent");
		callableSt.setInt(2,0);
		callableSt.setInt(3,0);
		callableSt.setInt(4,0);
		callableSt.setInt(5,0);
		callableSt.setInt(6,receiverId);
		ResultSet rs= callableSt.executeQuery();
		while(rs.next()){
			//System.out.println("event is"+rs.getString("Title"));
			/*EventCalander ec=new EventCalander();
			ec.setEvent(rs.getString(rs.getString("Title")));
			ec.setEventDate(rs.getDate("Datetime"));
			eventCal.add(ec);*/
			flag=false;
			if(array!=null){
				for(int n = 0; n < array.length(); n++)	{
				    JSONObject object = array.getJSONObject(n);
				    if(object.get("title").equals(rs.getString("Title"))){			    	
				    	flag=true; 
				    	break;
				    }
				}
			}
			if(!flag){
				JSONObject object=new JSONObject();
				object.put("title", rs.getString("Title"));
				object.put("start", rs.getTimestamp("Datetime"));
				object.put("className", "label-success");
				//System.out.println("rs.getTimestamp(Datetime)---"+rs.getTimestamp("Datetime"));			
				array.put(object);
			}
			
		}
		
         String procedureCall1 = "{call sp_InsertCompany(?,?)}";
		
			CallableStatement callableStComp = connection.prepareCall(procedureCall1);
			callableStComp.setString(1, "CompanyEvent");
			System.out.println("group id:"+receiverId);
			callableStComp.setInt(2, receiverId);
			ResultSet rs1 = callableStComp.executeQuery();
			while (rs1.next()) {
				//System.out.println(x);
				
				/*object.put("title", rs.getString("CompanyName"));
				object.put("start", rs.getString("OpningDatetime")); //rs.getDate("OpningDatetime")
				object.put("end",rs.getString("ClosingDatetime"));//rs.getDate("ClosingDatetime")
				object.put("process", rs.getString("ProcessName"));
				object.put("className", "label-info");*/
				JSONObject objectComp=new JSONObject();
				objectComp.put("title", rs1.getString("CompanyName")+" "+rs1.getString("ProcessName")+" Application");
				objectComp.put("start", rs1.getTimestamp("ClosingDatetime"));
				objectComp.put("className", "label-success");
				array.put(objectComp);
			}
		System.out.println("array:::::"+array.toString());
		mainObject.put("events", array);
		System.out.println("mainObject--"+mainObject);
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
		return mainObject;
	}

	@Override
	public JSONObject getCompanyEventList(int receiverId) {
		// TODO Auto-generated method stub
		JSONArray comarray=new JSONArray();
		JSONObject mainComObject=new JSONObject();
		final String procedureCall = "{call sp_InsertCompany(?,?,?)}";
		
		Connection connection = null;
		try {		
		connection = getJdbcTemplate().getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "SelectCompany");
			callableSt.setInt(2, 0);
			callableSt.setInt(3, receiverId);
			ResultSet rs = callableSt.executeQuery();
			while (rs.next()) {
				//System.out.println(x);
				JSONObject object=new JSONObject();
				object.put("title", rs.getString("CompanyName"));
				//object.put("start", rs.getString("OpningDatetime")); //rs.getDate("OpningDatetime")
				object.put("start",rs.getString("ClosingDatetime"));//rs.getDate("ClosingDatetime")
				object.put("process", rs.getString("ProcessName"));
				object.put("className", "label-info");
				
				comarray.put(object);
					
			}
			
			
			mainComObject.put("events", comarray);
			//System.out.println("mainComObject==="+mainComObject);
			}
		catch(SQLException e){
			e.printStackTrace();
		}
		return mainComObject;
	}

	@Override
	public void deleteLabel(Label label) throws Exception{
		//System.out.println("InboxDAOImpl.deleteLabel()"+label.getCreatedBy()+""+label.getLabelIds());				
		Connection connection = null;		
		try{
			connection = getJdbcTemplate().getDataSource().getConnection();
			connection.setAutoCommit(false);
			List<Integer> lab=label.getLabelIds();			            
		 	CallableStatement callableSt =  connection.prepareCall("{call proc_label(?,?,?,?,?)}");
			for(int i=0;i<lab.size();i++){           
				callableSt.setString(1, "deleteLabel");
				callableSt.setInt(2, lab.get(i));	
				callableSt.setString(3, null);
				callableSt.setString(4, null);
				callableSt.setInt(5, label.getCreatedBy());				
				callableSt.addBatch();
			}
            callableSt.executeBatch();					
            connection.commit();
		}catch (Exception e) {
			e.printStackTrace();
			connection.rollback();
		}finally {
			if (connection != null)
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		
		
	}

	
	
}
