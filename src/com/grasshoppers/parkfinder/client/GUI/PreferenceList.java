package com.grasshoppers.parkfinder.client.GUI;

import java.util.ArrayList;
import java.util.List;

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
import com.grasshoppers.parkfinder.client.widget.map.GoogleMapsWidget;
import com.grasshoppers.parkfinder.client.widget.weather.WeatherViewer;
import com.google.gwt.user.client.ui.Image;

public class PreferenceList extends Composite {

	private GUIController controller;
	
	public PreferenceList(final GUIController controller, final List<PreferencePark> prefPark) {
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
		if (!prefPark.isEmpty()) {
			Image image = new Image("images/Parkoover10.gif");
			flexTable.setWidget(0, 0, image);
	
			Label lblResults = new Label("preference list");
			lblResults.setStyleName("gwt-Label-Title");
			flexTable.setWidget(1, 0, lblResults);
		} else {
			Image image = new Image("images/sadpanda.png");
			flexTable.setWidget(0, 0, image);

			Label lblResults = new Label("SHOW SOME LOVIN' AND FILL ME!");
			lblResults.setStyleName("gwt-Label-Title");
			flexTable.setWidget(1, 0, lblResults);
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
		
		MenuItem mntmPreferenceList_1 = new MenuItem("preference list", false, new Command() {
			public void execute() {
				controller.buttonToSearch();
			}
		});
		mntmPreferenceList_1.setHTML("search");
		menuBar_4.addItem(mntmPreferenceList_1);

		
		MenuItem mntmSort_1 = new MenuItem("last result", false, new Command() {
			public void execute() {
				controller.goToSavedResult();
			}
		});
		
		menuBar_4.addItem(mntmSort_1);
		MenuBar menuBar = new MenuBar(true);
		
		MenuItem mntmNewMenu = new MenuItem("sort", false, menuBar);
		
		MenuItem menuItem = new MenuItem("by alphabet", false, new Command() {
			public void execute() {
				controller.sortPrefParks(prefPark, 0);
			}
		});
		menuBar.addItem(menuItem);
		
		MenuItem menuItem_1 = new MenuItem("by personal ratings", false, new Command() {
			public void execute() {
				controller.sortPrefParks(prefPark, 1);
			}
		});
		menuBar.addItem(menuItem_1);
		
		MenuItem menuItem_2 = new MenuItem("by size", false, new Command() {
			public void execute() {
				controller.sortPrefParks(prefPark, 2);
			}
		});
		menuBar.addItem(menuItem_2);
		menuBar_4.addItem(mntmNewMenu);
		menuBar_3.addItem(mntmMenu_1);
		mntmMenu_1.setWidth("100px");

		WeatherViewer wv = new WeatherViewer(controller);
		flexTable.setWidget(3, 0, wv);
		
		GoogleMapsWidget map = new GoogleMapsWidget();
		flexTable.setWidget(4, 0, map);
		
		DecoratedStackPanel decoratedStackPanel = new DecoratedStackPanel();
		flexTable.setWidget(5, 0, decoratedStackPanel);
		decoratedStackPanel.setWidth("100%");

		for (final PreferencePark park: prefPark) {
			VerticalPanel verticalPanel_2 = new VerticalPanel();
			decoratedStackPanel.add(verticalPanel_2,park.getName(), false);
			verticalPanel_2.setSize("100%", "100%");
			
			//Status
			String offStatus = "Official";
			if (park.getOfficial() == 0)
				offStatus = "Unofficial";
			Label status = new Label("Status: "+offStatus);
			status.setStyleName("gwt-Label-Login");
			verticalPanel_2.add(status);
			
			//Address 
			Label address = new Label("Address: "+park.getStreet_number()+" "+park.getStreet_name());
			address.setStyleName("gwt-Label-Login");
			verticalPanel_2.add(address);
			
			//Bound Streets
			Label nearBy = new Label("EW Bound: "+park.getEw_street()+", NS Bound: "+park.getNs_street());
			nearBy.setStyleName("gwt-Label-Login");
			verticalPanel_2.add(nearBy);
			
			//Coord 
			Label coords = new Label("Coordinates: "+park.getMap_x_loc()+", "+park.getMap_y_loc());
			coords.setStyleName("gwt-Label-Login");
			verticalPanel_2.add(coords);
			
			//Size
			Label hectares = new Label("Size: "+park.getHectares()+" Hectares");
			hectares.setStyleName("gwt-Label-Login");
			verticalPanel_2.add(hectares);	
			
			//Hood
			Label neighbourhood = new Label("Neighbourhood: "+park.getNeighbourhoodName());
			neighbourhood.setStyleName("gwt-Label-Login");
			verticalPanel_2.add(neighbourhood);	
			
			//Hood URL
			Label hoodURL = new Label("URL: "+park.getUrl());
			hoodURL.setStyleName("gwt-Label-Login");
			verticalPanel_2.add(hoodURL);
			
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
			Label blank = new Label("===========================================");
			blank.setStyleName("gwt-Label-Login");
			verticalPanel_2.add(blank);
			
			//Comments
			//Label comment = new Label("My comment: "+park.getComment());
			//comment.setStyleName("gwt-Label-Login");
			//verticalPanel_2.add(comment);
			
			Label commentB = new Label("My comment: (Last Updated: "+park.getTime()+")");
			commentB.setStyleName("gwt-Label-Login");
			verticalPanel_2.add(commentB);
			
			TextBox commBox = new TextBox();
			commBox.setStyleName("gwt-Label-Fields");
			commBox.setText(park.getComment());
			//flexTable.setWidget(2, 0, commBox);
			verticalPanel_2.add(commBox);
			commBox.setWidth("85%");
			
			//Ratings
			final HorizontalPanel ratingPanel = new HorizontalPanel();
			Label rating = new Label("I rated this park: ");
			rating.setStyleName("gwt-Label-Login");
			ratingPanel.add(rating);
			
			ListBox starBox = new ListBox();
			for (int i=1; i<= 5; i++) {
				starBox.addItem(Integer.toString(i));
			}
			starBox.setSelectedIndex(park.getRating() - 1);
			starBox.setStyleName("gwt-Label-Login");
			//flexTable.setWidget(4, 0, starBox);
			starBox.setWidth("100%");
			ratingPanel.add(starBox);
			
			Label ending = new Label("  stars.");
			ending.setStyleName("gwt-Label-Login");
			ratingPanel.add(ending);
			verticalPanel_2.add(ratingPanel);
			
			final TextBox comm = commBox;
			final ListBox lisb = starBox;
			
			final HorizontalPanel buttonPanel = new HorizontalPanel();
			
			//modify
			final Button buttonModify = new Button("save changes");
			buttonModify.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
					Park dummyPark = new Park();
					dummyPark.setParkId(park.getParkId());
					
					controller.modifyParkRating(controller.getUser().getId(), park.getParkId(),
							Integer.parseInt(lisb.getValue(lisb.getSelectedIndex())), comm.getValue(), park);
					
					Window.alert("Changes saved.");
					controller.buttonToPrefList();
				
				}
			});
			
