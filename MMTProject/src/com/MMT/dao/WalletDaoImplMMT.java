package com.MMT.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.MMT.bean.Promotion;
import com.MMT.bean.Wallet;

public class WalletDaoImplMMT implements WalletDaoMMT {
	Connection con = null;
	Wallet wl = null;

	@Override
	public Wallet displayWallet(String userId) throws SQLException, ClassNotFoundException, IOException {
		con = DbConnection.dbConnection();
		wl = new Wallet();
		
		ResultSet rs;
		PreparedStatement pst=con.prepareStatement("select * from wallet where UserId=?");
		pst.setString(1, userId);
		rs=pst.executeQuery();
		while (rs.next()) {
			wl.setUserId(rs.getString(1));
			wl.setWalletBalance(rs.getDouble(2));
			con.close();
			return wl;
		}
		con.close();
		return null;
	}

	@Override
	public int updateWallet(String userId, Wallet newWallet) throws SQLException, ClassNotFoundException, IOException {
		con = DbConnection.dbConnection();
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("select * from  WALLET where USERID=" + userId);
		while (rs.next()) {
			double newBalance = newWallet.getWalletBalance();
			int rows = stmt.executeUpdate("update WALLET set WALLETBALANCE=" + newBalance + "where USERID=" + userId);
			if (rows > 0) {
				con.close();
				return rows;
			}
		}
		con.close();
		return 0;
	}

	@Override
	public ArrayList<Wallet> displayWalletAll() throws SQLException, ClassNotFoundException, IOException {
		Wallet pro = new Wallet();
		con = DbConnection.dbConnection();

		ArrayList<Wallet> proList = new ArrayList<Wallet>();

		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("select * from Wallet ");
		while (rs.next()) {
			pro.setUserId(rs.getString(1));
			pro.setWalletBalance(rs.getDouble(2));
			proList.add(pro);
		}
		con.close();
		return proList;
	}

	@Override
	public int insertWallet(Wallet w) throws SQLException, ClassNotFoundException, IOException {
		con=DbConnection.dbConnection();
		String uId=w.getUserId();
		double userBalance=w.getWalletBalance();
		Statement stmt=con.createStatement();
		int rows=stmt.executeUpdate("INSERT INTO Wallet values("+uId+","+userBalance+")");
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
	public int deleteWallet(Wallet w) throws SQLException, ClassNotFoundException, IOException {
		con=DbConnection.dbConnection();
		String uId=w.getUserId();
		double userBalance=w.getWalletBalance();
		Statement stmt=con.createStatement();
		int rows=stmt.executeUpdate("delete from Wallet where USERID=+"+uId);
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
}
