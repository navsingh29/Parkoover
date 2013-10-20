package XMLParser;

import java.util.List;

public class QueryCore {

	//This class is for converting Park Object into query strings.
	
	public final static String URL = "ftp://webftp.vancouver.ca/opendata/xml/parks_facilities.xml";
	private ParserCore pc;
	private List<Park> parkList;
	
	//TODO: constructor, specify db connection here
	public void query() {
		
		
		pc = new ParserCore();
		pc.processSource(URL);
		parkList = pc.get();
		processTables();
		processTuples();
	}

	//TODO: should be pretty easy to implement
	//just send the db strings of create table statements
	private void processTables() {
		//Park table
		
		
		//Facilities
		
		
		//Neighborhood

	}
	
	private void processTuples() {
		for (Park p : parkList) {
			//do basic attribute move to db here
			
			

			Neighborhood nh = p.getHood();
			String nName = nh.getName();
			String nURL = nh.getUrl();
			//do neighborhood here
			
			
			//do facilities here
			for (Facility f : p.getFacList()) {
				//make sure to put arc into db for referencing convenience
				switch(f.getArc()) {
				case 0:
					//facility: count, type, url
					break;
				case 1:
					//washroom: location, notes, sHours, wHours
					break;
				case 2:
					//special feature
					break;
				default:
					break;
				}
			}
		}
	}


	
}
