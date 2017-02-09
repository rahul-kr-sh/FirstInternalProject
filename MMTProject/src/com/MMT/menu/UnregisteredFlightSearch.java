package com.MMT.menu;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Scanner;
import com.MMT.bean.Flight;
import com.MMT.bean.FlightBooking;
import com.MMT.bean.Promotion;
import com.MMT.bean.User;
import com.MMT.bl.FlightBookingBlMMT;
import com.MMT.bl.PromotionBlMMT;
import com.MMT.bl.UserBlMMT;
import com.MMT.bl.WalletBlMMT;

public class UnregisteredFlightSearch {
	FlightBookingBlMMT fbl = new FlightBookingBlMMT();
	UserBlMMT Us=new UserBlMMT();
	HomePage HP=new HomePage();
	PromotionBlMMT Pbl = new PromotionBlMMT();
	WalletBlMMT Wbl = new WalletBlMMT();
	UserDashboard Udb=new UserDashboard();
	Scanner sc=new Scanner(System.in);
	public void showDashboard(){
		User user=null;
		System.out.println("Enter Source:");
		String source = sc.next();
		System.out.println("Enter Destination:");
		String destination = sc.next();
		ArrayList<Flight> fList = null;
		LinkedHashMap<Integer, Flight> flightMap = new LinkedHashMap<Integer, Flight>();
		try {
			fList = fbl.searchFlight(source, destination);
		} catch (ClassNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		int i = 1;
		for (Flight findex : fList) {
			flightMap.put(i++, findex);
		}
		i = 1;
		for (Flight f : fList) {
			System.out.println(i++ + ":" + f);
		}
		System.out.println("Pick a flight :");
		int v = sc.nextInt();

		Flight fpicked = flightMap.get(v);
		System.out.println("Enter No of seats:");
		int seats = sc.nextInt();
		double cartValue = fpicked.getFlightTicketPrice() * seats;
		System.out.println("Total Amount to be paid:" +cartValue);
		
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
		System.out.println("Press 1 to See Additional Offers!!");
		int choice = sc.nextInt();
		if (choice == 1) {
			boolean paymentStatus = false;
			ArrayList<Promotion> promo = null;
			try {
				promo = Pbl.displayPromotion("FLIGHT");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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

			float valueAfterPromotion = Pbl.applyPromotion(pPicked, user.getUserId(), (float) cartValue);
			float amountShort = 0;
			try {
				amountShort = valueAfterPromotion - Wbl.walletBalance(user.getUserId());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
					System.out.println("Confirming Flight Booking!!");

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
				System.out.println("Confirming Flight Booking!!");
				
			}

			FlightBooking fb = null;
			try {
				fb = fbl.bookFlight(user.getUserId(), fpicked.getFlightId(), source, destination, seats);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if ((paymentStatus) && (fb != null)) {
				System.out.println("Flight Booking Done");
				System.out.println("Your Booking ID is: " + fb.getFlightBookingId());
				Udb.showDashboard(user);
			} else if (fb == null) {
				System.out.println("Sorry !! Booking Failed");
				Udb.showDashboard(user);
			}
		}

	}
}
