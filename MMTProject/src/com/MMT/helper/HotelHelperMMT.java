package com.MMT.helper;
import java.util.ArrayList;
import java.util.Scanner;

import com.MMT.bean.Hotel;
import com.MMT.bean.HotelRoom;
public class HotelHelperMMT {
	HotelRoomHelperMMT hrHelp=new HotelRoomHelperMMT();
	ArrayList<HotelRoom> arrayListHotelRoom=new ArrayList<HotelRoom>();
	Scanner sc=new Scanner(System.in); 
	Hotel hotel=new Hotel();
	public void input(){
		System.out.println("Enter Hotel Id :");
		hotel.setHotelId(sc.next());
		System.out.println("Enter Hotel Name :");
		hotel.setHotelName(sc.next());
		System.out.println("Enter Hotel Location :");
		hotel.setHotelLocation(sc.next().toLowerCase());
		System.out.println("Enter Information :");
		hotel.setHotelInfo(sc.next());
		System.out.println("Add hotel room details : ");
		 hrHelp.input();
		 arrayListHotelRoom=hrHelp.getArrayListHotelRoom();
		hotel.setHotelRoom(arrayListHotelRoom);
	}
	public Hotel getHotel() {
		return hotel;
	}
	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}
	
}
