package com.MMT.dao;

import java.sql.SQLException;


import com.MMT.bean.Wallet;

public interface WalletDaoMMT {
	double displayWallet(String userId)throws SQLException ;
	double updateWallet(String userId,Double amtToAddToWallet)throws SQLException;
}
