package com.precise.model;


public class ReplicateApplication {
	private String firmName;
	private int roleId;
	private String roleName;
	private int rmId;
	private int firmId;
	private int sourceRoleId;
	private String destinationRoleId;
	private int sourceAppId;
	private int apply;
	private int CL;
	private int SL;
	private int HL;
	private int sourceSL;
	private int targetSL;
	private int noOfRound;
	public int getNoOfRound() {
		return noOfRound;
	}
	public void setNoOfRound(int noOfRound) {
		this.noOfRound = noOfRound;
	}
	public int getSourceSL() {
		return sourceSL;
	}
	public void setSourceSL(int sourceSL) {
		this.sourceSL = sourceSL;
	}
	public int getTargetSL() {
		return targetSL;
	}
	public void setTargetSL(int targetSL) {
		this.targetSL = targetSL;
	}
	public int getApply() {
		return apply;
	}
	public void setApply(int apply) {
		this.apply = apply;
	}
	public int getCL() {
		return CL;
	}
	public void setCL(int cL) {
		CL = cL;
	}
	public int getSL() {
		return SL;
	}
	public void setSL(int sL) {
		SL = sL;
	}
	public int getHL() {
		return HL;
	}
	public void setHL(int hL) {
		HL = hL;
	}
	public int getSourceRoleId() {
		return sourceRoleId;
	}
	public void setSourceRoleId(int sourceRoleId) {
		this.sourceRoleId = sourceRoleId;
	}
	
	public int getSourceAppId() {
		return sourceAppId;
	}
	public void setSourceAppId(int sourceAppId) {
		this.sourceAppId = sourceAppId;
	}
	public String getDestinationRoleId() {
		return destinationRoleId;
	}
	public void setDestinationRoleId(String destinationRoleId) {
		this.destinationRoleId = destinationRoleId;
	}
	public int getFirmId() {
		return firmId;
	}
	public void setFirmId(int firmId) {
		this.firmId = firmId;
	}
	private String rmName;
	public String getFirmName() {
		return firmName;
	}
	public void setFirmName(String firmName) {
		this.firmName = firmName;
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public int getRmId() {
		return rmId;
	}
	public void setRmId(int rmId) {
		this.rmId = rmId;
	}
	public String getRmName() {
		return rmName;
	}
	public void setRmName(String rmName) {
		this.rmName = rmName;
	}
	

}
