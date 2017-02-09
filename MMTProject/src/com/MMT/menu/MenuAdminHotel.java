package com.MMT.menu;

import java.sql.SQLException;
import java.util.Scanner;

import com.MMT.bean.Hotel;
import com.MMT.bean.HotelBooking;
import com.MMT.bl.AdminBlMMT;
import com.MMT.bl.HotelBlMMT;
import com.MMT.dao.HotelBookingDaoImplMMT;
import com.MMT.helper.HotelHelperMMT;

public class MenuAdminHotel {

	
	public class MenuAdminFlight {
		Hotel hotel=new Hotel();
		AdminBlMMT adminBl= new AdminBlMMT();
		HotelBooking hotelBookingBlMMT=new HotelBooking();
		HotelHelperMMT hotelhelper=new HotelHelperMMT();
		Scanner sc=new Scanner(System.in);
		public void choice() throws SQLException{
			System.out.println("1.	Insert New Hotel_Detail");//
			System.out.println("2.	Update Existing Hotel");//
			System.out.println("3.	Search Hotel");
			System.out.println("4.	Delete Hotel");//
			System.out.println("5.	Display All Hotel");
			System.out.println("6.	Go back Main Menu");
			System.out.println("Pick a option from Menu");
			System.out.println("---------------");
			int i=sc.nextInt();
			switch(i){
			
			case 1:
			{
				hotelhelper.input();
				hotel=hotelhelper.getValue();
				try {
					if(adminBl.insertHotel(hotel)>0){
						System.out.println("Successfully added");
					}
					else
					{
						System.out.println("Hote can be added");
					}
				} catch (Exception e) {
					System.out.println("Please try again");
				}
				
			}
			
				return ;
			case 2:
				System.out.println("Enter Hotel id to be update");
				String fId=sc.next();
				System.out.println("Enter Hotel details");
				hotelhelper.input();
				hotel=hotelhelper.getValue();
				try {
					
					if(adminBl.modifyHotel(fId, hotel)>0){
						System.out.println("Successfully updated");
					}
					else
					{
						System.out.println("hotel can not be updated");
					}
				} catch (ClassNotFoundException e) {
					System.out.println("Please try again");
//					e.printStackTrace();
				}
				
				
			case 3:
				System.out.println("Enter Hotel id to be search");
				try {
					hotel=hotelBookingBlMMT;
					if(hotel!=null){
						System.out.println(hotel);
					}
					else 
						System.out.println("Please enter correct hotel id");
					
				} catch (ClassNotFoundException e) {
					System.out.println("Please try again");
//					
				}
				
				
				
			case 4:
				System.out.println("Enter hotel id to be delete");
			try{
				
				if(adminBl.deleteFlight(sc.next())>0){
					System.out.println("Successfully deleted");
				}
				else
				{
					System.out.println("hotel can not deleted");
				}
			} catch (ClassNotFoundException e) {
				System.out.println("Please try again");
//				e.printStackTrace();
			}
				
				
				
			case 5:
				try {
					((HotelBlMMT) hotelBookingBlMMT).displayHotel();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return ;
//			case 6:
				
			default :
				System.out.println("Invalid choice");
				
				
			}
		}
	}


}
