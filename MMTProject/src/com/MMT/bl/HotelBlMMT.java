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
	
	public ArrayList<Hotel> searchResult() throws ClassNotFoundException, SQLException{
		return H.displayHotel();
	}
	
	
	
	
	
}
