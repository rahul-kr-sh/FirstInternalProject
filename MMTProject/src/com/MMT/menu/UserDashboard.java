package com.MMT.menu;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Scanner;

import com.MMT.bean.Flight;
import com.MMT.bean.User;
import com.MMT.bl.FlightBookingBlMMT;

public class UserDashboard {
	FlightBookingBlMMT fbl = new FlightBookingBlMMT();
	Scanner sc=new Scanner(System.in);
	public void showDashboard(User user){
		System.out.println("-------------User Dashboard-----------");
		System.out.println("Welcome "+ user.getUserName()+"!!");
		System.out.println("1.Search Flight");
		System.out.println("2. Search Hotel");
		System.out.println("3. View Past Flight Bookings");
		System.out.println("4. View Past Hotel Bookings");
		System.out.println("5. Add Money to Wallet");
		System.out.println("6. Logout");
		
		System.out.println("Enter a choice: ");
		int input=sc.nextInt();
		
		switch(input){
		case 1:
			System.out.println("Enter Source:");
			String source=sc.next();
			System.out.println("Enter Destination:");
			String destination=sc.next();
			ArrayList<Flight> fList;
			LinkedHashMap<Integer, Flight> flightMap=new LinkedHashMap<Integer,Flight>();
			try{
			fList=fbl.searchFlight(source, destination);
			int i=1;
			for(Flight findex:fList){
				flightMap.put(i++, findex);
			}
			i=1;
			for(Flight f:fList){
				System.out.println(i++ +":"+f);
			}
			System.out.println("Pick a flight :");
			int v=sc.nextInt();
			
			Flight fpicked=flightMap.get(v);
			System.out.println("Enter No of seats:");
			int seats=sc.nextInt();
			System.out.println("Checking!!!!");
			
			if(fbl.bookFlight(user.getUserId(), fpicked.getFlightId(), source, destination, seats)){
				System.out.println("Booking Done");
			}
			
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
			
		case 2:
			//hotelDisplay();
			break;
		case 3:
			//flightBookingDisplay();
			break;
		case 4:
			//hotelBookingDisplay();
			break;
		case 5:
			//addMoneyDisplay();
			break;
		case 6:
			System.out.println("Successfully logged out!!!");
			HomePage hp=new HomePage();
			hp.HomePageMenu();
			break;
		default:
			System.out.println("Invalid Input!!");
			break;
		}
	}
	
	
	
}
