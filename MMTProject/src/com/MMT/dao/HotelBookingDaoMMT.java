package com.MMT.dao;

import java.io.IOException;
import java.util.ArrayList;

import com.MMT.bean.HotelBooking;

public interface HotelBookingDaoMMT {
	int insertHotelBooking(HotelBooking hb);
	ArrayList<HotelBooking> searchHotelBooking(String uid);
	int cancelHotelBooking(int hbId);
}
