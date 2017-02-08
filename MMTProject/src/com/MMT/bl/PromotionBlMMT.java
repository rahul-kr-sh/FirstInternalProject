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
	
	public ArrayList<Promotion> displayPromotion(String promotionType)throws SQLException {
		return ed.displayPromotion(promotionType);
	}
	
	public Promotion searchPromotion(String promotionId) throws SQLException{
		return ed.searchPromotion(promotionId);
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
			return currentPrice;
		}
	}
	
	
}
