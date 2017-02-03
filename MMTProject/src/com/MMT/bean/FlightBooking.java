package com.MMT.bean;

import java.util.Date;

public class FlightBooking {
	private String flightBookingId;
	private int userId;
	private String flightId;
	private Date flightBookingDate;
	private boolean flag;
	public FlightBooking(String flightBookingId, int userId, String flightId, Date flightBookingDate, boolean flag) {
		super();
		this.flightBookingId = flightBookingId;
		this.userId = userId;
		this.flightId = flightId;
		this.flightBookingDate = flightBookingDate;
		this.flag = flag;
	}
	public FlightBooking() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getFlightBookingId() {
		return flightBookingId;
	}
	public void setFlightBookingId(String flightBookingId) {
		this.flightBookingId = flightBookingId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getFlightId() {
		return flightId;
	}
	public void setFlightId(String flightId) {
		this.flightId = flightId;
	}
	public Date getFlightBookingDate() {
		return flightBookingDate;
	}
	public void setFlightBookingDate(Date flightBookingDate) {
		this.flightBookingDate = flightBookingDate;
	}
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	@Override
	public String toString() {
		return "FlightBooking [flightBookingId=" + flightBookingId + ", userId=" + userId + ", flightId=" + flightId
				+ ", flightBookingDate=" + flightBookingDate + ", flag=" + flag + "]";
	}
	
	
}
