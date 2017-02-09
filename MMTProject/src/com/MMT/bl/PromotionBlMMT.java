package com.MMT.bl;
import java.sql.SQLException;
import java.util.ArrayList;

import com.MMT.bean.Promotion;
import com.MMT.dao.PromotionDaoImplMMT;
import com.MMT.dao.PromotionDaoMMT;
public class PromotionBlMMT {
	private PromotionDaoMMT promotionDao=new PromotionDaoImplMMT();
	
	public int insertPromotion(Promotion p)throws SQLException {
		return promotionDao.insertPromotion(p);
	}
	
	public int deletePromotion(String promotionId)throws SQLException {
		return promotionDao.deletePromotion(promotionId);
	}
	
	public int updatePromotion(String pId, Promotion newp) throws SQLException{
		return promotionDao.updatePromotion(pId,newp);
	}
	
	public ArrayList<Promotion> displayPromotion()throws SQLException {
		return promotionDao.displayPromotion();
	}
	
	public ArrayList<Promotion> displayPromotion(String promotionType)throws SQLException {
		return promotionDao.displayPromotion(promotionType);
	}
	
	public Promotion searchPromotion(String promotionId) throws SQLException{
		return promotionDao.searchPromotion(promotionId);
	}
	
	public float applyPromotion(Promotion p, String userId, float TicketPrice){
		float finalPrice;
		float currentPrice=TicketPrice;
		if(currentPrice-p.getPromotionMinRequiredAmount()>0){
			float discount= (float) ((p.getPromotionDiscount()/100)*currentPrice);
			finalPrice=currentPrice-discount;
			return finalPrice;
		}
		else{
			//Make an Exception // Make logic if cart value is less than minimum required amount
			System.out.println("Cart Value should be greater than "+p.getPromotionMinRequiredAmount());
			return currentPrice;
		}
	}
	
	
}
