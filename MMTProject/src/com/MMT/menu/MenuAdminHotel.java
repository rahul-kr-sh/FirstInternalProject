package com.MMT.menu;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import com.MMT.bean.Admin;
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

	
	AdminDashboard adminDashboard = new AdminDashboard();
		Hotel hotel=new Hotel();
		HotelBlMMT hotelBl=new HotelBlMMT();
		AdminBlMMT adminBl= new AdminBlMMT();
		HotelBooking hotelBookingBlMMT=new HotelBooking();
		HotelHelperMMT hotelhelper=new HotelHelperMMT();
		Scanner sc=new Scanner(System.in);
		public void choice(Admin admin) throws SQLException, IOException, ClassNotFoundException{
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
				AdminBlMMT adminBl=new AdminBlMMT();
				int i1=adminBl.insertHotel(hotel);
				try {
					if(i1>0){
						System.out.println("Successfully added");
						choice(admin); 
					}
					else
					{
						System.out.println("Hotel can't be added");
						choice(admin) ;
					}
				} catch (Exception e) {
					System.out.println("Please try again");
					choice(admin) ;
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
						choice(admin); 
					}
					else
					{
						System.out.println("hotel can not be updated");
						choice(admin); 
					}
				} catch (ClassNotFoundException e) {
					System.out.println("Please try again");
					choice(admin); 
//					e.printStackTrace();
				}
				break;
				
			case 3:
				System.out.println("Enter Hotel id to be search");
				String hotelID=sc.next();
				Hotel hotel;
				hotel=hotelBl.searchHotel(hotelID);
				//					hotel=new hotelBookingBlMMT;
				if(hotel!=null){
					System.out.println(hotel);
					choice(admin); 
				}
				else 
					System.out.println("Please enter correct hotel id");
				choice(admin); 
				
				break;
				
			case 4:
				System.out.println("Enter hotel id to be delete");
				String hotelIdInput=sc.next();
			try{
				
				if(adminBl.deleteHotel(hotelIdInput)>0){
					System.out.println("Successfully deleted");
					choice(admin); 
				}
				else
				{
					System.out.println("hotel can not deleted");
					choice(admin); 
				}
			} catch (ClassNotFoundException e) {
				System.out.println("Please try again");
//				e.printStackTrace();
			}
				
				break;
				
			case 5:
				ArrayList<Hotel> arraylistHotel=new ArrayList<Hotel>();
				arraylistHotel= hotelBl.displayHotel();
				System.out.println(arraylistHotel);
				choice(admin); 
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



