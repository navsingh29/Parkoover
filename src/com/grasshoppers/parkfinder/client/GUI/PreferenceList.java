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
import com.google.gwt.user.client.ui.Label;
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
import com.google.gwt.user.client.ui.Image;

public class PreferenceList extends Composite {

	private GUIController controller;
	
	public PreferenceList(final GUIController controller, List<PreferencePark> prefPark) {
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
		
		Image image = new Image("images/Parkoover10.gif");
		flexTable.setWidget(0, 0, image);

		Label lblResults = new Label("preference list");
		lblResults.setStyleName("gwt-Label-Title");
		flexTable.setWidget(1, 0, lblResults);
		
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
		menuBar_3.addItem(mntmMenu_1);
		mntmMenu_1.setWidth("100px");

		DecoratedStackPanel decoratedStackPanel = new DecoratedStackPanel();
		flexTable.setWidget(3, 0, decoratedStackPanel);
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
			Label comment = new Label("My comment: "+park.getComment());
			comment.setStyleName("gwt-Label-Login");
			verticalPanel_2.add(comment);
			
			//Ratings
			Label rating = new Label("I rated this park: "+park.getRating()+" stars.");
			rating.setStyleName("gwt-Label-Login");
			verticalPanel_2.add(rating);
			
			//remove
			final Button chckbxFavourite = new Button("remove this park");
			chckbxFavourite.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
					
					controller.deleteParkRating(controller.getUser().getId(), park.getParkId(),park);
					Window.alert("Park deleted from preference list");
					controller.buttonToPrefList();
				
				}
			});
			
			chckbxFavourite.setStyleName("gwt-RichTextToolbar");
			verticalPanel_2.add(chckbxFavourite);
		}
		flexTable.getFlexCellFormatter().setColSpan(3, 0, 1);
		flexTable.getCellFormatter().setHorizontalAlignment(3, 0, HasHorizontalAlignment.ALIGN_CENTER);
		flexTable.getCellFormatter().setHorizontalAlignment(2, 0, HasHorizontalAlignment.ALIGN_RIGHT);
		flexTable.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);
	}

}

