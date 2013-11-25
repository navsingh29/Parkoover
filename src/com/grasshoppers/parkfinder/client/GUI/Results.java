package com.grasshoppers.parkfinder.client.GUI;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.ValueBoxBase.TextAlignment;
import com.google.gwt.user.client.ui.DecoratedStackPanel;
import com.google.gwt.user.client.ui.DoubleBox;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MenuItem;
import com.google.gwt.user.client.Command;
import com.grasshoppers.parkfinder.client.GUIController;
import com.grasshoppers.parkfinder.client.modeldata.Facility;
import com.grasshoppers.parkfinder.client.modeldata.Park;
import com.grasshoppers.parkfinder.client.modeldata.PreferencePark;
import com.grasshoppers.parkfinder.client.modeldata.User;
import com.grasshoppers.parkfinder.client.widget.map.GoogleMapsWidget;
import com.grasshoppers.parkfinder.client.widget.weather.WeatherViewer;
import com.google.gwt.user.client.ui.Image;

public class Results extends Composite {

	private GUIController controller;
	private GoogleMapsWidget map;
	
	public Results(final GUIController controller, final List<Park> parks) {
		this.controller = controller;
		
		
		VerticalPanel verticalPanel = new VerticalPanel();
		verticalPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		verticalPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		initWidget(verticalPanel);
		verticalPanel.setSize("100%", "100%");
		
		FlexTable flexTable = new FlexTable();
		verticalPanel.add(flexTable);
		verticalPanel.setCellHeight(flexTable, "100%");
		verticalPanel.setCellWidth(flexTable, "100%");
		verticalPanel.setCellHorizontalAlignment(flexTable, HasHorizontalAlignment.ALIGN_CENTER);
		verticalPanel.setCellVerticalAlignment(flexTable, HasVerticalAlignment.ALIGN_MIDDLE);
		flexTable.setSize("100%", "100%");
		
		if (!parks.isEmpty()) {
			Image image = new Image("images/Parkoover10.gif");
			flexTable.setWidget(0, 0, image);
		
			Label label_2 = new Label("results");
			label_2.setStyleName("gwt-Label-Title");
			flexTable.setWidget(1, 0, label_2);
		} else {
			Image image = new Image("images/index.png");
			flexTable.setWidget(0, 0, image);
		
			Label label_2 = new Label("NO MATCH FOUND: UMAD?");
			label_2.setStyleName("gwt-Label-Title");
			flexTable.setWidget(1, 0, label_2);
		}
		MenuBar menuBar_3 = new MenuBar(false);
		flexTable.setWidget(2, 0, menuBar_3);
		menuBar_3.setWidth("100px");
		MenuBar menuBar_4 = new MenuBar(true);
		
		String userMenuName = "Menu";
		if (this.controller.getUser().getFname() != null)
			userMenuName = this.controller.getUser().getFname()+"'s Menu";
		else if (this.controller.getUser().getUser_name() != null)
			userMenuName = this.controller.getUser().getUser_name()+"'s Menu";
		
		MenuItem mntmMenu_1 = new MenuItem(userMenuName, false, menuBar_4);
		
		MenuItem mntmSignOut = new MenuItem("sign out", false, new Command() {
			public void execute() {
				controller.goToLogIn();
			}
		});
		menuBar_4.addItem(mntmSignOut);
		
		
		
		MenuItem menuItemSearch = new MenuItem("Search", false, new Command() {
			public void execute() {
				controller.buttonToSearch();
			}
		});
		menuItemSearch.setHTML("search");
//		mntmPreferenceList_1.setHTML("search");
		menuBar_4.addItem(menuItemSearch);


		
		
		MenuItem mntmPreferenceList_1 = new MenuItem("preference list", false, new Command() {
			public void execute() {
				controller.buttonToPrefList();
			}
		});
		menuBar_4.addItem(mntmPreferenceList_1);
		MenuBar menuBar = new MenuBar(true);
		
		MenuItem menuItem = new MenuItem("sort", false, menuBar);
		
		MenuItem menuItem_1 = new MenuItem("by alphabet", false, new Command() {
			public void execute() {
				controller.sortDisplayParks(parks, 0);
			}
		});
		menuBar.addItem(menuItem_1);
		
		MenuItem menuItem_2 = new MenuItem("by rating", false, new Command() {
			public void execute() {
				controller.sortDisplayParks(parks, 1);
			}
		});
		menuBar.addItem(menuItem_2);
		
		MenuItem menuItem_3 = new MenuItem("by size", false, new Command() {
			public void execute() {
				controller.sortDisplayParks(parks, 2);
			}
		});
		menuBar.addItem(menuItem_3);
		menuBar_4.addItem(menuItem);
		//MenuBar menuBar_5 = new MenuBar(true);
		
/*		MenuItem mntmSort_1 = new MenuItem("sort", false, menuBar_5);
		
		
		
		MenuItem mntmAlphabetical = new MenuItem("alphabetical", false, new Command() {
			public void execute() {
			}
		});
		
		
		mntmAlphabetical.setHTML("ABC");
		menuBar_5.addItem(mntmAlphabetical);
		menuBar_4.addItem(mntmSort_1);*/
		menuBar_3.addItem(mntmMenu_1);
		mntmMenu_1.setWidth("100px");
		
//		WeatherViewer wv = new WeatherViewer(controller);
//		flexTable.setWidget(3, 0, wv);
		//wv.setWidth("100%");
		
	    map = new GoogleMapsWidget(49.2500, -123.1000, "V6T 1Z4", "vancouver");
	    map.clearEverything();
		flexTable.setWidget(4, 0, map);
		
		DecoratedStackPanel decoratedStackPanel = new DecoratedStackPanel();
		flexTable.setWidget(5, 0, decoratedStackPanel);
		decoratedStackPanel.setWidth("100%");
		
		/*
		VerticalPanel verticalPanel_1 = new VerticalPanel();
		decoratedStackPanel.add(verticalPanel_1, "Tynehead", false);
		verticalPanel_1.setSize("100%", "100%");
		
		Label lblNeighbourhood = new Label("facility type:");
		lblNeighbourhood.setStyleName("gwt-Label-Login");
		verticalPanel_1.add(lblNeighbourhood);
		
		Label lblNeighbourhood_1 = new Label("neighbourhood:");
		lblNeighbourhood_1.setStyleName("gwt-Label-Login");
		verticalPanel_1.add(lblNeighbourhood_1);
		
		CheckBox chckbxNewCheckBox = new CheckBox("favourite");
		chckbxNewCheckBox.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
			}
		});
		chckbxNewCheckBox.setStyleName("gwt-Label-Login");
		verticalPanel_1.add(chckbxNewCheckBox);
		*/
		
	//	for (int i = 0; i< parks.size(); i++ ) {
		
		for (final Park park: parks) {
			
			int avgRating = (int) park.getRating();
			StringBuffer rating = new StringBuffer();
			for(int i=0;i<5;i++){
				if (avgRating-->0)
					rating.append("&#9733");
				else rating.append("&#9734");
				
			}
			
			//Park Tab panel
			VerticalPanel verticalPanel_2 = new VerticalPanel();
			//Park Name
			decoratedStackPanel.add(verticalPanel_2, new String(rating) +"  "+ park.getName(), true);
			
			verticalPanel_2.setSize("100%", "100%");

			//Rating
			Label ratingLabel = new Label("Average Rating: "+park.getRating());
			ratingLabel.setStyleName("gwt-Label-Login");
			verticalPanel_2.add(ratingLabel);
			
			//Number of Ratings
			Label ratingsLabel = new Label("Number of Ratings: "+park.getCount());
			ratingsLabel.setStyleName("gwt-Label-Login");
			verticalPanel_2.add(ratingsLabel);
			
			//New Line
			Label emptyLabel = new Label(" ");
			emptyLabel .setStyleName("gwt-Label-Login");
			verticalPanel_2.add(emptyLabel);
			
			//Address 
			Label address = new Label("Address: "+park.getStreet_number()+" "+park.getStreet_name());
			address.setStyleName("gwt-Label-Login");
			verticalPanel_2.add(address);
			
			//Bound Streets
			Label nearBy = new Label("EW Bound: "+park.getEw_street()+", NS Bound "+park.getNs_street());
			nearBy.setStyleName("gwt-Label-Login");
			verticalPanel_2.add(nearBy);
			
			//Size
			Label hectares = new Label("Size: "+park.getHectares()+" Hectares");
			hectares.setStyleName("gwt-Label-Login");
			verticalPanel_2.add(hectares);			
			
			//Hood 
			Label neighbourhood = new Label("Neighbourhood: "+park.getNeighbourhoodName());
			neighbourhood.setStyleName("gwt-Label-Login");
			verticalPanel_2.add(neighbourhood);
			
			//Facs
			List<String> listFac = new ArrayList<String>();
			String listFacName = "Available Facilities: ";
			
			for (Facility fac : park.getFacilityList()) {
				//System.out.println(fac.getType().toString() + "yooooo");
				if (!listFac.contains(fac.getType())) {
					listFacName = listFacName + fac.getType().toString() + " (x"+ fac.getCount()+ "), ";
					listFac.add(fac.getType());
				}
			}
			Label label_FacilityNames = new Label(listFacName);
			label_FacilityNames.setStyleName("gwt-Label-Login");
			verticalPanel_2.add(label_FacilityNames);
			
			//Break line
			Label blank = new Label("=============================================");
			blank.setStyleName("gwt-Label-Login");
			verticalPanel_2.add(blank);
			


			//Fav/Remove 
			boolean isPref = false;
			for (PreferencePark pp : this.controller.getUser().getPreferenceList()) {
				if (pp.getParkId() == park.getParkId()) {
					isPref = true;
					break;
				}
			}
			
			TextBox commBox = null;
			ListBox starBox = null;
			
			String chkBox = "remove this park";
			if (!isPref) {
				Label commentB = new Label("Put your comments here: ");
				commentB.setStyleName("gwt-Label-Login");
				verticalPanel_2.add(commentB);
				
				commBox = new TextBox();
				commBox.setStyleName("gwt-Label-Fields");
				commBox.setText("");
				//flexTable.setWidget(2, 0, commBox);
				verticalPanel_2.add(commBox);
				commBox.setWidth("85%");
				
				Label ratingB = new Label("Rate this Park: ");
				ratingB.setStyleName("gwt-Label-Login");
				verticalPanel_2.add(ratingB);
				
				starBox = new ListBox();
				for (int i=1; i<= 5; i++) {
					starBox.addItem(Integer.toString(i));
				}
				starBox.setStyleName("gwt-Label-Login");
				//flexTable.setWidget(4, 0, starBox);
				starBox.setWidth("15%");
				verticalPanel_2.add(starBox);
				chkBox = "favourite this park";
			}
			
			final TextBox comm = commBox;
			final ListBox lisb = starBox;
			final boolean notInPref = !isPref;
			final Button chckbxFavourite = new Button(chkBox);
			chckbxFavourite.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
					//TODO: make a popup appear when checked that allows user to specify
					// rating and comment, for now they are null
					if (notInPref) {
						
						controller.createNewParkRating(controller.getUser().getId(), park.getParkId(),
								Integer.parseInt(lisb.getValue(lisb.getSelectedIndex())), comm.getValue(),park);
						Window.alert("Park added to preference list.");
						controller.goToSavedResult();
					} else {
						PreferencePark dummyPark = new PreferencePark();
						dummyPark.setParkId(park.getParkId());
						controller.deleteParkRating(controller.getUser().getId(), park.getParkId(), dummyPark);
						Window.alert("Park deleted from preference list.");
						controller.goToSavedResult();
					}
					
				}
			});
			
			chckbxFavourite.setStyleName("gwt-RichTextToolbar");
			verticalPanel_2.add(chckbxFavourite);
			
			Label blank2 = new Label("===========================================");
			blank.setStyleName("gwt-Label-Login");
			verticalPanel_2.add(blank2);
			
			verticalPanel_2.add(new FacebookEventWidget(controller, park));
		
		
		flexTable.getFlexCellFormatter().setColSpan(5, 0, 1);
		flexTable.getCellFormatter().setHorizontalAlignment(5, 0, HasHorizontalAlignment.ALIGN_CENTER);
		flexTable.getCellFormatter().setHorizontalAlignment(4, 0, HasHorizontalAlignment.ALIGN_CENTER);
		flexTable.getCellFormatter().setHorizontalAlignment(3, 0, HasHorizontalAlignment.ALIGN_CENTER);
		flexTable.getCellFormatter().setHorizontalAlignment(2, 0, HasHorizontalAlignment.ALIGN_RIGHT);
		flexTable.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);
		
		//----------------------------------------
		final HorizontalPanel mapButtonPanel = new HorizontalPanel();
		
		//plotPark
		final Button mapPark = new Button("Plot Park");
		mapPark.addClickHandler(new PlotParkClickHandler(park));
		
		mapPark.setStyleName("gwt-RichTextToolbar");
		mapButtonPanel.add(mapPark);
		
		//plotRoute
		final Button plotRoute = new Button("Plot Route");
		plotRoute.addClickHandler(new PlotRouteClickHandler(park,this.controller));
		
		plotRoute.setStyleName("gwt-RichTextToolbar");
		mapButtonPanel.add(plotRoute);
		
		//GointoStreetview
		
		final Button goInToSV = new Button("Street View");
		goInToSV.addClickHandler(new StreetViewClickHandler(park));
		goInToSV.setStyleName("gwt-RichTextToolbar");
		mapButtonPanel.add(goInToSV);
		
		verticalPanel_2.add(mapButtonPanel);
	
		
		//-----------------------------------
		
		
		}

	}
	
	
	private class PlotParkClickHandler implements ClickHandler {

		Park p = null;
		public PlotParkClickHandler(Park p) {
			// TODO Auto-generated constructor stub
			super();
			this.p = p;
		}

		@Override
		public void onClick(ClickEvent event) {
			// TODO Auto-generated method stub
			map.plotPark(p.getMap_x_loc(), p.getMap_y_loc());
		}
		
	}

	
private class PlotRouteClickHandler implements ClickHandler {
		
		GUIController gc;
		Park p = null;
		User user;
		public PlotRouteClickHandler(Park p,GUIController gc) {
			// TODO Auto-generated constructor stub
			super();
			this.p = p;
			this.user =gc.getUser();
		}

		@Override
		public void onClick(ClickEvent event) {
			// TODO Auto-generated method stub
			map.plotRouteFromHomeToPark(user.getAddress(),user.getCity(),p.getMap_x_loc(), p.getMap_y_loc(), "D");
			
			
		}
		
	}
	
private class StreetViewClickHandler implements ClickHandler {
		
		Park p = null;
		public StreetViewClickHandler(Park p) {
			// TODO Auto-generated constructor stub
			super();
			this.p = p;
			
		}

		@Override
		public void onClick(ClickEvent event) {
			map.goToNearestStreetView(p.getMap_x_loc(), p.getMap_y_loc());
			
		}
		
	}

	
	
}

