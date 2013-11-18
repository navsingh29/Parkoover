package com.grasshoppers.parkfinder.client.GUI;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.ValueBoxBase.TextAlignment;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.user.client.Window;
import com.grasshoppers.parkfinder.client.GUIController;
import com.google.gwt.user.client.ui.Image;
import com.grasshoppers.parkfinder.client.widget.weather.WeatherViewer;

public class Login extends Composite {
	private TextBox textBoxUsername;
	private TextBox textBoxPassword;
	private CheckBox chckbxRememberMe;
	private GUIController controller;
	
	public Login(final GUIController controller) {
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
		
		textBoxUsername = new TextBox();
		textBoxUsername.setStyleName("gwt-HorizontalSplitPanel");
		textBoxUsername.setAlignment(TextAlignment.CENTER);
		textBoxUsername.setText("Username");
		flexTable.setWidget(1, 0, textBoxUsername);
		flexTable.getCellFormatter().setStyleName(1, 0, "gwt-Label-Login");
		textBoxUsername.setWidth("75%");
		flexTable.getCellFormatter().setHorizontalAlignment(1, 0, HasHorizontalAlignment.ALIGN_CENTER);
		
		textBoxPassword = new TextBox();
		textBoxPassword.setStyleName("gwt-HorizontalSplitPanel");
		textBoxPassword.setAlignment(TextAlignment.CENTER);
		textBoxPassword.setText("Password");
		flexTable.setWidget(2, 0, textBoxPassword);
		textBoxPassword.setWidth("75%");
		flexTable.getCellFormatter().setWidth(2, 0, "");
		flexTable.getCellFormatter().setHorizontalAlignment(2, 0, HasHorizontalAlignment.ALIGN_CENTER);
		
		chckbxRememberMe = new CheckBox("Remember Me");
		chckbxRememberMe.setChecked(false);
		chckbxRememberMe.setStyleName("gwt-Label-Login");
	//	flexTable.setWidget(3, 0, chckbxRememberMe);
		
		Button btnNewButton = new Button("Sign In");
		btnNewButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				if (textBoxUsername.getText().length() == 0 || textBoxPassword.getText().length() == 0) {
					Window.alert("Username or password is empty."); 
				} else {
					controller.buttonDoSignIn(textBoxUsername.getText(), textBoxPassword.getText(),
							chckbxRememberMe.getValue());	
				}
			}
		});
		btnNewButton.setStyleName("gwt-RichTextToolbar");
		btnNewButton.setText("SIGN IN");
		flexTable.setWidget(4, 0, btnNewButton);
		btnNewButton.setWidth("25%");
		flexTable.getCellFormatter().setVerticalAlignment(4, 0, HasVerticalAlignment.ALIGN_MIDDLE);
		flexTable.getCellFormatter().setHorizontalAlignment(4, 0, HasHorizontalAlignment.ALIGN_CENTER);
		flexTable.getCellFormatter().setHorizontalAlignment(3, 0, HasHorizontalAlignment.ALIGN_CENTER);
		
		Button button = new Button("Sign Up");
		button.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				controller.goToSignUp();
			}
		});
		button.setText("REGISTER");
		button.setStyleName("gwt-RichTextToolbar");
		flexTable.setWidget(5, 0, button);
		button.setWidth("25%");
		flexTable.getCellFormatter().setHorizontalAlignment(5, 0, HasHorizontalAlignment.ALIGN_CENTER);
		flexTable.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);
	}
	
	
	
	

}
