package com.grasshoppers.parkfinder.client.modeldata;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Park implements Serializable{

	
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
	private List<Facility> facilityList;
	
	// Average Rating
	private double rating;
	private int count;
	
	public Park() {
		facilityList = new ArrayList<Facility>();
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

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return name+":"+parkId;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
	public static Comparator<Park> ParkNameComparator 
	    = new Comparator<Park>() {
	
		public int compare(Park p1, Park p2) {
	
			String parkName1 = p1.getName().toUpperCase();
			String parkName2 = p2.getName().toUpperCase();
	
			return parkName2.compareTo(parkName1);
		}	
	};	
	
	public static Comparator<Park> ParkRatingComparator 
    	= new Comparator<Park>() {

		public int compare(Park p1, Park p2) {

			double parkRating1 = p1.getRating();
			double parkRating2 = p2.getRating();

			return Double.compare(parkRating2, parkRating1);
		}	
	};	
	
	public static Comparator<Park> ParkSizeComparator 
    	= new Comparator<Park>() {

		public int compare(Park p1, Park p2) {

			double parkSize1 = p1.getHectares();
			double parkSize2 = p2.getHectares();
			
			return Double.compare(parkSize2, parkSize1);
		}	
	};	

}
