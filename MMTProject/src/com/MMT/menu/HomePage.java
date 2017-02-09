package com.MMT.menu;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

import com.MMT.bean.User;
import com.MMT.bl.UserBlMMT;
import com.MMT.helper.SignupHelper;

public class HomePage {
	public void homePageMenu() throws ClassNotFoundException, SQLException, IOException{
		System.out.println("-------------HomePage--------------");
		
		System.out.println("1.Login");
		System.out.println("2. Signup");
		System.out.println("3. Flight");
		System.out.println("4. Hotel");
		System.out.println("5. Exit");
		System.out.println("Pick a option from Menu");
		Scanner sc=new Scanner(System.in);
		int input=sc.nextInt();
		LoginMenu loginMenu=new LoginMenu();
		UnregisteredFlightSearch UnregisteredFlightSearch = new UnregisteredFlightSearch();
		UnregisteredHotelSearch UnregisteredHotelSearch=new UnregisteredHotelSearch();
		switch(input){
		case 1:
			loginMenu.loginPage();
			break;
		case 2:
			SignupHelper signUp=new SignupHelper();
			signUp.input();
			User user=signUp.getUs();
			 UserBlMMT userBL=new  UserBlMMT();
			 boolean trueFalse=userBL.register(user);
			 if(trueFalse==true)
			 {
				 System.out.println("SignUp Successful!!");
				 homePageMenu();
			 }
			 else
			 {
				 System.out.println("SignUp Failed!!");
				 homePageMenu();
			 }
			break;
		
		case 3:
			UnregisteredFlightSearch.showDashboard();
			break;
		
		case 4:
			UnregisteredHotelSearch.showDashboard();
			break;
		case 5:
			System.out.println("Thank you!!!");
			System.exit(0);
		
		default:
			System.out.println("Invalid Input");
			homePageMenu();
		}
	}
}
