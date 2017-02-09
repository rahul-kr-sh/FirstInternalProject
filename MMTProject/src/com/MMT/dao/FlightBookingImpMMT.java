package com.MMT.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.MMT.bean.FlightBooking;

public class FlightBookingImpMMT implements FlightBookingDaoMMT {

	@Override
	public ArrayList<FlightBooking> displayFlightBooking() throws ClassNotFoundException, SQLException, IOException {
		{
			ArrayList<FlightBooking> FB = new ArrayList<FlightBooking>();
			FlightBooking fb = new FlightBooking();
			Connection con = DbConnection.dbConnection();
			// Query
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from FlightBooking");

			while (rs.next()) {
				fb.setFlightBookingId(rs.getString("flightBookingId"));
				fb.setFlightId(rs.getString("flightId"));
				fb.setUserId(rs.getString("userId"));
				fb.setFlightBookingId(rs.getString("flightBookingDate"));

				String status = "false";
				if (rs.getString("flag").equals("true"))
					;
				status = "true";

				fb.setFlag(true);

			}

			return FB;
		}
	}

	@Override
	public ArrayList<FlightBooking> searchFlightBooking(String userId)
			throws ClassNotFoundException, SQLException, IOException {

		FlightBooking fb = new FlightBooking();
		ArrayList<FlightBooking> fList = new ArrayList<FlightBooking>();
		Connection con = DbConnection.dbConnection();
		// Query
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("select * from FlightBooking where userId=" + userId);
		// Process Results
		while (rs.next()) {
			fb.setFlightBookingId(rs.getString("flightBookingId"));
			fb.setFlightId(rs.getString("flightId"));
			fb.setUserId(rs.getString("userId"));
			fb.setFlightBookingId(rs.getString("flightBooikngDate"));

			String status = "false";
			if (rs.getString("flag").equals("true"))
				;
			status = "true";

			fb.setFlag(true);
			fList.add(fb);
		}
		return fList;
	}

	@Override
	public int insertFlightBooking(FlightBooking fb) throws ClassNotFoundException, SQLException, IOException {

		Connection con = DbConnection.dbConnection();
		int row = 0;
		Statement stmt = con.createStatement();
		// int rows=stmt.executeUpdate("insert into FlightBooking(
		// flightBookingid,flightId,userId,flightBookingDate,flage values
		// ("+flightBookingId+","+flightId+","+userId+","+flightBookingDate+","+flage+")");
		// Process Results
		PreparedStatement pst = con.prepareStatement("insert into FLIGHTBOOKING values(?,?,?,?,?)");
		pst.setString(1, fb.getFlightBookingId());
		pst.setString(2, fb.getUserId());
		pst.setString(3, fb.getFlightId());
		pst.setString(4, fb.getFlightBookingDate());
		String status = "false";
		if (fb.isFlag())
			status = "true";
		pst.setString(5, status);

		con.close();
		return row;
	}

	@Override
	public int deleteFlightBooking(String flightBookingId) throws ClassNotFoundException, SQLException, IOException {

		Connection con = DbConnection.dbConnection();

		Statement stmt = con.createStatement();
		int rows = stmt.executeUpdate("delete from FlightBooking where flightBookingId =" + flightBookingId);
		// Process Results

		con.close();
		return rows;
	}

	@Override
	public ArrayList<FlightBooking> displayFlightBooking1() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
