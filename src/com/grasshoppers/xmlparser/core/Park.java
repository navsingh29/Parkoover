package com.grasshoppers.xmlparser.core;

import java.util.List;

public class Park{

	private int 			id;
	private String 			name;
	//Note: 0/F means not official. 1/T means official
	private int 			isOfficial;
	private int				streetNum;
	private String			streetName;
	private String			ewStreet;
	private String			nsStreet;
	//parse coord set into independent doubles
	private double			xLoc;
	private double			yLoc;
	private double			hectare;
	private Neighborhood 	hood;
	private List<Facility>	facList;
	
	//dummy, not a single instance used it in dataset
	private String			advisoryText;
	private String			advisoryDate;
	private String			advisoryURL;
	
	public Park(int id) {
		super();
		this.id = id;
		//instantiate everything to avoid null pointer
		//not really necessary
//		this.name = "";
//		this.isOfficial = false;
//		this.streetNum = 0;
//		this.streetName = "";
//		this.ewStreet = "";
//		this.nsStreet = "";
//		this.xLoc = 0;
//		this.yLoc = 0;
//		this.hectare = 0;
//		this.hood = null;
//		this.facList = null;
//		this.advisoryText = "";
//		this.advisoryDate = null;
//		this.advisoryURL = "";
	}
	
	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int isOfficial() {
		return isOfficial;
	}

	//Note: 0/F means not official. 1/T means official
	public void setOfficial(String isOfficial) {
		if (Integer.parseInt(isOfficial) == 0)
			this.isOfficial = 0;
		else
			this.isOfficial = 1;
	}

	public int getStreetNum() {
		return streetNum;
	}

	public void setStreetNum(String streetNum) {
		this.streetNum = Integer.parseInt(streetNum);
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public String getEwStreet() {
		return ewStreet;
	}

	public void setEwStreet(String ewStreet) {
		this.ewStreet = ewStreet;
	}

	public String getNsStreet() {
		return nsStreet;
	}

	public void setNsStreet(String nsStreet) {
		this.nsStreet = nsStreet;
	}

	public double getxLoc() {
		return xLoc;
	}

	public double getyLoc() {
		return yLoc;
	}

	public void setLoc(String loc) {
		String[] xy = loc.split(",");
		xLoc = Double.parseDouble(xy[0]);
		yLoc = Double.parseDouble(xy[1]);		
	}
	
	public double getHectare() {
		return hectare;
	}

	public void setHectare(String hectare) {
		this.hectare = Double.parseDouble(hectare);
	}

	public Neighborhood getHood() {
		return hood;
	}

	public void setHood(Neighborhood hood) {
		this.hood = hood;
	}

	public List<Facility> getFacList() {
		return facList;
	}

	public void setFacList(List<Facility> facList) {
		this.facList = facList;
	}

	public String getAdvisoryText() {
		return advisoryText;
	}

	public void setAdvisoryText(String advisoryText) {
		this.advisoryText = advisoryText;
	}

	public String getAdvisoryDate() {
		return advisoryDate;
	}

	public void setAdvisoryDate(String advisoryDate) {
		this.advisoryDate = advisoryDate;
	}

	public String getAdvisoryURL() {
		return advisoryURL;
	}

	public void setAdvisoryURL(String advisoryURL) {
		this.advisoryURL = advisoryURL;
	}
}
