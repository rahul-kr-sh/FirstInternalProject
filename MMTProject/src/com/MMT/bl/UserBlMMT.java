package com.MMT.bl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import com.MMT.bean.FlightBooking;
import com.MMT.bean.HotelBooking;
import com.MMT.bean.User;
import com.MMT.dao.FlightBookingImpMMT;
import com.MMT.dao.HotelBookingDaoImplMMT;
import com.MMT.dao.UserDaoImplMMT;

public class UserBlMMT {
	private HotelBookingDaoImplMMT H = new HotelBookingDaoImplMMT();
	private FlightBookingImpMMT F = new FlightBookingImpMMT();

	public User checkLogin(String username, String password) throws SQLException {
		UserDaoImplMMT udi = new UserDaoImplMMT();

		User user = (User) udi.search(username);
		if (user.getUserId().equals(username) && user.getUserPassword().equals(password)) {
			return user;
		}

		return null;
	}

	public boolean register(User user) throws SQLException {
		UserDaoImplMMT udi = new UserDaoImplMMT();

		if (udi.search(user.getUserId()) == null) {

			udi.insert(user);
			return true;
		} else {
			System.out.println("User Name already taken.Please select another user name.");
			return false;
		}

	}
	
	
	public ArrayList<FlightBooking> pastFbooking(String userId) throws ClassNotFoundException, SQLException
	{
		
		return F.searchFlightBooking(userId);
	}
	
	public ArrayList<HotelBooking> pastHbooking(String userId) throws ClassNotFoundException, IOException, SQLException
	{
		
		return H.searchHotelBooking(userId);
	}
	public User searchUser(String uid) throws SQLException{
		return new UserDaoImplMMT().search(uid);
	}
	

}
