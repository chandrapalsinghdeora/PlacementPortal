package com.precise.model;

import java.sql.Timestamp;

public class Inbox {
	private int inboxId;
	private String inboxSubject;
	private String inboxText;
	private int labelId;
	private int receiverId;
	private int senderId;
	private int statusId;
	private int createdBy;
	private Timestamp createdDate;
	private int updatedBy;
	private Timestamp updatedDate;
	private String datetime;
	private String sender_email;
	
	public String getDatetime() {
		return datetime;
	}
	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}
	private String attachmentPath;
	public String getAttachmentPath() {
		return attachmentPath;
	}
	public void setAttachmentPath(String attachmentPath) {
		this.attachmentPath = attachmentPath;
	}
	public int getInboxId() {
		return inboxId;
	}
	public void setInboxId(int inboxId) {
		this.inboxId = inboxId;
	}
	public String getInboxSubject() {
		return inboxSubject;
	}
	public void setInboxSubject(String inboxSubject) {
		this.inboxSubject = inboxSubject;
	}
	public String getInboxText() {
		return inboxText;
	}
	public void setInboxText(String inboxText) {
		this.inboxText = inboxText;
	}
	public int getLabelId() {
		return labelId;
	}
	public void setLabelId(int labelId) {
		this.labelId = labelId;
	}
	public int getReceiverId() {
		return receiverId;
	}
	public void setReceiverId(int receiverId) {
		this.receiverId = receiverId;
	}
	public int getSenderId() {
		return senderId;
	}
	public void setSenderId(int senderId) {
		this.senderId = senderId;
	}
	public int getStatusId() {
		return statusId;
	}
	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}
	public int getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}
	public Timestamp getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}
	public int getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(int updatedBy) {
		this.updatedBy = updatedBy;
	}
	public Timestamp getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(Timestamp updatedDate) {
		this.updatedDate = updatedDate;
	}
	public String getSender_email() {
		return sender_email;
	}
	public void setSender_email(String sender_email) {
		this.sender_email = sender_email;
	}
	
}
