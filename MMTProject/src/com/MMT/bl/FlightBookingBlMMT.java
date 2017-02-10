package com.MMT.bl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import com.MMT.bean.Flight;
import com.MMT.bean.FlightBooking;
import com.MMT.dao.FlightBookingDaoMMT;
import com.MMT.dao.FlightBookingImpMMT;
import com.MMT.dao.FlightDaoImplMMT;

public class FlightBookingBlMMT {

	FlightDaoImplMMT flightdao = new FlightDaoImplMMT();
	FlightBookingDaoMMT flightBookingDao = new FlightBookingImpMMT();

	private boolean checkAvailability(Flight F, int num) {
		boolean status = false;
		int var = num;
		Flight f = F;
		if (f.getAvailableSeats() - var < 0) {
			return status;
		} else {
			status = true;
			return status;
		}
	}

	public ArrayList<Flight> displayFlight() throws ClassNotFoundException, SQLException, IOException {
		return flightdao.displayFlight();
	}

	public Flight searchFlight(String flightId) throws ClassNotFoundException, SQLException, IOException {
		return flightdao.searchFlight(flightId);
	}

	public ArrayList<Flight> searchFlight(String flightSource, String flightDestination)
			throws ClassNotFoundException, SQLException, IOException {
		if(flightdao.searchFlight(flightSource, flightDestination)==null){
			return null;
		}
				
		return flightdao.searchFlight(flightSource, flightDestination);
	}

	public FlightBooking bookFlight(String userId, String flightId, String flightSource, String flightDestination,
			int seats) throws ClassNotFoundException, SQLException, IOException {
		String fid = flightId;
		String UId = userId;
		boolean flag = false;
		int rseats = seats;
		Flight f = flightdao.searchFlight(fid);
		if (f == null) {
			return null;
		} else {
			if (checkAvailability(f, rseats) != true) {
				System.out.println("Seats Not Available!!");
				return null;
			} else {
				f.setAvailableSeats(f.getAvailableSeats() - rseats);
				System.out.println("Seats Available!!");
				// Date d=new Date();
				FlightBooking FBD = new FlightBooking();

				int hbid = 10000 + (int) (Math.random() * 11000);
				//System.out.println("Random No"+hbid);
				String id = Integer.toString(hbid);
				Date date=new Date();
				FBD.setUserId(UId);
				FBD.setFlightId(fid);
				FBD.setFlightBookingId(id);
				//System.out.println("BOOiinh IDs "+FBD.getFlightBookingId());
			//	System.out.println("Date: "+FBD.getFlightBookingDate());
				//System.out.println("BL fbd object:"+FBD);
				FBD.setFlightBookingDate(date);
				FBD.setFlag(true);
				flightBookingDao.insertFlightBooking(FBD);
				flightdao.updateFlight(fid, f);
				return FBD;
			}
		}
	}

}
