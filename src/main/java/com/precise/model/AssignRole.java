package com.precise.model;

import java.util.List;

public class AssignRole {
	private String studentName;
	private int studentId;
	private int mentorId;
	private int rmId;
	private int schedulingAdminId;
	private int negoId;
	private int offerProcessorId;
	private int sideTrackerId;
	private int superRMOnTheDayId;
	private int superRMThroughoutTheYearId;
	private int satcomId;
	private int printingTeamId;
	private int startoAdminId;
	private String userName;
	private int assignRole;
	private boolean active;
	private boolean rmActive;
	private boolean mentorActive;
	private boolean negoActive;
	private boolean offerProcessorActive;
	private boolean sideTrackerActive;
	private boolean schedulingAdminActive;
	private boolean superRMOnTheDayActive;
	private boolean superRMThroughoutTheYearActive;
	private boolean satcomActive;
	private boolean printingTeamActive;
	private boolean startoAdminActive;
	private List<Integer> assignedRoles;
	private int coordinatorId;
	private int schedulerId;
	private boolean coordinatorActive;
	private boolean schedulerActive;
	
	
	
	public int getOfferProcessorId() {
		return offerProcessorId;
	}
	public void setOfferProcessorId(int offerProcessorId) {
		this.offerProcessorId = offerProcessorId;
	}
	public boolean isOfferProcessorActive() {
		return offerProcessorActive;
	}
	public void setOfferProcessorActive(boolean offerProcessorActive) {
		this.offerProcessorActive = offerProcessorActive;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getAssignRole() {
		return assignRole;
	}
	public void setAssignRole(int assignRole) {
		this.assignRole = assignRole;
	}
	public int getSideTrackerId() {
		return sideTrackerId;
	}
	public void setSideTrackerId(int sideTrackerId) {
		this.sideTrackerId = sideTrackerId;
	}
	public boolean isSideTrackerActive() {
		return sideTrackerActive;
	}
	public void setSideTrackerActive(boolean sideTrackerActive) {
		this.sideTrackerActive = sideTrackerActive;
	}

	public int getStudentId() {
		return studentId;
	}
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	public int getMentorId() {
		return mentorId;
	}
	public void setMentorId(int mentorId) {
		this.mentorId = mentorId;
	}
	public int getRmId() {
		return rmId;
	}
	public void setRmId(int rmId) {
		this.rmId = rmId;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public List<Integer> getAssignedRoles() {
		return assignedRoles;
	}
	public void setAssignedRoles(List<Integer> assignedRoles) {
		this.assignedRoles = assignedRoles;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public boolean isRmActive() {
		return rmActive;
	}
	public void setRmActive(boolean rmActive) {
		this.rmActive = rmActive;
	}
	public boolean isMentorActive() {
		return mentorActive;
	}
	public void setMentorActive(boolean mentorActive) {
		this.mentorActive = mentorActive;
	}
	public int getSchedulingAdminId() {
		return schedulingAdminId;
	}
	public void setSchedulingAdminId(int schedulingAdminId) {
		this.schedulingAdminId = schedulingAdminId;
	}
	public int getNegoId() {
		return negoId;
	}
	public void setNegoId(int negoId) {
		this.negoId = negoId;
	}
	public int getSuperRMOnTheDayId() {
		return superRMOnTheDayId;
	}
	public void setSuperRMOnTheDayId(int superRMOnTheDayId) {
		this.superRMOnTheDayId = superRMOnTheDayId;
	}
	public int getSuperRMThroughoutTheYearId() {
		return superRMThroughoutTheYearId;
	}
	public void setSuperRMThroughoutTheYearId(int superRMThroughoutTheYearId) {
		this.superRMThroughoutTheYearId = superRMThroughoutTheYearId;
	}
	public boolean isNegoActive() {
		return negoActive;
	}
	public void setNegoActive(boolean negoActive) {
		this.negoActive = negoActive;
	}
	public boolean isSchedulingAdminActive() {
		return schedulingAdminActive;
	}
	public void setSchedulingAdminActive(boolean schedulingAdminActive) {
		this.schedulingAdminActive = schedulingAdminActive;
	}
	public boolean isSuperRMOnTheDayActive() {
		return superRMOnTheDayActive;
	}
	public void setSuperRMOnTheDayActive(boolean superRMOnTheDayActive) {
		this.superRMOnTheDayActive = superRMOnTheDayActive;
	}
	public boolean isSuperRMThroughoutTheYearActive() {
		return superRMThroughoutTheYearActive;
	}
	public void setSuperRMThroughoutTheYearActive(boolean superRMThroughoutTheYearActive) {
		this.superRMThroughoutTheYearActive = superRMThroughoutTheYearActive;
	}
	public int getSatcomId() {
		return satcomId;
	}
	public void setSatcomId(int satcomId) {
		this.satcomId = satcomId;
	}
	public int getPrintingTeamId() {
		return printingTeamId;
	}
	public void setPrintingTeamId(int printingTeamId) {
		this.printingTeamId = printingTeamId;
	}
	public int getStartoAdminId() {
		return startoAdminId;
	}
	public void setStartoAdminId(int startoAdminId) {
		this.startoAdminId = startoAdminId;
	}
	public boolean isSatcomActive() {
		return satcomActive;
	}
	public void setSatcomActive(boolean satcomActive) {
		this.satcomActive = satcomActive;
	}
	public boolean isPrintingTeamActive() {
		return printingTeamActive;
	}
	public void setPrintingTeamActive(boolean printingTeamActive) {
		this.printingTeamActive = printingTeamActive;
	}
	public boolean isStartoAdminActive() {
		return startoAdminActive;
	}
	public void setStartoAdminActive(boolean startoAdminActive) {
		this.startoAdminActive = startoAdminActive;
	}
	public int getCoordinatorId() {
		return coordinatorId;
	}
	public void setCoordinatorId(int coordinatorId) {
		this.coordinatorId = coordinatorId;
	}
	public int getSchedulerId() {
		return schedulerId;
	}
	public void setSchedulerId(int schedulerId) {
		this.schedulerId = schedulerId;
	}
	public boolean isCoordinatorActive() {
		return coordinatorActive;
	}
	public void setCoordinatorActive(boolean coordinatorActive) {
		this.coordinatorActive = coordinatorActive;
	}
	public boolean isSchedulerActive() {
		return schedulerActive;
	}
	public void setSchedulerActive(boolean schedulerActive) {
		this.schedulerActive = schedulerActive;
	}
	
	
	
	
}
