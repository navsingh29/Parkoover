package com.grasshoppers.parkfinder.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.grasshoppers.parkfinder.client.modeldata.PreferencePark;
import com.grasshoppers.parkfinder.client.modeldata.User;

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
	public static void createNewUser(String name, String password, String firstName, String lastName, String address, String city, String province, String country) {
		Connection con = getConnection();
		
		String query = "INSERT INTO "+USER_TABLE+"(user_name,password,fname,lname,address,city,province,country) "
				+ "VALUES (?,?,?,?,?,?,?,?);";
		try {
		PreparedStatement ps = con.prepareStatement(query);
		ps.setString(1, name);
		ps.setString(2, password);
		ps.setString(3,firstName);
		ps.setString(4, lastName);
		ps.setString(5, address);
		ps.setString(6, city);
		ps.setString(7, province);
		ps.setString(8, country);
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
	public User getUser(String name, String password) {
		Connection con = getConnection();
		ResultSet rs;
		User user = null;
		String query = "SELECT id,user_name,fname,lname,address,city,Province,Country"
				+ " FROM "+USER_TABLE+" WHERE user_name=? AND password = ?";
		try {
		PreparedStatement ps = con.prepareStatement(query);
		ps.setString(1, name);
		ps.setString(2, password);
		rs = ps.executeQuery();
		
		if(rs.next()){
			user = ModelFactory.makeUser(rs);
		} else return null;
		
		user.addPreferences(getParkRatings(user.getId()));
		
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
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
	public static List<PreferencePark> getParkRatings(int userId) {
		Connection con = getConnection();
		ResultSet rs;
		List<PreferencePark> parks = new ArrayList<PreferencePark>();
		String query = "SELECT * FROM "+PREFS_TABLE+" pr,"+ParkModel.DBNAME+" p WHERE p.id=pr.park_id AND user_id=?";
		query = "SELECT p.id AS Park_Id, p.name, p.official, p.street_number,p.street_name,p.ew_street,p.ns_street,"
				+ "p.map_x_loc,p.map_y_loc,p.hectares,f.id AS Facility_Id, f.type,f.feature,f.location,f.note,f.summer_hours,"
				+ "f.winter_hours,h.count,n.id AS Neighborhood_Id, n.name AS Neighborhood_Name, n.url,"
				+ " pr.rating,pr.comment"
				+ " FROM "+PREFS_TABLE+" pr,"+ParkModel.DBNAME+" p,"+ParkModel.TABLE_FACILITY+" f,"+ParkModel.TABLE_HAS_FACILITY+" h,"
				+ParkModel.TABLE_NEIGHBORHOOD+" n, "+ParkModel.TABLE_IN_NEIGHBORHOOD+" i"
				+ " WHERE p.id=i.park_id AND n.id=i.neighbourhood_id AND f.id=h.facility_id AND p.id=h.park_id"
				+ " AND p.id=pr.park_id AND user_id=?"
				+ " ORDER BY p.name";
		try {
		PreparedStatement ps = con.prepareStatement(query);
		ps.setInt(1, userId);
		rs = ps.executeQuery();


		List<Integer> ids = new ArrayList<Integer>();
		PreferencePark parkToAdd = null;
		while(rs.next()){
			int id = rs.getInt("Park_Id");
			if(!ids.contains(id)){
				parkToAdd = ModelFactory.makePreferencPark(rs);
				ids.add(id);
				parks.add(parkToAdd);
			} else {
				// TODO Check to see if parkToAdd is null or not
				parkToAdd.addFacility(ModelFactory.makeFacility(rs));
			}
		}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return parks;
	}
	
	
	public static void main(String[] args) {
//		createNewUser("batman","batmobile","Bruce","Wayne",null,"Gotham City",null,"USA");
//		createNewParkRating(1,2,5,"This park is awesome.");
//		getParkRatings(1);
//		getUser("superman","kryptonite");
		
	}
	
}
