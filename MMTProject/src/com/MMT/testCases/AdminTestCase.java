package com.MMT.testCases;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.MMT.bean.Admin;
import com.MMT.dao.AdminDaoImplMMT;

public class AdminTestCase {
	Admin admin,admin2;
	AdminDaoImplMMT adi;
	@Before
	public void setUp() throws Exception {
		admin=new Admin("admin1","Shreyoshi Mahato",1234567891,"shreyoshi@gmail.com","House no.13","admin1");
		adi=new AdminDaoImplMMT();
	}
	@After
	public void tearDown() throws Exception {
		adi=null;
		admin=null;
	}
	@Test
	public void testinsertAdmin() throws SQLException {
		assertEquals(1,adi.insert(admin)); 
	}
	@Test
	public void testsearchAdmin() throws SQLException {
		admin2=adi.search("admin1");
		assertEquals("admin1",admin2.getAdminId());
		admin2=null;
	}
}
