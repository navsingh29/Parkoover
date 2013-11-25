package com.grasshoppers.parkfinder.client.widget.weather;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;



import com.google.gwt.xml.client.DOMException;  
import com.google.gwt.xml.client.Document;  
import com.google.gwt.xml.client.Element;  
import com.google.gwt.xml.client.Node;  
import com.google.gwt.xml.client.XMLParser;  
import com.grasshoppers.parkfinder.client.GUIController;

public class WeatherParser {

	//fixed url (for now)
	private String 			srcLink;
	private String			source;
	//meta
	private String 			city;
	private String 			country;
	
	private double 			retTime;
	private List<Weather> 	weatherList;
	
	private Weather			tempWeather;
	
	public  WeatherParser(String source) {
		srcLink = "http://api.openweathermap.org/data/2.5/"
					+"forecast/daily?q=Vancouver&mode=xml&units=metric&cnt=7";
		weatherList = new ArrayList<Weather>(7);
		this.source = source;//controller.getWeatherSource();
		parse(source);
	}
	
	
	
private void parse(String source) {
		
		try {
			//srcLink -> DOM
			Document doc = XMLParser.parse(source);
			
			
			//parsing starts here
			
			city = doc.getElementsByTagName("name").item(0).getFirstChild().getNodeValue();  

			country = doc.getElementsByTagName("country").item(0).getFirstChild().getNodeValue();
			retTime = Double.parseDouble(doc.getElementsByTagName("calctime").item(0).getFirstChild().getNodeValue());
			
			
			int i = 0;
			while ((doc.getElementsByTagName("time").item(i)) != null) {

				Node timeNode = doc.getElementsByTagName("time").item(i); 
				String day = ((Element)timeNode).getAttribute("day");  
				tempWeather = new Weather(day);
				
				Node symbolNode = doc.getElementsByTagName("symbol").item(i);
				tempWeather.setGen(((Element)symbolNode).getAttribute("name"));
				
				Node precNode = doc.getElementsByTagName("precipitation").item(i);
				if (precNode.hasAttributes()) {
					tempWeather.setPrec(Double.parseDouble(((Element)precNode).getAttribute("value")), 
										((Element)precNode).getAttribute("type"));
				} else {
					tempWeather.setPrec(-1, "clear");					
				}
				
				Node windDirNode = doc.getElementsByTagName("windDirection").item(i);
				tempWeather.setWindDir(Integer.parseInt(((Element)windDirNode).getAttribute("deg")),
										((Element)windDirNode).getAttribute("name"));
				
				Node windSpeedNode = doc.getElementsByTagName("windSpeed").item(i);
				tempWeather.setWindSpeed(Double.parseDouble(((Element)windSpeedNode).getAttribute("mps")),
											((Element)windSpeedNode).getAttribute("name"));
				
				Node tempNode = doc.getElementsByTagName("temperature").item(i);
				tempWeather.setTemp(Double.parseDouble(((Element)tempNode).getAttribute("day")),
									Double.parseDouble(((Element)tempNode).getAttribute("min")), 
									Double.parseDouble(((Element)tempNode).getAttribute("max")),
									Double.parseDouble(((Element)tempNode).getAttribute("night")),
									Double.parseDouble(((Element)tempNode).getAttribute("eve")),
									Double.parseDouble(((Element)tempNode).getAttribute("morn")));
				
				Node presNode = doc.getElementsByTagName("pressure").item(i);
				tempWeather.setPressure(Double.parseDouble(((Element)presNode).getAttribute("value")),
										((Element)presNode).getAttribute("unit"));
				
				Node humidNode = doc.getElementsByTagName("humidity").item(i);
				tempWeather.setHumidity(Integer.parseInt(((Element)humidNode).getAttribute("value")),
										((Element)humidNode).getAttribute("unit"));
				
				Node cloudNode = doc.getElementsByTagName("clouds").item(i);
				tempWeather.setCloud(((Element)cloudNode).getAttribute("value"),
									Integer.parseInt(((Element)cloudNode).getAttribute("all")),
									((Element)cloudNode).getAttribute("unit"));
				
				weatherList.add(i, tempWeather);
				i++;
				
			}
			
		} catch (DOMException e) {
			System.out.println("Bad Bad XML! " + e);
		}
		
	}
	
	public String getLocation () {
		return this.city +", "+ this.country;
	}
	
	public double getRetTime() {
		return retTime;
	}

	public List<Weather> get() {
		return weatherList;
	}
	

}
