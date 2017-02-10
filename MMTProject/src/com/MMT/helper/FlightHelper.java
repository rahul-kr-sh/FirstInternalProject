package com.MMT.helper;

import java.util.Scanner;

import com.MMT.bean.Flight;

public class FlightHelper {
	private Flight flight=new Flight();
	
	
public Flight getFlight() {
		return flight;
	}


	public void setFlight(Flight flight) {
		this.flight = flight;
	}


	public void input(){
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter Flight Id ");
		flight.setFlightId(sc.next());
		System.out.println("Enter Flight Company name");
		flight.setFlightCompanyName(sc.next());
		System.out.println("Enter Flight source");
		flight.setFlightSource(sc.next().toLowerCase());
		System.out.println("Enter Flight Destination");
		flight.setFlightDestination(sc.next().toLowerCase());
		System.out.println("Enter Flight departure time");
		flight.setFlightDepartureTime(sc.next());
		System.out.println("Enter Flight arrival time");
		flight.setFlightArrivalTime(sc.next());
		System.out.println("Enter Flight ticket price");
		flight.setFlightTicketPrice(sc.nextDouble());
		System.out.println("Enter Flight seat capacity");
		flight.setAvailableSeats(sc.nextInt());
		

	}
}


