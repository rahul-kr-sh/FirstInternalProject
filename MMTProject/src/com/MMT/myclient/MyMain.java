package com.MMT.myclient;

import java.io.IOException;
import java.sql.SQLException;
import java.util.InputMismatchException;

import com.MMT.menu.HomePage;

public class MyMain {

	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {

		System.out.println("Welcome to Flight and Hotel Booking Website");
		HomePage home = new HomePage();
		// while(true){

		try {
			home.homePageMenu();
		} catch (InputMismatchException|ClassNotFoundException e) {
			System.out.println("Enter Correct option");
			home.homePageMenu();
			// TODO Auto-generated catch block
			//e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Enter Correct option");
			
			// TODO Auto-generated catch block
			//e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Enter Correct option");
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}

		// }

	}

}
