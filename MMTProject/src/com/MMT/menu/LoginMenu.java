package com.MMT.menu;

import java.sql.SQLException;
import java.util.Scanner;

import com.MMT.bean.Admin;
import com.MMT.bean.User;
import com.MMT.bl.AdminBlMMT;
import com.MMT.bl.UserBlMMT;

public class LoginMenu {
	AdminDashboard adminDashboard=new AdminDashboard();
	AdminBlMMT adminBl=new AdminBlMMT();
	UserBlMMT userBl=new UserBlMMT();
	UserDashboard userDashboard=new UserDashboard();
	
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
				if(adminBl.checkAdminLogin(name, pass)!=null)
					{
					
					System.out.println(" Successful Admin Login!!");
					Admin admin=adminBl.checkAdminLogin(name, pass);
					adminDashboard.showDashboard(admin);
				//	adminDisplay();
					
					}
				else if(userBl.checkLogin(name, pass)!=null)
				{
					System.out.println(" Successful User Login!!");
					User user = userBl.checkLogin(name, pass);
					
					userDashboard.showDashboard(user);                //User Display 
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
