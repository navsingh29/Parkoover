package com.grasshoppers.parkfinder.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.grasshoppers.parkfinder.client.modeldata.Facility;
import com.grasshoppers.parkfinder.client.modeldata.Park;
import com.grasshoppers.parkfinder.client.modeldata.PreferencePark;
import com.grasshoppers.parkfinder.client.modeldata.User;

public class ModelFactory {

	public static Park makePark(ResultSet rs) {
		Park park = new Park();
		try{
		
		
		park.setParkId(rs.getInt("Park_Id"));
		park.setName(rs.getString("name"));
		park.setOfficial(rs.getInt("official"));
		park.setStreet_number(rs.getInt("street_number"));
		park.setStreet_name(rs.getString("street_name"));
		park.setEw_street(rs.getString("ew_street"));
		park.setNs_street(rs.getString("ns_street"));
		park.setMap_x_loc(rs.getDouble("map_x_loc"));
		park.setMap_y_loc(rs.getDouble("map_y_loc"));
		park.setHectares(rs.getDouble("hectares"));
		park.setNeighbourhoodId(rs.getInt("Neighborhood_Id"));
		park.setNeighbourhoodName(rs.getString("Neighborhood_Name"));
		park.setUrl(rs.getString("url"));
		park.setRating(rs.getDouble("rating"));
		park.setCount(rs.getInt("count"));
		
		park.addFacility(ModelFactory.makeFacility(rs));
		
		} catch (SQLException e){
			e.printStackTrace();
		}
		return park;
	}
	
	public static Facility makeFacility(ResultSet rs) {
		
		Facility facility = new Facility();
		try {
		facility.setFacilityId(rs.getInt("Facility_Id"));
		facility.setType(rs.getString("type"));
		facility.setFeature(rs.getString("feature"));
		facility.setLocation(rs.getString("location"));
		facility.setNote(rs.getString("note"));
		facility.setSummer_hours(rs.getString("summer_hours"));
		facility.setWinter_hours(rs.getString("winter_hours"));
		facility.setCount(rs.getInt("count"));
		} catch (SQLException e){
			e.printStackTrace();
		}
		return facility;
		
	}
	
	
	public static PreferencePark makePreferencPark(ResultSet rs) {
		PreferencePark park = new PreferencePark();
		try{
		park.setParkId(rs.getInt("Park_Id"));
		park.setName(rs.getString("name"));
		park.setOfficial(rs.getInt("official"));
		park.setStreet_number(rs.getInt("street_number"));
		park.setStreet_name(rs.getString("street_name"));
		park.setEw_street(rs.getString("ew_street"));
		park.setNs_street(rs.getString("ns_street"));
		park.setMap_x_loc(rs.getDouble("map_x_loc"));
		park.setMap_y_loc(rs.getDouble("map_y_loc"));
		park.setHectares(rs.getDouble("hectares"));
		
		park.setNeighbourhoodId(rs.getInt("Neighborhood_Id"));
		park.setNeighbourhoodName(rs.getString("Neighborhood_Name"));
		park.setUrl(rs.getString("url"));
		
		park.setRating(rs.getInt("rating"));
		park.setCount(rs.getInt("count"));
		park.setComment(rs.getString("comment"));
		park.setTime(rs.getString("upd_time"));
		park.addFacility(ModelFactory.makeFacility(rs));
		
		} catch (SQLException e){
			e.printStackTrace();
		}
		return park;
	}
	
	public static User makeUser(ResultSet rs) {
		User user = new User();
		try {
		int id = rs.getInt("id");
		String user_name = rs.getString("user_name");
		String fname = rs.getString("fname");
		String lname = rs.getString("lname");
		String address = rs.getString("address");
		String city = rs.getString("city");
		String province = rs.getString("Province");
		String country = rs.getString("Country");
		
		user.setId(id);
		user.setUser_name(user_name);
		user.setFname(fname);
		user.setLname(lname);
		user.setAddress(address);
		user.setCity(city);
		user.setProvince(province);
		user.setCountry(country);

		} catch (SQLException e){
			e.printStackTrace();
		}
		return user;
	}
	
	
	
	
}
