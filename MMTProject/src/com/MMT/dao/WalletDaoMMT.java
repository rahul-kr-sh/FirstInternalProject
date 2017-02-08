package com.MMT.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import com.MMT.bean.Wallet;

public interface WalletDaoMMT {
	Wallet displayWallet(String userId)throws SQLException ;
	int updateWallet(String userId,Wallet newWallet)throws SQLException;
	ArrayList<Wallet> displayWallet() throws SQLException;
}
