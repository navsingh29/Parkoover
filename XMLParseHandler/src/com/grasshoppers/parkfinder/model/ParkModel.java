package com.grasshoppers.parkfinder.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ParkModel extends DBManager {

	public static final String DBNAME = "Park";
	public static final String TABLE_FACILITY = "Facility";
	public static final String TABLE_NEIGHBORHOOD = "Neighborhood";
	public static final String TABLE_HAS_FACILITY = "Has_Facility";
	public static final String TABLE_IN_NEIGHBORHOOD = "In_Neighborhood";	
	
	
	public static void findParks(String name, String neighbourhood) {
		Connection conn = getConnection();
		
		String query;
		
		if (name!=null&&neighbourhood==null) {
		query = "SELECT * FROM "+DBNAME
				+ "WHERE name LIKE "+name;
		} else if (name==null&&neighbourhood!=null) {
			query = "SELECT * FROM "+TABLE_NEIGHBORHOOD
					+ "WHERE name LIKE "+neighbourhood;	
		} else if (name!=null&&neighbourhood!=null) {
			query = "SELECT * FROM "+TABLE_NEIGHBORHOOD
					+","+DBNAME
					+ "WHERE name LIKE "+neighbourhood;	
		} 
		
		
	}
	
	/**
	 * 
	 * @param name
	 * @param url 
	 * @return NeighborhoodId
	 * (name, url) MUST BE UNIQUE
	 * 
	 */
	public static int getNeighborhoodId(String name, String url) {
		Connection conn = getConnection();
		try {

			Statement stmt = conn.createStatement();
			String query = "SELECT id FROM " + 
							TABLE_NEIGHBORHOOD + 
							" WHERE name = '" + name + "'" +
							" AND url = '" + url + "';";
			
			ResultSet rs = stmt.executeQuery(query);
			rs.first();
			int returnId = rs.getInt("id");
			System.out.println("Hood ID is: " + returnId);
			return returnId;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	/**
	 * 
	 * @param type
	 * @param count
	 * @param location 
	 * @return FacilityId
	 * (type, feature, location) MUST BE UNIQUE
	 * 
	 */
	public static int getFacilityId(String type, String feature, String location) {
		Connection conn = getConnection();
		try {

			Statement stmt = conn.createStatement();
			String query = "SELECT id FROM " + 
							TABLE_FACILITY + 
							" WHERE type = '" + type + "'" +
							" AND feature = '" + feature + "'" +
							" AND location = '" + location + "';";
			
			ResultSet rs = stmt.executeQuery(query);
			rs.first();
			int returnId = rs.getInt("id");
			System.out.println("Facility ID is: " + returnId);
			return returnId;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

}
