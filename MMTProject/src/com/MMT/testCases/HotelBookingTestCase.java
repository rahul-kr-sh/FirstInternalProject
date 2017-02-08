package com.MMT.testCases;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
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
	HotelBooking hb;
	Date date,myDate;
	
	@Before
	public void setUp() throws Exception {
		hbdi=new HotelBookingDaoImplMMT();
		date=new Date();
		myDate = DateUtil.addDays(date, 2);
		java.sql.Date sqlDate1 = new java.sql.Date(date.getTime());
		java.sql.Date sqlDate2 = new java.sql.Date(myDate.getTime());
		hb=new HotelBooking("hbId1","hid1","userid1",1,sqlDate1,sqlDate2,2);
	}

	@After
	public void tearDown() throws Exception {
		hbdi=null;
		date=null;
		myDate=null;
		hb=null;
	}

	@Test
	public void testInsertHotelBooking() throws SQLException {
		assertEquals(1, hbdi.insertHotelBooking(hb));
		
	}

}
