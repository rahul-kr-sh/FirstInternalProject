package com.MMT.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import com.MMT.bean.Promotion;
import com.MMT.bean.Wallet;

public interface WalletDaoMMT {
	int insertWallet(Wallet w) throws SQLException;
	Wallet displayWallet(String userId)throws SQLException ;
	int updateWallet(String userId,Wallet newWallet)throws SQLException;
	ArrayList<Wallet> displayWalletAll() throws SQLException;
	int deleteWallet(Wallet w) throws SQLException;
}
