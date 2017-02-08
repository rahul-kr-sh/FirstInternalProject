package com.MMT.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.MMT.bean.Admin;


public class AdminDaoImplMMT implements AdminDao{
	Admin admin=new Admin();
	@Override
	public int insert(Admin admin) throws SQLException {
		int row;
		Connection con=DbConnection.dbConnection();
		PreparedStatement pst=con.prepareStatement("insert into admin values(?,?,?,?,?,?)");
		pst.setString(1, admin.getAdminId());
		pst.setString(2,admin.getAdminName());
		pst.setLong(3,admin.getAdminPhoneNo());
		pst.setString(4,admin.getAdminEmailId());
		pst.setString(5,admin.getAdminAddress());
		pst.setString(6,admin.getAdminPassword());
		
		
		row=pst.executeUpdate();
		con.close();
		return row;
	}

	@Override
	public Admin search(String uid) throws SQLException {
		
		ResultSet rs;
		Connection con=DbConnection.dbConnection();;
		PreparedStatement pst=con.prepareStatement("select * from admin where ADMINID=?");
		pst.setString(1, uid);
		rs=pst.executeQuery();
		while(rs.next()){
			admin.setAdminId((rs.getString("ADMINID")));
			admin.setAdminName((rs.getString("ADMINNAME")));
			admin.setAdminPhoneNo(rs.getLong("ADMINPHONENO"));
			admin.setAdminEmailId(rs.getString("ADMINEMAILID"));
			admin.setAdminAddress(rs.getString("ADMINADDRESS"));
			admin.setAdminPassword(rs.getString("ADMINPASSWORD"));	
		}

		con.close();
		return admin;
	}

	@Override
	public int delete(String uid) throws SQLException {
		
		int row;
		Connection con=DbConnection.dbConnection();
		PreparedStatement pst=con.prepareStatement("delete from admin where ADMINID=?");
		pst.setString(1, uid);
		row=pst.executeUpdate();
		con.close();
		return row;
	}

	@Override
	public int update(String uid, Admin admin) throws SQLException {
		int row;
		Connection con=DbConnection.dbConnection();
		PreparedStatement pst=con.prepareStatement("update admin set ADMINNAME=?,  ADMINPHONENO=?, 	ADMINEMAILID=?, ADMINADDRESS=?,ADMINPASSWORD=? where ADMINID	=?");
		pst.setString(1, admin.getAdminName());
		pst.setLong(2, admin.getAdminPhoneNo());
		pst.setString(3, admin.getAdminEmailId());
		pst.setString(4, admin.getAdminAddress());
		pst.setString(5, admin.getAdminPassword());
		pst.setString(6, uid);
		row=pst.executeUpdate();
		return row;
		
	}

	
}
