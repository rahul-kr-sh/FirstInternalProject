package com.MMT.bl;

import java.io.IOException;
import java.sql.SQLException;

import com.MMT.bean.Flight;
import com.MMT.bean.Hotel;
import com.MMT.dao.FlightDaoImplMMT;
import com.MMT.dao.HotelDaoImplMMT;


public class AdminBlMMT {
	private FlightDaoImplMMT F = new FlightDaoImplMMT();
	private HotelDaoImplMMT H = new HotelDaoImplMMT();
	
	public int insertHotel(Hotel h) throws ClassNotFoundException, IOException, SQLException{		
		return H.insertHotel(h);		
	}
	
	public int insertFlight(Flight f) throws ClassNotFoundException, SQLException{
		return F.insertFlight(f);
	}
	
	public int deleteHotel(String hotelId) throws ClassNotFoundException, SQLException{
		return H.deleteHotel(hotelId);
	}
	
	public int deleteFlight(String flightId) throws ClassNotFoundException, SQLException{
		return F.deleteFlight(flightId);
	}
	
	public int modifyHotel(String hotelId, Hotel newhotel) throws ClassNotFoundException, SQLException{
		return H.updateHotel(hotelId, newhotel);
	}
	
	public int modifyFlight(String flightId, Flight newflight) throws ClassNotFoundException, SQLException{
		return F.updateFlight(flightId, newflight);
	}
	
}
