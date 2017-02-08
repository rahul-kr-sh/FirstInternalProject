package com.MMT.menu;

import java.util.Scanner;

public class HomePage {
	public void HomePageMenu(){
		System.out.println("-------------HomePage--------------");
		
		System.out.println("1.Login");
		System.out.println("2. Signup");
		System.out.println("3. Flight");
		System.out.println("4. Hotel");
		System.out.println("5. Exit");
		System.out.println("Pick a option from Menu");
		Scanner sc=new Scanner(System.in);
		int input=sc.nextInt();
		LoginMenu lm=new LoginMenu();
		switch(input){
		case 1:
			 lm.LoginPage();
			return;
		case 2:
		
			return;
		
		case 3:
			return;
		
		case 4:
			return;
			
		case 5:
			return;
		
		default:
			System.out.println("Invalid Input");
			HomePageMenu();
			break;
		}
	}
}
