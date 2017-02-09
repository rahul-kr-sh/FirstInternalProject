package com.MMT.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.MMT.bean.Hotel;
import com.MMT.bean.HotelRoom;


public class HotelDaoImplMMT implements HotelDaoMMT {

	@Override
	public int insertHotel(Hotel h) throws SQLException, ClassNotFoundException, IOException {
		
		int rows,rows2 = 0;
		Connection con=DbConnection.dbConnection();
		PreparedStatement pst=con.prepareStatement("insert into hotel values(?,?,?,?)");
		pst.setString(1, h.getHotelId());
		pst.setString(2, h.getHotelName());
		pst.setString(3, h.getHotelLocation());
		pst.setString(4, h.getHotelInfo());
		
		rows=pst.executeUpdate();
		System.out.println(rows);
		
		ArrayList<HotelRoom> rl=h.getHotelRoom();
		
		
		for(HotelRoom room:rl)
		{
			
			PreparedStatement pst1=con.prepareStatement("insert into hotelroom values(?,?,?,?,?)");
			pst1.setString(1, room.getHotelId());
			pst1.setInt(2, room.getHotelRoomNo());
			pst1.setString(3, room.getHotelRoomType());
			pst1.setDouble(4, room.getHotelRoomPrice());
			pst1.setString(5, room.getHotelRoomStatus());
			 rows2 = pst1.executeUpdate();
			
		}
		if(rows>0 && rows2>0)
		{
			con.close();
			return rows;
		}
		else 
		{	con.close();
		return 0;
		}
		
	}



	@Override
	public int deleteHotel(String hotelId) throws  SQLException, ClassNotFoundException, IOException {
		
		int rows,rows2;
		Connection con=DbConnection.dbConnection();
		PreparedStatement pst=con.prepareStatement("delete from hotel where HOTELID=?");
		PreparedStatement pst1=con.prepareStatement("delete from hotelROOM where HOTELID=?");
		rows=pst.executeUpdate();
		rows2=pst1.executeUpdate();
		if(rows>0 && rows2>0)
		{
			con.close();
			return rows;
		}
		else 
			{
			con.close();
			return 0;}
	}

	@Override
	public int updateHotel(String hotelId, Hotel newhotel) throws  SQLException, ClassNotFoundException, IOException {
		
		Connection con=DbConnection.dbConnection();
		String hotelId1=newhotel.getHotelId();
		String hotelName=newhotel.getHotelName();
		String hotelLocation=newhotel.getHotelLocation();
		String hotelInfo=newhotel.getHotelInfo();
		
		//Query
		Statement stmt=con.createStatement();
		//System.out.println("b4 update");
		//System.out.println(newhotel);
		int rows=stmt.executeUpdate("update hotel set hotelId= '"+hotelId1+"' ,hotelName ='"+hotelName+"',hotelLocation='"+hotelLocation+"' ,hotelInfo ='"+hotelInfo+"' where hotelId="+hotelId);
		 //rows=stmt.executeUpdate("insert into User (address,email,uid,name,pass,phoneNo values ("+address+","+email+","+uid+","+name+","+pass+","+phoneNo+")");
		//Process Results
		//System.out.println("a4 update");
		if(rows>0)
		{
			con.close();
			return rows;
		}
		else 
		{	con.close();
		return 0;
		}
	}

	@Override
	public ArrayList<Hotel> displayHotel() throws  SQLException, ClassNotFoundException, IOException {
		Hotel hotel =new Hotel();
		Connection con=DbConnection.dbConnection();
		//Query
		ArrayList<Hotel> hotList=new ArrayList<Hotel>();
		HotelRoom room=new HotelRoom(); 

		Statement stmt=con.createStatement();
		ResultSet rs=stmt.executeQuery("select * from Hotel ");
		//Process Results
		while(rs.next()){
			
			hotel.setHotelId(rs.getString("hotelId"));
			hotel.setHotelName(rs.getString("hotelName"));
			hotel.setHotelLocation(rs.getString("hotelLocation"));
			hotel.setHotelInfo(rs.getString("hotelInfo"));
			Statement stmt2=con.createStatement();
			ArrayList<HotelRoom> rl=new ArrayList<HotelRoom>();
			ResultSet rs2=stmt.executeQuery("select * from  HotelRoom where hotelId= "+rs.getString("hotelId"));
			while(rs2.next())
			{
				room.setHotelRoomNo(rs2.getInt("hotelRoomNo"));
				room.setHotelRoomType(rs2.getString("hotelRoomType"));
				room.setHotelRoomPrice(rs2.getDouble("hotelRoomPrice"));
				room.setHotelRoomStatus(rs2.getString("hotelRoomStatus"));
				
				rl.add(room);
			}
			hotel.setHotelRoom(rl);;
			
			hotList.add(hotel);

		}
		
		con.close();
		return hotList;
	}

