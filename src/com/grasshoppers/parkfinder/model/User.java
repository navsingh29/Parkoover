package com.grasshoppers.parkfinder.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class User {

	private int id;
	private String user_name;
	private String fname;
	private String lname;
	private String address;
	private String city;
	private String province;
	private String country;
	
	List<PreferencePark> preferenceList = new ArrayList<PreferencePark>();
	
	public User(ResultSet rs) {
		try {
			id = rs.getInt("id");
			user_name = rs.getString("user_name");
			fname = rs.getString("fname");
			lname = rs.getString("lname");
			address = rs.getString("address");
			city = rs.getString("city");
			province = rs.getString("Province");
			country = rs.getString("Country");
			 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void addPreferences(List<PreferencePark> list) {
		preferenceList.addAll(list);
	}
	
	public void addPreferences(PreferencePark park) {
		preferenceList.add(park);
	}

	public int getId() {
		return id;
	}

	public String getUser_name() {
		return user_name;
	}

	public String getFname() {
		return fname;
	}

	public String getLname() {
		return lname;
	}

	public String getAddress() {
		return address;
	}

	public String getCity() {
		return city;
	}

	public String getProvince() {
		return province;
	}

	public String getCountry() {
		return country;
	}

	public List<PreferencePark> getPreferenceList() {
		return preferenceList;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public void setPreferenceList(List<PreferencePark> preferenceList) {
		this.preferenceList = preferenceList;
	}

	@Override
	public String toString() {
		
		return user_name+":"+id;
	}
	
	
}
