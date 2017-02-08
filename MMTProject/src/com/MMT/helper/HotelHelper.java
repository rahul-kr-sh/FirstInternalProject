package com.MMT.helper;

import java.util.Scanner;

import com.MMT.bean.Hotel;


public class HotelHelper {

	
	Hotel hl=new Hotel();
	Scanner sc=new Scanner(System.in); 
	
public void input(){
	System.out.println("Enter Hotel Id :");
	hl.setHotelId(sc.next());
	System.out.println("Enter Hotel Name :");
	hl.setHotelName(sc.next());
	System.out.println("Enter Hotel Location :");
	hl.setHotelLocation(sc.next());
	System.out.println("Enter Information :");
	hl.setHotelInfo(sc.next());
	
}
public Hotel getValue() {
	return hl ;
}
public void setValue(Hotel hl) {
	this.hl = hl;
}
}



