package com.MMT.menu;

import java.io.IOException;
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
	HomePage homePage = new HomePage();
	Admin admin;
	public void showDashboard(Admin admin) throws SQLException, ClassNotFoundException, IOException{
		this.admin=admin;
		adminBl=new AdminBlMMT();
		System.out.println("-------------Admin HomePage--------------");
		System.out.println("Welcome "+ admin.getAdminName());
		System.out.println("1.	Admin Profile");
		System.out.println("2.	Hotel Profile");
		System.out.println("3.	Flight Profile");
		System.out.println("4.	Promotion Pofile");
		System.out.println("5.	User Profile");
		System.out.println("6.	Logout.");
		System.out.println("Pick a option from Menu");
		Scanner sc=new Scanner(System.in);
		int input=sc.nextInt();
		switch(input){
		case 1:
			 adminProfileChoice();
			return;
		case 2:
			MenuAdminHotel menuAdminHotel= new MenuAdminHotel ();
			menuAdminHotel.choice();
			return;
		
		case 3:
			MenuAdminFlight menuAdminFlight=new MenuAdminFlight();
			menuAdminFlight.choice(admin);
			return;
		
		case 4:
			MenuAdminPromotion menuAdminPromotion=new MenuAdminPromotion();
			menuAdminPromotion.choice(admin);
			return ;
			
			
		case 5:
			System.out.println("Enter user id to see details");
			try {
				System.out.println(userBl.searchUser(sc.next()));
				showDashboard(admin);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} //sc.next()
			return;
		
		case 6:
			try {
				homePage.homePageMenu();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		default:
			System.out.println("Invalid Input");
			
			break;
		}
	}
	
	
	void adminProfileChoice() throws SQLException, ClassNotFoundException, IOException{
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
				Admin admin2=adminBl.searchAdmin(admin.getAdminId());
				System.out.println("Admin ID : "+admin2.getAdminId());
				System.out.println("Admin Name : "+admin2.getAdminName());
				System.out.println("Admin PhoneNumber : "+admin2.getAdminPhoneNo());
				System.out.println("Admin EmailID : "+admin2.getAdminEmailId());
				System.out.println("Admin Address : "+admin2.getAdminAddress());
				adminProfileChoice();
			} catch (SQLException | ClassNotFoundException | IOException e) {
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
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		    return ;
		case 5:showDashboard(admin);
		
		default:
			System.out.println("Invalid Input");
			
			break;
		}
	}
	
	
	
	
}
