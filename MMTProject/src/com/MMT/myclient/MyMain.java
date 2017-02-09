package com.MMT.myclient;

import java.io.IOException;
import java.sql.SQLException;

import com.MMT.menu.HomePage;

public class MyMain {

	public static void main(String[] args) {

		System.out.println("Welcome to Flight and Hotel Booking Website");
		HomePage home = new HomePage();
		// while(true){

		try {
			home.homePageMenu();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// }

	}

}
