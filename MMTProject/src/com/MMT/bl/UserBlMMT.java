package com.MMT.bl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import com.MMT.bean.FlightBooking;
import com.MMT.bean.HotelBooking;
import com.MMT.bean.User;
import com.MMT.bean.Wallet;
import com.MMT.dao.FlightBookingImpMMT;
import com.MMT.dao.HotelBookingDaoImplMMT;
import com.MMT.dao.UserDaoImplMMT;
import com.MMT.dao.WalletDaoImplMMT;

public class UserBlMMT {
	private HotelBookingDaoImplMMT hotelBookingDao = new HotelBookingDaoImplMMT();
	private FlightBookingImpMMT flightBookingDao = new FlightBookingImpMMT();
	private UserDaoImplMMT userDao = new UserDaoImplMMT();

	public User checkLogin(String username, String password) throws SQLException, ClassNotFoundException, IOException {

		User user = (User) userDao.search(username);
		if (user == null) {
			return null;
		} else if (user != null && (user.getUserId().equals(username) && user.getUserPassword().equals(password))) {
			return user;
		}

		return null;
	}

	public boolean register(User user) throws SQLException, ClassNotFoundException, IOException {

		if (userDao.search(user.getUserId()) == null) {

			userDao.insert(user);
			WalletDaoImplMMT walletDao = new WalletDaoImplMMT();
			Wallet wallet = new Wallet();
			wallet.setUserId(user.getUserId());
			wallet.setWalletBalance(0);

			walletDao.insertWallet(wallet);
			return true;
		} else {
			System.out.println("User Name already taken.Please select another user name.");
			return false;
		}

	}

	public ArrayList<FlightBooking> pastFbooking(String userId)
			throws ClassNotFoundException, SQLException, IOException {

		return flightBookingDao.searchFlightBooking(userId);
	}

	public ArrayList<HotelBooking> pastHbooking(String userId)
			throws ClassNotFoundException, IOException, SQLException {

		return hotelBookingDao.searchHotelBooking(userId);
	}

	public User searchUser(String uid) throws SQLException, ClassNotFoundException, IOException {
		return new UserDaoImplMMT().search(uid);
	}

}
