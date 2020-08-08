package com.precise.model;

import java.sql.Date;
import java.sql.Timestamp;

public class Forum {
	private int forumId;
    private String forumName;
    private int threadCount;
    private int editPermission;
    private int postPermission;
    private int deletePermission;
    private int replyPermission;
    private Timestamp createdDate;
    private int rowCount;
    
	public int getForumId() {
		return forumId;
	}
	public void setForumId(int forumId) {
		this.forumId = forumId;
	}
	public String getForumName() {
		return forumName;
	}
	public void setForumName(String forumName) {
		this.forumName = forumName;
	}
	public int getEditPermission() {
		return editPermission;
	}
	public void setEditPermission(int editPermission) {
		this.editPermission = editPermission;
	}
	public int getPostPermission() {
		return postPermission;
	}
	public void setPostPermission(int postPermission) {
		this.postPermission = postPermission;
	}
	public int getDeletePermission() {
		return deletePermission;
	}
	public void setDeletePermission(int deletePermission) {
		this.deletePermission = deletePermission;
	}
	
	public int getReplyPermission() {
		return replyPermission;
	}
	public void setReplyPermission(int replyPermission) {
		this.replyPermission = replyPermission;
	}
	public Timestamp getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}
	
	public int getThreadCount() {
		return threadCount;
	}
	public void setThreadCount(int threadCount) {
		this.threadCount = threadCount;
	}
	
	public int getRowCount() {
		return rowCount;
	}
	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}
	
    
}
