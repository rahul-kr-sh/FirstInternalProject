package com.MMT.menu;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import com.MMT.bean.Flight;
import com.MMT.bean.FlightBooking;
import com.MMT.bean.Hotel;
import com.MMT.bean.HotelBooking;
import com.MMT.bean.HotelRoom;
import com.MMT.bean.Promotion;
import com.MMT.bean.User;
import com.MMT.bl.FlightBookingBlMMT;
import com.MMT.bl.HotelBlMMT;
import com.MMT.bl.PromotionBlMMT;
import com.MMT.bl.UserBlMMT;
import com.MMT.bl.WalletBlMMT;
import com.MMT.dao.HotelDaoImplMMT;

public class UserDashboard {
	FlightBookingBlMMT flightBookingBL = new FlightBookingBlMMT();
	PromotionBlMMT promotionBL = new PromotionBlMMT();
	WalletBlMMT walletBL = new WalletBlMMT();
	Scanner sc = new Scanner(System.in);
	HotelBlMMT hotelBL = new HotelBlMMT();
	UserBlMMT userBL = new UserBlMMT();

	public void showDashboard(User user) throws ClassNotFoundException, SQLException, IOException {
		System.out.println("-------------User Dashboard-----------");
		System.out.println("Welcome " + user.getUserName() + "!!");
		System.out.println("1.Search Flight");
		System.out.println("2. Search Hotel");
		System.out.println("3. View Past Flight Bookings");
		System.out.println("4. View Past Hotel Bookings");
		System.out.println("5. Add Money to Wallet");
		System.out.println("6. Show User Profile");
		System.out.println("7. Logout");
		// Comment
		System.out.println("Enter a choice: ");
		int input = sc.nextInt();

		switch (input) {
		case 1:
			System.out.println("Enter Source:");
			String source = sc.next().toLowerCase();
			System.out.println("Enter Destination:");
			String destination = sc.next().toLowerCase();
			ArrayList<Flight> fList = null;
			LinkedHashMap<Integer, Flight> flightMap = new LinkedHashMap<Integer, Flight>();
			try {
				try {
					fList = flightBookingBL.searchFlight(source, destination);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
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
			System.out.println("Total Amount to be paid:" + cartValue);
			System.out.println("Press 1 to See Additional Offers!!");
			int choice = sc.nextInt();
			if (choice == 1) {
				boolean paymentStatus = false;
				ArrayList<Promotion> promo = null;
				try {
					promo = promotionBL.displayPromotion("FLIGHT");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
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

				float valueAfterPromotion = promotionBL.applyPromotion(pPicked, user.getUserId(), (float) cartValue);
				float amountShort = 0;
				try {
					amountShort = valueAfterPromotion - walletBL.walletBalance(user.getUserId());
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
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
							walletBL.addWalletMoney(user.getUserId(), amount);
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (ClassNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						try {
							paymentStatus = walletBL.subtractWalletMoney(user.getUserId(),
									(double) valueAfterPromotion);
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (ClassNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						System.out.println("Payment Done!!! ");
						System.out.println("Confirming Flight Booking!!");

					} else if (ch1 == 2) {
						System.out.println("Booking Cancelled due to insufficient funds!!");
						System.out.println("Booking Cancelled!!");
						paymentStatus = false;
						showDashboard(user);

					} else {
						System.out.println("Invalid Input");
						paymentStatus = false;
						showDashboard(user);
					}

				} else {
					try {
						paymentStatus = walletBL.subtractWalletMoney(user.getUserId(), (double) valueAfterPromotion);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println("Payment Done!!! ");
					System.out.println("Confirming Flight Booking!!");
				}

				FlightBooking fb = null;
				try {
					try {
						fb = flightBookingBL.bookFlight(user.getUserId(), fpicked.getFlightId(), source, destination,
								seats);
						System.out.println(fb);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
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
					showDashboard(user);
				} else if (fb == null) {
					System.out.println("Sorry !! Booking Failed");
					showDashboard(user);
				}
			}

			break;

		case 2:
			// hotelDisplay();
			System.out.println("Enter Location:");
			String loc = sc.next();
			ArrayList<Hotel> hList = new ArrayList<>();
			LinkedHashMap<Integer, Hotel> hotelMap = new LinkedHashMap<Integer, Hotel>();
			try {
				hList = hotelBL.searchHotel1(loc);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
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
				arl = hotelBL.displayAvailHotelRoom(hpicked.getHotelId());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
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
				pickedRoom = hotelBL.searchHotelRoom(hpicked.getHotelId(), rno);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			float cartValue1 = (float) (pickedRoom.getHotelRoomPrice() * duration);
			System.out.println("Total Price to be paid: " + cartValue1);

			System.out.println("Press 1 to view Additional Offers!!");
			int choice1 = sc.nextInt();
			if (choice1 == 1) {
				boolean paymentStatus = false;
				ArrayList<Promotion> promo = null;

				try {
					promo = promotionBL.displayPromotion("HOTEL");
					//System.out.println(promo);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				LinkedHashMap<Integer, Promotion> promoMap = new LinkedHashMap<Integer, Promotion>();
				// System.out.println("Printing");
				int j = 1;
				for (Promotion pindex : promo) {
					promoMap.put(j++, pindex);
				}

				j = 1;
				for (Promotion p : promo) {
					// System.out.println("sdasuidhuiashd");
					System.out.println(j++ + ":" + p);
				}

				System.out.println("Pick a Promo Code!!");

				int promoindex = sc.nextInt();

				Promotion pPicked = promoMap.get(promoindex);

				float valueAfterPromotion = promotionBL.applyPromotion(pPicked, user.getUserId(), (float) cartValue1);
				float amountShort = 0;
				try {
					amountShort = valueAfterPromotion - walletBL.walletBalance(user.getUserId());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
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
							walletBL.addWalletMoney(user.getUserId(), amount);
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (ClassNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						try {
							paymentStatus = walletBL.subtractWalletMoney(user.getUserId(),
									(double) valueAfterPromotion);
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (ClassNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						System.out.println("Payment Done!!! ");
						System.out.println("Confirming HotelRoom Booking!!");

					} else if (ch1 == 2) {
						System.out.println("Booking Cancelled due to insufficient funds!!");
						System.out.println("Booking Cancelled!!");
						paymentStatus = false;
						showDashboard(user);

					} else {
						System.out.println("Invalid Input");
						paymentStatus = false;
						showDashboard(user);
					}

				} else {
					try {
						paymentStatus = walletBL.subtractWalletMoney(user.getUserId(), (double) valueAfterPromotion);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println("Payment Done!!! ");
					System.out.println("Confirming Hotel Booking!!");
				}

				HotelBooking hb = null;
				try {
					try {
						hb = hotelBL.bookHotel(user.getUserId(), hpicked.getHotelId(), rno, din, dout);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						showDashboard(user);
					}
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					showDashboard(user);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					showDashboard(user);
				}

				if ((paymentStatus) && (hb != null)) {
					System.out.println("Hotel Booking Done");
					System.out.println("Your Booking ID is: " + hb.getHotelBookingId());
					showDashboard(user);
				} else if (hb == null) {
					System.out.println("Sorry !! Booking Failed");
					showDashboard(user);
				}
			}
			// System.out.println("Total Price: "+);
			break;
		case 3:
			System.out.println("Your Past Flight Bookings are------");
			ArrayList<FlightBooking> fb = null;

			try {
				try {
					fb = userBL.pastFbooking(user.getUserId());
					System.out.println(fb);
					showDashboard(user);

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// flightBookingDisplay();
			break;
		case 4:
			System.out.println("Your Past Hotel Bookings are------");
			ArrayList<HotelBooking> hb = null;
			try {
				hb = userBL.pastHbooking(user.getUserId());
				System.out.println(hb);
				showDashboard(user);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// hotelBookingDisplay();
			break;
		case 5:
			try {
				System.out.println("Your current wallet Balance is " + walletBL.walletBalance(user.getUserId()));
				// showDashboard(user);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			System.out.println("Enter Amount you want to add to wallet:");
			double amt = sc.nextDouble();
			boolean flag = false;
			try {
				flag = walletBL.addWalletMoney(user.getUserId(), amt);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (flag) {
				System.out.println("Money added to wallet");
				try {
					int bal = (int) walletBL.walletBalance(user.getUserId());
					System.out.println("Your updated wallet balance is " + bal);
					showDashboard(user);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				System.out.println("Some problem accoured, Money not added");
			}
			// addMoneyDisplay();
			break;

		case 6:
			System.out.println(userBL.displayUserProfile(user.getUserId()));
			System.out.println("Aailable Balance is: " + userBL.userWalletBalance(user.getUserId()));
			showDashboard(user);

		case 7:
			System.out.println("Successfully logged out!!!");
			HomePage hp = new HomePage();
			try {
				hp.homePageMenu();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		default:
			System.out.println("Invalid Input!!");
			break;
		}
	}

}
