package com.MMT.testCases;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.MMT.bean.Promotion;
import com.MMT.dao.PromotionDaoImplMMT;

public class PromotionTestCase {
	PromotionDaoImplMMT pdi=null;
	Promotion pro=null;
	@Before
	public void setUp() throws Exception {
		pdi=new PromotionDaoImplMMT();
		pro=new Promotion("FLY200","200Off",200.00,"20 Feb 2017",1999.50,"FlightType");
	}

	@After
	public void tearDown() throws Exception {
		pdi=null;
		pro=null;
	}

	@Test(expected=SQLException.class)
	public void testInsertPromotion() throws SQLException {
		assertEquals(1,pdi.insertPromotion(pro));
		
	}
	@Test(expected=SQLException.class)
	public void testdeletePromotion() throws SQLException {
		assertEquals(1,pdi.deletePromotion("FLY200"));
		
	}
}
