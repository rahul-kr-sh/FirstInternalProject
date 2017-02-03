package com.MMT.bean;

public class Admin {
	private int adminId;
	private String adminName;
	private int adminPhoneNo;
	private String adminEmailId;
	private String adminAddress;
	private String adminPassword;
	public Admin(int adminId, String adminName, int adminPhoneNo, String adminEmailId, String adminAddress,
			String adminPassword) {
		super();
		this.adminId = adminId;
		this.adminName = adminName;
		this.adminPhoneNo = adminPhoneNo;
		this.adminEmailId = adminEmailId;
		this.adminAddress = adminAddress;
		this.adminPassword = adminPassword;
	}
	public Admin() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getAdminId() {
		return adminId;
	}
	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}
	public String getAdminName() {
		return adminName;
	}
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
	public int getAdminPhoneNo() {
		return adminPhoneNo;
	}
	public void setAdminPhoneNo(int adminPhoneNo) {
		this.adminPhoneNo = adminPhoneNo;
	}
	public String getAdminEmailId() {
		return adminEmailId;
	}
	public void setAdminEmailId(String adminEmailId) {
		this.adminEmailId = adminEmailId;
	}
	public String getAdminAddress() {
		return adminAddress;
	}
	public void setAdminAddress(String adminAddress) {
		this.adminAddress = adminAddress;
	}
	public String getAdminPassword() {
		return adminPassword;
	}
	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}
	@Override
	public String toString() {
		return "Admin [adminId=" + adminId + ", adminName=" + adminName + ", adminPhoneNo=" + adminPhoneNo
				+ ", adminEmailId=" + adminEmailId + ", adminAddress=" + adminAddress + ", adminPassword="
				+ adminPassword + "]";
	}
	
	
}
