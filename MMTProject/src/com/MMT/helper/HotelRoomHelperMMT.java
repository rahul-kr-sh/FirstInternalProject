package com.MMT.helper;

import java.util.ArrayList;
import java.util.Scanner;

import com.MMT.bean.HotelRoom;

public class HotelRoomHelperMMT {
	HotelRoom hotelRoom;
	Scanner sc=new Scanner(System.in);
	ArrayList<HotelRoom> arrayListHotelRoom=new ArrayList<HotelRoom>();
public void input(){
	do{
		hotelRoom=new HotelRoom();
	System.out.println("Enter Hotel ID");
	hotelRoom.setHotelId(sc.next());
	System.out.println("Enter Hotel Room No: :");
	hotelRoom.setHotelRoomNo(sc.nextInt());
	System.out.println("Enter Hotel Room Type :");
	hotelRoom.setHotelRoomType(sc.next());
	System.out.println("Enter Hotel Room Price :");
	hotelRoom.setHotelRoomPrice(sc.nextDouble());
	System.out.println("Enter Hotel Room Status :");
	hotelRoom.setHotelRoomStatus(sc.next());
	arrayListHotelRoom.add(hotelRoom);
	System.out.println("Do you want to add more rooms y/n : ");
	
	}while(sc.next().equalsIgnoreCase("y"));
	
}
public HotelRoom getHotelRoom() {
	return hotelRoom;
}
public void setHotelRoom(HotelRoom hotelRoom) {
	this.hotelRoom = hotelRoom;
}
public ArrayList<HotelRoom> getArrayListHotelRoom() {
	return arrayListHotelRoom;
}
public void setArrayListHotelRoom(ArrayList<HotelRoom> arrayListHotelRoom) {
	this.arrayListHotelRoom = arrayListHotelRoom;
}
}
