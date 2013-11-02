package com.grasshoppers.parkfinder.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class GUIController extends Composite{

	private VerticalPanel vPanel = new VerticalPanel();
	private TextBox textBox1;
	private Label resultLbl;
	private ServiceController service;
	
	
	public GUIController(ServiceController service) {
	initWidget(vPanel);
	textBox1 = new TextBox();
	Button button1 = new Button("Click For Park");
	button1.addClickHandler(new Button1ClickHandler());
	
		vPanel.add(textBox1);
		vPanel.add(button1);
	//	vPanel.add(new Login());
	//	vPanel.add(new Search());
		
		this.service = service;
	}
	
	
	
	public void setTextBox(String text) {
		textBox1.setText(text);
	}
	
	private class Button1ClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			service.getParkName();
			
		}
		
		
		
		
	}
	
	
}


