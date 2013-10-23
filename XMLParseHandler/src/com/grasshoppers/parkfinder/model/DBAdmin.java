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
	public void insertPark(int id, String name, int official, int street_number, String street_name, String ew_street, String ns_street, String googlemap, float hectares) {
		Connection con = getConnection();	
		
		try {
		
			Statement stmt = con.createStatement();
			stmt.execute("INSERT INTO "+ParkModel.DBNAME
			+" (id,name,official,street_number,street_name,ew_street,ns_street,googlemap,hectares)"
			+ "VALUES ("+id+",'"+name+"',"+official+","+street_number+",'"+street_name+"','"+ew_street+"','"+ns_street+"','"+googlemap+"',"+hectares+")");
			
			System.out.println(/*n +*/ "park inserted into table. Name: "+ name);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @param id Must be unique for each facility.
	 * @param type
	 * @param feature
	 * @param location
	 * @param summer_hours
	 * @param winter_hours
	 * 
	 */
	public void insertFacility(int id, String type,  String feature, String location, String summer_hours, String winter_hours) {
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
	 *  
	 * @param id Must be unique for neach neghborhood. Cannot be null.
	 * @param name Cannot be null.
	 * @param url
	 */
	public void insertNeighbourhood(int id, String name,  String url) {
		Connection con = getConnection();
		
		try {
		
			Statement stmt = con.createStatement();
			stmt.execute("INSERT INTO "+ParkModel.TABLE_NEIGHBOURHOOD
			+" (id,name,url)"
			+ "VALUES ("+id+",'"+name+"','"+url+"')");
			
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
	public void insertHasFacility(int parkId, int facilityId,  int count) {
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
	public void insertInNeghbourhood(int parkId, int neighbourhoodId) {
		Connection con = getConnection();
		
		try {
			
			Statement stmt = con.createStatement();
			stmt.execute("INSERT INTO "+ParkModel.TABLE_IN_NEIGHBOURHOOD
			+" (park_id,neighbourhood_id)"
			+ "VALUES ("+parkId+","+neighbourhoodId+")");
			
			
			System.out.println("update success. park Id: "+parkId+" neighbourhood Id: "+neighbourhoodId+"");
			
		} catch (SQLException e) {
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
						+ "official INT, "
						+ "street_number INT, "
						+ "street_name VARCHAR(50), "
						+ "ew_street VARCHAR(50), "
						+ "ns_street VARCHAR(50), "
						+ "googlemap VARCHAR(30), "
						+ "hectares DOUBLE);";
				
				try {
					Statement stmt = con.createStatement();
					stmt.execute(query);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			public static void makeNeighboourhoodTable(){
				Connection con = getConnection();
				
				String query = "CREATE TABLE "+ParkModel.TABLE_NEIGHBOURHOOD+"( "
						+ "id INT NOT NULL PRIMARY KEY, "
						+ "name VARCHAR(50) NOT NULL, "
						+ "url VARCHAR(100));";
		
				try {
					Statement stmt = con.createStatement();
					stmt.execute(query);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			
			public static void makeFacilityTable(){
				Connection con = getConnection();
				
				String query = "CREATE TABLE "+ParkModel.TABLE_FACILITY+"( "
						+ "id INT NOT NULL PRIMARY KEY, "
						+ "type VARCHAR(50), "
						+ "feature VARCHAR(50), "
						+ "location VARCHAR(50), "
						+ "note VARCHAR(150), "
						+ "summer_hours VARCHAR(50), "
						+ "winter_hours VARCHAR(50));";
				
				try {
					Statement stmt = con.createStatement();
					stmt.execute(query);
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
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			
			public static void makeInNeighborhoodTable(){
				Connection con = getConnection();
				
				String query = "CREATE TABLE "+ParkModel.TABLE_IN_NEIGHBOURHOOD+"( "
						+ "park_id INT NOT NULL, "
						+ "neighbourhood_id INT NOT NULL, "
						+ "PRIMARY KEY (park_id, neighbourhood_id), "
						+ "FOREIGN KEY (park_id) REFERENCES "+ParkModel.DBNAME+"(id), "
						+ "FOREIGN KEY (neighbourhood_id) REFERENCES "+ParkModel.TABLE_NEIGHBOURHOOD+"(id));";
				try {
					Statement stmt = con.createStatement();
					stmt.execute(query);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			public static void main(String[] args){
	  		// Create tables and test to see if they work.
	/*			makeParkTable();
				makeFacilityTable();
				makeNeighboourhoodTable();
				makeHasFacilityTable();
				makeInNeighborhoodTable();
				insertPark(00, "ipark", 1, 01234, "GrasshopperIsland", "Grass", "MoreGrass", "123456", (float) 123.425);
				insertFacility(00, "Playground", "ForMonster", "Mars", "24/7", null);
				insertNeighbourhood(00, "BeastStreet", null);
				insertInNeghbourhood(00, 00);
				insertHasFacility(00, 00, 25);
	*/		}
	
}
