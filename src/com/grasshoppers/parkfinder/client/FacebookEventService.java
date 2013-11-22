package com.grasshoppers.parkfinder.client;

import java.util.Date;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("facebookevent")
public interface FacebookEventService extends RemoteService {

	boolean makeEvent(String event, String location,String description, Date starTime, Date endTime);

	boolean makeEvent(String token, String event, String location,String description, Date startTime, Date endTime);
	
	
}
