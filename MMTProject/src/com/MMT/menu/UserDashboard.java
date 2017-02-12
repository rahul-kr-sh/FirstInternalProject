package com.MMT.menu;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
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
	Scanner sc;
	HotelBlMMT hotelBL = new HotelBlMMT();
	UserBlMMT userBL = new UserBlMMT();

	public void showDashboard(User user) throws ClassNotFoundException, SQLException, IOException {
	
		System.out.println("-------------User Dashboard-----------");
		System.out.println("Welcome " + user.getUserName() + "!!");
		System.out.println("1. Search Flight");
		System.out.println("2. Search Hotel");
		System.out.println("3. View Past Flight Bookings");
		System.out.println("4. View Past Hotel Bookings");
		System.out.println("5. Add Money to Wallet");
		System.out.println("6. Show User Profile");
		System.out.println("7. Logout");
		//Scanner sc=new Scanner(System.in);
		int input=0;
		sc = new Scanner(System.in);
		System.out.println("Enter a choice");
		try {
			
			 input=sc.nextInt();
		} catch (InputMismatchException e) {
			System.out.println("Enter Integer between 1 to 7");
			
		}
		
		
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
					if(fList.isEmpty()){
						System.out.println("No Flights available from "+source+" to "+destination);
						showDashboard(user);
					}
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
				System.out.println(" Flight Details " + i);
				System.out.println("");
				System.out.println(" Flight ID: " + f.getFlightId());
				System.out.println(" Flight Company Name: " + f.getFlightCompanyName());
				System.out.println(" Flight Source: " + f.getFlightSource());
				System.out.println(" Flight Destination: " + f.getFlightDestination());
				System.out.println(" Flight Departure Time: " + f.getFlightDepartureTime());
				System.out.println(" Flight Arrival Time: " + f.getFlightArrivalTime());
				System.out.println(" Flight Ticket Price: " + f.getFlightTicketPrice());
				System.out.println(" Flight Available Seats: " + f.getAvailableSeats());
				System.out.println("----------------------------");
				i++;
			}
			System.out.println("Pick a flight :");
			int v = sc.nextInt();

			Flight fpicked = flightMap.get(v);
			if(fpicked==null)
			{
				System.out.println("Choose from available options");
				showDashboard(user);
			}
			System.out.println("Enter No of seats:");
			int seats = sc.nextInt();
			double cartValue = fpicked.getFlightTicketPrice() * seats;
			System.out.println("Total Amount to be paid:" + cartValue);
			System.out.println("Press 1 to See Additional Offers!!");
			int choice = sc.nextInt();
