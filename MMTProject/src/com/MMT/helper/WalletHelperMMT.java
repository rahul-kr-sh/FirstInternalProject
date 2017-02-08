package com.MMT.helper;

import java.util.Scanner;

import com.MMT.bean.Wallet;

public class WalletHelperMMT {
	private Wallet ob=new Wallet();
	public Wallet getOb() {
		return ob;
	}
	public void setOb(Wallet ob) {
		this.ob = ob;
	}
	Scanner sc=new Scanner(System.in);
	public void input(){
		System.out.println("Enter userId : ");
		ob.setUserId(sc.next());
		System.out.println("Enter Wallet Balance");
		ob.setWalletBalance(sc.nextDouble());
	}
}
