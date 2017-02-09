package com.MMT.menu;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import com.MMT.bean.Promotion;
import com.MMT.bl.PromotionBlMMT;
import com.MMT.dao.PromotionDaoImplMMT;
import com.MMT.helper.PromotionHelperMMT;

public class MenuAdminPromotion {
	Promotion promotion=new Promotion();
	PromotionDaoImplMMT promotionDao=new PromotionDaoImplMMT();
	PromotionBlMMT promotionBl=new PromotionBlMMT();
	ArrayList<Promotion> promotionList=new ArrayList<Promotion>();
	PromotionBlMMT promotionBL=new PromotionBlMMT();
	PromotionHelperMMT promotionHelper=new PromotionHelperMMT();
	Scanner sc=new Scanner(System.in);
public void choice() throws SQLException{
	System.out.println("1.Display all Promotions");
	System.out.println("2.Search Promotions");
	System.out.println("3.Insert Promotion");
	System.out.println("4.Delete Promotion");
	System.out.println("5.Update Promotion");
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
	case 3:
		
		promotionHelper.input();
		Promotion ob=promotionHelper.getOb();
		int j=promotionBl.insertPromotion(ob);
		if(j==1){
			System.out.println("New Promotion Inserted");
		}
		else{
			System.out.println("Can't Insert");
		}
		break;
	case 4:
		System.out.println("Enter Promotion Id");
		String pId=sc.next();
		int k=promotionBL.deletePromotion(pId);
		if(k==1){
			System.out.println("Promotion "+pId+" is Deleted" );
		}
		else{
			System.out.println("Can't delete");
		}
		break;
	case 5:
		System.out.println("Enter the Promotion ID whose Information you want to update");
		String h=promotion.getPromotionId();
		Promotion p1;
		p1=promotionDao.searchPromotion(h);
		if(p1==null)
		{
			System.out.println("This Promotion doesn't not exist");
			choice();
		}
		else{
			promotionHelper.input();
			Promotion ob1=promotionHelper.getOb();
		int	l=promotionDao.updatePromotion(h,ob1);
		if(l==1)
		{System.out.println("Updated");}
		else
			System.out.println("Not Updated");
		}
		break;
	default:
			System.out.println("Invalid choice");
		
	}
}	
}
