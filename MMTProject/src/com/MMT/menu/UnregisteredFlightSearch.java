package com.MMT.menu;

import java.io.IOException;
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
	FlightBookingBlMMT flightBookingBL = new FlightBookingBlMMT();
	UserBlMMT userBL = new UserBlMMT();
	HomePage homepage = new HomePage();
	PromotionBlMMT promotionBl = new PromotionBlMMT();
	WalletBlMMT walletBL = new WalletBlMMT();
	UserDashboard userDashboard = new UserDashboard();
	Scanner sc = new Scanner(System.in);

	public void showDashboard() {
		User user = null;
		System.out.println("Enter Source:");
		String source = sc.next();
		System.out.println("Enter Destination:");
		String destination = sc.next();
		ArrayList<Flight> fList = null;
		LinkedHashMap<Integer, Flight> flightMap = new LinkedHashMap<Integer, Flight>();
		try {
			try {
				fList = flightBookingBL.searchFlight(source, destination);
				if(fList==null){
					System.out.println("Flight not available");
					showDashboard();
					
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
			System.out.println(" Flight Details "+i);
			System.out.println("");
			System.out.println(  " Flight ID: " + f.getFlightId());
			System.out.println(  " Flight Company Name: " + f.getFlightCompanyName());
			System.out.println( " Flight Source: " + f.getFlightSource());
			System.out.println( " Flight Destination: " + f.getFlightDestination());
			System.out.println( " Flight Departure Time: " + f.getFlightDepartureTime());
			System.out.println( " Flight Arrival Time: " + f.getFlightArrivalTime());
			System.out.println( " Flight Ticket Price: " + f.getFlightTicketPrice());
			System.out.println( " Flight Available Seats: " + f.getAvailableSeats());
			System.out.println("----------------------------");
			i++;
		}
		
		
			System.out.println("Pick a flight :");
		
		
		int v = sc.nextInt();

		Flight fpicked = flightMap.get(v);
		System.out.println("Enter No of seats:");
		int seats = sc.nextInt();
		double cartValue = fpicked.getFlightTicketPrice() * seats;
		System.out.println("Total Amount to be paid:" + cartValue);

		System.out.println("Press 1 forLogin to Proceed Further!!");
		System.out.println("Press 2 to go back");
		int in = sc.nextInt();
		if (in == 1) {
			System.out.println("Enter User Name:");
			String name = sc.next();
			System.out.println("Enter Password: ");
			String pass = sc.next();

			if (name == null) {
				System.out.println("THE USERNAME FIELD IS BLANK!!!");
			} else if (pass == null) {
				System.out.println("THE PASSWORD FIELD IS BLANK!!!");
			} else
				try {
					if (userBL.checkLogin(name, pass) != null) {
						System.out.println(" Successful User Login!!");
						user = userBL.checkLogin(name, pass);
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
		} else if (in == 2) {
			try {
				homepage.homePageMenu();
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
		} else {
			try {
				homepage.homePageMenu();
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
		}
		System.out.println("Press 1 to See Additional Offers!!");
		int choice = sc.nextInt();
		if (choice == 1) {
			boolean paymentStatus = false;
			ArrayList<Promotion> promo = null;
			try {
				promo = promotionBl.displayPromotion("FLIGHT");
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
				System.out.println("Promotion "+j);
				
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

			float valueAfterPromotion = promotionBl.applyPromotion(pPicked, user.getUserId(), (float) cartValue);
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

				} else if (ch1 == 2) {
					System.out.println("Booking Cancelled due to insufficient funds!!");
					System.out.println("Booking Cancelled!!");
					paymentStatus = false;
					try {
						userDashboard.showDashboard(user);
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

				} else {
					System.out.println("Invalid Input");
					paymentStatus = false;
					try {
						userDashboard.showDashboard(user);
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
				fb = flightBookingBL.bookFlight(user.getUserId(), fpicked.getFlightId(), source, destination, seats);
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
			if ((paymentStatus) && (fb != null)) {
				System.out.println("Flight Booking Done");
				System.out.println("Your Booking ID is: " + fb.getFlightBookingId());
				try {
					userDashboard.showDashboard(user);
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
			} else if (fb == null) {
				System.out.println("Sorry !! Booking Failed");
				try {
					userDashboard.showDashboard(user);
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
			}
		}

	}
}
