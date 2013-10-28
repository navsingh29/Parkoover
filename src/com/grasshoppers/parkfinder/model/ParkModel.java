package com.grasshoppers.parkfinder.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import sun.font.CreatedFontTracker;

public class ParkModel extends DBManager {

	public static final String DBNAME = "Park";
	public static final String TABLE_FACILITY = "Facility";
	public static final String TABLE_NEIGHBORHOOD = "Neighborhood";
	public static final String TABLE_HAS_FACILITY = "Has_Facility";
	public static final String TABLE_IN_NEIGHBORHOOD = "In_Neighborhood";	
	
	
	public static void findParks(String park, String neighbourhood, String facility) {
		Connection con = getConnection();
		String query;
		PreparedStatement ps = null;
		try {
			if (park!=null&&neighbourhood==null&&facility==null) {
				query = "SELECT * FROM "+DBNAME+" WHERE name LIKE ?";
				ps = con.prepareStatement(query);
				ps.setString(1, "%"+park+"%");
			} else if (park==null&&neighbourhood!=null&&facility==null) {
				query = "SELECT * FROM "+DBNAME+" p,"+TABLE_NEIGHBORHOOD+" n,"+TABLE_IN_NEIGHBORHOOD+" i"
						+ " WHERE p.id=i.park_id AND n.id=i.neighbourhood_id AND n.name LIKE ?";
				ps = con.prepareStatement(query);
				ps.setString(1, "%"+neighbourhood+"%");
			} else if (park==null&&neighbourhood==null&&facility!=null){
				query = "SELECT * FROM "+DBNAME+" p,"+TABLE_FACILITY+" f,"+TABLE_HAS_FACILITY+" i"
						+ " WHERE p.id=i.park_id AND f.id=i.facility_id AND f.type LIKE ?";
				ps = con.prepareStatement(query);
				ps.setString(1, "%"+facility+"%");
			} else if (park!=null&&neighbourhood!=null&&facility==null){
				query = "SELECT * FROM "+DBNAME+" p,"+TABLE_NEIGHBORHOOD+" n,"+TABLE_IN_NEIGHBORHOOD+" i"
						+ " WHERE p.id=i.park_id AND n.id=i.neighbourhood_id AND p.name LIKE ? AND n.name LIKE ?";
				ps = con.prepareStatement(query);
				ps.setString(1, "%"+park+"%");
				ps.setString(2, "%"+neighbourhood+"%");
			} else if (park!=null&&neighbourhood==null&&facility!=null){
				query = "SELECT * FROM "+DBNAME+" p,"+TABLE_FACILITY+" f,"+TABLE_HAS_FACILITY+" h"
						+ " WHERE p.id=h.park_id AND f.id=h.facility_id AND p.name LIKE ? AND f.type LIKE ?";
				ps = con.prepareStatement(query);
				ps.setString(1, "%"+park+"%");
				ps.setString(2, "%"+facility+"%");
			} else if (park==null&&neighbourhood!=null&&facility!=null){
				query = "SELECT * FROM "+DBNAME+" p,"+TABLE_FACILITY+" f,"+TABLE_HAS_FACILITY+" h,"
						+TABLE_NEIGHBORHOOD+" n, "+TABLE_IN_NEIGHBORHOOD+" i"
						+ " WHERE p.id=i.park_id AND n.id=i.neighbourhood_id AND f.id=h.facility_id AND p.id=h.park_id"
						+ " AND n.name LIKE ? AND f.type LIKE ?";
				ps = con.prepareStatement(query);
				ps.setString(1, "%"+neighbourhood+"%");
				ps.setString(2, "%"+facility+"%");
			} else if (park!=null&&neighbourhood!=null&&facility!=null){
				query = "SELECT * FROM "+DBNAME+" p,"+TABLE_FACILITY+" f,"+TABLE_HAS_FACILITY+" h,"
						+TABLE_NEIGHBORHOOD+" n, "+TABLE_IN_NEIGHBORHOOD+" i"
						+ " WHERE p.id=i.park_id AND n.id=i.neighbourhood_id AND f.id=h.facility_id AND p.id=h.park_id"
						+ " AND n.name LIKE ? AND f.type LIKE ? AND p.name LIKE ?";
				ps = con.prepareStatement(query);
				ps.setString(1, "%"+neighbourhood+"%");
				ps.setString(2, "%"+facility+"%");
				ps.setString(3, "%"+park+"%");
			}
			
			ResultSet rs = ps.executeQuery();
		
			while(rs.next()){
				System.out.println(rs.getString("name"));
			}
		} catch(SQLException e){
			e.printStackTrace();
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
	
	public static void main(String[] args){
		
		findParks(null, "ridge","ball");
	}

}
