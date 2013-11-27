package com.grasshoppers.parkfinder.model;

import java.sql.*;


public class ConnectionManager {

	private ConnectionManager instance = null;
	
	private final String CONN_STRING = "jdbc:mysql://ec2-23-21-211-172.compute-1.amazonaws.com:3306/parkfinder";
	private final String USERNAME = "grasshoppers";
	private final String PASSWORD = "grass";
	
	/*
	private final String CONN_STRING = "jdbc:mysql://ec2-50-19-213-178.compute-1.amazonaws.com:3306/parky";
	private final String USERNAME = "grasshopper";
	private final String PASSWORD = "grass";
	*/
	
	private Connection conn = null;
	
	ConnectionManager(){
	}
	
	public ConnectionManager getInstance() {
		
		if(instance==null){
			instance = new ConnectionManager();
		};
		return instance;
	}
	
	private boolean openConnection() {
		
			try {
				conn = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
			//	 conn.setAutoCommit(false);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return true;
	}
	
	public Connection getConnection() {
		if(conn==null){
			if(openConnection()){
			//	System.out.println("Connection Opened");
				return conn;
			} else {
				return null;
			}
		} return conn;
	}
	
	public void close() {
		System.out.println("Connection closing");
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	conn = null;
	}
	
}
