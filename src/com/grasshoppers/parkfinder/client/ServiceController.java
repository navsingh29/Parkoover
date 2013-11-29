package com.grasshoppers.parkfinder.client;


import java.util.Date;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.Widget;
import com.grasshoppers.parkfinder.client.modeldata.Park;
import com.grasshoppers.parkfinder.client.modeldata.PreferencePark;
import com.grasshoppers.parkfinder.client.modeldata.User;
import com.grasshoppers.parkfinder.client.widget.weather.Weather;
import com.grasshoppers.parkfinder.client.widget.weather.WeatherParser;

public class ServiceController {

	private ParkSearchServiceAsync service;
	private FacebookEventServiceAsync facebookEventservice;
	private GUIController maingui;
	private List<Weather> weathers = null;

	public ServiceController() {
		String url = GWT.getModuleBaseURL() + "parksearch";
		this.service = GWT.create(ParkSearchService.class);
		ServiceDefTarget endpoint = (ServiceDefTarget) this.service;
		endpoint.setServiceEntryPoint(url);
		this.maingui = new GUIController(this);
		
		this.facebookEventservice = GWT.create(FacebookEventService.class);
		String url2 = GWT.getModuleBaseURL() + "facebookevent";
		ServiceDefTarget endpoint2 = (ServiceDefTarget) this.facebookEventservice;
		endpoint2.setServiceEntryPoint(url2);
		
	}

	public Widget getGUIController() {
		return maingui;
	}
	
	
	public void getParkList(String park, String neighborhood, String facility) {
		
			service.findParkServer(park, neighborhood, facility, new AsyncCallback<List<Park>>(){

			@Override
			public void onFailure(Throwable caught) {
				
				
			}

			@Override
			public void onSuccess(List<Park> result) {
				maingui.displayParks(result);
		
			}
			
			
		});
		
	}
	
	/*
	public void getPrefList(int UID) {
		
		service.findPrefParks(UID, new AsyncCallback<List<PreferencePark>>(){

		@Override
		public void onFailure(Throwable caught) {
			
			
		}

		@Override
		public void onSuccess(List<PreferencePark> result) {
			maingui.goToPrefList(result);
	
		}
		
		
	});
	
}
*/
	public void getUserLogIn(String username, String password, boolean remember) {
		
		
		service.getUser(username, password, new AsyncCallback<User>() {
			
			@Override
			public void onFailure(Throwable caught) {
				
				
			}

			@Override
			public void onSuccess(User result) {
				maingui.signIntoAcc(result, false);
				String sessionID = result.getUser_name();
                final long DURATION = 1000 * 60 * 60 * 24 * 1;
                Date expires = new Date(System.currentTimeMillis() + DURATION);
                Cookies.setCookie("sid", sessionID, expires, null, "/", false);
			}
		});
	}
	
	public void getSearchAssets() {
		
		service.getAllHoodNames(new AsyncCallback<List<String>>() {
			
			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Error getting neighborhood names.");
				
			}

			@Override
			public void onSuccess(List<String> result) {
		//		maingui.setHoodList(result);
				
			}

		});
		
		service.getAllFacTypes(new AsyncCallback<List<String>>() {
			
			@Override
			public void onFailure(Throwable caught) {
				
				Window.alert("Error getting facility names.");
			}

			@Override
			public void onSuccess(List<String> result) {
		//		maingui.setFacList(result);
				maingui.goToSearch();
			}

		});
		
	}
	
	public void createNewUser(String name, String password, String firstName, String lastName, String address, String city, String province, String country) {
		
		service.createNewUser(name, password, firstName, lastName, address, city, province, country, new AsyncCallback<Boolean>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(Boolean result) {
				
				if (result)
				maingui.warnPopup("New User Account Created Successfully.");
				else 
				maingui.warnPopup("Could not create new user account. Try a different username.");
			}
			
			
		});
	}
	
	public void createNewParkRating(int userId, int parkId, int rating, String comment, String time) {
		
		service.createNewParkRating(userId, parkId, rating, comment, time, new AsyncCallback<Boolean>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(Boolean result) {
				// TODO Auto-generated method stub
				
			}
			
			
			
			
		});
		
	}

	
	public void deleteParkRating(int userId, int parkId) {
		service.deleteParkRating(userId, parkId, new AsyncCallback<Boolean>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(Boolean result) {
				// TODO Auto-generated method stub
				
			}
			
			
			
		});
		
	}

	public void modifyRating(int userId, int parkId, int rating,
			String comment, String time, PreferencePark park) {
		
		service.modifyParkRating(userId, parkId, rating, comment, time, new AsyncCallback<Boolean>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(Boolean result) {
				// TODO Auto-generated method stub
				
			}
			
			
			
			
		});
		
	}

	public void makeFacebookEvent(String token, String event, String location,String description, Date starTime, Date endTime) {
		facebookEventservice.makeEvent(token, event, location, description, starTime, endTime, new AsyncCallback<Boolean>() {

			@Override
			public void onFailure(Throwable caught) {
			//	Window.alert("An error occured. Your Facebook Login may have expired or your event time may be invalid.");
				Window.alert(caught.getMessage());
			}

			@Override
			public void onSuccess(Boolean result) {
				if (result)
					Window.alert("Event successfully created");
				else 
					Window.alert("An error occured. Your Facebook Login may have expired or the event dates may be invalid. Please re-login and/or try again.");
				
				
			}
			
			
		});
		
	}

	public void logout() {
		LoginService.Util.getInstance().logout(new AsyncCallback<Boolean>(){

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(Boolean result) {
				
				
			}
			
			
		});
		
	}

	/*public void getWeatherData() {
		service.getWeathers(new AsyncCallback<List<Weather>>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Could not retrieve weather data.");
				
			}

			@Override
			public void onSuccess(List<Weather> result) {
				maingui.setWeathers(result);
			}
			
			
			
		});
		
	}
*/
	public void getWeatherSource() {
		service.getWeatherData(new AsyncCallback<String>(){

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Could not retrieve weather source.");
				
			}

			@Override
			public void onSuccess(String result) {
				maingui.setWeathers(new WeatherParser(result).get());
				
			}
			
		});
		
	}


	
}
