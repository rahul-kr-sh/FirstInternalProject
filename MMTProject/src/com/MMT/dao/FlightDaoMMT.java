package com.MMT.dao;


import java.sql.SQLException;
import java.util.ArrayList;

import com.MMT.bean.Flight;


public interface FlightDaoMMT {
	ArrayList<Flight> displayFlight() throws ClassNotFoundException, SQLException;
	Flight searchFlight(String flightId) throws ClassNotFoundException, SQLException;
	int updateFlight(String flightId, Flight newflight) throws ClassNotFoundException, SQLException;
	int insertFlight(Flight f) throws ClassNotFoundException, SQLException;
	int deleteFlight(String flightId) throws ClassNotFoundException, SQLException;
}
