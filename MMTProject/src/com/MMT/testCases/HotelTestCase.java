package com.MMT.testCases;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.MMT.bean.Hotel;
import com.MMT.bean.HotelRoom;
import com.MMT.dao.HotelDaoImplMMT;

public class HotelTestCase {
	 HotelDaoImplMMT adi;
	HotelRoom rm1=new HotelRoom("1",1,"Deluxe",3000,"Avail");
	HotelRoom rm2=new HotelRoom("1",2,"Cabana",4000,"Avail");
	HotelRoom rm3=new HotelRoom("1",3,"Studio",5000,"Avail");
	ArrayList<HotelRoom> al=new ArrayList<HotelRoom>();
	Hotel h;
	@Before
	public void setUp() throws Exception {
		al.add(rm1);
		al.add(rm2);
		al.add(rm3);
		h=new Hotel("1","Taj Hotel","Mumbai","5 Star",al); 
		adi=new HotelDaoImplMMT();
	}

	@After
	public void tearDown() throws Exception {
		al=null;
		h=null;
	}

	@Test
	public void testinsertHotel() throws  SQLException {
		assertEquals(1,adi.insertHotel(h));
	}

}
