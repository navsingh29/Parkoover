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
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MenuItem;
import com.google.gwt.user.client.Command;
import com.grasshoppers.parkfinder.client.GUIController;

public class Search extends Composite {

	private TextBox tbParkSearch;
	private ListBox lbNeighborhood;
	private ListBox cbFacility;
	private GUIController controller;
	private List<String> facList;
	private List<String> hoodList;
	

	
	public Search(final GUIController controller, List<String> facList, List<String> hoodList) {
		this.controller = controller;
		this.facList = facList;
		this.hoodList = hoodList;
		
		VerticalPanel verticalPanel = new VerticalPanel();
		verticalPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		verticalPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		initWidget(verticalPanel);
		verticalPanel.setSize("100%", "100%");
		
		FlexTable flexTable = new FlexTable();
		verticalPanel.add(flexTable);
		verticalPanel.setCellWidth(flexTable, "200px");
		verticalPanel.setCellHorizontalAlignment(flexTable, HasHorizontalAlignment.ALIGN_CENTER);
		verticalPanel.setCellVerticalAlignment(flexTable, HasVerticalAlignment.ALIGN_MIDDLE);
		flexTable.setSize("100%", "100%");
		
		Label lblNewLabel = new Label("Parkoover");
		flexTable.setWidget(0, 0, lblNewLabel);
		flexTable.getCellFormatter().setStyleName(0, 0, "gwt-Label-Login");
		
		MenuBar menuBar = new MenuBar(false);
		flexTable.setWidget(1, 0, menuBar);
		menuBar.setWidth("100px");
		MenuBar menuBar_1 = new MenuBar(true);
		
		MenuItem mntmMenu = new MenuItem("menu", false, menuBar_1);
		
		MenuItem mntmSignOut = new MenuItem("sign out", false, new Command() {
			public void execute() {
				controller.goToLogIn();
			}
		});
		menuBar_1.addItem(mntmSignOut);
		
		MenuItem mntmPreferenceList = new MenuItem("preference list", false, new Command() {
			public void execute() {
				controller.buttonToPrefList(1);
			}
		});
		menuBar_1.addItem(mntmPreferenceList);
		menuBar.addItem(mntmMenu);
		mntmMenu.setWidth("100px");
		
		tbParkSearch = new TextBox();
		flexTable.setWidget(2, 0, tbParkSearch);
		tbParkSearch.setWidth("75%");
		
		cbFacility = new ListBox();
		cbFacility.addItem("choose facility type");
		for (String facType : this.facList) {
			cbFacility.addItem(facType);
		}
		
		cbFacility.setStyleName("gwt-Label-Login");
		flexTable.setWidget(3, 0, cbFacility);
		cbFacility.setWidth("75%");
		
		lbNeighborhood = new ListBox();
		lbNeighborhood.addItem("choose neighborhood");
		for (String hoodName : this.hoodList) {
			lbNeighborhood.addItem(hoodName);
		}
		lbNeighborhood.setStyleName("gwt-Label-Login");
		flexTable.setWidget(4, 0, lbNeighborhood);
		lbNeighborhood.setWidth("75%");
		
		Button btnNewButton = new Button("New button");
		btnNewButton.addClickHandler(new SearchClickHandler());
		btnNewButton.setStyleName("gwt-Label-Login");
		btnNewButton.setText("search");
		flexTable.setWidget(5, 0, btnNewButton);
		btnNewButton.setWidth("25%");
		flexTable.getCellFormatter().setVerticalAlignment(5, 0, HasVerticalAlignment.ALIGN_MIDDLE);
		flexTable.getCellFormatter().setHorizontalAlignment(5, 0, HasHorizontalAlignment.ALIGN_CENTER);
		flexTable.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);
		flexTable.getCellFormatter().setVerticalAlignment(0, 0, HasVerticalAlignment.ALIGN_MIDDLE);
		flexTable.getCellFormatter().setHorizontalAlignment(3, 0, HasHorizontalAlignment.ALIGN_CENTER);
		flexTable.getCellFormatter().setHorizontalAlignment(4, 0, HasHorizontalAlignment.ALIGN_CENTER);
		flexTable.getCellFormatter().setHorizontalAlignment(1, 0, HasHorizontalAlignment.ALIGN_RIGHT);
		flexTable.getCellFormatter().setHorizontalAlignment(2, 0, HasHorizontalAlignment.ALIGN_CENTER);
	}
	
	
	private class SearchClickHandler implements ClickHandler {
		 
				public void onClick(ClickEvent event) {
					String park = tbParkSearch.getText();
					
					String facility = cbFacility.getItemText(cbFacility.getSelectedIndex());
					if (facility.contains("choose facility type"))
						facility = "";;
					
					String neighborhood = lbNeighborhood.getItemText(lbNeighborhood.getSelectedIndex());
					if (neighborhood.contains("choose neighborhood"))
						neighborhood = "";;
					
					controller.doSearch(park,neighborhood,facility);
					
					
				}
		
		
	}

}

