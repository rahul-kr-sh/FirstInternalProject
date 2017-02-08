package com.MMT.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.MMT.bean.Wallet;

public class WalletDaoImplMMT implements WalletDaoMMT {
	Connection con=null;
	Wallet wl=new Wallet();
	@Override
	public Wallet displayWallet(String userId) throws SQLException {
		con=DbConnection.dbConnection();
		Statement stmt=con.createStatement();
		ResultSet rs=stmt.executeQuery("select * from  WALLET where USERID="+userId);
		while(rs.next()){
			String uId=rs.getString(1);
			if(uId.equals(userId)){
				double walletBalance=rs.getDouble(2);
				wl.setUserId(userId);
				wl.setWalletBalance(walletBalance);
				con.close();
				return wl;
				
			}
			
		}
		con.close();
		return null;
	}

	@Override
	public int updateWallet(String userId, Double amtToAddToWallet) throws SQLException {
		con=DbConnection.dbConnection();
		Statement stmt=con.createStatement();
		ResultSet rs=stmt.executeQuery("select * from  WALLET where USERID="+userId);
		while(rs.next()){
			String uId=rs.getString(1);
			if(uId.equals(userId)){
				double oldWalletBalance=rs.getDouble(2);
				double presentWalletBalance=oldWalletBalance+amtToAddToWallet;
				int rows=stmt.executeUpdate("update WALLET set WALLETBALANCE="+presentWalletBalance+"where USERID="+userId);
				if(rows>0){
					con.close();
				return rows;}
			}
			
		}
		con.close();
		return 0;
	}

}
