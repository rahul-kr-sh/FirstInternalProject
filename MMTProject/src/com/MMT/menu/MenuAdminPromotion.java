package com.MMT.menu;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import com.MMT.bean.Promotion;
import com.MMT.bl.PromotionBlMMT;
import com.MMT.dao.PromotionDaoImplMMT;
import com.MMT.helper.PromotionHelperMMT;

public class MenuAdminPromotion {
	Promotion pro=new Promotion();
	PromotionDaoImplMMT pdi=new PromotionDaoImplMMT();
	PromotionBlMMT pb=new PromotionBlMMT();
	ArrayList<Promotion> al=new ArrayList<Promotion>();
	PromotionBlMMT pBl=new PromotionBlMMT();
	PromotionHelperMMT ph=new PromotionHelperMMT();
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
		
		al=pBl.displayPromotion();
		System.out.println(al);
		break;
	case 2:
		System.out.println("Enter the Promotion ID");
		String promotionId=sc.next();
		pro=pBl.searchPromotion(promotionId);
		System.out.println(pro);
		break;
	case 3:
		
		ph.input();
		Promotion ob=ph.getOb();
		int j=pb.insertPromotion(ob);
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
		int k=pBl.deletePromotion(pId);
		if(k==1){
			System.out.println("Promotion "+pId+" is Deleted" );
		}
		else{
			System.out.println("Can't delete");
		}
		break;
	case 5:
		System.out.println("Enter the Promotion ID whose Information you want to update");
		String h=pro.getPromotionId();
		Promotion p1;
		p1=pdi.searchPromotion(h);
		if(p1==null)
		{
			System.out.println("This Promotion doesn't not exist");
		}
		else{
			ph.input();
			Promotion ob1=ph.getOb();
			int j1=pb.insertPromotion(ob1);
			if(j1==1){
				System.out.println("New Promotion Inserted");
			}
			else{
				System.out.println("Can't Insert");
			}
		int	l=pdi.updatePromotion(h,p1);
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
