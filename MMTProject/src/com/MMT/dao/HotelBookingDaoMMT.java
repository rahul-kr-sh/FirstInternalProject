package com.MMT.dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import com.MMT.bean.HotelBooking;

public interface HotelBookingDaoMMT {
	int insertHotelBooking(HotelBooking hb) throws SQLException;
	ArrayList<HotelBooking> searchHotelBooking(String uid);
	int cancelHotelBooking(int hbId);
	ArrayList<HotelBooking> display();
}
