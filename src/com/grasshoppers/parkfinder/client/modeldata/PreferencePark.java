package com.grasshoppers.parkfinder.client.modeldata;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PreferencePark implements Serializable {

	
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
	
// Preference Stuff
	private int rating;
	private int count;
	private String comment;
	private String time;
	
	public PreferencePark() {
	
	}
	
	public PreferencePark(Park park, int rating, String comment, String time) {
		
	// Park	
		this.parkId = park.getParkId();
		this.name = park.getName();
		this.official = park.getOfficial();
		this.street_number = park.getStreet_number();
		this.street_name = park.getStreet_name();
		this.ew_street = park.getEw_street();
		this.ns_street = park.getNs_street();
		this.map_x_loc = park.getMap_x_loc();
		this.map_y_loc = park.getMap_y_loc();
		this.hectares = park.getHectares();
		
	// Neighborhood
		this.neighborhoodId = park.getNeighbourhoodId();
		this.neighborhoodName = park.getName();
		this.url = park.getUrl();
		
	// Facilities
		this.facilityList = park.getFacilityList();
		
	// Preference Stuff
		this.rating = rating;
		this.comment = comment;
		this.time = time;
		
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if (obj==null) return false;
		else if (!(obj instanceof PreferencePark)) return false;
		PreferencePark prefPark = (PreferencePark) obj;
		if (this.parkId == prefPark.getParkId()) return true;
		else return false;
	}
	
	
	public void addFacility(Facility facility) {
		facilityList.add(facility);
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

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return name+":"+parkId;
	}

	public void setTime(String string) {
		// TODO Auto-generated method stub
		this.time = string;
	}
	

	public String getTime() {
		// TODO Auto-generated method stub
		return time;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
}
