package com.grasshoppers.parkfinder.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class DBAdmin extends DBManager {


	//=======================================================================================================//
	//	Insert Rows into Tables
	//=======================================================================================================//

	/**
	 * Creates a new row in the park table for a park.
	 * 
	 * @param id Park id. Cannot be Null.
	 * @param name Name of park. Cannot be Null.
	 * @param official 0 or 1
	 * @param street_number
	 * @param street_name
	 * @param ew_street
	 * @param ns_street
	 * @param googlemap please note that this is a string
	 * @param hectares
	 */
	public static void insertPark(int id, String name, int official, int street_number, String street_name, String ew_street, String ns_street, double map_x_loc, double map_y_loc, double hectares) {
		Connection con = getConnection();	

		try {

			Statement stmt = con.createStatement();
			stmt.execute("INSERT INTO "+ParkModel.DBNAME
					+" (id,name,official,street_number,street_name,ew_street,ns_street,map_x_loc,map_y_loc,hectares)"
					+ "VALUES ("+id+",'"+name+"',"+official+","+street_number+",'"+street_name+"','"+ew_street+"','"+ns_street+"','"+map_x_loc+"','"+map_y_loc+"',"+hectares+")");

			System.err.println("NOTICE::PARK INSERTED INTO TABLE. Name: "+ name + ", ID: " + id);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @deprecated
	 * @param id Must be unique for each facility.
	 * @param type
	 * @param feature
	 * @param location
	 * @param summer_hours
	 * @param winter_hours
	 * 
	 */
	public static void insertFacility(int id, String type,  String feature, String location, String summer_hours, String winter_hours) {
		Connection con = getConnection();

		try {

			Statement stmt = con.createStatement();
			stmt.execute("INSERT INTO "+ParkModel.TABLE_FACILITY
					+" (id,type,feature,location,summer_hours,winter_hours)"
					+ "VALUES ("+id+",'"+type+"','"+feature+"','"+location+"','"+summer_hours+"','"+winter_hours+"')");

			System.out.println("facility inserted into table. Type: "+ type);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	/**
	 * @deprecated 
	 * @param id Must be unique for neach neghborhood. Cannot be null.
	 * @param name Cannot be null.
	 * @param url
	 */
	public static void insertNeighborhood(String name,  String url) {
		Connection con = getConnection();

		try {

			Statement stmt = con.createStatement();
			stmt.execute("INSERT INTO "+ParkModel.TABLE_NEIGHBORHOOD
					+" (id,name,url)"
					+ "VALUES ('"+name+"','"+url+"')");

			System.out.println( "neighborhood inserted into table. Name: "+ name);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param parkId A Park with this id must already exist in the park table!!
	 * @param facilityId A facility with this id must already exist in the facility table!!
	 * @param count Number of this facility that a park has.
	 */
	public static void insertHasFacility(int parkId, int facilityId,  int count) {
		Connection con = getConnection();

		try {

			Statement stmt = con.createStatement();
			stmt.execute("INSERT INTO "+ParkModel.TABLE_HAS_FACILITY
					+" (park_id,facility_id,count)"
					+ "VALUES ("+parkId+","+facilityId+","+count+")");

			System.out.println("update success. park Id: "+parkId+" facility Id: "+facilityId+"");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	/**
	 *  
	 * @param parkId A Park with this id must already exist in the park table!!
	 * @param neighbourhoodId A neighbourhood with this id must already exist in the neighbourhood table!!
	 */
	public static void insertInNeighborhood(int parkId, int neighbourhoodId) {
		Connection con = getConnection();

		try {

			Statement stmt = con.createStatement();
			stmt.execute("INSERT INTO "+ParkModel.TABLE_IN_NEIGHBORHOOD
					+" (park_id,neighbourhood_id)"
					+ "VALUES ("+parkId+","+neighbourhoodId+")");


			System.out.println("update success. park Id: "+parkId+" neighbourhood Id: "+neighbourhoodId+"");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	/**
	 *  
	 * @param parkId A Park with this id must already exist in the park table!!
	 * @param nName Cannot be null. Must be unqie when combined with nUrl.
	 * @param nUrl  Must be unique when combined with nName.
	 * 
	 */

	public static void insertNeighborhoodComplete(int parkId, String nName, String nUrl) {
		Connection con = getConnection();

		int nid;
		try {

			Statement stmt = con.createStatement();
			stmt.execute("INSERT INTO "+ParkModel.TABLE_NEIGHBORHOOD
					+" (name,url)"
					+ "VALUES ('"+nName+"','"+nUrl+"')");


			nid = ParkModel.getNeighborhoodId(nName, nUrl);
			insertInNeighborhood(parkId, nid);
			System.out.println( "neighborhood inserted into table. Park Id: " + parkId +
					" Name: "+ nName + " Neighborhood Id: " + nid);
			
		} catch (SQLException e) {
			if(e.getMessage().startsWith("Duplicate entry")) {
				nid = ParkModel.getNeighborhoodId(nName, nUrl);
				insertInNeighborhood(parkId, nid);
				System.out.println( "neighborhood inserted into table. Park Id: " + parkId +
						" Name: "+ nName + " Neighborhood Id: " + nid);
			} else 
				e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @param parkId ID for which the facility is in.
	 * @param count
	 * @param type 
	 * @param feature
	 * @param location
	 * @param summer_hours
	 * @param winter_hours
	 * 
	 */
	public static void insertFacilityComplete(int parkId, int count, String type,  String feature, String location, String summer_hours, String winter_hours) {
		Connection con = getConnection();

		int fid;
		try {

			Statement stmt = con.createStatement();
			stmt.execute("INSERT INTO "+ParkModel.TABLE_FACILITY
					+"(type,feature,location,summer_hours,winter_hours)"
					+ "VALUES ('"+type+"','"+feature+"','"+location+"','"+summer_hours+"','"+winter_hours+"')");


			fid = ParkModel.getFacilityId(type, feature, location);
			insertHasFacility(parkId, fid, count);
			System.out.println( "facility and facility ownership inserted into table. Park Id: " + parkId +
					" Type: "+ type + " Facility Id: " + fid);
			
		} catch (SQLException e) {
			if(e.getMessage().startsWith("Duplicate entry")) {
				fid = ParkModel.getFacilityId(type, feature, location);
				insertHasFacility(parkId, fid, count);
				System.out.println( "facility ownership inserted into table. Park Id: " + parkId +
						" Type: "+ type + " Facility Id: " + fid);
			} else 
				e.printStackTrace();
		}
	}
	
	//======================================================================================================================//
	//	Create Tables
	//======================================================================================================================//

	public static void makeParkTable(){
		Connection con = getConnection();

		String query = "CREATE TABLE "+ParkModel.DBNAME+"( "
				+ "id INT NOT NULL PRIMARY KEY, "
				+ "name VARCHAR(50) NOT NULL, "
				+ "official BOOL, "
				+ "street_number INT, "
				+ "street_name VARCHAR(50), "
				+ "ew_street VARCHAR(50), "
				+ "ns_street VARCHAR(50), "
				+ "map_x_loc DOUBLE, "
				+ "map_y_loc DOUBLE, "
				+ "hectares DOUBLE);";
		
		try {
			Statement stmt = con.createStatement();
			stmt.execute(query);
			
			System.out.println("Table " + ParkModel.DBNAME
					+ "(id, name, official, street_number, street_name, ew_street, "
					+ "ns_street, map_x_loc, map_y_loc, hectares) succuessfully created.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void makeNeighborhoodTable(){
		Connection con = getConnection();

		String query = "CREATE TABLE "+ParkModel.TABLE_NEIGHBORHOOD+"( "
				+ "id INT NOT NULL AUTO_INCREMENT, "
				+ "name VARCHAR(50) NOT NULL, "
				+ "url VARCHAR(100),"
				+ "PRIMARY KEY (id),"
				+ "UNIQUE (name, url));";
		
		try {
			Statement stmt = con.createStatement();
			stmt.execute(query);
			
			System.out.println("Table " + ParkModel.TABLE_NEIGHBORHOOD
					+ "(id, name, url) succuessfully created.");
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}


	public static void makeFacilityTable(){
		Connection con = getConnection();

		String query = "CREATE TABLE "+ParkModel.TABLE_FACILITY+"( "
				+ "id INT NOT NULL PRIMARY KEY AUTO_INCREMENT, "
				+ "type VARCHAR(50), "
				+ "feature VARCHAR(50), "
				+ "location VARCHAR(50), "
				+ "note VARCHAR(150), "
				+ "summer_hours VARCHAR(50), "
				+ "winter_hours VARCHAR(50), "
				+ "UNIQUE (type, feature, location));";

		System.out.println("Table " + ParkModel.TABLE_FACILITY
				+ "(id, type, feature, location, note) succuessfully created.");
		
		try {
			Statement stmt = con.createStatement();
			stmt.execute(query);
			
			System.out.println("Table " + ParkModel.TABLE_HAS_FACILITY
					+ "(park_id, facility_id, count) succuessfully created.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	public static void makeHasFacilityTable(){
		Connection con = getConnection();

		String query = "CREATE TABLE "+ParkModel.TABLE_HAS_FACILITY+"( "
				+ "park_id INT NOT NULL, "
				+ "facility_id INT NOT NULL, "
				+ "count INT, "
				+ "PRIMARY KEY (park_id, facility_id), "
				+ "FOREIGN KEY (park_id) REFERENCES "+ParkModel.DBNAME+"(id), "
				+ "FOREIGN KEY (facility_id) REFERENCES "+ParkModel.TABLE_FACILITY+"(id));";
		
		try {
			Statement stmt = con.createStatement();
			stmt.execute(query);
			
			System.out.println("Table " + ParkModel.TABLE_HAS_FACILITY
					+ "(park_id, facility_id, count) succuessfully created.");
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}


	public static void makeInNeighborhoodTable(){
		Connection con = getConnection();

		String query = "CREATE TABLE "+ParkModel.TABLE_IN_NEIGHBORHOOD+"( "
				+ "park_id INT NOT NULL, "
				+ "neighbourhood_id INT NOT NULL, "
				+ "PRIMARY KEY (park_id, neighbourhood_id), "
				+ "FOREIGN KEY (park_id) REFERENCES "+ParkModel.DBNAME+"(id), "
				+ "FOREIGN KEY (neighbourhood_id) REFERENCES "+ParkModel.TABLE_NEIGHBORHOOD+"(id));";
		try {
			Statement stmt = con.createStatement();
			stmt.execute(query);
			
			System.out.println("Table " + ParkModel.TABLE_IN_NEIGHBORHOOD
					+ "(park_id, neighbourhood_id) succuessfully created.");
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}

	public static void dropAllTables() {
		Connection con = getConnection();

		try {
			Statement stmt = con.createStatement();
			stmt.execute("DROP TABLE "+ParkModel.TABLE_IN_NEIGHBORHOOD+";");
			stmt.execute("DROP TABLE "+ParkModel.TABLE_HAS_FACILITY+";");
			stmt.execute("DROP TABLE "+ParkModel.TABLE_FACILITY+";");
			stmt.execute("DROP TABLE "+ParkModel.TABLE_NEIGHBORHOOD+";");
			stmt.execute("DROP TABLE "+ParkModel.DBNAME+";");			
			
			System.out.println("All tables successfully dropped.");
		} catch (SQLException e) {
			e.printStackTrace();

		}
	}

}
