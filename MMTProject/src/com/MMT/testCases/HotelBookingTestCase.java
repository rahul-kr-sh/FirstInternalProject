package com.MMT.testCases;

import static org.junit.Assert.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.MMT.bean.HotelBooking;
import com.MMT.dao.HotelBookingDaoImplMMT;

class DateUtil
{
    public static Date addDays(Date date, int days)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days); //minus number would decrement the days
        return cal.getTime();/*new java.sql.Date*/
    }
}

public class HotelBookingTestCase {
	HotelBookingDaoImplMMT hbdi;
	ArrayList<HotelBooking> al1,al2;;
	HotelBooking hb,hb2,hb3,hb4,hb5;
	Date date,myDate;
	ArrayList<HotelBooking> alhb,alhb2;
	@Before
	public void setUp() throws Exception {
		hbdi=new HotelBookingDaoImplMMT();
		date=new Date();
		myDate = DateUtil.addDays(date, 2);
		java.sql.Date sqlDate1 = new java.sql.Date(date.getTime());
		java.sql.Date sqlDate2 = new java.sql.Date(myDate.getTime());
		hb=new HotelBooking("hbid1","hid1","userid1",1,sqlDate1,sqlDate2,2);
		hb2=new HotelBooking("hbid2","hid1","userid2",2,sqlDate1,sqlDate2,2);
		hb3=new HotelBooking("hbid3","hid1","userid3",3,sqlDate1,sqlDate2,2);
		hb4=new HotelBooking("hbid4","hid1","userid4",4,sqlDate1,sqlDate2,2);
		hb5=new HotelBooking("hbid5","hid1","userid5",4,sqlDate1,sqlDate2,2);
		al1=new ArrayList<HotelBooking>();
		al2=new ArrayList<HotelBooking>();
		al1.add(hb5);
		alhb=new ArrayList<HotelBooking>();
		alhb2=new ArrayList<HotelBooking>();
	}

	@After
	public void tearDown() throws Exception {
	
		hbdi=null;
		date=null;
		myDate=null;
		hb=null;
		hb2=null;
		hb3=null;
		hb4=null;
		alhb=null;
		
	}
	
	@Test
	public void testInsertHotelBooking() throws SQLException, ClassNotFoundException, IOException {
		assertEquals(1, hbdi.insertHotelBooking(hb));
		//hbdi.cancelHotelBooking("hbid1");
	}
	@Test
	public void testdisplayHotelBooking() throws SQLException, ClassNotFoundException, IOException {
		 hbdi.insertHotelBooking(hb5);
		 al2=hbdi.display();
		 assertEquals(1, al1.size());
	}
	
	@Test
	public void testsearchHotelBooking() throws SQLException, ClassNotFoundException, IOException {
		 hbdi.insertHotelBooking(hb2);
		 alhb.add(hb2);
		alhb2=hbdi.searchHotelBooking("userid2");
		int i=alhb.size();
		 int j=alhb2.size();
		assertEquals(1,i);
		//hbdi.cancelHotelBooking("hbid2");
		
	}
//	@Ignore
//	@Test
//	public void testcancelHotelBooking() throws SQLException {
//		 hbdi.insertHotelBooking(hb3);
//		
//		assertEquals(1, hbdi.cancelHotelBooking("hbid3"));
//	}

}
