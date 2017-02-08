
	package com.MMT.testCases;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.MMT.bean.Flight;

import com.MMT.dao.FlightDaoImplMMT;

public class FlightTestCase {

	
	FlightDaoImplMMT fdao=null;
	Flight flig=null;
	Flight flig1=null;
	

	@Before
	public void setUp() throws Exception {
		fdao=new FlightDaoImplMMT();
		flig=new Flight("AirIndia", "FLY2001", "Delhi", "Indore", "9:00 am", "12:00 pm", 6700.00, 20);
		flig1=new Flight("Indigo", "FLY1110", "mumbai", "Delhi", "3:00 pm", "5:50 pm", 5600.00, 23);
	
	}
	
	
	@After
	public void tearDown() throws Exception {
		fdao=null;
		flig=null;
	}
	
	@Test(expected=SQLException.class)
	public void testInsertFlight() throws SQLException, Exception {
		assertEquals(1,fdao.insertFlight(flig));
	}

	@Test(expected=SQLException.class)
	public void testupdateFlight() throws SQLException, ClassNotFoundException {
		assertEquals(1,fdao.updateFlight("FLY200", flig));
	
	}
	
	

	@Test(expected=SQLException.class)
	public void testdisplayFlight() throws SQLException, ClassNotFoundException {
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



	