package com.MMT.dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import com.MMT.bean.HotelBooking;

public interface HotelBookingDaoMMT {
	int insertHotelBooking(HotelBooking hb) throws SQLException;
	ArrayList<HotelBooking> searchHotelBooking(String userId) throws SQLException;
	int cancelHotelBooking(int hotelBookingId) throws SQLException;
	ArrayList<HotelBooking> display();
}
