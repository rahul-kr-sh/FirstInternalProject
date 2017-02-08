package com.MMT.dao;

import java.sql.SQLException;


import com.MMT.bean.Wallet;

public interface WalletDaoMMT {
	Wallet displayWallet(String userId)throws SQLException ;
	int updateWallet(String userId,Double amtToAddToWallet)throws SQLException;
}
