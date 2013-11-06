package com.grasshoppers.parkfinder.client.GUI;

import java.util.ArrayList;
import java.util.Collections;
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

public class Results extends Composite {

	private GUIController controller;
	
	public Results(final GUIController controller, List<Park> parks) {
		this.controller = controller;
		
		
		VerticalPanel verticalPanel = new VerticalPanel();
		verticalPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		verticalPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		initWidget(verticalPanel);
		verticalPanel.setSize("100%", "100%");
		
		FlexTable flexTable = new FlexTable();
		verticalPanel.add(flexTable);
		verticalPanel.setCellHorizontalAlignment(flexTable, HasHorizontalAlignment.ALIGN_CENTER);
		verticalPanel.setCellVerticalAlignment(flexTable, HasVerticalAlignment.ALIGN_MIDDLE);
		flexTable.setSize("100%", "100%");
		
		Label lblNewLabel = new Label("Parkoover");
		flexTable.setWidget(0, 0, lblNewLabel);
		flexTable.getCellFormatter().setStyleName(0, 0, "gwt-Label-Login");
		flexTable.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);
		flexTable.getCellFormatter().setVerticalAlignment(0, 0, HasVerticalAlignment.ALIGN_MIDDLE);
		
		Label label_2 = new Label("results");
		label_2.setStyleName("gwt-Label-Login");
		flexTable.setWidget(1, 0, label_2);
		
		MenuBar menuBar_3 = new MenuBar(false);
		flexTable.setWidget(2, 0, menuBar_3);
		menuBar_3.setWidth("100px");
		MenuBar menuBar_4 = new MenuBar(true);
		
		MenuItem mntmMenu_1 = new MenuItem("menu", false, menuBar_4);
		
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
//		mntmPreferenceList_1.setHTML("search");
		menuBar_4.addItem(menuItemSearch);


		
		
		MenuItem mntmPreferenceList_1 = new MenuItem("preference list", false, new Command() {
			public void execute() {
				controller.buttonToPrefList(1);
			}
		});
		menuBar_4.addItem(mntmPreferenceList_1);
		MenuBar menuBar_5 = new MenuBar(true);
		
		MenuItem mntmSort_1 = new MenuItem("sort", false, menuBar_5);
		
		
		
		MenuItem mntmAlphabetical = new MenuItem("alphabetical", false, new Command() {
			public void execute() {
			}
		});
		
		
		mntmAlphabetical.setHTML("ABC");
		menuBar_5.addItem(mntmAlphabetical);
		menuBar_4.addItem(mntmSort_1);
		menuBar_3.addItem(mntmMenu_1);
		mntmMenu_1.setWidth("100px");
		
		DecoratedStackPanel decoratedStackPanel = new DecoratedStackPanel();
		flexTable.setWidget(3, 0, decoratedStackPanel);
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
		for (Park park: parks) {
			VerticalPanel verticalPanel_2 = new VerticalPanel();
			decoratedStackPanel.add(verticalPanel_2, park.getName(), false);
			verticalPanel_2.setSize("100%", "100%");
			
			Label address = new Label("Address: "+park.getStreet_number()+" "+park.getStreet_name());
			address.setStyleName("gwt-Label-Login");
			verticalPanel_2.add(address);
			
			
			Label neighbourhood = new Label("Neighbourhood: "+park.getNeighbourhoodName());
			neighbourhood.setStyleName("gwt-Label-Login");
			verticalPanel_2.add(neighbourhood);
			
			Label hectares = new Label("Size: "+park.getHectares()+" Hectares");
			hectares.setStyleName("gwt-Label-Login");
			verticalPanel_2.add(hectares);			
			
			String listFacName = "";
			for (Facility fac : park.getFacilityList()) {
				listFacName.concat(fac.getType());
			}
			Label label_FacilityNames = new Label("Available Facilities: "+listFacName);
			label_FacilityNames.setStyleName("gwt-Label-Login");
			verticalPanel_2.add(label_FacilityNames);
			
			CheckBox chckbxFavourite = new CheckBox("favourite");
			chckbxFavourite.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
					Window.alert("changed: ");
				}
			});
			
			chckbxFavourite.setStyleName("gwt-Label-Login");
			verticalPanel_2.add(chckbxFavourite);
		}
		
		flexTable.getFlexCellFormatter().setColSpan(3, 0, 1);
		flexTable.getCellFormatter().setHorizontalAlignment(3, 0, HasHorizontalAlignment.ALIGN_CENTER);
		flexTable.getCellFormatter().setHorizontalAlignment(2, 0, HasHorizontalAlignment.ALIGN_RIGHT);
	}

}

