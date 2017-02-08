package com.MMT.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.MMT.bean.Promotion;
import com.MMT.bean.Wallet;

public class WalletDaoImplMMT implements WalletDaoMMT {
	Connection con=null;
	Wallet wl=null;
	@Override
	public Wallet displayWallet(String userId) throws SQLException {
		con=DbConnection.dbConnection();
		wl=new Wallet();
		Statement stmt=con.createStatement();
		ResultSet rs=stmt.executeQuery("select * from  WALLET where USERID="+userId);
		while(rs.next()){
				wl.setUserId(rs.getString(1));
				wl.setWalletBalance(rs.getDouble(2));
				con.close();
				return wl;	
		}
		con.close();
		return null;
	}

	@Override
	public int updateWallet(String userId, Wallet newWallet) throws SQLException {
		con=DbConnection.dbConnection();
		Statement stmt=con.createStatement();
		ResultSet rs=stmt.executeQuery("select * from  WALLET where USERID="+userId);
		while(rs.next()){
			double newBalance=newWallet.getWalletBalance();
				int rows=stmt.executeUpdate("update WALLET set WALLETBALANCE="+newBalance+"where USERID="+userId);
				if(rows>0){
					con.close();
				return rows;}
		}
		con.close();
		return 0;
	}

	@Override
	public ArrayList<Wallet> displayWalletAll() throws SQLException {
		Wallet pro=new Wallet();
		con=DbConnection.dbConnection();
		
		ArrayList<Wallet> proList=new ArrayList<Wallet>();
		
		Statement stmt=con.createStatement();
		ResultSet rs=stmt.executeQuery("select * from Wallet ");
		while(rs.next()){
			pro.setUserId(rs.getString(1));
			pro.setWalletBalance(rs.getDouble(2));
			proList.add(pro);
		}
		con.close();
		return proList;
	}
}
