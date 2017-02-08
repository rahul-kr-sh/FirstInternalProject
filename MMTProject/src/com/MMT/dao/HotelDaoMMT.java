package com.MMT.dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import com.MMT.bean.Hotel;

public interface HotelDaoMMT {
	int insertHotel(Hotel h) throws  SQLException  ;
	int deleteHotel(String hotelId) throws  SQLException;
	int updateHotel(String hotelId, Hotel newhotel) throws  SQLException;
	ArrayList<Hotel> displayHotel() throws  SQLException ;
	Hotel searchHotel(String hotelId) throws SQLException ;
	//Hotel searchHotel(String hotelLocation) throws SQLException ;
	ArrayList<Hotel> searchHotel1(String hotelLocation) throws SQLException;
}
