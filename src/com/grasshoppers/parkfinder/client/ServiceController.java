package com.grasshoppers.parkfinder.client;


import java.util.List;
import java.util.Random;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.Widget;

public class ServiceController {

	private ParkSearchServiceAsync service;
	private GUIController maingui;
//	Random rnd = new Random();

	public ServiceController(String url) {
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
				maingui.setTextBox(caught.toString());
				
			}

			@Override
			public void onSuccess(String result) {
				maingui.setTextBox(result);
				
			}
			
		});
	}
	
	public void getParkList() {
		
		service.findParkServer("ridge", null, null, new AsyncCallback<List<Park>>(){

			@Override
			public void onFailure(Throwable caught) {
				maingui.setResultLabel(caught.toString());
				
			}

			@Override
			public void onSuccess(List<Park> result) {
				int size = result.size();
				int i = size-1;//rnd.nextInt(size);
				maingui.setResultLabel(size+" parks found. Random park found is:"+ result.get(i).getName());
			}
			
			
		});
		
	}
	
	
	
	
}
