package com.MMT.menu;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import com.MMT.bean.Admin;
import com.MMT.bean.Flight;
import com.MMT.bl.AdminBlMMT;
import com.MMT.bl.FlightBookingBlMMT;
import com.MMT.helper.FlightHelper;

public class MenuAdminFlight {
	Flight flight=new Flight();
	AdminBlMMT adminBl= new AdminBlMMT();
	AdminDashboard adminDashboard = new AdminDashboard();
	FlightBookingBlMMT flightBookingBlMMT=new FlightBookingBlMMT();
	FlightHelper flightHelper=new FlightHelper();
	Scanner sc=new Scanner(System.in);
	public void choice(Admin admin) throws SQLException, ClassNotFoundException, IOException{
		System.out.println("1.	Insert New Flight");//
		System.out.println("2.	Update Existing Flight");//
		System.out.println("3.	Search Flight");
		System.out.println("4.	Delete Flight");//
		System.out.println("5.	Display All Flight");
		System.out.println("6.	Go back Main Menu");
		System.out.println("Pick a option from Menu");
		System.out.println("---------------");
		int i=sc.nextInt();
		switch(i){
		case 1:
			flightHelper.input();
			flight=flightHelper.getFlight();
			try {
				if(adminBl.insertFlight(flight)>0){
					System.out.println("Successfully added");
					choice(admin);
				}
				else
				{
					System.out.println("Flight could not be added");
					choice(admin);

				}
			} catch (Exception e) {
				System.out.println("Flight Already Exists!!");
				e.printStackTrace();
				choice(admin);
			
			}
			break;
		case 2:
			System.out.println("Enter Flight id to be update");
			String fId=sc.next();
			System.out.println("Enter Flight details");
			flightHelper.input();
			flight=flightHelper.getFlight();
			try {
				
				if(adminBl.modifyFlight(fId, flight)>0){
					System.out.println("Successfully updated");
					choice(admin);

				}
				else
				{
					System.out.println("Flight could not be updated");
					choice(admin);

				}
			} catch (ClassNotFoundException e) {
				System.out.println("Please try again");
				choice(admin);

//				e.printStackTrace();
			} catch (IOException e) {

				// TODO Auto-generated catch block

				e.printStackTrace();
				choice(admin);

			}
			break;
			
		case 3:
			System.out.println("Enter Flight id to be search");
			try {
				flight=flightBookingBlMMT.searchFlight(sc.next());
				if(flight!=null){
					System.out.println(flight);
					choice(admin);
				}
				else {
					System.out.println("Please enter correct flight id");
					choice(admin);

				}
					
				
			} catch (ClassNotFoundException e) {
				System.out.println("Please try again");
				choice(admin);

//				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				choice(admin);

			}
			
			break;
			
		case 4:
			System.out.println("Enter Flight id to be delete");
		try{
			
			if(adminBl.deleteFlight(sc.next())>0){
				System.out.println("Successfully deleted");
				choice(admin);

			}
			else
			{
				System.out.println("Flight could not be deleted");
				choice(admin);

			}
		} catch (ClassNotFoundException e) {
			System.out.println("Please try again");
			choice(admin);

//			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			choice(admin);

		}
			
			break;
			
		case 5:
			try {
				ArrayList<Flight> fList=flightBookingBlMMT.displayFlight();
				for(Flight f:fList){
					System.out.println(f);
				}
				choice(admin);

			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				choice(admin);

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				choice(admin);

			}
			break;
		case 6:
			adminDashboard.showDashboard(admin);
			break;
			
		default :
			System.out.println("Invalid choice");
			choice(admin);

			break;
			
		}
	}
}




