package com.MMT.bl;

import java.sql.SQLException;
import java.util.ArrayList;

import com.MMT.bean.Wallet;
import com.MMT.dao.WalletDaoImplMMT;
import com.MMT.dao.WalletDaoMMT;

public class WalletBlMMT {
	private WalletDaoMMT W = new WalletDaoImplMMT();

	public float walletBalance(String userId) throws SQLException {
		Wallet w;

		w = W.displayWallet(userId);
		return (float) w.getWalletBalance();
	}

	public ArrayList<Wallet> displayAll() throws SQLException {
		return W.displayWalletAll();
	}

	public boolean addWalletMoney(String userId,Double value) throws SQLException {
		Wallet w;
		w=W.displayWallet(userId);
		w.setWalletBalance(w.getWalletBalance()+value);
		W.updateWallet(userId, w);
		return true;
	}
	
	public boolean subtractWalletMoney(String userId,Double value) throws SQLException {
		Wallet w;
		w=W.displayWallet(userId);
		double temp=w.getWalletBalance()-value;
		if(temp<0){
			//Exception //Insufficient Funds Add money to wallet
			return false;
		}
		else{
			w.setWalletBalance(temp);
			W.updateWallet(userId, w);
			return true;
		}
		
	}
}
