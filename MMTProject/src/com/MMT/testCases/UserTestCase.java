package com.MMT.testCases;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.MMT.bean.User;
import com.MMT.dao.UserDaoImplMMT;

public class UserTestCase {
	UserDaoImplMMT userDao;
	User user;
	
	@Before
	public void setUp() throws Exception {
		userDao=new UserDaoImplMMT();
		user=new User("ui1","un1",11,"un@1","add1","up1");
		userDao.insert(new User("ui2","un2",22,"un@2","add2","up2"));
		userDao.insert(new User("ui3","un3",33,"un@3","add3","up3"));
		userDao.insert(new User("ui4","un4",44,"un@4","add4","up4"));	
	}

	@After
	public void tearDown() throws Exception {
		userDao.delete("ui1");
		userDao.delete("ui2");
		userDao.delete("ui3");
		userDao.delete("ui4");
	}

	@Test(expected=SQLException.class)
	public void insertTest() throws SQLException {
		assertEquals(1, userDao.insert(user));
		
	}
	@Test()
	public void searchTest(){
		assertEquals(user, "ui4");
	}
	

}
