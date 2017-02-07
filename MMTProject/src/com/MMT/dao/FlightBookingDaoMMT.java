package com.MMT.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import com.MMT.bean.FlightBooking;

public interface FlightBookingDaoMMT {
	
	ArrayList<FlightBooking> displayFlightBooking() throws ClassNotFoundException, SQLException;
	
	FlightBooking searchFlightBooking(String flightBookingId) throws ClassNotFoundException, SQLException;
	
	
	int insertFlightBooking(FlightBooking fb) throws ClassNotFoundException, SQLException;
	
	int deleteFlightBooking(String flightBookingId) throws ClassNotFoundException, SQLException;

	



}
