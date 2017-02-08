package com.MMT.menu;

import java.sql.SQLException;
import java.util.Scanner;

import com.MMT.bean.Flight;
import com.MMT.bl.FlightBookingBlMMT;

public class MenuAdminFlight {
	Flight flight=new Flight();
	FlightBookingBlMMT flightbl=new FlightBookingBlMMT();
	Scanner sc=new Scanner(System.in);
	public void choice() throws SQLException{
		System.out.println("1.");
		System.out.println("2.");
		System.out.println("---------------");
		int i=sc.nextInt();
		switch(i){
		case 1:
			
			al=pBl.displayPromotion();
			System.out.println(al);
			break;
		case 2:
		}
	}
}
