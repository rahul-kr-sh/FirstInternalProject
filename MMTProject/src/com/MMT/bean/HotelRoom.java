package com.MMT.bean;

public class HotelRoom {
	private int hotelRoomNo;
	private String hotelRoomType;
	private double hotelRoomPrice;
	private String hotelRoomStatus;
	public HotelRoom(int hotelRoomNo, String hotelRoomType, double hotelRoomPrice, String hotelRoomStatus) {
		super();
		this.hotelRoomNo = hotelRoomNo;
		this.hotelRoomType = hotelRoomType;
		this.hotelRoomPrice = hotelRoomPrice;
		this.hotelRoomStatus = hotelRoomStatus;
	}
	public int getHotelRoomNo() {
		return hotelRoomNo;
	}
	public void setHotelRoomNo(int hotelRoomNo) {
		this.hotelRoomNo = hotelRoomNo;
	}
	public String getHotelRoomType() {
		return hotelRoomType;
	}
	public void setHotelRoomType(String hotelRoomType) {
		this.hotelRoomType = hotelRoomType;
	}
	public double getHotelRoomPrice() {
		return hotelRoomPrice;
	}
	public void setHotelRoomPrice(double hotelRoomPrice) {
		this.hotelRoomPrice = hotelRoomPrice;
	}
	public String getHotelRoomStatus() {
		return hotelRoomStatus;
	}
	public void setHotelRoomStatus(String hotelRoomStatus) {
		this.hotelRoomStatus = hotelRoomStatus;
	}
	@Override
	public String toString() {
		return "HotelRoom [hotelRoomNo=" + hotelRoomNo + ", hotelRoomType=" + hotelRoomType + ", hotelRoomPrice="
				+ hotelRoomPrice + ", hotelRoomStatus=" + hotelRoomStatus + "]";
	}
	
	
	
}
