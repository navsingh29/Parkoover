package XMLParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


public class ParserCore extends DefaultHandler {

	private List<Park> 		parkList;
	private String 			srcLink;
	//in case it changes = "ftp://webftp.vancouver.ca/opendata/xml/parks_facilities.xml";

	//stores element data
	private String 			tempStore;
	
	//temp store of element object
	private Park			tempPark;
	private Facility		tempFac;
	private Neighborhood 	tempHood;
	private List<Facility> 	facList;

	
	public void processSource (String url) {
		this.srcLink = url;
		parkList = new ArrayList<Park>(300);
		parse();
	}
	
	private void parse() {
		SAXParserFactory factory = SAXParserFactory.newInstance();
		
		try {
			SAXParser parser = factory.newSAXParser();
			parser.parse(srcLink, this);
		} catch (SAXException e){
			System.out.println("bad bad XML");;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void startElement (String uri, String localName, String qName, Attributes atts) throws SAXException {
		if (qName.equalsIgnoreCase("park")) {
			int parkID = Integer.parseInt(atts.getValue("ID"));
			tempPark = new Park(parkID);
		} else if (qName.equalsIgnoreCase("Neighbourhood")) {
			tempHood = new Neighborhood();
		} else if (qName.equalsIgnoreCase("Facilities")) {
			facList = new LinkedList<Facility>();
		} else if (qName.equalsIgnoreCase("Facility")) {
			tempFac = new Facility(0);
		} else if (qName.equalsIgnoreCase("Washroom")) {
			tempFac = new Facility(1);
		} else if (qName.equalsIgnoreCase("SpecialFeature")) {
			tempFac = new Facility(2);
		}
	}
	
	@Override
	public void endElement (String uri, String localName, String qName) throws SAXException {
		//basic element processing
		if (qName.equalsIgnoreCase("Park")) {
			parkList.add(tempPark);
		} else if (qName.equalsIgnoreCase("Name")) {
			tempPark.setName(tempStore);
		} else if (qName.equalsIgnoreCase("Official")) {
			tempPark.setOfficial(tempStore);
		} else if (qName.equalsIgnoreCase("StreetNumber")) {
			tempPark.setStreetNum(tempStore);
		} else if (qName.equalsIgnoreCase("StreetName")) {
			tempPark.setStreetName(tempStore);
		} else if (qName.equalsIgnoreCase("EWStreet")) {
			tempPark.setEwStreet(tempStore);
		} else if (qName.equalsIgnoreCase("NSStreet")) {
			tempPark.setNsStreet(tempStore);
		} else if (qName.equalsIgnoreCase("GoogleMapDest")) {
			tempPark.setLoc(tempStore);
		} else if (qName.equalsIgnoreCase("Hectare")) {
			tempPark.setHectare(tempStore);
		}
		
		//process neighborhood
		if (qName.equalsIgnoreCase("Neighbourhood")) {
			tempPark.setHood(tempHood);
			tempHood = null;
		} else if (qName.equalsIgnoreCase("NeighbourhoodName")) {
			tempHood.setName(tempStore);
		} else if (qName.equalsIgnoreCase("NeighbourhoodURL")) {
			tempHood.setUrl(tempStore);
		}
		
		//process facilities
		if (qName.equalsIgnoreCase("Facilities")) {
			tempPark.setFacList(facList);
			facList = null;
		} else if (qName.equalsIgnoreCase("Facility") || (qName.equalsIgnoreCase("Washroom"))) {
			if (tempFac != null) {
				facList.add(tempFac);
				tempFac = null;
			}
		} else if (qName.equalsIgnoreCase("SpecialFeature")) {
			tempFac = null;
			tempFac.setFeature(tempStore);
			facList.add(tempFac);
			tempFac = null;
		} else if (qName.equalsIgnoreCase("FacilityCount")) {
			tempFac.setCount(tempStore);
		} else if (qName.equalsIgnoreCase("FacilityType")) {
			tempFac.setType(tempStore);
		} else if (qName.equalsIgnoreCase("FacilityURL")) {
			tempFac.setUrl(tempStore);
		} else if (qName.equalsIgnoreCase("Location")) {
			tempFac.setLocation(tempStore);
		} else if (qName.equalsIgnoreCase("Notes")) {
			tempFac.setNote(tempStore);
		} else if (qName.equalsIgnoreCase("SummerHours")) {
			tempFac.setSummerHour(tempStore);
		} else if (qName.equalsIgnoreCase("WinterHours")) {
			tempFac.setWinterHour(tempStore);
		} 
		
		//process dummies
		if (qName.equalsIgnoreCase("AdvisoryText")) {
			tempPark.setAdvisoryText(tempStore);
		} else if (qName.equalsIgnoreCase("DateLast")) {
			tempPark.setAdvisoryDate(tempStore);
		} else if (qName.equalsIgnoreCase("URL")) {
			tempPark.setAdvisoryURL(tempStore);
		}
	}
	
	@Override
	public void characters (char[] ch, int start, int length) throws SAXException{
		tempStore = new String(ch, start, length);
	}
	
	public List<Park> get() {
		return parkList;
	}
}
