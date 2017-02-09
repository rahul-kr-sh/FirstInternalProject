package com.MMT.menu;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import com.MMT.bean.Hotel;
import com.MMT.bean.HotelBooking;
import com.MMT.bean.HotelRoom;
import com.MMT.bl.AdminBlMMT;
import com.MMT.bl.HotelBlMMT;
import com.MMT.dao.HotelBookingDaoImplMMT;
import com.MMT.dao.HotelDaoImplMMT;
import com.MMT.helper.HotelHelperMMT;
import com.MMT.helper.HotelRoomHelperMMT;

public class MenuAdminHotel {

	
	
		Hotel hotel=new Hotel();
		HotelBlMMT hotelBl=new HotelBlMMT();
		AdminBlMMT adminBl= new AdminBlMMT();
		HotelBooking hotelBookingBlMMT=new HotelBooking();
		HotelHelperMMT hotelhelper=new HotelHelperMMT();
		Scanner sc=new Scanner(System.in);
		public void choice() throws SQLException, IOException, ClassNotFoundException{
			System.out.println("1.	Insert New Hotel_Detail");
			System.out.println("2.	Update Existing Hotel");
			System.out.println("3.	Search Hotel");
			System.out.println("4.	Delete Hotel");
			System.out.println("5.	Display All Hotel");
			System.out.println("6.	Go back Main Menu");
			System.out.println("Pick a option from Menu");
			System.out.println("---------------");
			int i=sc.nextInt();
			switch(i){
			
			case 1:
			{
				hotelhelper.input();
				Hotel hotel;
				hotel=hotelhelper.getHotel();
				HotelDaoImplMMT hotelDaoImplOb=new HotelDaoImplMMT();
				hotelDaoImplOb.insertHotel(hotel);
				try {
					if(adminBl.insertHotel(hotel)>0){
						System.out.println("Successfully added");
						choice(); 
					}
					else
					{
						System.out.println("Hote can't be added");
						choice() ;
					}
				} catch (Exception e) {
					System.out.println("Please try again");
					choice() ;
				}
				
			}
			
				return ;
			case 2:
				System.out.println("Enter Hotel id to be update");
				String hotelId=sc.next();
				System.out.println("Enter Hotel details");
				hotelhelper.input();
				hotel=hotelhelper.getHotel();
				try {
					
					if(adminBl.modifyHotel(hotelId, hotel)>0){
						System.out.println("Successfully updated");
						choice(); 
					}
					else
					{
						System.out.println("hotel can not be updated");
						choice(); 
					}
				} catch (ClassNotFoundException e) {
					System.out.println("Please try again");
					choice(); 
//					e.printStackTrace();
				}
				break;
				
			case 3:
				System.out.println("Enter Hotel id to be search");
				//					hotel=new hotelBookingBlMMT;
				if(hotel!=null){
					System.out.println(hotel);
					choice(); 
				}
				else 
					System.out.println("Please enter correct hotel id");
				choice(); 
				
				break;
				
			case 4:
				System.out.println("Enter hotel id to be delete");
			try{
				
				if(adminBl.deleteFlight(sc.next())>0){
					System.out.println("Successfully deleted");
					choice(); 
				}
				else
				{
					System.out.println("hotel can not deleted");
					choice(); 
				}
			} catch (ClassNotFoundException e) {
				System.out.println("Please try again");
//				e.printStackTrace();
			}
				
				break;
				
			case 5:
				ArrayList<Hotel> arraylistHotel=new ArrayList<Hotel>();
				arraylistHotel= hotelBl.displayHotel();
				choice(); 
				break;
		case 6:choice();
			
				break;
			default :
				System.out.println("Invalid choice");
				choice(); 
				break;
				
			}
		}
	}



