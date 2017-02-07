package com.MMT.bl;
import java.sql.SQLException;
import java.util.ArrayList;

import com.MMT.bean.Promotion;
import com.MMT.dao.PromotionDaoImplMMT;
import com.MMT.dao.PromotionDaoMMT;
public class PromotionBlMMT {
	private PromotionDaoMMT ed=new PromotionDaoImplMMT();
	public int insertPromotion(Promotion p)throws SQLException {
		return ed.insertPromotion(p);
	}
	public int deletePromotion(String promotionId)throws SQLException {
		return ed.deletePromotion(promotionId);
	}
	public int updatePromotion(String pId, Promotion newp) throws SQLException{
		return ed.updatePromotion(pId,newp);
	}
	public ArrayList<Promotion> displayPromotion()throws SQLException {
		return ed.displayPromotion();
	}
	public Promotion searchPromotion(String promotionId) throws SQLException{
		return ed.searchPromotion(promotionId);
	}
}
