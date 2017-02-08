package com.MMT.menu;

import java.sql.SQLException;
import java.util.Scanner;

import com.MMT.bean.Admin;
import com.MMT.bl.AdminBlMMT;
import com.MMT.bl.UserBlMMT;
import com.MMT.helper.AdminHelperMMT;

public class AdminDashboard {
	AdminBlMMT adminBl;
	AdminHelperMMT adminHelper;
	UserBlMMT userBl=new UserBlMMT();
	Admin admin;
	public void showDashboard(Admin admin) throws SQLException{
		this.admin=admin;
		adminBl=new AdminBlMMT();
		System.out.println("-------------Admin HomePage--------------");
		System.out.println("Welcome "+ admin.getAdminName());
		System.out.println("1.	Admin Profile");
		System.out.println("2.	Hotel Profile");
		System.out.println("3.	Flight Profile");
		System.out.println("4.	Promotion Pofile");
		System.out.println("5.	User Profile");
		System.out.println("5.	Logout.");
		System.out.println("Pick a option from Menu");
		Scanner sc=new Scanner(System.in);
		int input=sc.nextInt();
		switch(input){
		case 1:
			 adminProfileChoice();
			return;
		case 2:
			//hoteladminProfile
			return;
		
		case 3:
			//FlightAdminProfile
			return;
		
		case 4:
			MenuAdminPromotion menuAdminPromotion=new MenuAdminPromotion();
			menuAdminPromotion.choice();
			return ;
			
			
		case 5:
			System.out.println("Enter user id to see details");
			System.out.println(userBl.searchUser(sc.next())); //sc.next()
			return;
		
		default:
			System.out.println("Invalid Input");
			
			break;
		}
	}
	
	
	void adminProfileChoice() throws SQLException{
		System.out.println("-------------Admin HomePage--------------");
		System.out.println("Welcome "+ admin.getAdminName());
		System.out.println("1.	Display Profile");
		System.out.println("2.	Update Profile");
		System.out.println("3.	Add another Admin");
		System.out.println("4.	Remove another Admin");
		System.out.println("5	Go back Main Menu");
		System.out.println("Pick a option from Menu");
		Admin admin1=new Admin();
		Scanner sc=new Scanner(System.in);
		int input=sc.nextInt();
		switch(input){
		case 1:
			 
			 try {
				adminBl.searchAdmin(admin.getAdminId());
			} catch (SQLException e) {
				System.out.println("No record Found");
//				e.printStackTrace();
			}
			return;
		case 2:
			
			
			adminHelper=new AdminHelperMMT();
			adminHelper.input();
			admin1=adminHelper.getAdmin();
			try {
				adminBl.modifyAdmin(admin.getAdminId(), admin1);
			} catch (SQLException e) {
				System.out.println("Please enter correct details");
//				e.printStackTrace();
			}
			return;
		
		case 3:
			
			adminHelper=new AdminHelperMMT();
			adminHelper.input();
			admin1=adminHelper.getAdmin();
			try {
				adminBl.addAdmin(admin1);
			} catch (SQLException e) {
				System.out.println("Please try another one");
//				e.printStackTrace();
			}
			return;
		
		case 4:
			System.out.println("Enter Admin id");
		    try {
				if(adminBl.removeAdmin(sc.next())!=1){
					System.out.println("Please enter correct Admin id.");
				}
				else
					System.out.println("Admin succesfully deleted.");
				
			} catch (SQLException e) {
				System.out.println("Admin could not be delete.");
//				e.printStackTrace();
			} 
		    return ;
		case 5:showDashboard(admin);
		
		default:
			System.out.println("Invalid Input");
			
			break;
		}
	}
	
	
	
	
}
