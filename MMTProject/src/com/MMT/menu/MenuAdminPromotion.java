package com.MMT.menu;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import com.MMT.bean.Admin;
import com.MMT.bean.Promotion;
import com.MMT.bl.PromotionBlMMT;
import com.MMT.dao.PromotionDaoImplMMT;
import com.MMT.helper.PromotionHelperMMT;

public class MenuAdminPromotion {
	Promotion promotion = new Promotion();
	PromotionDaoImplMMT promotionDao = new PromotionDaoImplMMT();
	PromotionBlMMT promotionBl = new PromotionBlMMT();
	ArrayList<Promotion> promotionList = new ArrayList<Promotion>();
	PromotionBlMMT promotionBL = new PromotionBlMMT();
	PromotionHelperMMT promotionHelper = new PromotionHelperMMT();
	AdminDashboard adminDashboard = new AdminDashboard();
	Scanner sc = new Scanner(System.in);

	public void choice(Admin admin) throws SQLException, ClassNotFoundException, IOException {
		System.out.println("1.Display all Promotions");
		System.out.println("2.Search Promotions");
		System.out.println("3.Insert Promotion");
		System.out.println("4.Delete Promotion");
		System.out.println("5.Update Promotion");
		System.out.println("6.Go Back");
		int i = sc.nextInt();
		switch (i) {
		case 1:

			try {
				promotionList = promotionBL.displayPromotion();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(promotionList);
			choice(admin);
			break;
		case 2:
			System.out.println("Enter the Promotion ID");
			String promotionId = sc.next();
			try {
				promotion = promotionBL.searchPromotion(promotionId);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				choice(admin);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				choice(admin);
			}
			System.out.println(promotion.getPromotionId());
			System.out.println(promotion.getPromotionName());
			System.out.println(promotion.getPromotionType());
			System.out.println(promotion.getPromotionDiscount());
			System.out.println(promotion.getPromotionExpiryDate());
			
			System.out.println(promotion.getPromotionMinRequiredAmount());
			System.out.println("------------------------");
			choice(admin);
			break;
		case 3:

			promotionHelper.input();
			Promotion ob = promotionHelper.getOb();
			int j = 0;
			try {
				j = promotionBl.insertPromotion(ob);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (j == 1) {
				System.out.println("New Promotion Inserted");
				choice(admin);
			} else {
				System.out.println("Can't Insert");
			}
			break;
		case 4:
			System.out.println("Enter Promotion Id");
			String pId = sc.next();
			int k = 0;
			try {
				k = promotionBL.deletePromotion(pId);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (k == 1) {
				System.out.println("Promotion " + pId + " is Deleted");
				choice(admin);
			} else {
				System.out.println("Can't delete");
				choice(admin);
			}
			break;
		case 5:
			System.out.println("Enter the Promotion ID whose Information you want to update");
			String promotionID=sc.next();
			//String  = promotion.getPromotionId();
			Promotion promo1 = null;
			try {
				promo1 = promotionDao.searchPromotion(promotionID);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (promo1 == null) {
				System.out.println("This Promotion doesn't not exist");
				choice(admin);
			} else {
				promotionHelper.input();
				Promotion ob1 = promotionHelper.getOb();
				int l = 0;
				try {
					l = promotionDao.updatePromotion(promotionID, ob1);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					choice(admin);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					choice(admin);
				}
				if (l == 1) {
					System.out.println("Updated");
					choice(admin);
				} else{
					System.out.println("Not Updated");
					choice(admin);
				}
					
			}
			break;
		case 6:
			adminDashboard.showDashboard(admin);
			break;

		default:
			System.out.println("Invalid choice");

		}
	}
}
