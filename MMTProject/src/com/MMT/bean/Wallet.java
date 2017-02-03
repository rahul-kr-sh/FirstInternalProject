package com.MMT.bean;

public class Wallet {
	private int userId;
	private double walletBalance;
	public Wallet(int userId, double walletBalance) {
		super();
		this.userId = userId;
		this.walletBalance = walletBalance;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public double getWalletBalance() {
		return walletBalance;
	}
	public void setWalletBalance(double walletBalance) {
		this.walletBalance = walletBalance;
	}
	@Override
	public String toString() {
		return "Wallet [userId=" + userId + ", walletBalance=" + walletBalance + "]";
	}
	
	
}
