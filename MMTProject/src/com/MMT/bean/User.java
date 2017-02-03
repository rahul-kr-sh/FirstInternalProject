package com.MMT.bean;

public class User {
	private int userId;
	private String userName;
	private int userPhoneNo;
	private String userEmailId;
	private String userAddress;
	private String userPassword;
	public User(int userId, String userName, int userPhoneNo, String userEmailId, String userAddress,
			String userpassword, String userWalletId) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userPhoneNo = userPhoneNo;
		this.userEmailId = userEmailId;
		this.userAddress = userAddress;
		this.userPassword = userpassword;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getUserPhoneNo() {
		return userPhoneNo;
	}
	public void setUserPhoneNo(int userPhoneNo) {
		this.userPhoneNo = userPhoneNo;
	}
	public String getUserEmailId() {
		return userEmailId;
	}
	public void setUserEmailId(String userEmailId) {
		this.userEmailId = userEmailId;
	}
	public String getUserAddress() {
		return userAddress;
	}
	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}
	public String getUserpassword() {
		return userPassword;
	}
	public void setUserpassword(String userpassword) {
		this.userPassword = userpassword;
	}
	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", userPhoneNo=" + userPhoneNo + ", userEmailId="
				+ userEmailId + ", userAddress=" + userAddress + ", userpassword=" + userPassword + "]";
	}
	
	
	
	
	
}