	@Override
	public Hotel searchHotel(String hotelId) throws  SQLException, ClassNotFoundException, IOException {
		Hotel hotel =new Hotel();
		Connection con=DbConnection.dbConnection();
		//Query
		Statement stmt=con.createStatement();
		ResultSet rs=stmt.executeQuery("select * from Hotel where hotelId="+hotelId);
		//Process Results
		while(rs.next()){
//			int id=rs.getInt("eid");
			
			
			hotel.setHotelId(rs.getString("hotelId"));
			hotel.setHotelName(rs.getString("hotelName"));
			hotel.setHotelLocation(rs.getString("hotelLocation"));
			hotel.setHotelInfo(rs.getString("hotelInfo"));
//			hotel.setuEmailId(rs.getString("uemail"));
//			user.setuPassword(rs.getString("upassword"));
//			user.setuPhoneNumber(rs.getInt("uphoneNumber"));
			Statement stmt2=con.createStatement();
			HotelRoom room=new HotelRoom();
			ArrayList<HotelRoom> rl=new ArrayList<HotelRoom>();
			ResultSet rs2=stmt.executeQuery("select * from Hoteloom where hotelId= "+rs.getString("hotelId"));
			while(rs2.next())
			{
				room.setHotelRoomNo(rs2.getInt("hotelRoomNo"));
				room.setHotelRoomType(rs2.getString("hotelRoomType"));
				room.setHotelRoomPrice(rs2.getDouble("hotelRoomPrice"));
				room.setHotelRoomStatus(rs2.getString("hotelRoomStatus"));
				rl.add(room);
			}
			hotel.setHotelRoom(rl);;
			
			
			return hotel;

		}
		
		
		return null;
	}



	@Override
	public ArrayList<Hotel> searchHotel1(String hotelLocation) throws SQLException, ClassNotFoundException, IOException {
		ArrayList<Hotel> H=new ArrayList<>();
		Hotel hotel =new Hotel();
		Connection con=DbConnection.dbConnection();
		//Query
		Statement stmt=con.createStatement();
		ResultSet rs=stmt.executeQuery("select * from Hotel where hotelLocation="+hotelLocation);
		//Process Results
		while(rs.next()){
//			int id=rs.getInt("eid");
			
			
			hotel.setHotelId(rs.getString("hotelId"));
			hotel.setHotelName(rs.getString("hotelName"));
			hotel.setHotelLocation(rs.getString("hotelLocation"));
			hotel.setHotelInfo(rs.getString("hotelInfo"));
//			hotel.setuEmailId(rs.getString("uemail"));
//			user.setuPassword(rs.getString("upassword"));
//			user.setuPhoneNumber(rs.getInt("uphoneNumber"));
			Statement stmt2=con.createStatement();
			HotelRoom room=new HotelRoom();
			ArrayList<HotelRoom> rl=new ArrayList<HotelRoom>();
			ResultSet rs2=stmt.executeQuery("select * from Hoteloom where hotelId= "+rs.getString("hotelId"));
			while(rs2.next())
			{
				room.setHotelRoomNo(rs2.getInt("hotelRoomNo"));
				room.setHotelRoomType(rs2.getString("hotelRoomType"));
				room.setHotelRoomPrice(rs2.getDouble("hotelRoomPrice"));
				room.setHotelRoomStatus(rs2.getString("hotelRoomStatus"));
				rl.add(room);
			}
			hotel.setHotelRoom(rl);;
			
			
			H.add(hotel);

		}
		
		
		return H;
	}

}
