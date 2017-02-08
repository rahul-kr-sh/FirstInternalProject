package com.MMT.menu;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Scanner;

import com.MMT.bean.Flight;
import com.MMT.bean.FlightBooking;
import com.MMT.bean.Hotel;
import com.MMT.bean.Promotion;
import com.MMT.bean.User;
import com.MMT.bl.FlightBookingBlMMT;
import com.MMT.bl.PromotionBlMMT;
import com.MMT.bl.WalletBlMMT;
import com.MMT.dao.HotelDaoImplMMT;

public class UserDashboard {
	FlightBookingBlMMT fbl = new FlightBookingBlMMT();
	HotelDaoImplMMT hbl = new HotelDaoImplMMT();
	PromotionBlMMT Pbl = new PromotionBlMMT();
	WalletBlMMT Wbl = new WalletBlMMT();
	Scanner sc = new Scanner(System.in);

	public void showDashboard(User user) {
		System.out.println("-------------User Dashboard-----------");
		System.out.println("Welcome " + user.getUserName() + "!!");
		System.out.println("1.Search Flight");
		System.out.println("2. Search Hotel");
		System.out.println("3. View Past Flight Bookings");
		System.out.println("4. View Past Hotel Bookings");
		System.out.println("5. Add Money to Wallet");
		System.out.println("6. Logout");

		System.out.println("Enter a choice: ");
		int input = sc.nextInt();

		switch (input) {
		case 1:
			System.out.println("Enter Source:");
			String source = sc.next();
			System.out.println("Enter Destination:");
			String destination = sc.next();
			ArrayList<Flight> fList;
			LinkedHashMap<Integer, Flight> flightMap = new LinkedHashMap<Integer, Flight>();
			try {
				fList = fbl.searchFlight(source, destination);
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
				System.out.println("Press 1 to See Additional Offers!!");
				int choice = sc.nextInt();
				if (choice == 1) {
					boolean paymentStatus = false;
					ArrayList<Promotion> promo;
					promo = Pbl.displayPromotion("FLIGHT");
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
					float amountShort = valueAfterPromotion - Wbl.walletBalance(user.getUserId());
					if (amountShort > 0) {
						System.out.println("Insufficient Funds!!");
						System.out.println("Add Rs" + amountShort + " to Wallet!!");
						System.out.println("1. Yes");
						System.out.println("2. No");
						int ch1 = sc.nextInt();
						if (ch1 == 1) {
							System.out.println("Enter amount you want to add to wallet!!");
							double amount = sc.nextDouble();
							Wbl.addWalletMoney(user.getUserId(), amount);
							paymentStatus = Wbl.subtractWalletMoney(user.getUserId(), (double) valueAfterPromotion);
							System.out.println("Payment Done!!! ");
							System.out.println("Confirming Flight Booking!!");

						} else if (ch1 == 2) {
							System.out.println("Booking Cancelled due to insufficient funds!!");
							System.out.println("Booking Cancelled!!");
							paymentStatus = false;

						} else {
							System.out.println("Invalid Input");
							paymentStatus = false;
						}

					} else {
						paymentStatus = Wbl.subtractWalletMoney(user.getUserId(), (double) valueAfterPromotion);
						System.out.println("Payment Done!!! ");
						System.out.println("Confirming Flight Booking!!");
					}

					FlightBooking fb;
					fb = fbl.bookFlight(user.getUserId(), fpicked.getFlightId(), source, destination, seats);
					if ((paymentStatus) && (fb != null)) {
						System.out.println("Flight Booking Done");
						System.out.println("Your Booking ID is: " + fb.getFlightBookingId());
					} else if (fb == null) {
						System.out.println("Sorry !! Booking Failed");
					}
				}

			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;

		case 2:
			// hotelDisplay();
			System.out.println("Enter Location:");
			String loc = sc.next();
			ArrayList<Hotel> hList = new ArrayList<>();
			LinkedHashMap<Integer, Hotel> hotelMap = new LinkedHashMap<Integer, Hotel>();
			try {
				hList = hbl.searchHotel1(loc);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			int i = 1;
			for (Hotel hindex : hList) {
				hotelMap.put(i++, hindex);
			}
			i = 1;
			for (Hotel h : hList) {
				System.out.println(i++ + ":" + h);
			}
			System.out.println("Pick a Hotel :");
			int m = sc.nextInt();

			Hotel hpicked = hotelMap.get(m);
			
			break;
		case 3:
			// flightBookingDisplay();
			break;
		case 4:
			// hotelBookingDisplay();
			break;
		case 5:
			// addMoneyDisplay();
			break;
		case 6:
			System.out.println("Successfully logged out!!!");
			HomePage hp = new HomePage();
			hp.HomePageMenu();
			break;
		default:
			System.out.println("Invalid Input!!");
			break;
		}
	}

}
