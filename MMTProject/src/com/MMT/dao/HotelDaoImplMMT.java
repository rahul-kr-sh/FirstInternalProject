package com.MMT.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.MMT.bean.Hotel;
import com.MMT.bean.HotelRoom;


public class HotelDaoImplMMT implements HotelDaoMMT {

	@Override
	public int insertHotel(Hotel h) throws SQLException, ClassNotFoundException, IOException {
		Connection con=DbConnection.dbConnection();
		String hotelId=h.getHotelId();
		String hotelName=h.getHotelName();
		String hotelLocation=h.getHotelLocation();
		String hotelInfo=h.getHotelInfo();
	
		//Query
		Statement stmt=con.createStatement();
		//System.out.println("b4 insert hotel");
		int rows=stmt.executeUpdate("INSERT INTO Hotel (hotelId,hotelName,hotelLocation,hotelInfo) VALUES ("+hotelId+",'"+hotelName+"','"+hotelLocation+"','"+hotelInfo+"')");
		//Process Results
		//System.out.println("a4 insert hotel");
		
		ArrayList<HotelRoom> rl=h.getHotelRoom();
		int rows2=0;
		
		for(HotelRoom room:rl)
		{
			Statement stmt2=con.createStatement();
			//System.out.println("b4 insert room");
			rows2=stmt2.executeUpdate("INSERT INTO HotelRoom (hotelId,hotelRoomNo,hotelRoomType,hotelRoomPrice,hotelRoomStatus) VALUES ("+hotelId+","+room.getHotelRoomNo()+",'"+room.getHotelRoomType()+"',"+room.getHotelRoomPrice()+",'"+room.getHotelRoomStatus()+"')");
			//System.out.println("a4 insert room");
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
		
		Connection con=DbConnection.dbConnection();
		Statement stmt3=con.createStatement();
		 int rows3=stmt3.executeUpdate("delete from HotelRoom where hotelId ="+hotelId);
	
		 
		Statement stmt=con.createStatement();
		int rows=stmt.executeUpdate("delete from Hotel where hotelId ="+hotelId);
		//Process Results
		
			
		
		if(rows>0&rows3>0)
		{
			return rows;
		}
		else return 0;
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
