package com.MMT.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
	public static Connection dbConnection(){
		Connection con=null;
	
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521",
						"system", "sapient@123");
			} catch (ClassNotFoundException | SQLException e) {
			
				e.printStackTrace();
				System.out.println("this==============");
			}
			
		return con;
	}
}
