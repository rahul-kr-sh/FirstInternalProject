package com.MMT.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import com.MMT.dao.DbConnection.*;
import com.MMT.bean.HotelRoom;
import com.MMT.bean.Promotion;

public class PromotionDaoImplMMT implements PromotionDaoMMT {
	
	Connection con=null;
	@Override
	public int insertPromotion(Promotion p)throws SQLException {
		con=DbConnection.dbConnection();
		String promotionId=p.getPromotionId();
		String promotionName=p.getPromotionName();
		float promotionDiscount=p.getPromotionDiscount();
		Date promotionExp=p.getPromotionExpiryDate();
		float promotionReqAmt=p.getPromotionMinRequiredAmount();
		String promotionType=p.getPromotionType();
		//Query
				Statement stmt=con.createStatement();
				//System.out.println("b4 insert hotel");
				int rows=stmt.executeUpdate("INSERT INTO PROMOTION (PROMOTIONID,PROMOTIONNAME,"
						+ "PROMOTIONDISCOUNT,PROMOTIONEXPIRYDATE,PROMOTIONMINREQ"
						+ "UIREDAMOUNT,PROMO"
						+ "TIONTYPE) VALU"
						+ "ES "
+ "("+promotionId+",'"+promotionName+"','"+promotionDiscount+"','"+promotionExp+"','"+promotionReqAmt+"','"+promotionType+"')");
				//Process Results
				if(rows>0)
				{
					con.close();
					return rows;
				}
				else 
				{	con.close();
				return 0;
				}
				
	
		
	}

	@Override
	public int deletePromotion(String promotionId)throws SQLException {
		con=DbConnection.dbConnection();
		con=null;
		return 0;
	}

	@Override
	public int updatePromotion(String promotionId, Promotion newPromotion) throws SQLException{
		con=DbConnection.dbConnection();
		con=null;
		return 0;
	}

	@Override
	public ArrayList<Promotion> displayPromotion()throws SQLException {
		con=DbConnection.dbConnection();
		con=null;
		return null;
	}

	@Override
	public Promotion searchPromotion(String promotionId) throws SQLException{
		con=DbConnection.dbConnection();
		con=null;
		return null;
	}

}
