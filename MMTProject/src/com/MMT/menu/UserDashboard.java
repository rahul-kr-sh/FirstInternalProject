package com.MMT.menu;

import java.util.Scanner;

import com.MMT.bean.User;

public class UserDashboard {
	Scanner sc=new Scanner(System.in);
	public void showDashboard(User user){
		System.out.println("-------------User Dashboard-----------");
		System.out.println("Welcome "+ user.getUserName()+"!!");
		System.out.println("1. Flight");
		System.out.println("2. Hotel");
		System.out.println("3. View Past Flight Bookings");
		System.out.println("4. View Past Hotel Bookings");
		System.out.println("5. Add Money to Wallet");
		System.out.println("6. Logout");
		
		System.out.println("Enter a choice: ");
		int input=sc.nextInt();
		
		switch(input){
		case 1:
			
			return;
			
		case 2:
			return;
		case 3:
			return;
		case 4:
			return;
		case 5:
			return;
		case 6:
			return;
		}
	}
}
