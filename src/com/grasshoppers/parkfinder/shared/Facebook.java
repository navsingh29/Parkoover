package com.grasshoppers.parkfinder.shared;

import java.util.Date;

import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.FacebookType;
import com.restfb.types.User;

public class Facebook {

	public void newMessage(FacebookClient fbClient) {
		FacebookType publishMessageResponse =
				fbClient.publish("me/feed", FacebookType.class,
				    Parameter.with("message", "RestFB test"));
		System.out.println("Published message ID: " + publishMessageResponse.getId());
		
	}
	
	public static void newEvent(FacebookClient fbClient) {
		Date now = new Date();
		long endLong =  now.getTime()+1000*60*60*24*2;
		Date twoDaysFromNow = new Date(endLong);
		
		
		FacebookType publishEventResponse = fbClient.publish("me/events", FacebookType.class,
				  Parameter.with("name", "Baseball"), Parameter.with("start_time", now),
				    Parameter.with("end_time", twoDaysFromNow), Parameter.with("description", "lets play some baseball"),
				    Parameter.with("location", "Stanley Park"),Parameter.with("location_id", "2000 W Georgia Street, Vancouver, BC"));

				System.out.println("Published event ID: " + publishEventResponse.getId());
		
	}
	
	public static void main(String[] args) {
		
		FacebookClient fbClient = new DefaultFacebookClient("CAACEdEose0cBAHn3M5vvIESzGhVbLlK2OjgabZApjfj3orQWJtTdfznL84rGGBxzCf6uULmqdoZCZCFaMTgzhTxduPkbiV7bZBzdP242U3CcgwNhb5vNM7ZARZC3OYoxFQtMzh4cR5bAYVuiGfDcxSS8aOjZCudsyygNMGukTQle66biltKEG43ZAjZCQcmDSpeNFZAFfe1p8aFwZDZD");
		User user = fbClient.fetchObject("me", User.class);
		System.out.println(user.getBio());
		newEvent(fbClient);
		
	
		
		
	}
	
}