			buttonModify.setStyleName("gwt-RichTextToolbar");
			buttonPanel.add(buttonModify);
			
			//remove
			final Button chckbxFavourite = new Button("remove this park");
			chckbxFavourite.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
					
					controller.deleteParkRating(controller.getUser().getId(), park.getParkId(),park);
					Window.alert("Park deleted from preference list.");
					controller.buttonToPrefList();
				
				}
			});
			
			chckbxFavourite.setStyleName("gwt-RichTextToolbar");
			buttonPanel.add(chckbxFavourite);
			
			verticalPanel_2.add(buttonPanel);
			
			Label blank2 = new Label("===========================================");
			blank2.setStyleName("gwt-Label-Login");
			verticalPanel_2.add(blank2);
			
			verticalPanel_2.add(new FacebookEventWidget(controller, park));
		}
		flexTable.getFlexCellFormatter().setColSpan(5, 0, 1);
		flexTable.getCellFormatter().setHorizontalAlignment(5, 0, HasHorizontalAlignment.ALIGN_CENTER);
		flexTable.getCellFormatter().setHorizontalAlignment(4, 0, HasHorizontalAlignment.ALIGN_CENTER);
		flexTable.getCellFormatter().setHorizontalAlignment(3, 0, HasHorizontalAlignment.ALIGN_CENTER);
		flexTable.getCellFormatter().setHorizontalAlignment(2, 0, HasHorizontalAlignment.ALIGN_RIGHT);
		flexTable.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);
	}

}

