package com.MMT.testCases;

import static org.junit.Assert.*;

import java.io.IOException;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.MMT.bean.User;
import com.MMT.dao.UserDaoImplMMT;

public class UserTestCase {
	UserDaoImplMMT userDao;
	User user1;
	User user2;
	
	@Before
	public void setUp() throws Exception {
		userDao=new UserDaoImplMMT();
		user1=new User("ui1","un1",11,"un@1","add1","up1");
		user2=new User("ui2","un2",22,"un@2","add2","up2");
			
	}

	@After
	public void tearDown() throws Exception {
		
		user1=null;
		user2=null;
		userDao=null;
		
	
	}

	@Test
	public void insertTest() throws SQLException, ClassNotFoundException, IOException {
		assertEquals(1, userDao.insert(user1));
		userDao.delete(user1.getUserId());
		
		
	}
	@Test(expected=SQLException.class)
	public void deleteTest() throws SQLException, ClassNotFoundException, IOException{
		userDao.insert(user2);
		assertEquals(1, userDao.delete(user2.getUserId()));
		
	}
	
	@Test(expected=SQLException.class)
	public void updateTest() throws SQLException, ClassNotFoundException, IOException{
		userDao.insert(user2);
		assertEquals(1,userDao.update("ui2",new User("ui2","un5",55,"un@5","add5","up5")));
		userDao.delete("ui2");
		
	}
	
	@Test(expected=SQLException.class)
	public void searchTest() throws SQLException, ClassNotFoundException, IOException{
		userDao.insert(user2);
		assertEquals(user1, userDao.search("ui2"));
		userDao.delete("ui2");
		
	}
	
	@Test(expected=SQLException.class)
	public void displayAllTest() throws SQLException, ClassNotFoundException, IOException{
		userDao.insert(user2);
		assertEquals(1, userDao.displayAll().size());
		userDao.delete("ui2");
		
	}

}
