package com.grasshoppers.parkfinder.server;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.grasshoppers.parkfinder.client.ParkSearchService;
import com.grasshoppers.parkfinder.client.modeldata.Park;
import com.grasshoppers.parkfinder.client.modeldata.PreferencePark;
import com.grasshoppers.parkfinder.client.modeldata.User;
import com.grasshoppers.parkfinder.client.widget.weather.Weather;
import com.grasshoppers.parkfinder.model.ParkModel;
import com.grasshoppers.parkfinder.model.UserModel;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class ParkSearchServiceImpl extends RemoteServiceServlet implements
		ParkSearchService {

	UserModel userModel = new UserModel();
	ParkModel parkModel = new ParkModel();
	
	@Override
	public List<Park> findParkServer(String name, String neighbourhood,
		String facility) throws IllegalArgumentException {
		 List<Park> parks = new ParkModel().findParks(name, neighbourhood, facility);
		return parks;
	}
	
	public List<PreferencePark> findPrefParks(int UID) throws IllegalArgumentException {
		
		  List<PreferencePark> parks = userModel.getParkRatings(UID);
		return parks;
	}


	public User getUser(String username, String password) {
		User user = userModel.getUser(username, password);
		
		HttpServletRequest httpServletRequest = this.getThreadLocalRequest();
        HttpSession session = httpServletRequest.getSession(true);
        session.setAttribute("user", user);
		
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
	

	@Override
	public Boolean createNewUser(String name, String password, String firstName,
			String lastName, String address, String city, String province,
			String country) {
		
		return userModel.createNewUser(
				name, password, firstName, lastName, address, city, province, country
				);
		
	}

	@Override
	public Boolean createNewParkRating(int userId, int parkId, int rating,
			String comment, String time) {
		return userModel.createNewParkRating(userId, parkId, rating, comment, time);
	}

	@Override
	public Boolean deleteParkRating(int userId, int parkId) {
		// TODO Auto-generated method stub
		return userModel.deleteParkRating(userId, parkId);

	}

	public Boolean modifyParkRating(int userId, int parkId, int rating,
			String comment, String time) {
		return userModel.modifyParkRating(userId, parkId, rating, comment, time);
	}
	
	@Override
	public List<Weather> getWeathers() {
		WeatherParser tempWP = new WeatherParser();
		return tempWP.get();
		
	}
	
}
