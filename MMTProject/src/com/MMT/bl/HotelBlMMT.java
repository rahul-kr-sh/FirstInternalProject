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
	HotelDaoImplMMT hotelDao=new HotelDaoImplMMT();
	HotelBookingDaoImplMMT hotelBookingDao=new HotelBookingDaoImplMMT();
	
	public ArrayList<Hotel> displayHotel() throws ClassNotFoundException, SQLException, IOException{
		return hotelDao.displayHotel();
	}
	
	public Hotel searchHotel(String hotelId) throws ClassNotFoundException, IOException, SQLException{
		return hotelDao.searchHotel(hotelId);
	}
	
	public ArrayList<Hotel> searchHotel1(String location) throws SQLException, ClassNotFoundException, IOException{
		return hotelDao.searchHotel1(location);
	}
	
	public HotelRoom searchHotelRoom(String hotelId, int rno) throws SQLException, ClassNotFoundException, IOException{
		Hotel h;
		h=hotelDao.searchHotel(hotelId);
		
		ArrayList<HotelRoom> hotelRoomList;
		hotelRoomList=h.getHotelRoom();
		HotelRoom hotelRoom=null;
		for(HotelRoom hr:hotelRoomList){
			if(hr.getHotelRoomNo()==rno){
				hotelRoom=hr;
			}
		}
		return hotelRoom; 
	}
	
	public HotelBooking bookHotel(String userId, String hotelId, int hotelRoomNo, Date checkInDate, Date checkOutDate ) throws SQLException, ClassNotFoundException, IOException{
		Hotel hotel=new Hotel();
		hotel=hotelDao.searchHotel(hotelId);
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
					return null;
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
		System.out.println("skljds: "+hb);
		hotelBookingDao.insertHotelBooking(hb);
		return hb;
	}
	
	
	public ArrayList<HotelRoom> displayAvailHotelRoom(String hotelId) throws SQLException, ClassNotFoundException, IOException{
		ArrayList<HotelRoom> hr;
		hr=hotelDao.searchHotel(hotelId).getHotelRoom();
		
		ArrayList<HotelRoom> HR=new ArrayList<HotelRoom>();
		for(HotelRoom r:hr){
			if(r.getHotelRoomStatus().equals("avail")){
				HR.add(r);
			}
		}
		return HR;
	}
	
}
