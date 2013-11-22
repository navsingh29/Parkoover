package com.grasshoppers.parkfinder.server;

import java.util.Date;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.grasshoppers.parkfinder.client.FacebookEventService;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.FacebookType;

@SuppressWarnings("serial")
public class FacebookEventServiceImpl extends RemoteServiceServlet implements FacebookEventService {

	@Override
	public boolean makeEvent(String event, String location,String description, Date startTime, Date endTime) {
		FacebookClient fbClient = new DefaultFacebookClient("CAACEdEose0cBAAxcqddZBbGFmif75YtNWZBQSlFAYNbEXZAtslWZAKiZCIcZAvVMvQnrjTJJdepuKiRD1GSv72aLjJ7FalRs9ltmSkDEpXGZBdLQNYB6lY1aJZCdDYXbyvEpIgaAwULb6dO2lvIGSoiKfI1FDk09UxCuZBMbSqWXq2MZB4ZC8t5xdBFWijPVkg4QsLs8M0B6421VwZDZD");
		
		Date now = new Date();
		long endLong =  now.getTime()+1000*60*60*24*2;
		Date twoDaysFromNow = new Date(endLong);
		
		
		FacebookType publishEventResponse = fbClient.publish("me/events", FacebookType.class,
				  Parameter.with("name", event), Parameter.with("start_time", startTime),
				    Parameter.with("end_time", endTime), Parameter.with("description", description),
				    Parameter.with("location", location));

				System.out.println("Published event ID: " + publishEventResponse.getId());
		
	
		
		return false;
	}

}
