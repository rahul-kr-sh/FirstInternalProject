package com.MMT.bl;

import java.sql.SQLException;
import java.util.ArrayList;

import com.MMT.bean.Wallet;
import com.MMT.dao.WalletDaoImplMMT;
import com.MMT.dao.WalletDaoMMT;

public class WalletBlMMT {
	private WalletDaoMMT walletDao = new WalletDaoImplMMT();

	public float walletBalance(String userId) throws SQLException {
		Wallet w;

		w = walletDao.displayWallet(userId);
		return (float) w.getWalletBalance();
	}

	public ArrayList<Wallet> displayAll() throws SQLException {
		return walletDao.displayWalletAll();
	}

	public boolean addWalletMoney(String userId,Double value) throws SQLException {
		Wallet w;
		w=walletDao.displayWallet(userId);
		w.setWalletBalance(w.getWalletBalance()+value);
		walletDao.updateWallet(userId, w);
		return true;
	}
	
	public boolean subtractWalletMoney(String userId,Double value) throws SQLException {
		Wallet w;
		w=walletDao.displayWallet(userId);
		double temp=w.getWalletBalance()-value;
		if(temp<0){
			//Exception //Insufficient Funds Add money to wallet
			return false;
		}
		else{
			w.setWalletBalance(temp);
			walletDao.updateWallet(userId, w);
			return true;
		}
		
	}
}
