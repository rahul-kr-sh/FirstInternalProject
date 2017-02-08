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
	private FlightDaoImplMMT F = new FlightDaoImplMMT();
	private HotelDaoImplMMT H = new HotelDaoImplMMT();
	private AdminDaoImplMMT A=new AdminDaoImplMMT();
	public int insertHotel(Hotel h) throws ClassNotFoundException, IOException, SQLException{		
		return H.insertHotel(h);		
	}
	
	public int insertFlight(Flight f) throws ClassNotFoundException, SQLException{
		return F.insertFlight(f);
	}
	
	public int deleteHotel(String hotelId) throws ClassNotFoundException, SQLException{
		return H.deleteHotel(hotelId);
	}
	
	public int deleteFlight(String flightId) throws ClassNotFoundException, SQLException{
		return F.deleteFlight(flightId);
	}
	
	public int modifyHotel(String hotelId, Hotel newhotel) throws ClassNotFoundException, SQLException{
		return H.updateHotel(hotelId, newhotel);
	}
	
	public int modifyFlight(String flightId, Flight newflight) throws ClassNotFoundException, SQLException{
		return F.updateFlight(flightId, newflight);
	}
	
	public Admin searchAdmin(String adminId) throws SQLException{
		return A.search(adminId);
	}
	
	public boolean addAdmin(Admin admin) throws SQLException{
		if (A.search(admin.getAdminName()) == null) {

			A.insert(admin);
			return true;
		} else {
			System.out.println("UserName already taken.Please select another  Username.");
			return false;
		}
	}
	
	
	public int removeAdmin(String adminId) throws SQLException{
		return A.delete(adminId);
	}
	
	public int modifyAdmin(String adminId, Admin admin) throws SQLException{
		return A.update(adminId, admin);
	}
	
	public Admin checkAdminLogin(String username, String password) throws SQLException{

		Admin admin = (Admin) A.search(username);
		if (admin.getAdminId().equals(username) && admin.getAdminPassword().equals(password)) {
			return admin;
		}

		return null;
	}
	
	
	
}
