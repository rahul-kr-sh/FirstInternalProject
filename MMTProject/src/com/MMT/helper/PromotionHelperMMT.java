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
	ob.setPromotionExpiryDate(sc.next());
	ob.setPromotionMinRequiredAmount(sc.nextFloat());
	ob.setPromotionType(sc.next());
}
}
