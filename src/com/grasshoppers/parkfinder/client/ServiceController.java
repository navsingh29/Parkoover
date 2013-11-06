package com.grasshoppers.parkfinder.client;


import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.Widget;
import com.grasshoppers.parkfinder.client.modeldata.Park;
import com.grasshoppers.parkfinder.client.modeldata.PreferencePark;
import com.grasshoppers.parkfinder.client.modeldata.User;

public class ServiceController {

	private ParkSearchServiceAsync service;
	private GUIController maingui;

	public ServiceController() {
		String url = GWT.getModuleBaseURL() + "parksearch";
		this.service = GWT.create(ParkSearchService.class);
		ServiceDefTarget endpoint = (ServiceDefTarget) this.service;
		endpoint.setServiceEntryPoint(url);
		this.maingui = new GUIController(this);
		
	}

	public Widget getGUIController() {
		return maingui;
	}
	

	public void getParkName() {
		// TODO Auto-generated method stub
		service.getPark(new AsyncCallback<String>(){

			@Override
			public void onFailure(Throwable caught) {
				
				
			}

			@Override
			public void onSuccess(String result) {
				
				
			}
			
		});
	}
	
	public void getParkList(String park, String facility, String neighborhood) {
		
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
	public void getUserLogIn(String username, String password, boolean remember) {
		
		service.getAllHoodNames(new AsyncCallback<List<String>>() {
			
			@Override
			public void onFailure(Throwable caught) {
				
				
			}

			@Override
			public void onSuccess(List<String> result) {
				maingui.setHoodList(result);
				
			}

		});
		
		service.getAllFacTypes(new AsyncCallback<List<String>>() {
			
			@Override
			public void onFailure(Throwable caught) {
				
				
			}

			@Override
			public void onSuccess(List<String> result) {
				maingui.setFacList(result);
				
			}

		});
		
		service.getUser(username, password, new AsyncCallback<User>() {
			
			@Override
			public void onFailure(Throwable caught) {
				
				
			}

			@Override
			public void onSuccess(User result) {
				maingui.signIntoAcc(result, false);
				
			}
		});
	}
	
	public void getSearchAssets() {
		
		service.getAllHoodNames(new AsyncCallback<List<String>>() {
			
			@Override
			public void onFailure(Throwable caught) {
				
				
			}

			@Override
			public void onSuccess(List<String> result) {
				maingui.setHoodList(result);
				
			}

		});
		
		service.getAllFacTypes(new AsyncCallback<List<String>>() {
			
			@Override
			public void onFailure(Throwable caught) {
				
				
			}

			@Override
			public void onSuccess(List<String> result) {
				maingui.setFacList(result);
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
	
}
