package com.MMT.dao;

import java.sql.SQLException;


import com.MMT.bean.Wallet;

public interface WalletDaoMMT {
	Wallet displayWallet(String userId)throws SQLException ;
	int updateWalletAdd(String userId,Double amtToAddToWallet)throws SQLException;
	int updateWalletSub(String userId,Double amtToSubToWallet)throws SQLException;
}
