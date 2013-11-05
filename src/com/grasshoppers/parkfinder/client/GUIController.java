package com.grasshoppers.parkfinder.client;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.grasshoppers.parkfinder.client.GUI.Login;
import com.grasshoppers.parkfinder.client.GUI.PreferenceList;
import com.grasshoppers.parkfinder.client.GUI.Results;
import com.grasshoppers.parkfinder.client.GUI.Search;
import com.grasshoppers.parkfinder.client.GUI.Signup;
import com.grasshoppers.parkfinder.client.modeldata.Park;
import com.grasshoppers.parkfinder.client.modeldata.PreferencePark;

import com.grasshoppers.parkfinder.shared.StringMethods;

import com.grasshoppers.parkfinder.client.modeldata.User;

public class GUIController extends Composite{

	private HorizontalPanel horizontalPanel = new HorizontalPanel();
	private ServiceController service;

	private List<String> facList = null;


	private List<String> hoodList = null;

	public GUIController(ServiceController service) {
	initWidget(horizontalPanel);
	this.service = service;
	horizontalPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
	horizontalPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
	horizontalPanel.setSize("450px", "300px");
	
	Login log = new Login(this);
	horizontalPanel.add(log);
	
	}
	


//=============================================================================================================
//		GOTOs
//=============================================================================================================
		
		public void goToSignUp() {
			horizontalPanel.clear();
			Signup signup = new Signup();
			horizontalPanel.add(signup);
		}


		public void goToLogIn() {
			horizontalPanel.clear();
			Login login = new Login(this);
			horizontalPanel.add(login);
		}
		

		public void buttonToPrefList(int UID) {
			service.getPrefList(UID);
		}
		
		public void buttonToSearch() {
			service.getSearchAssets();
		}
		
//=============================================================================================================
//		Triggers
//=============================================================================================================
		
		public void buttonDoSignIn(String username, String password, Boolean rememberMe ) {
			service.getUserLogIn(username, password, rememberMe);
		}
		
		//DO NOT TOUCH: service callback
		public void setFacList(List<String> list) {
			this.facList = list;
		}
		
		//DO NOT TOUCH: service callback
		public void setHoodList(List<String> result) {
			this.hoodList = result;
			
		}
		
		//DO NOT TOUCH: service callback for signIN
		public void signIntoAcc(User user, boolean remembered) {
			if (user == null) {
				Window.alert("Username or password is wrong."); 
			} else {
				//Window.alert("Logged on: "+user.getId()+", "+user.getUser_name()+", with Remember Me: "+remembered);
				System.out.println("Logged on: "+user.getId()+", "+user.getUser_name()+", with Remember Me: "+remembered);
				goToSearch();
			}
		}
		
		public void buttonDoSignUp(String username, String password, String email, String addr, 
				String country, String city, String postal) {
			if (username.length() == 0 || password.length() == 0) {
				Window.alert("Username or password must be filled."); 
			} else {
				Window.alert("Query check on: "+username+", "+password+", from: "+city+", "+country);
				System.out.println("Query check on: "+username+", "+password+", from: "+city+", "+country);
				goToLogIn();
			}
		}


		public void goToPrefList(List<PreferencePark> prefPark) {
			horizontalPanel.clear();
			PreferenceList pList = new PreferenceList(this, prefPark);
			horizontalPanel.add(pList);
		}
		
		public void doSearch(String park, String facility, String neighborhood) {
			park = StringMethods.retString(park);
			facility = StringMethods.retString(facility);
			neighborhood = StringMethods.retString(neighborhood);
			service.getParkList(park, neighborhood, facility);

		}

		public void displayParks(List<Park> parks) {
			horizontalPanel.clear();
			Results results = new Results(this, parks);
			horizontalPanel.add(results);
		}		

		
		
		
		public void warnPopup (String warning) {
			Window.alert(warning);
		}

		public void goToSearch() {
			horizontalPanel.clear();

			Search search = new Search(this, this.facList, this.hoodList);
			horizontalPanel.add(search);
		}

		
	}
	
	

