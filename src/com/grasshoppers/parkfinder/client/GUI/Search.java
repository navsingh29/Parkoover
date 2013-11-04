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

public class Search extends Composite {

	public Search() {
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
				GUIHub.goToLogIn();
			}
		});
		menuBar_1.addItem(mntmSignOut);
		
		MenuItem mntmPreferenceList = new MenuItem("preference list", false, new Command() {
			public void execute() {
				GUIHub.goToPrefList();
			}
		});
		menuBar_1.addItem(mntmPreferenceList);
		menuBar.addItem(mntmMenu);
		mntmMenu.setWidth("100px");
		
		TextBox textBox = new TextBox();
		flexTable.setWidget(2, 0, textBox);
		textBox.setWidth("75%");
		
		ListBox comboBox = new ListBox();
		comboBox.addItem("facility type");
		comboBox.addItem("soccer field");
		comboBox.addItem("park");
		comboBox.addItem("dog park");
		comboBox.setStyleName("gwt-Label-Login");
		flexTable.setWidget(3, 0, comboBox);
		comboBox.setWidth("75%");
		
		ListBox listBox = new ListBox();
		listBox.addItem("neighbourhood");
		listBox.addItem("a");
		listBox.addItem("b");
		listBox.addItem("c");
		listBox.setStyleName("gwt-Label-Login");
		flexTable.setWidget(4, 0, listBox);
		listBox.setWidth("75%");
		
		Button btnNewButton = new Button("New button");
		btnNewButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				List<String> list = new ArrayList<String>();
				list.add("0");
				list.add("holy");
				list.add("shyt");
				list.add("it");
				list.add("works!");
				list.add("want");
				list.add("a");
				list.add("cake? =3=");
				GUIHub.doSearch(list);
			}
		});
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

}

