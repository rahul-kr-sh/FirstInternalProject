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
	Connection con;
	@Override
	public int insertHotel(Hotel h) throws SQLException, ClassNotFoundException, IOException {
		
		int rows,rows2 = 0;
		con=DbConnection.dbConnection();
		PreparedStatement pst=con.prepareStatement("insert into hotel values(?,?,?,?)");
		pst.setString(1, h.getHotelId());
		pst.setString(2, h.getHotelName());
		pst.setString(3, h.getHotelLocation());
		pst.setString(4, h.getHotelInfo());
		
		rows=pst.executeUpdate();
		//System.out.println(rows);
		
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
		con=DbConnection.dbConnection();
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
		
		con=DbConnection.dbConnection();
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
		con=DbConnection.dbConnection();
		//Query
		ArrayList<Hotel> hotList=new ArrayList<Hotel>();
		HotelRoom room=new HotelRoom(); 

		//Statement stmt=con.createStatement();
		//ResultSet rs=stmt.executeQuery("select * from Hotel ");
		//Process Results
		ResultSet rs;
		PreparedStatement pst=con.prepareStatement("select * from hotel");
		rs=pst.executeQuery();
		while(rs.next()){
			
			hotel.setHotelId(rs.getString("hotelId"));
			hotel.setHotelName(rs.getString("hotelName"));
			hotel.setHotelLocation(rs.getString("hotelLocation"));
			hotel.setHotelInfo(rs.getString("hotelInfo"));
			ResultSet rs2;
			PreparedStatement pst1=con.prepareStatement("select * from hotelroom where hotelId=?");
			pst1.setString(1, rs.getString("hotelId"));
			//Statement stmt2=con.createStatement();
			ArrayList<HotelRoom> rl=new ArrayList<HotelRoom>();
			rs2=pst1.executeQuery();
			//ResultSet rs2=stmt.executeQuery("select * from  HotelRoom where hotelId= "+rs.getString("hotelId"));
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
			//System.out.println(hotList);

		}
		
		con.close();
		return hotList;
	}

	@Override
	public Hotel searchHotel(String hotelId) throws  SQLException, ClassNotFoundException, IOException {
		Hotel hotel =new Hotel();
		con=DbConnection.dbConnection();
		
		ResultSet rs;
		PreparedStatement pst=con.prepareStatement("select * from hotel where hotelId=?");
		pst.setString(1, hotelId);
		rs=pst.executeQuery();
		while(rs.next()){

			hotel.setHotelId(rs.getString("hotelId"));
			hotel.setHotelName(rs.getString("hotelName"));
			hotel.setHotelLocation(rs.getString("hotelLocation"));
			hotel.setHotelInfo(rs.getString("hotelInfo"));
			PreparedStatement pst1=con.prepareStatement("select * from hotelroom where hotelId=?");
			pst1.setString(1, hotelId);
			HotelRoom room=new HotelRoom();
			ArrayList<HotelRoom> rl=new ArrayList<HotelRoom>();
			ResultSet rs2=pst1.executeQuery();
			//ResultSet rs2=stmt.executeQuery("select * from Hoteloom where hotelId= "+rs.getString("hotelId"));
			while(rs2.next())
			{
				room.setHotelRoomNo(rs2.getInt("hotelRoomNo"));
				room.setHotelRoomType(rs2.getString("hotelRoomType"));
				room.setHotelRoomPrice(rs2.getDouble("hotelRoomPrice"));
				room.setHotelRoomStatus(rs2.getString("hotelRoomStatus"));
				rl.add(room);
			}
			hotel.setHotelRoom(rl);;
			
			con.close();
			return hotel;

		}
		
		con.close();
		return null;
	}



	@Override
	public ArrayList<Hotel> searchHotel1(String hotelLocation) throws SQLException, ClassNotFoundException, IOException {
		ArrayList<Hotel> H=new ArrayList<>();
		Hotel hotel =new Hotel();
		con=DbConnection.dbConnection();
		ResultSet rs;
		PreparedStatement pst=con.prepareStatement("select * from hotel where hotelLocation=?");
		
		pst.setString(1, hotelLocation);
		rs=pst.executeQuery();
		while(rs.next()){
			
			hotel.setHotelId(rs.getString("hotelId"));
			hotel.setHotelName(rs.getString("hotelName"));
			hotel.setHotelLocation(rs.getString("hotelLocation"));
			hotel.setHotelInfo(rs.getString("hotelInfo"));
			PreparedStatement pst1=con.prepareStatement("select * from hotelroom where hotelId=?");
			
			pst1.setString(1, (rs.getString("hotelId")));
			ResultSet rs2=pst1.executeQuery();

			HotelRoom room=new HotelRoom();
			ArrayList<HotelRoom> rl=new ArrayList<HotelRoom>();
			//ResultSet rs2=stmt.executeQuery("select * from Hoteloom where hotelId= "+rs.getString("hotelId"));
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
