package com.grasshoppers.parkfinder.client.widget.weather;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


public class WeatherParser extends DefaultHandler {

	//fixed url (for now)
	private String 			srcLink;
	//meta
	private String 			city;
	private String 			country;
	
	private double 			retTime;
	private Date 			sunriseTime;
	private Date 			sunsetTime;
	private List<Weather> 	weatherList;
	
	private Weather			tempWeather;
	private String			tempStore;
	
	public  WeatherParser() {
		srcLink = "http://api.openweathermap.org/data/2.5/"
					+"forecast/daily?q=Vancouver&mode=xml&units=metric&cnt=7";
		weatherList = new ArrayList<Weather>(7);
		parse();
	}
	
	public  WeatherParser(String URL) {
		srcLink = URL;
		weatherList = new ArrayList<Weather>();
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
		if (qName.equalsIgnoreCase("time")) {
			String day = atts.getValue("day");
			tempWeather = new Weather(day);
		} else if (qName.equalsIgnoreCase("symbol")) {
			tempWeather.setGen(atts.getValue("name").toString());
		} else if (qName.equalsIgnoreCase("precipitation")) {
			if (atts.getLength() > 0) {
				tempWeather.setPrec(Double.parseDouble(atts.getValue("value")),	
						atts.getValue("type").toString());
			} else {
				tempWeather.setPrec(-1, "clear");
			}
		} else if (qName.equalsIgnoreCase("windDirection")) {
			tempWeather.setWindDir(Integer.parseInt(atts.getValue("deg")),
					atts.getValue("name").toString());
		} else if (qName.equalsIgnoreCase("windSpeed")) {
			tempWeather.setWindSpeed(Double.parseDouble(atts.getValue("mps")),
					atts.getValue("name"));
		} else if (qName.equalsIgnoreCase("temperature")) {
			tempWeather.setTemp(Double.parseDouble(atts.getValue("day")),
					Double.parseDouble(atts.getValue("min")),
					Double.parseDouble(atts.getValue("max")),
					Double.parseDouble(atts.getValue("night")),
					Double.parseDouble(atts.getValue("eve")),
					Double.parseDouble(atts.getValue("morn")));
		} else if (qName.equalsIgnoreCase("pressure")) {
			tempWeather.setPressure(Double.parseDouble(atts.getValue("value")),
					atts.getValue("unit").toString());
		} else if (qName.equalsIgnoreCase("humidity")) {
			tempWeather.setHumidity(Double.parseDouble(atts.getValue("value")),
					atts.getValue("unit").toString());
		} else if (qName.equalsIgnoreCase("clouds")) {
			tempWeather.setCloud(atts.getValue("value").toString(), Integer.parseInt(atts.getValue("all")),
					atts.getValue("unit").toString());
		} else if (qName.equalsIgnoreCase("sun")) {
			this.sunriseTime 	= parseSunTime(atts.getValue("rise"));
			this.sunsetTime		= parseSunTime(atts.getValue("set"));	
		}
	}
	
	@Override
	public void endElement (String uri, String localName, String qName) throws SAXException {
		//basic element processing
		if (qName.equalsIgnoreCase("time")) {
			weatherList.add(tempWeather);
		} else if (qName.equalsIgnoreCase("calctime")) {
			this.retTime = Double.parseDouble(tempStore);
		} else if (qName.equalsIgnoreCase("name")) {
			this.city = tempStore;
		} else if (qName.equalsIgnoreCase("country")) {
			this.country = tempStore;
		}
	}
	
	@Override
	public void characters (char[] ch, int start, int length) throws SAXException{
		tempStore = new String(ch, start, length);
	}
	
	public String getLocation () {
		return this.city +", "+ this.country;
	}
	
	public double getRetTime() {
		return retTime;
	}

	public Date getSunriseTime() {
		return sunriseTime;
	}

	public Date getSunsetTime() {
		return sunsetTime;
	}

	public List<Weather> get() {
		return weatherList;
	}

	private Date parseSunTime (String time) {
		Date date = null;
		try {
			date = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss").parse(time);
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;	
	}
}
