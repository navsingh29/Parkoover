package com.grasshoppers.parkfinder.client;

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
import com.grasshoppers.parkfinder.shared.StringMethods;

public class GUIController extends Composite{

	private HorizontalPanel horizontalPanel = new HorizontalPanel();
	private ServiceController service;
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
		

		public void goToPrefList() {
			horizontalPanel.clear();
			PreferenceList pList = new PreferenceList();
			horizontalPanel.add(pList);
		}
		
//=============================================================================================================
//		OnClick Triggers
//=============================================================================================================
		
		public void buttonDoSignIn(String username, String password, Boolean rememberMe ) {
			if (username.length() == 0 || password.length() == 0) {
				Window.alert("Username or password is empty."); 
			} else {
				
				Window.alert("Query check on: "+username+", "+password+", with Remember Me: "+rememberMe);
				System.out.println("Query check on: "+username+", "+password+", with Remember Me: "+rememberMe);
				horizontalPanel.clear();
				Search search = new Search(this);
				horizontalPanel.add(search);
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


		public void doSearch(String park, String facility, String neighborhood) {
			park = StringMethods.retString(park);
			facility = StringMethods.retString(facility);
			neighborhood = StringMethods.retString(neighborhood);
			service.getParkList(park, neighborhood, facility);
		}

		public void displayParks(List<Park> parks) {
			horizontalPanel.clear();
			Results results = new Results(parks);
			horizontalPanel.add(results);
			
		}		
	}
	
	

