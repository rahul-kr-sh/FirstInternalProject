package com.MMT.dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import com.MMT.bean.Hotel;

public interface HotelDaoMMT {
	int insertHotel(Hotel h) throws ClassNotFoundException, SQLException  ;
	int deleteHotel(String hotelId) throws ClassNotFoundException, SQLException;
	int updateHotel(String hotelId, Hotel newhotel) throws ClassNotFoundException, SQLException;
	ArrayList<Hotel> displayHotel() throws ClassNotFoundException, SQLException ;
	Hotel searchHotel(String hotelId) throws ClassNotFoundException, SQLException ;

}
