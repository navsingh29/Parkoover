package com.grasshoppers.parkfinder.server;

import java.util.List;

import com.grasshoppers.parkfinder.client.ParkSearchService;
import com.grasshoppers.parkfinder.client.modeldata.Park;
import com.grasshoppers.parkfinder.client.modeldata.PreferencePark;
import com.grasshoppers.parkfinder.client.modeldata.User;
import com.grasshoppers.parkfinder.model.ParkModel;
import com.grasshoppers.parkfinder.model.UserModel;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class ParkSearchServiceImpl extends RemoteServiceServlet implements
		ParkSearchService {

	@Override
	public List<Park> findParkServer(String name, String neighbourhood,
		String facility) throws IllegalArgumentException {
		 List<Park> parks = new ParkModel().findParks(name, neighbourhood, facility);
		return parks;
	}
	
	public List<PreferencePark> findPrefParks(int UID) throws IllegalArgumentException {
		
		  List<PreferencePark> parks = new UserModel().getParkRatings(UID);
		return parks;
	}

	
	public String getPark() {
	//	User user = UserModel.getUser("superman", "kryptonite");
		List<Park> parks = new ParkModel().findParks(null, "ridge","ball");
		
		return parks.get(0).getName();
	}

	public User getUser(String username, String password) {
		User user = new UserModel().getUser(username, password);
		return user;
	}
	
	public List<String> getAllFacTypes() {
		List<String> facList = new ParkModel().getAllFacilityTypes();
		return facList;
	}
	
	public List<String> getAllHoodNames() {
		List<String> hoodNames = new ParkModel().getAllNeighborhoodNames();
		return hoodNames;
	}
	
	public String retString (String string) {
		string = string.trim();
		if (string.equals(""))
			return null;
		else return string;
	}

	@Override
	public Boolean createNewUser(String name, String password, String firstName,
			String lastName, String address, String city, String province,
			String country) {
		
		return new UserModel().createNewUser(
				name, password, firstName, lastName, address, city, province, country
				);
		
	}

	@Override
	public String removeParkRating(int uid, int pid) {
		new UserModel().removeParkRating(uid, pid);
		return "";
		// TODO Auto-generated method stub
		
	}

	
	

}
