package com.MMT.bean;

import java.util.Date;

public class Promotion {
	private int promotionId;
	private String promotionName;
	private float promotionDiscount;
	private Date promotionExpiryDate;
	private float promotionMinRequiredAmount;
	private String promotionType;
	public Promotion(int promotionId, String promotionName, float promotionDiscount, Date promotionExpiryDate,
			float promotionMinRequiredAmount, String promotionType) {
		super();
		this.promotionId = promotionId;
		this.promotionName = promotionName;
		this.promotionDiscount = promotionDiscount;
		this.promotionExpiryDate = promotionExpiryDate;
		this.promotionMinRequiredAmount = promotionMinRequiredAmount;
		this.promotionType = promotionType;
	}
	public Promotion() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getPromotionId() {
		return promotionId;
	}
	public void setPromotionId(int promotionId) {
		this.promotionId = promotionId;
	}
	public String getPromotionName() {
		return promotionName;
	}
	public void setPromotionName(String promotionName) {
		this.promotionName = promotionName;
	}
	public float getPromotionDiscount() {
		return promotionDiscount;
	}
	public void setPromotionDiscount(float promotionDiscount) {
		this.promotionDiscount = promotionDiscount;
	}
	public Date getPromotionExpiryDate() {
		return promotionExpiryDate;
	}
	public void setPromotionExpiryDate(Date promotionExpiryDate) {
		this.promotionExpiryDate = promotionExpiryDate;
	}
	public float getPromotionMinRequiredAmount() {
		return promotionMinRequiredAmount;
	}
	public void setPromotionMinRequiredAmount(float promotionMinRequiredAmount) {
		this.promotionMinRequiredAmount = promotionMinRequiredAmount;
	}
	public String getPromotionType() {
		return promotionType;
	}
	public void setPromotionType(String promotionType) {
		this.promotionType = promotionType;
	}
	@Override
	public String toString() {
		return "Promotion [promotionId=" + promotionId + ", promotionName=" + promotionName + ", promotionDiscount="
				+ promotionDiscount + ", promotionExpiryDate=" + promotionExpiryDate + ", promotionMinRequiredAmount="
				+ promotionMinRequiredAmount + ", promotionType=" + promotionType + "]";
	} 
	
	
}
