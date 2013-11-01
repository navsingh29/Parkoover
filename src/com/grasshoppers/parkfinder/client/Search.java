package com.grasshoppers.parkfinder.client;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.NumberLabel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.DeckPanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ValueBoxBase.TextAlignment;
import com.google.gwt.user.client.ui.CheckBox;

public class Search extends Composite {

	public Search() {
		
		FlexTable flexTable = new FlexTable();
		initWidget(flexTable);
		flexTable.setSize("557px", "100%");
		
		TextBox txtbxSearchMe = new TextBox();
		txtbxSearchMe.setText("Search Me!");
		txtbxSearchMe.setStyleName("gwt-Label-Login");
		txtbxSearchMe.setAlignment(TextAlignment.CENTER);
		flexTable.setWidget(2, 0, txtbxSearchMe);
		txtbxSearchMe.setWidth("75%");
		
		Button button = new Button("New button");
		button.setText("GO");
		button.setStyleName("gwt-Label-Login");
		flexTable.setWidget(4, 0, button);
		button.setWidth("25%");
		flexTable.getCellFormatter().setHorizontalAlignment(2, 0, HasHorizontalAlignment.ALIGN_CENTER);
		flexTable.getCellFormatter().setHorizontalAlignment(4, 0, HasHorizontalAlignment.ALIGN_CENTER);
	}

}
