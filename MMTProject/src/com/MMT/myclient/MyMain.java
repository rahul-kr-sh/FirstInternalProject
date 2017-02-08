package com.MMT.myclient;

import com.MMT.menu.HomePage;

public class MyMain {

	public static void main(String[] args) {
		
		System.out.println("Welcome to Flight and Hotel Booking Website");
		HomePage home=new HomePage();
		while(true){
			home.HomePageMenu();
		}
		
	}

}
