package com.MMT.helper;

import java.util.Scanner;


import com.MMT.bean.User;

public class SignupHelper {
	User us=new User();
	Scanner sc=new Scanner(System.in);
public void input(){
	String EMAIL_REGEX = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
	System.out.println("Enter User Id :");
	us.setUserId(sc.next());
	System.out.println("Enter User Name :");
	us.setUserName(sc.next());
	System.out.println("Enter User password :");
	us.setUserPassword(sc.next());
	System.out.println("Enter User EmailId :");
	String email1=sc.next();
	Boolean b = email1.matches(EMAIL_REGEX);
	if(b==true)
	{us.setUserEmailId(email1);}
	else{
		do
		{
		System.out.println("Enter Valid User EmailId in format abc12@xyz.in :");
		email1=sc.next();
		 b = email1.matches(EMAIL_REGEX);
		}while(b==false);
	}
	us.setUserEmailId(email1);
	System.out.println("Enter User phone no :");
	us.setUserPhoneNo(sc.nextLong());
	System.out.println("Enter User Address");
	us.setUserAddress(sc.next());
}

public User getUs() {
	return us;
}
public void setUs(User us) {
	this.us = us;
}


}

