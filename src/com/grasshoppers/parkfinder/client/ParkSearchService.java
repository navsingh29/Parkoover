package com.grasshoppers.parkfinder.client;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.grasshoppers.parkfinder.model.Park;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("parksearch")
public interface ParkSearchService extends RemoteService {
//	List<Park> findParkServer(String name, String neighbourhood, String facility) throws IllegalArgumentException;
	String getPark();
	
	
}
