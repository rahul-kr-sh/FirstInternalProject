package com.MMT.helper;

import java.util.Scanner;

import com.MMT.bean.Admin;

public class AdminHelperMMT {
	private Admin admin=new  Admin();
	private Scanner sc=new Scanner(System.in);
	public Admin getAdmin() {
		return admin;
	}
	public void setAdmin(Admin admin) {
		this.admin = admin;
	}
	
	public void input(){
		System.out.println("Enter Admin ID");
		admin.setAdminId(sc.next());
		System.out.println("Enter Admin Name");
		admin.setAdminName(sc.next());
		System.out.println("Enter Admin Phone No");
		admin.setAdminPhoneNo(sc.nextLong());
		System.out.println("Enter Admin Email ID");
		admin.setAdminEmailId(sc.next());
		System.out.println("Enter Admin Address");
		admin.setAdminAddress(sc.next());
	}
	
}
