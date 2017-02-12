package com.MMT.helper;

import java.util.Scanner;

import com.MMT.bean.Admin;

public class AdminHelperMMT {
	boolean flag=false;
	private Admin admin=new  Admin();
	private Scanner sc=new Scanner(System.in);
	public Admin getAdmin() {
		return admin;
	}
	public void setAdmin(Admin admin) {
		this.admin = admin;
	}
	
	public void input(){
		String EMAIL_REGEX = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
		System.out.println("Enter Admin ID");
		admin.setAdminId(sc.next());
		System.out.println("Enter Admin Password");
		admin.setAdminPassword(sc.next());
		System.out.println("Enter Admin Name");
		admin.setAdminName(sc.next());
		System.out.println("Enter Admin Phone No");
		admin.setAdminPhoneNo(sc.nextLong());
		//System.out.println("Enter Admin Email ID");
		//admin.setAdminEmailId(sc.next());
		System.out.println("Enter Admin Email ID");
		String email1=sc.next();
		Boolean b = email1.matches(EMAIL_REGEX);
		if(b==true)
			admin.setAdminEmailId(email1);
		else{
		do
		{
		System.out.println("Enter Valid Admin EmailId in format abc12@xyz.in :");
			email1=sc.next();
			 b = email1.matches(EMAIL_REGEX);
			}while(b==false);}
		admin.setAdminEmailId(email1);
		System.out.println("Enter Admin Address");
		admin.setAdminAddress(sc.next());
	}
	
}
