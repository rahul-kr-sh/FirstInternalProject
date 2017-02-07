package com.MMT.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.MMT.bean.Flight;
import com.MMT.bean.HotelBooking;


public class HotelBookingDaoImplMMT implements HotelBookingDaoMMT {

	@Override
	public int insertHotelBooking(HotelBooking hb) throws SQLException {
		int row;
		Connection con=DbConnection.dbConnection();
		PreparedStatement pst=con.prepareStatement("insert into HotelBooking values(?,?,?,?,?,?,?)");
		pst.setString(1, hb.getHotelBookingId());
		pst.setString(2, hb.getHotelId());
		pst.setString(3, hb.getUserId());
		pst.setInt(4, hb.getRoomNo());
		pst.setDate(5, (Date) hb.getHotelCheckInDate());
		pst.setDate(6, (Date) hb.getHotelCheckOutDate());
		pst.setInt(7, hb.getStayDuration());
		row=pst.executeUpdate();
		con.close();
		return row;
	}

	@Override
	public ArrayList<HotelBooking> searchHotelBooking(String userId) throws SQLException {
		ArrayList<HotelBooking> hb =new ArrayList<HotelBooking>();
		HotelBooking h=new HotelBooking();
		Connection con=DbConnection.dbConnection();
		Statement stmt=con.createStatement();
		ResultSet rs=stmt.executeQuery("select * from HotelBooking where userId like '"+userId+"'");
		//Process Results
		while(rs.next()){
			h.setHotelBookingId(rs.getString("hotelBookingId"));
			h.setHotelId(rs.getString("hotelId"));
			h.setUserId(rs.getString("userId"));
			h.setRoomNo(rs.getInt("roomNo"));
			h.setHotelCheckInDate(rs.getDate("hotelCheckInDate"));
			h.setHotelCheckOutDate(rs.getDate("hotelCheckOutDate"));
			h.setStayDuration(rs.getInt("stayDuration"));
			hb.add(h);
		}
		return hb;
	}

	@Override
	public int cancelHotelBooking(int hotelBookingId) throws SQLException {
		Connection con=DbConnection.dbConnection();
		Statement stmt3=con.createStatement();
		 int rows=stmt3.executeUpdate("delete from HotelBooking where hotelBookingId ="+hotelBookingId);
	
		//Process Results
		if(rows>0)
		{
			return rows;
		}
		else return 0;
	}

	@Override
	public ArrayList<HotelBooking> display() throws SQLException {
		ArrayList<HotelBooking> hb =new ArrayList<HotelBooking>();
		HotelBooking h=new HotelBooking();
		Connection con=DbConnection.dbConnection();
		//Query
		Statement stmt=con.createStatement();
		ResultSet rs=stmt.executeQuery("select * from HotelBooking");
		//Process Results
		while(rs.next()){
			h.setHotelBookingId(rs.getString("hotelBookingId"));
			h.setHotelId(rs.getString("hotelId"));
			h.setUserId(rs.getString("userId"));
			h.setRoomNo(rs.getInt("roomNo"));
			h.setHotelCheckInDate(rs.getDate("hotelCheckInDate"));
			h.setHotelCheckOutDate(rs.getDate("hotelCheckOutDate"));
			h.setStayDuration(rs.getInt("stayDuration"));
			hb.add(h);
		}
		return hb;
	}


}
