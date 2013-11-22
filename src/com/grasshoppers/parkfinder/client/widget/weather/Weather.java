package com.grasshoppers.parkfinder.client.widget.weather;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Weather {

	private String 	day;
	
	private String 	generalWeather;
	
	private double	precVal;
	private String 	precType;
	
	private int 	windDeg;
	private String	windDir;
	private double	windSpeed;
	private String	windType;
	
	private double 	tempDay;
	private double 	tempMin;	
	private double 	tempMax;	
	private double 	tempNight;	
	private double 	tempEve;	
	private double 	tempMorn;	
	
	private double	pressure;
	private String	presUnit;
	
	private double	humidity;
	private String	humidUnit;
	
	private String	cloudsType;
	private int		cloudsAmount;
	private String	cloudsUnit;
	
	public Weather(String day) {
		this.day = day;
	}
	
	public void setGen(String gen) {
		this.generalWeather = gen;
	}
	
	public String getGen() {
		return this.generalWeather;
	}
	
	/*
	public void setDay(String day) {
		try {
			Date date = new SimpleDateFormat("yyyy-MM-dd").parse(day);
			this.day  = date;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	*/
	
	public String getDay() {
		return this.day;
	}
	
	public void setPrec(double val, String type) {
		this.precVal 	= val;
		this.precType 	= type;
	}
	
	public String getPrec() {
		if (this.precVal != -1) {
			return Double.toString(this.precVal) +"mm "+ this.precType;
		} else {
			return "None";
		}
	}
	
	public void setWindDir(int deg, String dir) {
		this.windDeg 	= deg;
		this.windDir	= dir;
	}
	
	public void setWindSpeed(double speed, String type) {
		this.windSpeed	= speed;
		this.windType	= type;
	}
	
	public String getWind() {
		return this.windType +", "+ this.windSpeed 
				+"m/s @ "+ this.windDeg + " degrees " + this.windDir;
	}
	
	public void setTemp(double day, double min, double max, double night, double eve, double morn) {
		this.tempDay 	= day;
		this.tempMin 	= min;
		this.tempMax 	= max;
		this.tempNight	= night;
		this.tempEve 	= eve;
		this.tempMorn	= morn;
	}
	
	public String getTempDay() {
		return "Average: "+ Double.toString(this.tempDay)
				+"C, Min: "+ Double.toString(this.tempMin)
				+"C, Max: "+ Double.toString(this.tempMax)
				+"C";
	}
	
	public String getTempOther() {
		return "Night: "+ Double.toString(this.tempNight)
				+"C, Evening: "+ Double.toString(this.tempEve)
				+"C, Morning: "+ Double.toString(this.tempMorn)
				+"C";
	}
	
	public void setPressure(double val, String unit) {
		this.pressure	= val;
		this.presUnit	= unit;
	}
	
	public String getPressure() {
		return Double.toString(this.pressure) + this.presUnit;
	}
	
	public void setHumidity(double val, String unit) {
		this.humidity	= val;
		this.humidUnit	= unit;
	}
	
	public String getHumidity() {
		return Double.toString(this.humidity) + this.humidUnit;
	}
	
	public void setCloud(String type, int amount, String unit) {
		this.cloudsType		= type;
		this.cloudsAmount	= amount;
		this.cloudsUnit		= unit;
	}
	
	public String getCloud() {
		return Integer.toString(this.cloudsAmount) + this.cloudsUnit 
				+" "+ this.cloudsType;
	}
}
