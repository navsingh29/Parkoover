package com.grasshoppers.parkfinder.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserModel extends DBManager {

	public static final String USER_TABLE = "user";
	public static final String PREFS_TABLE = "pref_list";
	
	
	/**
	 * Method called when new user registers.
	 * 
	 * @param name Username cannot be null
	 * @param password Cannot be null
	 * @param firstName
	 * @param lastName
	 * @param address
	 * @param city
	 */
	public static void createNewUser(String name, String password, String firstName, String lastName, String address, String city) {
		Connection con = getConnection();
		
		String query = "INSERT INTO "+USER_TABLE+"(user_name,password,fname,lname,address,city) "
				+ "VALUES (?,?,?,?,?,?);";
		try {
		PreparedStatement ps = con.prepareStatement(query);
		ps.setString(1, name);
		ps.setString(2, password);
		ps.setString(3,firstName);
		ps.setString(4, lastName);
		ps.setString(5, address);
		ps.setString(6, city);
		ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Call this method during login to obtain user data.
	 * @param name Username used during account creation.
	 * @param password
	 * 
	 */
	public static void getUser(String name, String password) {
		Connection con = getConnection();
		ResultSet rs;
		String query = "SELECT * FROM "+USER_TABLE+" WHERE user_name=? AND password = ?";
		try {
		PreparedStatement ps = con.prepareStatement(query);
		ps.setString(1, name);
		ps.setString(2, password);
		rs = ps.executeQuery();
		while(rs.next()){
			System.out.println(rs.getString("user_name"));
		}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void createNewParkRating(int userId, int parkId, int rating, String comment) {
		Connection con = getConnection();
		
		String query = "INSERT INTO "+PREFS_TABLE+"(user_id,park_id,rating,comment) "
				+ "VALUES (?,?,?,?);";
		try {
		PreparedStatement ps = con.prepareStatement(query);
		ps.setInt(1, userId);
		ps.setInt(2, parkId);
		ps.setInt(3,rating);
		ps.setString(4, comment);
		ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Obtain the park ratings and comments for a particular user.
	 * @param userId
	 */
	public static void getParkRatings(int userId) {
		Connection con = getConnection();
		ResultSet rs;
		String query = "SELECT * FROM "+PREFS_TABLE+" pr,"+ParkModel.DBNAME+" p WHERE p.id=pr.park_id AND user_id=?";
		try {
		PreparedStatement ps = con.prepareStatement(query);
		ps.setInt(1, userId);
		rs = ps.executeQuery();
		while(rs.next()){
			System.out.println(rs.getString("comment"));
		}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		createNewParkRating(1,2,5,"This park is awesome.");
		getParkRatings(1);
	}
	
}
