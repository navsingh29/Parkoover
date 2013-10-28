package com.grasshoppers.xmlparser.core;

public class Facility {

	private int 		archetype;
	private int			count;
	private String		type;
	private String		url;
	private String		feature;
	private String		location;
	private String		note;
	private String		summerHour;
	private String		winterHour;
	
	public Facility(int archetype) {
		super();
		if (archetype >= 0 || archetype <= 2)
			this.archetype = archetype;
	}

	public int getArc() {
		return archetype;
	}
	
	public int getCount() {
		return count;
	}

	public void setCount(String count) {
		if (this.archetype == 0)
			this.count = Integer.parseInt(count);
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		if (this.archetype == 0)
			this.type = type;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		if (this.archetype == 0)
			this.url = url;
	}

	public String getFeature() {
		return feature;
	}

	public void setFeature(String feature) {
		if (this.archetype == 2)
			this.feature = feature;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		if (this.archetype == 1)
			this.location = location;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		if (this.archetype == 1)
			this.note = note;
	}

	public String getSummerHour() {
		return summerHour;
	}

	public void setSummerHour(String summerHour) {
		if (this.archetype == 1)
			this.summerHour = summerHour;
	}

	public String getWinterHour() {
		return winterHour;
	}

	public void setWinterHour(String winterHour) {
		if (this.archetype == 1)
			this.winterHour = winterHour;
	}

	
	
	
}
