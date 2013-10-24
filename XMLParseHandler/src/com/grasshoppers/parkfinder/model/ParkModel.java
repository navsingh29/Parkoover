package com.grasshoppers.parkfinder.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ParkModel extends DBManager {

	public static final String DBNAME = "Park";
	public static final String TABLE_FACILITY = "Facility";
	public static final String TABLE_NEIGHBOURHOOD = "Neighbourhood";
	public static final String TABLE_HAS_FACILITY = "Has_Facility";
	public static final String TABLE_IN_NEIGHBOURHOOD = "In_Neighbourhood";
	
	
	
	public static void findParks(String name, String neighbourhood) {
		Connection conn = getConnection();
		
		String query;
		
		if (name!=null&&neighbourhood==null) {
		query = "SELECT * FROM "+DBNAME
				+ "WHERE name LIKE "+name;
		} else if (name==null&&neighbourhood!=null) {
			query = "SELECT * FROM "+TABLE_NEIGHBOURHOOD
					+ "WHERE name LIKE "+neighbourhood;	
		} else if (name!=null&&neighbourhood!=null) {
			query = "SELECT * FROM "+TABLE_NEIGHBOURHOOD
					+","+DBNAME
					+ "WHERE name LIKE "+neighbourhood;	
		} 
		
		
	}
	
	
	
	
	
}
