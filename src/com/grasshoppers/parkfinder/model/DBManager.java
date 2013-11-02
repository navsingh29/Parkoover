package com.grasshoppers.parkfinder.model;

import java.sql.Connection;

public class DBManager {

	public static Connection getConnection() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = new ConnectionManager().getInstance().getConnection();
		} catch (ClassNotFoundException e2) {
			e2.printStackTrace();
		}
		return con;
	}
	
	
		
		
		
	
	
}
