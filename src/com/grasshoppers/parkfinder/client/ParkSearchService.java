package com.grasshoppers.parkfinder.client;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.grasshoppers.parkfinder.client.modeldata.Park;
import com.grasshoppers.parkfinder.client.modeldata.PreferencePark;
import com.grasshoppers.parkfinder.client.modeldata.User;
import com.grasshoppers.parkfinder.client.widget.weather.Weather;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("parksearch")
public interface ParkSearchService extends RemoteService {
	List<Park> findParkServer(String name, String neighbourhood, String facility) throws IllegalArgumentException;
	User getUser(String username, String password);
	List<String> getAllFacTypes();
	List<String> getAllHoodNames();
	List<PreferencePark> findPrefParks(int UID);
	Boolean createNewUser(String name, String password, String firstName, String lastName, String address, String city, String province, String country);
	Boolean createNewParkRating(int userId, int parkId, int rating, String comment, String time);
	Boolean deleteParkRating(int userId, int parkId);
	Boolean modifyParkRating(int userId, int parkId, int rating, String comment, String time);
	List<Weather> getWeathers();
}
