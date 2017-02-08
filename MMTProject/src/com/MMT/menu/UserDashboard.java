package com.MMT.menu;

import com.MMT.bean.User;

public class UserDashboard {
	public void showDashboard(User user){
		
		System.out.println("Welcome "+ user.getUserName());
		System.out.println("1. Flight");
		System.out.println("2. Hotel");
		System.out.println("3. View Past Flight Bookings");
		System.out.println("4. View Past Hotel Bookings");
		System.out.println("5. Add Money to Wallet");
		System.out.println("6. Logout");
		
	}
}
