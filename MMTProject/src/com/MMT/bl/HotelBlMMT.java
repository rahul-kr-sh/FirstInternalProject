package com.MMT.bl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import com.MMT.bean.Hotel;
import com.MMT.bean.HotelBooking;
import com.MMT.bean.HotelRoom;
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
	
	public boolean bookHotel(String userId, String hotelId, int hotelRoomNo, Date checkInDate, Date checkOutDate ){
		Hotel hotel=new Hotel();
		hotel=H.searchHotel(hotelId);
		
		ArrayList<HotelRoom> room=new ArrayList<HotelRoom>();
	
		room=hotel.getHotelRoom();
		
		int count=0;
		int index=0;
		HotelRoom rnew=new HotelRoom();
		for(HotelRoom r:room)
		{
			count++;
			if(r.getHotelRoomNo()==hotelRoomNo)
			{
				index=count;
				if(r.getHotelRoomStatus().equals("not"))
				{
					return false;
				}
				else
				{
					rnew.setHotelRoomNo(r.getHotelRoomNo());
					rnew.setHotelRoomPrice(r.getHotelRoomPrice());
					rnew.setHotelRoomStatus("not");
					rnew.setHotelRoomType(r.getHotelRoomType());
					
				}
				
			}
		}
		
		room.set(index-1, rnew);
		
		Date date = new Date();
		
		hotel.setHotelRoom(room);
		
		//hd.updateHotel(hid, hotel);
		String hbid=(String) (new Date()).getTime();
		
		HotelBooking hb=new HotelBooking();
		hb.setHotelCheckInDate(checkInDate);
		hb.setHotelCheckOutDate(checkOutDate);
		hb.setHotelId(hotelId);
		hb.setHotelBookingId(hbid);
		hb.setRoomNo(hotelRoomNo);
		hb.setUserId(userId);
		hb.setStayDuration(stayDuration);
		HB.insertHotelBooking(hb);
		return true;
	}
	
	
}
