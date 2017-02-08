package com.MMT.menu;

import java.sql.SQLException;
import java.util.Scanner;

import com.MMT.bean.Admin;
import com.MMT.bean.User;
import com.MMT.bl.AdminBlMMT;
import com.MMT.bl.UserBlMMT;

public class LoginMenu {
	AdminDashboard adb=new AdminDashboard();
	AdminBlMMT Ad=new AdminBlMMT();
	UserBlMMT Us=new UserBlMMT();
	UserDashboard ud=new UserDashboard();
	Scanner sc=new Scanner(System.in);
	public void LoginPage(){
		System.out.println("Enter User Name:");
		String name=sc.next();
		System.out.println("Enter Password: ");
		String pass=sc.next();
		
		if(name==null){
			System.out.println("THE USERNAME FIELD IS BLANK!!!");
		}
		else if(pass==null){
			System.out.println("THE PASSWORD FIELD IS BLANK!!!");
		}
		else{
			
			try {
				if(Ad.checkAdminLogin(name, pass)!=null)
					{
					
					System.out.println(" Successful Admin Login!!");
					Admin admin=Ad.checkAdminLogin(name, pass);
					adb.showDashboard(admin);
				//	adminDisplay();
					
					}
				else if(Us.checkLogin(name, pass)!=null)
				{
					System.out.println(" Successful User Login!!");
					User user = Us.checkLogin(name, pass);
					
					ud.showDashboard(user);                //User Display 
				}
				else{
					System.out.println("Invalid Credentials");
					return;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return;
	}
}
