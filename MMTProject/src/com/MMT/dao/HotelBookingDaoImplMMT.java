package com.MMT.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

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
	public ArrayList<HotelBooking> searchHotelBooking(String uid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int cancelHotelBooking(int hbId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<HotelBooking> display() {
		// TODO Auto-generated method stub
		return null;
	}

}
