package com.precise.model;



import java.util.List;

import org.springframework.web.multipart.MultipartFile;


public class Announcement {

	private int announmtId;
	private String title;
	private String description;
	private String dateTime;
	private String groupToPost;
	private String otherPostText;
	private Boolean urgentFlag;
	MultipartFile fileupload;
	private String fileName;
	private String filePath;
	private List<String> pgpgroup;
	private int RowCount;
	private int createdBy;
	private String url;
	private String createdDate;
	
	public int getRowCount() {
		return RowCount;
	}
	public void setRowCount(int rowCount) {
		RowCount = rowCount;
	}
	public int getAnnounmtId() {
		return announmtId;
	}
	public void setAnnounmtId(int announmtId) {
		this.announmtId = announmtId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDateTime() {
		return dateTime;
	}
	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}
	
	public String getGroupToPost() {
		return groupToPost;
	}
	public void setGroupToPost(String groupToPost) {
		this.groupToPost = groupToPost;
	}
	public String getOtherPostText() {
		return otherPostText;
	}
	public void setOtherPostText(String otherPostText) {
		this.otherPostText = otherPostText;
	}
	public Boolean getUrgentFlag() {
		return urgentFlag;
	}
	public void setUrgentFlag(Boolean urgentFlag) {
		this.urgentFlag = urgentFlag;
	}
	public MultipartFile getFileupload() {
		return fileupload;
	}
	public void setFileupload(MultipartFile fileupload) {
		this.fileupload = fileupload;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public List<String> getPgpgroup() {
		return pgpgroup;
	}
	public void setPgpgroup(List<String> pgpgroup) {
		this.pgpgroup = pgpgroup;
	}
	public int getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	
	
	
	
}
