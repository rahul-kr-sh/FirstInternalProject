package com.MMT.dao;


import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


import com.MMT.bean.Flight;

public class FlightDaoImplMMT implements FlightDaoMMT {
	Connection con;
	@Override
	public int insertFlight(Flight flight) throws ClassNotFoundException, SQLException, IOException {	
		
		int row=0;
		con=DbConnection.dbConnection();
		PreparedStatement pst=con.prepareStatement("insert into flight values(?,?,?,?,?,?,?,?)");

		pst.setString(1, flight.getFlightCompanyName());
		pst.setString(2, flight.getFlightId());
		pst.setString(3, flight.getFlightSource());
		pst.setString(4, flight.getFlightDestination());
		pst.setString(5, flight.getFlightDepartureTime());
		pst.setString(6, flight.getFlightArrivalTime());
		pst.setDouble(7, flight.getFlightTicketPrice());
		pst.setInt(8, flight.getAvailableSeats());
		row=pst.executeUpdate();
		return row;
	}

	@Override
	public int deleteFlight(String flightId) throws ClassNotFoundException, SQLException, IOException {
		Connection con=DbConnection.dbConnection();
		//Query
		Statement stmt=con.createStatement();
		int rows=stmt.executeUpdate("delete from Flight where flightId ="+flightId);
		//Process Results
		
		if(rows>0)
		{
			return rows;
		}
		else return 0;
	}

	@Override
	public int updateFlight(String flightId, Flight newflight) throws ClassNotFoundException, SQLException, IOException {
		Connection con=DbConnection.dbConnection();
		
		String flightCompanyName=newflight.getFlightCompanyName();
		String flightId1=newflight.getFlightId();
		String flightSource=newflight.getFlightSource();
		String flightDestination=newflight.getFlightDestination();
		String flightDepartureTime=newflight.getFlightDepartureTime();
		String flightArrivalTime=newflight.getFlightArrivalTime();
		double flightTicketPrice=newflight.getFlightTicketPrice();
		int availableSeats=newflight.getAvailableSeats();
		
		//Query
		Statement stmt=con.createStatement();
		int rows=stmt.executeUpdate("update Flight set flightCompanyName="+flightCompanyName+", flightId="+flightId1+",flightSource="+flightSource+",flightDestination="+flightDestination+",flightDepartureTime="+flightDepartureTime+",flightArrivalTime="+flightArrivalTime+",flightTicketPrice="+flightTicketPrice+",availableSeats="+availableSeats);
		 //rows=stmt.executeUpdate("insert into User (address,email,uid,name,pass,phoneNo values ("+address+","+email+","+uid+","+name+","+pass+","+phoneNo+")");
		//Process Results
		
		if(rows>0)
		{
			con.close();
			return rows;
		}
		else 
		{	con.close();
		return 0;}
	}

	@Override
	public Flight searchFlight(String flightId) throws ClassNotFoundException, SQLException, IOException {
		Flight flight =new Flight();
		Connection con=DbConnection.dbConnection();
	
		
		PreparedStatement pst=con.prepareStatement("select * from Flight where flightId=?");
		
		pst.setString(1, flightId);
		ResultSet rs=pst.executeQuery();
		if(rs.next()){
			flight.setFlightCompanyName(rs.getString("flightCompanyName"));
			flight.setFlightId(rs.getString("flightId"));
			flight.setFlightSource(rs.getString("flightSource"));
			flight.setFlightDestination(rs.getString("flightDestination"));
			flight.setFlightDepartureTime(rs.getString("flightDepartureTime"));
			flight.setFlightArrivalTime(rs.getString("flightArrivalTime"));
			flight.setFlightTicketPrice(rs.getDouble("flightTicketPrice"));
			flight.setAvailableSeats(rs.getInt("availableSeats"));

		}
		return flight;
	}

	@Override
	public ArrayList<Flight> displayFlight() throws ClassNotFoundException, SQLException, IOException {
		ArrayList<Flight> F =new ArrayList<Flight>();
		Flight f=new Flight();
		Connection con=DbConnection.dbConnection();
		//Query
		Statement stmt=con.createStatement();
		ResultSet rs=stmt.executeQuery("select * from Flight");
		//Process Results
		while(rs.next()){
			f.setFlightCompanyName(rs.getString("flightCompanyName"));
			f.setFlightId(rs.getString("flightId"));
			f.setFlightSource(rs.getString("flightSource"));
			f.setFlightDestination(rs.getString("flightDestination"));
			f.setFlightDepartureTime(rs.getString("flightDepartureTime"));
			f.setFlightArrivalTime(rs.getString("flightArrivalTime"));
			f.setFlightTicketPrice(rs.getDouble("flightTicketPrice"));
			f.setAvailableSeats(rs.getInt("availableSeats"));
			F.add(f);
		}
		return F;
	}

	@Override
	public ArrayList<Flight> searchFlight(String flightSource, String flightDestination)
			throws ClassNotFoundException, SQLException, IOException {
		Flight f =new Flight();
		ArrayList<Flight> F =new ArrayList<Flight>();
		Connection con=DbConnection.dbConnection();
		//Query
		PreparedStatement pst;
		 pst=con.prepareStatement("select * from Flight where flightSource=? AND flightDestination=?");
		pst.setString(1, flightSource);
		 pst.setString(2, flightDestination);
		 ResultSet rs=pst.executeQuery();
		//Process Results
		while(rs.next()){
			f.setFlightCompanyName(rs.getString("flightCompanyName"));
			f.setFlightId(rs.getString("flightId"));
			f.setFlightSource(rs.getString("flightSource"));
			f.setFlightDestination(rs.getString("flightDestination"));
			f.setFlightDepartureTime(rs.getString("flightDepartureTime"));
			f.setFlightArrivalTime(rs.getString("flightArrivalTime"));
			f.setFlightTicketPrice(rs.getDouble("flightTicketPrice"));
			f.setAvailableSeats(rs.getInt("availableSeats"));
			F.add(f);

		}
		return F;
	}
}
