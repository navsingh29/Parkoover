package com.grasshoppers.parkfinder.model;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Facility implements Serializable {

	private int facilityId;
	private String type;
	private String feature;
	private String location;
	private String note;
	private String summer_hours;
	private String winter_hours;
	
	public Facility(ResultSet rs) {
		try{
			facilityId = rs.getInt("Facility_Id");
			type = rs.getString("type");
			feature = rs.getString("feature");
			location = rs.getString("location");
			note = rs.getString("note");
			summer_hours = rs.getString("summer_hours");
			winter_hours = rs.getString("winter_hours");
			} catch(SQLException e) {
				e.printStackTrace();
			}
		
	}

	public int getFacilityId() {
		return facilityId;
	}

	public String getType() {
		return type;
	}

	public String getFeature() {
		return feature;
	}

	public String getLocation() {
		return location;
	}

	public String getNote() {
		return note;
	}

	public String getSummer_hours() {
		return summer_hours;
	}

	public String getWinter_hours() {
		return winter_hours;
	}

	public void setFacilityId(int facilityId) {
		this.facilityId = facilityId;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setFeature(String feature) {
		this.feature = feature;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public void setSummer_hours(String summer_hours) {
		this.summer_hours = summer_hours;
	}

	public void setWinter_hours(String winter_hours) {
		this.winter_hours = winter_hours;
	}
	
}
