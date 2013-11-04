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

public class Login extends Composite {
	private TextBox textBoxUsername;
	private TextBox textBoxPassword;
	private CheckBox chckbxRememberMe;
	
	public Login() {
		
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
		
		textBoxUsername = new TextBox();
		textBoxUsername.setStyleName("gwt-Label-Login");
		textBoxUsername.setAlignment(TextAlignment.CENTER);
		textBoxUsername.setText("username");
		flexTable.setWidget(1, 0, textBoxUsername);
		flexTable.getCellFormatter().setStyleName(1, 0, "gwt-Label-Login");
		textBoxUsername.setWidth("75%");
		flexTable.getCellFormatter().setHorizontalAlignment(1, 0, HasHorizontalAlignment.ALIGN_CENTER);
		
		textBoxPassword = new TextBox();
		textBoxPassword.setStyleName("gwt-Label-Login");
		textBoxPassword.setAlignment(TextAlignment.CENTER);
		textBoxPassword.setText("password");
		flexTable.setWidget(2, 0, textBoxPassword);
		textBoxPassword.setWidth("75%");
		flexTable.getCellFormatter().setWidth(2, 0, "");
		flexTable.getCellFormatter().setHorizontalAlignment(2, 0, HasHorizontalAlignment.ALIGN_CENTER);
		
		chckbxRememberMe = new CheckBox("remember me");
		chckbxRememberMe.setStyleName("gwt-Label-Login");
		flexTable.setWidget(3, 0, chckbxRememberMe);
		
		Button btnNewButton = new Button("New button");
		btnNewButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				GUIHub.buttonDoSignIn(textBoxUsername.getText(), textBoxPassword.getText(),
						chckbxRememberMe.getValue());				
			}
		});
		btnNewButton.setStyleName("gwt-Label-Login");
		btnNewButton.setText("sign in");
		flexTable.setWidget(4, 0, btnNewButton);
		btnNewButton.setWidth("25%");
		flexTable.getCellFormatter().setVerticalAlignment(4, 0, HasVerticalAlignment.ALIGN_MIDDLE);
		flexTable.getCellFormatter().setHorizontalAlignment(4, 0, HasHorizontalAlignment.ALIGN_CENTER);
		flexTable.getCellFormatter().setHorizontalAlignment(3, 0, HasHorizontalAlignment.ALIGN_CENTER);
		flexTable.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);
		flexTable.getCellFormatter().setVerticalAlignment(0, 0, HasVerticalAlignment.ALIGN_MIDDLE);
		
		Button button = new Button("New button");
		button.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				GUIHub.goToSignUp();
			}
		});
		button.setText("don't have an account? sign up");
		button.setStyleName("gwt-Label-Login");
		flexTable.setWidget(5, 0, button);
		button.setWidth("25%");
		flexTable.getCellFormatter().setHorizontalAlignment(5, 0, HasHorizontalAlignment.ALIGN_CENTER);
	}
	
	
	

}
