package com.MMT.bean;

import java.util.ArrayList;
import java.util.Date;

public class Hotel {
	private int hotelId;
	private String hotelName;
	private String hotelLocation;
	private String hotelInfo;
	private ArrayList<HotelRoom> hotelRoom;
	public Hotel(int hotelId, String hotelName, String hotelLocation, String hotelInfo, ArrayList<HotelRoom> hotelRoom) {
		super();
		this.hotelId = hotelId;
		this.hotelName = hotelName;
		this.hotelLocation = hotelLocation;
		this.hotelInfo = hotelInfo;
		this.hotelRoom = hotelRoom;
	}
	public int getHotelId() {
		return hotelId;
	}
	public void setHotelId(int hotelId) {
		this.hotelId = hotelId;
	}
	public String getHotelName() {
		return hotelName;
	}
	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}
	public String getHotelLocation() {
		return hotelLocation;
	}
	public void setHotelLocation(String hotelLocation) {
		this.hotelLocation = hotelLocation;
	}
	public String getHotelInfo() {
		return hotelInfo;
	}
	public void setHotelInfo(String hotelInfo) {
		this.hotelInfo = hotelInfo;
	}
	public ArrayList<HotelRoom> getHotelRoom() {
		return hotelRoom;
	}
	public void setHotelRoom(ArrayList<HotelRoom> hotelRoom) {
		this.hotelRoom = hotelRoom;
	}
	@Override
	public String toString() {
		return "Hotel [hotelId=" + hotelId + ", hotelName=" + hotelName + ", hotelLocation=" + hotelLocation
				+ ", hotelInfo=" + hotelInfo + ", hotelRoom=" + hotelRoom + "]";
	}
	
	
}
