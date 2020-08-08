package com.precise.model;

public class RoleCompany {
	public int roleCompanyId;
	public String roleCompany;
	public int companyId;
	public int mapRoleId;
	private boolean coverLetter;
	
	public int getCompanyId() {
		return companyId;
	}
	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}
	public int getRoleCompanyId() {
		return roleCompanyId;
	}
	public void setRoleCompanyId(int roleCompanyId) {
		this.roleCompanyId = roleCompanyId;
	}
	public String getRoleCompany() {
		return roleCompany;
	}
	public void setRoleCompany(String roleCompany) {
		this.roleCompany = roleCompany;
	}
	public int getMapRoleId() {
		return mapRoleId;
	}
	public void setMapRoleId(int mapRoleId) {
		this.mapRoleId = mapRoleId;
	}
	public boolean isCoverLetter() {
		return coverLetter;
	}
	public void setCoverLetter(boolean coverLetter) {
		this.coverLetter = coverLetter;
	}

	
}
