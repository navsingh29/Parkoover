package com.grasshoppers.parkfinder.client;


import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.Widget;
import com.grasshoppers.parkfinder.client.modeldata.Park;

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
	
	
	
	
}
