package com.precise.model;

import java.util.Date;
import java.util.List;

public class Reply {
    private int questionId;
    private int replyId;
    private String questionName;
    private String reply;
    private String photo;
    private Date createdDate;
    private List<Reply> replyList;
    private String userName;
    private String roleName;
    private int postPermission;
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getQuestionId() {
		return questionId;
	}
	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}
	public int getReplyId() {
		return replyId;
	}
	public void setReplyId(int replyId) {
		this.replyId = replyId;
	}
	public String getReply() {
		return reply;
	}
	
	public String getQuestionName() {
		return questionName;
	}
	public void setQuestionName(String questionName) {
		this.questionName = questionName;
	}
	public void setReply(String reply) {
		this.reply = reply;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public List<Reply> getReplyList() {
		return replyList;
	}
	public void setReplyList(List<Reply> replyList) {
		this.replyList = replyList;
	}
	public int getPostPermission() {
		return postPermission;
	}
	public void setPostPermission(int postPermission) {
		this.postPermission = postPermission;
	}
       
    
}
