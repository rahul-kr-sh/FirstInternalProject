package com.MMT.dao;

//New Code Changesdfs
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.MMT.bean.User;

public class UserDaoImplMMT implements UserDaoMMT {

	@Override
	public int insert(User user) throws SQLException {
		int row;
		Connection con=DbConnection.dbConnection();
		PreparedStatement pst=con.prepareStatement("insert into movie_list values(?,?,?,?,?,?)");
		pst.setString(1, user.getUserId());
		pst.setString(2,user.getUserName());
		pst.setLong(3,user.getUserPhoneNo());
		pst.setString(4,user.getUserEmailId());
		pst.setString(5,user.getUserAddress());
		pst.setString(6,user.getUserWalletId());
		
		
		row=pst.executeUpdate();
		con.close();
		return row;
		
	}

	@Override
	public User search(String uid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User delete(String uid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(String uid, User newUser) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public User displayAll() {
		// TODO Auto-generated method stub
		return null;
	}
}
