package com.MMT.dao;

import java.sql.Connection;
import java.sql.ResultSet;
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
		Statement stmt=con.createStatement();
		int rows=stmt.executeUpdate("delete from PROMOTION where PROMOTIONID ="+promotionId);
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
	public int updatePromotion(String pId, Promotion newp) throws SQLException{
		con=DbConnection.dbConnection();
		String promotionId=newp.getPromotionId();
		String promotionName=newp.getPromotionName();
		float promotionDiscount=newp.getPromotionDiscount();
		Date promotionExp=newp.getPromotionExpiryDate();
		float promotionReqAmt=newp.getPromotionMinRequiredAmount();
		String promotionType=newp.getPromotionType();
		Statement stmt=con.createStatement();
		int rows=stmt.executeUpdate("update PROMOTION set PROMO"
				+ "TIONID= '"+promotionId+"' ,PROMOTIONNAME ='"+promotionName+"',PROMOTIONDI"
						+ "SCOUNT='"+promotionDiscount+"' ,PROMOTIONEXPIRYDATE='"+promotionExp+"',PROMOTIONMINREQ"
								+ "UIREDAMOUNT='"+promotionReqAmt+"',PROMOTIONTYPE ='"+promotionType+"' where PROMOTIONID="+promotionId);
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
	public ArrayList<Promotion> displayPromotion()throws SQLException {
		Promotion pro=new Promotion();
		con=DbConnection.dbConnection();
		ArrayList<Promotion> proList=new ArrayList<Promotion>();
		Statement stmt=con.createStatement();
		ResultSet rs=stmt.executeQuery("select * from Promotion ");
		while(rs.next()){
			pro.setPromotionId(rs.getString(1));
			pro.setPromotionName(rs.getString(2));
			pro.setPromotionDiscount(rs.getFloat(3));
			pro.setPromotionExpiryDate(rs.getDate(4));
			pro.setPromotionMinRequiredAmount(rs.getFloat(5));
			pro.setPromotionType(rs.getString(6));
			
		}
		return null;
	}

	@Override
	public Promotion searchPromotion(String promotionId) throws SQLException{
		con=DbConnection.dbConnection();
		
		return null;
	}

}
