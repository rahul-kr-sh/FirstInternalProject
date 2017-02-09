package com.MMT.bean;



public class FlightBooking {
	private String flightBookingId;
	private String userId;
	private String flightId;
	private String flightBookingDate;
	private boolean flag;
	public FlightBooking(String flightBookingId, String userId, String flightId, String flightbookingDate , boolean flag) {
		super();
		this.flightBookingId = flightBookingId;
		this.userId = userId;
		this.flightId = flightId;
		this.flightBookingDate = flightbookingDate;
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
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getFlightId() {
		return flightId;
	}
	public void setFlightId(String flightId) {
		this.flightId = flightId;
	}
	public String getFlightBookingDate() {
		return flightBookingDate;
	}
	public void setFlightBookingDate(String flightBookingDate) {
		this.flightBookingDate = flightBookingDate;
	}
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean string) {
		this.flag = string;
	}
	@Override
	public String toString() {
		return "FlightBooking [flightBookingId=" + flightBookingId + ", userId=" + userId + ", flightId=" + flightId
				+ ", flightBookingDate=" + flightBookingDate + ", flag=" + flag + "]";
	}
	
	
}
