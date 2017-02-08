package com.MMT.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import com.MMT.bean.Promotion;
public interface PromotionDaoMMT {
	int insertPromotion(Promotion p) throws SQLException  ;
	int deletePromotion(String promotionId)throws SQLException;
	int updatePromotion(String promotionId,Promotion newPromotion)throws SQLException;
	ArrayList<Promotion> displayPromotion() throws SQLException;
	Promotion searchPromotion(String promotionId)throws SQLException ;
	ArrayList<Promotion> displayPromotion(String promotionType) throws SQLException;
}
