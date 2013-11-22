package com.grasshoppers.parkfinder.client;

import java.util.Date;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface FacebookEventServiceAsync {
	
	void makeEvent(String event, String location,String description, Date starTime, Date endTime,AsyncCallback<Boolean> callback);

}
