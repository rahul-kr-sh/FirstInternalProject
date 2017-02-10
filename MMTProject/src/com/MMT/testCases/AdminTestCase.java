package com.MMT.testCases;

import static org.junit.Assert.*;

import java.io.IOException;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
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
	public void testinsertAdmin() throws SQLException, ClassNotFoundException, IOException {
		assertEquals(1,adi.insert(admin)); 
		adi.delete("admin1");
	}
	@Ignore
	@Test
	public void testsearchAdmin() throws SQLException, ClassNotFoundException, IOException {
		adi.insert(admin);
		admin2=adi.search("admin1");
		assertEquals("admin1",admin2.getAdminId());
		admin2=null;
	}
	@Test
	public void testdeleteAdmin() throws SQLException, ClassNotFoundException, IOException {
		adi.insert(admin);
		assertEquals(1,adi.delete("admin1")); 
	}
	@Ignore
	@Test
	public void testupdateAdmin() throws SQLException, ClassNotFoundException, IOException {
		adi.insert(admin);
		admin2=new Admin("admin1","Deepika",1454567891,"deepika@gmail.com","House no.13","admin1");
		assertEquals(1,adi.update("admin1",admin2));
		adi.delete("admin1");
		admin2=null;
	}
}
