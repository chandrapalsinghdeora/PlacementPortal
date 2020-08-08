package com.precise.model;

import java.util.List;

public class Admin {
	
	private int groupId;
	private List<Integer> studentIds;
	private int mentorId;
	private Boolean interviewExperience;
	private Boolean internshipExperience;
	private String interviewExperienceVal;
	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	

	public List<Integer> getStudentIds() {
		return studentIds;
	}

	public void setStudentIds(List<Integer> studentIds) {
		this.studentIds = studentIds;
	}

	public int getMentorId() {
		return mentorId;
	}

	public void setMentorId(int mentorId) {
		this.mentorId = mentorId;
	}

	public Boolean getInterviewExperience() {
		return interviewExperience;
	}

	public void setInterviewExperience(Boolean interviewExperience) {
		this.interviewExperience = interviewExperience;
	}

	public Boolean getInternshipExperience() {
		return internshipExperience;
	}

	public void setInternshipExperience(Boolean internshipExperience) {
		this.internshipExperience = internshipExperience;
	}

	public String getInterviewExperienceVal() {
		return interviewExperienceVal;
	}

	public void setInterviewExperienceVal(String interviewExperienceVal) {
		this.interviewExperienceVal = interviewExperienceVal;
	}
	

}
