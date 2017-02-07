package com.MMT.bl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

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
	
	public boolean bookHotel(String userId, String hotelId, int hotelRoomNo, Date checkInDate, Date checkOutDate ) throws SQLException, ClassNotFoundException{
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
		
		hotel.setHotelRoom(room);
		long diff = checkOutDate.getTime() - checkInDate.getTime();
		//hd.updateHotel(hid, hotel);
		int hbid = 10000 + (int)(Math.random() * 11000); 
		String id=Integer.toString(hbid);
		HotelBooking hb=new HotelBooking();
		hb.setHotelCheckInDate(checkInDate);
		hb.setHotelCheckOutDate(checkOutDate);
		hb.setHotelId(hotelId);
		hb.setHotelBookingId(id);
		hb.setRoomNo(hotelRoomNo);
		hb.setUserId(userId);
		hb.setStayDuration((int)TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS));
		HB.insertHotelBooking(hb);
		return true;
	}
	
	
}
