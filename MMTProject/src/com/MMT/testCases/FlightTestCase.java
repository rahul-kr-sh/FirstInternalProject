
	package com.MMT.testCases;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.MMT.bean.Flight;
//priyanka test
import com.MMT.dao.FlightDaoImplMMT;

public class FlightTestCase {

	
	FlightDaoImplMMT fdao=null;
	Flight flig=null;
	Flight flig1=null;
	

	@Before
	public void setUp() throws Exception {
		fdao=new FlightDaoImplMMT();
		flig=new Flight("AirIndia", "FLY2001","Delhi","Indore","3:00pm","5:00pm",6700.00,20);
		flig1=new Flight("Indigo", "FLY2001", "Mumbai", "Delhi", "12:00pm", "2:30pm", 5600.00, 23);
	
	}
	
	
	@After
	public void tearDown() throws Exception{
		fdao=null;
		flig=null;
	}
	
	@Test(expected=SQLException.class)
	public void testInsertFlight() throws SQLException, Exception {
		//System.out.println("insert");
		assertEquals(1,fdao.insertFlight(flig));
	}

	@Test(expected=SQLException.class)
	public void testupdateFlight() throws SQLException, ClassNotFoundException {
		assertEquals(1,fdao.updateFlight("FLY200", flig));
	
	}
	
	

	@Test(expected=SQLException.class)
	public void testdisplayFlight() throws SQLException, ClassNotFoundException {
		//System.out.println("dispaly");
		fdao.insertFlight(flig);
		ArrayList<Flight> flightList =fdao.displayFlight();
		assertEquals(1,flightList.size());
	}
	
	@Test(expected=SQLException.class)
	public void testsearchFlight() throws SQLException, ClassNotFoundException {
		fdao.insertFlight(flig);
		assertEquals(flig,fdao.searchFlight("FLY2001"));
	
	}
	
	@Test(expected=SQLException.class)
	public void testdeleteFlight() throws SQLException, ClassNotFoundException {
		assertEquals(1,fdao.deleteFlight("FLY200"));
		
}
}



	