package com.precise.model;

import java.util.Date;

public class ThreadBlog {
	
	private int threadId;
	private int forumId;
	private String threadName;
	private String forumName;
	private int editPermissionId;
	private String editPermissionName;
	private int postPermissionId;
	private String postPermissionName;
	private int	deletePermissionId;
	private String	deletePermissionName;
	private int replyPermissionId;
	private int replyPermissionShareRMId;
	private String	replyPermissionName;
	private int createdId;
	private Date createdDate;
	private String status;

	private int questioncount;
	private int rowCount;
	
	
	
	public int getRowCount() {
		return rowCount;
	}
	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}
	

	public int getThreadId() {
		return threadId;
	}
	public void setThreadId(int threadId) {
		this.threadId = threadId;
	}
	public int getForumId() {
		return forumId;
	}
	public void setForumId(int forumId) {
		this.forumId = forumId;
	}
	public String getThreadName() {
		return threadName;
	}
	public void setThreadName(String threadName) {
		this.threadName = threadName;
	}
	public String getForumName() {
		return forumName;
	}
	public void setForumName(String forumName) {
		this.forumName = forumName;
	}
	
	public int getEditPermissionId() {
		return editPermissionId;
	}
	public void setEditPermissionId(int editPermissionId) {
		this.editPermissionId = editPermissionId;
	}
	public String getEditPermissionName() {
		return editPermissionName;
	}
	public void setEditPermissionName(String editPermissionName) {
		this.editPermissionName = editPermissionName;
	}
	public int getPostPermissionId() {
		return postPermissionId;
	}
	public void setPostPermissionId(int postPermissionId) {
		this.postPermissionId = postPermissionId;
	}
	public String getPostPermissionName() {
		return postPermissionName;
	}
	public void setPostPermissionName(String postPermissionName) {
		this.postPermissionName = postPermissionName;
	}
	public int getDeletePermissionId() {
		return deletePermissionId;
	}
	public void setDeletePermissionId(int deletePermissionId) {
		this.deletePermissionId = deletePermissionId;
	}
	public String getDeletePermissionName() {
		return deletePermissionName;
	}
	public void setDeletePermissionName(String deletePermissionName) {
		this.deletePermissionName = deletePermissionName;
	}
	
	public int getReplyPermissionId() {
		return replyPermissionId;
	}
	public void setReplyPermissionId(int replyPermissionId) {
		this.replyPermissionId = replyPermissionId;
	}
	public int getReplyPermissionShareRMId() {
		return replyPermissionShareRMId;
	}
	public void setReplyPermissionShareRMId(int replyPermissionShareRMId) {
		this.replyPermissionShareRMId = replyPermissionShareRMId;
	}
	public String getReplyPermissionName() {
		return replyPermissionName;
	}
	public void setReplyPermissionName(String replyPermissionName) {
		this.replyPermissionName = replyPermissionName;
	}
	public int getCreatedId() {
		return createdId;
	}
	public void setCreatedId(int createdId) {
		this.createdId = createdId;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getQuestioncount() {
		return questioncount;
	}
	public void setQuestioncount(int questioncount) {
		this.questioncount = questioncount;
	}
	

}
