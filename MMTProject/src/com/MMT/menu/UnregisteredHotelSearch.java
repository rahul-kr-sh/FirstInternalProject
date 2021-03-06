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
	UserBlMMT userBL=new UserBlMMT();
	HotelBlMMT hotelBL = new HotelBlMMT();
	PromotionBlMMT promotionBL = new PromotionBlMMT();
	WalletBlMMT walletBL = new WalletBlMMT();
	UserDashboard userDashboard=new UserDashboard();
	HomePage homepage=new HomePage();
	public void showDashboard(){
		User user=null;
		System.out.println("Enter Location:");
		String loc = sc.next().toLowerCase();
		ArrayList<Hotel> hList = new ArrayList<Hotel>();
		LinkedHashMap<Integer, Hotel> hotelMap = new LinkedHashMap<Integer, Hotel>();
		try {
			hList = hotelBL.searchHotel1(loc);
			if(hList.isEmpty()){
				System.out.println("No Hotels in "+loc);
				showDashboard();
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
		i1 = 1;
		
	System.out.println("------------Hotel Details------------");
		for(Hotel hotelNew:hList){
			System.out.println(i1+" Hotel ");
			System.out.println("Hotel ID : "+hotelNew.getHotelId());
			System.out.println("Hotel name : "+hotelNew.getHotelName());
			System.out.println("Hotel location : "+hotelNew.getHotelLocation());
			System.out.println("Hotel information : "+hotelNew.getHotelInfo());
			System.out.println("-----------Room Details-----------");
			//ArrayList<HotelRoom> hotelroom= hotelNew.getHotelRoom();
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
			showDashboard();
		}
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
		System.out.println("Enter Room Number:");
		//int rno = sc.nextInt();
		int rno =0;
		try{
		 rno = sc.nextInt();
		}catch(InputMismatchException e){System.out.println("Choose from above rooms try again!!");
		showDashboard();}

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
			showDashboard();
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
		if(dout.getTime()<din.getTime()){
			System.out.println("CheckOut Date should be greater than CheckIn date ");
			System.out.println();
			showDashboard();
		}
		long diff = dout.getTime() - din.getTime();
		int duration = (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);

		HotelRoom pickedRoom = null;
		try {
			pickedRoom = hotelBL.searchHotelRoom(hpicked.getHotelId(), rno);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Choose from available options");
			showDashboard();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Choose from available options");
			showDashboard();
		}
		if(dout.equals(din)){
			System.out.println("");
			System.out.println("One day payment is compulsary");
			duration=1;
			System.out.println();
			//System.out.println("duration inside if: "+duration);
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
					if(userBL.checkLogin(name, pass)!=null){
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
		}
		else if(in==2){
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
		else {
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
		
		System.out.println("Press 1 to view Additional Offers!!");
		int choice1 = sc.nextInt();
		if (choice1 == 1) {
			boolean paymentStatus = false;
			ArrayList<Promotion> promo = null;

			try {
				promo = promotionBL.displayPromotion("HOTEL");
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
					System.out.println("Confirming HotelRoom Booking!!");

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
				System.out.println("Confirming Hotel Booking!!");
			}

			HotelBooking hb = null;
			try {
				hb = hotelBL.bookHotel(user.getUserId(), hpicked.getHotelId(), rno, din, dout);
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

			if ((paymentStatus) && (hb != null)) {
				System.out.println("Hotel Booking Done");
				System.out.println("Your Booking ID is: " + hb.getHotelBookingId());
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
			} else if (hb == null) {
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
