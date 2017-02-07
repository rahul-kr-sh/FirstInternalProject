package com.MMT.bl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import com.MMT.bean.Hotel;
import com.MMT.dao.HotelBookingDaoImplMMT;
import com.MMT.dao.HotelDaoImplMMT;


public class HotelBlMMT {
	HotelDaoImplMMT H=new HotelDaoImplMMT();
	HotelBookingDaoImplMMT HB=new HotelBookingDaoImplMMT();
	
	public ArrayList<Hotel> displayHotel() throws ClassNotFoundException, SQLException{
		return H.displayHotel();
	}
	
	public Hotel searchHotel(String hotelId) throws ClassNotFoundException, IOException, SQLException{
		return H.searchHotel(hotelId);
	}
	
	public boolean bookHotel(String userId, String hotelId, int hotelRoomNo ){
		return true;
	}
	
	
}
