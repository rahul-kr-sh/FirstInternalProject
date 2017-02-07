package com.MMT.bl;

import java.sql.SQLException;
import java.util.ArrayList;

import com.MMT.bean.Flight;
import com.MMT.dao.FlightBookingDaoMMT;
import com.MMT.dao.FlightBookingImpMMT;
import com.MMT.dao.FlightDaoImplMMT;


public class FlightBookingBlMMT {
	
	FlightDaoImplMMT F=new FlightDaoImplMMT();
	FlightBookingDaoMMT FB=new FlightBookingImpMMT();
	
	ArrayList<Flight> displayFlight() throws ClassNotFoundException, SQLException{
		return F.displayFlight();
		}

	Flight searchFlight(String flightId) throws ClassNotFoundException, SQLException{
		return F.searchFlight(flightId);
		}
	
	
	
	
}