//			if(choice!=1)
//				showDashboard(user);
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
					System.out.println("Promotion " + j);

					System.out.println("Promotion Name : " + p.getPromotionName());
					System.out.println("Promotion Type : " + p.getPromotionType());
					System.out.println("Promotion Discount% : " + p.getPromotionDiscount());
					System.out.println("Promotion Expiry Date : " + p.getPromotionExpiryDate());
					j++;
				}
				System.out.println("");
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
						System.out.println("Payment Done!!! \n");
						System.out.println("Confirming Flight Booking!!\n");

					} else if (ch1 == 2) {
						System.out.println("Booking Cancelled due to insufficient funds!!\n");
						System.out.println("Booking Cancelled!!\n");
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
					System.out.println("Payment Done!!!\n ");
					System.out.println("Confirming Flight Booking!!\n");
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
			String loc = sc.next().toLowerCase();
			ArrayList<Hotel> hList = new ArrayList<Hotel>();
			LinkedHashMap<Integer, Hotel> hotelMap = new LinkedHashMap<Integer, Hotel>();
			try {
				hList = hotelBL.searchHotel1(loc);
				if(hList.isEmpty()){
					System.out.println("No Hotels in "+loc);
					showDashboard(user);
				}
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
//			i1 = 1;
//			for (Hotel h : hList) {
//				System.out.println(i1++ + ":" + h);
//			}
			i1 = 1;
			
			System.out.println("------------Hotel Details------------");
				for(Hotel hotelNew:hList){
					System.out.println(i1+" Hotel ");
					System.out.println("Hotel ID : "+hotelNew.getHotelId());
					System.out.println("Hotel name : "+hotelNew.getHotelName());
					System.out.println("Hotel location : "+hotelNew.getHotelLocation());
					System.out.println("Hotel information : "+hotelNew.getHotelInfo());
					System.out.println("-----------Room Details-----------");
					
					for(HotelRoom hotelRoom:hotelNew.getHotelRoom()){
						System.out.println("Hotel room No : "+hotelRoom.getHotelRoomNo());
						System.out.println("Hotel room type : "+hotelRoom.getHotelRoomType());
						System.out.println("Hotel room price : "+hotelRoom.getHotelRoomPrice());
						System.out.println("Hotel room status : "+hotelRoom.getHotelRoomStatus());
					}
					System.out.println("============================================");
					i1++;
				}
			System.out.println("Pick a Hotel :");
			int m = sc.nextInt();

			Hotel hpicked = hotelMap.get(m);
			if(hpicked==null)
			{
				System.out.println("Choose from available options");
				showDashboard(user);
			}
			ArrayList<HotelRoom> arl = null;
			try {
				arl = hotelBL.displayAvailHotelRoom(hpicked.getHotelId());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				System.out.println("Choose from available hotels ");
				showDashboard(user);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				System.out.println("Choose from available hotels ");
				showDashboard(user);
			}
			System.out.println("Enter Room Number : ");
			int rno =0;
			try{
			 rno = sc.nextInt();
			}catch(InputMismatchException e){System.out.println("Choose from above rooms try again!!");
			showDashboard(user);}
			System.out.println("Enter Check In Date in DD-MM-YYYY");
			String date = sc.next();
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
			Date din = null;
			try {
				// Parsing the String
				din = dateFormat.parse(date);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				System.out.println("Enter correct date format");
				showDashboard(user);
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
				//e.printStackTrace();
				System.out.println("Enter correct date format");
				showDashboard(user);
			}
			if(dout.getTime()<din.getTime()){
				System.out.println("CheckOut Date should be greater than CheckIn date ");
				System.out.println();
				showDashboard(user);
			}
				
			long diff = dout.getTime() - din.getTime();
			int duration = (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);

			HotelRoom pickedRoom = null;
			try {
				pickedRoom = hotelBL.searchHotelRoom(hpicked.getHotelId(), rno);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				System.out.println("Room not available !!!");
				showDashboard(user);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				System.out.println("Room not available !!!");
				showDashboard(user);
			}
			if(dout.equals(din)){
				System.out.println("");
				System.out.println("One day payment is compulsary");
				duration=1;
				System.out.println();
				//System.out.println("duration inside if: "+duration);
				}
			//System.out.println("duration outside if: "+duration);
			float cartValue1 = (float) (pickedRoom.getHotelRoomPrice() * duration);
			System.out.println("Total Price to be paid: " + cartValue1);

			System.out.println("Press 1 to view Additional Offers!!");
			int choice1 = sc.nextInt();
			if (choice1 == 1) {
				boolean paymentStatus = false;
				ArrayList<Promotion> promo = null;

				try {
					promo = promotionBL.displayPromotion("HOTEL");
					// System.out.println(promo);
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
					System.out.println("Promotion " + j);

					System.out.println("Promotion Name : " + p.getPromotionName());
					System.out.println("Promotion Type : " + p.getPromotionType());
					System.out.println("Promotion Discount% : " + p.getPromotionDiscount());
					System.out.println("Promotion Expiry Date : " + p.getPromotionExpiryDate());
					j++;
				}
				System.out.println("");

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
			
			

			try {
				try {
					ArrayList<FlightBooking> fb = null;
					fb = userBL.pastFbooking(user.getUserId());
					if(fb.isEmpty()){
						System.out.println("No past Flight Bookings");
						showDashboard(user);
					}
					System.out.println("Your Past Flight Bookings are------");
					int h = 1;
					for (FlightBooking fligtBooking : fb) {
						System.out.println("Flight Booking : " + h++ + " Details");
						System.out.println("flight booking ID : " + fligtBooking.getFlightBookingId());
						
						System.out.println("flight id : " + fligtBooking.getFlightId());
						System.out.println("flight booking date : " + fligtBooking.getFlightBookingDate());
						System.out.println("User Id : " + fligtBooking.getUserId());
						System.out.println("---------------------------");

					}
					// System.out.println(fb);
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
			
			
			try {
				ArrayList<HotelBooking> hb = null;
				hb = userBL.pastHbooking(user.getUserId());
				if(hb.isEmpty()){
					System.out.println("No past hotel Bookings");
					showDashboard(user);
				}
				System.out.println("Your Past Hotel Bookings are------");
				
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
			double amt=0;
			try{
			amt = sc.nextDouble();
			}catch(InputMismatchException e){
				System.out.println("Invalid Input try again!!");
				showDashboard(user);
			}
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
				System.out.println("Some problem occurred, Money cannot be added");
			}
			// addMoneyDisplay();
			break;

		case 6:
			User user1=userBL.displayUserProfile(user.getUserId());
			System.out.println();
			System.out.println("User ID : "+user1.getUserId());
			System.out.println("User Name : "+user1.getUserName());
			System.out.println("User Phone number: "+user1.getUserPhoneNo());
			System.out.println("User EmailId : "+user1.getUserEmailId());
			System.out.println("User Address : "+user1.getUserAddress());
			System.out.println();
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
			showDashboard(user);
		}
	}

}
