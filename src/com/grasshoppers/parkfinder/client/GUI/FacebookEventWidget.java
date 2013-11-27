package com.grasshoppers.parkfinder.client.GUI;

import java.util.Date;
import java.util.Set;
import java.util.TreeSet;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DateBox;
import com.grasshoppers.parkfinder.client.GUIController;
import com.grasshoppers.parkfinder.client.modeldata.Park;
import com.grasshoppers.parkfinder.client.modeldata.PreferencePark;

public class FacebookEventWidget extends Composite {

	
	public FacebookEventWidget(final GUIController controller, final Park park) {
		
		VerticalPanel verticalPanel = new VerticalPanel();
		verticalPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		verticalPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		initWidget(verticalPanel);
		
		
		
		final ListBox eventType = new ListBox();
		eventType.addItem("Select an Activity");
		
		Set<String> featureSet = new TreeSet<String>();
		for(int i=0;i<park.getFacilityList().size();i++){
			String feature = park.getFacilityList().get(i).getType();
			if(!featureSet.contains(feature)){
			featureSet.add(feature);
			eventType.addItem(feature);
			}
		}
		
		eventType.addItem("Picnic");
		eventType.addItem("Sight Seeing");
		eventType.addItem("Other");
		
		
		final TextBox eventDescription  = new TextBox();
		eventDescription.setText("Event Description.");
		eventDescription.setWidth("85%");
		
	//	final Date now = new Date();
	//	final Date tom = new Date(now.getTime()+1000*60*60*24);
		
		final DateBox beginDateBox = new DateBox();
		beginDateBox.setTitle("Choose The Event's Start Date and Time");
		final DateBox endDateBox = new DateBox();
		beginDateBox.setTitle("Choose The Event's End Date and Time");
		
		final Button makeEvent = new Button("Create Facebook Event");
		makeEvent.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				controller.makeFacebookEvent(eventType.getItemText(eventType.getSelectedIndex()), park.getName(), eventDescription.getText(), beginDateBox.getValue(), endDateBox.getValue());
			}
		});
		
		HorizontalPanel startTimeHorizontalPanel = new HorizontalPanel();
		HorizontalPanel endTimeHorizontalPanel = new HorizontalPanel();
		startTimeHorizontalPanel.add(new Label("Event Start Date and Time: "));
		startTimeHorizontalPanel.add(beginDateBox);
		endTimeHorizontalPanel.add(new Label("Event End Date and Time: "));
		endTimeHorizontalPanel.add(endDateBox);
		
		verticalPanel.add(eventType);
		verticalPanel.add(startTimeHorizontalPanel);
		verticalPanel.add(endTimeHorizontalPanel);
		verticalPanel.add(eventDescription);
		verticalPanel.add(makeEvent);
		
	}


	public FacebookEventWidget(final GUIController controller, final PreferencePark park) {
		
		VerticalPanel verticalPanel = new VerticalPanel();
		verticalPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		verticalPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		initWidget(verticalPanel);
		
		
		
		final ListBox eventType = new ListBox();
		eventType.addItem("Select an Activity");
		
		
		Set<String> featureSet = new TreeSet<String>();
		for(int i=0;i<park.getFacilityList().size();i++){
			String feature = park.getFacilityList().get(i).getType();
			if(!featureSet.contains(feature)){
			featureSet.add(feature);
			eventType.addItem(feature);
			}
		}
		
		eventType.addItem("Picnic");
		eventType.addItem("Sight Seeing");
		eventType.addItem("Other");
		
		
		final TextBox eventDescription  = new TextBox();
		eventDescription.setText("Event Description.");
		eventDescription.setWidth("85%");
		
	//	final Date now = new Date();
	//	final Date tom = new Date(now.getTime()+1000*60*60*24);
		
		final DateBox beginDateBox = new DateBox();
		beginDateBox.setTitle("Choose The Event's Start Date and Time");
		final DateBox endDateBox = new DateBox();
		beginDateBox.setTitle("Choose The Event's End Date and Time");
		
		final Button makeEvent = new Button("Create Facebook Event");
		makeEvent.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				controller.makeFacebookEvent(eventType.getItemText(eventType.getSelectedIndex()), park.getName(), eventDescription.getText(), beginDateBox.getValue(), endDateBox.getValue());
			}
		});
		
		HorizontalPanel startTimeHorizontalPanel = new HorizontalPanel();
		HorizontalPanel endTimeHorizontalPanel = new HorizontalPanel();
		startTimeHorizontalPanel.add(new Label("Event Start Date and Time: "));
		startTimeHorizontalPanel.add(beginDateBox);
		endTimeHorizontalPanel.add(new Label("Event End Date and Time: "));
		endTimeHorizontalPanel.add(endDateBox);
		
		verticalPanel.add(eventType);
		verticalPanel.add(startTimeHorizontalPanel);
		verticalPanel.add(endTimeHorizontalPanel);
		verticalPanel.add(eventDescription);
		verticalPanel.add(makeEvent);
		
	}
	
	}
	

