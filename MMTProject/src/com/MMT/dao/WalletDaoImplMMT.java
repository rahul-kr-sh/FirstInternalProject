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
	public double displayWallet(String userId) throws SQLException {
		con=DbConnection.dbConnection();
		Statement stmt=con.createStatement();
		ResultSet rs=stmt.executeQuery("select * from  WALLET where USERID="+userId);
		while(rs.next()){
			String uId=rs.getString(1);
			if(uId.equals(userId)){
				double walletBalance=rs.getDouble(2);
				con.close();
				return walletBalance;
			}
			else{
				System.out.println("User is not logged in");
			}
		}
		con.close();
		return 0;
	}

	@Override
	public double updateWallet(String userId, Double amtToAddToWallet) throws SQLException {
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
				return presentWalletBalance;}
			}
			else{
				System.out.println("User data doesn't exist");
			}
		}
		con.close();
		return 0;
	}

}
