package com.MMT.menu;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import com.MMT.bean.Hotel;
import com.MMT.bean.HotelBooking;
import com.MMT.bean.HotelRoom;
import com.MMT.bean.Promotion;
import com.MMT.bean.User;
import com.MMT.bl.HotelBlMMT;
import com.MMT.bl.PromotionBlMMT;
import com.MMT.bl.UserBlMMT;
import com.MMT.bl.WalletBlMMT;

public class UnregisteredHotelSearch {
	Scanner sc=new Scanner(System.in);
	UserBlMMT Us=new UserBlMMT();
	HotelBlMMT Hbl = new HotelBlMMT();
	UserBlMMT Ubl = new UserBlMMT();
	PromotionBlMMT Pbl = new PromotionBlMMT();
	WalletBlMMT Wbl = new WalletBlMMT();
	UserDashboard Udb=new UserDashboard();
	HomePage HP=new HomePage();
	public void showDashboard(){
		User user=null;
		System.out.println("Enter Location:");
		String loc = sc.next();
		ArrayList<Hotel> hList = new ArrayList<>();
		LinkedHashMap<Integer, Hotel> hotelMap = new LinkedHashMap<Integer, Hotel>();
		try {
			hList = Hbl.searchHotel1(loc);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int i1 = 1;
		for (Hotel hindex : hList) {
			hotelMap.put(i1++, hindex);
		}
		i1 = 1;
		for (Hotel h : hList) {
			System.out.println(i1++ + ":" + h);
		}
		System.out.println("Pick a Hotel :");
		int m = sc.nextInt();

		Hotel hpicked = hotelMap.get(m);
		ArrayList<HotelRoom> arl = null;
		try {
			arl = Hbl.displayAvailHotelRoom(hpicked.getHotelId());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Enter RoomID:");
		int rno = sc.nextInt();

		System.out.println("Enter Check In Date in DD-MM-YYYY");
		String date = sc.next();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
		Date din = null;
		try {
			// Parsing the String
			din = dateFormat.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("Enter Check Out Date in DD-MM-YYYY");
		String date1 = sc.next();
		SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd-MMM-yyyy");
		Date dout = null;
		try {
			// Parsing the String
			dout = dateFormat1.parse(date1);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		long diff = dout.getTime() - din.getTime();
		int duration = (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);

		HotelRoom pickedRoom = null;
		try {
			pickedRoom = Hbl.searchHotelRoom(hpicked.getHotelId(), rno);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		float cartValue1 = (float) (pickedRoom.getHotelRoomPrice() * duration);
		System.out.println("Total Price to be paid: " + cartValue1);
		
		System.out.println("Press 1 forLogin to Proceed Further!!");
		System.out.println("Press 2 to go back");
		int in=sc.nextInt();
		if(in==1){
			System.out.println("Enter User Name:");
			String name=sc.next();
			System.out.println("Enter Password: ");
			String pass=sc.next();
			
			if(name==null){
				System.out.println("THE USERNAME FIELD IS BLANK!!!");
			}
			else if(pass==null){
				System.out.println("THE PASSWORD FIELD IS BLANK!!!");
			} else
				try {
					if(Us.checkLogin(name, pass)!=null){
						System.out.println(" Successful User Login!!");
						user = Us.checkLogin(name, pass);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		else if(in==2){
			HP.HomePageMenu();
		}
		else {
			HP.HomePageMenu();
		}
		
		System.out.println("Press 1 to view Additional Offers!!");
		int choice1 = sc.nextInt();
		if (choice1 == 1) {
			boolean paymentStatus = false;
			ArrayList<Promotion> promo = null;

			try {
				promo = Pbl.displayPromotion("Hotel");
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			LinkedHashMap<Integer, Promotion> promoMap = new LinkedHashMap<Integer, Promotion>();
			int j = 1;
			for (Promotion pindex : promo) {
				promoMap.put(j++, pindex);
			}
			j = 1;
			for (Promotion p : promo) {
				System.out.println(j++ + ":" + p);
			}

			System.out.println("Pick a Promo Code!!");
			int promoindex = sc.nextInt();

			Promotion pPicked = promoMap.get(promoindex);

			float valueAfterPromotion = Pbl.applyPromotion(pPicked, user.getUserId(), (float) cartValue1);
			float amountShort = 0;
			try {
				amountShort = valueAfterPromotion - Wbl.walletBalance(user.getUserId());
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if (amountShort > 0) {
				System.out.println("Insufficient Funds!!");
				System.out.println("Add Rs" + amountShort + " to Wallet!!");
				System.out.println("1. Yes");
				System.out.println("2. No");
				int ch1 = sc.nextInt();
				if (ch1 == 1) {
					System.out.println("Enter amount you want to add to wallet!!");
					double amount = sc.nextDouble();
					try {
						Wbl.addWalletMoney(user.getUserId(), amount);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					try {
						paymentStatus = Wbl.subtractWalletMoney(user.getUserId(), (double) valueAfterPromotion);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println("Payment Done!!! ");
					System.out.println("Confirming HotelRoom Booking!!");

				} else if (ch1 == 2) {
					System.out.println("Booking Cancelled due to insufficient funds!!");
					System.out.println("Booking Cancelled!!");
					paymentStatus = false;
					Udb.showDashboard(user);

				} else {
					System.out.println("Invalid Input");
					paymentStatus = false;
					Udb.showDashboard(user);
				}

			} else {
				try {
					paymentStatus = Wbl.subtractWalletMoney(user.getUserId(), (double) valueAfterPromotion);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("Payment Done!!! ");
				System.out.println("Confirming Hotel Booking!!");
			}

			HotelBooking hb = null;
			try {
				hb = Hbl.bookHotel(user.getUserId(), hpicked.getHotelId(), rno, din, dout);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if ((paymentStatus) && (hb != null)) {
				System.out.println("Hotel Booking Done");
				System.out.println("Your Booking ID is: " + hb.getHotelBookingId());
				Udb.showDashboard(user);
			} else if (hb == null) {
				System.out.println("Sorry !! Booking Failed");
				Udb.showDashboard(user);
			}
		}
		
	}
}