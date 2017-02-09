package com.MMT.dao;


import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


import com.MMT.bean.Flight;

public class FlightDaoImplMMT implements FlightDaoMMT {
	
	@Override
	public int insertFlight(Flight f) throws ClassNotFoundException, SQLException, IOException {
		Connection con=DbConnection.dbConnection();		
		String flightCompanyName=f.getFlightCompanyName();
		String flightId=f.getFlightId();
		String flightSource=f.getFlightSource();
		String flightDestination=f.getFlightDestination();
		String flightDepartureTime=f.getFlightDepartureTime();
		String flightArrivalTime=f.getFlightArrivalTime();
		double flightTicketPrice=f.getFlightTicketPrice();
		int availableSeats=f.getAvailableSeats();
		//Testing//server
		//Query
		Statement stmt=con.createStatement();
		int rows=stmt.executeUpdate("insert into Flight( flightCompanyName,flightId,flightSource,flightDestination,flightDepartureTime,flightArrivalTime,flightTicketPrice ,availableSeats)  values ('"+flightCompanyName+"','"+flightId+"','"+flightSource+"','"+flightDestination+"','"+flightDepartureTime+"','"+flightArrivalTime+"',"+flightTicketPrice+","+availableSeats+")");
		System.out.println("Inserted");
		//Process Results
		
		if(rows>0)
		{
			con.close();
			return rows;
		}
		else 
		{	con.close();
		return 0;
		}
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
		Flight f =new Flight();
		Connection con=DbConnection.dbConnection();
		//Query
		Statement stmt=con.createStatement();
		ResultSet rs=stmt.executeQuery("select * from Flight where flightId="+flightId);
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

		}
		return f;
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
		Statement stmt=con.createStatement();
		ResultSet rs=stmt.executeQuery("select * from Flight where flightSource="+flightSource +" AND flightDestination="+flightDestination);
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
