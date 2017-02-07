package com.MMT.menu;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import com.MMT.bean.Promotion;
import com.MMT.bl.PromotionBlMMT;

public class MyMenuPromotion {
	Promotion pro=new Promotion();
	ArrayList<Promotion> al=new ArrayList<Promotion>();
	PromotionBlMMT pBl=new PromotionBlMMT();
	Scanner sc=new Scanner(System.in);
public void display() throws SQLException{
	System.out.println("1.Login");
	choice();
}
public void choice() throws SQLException{
	System.out.println("1.Display all Promotions");
	System.out.println("2.Search Promotions");
	int i=sc.nextInt();
	switch(i){
	case 1:
		
		al=pBl.displayPromotion();
		System.out.println(al);
		break;
	case 2:
		System.out.println("Enter the Promotion ID");
		String promotionId=sc.next();
		pro=pBl.searchPromotion(promotionId);
		System.out.println(pro);
		break;
	default:
			System.out.println("Invalid choice");
		
	}
}
}
