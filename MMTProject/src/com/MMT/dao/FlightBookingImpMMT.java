package com.MMT.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import com.MMT.bean.FlightBooking;

public class FlightBookingImpMMT implements FlightBookingDaoMMT{

	@Override
	public ArrayList<FlightBooking> displayFlightBooking() throws ClassNotFoundException, SQLException {
		{	
		ArrayList<FlightBooking> FB =new ArrayList<FlightBooking>();
		FlightBooking fb=new FlightBooking();
		Connection con=DbConnection.dbConnection();
		//Query
		Statement stmt=con.createStatement();
		ResultSet rs=stmt.executeQuery("select * from FlightBooking");
	
		while(rs.next()){
			fb.setFlightBookingId(rs.getString("flightBookingId"));
			fb.setFlightId(rs.getString("flightId"));
			fb.setUserId(rs.getString("userId"));
			fb.setFlightBookingDate(rs.getDate("flightBookingDate"));
			fb.setFlag(rs.getBoolean("flage"));
			FB.add(fb);
			
		}
	
		return FB;
		}
}
	

	@Override
	public FlightBooking searchFlightBooking(String flightBookingId) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insertFlightBooking(FlightBooking fb) throws ClassNotFoundException, SQLException {
	
Connection con=DbConnection.dbConnection();		
		
		String flightBookingId=fb.getFlightBookingId();
		String flightId= fb.getFlightId();
		String userId= fb.getUserId();
		Date flightBookingDate=fb.getFlightBookingDate();
		boolean flage=fb.isFlag();
		
		Statement stmt=con.createStatement();
		int rows=stmt.executeUpdate("insert into FlightBooking( flightBookingid,flightId,userId,flightBookingDate,flage  values ("+flightBookingId+","+flightId+","+userId+","+flightBookingDate+","+flage+")");
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
	public int deleteFlightBooking(String flightBookingId) throws ClassNotFoundException, SQLException {
		
		Connection con=DbConnection.dbConnection();
		
		Statement stmt=con.createStatement();
		int rows=stmt.executeUpdate("delete from FlightBooking where flightBookingId ="+flightBookingId);
		//Process Results
		
		if(rows>0)
		{
			return rows;
		}
		else return 0;
	}

	
}
