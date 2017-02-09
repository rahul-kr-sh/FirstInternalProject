package com.MMT.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import com.MMT.dao.DbConnection.*;
import com.MMT.bean.HotelRoom;
import com.MMT.bean.Promotion;

public class PromotionDaoImplMMT implements PromotionDaoMMT {

	Connection con = null;

	@Override
	public int insertPromotion(Promotion p) throws SQLException, ClassNotFoundException, IOException {
		int row;
		con = DbConnection.dbConnection();

		

		con=DbConnection.dbConnection();
		PreparedStatement pst=con.prepareStatement("insert into promotion values(?,?,?,?,?,?)");
		
		pst.setString(1, p.getPromotionId());
		pst.setString(2, p.getPromotionName());
		pst.setDouble(3, p.getPromotionDiscount());
		pst.setString(4, p.getPromotionExpiryDate());
		pst.setDouble(5, p.getPromotionMinRequiredAmount());
		pst.setString(6,p.getPromotionType());
		row=pst.executeUpdate();
		return row;

	}

	@Override
	public int deletePromotion(String promotionId) throws SQLException, ClassNotFoundException, IOException {
		con = DbConnection.dbConnection();
		Statement stmt = con.createStatement();
		int rows = stmt.executeUpdate("delete from PROMOTION where PROMOTIONID =" + promotionId);
		if (rows > 0) {
			con.close();
			return rows;
		} else {
			con.close();
			return 0;
		}
	}

	@Override
	public int updatePromotion(String pId, Promotion newp) throws SQLException, ClassNotFoundException, IOException {
		con = DbConnection.dbConnection();
		String promotionId = newp.getPromotionId();
		String promotionName = newp.getPromotionName();
		double promotionDiscount = newp.getPromotionDiscount();
		String promotionExp = newp.getPromotionExpiryDate();
		double promotionReqAmt = newp.getPromotionMinRequiredAmount();
		String promotionType = newp.getPromotionType();
		Statement stmt = con.createStatement();
		int rows = stmt.executeUpdate("update PROMOTION set PROMO" + "TIONID= '" + promotionId + "' ,PROMOTIONNAME ='"
				+ promotionName + "',PROMOTIONDI" + "SCOUNT='" + promotionDiscount + "' ,PROMOTIONEXPIRYDATE='"
				+ promotionExp + "',PROMOTIONMINREQ" + "UIREDAMOUNT='" + promotionReqAmt + "',PROMOTIONTYPE ='"
				+ promotionType + "' where PROMOTIONID=" + promotionId);
		if (rows > 0) {
			con.close();
			return rows;
		} else {
			con.close();
			return 0;
		}
	}

	@Override
	public ArrayList<Promotion> displayPromotion() throws SQLException, ClassNotFoundException, IOException {
		Promotion pro = new Promotion();
		con = DbConnection.dbConnection();

		ArrayList<Promotion> proList = new ArrayList<Promotion>();

		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("select * from Promotion ");
		while (rs.next()) {
			pro.setPromotionId(rs.getString(1));
			pro.setPromotionName(rs.getString(2));
			pro.setPromotionDiscount(rs.getFloat(3));
			pro.setPromotionExpiryDate(rs.getString(4));
			pro.setPromotionMinRequiredAmount(rs.getFloat(5));
			pro.setPromotionType(rs.getString(6));
			proList.add(pro);
		}
		con.close();
		return proList;
	}

	@Override
	public Promotion searchPromotion(String promotionId) throws SQLException, ClassNotFoundException, IOException {
		con = DbConnection.dbConnection();
		Promotion pro = new Promotion();
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("select * from  Promotion where PROMOTIONID=" + promotionId);
		while (rs.next()) {
			pro.setPromotionId(rs.getString(1));
			pro.setPromotionName(rs.getString(2));
			pro.setPromotionDiscount(rs.getFloat(3));
			pro.setPromotionExpiryDate(rs.getString(4));
			pro.setPromotionMinRequiredAmount(rs.getFloat(5));
			pro.setPromotionType(rs.getString(6));
			con.close();
			return pro;
		}
		return null;
	}

	@Override
	public ArrayList<Promotion> displayPromotion(String promotionType)
			throws SQLException, ClassNotFoundException, IOException {
		con = DbConnection.dbConnection();
		Promotion pro = new Promotion();
		ArrayList<Promotion> proList = new ArrayList<Promotion>();
		PreparedStatement pst;
		pst = con.prepareStatement("select * from  Promotion where promotionType=?");
		pst.setString(1, promotionType);
		ResultSet rs = pst.executeQuery();

		while (rs.next()) {
			pro.setPromotionId(rs.getString(1));
			pro.setPromotionName(rs.getString(2));
			pro.setPromotionDiscount(rs.getFloat(3));
			pro.setPromotionExpiryDate(rs.getString(4));
			pro.setPromotionMinRequiredAmount(rs.getFloat(5));
			pro.setPromotionType(rs.getString(6));
			proList.add(pro);
		}
		con.close();
		return proList;
	}

}
