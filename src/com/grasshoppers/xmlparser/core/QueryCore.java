package com.grasshoppers.xmlparser.core;

import java.util.List;

import com.grasshoppers.parkfinder.model.DBAdmin;

public class QueryCore {

	//This class is for converting Park Object into query strings.
	
	public final static String URL = "ftp://webftp.vancouver.ca/opendata/xml/parks_facilities.xml";
	private ParserCore pc;
	private static List<Park> parkList;
	
	//TODO: constructor, specify db connection here
	public QueryCore() {
		pc = new ParserCore(URL);
		parkList = pc.get();
		processTables();
		processTuples();
	}

	//TODO: should be pretty easy to implement
	//just send the db strings of create table statements
	private static void processTables() {
		DBAdmin.dropAllTables();
		
		DBAdmin.makeParkTable();
		DBAdmin.makeFacilityTable();
		DBAdmin.makeNeighborhoodTable();
		DBAdmin.makeHasFacilityTable();
		DBAdmin.makeInNeighborhoodTable();
	}
	
	private void processTuples() {
		for (Park p : parkList) {
			//do basic attribute move to db here
			DBAdmin.insertPark(p.getId(), p.getName(), p.isOfficial(), p.getStreetNum(), 
					p.getStreetName(), p.getEwStreet(), p.getNsStreet(), p.getxLoc(), 
					p.getyLoc(), p.getHectare());
			

			Neighborhood nh = p.getHood();
			String nName = nh.getName();
			String nURL = nh.getUrl();
			//do neighborhood here
			DBAdmin.insertNeighborhoodComplete(p.getId(), nName, nURL);
			
			
			//do facilities here
			for (Facility f : p.getFacList()) {
				//make sure to put arc into db for referencing convenience
				switch(f.getArc()) {
				case 0:
					//facility: count, type, url
					DBAdmin.insertFacilityComplete(p.getId(), f.getCount(), f.getType(), null, null, null, null);
					break;
				case 1:
					//washroom: location, notes, sHours, wHours
					DBAdmin.insertFacilityComplete(p.getId(), 1, "Washroom", null, f.getLocation(), f.getSummerHour(), f.getWinterHour());
					break;
				case 2:
					//special feature
					DBAdmin.insertFacilityComplete(p.getId(), 1, "Special Feature", f.getFeature(), null, null, null);
					break;
				default:
					break;
				}
			}
		}
	}


	public static void main(String[] args){
		// Create tables and test to see if they work.
		new QueryCore();

		 		}
	
	
}
