package com.precise.model;

public class Question {
	private int questionId;
    private String question;
    private String userName;
    private int postPermission;
    private String questionCount;
    private int threadId;
    private String userId;
	private String replyContent;
	private int year;
	private String companyName;
	private int processId;
	private String processName;
	
    public String getReplyContent() {
		return replyContent;
	}


	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}


	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public int getThreadId() {
		return threadId;
	}


	public void setThreadId(int threadId) {
		this.threadId = threadId;
	}


	public String getQuestionCount() {
 		return questionCount;
 	}


 	public void setQuestionCount(String questionCount) {
 		this.questionCount = questionCount;
 	}

    
	public int getQuestionId() {
		return questionId;
	}
	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}
	public int getPostPermission() {
		return postPermission;
	}
	public void setPostPermission(int postPermission) {
		this.postPermission = postPermission;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}


	public int getYear() {
		return year;
	}


	public void setYear(int year) {
		this.year = year;
	}


	public String getCompanyName() {
		return companyName;
	}


	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}


	public int getProcessId() {
		return processId;
	}


	public void setProcessId(int processId) {
		this.processId = processId;
	}


	public String getProcessName() {
		return processName;
	}


	public void setProcessName(String processName) {
		this.processName = processName;
	}
    
}
