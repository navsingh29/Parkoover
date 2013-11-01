package com.grasshoppers.parkfinder.model;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Park implements Serializable {

	
/**
	 * 
	 */
	private static final long serialVersionUID = -2001485435540597595L;
	// Park	
	private int parkId;
	private String name;
	private int official;
	private int street_number;
	private String street_name;
	private String ew_street;
	private String ns_street;
	private double map_x_loc;
	private double map_y_loc;
	private double hectares;
	
// Neighborhood
	private int neighborhoodId;
	private String neighborhoodName;
	private String url;
	
// Facilities
	private List<Facility> facilityList = new ArrayList<Facility>();
	
	public Park(ResultSet rs) {
		try{
		parkId = rs.getInt("Park_Id");
		name = rs.getString("name");
		official = rs.getInt("official");
		street_number = rs.getInt("street_number");
		street_name = rs.getString("street_name");
		ew_street = rs.getString("ew_street");
		ns_street = rs.getString("ns_street");
		map_x_loc = rs.getDouble("map_x_loc");
		map_y_loc = rs.getDouble("map_y_loc");
		hectares = rs.getDouble("hectares");
		
		neighborhoodId = rs.getInt("Neighborhood_Id");
		neighborhoodName = rs.getString("Neighborhood_Name");
		url = rs.getString("url");
		
		facilityList.add(new Facility(rs));
		
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}

	public void addFacility(ResultSet rs) {
		facilityList.add(new Facility(rs));
	}
	
	public List<Facility> getFacilityList() {
		return facilityList;
	}
	
	public int getParkId() {
		return parkId;
	}

	public String getName() {
		return name;
	}

	public int getOfficial() {
		return official;
	}

	public int getStreet_number() {
		return street_number;
	}

	public String getStreet_name() {
		return street_name;
	}

	public String getEw_street() {
		return ew_street;
	}

	public String getNs_street() {
		return ns_street;
	}

	public double getMap_x_loc() {
		return map_x_loc;
	}

	public double getMap_y_loc() {
		return map_y_loc;
	}

	public double getHectares() {
		return hectares;
	}

	public int getNeighbourhoodId() {
		return neighborhoodId;
	}

	public String getNeighbourhoodName() {
		return neighborhoodName;
	}

	public String getUrl() {
		return url;
	}

	public void setParkId(int parkId) {
		this.parkId = parkId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setOfficial(int official) {
		this.official = official;
	}

	public void setStreet_number(int street_number) {
		this.street_number = street_number;
	}

	public void setStreet_name(String street_name) {
		this.street_name = street_name;
	}

	public void setEw_street(String ew_street) {
		this.ew_street = ew_street;
	}

	public void setNs_street(String ns_street) {
		this.ns_street = ns_street;
	}

	public void setMap_x_loc(double map_x_loc) {
		this.map_x_loc = map_x_loc;
	}

	public void setMap_y_loc(double map_y_loc) {
		this.map_y_loc = map_y_loc;
	}

	public void setHectares(double hectares) {
		this.hectares = hectares;
	}

	public void setNeighbourhoodId(int neighbourhoodId) {
		this.neighborhoodId = neighbourhoodId;
	}

	public void setNeighbourhoodName(String neighbourhoodName) {
		this.neighborhoodName = neighbourhoodName;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return name+":"+parkId;
	}
	
	
	
	
}
