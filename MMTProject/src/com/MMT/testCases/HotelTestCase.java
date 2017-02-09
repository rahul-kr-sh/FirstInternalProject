package com.MMT.testCases;
import static org.junit.Assert.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import com.MMT.bean.Hotel;
import com.MMT.bean.HotelRoom;
import com.MMT.dao.HotelDaoImplMMT;

public class HotelTestCase {
	 HotelDaoImplMMT adi;
	 HotelRoom rm01;
	 HotelRoom rm02;
	 HotelRoom rm03;
	 HotelRoom rm11;
	 HotelRoom rm12;
	 HotelRoom rm13;
	 ArrayList<HotelRoom> al1;
	 ArrayList<HotelRoom> al2;
	Hotel h,newHotel,hCheck;
	@Before
	public void setUp() throws Exception {
		 rm01=new HotelRoom("1",1,"Deluxe",3000,"Avail");
		rm02=new HotelRoom("1",2,"Cabana",4000,"Avail");
		 rm03=new HotelRoom("1",3,"Studio",5000,"Avail");
		 rm11=new HotelRoom("2",1,"Deluxe",3000,"Avail");
		 rm12=new HotelRoom("2",2,"Cabana",4000,"Avail");
		 rm13=new HotelRoom("2",3,"Studio",5000,"Avail");
		al1=new ArrayList<HotelRoom>();
		 al2=new ArrayList<HotelRoom>();
		al1.add(rm01);
		al1.add(rm02);
		al1.add(rm03);
		al2.add(rm11);
		al2.add(rm12);
		al2.add(rm13);
		h=new Hotel("1","Taj Hotel","Mumbai","5 Star",al1); 
		newHotel=new Hotel("2","Ramada","Jaipur","5 Star",al2); 
		adi=new HotelDaoImplMMT();
	}
	@After
	public void tearDown() throws Exception {
		al1=null;
		al2=null;
		rm01=null;
		rm02=null;
		rm03=null;
		rm11=null;
		rm12=null;
		rm13=null;
		h=null;
	}

	@Test
	public void testinsertHotel() throws  SQLException, ClassNotFoundException, IOException {
		assertEquals(1,adi.insertHotel(h));
		adi.deleteHotel("1");
	}
	@Test
	public void testdeleteHotel() throws  SQLException, ClassNotFoundException, IOException {
		adi.insertHotel(h);
		assertEquals(1,adi.deleteHotel("1"));
		
	}
	@Test
	public void testupdateHotel() throws  SQLException, ClassNotFoundException, IOException {
		adi.insertHotel(h);
		assertEquals(1,adi.updateHotel("1", newHotel));
		adi.deleteHotel("1");
		adi.deleteHotel("2");
	}
	@Test
	public void testdisplayHotel() throws  SQLException, ClassNotFoundException, IOException {
		adi.insertHotel(h);
		assertEquals(1, adi.displayHotel().size());
		adi.deleteHotel("1");
	}
	@Ignore
	@Test
	public void testsearchHotel() throws  SQLException, ClassNotFoundException, IOException {
		adi.insertHotel(newHotel);
		hCheck=adi.searchHotel("2");
		assertEquals(2,hCheck.getHotelId());
		adi.deleteHotel("2");
	}
}
