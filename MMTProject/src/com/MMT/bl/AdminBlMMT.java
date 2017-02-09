package com.MMT.bl;

import java.io.IOException;
import java.sql.SQLException;

import com.MMT.bean.Admin;
import com.MMT.bean.Flight;
import com.MMT.bean.Hotel;
import com.MMT.bean.User;
import com.MMT.dao.AdminDaoImplMMT;
import com.MMT.dao.FlightDaoImplMMT;
import com.MMT.dao.HotelDaoImplMMT;
import com.MMT.dao.UserDaoImplMMT;

public class AdminBlMMT {
	private FlightDaoImplMMT flightdao = new FlightDaoImplMMT();
	private HotelDaoImplMMT hoteldao = new HotelDaoImplMMT();
	private AdminDaoImplMMT admindao = new AdminDaoImplMMT();

	public int insertHotel(Hotel h) throws ClassNotFoundException, IOException, SQLException {
		return hoteldao.insertHotel(h);
	}

	public int insertFlight(Flight f) throws ClassNotFoundException, SQLException, IOException {
		return flightdao.insertFlight(f);
	}

	public int deleteHotel(String hotelId) throws ClassNotFoundException, SQLException, IOException {
		return hoteldao.deleteHotel(hotelId);
	}

	public int deleteFlight(String flightId) throws ClassNotFoundException, SQLException, IOException {
		return flightdao.deleteFlight(flightId);
	}

	public int modifyHotel(String hotelId, Hotel newhotel) throws ClassNotFoundException, SQLException, IOException {
		return hoteldao.updateHotel(hotelId, newhotel);
	}

	public int modifyFlight(String flightId, Flight newflight) throws ClassNotFoundException, SQLException, IOException {
		return flightdao.updateFlight(flightId, newflight);
	}

	public Admin searchAdmin(String adminId) throws SQLException, ClassNotFoundException, IOException {
		return admindao.search(adminId);
	}

	public boolean addAdmin(Admin admin) throws SQLException, ClassNotFoundException, IOException {
		if (admindao.search(admin.getAdminName()) == null) {

			admindao.insert(admin);
			return true;
		} else {
			System.out.println("UserName already taken.Please select another  Username.");
			return false;
		}
	}

	public int removeAdmin(String adminId) throws SQLException, ClassNotFoundException, IOException {
		return admindao.delete(adminId);
	}

	public int modifyAdmin(String adminId, Admin admin) throws SQLException, ClassNotFoundException, IOException {
		return admindao.update(adminId, admin);
	}

	public Admin checkAdminLogin(String username, String password) throws SQLException, ClassNotFoundException, IOException {
		
		Admin admin = (Admin) admindao.search(username);
		if(admin==null){
			return null;
		}
		else if (admin!=null && (admin.getAdminId().equals(username) && admin.getAdminPassword().equals(password))){
			return admin;
		}
		return null;
	}

}
