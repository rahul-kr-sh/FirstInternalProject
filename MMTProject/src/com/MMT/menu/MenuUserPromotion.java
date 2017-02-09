package com.MMT.menu;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import com.MMT.bean.Promotion;
import com.MMT.bl.PromotionBlMMT;

public class MenuUserPromotion {
	Promotion promotion=new Promotion();
	ArrayList<Promotion> promotionList=new ArrayList<Promotion>();
	PromotionBlMMT promotionBL=new PromotionBlMMT();
	Scanner sc=new Scanner(System.in);
public void choice() throws SQLException{
	System.out.println("1.Display all Promotions");
	System.out.println("2.Search Promotions");
	System.out.println("---------------");
	int i=sc.nextInt();
	switch(i){
	case 1:
		
		promotionList=promotionBL.displayPromotion();
		System.out.println(promotionList);
		break;
	case 2:
		System.out.println("Enter the Promotion ID");
		String promotionId=sc.next();
		promotion=promotionBL.searchPromotion(promotionId);
		System.out.println(promotion);
		break;
	default:
			System.out.println("Invalid choice");
		
	}
}
}
