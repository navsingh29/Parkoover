package com.grasshoppers.parkfinder.client;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface ParkSearchServiceAsync {
	void findParkServer(String name, String neighbourhood, String facility, AsyncCallback<List<Park>> callback)	throws IllegalArgumentException;
	void getPark(AsyncCallback<String> callback);
}
