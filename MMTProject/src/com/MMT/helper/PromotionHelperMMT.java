package com.MMT.helper;

import java.util.Scanner;

import com.MMT.bean.Promotion;

public class PromotionHelperMMT {
	Promotion ob=new Promotion();
	Scanner sc=new Scanner(System.in);
public void input(){
	System.out.println("Enter Promotion Id");
	ob.setPromotionId(sc.next());
	System.out.println("Enter Promotion Name");
	ob.setPromotionName(sc.next());
	System.out.println("Enter Promotion Discount");
	ob.setPromotionDiscount(sc.nextFloat());
	System.out.println("Enter promotion ExpiryDate");
	ob.setPromotionExpiryDate(sc.next());
	System.out.println("Enter Promotion Minimum Required Amount");
	ob.setPromotionMinRequiredAmount(sc.nextFloat());
	System.out.println("Enter Promotion Type");
	ob.setPromotionType(sc.next());
}
public Promotion getOb() {
	return ob;
}
public void setOb(Promotion ob) {
	this.ob = ob;
}

}
