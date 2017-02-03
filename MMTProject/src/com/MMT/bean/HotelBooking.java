package com.MMT.bean;

import java.util.Date;

public class HotelBooking {
	private long hotelBookingId;
	private int hotelId;
	private String userId;
	private int roomNo;
	private Date hotelCheckInDate;
	private Date hotelCheckOutDate;
	public HotelBooking(long hotelBookingId, int hotelId, String userId, int roomNo, Date hotelCheckInDate,
			Date hotelCheckOutDate) {
		super();
		this.hotelBookingId = hotelBookingId;
		this.hotelId = hotelId;
		this.userId = userId;
		this.roomNo = roomNo;
		this.hotelCheckInDate = hotelCheckInDate;
		this.hotelCheckOutDate = hotelCheckOutDate;
	}
	public long getHotelBookingId() {
		return hotelBookingId;
	}
	public void setHotelBookingId(long hotelBookingId) {
		this.hotelBookingId = hotelBookingId;
	}
	public int getHotelId() {
		return hotelId;
	}
	public void setHotelId(int hotelId) {
		this.hotelId = hotelId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getRoomNo() {
		return roomNo;
	}
	public void setRoomNo(int roomNo) {
		this.roomNo = roomNo;
	}
	public Date getHotelCheckInDate() {
		return hotelCheckInDate;
	}
	public void setHotelCheckInDate(Date hotelCheckInDate) {
		this.hotelCheckInDate = hotelCheckInDate;
	}
	public Date getHotelCheckOutDate() {
		return hotelCheckOutDate;
	}
	public void setHotelCheckOutDate(Date hotelCheckOutDate) {
		this.hotelCheckOutDate = hotelCheckOutDate;
	}
	@Override
	public String toString() {
		return "HotelBooking [hotelBookingId=" + hotelBookingId + ", hotelId=" + hotelId + ", userId=" + userId
				+ ", roomNo=" + roomNo + ", hotelCheckInDate=" + hotelCheckInDate + ", hotelCheckOutDate="
				+ hotelCheckOutDate + "]";
	}
	
	
}
