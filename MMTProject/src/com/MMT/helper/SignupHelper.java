package com.MMT.helper;

import java.util.Scanner;


import com.MMT.bean.User;

public class SignupHelper {
	User us=new User();
	Scanner sc=new Scanner(System.in);
public void input(){
	System.out.println("Enter User Id :");
	us.setUserId(sc.next());
	System.out.println("Enter User Name :");
	us.setUserName(sc.next());
	System.out.println("Enter User password :");
	us.setUserPassword(sc.next());
	System.out.println("Enter User EmailId :");
	us.setUserEmailId(sc.next());
	System.out.println("Enter User phone no :");
	us.setUserPhoneNo(sc.nextLong());
	System.out.println("Enter User Address");
	us.setUserAddress(sc.next());
}
public User getValue() {
	return us;
}
public void setValue(User us) {
	this.us = us;
}


}

